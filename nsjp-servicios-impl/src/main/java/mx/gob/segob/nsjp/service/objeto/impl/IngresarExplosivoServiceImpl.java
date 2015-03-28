/**
 * Nombre del Programa : IngresarExplosivoServiceImpl.java
 * Autor                            : Tattva-IT
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 9 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementacion del Contrato del servicio para consultar un explosivo de acuerdo a ciertos parametros de busqueda.
 * Programa Dependiente  :N/A
 * Programa Subsecuente :N/A
 * Cond. de ejecucion        :N/A
 * Dias de ejecucion          :N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                       :N/A
 * Compania               :N/A
 * Proyecto                 :N/A                                 Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.objeto.impl;

import mx.gob.segob.nsjp.comun.enums.elemento.Elementos;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.objeto.Objetos;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.elemento.CalidadDAO;
import mx.gob.segob.nsjp.dao.objeto.ExplosivoDAO;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.objeto.ExplosivoDTO;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.Explosivo;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.objeto.IngresarExplosivoService;
import mx.gob.segob.nsjp.service.objeto.impl.transform.ExplosivoTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del Contrato del servicio para consultar un explosivo de acuerdo a ciertos parametros de busqueda.
 * @version 1.0
 * @author Tattva-IT
 *
 */
@Service
@Transactional
public class IngresarExplosivoServiceImpl implements IngresarExplosivoService {

    private final static Logger logger = Logger.getLogger(IngresarExplosivoServiceImpl.class);
    @Autowired
    private ExplosivoDAO explosivoDAO;
    
    @Autowired
    private GuardaOActualizaEvidencia guardaOActualizaEvidencia;
    
    /**
     * Objeto de Acceso a Datos para la entidad Calidad
     */
    @Autowired
    private CalidadDAO calidadDAO;

    /**
     * {@inheritDoc}
     */
    @Override
    public Long ingresarExplosivo(ExplosivoDTO explosivoDto)
            throws NSJPNegocioException {
        if(explosivoDto == null){
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        if(explosivoDto.getElementoId() == null || explosivoDto.getElementoId() == 0){
            return guardar(explosivoDto);
        }else{
            return actualiza(explosivoDto);
        }
    }

    private Long guardar(ExplosivoDTO explosivoDto) throws NSJPNegocioException {
        Long idCalidad = 0L;
        Calidad cal = new Calidad();
        Long idExplosivo = 0L;
        Explosivo expl = new Explosivo();
        if (logger.isDebugEnabled()) {
            logger.debug("Inicia almacenamiento del Explosivo");
        }
        if (explosivoDto == null) {
            logger.error("El explosivo es requerido para el guardado");
            throw new NSJPNegocioException(CodigoError.FORMATO);
        }
        if (explosivoDto.getExpedienteDTO() == null ||
                explosivoDto.getExpedienteDTO().getExpedienteId() == null) {
            logger.error("El expediente es requerido para el guardado");
            throw new NSJPNegocioException(CodigoError.FORMATO);
        }
        expl = ExplosivoTransformer.tranformarExplosivo(explosivoDto);
        logger.debug("Verificando Calidad");
        CalidadDTO calidadDTO = explosivoDto.getCalidadDTO();
        if (calidadDTO != null &&
                calidadDTO.getDescripcionEstadoFisico() != null) {
            cal.setDescripcionEstadoFisico(
                    calidadDTO.getDescripcionEstadoFisico());
        }
        if (calidadDTO != null &&
                calidadDTO.getCalidades() != null) {
            cal.setTipoCalidad(
                    new Valor(calidadDTO.getCalidades().getValorId()));
        }
        idCalidad = this.calidadDAO.create(cal);
        logger.debug("Insertando Calidad " + idCalidad);
        cal.setCalidadId(idCalidad);
        expl.setCalidad(cal);
        expl.setValorByTipoObjetoVal(new Valor(Objetos.EXPLOSIVO.getValorId()));
        expl.setTipoElemento(new Valor(Elementos.OBJETO.getValorId()));
        idExplosivo = this.explosivoDAO.create(expl);
        explosivoDto.setElementoId(idExplosivo);
        guardaOActualizaObjetos(explosivoDto);
        logger.debug("Se genero el explosivo con id: " + idExplosivo);
        return idExplosivo;
    }

    private Long actualiza(ExplosivoDTO explosivoDto) throws NSJPNegocioException {
        if(explosivoDto.getElementoId() < 0){
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        Explosivo actualizable = explosivoDAO.read(explosivoDto.getElementoId());
        Explosivo actualizador =
                ExplosivoTransformer.tranformarExplosivo(explosivoDto);
        IngresarVehiculoServiceImpl.actualizaObjeto(actualizador, actualizable);
        return explosivoDto.getElementoId();
    }
    
    private void guardaOActualizaObjetos(ExplosivoDTO explosivoDto) throws NSJPNegocioException {
        CadenaDeCustodiaDTO cadenaDeCustodia = explosivoDto.getCadenaDeCustodia();
        if (cadenaDeCustodia != null) {
            guardaOActualizaEvidencia.guardaOActualizaEvidencia(explosivoDto);
        }
    }
}

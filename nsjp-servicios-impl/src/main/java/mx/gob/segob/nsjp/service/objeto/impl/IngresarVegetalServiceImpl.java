/**
 * Nombre del Programa : IngresarVegetalServiceImpl.java
 * Autor                            : Hugo Serrano
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 13 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementacion del servicios para ingresar un vegetal
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

import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.elemento.Elementos;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.objeto.Objetos;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.elemento.CalidadDAO;
import mx.gob.segob.nsjp.dao.objeto.VegetalDAO;
import mx.gob.segob.nsjp.dto.objeto.JoyaDTO;
import mx.gob.segob.nsjp.dto.objeto.VegetalDTO;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.model.Vegetal;
import mx.gob.segob.nsjp.service.objeto.IngresarVegetalService;
import mx.gob.segob.nsjp.service.objeto.impl.transform.VegetalTransformer;

/**
 * Implementacion del servicios para ingresar un vegetal
 * @version 1.0
 * @author Tattva-IT
 *
 */
@Service
@Transactional
public class IngresarVegetalServiceImpl implements IngresarVegetalService {

    private final static Logger logger = Logger.getLogger(IngresarVegetalServiceImpl.class);
    /**
     * Objeto de Acceso a Datos para la entidad Calidad
     */
    @Autowired
    private CalidadDAO calidadDAO;
    @Autowired
    private VegetalDAO vegetalDAO;
    
    @Autowired
    private GuardaOActualizaEvidencia guardaOActualizaEvidencia;

    /**
     * {@inheritDoc}
     */
    @Override
    public Long ingresarVegetalService(VegetalDTO vegetalDto)
            throws NSJPNegocioException {
        if (vegetalDto == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        Long elementoId = vegetalDto.getElementoId();
        if (elementoId == null || elementoId == 0) {
            return guarda(vegetalDto);
        }else{
            return actualiza(vegetalDto);
        }
    }

    private Long guarda(VegetalDTO vegetalDto) throws NSJPNegocioException {
        Long idVegetal = 0L;
        Vegetal veg = new Vegetal();
        Long idCalidad = 0L;
        Calidad cal = new Calidad();
        if (logger.isDebugEnabled()) {
            logger.debug("Inicia almacenamiento del Vegetal");
        }
        if (vegetalDto == null) {
            logger.error("El vegetal es requerido para el guardado");
            throw new NSJPNegocioException(CodigoError.FORMATO);
        }
        ExpedienteDTO expedienteDTO = vegetalDto.getExpedienteDTO();
        if (expedienteDTO == null || expedienteDTO.getExpedienteId() == null) {
            logger.error("El vegetal es requerido para el guardado");
            throw new NSJPNegocioException(CodigoError.FORMATO);
        }
        logger.info("Vegetal    !!!!!!!");
        veg = VegetalTransformer.transformarVegetal(vegetalDto);
        CalidadDTO calidadDTO = vegetalDto.getCalidadDTO();
        if (calidadDTO != null && calidadDTO.getDescripcionEstadoFisico() != null) {
            cal.setDescripcionEstadoFisico(calidadDTO.getDescripcionEstadoFisico());
        }
        if (calidadDTO != null && calidadDTO.getCalidades() != null) {
            cal.setTipoCalidad(new Valor(calidadDTO.getCalidades().getValorId()));
        }
        idCalidad = this.calidadDAO.create(cal);
        logger.debug("Insertando Calidad " + idCalidad);
        veg.setCalidad(this.calidadDAO.read(idCalidad));
        veg.setValorByTipoObjetoVal(new Valor(Objetos.VEGETAL.getValorId()));
        veg.setTipoElemento(new Valor(Elementos.OBJETO.getValorId()));
        idVegetal = this.vegetalDAO.create(veg);
        vegetalDto.setElementoId(idVegetal);
        guardaOActualizaObjetos(vegetalDto);
        logger.debug("Se genero el vegetal con id: " + idVegetal);
        return idVegetal;
    }

    private Long actualiza(VegetalDTO vegetalDto) throws NSJPNegocioException {
        Long elementoId = vegetalDto.getElementoId();
        if (elementoId < 0) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        Vegetal actualizable = vegetalDAO.read(elementoId);
        Vegetal actualizador = VegetalTransformer.transformarVegetal(vegetalDto);
        IngresarVehiculoServiceImpl.actualizaObjeto(actualizador, actualizable);
        return elementoId;
    }
    
    private void guardaOActualizaObjetos(VegetalDTO vegetalDto) throws NSJPNegocioException {
        CadenaDeCustodiaDTO cadenaDeCustodia = vegetalDto.getCadenaDeCustodia();
        if (cadenaDeCustodia != null) {
            guardaOActualizaEvidencia.guardaOActualizaEvidencia(vegetalDto);
        }
    }
}

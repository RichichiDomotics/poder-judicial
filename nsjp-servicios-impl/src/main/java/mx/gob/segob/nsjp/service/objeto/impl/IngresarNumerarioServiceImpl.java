/**
 * Nombre del Programa : IngresarNumerarioServiceImpl.java
 * Autor                            : Hugo Serrano
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 12 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementacion del servicios para ingresar numerario
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
import mx.gob.segob.nsjp.dao.objeto.NumerarioDAO;
import mx.gob.segob.nsjp.dto.objeto.NumerarioDTO;
import mx.gob.segob.nsjp.dto.objeto.VegetalDTO;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.Numerario;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.objeto.IngresarNumerarioService;
import mx.gob.segob.nsjp.service.objeto.impl.transform.NumerarioTransformer;

/**
 * Implementacion del servicios para ingresar numerario
 * @version 1.0
 * @author Tattva-IT
 *
 */
@Service
@Transactional
public class IngresarNumerarioServiceImpl implements IngresarNumerarioService {

    private final static Logger logger = Logger.getLogger(IngresarNumerarioServiceImpl.class);
    /**
     * Objeto de Acceso a Datos para la entidad Calidad
     */
    @Autowired
    private CalidadDAO calidadDAO;
    @Autowired
    private NumerarioDAO numerarioDAO;
    
    @Autowired
    private GuardaOActualizaEvidencia guardaOActualizaEvidencia;

    /**
     * {@inheritDoc}
     */
    @Override
    public Long ingresarNumerario(NumerarioDTO numerarioDTO)
            throws NSJPNegocioException {
        if (numerarioDTO == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        Long elementoId = numerarioDTO.getElementoId();
        if (elementoId == null || elementoId == 0) {
            return guardar(numerarioDTO);
        } else {
            return actualiza(numerarioDTO);
        }
    }

    private Long guardar(NumerarioDTO numerarioDTO) throws NSJPNegocioException {
        Long idNumerario = 0L;
        Numerario num = new Numerario();
        Long idCalidad = 0L;
        Calidad cal = new Calidad();
        if (logger.isDebugEnabled()) {
            logger.debug("Inicia almacenamiento del numerario");
        }
        if (numerarioDTO == null) {
            logger.error("El numerario es requerido para el guardado");
            throw new NSJPNegocioException(CodigoError.FORMATO);
        }
        ExpedienteDTO expedienteDTO = numerarioDTO.getExpedienteDTO();
        if (expedienteDTO == null || expedienteDTO.getExpedienteId() == null) {
            logger.error("El numerario es requerido para el guardado");
            throw new NSJPNegocioException(CodigoError.FORMATO);
        }
        logger.info("Numerario    !!!!!!!");
        num = NumerarioTransformer.transformarNumerario(numerarioDTO);
        CalidadDTO calidadDTO = numerarioDTO.getCalidadDTO();
        if (calidadDTO != null && calidadDTO.getDescripcionEstadoFisico() != null) {
            cal.setDescripcionEstadoFisico(calidadDTO.getDescripcionEstadoFisico());
        }
        if (calidadDTO != null && calidadDTO.getCalidades() != null) {
            cal.setTipoCalidad(new Valor(calidadDTO.getCalidades().getValorId()));
        }
        idCalidad = this.calidadDAO.create(cal);
        logger.debug("Insertando Calidad " + idCalidad);
        cal.setCalidadId(idCalidad);
        num.setCalidad(cal);
        num.setValorByTipoObjetoVal(new Valor(Objetos.NUMERARIO.getValorId()));
        num.setTipoElemento(new Valor(Elementos.OBJETO.getValorId()));
        idNumerario = this.numerarioDAO.create(num);
        numerarioDTO.setElementoId(idNumerario);
        guardaOActualizaObjetos(numerarioDTO);
        logger.debug("Se genero el numerarioS con id: " + idNumerario);
        return idNumerario;
    }

    private Long actualiza(NumerarioDTO numerarioDTO) throws NSJPNegocioException {
        Long elementoId = numerarioDTO.getElementoId();
        if (elementoId < 0) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        Numerario actualizable = numerarioDAO.read(elementoId);
        Numerario actualizador = NumerarioTransformer.transformarNumerario(numerarioDTO);
        IngresarVehiculoServiceImpl.actualizaObjeto(actualizador, actualizable);
        return elementoId;
    }
    
    private void guardaOActualizaObjetos(NumerarioDTO numerarioDTO) throws NSJPNegocioException {
        CadenaDeCustodiaDTO cadenaDeCustodia = numerarioDTO.getCadenaDeCustodia();
        if (cadenaDeCustodia != null) {
            guardaOActualizaEvidencia.guardaOActualizaEvidencia(numerarioDTO);
        }
    }
}

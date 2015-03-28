/**
 * Nombre del Programa : IngresarEmbarcacionServiceImpl.java
 * Autor                            : Hugo Serrano
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 12 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Describir el objetivo de la clase de manera breve
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
import mx.gob.segob.nsjp.dao.objeto.EmbarcacionDAO;
import mx.gob.segob.nsjp.dto.objeto.EmbarcacionDTO;
import mx.gob.segob.nsjp.dto.objeto.NumerarioDTO;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.Embarcacion;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.objeto.IngresarEmbarcacionService;
import mx.gob.segob.nsjp.service.objeto.impl.transform.EmbarcacionTransformer;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author Tattva-IT
 *
 */
@Service
@Transactional
public class IngresarEmbarcacionServiceImpl implements
        IngresarEmbarcacionService {

    private final static Logger logger = Logger.getLogger(IngresarEmbarcacionServiceImpl.class);
    /**
     * Objeto de Acceso a Datos para la entidad Calidad
     */
    @Autowired
    private CalidadDAO calidadDAO;
    @Autowired
    private EmbarcacionDAO embarcacionDAO;
    
    @Autowired
    private GuardaOActualizaEvidencia guardaOActualizaEvidencia;

    /**
     * {@inheritDoc}
     */
    @Override
    public Long ingresarEmbarcacion(EmbarcacionDTO embarcacionDto)
            throws NSJPNegocioException {
        if (embarcacionDto == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        Long elementoId = embarcacionDto.getElementoId();
        if (elementoId == null || elementoId == 0) {
            return guarda(embarcacionDto);
        }else{
            return actualiza(embarcacionDto);
        }
    }

    private Long guarda(EmbarcacionDTO embarcacionDto) throws NSJPNegocioException {
        Embarcacion emb = new Embarcacion();
        Long idEmbarcacion = 0L;
        Long idCalidad = 0L;
        Calidad cal = new Calidad();
        if (logger.isDebugEnabled()) {
            logger.debug("Inicia almacenamiento de la Embarcacion");
        }
        if (embarcacionDto == null) {
            logger.error("La embarcacion es requerida para el guardado");
            throw new NSJPNegocioException(CodigoError.FORMATO);
        }
        ExpedienteDTO expedienteDTO = embarcacionDto.getExpedienteDTO();
        if (expedienteDTO == null ||
                expedienteDTO.getExpedienteId() == null) {
            logger.error("La embarcacion es requerida para el guardado");
            throw new NSJPNegocioException(CodigoError.FORMATO);
        }
        logger.info("Embarcacion    !!!!!!!");
        emb = EmbarcacionTransformer.transformarEmbarcacion(embarcacionDto);
        CalidadDTO calidadDTO = embarcacionDto.getCalidadDTO();
        if (calidadDTO != null && calidadDTO.getDescripcionEstadoFisico() != null) {
            cal.setDescripcionEstadoFisico(calidadDTO.getDescripcionEstadoFisico());
        }
        if (calidadDTO != null && calidadDTO.getCalidades() != null) {
            cal.setTipoCalidad(new Valor(calidadDTO.getCalidades().getValorId()));
        }
        idCalidad = this.calidadDAO.create(cal);
        logger.debug("Insertando Calidad " + idCalidad);
        cal.setCalidadId(idCalidad);
        emb.setCalidad(cal);
        emb.setValorByTipoObjetoVal(new Valor(Objetos.EMBARCACION.getValorId()));
        emb.setTipoElemento(new Valor(Elementos.OBJETO.getValorId()));
        idEmbarcacion = this.embarcacionDAO.create(emb);
        embarcacionDto.setElementoId(idEmbarcacion);
        guardaOActualizaObjetos(embarcacionDto);
        logger.debug("Se genero la embarcacion con id: " + idEmbarcacion);
        return idEmbarcacion;
    }

    private Long actualiza(EmbarcacionDTO embarcacionDto) throws NSJPNegocioException {
        Long elementoId = embarcacionDto.getElementoId();
        if (elementoId < 0) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        Embarcacion actualizable = embarcacionDAO.read(elementoId);
        Embarcacion actualizador = EmbarcacionTransformer.transformarEmbarcacion(embarcacionDto);
        IngresarVehiculoServiceImpl.actualizaObjeto(actualizador, actualizable);
        return elementoId;
    }
    
    private void guardaOActualizaObjetos(EmbarcacionDTO embarcacionDto) throws NSJPNegocioException {
        CadenaDeCustodiaDTO cadenaDeCustodia = embarcacionDto.getCadenaDeCustodia();
        if (cadenaDeCustodia != null) {
            guardaOActualizaEvidencia.guardaOActualizaEvidencia(embarcacionDto);
        }
    }
}

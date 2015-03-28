/**
 * Nombre del Programa : IngresarSustanciaServiceImpl.java
 * Autor                            : Tattva-IT
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 10 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementacion del servicios para ingresar una sustanciad
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
import mx.gob.segob.nsjp.dao.objeto.SustanciaDAO;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.objeto.SustanciaDTO;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.Sustancia;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.objeto.IngresarSustanciaService;
import mx.gob.segob.nsjp.service.objeto.impl.transform.SustanciaTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del servicios para ingresar una sustancia
 * @version 1.0
 * @author Tattva-IT
 *
 */
@Service
@Transactional
public class IngresarSustanciaServiceImpl implements IngresarSustanciaService {
    /**
     * Logger
     */
    private final static Logger logger = Logger.getLogger(IngresarSustanciaServiceImpl.class);
    /**
     * Objeto de Acceso a Datos para la entidad Calidad
     */
    @Autowired
    private CalidadDAO calidadDAO;
    @Autowired
    private SustanciaDAO sustanciaDAO;
    
    @Autowired
    private GuardaOActualizaEvidencia guardaOActualizaEvidencia;

    /**
     * {@inheritDoc}
     */
    @Override
    public Long ingresarSustancia(SustanciaDTO sustanciaDto)
            throws NSJPNegocioException {
        if (sustanciaDto == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        Long elementoId = sustanciaDto.getElementoId();
        if (elementoId == null || elementoId == 0) {
            return guardar(sustanciaDto);
        }else{
            return actualiza(sustanciaDto);
        }
    }

    private Long guardar(SustanciaDTO sustanciaDto) throws NSJPNegocioException {
        Long idCalidad = 0L;
        Calidad cal = new Calidad();
        Long idSustancia = 0L;
        Sustancia sust = new Sustancia();
        if (logger.isDebugEnabled()) {
            logger.debug("Inicia almacenamiento de Sustancia");
        }
        if (sustanciaDto == null) {
            logger.error("La sustancia es requerida para el guardado");
            throw new NSJPNegocioException(CodigoError.FORMATO);
        }
        if (sustanciaDto.getExpedienteDTO() == null
                || sustanciaDto.getExpedienteDTO().getExpedienteId() == null) {
            logger.error("La sustancia es requerido para el guardado");
            throw new NSJPNegocioException(CodigoError.FORMATO);
        }
        logger.info("Sustancia    !!!!!!!");
        sust = SustanciaTransformer.transformarSustancia(sustanciaDto);
        CalidadDTO calidadDTO = sustanciaDto.getCalidadDTO();
        if (calidadDTO != null
                && calidadDTO.getDescripcionEstadoFisico() != null) {
            cal.setDescripcionEstadoFisico(calidadDTO.getDescripcionEstadoFisico());
        }
        if (calidadDTO != null && calidadDTO.getCalidades() != null) {
            cal.setTipoCalidad(new Valor(calidadDTO.getCalidades().getValorId()));
        }
        idCalidad = this.calidadDAO.create(cal);
        logger.debug("Insertando Calidad " + idCalidad);
        cal.setCalidadId(idCalidad);
        sust.setCalidad(cal);
        sust.setValorByTipoObjetoVal(new Valor(Objetos.SUSTANCIA.getValorId()));
        sust.setTipoElemento(new Valor(Elementos.OBJETO.getValorId()));
        idSustancia = this.sustanciaDAO.create(sust);
        sustanciaDto.setElementoId(idSustancia);
        guardaOActualizaObjetos(sustanciaDto);
        logger.debug("Se genero la sustancia con id: " + idSustancia);
        return idSustancia;
    }

    private Long actualiza(SustanciaDTO sustanciaDto) throws NSJPNegocioException {
        Long elementoId = sustanciaDto.getElementoId();
        if (elementoId < 0) {
         throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        Sustancia actualizable = sustanciaDAO.read(elementoId);
        Sustancia actualizador = SustanciaTransformer.transformarSustancia(sustanciaDto);
        IngresarVehiculoServiceImpl.actualizaObjeto(actualizador, actualizable);
        return elementoId;
    }
    
    private void guardaOActualizaObjetos(SustanciaDTO sustanciaDto) throws NSJPNegocioException {
        CadenaDeCustodiaDTO cadenaDeCustodia = sustanciaDto.getCadenaDeCustodia();
        if (cadenaDeCustodia != null) {
            guardaOActualizaEvidencia.guardaOActualizaEvidencia(sustanciaDto);
        }
    }
}

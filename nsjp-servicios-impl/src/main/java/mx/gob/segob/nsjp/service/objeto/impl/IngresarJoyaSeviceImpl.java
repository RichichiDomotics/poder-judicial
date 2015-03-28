/**
 * Nombre del Programa : IngresarJoyaSevice.java
 * Autor                            : Hugo Serrano
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 13 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementacion del servicios para ingresar una joya
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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.elemento.Elementos;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.objeto.Objetos;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.elemento.CalidadDAO;
import mx.gob.segob.nsjp.dao.objeto.JoyaDAO;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.objeto.ArmaDTO;
import mx.gob.segob.nsjp.dto.objeto.JoyaDTO;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.model.Joya;
import mx.gob.segob.nsjp.service.objeto.IngresarJoyaService;
import mx.gob.segob.nsjp.service.objeto.impl.transform.JoyaTransformer;

/**
 * Implementacion del servicios para ingresar una joya 
 * @version 1.0
 * @author Tattva-IT
 *
 */
@Service
@Transactional
public class IngresarJoyaSeviceImpl implements IngresarJoyaService {

    private final static Logger logger = Logger.getLogger(IngresarJoyaSeviceImpl.class);
    /**
     * Objeto de Acceso a Datos para la entidad Calidad
     */
    @Autowired
    private CalidadDAO calidadDAO;
    @Autowired
    private JoyaDAO joyaDAO;

    @Autowired
    private GuardaOActualizaEvidencia guardaOActualizaEvidencia;
    /**
     * {@inheritDoc}
     */
    @Override
    public Long ingresarJoya(JoyaDTO joyaDto)
            throws NSJPNegocioException {
        if (joyaDto == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        Long elementoId = joyaDto.getElementoId();
        if (elementoId == null || elementoId == 0) {
            return guarda(joyaDto);
        } else {
            return actualiza(joyaDto);
        }
    }

    private Long guarda(JoyaDTO joyaDto) throws NSJPNegocioException {
        Long idJoya = 0L;
        Joya joya = new Joya();
        Long idCalidad = 0L;
        Calidad cal = new Calidad();
        if (logger.isDebugEnabled()) {
            logger.debug("Inicia almacenamiento del Joya");
        }
        if (joyaDto == null) {
            logger.error("El joya es requerido para el guardado");
            throw new NSJPNegocioException(CodigoError.FORMATO);
        }
        ExpedienteDTO expedienteDTO = joyaDto.getExpedienteDTO();
        if (expedienteDTO == null || expedienteDTO.getExpedienteId() == null) {
            logger.error("El joya es requerido para el guardado");
            throw new NSJPNegocioException(CodigoError.FORMATO);
        }
        logger.info("Joya    !!!!!!!");
        joya = JoyaTransformer.trasnformarJoya(joyaDto);
        CalidadDTO calidadDTO = joyaDto.getCalidadDTO();
        if (calidadDTO != null && calidadDTO.getDescripcionEstadoFisico() != null) {
            cal.setDescripcionEstadoFisico(calidadDTO.getDescripcionEstadoFisico());
        }
        if (calidadDTO != null && calidadDTO.getCalidades() != null) {
            cal.setTipoCalidad(new Valor(calidadDTO.getCalidades().getValorId()));
        }
        idCalidad = this.calidadDAO.create(cal);
        logger.debug("Insertando Calidad " + idCalidad);
        joya.setCalidad(this.calidadDAO.read(idCalidad));
        joya.setValorByTipoObjetoVal(new Valor(Objetos.JOYA.getValorId()));
        joya.setTipoElemento(new Valor(Elementos.OBJETO.getValorId()));
        idJoya = this.joyaDAO.create(joya);
        joyaDto.setElementoId(idJoya);
        guardaOActualizaObjetos(joyaDto);
        logger.debug("Se genero el joya con id: " + idJoya);
        return idJoya;
    }

    private Long actualiza(JoyaDTO joyaDto) throws NSJPNegocioException {
        Long elementoId = joyaDto.getElementoId();
        if (elementoId < 0) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        Joya actualizable = joyaDAO.read(elementoId);
        Joya actualizador = JoyaTransformer.trasnformarJoya(joyaDto);
        IngresarVehiculoServiceImpl.actualizaObjeto(actualizador, actualizable);
        return elementoId;
    }
    
    private void guardaOActualizaObjetos(JoyaDTO joyaDto) throws NSJPNegocioException {
        CadenaDeCustodiaDTO cadenaDeCustodia = joyaDto.getCadenaDeCustodia();
        if (cadenaDeCustodia != null) {
            guardaOActualizaEvidencia.guardaOActualizaEvidencia(joyaDto);
        }
    }
}

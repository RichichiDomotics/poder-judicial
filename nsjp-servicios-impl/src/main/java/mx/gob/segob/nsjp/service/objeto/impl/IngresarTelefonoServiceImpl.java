/**
 * Nombre del Programa : IngresarTelefonoServiceImpl.java
 * Autor                            : Tattva-IT
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 5 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementacion de servicios para ingresar telefonia
 * Programa Dependiente  :N/A
 * Programa Subsecuente :N/A
 * Cond. de ejecucion        :N/A
 * Dias de ejecucion          :N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                       :N/A
 * Compania               :N/A
 * Proyecto                 :N/A                                   Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.objeto.impl;

import java.util.Set;
import mx.gob.segob.nsjp.comun.enums.elemento.Elementos;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.objeto.Objetos;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.elemento.CalidadDAO;
import mx.gob.segob.nsjp.dao.objeto.TelefoniaDAO;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.dto.objeto.TelefoniaDTO;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.Telefonia;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.objeto.IngresarTelefonoService;
import mx.gob.segob.nsjp.service.objeto.impl.transform.TelefoniaTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion de servicios para ingresar telefonia
 * @version 1.0
 * @author Tattva-IT
 *
 */
@Service
@Transactional
public class IngresarTelefonoServiceImpl implements IngresarTelefonoService {

    private final static Logger logger = Logger.getLogger(IngresarTelefonoServiceImpl.class);
    /**
     * Objeto de Acceso a Datos para la entidad Calidad
     */
    @Autowired
    private CalidadDAO calidadDAO;
    @Autowired
    private TelefoniaDAO telefoniaDAO;
    @Autowired
    private GuardaOActualizaEvidencia guardaOActualizaEvidencia;


    @Override
    public Long ingresarTelefono(TelefoniaDTO telefoniaDTO)
            throws NSJPNegocioException {
        if(telefoniaDTO == null){
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        if(telefoniaDTO.getElementoId() == null || telefoniaDTO.getElementoId() == 0){
            return guardar(telefoniaDTO);
        }else{
            return actualiza(telefoniaDTO);
        }
    }

    private Long guardar(TelefoniaDTO telefoniaDTO) throws NSJPNegocioException {
        Telefonia tel = new Telefonia();
        Long idTelefonia = 0L;
        Long idCalidad = 0L;
        Calidad cal = new Calidad();
        if (logger.isDebugEnabled()) {
            logger.debug("Inicia almacenamiento de telefonia");
        }
        if (telefoniaDTO == null) {
            logger.error("El telefono es requerido para el guardado");
            throw new NSJPNegocioException(CodigoError.FORMATO);
        }
        if (telefoniaDTO.getExpedienteDTO() != null && telefoniaDTO.getExpedienteDTO().getExpedienteId() == null) {
            logger.error("El expediente es requerido para el guardado");
            throw new NSJPNegocioException(CodigoError.FORMATO);
        }
        tel = TelefoniaTransformer.transformarTelefonia(telefoniaDTO);
        logger.debug("Verificando Calidad");
        if (telefoniaDTO.getCalidadDTO() != null && telefoniaDTO.getCalidadDTO().getDescripcionEstadoFisico() != null) {
            cal.setDescripcionEstadoFisico(telefoniaDTO.getCalidadDTO().getDescripcionEstadoFisico());
        }
        if (telefoniaDTO.getCalidadDTO() != null && telefoniaDTO.getCalidadDTO().getCalidades() != null) {
            cal.setTipoCalidad(new Valor(telefoniaDTO.getCalidadDTO().getCalidades().getValorId()));
        }
        idCalidad = this.calidadDAO.create(cal);
        logger.debug("Insertando Calidad " + idCalidad);
        tel.setValorByTipoObjetoVal(new Valor(Objetos.EQUIPO_TELEFONICO.getValorId()));
        tel.setTipoElemento(new Valor(Elementos.OBJETO.getValorId()));
        cal.setCalidadId(idCalidad);
        tel.setCalidad(cal);
        idTelefonia = this.telefoniaDAO.create(tel);
        telefoniaDTO.setElementoId(idTelefonia);
        guardaOActualizaObjetos(telefoniaDTO);
        logger.debug("Se genero el equipo telefonico con id: " + idTelefonia);
        return idTelefonia;
    }

    private Long actualiza(TelefoniaDTO telefoniaDTO) throws NSJPNegocioException {
        if(telefoniaDTO.getElementoId() < 0){
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        Telefonia actualizable = telefoniaDAO.read(telefoniaDTO.getElementoId());
        Telefonia actualizador = TelefoniaTransformer.transformarTelefonia(telefoniaDTO);
        guardaOActualizaObjetos(telefoniaDTO);
        IngresarVehiculoServiceImpl.actualizaObjeto(actualizador, actualizable);
        return telefoniaDTO.getElementoId();
    }


    private void guardaOActualizaObjetos(TelefoniaDTO telefoniaDto) throws NSJPNegocioException {
        CadenaDeCustodiaDTO cadenaDeCustodia = telefoniaDto.getCadenaDeCustodia();
        if (cadenaDeCustodia != null) {
            guardaOActualizaEvidencia.guardaOActualizaEvidencia(telefoniaDto);
        }
    }
}

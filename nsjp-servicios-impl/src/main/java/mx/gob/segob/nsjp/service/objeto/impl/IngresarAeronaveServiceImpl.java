/**
 * Nombre del Programa : IngresarAeronaveServiceImpl.java
 * Autor                            : Hugo Serrano
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 12 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementacion del servicios para ingresar aeronave
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
import mx.gob.segob.nsjp.dao.objeto.AeronaveDAO;
import mx.gob.segob.nsjp.dto.objeto.AeronaveDTO;
import mx.gob.segob.nsjp.dto.objeto.AnimalDTO;
import mx.gob.segob.nsjp.model.Aeronave;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.objeto.IngresarAeronaveService;
import mx.gob.segob.nsjp.service.objeto.impl.transform.AeronaveTransformer;

/**
 * Implementacion del servicios para ingresar aeronave
 * @version 1.0
 * @author Tattva-IT
 *
 */
@Service
@Transactional
public class IngresarAeronaveServiceImpl implements IngresarAeronaveService {

    private final static Logger logger = Logger.getLogger(IngresarAeronaveServiceImpl.class);
    /**
     * Objeto de Acceso a Datos para la entidad Calidad
     */
    @Autowired
    private CalidadDAO calidadDAO;
    @Autowired
    private AeronaveDAO aeronaveDAO;
    
    @Autowired
    private GuardaOActualizaEvidencia guardaOActualizaEvidencia;

    /**
     * {@inheritDoc}
     */
    @Override
    public Long ingresarAeronave(AeronaveDTO aeronaveDto)
            throws NSJPNegocioException {
        if (aeronaveDto == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        Long elementoId = aeronaveDto.getElementoId();
        if (elementoId == null || elementoId == 0) {
            return guardar(aeronaveDto);
        }else{
            return actualiza(aeronaveDto);
        }
    }

    private Long guardar(AeronaveDTO aeronaveDto) throws NSJPNegocioException {
        Long idAeronave = 0L;
        Aeronave aer = new Aeronave();
        Long idCalidad = 0L;
        Calidad cal = new Calidad();
        if (logger.isDebugEnabled()) {
            logger.debug("Inicia almacenamiento del Aeronave");
        }
        if (aeronaveDto == null) {
            logger.error("El aeronave es requerido para el guardado");
            throw new NSJPNegocioException(CodigoError.FORMATO);
        }
        ExpedienteDTO expedienteDTO = aeronaveDto.getExpedienteDTO();
        if (expedienteDTO == null || expedienteDTO.getExpedienteId() == null) {
            logger.error("El aeronave es requerido para el guardado");
            throw new NSJPNegocioException(CodigoError.FORMATO);
        }
        logger.info("Aeronave    !!!!!!!");
        aer = AeronaveTransformer.transformarAeronave(aeronaveDto);
        CalidadDTO calidadDTO = aeronaveDto.getCalidadDTO();
        if (calidadDTO != null && calidadDTO.getDescripcionEstadoFisico() != null) {
            cal.setDescripcionEstadoFisico(calidadDTO.getDescripcionEstadoFisico());
        }
        if (calidadDTO != null && calidadDTO.getCalidades() != null) {
            cal.setTipoCalidad(new Valor(calidadDTO.getCalidades().getValorId()));
        }
        idCalidad = this.calidadDAO.create(cal);
        logger.debug("Insertando Calidad " + idCalidad);
        cal.setCalidadId(idCalidad);
        aer.setCalidad(cal);
        aer.setValorByTipoObjetoVal(new Valor(Objetos.AERONAVE.getValorId()));
        aer.setTipoElemento(new Valor(Elementos.OBJETO.getValorId()));
        idAeronave = this.aeronaveDAO.create(aer);
        aeronaveDto.setElementoId(idAeronave);
        guardaOActualizaObjetos(aeronaveDto);
        logger.debug("Se genero el arma con id: " + idAeronave);
        return idAeronave;
    }

    private Long actualiza(AeronaveDTO aeronaveDto) throws NSJPNegocioException {
        Long elementoId = aeronaveDto.getElementoId();
        if (elementoId < 0) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        Aeronave actualizable = aeronaveDAO.read(elementoId);
        Aeronave actualizadora = AeronaveTransformer.transformarAeronave(aeronaveDto);
        IngresarVehiculoServiceImpl.actualizaObjeto(actualizadora, actualizable);
        return elementoId;
    }
    
    private void guardaOActualizaObjetos(AeronaveDTO aeronaveDto) throws NSJPNegocioException {
        CadenaDeCustodiaDTO cadenaDeCustodia = aeronaveDto.getCadenaDeCustodia();
        if (cadenaDeCustodia != null) {
            guardaOActualizaEvidencia.guardaOActualizaEvidencia(aeronaveDto);
        }
    }
}

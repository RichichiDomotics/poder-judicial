/**
 * Nombre del Programa : EnviarCarpetaDeInvestigacionServiceImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 22-jul-2011
 * Marca de cambio        : N/A
 * Descripcion General    : N/A
 * Programa Dependient    :N/A
 * Programa Subsecuente   :N/A
 * Cond. de ejecucion     :N/A
 * Dias de ejecucion      :N/A                                Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                            :N/A
 * Compania                         :N/A
 * Proyecto                         :N/A                      Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.expediente.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente;
import mx.gob.segob.nsjp.comun.enums.expediente.TipoMovimiento;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteWSDTO;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.RegistroBitacora;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.bitacora.RegistrarBitacoraService;
import mx.gob.segob.nsjp.service.expediente.ActualizarCarpetaDeInvestigacionService;
import mx.gob.segob.nsjp.service.expediente.EnviarCarpetaDeInvestigacionService;
import mx.gob.segob.nsjp.service.infra.impl.transform.enviarcarpetainvestigacion.ExpedienteWSDTOTransformer;
import mx.gob.segob.nsjp.service.solicitud.ActualizarEstatusSolicitudService;
import mx.gob.segob.nsjp.services.dtos.print.ExpedientePrint;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Este es la implementacion del ws que recibe el expedienteWSDTO para
 * registrarlo en la base de datos.
 * @version 1.0
 * @author Jacob Lobaco
 */
@Service("enviarCarpetaDeInvestigacionService")
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
@Transactional
public class EnviarCarpetaDeInvestigacionServiceImpl implements
        EnviarCarpetaDeInvestigacionService {
    @Autowired
    private RegistrarBitacoraService registrarBitacoraService;
	 /**
     * Logger de la clase.
     */
   private final static Logger logger = Logger
           .getLogger(EnviarCarpetaDeInvestigacionServiceImpl.class);
   
	
	@Autowired 
	ActualizarCarpetaDeInvestigacionService actualizarCarpetaDeInvestigacionService;
	@Autowired
	private ActualizarEstatusSolicitudService actualizarEstatusSolicitudService;
	
    @Override
    public Long enviarCarpetaDeInvestigacion(ExpedienteWSDTO expedienteWSDTO,
    		String numeroGeneralCaso, String folioSolicitud)
            throws NSJPNegocioException {
    	
    	if(expedienteWSDTO== null || !(numeroGeneralCaso!= null && !numeroGeneralCaso.trim().isEmpty()) 
    			|| !(folioSolicitud != null && !folioSolicitud.trim().isEmpty()))
    		return 0L;
    	
    	logger.info("*************** RECIBIDA CARPETA INVESTIGACION ***************");
    	logger.info(" Expediente : "+expedienteWSDTO);
    	logger.info(" numeroGeneralCaso : "+numeroGeneralCaso);
    	logger.info(" folioSolicitud : "+folioSolicitud);
    	
    	ExpedienteDTO expedienteDTO =  ExpedienteWSDTOTransformer.expedienteWsdto2ExpedienteDto(expedienteWSDTO);
    	logger.info("************  RECIBIDA ************ ");
    	ExpedientePrint.imprimirDatosExpediente(expedienteDTO);
    	
    	//Consultar el expediente por folioSolicitud
    	ExpedienteDTO  expedienteBDDTO = actualizarCarpetaDeInvestigacionService.consultarExpedientePorFolioSolicitud(folioSolicitud);
    	if( expedienteBDDTO == null || expedienteBDDTO.getExpedienteId()==null ||expedienteBDDTO.getExpedienteId()<0 )
    		return 0L;
    	
    	logger.info("Expediente encontrado ID: "+ expedienteBDDTO.getExpedienteId());
    
    	expedienteDTO.setExpedienteId(expedienteBDDTO.getExpedienteId());
    	expedienteDTO.setNumeroExpedienteId(expedienteBDDTO.getNumeroExpedienteId());
    	
    	expedienteDTO = actualizarCarpetaDeInvestigacionService.actualizarExpedienteDeCarpetaInvestigacionDefensoria(expedienteDTO);   	
    	
    	Long idExpediente = actualizarCarpetaDeInvestigacionService.actualizarExpedientePorFolioSolicitud(folioSolicitud, EstatusExpediente.ABIERTO_TECNICA_CON_CARPETA);
    	actualizarEstatusSolicitudService.actualizaEstatusSolicitud(folioSolicitud, EstatusSolicitud.CERRADA);
    	logger.info("*************** RECIBIDA CARPETA INVESTIGACION *************** [OK]");
        RegistroBitacora regBitEta = new RegistroBitacora();

        regBitEta.setFechaInicio(new Date());
        regBitEta.setTipoMovimiento(new Valor(
                TipoMovimiento.RECIBIR_CARPETA_DE_INVESTIGACION.getValorId()));
        regBitEta.setNuevo("Carpeta de Investigación Recibida");
        regBitEta.setNumeroExpediente(new NumeroExpediente(idExpediente));
        registrarBitacoraService.registrarMovimientoDeExpedienteEnBitacora(regBitEta);
    	return expedienteDTO!=null?expedienteDTO.getExpedienteId():0L;
    }
}

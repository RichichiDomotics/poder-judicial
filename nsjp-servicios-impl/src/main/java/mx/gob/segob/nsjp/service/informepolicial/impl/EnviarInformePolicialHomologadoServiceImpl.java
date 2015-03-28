/**
* Nombre del Programa : EnviarInformePolicialHomologadoServiceImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 07/09/2011
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
package mx.gob.segob.nsjp.service.informepolicial.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.informepolicial.InformePolicialHomologadoDTO;
import mx.gob.segob.nsjp.dto.informepolicial.InformePolicialHomologadoWSDTO;
import mx.gob.segob.nsjp.service.expediente.RecibirExpedienteService;
import mx.gob.segob.nsjp.service.informepolicial.EnviarInformePolicialHomologadoService;
import mx.gob.segob.nsjp.service.infra.impl.transform.enviarinformepolicialhomologado.InformePolicialHomologadoWSDTOTransformer;
import mx.gob.segob.nsjp.services.dtos.print.ExpedientePrintIPH;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio que permite enviar el Informe Policial Homologado
 * a otra institución mediante un WebService. 
 * @version 1.0
 * @author GustavoBP
 *
 */
@Service("enviarInformePolicialHomologadoService")
@Transactional
public class EnviarInformePolicialHomologadoServiceImpl  implements EnviarInformePolicialHomologadoService {
	   
	private final static Logger logger = Logger
	           .getLogger(EnviarInformePolicialHomologadoServiceImpl.class);
	   

	@Autowired
	private RecibirExpedienteService recibirExpedienteService;
	
	@Override
	public Long enviarInformePolicialHomologado(InformePolicialHomologadoWSDTO iphWSDTO, Long idAgencia)throws NSJPNegocioException{
		Long id = 0L;
		
		if(iphWSDTO == null)
    		return id;
		
		logger.info("*************** IPH RECIBIDO  ***************");
		logger.info(" IPH: "+ iphWSDTO);
		logger.info(" IPH-ConfInstitucionId: "+ iphWSDTO.getConfInstitucionId());
		logger.info(" IPH-idAgencia: "+ idAgencia);
		logger.info(" IPH-Expediente: "+ iphWSDTO.getExpediente());
		if (iphWSDTO.getExpediente()!= null){
			if(iphWSDTO.getExpediente().getInvolucradosDTO()!=null && !iphWSDTO.getExpediente().getInvolucradosDTO().isEmpty()){
				logger.info(" - IPH -Involucrado: " + iphWSDTO.getExpediente().getInvolucradosDTO().get(0));
				if(iphWSDTO.getExpediente().getInvolucradosDTO().get(0)!= null && iphWSDTO.getExpediente().getInvolucradosDTO().get(0).getDomicilio()!= null){
					logger.info(" - IPH -Involucrado: " + iphWSDTO.getExpediente().getInvolucradosDTO().get(0).getDomicilio());
					logger.info(" - IPH -Involucrado - Domicilio -AsentamientoId: " + iphWSDTO.getExpediente().getInvolucradosDTO().get(0).getDomicilio().getAsentamientoId());
					logger.info(" - IPH -Involucrado - Domicilio -ValorCalleId: " + iphWSDTO.getExpediente().getInvolucradosDTO().get(0).getDomicilio().getValorCalleId());
					logger.info(" - IPH -Involucrado - Domicilio -EntidadId: " + iphWSDTO.getExpediente().getInvolucradosDTO().get(0).getDomicilio().getEntidadId());
					logger.info(" - IPH -Involucrado - Domicilio -MunicipioId: " + iphWSDTO.getExpediente().getInvolucradosDTO().get(0).getDomicilio().getMunicipioId());
					logger.info(" - IPH -Involucrado - Domicilio -CiudadId: " + iphWSDTO.getExpediente().getInvolucradosDTO().get(0).getDomicilio().getCiudadId());
				}
				if(iphWSDTO.getExpediente().getHechoDTO()!= null){
					mx.gob.segob.nsjp.dto.hecho.HechoWSDTO hechoWSDTO = iphWSDTO.getExpediente().getHechoDTO() ; 
					logger.info("Hecho - DescNarrativa: "+ hechoWSDTO.getDescNarrativa());
					logger.info("Hecho - Lugar: "+ hechoWSDTO.getLugar());
					if (hechoWSDTO.getLugar()instanceof mx.gob.segob.nsjp.dto.domicilio.LugarWSDTO ){
						mx.gob.segob.nsjp.dto.domicilio.DomicilioWSDTO domicilioWSDTO = (mx.gob.segob.nsjp.dto.domicilio.DomicilioWSDTO) hechoWSDTO.getLugar();
						logger.info(" Descripcion:"+domicilioWSDTO.getDescripcion());
						logger.info(" Calle:"+domicilioWSDTO.getCalle());
						logger.info(" NumeroExterior:"+domicilioWSDTO.getNumeroExterior());
						logger.info(" AsentamientoDTO:"+domicilioWSDTO.getAsentamientoId());
						logger.info(" EntidadDTO:"+domicilioWSDTO.getEntidadId());
						logger.info(" MunicipioDTO:"+domicilioWSDTO.getMunicipioId());
						logger.info(" CiudadDTO:"+domicilioWSDTO.getCiudadId());
						logger.info(" NumeroInterior:"+domicilioWSDTO.getNumeroInterior());
						logger.info(" IPH-FechaDeArribo: "+ iphWSDTO.getExpediente().getHechoDTO().getFechaDeArribo());
					}
					logger.info("Hecho - Domicilio: "+ hechoWSDTO.getDomicilio());
					logger.info("Hecho - Tiempo: "+ hechoWSDTO.getTiempo());
					if(hechoWSDTO.getTiempo()!= null){
						logger.info("Tiempo - TiempoId: N/A");
						logger.info("Tiempo - Descripcion: "+ hechoWSDTO.getTiempo().getDescripcion());
						logger.info("Tiempo - FechaInicio: "+ hechoWSDTO.getTiempo().getFechaInicio());
						logger.info("Tiempo - FechaFin: "+ hechoWSDTO.getTiempo().getFechaFin());
						logger.info("Tiempo - TipoRegistro: "+ hechoWSDTO.getTiempo().getTipoRegistro());
					}
				}
			}
		}
		
		
		//Transformar de WSDTo --> DTO
		logger.info("* IPH A Tranformar  *");
		InformePolicialHomologadoDTO iphDTO = InformePolicialHomologadoWSDTOTransformer.transformarWSDTO(iphWSDTO);
		logger.info("* IPH Tranformado  *");
		logger.info(" IPH: "+ iphDTO);
		logger.info(" IPH-ConfInstitucionId: "+ iphDTO.getFolioIPH());
		logger.info(" IPH-Expediente: "+ iphDTO.getExpediente());
		if(iphDTO.getExpediente()== null)
			return 0L;
		
		ExpedientePrintIPH.imprimirDatosExpediente(iphDTO.getExpediente());
		
		ExpedienteDTO expedienteNuevoDTO = recibirExpedienteService.guardarExpedienteRecibido(iphDTO.getExpediente(), idAgencia);
		if(expedienteNuevoDTO==null)
			return 0L;
				
		return expedienteNuevoDTO.getExpedienteId();
	}
	
	
}

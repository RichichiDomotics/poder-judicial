/**
* Nombre del Programa : RecibirExpedienteServiceImplTest.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 08/09/2011
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
package mx.gob.segob.nsjp.service.test.expediente.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.informepolicial.InformePolicialHomologadoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.service.expediente.RecibirExpedienteService;
import mx.gob.segob.nsjp.service.informepolicial.ConsultarInformePolicialHomologadoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;
import mx.gob.segob.nsjp.services.dtos.print.ExpedientePrint;
import mx.gob.segob.nsjp.services.dtos.print.ExpedientePrintIPH;

/**
 * Clase de pruebas unitarias sobre los servicios para recibir y guardar un expediente
 * El expediente es enviado por SSP y lo recibe el PGJ
 * 
 * @version 1.0
 * @author GustavoBP
 */
public class RecibirExpedienteServiceImplTest extends
BaseTestServicios<RecibirExpedienteService>  {
	
	@Autowired 
	private ConsultarInformePolicialHomologadoService consultarIPH;
	
	public void testConsultarIPHYGuardarExpedienteRecibido(){
		
		//Guardar el DTO
		try {
			//Consultar el IPH ya que la BD es la misma que se tiene para SSP y PGJ
			Long folioIPH = 2011000001L;
			InformePolicialHomologadoDTO iphDTO = consultarIPH.consultarInformePolicialHomologadoPorFolio(folioIPH );
			assertNotNull("InformePolicialHomologadoDTO", iphDTO);
			logger.info(" IPHDTO - InformePolicialHomologadoId:" + iphDTO.getInformePolicialHomologadoId());
			logger.info(" IPHDTO - FolioIPH:" + iphDTO.getFolioIPH());
			logger.info(" IPHDTO - Expediente:" + iphDTO.getExpediente());
			if(iphDTO.getExpediente()!= null){
				logger.info(" IPHDTO - :" + iphDTO.getExpediente().getExpedienteId());
				logger.info(" IPHDTO - :" + iphDTO.getExpediente().getNumeroExpediente());
				logger.info(" IPHDTO - :" + iphDTO.getExpediente().getInvolucradosDTO());
				if(iphDTO.getExpediente().getInvolucradosDTO()!= null){
					logger.info(" IPHDTO - :" + iphDTO.getExpediente().getInvolucradosDTO().size());
					List<InvolucradoDTO> involucrados = iphDTO.getExpediente().getInvolucradosDTO();
					for (InvolucradoDTO involucradoDTO : involucrados) {
						logger.info(" IPHDTO - involucradoDTO.getElementoId:" + involucradoDTO.getElementoId());
						logger.info(" IPHDTO - involucradoDTO.getCalidadDTO():" + involucradoDTO.getCalidadDTO());
						logger.info(" IPHDTO - involucradoDTO.getCalidadDTO().getCalidadId:" + involucradoDTO.getCalidadDTO().getCalidadId());
						logger.info(" IPHDTO - :" + involucradoDTO.getCalidadDTO().getValorIdCalidad().getIdCampo());
						logger.info(" IPHDTO - :" + involucradoDTO.getCalidadDTO().getCalidades());	
						
						//OJO IMPORTANTE Se hace la transformacion de la calidad
						involucradoDTO.setElementoId(0L); //Setear el ID en 0 para indicar q se trata de un involucrado nuevo
						CalidadDTO calidadDTO = new CalidadDTO();
						calidadDTO.setValorIdCalidad(involucradoDTO.getCalidadDTO().getValorIdCalidad());
						calidadDTO.setCalidades(Calidades.getByValor(involucradoDTO.getCalidadDTO().getValorIdCalidad().getIdCampo() ));
						
						involucradoDTO.setCalidadDTO(calidadDTO);
						
						logger.info(" IPHDTO - :" + involucradoDTO.getCalidadDTO().getValorIdCalidad().getIdCampo());
						logger.info(" IPHDTO - :" + involucradoDTO.getCalidadDTO().getCalidades());	
					}
				}
				
				
				ExpedientePrintIPH.imprimirDatosExpediente(iphDTO.getExpediente());
			}
			
			ExpedienteDTO expDTO = iphDTO.getExpediente();
			
//			ExpedienteDTO expedienteDTO = service.guardarExpedienteRecibido(expDTO);
//			assertNotNull("Debe de recuperarse un Expediente ", expedienteDTO);
//			logger.info(" Expediente:" + expedienteDTO);
//			logger.info(" Expediente id:" + expedienteDTO.getExpedienteId());
//			logger.info(" Expediente NumeroExpediente:" + expedienteDTO.getNumeroExpediente());
//			logger.info(" Expediente NumeroExpedienteId:" + expedienteDTO.getNumeroExpedienteId());
			
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
		}
		
	}

}

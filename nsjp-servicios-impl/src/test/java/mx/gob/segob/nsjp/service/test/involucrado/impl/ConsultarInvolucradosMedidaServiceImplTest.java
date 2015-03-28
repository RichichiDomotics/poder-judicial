/**
* Nombre del Programa : ConsultarInvolucradosMedidaServiceImplTest.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 09/08/2011
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
package mx.gob.segob.nsjp.service.test.involucrado.impl;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.dto.medida.CompromisoPeriodicoDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaAlternaDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaDTO;
import mx.gob.segob.nsjp.service.involucrado.ConsultarInvolucradosMedidaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public class ConsultarInvolucradosMedidaServiceImplTest extends
		BaseTestServicios<ConsultarInvolucradosMedidaService> {
	
	public void testConsultarInvolucradosMedidasPorFecha(){
		
		Long cFuncionario = 2L;
		Date fechaFin = new Date();
		Boolean esMedidaAlterna = true;
		
		try {
			List<InvolucradoDTO> involucradosDTO = service.consultarInvolucradosMedidasPorFecha(cFuncionario, fechaFin, esMedidaAlterna);
			
			assertTrue("La lista debe traer almenos un registro ", involucradosDTO.size()>0);
			logger.info("#Involucrados : " + involucradosDTO.size());
			for (InvolucradoDTO involucradoDTO : involucradosDTO) {
				logger.info(" Involucrado ID: "+ involucradoDTO.getElementoId());
				if(involucradoDTO!=null){
					logger.info(" Involucrado : "+ involucradoDTO.getNombreCompleto());
					logger.info(" Involucrado : "+ involucradoDTO.getTipoPersona());
				}
				logger.info(" Involucrado: "+ involucradoDTO);
				logger.info(" NombreCompleto: "+ involucradoDTO.getNombreCompleto());
				logger.info(" Expediente: "+ involucradoDTO.getExpedienteDTO());
				if (involucradoDTO.getExpedienteDTO()!= null ){
					logger.info(" getNumeroExpedienteId: "+ involucradoDTO.getExpedienteDTO().getNumeroExpedienteId());
					logger.info(" getNumeroExpediente: "+ involucradoDTO.getExpedienteDTO().getNumeroExpediente());
					logger.info(" getTipoExpediente: "+ involucradoDTO.getExpedienteDTO().getTipoExpediente() + "- CARPETA_EJECUCION(2095L)");
				}
				if(involucradoDTO.getNombresDemograficoDTO()!= null ){
					for (NombreDemograficoDTO nombreDTO : involucradoDTO.getNombresDemograficoDTO()) {
						logger.info("Nombre:"+nombreDTO.getNombreCompleto());
					}
				}
				
				if(involucradoDTO.getMedidasDTO()!=null){
					logger.info("Medida :"+involucradoDTO.getMedidasDTO().size());
					for (MedidaDTO medidaDTO : involucradoDTO.getMedidasDTO()) {
						logger.info("Medida -getDocumentoId:"+medidaDTO.getDocumentoId());	
						logger.info("Medida -getFechaInicio:"+medidaDTO.getFechaInicio());
						logger.info("Medida -getFechaFin:"+medidaDTO.getFechaFin());
						logger.info("Medida -getDescripcionMedida:"+medidaDTO.getDescripcionMedida());
						logger.info("Medida -getFuncionario:"+medidaDTO.getFuncionario());
						logger.info("Medida -getValorPeriodicidad:"+medidaDTO.getValorPeriodicidad());
						logger.info("Medida -getValorTipoMedida:"+medidaDTO.getValorTipoMedida());
						logger.info("Medida -getDomicilio:"+medidaDTO.getDomicilio());
						if(medidaDTO instanceof MedidaAlternaDTO){
							logger.info(" MedidaAlternaDTO:");
							MedidaAlternaDTO medidaAlternaDTO =(MedidaAlternaDTO) medidaDTO; 
							logger.info(" MedidaAlternaDTO - getAnios:" + medidaAlternaDTO.getAnios());
							logger.info(" MedidaAlternaDTO - getMeses:" + medidaAlternaDTO.getMeses());
							logger.info(" MedidaAlternaDTO - getSentenciaId:" + medidaAlternaDTO.getSentenciaId());
						}
						logger.info("CompromisoPeriodico :"+medidaDTO.getCompromisoPeriodico());
						if(medidaDTO.getCompromisoPeriodico()!= null){
							CompromisoPeriodicoDTO compromisoPeriodicoDTO = medidaDTO.getCompromisoPeriodico();
							logger.info("CompromisoPeriodico - getCompromisoPeriodicoId:"+compromisoPeriodicoDTO.getCompromisoPeriodicoId());
							logger.info("CompromisoPeriodico - getMonto:"+compromisoPeriodicoDTO.getMonto());
							logger.info("CompromisoPeriodico - getLugarPresentacion:"+compromisoPeriodicoDTO.getLugarPresentacion());
							logger.info("CompromisoPeriodico - getFechaCompromisos:"+compromisoPeriodicoDTO.getFechaCompromisos());
						}
					}
				}
			}
		} catch (NSJPNegocioException e) {
			logger.info(e.getMessage(), e);
		}
		
		
		
	}

}

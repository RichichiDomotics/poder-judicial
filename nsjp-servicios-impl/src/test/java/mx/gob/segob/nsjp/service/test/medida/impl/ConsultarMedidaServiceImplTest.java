/**
* Nombre del Programa : ConsultarMedidaServiceImplTest.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 11/08/2011
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
package mx.gob.segob.nsjp.service.test.medida.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.medida.CompromisoPeriodicoDTO;
import mx.gob.segob.nsjp.dto.medida.IncidenciaDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaAlternaDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaDTO;
import mx.gob.segob.nsjp.service.medida.ConsultarMedidaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Clase para generar pruebas unitarias de los medotos de ConsultarMedidaService.
 * @version 1.0
 * @author GustavoBP
 *
 */
public class ConsultarMedidaServiceImplTest extends BaseTestServicios<ConsultarMedidaService> {

	public void testConsultarMedida(){
		MedidaDTO medidaDTO = null;
		
		Long idMedida = 272L;
		Boolean esMedidaAlterna = true;
		
		try {
			medidaDTO = service.consultarMedida(idMedida, esMedidaAlterna);
			logger.info(" MedidaDTO :" + medidaDTO);
			if(medidaDTO!= null){
				logger.info(" MedidaDTO -getDocumentoId:" + medidaDTO.getDocumentoId());
				logger.info(" MedidaDTO -getDescripcionMedida:" + medidaDTO.getDescripcionMedida());
				logger.info(" MedidaDTO -getFechaInicio:" + medidaDTO.getFechaInicio());
				logger.info(" MedidaDTO -getFuncionario:" + medidaDTO.getFuncionario());
				logger.info(" MedidaDTO -getValorPeriodicidad:" + medidaDTO.getValorPeriodicidad());
				logger.info(" MedidaDTO -getValorTipoMedida:" + medidaDTO.getValorTipoMedida());
				logger.info(" MedidaDTO -getDomicilio:" + medidaDTO.getDomicilio());
				if(medidaDTO.getDomicilio()!= null){
					DomicilioDTO domicilioDTO = medidaDTO.getDomicilio();
					logger.info(" DomicilioDTO -getAsentamientoDTO:" + domicilioDTO.getAsentamientoDTO());
				}
				if(medidaDTO.getInvolucrado()!= null){
					InvolucradoDTO involucradoDTO = medidaDTO.getInvolucrado();
					logger.info(" Involucrado - getElementoId:" + involucradoDTO.getElementoId());
					logger.info(" Involucrado - getNombreCompleto:" + involucradoDTO.getNombreCompleto());
				}
				if(medidaDTO instanceof MedidaAlternaDTO){
					logger.info(" MedidaAlternaDTO:");
					MedidaAlternaDTO medidaAlternaDTO =(MedidaAlternaDTO) medidaDTO; 
					logger.info(" MedidaAlternaDTO - getAnios:" + medidaAlternaDTO.getAnios());
					logger.info(" MedidaAlternaDTO - getMeses:" + medidaAlternaDTO.getMeses());
					logger.info(" MedidaAlternaDTO - getSentenciaId:" + medidaAlternaDTO.getSentenciaId());
				}
				logger.info(" MedidaDTO -getIncidencias:" + medidaDTO.getIncidencias());
				for(IncidenciaDTO incidenciaDTO : medidaDTO.getIncidencias()){
					logger.info(" Incidencia -getDocumentoId:" + incidenciaDTO.getDocumentoId());
					logger.info(" Incidencia -getReporte:" + incidenciaDTO.getReporte());
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
		} catch (NSJPNegocioException e) {
			logger.info(e.getMessage(), e);
		}
	}
}

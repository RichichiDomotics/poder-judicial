/**
* Nombre del Programa : ConsultarMedidasAlternasServiceImplTest.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 01/11/2011
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

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.documento.EstatusMedida;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaAlternaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.service.medidasalternas.ConsultarMedidasAlternasService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @version 1.0
 * @author GustavoBP
 *
 */
public class ConsultarMedidasAlternasServiceImplTest  extends BaseTestServicios<ConsultarMedidasAlternasService>{
	
	public void  testConsultarMedidasAlternasPorEstatus(){
		List<MedidaAlternaDTO> medidasAlternasDTO = new ArrayList<MedidaAlternaDTO>();
		
		EstatusMedida estatusMedida = EstatusMedida.getByValor(EstatusMedida.EN_PROCESO.getValorId());
		
		try {
			medidasAlternasDTO = service.consultarMedidasAlternasPorEstatus(estatusMedida);
			assertFalse("La lista no debe de ser vacia: ", medidasAlternasDTO.isEmpty());
			logger.info(" Total:"+ medidasAlternasDTO.size());
			for (MedidaAlternaDTO medidaAlternaDTO : medidasAlternasDTO) {
				logger.info(" MedidaDTO:"+ medidaAlternaDTO.getDocumentoId());
				logger.info(" MedidaDTO:"+ medidaAlternaDTO.getInvolucrado());
				logger.info(" MedidaDTO:"+ medidaAlternaDTO.getInvolucrado().getNombreCompleto());
			}
			
		} catch (NSJPNegocioException e) {
			logger.info(e.getMessage(), e);
		}
		
	}

	public void testConsultarInvolucradosConMedidasAlternativasPorCarpetaEjecucion(){
		try {
			
			UsuarioDTO usuario = new UsuarioDTO();
			
			List<InvolucradoDTO> invDTO = 
					service.consultarInvolucradosConMedidasAlternativasPorCarpetaEjecucion("NSJYUCPJ20114433333",usuario);
			assertFalse("La lista no puede ser vacia:", invDTO.isEmpty());
			logger.debug("Involucrados:" + invDTO);
			for (InvolucradoDTO involucradoDTO : invDTO) {
				logger.debug("Involucrados:" + involucradoDTO);
				logger.debug("Involucrados:" + involucradoDTO.getElementoId());
				logger.debug("Involucrados:" + involucradoDTO.getMedidasAlternasDTO());
				if(involucradoDTO.getMedidasAlternasDTO()!= null){
					logger.debug("Involucrados -Total Medidas:" + involucradoDTO.getMedidasAlternasDTO().size());
					List<MedidaAlternaDTO> medidasDTO = involucradoDTO.getMedidasAlternasDTO();
					for (MedidaAlternaDTO medidaAlternaDTO : medidasDTO) {
						logger.debug("MedidasDTO:" + medidaAlternaDTO.getDocumentoId());
						logger.debug("MedidasDTO:" + medidaAlternaDTO.getDescripcionMedida());
						logger.debug("MedidasDTO:" + medidaAlternaDTO.getCompromisoPeriodico());
						if(medidaAlternaDTO.getCompromisoPeriodico()!= null){
							logger.debug("MedidasDTO:" + medidaAlternaDTO.getCompromisoPeriodico().getMonto());
							logger.debug("MedidasDTO:" + medidaAlternaDTO.getCompromisoPeriodico().getFechaCompromisos());
						}
					}
				}
			}
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	public void testConsultarMedidasAlternasPorId(){
		
		MedidaAlternaDTO medidaAlternaDTO = new MedidaAlternaDTO();
		medidaAlternaDTO.setDocumentoId( 285L);

		try {
			MedidaAlternaDTO medidaDTO = service.consultarMedidasAlternasPorId(medidaAlternaDTO);
			assertNotNull("No se encontro el elemento", medidaDTO);
			logger.info("MedidaDTO - :"+ medidaDTO.getDocumentoId());
			logger.info("MedidaDTO - :"+ medidaDTO.getDescripcionMedida());
			logger.info("MedidaDTO - :"+ medidaDTO.getFechaInicioStr());
			logger.info("MedidaDTO - :"+ medidaDTO.getFechaFinStr());
			logger.info("MedidaDTO - IN:"+ medidaDTO.getInvolucrado());
			if(medidaDTO.getInvolucrado()!= null){
				logger.info("MedidaDTO - IN:"+ medidaDTO.getInvolucrado().getElementoId());
				logger.info("MedidaDTO - IN:"+ medidaDTO.getInvolucrado().getNombreCompleto());
			}
			logger.info("MedidaDTO - :"+ medidaDTO.getNombreDocumento());
			logger.info("MedidaDTO - :"+ medidaDTO.getNumeroCarpetaEjecucion());
			logger.info("MedidaDTO - :"+ medidaDTO.getNumeroCausa());
			logger.info("MedidaDTO - :"+ medidaDTO.getNumeroCaso());
			logger.info("MedidaDTO - :"+ medidaDTO.getAnios());
			logger.info("MedidaDTO - :"+ medidaDTO.getMeses());
		} catch (NSJPNegocioException e) {
			logger.debug(e.getMessage(), e);
		}
	}

	public void testCobtenerDetalleMedidaAlterna(){

		try {
			Long idMedidaAlterna = 458L;
			Long idInvolucrado = 692L;
			MedidaAlternaDTO medidaDTO = service.obtenerDetalleMedidaAlterna(idMedidaAlterna, idInvolucrado);
			assertNotNull("No se encontro el elemento", medidaDTO);
			logger.info("MedidaDTO - :"+ medidaDTO.getDocumentoId());
			logger.info("MedidaDTO - :"+ medidaDTO.getDescripcionMedida());
			logger.info("MedidaDTO - :"+ medidaDTO.getFechaInicioStr());
			logger.info("MedidaDTO - :"+ medidaDTO.getFechaFinStr());
			logger.info("MedidaDTO - IN:"+ medidaDTO.getInvolucrado());
			if(medidaDTO.getInvolucrado()!= null){
				logger.info("MedidaDTO - IN:"+ medidaDTO.getInvolucrado().getElementoId());
				logger.info("MedidaDTO - IN:"+ medidaDTO.getInvolucrado().getNombreCompleto());
			}
			logger.info("MedidaDTO - :"+ medidaDTO.getNombreDocumento());
			logger.info("MedidaDTO - :"+ medidaDTO.getNumeroCarpetaEjecucion());
			logger.info("MedidaDTO - :"+ medidaDTO.getNumeroCausa());
			logger.info("MedidaDTO - :"+ medidaDTO.getNumeroCaso());
			logger.info("MedidaDTO - :"+ medidaDTO.getAnios());
			logger.info("MedidaDTO - :"+ medidaDTO.getMeses());
			
		} catch (NSJPNegocioException e) {
			logger.debug(e.getMessage(), e);
		}
	}

}

/**
 * 
 */
package mx.gob.segob.nsjp.service.test.quejaciudadana.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.quejaciudadana.EstatusQueja;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.quejaciudadana.QuejaCiudadanaDTO;
import mx.gob.segob.nsjp.service.quejaciudadana.ConsultarQuejaCiudadanaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class ConsultarQuejaCiudadanaServiceImplTest extends
		BaseTestServicios<ConsultarQuejaCiudadanaService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.quejaciudadana.impl.ConsultarQuejaCiudadanaServiceImpl#consultarQuejasCiudadanas()}.
	 */
	public void testConsultarQuejasCiudadanas() {
		try {
			List<QuejaCiudadanaDTO> quejas = service.consultarQuejasCiudadanas();
			assertNotNull(quejas);
			logger.info("Existen "+quejas.size()+" quejas");
			for (QuejaCiudadanaDTO dto : quejas) {
				logger.info("---------------------------------");
				logger.info("ID: "+dto.getQuejaCiudadanaId());
				logger.info("Folio: "+dto.getFolioQueja());
				logger.info("NumExp: "+dto.getNumeroExpediente());
//				logger.info("Nombre Denunciado: "+dto.getNombreInvDenunciado());
				logger.info("Estatus: "+dto.getEstatusQuejaDTO().getValor());
				if(dto.getInvolucradoDTO()!=null)
				logger.info("Invol: "+dto.getInvolucradoDTO().getElementoId());
				if(dto.getMotivoRechazoDTO()!=null)
					logger.info("Motivo Rechazo: "+dto.getMotivoRechazoDTO().getValor());
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.quejaciudadana.impl.ConsultarQuejaCiudadanaServiceImpl#consultarQuejaCiudadanaXId(java.lang.Long)}.
	 */
	public void testConsultarQuejaCiudadanaXId() {
		try {
			QuejaCiudadanaDTO dto = service.consultarQuejaCiudadanaXId(5L);
			assertNotNull(dto);
			logger.info("ID: "+dto.getQuejaCiudadanaId());
			logger.info("Folio: "+dto.getFolioQueja());
			logger.info("NumExp: "+dto.getNumeroExpediente());
//			logger.info("Nombre Denunciado: "+dto.getNombreInvDenunciado());
			logger.info("Estatus: "+dto.getEstatusQuejaDTO().getValor());
			if(dto.getMotivoRechazoDTO()!=null)
				logger.info("Motivo Rechazo: "+dto.getMotivoRechazoDTO().getValor());
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	public void testConsultarQuejasPorEstatus(){
		try {
			List<QuejaCiudadanaDTO> quejas = service.consultarQuejasPorEstatus(EstatusQueja.EN_PROCESO.getValorId());
			assertNotNull(quejas);
			logger.info("Existen "+quejas.size()+" quejas");
			for (QuejaCiudadanaDTO dto : quejas) {
				logger.info("---------------------------------");
				logger.info("ID: "+dto.getQuejaCiudadanaId());
				logger.info("Folio: "+dto.getFolioQueja());
				logger.info("NumExp: "+dto.getNumeroExpediente());
//				logger.info("Nombre Denunciado: "+dto.getNombreInvDenunciado());
				logger.info("Estatus: "+dto.getEstatusQuejaDTO().getValor());
				if(dto.getMotivoRechazoDTO()!=null)
					logger.info("Motivo Rechazo: "+dto.getMotivoRechazoDTO().getValor());
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}

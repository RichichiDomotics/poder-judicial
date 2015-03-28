/**
 * 
 */
package mx.gob.segob.nsjp.service.test.documento.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.documento.EstatusNotificacion;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.AvisoHechoDelictivoDTO;
import mx.gob.segob.nsjp.service.documento.ConsultarAvisosHDelictivoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class ConsultarAvisosHDelictivoServiceImplTest extends
		BaseTestServicios<ConsultarAvisosHDelictivoService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.documento.impl.ConsultarAvisosHDelictivoServiceImpl#consultarAvisosHDelictivoPorEstatus(java.lang.Long)}.
	 */
	public void testConsultarAvisosHDelictivoPorEstatus() {
		try {
			List<AvisoHechoDelictivoDTO> avisos = service.consultarAvisosHDelictivoPorEstatus(EstatusNotificacion.NO_ATENDIDA.getValorId());
			assertNotNull("Exito", avisos);
			logger.info("Existen "+avisos.size()+" avisos");
			for (AvisoHechoDelictivoDTO avi : avisos) {
				logger.info("---------------------------");
				logger.info("ID: "+avi.getDocumentoId());
				logger.info("fechaAtencion: "+avi.getFechaAtencion());
				logger.info("Delito: "+avi.getCatDelito().getNombre());
				logger.info("hecho: "+avi.getHechoDTO().getHechoId());
				if(avi.getHechoDTO().getLugar()!=null){
					logger.info("Lugar: "+avi.getHechoDTO().getLugar().getDescripcion());
				}
				if(avi.getHechoDTO().getDomicilio()!=null)
					logger.info("Domicilio: "+avi.getHechoDTO().getDomicilio().getCalle());
				logger.info("folio notificacion: "+avi.getFolioNotificacion());
				
				logger.info("fecha creacion: "+avi.getFechaCreacion());

			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
	}
	
	public void testConsultarAvisoHDelictivo(){
			AvisoHechoDelictivoDTO avisoDTO=new AvisoHechoDelictivoDTO();
			avisoDTO.setDocumentoId(88L);
			AvisoHechoDelictivoDTO avi;
			try {
				avi = service.consultarAvisoHDelictivo(avisoDTO);
				assertNotNull("Exito", avi);
				logger.info("ID: "+avi.getDocumentoId());
				logger.info("fechaAtencion: "+avi.getFechaAtencion());
				logger.info("Delito: "+avi.getCatDelito().getNombre());
				logger.info("hecho: "+avi.getHechoDTO().getHechoId());
				if(avi.getHechoDTO().getLugar()!=null)
				logger.info("Lugar: "+avi.getHechoDTO().getLugar().getDescripcion());
				if(avi.getHechoDTO().getDomicilio()!=null)
					logger.info("Domicilio: "+avi.getHechoDTO().getDomicilio().getAlias());
				logger.info("Implicado: "+avi.getNombreImplicado());
			} catch (NSJPNegocioException e) {
				e.printStackTrace();
			}
			
	}

	

	public void testCnsultarAvisoHechoXId(){
		AvisoHechoDelictivoDTO avi;
		try {
			avi = service.consultarAvisoHechoXId(38L);
			logger.info("ID: "+avi.getDocumentoId());
			logger.info("fechaAtencion: "+avi.getFechaAtencion());
			logger.info("Delito: "+avi.getCatDelito().getNombre());
			logger.info("hecho: "+avi.getHechoDTO().getHechoId());
			if(avi.getHechoDTO().getLugar()!=null)
			logger.info("Lugar: "+avi.getHechoDTO().getLugar().getDescripcion());
			if(avi.getHechoDTO().getDomicilio()!=null)
			logger.info("Domicilio: "+avi.getHechoDTO().getDomicilio().getAlias());
			logger.info("Implicado: "+avi.getNombreImplicado());
			logger.info("Implicado: "+avi.getIdImplicado());

		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
		
}

}

/**
 * 
 */
package mx.gob.segob.nsjp.service.test.documento.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.CuerpoOficioEstructuradoDTO;
import mx.gob.segob.nsjp.dto.documento.IndiceEstructuradoDTO;
import mx.gob.segob.nsjp.service.documento.ConsultarCuerpoOficioService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 * 
 */
public class ConsultarCuerpoOficioServiceImplTest extends
		BaseTestServicios<ConsultarCuerpoOficioService> {

	/**
	 * Test method for
	 * {@link mx.gob.segob.nsjp.service.documento.impl.ConsultarCuerpoOficioServiceImpl#consultarCuerpoOficio(java.lang.Long)}
	 * .
	 */
	public void testConsultarCuerpoOficio() {
		try {
			CuerpoOficioEstructuradoDTO cuerpoOficio = new CuerpoOficioEstructuradoDTO();
			 cuerpoOficio.setCuerpoOficioEstructuradoId(null);
			 
			IndiceEstructuradoDTO indiceDTO = new IndiceEstructuradoDTO();
			indiceDTO.setIndiceEstructuradoId(12L);
			
			cuerpoOficio.setIndiceEstructurado(indiceDTO);
			CuerpoOficioEstructuradoDTO cuerpo = service
					.consultarCuerpoOficio(cuerpoOficio);
			assertNotNull(cuerpo);
			logger.info("ID: " + cuerpo.getCuerpoOficioEstructuradoId());
			logger.info("Texto: " + cuerpo.getTexto());
			if(cuerpo.getIndiceEstructurado()!=null){
				logger.info("Id Indice: "+cuerpo.getIndiceEstructurado().getIndiceEstructuradoId());
				logger.info("Texto Indice: "+cuerpo.getIndiceEstructurado().getTextoEtiqueta());
				logger.info("Nombre Etiqueta: "+cuerpo.getIndiceEstructurado().getNombreEtiqueta());
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}

	}

}

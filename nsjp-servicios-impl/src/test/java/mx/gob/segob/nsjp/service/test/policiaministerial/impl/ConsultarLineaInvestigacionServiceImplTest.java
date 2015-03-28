/**
 * 
 */
package mx.gob.segob.nsjp.service.test.policiaministerial.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.policiaministerial.ComentarioDTO;
import mx.gob.segob.nsjp.dto.policiaministerial.LineaInvestigacionDTO;
import mx.gob.segob.nsjp.dto.policiaministerial.SeguimientoLIDTO;
import mx.gob.segob.nsjp.service.policiaministerial.ConsultarLineaInvestigacionService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class ConsultarLineaInvestigacionServiceImplTest extends
		BaseTestServicios<ConsultarLineaInvestigacionService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.policiaministerial.impl.ConsultarLineaInvestigacionServiceImpl#consultarLineaInvestigacion(mx.gob.segob.nsjp.dto.policiaministerial.LineaInvestigacionDTO)}.
	 */
	public void testConsultarLineaInvestigacion() {
		try {
			LineaInvestigacionDTO linea = service.consultarLineaInvestigacion(new LineaInvestigacionDTO(1L));
			assertNotNull(linea);
			logger.info("Línea: "+linea);
			if(linea.getSeguimientoLIDTO()!=null){
				logger.info("Seguimiento ID: "+linea.getSeguimientoLIDTO().getSeguimientoLIId());
				if(linea.getSeguimientoLIDTO().getExpedienteDTO()!=null)
					logger.info("Expediente: "+linea.getSeguimientoLIDTO().getExpedienteDTO());
				
				logger.info("lineaInvestigacionDTO: "+linea.getTipoLineaInvestigacioValorDTO());
				for (ComentarioDTO comentarioDTO : linea.getComentarioDTOs()) {
					logger.info("comentarioDTO: "+comentarioDTO);
					logger.info("comentarioDTO: "+comentarioDTO.getComentarioId());
					logger.info("comentarioDTO: "+comentarioDTO.getDescripcion());
					logger.info("comentarioDTO: "+comentarioDTO.getFechaCreacion());
					logger.info("comentarioDTO: "+comentarioDTO.getArchivoDigitalDTOs());
				}
			}
			
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.policiaministerial.impl.ConsultarLineaInvestigacionServiceImpl#consultarLineasInvestigacionXExpedienteId(mx.gob.segob.nsjp.dto.policiaministerial.SeguimientoLIDTO)}.
	 */
	public void testConsultarLineasInvestigacionXSeguimiento() {
		try {
			List<LineaInvestigacionDTO> lineas = service.consultarLineasInvestigacionXExpedienteId(new ExpedienteDTO(5L));
			assertNotNull(lineas);
			logger.info("Existen "+lineas.size()+" líneas");
			for (LineaInvestigacionDTO dto : lineas) {
				logger.info("----------------------------------------");
				logger.info("Linea Inv: "+dto.toString());
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}

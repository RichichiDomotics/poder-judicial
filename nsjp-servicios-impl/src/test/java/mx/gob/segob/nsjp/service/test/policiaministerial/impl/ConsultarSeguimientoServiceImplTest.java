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
import mx.gob.segob.nsjp.service.policiaministerial.ConsultarSeguimientoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class ConsultarSeguimientoServiceImplTest extends BaseTestServicios<ConsultarSeguimientoService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.policiaministerial.impl.ConsultarSeguimientoServiceImpl#consultarSeguimientoLI(mx.gob.segob.nsjp.dto.policiaministerial.SeguimientoLIDTO)}.
	 */
	public void testConsultarSeguimientoLI() {
		try {
			SeguimientoLIDTO seguimiento = service.consultarSeguimientoLI(new SeguimientoLIDTO(5L));
			assertNotNull(seguimiento);
			logger.info("Seguimiento: "+seguimiento);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.policiaministerial.impl.ConsultarSeguimientoServiceImpl#consultarSeguimientosLIXNumeroExpediente(mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO)}.
	 */
	public void testConsultarSeguimientosLIXNumeroExpediente() {
		ExpedienteDTO dto=new ExpedienteDTO();
		dto.setNumeroExpediente("NSJYUCSP20113733333");
//		dto.setNumeroExpedienteId(1L);
		try {
			List<SeguimientoLIDTO> seguimientos = service.consultarSeguimientosLIXExpedienteId(dto);
			assertNotNull(seguimientos);
			logger.info("Existen "+seguimientos.size()+ " seguimientos");
			for (SeguimientoLIDTO segDTO : seguimientos) {
				logger.info("--------------------------------------");
				logger.info("Seguimiento: "+segDTO);
				logger.info("LineasInvestigacionDTO: "+segDTO.getLineasInvestigacionDTO());
				if(segDTO.getLineasInvestigacionDTO()!=null && !segDTO.getLineasInvestigacionDTO().isEmpty()){
					logger.info("LineasInvestigacionDTO - Size: "+segDTO.getLineasInvestigacionDTO().size());
					for (LineaInvestigacionDTO lineaInvestigacionDTO : segDTO.getLineasInvestigacionDTO()) {
						logger.info("LI--------------------------------------");
						logger.info("lineaInvestigacionDTO: "+lineaInvestigacionDTO.getLineaInvestigacionId());
						logger.info("lineaInvestigacionDTO: "+lineaInvestigacionDTO.getDescripcion());
						logger.info("lineaInvestigacionDTO: "+lineaInvestigacionDTO.getNumComentarios());
						logger.info("lineaInvestigacionDTO: "+lineaInvestigacionDTO.getEsImpreso());
						logger.info("lineaInvestigacionDTO: "+lineaInvestigacionDTO.getFechaCreacion());
						logger.info("lineaInvestigacionDTO: "+lineaInvestigacionDTO.getFechaUltimoComentario());
						logger.info("lineaInvestigacionDTO: "+lineaInvestigacionDTO.getTipoLineaInvestigacioValorDTO());
						for (ComentarioDTO comentarioDTO : lineaInvestigacionDTO.getComentarioDTOs()) {
							logger.info("CC--------------------------------------");
							logger.info("comentarioDTO: "+comentarioDTO);
							logger.info("comentarioDTO: "+comentarioDTO.getComentarioId());
							logger.info("comentarioDTO: "+comentarioDTO.getDescripcion());
							logger.info("comentarioDTO: "+comentarioDTO.getFechaCreacion());
							logger.info("comentarioDTO: "+comentarioDTO.getArchivoDigitalDTOs());
						}
					}
				}
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
	}

}

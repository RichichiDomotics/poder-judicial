/**
 * 
 */
package mx.gob.segob.nsjp.service.test.lugar.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.domicilio.AsentamientoDTO;
import mx.gob.segob.nsjp.service.lugar.CompletarAsentamientoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 * 
 */
public class CompletarAsentamientoServiceImplTest extends
		BaseTestServicios<CompletarAsentamientoService> {

	/**
	 * Test method for
	 * {@link mx.gob.segob.nsjp.service.lugar.impl.CompletarAsentamientoServiceImpl#completarAsentamientoXCodigoPostal(java.lang.String)}
	 * .
	 */
	public void testCompletarAsentamientoXCodigoPostal() {
		try {
			 List<AsentamientoDTO> asentamientos = service
					.completarAsentamientoXCodigoPostal("77533");
			assertNotNull(asentamientos);
			logger.info("Existen "+asentamientos.size()+" asentamientos");
			for (AsentamientoDTO asentamiento : asentamientos) {
				logger.info("--------------------------------------------------------- ");
				logger.info("ID: " + asentamiento.getAsentamientoId());
				logger.info("Asentamiento: " + asentamiento.getNombreAsentamiento());
				logger.info("CP: " + asentamiento.getCodigoPostal());
				
				if (asentamiento.getTipoAsentamientoDTO() != null)
					logger.info("Tipo Asentamiento: "
							+ asentamiento.getTipoAsentamientoDTO()
									.getTipoAsentamiento());
				
				/*Municipio*/
				if (asentamiento.getMunicipioDTO() != null) {
					logger.info("Municipio: "
							+ asentamiento.getMunicipioDTO().getNombreMunicipio());
					if (asentamiento.getMunicipioDTO().getEntidadFederativaDTO() != null) {
						logger.info("Entidad Fed: "
								+ asentamiento.getMunicipioDTO()
										.getEntidadFederativaDTO()
										.getNombreEntidad());
						if(asentamiento.getMunicipioDTO().getEntidadFederativaDTO().getValorIdPais()!=null)
						logger.info("País: " + asentamiento.getMunicipioDTO().getEntidadFederativaDTO().getValorIdPais().getValor());
					}
				}
				/*Ciudad*/
				if (asentamiento.getCiudadDTO() != null) {
					logger.info("Ciudad: "
							+ asentamiento.getCiudadDTO().getNombreCiudad());
					if (asentamiento.getCiudadDTO().getEntidadFederativaDTO() != null) {
						logger.info("Entidad Fed: "
								+ asentamiento.getCiudadDTO().getEntidadFederativaDTO()
										.getNombreEntidad());
						if(asentamiento.getCiudadDTO().getEntidadFederativaDTO().getValorIdPais()!=null)
						logger.info("País: " + asentamiento.getCiudadDTO().getEntidadFederativaDTO().getValorIdPais().getValor());
					}
				}
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}

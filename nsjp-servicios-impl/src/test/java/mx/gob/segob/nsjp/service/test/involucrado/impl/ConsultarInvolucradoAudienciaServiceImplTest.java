package mx.gob.segob.nsjp.service.test.involucrado.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoViewDTO;
import mx.gob.segob.nsjp.service.involucrado.ConsultarInvolucradoAudienciaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

import org.apache.log4j.Logger;

public class ConsultarInvolucradoAudienciaServiceImplTest extends
		BaseTestServicios<ConsultarInvolucradoAudienciaService> {

	private final static Logger logger = Logger
			.getLogger(ConsultarInvolucradoAudienciaServiceImplTest.class);

	public void testObtenerImputadosAudiencia() {
		AudienciaDTO audiencia = new AudienciaDTO();
		audiencia.setId(182L);
		List<InvolucradoViewDTO> respuesta;
		try {
			respuesta = service.obtenerInvolucradosAudiencia(audiencia);
			assertNotNull("No puede ser nulo el objeto", respuesta);
			assertFalse("No puede estar vacia la lista", respuesta.isEmpty());

			for (InvolucradoViewDTO imputado : respuesta) {
				logger.debug(imputado);
			}
		} catch (NSJPNegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testObtenerInvolucradosAudiencia() {
		AudienciaDTO audiencia = new AudienciaDTO();
		audiencia.setId(104L);
		List<InvolucradoViewDTO> respuesta;
		try {
			respuesta = service.obtenerInvolucradosAudiencia(audiencia);
			assertNotNull("No puede ser nulo el objeto", respuesta);
			assertFalse("No puede estar vacia la lista", respuesta.isEmpty());

			for (InvolucradoViewDTO imputado : respuesta) {
				logger.debug("*****" + imputado);
				logger.debug(imputado.getCalidad());
				logger.debug(imputado.getCalidad().toString().toUpperCase());
				logger.debug(Calidades.PROBABLE_RESPONSABLE_PERSONA.toString() );
				logger.debug(Calidades.PROBABLE_RESPONSABLE_PERSONA.toString().toUpperCase() );
				
			}
		} catch (NSJPNegocioException e) {
			logger.info(e.getMessage(), e);
		}
	}

	
	public void testObtenerImputadosAudienciaNew() {
		AudienciaDTO audienciaDTO = new AudienciaDTO();
		audienciaDTO.setId(new Long(277));

		List<InvolucradoViewDTO> respuesta;
		try {
			respuesta = service.obtenerImputadosAudiencia(audienciaDTO);

			assertTrue("La lista debe tener minimo un registro ",
					respuesta.size() > 0);
			logger.info("Involucrados " + respuesta.size());
			for (InvolucradoViewDTO involucradoViewDTO : respuesta) {
				logger.info("Involucrado id :: "
						+ involucradoViewDTO.getInvolucradoId());
				logger.info("Involucrado Calidad :: "
						+ involucradoViewDTO.getCalidad());
				logger.info("Delitos "
						+ involucradoViewDTO.getDelitosCometidos());
				involucradoViewDTO.getDelitosCometidos();
				
				
				List<DelitoDTO> delitos = involucradoViewDTO.getDelitosCometidos();
				for (DelitoDTO delitoDTO : delitos) {
					logger.info( "Delito:"+ delitoDTO.getDelitoId());
					logger.info( "Delito:"+ delitoDTO.getNombreDelito());
					logger.info( "Delito Principal:"+ delitoDTO.getEsPrincipal());
					logger.info( "Delito Probable:"+ delitoDTO.getEsProbable());
					logger.info( "Delito CatDelito:"+ delitoDTO.getCatDelitoDTO());
				}
				logger.info(" *****  ");
					
			}
		} catch (NSJPNegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void testobtenerImputadosSiguienteAudiencia() {
		AudienciaDTO audienciaDTO = new AudienciaDTO();
		audienciaDTO.setId(new Long(103));

		List<InvolucradoViewDTO> respuesta;
		try {
			respuesta = service.obtenerImputadosSiguienteAudiencia(audienciaDTO);

			assertTrue("La lista debe tener minimo un registro ",
					respuesta.size() > 0);
			logger.info("Involucrados " + respuesta.size());
			for (InvolucradoViewDTO involucradoViewDTO : respuesta) {
				logger.info("Involucrado id :: "
						+ involucradoViewDTO.getInvolucradoId());
				logger.info("Delitos "
						+ involucradoViewDTO.getDelitosCometidos());
			}
		} catch (NSJPNegocioException e) {
			logger.info(e.getMessage(), e);
		}
	}

	public void testObtenerImputadosSiguienteAudiencia() {
		AudienciaDTO audiencia = new AudienciaDTO();
		audiencia.setId(108L);
		List<InvolucradoViewDTO> respuesta;
		try {
			respuesta = service.obtenerImputadosSiguienteAudiencia(audiencia);
			assertNotNull("No puede ser nulo el objeto", respuesta);
			assertFalse("No puede estar vacia la lista", respuesta.isEmpty());

			for (InvolucradoViewDTO imputado : respuesta) {
				logger.debug("*****" + imputado);
				logger.debug(imputado.getCalidad());
				logger.debug(imputado.getCalidad().toString().toUpperCase());
				logger.debug(Calidades.PROBABLE_RESPONSABLE_PERSONA.toString() );
				logger.debug(Calidades.PROBABLE_RESPONSABLE_PERSONA.toString().toUpperCase() );
				
			}
		} catch (NSJPNegocioException e) {
			logger.info(e.getMessage(), e);
		}

	}	
}

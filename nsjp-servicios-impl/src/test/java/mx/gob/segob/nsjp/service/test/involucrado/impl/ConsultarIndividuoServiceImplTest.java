/**
 * Nombre del Programa : ConsultarIndividuoServiceImplTest.java
 * Autor                            : cesarAgustin
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 18 Apr 2011
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

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.involucrado.DetencionDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.service.involucrado.ConsultarIndividuoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;
import mx.gob.segob.nsjp.services.dtos.print.InvolucradoPrint;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author cesarAgustin
 * 
 */
public class ConsultarIndividuoServiceImplTest extends
		BaseTestServicios<ConsultarIndividuoService> {

	public void testConsultarIndividuo() {
		InvolucradoDTO involucradoDTO = new InvolucradoDTO();

		involucradoDTO.setElementoId(1273L);

		
		try {
			InvolucradoDTO respuesta = service
					.obtenerInvolucrado(involucradoDTO);

			assertTrue("El id regresado debe ser mayor a 0 ",
					respuesta.getElementoId() > 0);
			
			logger.info("DTO:"+ respuesta);
			logger.info("DTO-Calidad:"+ respuesta.getCalidadDTO());
			InvolucradoPrint.imprimirDatosInvolucrado(respuesta);
			
			
		} catch (NSJPNegocioException e) {			
			e.printStackTrace();
		}
	}

	public void testConsultarInvExpCalidad() {
		try {
			UsuarioDTO usuario = new UsuarioDTO();
			usuario.setAreaActual(new AreaDTO(10L));
			ExpedienteDTO expedienteDTO = new ExpedienteDTO(423L);
			
			List<InvolucradoDTO> respuesta = service
					.consultarInvolucradoExpediente(expedienteDTO ,
							Calidades.VICTIMA_PERSONA, usuario);
			assertNotNull(respuesta);
			logger.info("La lista debe regresar minimo un registro : "
					+ respuesta.size());
			for (InvolucradoDTO involucradoDTO : respuesta) {
				logger.info(" Involucrado: "+ involucradoDTO.getElementoId());
				logger.info(" Involucrado: "+ involucradoDTO.getCalidadDTO());
				logger.info(" Involucrado: "+ involucradoDTO.getCondicion());
			}
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}

	public void testConsultarProbablesResponsablesDetenidos() {
		ExpedienteDTO expedienteDTO = new ExpedienteDTO(42L);
		Boolean esDetenido = true;
		try {
			List<InvolucradoDTO> detenidos = service.consultarProbablesResponsablesDetenidos(expedienteDTO,esDetenido);
			logger.info("Existen " + detenidos.size()+ " detenidos para el expediente "+ detenidos.get(0).getExpedienteDTO().getExpedienteId());
			for (InvolucradoDTO dto : detenidos) {
				logger.info("-----------------------------------");
				logger.info("ID: " + dto.getElementoId());
				logger.info("Nombre: " + dto.getNombreCompleto());
				logger.info("Detenido: " + dto.getEsDetenido());
				if (dto.getDelitosCometidos() != null) {
					if (dto.getDelitosCometidos().size() > 0) {
						logger.info("Delitos:"
								+ dto.getDelitosCometidos().size());
						for (DelitoDTO delito : dto.getDelitosCometidos()) {
							logger.info("*Delito ID:" + delito.getDelitoId());
//							logger.info("*Delito :" + delito.getNombreDelito());
						}
					}
				} else
					logger.info("Delitos: sin lista");

				if (dto.getDetenciones() != null) {
					if (dto.getDetenciones().size() > 0) {
						logger.info("Detenciones: "
								+ dto.getDetenciones().size());
						DetencionDTO detencion = dto.getDetenciones().get(0);
						logger.info("*fecha-hora de detención: "
								+ detencion.getFechaInicioDetencion());
						String strlugar=(detencion.getLugarDetencionDTO()!=null)?detencion.getLugarDetencionDTO()
								.getDescripcion():"sin lugar";
						logger.info("*lugar donde está detenido: "
								+ strlugar);
					}
				} else
					logger.info("Detenciones: sin lista");

			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
	}

	public void ConsultarInvolucradoExpediente() {
		ExpedienteDTO expedienteDTO = new ExpedienteDTO(1L);
		Calidades calidad = Calidades.PROBABLE_RESPONSABLE_PERSONA;
		try {
			UsuarioDTO usuario = new UsuarioDTO();
			List<InvolucradoDTO> detenidos = service
					.consultarInvolucradoExpediente(expedienteDTO, calidad, usuario);
			logger.info("Existen " + detenidos.size()
					+ "probables responsables "
					+ detenidos.get(0).getExpedienteDTO().getExpedienteId());
			for (InvolucradoDTO dto : detenidos) {
				logger.info("-----------------------------------");
				logger.info("ID: " + dto.getElementoId());
				logger.info("Nombre: " + dto.getNombreCompleto());
				logger.info("Detenido: " + dto.getEsDetenido());
				if (dto.getDelitosCometidos() != null) {
					if (dto.getDelitosCometidos().size() > 0) {
						logger.info("Delitos:"
								+ dto.getDelitosCometidos().size());
						for (DelitoDTO delito : dto.getDelitosCometidos()) {
							logger.info("*Delito ID:" + delito.getDelitoId());
//							logger.info("*Delito :" + delito.getNombreDelito());
						}
					}
				} else
					logger.info("Delitos: sin lista");

				if (dto.getDetenciones() != null) {
					if (dto.getDetenciones().size() > 0) {
						logger.info("Detenciones: "
								+ dto.getDetenciones().size());
						DetencionDTO detencion = dto.getDetenciones().get(0);
						logger.info("fecha-hora de detención: "
								+ detencion.getFechaInicioDetencion());
						String strlugar=(detencion.getLugarDetencionDTO()!=null)?detencion.getLugarDetencionDTO()
								.getDescripcion():"sin lugar";
						logger.info("*lugar donde está detenido: "
								+ strlugar);
					}
				} else
					logger.info("Detenciones: sin lista");

			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
	}

	public void testConsultarInvolucradoPorCalidadCaso(){
		
		CasoDTO casoDTO=new CasoDTO(14L);
		CalidadDTO calidadDTO = new CalidadDTO();
		calidadDTO.setCalidades(Calidades.PROBABLE_RESPONSABLE_PERSONA);
		calidadDTO.setCalidadId(Calidades.PROBABLE_RESPONSABLE_PERSONA.getValorId());
		try {
			List<InvolucradoDTO> involucrados = service.consultarInvolucradoPorCalidadCaso(casoDTO, calidadDTO );
			logger.info("Existe "+involucrados.size()+" involucrados");
			for (InvolucradoDTO inv : involucrados) {
				logger.info("----------------------------------");
				logger.info("ID: "+inv.getElementoId());
				logger.info("Nombre: "+inv.getNombreCompleto());
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
	}
	
	public void testObtenerInvolucradosPorExpedienteYCalidades() {
		String numeroExpediente = "NSJYUCPJ2011263333W";
		Calidades[] calidades = new Calidades[]{Calidades.VICTIMA_PERSONA,
				Calidades.PROBABLE_RESPONSABLE_PERSONA,
				Calidades.PROBABLE_RESPONSABLE_ORGANIZACION,
				Calidades.DENUNCIANTE}; 
		try {
			List<InvolucradoDTO> involucrados = service
					.obtenerInvolucradosPorExpedienteYCalidades(numeroExpediente, calidades);
			assertFalse("La lista no debe ser vacia", involucrados.isEmpty());
			logger.info("Existen " + involucrados.size());
			for (InvolucradoDTO involucradoDTO : involucrados) {
				logger.info("Involucrado " + involucradoDTO.getElementoId());
				logger.info("Involucrado " + involucradoDTO.getNombreCompleto());
				logger.info("Involucrado " + involucradoDTO.getCalidadDTO());
				logger.info("Involucrado " + involucradoDTO.getCalidadDTO().getCalidades());
			}
			
		} catch (NSJPNegocioException e) {
			logger.debug(e.getMessage(), e);
		}
	}
	
}

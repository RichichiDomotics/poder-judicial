/**
 * Nombre del Programa : AdministrarNumeroExpedienteServiceImplTest.java
 * Autor                            : GustavoBP
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 27/06/2011
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
package mx.gob.segob.nsjp.service.test.expediente.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.ConsecutivosUtil;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoPersonaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.institucion.InstitucionDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.service.expediente.AdministrarNumeroExpedienteService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Clase que implementa las pruebas unitarias sobre los expedientes y numero de
 * expedientes.
 * 
 * @version 1.0
 * @author GustavoBP
 * 
 */
public class AdministrarNumeroExpedienteServiceImplTest extends
		BaseTestServicios<AdministrarNumeroExpedienteService> {

	public void testCrearAsignarNumeroExpediente() {

		ExpedienteDTO expedienteDTO = new ExpedienteDTO(1L);
		expedienteDTO.setNumeroExpedienteId(1L);
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setIdUsuario(4L);
		usuarioDTO.setUsuario(usuarioDTO);
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO(40L);
		usuarioDTO.setFuncionario(funcionarioDTO);
		expedienteDTO.setUsuario(usuarioDTO);

		// Institucion con numero de expedientes
		// InstitucionDTO institucionDTO = new InstitucionDTO(3L,
		// "Poder Judicial");
		// Institucion sin numero de expedientes
		InstitucionDTO institucionDTO = new InstitucionDTO(10L,
				"Atención temprana defensoria");
		// InstitucionDTO institucionDTO = new InstitucionDTO(14L,
		// "Atención temprana defensoria Prueba");

		try {
			// El usuario esta asociado al Funcionario el cual pertenece a una
			// institución.
			expedienteDTO = service.crearAsignarNumeroExpediente(usuarioDTO,
					expedienteDTO, institucionDTO);

		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void testConsultarUltimoNumeroExpediente() {
		String numeroExpediente = null;
		// Institucion con numero de expedientes
		// InstitucionDTO institucionDTO = new InstitucionDTO(3L,
		// "Poder Judicial");
		// Institucion sin numero de expedientes
		InstitucionDTO institucionDTO = new InstitucionDTO(10L,
				"Atención temprana defensoria");

		try {
			numeroExpediente = service
					.consultarUltimoNumeroExpediente(institucionDTO
							.getInstitucionId());
			logger.info("Numero de expediente:" + numeroExpediente);
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
		}
	}

	// Versión utilizada cuando se exponia el servicio
	public void testGenerarConsecutivoNumeroExpediente() {
		// String numeroExpediente = "000/PR/15/RBO/2011/Z-99999"; // OK A-00000
		// String numeroExpediente = "000/PR/15/RBO/2011/A-00000"; // OK A-00001
		String numeroExpediente = "000/PR/15/RBO/2011/B-99999"; // OK C-00000
		String consecutivoNumeroExpediente = numeroExpediente
				.substring(numeroExpediente
						.lastIndexOf(ConsecutivosUtil.SEPARADOR_PREFIJOS) + 1);
		logger.info(" Consecutivo:" + consecutivoNumeroExpediente);
		// try {
		// String numeroExpedienteNuevo
		// =service.generarConsecutivoNumeroExpediente(consecutivoNumeroExpediente);
		// logger.info("Numero de expediente: " + numeroExpedienteNuevo);
		// } catch (NSJPNegocioException e) {
		// logger.error(e.getMessage(),e);
		// }

	}

	public void testAsociarNumExpediente() {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("Test Asociar Numero Expediente");
			}
			// Usuario usuario = usuarioDao.read(1L);
			// assertNotNull("usuario.getFuncionario()",
			// usuario.getFuncionario());
			// assertNotNull("usuario", usuario);
			// Expediente expediente = expedienteDao.read(1L);
			// expediente.setCaso(null);
			// expediente.setDelitos(null);
			// expediente.setNumeroExpediente(TestUtilServiceImpl.nuevoExpedienteUnico());
			// expediente.setNumeroExpediente("XXX/PR/15/RBO/2011/C-12345");
			//
			// UsuarioDTO usuarioDto = getUsuario();

			ExpedienteDTO expedienteDto = new ExpedienteDTO(2L);

			expedienteDto.setNumeroExpediente("PR/15/RBO/2011/C-12346");

			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuarioDTO.setIdUsuario(1L);
			expedienteDto.setUsuario(usuarioDTO);
			expedienteDto.setEtapa(new ValorDTO(2048L));

			// Probamos el servicio.
			service.asociarNumExpediente(expedienteDto);
		} catch (NSJPNegocioException ex) {
			Logger.getLogger(
					AdministrarNumeroExpedienteServiceImplTest.class.getName())
					.log(Level.SEVERE, null, ex);
			fail("Ocurrio una excepcion durante la ejecucion de la prueba");
		}
	}

	public void testCrearExpediente() {
		try {
			ExpedienteDTO expedienteDTO = service.crearExpediente();
			assertNotNull("El expediente no se genero",
					expedienteDTO.getExpedienteId());
			logger.info("El expediente generado contienen el id:"
					+ (expedienteDTO != null ? expedienteDTO.getExpedienteId()
							: expedienteDTO));
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void testAsociarNumCarpetaASolicitud() {
		ExpedienteDTO expedienteDTO = new ExpedienteDTO();
		// Es necesario asegurarse de que el numero de expediente sea unico
		expedienteDTO.setNumeroExpediente("NSJYUCPROC201133331");

		// Revisar en BD q la Solicitud no este asociada a un Numero de
		// expediente, si no lo sobre escribe.
		Long idSolicitud = 8L;
		boolean exito = false;

		try {
			exito = service.asociarNumCarpetaASolicitud(expedienteDTO,
					idSolicitud);
			logger.info("La actualizacion se realizo con exito:" + exito);

		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void testAsociarExpedientesAFuncionario() {
		List<Long> idsNumsExps = new ArrayList<Long>();
		idsNumsExps.add(1L);
		idsNumsExps.add(2L);
		idsNumsExps.add(3L);
		Long idFuncionario = 5L;

		try {
			service.asociarExpedientesAFuncionario(idsNumsExps, idFuncionario);
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
		}
	}	
	
	public void testGenerarNuevoExpedienteUAVD(){
		DelitoPersonaDTO delitoPersonaDTO = new DelitoPersonaDTO();
		delitoPersonaDTO.setVictima(new InvolucradoDTO(2918L));
		delitoPersonaDTO.setProbableResponsable(new InvolucradoDTO(2918L));
		delitoPersonaDTO.setDelito(new DelitoDTO(205L));
		UsuarioDTO loUsuario = new UsuarioDTO(10L);
		loUsuario.setFuncionario(new FuncionarioDTO(10L));		
		delitoPersonaDTO.setUsuario(loUsuario);
		try {
			ExpedienteDTO expedienteDTO = service.generarNuevoExpedienteUAVD(delitoPersonaDTO);
			assertNotNull("El expediente no se genero",
					expedienteDTO.getExpedienteId());
			logger.info("El expediente generado contiene el id:"
					+ (expedienteDTO != null ? expedienteDTO.getExpedienteId()
							: expedienteDTO));
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
		}
	}

}

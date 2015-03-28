/**
 * Nombre del Programa : ProgramarAudienciaServiceImplTest.java
 * Autor                            : Emigdio
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 23/06/2011
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
package mx.gob.segob.nsjp.service.test.audiencia.impl;

import java.util.ArrayList;
import java.util.Date;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.audiencia.SalaAudienciaDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.service.audiencia.ProgramarAudienciaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Pruebas unitarias del servicio de programación de audiencia
 * 
 * @version 1.0 - @author Emigdio Hernández
 * 
 */
public class ProgramarAudienciaServiceImplTest extends
		BaseTestServicios<ProgramarAudienciaService> {

	public void testProgramarAudienciaManualSalaAudiencia() {

		AudienciaDTO audiencia = new AudienciaDTO();

		try {
			audiencia.setId(297L);
			audiencia.setEsReprogramacionDeAudiencia(true);
			audiencia.setFechaAsignacionSala(new Date());
			audiencia.setFechaEvento(DateUtils.obtener("30/04/2012", "09:00"));
			audiencia.setDuracionEstimada(30);
			audiencia.setSala(new SalaAudienciaDTO(6L, "Sala"));
			audiencia.getSala().setTemporal(false);
			audiencia.setFuncionarios(new ArrayList<FuncionarioDTO>());
			audiencia.getFuncionarios().add(new FuncionarioDTO(3L));
			audiencia.setTipoAudiencia(new ValorDTO(2774L));

			service.guardarProgramacionAudiencia(audiencia);

		} catch (NSJPNegocioException e) {

			e.printStackTrace();
			assertNotNull("Servicio retornó excepción de negocio", null);
		}

	}

	public void testProgramarAudienciaManualSalaTemporal() {

		AudienciaDTO audiencia = new AudienciaDTO();

		try {
			audiencia.setId(1L);
			audiencia.setFechaAsignacionSala(new Date());
			audiencia.setFechaEvento(DateUtils.obtener("24/06/2011", "09:00"));
			audiencia.setDuracionEstimada(30);
			audiencia.setSala(new SalaAudienciaDTO());
			audiencia.getSala().setTemporal(true);
			audiencia.getSala().setDomicilioSala("Av Revolución 1181");
			audiencia.getSala().setUbicacionSala("Piso 8 al fondo");
			audiencia.getSala().setMotivo("Otras salas llenas");
			audiencia.setFuncionarios(new ArrayList<FuncionarioDTO>());
			audiencia.getFuncionarios().add(new FuncionarioDTO(2L));

			service.guardarProgramacionAudiencia(audiencia);

		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertNotNull("Servicio retornó excepción de negocio", null);
		}
	}

	public void testCrearAudienciaSiguiente() {
		try {
			Long idAudiencia = service.crearAudienciaSiguiente(new AudienciaDTO(1L));
			assertNotNull(idAudiencia);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}

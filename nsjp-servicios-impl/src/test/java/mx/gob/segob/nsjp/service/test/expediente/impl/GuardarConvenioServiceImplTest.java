/**
 * 
 */
package mx.gob.segob.nsjp.service.test.expediente.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ConvenioDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.medida.CompromisoPeriodicoDTO;
import mx.gob.segob.nsjp.dto.medida.FechaCompromisoDTO;
import mx.gob.segob.nsjp.service.expediente.GuardarConvenioService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 * 
 */
public class GuardarConvenioServiceImplTest extends
		BaseTestServicios<GuardarConvenioService> {

	/**
	 * Test method for
	 * {@link mx.gob.segob.nsjp.service.expediente.impl.GuardarConvenioServiceImpl#guardarConvenio(mx.gob.segob.nsjp.dto.expediente.AcuerdoRestaurativoDTO)}
	 * .
	 */
	public void testGuardarAcuerdoRestaurativo() {
		ConvenioDTO dto = new ConvenioDTO();
		// dto.setAcuerdoRestaurativoID(1L);
		dto.setInvolucradoPR(new InvolucradoDTO(1L));
		dto.setFuncionario(new FuncionarioDTO(5L));
		ExpedienteDTO expediente = new ExpedienteDTO(1L);
		expediente.setNumeroExpedienteId(1L);
		dto.setNumeroExpediente(expediente);
		dto.setPeriodicidad(new ValorDTO(1L));
		//Tipo Convenio
		
		dto.setNumeroConvenio(1L);
		dto.setFechaInicio(new Date());
//		dto.setFechaFin();
		dto.setMonto(new Double(66600.5));
		
		dto.setCompromisoPeriodicoDTO(darCompromisoPeriodico());
		try {
			Long idAcuerdo = service.guardarConvenio(dto);
			assertNotNull(idAcuerdo);
			logger.info("Registró el acuerdo: " + idAcuerdo);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	public void testGuardarConvenio() {
		ConvenioDTO dto = new ConvenioDTO();
	
		dto.setInvolucradoPR(new InvolucradoDTO(12L));
		dto.setInvolucradoVictima(new InvolucradoDTO(13L));
		dto.setFuncionario(new FuncionarioDTO(14L));		
		
		dto.setFechaInicio(new Date());
		dto.setFechaFin(new Date());
		dto.setTipoConvenio(new ValorDTO(101L));
		
		
		ExpedienteDTO expediente = new ExpedienteDTO();
		expediente.setNumeroExpedienteId(1L);		
		dto.setNumeroExpediente(expediente);
				
		dto.setCompromisoPeriodicoDTO(darCompromisoPeriodico());
		try {
			Long idAcuerdo = service.guardarConvenio(dto);
			assertNotNull(idAcuerdo);
			logger.info("Registró el acuerdo: " + idAcuerdo);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	private CompromisoPeriodicoDTO darCompromisoPeriodico() {
		CompromisoPeriodicoDTO dto=new CompromisoPeriodicoDTO();
		
		dto.setMonto(550000.00);

		dto.setFechaCompromisos(generaFechasCompromiso(5));
		
		return dto;
	}
	
	private List<FechaCompromisoDTO> generaFechasCompromiso(int numFechasCompromiso){
		List<FechaCompromisoDTO> fechas = new ArrayList<FechaCompromisoDTO>();
		for(int i=1; i<=numFechasCompromiso; i++){
			FechaCompromisoDTO loFecha = new FechaCompromisoDTO();
			loFecha.setFechaCompromiso(new Date());
			loFecha.setFechaCumplimiento(new Date());

			loFecha.setMonto(110000D);
			loFecha.setDescripcionCompromiso("Descripcion del Pago modificada");
			loFecha.setObservaciones("Observaciones del pago modificada");
			fechas.add(loFecha);
		}
		return fechas;
		
	}

}

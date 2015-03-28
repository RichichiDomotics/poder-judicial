/**
 * 
 */
package mx.gob.segob.nsjp.service.test.expediente.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ConvenioDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.medida.CompromisoPeriodicoDTO;
import mx.gob.segob.nsjp.dto.medida.FechaCompromisoDTO;
import mx.gob.segob.nsjp.service.expediente.ConsultarConvenioService;
import mx.gob.segob.nsjp.service.expediente.GuardarConvenioService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 * 
 */
public class ConsultarConvenioServiceImplTest extends
		BaseTestServicios<ConsultarConvenioService> {
	
	@Autowired
	public GuardarConvenioService guardarConvenioService;
	

	/**
	 * Test method for
	 * {@link mx.gob.segob.nsjp.service.expediente.impl.ConsultarConvenioServiceImpl#consultarConveniosPorExpediente(mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO)}
	 * .
	 */
	public void testConsultarConveniosPorExpediente() {
		ExpedienteDTO expedienteDTO = new ExpedienteDTO();
		expedienteDTO.setNumeroExpedienteId(3L);
		try {
			List<ConvenioDTO> acuerdos = service
					.consultarConveniosPorExpediente(expedienteDTO);
			assertNotNull(acuerdos);
			
			for (ConvenioDTO acu : acuerdos) {
				logger.info("Existen " + acuerdos.size() + " convenios");
				logger.info("---------------------------------");
				if (acu.getConvenioID() != null)
					logger.info("ID: " + acu.getConvenioID());
				logger.info("Nombre del probable responsable: " + (acu.getInvolucradoPR() != null && acu.getInvolucradoPR().getNombreCompleto() != null? 
					acu.getInvolucradoPR().getNombreCompleto(): "-"));
				logger.info("Nombre de la victima: " + (acu.getInvolucradoVictima() != null && acu.getInvolucradoVictima().getNombreCompleto() != null? 
						acu.getInvolucradoVictima().getNombreCompleto(): "-"));
				logger.info("Nombre del Mediador: " + (acu.getFuncionario() != null && acu.getFuncionario().getNombreCompleto() != null? 
						acu.getFuncionario().getNombreCompleto(): "-"));
				logger.info("Tipo de convenio: " + (acu.getTipoConvenio() != null && acu.getTipoConvenio().getValor() != null? 
						acu.getTipoConvenio().getValor(): "-"));
				logger.info("FechaIncio: " + acu.getFechaInicio());
				logger.info("FechaFinal: " + acu.getFechaFin());

				if (acu.getCompromisoPeriodicoDTO() != null) {
					logger.info(" Monto: " + acu.getCompromisoPeriodicoDTO().getMonto());
					if (acu.getCompromisoPeriodicoDTO().getFechaCompromisos() != null) {
						for (FechaCompromisoDTO feco : acu
								.getCompromisoPeriodicoDTO().getFechaCompromisos()) {
							logger.info("   -----++------");
							logger.info("   IdFecha: " + feco.getFechaCompromisoId());
							logger.info("   fecha Compromiso: " + feco.getFechaCompromiso());
							logger.info("   fecha Cumpliminto: " + feco.getFechaCumplimiento());
							logger.info("   Monto Parcial: " + feco.getMonto());
							logger.info("   Observaciones: " + feco.getObservaciones());
						}
					}
				}
			}
			} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for
	 * {@link mx.gob.segob.nsjp.service.expediente.impl.ConsultarConvenioServiceImpl#consultarDetalleConvenio(mx.gob.segob.nsjp.dto.expediente.AcuerdoRestaurativoDTO)}
	 * .
	 */
	public void testConsultarDetalleConvenio() {
		try {
			ConvenioDTO convenioDTO = new ConvenioDTO();
			convenioDTO.setConvenioID(758L);
			ConvenioDTO acu = service.consultarDetalleConvenio(convenioDTO);
			assertNotNull(acu);
			logger.info("---------------------------------");
			if (acu.getConvenioID() != null)
				logger.info("ID: " + acu.getConvenioID());
			logger.info("Nombre del probable responsable: " + (acu.getInvolucradoPR() != null && acu.getInvolucradoPR().getNombreCompleto() != null? 
				acu.getInvolucradoPR().getNombreCompleto(): "-"));
			logger.info("Nombre de la victima: " + (acu.getInvolucradoVictima() != null && acu.getInvolucradoVictima().getNombreCompleto() != null? 
					acu.getInvolucradoVictima().getNombreCompleto(): "-"));
			logger.info("Nombre del Mediador: " + (acu.getFuncionario() != null && acu.getFuncionario().getNombreCompleto() != null? 
					acu.getFuncionario().getNombreCompleto(): "-"));
			logger.info("Tipo de convenio: " + (acu.getTipoConvenio() != null && acu.getTipoConvenio().getValor() != null? 
					acu.getTipoConvenio().getValor(): "-"));
			logger.info("FechaIncio: " + acu.getFechaInicio());
			logger.info("FechaFinal: " + acu.getFechaFin());

			if (acu.getCompromisoPeriodicoDTO() != null) {
				logger.info(" Monto: " + acu.getCompromisoPeriodicoDTO().getMonto());
				if (acu.getCompromisoPeriodicoDTO().getFechaCompromisos() != null) {
					for (FechaCompromisoDTO feco : acu
							.getCompromisoPeriodicoDTO().getFechaCompromisos()) {
						logger.info("   -----++------");
						logger.info("   IdFecha: " + feco.getFechaCompromisoId());
						logger.info("   fecha Compromiso: " + feco.getFechaCompromiso());
						logger.info("   fecha Cumpliminto: " + feco.getFechaCumplimiento());
						logger.info("   Monto Parcial: " + feco.getMonto());
						logger.info("   Observaciones: " + feco.getObservaciones());
					}
				}
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	
	public void testActualizarDetalleConvenio() {
		try {
			ConvenioDTO convenioDTO = new ConvenioDTO();
			convenioDTO.setConvenioID(799L);
			convenioDTO.setInvolucradoPR(new InvolucradoDTO(48L));
			convenioDTO.setInvolucradoVictima(new InvolucradoDTO(48L));
			convenioDTO.setFechaInicio(new Date());
			convenioDTO.setFechaFin(new Date());
			CompromisoPeriodicoDTO loCompromiso = new CompromisoPeriodicoDTO();
			loCompromiso.setMonto(66666D);
			convenioDTO.setCompromisoPeriodicoDTO(loCompromiso);			
			guardarConvenioService.guardarConvenio(convenioDTO);
			
			
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}


}

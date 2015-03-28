/**
 * 
 */
package mx.gob.segob.nsjp.delegate.sesion.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.sesion.SesionDelegate;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.sesion.EntrevistaComplementariaDTO;
import mx.gob.segob.nsjp.dto.sesion.EntrevistaInicialDTO;
import mx.gob.segob.nsjp.dto.sesion.NotaEvolucionDTO;
import mx.gob.segob.nsjp.dto.sesion.SesionDTO;
import mx.gob.segob.nsjp.service.sesion.ConsultarEntrevistaComplementariaPorIdService;
import mx.gob.segob.nsjp.service.sesion.ConsultarEntrevistaInicialPorIdService;
import mx.gob.segob.nsjp.service.sesion.ConsultarNotaEvolucionPorIdService;
import mx.gob.segob.nsjp.service.sesion.ConsultarSesionesPorIdNumeroExpedienteService;
import mx.gob.segob.nsjp.service.sesion.GuardarEntrevistaComplementariaService;
import mx.gob.segob.nsjp.service.sesion.GuardarEntrevistaInicialService;
import mx.gob.segob.nsjp.service.sesion.GuardarNotaEvolucionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author rgama
 *
 */
@Service("sesionDelegate")
public class SesionDelegateImpl implements
	SesionDelegate {
	
	@Autowired
	private GuardarEntrevistaInicialService guardarEntrevistaInicialService;
	
	@Autowired
	private GuardarEntrevistaComplementariaService guardarEntrevistaComplementariaService;
	
	@Autowired
	private GuardarNotaEvolucionService guardarNotaEvolucionService;
	
	@Autowired
	private ConsultarEntrevistaInicialPorIdService consultarEntrevistaInicialPorIdService;
	
	@Autowired
	private ConsultarEntrevistaComplementariaPorIdService consultarEntrevistaComplementariaPorIdService;
		
	@Autowired
	private ConsultarNotaEvolucionPorIdService consultarNotaEvolucionPorIdService;
	
	@Autowired
	private ConsultarSesionesPorIdNumeroExpedienteService consultarSesionesPorIdNumeroExpedienteService;
	
	
	@Override
	public EntrevistaInicialDTO guardarEntrevistaInicial(
			EntrevistaInicialDTO aoEntrevistaInicialDTO)
			throws NSJPNegocioException {
		return guardarEntrevistaInicialService.guardarEntrevistaInicial(aoEntrevistaInicialDTO);		
	}
	@Override
	public EntrevistaComplementariaDTO guardarEntrevistaComplementaria(
			EntrevistaComplementariaDTO aoEntrevistaComplementariaDTO)
			throws NSJPNegocioException {
		return guardarEntrevistaComplementariaService.guardarEntrevistaComplementaria(aoEntrevistaComplementariaDTO);
	}
	@Override
	public NotaEvolucionDTO guardarNotaEvolucion(
			NotaEvolucionDTO aoNotaEvolucionDTO) throws NSJPNegocioException {
		return guardarNotaEvolucionService.guardarNotaEvolucion(aoNotaEvolucionDTO);
	}
	@Override
	public EntrevistaInicialDTO consultarEntrevistaInicialPorId(
			EntrevistaInicialDTO aoEntrevistaInicialDTO)
			throws NSJPNegocioException {
		return consultarEntrevistaInicialPorIdService.consultarEntrevistaInicialPorId(aoEntrevistaInicialDTO);
	}
	@Override
	public EntrevistaComplementariaDTO consultarEntrevistaComplementariaPorId(
			EntrevistaComplementariaDTO aoEntrevistaComplementariaDTO)
			throws NSJPNegocioException {
		return consultarEntrevistaComplementariaPorIdService.consultarEntrevistaComplementariaPorId(aoEntrevistaComplementariaDTO);
	}
	@Override
	public NotaEvolucionDTO consultarNotaEvolucionPorId(
			NotaEvolucionDTO aoNotaEvolucionDTO) throws NSJPNegocioException {
		return consultarNotaEvolucionPorIdService.consultarNotaEvolucionPorId(aoNotaEvolucionDTO);
	}
	@Override
	public List<SesionDTO> consultarSesionesPorIdNumeroExpediente(
			ExpedienteDTO aoExpedienteDTO) throws NSJPNegocioException {
		return consultarSesionesPorIdNumeroExpedienteService.consultarSesionesPorIdNumeroExpediente(aoExpedienteDTO);
	}

}


/**
 * 
 */
package mx.gob.segob.nsjp.delegate.familiar.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.familiar.FamiliarDelegate;
import mx.gob.segob.nsjp.dto.sesion.EntrevistaComplementariaDTO;
import mx.gob.segob.nsjp.dto.sesion.FamiliarDTO;
import mx.gob.segob.nsjp.service.familiar.ConsultarFamiliarPorIdService;
import mx.gob.segob.nsjp.service.familiar.ConsultarFamiliaresPorIdEntrevistaComplementariaService;
import mx.gob.segob.nsjp.service.familiar.GuardarFamiliarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author rgama
 *
 */
@Service("familiarDelegate")
public class FamiliarDelegateImpl implements
	FamiliarDelegate {
	
	@Autowired
	private GuardarFamiliarService guardarFamiliarService;
	@Autowired
	private ConsultarFamiliarPorIdService consultarFamiliarPorIdService;
	@Autowired
	private ConsultarFamiliaresPorIdEntrevistaComplementariaService consultarFamiliaresPorIdEntrevistaComplementariaService;

	@Override
	public FamiliarDTO guardarFamiliar(FamiliarDTO aoFamiliarDTO)
			throws NSJPNegocioException {
		return guardarFamiliarService.guardarFamiliar(aoFamiliarDTO);
	}

	@Override
	public FamiliarDTO consultarFamiliarPorId(FamiliarDTO aoFamiliarDTO)
			throws NSJPNegocioException {
		return consultarFamiliarPorIdService.consultarFamiliarPorId(aoFamiliarDTO);
	}

	@Override
	public List<FamiliarDTO> consultarFamiliaresPorIdEntrevistaComplementaria(
			EntrevistaComplementariaDTO aoEntrevistaComplementariaDTO)
			throws NSJPNegocioException {
		return consultarFamiliaresPorIdEntrevistaComplementariaService.
			consultarFamiliaresPorIdEntrevistaComplementaria(aoEntrevistaComplementariaDTO);
	}
	

}


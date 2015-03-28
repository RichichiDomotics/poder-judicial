package mx.gob.segob.nsjp.service.catalogo;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.DiaInhabilDTO;

public interface DiaInhabilService {

	public Long guardarDiaInhabil(DiaInhabilDTO dto) throws NSJPNegocioException;
	
	public void eliminarDiaInhabil(DiaInhabilDTO dto) throws NSJPNegocioException;
	
	public List<DiaInhabilDTO> consultarDiasInhabiles() throws NSJPNegocioException;
	
	public List<DiaInhabilDTO> consultarDiasInhabilesPorMes(Short mes) throws NSJPNegocioException;
}

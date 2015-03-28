/**
 * 
 */
package mx.gob.segob.nsjp.delegate.policiaministerial.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.policiaministerial.LineaInvestigacionDelegate;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.policiaministerial.LineaInvestigacionDTO;
import mx.gob.segob.nsjp.service.policiaministerial.ConsultarLineaInvestigacionService;
import mx.gob.segob.nsjp.service.policiaministerial.CrearDocumentoLineaInvestigacionService;
import mx.gob.segob.nsjp.service.policiaministerial.GuardarLineaInvestigacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author adrian
 *
 */
@Service("lineaInvestigacion")
public class LineaInvestigacionDelegateImpl implements
		LineaInvestigacionDelegate {
	
	@Autowired
	private GuardarLineaInvestigacionService guardarLineaInvestigacionService;
	@Autowired
	private ConsultarLineaInvestigacionService consultarLineaInvestigacionService;
	@Autowired
	private CrearDocumentoLineaInvestigacionService crearDocumentoLineaInvestigacionService;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.policiaministerial.LineaInvestigacionDelegate#guardarLineaInvestigacion(mx.gob.segob.nsjp.dto.policiaministerial.LineaInvestigacionDTO)
	 */
	@Override
	public Long guardarLineaInvestigacion(LineaInvestigacionDTO investigacionDTO)
			throws NSJPNegocioException {
		return guardarLineaInvestigacionService.guardarLineaInvestigacion(investigacionDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.policiaministerial.LineaInvestigacionDelegate#consultarLineaInvestigacion(mx.gob.segob.nsjp.dto.policiaministerial.LineaInvestigacionDTO)
	 */
	@Override
	public LineaInvestigacionDTO consultarLineaInvestigacion(
			LineaInvestigacionDTO investigacionDTO) throws NSJPNegocioException {
		return consultarLineaInvestigacionService.consultarLineaInvestigacion(investigacionDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.policiaministerial.LineaInvestigacionDelegate#consultarLineasInvestigacionXSeguimiento(mx.gob.segob.nsjp.dto.policiaministerial.SeguimientoLIDTO)
	 */
	@Override
	public List<LineaInvestigacionDTO> consultarLineasInvestigacionXExpedienteId(
			ExpedienteDTO expedienteDTO) throws NSJPNegocioException {
		return consultarLineaInvestigacionService.consultarLineasInvestigacionXExpedienteId(expedienteDTO);
	}

	@Override
	public DocumentoDTO crearDocumentoLineaInvestigacion(
			LineaInvestigacionDTO investigacionDTO, Boolean esGuardado) throws NSJPNegocioException {
		DocumentoDTO docDTO=crearDocumentoLineaInvestigacionService.crearDocumentoLineaInvestigacion(investigacionDTO,esGuardado);
		if(esGuardado)
			guardarLineaInvestigacionService.actualizarLineaConImpreso(investigacionDTO.getLineaInvestigacionId());
		return docDTO;
	}

}

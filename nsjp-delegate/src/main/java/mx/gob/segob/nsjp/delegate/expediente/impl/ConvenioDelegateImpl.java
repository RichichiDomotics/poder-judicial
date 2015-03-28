/**
 * 
 */
package mx.gob.segob.nsjp.delegate.expediente.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.expediente.ConvenioDelegate;
import mx.gob.segob.nsjp.dto.expediente.ConvenioDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.medida.FechaCompromisoDTO;
import mx.gob.segob.nsjp.service.convenio.ActualizarFechaCompromisoService;
import mx.gob.segob.nsjp.service.convenio.ConsultarPagoService;
import mx.gob.segob.nsjp.service.expediente.ConsultarConvenioService;
import mx.gob.segob.nsjp.service.expediente.GuardarConvenioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author adrian
 *
 */
@Service("acuerdoRestaurativoDelegate")
public class ConvenioDelegateImpl implements
		ConvenioDelegate {
	
	@Autowired
	private GuardarConvenioService guardarAcuerdoRestaurativoService;
	@Autowired
	private ConsultarConvenioService consultarAcuerdoRestaurativoService;
	@Autowired
	private ActualizarFechaCompromisoService actualizarFechaCompromisoService;
	@Autowired
	private ConsultarPagoService consultarPagoService;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.expediente.AcuerdoRestaurativoDelegate#guardarAcuerdoRestaurativo(mx.gob.segob.nsjp.dto.expediente.AcuerdoRestaurativoDTO)
	 */
	@Override
	public Long guardarAcuerdoRestaurativo(
			ConvenioDTO restaurativoDTO) throws NSJPNegocioException {
		return guardarAcuerdoRestaurativoService.guardarConvenio(restaurativoDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.expediente.AcuerdoRestaurativoDelegate#consultarConveniosPorExpediente(mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO)
	 */
	@Override
	public List<ConvenioDTO> consultarConveniosPorExpediente(
			ExpedienteDTO expedienteDTO) throws NSJPNegocioException {
		return consultarAcuerdoRestaurativoService.consultarConveniosPorExpediente(expedienteDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.expediente.AcuerdoRestaurativoDelegate#consultarDetalleConvenio(mx.gob.segob.nsjp.dto.expediente.AcuerdoRestaurativoDTO)
	 */
	@Override
	public ConvenioDTO consultarDetalleConvenio(
			ConvenioDTO acuerdoRestaurativoDTO)
			throws NSJPNegocioException {
		return consultarAcuerdoRestaurativoService.consultarDetalleConvenio(acuerdoRestaurativoDTO);
	}
	
	/**
     * Actualiza los datos de una Fecha Compromiso. Actualiza la fecha compromiso con los parametros
     * distintos de {@code null} que vengan dentro de la fecha compromiso DTO
     * @throws NSJPNegocioException En caso que "{@code fechaCompromisoDTO}" o
     * "{@code loFechaCompromisoDTO.fechaCompromisoId}" sean {@code null}.|
     */
    public void actualizarFechaCompromido(FechaCompromisoDTO loFechaCompromisoDTO) throws NSJPNegocioException{
    	actualizarFechaCompromisoService.actualizarFechaCompromido(loFechaCompromisoDTO);
    }
    
    public FechaCompromisoDTO consultarPagoXId(FechaCompromisoDTO aoFechaCompromiso)throws NSJPNegocioException{
    	return consultarPagoService.consultarPagoXId(aoFechaCompromiso);
    }

}

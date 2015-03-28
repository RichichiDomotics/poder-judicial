/**
* Nombre del Programa : MedidasAlternasDelegateImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 19 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion para delegate de MedidasAlternas
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
package mx.gob.segob.nsjp.delegate.medidasalternas.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.documento.EstatusMedida;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.medidasalternas.MedidasAlternasDelegate;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaAlternaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.service.medidasalternas.ConsultarMedidasAlternasService;
import mx.gob.segob.nsjp.service.medidasalternas.EnviarMedidaAlternaService;
import mx.gob.segob.nsjp.service.medidasalternas.ObtenerMedidasAlternasService;
import mx.gob.segob.nsjp.service.medidasalternas.RegistrarMedidaAlternaPJService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementacion para delegate de MedidasAlternas.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service("medidasAlternasDelegate")
public class MedidasAlternasDelegateImpl implements MedidasAlternasDelegate{

	@Autowired
	private ConsultarMedidasAlternasService consultarMedidasAlternasService;
	@Autowired
	private RegistrarMedidaAlternaPJService registrarMedidaAlternaPJService;
	@Autowired
	private EnviarMedidaAlternaService enviarMedidaAlternaService;
	@Autowired
	private ObtenerMedidasAlternasService obtenerMedidasAlternasService;	

	
	
	@Override
	public List<MedidaAlternaDTO> consultarMedidasAlternasPorNumeroExpediente(
			ExpedienteDTO expedienteDTO) throws NSJPNegocioException {		
		return consultarMedidasAlternasService.consultarMedidasAlternasPorNumeroExpediente(expedienteDTO);
	}

	@Override
	public Long registrarMedidaAlterna(
			MedidaAlternaDTO medidaAlternaDTO) throws NSJPNegocioException {		
		return registrarMedidaAlternaPJService.resgistrarMedidaAlterna(medidaAlternaDTO);
	}

	@Override
	public MedidaAlternaDTO enviarMedidaAlternaASSP(Long idMedidaAlterna)
			throws NSJPNegocioException {		
		return enviarMedidaAlternaService.enviarMedidaAlternaASSP(idMedidaAlterna);
	}

	@Override
	public List<MedidaAlternaDTO> consultarMedidaAlternaPorEstatus(
			EstatusMedida estatusMedida) throws NSJPNegocioException {		
		return consultarMedidasAlternasService.consultarMedidasAlternasPorEstatus(estatusMedida);
	}

	@Override
	public MedidaAlternaDTO consultarMedidasAlternasPorId(
			MedidaAlternaDTO medidaAlternaDTO) throws NSJPNegocioException {		
		return consultarMedidasAlternasService.consultarMedidasAlternasPorId(medidaAlternaDTO);
	}	
	
	@Override
	public List<InvolucradoDTO> consultarInvolucradosConMedidasAlternativasPorCarpetaEjecucion(
			String numeroExpediente,UsuarioDTO usuario) throws NSJPNegocioException {
		return consultarMedidasAlternasService.consultarInvolucradosConMedidasAlternativasPorCarpetaEjecucion(numeroExpediente,usuario);
	}
	
	@Override
	public MedidaAlternaDTO obtenerDetalleMedidaAlterna(Long idMedidaAlterna, Long idInvolucrado) throws NSJPNegocioException{
		return consultarMedidasAlternasService.obtenerDetalleMedidaAlterna(idMedidaAlterna, idInvolucrado);
	}
	
	@Override
	public List<MedidaAlternaDTO> obtenerMedidasAlternasIncumplidasDelDiaAnterior()
			throws NSJPNegocioException {
		return obtenerMedidasAlternasService.obtenerMedidasAlternasIncumplidasDelDiaAnterior();
	}

}

/**
* Nombre del Programa : MandamientoJudicialDelegateImpl.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 24/08/2011
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
package mx.gob.segob.nsjp.delegate.documento.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.documento.MandamientoJudicialDelegate;
import mx.gob.segob.nsjp.dto.documento.MandamientoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.service.documento.MandamientoJudicialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementación del delegate del mandamiento judicial
 * @version 1.0
 * @author Emigdio Hernandez
 *
 */
@Service
public class MandamientoJudicialDelegateImpl implements
		MandamientoJudicialDelegate {
	@Autowired
	MandamientoJudicialService mandamientoService = null;
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.documento.MandamientoJudicialDelegate#registrarMandamientoJudicial(mx.gob.segob.nsjp.dto.documento.MandamientoDTO)
	 */
	@Override
	public MandamientoDTO registrarMandamientoJudicial(
			MandamientoDTO mandamiento) throws NSJPNegocioException{
		return mandamientoService.registrarMandamientoJudicial(mandamiento);
	}
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.documento.MandamientoJudicialDelegate#consultarMandamientosPorNumeroExpediente(java.lang.String)
	 */
	@Override
	public List<MandamientoDTO> consultarMandamientosPorNumeroExpediente(
			String numeroExpediente,UsuarioDTO usuario) throws NSJPNegocioException {
		return mandamientoService.consultarMandamientosPorNumeroExpediente(numeroExpediente,usuario);
	}
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.documento.MandamientoJudicialDelegate#consultarMandamientosPorNumeroExpediente(java.lang.String)
	 */
	@Override
	public List<MandamientoDTO> consultarMandamientoPorFiltro(
			MandamientoDTO mandamientoDTO, String numeroExpediente) throws NSJPNegocioException{
		return mandamientoService.consultarMandamientoPorFiltro(mandamientoDTO, numeroExpediente);
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.documento.MandamientoJudicialDelegate#actualizarMandamiento(mx.gob.segob.nsjp.dto.documento.MandamientoDTO)
	 */
	@Override
	public void actualizarMandamiento(MandamientoDTO mandamiento) {
		mandamientoService.actualizarMandamiento(mandamiento);
	}
	@Override
	public void enviarMandamientoJudicial(Long mandamientoId)
			throws NSJPNegocioException {
		mandamientoService.enviarMandamientoJudicial(mandamientoId);
		
	}

}

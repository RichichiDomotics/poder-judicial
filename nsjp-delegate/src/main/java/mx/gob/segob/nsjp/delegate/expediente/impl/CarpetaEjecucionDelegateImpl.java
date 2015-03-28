/**
* Nombre del Programa : CarpetaEjecucionDelegateImpl.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 07/07/2011
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
package mx.gob.segob.nsjp.delegate.expediente.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.expediente.CarpetaEjecucionDelegate;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.service.expediente.ConsultarCarpetaEjecucionService;

/**
 * Implementación del delegate de administración de carpetas de ejecución
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
@Service
public class CarpetaEjecucionDelegateImpl implements CarpetaEjecucionDelegate {

	@Autowired
	ConsultarCarpetaEjecucionService carpetaEjecucionService;
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.expediente.CarpetaEjecucionDelegate#consultarDatosGeneralesExpedienteCarpetaDeEjecucion(java.lang.Long)
	 */
	@Override
	public ExpedienteDTO consultarDatosGeneralesExpedienteCarpetaDeEjecucion(
			Long numeroExpedienteId) throws NSJPNegocioException {
		return carpetaEjecucionService.consultarDatosGeneralesExpedienteCarpetaDeEjecucion(numeroExpedienteId);
	}

	@Override
	public List<ExpedienteDTO> consultarCarpetasEjecucionPorEstatus(List<ValorDTO> estatusExpediente,UsuarioDTO usuario) throws NSJPNegocioException {
		return carpetaEjecucionService.consultarCarpetasEjecucionPorEstatus(estatusExpediente,usuario);
	}
	
}

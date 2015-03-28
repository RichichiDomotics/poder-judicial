/**
* Nombre del Programa : SolicitudTrasladoImputadoDelegateImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 13 Sep 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del delegate para los servicio de la Solicitud de traslado del imputado
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
package mx.gob.segob.nsjp.delegate.solicitud.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.solicitud.SolicitudTrasladoImputadoDelegate;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudTrasladoImputadoDTO;
import mx.gob.segob.nsjp.service.solicitud.ConsultarSolicitudesTrasladosImputadoService;
import mx.gob.segob.nsjp.service.solicitud.EnviarSolicitudTrasladoImputadoSSPService;
import mx.gob.segob.nsjp.service.solicitud.RegistrarSolicitudTransferenciaImputadoService;

/**
 * Implementacion del delegate para los servicio de la Solicitud de traslado del imputado.
 * @author cesarAgustin
 *
 */
@Service("solicitudTrasladoImputadoDelegate")
public class SolicitudTrasladoImputadoDelegateImpl implements
		SolicitudTrasladoImputadoDelegate {
	
	@Autowired
	private RegistrarSolicitudTransferenciaImputadoService registrarSolicitudTransferenciaImputadoService;
	@Autowired
	private EnviarSolicitudTrasladoImputadoSSPService enviarSolicitudTrasladoImputadoSSPService;
	@Autowired
	private ConsultarSolicitudesTrasladosImputadoService consultarSolicitudesTrasladosImputadoService;
	
	@Override
	public SolicitudTrasladoImputadoDTO registrarSolicitudTrasladoImputado(
			SolicitudTrasladoImputadoDTO solicitudTrasladoImputadoDTO)
			throws NSJPNegocioException {		
		return registrarSolicitudTransferenciaImputadoService.registrarSolicitudTrasladoImputado(solicitudTrasladoImputadoDTO);
	}
	
	@Override
	public void enviarSolicitudTrasladoImputadoASSP(
			SolicitudTrasladoImputadoDTO solicitudTrasladoImputadoDTO)
			throws NSJPNegocioException {
		enviarSolicitudTrasladoImputadoSSPService.enviarSolicitudTrasladoImputadoASSP(solicitudTrasladoImputadoDTO);
	}

	@Override
	public List<SolicitudTrasladoImputadoDTO> consultarSolicitudTrasladoPorEstatus(
			EstatusSolicitud estatusSolicitud) throws NSJPNegocioException {		
		return consultarSolicitudesTrasladosImputadoService.consultarSolicitudTrasladoPorEstatus(estatusSolicitud);
	}

	@Override
	public SolicitudTrasladoImputadoDTO obtenerSolicitudTrasladoImputadoPorId(
			SolicitudTrasladoImputadoDTO solicitudTrasladoImputadoDTO)
			throws NSJPNegocioException {		
		return consultarSolicitudesTrasladosImputadoService.obtenerSolicitudTrasladoImputadoPorId(solicitudTrasladoImputadoDTO);
	}
}

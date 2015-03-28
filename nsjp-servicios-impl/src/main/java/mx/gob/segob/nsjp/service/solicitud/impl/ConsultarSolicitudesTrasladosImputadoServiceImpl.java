/**
* Nombre del Programa : ConsultarSolicitudesTrasladosImputadoServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 21 Sep 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para realizar las consultas a solicitud traslado imputado
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
package mx.gob.segob.nsjp.service.solicitud.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudTrasladoImputadoDAO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudTrasladoImputadoDTO;
import mx.gob.segob.nsjp.model.SolicitudTrasladoImputado;
import mx.gob.segob.nsjp.service.solicitud.ConsultarSolicitudesTrasladosImputadoService;
import mx.gob.segob.nsjp.service.solicitud.impl.transform.SolicitudTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del servicio para realizar las consultas a solicitud traslado imputado.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class ConsultarSolicitudesTrasladosImputadoServiceImpl implements
		ConsultarSolicitudesTrasladosImputadoService {

	/**
	 * 
	 */
	public static final Logger logger = Logger.getLogger(ConsultarSolicitudesTrasladosImputadoServiceImpl.class);
	
	@Autowired
	private SolicitudTrasladoImputadoDAO solicitudTrasladoImputadoDAO;
		
	@Override
	public List<SolicitudTrasladoImputadoDTO> consultarSolicitudTrasladoPorEstatus(
			EstatusSolicitud estatusSolicitud) throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR SOLICITUDES DE TRASLADO DE IMPUTADO POR ESTATUS ****/");
		
		List<SolicitudTrasladoImputado> solicitudesTraslado = solicitudTrasladoImputadoDAO.consultarSolicitudesTrasladosImputadoByEstatus(estatusSolicitud.getValorId());
		
		List<SolicitudTrasladoImputadoDTO> solicitudesRetorno = new ArrayList<SolicitudTrasladoImputadoDTO>();
		for (SolicitudTrasladoImputado solicitudTrasladoImputado : solicitudesTraslado) {
			solicitudesRetorno.add(SolicitudTransformer.solicitudTrasladoImputadoTransformer(solicitudTrasladoImputado));
		}
		
		if (logger.isDebugEnabled())
			logger.debug("/**** LA CONSULTA DE SOLICITUDES DE TRASLADO DE IMPUTADO POR ESTATUS FUE EXITOSA ****/");
		
		return solicitudesRetorno;
	}

	@Override
	public SolicitudTrasladoImputadoDTO obtenerSolicitudTrasladoImputadoPorId(
			SolicitudTrasladoImputadoDTO solicitudTrasladoImputadoDTO)
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA OBTNER EL DETALLE DE SOLICITUDES DE TRASLADO DE IMPUTADO ****/");
		
		SolicitudTrasladoImputado solicitudTrasladoImputado = solicitudTrasladoImputadoDAO.read(solicitudTrasladoImputadoDTO.getDocumentoId());
		
		SolicitudTrasladoImputadoDTO solicitudRetorno = SolicitudTransformer.solicitudTrasladoImputadoTransformer(solicitudTrasladoImputado);
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SE OBTUVO EL DETALLE DE SOLICITUDES DE TRASLADO DE IMPUTADO CORRECTAMENTE ****/");
		
		return solicitudRetorno;
	}

}

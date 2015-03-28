/**
* Nombre del Programa : AsociarSolicitudANumeroExpedienteServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 6 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para asociar una solicitud a un NumeroExpediente
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

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudDAO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Solicitud;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.solicitud.AsociarSolicitudANumeroExpedienteService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del servicio para asociar una solicitud a un NumeroExpediente.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class AsociarSolicitudANumeroExpedienteServiceImpl implements
		AsociarSolicitudANumeroExpedienteService {

	/**
	 * 
	 */
	public static final Logger logger = Logger.getLogger(AsociarSolicitudANumeroExpedienteServiceImpl.class);
	
	/**
	 * 
	 */
	@Autowired
	private SolicitudDAO solicitudDAO;	
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.solicitud.AsociarSolicitudANumeroExpedienteService#asociarSolicitudANumeroExpediente(mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO, mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO)
	 */
	@Override
	public void asociarSolicitudANumeroExpediente(ExpedienteDTO expedienteDTO,
			SolicitudDTO solicitudDTO) throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA ASOCIAR UNA SOLICITUD A UN NUMERO EXPEDIENTE ****/");
		
		if (solicitudDTO.getDocumentoId()==null || expedienteDTO.getNumeroExpedienteId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		Solicitud solicitud = solicitudDAO.read(solicitudDTO.getDocumentoId());
		
		solicitud.setNumeroExpediente(new NumeroExpediente(expedienteDTO.getNumeroExpedienteId()));
		solicitud.setEstatus(new Valor(EstatusSolicitud.EN_PROCESO.getValorId()));
		solicitudDAO.update(solicitud);
		
		if (logger.isDebugEnabled())
			logger.debug("/**** LA ASOCIACION SE REALIZO EXITOSAMENTE ****/");
	}

}

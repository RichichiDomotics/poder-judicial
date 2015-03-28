/**
* Nombre del Programa : ActualizarEstatusSolicitudDefensorServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 12 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para realizar la actualizacion de estus de las solicitudes de defensoria
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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudDefensorDAO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDefensorDTO;
import mx.gob.segob.nsjp.model.SolicitudDefensor;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.solicitud.ActualizarEstatusSolicitudDefensorService;

/**
 * Implementacion del servicio para realizar la actualizacion de estus de las solicitudes de defensoria.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class ActualizarEstatusSolicitudDefensorServiceImpl implements
		ActualizarEstatusSolicitudDefensorService {

	/**
	 * 
	 */
	public static final Logger logger = Logger.getLogger(ActualizarEstatusSolicitudDefensorServiceImpl.class); 
	
	@Autowired
	SolicitudDefensorDAO solicitudDefensorDAO;
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.solicitud.ActualizarEstatusSolicitudDefensorService#actualizarEstatusSolicitudDefensor(mx.gob.segob.nsjp.dto.solicitud.SolicitudDefensorDTO, mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud)
	 */
	@Override
	public void actualizarEstatusSolicitudDefensor(
			SolicitudDefensorDTO solDefensorIn,
			EstatusSolicitud estatusSolicitud) throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA ACTUALIZAR EL ESTATUS A LAS SOLICITUDES DE DEFENSORIA ****/");
	
		if (solDefensorIn.getDocumentoId()==null || estatusSolicitud==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		SolicitudDefensor solDefensor = solicitudDefensorDAO.read(solDefensorIn.getDocumentoId());	
		solDefensor.setEstatus(new Valor(estatusSolicitud.getValorId()));
		
		solicitudDefensorDAO.update(solDefensor);
	}

}

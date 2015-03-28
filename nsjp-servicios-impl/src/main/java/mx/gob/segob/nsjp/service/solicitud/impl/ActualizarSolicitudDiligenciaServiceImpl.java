/**
* Nombre del Programa : ActualizarSolicitudDiligenciaServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 8 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para realizar la actualizacion de informacion de una solicitud de diligencia
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
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudDiligenciaDAO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDiligenciaDTO;
import mx.gob.segob.nsjp.model.SolicitudDiligencia;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.solicitud.ActualizarSolicitudDiligenciaService;

/**
 * Implementacion del servicio para realizar la actualizacion de informacion de una solicitud de diligencia.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class ActualizarSolicitudDiligenciaServiceImpl implements
		ActualizarSolicitudDiligenciaService {
	
	/**
	 * 
	 */
	public static final Logger logger = Logger.getLogger(ActualizarSolicitudDiligenciaServiceImpl.class);
	
	@Autowired
	private SolicitudDiligenciaDAO solicitudDiligenciaDAO;
	
	@Override
	public void actualizarSolicitudDiligencia(
			SolicitudDiligenciaDTO solicitudDiligenciaDTO)
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA ACTUALIZAR INFORMACION DE SOLICITUD DILIGENCIA ****/");
		
		if (solicitudDiligenciaDTO.getDocumentoId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		//Obtener solicitud diligencia
		SolicitudDiligencia solicitudDiligencia = solicitudDiligenciaDAO.read(solicitudDiligenciaDTO.getDocumentoId());
		
		if (solicitudDiligenciaDTO.getTipoDiligencia()!=null)
			solicitudDiligencia.setTipoDiligencia(new Valor(solicitudDiligenciaDTO.getTipoDiligencia().getIdCampo()));
		if (solicitudDiligenciaDTO.getMotivo()!=null) 
			solicitudDiligencia.setMotivo(solicitudDiligenciaDTO.getMotivo());
		if (solicitudDiligenciaDTO.getFechaLimite()!=null)
			solicitudDiligencia.setFechaLimite(solicitudDiligenciaDTO.getFechaLimite());
		
		solicitudDiligenciaDAO.update(solicitudDiligencia);	
		logger.debug("/****LA ACTUALIZACION DE SOLICITUD DILIGENCIA FUE EXITOSA ****/");
	}
}

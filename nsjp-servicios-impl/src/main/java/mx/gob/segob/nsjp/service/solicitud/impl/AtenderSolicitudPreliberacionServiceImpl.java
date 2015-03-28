/**
* Nombre del Programa : AtenderSolicitudPreliberacionServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 19 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio pata atender la solicitud beneficios preliberacion
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
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.service.expediente.ObtenerDetalleCarpetaEjecucionService;
import mx.gob.segob.nsjp.service.solicitud.AtenderSolicitudPreliberacionService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del servicio pata atender la solicitud beneficios preliberacion.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class AtenderSolicitudPreliberacionServiceImpl implements
		AtenderSolicitudPreliberacionService {

	/**
	 * 
	 */
	public static final Logger logger = Logger.getLogger(AtenderSolicitudPreliberacionServiceImpl.class);
	
//	@Autowired
//	private SolicitudDAO solicitudDAO;
	
	@Autowired
	private ObtenerDetalleCarpetaEjecucionService obtenerDetalleCarpetaEjecucionService;
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.solicitud.AtenderSolicitudPreliberacionService#atenderSolicitudPreliberacionService(mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO)
	 */
	@Override
	public ExpedienteDTO atenderSolicitudPreliberacionService(
			SolicitudDTO solicitudDTO) throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA ATENDER SOLICITUDES BENEFICIOS PRELIBERACION ****/");
		
		if (solicitudDTO.getDocumentoId()==null ||
				solicitudDTO.getExpedienteDTO().getNumeroExpedienteId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
//		Solicitud solBeneficiosPre = solicitudDAO.read(solicitudDTO.getDocumentoId());
//		solBeneficiosPre.setEstatus(new Valor(EstatusSolicitud.EN_PROCESO.getValorId()));
//		solicitudDAO.update(solBeneficiosPre);
		
		//Obtener Carpeta de ejecuvion
		ExpedienteDTO carpetaEjecucion = obtenerDetalleCarpetaEjecucionService.obtenerDetalleCarpetaEjecucion(solicitudDTO.getExpedienteDTO());
		
		if (carpetaEjecucion==null)
			throw new NSJPNegocioException(CodigoError.EJCUCION_OPERACION_ESTADO_INCORRECTO);
		
		return carpetaEjecucion;
	}

}

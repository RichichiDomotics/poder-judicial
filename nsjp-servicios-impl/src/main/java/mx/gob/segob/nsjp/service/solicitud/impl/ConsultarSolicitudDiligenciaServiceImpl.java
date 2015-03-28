/**
* Nombre del Programa : ConsultarSolicitudDiligenciaServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 28 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para consultar las solicitudes diligencia
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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudDiligenciaDAO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDiligenciaDTO;
import mx.gob.segob.nsjp.model.SolicitudDiligencia;
import mx.gob.segob.nsjp.service.solicitud.ConsultarSolicitudDiligenciaService;
import mx.gob.segob.nsjp.service.solicitud.impl.transform.SolicitudDiligenciaTransformer;

/**
 * Implementacion del servicio para consultar las solicitudes diligencia.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class ConsultarSolicitudDiligenciaServiceImpl implements
		ConsultarSolicitudDiligenciaService {
	/**
	 * 
	 */
	public static final Logger logger = Logger.getLogger(ConsultarSolicitudDiligenciaServiceImpl.class);
	
	@Autowired
	private SolicitudDiligenciaDAO solicitudDiligenciaDAO;
	
	@Override
	public List<SolicitudDiligenciaDTO> consultarDiligenciasDelExpediente(
			ExpedienteDTO expedienteDTO) throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR LAS SOLICITUDES DILIGENCIA DE UN EXPEDIENTE ****/");
		
		if (expedienteDTO.getExpedienteId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		List<SolicitudDiligencia> solsDiligencia = solicitudDiligenciaDAO.consultarDiligenciasDelExpediente(expedienteDTO.getExpedienteId());
		
		List<SolicitudDiligenciaDTO> solsDiligenciaDTO = new ArrayList<SolicitudDiligenciaDTO>();
		for (SolicitudDiligencia solicitudDiligencia : solsDiligencia) {
			solsDiligenciaDTO.add(SolicitudDiligenciaTransformer.transformarSolicitudDiligencia(solicitudDiligencia));
		}
		
		return solsDiligenciaDTO;
	}

	@Override
	public SolicitudDiligenciaDTO obtenerDiligenciaById(
			SolicitudDiligenciaDTO solicitudDiligenciaDTO) throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR LAS SOLICITUDES DILIGENCIA DE UN EXPEDIENTE ****/");
		
		if (solicitudDiligenciaDTO.getDocumentoId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		SolicitudDiligencia solDiligencia = solicitudDiligenciaDAO.read(solicitudDiligenciaDTO.getDocumentoId());
		
		SolicitudDiligenciaDTO solDiligenciaDTO = SolicitudDiligenciaTransformer.transformarSolicitudDiligencia(solDiligencia);
		
		return solDiligenciaDTO;
	}

}

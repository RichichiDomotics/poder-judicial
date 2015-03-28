/**
* Nombre del Programa : RegistrarSolicitudDiligenciaServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 29 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para realizar las consultas de las solicitudes diligencia
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

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudDiligenciaDAO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDiligenciaDTO;
import mx.gob.segob.nsjp.model.SolicitudDiligencia;
import mx.gob.segob.nsjp.service.solicitud.RegistrarSolicitudDiligenciaService;
import mx.gob.segob.nsjp.service.solicitud.impl.transform.SolicitudDiligenciaTransformer;
import mx.gob.segob.nsjp.service.solicitud.impl.transform.SolicitudTransformer;

/**
 * Implementacion del servicio para realizar las consultas de las solicitudes diligencia.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class RegistrarSolicitudDiligenciaServiceImpl implements
		RegistrarSolicitudDiligenciaService {
	
	/**
	 * 
	 */
	public static final Logger logger = Logger.getLogger(RegistrarSolicitudDiligenciaServiceImpl.class);
	
	@Autowired
	private SolicitudDiligenciaDAO solicitudDiligenciaDAO;
	
	@Override
	public SolicitudDiligenciaDTO registrarSolicitudDiligencia(
			SolicitudDiligenciaDTO solicitudDiligenciaDTO)
			throws NSJPNegocioException {		
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA REGISTRAR LA SOLICITUD DE DILIGENCIA ****/");
		
		SolicitudDiligencia solDiligenciaNew = SolicitudDiligenciaTransformer.transformarSolicitudDiligencia(solicitudDiligenciaDTO);
		
		Long idSolDiligencia= solicitudDiligenciaDAO.create(solDiligenciaNew);		
		
        SolicitudDiligenciaDTO solDiligenciaDTO = new SolicitudDiligenciaDTO(idSolDiligencia); 
		
		return solDiligenciaDTO;
	}

}

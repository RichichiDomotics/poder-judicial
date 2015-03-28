/**
* Nombre del Programa : AsignarNumeroSolicitudServiceImpl.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 14 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio de generacion de solicitudes
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

import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudDefensorDAO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDefensorDTO;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.SolicitudDefensor;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.documento.GenerarNumeroDeAcuseDeAtencionService;
import mx.gob.segob.nsjp.service.solicitud.AsignarNumeroSolicitudService;
import mx.gob.segob.nsjp.service.solicitud.GenerarFolioSolicitudService;
import mx.gob.segob.nsjp.service.solicitud.impl.transform.SolicitudTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del servicio de generacion de solicitudes
 * @version 1.0
 * @author Tattva-IT
 *
 */

@Service
@Transactional
public class AsignarNumeroSolicitudServiceImpl implements
		AsignarNumeroSolicitudService {

	@Autowired
	private SolicitudDefensorDAO solicitudDefensorDAO; 
	
	@Autowired
	private GenerarNumeroDeAcuseDeAtencionService generarNumeroDeAcuseDeAtencionService;
    

	private static final Logger logger = Logger
    .getLogger(AsignarNumeroSolicitudServiceImpl.class);
	
	//FIXME ELIMINAR ESTA CLASE YA NO SE USA
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public synchronized SolicitudDefensorDTO asignarNumeroSolicitud(
			Long  idSolicitud)
			throws NSJPNegocioException {
		

		Long loNumeroAcuse = null;
		SolicitudDefensorDTO solDTOResp = new SolicitudDefensorDTO(idSolicitud);

		loNumeroAcuse = generarNumeroDeAcuseDeAtencionService.generarNumeroDeAcuse(Instituciones.DEF.getValorId(),idSolicitud);
		
		solDTOResp.setFolioDocumento(loNumeroAcuse.toString());
//		solDTOResp.setExpedienteDTO(solicitudDTO.getExpedienteDTO());
//		solDTOResp.setDocumentoId(solctNueva);
		logger.debug("Numero de Acuse recibido" + loNumeroAcuse);
		
//		SolicitudDefensor loSolicitudDefensor = solicitudDefensorDAO.read(solctNueva);
//		loSolicitudDefensor.setFolioDocumento(loNumeroAcuse.toString());
//		solicitudDefensorDAO.update(loSolicitudDefensor);
		
		return solDTOResp;
	}

}

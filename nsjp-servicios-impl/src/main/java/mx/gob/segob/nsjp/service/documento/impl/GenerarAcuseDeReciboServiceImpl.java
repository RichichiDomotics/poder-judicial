/**
 * Nombre del Programa  : GenerarAcuseDeReciboServiceImpl.java
 * Autor                : Daniel Jiménez
 * Compania             : TATTVA-IT
 * Proyecto             : NSJP                    Fecha: 24 Jun 2011
 * Marca de cambio      : N/A
 * Descripcion General  : Genera un acuse de recibo y lo asocia a la solicitud correspondiente
 * Programa Dependiente : N/A
 * Programa Subsecuente : N/A
 * Cond. de ejecucion   : N/A
 * Dias de ejecucion    : N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                :N/A
 * Compania             :N/A
 * Proyecto             :N/A                                 Fecha: N/A
 * Modificacion         :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.documento.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

import javax.xml.namespace.QName;

import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.documento.AcuseReciboDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudDAO;
import mx.gob.segob.nsjp.model.AcuseRecibo;
import mx.gob.segob.nsjp.model.Solicitud;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.documento.GenerarAcuseDeReciboService;
import mx.gob.segob.nsjp.ws.cliente.generaracuse.GenerarAcuseDeReciboExporter;
import mx.gob.segob.nsjp.ws.cliente.generaracuse.GenerarAcuseDeReciboExporterImplService;
import mx.gob.segob.nsjp.ws.cliente.generaracuse.NSJPNegocioException_Exception;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Genera un acuse de recibo y lo asocia a la solicitud correspondiente
 * 
 * @version 1.0
 * @author Daniel Jiménez
 * 
 */
@Service("generarAcuseDeReciboService")
@Transactional
public class GenerarAcuseDeReciboServiceImpl implements
		GenerarAcuseDeReciboService {
	
	private final static Logger logger = Logger.getLogger(GenerarAcuseDeReciboServiceImpl.class);
	
	@Autowired
	private SolicitudDAO solicitudDAO;
	@Autowired
	private AcuseReciboDAO acuseReciboDAO;

	@Override
	public void generarAcuseDeRecibo(Long idSolicitud)
			throws NSJPNegocioException {
	    logger.info("Inicia - generarAcuseDeRecibo("+idSolicitud+")");
		Solicitud solicitud  = solicitudDAO.read(idSolicitud);
		registrarAcuseDeRecibo(solicitud);
		logger.info("Fin - generarAcuseDeRecibo("+idSolicitud+")");
	}
	
	@Override
	public void generarAcuseDeRecibo(String folio)
			throws NSJPNegocioException {
	    logger.info("Inicia - generarAcuseDeRecibo("+folio+")");
		Solicitud solicitud  = solicitudDAO.obtenerSolicitudPorFolio(folio);
		registrarAcuseDeRecibo(solicitud);
		logger.info("Fin - generarAcuseDeRecibo("+folio+")");
	}
	
	/**
	 * Regista un acuse de recibo para la <code>solicitud</code>
	 * @param solicitud a la que hay que generarle el acuse de recibo
	 * @throws NSJPNegocioException
	 */
	private void registrarAcuseDeRecibo(Solicitud solicitud) throws NSJPNegocioException{
		if(solicitud != null){
			if(solicitud.getFuncionarioSolicitante() != null){
				AcuseRecibo acuse = new AcuseRecibo();
				acuse.setSolicitud(solicitud);
				acuse.setFechaAcuse(Calendar.getInstance().getTime());
				acuse.setNombreRecibe(solicitud.getDestinatario().getNombreCompleto()); 
				acuse.setConfInstitucion(solicitud.getConfInstitucion()); 
				acuse.setTipoDocumento(new Valor(TipoDocumento.ACUSE.getValorId()));
				Long id = acuseReciboDAO.create(acuse);
				logger.debug("Se creo el acuse de recibo "+id);
//				solicitud.setEstatus(new Valor(EstatusSolicitud.EN_PROCESO.getValorId()));
//				solicitudDAO.saveOrUpdate(solicitud);
			}else{
				enviarAcuseDeRecibo(solicitud);
			}
		}else{
			throw new NSJPNegocioException(CodigoError.INFORMACION_PARAMETROS_ERRONEA);
		}
		
	}
	
	/**
	 * Envia una petición al sistem que origino la solicitud para almacenar el acuse de recibo y 
	 * actualizar el estado de la misma.
	 * @param solicitud
	 * @throws NSJPNegocioException
	 */
	private void enviarAcuseDeRecibo(Solicitud solicitud) throws NSJPNegocioException{
		try {
			final QName SERVICE_NAME = new QName("http://impl.ws.service.nsjp.segob.gob.mx/", "GenerarAcuseDeReciboExporterImplService");
			URL wsdlURL  = new URL(solicitud.getConfInstitucion().getUrlInst()+ "/RegistrarSolicitudDefensorAreaExternaExporterImplService?wsdl");
			GenerarAcuseDeReciboExporterImplService ss = new GenerarAcuseDeReciboExporterImplService(wsdlURL, SERVICE_NAME);
	        GenerarAcuseDeReciboExporter port = ss.getGenerarAcuseDeReciboExporterImplPort();
            logger.debug("Port obteido....");
            port.generarAcuseDeRecibo(solicitud.getFolioSolicitud());
		} catch (MalformedURLException e) {
			throw new NSJPNegocioException(CodigoError.INFORMACION_PARAMETROS_ERRONEA);
		} catch (NSJPNegocioException_Exception e) {
			throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION);
		}
	}
}

package mx.gob.segob.nsjp.service.solicitud.impl;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.audiencia.AudienciaDAO;
import mx.gob.segob.nsjp.dao.documento.DocumentoDAO;
import mx.gob.segob.nsjp.dao.documento.NotificacionDAO;
import mx.gob.segob.nsjp.dao.institucion.ConfInstitucionDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudDAO;
import mx.gob.segob.nsjp.model.ConfInstitucion;
import mx.gob.segob.nsjp.service.solicitud.GenerarFolioSolicitudService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GenerarFolioSolicitudServiceImpl implements
		GenerarFolioSolicitudService {

	@Autowired
	private SolicitudDAO solicitudDAO;

	@Autowired
	private NotificacionDAO notificacionDAO;
	@Autowired
	private DocumentoDAO documentoDAO;
	
	@Autowired
	private ConfInstitucionDAO confInstitucionDAO;
	@Autowired
	AudienciaDAO audienciaDAO;
	
	private static Long folioSol = 0L;
	private static Long folioNot = 0L;
	private static Long folioDoc = 0L;
	private static ConfInstitucion institucion = null;
	
	public synchronized String generarFolioSolicitud() throws NSJPNegocioException {
		if(folioSol == 0){
			String ultimo = solicitudDAO.obtenerUltimoFolioSolicitud();
			if(ultimo != null && !ultimo.isEmpty()){
				folioSol = Long.parseLong(ultimo.split("/")[1].substring(4));
			}
		}
		
		if(institucion == null){
			institucion = confInstitucionDAO.consultarInsitucionActual();
		}
		
		String format1 = "00000" ;  
		DecimalFormat fm1 = new DecimalFormat( format1, new DecimalFormatSymbols(Locale.US));
		
		return institucion.getMonograma()+"/"+Calendar.getInstance().get(Calendar.YEAR)+fm1.format(++folioSol);
	}

	
	@Override
	public synchronized String generarFolioNotificacion() throws NSJPNegocioException {
		if(folioNot == 0){
			String ultimo = notificacionDAO.obtenerUltimoFolioNotificacion();
			if(ultimo != null && !ultimo.isEmpty()){
				folioNot = Long.parseLong(ultimo.split("/")[1].substring(4));
			}
		}
		
		if(institucion == null){
			institucion = confInstitucionDAO.consultarInsitucionActual();
		}
		
		String format1 = "00000" ;  
		DecimalFormat fm1 = new DecimalFormat( format1, new DecimalFormatSymbols(Locale.US));
		
		return institucion.getMonograma()+"/"+Calendar.getInstance().get(Calendar.YEAR)+fm1.format(++folioNot);
	}
	
	@Override
	public synchronized String generarFoliodDocumento() throws NSJPNegocioException {
		if(folioDoc == 0){
			String ultimo = documentoDAO.obtenerUltimoFolioDocumento();
			if(ultimo != null && !ultimo.isEmpty()){
				folioDoc = Long.parseLong(ultimo.split("/")[1].substring(4));
			}
		}
		
		if(institucion == null){
			institucion = confInstitucionDAO.consultarInsitucionActual();
		}
		
		String format1 = "00000" ;  
		DecimalFormat fm1 = new DecimalFormat( format1, new DecimalFormatSymbols(Locale.US));
		
		return institucion.getMonograma()+"/"+Calendar.getInstance().get(Calendar.YEAR)+fm1.format(++folioDoc);
	}

	
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.solicitud.GenerarFolioSolicitudService#generarFolioAudiencia()
	 */
	@Override
	public synchronized String generarFolioAudiencia() throws NSJPNegocioException {
		if(folioDoc == 0){
			String ultimo = audienciaDAO.obtenerUltimoFolioAudiencia();
			if(ultimo != null && !ultimo.isEmpty()){
				folioDoc = Long.parseLong(ultimo.split("/")[1].substring(4));
			}
		}
		
		if(institucion == null){
			institucion = confInstitucionDAO.consultarInsitucionActual();
		}
		
		String format1 = "00000" ;  
		DecimalFormat fm1 = new DecimalFormat( format1, new DecimalFormatSymbols(Locale.US));
		
		return institucion.getMonograma()+"/"+Calendar.getInstance().get(Calendar.YEAR)+fm1.format(++folioDoc);
	}

}

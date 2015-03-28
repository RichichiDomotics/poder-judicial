package mx.gob.segob.nsjp.service.documento.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.archivo.ArchivoDigitalDAO;
import mx.gob.segob.nsjp.dao.documento.DocumentoDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudAdjuntosDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudDAO;
import mx.gob.segob.nsjp.model.ArchivoDigital;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.model.Solicitud;
import mx.gob.segob.nsjp.model.SolicitudAdjuntos;
import mx.gob.segob.nsjp.model.SolicitudAdjuntosId;
import mx.gob.segob.nsjp.service.documento.AsociarArchivosDigitalesASolicitudService;

@Service
@Transactional
public class AsociarArchivosDigitalesASolicitudServiceImpl implements
		AsociarArchivosDigitalesASolicitudService {

	Log logger;
	@Autowired
	SolicitudDAO solicitudDAO;
	@Autowired
	SolicitudAdjuntosDAO solicitudADjuntosDAO;
	@Autowired
	ArchivoDigitalDAO archivoDAO;
	
	@Autowired
	DocumentoDAO documentoDAO;

	@Override
	@Transactional
	public Boolean asociarArchivosDigitalesASolicitud(Long idSolicitud,
			String cadenaIds) throws Exception {
		
		Boolean respuesta = false;
		
		//List<ArchivoDigital> archivosDigitales = null;
		List<ArchivoDigital> archivosDigitales = new ArrayList<ArchivoDigital>();
		
		Solicitud sol = new Solicitud();
		if(idSolicitud!=null && cadenaIds != null && !cadenaIds.isEmpty())
		{
			sol = solicitudDAO.read(idSolicitud);
			String solIds[] = cadenaIds.split(",");
			for(int i=0;i<solIds.length;i++)
			{
				try
				{
					ArchivoDigital archivoDigital = documentoDAO.read(Long.parseLong(solIds[i])).getArchivoDigital();
					
					if(archivoDigital != null){
						archivosDigitales.add(archivoDigital);
					}
					
					//archivosDigitales.add(archivoDAO.read(Long.parseLong(solIds[i])));
				}catch (Exception ex)
				{
					logger.debug(ex.getStackTrace());
				}				
			}
			if(sol!=null && archivosDigitales.size()>0)				
			{
				try
				{
					for(ArchivoDigital row:archivosDigitales)
					{
						SolicitudAdjuntos solAdj = new SolicitudAdjuntos();
						solAdj.setArchivoDigital(row);
						solAdj.setSolicitud(sol);
						solAdj.setId(new SolicitudAdjuntosId(sol.getDocumentoId(),row.getArchivoDigitalId()));
						solicitudADjuntosDAO.create(solAdj);
					}
					respuesta = true;
				}catch(Exception ex)
				{
					respuesta = false;
					logger.debug(ex.getStackTrace());
					throw ex;
				}
			}
		}else
		{
			respuesta = false;
		}
		return respuesta;
	}
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.documento.AsociarArchivosDigitalesASolicitudService#asociarArchivoDigitalADocumento(java.lang.Long, java.lang.Long)
	 */
	@Override
	public void asociarArchivoDigitalADocumento(Long documentoId,
			Long archivoDigitalId) throws NSJPNegocioException {
		Documento doc = documentoDAO.read(documentoId);
		if(doc != null){
			doc.setArchivoDigital(new ArchivoDigital());
			doc.getArchivoDigital().setArchivoDigitalId(archivoDigitalId);
			documentoDAO.saveOrUpdate(doc);
		}
		
	}

}

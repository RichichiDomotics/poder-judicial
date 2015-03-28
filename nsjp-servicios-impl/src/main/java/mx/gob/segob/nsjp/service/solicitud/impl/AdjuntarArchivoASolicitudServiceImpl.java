/**
 * Nombre del Programa  : AdjuntarArchivoASolicitudServiceImpl.java
 * Autor                : Daniel Jiménez
 * Compania             : TATTVA-IT
 * Proyecto             : NSJP                    Fecha: 24 Jun 2011
 * Marca de cambio      : N/A
 * Descripcion General  : Guarda un archivo omo un ArchivoDijital y lo asocia a 
 * 						  una solicitud como archivo adjunto.
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

package mx.gob.segob.nsjp.service.solicitud.impl;

import mx.gob.segob.nsjp.comun.enums.forma.Formas;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.archivo.ArchivoDigitalDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudAdjuntosDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudDAO;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.model.ArchivoDigital;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Solicitud;
import mx.gob.segob.nsjp.model.SolicitudAdjuntos;
import mx.gob.segob.nsjp.model.SolicitudAdjuntosId;
import mx.gob.segob.nsjp.service.archivo.impl.transform.ArchivoDigitalTransformer;
import mx.gob.segob.nsjp.service.solicitud.AdjuntarArchivoASolicitudService;
import mx.gob.segob.nsjp.service.solicitud.GenerarFolioSolicitudService;
import mx.gob.segob.nsjp.service.solicitud.impl.transform.SolicitudTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdjuntarArchivoASolicitudServiceImpl implements
		AdjuntarArchivoASolicitudService {

	private static final Logger logger  = Logger.getLogger(AdjuntarArchivoASolicitudServiceImpl.class);
	
	@Autowired
	private SolicitudDAO solicitudDAO;
	@Autowired
	private ArchivoDigitalDAO archivoDigitalDAO;
	@Autowired
	private SolicitudAdjuntosDAO solicitudAdjuntosDAO;
    @Autowired
    private GenerarFolioSolicitudService generarFolioSolicitudService;
	
	@Override
	public void adjuntarArchivoASolicitud(SolicitudDTO solicitud,
			ArchivoDigitalDTO adjunto) throws NSJPNegocioException {
		
		Solicitud sol = SolicitudTransformer.solicitudTransformer(solicitud);
        sol.setForma(new Forma(Formas.SOLICITUD.getValorId()));
		sol.setNombreDocumento("Solicitud de "+sol.getTipoSolicitud().getValor());
		sol.setNumeroExpediente(new NumeroExpediente(solicitud.getExpedienteDTO().getNumeroExpedienteId()));
        sol.setFolioSolicitud(generarFolioSolicitudService.generarFolioSolicitud());
		Long idSol = solicitudDAO.create(sol);
		
		logger.info("SOLICITUD INSERTADA CON ID :: "+idSol);
		
		ArchivoDigital ad = ArchivoDigitalTransformer.transformarArchivoDigitalDTO(adjunto);
		
		Long idAD = archivoDigitalDAO.create(ad);
		
		logger.info("ARCHIVO DIGITAL INSERTADO CON ID :: "+idSol);
		
		SolicitudAdjuntos solicitudAdjuntos = new SolicitudAdjuntos();
		SolicitudAdjuntosId id = new SolicitudAdjuntosId();
		id.setArchivoDigitalId(idAD);
		id.setSolicitudId(idSol);
		solicitudAdjuntos.setId(id);
		
		solicitudAdjuntosDAO.create(solicitudAdjuntos);
	}
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.solicitud.AdjuntarArchivoASolicitudService#adjuntarArchivoASolicitudBasico(mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO, mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO)
	 */
	@Override
	public void adjuntarArchivoASolicitudBasico(SolicitudDTO solicitud,
			ArchivoDigitalDTO adjunto) throws NSJPNegocioException {
		  
				
		ArchivoDigital ad = ArchivoDigitalTransformer.transformarArchivoDigitalDTO(adjunto);
		
		Long idAD = archivoDigitalDAO.create(ad);
		
		logger.info("ARCHIVO DIGITAL INSERTADO CON ID :: "+idAD);
		
		SolicitudAdjuntos solicitudAdjuntos = new SolicitudAdjuntos();
		SolicitudAdjuntosId id = new SolicitudAdjuntosId();
		id.setArchivoDigitalId(idAD);
		id.setSolicitudId(solicitud.getDocumentoId());
		solicitudAdjuntos.setId(id);
		
		solicitudAdjuntosDAO.create(solicitudAdjuntos);
		
	}

}

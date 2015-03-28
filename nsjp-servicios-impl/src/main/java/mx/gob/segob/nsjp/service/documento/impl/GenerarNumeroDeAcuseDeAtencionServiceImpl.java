/**
 * Nombre del Programa : ConsultarUltimoAcuseSServiceImpl.java
 * Autor                            : rgama
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    
 * Marca de cambio        : N/A
 * Descripcion General    : Implementación del servicio de negocio para la carga de documentos
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
package mx.gob.segob.nsjp.service.documento.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.documento.AcuseReciboDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudDefensorDAO;
import mx.gob.segob.nsjp.model.AcuseRecibo;
import mx.gob.segob.nsjp.model.ConfInstitucion;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.Solicitud;
import mx.gob.segob.nsjp.model.SolicitudDefensor;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.documento.GenerarNumeroDeAcuseDeAtencionService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio de negocio para Generar un Numero de Acuse de Atencion
 * @author rgama
 *
 */
@Service
@Transactional
public class GenerarNumeroDeAcuseDeAtencionServiceImpl implements GenerarNumeroDeAcuseDeAtencionService {
	
	@Autowired
	AcuseReciboDAO acuseReciboDAO;
	@Autowired
	SolicitudDefensorDAO solicitudDefensorDAO;
	private static final Logger logger  = Logger.getLogger(GenerarNumeroDeAcuseDeAtencionServiceImpl.class);
	
	public Long generarNumeroDeAcuse(Long idInstitucion,Long idSolicitud) throws NSJPNegocioException{
		Long idUltimoAcuse = this.consultarUltimoAcuse();
		Long idAcuseRegistrado = asignarNumeroAcuse(idUltimoAcuse,idSolicitud);
		this.asociarInstitucion(idInstitucion, idAcuseRegistrado);
		AcuseRecibo acuseRecibo = acuseReciboDAO.read(idAcuseRegistrado);
		SolicitudDefensor solicitud = solicitudDefensorDAO.read(idSolicitud);
		solicitud.getAcuseRecibos().add(acuseRecibo);
		solicitudDefensorDAO.update(solicitud);
		logger.debug("::::: El id del nuevo numero de acuse de atencion es-->" + idAcuseRegistrado);
		logger.debug("::::: El folio asignado al nuevo numero de acuse es -->" + acuseRecibo.getFolioDocumento());	
		return Long.parseLong(acuseRecibo.getFolioDocumento());
	}
	
	@Override
	public Long consultarUltimoAcuse()	
			throws NSJPNegocioException {			
			return acuseReciboDAO.consultarUltimoAcuse();
	}
	
	public Long asignarNumeroAcuse(Long idAcuseReciboAnterior,Long idSolicitud) throws NSJPNegocioException{
		AcuseRecibo loAcuseRecibo = new AcuseRecibo();
		
		loAcuseRecibo.setFechaAcuse(new Date());
		loAcuseRecibo.setNombreRecibe("");

		//SE DEBE DE ELIMINAR LA RELACION CON LA SOLICITUD
		Solicitud loSolicitud = new Solicitud();
		loSolicitud.setDocumentoId(idSolicitud);
		loAcuseRecibo.setSolicitud(loSolicitud);
		
		//Se asigna el consecutivo del folio anterior
		Long numFolioAnterior = 0L;
		if(idAcuseReciboAnterior != 0){
			AcuseRecibo loAR =acuseReciboDAO.read(idAcuseReciboAnterior);
			//Se obtiene el numero de folio anteior
			String sFolioAnterior = loAR.getFolioDocumento();
			numFolioAnterior = Long.parseLong(sFolioAnterior);		
		}		
		loAcuseRecibo.setFolioDocumento(String.valueOf(++numFolioAnterior));
					
		//Campos obligatorios de un documento
		loAcuseRecibo.setNombreDocumento("Acuse de recibo");
		loAcuseRecibo.setFechaCreacion(new Date());
		loAcuseRecibo.setForma(new Forma(1L));
		loAcuseRecibo.setTipoDocumento(new Valor(1780L));//Acuse
		Long idAcuse = acuseReciboDAO.create(loAcuseRecibo);
//		logger.debug("::::: El id del nuevo acuse de recibo es-->" + idAcuse);
//		logger.debug("::::: El folio asignado es -->" + numFolioAnterior);			
		return idAcuse;		
	}
	
	public void asociarInstitucion(Long idInstitucion, Long idAcuseDeRecibo) throws NSJPNegocioException{
		if(idInstitucion != null && idAcuseDeRecibo >0){
			AcuseRecibo loAR = acuseReciboDAO.read(idAcuseDeRecibo);
			loAR.setConfInstitucion(new ConfInstitucion(idInstitucion));	
		}					
	}
	
}

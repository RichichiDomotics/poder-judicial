/**
 * Nombre del Programa : ConsultarResolutivosAudienciaServiceImpl.java
 * Autor                            : adrian
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 23/06/2011
 * Marca de cambio        : N/A
 * Descripcion General    : Describir el objetivo de la clase de manera breve
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
package mx.gob.segob.nsjp.service.audiencia.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dao.audiencia.AudienciaDAO;
import mx.gob.segob.nsjp.dao.audiencia.ResolutivoDAO;
import mx.gob.segob.nsjp.dto.resolutivo.ResolutivoDTO;
import mx.gob.segob.nsjp.dto.resolutivo.ResolutivoViewDTO;
import mx.gob.segob.nsjp.model.Resolutivo;
import mx.gob.segob.nsjp.service.audiencia.ConsultarResolutivosAudienciaService;
import mx.gob.segob.nsjp.service.audiencia.impl.transform.ResolutivoTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author adrian
 * 
 */
@Service
@Transactional
public class ConsultarResolutivosAudienciaServiceImpl implements
		ConsultarResolutivosAudienciaService {

	private final static Logger logger = Logger
			.getLogger(ConsultarAudienciaServiceImpl.class);

	@Autowired
	private AudienciaDAO audDao;

	@Autowired
	private ResolutivoDAO resolutivoDAO;
	
	@Override
	public List<ResolutivoDTO> consultarResolutivosAudiencia(Long idAudiencia)
			throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR RESOLUTIVOS DE UNA AUDIENCIA ****/");
		
		/*Verificación de parámetros*/
		if (idAudiencia==null || idAudiencia<0)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		/*Operación*/
		List<Resolutivo> resolutivos =audDao.consultarResolutivosAudiencia(idAudiencia);
		
		/*Transformación*/
		List<ResolutivoDTO> resDtos = new ArrayList<ResolutivoDTO>();
		for (Resolutivo res : resolutivos) {
			resDtos.add(ResolutivoTransformer.transformarResolutivo(res));
		}
		
		return resDtos;
	}

	@Override
	public List<ResolutivoViewDTO> consultarResolutivosByAudienciaId(
			Long idAudiencia) throws NSJPNegocioException {
		List<Resolutivo> lista = resolutivoDAO.consultarResolutivosByAudienciaId(idAudiencia);
		List<ResolutivoViewDTO> resolutivos = new LinkedList<ResolutivoViewDTO>();
		for(Resolutivo resolut : lista){
			resolutivos.add(ResolutivoTransformer.transformarResolutivoView(resolut));
		}

		return resolutivos;
	}
	
	@Override
	public List<ResolutivoViewDTO> consultarResolutivosByAudienciaIdWithDocumento (
			Long idAudiencia) throws NSJPNegocioException{
		List<ResolutivoViewDTO> resp = new ArrayList <ResolutivoViewDTO> ();
		List<Resolutivo> lista = resolutivoDAO.consultarResolutivosByAudienciaIdWithDocumento(idAudiencia);
		
		ResolutivoViewDTO resolutivo = null;
		for(Resolutivo resolut : lista){
			resp.add(ResolutivoTransformer.transformarResolutivoView(resolut));
		}
		
		return resp;
		
	}
	
	@Override
	public List<ResolutivoViewDTO> consultarResolutivosByAudienciaIdWithDocumentoAndArchDigital (
			Long idAudiencia) throws NSJPNegocioException{
		List<ResolutivoViewDTO> resp = new ArrayList <ResolutivoViewDTO> ();
		List<Resolutivo> lista = resolutivoDAO.consultarResolutivosByAudienciaIdWithDocumentoAndArchDigital(idAudiencia);
		
		ResolutivoViewDTO resolutivo = null;
		for(Resolutivo resolut : lista){
			resp.add(ResolutivoTransformer.transformarResolutivoView(resolut));
		}
		
		return resp;
		
	}
	
	

}

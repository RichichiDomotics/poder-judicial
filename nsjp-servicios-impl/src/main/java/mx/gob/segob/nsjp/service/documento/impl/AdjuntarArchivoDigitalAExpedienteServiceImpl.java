/**
 * 
 */
package mx.gob.segob.nsjp.service.documento.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.actividad.Actividades;
import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.archivo.ArchivoDigitalDAO;
import mx.gob.segob.nsjp.dao.documento.DocumentoDAO;
import mx.gob.segob.nsjp.dao.expediente.ActividadDAO;
import mx.gob.segob.nsjp.dao.forma.FormaDAO;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.model.Actividad;
import mx.gob.segob.nsjp.model.ArchivoDigital;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.archivo.impl.transform.ArchivoDigitalTransformer;
import mx.gob.segob.nsjp.service.documento.AdjuntarArchivoDigitalAExpedienteService;
import mx.gob.segob.nsjp.service.documento.impl.tranform.DocumentoTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author adrian
 *
 */
@Service
@Transactional
public class AdjuntarArchivoDigitalAExpedienteServiceImpl implements
		AdjuntarArchivoDigitalAExpedienteService {
	
	public final static Logger logger = 
		Logger.getLogger(AdjuntarArchivoDigitalAExpedienteServiceImpl.class);
	
	@Autowired
	private DocumentoDAO documentoDAO;
	@Autowired
	private ActividadDAO actividadDAO;
	@Autowired
	private FormaDAO formaDAO;
	@Autowired
	private ArchivoDigitalDAO archivoDigitalDAO;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.documento.AdjuntarArchivoDigitalAExpedienteService#adjuntarArchivoDigitalAExpediente(mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO, mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO)
	 */
	@Override
	public Long adjuntarArchivoDigitalAExpediente(ExpedienteDTO expedienteDTO,
			ArchivoDigitalDTO archivoDigitalDTO, FuncionarioDTO funcionarioDTO, Actividades actividad) throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA ADJUNTAR UN ARCHIVO DIGITAL A UN EXPEDIENTE ****/");

		if(expedienteDTO==null||archivoDigitalDTO==null||funcionarioDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(expedienteDTO.getExpedienteId()==null||funcionarioDTO.getClaveFuncionario()==null||archivoDigitalDTO.getContenido()==null||archivoDigitalDTO.getNombreArchivo()==null||archivoDigitalDTO.getTipoArchivo()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		logger.debug(ArchivoDigitalTransformer.transformarArchivoDigitalDTO(archivoDigitalDTO)+" Archivo transformado :)");
		ArchivoDigital archivoD = ArchivoDigitalTransformer.transformarArchivoDigitalDTO(archivoDigitalDTO);
		
		if(archivoD.getNombreArchivo() != null && archivoD.getNombreArchivo().length()>60){
			archivoD.setNombreArchivo(archivoD.getNombreArchivo().substring(0, 60));
		}
		if(archivoD.getTipoArchivo() != null && archivoD.getTipoArchivo().length()>10){
			archivoD.setTipoArchivo(archivoD.getTipoArchivo().substring(0,10));
		}
		
		Long idArchivo = archivoDigitalDAO.create(archivoD);
				
		Documento documento=new Documento();
		ArchivoDigital archD=new ArchivoDigital();
		archD.setArchivoDigitalId(idArchivo);
		documento.setArchivoDigital(archD);
		
		/*Obligatorios de Documento*/
		documento.setNombreDocumento(archivoD.getNombreArchivo());
		documento.setFechaCreacion(new Date());
		documento.setTipoDocumento(new Valor(TipoDocumento.ARCHIVO_ADJUNTADO.getValorId()));
		documento.setForma(formaDAO.consultarFormaPorNombre("Blanco"));// Plantilla en Blanco
		documento.setEsGuardadoParcial(false);
		Long idDocumento = documentoDAO.create(documento);
		
		Actividad actividadDB=new Actividad();
		Documento doc=new Documento();
		doc.setDocumentoId(idDocumento);
		actividadDB.setDocumento(doc);
		/*Obligatorios Actividad*/
		actividadDB.setFechaCreacion(new Date());
		actividadDB.setTipoActividad(new Valor(actividad.getValorId()));
		actividadDB.setExpediente(new Expediente(expedienteDTO.getExpedienteId()));
		actividadDB.setFuncionario(new Funcionario(funcionarioDTO.getClaveFuncionario()));
		
		Long idActividad = actividadDAO.create(actividadDB);
				
		return idActividad;
	}
	
	
	@Override
	public Long adjuntarDocumentoAExpediente(ExpedienteDTO expedienteDTO,
			DocumentoDTO documentoDTO, FuncionarioDTO funcionarioDTO, Actividades actividad, TipoDocumento tipoDocumento) throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA ADJUNTAR UN ARCHIVO DIGITAL A UN EXPEDIENTE ****/");

		if(expedienteDTO==null|| documentoDTO == null || documentoDTO.getArchivoDigital()==null||funcionarioDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		else if(expedienteDTO.getExpedienteId()==null||funcionarioDTO.getClaveFuncionario()==null||documentoDTO.getArchivoDigital().getContenido()==null||documentoDTO.getArchivoDigital().getNombreArchivo()==null||documentoDTO.getArchivoDigital().getTipoArchivo()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		Documento documento = DocumentoTransformer.transformarDocumentoDTO(documentoDTO);
		ArchivoDigital archivoD = documento.getArchivoDigital();
		
		if(archivoD.getNombreArchivo() != null && archivoD.getNombreArchivo().length()>60){
			archivoD.setNombreArchivo(archivoD.getNombreArchivo().substring(0, 60));
		}
		if(archivoD.getTipoArchivo() != null && archivoD.getTipoArchivo().length()>10){
			archivoD.setTipoArchivo(archivoD.getTipoArchivo().substring(0,10));
		}
		
		Long idArchivo = archivoDigitalDAO.create(archivoD);				
		ArchivoDigital archD=new ArchivoDigital();
		archD.setArchivoDigitalId(idArchivo);		
		documento.setArchivoDigital(archD);
		
		/*Obligatorios de Documento*/
		documento.setNombreDocumento(archivoD.getNombreArchivo());
		documento.setFechaCreacion(new Date());
		//Se asigna el tipo de documento
		if(tipoDocumento != null && tipoDocumento.getValorId() > 0)
			documento.setTipoDocumento(new Valor(tipoDocumento.getValorId()));
		else
			documento.setTipoDocumento(new Valor(TipoDocumento.ARCHIVO_ADJUNTADO.getValorId()));
		
		documento.setForma(formaDAO.consultarFormaPorNombre("Blanco"));// Plantilla en Blanco
		Long idDocumento = documentoDAO.create(documento);
		
		Actividad actividadDB=new Actividad();
		Documento doc=new Documento();
		doc.setDocumentoId(idDocumento);
		actividadDB.setDocumento(doc);
		/*Obligatorios Actividad*/
		actividadDB.setFechaCreacion(new Date());
		actividadDB.setTipoActividad(new Valor(actividad.getValorId()));
		actividadDB.setExpediente(new Expediente(expedienteDTO.getExpedienteId()));
		actividadDB.setFuncionario(new Funcionario(funcionarioDTO.getClaveFuncionario()));
		
		actividadDAO.create(actividadDB);
		return idDocumento;
	}

}

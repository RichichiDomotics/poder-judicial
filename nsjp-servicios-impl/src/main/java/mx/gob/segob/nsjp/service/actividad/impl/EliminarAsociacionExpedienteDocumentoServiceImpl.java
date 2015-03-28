/**
 * 
 */
package mx.gob.segob.nsjp.service.actividad.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.ActividadDAO;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.model.Actividad;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.service.actividad.EliminarAsociacionExpedienteDocumentoService;

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
public class EliminarAsociacionExpedienteDocumentoServiceImpl implements
		EliminarAsociacionExpedienteDocumentoService {
	
	public final static Logger logger = 
		Logger.getLogger(EliminarAsociacionExpedienteDocumentoServiceImpl.class);
	@Autowired
	private ExpedienteDAO expedienteDAO;
	@Autowired
	private ActividadDAO actividadDAO;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.actividad.EliminarAsociacionExpedienteDocumentoService#eliminarAsocArchivoAExpediente(mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO, mx.gob.segob.nsjp.dto.documento.DocumentoDTO)
	 */
	@Override
	public void eliminarAsocArchivoAExpediente(ExpedienteDTO expedienteDTO,
			DocumentoDTO documentoDTO)throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA ELIMINAR ASOCIACIÓN DE UN DOCUMENTO PARA UN EXPEDIENTE ****/");
		
		/*Verificación de parámetros*/
		if (expedienteDTO==null || documentoDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		if (expedienteDTO.getNumeroExpediente()==null || documentoDTO.getDocumentoId()==null ||expedienteDTO.getArea()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		/*Operación*/
//		Long expediente = expedienteDAO.consultarExpedientePorNumeroExpediente(expedienteDTO.getNumeroExpediente());
		List<Expediente> expedientes = expedienteDAO.buscarExpedientes(expedienteDTO.getNumeroExpediente(), expedienteDTO.getArea().getAreaId(),null);
		Actividad actividad=actividadDAO.consultarActividadXExpedienteYDocumento(expedientes.get(0).getExpedienteId(),documentoDTO.getDocumentoId());
		actividad.setDocumento(null);
		actividadDAO.update(actividad);
		
	}

}

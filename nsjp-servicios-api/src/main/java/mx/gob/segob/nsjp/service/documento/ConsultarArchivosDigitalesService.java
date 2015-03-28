package mx.gob.segob.nsjp.service.documento;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;

public interface ConsultarArchivosDigitalesService {
	
	/**
	 * Operación que permite consultar cuales son los archivos digitales
	 * asociados a una Solicitud.
	 * @param idSolicitud : Long El id de solicitud de la cual se quieran consultar sus
	 * archivos digitales asociados	 
	 * @return List<ArchivoDigitalDTO> Lista con los archivos digitales asociados a la solicitud
	 * @throws NSJPNegocioException
	 */
	public List<ArchivoDigitalDTO> consultarArchivosDigitalesXSolicitud(Long IdSolicitud) throws NSJPNegocioException;
	
	
	/**
	 * Consulta un archivo digital completo en base al ID de documento
	 * @param documentoId ID del documento para buscar el archivo digital
	 * @return Archivo digital Completo
	 * @throws NSJPNegocioException
	 * @author Emigdio Hernández
	 */
	ArchivoDigitalDTO consultarArchivoDigitalCompleto(Long documentoId) throws NSJPNegocioException;
	
	
	/**
	 * Consulta los documentos asociados a un archivo digital completo en base
	 * @param IdSolicitud del documento para buscar el documento
	 * @return Documentos completos
	 * @throws NSJPNegocioException 
	 */
	List<DocumentoDTO> consultarArchivosDigitalesXSolicitudPericial(Long IdSolicitud) throws NSJPNegocioException;
	
}

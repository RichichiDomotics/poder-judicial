package mx.gob.segob.nsjp.service.documento;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;

public interface AsociarArchivosDigitalesASolicitudService 	   
{
	
	/**
	 * Operación que permite consultar cuales son los archivos digitales
	 * asociados a una Solicitud.
	 * @param idSolicitud : Long El id de solicitud a la cual se asociaran archivos digitales
	 * @param cadenaIds : String una lista de id's de archivos digitales separados por coma (,)	 
	 * @return Boolean Devuelve true si la operación fué exitosa a l guardar todos los registros
	 * @throws Exception
	 */	
	
	public Boolean asociarArchivosDigitalesASolicitud(Long idSolicitud,
			String cadenaIds) throws Exception;	
	/**
	 * Asocia un archivo digital ya creado a un documento ya creado
	 * @param documentoId ID del documento al cuál se asociará el archivo
	 * @param archivoDigitalId Archivo digital a asociar
	 * @throws NSJPNegocioException
	 * @author Emigdio Hernández
	 */
	public void asociarArchivoDigitalADocumento(Long documentoId,Long archivoDigitalId) throws NSJPNegocioException;
		
}

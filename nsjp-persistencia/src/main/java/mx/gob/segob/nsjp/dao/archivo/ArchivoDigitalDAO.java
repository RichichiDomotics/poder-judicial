/**
 * Nombre del Programa : ArchivoDigitalDAO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 18 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Contrato para accesar a la entidad archivo digital
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
package mx.gob.segob.nsjp.dao.archivo;

import mx.gob.segob.nsjp.comun.enums.actividad.Actividades;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.ArchivoDigital;

/**
 * Contrato para accesar a la entidad archivo digital.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface ArchivoDigitalDAO extends GenericDao<ArchivoDigital, Long> {

	/**
	 * Lee el contenido de un archivo digital en base a su ID o al ID de su documento
	 * @param id ID de archivo o documento
	 * @param esArchivo Indica si el ID es de archivo
	 * @return
	 */
	byte []consultarContenidoPorDocumentoOArchivo(Long id,boolean esArchivo) throws NSJPNegocioException;

	/**
	 * Lee todo el Archivo Digital asociado a un documento
	 * @param idDocumento
	 * @param esArchivo
	 * @return
	 * @throws NSJPNegocioException
	 */
	ArchivoDigital consultarArchivoDigitalPorDocumento(Long idDocumento) throws NSJPNegocioException;


	ArchivoDigital consultarArchivoDitalSinContenidoPorActividad(Long idExpediente, Actividades actividad)throws NSJPNegocioException;

	/**
	 * Lee el contenido de un archivo digital en base a su ID de un Elemento
	 * Esto permite consultar la foto asociada a cualquier elemento
	 * @param id ID de archivo o documento
	 * @param esArchivo Indica si el ID es de archivo
	 * @return
	 */
	byte[] consultarArchivoDigitalXElementoId(Long idElemento) throws NSJPNegocioException;

}

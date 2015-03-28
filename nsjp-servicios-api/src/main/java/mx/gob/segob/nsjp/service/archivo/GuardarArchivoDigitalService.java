/**
 * Nombre del Programa : GuardarArchivoDigitalService.java
 * Autor                            : Emigdio Hernández
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 30/05/2011
 * Marca de cambio        : N/A
 * Descripcion General    : Define el contrato para el servicio de negocio utilizado para guardar
 * archivos digitales
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
package mx.gob.segob.nsjp.service.archivo;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;

/**
 * Define el contrato para el servicio de negocio utilizado para guardar
 * archivos digitales
 * @author Emigdio Hernández
 *
 */
public interface GuardarArchivoDigitalService {

	/**
	 * Almacena en base de datos un nuevo archivo digital y retorna el ID generado
	 * @param archivo Datos del archivo a guardar
	 * @return ID del archivo generado en base de datos
	 * 
	 */
	Long guardarArchivoDigital(ArchivoDigitalDTO archivo) throws NSJPNegocioException;
}

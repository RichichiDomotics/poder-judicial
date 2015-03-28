/**
* Nombre del Programa : consultarFormaPlantillaService.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 21/06/2011
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
package mx.gob.segob.nsjp.service.forma;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.forma.CamposFormaDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
public interface ConsultarFormaPlantillaService {

	/**
	 * Operación que realiza la funcionalidad de consultar las plantillas 
	 * registradas en el sistema de acuerdo a  un criterio de tipo de documento.
	 * 
	 * @param tipoDocumento
	 * @return Devuelve un listado de plantillas, en caso contrario, regresa null.
	 * @throws NSJPNegocioException
	 */
	List<FormaDTO> consultarFormaPlantilla(String tipoDocumento) throws NSJPNegocioException;
	/**
	 * Consulta el inventario de formas disponibles en el sistema filtradas
	 * por cierto tipo de forma, si el tipo de forma enviado es 0 entonces
	 * no filtra los elementos
	 * @param tipoFormaId el tipo de forma a filtrar
	 * @return Listado de formas
	 */
	List<FormaDTO> consultarPlantillaPorTipo(Long tipoForma) throws NSJPNegocioException;
	
	/**
	 * Consulta los campos que pueden ser usados para llenar un expediente
	 * @return Lista de campos
	 * @throws NSJPNegocioException
	 */
	List<CamposFormaDTO> consultarCamposForma() throws NSJPNegocioException;
}

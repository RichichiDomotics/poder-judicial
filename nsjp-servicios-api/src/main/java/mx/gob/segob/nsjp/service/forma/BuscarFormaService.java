/**
* Nombre del Programa : BuscarFormaService.java
* Autor                            : Emigdio Hernández
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 30/05/2011
* Marca de cambio        : N/A
* Descripcion General    : Definición del contrato de negocio del servicio para buscar Formas
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

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;

/**
 * Definición del contrato de negocio del servicio para buscar Formas
 * @author Emigdio Hernández
 *
 */
public interface BuscarFormaService {
	/**
	 * Consulta una Forma en base a su Identificador
	 * @param formaId Identificador de la forma
	 * @return Objeto con los datos de la Forma
	 */
	FormaDTO buscarForma(Long formaId) throws NSJPNegocioException ;

}

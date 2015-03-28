/**
* Nombre del Programa : ConsultarCamposFormaService.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 06/06/2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio de negocio para consultar los campos configurados
* de una Forma
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

import mx.gob.segob.nsjp.dto.forma.CamposFormaDTO;

/**
 * Contrato del servicio de negocio para consultar los campos configurados
 * de una Forma
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public interface ConsultarCamposFormaService {

	/**
	 * Consulta los campos que pueden configurarse en una forma
	 * @return Lista de campos forma que pueden utilizarse para llenar un formato
	 */
	List<CamposFormaDTO> consultarCamposForma();
	
}

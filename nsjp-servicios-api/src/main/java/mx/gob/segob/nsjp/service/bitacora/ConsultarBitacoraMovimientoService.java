/**
* Nombre del Programa : ConsultarBitacoraMovimientoService.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 31/08/2011
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
package mx.gob.segob.nsjp.service.bitacora;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.bitacora.BitacoraMovimientoDTO;

/**
 * Contrato de los servicios que permiten consultar la información de 
 * la Bitacora de Movimiento (IAM)
 * 
 * @version 1.0
 * @author GustavoBP
 */
public interface ConsultarBitacoraMovimientoService {

	/**
	 * Consulta los registros de Bitacora Movimiento por cualquiera o
	 * combinación de los siguientes filtros:
	 * numeroExpediente:
	 * idCategoriaElemento:  de acuerdo al catálogo CategoríaElemento
	 * 
	 * En caso de que no se le pasen parámetros, se regresan todos los 
	 * registros de la Bitacora de Movimientos 
	 * 
	 * @param numeroExpediente
	 * @param idCategoriaElemento  de acuerdo al catálogo CategoríaElemento
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<BitacoraMovimientoDTO> consultarBitacoraMovimientoPorNumeroExpedienteCategoria(
			String numeroExpediente, Long idCategoriaElemento) throws NSJPNegocioException;
	
}

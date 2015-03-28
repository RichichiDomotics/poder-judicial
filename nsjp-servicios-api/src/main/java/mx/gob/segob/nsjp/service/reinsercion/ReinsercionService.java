/**
* Nombre del Programa 		: ReinsercionService.java
* Autor 					: EdgarTE
* Compania 					: Ultrasist
* Proyecto 					: NSJP 							Fecha: 28 Mar 2012
* Marca de cambio 			: N/A
* Descripcion General 		: Describir el objetivo de la clase de manera breve
* Programa Dependiente 		: N/A
* Programa Subsecuente 		: N/A
* Cond. de ejecucion 		: N/A
* Dias de ejecucion 		: N/A 							Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor 					: N/A
* Compania 					: N/A
* Proyecto 					: N/A 							Fecha: N/A
* Modificacion 				: N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.service.reinsercion;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.detencion.PertenenciaDTO;
import mx.gob.segob.nsjp.dto.reinsercion.InventarioPertenenciaDTO;

/**
 * Interfaz que define los m&eacute;todos que se exponen para la utilizaci&oacute;n
 * del m&oacute;dulo de reinserci&oacute;n social.
 * @version 1.0
 * @author EdgarTE
 *
 */
public interface ReinsercionService {
	
	/**
	 * M&eacute;todo que lleva a cabo el registro de un nuevo inventario de 
	 * pertenencias dentro de la base de datos.
	 * @param inventarioPertenenciaDTO - El inventario de pertenencias a persistir.
	 * @return El inventarioPertenenciaDTO con su identificador 
	 */
	public InventarioPertenenciaDTO crearInventarioPertenencia (InventarioPertenenciaDTO inventarioPertenenciaDTO);

	/**
	 * M&eacute;todo que lleva a cabo la actualizaci&oacute;n de un inventario de
	 * pertenencias dentro de la base de datos.
	 * @param inventarioPertenenciaDTO - El inventario pertenencia a actualizar.
	 */
	public void actualizarInventarioPertenencias (InventarioPertenenciaDTO inventarioPertenenciaDTO);
	
	/**
	 * M&eacute;todo que lleva a cabo la consulta de todas las pertenencias 
	 * asociadas a un inventario.
	 * @param inventarioPertenenciaDTO - El inventario del cual se van a obtener 
	 * 									 las pertenencias.
	 * @return List<PertenenciaDTO> - Lista con las pertenencias asociadas al 
	 * 								  inventario.
	 */
	public List<PertenenciaDTO> consultarPertenenciasPorInventario(
			InventarioPertenenciaDTO inventarioPertenenciaDTO)
			throws NSJPNegocioException;
	
}

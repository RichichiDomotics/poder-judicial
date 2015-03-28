/**
* Nombre del Programa : ActualizarRelacionService.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 29/08/2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para actualizar el estatus EsActivo de la Relación
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
package mx.gob.segob.nsjp.service.relacion;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;

/**
 * Contrato del servicio para actualizar el estatus EsActivo de la Relación
 * 
 * @version 1.0
 * @author GustavoBP 
 */
public interface ActualizarRelacionService {

	/**
	 * Servicio que permite la actualizacion del estatus de EsActivo definido en la entidad
	 * Relacion. el objetivo es desactivar la relacion entre elementos. 
	 * 
	 * @param idRelaciones
	 * @throws NSJPNegocioException
	 */
	void actualizarEsActivoRelaciones(List<Long> idRelaciones ) throws NSJPNegocioException;
	
}

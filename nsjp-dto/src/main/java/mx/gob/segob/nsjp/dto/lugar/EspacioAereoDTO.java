/**
* Nombre del Programa : EspacioAereoDTO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 14 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Clase para la transferencia de datos entre la vista y el negocio, del objeto EspacioAereo
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
package mx.gob.segob.nsjp.dto.lugar;

import mx.gob.segob.nsjp.dto.domicilio.LugarDTO;

/**
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto EspacioAereo.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class EspacioAereoDTO extends LugarDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3407390605363694041L;

	private String lineaAerea;
	private String ruta;
	
	/**
	 * Método de acceso al campo lineaAerea.
	 * @return El valor del campo lineaAerea
	 */
	public String getLineaAerea() {
		return lineaAerea;
	}
	/**
	 * Asigna el valor al campo lineaAerea.
	 * @param lineaAerea el valor lineaAerea a asignar
	 */
	public void setLineaAerea(String lineaAerea) {
		this.lineaAerea = lineaAerea;
	}
	/**
	 * Método de acceso al campo ruta.
	 * @return El valor del campo ruta
	 */
	public String getRuta() {
		return ruta;
	}
	/**
	 * Asigna el valor al campo ruta.
	 * @param ruta el valor ruta a asignar
	 */
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	
}

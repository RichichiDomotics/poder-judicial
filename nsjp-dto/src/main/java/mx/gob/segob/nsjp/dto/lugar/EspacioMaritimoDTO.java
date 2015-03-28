/**
* Nombre del Programa : EspacioMaritimoDTO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 14 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Clase para la transferencia de datos entre la vista y el negocio, del objeto EspacioMaritimo
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
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto EspacioMaritimo.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class EspacioMaritimoDTO extends LugarDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -554553577888285481L;

	private String zonaCostera;
	private String puerto;
	private String zonaMaritima;
	
	/**
	 * Método de acceso al campo zonaCostera.
	 * @return El valor del campo zonaCostera
	 */
	public String getZonaCostera() {
		return zonaCostera;
	}
	/**
	 * Asigna el valor al campo zonaCostera.
	 * @param zonaCostera el valor zonaCostera a asignar
	 */
	public void setZonaCostera(String zonaCostera) {
		this.zonaCostera = zonaCostera;
	}
	/**
	 * Método de acceso al campo puerto.
	 * @return El valor del campo puerto
	 */
	public String getPuerto() {
		return puerto;
	}
	/**
	 * Asigna el valor al campo puerto.
	 * @param puerto el valor puerto a asignar
	 */
	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}
	/**
	 * Método de acceso al campo zonaMaritima.
	 * @return El valor del campo zonaMaritima
	 */
	public String getZonaMaritima() {
		return zonaMaritima;
	}
	/**
	 * Asigna el valor al campo zonaMaritima.
	 * @param zonaMaritima el valor zonaMaritima a asignar
	 */
	public void setZonaMaritima(String zonaMaritima) {
		this.zonaMaritima = zonaMaritima;
	}	
}

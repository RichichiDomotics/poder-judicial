/**
*
* Nombre del Programa : LugarWSDTO.java                                    
* Autor                            : GustavoBP                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 25/07/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : DTO de intercambio entre sistemas para transportar los datos básicos de Lugar.                      
* Programa Dependiente  :N/A                                                      
* Programa Subsecuente :N/A                                                      
* Cond. de ejecucion        :N/A                                                      
* Dias de ejecucion          :N/A                             Horario: N/A       
*                              MODIFICACIONES                                       
*------------------------------------------------------------------------------           
* Autor                       :N/A                                                           
* Compania               :N/A                                                           
* Proyecto                 :N/A                                   Fecha: N/A       
* Modificacion           :N/A                                                           
*------------------------------------------------------------------------------           
*/
package mx.gob.segob.nsjp.dto.domicilio;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.dto.elemento.ElementoWSDTO;
import mx.gob.segob.nsjp.dto.lugar.InmediacionWSDTO;

/**
 * DTO de intercambio entre sistemas para transportar los datos básicos de Lugar. 
 * @author GustavoBP
 * @version 1.0
 */
public class LugarWSDTO extends ElementoWSDTO {

	private String descripcion;
	private List<InmediacionWSDTO> inmediaciones = new ArrayList<InmediacionWSDTO>();
	private String latitud;
	private String longitud;
	private String latitudN;
	private String latitudGrados;
	private String latitudMinutos;
	private String latitudSegundos;
	private String longitudE;
	private String longitudGrados;
	private String longitudMinutos;
	private String longitudSegundos;
	
	/**
	 * @param descripcion
	 */
	public LugarWSDTO(String descripcion) {
		super();			
		this.descripcion = descripcion;
	}
	
	/**
	 * 
	 */
	public LugarWSDTO() {
		super();		
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Método de acceso al campo latitud.
	 * @return El valor del campo latitud
	 */
	public String getLatitud() {
		return latitud;
	}

	/**
	 * Asigna el valor al campo latitud.
	 * @param latitud el valor latitud a asignar
	 */
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	/**
	 * Método de acceso al campo longitud.
	 * @return El valor del campo longitud
	 */
	public String getLongitud() {
		return longitud;
	}

	/**
	 * Asigna el valor al campo longitud.
	 * @param longitud el valor longitud a asignar
	 */
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	/**
	 * Método de acceso al campo inmediaciones.
	 * @return El valor del campo inmediaciones
	 */
	public List<InmediacionWSDTO> getInmediaciones() {
		return inmediaciones;
	}

	/**
	 * Asigna el valor al campo inmediaciones.
	 * @param inmediaciones el valor inmediaciones a asignar
	 */
	public void setInmediaciones(List<InmediacionWSDTO> inmediaciones) {
		this.inmediaciones = inmediaciones;
	}

	/**
	 * Método de acceso al campo latitudN.
	 * @return El valor del campo latitudN
	 */
	public String getLatitudN() {
		return latitudN;
	}

	/**
	 * Asigna el valor al campo latitudN.
	 * @param latitudN el valor latitudN a asignar
	 */
	public void setLatitudN(String latitudN) {
		this.latitudN = latitudN;
	}

	/**
	 * Método de acceso al campo latitudGrados.
	 * @return El valor del campo latitudGrados
	 */
	public String getLatitudGrados() {
		return latitudGrados;
	}

	/**
	 * Asigna el valor al campo latitudGrados.
	 * @param latitudGrados el valor latitudGrados a asignar
	 */
	public void setLatitudGrados(String latitudGrados) {
		this.latitudGrados = latitudGrados;
	}

	/**
	 * Método de acceso al campo latitudMinutos.
	 * @return El valor del campo latitudMinutos
	 */
	public String getLatitudMinutos() {
		return latitudMinutos;
	}

	/**
	 * Asigna el valor al campo latitudMinutos.
	 * @param latitudMinutos el valor latitudMinutos a asignar
	 */
	public void setLatitudMinutos(String latitudMinutos) {
		this.latitudMinutos = latitudMinutos;
	}

	/**
	 * Método de acceso al campo latitudSegundos.
	 * @return El valor del campo latitudSegundos
	 */
	public String getLatitudSegundos() {
		return latitudSegundos;
	}

	/**
	 * Asigna el valor al campo latitudSegundos.
	 * @param latitudSegundos el valor latitudSegundos a asignar
	 */
	public void setLatitudSegundos(String latitudSegundos) {
		this.latitudSegundos = latitudSegundos;
	}

	/**
	 * Método de acceso al campo longitudE.
	 * @return El valor del campo longitudE
	 */
	public String getLongitudE() {
		return longitudE;
	}

	/**
	 * Asigna el valor al campo longitudE.
	 * @param longitudE el valor longitudE a asignar
	 */
	public void setLongitudE(String longitudE) {
		this.longitudE = longitudE;
	}

	/**
	 * Método de acceso al campo longitudGrados.
	 * @return El valor del campo longitudGrados
	 */
	public String getLongitudGrados() {
		return longitudGrados;
	}

	/**
	 * Asigna el valor al campo longitudGrados.
	 * @param longitudGrados el valor longitudGrados a asignar
	 */
	public void setLongitudGrados(String longitudGrados) {
		this.longitudGrados = longitudGrados;
	}

	/**
	 * Método de acceso al campo longitudMinutos.
	 * @return El valor del campo longitudMinutos
	 */
	public String getLongitudMinutos() {
		return longitudMinutos;
	}

	/**
	 * Asigna el valor al campo longitudMinutos.
	 * @param longitudMinutos el valor longitudMinutos a asignar
	 */
	public void setLongitudMinutos(String longitudMinutos) {
		this.longitudMinutos = longitudMinutos;
	}

	/**
	 * Método de acceso al campo longitudSegundos.
	 * @return El valor del campo longitudSegundos
	 */
	public String getLongitudSegundos() {
		return longitudSegundos;
	}

	/**
	 * Asigna el valor al campo longitudSegundos.
	 * @param longitudSegundos el valor longitudSegundos a asignar
	 */
	public void setLongitudSegundos(String longitudSegundos) {
		this.longitudSegundos = longitudSegundos;
	}
	
	
}

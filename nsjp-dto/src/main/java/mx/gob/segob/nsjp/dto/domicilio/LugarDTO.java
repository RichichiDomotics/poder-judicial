/**
*
* Nombre del Programa : LugarDTO.java                                    
* Autor                            : Cesar Agustin                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 30/03/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : DTO para la transferencia de parametros de Lugar entre la vista y servicios.                      
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

import mx.gob.segob.nsjp.dto.elemento.ElementoDTO;
import mx.gob.segob.nsjp.dto.lugar.InmediacionDTO;

/**
 * @author CesarAgustin
 * @version 1.0
 */
public class LugarDTO extends ElementoDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2183473369850230277L;
		
	private String descripcion;
	private List<InmediacionDTO> inmediaciones = new ArrayList<InmediacionDTO>();
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
	 * @param lugarId
	 * @param elemento
	 * @param descripcion
	 */
	public LugarDTO(String descripcion) {
		super();			
		this.descripcion = descripcion;
	}
	
	/**
	 * 
	 */
	public LugarDTO() {
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
	public List<InmediacionDTO> getInmediaciones() {
		return inmediaciones;
	}

	/**
	 * Asigna el valor al campo inmediaciones.
	 * @param inmediaciones el valor inmediaciones a asignar
	 */
	public void setInmediaciones(List<InmediacionDTO> inmediaciones) {
		this.inmediaciones = inmediaciones;
	}

	/**
	 * @return the latitudN
	 */
	public String getLatitudN() {
		return latitudN;
	}

	/**
	 * @param latitudN the latitudN to set
	 */
	public void setLatitudN(String latitudN) {
		this.latitudN = latitudN;
	}

	/**
	 * @return the latitudGrados
	 */
	public String getLatitudGrados() {
		return latitudGrados;
	}

	/**
	 * @param latitudGrados the latitudGrados to set
	 */
	public void setLatitudGrados(String latitudGrados) {
		this.latitudGrados = latitudGrados;
	}

	/**
	 * @return the latitudMinutos
	 */
	public String getLatitudMinutos() {
		return latitudMinutos;
	}

	/**
	 * @param latitudMinutos the latitudMinutos to set
	 */
	public void setLatitudMinutos(String latitudMinutos) {
		this.latitudMinutos = latitudMinutos;
	}

	/**
	 * @return the latitudSegundos
	 */
	public String getLatitudSegundos() {
		return latitudSegundos;
	}

	/**
	 * @param latitudSegundos the latitudSegundos to set
	 */
	public void setLatitudSegundos(String latitudSegundos) {
		this.latitudSegundos = latitudSegundos;
	}

	/**
	 * @return the longitudE
	 */
	public String getLongitudE() {
		return longitudE;
	}

	/**
	 * @param longitudE the longitudE to set
	 */
	public void setLongitudE(String longitudE) {
		this.longitudE = longitudE;
	}

	/**
	 * @return the longitudGrados
	 */
	public String getLongitudGrados() {
		return longitudGrados;
	}

	/**
	 * @param longitudGrados the longitudGrados to set
	 */
	public void setLongitudGrados(String longitudGrados) {
		this.longitudGrados = longitudGrados;
	}

	/**
	 * @return the longitudMinutos
	 */
	public String getLongitudMinutos() {
		return longitudMinutos;
	}

	/**
	 * @param longitudMinutos the longitudMinutos to set
	 */
	public void setLongitudMinutos(String longitudMinutos) {
		this.longitudMinutos = longitudMinutos;
	}

	/**
	 * @return the longitudSegundos
	 */
	public String getLongitudSegundos() {
		return longitudSegundos;
	}

	/**
	 * @param longitudSegundos the longitudSegundos to set
	 */
	public void setLongitudSegundos(String longitudSegundos) {
		this.longitudSegundos = longitudSegundos;
	}
	
	
	
}

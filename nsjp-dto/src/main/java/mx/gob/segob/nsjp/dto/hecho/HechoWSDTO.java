/**
* Nombre del Programa : HechoWSDTO.java
 * Autor                            : GustavoBP                                               
 * Compania                    : Ultrasist                                                
 * Proyecto                      : NSJP                    Fecha: 13/09/2011 
 * Marca de cambio        : N/A                                                     
 * Descripcion General    : DTO de intercambio entre sistemas para transportar los datos básicos de un Hecho.  
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
package mx.gob.segob.nsjp.dto.hecho;

import java.util.Date;

import mx.gob.segob.nsjp.dto.base.GenericWSDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioWSDTO;
import mx.gob.segob.nsjp.dto.domicilio.LugarWSDTO;

/**
 * DTO de intercambio entre sistemas para transportar los datos básicos de un Hecho.
 * 
 * @author GustavoBP
 * @version 1.0
 */
public class HechoWSDTO extends GenericWSDTO {

    private TiempoWSDTO tiempo;
    private LugarWSDTO lugar;
    private String descNarrativa;
    private DomicilioWSDTO domicilio;
    private Date fechaDeArribo; 
    
	public HechoWSDTO() {
		
	}

	/**
	 * Método de acceso al campo tiempo.
	 * @return El valor del campo tiempo
	 */
	public TiempoWSDTO getTiempo() {
		return tiempo;
	}
	/**
	 * Asigna el valor al campo tiempo.
	 * @param tiempo el valor tiempo a asignar
	 */
	public void setTiempo(TiempoWSDTO tiempo) {
		this.tiempo = tiempo;
	}

	/**
	 * Método de acceso al campo lugar.
	 * @return El valor del campo lugar
	 */
	public LugarWSDTO getLugar() {
		return lugar;
	}
	/**
	 * Asigna el valor al campo lugar.
	 * @param lugar el valor lugar a asignar
	 */
	public void setLugar(LugarWSDTO lugar) {
		this.lugar = lugar;
	}
	/**
	 * Método de acceso al campo descNarrativa.
	 * @return El valor del campo descNarrativa
	 */
	public String getDescNarrativa() {
		return descNarrativa;
	}
	/**
	 * Asigna el valor al campo descNarrativa.
	 * @param descNarrativa el valor descNarrativa a asignar
	 */
	public void setDescNarrativa(String descNarrativa) {
		this.descNarrativa = descNarrativa;
	}

	/**
	 * @param domicilio the domicilio to set
	 */
	public void setDomicilio(DomicilioWSDTO domicilio) {
		this.domicilio = domicilio;
	}

	/**
	 * @return the domicilio
	 */
	public DomicilioWSDTO getDomicilio() {
		return domicilio;
	}

	public Date getFechaDeArribo() {
		return fechaDeArribo;
	}

	public void setFechaDeArribo(Date fechaDeArribo) {
		this.fechaDeArribo = fechaDeArribo;
	}	
	
}

/**
* Nombre del Programa : EquipoComputoDTO.java                                    
* Autor                            : Tattva-IT                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 3 May 2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Clase para la transferencia de datos entre la vista y el negocio, del objeto equipo de computo.                  
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
package mx.gob.segob.nsjp.dto.objeto;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;


/**
 *Clase para la transferencia de datos entre la vista y el negocio, del objeto equipo de computo.
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class EquipoComputoDTO extends ObjetoDTO {
	
	private String noSerie;
    private ValorDTO color;
    private ValorDTO Marca;
    private ValorDTO tipoEquipo;
    private String modelo;
	/**
	 * M�todo de acceso al campo noSerie.
	 * @return El valor del campo noSerie
	 */
	public String getNoSerie() {
		return noSerie;
	}
	/**
	 * Asigna el valor al campo noSerie.
	 * @param noSerie el valor noSerie a asignar
	 */
	public void setNoSerie(String noSerie) {
		this.noSerie = noSerie;
	}
	/**
	 * M�todo de acceso al campo color.
	 * @return El valor del campo color
	 */
	public ValorDTO getColor() {
		return color;
	}
	/**
	 * Asigna el valor al campo color.
	 * @param color el valor color a asignar
	 */
	public void setColor(ValorDTO color) {
		this.color = color;
	}
	/**
	 * M�todo de acceso al campo marca.
	 * @return El valor del campo marca
	 */
	public ValorDTO getMarca() {
		return Marca;
	}
	/**
	 * Asigna el valor al campo marca.
	 * @param marca el valor marca a asignar
	 */
	public void setMarca(ValorDTO marca) {
		Marca = marca;
	}
	/**
	 * M�todo de acceso al campo tipoEquipo.
	 * @return El valor del campo tipoEquipo
	 */
	public ValorDTO getTipoEquipo() {
		return tipoEquipo;
	}
	/**
	 * Asigna el valor al campo tipoEquipo.
	 * @param tipoEquipo el valor tipoEquipo a asignar
	 */
	public void setTipoEquipo(ValorDTO tipoEquipo) {
		this.tipoEquipo = tipoEquipo;
	}
	/**
	 * M�todo de acceso al campo modelo.
	 * @return El valor del campo modelo
	 */
	public String getModelo() {
		return modelo;
	}
	/**
	 * Asigna el valor al campo modelo.
	 * @param modelo el valor modelo a asignar
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

}

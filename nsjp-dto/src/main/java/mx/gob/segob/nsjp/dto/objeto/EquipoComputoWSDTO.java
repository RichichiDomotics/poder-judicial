/**
* Nombre del Programa : EquipoComputoWSDTO.java                                    
* Autor                            : GustavoBP                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 24/08/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : DTO de intercambio entre sistemas para transportar los datos básicos de un EquipoComputo.                  
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

/**
 * DTO de intercambio entre sistemas para transportar los datos básicos de un EquipoComputo.
 * @author GustavoBP
 * @version 1.0
 */
public class EquipoComputoWSDTO extends ObjetoWSDTO {
	
	private String noSerie;
    private Long color;
    private Long Marca;
    private Long tipoEquipo;
    private String modelo;
	/**
	 * Mï¿½todo de acceso al campo noSerie.
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
	 * Mï¿½todo de acceso al campo color.
	 * @return El valor del campo color
	 */
	public Long getColor() {
		return color;
	}
	/**
	 * Asigna el valor al campo color.
	 * @param color el valor color a asignar
	 */
	public void setColor(Long color) {
		this.color = color;
	}
	/**
	 * Mï¿½todo de acceso al campo marca.
	 * @return El valor del campo marca
	 */
	public Long getMarca() {
		return Marca;
	}
	/**
	 * Asigna el valor al campo marca.
	 * @param marca el valor marca a asignar
	 */
	public void setMarca(Long marca) {
		Marca = marca;
	}
	/**
	 * Mï¿½todo de acceso al campo tipoEquipo.
	 * @return El valor del campo tipoEquipo
	 */
	public Long getTipoEquipo() {
		return tipoEquipo;
	}
	/**
	 * Asigna el valor al campo tipoEquipo.
	 * @param tipoEquipo el valor tipoEquipo a asignar
	 */
	public void setTipoEquipo(Long tipoEquipo) {
		this.tipoEquipo = tipoEquipo;
	}
	/**
	 * Mï¿½todo de acceso al campo modelo.
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

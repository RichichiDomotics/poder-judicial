/**
* Nombre del Programa : EmbarcacionWSDTO.java                                    
* Autor                            : GustavoBP                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 24/08/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : DTO de intercambio entre sistemas para transportar los datos básicos de un Embarcacion.                       
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
 * DTO de intercambio entre sistemas para transportar los datos básicos de un Embarcacion.
 * @author GustavoBP
 * @version 1.0
 */
public class EmbarcacionWSDTO extends ObjetoWSDTO {
    private String matricula;
    private String noSerie;
    private String noMotor;
    private String nombreEmbarcacion;
    private Long paisOrigen;
    private Long color;
    private Long marca;
    private Long tipoEmbarcacion;
    private Long subMarca;
	/**
	 * Mï¿½todo de acceso al campo matricula.
	 * @return El valor del campo matricula
	 */
	public String getMatricula() {
		return matricula;
	}
	/**
	 * Asigna el valor al campo matricula.
	 * @param matricula el valor matricula a asignar
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
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
	 * Mï¿½todo de acceso al campo noMotor.
	 * @return El valor del campo noMotor
	 */
	public String getNoMotor() {
		return noMotor;
	}
	/**
	 * Asigna el valor al campo noMotor.
	 * @param noMotor el valor noMotor a asignar
	 */
	public void setNoMotor(String noMotor) {
		this.noMotor = noMotor;
	}
	/**
	 * Mï¿½todo de acceso al campo nombreEmbarcacion.
	 * @return El valor del campo nombreEmbarcacion
	 */
	public String getNombreEmbarcacion() {
		return nombreEmbarcacion;
	}
	/**
	 * Asigna el valor al campo nombreEmbarcacion.
	 * @param nombreEmbarcacion el valor nombreEmbarcacion a asignar
	 */
	public void setNombreEmbarcacion(String nombreEmbarcacion) {
		this.nombreEmbarcacion = nombreEmbarcacion;
	}
	/**
	 * Mï¿½todo de acceso al campo paisOrigen.
	 * @return El valor del campo paisOrigen
	 */
	public Long getPaisOrigen() {
		return paisOrigen;
	}
	/**
	 * Asigna el valor al campo paisOrigen.
	 * @param paisOrigen el valor paisOrigen a asignar
	 */
	public void setPaisOrigen(Long paisOrigen) {
		this.paisOrigen = paisOrigen;
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
		return marca;
	}
	/**
	 * Asigna el valor al campo marca.
	 * @param marca el valor marca a asignar
	 */
	public void setMarca(Long marca) {
		this.marca = marca;
	}
	/**
	 * Mï¿½todo de acceso al campo tipoEmbarcacion.
	 * @return El valor del campo tipoEmbarcacion
	 */
	public Long getTipoEmbarcacion() {
		return tipoEmbarcacion;
	}
	/**
	 * Asigna el valor al campo tipoEmbarcacion.
	 * @param tipoEmbarcacion el valor tipoEmbarcacion a asignar
	 */
	public void setTipoEmbarcacion(Long tipoEmbarcacion) {
		this.tipoEmbarcacion = tipoEmbarcacion;
	}
	/**
	 * Mï¿½todo de acceso al campo subMarca.
	 * @return El valor del campo subMarca
	 */
	public Long getSubMarca() {
		return subMarca;
	}
	/**
	 * Asigna el valor al campo subMarca.
	 * @param subMarca el valor subMarca a asignar
	 */
	public void setSubMarca(Long subMarca) {
		this.subMarca = subMarca;
	}


}

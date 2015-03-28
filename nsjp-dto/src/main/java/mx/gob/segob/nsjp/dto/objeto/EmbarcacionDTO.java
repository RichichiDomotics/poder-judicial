/**
* Nombre del Programa : EmbarcaderoDTO.java                                    
* Autor                            : Tattva-IT                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 5 May 2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Clase para la transferencia de datos entre la vista y el negocio, del objeto embarcadero                      
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
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto embarcadero
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class EmbarcacionDTO extends ObjetoDTO {
    private String matricula;
    private String noSerie;
    private String noMotor;
    private String nombreEmbarcacion;
    private ValorDTO paisOrigen;
    private ValorDTO color;
    private ValorDTO marca;
    private ValorDTO tipoEmbarcacion;
    private ValorDTO subMarca;
	/**
	 * M�todo de acceso al campo matricula.
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
	 * M�todo de acceso al campo noMotor.
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
	 * M�todo de acceso al campo nombreEmbarcacion.
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
	 * M�todo de acceso al campo paisOrigen.
	 * @return El valor del campo paisOrigen
	 */
	public ValorDTO getPaisOrigen() {
		return paisOrigen;
	}
	/**
	 * Asigna el valor al campo paisOrigen.
	 * @param paisOrigen el valor paisOrigen a asignar
	 */
	public void setPaisOrigen(ValorDTO paisOrigen) {
		this.paisOrigen = paisOrigen;
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
		return marca;
	}
	/**
	 * Asigna el valor al campo marca.
	 * @param marca el valor marca a asignar
	 */
	public void setMarca(ValorDTO marca) {
		this.marca = marca;
	}
	/**
	 * M�todo de acceso al campo tipoEmbarcacion.
	 * @return El valor del campo tipoEmbarcacion
	 */
	public ValorDTO getTipoEmbarcacion() {
		return tipoEmbarcacion;
	}
	/**
	 * Asigna el valor al campo tipoEmbarcacion.
	 * @param tipoEmbarcacion el valor tipoEmbarcacion a asignar
	 */
	public void setTipoEmbarcacion(ValorDTO tipoEmbarcacion) {
		this.tipoEmbarcacion = tipoEmbarcacion;
	}
	/**
	 * M�todo de acceso al campo subMarca.
	 * @return El valor del campo subMarca
	 */
	public ValorDTO getSubMarca() {
		return subMarca;
	}
	/**
	 * Asigna el valor al campo subMarca.
	 * @param subMarca el valor subMarca a asignar
	 */
	public void setSubMarca(ValorDTO subMarca) {
		this.subMarca = subMarca;
	}


}

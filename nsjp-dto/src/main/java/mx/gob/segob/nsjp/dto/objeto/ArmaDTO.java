/**
* Nombre del Programa : ArmaDTO.java                                    
* Autor                            : Tattva-IT                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 4 May 2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Clase para la transferencia de datos entre la vista y el negocio, del objeto arma                      
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
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto arma
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class ArmaDTO extends ObjetoDTO{
	
    private String matricula;
    private String calibre;
    private ValorDTO marca;
    private ValorDTO tipoArma;
    private String modelo;
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
	 * M�todo de acceso al campo calibre.
	 * @return El valor del campo calibre
	 */
	public String getCalibre() {
		return calibre;
	}
	/**
	 * Asigna el valor al campo calibre.
	 * @param calibre el valor calibre a asignar
	 */
	public void setCalibre(String calibre) {
		this.calibre = calibre;
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
	 * M�todo de acceso al campo tipoArma.
	 * @return El valor del campo tipoArma
	 */
	public ValorDTO getTipoArma() {
		return tipoArma;
	}
	/**
	 * Asigna el valor al campo tipoArma.
	 * @param tipoArma el valor tipoArma a asignar
	 */
	public void setTipoArma(ValorDTO tipoArma) {
		this.tipoArma = tipoArma;
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

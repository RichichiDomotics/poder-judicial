/**
* Nombre del Programa : AeronaveWSDTO.java                                    
* Autor                            : GustavoBP                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 24/08/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : DTO de intercambio entre sistemas para transportar los datos básicos de un Aeronave.                     
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
 * DTO de intercambio entre sistemas para transportar los datos básicos de un Aeronave.
 * @author GustavoBP
 * @version 1.0
 */
public class AeronaveWSDTO extends ObjetoWSDTO {
    private Long paisProcedencia;
    private String matricula;
    private String noSerie;
    private String noMotor;
    private String modelo;
    private Long marca;
    private Long subMarca;
    private Long tipoAeroNave;
    private Long color;
    
	public Long getColor() {
		return color;
	}
	public void setColor(Long color) {
		this.color = color;
	}
	/**
	 * Mï¿½todo de acceso al campo paisProcedencia.
	 * @return El valor del campo paisProcedencia
	 */
	public Long getPaisProcedencia() {
		return paisProcedencia;
	}
	/**
	 * Asigna el valor al campo paisProcedencia.
	 * @param paisProcedencia el valor paisProcedencia a asignar
	 */
	public void setPaisProcedencia(Long paisProcedencia) {
		this.paisProcedencia = paisProcedencia;
	}
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
	/**
	 * Mï¿½todo de acceso al campo tipoAeroNave.
	 * @return El valor del campo tipoAeroNave
	 */
	public Long getTipoAeroNave() {
		return tipoAeroNave;
	}
	/**
	 * Asigna el valor al campo tipoAeroNave.
	 * @param tipoAeroNave el valor tipoAeroNave a asignar
	 */
	public void setTipoAeroNave(Long tipoAeroNave) {
		this.tipoAeroNave = tipoAeroNave;
	}

}

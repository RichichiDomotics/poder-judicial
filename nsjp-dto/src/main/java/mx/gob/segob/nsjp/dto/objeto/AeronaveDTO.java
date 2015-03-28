/**
* Nombre del Programa : AeronaveDTO.java                                    
* Autor                            : Tattva-IT                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 5 May 2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Clase para la transferencia de datos entre la vista y el negocio, del objeto aeronave.                      
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
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto aeronave.
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class AeronaveDTO extends ObjetoDTO {
    private ValorDTO paisProcedencia;
    private String matricula;
    private String noSerie;
    private String noMotor;
    private String modelo;
    private ValorDTO marca;
    private ValorDTO subMarca;
    private ValorDTO tipoAeroNave;
    private ValorDTO color;
    
	public ValorDTO getColor() {
		return color;
	}
	public void setColor(ValorDTO color) {
		this.color = color;
	}
	/**
	 * M�todo de acceso al campo paisProcedencia.
	 * @return El valor del campo paisProcedencia
	 */
	public ValorDTO getPaisProcedencia() {
		return paisProcedencia;
	}
	/**
	 * Asigna el valor al campo paisProcedencia.
	 * @param paisProcedencia el valor paisProcedencia a asignar
	 */
	public void setPaisProcedencia(ValorDTO paisProcedencia) {
		this.paisProcedencia = paisProcedencia;
	}
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
	/**
	 * M�todo de acceso al campo tipoAeroNave.
	 * @return El valor del campo tipoAeroNave
	 */
	public ValorDTO getTipoAeroNave() {
		return tipoAeroNave;
	}
	/**
	 * Asigna el valor al campo tipoAeroNave.
	 * @param tipoAeroNave el valor tipoAeroNave a asignar
	 */
	public void setTipoAeroNave(ValorDTO tipoAeroNave) {
		this.tipoAeroNave = tipoAeroNave;
	}

}

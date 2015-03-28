/**
* Nombre del Programa : VehiculoDTO.java                                    
* Autor                            : Tattva-IT                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 2 May 2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Clase para la transferencia de datos entre la vista y el negocio, del objeto Vehiculo                      
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

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;


/**
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto Vehiculo
 * @version 1.0
 * @author Tattva-IT
 *
 */

public class VehiculoDTO extends ObjetoDTO{
	private ValorDTO valorByPaisOrigenVal;
	private ValorDTO valorByColorVal;
	private ValorDTO valorByMarcaVal;
	private ValorDTO valorBySubmarcaVal;
	private ValorDTO valorByTipoVehiculo;
	private Short modelo;
	private String placa;
	private String noSerie;
	private String noMotor;
	private String nrfv;
	private Boolean esBlindado;
	private Short noPuertas;
	private Short noCilindros;
	private String propietario;
	private ValorDTO relacionHechoVal;

	

	/**
	 * @param valorByPaisOrigenVal
	 * @param valorByColorVal
	 * @param valorByMarcaVal
	 * @param valorBySubmarcaVal
	 * @param valorByTipoVehiculo
	 * @param modelo
	 * @param placa
	 * @param noSerie
	 * @param noMotor
	 * @param nrfv
	 * @param esBlindado
	 * @param noPuertas
	 * @param noCilindros
	 */
	public VehiculoDTO(ValorDTO valorByPaisOrigenVal,
			ValorDTO valorByColorVal,
			ValorDTO  valorByMarcaVal,
			ValorDTO valorBySubmarcaVal,
			ValorDTO valorByTipoVehiculo,
			Short modelo, String placa, String noSerie, String noMotor,
			String nrfv, Boolean esBlindado, Short noPuertas, Short noCilindros) {
		super();
		this.valorByPaisOrigenVal = valorByPaisOrigenVal;
		this.valorByColorVal = valorByColorVal;
		this.valorByMarcaVal = valorByMarcaVal;
		this.valorBySubmarcaVal = valorBySubmarcaVal;
		this.valorByTipoVehiculo = valorByTipoVehiculo;
		this.modelo = modelo;
		this.placa = placa;
		this.noSerie = noSerie;
		this.noMotor = noMotor;
		this.nrfv = nrfv;
		this.esBlindado = esBlindado;
		this.noPuertas = noPuertas;
		this.noCilindros = noCilindros;
		
	}
	
	public VehiculoDTO() {
        super();
    }
    /**
	 * Mï¿½todo de acceso al campo valorByPaisOrigenVal.
	 * @return El valor del campo valorByPaisOrigenVal
	 */
	public ValorDTO getValorByPaisOrigenVal() {
		return valorByPaisOrigenVal;
	}
	/**
	 * Asigna el valor al campo valorByPaisOrigenVal.
	 * @param valorByPaisOrigenVal el valor valorByPaisOrigenVal a asignar
	 */
	public void setValorByPaisOrigenVal(ValorDTO valorByPaisOrigenVal) {
		this.valorByPaisOrigenVal = valorByPaisOrigenVal;
	}
	/**
	 * Mï¿½todo de acceso al campo valorByColorVal.
	 * @return El valor del campo valorByColorVal
	 */
	public ValorDTO getValorByColorVal() {
		return valorByColorVal;
	}
	/**
	 * Asigna el valor al campo valorByColorVal.
	 * @param valorByColorVal el valor valorByColorVal a asignar
	 */
	public void setValorByColorVal(ValorDTO valorByColorVal) {
		this.valorByColorVal = valorByColorVal;
	}
	/**
	 * Mï¿½todo de acceso al campo valorByMarcaVal.
	 * @return El valor del campo valorByMarcaVal
	 */
	public ValorDTO getValorByMarcaVal() {
		return valorByMarcaVal;
	}
	/**
	 * Asigna el valor al campo valorByMarcaVal.
	 * @param valorByMarcaVal el valor valorByMarcaVal a asignar
	 */
	public void setValorByMarcaVal(ValorDTO valorByMarcaVal) {
		this.valorByMarcaVal = valorByMarcaVal;
	}
	/**
	 * Mï¿½todo de acceso al campo valorBySubmarcaVal.
	 * @return El valor del campo valorBySubmarcaVal
	 */
	public ValorDTO getValorBySubmarcaVal() {
		return valorBySubmarcaVal;
	}
	/**
	 * Asigna el valor al campo valorBySubmarcaVal.
	 * @param valorBySubmarcaVal el valor valorBySubmarcaVal a asignar
	 */
	public void setValorBySubmarcaVal(ValorDTO valorBySubmarcaVal) {
		this.valorBySubmarcaVal = valorBySubmarcaVal;
	}
	/**
	 * Mï¿½todo de acceso al campo valorByTipoVehiculo.
	 * @return El valor del campo valorByTipoVehiculo
	 */
	public ValorDTO getValorByTipoVehiculo() {
		return valorByTipoVehiculo;
	}
	/**
	 * Asigna el valor al campo valorByTipoVehiculo.
	 * @param valorByTipoVehiculo el valor valorByTipoVehiculo a asignar
	 */
	public void setValorByTipoVehiculo(ValorDTO valorByTipoVehiculo) {
		this.valorByTipoVehiculo = valorByTipoVehiculo;
	}
	/**
	 * Mï¿½todo de acceso al campo modelo.
	 * @return El valor del campo modelo
	 */
	public Short getModelo() {
		return modelo;
	}
	/**
	 * Asigna el valor al campo modelo.
	 * @param modelo el valor modelo a asignar
	 */
	public void setModelo(Short modelo) {
		this.modelo = modelo;
	}
	/**
	 * Mï¿½todo de acceso al campo placa.
	 * @return El valor del campo placa
	 */
	public String getPlaca() {
		return placa;
	}
	/**
	 * Asigna el valor al campo placa.
	 * @param placa el valor placa a asignar
	 */
	public void setPlaca(String placa) {
		this.placa = placa;
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
	 * Mï¿½todo de acceso al campo nrfv.
	 * @return El valor del campo nrfv
	 */
	public String getNrfv() {
		return nrfv;
	}
	/**
	 * Asigna el valor al campo nrfv.
	 * @param nrfv el valor nrfv a asignar
	 */
	public void setNrfv(String nrfv) {
		this.nrfv = nrfv;
	}
	/**
	 * Mï¿½todo de acceso al campo esBlindado.
	 * @return El valor del campo esBlindado
	 */
	public Boolean getEsBlindado() {
		return esBlindado;
	}
	/**
	 * Asigna el valor al campo esBlindado.
	 * @param esBlindado el valor esBlindado a asignar
	 */
	public void setEsBlindado(Boolean esBlindado) {
		this.esBlindado = esBlindado;
	}
	/**
	 * Mï¿½todo de acceso al campo noPuertas.
	 * @return El valor del campo noPuertas
	 */
	public Short getNoPuertas() {
		return noPuertas;
	}
	/**
	 * Asigna el valor al campo noPuertas.
	 * @param noPuertas el valor noPuertas a asignar
	 */
	public void setNoPuertas(Short noPuertas) {
		this.noPuertas = noPuertas;
	}
	/**
	 * Mï¿½todo de acceso al campo noCilindros.
	 * @return El valor del campo noCilindros
	 */
	public Short getNoCilindros() {
		return noCilindros;
	}
	/**
	 * Asigna el valor al campo noCilindros.
	 * @param noCilindros el valor noCilindros a asignar
	 */
	public void setNoCilindros(Short noCilindros) {
		this.noCilindros = noCilindros;
	}

	/**
	 * Método de acceso al campo propietario.
	 * @return El valor del campo propietario
	 */
	public String getPropietario() {
		return propietario;
	}

	/**
	 * Asigna el valor al campo propietario.
	 * @param propietario el valor propietario a asignar
	 */
	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}

	/**
	 * Método de acceso al campo relacionHechoVal.
	 * @return El valor del campo relacionHechoVal
	 */
	public ValorDTO getRelacionHechoVal() {
		return relacionHechoVal;
	}

	/**
	 * Asigna el valor al campo relacionHechoVal.
	 * @param relacionHechoVal el valor relacionHechoVal a asignar
	 */
	public void setRelacionHechoVal(ValorDTO relacionHechoVal) {
		this.relacionHechoVal = relacionHechoVal;
	}



}

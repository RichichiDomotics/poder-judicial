/**
* Nombre del Programa : RelacionDatoMedioPruebaDTO.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 30/09/2011
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
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
package mx.gob.segob.nsjp.dto.prueba;

import java.util.Date;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;

/**
 * DTO para la transferencia de parámetros de Relacion de Dato Prueba con Medio Prueba, entre la Vista y Servicio.
 * @version 1.0
 * @author GustavoBP
 *
 */
public class RelacionDatoMedioPruebaDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4699214704863929238L;
	private Long relacionDatoMedioPruebaId;
	private Boolean esAceptado;
	private Date tiempoCancelacion;
	
	//Relaciones
	private ValorDTO motivoCancelacion;
	private DatoPruebaDTO datoPrueba;
	private MedioPruebaDTO medioPrueba;
	
	
	/**
	 * 
	 */
	public RelacionDatoMedioPruebaDTO() {
	}


	public RelacionDatoMedioPruebaDTO(Long relacionDatoMedioPruebaId,
			Boolean esAceptado, Date tiempoCancelacion,
			ValorDTO motivoCancelacion, DatoPruebaDTO datoPrueba,
			MedioPruebaDTO medioPrueba) {
		this.relacionDatoMedioPruebaId = relacionDatoMedioPruebaId;
		this.esAceptado = esAceptado;
		this.tiempoCancelacion = tiempoCancelacion;
		this.motivoCancelacion = motivoCancelacion;
		this.datoPrueba = datoPrueba;
		this.medioPrueba = medioPrueba;
	}


	/**
	 * Método de acceso al campo relacionDatoMedioPruebaId.
	 * @return El valor del campo relacionDatoMedioPruebaId
	 */
	public Long getRelacionDatoMedioPruebaId() {
		return relacionDatoMedioPruebaId;
	}


	/**
	 * Asigna el valor al campo relacionDatoMedioPruebaId.
	 * @param relacionDatoMedioPruebaId el valor relacionDatoMedioPruebaId a asignar
	 */
	public void setRelacionDatoMedioPruebaId(Long relacionDatoMedioPruebaId) {
		this.relacionDatoMedioPruebaId = relacionDatoMedioPruebaId;
	}


	/**
	 * Método de acceso al campo esAceptado.
	 * @return El valor del campo esAceptado
	 */
	public Boolean getEsAceptado() {
		return esAceptado;
	}


	/**
	 * Asigna el valor al campo esAceptado.
	 * @param esAceptado el valor esAceptado a asignar
	 */
	public void setEsAceptado(Boolean esAceptado) {
		this.esAceptado = esAceptado;
	}


	/**
	 * Método de acceso al campo tiempoCancelacion.
	 * @return El valor del campo tiempoCancelacion
	 */
	public Date getTiempoCancelacion() {
		return tiempoCancelacion;
	}


	/**
	 * Asigna el valor al campo tiempoCancelacion.
	 * @param tiempoCancelacion el valor tiempoCancelacion a asignar
	 */
	public void setTiempoCancelacion(Date tiempoCancelacion) {
		this.tiempoCancelacion = tiempoCancelacion;
	}


	/**
	 * Método de acceso al campo motivoCancelacion.
	 * @return El valor del campo motivoCancelacion
	 */
	public ValorDTO getMotivoCancelacion() {
		return motivoCancelacion;
	}


	/**
	 * Asigna el valor al campo motivoCancelacion.
	 * @param motivoCancelacion el valor motivoCancelacion a asignar
	 */
	public void setMotivoCancelacion(ValorDTO motivoCancelacion) {
		this.motivoCancelacion = motivoCancelacion;
	}


	/**
	 * Método de acceso al campo datoPrueba.
	 * @return El valor del campo datoPrueba
	 */
	public DatoPruebaDTO getDatoPrueba() {
		return datoPrueba;
	}


	/**
	 * Asigna el valor al campo datoPrueba.
	 * @param datoPrueba el valor datoPrueba a asignar
	 */
	public void setDatoPrueba(DatoPruebaDTO datoPrueba) {
		this.datoPrueba = datoPrueba;
	}


	/**
	 * Método de acceso al campo medioPrueba.
	 * @return El valor del campo medioPrueba
	 */
	public MedioPruebaDTO getMedioPrueba() {
		return medioPrueba;
	}


	/**
	 * Asigna el valor al campo medioPrueba.
	 * @param medioPrueba el valor medioPrueba a asignar
	 */
	public void setMedioPrueba(MedioPruebaDTO medioPrueba) {
		this.medioPrueba = medioPrueba;
	}
	
}

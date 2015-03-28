/**
* Nombre del Programa : IngresarDatosGeneralesForm.java
* Autor               : Arturo Leon
* Compania            : Ultrasist
* Proyecto            : NSJP                    Fecha: 01/04/2011
* Marca de cambio     : N/A
* Descripcion General : Clase Form para capturar los datos de entranda en la vista de Ingresar datos generales
* Programa Dependiente: N/A
* Programa Subsecuente: N/A
* Cond. de ejecucion  : N/A
* Dias de ejecucion   : N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor               :N/A
* Compania            :N/A
* Proyecto            :N/A                                   Fecha: N/A
* Modificacion        :N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.web.caso.form;

import mx.gob.segob.nsjp.web.base.form.GenericForm;
/**
* Clase Form para capturar los datos de entranda en la vista de Ingresar datos generales.
*
* @version 1.0
* @author Arturo Leon
*/
public class IngresarDatosGeneralesForm  extends GenericForm {

	
	private static final long serialVersionUID = 1L;
	
	private String nacionalidad;
	private String idNacionalidad;
	private String escolaridad;
	private String idEscolaridad;
	private String idioma;
	private String idIdioma;
	private String estadocivil;
	private String idEstadocivil;
	private String religion;
	private String ocupacion;
	private String serviciopericial;
	private String especialidadpericial;
	
	/**
	 * Método de acceso al campo serviciopericial.
	 * @return El valor del campo serviciopericial
	 */
	public String getServiciopericial() {
		return serviciopericial;
	}
	/**
	 * Asigna el valor al campo serviciopericial.
	 * @param serviciopericial el valor serviciopericial a asignar
	 */
	public void setServiciopericial(String serviciopericial) {
		this.serviciopericial = serviciopericial;
	}
	/**
	 * Método de acceso al campo especialidadpericial.
	 * @return El valor del campo especialidadpericial
	 */
	public String getEspecialidadpericial() {
		return especialidadpericial;
	}
	/**
	 * Asigna el valor al campo especialidadpericial.
	 * @param especialidadpericial el valor especialidadpericial a asignar
	 */
	public void setEspecialidadpericial(String especialidadpericial) {
		this.especialidadpericial = especialidadpericial;
	}
	/**
	 * @return El valor de nacionalidad
	 */
	public String getNacionalidad() {
		return nacionalidad;
	}
	/**
	 * @param nacionalidad el valor de nacionalidad a asignar
	 */
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	/**
	 * @return El valor de idNacionalidad
	 */
	public String getIdNacionalidad() {
		return idNacionalidad;
	}
	/**
	 * @param idNacionalidad el valor de idNacionalidad a asignar
	 */
	public void setIdNacionalidad(String idNacionalidad) {
		this.idNacionalidad = idNacionalidad;
	}
	/**
	 * @return El valor de escolaridad
	 */
	public String getEscolaridad() {
		return escolaridad;
	}
	/**
	 * @param escolaridad el valor de escolaridad a asignar
	 */
	public void setEscolaridad(String escolaridad) {
		this.escolaridad = escolaridad;
	}
	/**
	 * @return El valor de idEscolaridad
	 */
	public String getIdEscolaridad() {
		return idEscolaridad;
	}
	/**
	 * @param idEscolaridad el valor de idEscolaridad a asignar
	 */
	public void setIdEscolaridad(String idEscolaridad) {
		this.idEscolaridad = idEscolaridad;
	}
	/**
	 * @return El valor de idioma
	 */
	public String getIdioma() {
		return idioma;
	}
	/**
	 * @param idioma el valor de idioma a asignar
	 */
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	/**
	 * @return El valor de idIdioma
	 */
	public String getIdIdioma() {
		return idIdioma;
	}
	/**
	 * @param idIdioma el valor de idIdioma a asignar
	 */
	public void setIdIdioma(String idIdioma) {
		this.idIdioma = idIdioma;
	}
	/**
	 * @return El valor de estadocivil
	 */
	public String getEstadocivil() {
		return estadocivil;
	}
	/**
	 * @param estadocivil el valor de estadocivil a asignar
	 */
	public void setEstadocivil(String estadocivil) {
		this.estadocivil = estadocivil;
	}
	/**
	 * @return El valor de idEstadocivil
	 */
	public String getIdEstadocivil() {
		return idEstadocivil;
	}
	/**
	 * @param idEstadocivil el valor de idEstadocivil a asignar
	 */
	public void setIdEstadocivil(String idEstadocivil) {
		this.idEstadocivil = idEstadocivil;
	}
	/**
	 * @return El valor de religion
	 */
	public String getReligion() {
		return religion;
	}
	/**
	 * @param religion el valor de religion a asignar
	 */
	public void setReligion(String religion) {
		this.religion = religion;
	}
	/**
	 * @return El valor de ocupacion
	 */
	public String getOcupacion() {
		return ocupacion;
	}
	/**
	 * @param ocupacion el valor de ocupacion a asignar
	 */
	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}
	
}

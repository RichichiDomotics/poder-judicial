/**
 * 
 */
package mx.gob.segob.nsjp.web.solicitud.form;

import mx.gob.segob.nsjp.web.base.action.GenericAction;
import mx.gob.segob.nsjp.web.base.form.GenericForm;

/**
 * @author EdgarAG
 *
 */
public class solicitudForm extends GenericForm {
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String curp;
	private String rfc;
	private String idioma;
	//private String religion;
	private String escolaridad;
	private String estadoCivil;
	private String sexo;
	private String ocupacion;
	private String nacionalidad;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getCurp() {
		return curp;
	}
	public void setCurp(String curp) {
		this.curp = curp;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
//	public String getReligion() {
//		return religion;
//	}
//	public void setReligion(String religion) {
//		this.religion = religion;
//	}
	public String getEscolaridad() {
		return escolaridad;
	}
	public void setEscolaridad(String escolaridad) {
		this.escolaridad = escolaridad;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getOcupacion() {
		return ocupacion;
	}
	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}

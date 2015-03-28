/**
* Nombre del Programa : funcionarioForm.java
* Autor                            : SergioDC
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 20 Jul 2011
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
package mx.gob.segob.nsjp.web.funcionario.form;

import mx.gob.segob.nsjp.web.base.form.GenericForm;

import org.apache.struts.upload.FormFile;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author SergioDC
 *
 */
public class FuncionarioForm extends GenericForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8825602910690544728L;

	private Long idFuncionario;
	private String numeroEmpleado;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String curp;
	private String rfc;
	private String sexo;
	private String edad;
	private String fechaIngreso;
	private String fechaNacimiento;
	private String area;
	private String puesto;
	private String tipoEspecialidad;
	private String especialidad;
	private String funcionario;
	private String medioContactoTelefono;
	private String medioContactoCorreo;
	private Long agenciaId;	
	private Long unidadInvEspId;
	
	/** archivo adjunto */
	private FormFile archivo;	
	/**
	 * Método de acceso al campo idFuncionario.
	 * @return El valor del campo idFuncionario
	 */
	public Long getIdFuncionario() {
		return idFuncionario;
	}
	/**
	 * Asigna el valor al campo idFuncionario.
	 * @param idFuncionario el valor idFuncionario a asignar
	 */
	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	/**
	 * Método de acceso al campo numeroEmpleado.
	 * @return El valor del campo numeroEmpleado
	 */
	public String getNumeroEmpleado() {
		return numeroEmpleado;
	}
	/**
	 * Asigna el valor al campo numeroEmpleado.
	 * @param numeroEmpleado el valor numeroEmpleado a asignar
	 */
	public void setNumeroEmpleado(String numeroEmpleado) {
		this.numeroEmpleado = numeroEmpleado;
	}
	/**
	 * Método de acceso al campo nombre.
	 * @return El valor del campo nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Asigna el valor al campo nombre.
	 * @param nombre el valor nombre a asignar
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Método de acceso al campo apellidoPaterno.
	 * @return El valor del campo apellidoPaterno
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	/**
	 * Asigna el valor al campo apellidoPaterno.
	 * @param apellidoPaterno el valor apellidoPaterno a asignar
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	/**
	 * Método de acceso al campo apellidoMaterno.
	 * @return El valor del campo apellidoMaterno
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	/**
	 * Asigna el valor al campo apellidoMaterno.
	 * @param apellidoMaterno el valor apellidoMaterno a asignar
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	/**
	 * Método de acceso al campo curp.
	 * @return El valor del campo curp
	 */
	public String getCurp() {
		return curp;
	}
	/**
	 * Asigna el valor al campo curp.
	 * @param curp el valor curp a asignar
	 */
	public void setCurp(String curp) {
		this.curp = curp;
	}
	/**
	 * Método de acceso al campo rfc.
	 * @return El valor del campo rfc
	 */
	public String getRfc() {
		return rfc;
	}
	/**
	 * Asigna el valor al campo rfc.
	 * @param rfc el valor rfc a asignar
	 */
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	/**
	 * Método de acceso al campo sexo.
	 * @return El valor del campo sexo
	 */
	public String getSexo() {
		return sexo;
	}
	/**
	 * Asigna el valor al campo sexo.
	 * @param sexo el valor sexo a asignar
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	/**
	 * Método de acceso al campo fechaNacimiento.
	 * @return El valor del campo fechaNacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	/**
	 * Asigna el valor al campo fechaNacimiento.
	 * @param fechaNacimiento el valor fechaNacimiento a asignar
	 */
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	/**
	 * Método de acceso al campo fechaNacimiento.
	 * @return El valor del campo fechaNacimiento
	 */
	public String getFechaIngreso() {
		return fechaIngreso;
	}
	/**
	 * Asigna el valor al campo fechaNacimiento.
	 * @param fechaNacimiento el valor fechaNacimiento a asignar
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	/**
	 * Método de acceso al campo area.
	 * @return El valor del campo area
	 */
	public String getArea() {
		return area;
	}
	/**
	 * Asigna el valor al campo area.
	 * @param area el valor area a asignar
	 */
	public void setArea(String area) {
		this.area = area;
	}
	/**
	 * Método de acceso al campo puesto.
	 * @return El valor del campo puesto
	 */
	public String getPuesto() {
		return puesto;
	}
	/**
	 * Asigna el valor al campo puesto.
	 * @param puesto el valor puesto a asignar
	 */
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	/**
	 * Método de acceso al campo tipoEspecialidad.
	 * @return El valor del campo tipoEspecialidad
	 */
	public String getTipoEspecialidad() {
		return tipoEspecialidad;
	}
	/**
	 * Asigna el valor al campo tipoEspecialidad.
	 * @param tipoEspecialidad el valor tipoEspecialidad a asignar
	 */
	public void setTipoEspecialidad(String tipoEspecialidad) {
		this.tipoEspecialidad = tipoEspecialidad;
	}
	/**
	 * Método de acceso al campo especialidad.
	 * @return El valor del campo especialidad
	 */
	public String getEspecialidad() {
		return especialidad;
	}
	/**
	 * Asigna el valor al campo especialidad.
	 * @param especialidad el valor especialidad a asignar
	 */
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	/**
	 * Método de acceso al campo funcionario.
	 * @return El valor del campo funcionario
	 */
	public String getFuncionario() {
		return funcionario;
	}
	/**
	 * Asigna el valor al campo funcionario.
	 * @param funcionario el valor funcionario a asignar
	 */
	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}
	/**
	 * Método de acceso al campo medioContactoTelefono.
	 * @return El valor del campo medioContactoTelefono
	 */
	public String getMedioContactoTelefono() {
		return medioContactoTelefono;
	}
	/**
	 * Asigna el valor al campo medioContactoTelefono.
	 * @param medioContactoTelefono el valor medioContactoTelefono a asignar
	 */
	public void setMedioContactoTelefono(String medioContactoTelefono) {
		this.medioContactoTelefono = medioContactoTelefono;
	}
	/**
	 * Método de acceso al campo medioContactoCorreo.
	 * @return El valor del campo medioContactoCorreo
	 */
	public String getMedioContactoCorreo() {
		return medioContactoCorreo;
	}
	/**
	 * Asigna el valor al campo medioContactoCorreo.
	 * @param medioContactoCorreo el valor medioContactoCorreo a asignar
	 */
	public void setMedioContactoCorreo(String medioContactoCorreo) {
		this.medioContactoCorreo = medioContactoCorreo;
	}
	/**
	 * Método de acceso al campo edad.
	 * @return El valor del campo edad
	 */
	public String getEdad() {
		return edad;
	}
	/**
	 * Asigna el valor al campo edad.
	 * @param edad el valor edad a asignar
	 */
	public void setEdad(String edad) {
		this.edad = edad;
	}
	
	/**
	 * Método de acceso al campo Agencia relacionado al funcionario.
	 * @return El valor del campo agenciaId
	 */
	public Long getAgenciaId() {
		return agenciaId;
	}
	
	/**
	 * Asigna el valor al campo agencia.
	 * @param agenciaId el valor de la agenciaId a asignar
	 */
	public void setAgenciaId(Long agenciaId) {
		this.agenciaId = agenciaId;
	}
	/**
	 * @return the archivo
	 */
	public FormFile getArchivo() {
		return archivo;
	}
	/**
	 * @param archivo the archivo to set
	 */
	public void setArchivo(FormFile archivo) {
		this.archivo = archivo;
	}
	public Long getUnidadInvEspId() {
		return unidadInvEspId;
}
	public void setUnidadInvEspId(Long unidadInvEspId) {
		this.unidadInvEspId = unidadInvEspId;
	}
}

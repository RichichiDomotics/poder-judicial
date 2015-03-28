/**
* Nombre del Programa : IngresarServidorPublicoForm.java
* Autor               : Arturo Leon
* Compania            : Ultrasist
* Proyecto            : NSJP                    Fecha: 09/05/2011
* Marca de cambio     : N/A
* Descripcion General : Clase Form para Ingresar Servidor Publico
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
 * Forma Asociada a la pantalla Ingresar Servidor Publico
 * 
 * @version 1.0
 * @author Arturo Leon Galicia - Ultrasist
 * 
 * 
 */
public class IngresarServidorPublicoForm extends GenericForm{

	/** Default Serial version */
	private static final long serialVersionUID = -8825062910690544728L;
	
	private Short nivelDependenciaServPublico;
	private Short tipoDependenciaServPublico;
	private Short dependenciaServPublico;
	private Short puestoServPublico;
	private String numEmpleadoServPublico;
	/**
	 * Método de acceso al campo dependenciaServPublico.
	 * @return El valor del campo dependenciaServPublico
	 */
	public Short getDependenciaServPublico() {
		return dependenciaServPublico;
	}
	/**
	 * Asigna el valor al campo dependenciaServPublico.
	 * @param dependenciaServPublico el valor dependenciaServPublico a asignar
	 */
	public void setDependenciaServPublico(Short dependenciaServPublico) {
		this.dependenciaServPublico = dependenciaServPublico;
	}
	/**
	 * Método de acceso al campo puestoServPublico.
	 * @return El valor del campo puestoServPublico
	 */
	public Short getPuestoServPublico() {
		return puestoServPublico;
	}
	/**
	 * Asigna el valor al campo puestoServPublico.
	 * @param puestoServPublico el valor puestoServPublico a asignar
	 */
	public void setPuestoServPublico(Short puestoServPublico) {
		this.puestoServPublico = puestoServPublico;
	}
	/**
	 * Método de acceso al campo numEmpleadoServPublico.
	 * @return El valor del campo numEmpleadoServPublico
	 */
	public String getNumEmpleadoServPublico() {
		return numEmpleadoServPublico;
	}
	/**
	 * Asigna el valor al campo numEmpleadoServPublico.
	 * @param numEmpleadoServPublico el valor numEmpleadoServPublico a asignar
	 */
	public void setNumEmpleadoServPublico(String numEmpleadoServPublico) {
		this.numEmpleadoServPublico = numEmpleadoServPublico;
	}
	/**
	 * Método de acceso al campo nivelDependenciaServPublico.
	 * @return El valor del campo nivelDependenciaServPublico
	 */
	public Short getNivelDependenciaServPublico() {
		return nivelDependenciaServPublico;
	}
	/**
	 * Asigna el valor al campo nivelDependenciaServPublico.
	 * @param nivelDependenciaServPublico el valor nivelDependenciaServPublico a asignar
	 */
	public void setNivelDependenciaServPublico(Short nivelDependenciaServPublico) {
		this.nivelDependenciaServPublico = nivelDependenciaServPublico;
	}
	/**
	 * Método de acceso al campo tipoDependenciaServPublico.
	 * @return El valor del campo tipoDependenciaServPublico
	 */
	public Short getTipoDependenciaServPublico() {
		return tipoDependenciaServPublico;
	}
	/**
	 * Asigna el valor al campo tipoDependenciaServPublico.
	 * @param tipoDependenciaServPublico el valor tipoDependenciaServPublico a asignar
	 */
	public void setTipoDependenciaServPublico(Short tipoDependenciaServPublico) {
		this.tipoDependenciaServPublico = tipoDependenciaServPublico;
	}
	
	
}
/**
* Nombre del Programa 	: BuscarExpedientePorNombreDePersonaForm.java                                    
* Autor               	: Cuauhtemoc Paredes Serrano                                              
* Compania            	: Ultrasist                                                
* Proyecto            	: NSJP              			Fecha:14/03/2011 
* Marca de cambio     	: N/A                                                     
* Descripcion General   : Clase forma para la busqueda de expediente
* Programa Dependiente  : N/A                                                      
* Programa Subsecuente 	: N/A                                                      
* Cond. de ejecucion    : struts-config.xml                                                    
* Dias de ejecucion     : N/A                             Horario: N/A
*                               MODIFICACIONES                                       
*------------------------------------------------------------------------------           
* Autor                 :N/A                                                           
* Compania              :N/A                                                           
* Proyecto              :N/A                                   Fecha: N/A       
* Modificacion          :N/A                                                           
*------------------------------------------------------------------------------      
*/
package mx.gob.segob.nsjp.web.expediente.form;

import mx.gob.segob.nsjp.web.base.form.GenericForm;

/**
 * Clase de forma de las busquedas de expedientes
 * @version 1.0
 * @author CuauhtemocPS 
 */
public class BuscarExpedientePorNombreDePersonaForm extends GenericForm{

	/**
	 * Serializable
	 */
	private static final long serialVersionUID = 5246580057170055125L;
	/**
	 * Cadena que representa la propiedad nombre
	 */
	private String nombre;
	/**
	 * Cadena que representa la propiedad apellido paterno
	 */
	private String apellido;
	/**
	 *  Cadena que representa la propiedad apellido materno
	 */
	private String apellidoMat;
	/**
	 * Constructor de la clase
	 */
	public BuscarExpedientePorNombreDePersonaForm() {
		
	}
	/**
	 * @return el nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre el nombre a asignar
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return el apellido
	 */
	public String getApellido() {
		return apellido;
	}
	/**
	 * @param apellido el apellido a asignar
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}	
	/**
	 * @return the apellidoMat
	 */
	public String getApellidoMat() {
		return apellidoMat;
	}
	/**
	 * @param apellidoMat the apellidoMat to set
	 */
	public void setApellidoMat(String apellidoMat) {
		this.apellidoMat = apellidoMat;
	}
	/**
	 * @return el serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}

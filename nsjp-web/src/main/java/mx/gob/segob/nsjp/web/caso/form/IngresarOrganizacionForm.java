/**
* Nombre del Programa 	: IngresarOrganizacionForm.java                                    
* Autor               	: Armando Castaneda                                            
* Compania            	: Ultrasist                                                
* Proyecto            	: NSJP              			Fecha:09/05/2011 
* Marca de cambio     	: N/A                                                     
* Descripcion General   : Clase que implementa la forma que se tiene en la JSP 
* Programa Dependiente  : N/A                                                      
* Programa Subsecuente 	: N/A                                                      
* Cond. de ejecucion    : N/A                                                    
* Dias de ejecucion     : N/A                             Horario: N/A
*                               MODIFICACIONES                                       
*------------------------------------------------------------------------------           
* Autor                 :N/A                                                           
* Compania              :N/A                                                           
* Proyecto              :N/A                                   Fecha: N/A       
* Modificacion          :N/A                                                           
*------------------------------------------------------------------------------      
*/
package mx.gob.segob.nsjp.web.caso.form;

import mx.gob.segob.nsjp.web.base.form.GenericForm;

/**
 * Clase que implementa la forma que se tiene en la JSP Ingresar organizacion
 * 
 * @version 1.0 
 * @author ArmandoCT
 */
public class IngresarOrganizacionForm extends GenericForm {

	private static final long serialVersionUID = 1L;
	
	/** identificador del pais */
	private Long glNivelDependencia;
	
	/** identificador del pais */
	private Long glTipoDependencia;

	
	/***
	 * obtiene el ID del nivel de dependencia
	 * @return glNivelDependencia
	 */
	public Long getGlNivelDependencia() {
		return glNivelDependencia;
	}

	/***
	 * 
	 * @param glNivelDependencia, el ID del Nivel de Dependencia
	 */
	public void setGlNivelDependencia(Long glNivelDependencia) {
		this.glNivelDependencia = glNivelDependencia;
	}

	/***
	 * Obtiene el ID del tipo de dependencia
	 * @return glTipoDependencia
	 */
	public Long getGlTipoDependencia() {
		return glTipoDependencia;
	}

	/***
	 * 
	 * @param glTipoDependencia, el ID del Tipo de dependencia
	 */
	public void setGlTipoDependencia(Long glTipoDependencia) {
		this.glTipoDependencia = glTipoDependencia;
	}
	
}

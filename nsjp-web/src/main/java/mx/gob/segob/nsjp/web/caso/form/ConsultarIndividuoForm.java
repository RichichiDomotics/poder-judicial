/**
 * Nombre del Programa 		: ConsultarIndividuoForm.java                                    
 * Autor               		: Alejandro Galaviz                                              
 * Compania            		: Ultrasist                                                
 * Proyecto            		: NSJP              		Fecha:03/03/2011 
 * Marca de cambio     		: N/A                                                     
 * Descripcion General   	: Forma usada para recuperar el no de expediente y el
 * 								id de la calidad del individuo
 * Programa Dependiente  	: N/A                                                      
 * Programa Subsecuente 	: N/A                                                      
 * Cond. de ejecucion    	: N/A                                                  
 * Dias de ejecucion     	: N/A                      Horario: N/A
 *                               MODIFICACIONES                                       
 *------------------------------------------------------------------------------           
 * Autor                	:Jorge Ignacio Fernandez Ortiz                                                           
 * Compania              	:Ultrasist                                                          
 * Proyecto              	:NSJP                                    Fecha: 11/03/2011      
 * Modificacion          	:Se coloca el atributo que identifica al involucrado 
 * 							 y su nombre demografico seleccionado.  
 *------------------------------------------------------------------------------      
 */
package mx.gob.segob.nsjp.web.caso.form;

import mx.gob.segob.nsjp.web.base.form.GenericForm;

/**
 * Clase que implementa la forma para el CU consultar individuo
 * @version 1.0 
 * @author AlejandroGA
 */
public class ConsultarIndividuoForm extends GenericForm {
	
	/**
	 * Serializacion para racepcion de la informacion 
	 */
	private static final long serialVersionUID = 1L;
	
	/**Atributo que representa el no de expediente
	 * asociado al individuo a consultar
	 */
	private String noExpediente;
	
	/**Atributo que representa la calidad del 
	 * individuo a consultar
	 */
	private String calidadIndividuo;
	
	/**Atributo que representa el identificador del individuo seleccionado 
	 * en la busqueda
	 */
	private String idIndividuo;
	
	/**Atributo que representa el identificador del nombre demografico seleccionado 
	 * en la busqueda
	 */
	private String idNombreDemografico;
	
	
	/**
	 * @return noExpediente	,el valor del no de expediente
	 */
	public String getNoExpediente() {
		return noExpediente;
	}
	
	/**
	 * @param noExpediente	,el numero de expediente
	 */
	public void setNoExpediente(String noExpediente) {
		this.noExpediente = noExpediente;
	}
	
	/**
	 * @return calidadIndividuo	,el valor del id de la
	 * calidad del individuo
	 */
	public String getCalidadIndividuo() {
		return calidadIndividuo;
	}
	
	/**
	 * @param calidadIndividuo	,el id de la calidad del individuo
	 */
	public void setCalidadIndividuo(String calidadIndividuo) {
		this.calidadIndividuo = calidadIndividuo;
	}

	/**
	 * @return idIndividuo, el valos del id del individuo
	 */
	public String getIdIndividuo() {
		return idIndividuo;
	}

	/**
	 * @param idIndividuo, el id del individuo
	 */
	public void setIdIndividuo(String idIndividuo) {
		this.idIndividuo = idIndividuo;
	}

	/**
	 * @return idNombreDemografico, el valor del id del nombre demografico
	 */
	public String getIdNombreDemografico() {
		return idNombreDemografico;
	}

	/**
	 * @param idNombreDemografico, el id del nombre demografico
	 */
	public void setIdNombreDemografico(String idNombreDemografico) {
		this.idNombreDemografico = idNombreDemografico;
	}	
}

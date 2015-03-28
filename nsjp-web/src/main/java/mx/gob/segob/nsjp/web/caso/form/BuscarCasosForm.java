/**
* Nombre del Programa 	: BuscarExpedientePorAliasForm.java                                    
* Autor               	: Andres Gomez Godinez                                              
* Compania            	: Ultrasist                                                
* Proyecto            	: NSJP              			Fecha:10/03/2011 
* Marca de cambio     	: N/A                                                     
* Descripcion General   : Clase forma para el CU Buscar Expediente Por Alias
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
package mx.gob.segob.nsjp.web.caso.form;

import mx.gob.segob.nsjp.web.base.form.GenericForm;

/**
 * Clase forma para el CU Buscar Expediente Por Alias
 * @version 1.0
 * @author AndresGG
 */

public class BuscarCasosForm extends GenericForm {

	/**Atributo que representa el Alias a buscar
	 */
	private Long idCaso;

	public Long getIdCaso() {
		return idCaso;
	}

	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}
}

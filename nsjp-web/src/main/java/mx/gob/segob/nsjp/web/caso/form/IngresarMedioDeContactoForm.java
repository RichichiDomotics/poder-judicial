/**
*
* Nombre del Programa : IngresarMedioDeContactoForm.java                                    
* Autor                  : Andres Gomez Godinez                                             
* Compania               : Ultrasist                                                
* Proyecto               : NSJP                    Fecha: 26-02-2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Forma para ingresar medios de contacto                      
* Programa Dependiente   :N/A                                                      
* Programa Subsecuente   :N/A                                                      
* Cond. de ejecucion     :N/A                                                      
* Dias de ejecucion      :N/A                             Horario: N/A       
*                              MODIFICACIONES                                       
*------------------------------------------------------------------------------           
* Autor                  :N/A                                                           
* Compania               :N/A                                                           
* Proyecto               :N/A                                   Fecha: N/A       
* Modificacion           :N/A                                                           
*------------------------------------------------------------------------------           
*/


package mx.gob.segob.nsjp.web.caso.form;

import mx.gob.segob.nsjp.web.base.form.GenericForm;
/**
 * 
 * Forma para ingresar medios de contacto
 * 
 * @version 1.0
 * @author Andres Gomez Godinez
 * 
 
 */
public class IngresarMedioDeContactoForm extends GenericForm {
	
	private static final long serialVersionUID = 1L;
	
	private String TipoTelefono;

	/**
	 * 
	 * @return TipoTelefono
	 */
	public String getTipoTelefono() {
		return TipoTelefono;
	}
	
	/**
	 * 
	 * @param tipoTelefono
	 */
	public void setTipoTelefono(String tipoTelefono) {
		TipoTelefono = tipoTelefono;
	}

}

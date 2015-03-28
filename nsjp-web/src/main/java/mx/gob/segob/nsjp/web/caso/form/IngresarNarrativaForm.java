/**
*
* Nombre del Programa : IngresarNarrativaForm.java                                    
* Autor                            : Armando Castaneda
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 24/02/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Integracion xxxxxxxxxxx                      
* Programa Dependiente  :N/A                                                      
* Programa Subsecuente :N/A                                                      
* Cond. de ejecucion        :N/A                                                      
* Dias de ejecucion          :N/A                             Horario: N/A       
*                              MODIFICACIONES                                       
*------------------------------------------------------------------------------           
* Autor                       :N/A                                                           
* Compania               :N/A                                                           
* Proyecto                 :N/A                                   Fecha: N/A       
* Modificacion           :N/A                                                           
*------------------------------------------------------------------------------           
*/
package mx.gob.segob.nsjp.web.caso.form;

import javax.servlet.http.HttpServletRequest;

import mx.gob.segob.nsjp.web.base.form.GenericForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * Clase ActionForm para Ingresar Narrativa
 * 
 * @version 1.0
 * @author Armando Castaneda Tenango
 */

public class IngresarNarrativaForm extends GenericForm{
	/**
	 * Atributo para guardar el texto de la narrativa
	 */
	public String texto;
	
	/**
	 * Atributo para guardar el tipo de la narrativa
	 */
	public String tipoNarrativa;
	
	/**
	 * Atributo para guardar el numero de expediente
	 */
	public String numeroExpediente;
	
	/** Log de clase*/
	private static final Logger log  = Logger.getLogger(IngresarNarrativaForm.class);
	
	/**
	 * Funcion para obtener el atributo texto
	 * @return String - regresamos el valor de texto
	 */
	public String getTexto() {
		return texto;
	}
	/**
	 * Funcion para setear al atributo texto
	 * @param texto - String con el texto a setear en el atributo texto
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	/**
	 * Funcion para obtener tipoNarrativa
	 * @return String - con el valor de tipoNarrativa
	 */
	public String getTipoNarrativa() {
		return tipoNarrativa;
	}
	
	/**
	 * Funcion para setear el tipoNarrativa
	 * @param tipoNarrativa String - con el valor a setear en tipoNarrativa
	 */
	public void setTipoNarrativa(String tipoNarrativa) {
		this.tipoNarrativa = tipoNarrativa;
	}
	
	/**
	 * Funcion para obtener el numeroExpediente
	 * @return String - cadena con el valor de numeroExpediente
	 */
	public String getNumeroExpediente() {
		return numeroExpediente;
	}
	
	/**
	 * Funcion para setear el numeroExpediente
	 * @param numeroExpediente String - con el numero de expediente a setear
	 */
	public void setNumeroExpediente(String numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}
	
	/**
	 * Funcion para hacer reset a los campos del jsp asociado a este ActionForm
	 */
	public void reset(ActionMapping map, HttpServletRequest req){
        this.texto = "";
        this.tipoNarrativa="";
        this.numeroExpediente="";
    }

	/**
	 * Funcion para realizar las validaciones y revisar si todos los datos fueron
	 * ingresados en la forma
	 */
    public ActionErrors validate(ActionMapping map,
    HttpServletRequest req){
    	log.info("ejecutando validate  - IngresarNarrativaForm");
    	ActionErrors errors;
    	errors=null;
    	errors = new ActionErrors();
        if(((texto == null) || (texto.length() < 1)) || 
        		((tipoNarrativa == null) || (tipoNarrativa.length() < 1)) ||
        		((numeroExpediente == null) || (numeroExpediente.length() < 1)))
            errors.add("ingresarNarrativaError", new ActionMessage("ingresarNarrativaError"));
        return errors;
    }
	
}

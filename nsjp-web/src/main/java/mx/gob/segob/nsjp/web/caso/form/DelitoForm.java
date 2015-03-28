/**
* Nombre del Programa : DelitoForm.java
* Autor               : Arturo Leon
* Compania            : Ultrasist
* Proyecto            : NSJP                    Fecha: 01/04/2011
* Marca de cambio     : N/A
* Descripcion General : Clase Form para capturar los datos de entranda en la vista de delitos
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
* Clase Form para capturar los datos de entranda en la vista de delitos.
*
* @version 1.0
* @author Arturo Leon
*/
public class DelitoForm extends GenericForm {

	/** Default Serial version */
	private static final long serialVersionUID = 993838711305607075L;
	
	/**Nombre del delito*/
	private String delito;
	
	/**Id del delito*/
	private String idDelito;

	/**
	 * @return El valor de delito
	 */
	public String getDelito() {
		return delito;
	}

	/**
	 * @param delito el valor de delito a asignar
	 */
	public void setDelito(String delito) {
		this.delito = delito;
	}

	/**
	 * @return El valor de idDelito
	 */
	public String getIdDelito() {
		return idDelito;
	}

	/**
	 * @param idDelito el valor de idDelito a asignar
	 */
	public void setIdDelito(String idDelito) {
		this.idDelito = idDelito;
	}

	

}

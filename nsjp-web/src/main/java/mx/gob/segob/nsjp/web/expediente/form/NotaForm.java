/**
* Nombre del Programa 		: NoraForm.java
* Autor                     : ArmandoCT
* Compania                  : Ultrasist
* Proyecto                  : NSJP                    Fecha: 21 Marzo 2012
* Marca de cambio        	: N/A
* Descripcion General    	: Forma para el objeto Nota del Expediente.
* Programa Dependiente  	: N/A
* Programa Subsecuente 		: N/A
* Cond. de ejecucion        : N/A
* Dias de ejecucion         : N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor                     : N/A
* Compania               	: N/A
* Proyecto                 	: N/A                                 Fecha: N/A
* Modificacion           	: N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.web.expediente.form;

import mx.gob.segob.nsjp.web.base.form.GenericForm;

/**
 * Forma para el objeto Nota.
 * @version 1.0
 * @author ArmandoCT
 *
 */
public class NotaForm extends GenericForm{

	/**
	 * Serialize de la clase
	 */
	private static final long serialVersionUID = 1L;
	
	//id para la Nota
	private String idNota;
		
	//campo para el cuerpo de la nota
	private String notas;
	
	//para guardar si el numero de expediente que se ligará a la nota
	private String idNumeroExpediente;
	
		/**
	 * @return the idNota
	 */
	public String getIdNota() {
		return idNota;
	}

	/**
	 * @param idNota the idNota to set
	 */
	public void setIdNota(String idNota) {
		this.idNota = idNota;
	}

	/**
	 * @return the notas
	 */
	public String getNotas() {
		return notas;
	}

	/**
	 * @param notas the notas to set
	 */
	public void setNotas(String notas) {
		this.notas = notas;
	}

	/**
	 * @return the idNumeroExpediente
	 */
	public String getIdNumeroExpediente() {
		return idNumeroExpediente;
	}

	/**
	 * @param idNumeroExpediente the idNumeroExpediente to set
	 */
	public void setIdNumeroExpediente(String idNumeroExpediente) {
		this.idNumeroExpediente = idNumeroExpediente;
	}
	
}

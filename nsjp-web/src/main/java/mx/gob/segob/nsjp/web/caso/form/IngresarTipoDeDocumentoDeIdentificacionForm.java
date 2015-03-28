/**
 *Nombre del Programa : IngresarTipoDeDocumentoDeIdentificacionForm.java                                   
 *Autor               : Andres Gomez Godinez                                              
 *Compania            : Ultrasist                                                
 *Proyecto            : NSJP                    Fecha: 5 Mar 2011 
 *Marca de cambio     : N/A                                                     
 *Descripcion General : Form                   
 *Programa Dependiente :N/A                                                      
 *Programa Subsecuente :N/A                                                      
 *Cond. de ejecucion   :N/A                                                  
 *Dias de ejecucion    :N/A                     Horario: N/A  
 */
package mx.gob.segob.nsjp.web.caso.form;

/**
 * Forma que recibe los datos de tipo de documento de identificacion
 * @version 1.0
 * @author AndresGG
 */

public class IngresarTipoDeDocumentoDeIdentificacionForm {
	
	private String TipoDeDocumentoDeIdentificacion;

	/**
	 * @return TipoDeDocumentoDeIdentificacion
	 */
	public String getTipoDeDocumentoDeIdentificacion() {
		return TipoDeDocumentoDeIdentificacion;
	}

	/**
	 * @param tipoDeDocumentoDeIdentificacion
	 **/
	public void setTipoDeDocumentoDeIdentificacion(
			String tipoDeDocumentoDeIdentificacion) {
		TipoDeDocumentoDeIdentificacion = tipoDeDocumentoDeIdentificacion;
	}

}

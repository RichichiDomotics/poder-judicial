/**
* Nombre del Programa : BuscarAliasForm.java
* Autor               : Cuauhtemoc Paredes Serrano
* Compania            : Ultrasist
* Proyecto            : NSJP                    Fecha: 28/04/2011
* Marca de cambio     : N/A
* Descripcion General : Clase Form para buscar alias
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
package mx.gob.segob.nsjp.web.expediente.form;

import mx.gob.segob.nsjp.web.base.form.GenericForm;

/**
 * Forma Asociada a la pantalla buscar expediente por alias
 * 
 * @version 1.0
 * @author Cuauhtemoc Paredes Serrano - Ultrasist
 * 
 * 
 */
public class BuscarExpedientePorAliasForm extends GenericForm{

	/** Default Serial version */
	private static final long serialVersionUID = -8825062910690544728L;
	
	private String alias;
	
	private Long tipo;

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Long getTipo() {
		return tipo;
	}

	public void setTipo(Long tipo) {
		this.tipo = tipo;
	}
	
	
	
	
	
}
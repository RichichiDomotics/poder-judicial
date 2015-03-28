/**
* Nombre del Programa		: ElementoForm.java
* Autor                 	: rgama
* Compania                  : Ultrasist
* Proyecto                  : NSJP                    Fecha: 24 08 2011
* Marca de cambio        	: N/A
* Descripcion General    	: Forma para el objeto Elemento
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
package mx.gob.segob.nsjp.web.elemento.form;

import org.apache.struts.upload.FormFile;

import mx.gob.segob.nsjp.web.base.form.GenericForm;

/**
 * Forma para el objeto Elemento.
 * @version 1.0
 * @author rgama
 *
 */
public class ElementoForm extends GenericForm{

	/**
	 * Serialize de la clase
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idElemento;
	
	private Long muestraDetenidoModificar;
		
	/** archivo adjunto */
	private FormFile archivo;
		
	
	/**
	 * @return the muestraDetenidoModificar
	 */
	public Long getMuestraDetenidoModificar() {
		return muestraDetenidoModificar;
	}

	/**
	 * @param muestraDetenidoModificar the muestraDetenidoModificar to set
	 */
	public void setMuestraDetenidoModificar(Long muestraDetenidoModificar) {
		this.muestraDetenidoModificar = muestraDetenidoModificar;
	}

	/**
	 * Método de acceso al campo idElemento.
	 * @return El valor del campo idElemento
	 */
	public Long getIdElemento() {
		return idElemento;
	}

	/**
	 * Asigna el valor al campo idElemento.
	 * @param idElemento el valor idElemento a asignar
	 */
	public void setIdElemento(Long idElemento) {
		this.idElemento = idElemento;
	}

	/**
	 * @return the archivo
	 */
	public FormFile getArchivo() {
		return archivo;
	}

	/**
	 * @param archivo the archivo to set
	 */
	public void setArchivo(FormFile archivo) {
		this.archivo = archivo;
	}


	
}

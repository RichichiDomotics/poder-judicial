/**
* Nombre del Programa 				: AdministrarActosBuenaConductaForm.java
* Autor                            	: EdgarTE
* Compania                    		: Ultrasist
* Proyecto                      	: NSJP                    	Fecha: 20 Mar 2012
* Marca de cambio        			: N/A
* Descripcion General    			: Forma de struts para mostrar los datos de 
* 									  Administraci&oacute;n de actos de buena 
* 									  conducta.
* Programa Dependiente  			: N/A
* Programa Subsecuente 				: N/A
* Cond. de ejecucion        		: N/A
* Dias de ejecucion          		: N/A 						Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor                       		: N/A
* Compania               			: N/A
* Proyecto                 			: N/A 						Fecha: N/A
* Modificacion           :N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.web.reinsercionsocial.form;

import java.util.List;

import mx.gob.segob.nsjp.dto.detencion.CentroDetencionDTO;
import mx.gob.segob.nsjp.web.base.form.GenericForm;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author EdgarTE
 *
 */
public class EnvioDocumentosReinsercionForm extends GenericForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3184313874682558790L;

	private String cereso;
	
	private String fechaDeIngreso;
	
	private List<CentroDetencionDTO>  lstCentrosDetencion;

	
	
	/**
	 * @return the lstCentrosDetencion
	 */
	public List<CentroDetencionDTO> getLstCentrosDetencion() {
		return lstCentrosDetencion;
	}

	/**
	 * @param lstCentrosDetencion the lstCentrosDetencion to set
	 */
	public void setLstCentrosDetencion(List<CentroDetencionDTO> lstCentrosDetencion) {
		this.lstCentrosDetencion = lstCentrosDetencion;
	}

	/**
	 * @return the cereso
	 */
	public String getCereso() {
		return cereso;
	}

	/**
	 * @param cereso the cereso to set
	 */
	public void setCereso(String cereso) {
		this.cereso = cereso;
	}

	/**
	 * @return the fechaDeIngreso
	 */
	public String getFechaDeIngreso() {
		return fechaDeIngreso;
	}

	/**
	 * @param fechaDeIngreso the fechaDeIngreso to set
	 */
	public void setFechaDeIngreso(String fechaDeIngreso) {
		this.fechaDeIngreso = fechaDeIngreso;
	}
	
}

/**
* Nombre del Programa 		: AdministrarAcumulacionPuntosForm.java
* Autor                  	: EdgarTE
* Compania              	: Ultrasist
* Proyecto         		    : NSJP 						`	Fecha: 22 Mar 2012
* Marca de cambio  			: N/A
* Descripcion General    	: Forma de struts para acumulaci&oacute;n de puntos.
* Programa Dependiente  	: N/A
* Programa Subsecuente 		: N/A
* Cond. de ejecucion        : N/A
* Dias de ejecucion   		: N/A 							Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor 					: N/A
* Compania               	: N/A
* Proyecto                 	: N/A 							Fecha: N/A
* Modificacion           	: N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.web.reinsercionsocial.form;

import mx.gob.segob.nsjp.web.base.form.GenericForm;

/**
 * Forma de struts que contiene los campos que son utilizados para la 
 * acumulaci&oacute;n de puntos derivados de actos de buena conducta.
 * @version 1.0
 * @author EdgarTE
 *
 */
public class AdministrarAcumulacionPuntosForm extends GenericForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4962925383379377829L;
	
	private String nombrePeriodo;
	private String fechaInicio;
	private String fechaTermino;
	private long sentenciaId;
	private String idsAbcConcatenados;
	private String nombreABC;
	private String descABC;
	private String acumuladoABC;
	private long puntosABC;
	
	
	/**
	 * Método de acceso al campo nombrePeriodo.
	 * @return El valor del campo nombrePeriodo
	 */
	public String getNombrePeriodo() {
		return nombrePeriodo;
	}
	
	/**
	 * Asigna el valor al campo nombrePeriodo.
	 * @param nombrePeriodo el valor nombrePeriodo a asignar
	 */
	public void setNombrePeriodo(String nombrePeriodo) {
		this.nombrePeriodo = nombrePeriodo;
	}
	
	/**
	 * Método de acceso al campo fechaInicio.
	 * @return El valor del campo fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}
	
	/**
	 * Asigna el valor al campo fechaInicio.
	 * @param fechaInicio el valor fechaInicio a asignar
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	/**
	 * Método de acceso al campo fechaTermino.
	 * @return El valor del campo fechaTermino
	 */
	public String getFechaTermino() {
		return fechaTermino;
	}
	
	/**
	 * Asigna el valor al campo fechaTermino.
	 * @param fechaTermino el valor fechaTermino a asignar
	 */
	public void setFechaTermino(String fechaTermino) {
		this.fechaTermino = fechaTermino;
	}

	/**
	 * Método de acceso al campo sentenciaId.
	 * @return El valor del campo sentenciaId
	 */
	public long getSentenciaId() {
		return sentenciaId;
	}

	/**
	 * Asigna el valor al campo sentenciaId.
	 * @param sentenciaId el valor sentenciaId a asignar
	 */
	public void setSentenciaId(long sentenciaId) {
		this.sentenciaId = sentenciaId;
	}

	/**
	 * Método de acceso al campo idsAbcConcatenados.
	 * @return El valor del campo idsAbcConcatenados
	 */
	public String getIdsAbcConcatenados() {
		return idsAbcConcatenados;
	}

	/**
	 * Asigna el valor al campo idsAbcConcatenados.
	 * @param idsAbcConcatenados el valor idsAbcConcatenados a asignar
	 */
	public void setIdsAbcConcatenados(String idsAbcConcatenados) {
		this.idsAbcConcatenados = idsAbcConcatenados;
	}

	/**
	 * Método de acceso al campo nombreABC.
	 * @return El valor del campo nombreABC
	 */
	public String getNombreABC() {
		return nombreABC;
	}

	/**
	 * Asigna el valor al campo nombreABC.
	 * @param nombreABC el valor nombreABC a asignar
	 */
	public void setNombreABC(String nombreABC) {
		this.nombreABC = nombreABC;
	}

	/**
	 * Método de acceso al campo descABC.
	 * @return El valor del campo descABC
	 */
	public String getDescABC() {
		return descABC;
	}

	/**
	 * Asigna el valor al campo descABC.
	 * @param descABC el valor descABC a asignar
	 */
	public void setDescABC(String descABC) {
		this.descABC = descABC;
	}

	/**
	 * Método de acceso al campo acumuladoABC.
	 * @return El valor del campo acumuladoABC
	 */
	public String getAcumuladoABC() {
		return acumuladoABC;
	}

	/**
	 * Asigna el valor al campo acumuladoABC.
	 * @param acumuladoABC el valor acumuladoABC a asignar
	 */
	public void setAcumuladoABC(String acumuladoABC) {
		this.acumuladoABC = acumuladoABC;
	}

	/**
	 * Método de acceso al campo puntosABC.
	 * @return El valor del campo puntosABC
	 */
	public long getPuntosABC() {
		return puntosABC;
	}

	/**
	 * Asigna el valor al campo puntosABC.
	 * @param puntosABC el valor puntosABC a asignar
	 */
	public void setPuntosABC(long puntosABC) {
		this.puntosABC = puntosABC;
	}
	
	

}

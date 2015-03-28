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

import mx.gob.segob.nsjp.web.base.form.GenericForm;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author EdgarTE
 *
 */
public class AdministrarActosBuenaConductaForm extends GenericForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8151756403824232609L;
	
	private String nombreSentenciado;
	private String nus;
	private String tipoSentencia;
	private String fechaInicioPena;
	private String fechaFinPena;
	private String nombreCereso;
	private String nombreABC;
	private String descABC;
	private String acumuladoABC;
	private long actoBuenaConductaId;
	private long puntosPorAcumular;
	private long puntosAcumulados;
	private long sentenciaId;
	private long puntosABC;
	
	/**
	 * Método de acceso al campo nombreSentenciado.
	 * @return El valor del campo nombreSentenciado
	 */
	public String getNombreSentenciado() {
		return nombreSentenciado;
	}
	
	/**
	 * Asigna el valor al campo nombreSentenciado.
	 * @param nombreSentenciado el valor nombreSentenciado a asignar
	 */
	public void setNombreSentenciado(String nombreSentenciado) {
		this.nombreSentenciado = nombreSentenciado;
	}
	
	/**
	 * Método de acceso al campo nus.
	 * @return El valor del campo nus
	 */
	public String getNus() {
		return nus;
	}
	
	/**
	 * Asigna el valor al campo nus.
	 * @param nus el valor nus a asignar
	 */
	public void setNus(String nus) {
		this.nus = nus;
	}
	
	/**
	 * Método de acceso al campo tipoSentencia.
	 * @return El valor del campo tipoSentencia
	 */
	public String getTipoSentencia() {
		return tipoSentencia;
	}
	
	/**
	 * Asigna el valor al campo tipoSentencia.
	 * @param tipoSentencia el valor tipoSentencia a asignar
	 */
	public void setTipoSentencia(String tipoSentencia) {
		this.tipoSentencia = tipoSentencia;
	}
	
	/**
	 * Método de acceso al campo fechaInicioPena.
	 * @return El valor del campo fechaInicioPena
	 */
	public String getFechaInicioPena() {
		return fechaInicioPena;
	}
	
	/**
	 * Asigna el valor al campo fechaInicioPena.
	 * @param fechaInicioPena el valor fechaInicioPena a asignar
	 */
	public void setFechaInicioPena(String fechaInicioPena) {
		this.fechaInicioPena = fechaInicioPena;
	}
	
	/**
	 * Método de acceso al campo fechaFinPena.
	 * @return El valor del campo fechaFinPena
	 */
	public String getFechaFinPena() {
		return fechaFinPena;
	}
	
	/**
	 * Asigna el valor al campo fechaFinPena.
	 * @param fechaFinPena el valor fechaFinPena a asignar
	 */
	public void setFechaFinPena(String fechaFinPena) {
		this.fechaFinPena = fechaFinPena;
	}
	
	/**
	 * Método de acceso al campo nombreCereso.
	 * @return El valor del campo nombreCereso
	 */
	public String getNombreCereso() {
		return nombreCereso;
	}
	
	/**
	 * Asigna el valor al campo nombreCereso.
	 * @param nombreCereso el valor nombreCereso a asignar
	 */
	public void setNombreCereso(String nombreCereso) {
		this.nombreCereso = nombreCereso;
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
	 * Método de acceso al campo actoBuenaConductaId.
	 * @return El valor del campo actoBuenaConductaId
	 */
	public long getActoBuenaConductaId() {
		return actoBuenaConductaId;
	}

	/**
	 * Asigna el valor al campo actoBuenaConductaId.
	 * @param actoBuenaConductaId el valor actoBuenaConductaId a asignar
	 */
	public void setActoBuenaConductaId(long actoBuenaConductaId) {
		this.actoBuenaConductaId = actoBuenaConductaId;
	}

	/**
	 * Método de acceso al campo puntosPorAcumular.
	 * @return El valor del campo puntosPorAcumular
	 */
	public long getPuntosPorAcumular() {
		return puntosPorAcumular;
	}
	
	/**
	 * Asigna el valor al campo puntosPorAcumular.
	 * @param puntosPorAcumular el valor puntosPorAcumular a asignar
	 */
	public void setPuntosPorAcumular(long puntosPorAcumular) {
		this.puntosPorAcumular = puntosPorAcumular;
	}
	
	/**
	 * Método de acceso al campo puntosAcumulados.
	 * @return El valor del campo puntosAcumulados
	 */
	public long getPuntosAcumulados() {
		return puntosAcumulados;
	}
	
	/**
	 * Asigna el valor al campo puntosAcumulados.
	 * @param puntosAcumulados el valor puntosAcumulados a asignar
	 */
	public void setPuntosAcumulados(long puntosAcumulados) {
		this.puntosAcumulados = puntosAcumulados;
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

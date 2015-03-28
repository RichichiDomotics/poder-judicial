/**
* Nombre del Programa : GenerarTurnoAtencionForm.java
* Autor               : Arturo Leon
* Compania            : Ultrasist
* Proyecto            : NSJP                    Fecha: 01/04/2011
* Marca de cambio     : N/A
* Descripcion General : Clase Form para capturar los datos de entranda en la vista de Generar turnos de atencion
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

import mx.gob.segob.nsjp.comun.enums.expediente.TipoTurno;
import mx.gob.segob.nsjp.web.base.form.GenericForm;

/**
* Clase Form para capturar los datos de entranda en la vista de Generar turnos de atencion.
*
* @version 1.0
* @author Arturo Leon
*/
public class GenerarTurnoAtencionForm extends GenericForm {
	private Long turnoId;
	private String numeroTurno;
	private int tipoTurno;
	private Boolean esUrgente;
	private String fichaAtencion;
	private String estado;
	/**
	 * @return obtiene generarTurno 
	 */
	/**
	 * @return the turnoId
	 */
	public Long getTurnoId() {
		return turnoId;
	}
	/**
	 * @param turnoId the turnoId to set
	 */
	public void setTurnoId(Long turnoId) {
		this.turnoId = turnoId;
	}
	/**
	 * @return the numeroTurno
	 */
	public String getNumeroTurno() {
		return numeroTurno;
	}
	/**
	 * @param numeroTurno the numeroTurno to set
	 */
	public void setNumeroTurno(String numeroTurno) {
		this.numeroTurno = numeroTurno;
	}
	/**
	 * @return the tipoTurno
	 */
	public int getTipoTurno() {
		return tipoTurno;
	}
	/**
	 * @param tipoTurno the tipoTurno to set
	 */
	public void setTipoTurno(int tipoTurno) {
		this.tipoTurno = tipoTurno;
	}
	/**
	 * @return the esUrgente
	 */
	public Boolean getEsUrgente() {
		return esUrgente;
	}
	/**
	 * @param esUrgente the esUrgente to set
	 */
	public void setEsUrgente(Boolean esUrgente) {
		this.esUrgente = esUrgente;
	}
	/**
	 * @return the fichaAtencion
	 */
	public String getFichaAtencion() {
		return fichaAtencion;
	}
	/**
	 * @param fichaAtencion the fichaAtencion to set
	 */
	public void setFichaAtencion(String fichaAtencion) {
		this.fichaAtencion = fichaAtencion;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getEstado() {
		return estado;
	}
	

}

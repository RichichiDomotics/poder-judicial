/**
* Nombre del Programa : EventoCitaDTO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 12 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Objeto de transferencia de EventoCita
* Programa Dependiente  :N/A
* Programa Subsecuente :N/A
* Cond. de ejecucion        :N/A
* Dias de ejecucion          :N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor                       :N/A
* Compania               :N/A
* Proyecto                 :N/A                                 Fecha: N/A
* Modificacion           :N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.dto.tarea;

import java.util.Date;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;

/**
 * Objeto de transferencia de EventoCita.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class EventoCitaDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4955771229809983894L;

	private Long eventoCitaId;
//    private AgendaFuncionario agendaFuncionario;
    private ValorDTO estatus;
    private String nombreEvento;
    private Date fechaInicioEvento;
    private Date fechaFinEvento;
    private String strFechaInicioEvento;
    private String strFechaFinEvento;
    private String horaInicioEvento;
    private String horaFinEvento;
    private String descripcionEvento;
    private String direccion;
    private Date fechaNotificacion;
    private ValorDTO tipoEvento;
    private TareaDTO tarea;
    private Boolean esAlertaAlarma;
    
	/**
	 * Método de acceso al campo eventoCitaId.
	 * @return El valor del campo eventoCitaId
	 */
	public Long getEventoCitaId() {
		return eventoCitaId;
	}
	/**
	 * Asigna el valor al campo eventoCitaId.
	 * @param eventoCitaId el valor eventoCitaId a asignar
	 */
	public void setEventoCitaId(Long eventoCitaId) {
		this.eventoCitaId = eventoCitaId;
	}
	/**
	 * Método de acceso al campo estatus.
	 * @return El valor del campo estatus
	 */
	public ValorDTO getEstatus() {
		return estatus;
	}
	/**
	 * Asigna el valor al campo estatus.
	 * @param estatus el valor estatus a asignar
	 */
	public void setEstatus(ValorDTO estatus) {
		this.estatus = estatus;
	}
	/**
	 * Método de acceso al campo nombreEvento.
	 * @return El valor del campo nombreEvento
	 */
	public String getNombreEvento() {
		return nombreEvento;
	}
	/**
	 * Asigna el valor al campo nombreEvento.
	 * @param nombreEvento el valor nombreEvento a asignar
	 */
	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}
	/**
	 * Método de acceso al campo fechaInicioEvento.
	 * @return El valor del campo fechaInicioEvento
	 */
	public Date getFechaInicioEvento() {
		return fechaInicioEvento;
	}
	/**
	 * Asigna el valor al campo fechaInicioEvento.
	 * @param fechaInicioEvento el valor fechaInicioEvento a asignar
	 */
	public void setFechaInicioEvento(Date fechaInicioEvento) {
		this.fechaInicioEvento = fechaInicioEvento;
	}
	/**
	 * Método de acceso al campo fechaFinEvento.
	 * @return El valor del campo fechaFinEvento
	 */
	public Date getFechaFinEvento() {
		return fechaFinEvento;
	}
	/**
	 * Asigna el valor al campo fechaFinEvento.
	 * @param fechaFinEvento el valor fechaFinEvento a asignar
	 */
	public void setFechaFinEvento(Date fechaFinEvento) {
		this.fechaFinEvento = fechaFinEvento;
	}
	/**
	 * Método de acceso al campo strFechaInicioEvento.
	 * @return El valor del campo strFechaInicioEvento
	 */
	public String getStrFechaInicioEvento() {
		return strFechaInicioEvento;
	}
	/**
	 * Asigna el valor al campo strFechaInicioEvento.
	 * @param strFechaInicioEvento el valor strFechaInicioEvento a asignar
	 */
	public void setStrFechaInicioEvento(String strFechaInicioEvento) {
		this.strFechaInicioEvento = strFechaInicioEvento;
	}
	/**
	 * Método de acceso al campo strFechaFinEvento.
	 * @return El valor del campo strFechaFinEvento
	 */
	public String getStrFechaFinEvento() {
		return strFechaFinEvento;
	}
	/**
	 * Asigna el valor al campo strFechaFinEvento.
	 * @param strFechaFinEvento el valor strFechaFin a asignar
	 */
	public void setStrFechaFinEvento(String strFechaFinEvento) {
		this.strFechaFinEvento = strFechaFinEvento;
	}
	/**
	 * Método de acceso al campo horaInicioEvento.
	 * @return El valor del campo horaInicioEvento
	 */
	public String getHoraInicioEvento() {
		return horaInicioEvento;
	}
	/**
	 * Asigna el valor al campo horaInicioEvento.
	 * @param horaInicioEvento el valor horaInicio a asignar
	 */
	public void setHoraInicioEvento(String horaInicioEvento) {
		this.horaInicioEvento = horaInicioEvento;
	}
	/**
	 * Método de acceso al campo horaFinEvento.
	 * @return El valor del campo horaFinEvento
	 */
	public String getHoraFinEvento() {
		return horaFinEvento;
	}
	/**
	 * Asigna el valor al campo horaFinEvento.
	 * @param horaFinEvento el valor horaFin a asignar
	 */
	public void setHoraFinEvento(String horaFinEvento) {
		this.horaFinEvento = horaFinEvento;
	}
	/**
	 * Método de acceso al campo descripcionEvento.
	 * @return El valor del campo descripcionEvento
	 */
	public String getDescripcionEvento() {
		return descripcionEvento;
	}
	/**
	 * Asigna el valor al campo descripcionEvento.
	 * @param descripcionEvento el valor descripcionEvento a asignar
	 */
	public void setDescripcionEvento(String descripcionEvento) {
		this.descripcionEvento = descripcionEvento;
	}
	/**
	 * Método de acceso al campo direccion.
	 * @return El valor del campo direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * Asigna el valor al campo direccion.
	 * @param direccion el valor direccion a asignar
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * Método de acceso al campo fechaNotificacion.
	 * @return El valor del campo fechaNotificacion
	 */
	public Date getFechaNotificacion() {
		return fechaNotificacion;
	}
	/**
	 * Asigna el valor al campo fechaNotificacion.
	 * @param fechaNotificacion el valor fechaNotificacion a asignar
	 */
	public void setFechaNotificacion(Date fechaNotificacion) {
		this.fechaNotificacion = fechaNotificacion;
	}
	/**
	 * @return the tipoEvento
	 */
	public ValorDTO getTipoEvento() {
		return tipoEvento;
	}
	/**
	 * @param tipoEvento the tipoEvento to set
	 */
	public void setTipoEvento(ValorDTO tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
	/**
	 * Establece el valor de la propiedad tarea
	 * @param tarea valo tarea a almacenar
	 */
	public void setTarea(TareaDTO tarea) {
		this.tarea = tarea;
	}
	/**
	 * Regresa el valor de la propiedad tarea
	 * @return the tarea
	 */
	public TareaDTO getTarea() {
		return tarea;
	}
	/**
	 * @return the esAlertaAlarma
	 */
	public Boolean getEsAlertaAlarma() {
		return esAlertaAlarma;
	}
	/**
	 * @param esAlertaAlarma the esAlertaAlarma to set
	 */
	public void setEsAlertaAlarma(Boolean esAlertaAlarma) {
		this.esAlertaAlarma = esAlertaAlarma;
	}
	
}

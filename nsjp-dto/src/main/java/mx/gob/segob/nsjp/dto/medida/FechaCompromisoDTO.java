/**
* Nombre del Programa : FechaCompromisoDTO.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 05/08/2011
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
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
package mx.gob.segob.nsjp.dto.medida;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto FechaCompromiso.
 * @version 1.0
 * @author GustavoBP
 *
 */
public class FechaCompromisoDTO {
	
	private Long fechaCompromisoId;
	private Date fechaCompromiso;
	private Date fechaCumplimiento;
	private String fechaCumplimientoString;
	private String horaCumplimientoString;
	private String descripcionCompromiso;
	private Double monto;
	private String observaciones;
	private List<FechaCompArchDigDTO> archivosDigital=new ArrayList<FechaCompArchDigDTO>();
	private CompromisoPeriodicoDTO compromisoPeriodico;
	/**
	 * Método de acceso al campo fechaCompromisoId.
	 * @return El valor del campo fechaCompromisoId
	 */
	public Long getFechaCompromisoId() {
		return fechaCompromisoId;
	}
	/**
	 * Asigna el valor al campo fechaCompromisoId.
	 * @param fechaCompromisoId el valor fechaCompromisoId a asignar
	 */
	public void setFechaCompromisoId(Long fechaCompromisoId) {
		this.fechaCompromisoId = fechaCompromisoId;
	}
	/**
	 * Método de acceso al campo fechaCompromiso.
	 * @return El valor del campo fechaCompromiso
	 */
	public Date getFechaCompromiso() {
		return fechaCompromiso;
	}
	/**
	 * Asigna el valor al campo fechaCompromiso.
	 * @param fechaCompromiso el valor fechaCompromiso a asignar
	 */
	public void setFechaCompromiso(Date fechaCompromiso) {
		this.fechaCompromiso = fechaCompromiso;
	}
	/**
	 * Método de acceso al campo fechaCumplimiento.
	 * @return El valor del campo fechaCumplimiento
	 */
	public Date getFechaCumplimiento() {
		return fechaCumplimiento;
	}
	/**
	 * Asigna el valor al campo fechaCumplimiento.
	 * @param fechaCumplimiento el valor fechaCumplimiento a asignar
	 */
	public void setFechaCumplimiento(Date fechaCumplimiento) {
		this.fechaCumplimiento = fechaCumplimiento;
	}
	/**
	 * Método de acceso al campo descripcionCompromiso.
	 * @return El valor del campo descripcionCompromiso
	 */
	public String getDescripcionCompromiso() {
		return descripcionCompromiso;
	}
	/**
	 * Asigna el valor al campo descripcionCompromiso.
	 * @param descripcionCompromiso el valor descripcionCompromiso a asignar
	 */
	public void setDescripcionCompromiso(String descripcionCompromiso) {
		this.descripcionCompromiso = descripcionCompromiso;
	}
	/**
	 * Método de acceso al campo monto.
	 * @return El valor del campo monto
	 */
	public Double getMonto() {
		return monto;
	}
	/**
	 * Asigna el valor al campo monto.
	 * @param monto el valor monto a asignar
	 */
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	/**
	 * Método de acceso al campo observaciones.
	 * @return El valor del campo observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}
	/**
	 * Asigna el valor al campo observaciones.
	 * @param observaciones el valor observaciones a asignar
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	/**
	 * Método de acceso al campo archivosDigital.
	 * @return El valor del campo archivosDigital
	 */
	public List<FechaCompArchDigDTO> getArchivosDigital() {
		return archivosDigital;
	}
	/**
	 * Asigna el valor al campo archivosDigital.
	 * @param archivosDigital el valor archivosDigital a asignar
	 */
	public void setArchivosDigital(List<FechaCompArchDigDTO> archivosDigital) {
		this.archivosDigital = archivosDigital;
	}
	/**
	 * Método de acceso al campo compromisoPeriodico.
	 * @return El valor del campo compromisoPeriodico
	 */
	public CompromisoPeriodicoDTO getCompromisoPeriodico() {
		return compromisoPeriodico;
	}
	/**
	 * Asigna el valor al campo compromisoPeriodico.
	 * @param compromisoPeriodico el valor compromisoPeriodico a asignar
	 */
	public void setCompromisoPeriodico(CompromisoPeriodicoDTO compromisoPeriodico) {
		this.compromisoPeriodico = compromisoPeriodico;
	}
	/**
	 * @return the fechaCumplimientoString
	 */
	public String getFechaCumplimientoString() {
		return fechaCumplimientoString;
	}
	/**
	 * @param fechaCumplimientoString the fechaCumplimientoString to set
	 */
	public void setFechaCumplimientoString(String fechaCumplimientoString) {
		this.fechaCumplimientoString = fechaCumplimientoString;
	}
	/**
	 * @return the horaCumplimientoString
	 */
	public String getHoraCumplimientoString() {
		return horaCumplimientoString;
	}
	/**
	 * @param horaCumplimientoString the horaCumplimientoString to set
	 */
	public void setHoraCumplimientoString(String horaCumplimientoString) {
		this.horaCumplimientoString = horaCumplimientoString;
	}
	
	
}

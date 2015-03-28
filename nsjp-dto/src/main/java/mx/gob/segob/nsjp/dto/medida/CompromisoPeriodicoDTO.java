/**
* Nombre del Programa : CompromisoPeriodicoDTO.java
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

import java.util.List;

import mx.gob.segob.nsjp.dto.institucion.JerarquiaOrganizacionalDTO;

/**
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto CompromisoPeriodico.
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public class CompromisoPeriodicoDTO {
	
	private Long compromisoPeriodicoId;
	private Double monto;
	private List<FechaCompromisoDTO> fechaCompromisos;
	private JerarquiaOrganizacionalDTO lugarPresentacion;
	private MedidaDTO medida;
	/**
	 * Método de acceso al campo compromisoPeriodicoId.
	 * @return El valor del campo compromisoPeriodicoId
	 */
	public Long getCompromisoPeriodicoId() {
		return compromisoPeriodicoId;
	}
	/**
	 * Asigna el valor al campo compromisoPeriodicoId.
	 * @param compromisoPeriodicoId el valor compromisoPeriodicoId a asignar
	 */
	public void setCompromisoPeriodicoId(Long compromisoPeriodicoId) {
		this.compromisoPeriodicoId = compromisoPeriodicoId;
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
	 * Método de acceso al campo fechaCompromisos.
	 * @return El valor del campo fechaCompromisos
	 */
	public List<FechaCompromisoDTO> getFechaCompromisos() {
		return fechaCompromisos;
	}
	/**
	 * Asigna el valor al campo fechaCompromisos.
	 * @param fechaCompromisos el valor fechaCompromisos a asignar
	 */
	public void setFechaCompromisos(List<FechaCompromisoDTO> fechaCompromisos) {
		this.fechaCompromisos = fechaCompromisos;
	}
	/**
	 * Método de acceso al campo lugarPresentacion.
	 * @return El valor del campo lugarPresentacion
	 */
	public JerarquiaOrganizacionalDTO getLugarPresentacion() {
		return lugarPresentacion;
	}
	/**
	 * Asigna el valor al campo lugarPresentacion.
	 * @param lugarPresentacion el valor lugarPresentacion a asignar
	 */
	public void setLugarPresentacion(JerarquiaOrganizacionalDTO lugarPresentacion) {
		this.lugarPresentacion = lugarPresentacion;
	}
	/**
	 * Método de acceso al campo medida.
	 * @return El valor del campo medida
	 */
	public MedidaDTO getMedida() {
		return medida;
	}
	/**
	 * Asigna el valor al campo medida.
	 * @param medida el valor medida a asignar
	 */
	public void setMedida(MedidaDTO medida) {
		this.medida = medida;
	} 
}

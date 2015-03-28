/**
* Nombre del Programa : BitacoraConsultaDTO.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 31/08/2011
* Marca de cambio        : N/A
* Descripcion General    : DTO para la transferencia de parametros de Bitacora Consulta entre la vista y servicios.
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
package mx.gob.segob.nsjp.dto.bitacora;

import java.util.Date;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * DTO para la transferencia de parametros de Bitacora Consulta entre la vista y
 * servicios.
 * 
 * @version 1.0
 * @author GustavoBP
 */
public class BitacoraConsultaDTO extends GenericDTO {
	
	private Long bitacoraConsultaId;
	private Date fechaConsulta;
	private Boolean esPermitida;
	
	private String numeroExpediente;
	private Long numeroExpedienteId;
	private UsuarioDTO usuario;

	/**
	 */
	public BitacoraConsultaDTO() {

	}
	
	/**
	 * @param bitacoraConsultaId
	 */
	public BitacoraConsultaDTO(Long bitacoraConsultaId) {
		this.bitacoraConsultaId = bitacoraConsultaId;
	}

	/**
	 * Método de acceso al campo bitacoraConsultaId.
	 * @return El valor del campo bitacoraConsultaId
	 */
	public Long getBitacoraConsultaId() {
		return bitacoraConsultaId;
	}

	/**
	 * Asigna el valor al campo bitacoraConsultaId.
	 * @param bitacoraConsultaId el valor bitacoraConsultaId a asignar
	 */
	public void setBitacoraConsultaId(Long bitacoraConsultaId) {
		this.bitacoraConsultaId = bitacoraConsultaId;
	}

	/**
	 * Método de acceso al campo fechaConsulta.
	 * @return El valor del campo fechaConsulta
	 */
	public Date getFechaConsulta() {
		return fechaConsulta;
	}

	/**
	 * Asigna el valor al campo fechaConsulta.
	 * @param fechaConsulta el valor fechaConsulta a asignar
	 */
	public void setFechaConsulta(Date fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}

	/**
	 * Método de acceso al campo esPermitida.
	 * @return El valor del campo esPermitida
	 */
	public Boolean getEsPermitida() {
		return esPermitida;
	}

	/**
	 * Asigna el valor al campo esPermitida.
	 * @param esPermitida el valor esPermitida a asignar
	 */
	public void setEsPermitida(Boolean esPermitida) {
		this.esPermitida = esPermitida;
	}

	/**
	 * Método de acceso al campo numeroExpediente.
	 * @return El valor del campo numeroExpediente
	 */
	public String getNumeroExpediente() {
		return numeroExpediente;
	}

	/**
	 * Asigna el valor al campo numeroExpediente.
	 * @param numeroExpediente el valor numeroExpediente a asignar
	 */
	public void setNumeroExpediente(String numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}

	/**
	 * Método de acceso al campo numeroExpedienteId.
	 * @return El valor del campo numeroExpedienteId
	 */
	public Long getNumeroExpedienteId() {
		return numeroExpedienteId;
	}

	/**
	 * Asigna el valor al campo numeroExpedienteId.
	 * @param numeroExpedienteId el valor numeroExpedienteId a asignar
	 */
	public void setNumeroExpedienteId(Long numeroExpedienteId) {
		this.numeroExpedienteId = numeroExpedienteId;
	}

	/**
	 * Método de acceso al campo usuario.
	 * @return El valor del campo usuario
	 */
	public UsuarioDTO getUsuario() {
		return usuario;
	}

	/**
	 * Asigna el valor al campo usuario.
	 * @param usuario el valor usuario a asignar
	 */
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
}

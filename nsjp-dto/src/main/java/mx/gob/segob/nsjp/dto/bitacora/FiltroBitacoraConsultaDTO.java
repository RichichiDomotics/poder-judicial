/**
* Nombre del Programa : FiltroBitacoraConsultaDTO.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 31/08/2011
* Marca de cambio        : N/A
* Descripcion General    : DTO para los filtros de busqueda de Bitacora Consulta
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

import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;

/**
 * DTO para los filtros de busqueda de Bitacora Consulta 
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public class FiltroBitacoraConsultaDTO {
	private String numeroExpediente;
	private Date fechaConsulta;
	private Date horaConsulta;
	private FuncionarioDTO funcionario;
	private Boolean esPermitida;

	public FiltroBitacoraConsultaDTO(){
	}

	/**
	 * @param numeroExpediente
	 * @param fechaConsulta
	 * @param horaConsulta
	 * @param funcionario
	 * @param esPermitida
	 */
	public FiltroBitacoraConsultaDTO(String numeroExpediente,
			Date fechaConsulta, Date horaConsulta, FuncionarioDTO funcionario,
			Boolean esPermitida) {
		super();
		this.numeroExpediente = numeroExpediente;
		this.fechaConsulta = fechaConsulta;
		this.horaConsulta = horaConsulta;
		this.funcionario = funcionario;
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
	 * Método de acceso al campo horaConsulta.
	 * @return El valor del campo horaConsulta
	 */
	public Date getHoraConsulta() {
		return horaConsulta;
	}

	/**
	 * Asigna el valor al campo horaConsulta.
	 * @param horaConsulta el valor horaConsulta a asignar
	 */
	public void setHoraConsulta(Date horaConsulta) {
		this.horaConsulta = horaConsulta;
	}

	/**
	 * Método de acceso al campo funcionario.
	 * @return El valor del campo funcionario
	 */
	public FuncionarioDTO getFuncionario() {
		return funcionario;
	}

	/**
	 * Asigna el valor al campo funcionario.
	 * @param funcionario el valor funcionario a asignar
	 */
	public void setFuncionario(FuncionarioDTO funcionario) {
		this.funcionario = funcionario;
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
}

/**
* Nombre del Programa : BitacoraConsulta.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 30/08/2011
* Marca de cambio        : N/A
* Descripcion General    : Entidad que permite registrar y consultar los accesos a los expedientes.
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
package mx.gob.segob.nsjp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 * Entidad que permite registrar y consultar los accesos a los expedientes
 * tanto para las que son permitidas como las que no lo son. Bitacora INQ.
 * @version 1.0
 * @author GustavoBP
 *
 */
@Entity
@Table(name = "BitacoraConsulta")
public class BitacoraConsulta implements java.io.Serializable {

	private Long bitacoraConsultaId;
	private Date fechaConsulta;
	private Boolean esPermitida;
	
	private NumeroExpediente numeroExpediente;
	private Usuario usuario;
	private Long numeroExpedienteID;
	
	public BitacoraConsulta(){
	}

	public BitacoraConsulta(Long bitacoraConsultaId){
		this.bitacoraConsultaId = bitacoraConsultaId;
	}
	 
	
	/**
	 * @param fechaConsulta
	 * @param esPermitida
	 * @param numeroExpediente
	 * @param usuario
	 */
	public BitacoraConsulta(Date fechaConsulta, Boolean esPermitida,
			NumeroExpediente numeroExpediente, Usuario usuario) {
		this.fechaConsulta = fechaConsulta;
		this.esPermitida = esPermitida;
		this.numeroExpediente = numeroExpediente;
		this.usuario = usuario;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BitacoraConsulta_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getBitacoraConsultaId() {
		return bitacoraConsultaId;
	}
	public void setBitacoraConsultaId(Long bitacoraConsultaId) {
		this.bitacoraConsultaId = bitacoraConsultaId;
	}
	
	@Column(name = "dFechaConsulta", nullable = false, length = 23)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	public Date getFechaConsulta() {
		return fechaConsulta;
	}
	public void setFechaConsulta(Date fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}
	
	@Column(name = "bEsPermitida", precision = 1, scale = 0)
	public Boolean getEsPermitida() {
		return esPermitida;
	}
	public void setEsPermitida(Boolean esPermitida) {
		this.esPermitida = esPermitida;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NumeroExpediente_id", nullable = false)
	public NumeroExpediente getNumeroExpediente() {
		return numeroExpediente;
	}
	
	public void setNumeroExpediente(NumeroExpediente numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Usuario_id", nullable = false)
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setNumeroExpedienteID(Long numeroExpedienteID) {
		this.numeroExpedienteID = numeroExpedienteID;
	}

	@Transient
	public Long getNumeroExpedienteID() {
		return numeroExpedienteID;
	}
	
}

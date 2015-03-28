/**
* Nombre del Programa : BitacoraMovimiento.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 30/08/2011
* Marca de cambio        : N/A
* Descripcion General    : Entidad que permite registrar Y consultar la bítacora de Ingreso, Anulación y Modificación (IAM) de Expedientes.
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

/**
 * Entidad que permite registrar Y consultar la bítacora de
 * Ingreso, Anulación y Modificación (IAM) de Expedientes.
 * @version 1.0
 * @author GustavoBP
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "BitacoraMovimiento")
public class BitacoraMovimiento implements java.io.Serializable {

	private Long bitacoraMovimientoId;
	private Date fechaMovimiento;
	private String grupoDatos;
	private String accion;
	private String campo;
	private String valorAnterior;
	private String valorNuevo;
	
	private NumeroExpediente numeroExpediente;
	private Usuario usuario;
//	private Elemento elemento;
	private Long elemento;
	private CategoriaElemento categoriaElemento;
	

	
	public BitacoraMovimiento(){
		
	}
	
	public BitacoraMovimiento(Long bitacoraMovimientoId){
		this.bitacoraMovimientoId= bitacoraMovimientoId;
	}
    /**
	 * @param fechaMovimiento
	 * @param grupoDatos
	 * @param accion
	 * @param campo
	 * @param valorAnterior
	 * @param valorNuevo
	 * @param numeroExpediente
	 * @param usuario
	 * @param elemento
	 * @param categoriaElemento
	 */
	public BitacoraMovimiento(Date fechaMovimiento, String grupoDatos,
			String accion, String campo, String valorAnterior,
			String valorNuevo, NumeroExpediente numeroExpediente,
			Usuario usuario, Long elemento,
			CategoriaElemento categoriaElemento) {
		super();
		this.fechaMovimiento = fechaMovimiento;
		this.grupoDatos = grupoDatos;
		this.accion = accion;
		this.campo = campo;
		this.valorAnterior = valorAnterior;
		this.valorNuevo = valorNuevo;
		this.numeroExpediente = numeroExpediente;
		this.usuario = usuario;
		this.elemento = elemento;
		this.categoriaElemento = categoriaElemento;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BitacoraMovimiento_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getBitacoraMovimientoId() {
		return bitacoraMovimientoId;
	}

	public void setBitacoraMovimientoId(Long bitacoraMovimientoId) {
		this.bitacoraMovimientoId = bitacoraMovimientoId;
	}

	@Column(name = "dFechaMovimiento", nullable = false, length = 23)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	public Date getFechaMovimiento() {
		return fechaMovimiento;
	}

	public void setFechaMovimiento(Date fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}

	@Column(name = "cGrupoDatos", length = 100)
	public String getGrupoDatos() {
		return grupoDatos;
	}

	public void setGrupoDatos(String grupoDatos) {
		this.grupoDatos = grupoDatos;
	}

	@Column(name = "cAccion", length = 10)
	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}
	
	@Column(name = "cCampo", length = 30)
	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}
	
	@Column(name = "cValorAnterior", length = 100)
	public String getValorAnterior() {
		return valorAnterior;
	}

	public void setValorAnterior(String valorAnterior) {
		this.valorAnterior = valorAnterior;
	}

	@Column(name = "cValorNuevo", length = 100)
	public String getValorNuevo() {
		return valorNuevo;
	}

	public void setValorNuevo(String valorNuevo) {
		this.valorNuevo = valorNuevo;
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

	//@ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "Elemento_id", nullable = false)
	public Long getElemento() {
		return elemento;
	}

	public void setElemento(Long elemento) {
		this.elemento = elemento;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CategoriaElemento_id", nullable = false)
	public CategoriaElemento getCategoriaElemento() {
		return categoriaElemento;
	}

	public void setCategoriaElemento(CategoriaElemento categoriaElemento) {
		this.categoriaElemento = categoriaElemento;
	}
}

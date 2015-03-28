/**
 * 
 */
package mx.gob.segob.nsjp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author adrian
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "CompromisoPeriodico")
public class CompromisoPeriodico implements Serializable {

	//Fields
	private Long compromisoPeriodicoId;
	private Double monto;
	private List<FechaCompromiso> fechaCompromisos;
	private JerarquiaOrganizacional lugarPresentacion;
	private Medida medida; 
	public CompromisoPeriodico(){
		super();
	}
	public CompromisoPeriodico(Long idCompromiso) {
		this.compromisoPeriodicoId=idCompromiso;
	}
	/**
	 * @return the monto
	 */
	@Column(name = "dcMonto", precision = 5)
	public Double getMonto() {
		return monto;
	}
	/**
	 * @param monto the monto to set
	 */
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	/**
	 * @return the fechaCompromisos
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "compromisoPeriodico")
	public List<FechaCompromiso> getFechaCompromisos() {
		return fechaCompromisos;
	}
	/**
	 * @param fechaCompromisos the fechaCompromisos to set
	 */
	public void setFechaCompromisos(List<FechaCompromiso> fechaCompromisos) {
		this.fechaCompromisos = fechaCompromisos;
	}
	/**
	 * @return the lugarPresentacion
	 */
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "JerarquiaOrganizacional_id", nullable=true)
	public JerarquiaOrganizacional getLugarPresentacion() {
		return lugarPresentacion;
	}
	/**
	 * @param lugarPresentacion the lugarPresentacion to set
	 */
	public void setLugarPresentacion(JerarquiaOrganizacional lugarPresentacion) {
		this.lugarPresentacion = lugarPresentacion;
	}
	/**
	 * @param compromisoPeriodicoId the compromisoPeriodicoId to set
	 */
	public void setCompromisoPeriodicoId(Long compromisoPeriodicoId) {
		this.compromisoPeriodicoId = compromisoPeriodicoId;
	}
	/**
	 * @return the compromisoPeriodicoId
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CompromisoPeriodico_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getCompromisoPeriodicoId() {
		return compromisoPeriodicoId;
	}
	/**
	 * Método de acceso al campo medida.
	 * @return El valor del campo medida
	 */

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "Medida_id")
	public Medida getMedida() {
		return medida;
	}
	/**
	 * Asigna el valor al campo medida.
	 * @param medida el valor medida a asignar
	 */
	public void setMedida(Medida medida) {
		this.medida = medida;
	}
	
	
}


/**
 * 
 */
package mx.gob.segob.nsjp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author adrian
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Alarma")
public class Alarma implements Serializable {
	
	private Long alarmaId;
	private Date fechaAlarma;
	private String motivo;
	private String folioEvento;
	private String datosAsociados;
	
	private List<Alerta> alertas;
	private Valor estatusAlarmaAlerta;
	private Valor tipoEventoAlarma;
	private Funcionario funcionario;
	
	
	/**
	 * 
	 */
	public Alarma() {
		super();
	}
	
	/**
	 * @param alarmaId
	 */
	public Alarma(Long alarmaId) {
		super();
		this.alarmaId = alarmaId;
	}

	/**
	 * @return the alarmaId
	 */
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "Alarma_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getAlarmaId() {
		return alarmaId;
	}
	/**
	 * @return the fechaAlarma
	 */
	@Column(name = "dFechaAlarma", nullable=false, length = 23)
	public Date getFechaAlarma() {
		return fechaAlarma;
	}
	/**
	 * @return the motivo
	 */
	@Column(name = "cMotivo", nullable = false, length = 200)
	public String getMotivo() {
		return motivo;
	}
	/**
	 * @return the folioEvento
	 */
	@Column(name = "cFolioEvento", length = 12)
	public String getFolioEvento() {
		return folioEvento;
	}
	/**
	 * @return the datosAsociados
	 */
	@Column(name = "cDatosAsociados", nullable = false, length = 300)
	public String getDatosAsociados() {
		return datosAsociados;
	}
	/**
	 * @return the estatusAlarmaAlerta
	 */
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EstatusAlarmaAlerta_val", nullable = false)
	public Valor getEstatusAlarmaAlerta() {
		return estatusAlarmaAlerta;
	}
	/**
	 * @return the tipoEventoAlarma
	 */
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoEvento_val", nullable = false)
	public Valor getTipoEventoAlarma() {
		return tipoEventoAlarma;
	}
	/**
	 * @return the funcionario
	 */
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iClaveFuncionario", nullable = false)
	public Funcionario getFuncionario() {
		return funcionario;
	}
	/**
	 * @param alarmaId the alarmaId to set
	 */
	public void setAlarmaId(Long alarmaId) {
		this.alarmaId = alarmaId;
	}
	/**
	 * @param fechaAlarma the fechaAlarma to set
	 */
	public void setFechaAlarma(Date fechaAlarma) {
		this.fechaAlarma = fechaAlarma;
	}
	/**
	 * @param motivo the motivo to set
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	/**
	 * @param folioEvento the folioEvento to set
	 */
	public void setFolioEvento(String folioEvento) {
		this.folioEvento = folioEvento;
	}
	/**
	 * @param datosAsociados the datosAsociados to set
	 */
	public void setDatosAsociados(String datosAsociados) {
		this.datosAsociados = datosAsociados;
	}
	/**
	 * @param estatusAlarmaAlerta the estatusAlarmaAlerta to set
	 */
	public void setEstatusAlarmaAlerta(Valor estatusAlarmaAlerta) {
		this.estatusAlarmaAlerta = estatusAlarmaAlerta;
	}
	/**
	 * @param tipoEventoAlarma the tipoEventoAlarma to set
	 */
	public void setTipoEventoAlarma(Valor tipoEventoAlarma) {
		this.tipoEventoAlarma = tipoEventoAlarma;
	}
	/**
	 * @param funcionario the funcionario to set
	 */
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	/**
	 * @param alertas the alertas to set
	 */
	public void setAlertas(List<Alerta> alertas) {
		this.alertas = alertas;
	}
	/**
	 * @return the alertas
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "alarma")
	public List<Alerta> getAlertas() {
		return alertas;
	}

	
}

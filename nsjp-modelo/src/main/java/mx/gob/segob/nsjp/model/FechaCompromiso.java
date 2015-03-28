/**
 * 
 */
package mx.gob.segob.nsjp.model;

import java.util.ArrayList;
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
@Table(name = "FechaCompromiso")
public class FechaCompromiso implements java.io.Serializable{
	
	//Fields
	private Long fechaCompromisoId;
	private Date fechaCompromiso;
	private Date fechaCumplimiento;
	private String descripcionCompromiso;
	private Double monto;
	private String observaciones;
	private List<FechaCompArchDig> archivosDigital=new ArrayList<FechaCompArchDig>();
	private CompromisoPeriodico compromisoPeriodico;
	
	
	public FechaCompromiso(Long fechaCompromisoId) {
		super();
		this.fechaCompromisoId = fechaCompromisoId;
	}
	public FechaCompromiso() {

	}
	/**
	 * @return the fechaCompromiso
	 */
	@Column(name = "dFechaCompromiso", length = 23)
	public Date getFechaCompromiso() {
		return fechaCompromiso;
	}
	/**
	 * @param fechaCompromiso the fechaCompromiso to set
	 */
	public void setFechaCompromiso(Date fechaCompromiso) {
		this.fechaCompromiso = fechaCompromiso;
	}
	/**
	 * @return the fechaCumplimiento
	 */
	@Column(name = "dFechaCumplimiento", length = 23)
	public Date getFechaCumplimiento() {
		return fechaCumplimiento;
	}
	/**
	 * @param fechaCumplimiento the fechaCumplimiento to set
	 */
	public void setFechaCumplimiento(Date fechaCumplimiento) {
		this.fechaCumplimiento = fechaCumplimiento;
	}
	/**
	 * @return the descripcionCompromiso
	 */
	@Column(name="cDescripcionCompromiso")
	public String getDescripcionCompromiso() {
		return descripcionCompromiso;
	}
	/**
	 * @param descripcionCompromiso the descripcionCompromiso to set
	 */
	public void setDescripcionCompromiso(String descripcionCompromiso) {
		this.descripcionCompromiso = descripcionCompromiso;
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
	 * @return the observaciones
	 */
	@Column(name="cObservaciones")
	public String getObservaciones() {
		return observaciones;
	}
	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	/**
	 * @return the compromisoPeriodico
	 */
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CompromisoPeriodico_id")
	public CompromisoPeriodico getCompromisoPeriodico() {
		return compromisoPeriodico;
	}
	/**
	 * @param compromisoPeriodico the compromisoPeriodico to set
	 */
	public void setCompromisoPeriodico(CompromisoPeriodico compromisoPeriodico) {
		this.compromisoPeriodico = compromisoPeriodico;
	}
	/**
	 * @param archivoDigital the archivoDigital to set
	 */
	public void setArchivosDigital(List<FechaCompArchDig> archivosDigital) {
		this.archivosDigital = archivosDigital;
	}
	/**
	 * @return the archivoDigital
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fechaCompromiso")
	public List<FechaCompArchDig> getArchivosDigital() {
		return archivosDigital;
	}
	/**
	 * @param fechaCompromisoId the fechaCompromisoId to set
	 */
	public void setFechaCompromisoId(Long fechaCompromisoId) {
		this.fechaCompromisoId = fechaCompromisoId;
	}
	/**
	 * @return the fechaCompromisoId
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FechaCompromiso_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getFechaCompromisoId() {
		return fechaCompromisoId;
	}
	
	

}
/**
 * 
 */
package mx.gob.segob.nsjp.model;

import java.io.Serializable;
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
import javax.persistence.Transient;

/**
 * @author adrian
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Arancel")
public class Arancel implements Serializable {
	
	//Fields
	private Long arancel_id;
	private Date fechaRegistro;
    private Date fechaInicio;
    private Date fechaFin;
    private Short horas;
    private Double monto;
    private Boolean esPagada;
    private Date fechaPago;
    
    private Valor clase_val;
    private NumeroExpediente numeroExpediente;
    private Funcionario funcionario;
    

    public Arancel(){
    	super();
    }
    
	/**
	 * @param arancel_id
	 * @param dFechaInicio
	 * @param dFechaFin
	 * @param iHoras
	 * @param dcMonto
	 * @param bEsPagada
	 * @param dFechaPago
	 * @param clase_val
	 * @param numeroExpediente_id
	 */
	public Arancel(Long arancel_id, Date dFechaInicio, Date dFechaFin,
			Short iHoras, Double dcMonto, Date dFechaPago,
			Valor clase_val, NumeroExpediente numeroExpediente, Funcionario funcionario) {
		super();
		this.arancel_id = arancel_id;
		this.fechaInicio = dFechaInicio;
		this.fechaFin = dFechaFin;
		this.horas = iHoras;
		this.monto = dcMonto;
		this.fechaPago = dFechaPago;
		this.clase_val = clase_val;
		this.numeroExpediente = numeroExpediente;
		this.funcionario=funcionario;
	}
	/**
	 * @return the arancel_id
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Arancel_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getArancel_id() {
		return arancel_id;
	}
	/**
	 * @return the dFechaInicio
	 */
	@Column(name = "dFechaInicio", nullable = false, length = 23)
	public Date getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @return the dFechaFin
	 */
	@Column(name = "dFechaFin", nullable = false, length = 23)
	public Date getFechaFin() {
		return fechaFin;
	}
	/**
	 * @return the iHoras
	 */
	@Column(name = "iHoras", nullable = false, precision = 4, scale = 0)
	public Short getHoras() {
		return horas;
	}
	/**
	 * @return the dcMonto
	 */
	@Column(name = "dcMonto", nullable = false, precision = 5)
	public Double getMonto() {
		return monto;
	}
	/**
	 * @return the bEsPagada
	 */
	@Transient
	public Boolean getEsPagada() {
		return esPagada;
	}
	/**
	 * @return the dFechaPago
	 */
	@Column(name = "dFechaPago", length = 23)
	public Date getFechaPago() {
		return fechaPago;
	}
	/**
	 * @return the clase_val
	 */
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Clase_val", nullable=false)
	public Valor getClase_val() {
		return clase_val;
	}
	/**
	 * @return the numeroExpediente_id
	 */
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NumeroExpediente_id", nullable=false)
	public NumeroExpediente getNumeroExpediente() {
		return numeroExpediente;
	}
	/**
	 * @return the funcionario
	 */
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iClaveFuncionario", nullable=false)
	public Funcionario getFuncionario() {
		return funcionario;
	}
	/**
	 * @param arancel_id the arancel_id to set
	 */
	public void setArancel_id(Long arancel_id) {
		this.arancel_id = arancel_id;
	}
	/**
	 * @param dFechaInicio the dFechaInicio to set
	 */
	public void setFechaInicio(Date dFechaInicio) {
		this.fechaInicio = dFechaInicio;
	}
	/**
	 * @param dFechaFin the dFechaFin to set
	 */
	public void setFechaFin(Date dFechaFin) {
		this.fechaFin = dFechaFin;
	}
	/**
	 * @param iHoras the iHoras to set
	 */
	public void setHoras(Short iHoras) {
		this.horas = iHoras;
	}
	/**
	 * @param dcMonto the dcMonto to set
	 */
	public void setMonto(Double dcMonto) {
		this.monto = dcMonto;
	}
	/**
	 * @param bEsPagada the bEsPagada to set
	 */
	public void setEsPagada(Boolean bEsPagada) {
		this.esPagada = bEsPagada;
	}
	/**
	 * @param dFechaPago the dFechaPago to set
	 */
	public void setFechaPago(Date dFechaPago) {
		this.fechaPago = dFechaPago;
	}
	/**
	 * @param clase_val the clase_val to set
	 */
	public void setClase_val(Valor clase_val) {
		this.clase_val = clase_val;
	}
	/**
	 * @param numeroExpediente_id the numeroExpediente_id to set
	 */
	public void setNumeroExpediente(NumeroExpediente numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}

	/**
	 * @param funcionario the funcionario to set
	 */
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

    /**
     * Método de acceso al campo fechaRegistro.
     * @return El valor del campo fechaRegistro
     */
	@Column(name = "dFechaRegistro", nullable = false, length = 23)
	public Date getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Asigna el valor al campo fechaRegistro.
     * @param fechaRegistro el valor fechaRegistro a asignar
     */
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

	
    
    

}

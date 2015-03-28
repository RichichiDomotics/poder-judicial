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
import javax.persistence.Temporal;

/**
 *
 * @author Jacob Lobaco
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Eslabon")
public class Eslabon implements Serializable {

    private Long eslabonId;
    private String quienEntrega;
    private String quienRecibe;
    private Date fechaEntrega;
    private Date fechaRecibe;
    private String observacion;
    private Integer numeroEslabon;
    
    private Date fechaInicioPrestamo;
    private Date fechaFinPrestamo;
    
    private Evidencia evidencia;
    private Valor tipoEslabon;
    
    private Funcionario funcionarioRecibe;
    private Funcionario funcionarioEntrega;
    private Documento documento;
    private String ubicacionFisica;
    private String posicion;
    
    private Valor tipoEslabonDeRecepcion;
    private String institucionQueEntrega;
    private String institucionQueRecibe;
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Eslabon_id", unique = true, nullable = false,
    precision = 18, scale = 0)
    public Long getEslabonId() {
        return eslabonId;
    }

    public void setEslabonId(Long eslabonId) {
        this.eslabonId = eslabonId;
    }

    @Column(name = "cQuienEntrega", length = 100)
    public String getQuienEntrega() {
        return quienEntrega;
    }

    public void setQuienEntrega(String quienEntrega) {
        this.quienEntrega = quienEntrega;
    }

    @Column(name = "cQuienRecibe", length = 100)
    public String getQuienRecibe() {
        return quienRecibe;
    }

    public void setQuienRecibe(String quienRecibe) {
        this.quienRecibe = quienRecibe;
    }

    @Column(name = "dFechaEntrega", nullable = false, length = 23)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    @Column(name = "dFechaRecibe", nullable = false, length = 23)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getFechaRecibe() {
        return fechaRecibe;
    }

    public void setFechaRecibe(Date fecheRecibe) {
        this.fechaRecibe = fecheRecibe;
    }

    @Column(name = "cObservacion", length = 300)
    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Column(name = "iNumeroEslabon")
    public Integer getNumeroEslabon() {
        return numeroEslabon;
    }

    
    public void setNumeroEslabon(Integer numeroEslabon) {
        this.numeroEslabon = numeroEslabon;
    }

    @Column(name = "dFechaInicioPrestamo", nullable = false, length = 23)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getFechaInicioPrestamo() {
		return fechaInicioPrestamo;
	}

	public void setFechaInicioPrestamo(Date fechaInicioPrestamo) {
		this.fechaInicioPrestamo = fechaInicioPrestamo;
	}

    @Column(name = "dFechaFinPrestamo", nullable = false, length = 23)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
	public Date getFechaFinPrestamo() {
		return fechaFinPrestamo;
	}

	public void setFechaFinPrestamo(Date fechaFinPrestamo) {
		this.fechaFinPrestamo = fechaFinPrestamo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Evidencia_id", nullable = false)
    public Evidencia getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(Evidencia evidencia) {
        this.evidencia = evidencia;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TipoEslabon_val", nullable = false)
    public Valor getTipoEslabon() {
        return tipoEslabon;
    }

    public void setTipoEslabon(Valor valorBytipoEslabonVal) {
        this.tipoEslabon = valorBytipoEslabonVal;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iClaveFuncionarioRecibe")
    public Funcionario getFuncionarioRecibe() {
        return this.funcionarioRecibe;
    }

    public void setFuncionarioRecibe(
            Funcionario funcionarioByIclaveFuncionarioRecibe) {
        this.funcionarioRecibe = funcionarioByIclaveFuncionarioRecibe;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iClaveFuncionarioEntrega")
    public Funcionario getFuncionarioEntrega() {
        return this.funcionarioEntrega;
    }

    public void setFuncionarioEntrega(
            Funcionario funcionarioByIclaveFuncionarioEntrega) {
        this.funcionarioEntrega = funcionarioByIclaveFuncionarioEntrega;
    }

	/**
	 * @return the documento
	 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Documento_id")
	public Documento getDocumento() {
		return documento;
	}

	/**
	 * @return the ubicacionFisica
	 */
    @Column(name = "cUbicacionFisica", length = 300)
	public String getUbicacionFisica() {
		return ubicacionFisica;
	}

	/**
	 * @return the posicion
	 */
    @Column(name = "cPosicion", length = 10)
	public String getPosicion() {
		return posicion;
	}

	/**
	 * @param documento the documento to set
	 */
	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	/**
	 * @param ubicacionFisica the ubicacionFisica to set
	 */
	public void setUbicacionFisica(String ubicacionFisica) {
		this.ubicacionFisica = ubicacionFisica;
	}

	/**
	 * @param posicion the posicion to set
	 */
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	/**
	 * Método de acceso al campo tipoEslabonDeRecepcion.
	 * @return El valor del campo tipoEslabonDeRecepcion
	 */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TipoEslabonDeRecepcion_val", nullable = false)
	public Valor getTipoEslabonDeRecepcion() {
		return tipoEslabonDeRecepcion;
	}

	/**
	 * Asigna el valor al campo tipoEslabonDeRecepcion.
	 * @param tipoEslabonDeRecepcion el valor tipoEslabonDeRecepcion a asignar
	 */
	public void setTipoEslabonDeRecepcion(Valor tipoEslabonDeRecepcion) {
		this.tipoEslabonDeRecepcion = tipoEslabonDeRecepcion;
	}

	/**
	 * Método de acceso al campo institucionQueEntrega.
	 * @return El valor del campo institucionQueEntrega
	 */
    @Column(name = "cInstitucionQueEntrega", length = 100, nullable = false)
	public String getInstitucionQueEntrega() {
		return institucionQueEntrega;
	}

	/**
	 * Asigna el valor al campo institucionQueEntrega.
	 * @param institucionQueEntrega el valor institucionQueEntrega a asignar
	 */
	public void setInstitucionQueEntrega(String institucionQueEntrega) {
		this.institucionQueEntrega = institucionQueEntrega;
	}

	/**
	 * Método de acceso al campo institucionQueRecibe.
	 * @return El valor del campo institucionQueRecibe
	 */
    @Column(name = "cInstitucionQueRecibe", length = 100, nullable = false)
	public String getInstitucionQueRecibe() {
		return institucionQueRecibe;
	}

	/**
	 * Asigna el valor al campo institucionQueRecibe.
	 * @param institucionQueRecibe el valor institucionQueRecibe a asignar
	 */
	public void setInstitucionQueRecibe(String institucionQueRecibe) {
		this.institucionQueRecibe = institucionQueRecibe;
	}
	
	
}

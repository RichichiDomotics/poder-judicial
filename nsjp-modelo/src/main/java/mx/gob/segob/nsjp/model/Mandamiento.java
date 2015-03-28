package mx.gob.segob.nsjp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * Mandamiento entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Mandamiento")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "Mandamiento_id")
public class Mandamiento extends Documento{

	// Fields
	private Date fechaInicial;
	private Date fechaFinal;
	
	//Relaciones
	private Valor tipoMandamiento;
	private Valor tipoSentencia;
	private Medida medida;
	private Resolutivo resolutivo;
	private Valor estatus;

	private Domicilio domicilio;
	private Involucrado involucrado;
	
	// Constructors

	/** default constructor */
	public Mandamiento() {
	}

	public Mandamiento(Long mandamientoId) {
		super(mandamientoId);
	}
	
	/**
	 * @param documentoId
	 * @param fechaInicial
	 * @param fechaFinal
	 * @param tipoMandamiento
	 */
	public Mandamiento(Long documentoId, Date fechaInicial, Date fechaFinal,
			Valor tipoMandamiento) {
		super(documentoId);
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
		this.tipoMandamiento = tipoMandamiento;
	}

	@Column(name = "dFechaInicial", nullable = true, length = 23)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	@Column(name = "dFechaFinal", nullable = true, length = 23)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TipoMandamiento_val")
	public Valor getTipoMandamiento() {
		return this.tipoMandamiento;
	}

	public void setTipoMandamiento(Valor valor) {
		this.tipoMandamiento = valor;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TipoSentencia_val")
	public Valor getTipoSentencia() {
		return tipoSentencia;
	}

	public void setTipoSentencia(Valor tipoSentencia) {
		this.tipoSentencia = tipoSentencia;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Medida_id")
	public Medida getMedida() {
		return this.medida;
	}

	public void setMedida(Medida medida) {
		this.medida = medida;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Resolutivo_id")
	public Resolutivo getResolutivo() {
		return this.resolutivo;
	}

	public void setResolutivo(Resolutivo resolutivo) {
		this.resolutivo = resolutivo;
	}


    /**
     * Método de acceso al campo estatus.
     * @return El valor del campo estatus
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Estatus_val")
    public Valor getEstatus() {
        return estatus;
    }


    /**
     * Asigna el valor al campo estatus.
     * @param estatus el valor estatus a asignar
     */
    public void setEstatus(Valor estatus) {
        this.estatus = estatus;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Domicilio_id")
	public Domicilio getDomicilio() {
		return domicilio;
	}


	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Involucrado_id")
	public Involucrado getInvolucrado() {
		return involucrado;
	}


	public void setInvolucrado(Involucrado involucrado) {
		this.involucrado = involucrado;
	}
}
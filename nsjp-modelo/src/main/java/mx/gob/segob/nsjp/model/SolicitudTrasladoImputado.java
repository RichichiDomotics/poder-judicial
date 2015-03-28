package mx.gob.segob.nsjp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * SolicitudTrasladoImputado entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SolicitudTrasladoImputado")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "SolicitudTrasladoImputado_id")
public class SolicitudTrasladoImputado extends Solicitud {

    // Fields
    private Date fechaTraslado;
    private Double tiempo;
    
    //Relaciones
    private Involucrado involucrado;
    @Deprecated
    private Audiencia audiencia;
    private Sitio sitioOrigen;
    private Sitio sitioDestino;
    
    
    // Constructors

    /** default constructor */
    public SolicitudTrasladoImputado() {
    }
    
	@Column(name = "dFechaTraslado", nullable=false, length = 23)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getFechaTraslado() {
		return fechaTraslado;
	}


	public void setFechaTraslado(Date fechaTraslado) {
		this.fechaTraslado = fechaTraslado;
	}

	@Column(name = "dcTiempo", precision = 5)
	public Double getTiempo() {
		return tiempo;
	}


	public void setTiempo(Double tiempo) {
		this.tiempo = tiempo;
	}


	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Involucrado_id", nullable = false)
    public Involucrado getInvolucrado() {
        return this.involucrado;
    }

    public void setInvolucrado(Involucrado involucrado) {
        this.involucrado = involucrado;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Audiencia_id", nullable = true)
    public Audiencia getAudiencia() {
        return this.audiencia;
    }

    public void setAudiencia(Audiencia audiencia) {
        this.audiencia = audiencia;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SitioOrigen_id", nullable = true)
	public Sitio getSitioOrigen() {
		return sitioOrigen;
	}

	public void setSitioOrigen(Sitio sitioOrigen) {
		this.sitioOrigen = sitioOrigen;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SitioDestino_id", nullable = true)
	public Sitio getSitioDestino() {
		return sitioDestino;
	}

	public void setSitioDestino(Sitio sitioDestino) {
		this.sitioDestino = sitioDestino;
	}

}
package mx.gob.segob.nsjp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * ExpedienteAdscrito entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ExpedienteAdscrito")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "ExpedienteAdscrito_id")
public class ExpedienteAdscrito extends Expediente{

	// Fields

	private String relacionImputadoSolicitante;
	private String lugarDondeEstaElDetenido;
	private Date fechaDeDetencion;

	// Constructors

	/** default constructor */
	public ExpedienteAdscrito() {
	}


	// Property accessors


	@Column(name = "cRelacionImputadoSolicitante", length = 30)
	public String getRelacionImputadoSolicitante() {
		return this.relacionImputadoSolicitante;
	}

	public void setRelacionImputadoSolicitante(
			String relacionImputadoSolicitante) {
		this.relacionImputadoSolicitante = relacionImputadoSolicitante;
	}

	@Column(name = "cLugarDondeEstaElDetenido", length = 100)
	public String getLugarDondeEstaElDetenido() {
		return this.lugarDondeEstaElDetenido;
	}

	public void setLugarDondeEstaElDetenido(String lugarDondeEstaElDetenido) {
		this.lugarDondeEstaElDetenido = lugarDondeEstaElDetenido;
	}

	@Column(name = "dFechaDeDetencion", nullable = false, length = 23)
	public Date getFechaDeDetencion() {
		return this.fechaDeDetencion;
	}

	public void setFechaDeDetencion(Date fechaDeDetencion) {
		this.fechaDeDetencion = fechaDeDetencion;
	}

}
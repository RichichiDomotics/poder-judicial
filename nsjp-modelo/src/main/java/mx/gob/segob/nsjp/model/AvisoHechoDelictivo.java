/**
 * 
 */
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

/**
 * @author adrian
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "AvisoHechoDelictivo")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "AvisoHechoDelictivo_id")
public class AvisoHechoDelictivo extends Notificacion {

	private Date fechaAtencion;
	private Hecho hecho;
	private CatDelito catDelito;
	private Implicado implicado;
	private Valor motivoRechazo;

	/**
	 * @return the hecho
	 */
	@OneToOne
	@JoinColumn(name = "hecho_id")
	public Hecho getHecho() {
		return hecho;
	}

	/**
	 * @param hecho the hecho to set
	 */
	public void setHecho(Hecho hecho) {
		this.hecho = hecho;
	}
	/**
	 * @return the fechaAtencion
	 */
	@Column(name = "dFechaAtencion", length = 23, nullable = true)
	public Date getFechaAtencion() {
		return fechaAtencion;
	}

	/**
	 * @param fechaAtencion the fechaAtencion to set
	 */
	public void setFechaAtencion(Date fechaAtencion) {
		this.fechaAtencion = fechaAtencion;
	}

	/**
	 * @param catDelito the catDelito to set
	 */
	public void setCatDelito(CatDelito catDelito) {
		this.catDelito = catDelito;
	}

	/**
	 * @return the catDelito
	 */
	@OneToOne
	@JoinColumn(name = "catDelito_id")
	public CatDelito getCatDelito() {
		return catDelito;
	}

	/**
	 * @return the involucrado
	 */
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Implicado_id", nullable = true)
	public Implicado getImplicado() {
		return implicado;
	}

	/**
	 * @param involucrado the involucrado to set
	 */
	public void setImplicado(Implicado implicado) {
		this.implicado = implicado;
	}

	/**
	 * @return the motivoRechazo
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "motivoRechazo_val")
	public Valor getMotivoRechazo() {
		return motivoRechazo;
	}

	/**
	 * @param motivoRechazo the motivoRechazo to set
	 */
	public void setMotivoRechazo(Valor motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}
	
	
}

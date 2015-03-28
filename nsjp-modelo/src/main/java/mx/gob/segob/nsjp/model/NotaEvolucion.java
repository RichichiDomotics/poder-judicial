package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "NotaEvolucion")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "NotaEvolucion_id")
public class NotaEvolucion extends Sesion {

	private String seguimiento;
	private String objetivo;
	private String analisisSesion;
	private String planteamientoTerap;
	private String obsFaltaInteres;
	private Involucrado victima;

	public NotaEvolucion(Long idObjeto) {
		super.setSesionId(idObjeto);
	}

	public NotaEvolucion(String a_seguimiento, String a_objetivo,
			String a_analisisSesion, String a_planteamientoTerap,
			String a_obsFaltaInteres, Involucrado a_victima) {
		this.setSeguimiento(a_seguimiento);
		this.setObjetivo(a_objetivo);
		this.setAnalisisSesion(a_analisisSesion);
		this.setPlanteamientoTerap(a_planteamientoTerap);
		this.setObsFaltaInteres(a_obsFaltaInteres);
		this.setVictima(a_victima);

	}

	public NotaEvolucion() {
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}
    @Column(name = "cObjetivo")
	public String getObjetivo() {
		return objetivo;
	}

	public void setSeguimiento(String seguimiento) {
		this.seguimiento = seguimiento;
	}

	@Column(name = "cSeguimiento")
	public String getSeguimiento() {
		return seguimiento;
	}

	public void setAnalisisSesion(String analisisSesion) {
		this.analisisSesion = analisisSesion;
	}

	@Column(name = "cAnalisisSesion")
	public String getAnalisisSesion() {
		return analisisSesion;
	}

	public void setPlanteamientoTerap(String planteamientoTerap) {
		this.planteamientoTerap = planteamientoTerap;
	}

	@Column(name = "cPlanteamientoTerap")
	public String getPlanteamientoTerap() {
		return planteamientoTerap;
	}

	public void setObsFaltaInteres(String obsFaltaInteres) {
		this.obsFaltaInteres = obsFaltaInteres;
	}

	@Column(name = "cObsFaltaInteres")
	public String getObsFaltaInteres() {
		return obsFaltaInteres;
	}

	public void setVictima(Involucrado victima) {
		this.victima = victima;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Involucrado_id", nullable = false)
	public Involucrado getVictima() {
		return victima;
	}

}

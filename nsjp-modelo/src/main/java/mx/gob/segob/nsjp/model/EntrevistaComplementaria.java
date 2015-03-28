package mx.gob.segob.nsjp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "EntrevistaComplementaria")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "EntrevistaComplementaria_id")
public class EntrevistaComplementaria extends Sesion {

	private String motivoConsulta;
	private String concienciaProblema;
	private String impresionDiagnostico;
	private String hipotesisFamilia;
	private Set<Familiar> familiares = new HashSet<Familiar>(0);

	public EntrevistaComplementaria(Long sesionId) {
		super.setSesionId(sesionId);
	}

	public EntrevistaComplementaria() {
	}

	public void setMotivoConsulta(String motivoConsulta) {
		this.motivoConsulta = motivoConsulta;
	}
	
	@Column(name = "cMotivoConsulta")
	public String getMotivoConsulta() {
		return motivoConsulta;
	}

	public void setConcienciaProblema(String concienciaProblema) {
		this.concienciaProblema = concienciaProblema;
	}

	@Column(name = "cConcienciaProblema")
	public String getConcienciaProblema() {
		return concienciaProblema;
	}

	public void setImpresionDiagnostico(String impresionDiagnostico) {
		this.impresionDiagnostico = impresionDiagnostico;
	}

	@Column(name = "cImpresionDiagnostico")
	public String getImpresionDiagnostico() {
		return impresionDiagnostico;
	}

	public void setHipotesisFamilia(String hipotesisFamilia) {
		this.hipotesisFamilia = hipotesisFamilia;
	}
	
	@Column(name = "cHipotesisFamilia")
	public String getHipotesisFamilia() {
		return hipotesisFamilia;
	}

	public void setFamiliares(Set<Familiar> familiares) {
		this.familiares = familiares;
	}

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "entrevistaComplementaria")
	public Set<Familiar> getFamiliares() {
		return familiares;
	}

}

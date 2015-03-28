package mx.gob.segob.nsjp.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import mx.gob.segob.nsjp.comun.enums.evidencia.EstatusEvidencia;

/**
 * Evidencia entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Evidencia")
public class Evidencia implements java.io.Serializable {

    // Fields
    private Long evidenciaId;
    private Objeto objeto;
    private CadenaDeCustodia cadenaDeCustodia;
    private Long numeroEvidencia;
    private String descripcion = new String("-");
    private Date fechaLevantamiento;
    private String origenEvidencia;
    private String codigoBarras;
    private Set<AudienciaEvidencia> audienciaEvidencias = new HashSet<AudienciaEvidencia>(
            0);
    private Set<Eslabon> eslabones = new HashSet<Eslabon>(0);
    private Funcionario funcionario;
    private Valor estatus = new Valor(EstatusEvidencia.BAJA.getValorId());
    private Valor destinoLegal;
    private Funcionario funcionarioBaja;
    private String motivoBaja;
    // Constructors

    /** default constructor */
    public Evidencia() {
    }

    /** Minimal constructor */
    public Evidencia(Long evidenciaId) {
        this.evidenciaId = evidenciaId;
    }

    /** full constructor */
    public Evidencia(Long evidenciaId, Objeto objeto,
            CadenaDeCustodia cadenaDeCustodia, Long inumeroEvidencia,
            String cdescripcion, Date dfechaLevantamiento,
            String corigenEvidencia, String ccodigoBarras) {
        this.evidenciaId = evidenciaId;
        this.objeto = objeto;
        this.cadenaDeCustodia = cadenaDeCustodia;
        this.numeroEvidencia = inumeroEvidencia;
        this.descripcion = cdescripcion;
        this.fechaLevantamiento = dfechaLevantamiento;
        this.origenEvidencia = corigenEvidencia;
        this.codigoBarras = ccodigoBarras;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Evidencia_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getEvidenciaId() {
        return this.evidenciaId;
    }

    public void setEvidenciaId(Long evidenciaId) {
        this.evidenciaId = evidenciaId;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Objeto_id", nullable = false, unique = true)
    public Objeto getObjeto() {
        return this.objeto;
    }

    public void setObjeto(Objeto objeto) {
        this.objeto = objeto;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CadenaDeCustodia_id", nullable = false)
    public CadenaDeCustodia getCadenaDeCustodia() {
        return this.cadenaDeCustodia;
    }

    public void setCadenaDeCustodia(CadenaDeCustodia cadenaDeCustodia) {
        this.cadenaDeCustodia = cadenaDeCustodia;
    }

    @Column(name = "iNumeroEvidencia", nullable = false, precision = 18, scale = 0)
    public Long getNumeroEvidencia() {
        return this.numeroEvidencia;
    }

    public void setNumeroEvidencia(Long inumeroEvidencia) {
        this.numeroEvidencia = inumeroEvidencia;
    }

    @Column(name = "cDescripcion", nullable = false, length = 200)
    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String cdescripcion) {
        this.descripcion = cdescripcion;
    }

    @Column(name = "dFechaLevantamiento", nullable = false, length = 23)
//    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getFechaLevantamiento() {
        return this.fechaLevantamiento;
    }

    public void setFechaLevantamiento(Date dfechaLevantamiento) {
        this.fechaLevantamiento = dfechaLevantamiento;
    }

    @Column(name = "cOrigenEvidencia", nullable = false, length = 200)
    public String getOrigenEvidencia() {
        return this.origenEvidencia;
    }

    public void setOrigenEvidencia(String corigenEvidencia) {
        this.origenEvidencia = corigenEvidencia;
    }

    @Column(name = "cCodigoBarras", nullable = true, length = 100)
    public String getCodigoBarras() {
        return this.codigoBarras;
    }

    public void setCodigoBarras(String ccodigoBarras) {
        this.codigoBarras = ccodigoBarras;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "evidencia")
    public Set<AudienciaEvidencia> getAudienciaEvidencias() {
        return this.audienciaEvidencias;
    }

    public void setAudienciaEvidencias(
            Set<AudienciaEvidencia> audienciaEvidencias) {
        this.audienciaEvidencias = audienciaEvidencias;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "evidencia")
    public Set<Eslabon> getEslabones() {
        return eslabones;
    }

    public void setEslabones(Set<Eslabon> eslabones) {
        this.eslabones = eslabones;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Funcionario_id")
    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

	public void setEstatus(Valor estatus) {
		this.estatus = estatus;
	}

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Estatus_val", nullable = true)
	public Valor getEstatus() {
		return estatus;
	}

	/**
	 * @param destinoLegal the destinoLegal to set
	 */
	public void setDestinoLegal(Valor destinoLegal) {
		this.destinoLegal = destinoLegal;
	}

	/**
	 * @return the destinoLegal
	 */
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DestinoLegal_val", nullable = true)
	public Valor getDestinoLegal() {
		return destinoLegal;
	}

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "Funcionario_baja_id", nullable = true)
    public Funcionario getFuncionarioBaja() {
        return funcionarioBaja;
    }

    public void setFuncionarioBaja(Funcionario funcionarioBaja) {
        this.funcionarioBaja = funcionarioBaja;
    }

    @Column(name="cMotivoBaja", length=300, nullable=true)
    public String getMotivoBaja() {
        return motivoBaja;
    }

    public void setMotivoBaja(String motivoBaja) {
        this.motivoBaja = motivoBaja;
    }
}

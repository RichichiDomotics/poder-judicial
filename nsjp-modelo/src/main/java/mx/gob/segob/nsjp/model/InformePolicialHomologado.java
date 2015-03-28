package mx.gob.segob.nsjp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 * InformePolicialHomologado entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "InformePolicialHomologado", uniqueConstraints = @UniqueConstraint(columnNames = "Expediente_id"))
public class InformePolicialHomologado implements java.io.Serializable {

    // Fields

    /**
	 * 
	 */
	private static final long serialVersionUID = -8581028226278151914L;
	private Long informePolicialHomologadoId;
    private Hecho hecho;
    private Funcionario funcionarioElabora;
    private Funcionario funcionarioDestinatario;
    private Expediente expediente;
    private Valor tipoParticipacion;
    private Date fechaCaptura;
    private String objetivosGenerales;
    private Long numeroOficio;
    private Long anio;
    private Long folioIPH;
    private String numEcoTransporte;
    private Operativo operativo;
    private Set<TurnoLaboralIph> turnoLaboralIphs = new HashSet<TurnoLaboralIph>(
            0);
    private Set<Multimedia> multimedias = new HashSet<Multimedia>(0);
    // FIXME Se emplea los Involucrados del Expediente
    private Set<InvolucradoIph> involucradoIphs = new HashSet<InvolucradoIph>(0);
    // relaciones con objetos
    private Set<ObjetoAsegurado> objetoAsegurados = new HashSet<ObjetoAsegurado>(
            0);
    private Set<MedioTransporte> medioTransportes = new HashSet<MedioTransporte>(
            0);
    private Set<ArmaExplosivoInvolucrado> armaExplosivoInvolucrados = new HashSet<ArmaExplosivoInvolucrado>(
            0);
    // delitos o faltas admisnitrativas
    private Set<FaltaAdministrativaIph> faltaAdministrativaIphs = new HashSet<FaltaAdministrativaIph>(
            0);
    private Set<DelitoIph> delitoIphs = new HashSet<DelitoIph>(0);
    private Boolean esDelito;
    
    private List<Involucrado> involucradosExp=new ArrayList<Involucrado>();
    private String asunto;
    

	// Constructors
	/** default constructor */
    public InformePolicialHomologado() {
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InformePolicialHomologado_id", unique = true, precision = 18, scale = 0)
    public Long getInformePolicialHomologadoId() {
        return this.informePolicialHomologadoId;
    }

    public void setInformePolicialHomologadoId(Long informePolicialHomologadoId) {
        this.informePolicialHomologadoId = informePolicialHomologadoId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Hecho_id")
    public Hecho getHecho() {
        return this.hecho;
    }

    public void setHecho(Hecho hecho) {
        this.hecho = hecho;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iClaveFuncionarioElabora", nullable = false)
    public Funcionario getFuncionarioElabora() {
        return this.funcionarioElabora;
    }

    public void setFuncionarioElabora(
            Funcionario funcionarioByIclaveFuncionarioElabora) {
        this.funcionarioElabora = funcionarioByIclaveFuncionarioElabora;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iClaveFuncionarioDestinatario")
    public Funcionario getFuncionarioDestinatario() {
        return this.funcionarioDestinatario;
    }

    public void setFuncionarioDestinatario(
            Funcionario funcionarioByIclaveFuncionarioDestinatario) {
        this.funcionarioDestinatario = funcionarioByIclaveFuncionarioDestinatario;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Expediente_id", nullable = false, unique = true)
    public Expediente getExpediente() {
        return this.expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoParticipacion_val")
    public Valor getTipoParticipacion() {
        return this.tipoParticipacion;
    }

    public void setTipoParticipacion(Valor valor) {
        this.tipoParticipacion = valor;
    }

    @Column(name = "dFechaCaptura", nullable = false, length = 23)
    public Date getFechaCaptura() {
        return this.fechaCaptura;
    }

    public void setFechaCaptura(Date dfechaCaptura) {
        this.fechaCaptura = dfechaCaptura;
    }

    @Column(name = "cObjetivosGenerales")
    public String getObjetivosGenerales() {
        return this.objetivosGenerales;
    }

    public void setObjetivosGenerales(String cobjetivosGenerales) {
        this.objetivosGenerales = cobjetivosGenerales;
    }

    @Column(name = "iNumeroOficio", precision = 18, scale = 0)
    public Long getNumeroOficio() {
        return this.numeroOficio;
    }

    public void setNumeroOficio(Long inumeroOficio) {
        this.numeroOficio = inumeroOficio;
    }

    @Column(name = "iAnio", nullable = false, precision = 18, scale = 0)
    public Long getAnio() {
        return this.anio;
    }

    public void setAnio(Long ianio) {
        this.anio = ianio;
    }

    @Column(name = "iFolioIPH", nullable = false, precision = 18, scale = 0)
    public Long getFolioIPH() {
        return this.folioIPH;
    }

    public void setFolioIPH(Long ifolioIph) {
        this.folioIPH = ifolioIph;
    }

    /**
     * Método de acceso al campo operativo.
     * 
     * @return El valor del campo operativo
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "informePolicialHomologado")
    public Operativo getOperativo() {
        return operativo;
    }

    /**
     * Asigna el valor al campo operativo.
     * 
     * @param operativo
     *            el valor operativo a asignar
     */
    public void setOperativo(Operativo operativo) {
        this.operativo = operativo;
    }

    /**
     * Método de acceso al campo numEcoTransporte.
     * 
     * @return El valor del campo numEcoTransporte
     */
    @Column(name = "cNumEcoTransporte", nullable = true, length = 100)
    public String getNumEcoTransporte() {
        return numEcoTransporte;
    }

    /**
     * Asigna el valor al campo numEcoTransporte.
     * 
     * @param numEcoTransporte
     *            el valor numEcoTransporte a asignar
     */
    public void setNumEcoTransporte(String numEcoTransporte) {
        this.numEcoTransporte = numEcoTransporte;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "informePolicialHomologado")
    public Set<TurnoLaboralIph> getTurnoLaboralIphs() {
        return this.turnoLaboralIphs;
    }

    public void setTurnoLaboralIphs(Set<TurnoLaboralIph> turnoLaboralIphs) {
        this.turnoLaboralIphs = turnoLaboralIphs;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "informePolicialHomologado")
    public Set<Multimedia> getMultimedias() {
        return this.multimedias;
    }

    public void setMultimedias(Set<Multimedia> multimedias) {
        this.multimedias = multimedias;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "informePolicialHomologado")
    public Set<InvolucradoIph> getInvolucradoIphs() {
        return this.involucradoIphs;
    }

    public void setInvolucradoIphs(Set<InvolucradoIph> involucradoIphs) {
        this.involucradoIphs = involucradoIphs;
    }


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "informePolicialHomologado")
    public Set<ObjetoAsegurado> getObjetoAsegurados() {
        return this.objetoAsegurados;
    }

    public void setObjetoAsegurados(Set<ObjetoAsegurado> objetoAsegurados) {
        this.objetoAsegurados = objetoAsegurados;
    }
    @Transient
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "informePolicialHomologado")
    public Set<MedioTransporte> getMedioTransportes() {
        return this.medioTransportes;
    }

    public void setMedioTransportes(Set<MedioTransporte> medioTransportes) {
        this.medioTransportes = medioTransportes;
    }

    @Transient
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "informePolicialHomologado")
    public Set<ArmaExplosivoInvolucrado> getArmaExplosivoInvolucrados() {
        return this.armaExplosivoInvolucrados;
    }

    public void setArmaExplosivoInvolucrados(
            Set<ArmaExplosivoInvolucrado> armaExplosivoInvolucrados) {
        this.armaExplosivoInvolucrados = armaExplosivoInvolucrados;
    }
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "informePolicialHomologado")
    public Set<FaltaAdministrativaIph> getFaltaAdministrativaIphs() {
        return this.faltaAdministrativaIphs;
    }

    public void setFaltaAdministrativaIphs(
            Set<FaltaAdministrativaIph> faltaAdministrativaIphs) {
        this.faltaAdministrativaIphs = faltaAdministrativaIphs;
    }
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "informePolicialHomologado")
    public Set<DelitoIph> getDelitoIphs() {
        return this.delitoIphs;
    }

    public void setDelitoIphs(Set<DelitoIph> delitoIphs) {
        this.delitoIphs = delitoIphs;
    }

    /**
     * Método de acceso al campo esDelito.
     * @return El valor del campo esDelito
     */
    @Column(name = "bEsDelito", precision = 1, scale = 0)
    public Boolean getEsDelito() {
        return esDelito;
    }

    /**
     * Asigna el valor al campo esDelito.
     * @param esDelito el valor esDelito a asignar
     */
    public void setEsDelito(Boolean esDelito) {
        this.esDelito = esDelito;
    }

	public void setInvolucradosExp(List<Involucrado> involucradosExp) {
		this.involucradosExp = involucradosExp;
	}

	@Transient
	public List<Involucrado> getInvolucradosExp() {
		return involucradosExp;
	}

	/**
	 * @param asunto the asunto to set
	 */
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	/**
	 * @return the asunto
	 */
	@Column(name = "cAsunto", nullable = true, length = 150)
	public String getAsunto() {
		return asunto;
	}
}

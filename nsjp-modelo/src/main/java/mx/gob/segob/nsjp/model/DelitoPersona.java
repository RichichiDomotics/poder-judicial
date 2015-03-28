package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * DelitoPersona entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "DelitoPersona")
public class DelitoPersona implements java.io.Serializable {

	// Fields
	private Long delitoPersonaId;
	private Valor bienTutelado;
	private Valor formaParticipacion;
	private Delito delito;
	private Involucrado victima;
	private Involucrado probableResponsable;
    private Boolean esActivo;
    private Long catDelitoClasificacionId;
    private Long catDelitoLugarId;
    private Long catDelitoModalidadId;
    private Long catDelitoModusId;
    private Long catDelitoCausaId;


	// Constructors

	/** default constructor */
	public DelitoPersona() {
	}

	/** minimal constructor */
	public DelitoPersona(Long delitoPersonaId,
			Valor valorByFormaParticipacionVal, Delito delito,
			Involucrado involucradoByProbableResponsableId,
			Long catDelitoClasificacionId,
		    Long catDelitoLugarId,
		    Long catDelitoModalidadId,
		    Long catDelitoModusId,
		    Long catDelitoCausaId) {
		this.delitoPersonaId = delitoPersonaId;
		this.formaParticipacion = valorByFormaParticipacionVal;
		this.delito = delito;
		this.probableResponsable = involucradoByProbableResponsableId;
		this.catDelitoClasificacionId = catDelitoClasificacionId;
		this.catDelitoLugarId = catDelitoLugarId;
		this.catDelitoModalidadId = catDelitoModalidadId;
		this.catDelitoModusId = catDelitoModusId;
		this.catDelitoCausaId = catDelitoCausaId;
	}

	/** full constructor */
	public DelitoPersona(Long delitoPersonaId, Valor valorByBienTuteladoVal,
			Valor valorByFormaParticipacionVal, Delito delito,
			Involucrado involucradoByVictimaId,
			Involucrado involucradoByProbableResponsableId,
			Long catDelitoClasificacionId,
		    Long catDelitoLugarId,
		    Long catDelitoModalidadId,
		    Long catDelitoModusId,
		    Long catDelitoCausaId) {
		this.delitoPersonaId = delitoPersonaId;
		this.bienTutelado = valorByBienTuteladoVal;
		this.formaParticipacion = valorByFormaParticipacionVal;
		this.delito = delito;
		this.victima = involucradoByVictimaId;
		this.probableResponsable = involucradoByProbableResponsableId;
		this.catDelitoClasificacionId = catDelitoClasificacionId;
		this.catDelitoLugarId = catDelitoLugarId;
		this.catDelitoModalidadId = catDelitoModalidadId;
		this.catDelitoModusId = catDelitoModusId;
		this.catDelitoCausaId = catDelitoCausaId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "DelitoPersona_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getDelitoPersonaId() {
		return this.delitoPersonaId;
	}

	public void setDelitoPersonaId(Long delitoPersonaId) {
		this.delitoPersonaId = delitoPersonaId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BienTutelado_val")
	public Valor getBienTutelado() {
		return this.bienTutelado;
	}

	public void setBienTutelado(Valor valorByBienTuteladoVal) {
		this.bienTutelado = valorByBienTuteladoVal;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FormaParticipacion_val")
	public Valor getFormaParticipacion() {
		return this.formaParticipacion;
	}

	public void setFormaParticipacion(
			Valor valorByFormaParticipacionVal) {
		this.formaParticipacion = valorByFormaParticipacionVal;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Delito_id", nullable = false)
	public Delito getDelito() {
		return this.delito;
	}

	public void setDelito(Delito delito) {
		this.delito = delito;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Victima_id")
	public Involucrado getVictima() {
		return this.victima;
	}

	public void setVictima(Involucrado involucradoByVictimaId) {
		this.victima = involucradoByVictimaId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ProbableResponsable_id", nullable = false)
	public Involucrado getProbableResponsable() {
		return this.probableResponsable;
	}

	public void setProbableResponsable(
			Involucrado involucradoByProbableResponsableId) {
		this.probableResponsable = involucradoByProbableResponsableId;
	}
	
	/**
	 * Método de acceso al campo esActivo.
	 * @return El valor del campo esActivo
	 */
    @Column(name = "bEsActivo", precision = 1, scale = 0)
	public Boolean getEsActivo() {
		return esActivo;
	}

	/**
	 * Asigna el valor al campo esActivo.
	 * @param esActivo el valor esActivo a asignar
	 */
	public void setEsActivo(Boolean esActivo) {
		this.esActivo = esActivo;
	}

	@Column(name = "catDelitoClasificacion_id")
	public Long getCatDelitoClasificacionId() {
		return catDelitoClasificacionId;
	}

	public void setCatDelitoClasificacionId(Long catDelitoClasificacionId) {
		this.catDelitoClasificacionId = catDelitoClasificacionId;
	}

	@Column(name = "catDelitoLugar_id")
	public Long getCatDelitoLugarId() {
		return catDelitoLugarId;
	}

	public void setCatDelitoLugarId(Long catDelitoLugarId) {
		this.catDelitoLugarId = catDelitoLugarId;
	}

	@Column(name = "catDelitoModalidad_id")
	public Long getCatDelitoModalidadId() {
		return catDelitoModalidadId;
	}

	public void setCatDelitoModalidadId(Long catDelitoModalidadId) {
		this.catDelitoModalidadId = catDelitoModalidadId;
	}

	@Column(name = "catDelitoModus_id")
	public Long getCatDelitoModusId() {
		return catDelitoModusId;
	}

	public void setCatDelitoModusId(Long catDelitoModusId) {
		this.catDelitoModusId = catDelitoModusId;
	}

	@Column(name = "catDelitoCausa_id")
	public Long getCatDelitoCausaId() {
		return catDelitoCausaId;
	}

	public void setCatDelitoCausaId(Long catDelitoCausaId) {
		this.catDelitoCausaId = catDelitoCausaId;
	}
}
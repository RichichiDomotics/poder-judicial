package mx.gob.segob.nsjp.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * MedioTransporte entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MedioTransporte")
public class MedioTransporte implements java.io.Serializable {

	// Fields

	private MedioTransporteId id;
	private Objeto objeto;
	private InformePolicialHomologado informePolicialHomologado;
	private String origen="___";
	private String destino="___";
	private String nombreEmpresa="___";
	private String descripcion;
	private String conductor;
	private Valor situacionMedioTransporte;

	// Constructors

	/** default constructor */
	public MedioTransporte() {
	}

	/** minimal constructor */
	public MedioTransporte(MedioTransporteId id, Objeto objeto,
			InformePolicialHomologado informePolicialHomologado) {
		this.id = id;
		this.objeto = objeto;
		this.informePolicialHomologado = informePolicialHomologado;
	}

	/** full constructor */
	public MedioTransporte(MedioTransporteId id, Objeto objeto,
			InformePolicialHomologado informePolicialHomologado,
			String corigen, String cdestino, String cnombreEmpresa,
			String cdescripcion, String cconductor) {
		this.id = id;
		this.objeto = objeto;
		this.informePolicialHomologado = informePolicialHomologado;
		this.origen = corigen;
		this.destino = cdestino;
		this.nombreEmpresa = cnombreEmpresa;
		this.descripcion = cdescripcion;
		this.conductor = cconductor;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "informePolicialHomologadoId", column = @Column(name = "InformePolicialHomologado_id", nullable = false, precision = 18, scale = 0)),
			@AttributeOverride(name = "objetoId", column = @Column(name = "Objeto_id", nullable = false, precision = 18, scale = 0)) })
	public MedioTransporteId getId() {
		return this.id;
	}

	public void setId(MedioTransporteId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Objeto_id", nullable = false, insertable = false, updatable = false)
	public Objeto getObjeto() {
		return this.objeto;
	}

	public void setObjeto(Objeto objeto) {
		this.objeto = objeto;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "InformePolicialHomologado_id", nullable = false, insertable = false, updatable = false)
	public InformePolicialHomologado getInformePolicialHomologado() {
		return this.informePolicialHomologado;
	}

	public void setInformePolicialHomologado(
			InformePolicialHomologado informePolicialHomologado) {
		this.informePolicialHomologado = informePolicialHomologado;
	}

	@Column(name = "cOrigen", length = 300)
	public String getOrigen() {
		return this.origen;
	}

	public void setOrigen(String corigen) {
		this.origen = corigen;
	}

	@Column(name = "cDestino", length = 300)
	public String getDestino() {
		return this.destino;
	}

	public void setDestino(String cdestino) {
		this.destino = cdestino;
	}

	@Column(name = "cNombreEmpresa", length = 100)
	public String getNombreEmpresa() {
		return this.nombreEmpresa;
	}

	public void setNombreEmpresa(String cnombreEmpresa) {
		this.nombreEmpresa = cnombreEmpresa;
	}

	@Column(name = "cDescripcion", length = 300)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String cdescripcion) {
		this.descripcion = cdescripcion;
	}

	@Column(name = "cConductor", length = 100)
	public String getConductor() {
		return this.conductor;
	}

	public void setConductor(String cconductor) {
		this.conductor = cconductor;
	}

    /**
     * Método de acceso al campo situacionMedioTransporte.
     * @return El valor del campo situacionMedioTransporte
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SituacionMedioTransporte_val")
    public Valor getSituacionMedioTransporte() {
        return situacionMedioTransporte;
    }

    /**
     * Asigna el valor al campo situacionMedioTransporte.
     * @param situacionMedioTransporte el valor situacionMedioTransporte a asignar
     */
    public void setSituacionMedioTransporte(Valor situacionMedioTransporte) {
        this.situacionMedioTransporte = situacionMedioTransporte;
    }

}
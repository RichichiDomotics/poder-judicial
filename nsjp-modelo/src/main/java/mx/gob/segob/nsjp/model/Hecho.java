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

/**
 * Hecho entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Hecho")
public class Hecho implements java.io.Serializable {

    // Fields

    private Long hechoId;
    private Tiempo tiempo;
    private Expediente expediente;
    private Lugar lugar;
    private String descNarrativa;
    private Set<InformePolicialHomologado> informePolicialHomologados = new HashSet<InformePolicialHomologado>(
            0);    
    private Date fechaDeArribo;
    /**
     * Relación con la institución donde se narraron los hechos.
     */
    private ConfInstitucion narrado;

    private AvisoHechoDelictivo avisoHechoDelictivo = null;

    // Constructors

    /** default constructor */
    public Hecho() {
    }

    /** minimal constructor */
    public Hecho(Long hechoId, Tiempo tiempo, Expediente expediente, Lugar lugar) {
        this.hechoId = hechoId;
        this.tiempo = tiempo;
        this.expediente = expediente;
        this.lugar = lugar;
    }

    /** full constructor */
    public Hecho(Long hechoId, Tiempo tiempo, Expediente expediente,
            Lugar lugar, String cdescNarrativa) {
        this.hechoId = hechoId;
        this.tiempo = tiempo;
        this.expediente = expediente;
        this.lugar = lugar;
        this.descNarrativa = cdescNarrativa;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Hecho_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getHechoId() {
        return this.hechoId;
    }

    public void setHechoId(Long hechoId) {
        this.hechoId = hechoId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Tiempo_id", nullable = true)
    public Tiempo getTiempo() {
        return this.tiempo;
    }

    public void setTiempo(Tiempo tiempo) {
        this.tiempo = tiempo;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Expediente_id", unique = true, nullable = false)
    public Expediente getExpediente() {
        return this.expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Lugar_id", nullable = true)
    public Lugar getLugar() {
        return this.lugar;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    @Column(name = "cDescNarrativa")
    public String getDescNarrativa() {
        return this.descNarrativa;
    }

    public void setDescNarrativa(String cdescNarrativa) {
        this.descNarrativa = cdescNarrativa;
    }

    /**
     * Método de acceso al campo narrado.
     * 
     * @return El valor del campo narrado
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ConfInstitucion_id")
    public ConfInstitucion getNarrado() {
        return narrado;
    }

    /**
     * Asigna el valor al campo narrado.
     * 
     * @param narrado
     *            el valor narrado a asignar
     */
    public void setNarrado(ConfInstitucion narrado) {
        this.narrado = narrado;
    }
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "hecho")
    public Set<InformePolicialHomologado> getInformePolicialHomologados() {
        return this.informePolicialHomologados;
    }

    public void setInformePolicialHomologados(
            Set<InformePolicialHomologado> informePolicialHomologados) {
        this.informePolicialHomologados = informePolicialHomologados;
    }

    /**
     * Método de acceso al campo avisoHechoDelictivo.
     * @return El valor del campo avisoHechoDelictivo
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "hecho")
    public AvisoHechoDelictivo getAvisoHechoDelictivo() {
        return avisoHechoDelictivo;
    }

    /**
     * Asigna el valor al campo avisoHechoDelictivo.
     * @param avisoHechoDelictivo el valor avisoHechoDelictivo a asignar
     */
    public void setAvisoHechoDelictivo(AvisoHechoDelictivo avisoHechoDelictivo) {
        this.avisoHechoDelictivo = avisoHechoDelictivo;
    }
    
	@Column(name = "dFechaDeArribo", length = 23)
	public Date getFechaDeArribo() {
		return fechaDeArribo;
	}
	
	public void setFechaDeArribo(Date fechaDeArribo) {
		this.fechaDeArribo = fechaDeArribo;
	}

}
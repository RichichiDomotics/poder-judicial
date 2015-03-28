package mx.gob.segob.nsjp.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entidad MediaFiliacion.<br>
 * Los valores en <code>Null</code> representan <b>desconocido</b>.
 * 
 * @author vaguirre
 */
@Entity
@Table(name = "MediaFiliacion")
@Inheritance(strategy = InheritanceType.JOINED)
public class MediaFiliacion implements java.io.Serializable {

    // Fields
    private Long mediaFiliacionId;
    private Involucrado involucrado;
    private SeniaParticular seniaParticular; 
    
    private Double estatura;
    private Double peso;
    private String perfil;
    /**
     * <ul>
     * <li> <code>Null</code>: desconocido</li>
     * <li><code>True</code> : Si</li>
     * <li><code>False</code>: No</li>
     * </ul>
     */
    private Boolean tieneBarba;
    /**
     * <ul>
     * <li> <code>Null</code>: desconocido</li>
     * <li><code>True</code> : Si</li>
     * <li><code>False</code>: No</li>
     * </ul>
     */
    private Boolean tieneBigote;
    /**
     * <ul>
     * <li> <code>Null</code>: desconocido</li>
     * <li><code>True</code> : Si</li>
     * <li><code>False</code>: No</li>
     * </ul>
     */
    private Boolean usaLentes;
    
    private String factorRH;
    
    private Valor tipoSangre;    
    private Valor formaOreja;
    private Valor tamanioCeja;
    private Valor tamanioOjo;
    private Valor formaCeja;
    private Valor anchoNariz;
    private Valor espesorLabioInf;
    private Valor orejaTamanio;
    private Valor cabelloImplantacion;
    private Valor colorOjos;
    private Valor formaOjos;
    private Valor frenteAltura;
    private Valor raizNariz;
    private Valor formaMenton;
    private Valor calvicieTipo;
    private Valor inclinacionMenton;
    private Valor cabelloCantidad;
    private Valor alturaNasoLabial;
    private Valor baseNariz;
    private Valor dorsoNariz;
	private Valor colorCabello;
    private Valor labioComisuras;
    private Valor helixPosterior;
    private Valor tamanioBoca;
    private Valor labiosProminencia;
    private Valor tez;
    private Valor tipoCara;
    private Valor formaCabello;
    private Valor implantacionCeja;
    private Valor tipoMenton;
    private Valor orejaLobDimension;
    private Valor direccionCeja;
    private Valor espesorLabioSup;
    private Valor frenteAncho;
    private Valor helixContorno;
    private Valor alturaNariz;
    private Valor orejaLobParticularidad;
    private Valor helixAdherencia;
    private Valor orejaLobAdherencia;
    private Valor helixSuperior;
    private Valor frenteInclinacion;
    private Valor helixOriginal;
    private Valor orejaLobContorno;
    private Valor complexion;

    // Constructors

    /** default constructor */
    public MediaFiliacion() {
    }

    /** minimal constructor */
    public MediaFiliacion(Long mediaFiliacionId, Involucrado involucrado) {
        this.mediaFiliacionId = mediaFiliacionId;
        this.involucrado = involucrado;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MediaFiliacion_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getMediaFiliacionId() {
        return this.mediaFiliacionId;
    }

    public void setMediaFiliacionId(Long mediaFiliacionId) {
        this.mediaFiliacionId = mediaFiliacionId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FormaOreja_val")
    public Valor getFormaOreja() {
        return this.formaOreja;
    }

    public void setFormaOreja(Valor valorByFormaOrejaVal) {
        this.formaOreja = valorByFormaOrejaVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TamanioCeja_val")
    public Valor getTamanioCeja() {
        return this.tamanioCeja;
    }

    public void setTamanioCeja(Valor valorByTamanioCejaVal) {
        this.tamanioCeja = valorByTamanioCejaVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TamanioOjo_val")
    public Valor getTamanioOjo() {
        return this.tamanioOjo;
    }

    public void setTamanioOjo(Valor valorByTamanioOjoVal) {
        this.tamanioOjo = valorByTamanioOjoVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FormaCeja_val")
    public Valor getFormaCeja() {
        return this.formaCeja;
    }

    public void setFormaCeja(Valor valorByFormaCejaVal) {
        this.formaCeja = valorByFormaCejaVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AnchoNariz_val")
    public Valor getAnchoNariz() {
        return this.anchoNariz;
    }

    public void setAnchoNariz(Valor valorByAnchoNarizVal) {
        this.anchoNariz = valorByAnchoNarizVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EspesorLabioInf_val")
    public Valor getEspesorLabioInf() {
        return this.espesorLabioInf;
    }

    public void setEspesorLabioInf(Valor valorByEspesorLabioInfVal) {
        this.espesorLabioInf = valorByEspesorLabioInfVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrejaTamanio_val")
    public Valor getOrejaTamanio() {
        return this.orejaTamanio;
    }

    public void setOrejaTamanio(Valor valorByOrejaTamanioVal) {
        this.orejaTamanio = valorByOrejaTamanioVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CabelloImplantacion_val")
    public Valor getCabelloImplantacion() {
        return this.cabelloImplantacion;
    }

    public void setCabelloImplantacion(Valor valorByCabelloImplantacionVal) {
        this.cabelloImplantacion = valorByCabelloImplantacionVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Involucrado_id", nullable = false)
    public Involucrado getInvolucrado() {
        return this.involucrado;
    }

    public void setInvolucrado(Involucrado involucrado) {
        this.involucrado = involucrado;
    }
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "mediaFiliacion")
    public SeniaParticular getSeniaParticular() {
		return seniaParticular;
	}

	public void setSeniaParticular(SeniaParticular seniaParticular) {
		this.seniaParticular = seniaParticular;
	}

	/**
     * @return
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ColorOjos_val")
    public Valor getColorOjos() {
        return this.colorOjos;
    }

    public void setColorOjos(
            Valor valorByTamanioOjoParticularidadVal) {
        this.colorOjos = valorByTamanioOjoParticularidadVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FrenteAltura_val")
    public Valor getFrenteAltura() {
        return this.frenteAltura;
    }

    public void setFrenteAltura(Valor valorByFrenteAlturaVal) {
        this.frenteAltura = valorByFrenteAlturaVal;
    }
    /**
     * @return
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FormaOjos_val")
    public Valor getFormaOjos() {
        return this.formaOjos;
    }

    public void setFormaOjos(Valor valorByTamanioOjoDimensionVal) {
        this.formaOjos = valorByTamanioOjoDimensionVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RaizNariz_val")
    public Valor getRaizNariz() {
        return this.raizNariz;
    }

    public void setRaizNariz(Valor valorByRaizNarizVal) {
        this.raizNariz = valorByRaizNarizVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FormaMenton_val")
    public Valor getFormaMenton() {
        return this.formaMenton;
    }

    public void setFormaMenton(Valor valorByFormaMentonVal) {
        this.formaMenton = valorByFormaMentonVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CalvicieTipo_val")
    public Valor getCalvicieTipo() {
        return this.calvicieTipo;
    }

    public void setCalvicieTipo(Valor valorByCalvicieTipoVal) {
        this.calvicieTipo = valorByCalvicieTipoVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "InclinacionMenton_val")
    public Valor getInclinacionMenton() {
        return this.inclinacionMenton;
    }

    public void setInclinacionMenton(Valor valorByInclinacionMentonVal) {
        this.inclinacionMenton = valorByInclinacionMentonVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CabelloCantidad_val")
    public Valor getCabelloCantidad() {
        return this.cabelloCantidad;
    }

    public void setCabelloCantidad(Valor valorByCabelloCantidadVal) {
        this.cabelloCantidad = valorByCabelloCantidadVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AlturaNasoLabial_val")
    public Valor getAlturaNasoLabial() {
        return this.alturaNasoLabial;
    }

    public void setAlturaNasoLabial(Valor valorByAlturaNasoLabialVal) {
        this.alturaNasoLabial = valorByAlturaNasoLabialVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BaseNariz_val")
    public Valor getBaseNariz() {
        return this.baseNariz;
    }

    public void setBaseNariz(Valor valorByBaseNarizVal) {
        this.baseNariz = valorByBaseNarizVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ColorCabello_val")
    public Valor getColorCabello() {
        return this.colorCabello;
    }

    public void setColorCabello(Valor valorByColorCabelloVal) {
        this.colorCabello = valorByColorCabelloVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LabioComisuras_val")
    public Valor getLabioComisuras() {
        return this.labioComisuras;
    }

    public void setLabioComisuras(Valor valorByLabioComisurasVal) {
        this.labioComisuras = valorByLabioComisurasVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HelixPosterior_val")
    public Valor getHelixPosterior() {
        return this.helixPosterior;
    }

    public void setHelixPosterior(Valor valorByHelixPosteriorVal) {
        this.helixPosterior = valorByHelixPosteriorVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TamanioBoca_val")
    public Valor getTamanioBoca() {
        return this.tamanioBoca;
    }

    public void setTamanioBoca(Valor valorByTamanioBocaVal) {
        this.tamanioBoca = valorByTamanioBocaVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LabiosProminencia_val")
    public Valor getLabiosProminencia() {
        return this.labiosProminencia;
    }

    public void setLabiosProminencia(Valor valorByLabiosProminenciaVal) {
        this.labiosProminencia = valorByLabiosProminenciaVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Tez_val")
    public Valor getTez() {
        return this.tez;
    }

    public void setTez(Valor valorByTezVal) {
        this.tez = valorByTezVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoCara_val")
    public Valor getTipoCara() {
        return this.tipoCara;
    }

    public void setTipoCara(Valor valorByTipoCaraVal) {
        this.tipoCara = valorByTipoCaraVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FormaCabello_val")
    public Valor getFormaCabello() {
        return this.formaCabello;
    }

    public void setFormaCabello(Valor valorByFormaCabelloVal) {
        this.formaCabello = valorByFormaCabelloVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ImplantacionCeja_val")
    public Valor getImplantacionCeja() {
        return this.implantacionCeja;
    }

    public void setImplantacionCeja(Valor valorByImplantacionCejaVal) {
        this.implantacionCeja = valorByImplantacionCejaVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoMenton_val")
    public Valor getTipoMenton() {
        return this.tipoMenton;
    }

    public void setTipoMenton(Valor valorByTipoMentonVal) {
        this.tipoMenton = valorByTipoMentonVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrejaLobDimension_val")
    public Valor getOrejaLobDimension() {
        return this.orejaLobDimension;
    }

    public void setOrejaLobDimension(Valor valorByOrejaLobDimensionVal) {
        this.orejaLobDimension = valorByOrejaLobDimensionVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DireccionCeja_val")
    public Valor getDireccionCeja() {
        return this.direccionCeja;
    }

    public void setDireccionCeja(Valor valorByDireccionCejaVal) {
        this.direccionCeja = valorByDireccionCejaVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EspesorLabioSup_val")
    public Valor getEspesorLabioSup() {
        return this.espesorLabioSup;
    }

    public void setEspesorLabioSup(Valor valorByEspesorLabioSupVal) {
        this.espesorLabioSup = valorByEspesorLabioSupVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FrenteAncho_val")
    public Valor getFrenteAncho() {
        return this.frenteAncho;
    }

    public void setFrenteAncho(Valor valorByFrenteAnchoVal) {
        this.frenteAncho = valorByFrenteAnchoVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HelixContorno_val")
    public Valor getHelixContorno() {
        return this.helixContorno;
    }

    public void setHelixContorno(Valor valorByHelixContornoVal) {
        this.helixContorno = valorByHelixContornoVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AlturaNariz_val")
    public Valor getAlturaNariz() {
        return this.alturaNariz;
    }

    public void setAlturaNariz(Valor valorByAlturaNarizVal) {
        this.alturaNariz = valorByAlturaNarizVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrejaLobParticularidad_val")
    public Valor getOrejaLobParticularidad() {
        return this.orejaLobParticularidad;
    }

    public void setOrejaLobParticularidad(Valor valorByOrejaLobParticularidadVal) {
        this.orejaLobParticularidad = valorByOrejaLobParticularidadVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HelixAdherencia_val")
    public Valor getHelixAdherencia() {
        return this.helixAdherencia;
    }

    public void setHelixAdherencia(Valor valorByHelixAdherenciaVal) {
        this.helixAdherencia = valorByHelixAdherenciaVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrejaLobAdherencia_val")
    public Valor getOrejaLobAdherencia() {
        return this.orejaLobAdherencia;
    }

    public void setOrejaLobAdherencia(Valor valorByOrejaLobAdherenciaVal) {
        this.orejaLobAdherencia = valorByOrejaLobAdherenciaVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HelixSuperior_val")
    public Valor getHelixSuperior() {
        return this.helixSuperior;
    }

    public void setHelixSuperior(Valor valorByHelixSuperiorVal) {
        this.helixSuperior = valorByHelixSuperiorVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FrenteInclinacion_val")
    public Valor getFrenteInclinacion() {
        return this.frenteInclinacion;
    }

    public void setFrenteInclinacion(Valor valorByFrenteInclinacionVal) {
        this.frenteInclinacion = valorByFrenteInclinacionVal;
    }

    @Column(name = "dcEstatura", precision = 5)
    public Double getEstatura() {
        return this.estatura;
    }

    public void setEstatura(Double dcEstatura) {
        this.estatura = dcEstatura;
    }

    @Column(name = "dcPeso", precision = 5)
    public Double getPeso() {
        return this.peso;
    }

    public void setPeso(Double dcPeso) {
        this.peso = dcPeso;
    }

    @Column(name = "cPerfil", length = 150)
    public String getPerfil() {
        return this.perfil;
    }

    public void setPerfil(String cperfil) {
        this.perfil = cperfil;
    }

    @Column(name = "bTieneBarba", precision = 1, scale = 0)
    public Boolean getTieneBarba() {
        return this.tieneBarba;
    }

    public void setTieneBarba(Boolean btieneBarba) {
        this.tieneBarba = btieneBarba;
    }

    @Column(name = "bTieneBigote", precision = 1, scale = 0)
    public Boolean getTieneBigote() {
        return this.tieneBigote;
    }

    public void setTieneBigote(Boolean btieneBigote) {
        this.tieneBigote = btieneBigote;
    }

    @Column(name = "bUsaLentes", precision = 1, scale = 0)
    public Boolean getUsaLentes() {
        return this.usaLentes;
    }

    public void setUsaLentes(Boolean busaLentes) {
        this.usaLentes = busaLentes;
    }
    
    @Column(name = "cFactorRH", length = 8)
    public String getFactorRH() {
		return factorRH;
	}

	public void setFactorRH(String factorRH) {
		this.factorRH = factorRH;
	}

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoSangre_val")
	public Valor getTipoSangre() {
		return tipoSangre;
	}

	public void setTipoSangre(Valor tipoSangre) {
		this.tipoSangre = tipoSangre;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HelixOriginal_val")
    public Valor getHelixOriginal() {
        return this.helixOriginal;
    }

    public void setHelixOriginal(Valor valorByHelixOriginalVal) {
        this.helixOriginal = valorByHelixOriginalVal;
    }

//	@Transient
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DorsoNariz_val")
    public Valor getDorsoNariz() {
		return dorsoNariz;
	}

	public void setDorsoNariz(Valor dorsoNariz) {
		this.dorsoNariz = dorsoNariz;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrejaLobContorno_val")
    public Valor getOrejaLobContorno() {
		return orejaLobContorno;
	}

	public void setOrejaLobContorno(Valor orejaLobContorno) {
		this.orejaLobContorno = orejaLobContorno;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Complexion_val")
    public Valor getComplexion() {
		return complexion;
	}

	public void setComplexion(Valor complexion) {
		this.complexion = complexion;
	}
}
package mx.gob.segob.nsjp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Domicilio entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Domicilio")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "Domicilio_id")
public class Domicilio extends Lugar {

    // Fields

    private Asentamiento asentamiento;
    private Valor valorByTipoCalleVal;
    private String calle;
    private String numeroExterior;
    private String numeroInterior;
    private String numeroLote;
    private String referencias;
    private String entreCalle1;
    private String entreCalle2;
    private String alias;
    private String edificio;

    private Municipio municipio;
    private EntidadFederativa entidadFederativa;
    private Ciudad ciudad;
    private Set<Medida> medidas = new HashSet<Medida>(0);

    // Constructors

    /** default constructor */
    public Domicilio() {
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Asentamiento_id")
    public Asentamiento getAsentamiento() {
        return this.asentamiento;
    }

    public void setAsentamiento(Asentamiento asentamiento) {
        this.asentamiento = asentamiento;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoCalle_val")
    public Valor getValorByTipoCalleVal() {
        return this.valorByTipoCalleVal;
    }

    public void setValorByTipoCalleVal(Valor valorByTipoCalleVal) {
        this.valorByTipoCalleVal = valorByTipoCalleVal;
    }

    @Column(name = "cCalle", length = 50)
    public String getCalle() {
        return this.calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    @Column(name = "cNumeroExterior", length = 15)
    public String getNumeroExterior() {
        return this.numeroExterior;
    }

    public void setNumeroExterior(String numeroExterior) {
        this.numeroExterior = numeroExterior;
    }

    @Column(name = "cNumeroInterior", length = 15)
    public String getNumeroInterior() {
        return this.numeroInterior;
    }

    public void setNumeroInterior(String numeroInterior) {
        this.numeroInterior = numeroInterior;
    }

    @Column(name = "cNumeroLote", length = 10)
    public String getNumeroLote() {
        return this.numeroLote;
    }

    public void setNumeroLote(String numeroLote) {
        this.numeroLote = numeroLote;
    }

    @Column(name = "cReferencias", length = 60)
    public String getReferencias() {
        return this.referencias;
    }

    public void setReferencias(String referencias) {
        this.referencias = referencias;
    }

    @Column(name = "cEntreCalle1", length = 60)
    public String getEntreCalle1() {
        return this.entreCalle1;
    }

    public void setEntreCalle1(String entreCalle1) {
        this.entreCalle1 = entreCalle1;
    }

    @Column(name = "cEntreCalle2", length = 60)
    public String getEntreCalle2() {
        return this.entreCalle2;
    }

    public void setEntreCalle2(String entreCalle2) {
        this.entreCalle2 = entreCalle2;
    }

    @Column(name = "cAlias", length = 60)
    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Column(name = "cEdificio", length = 60)
    public String getEdificio() {
        return this.edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Municipio_id")
    public Municipio getMunicipio() {
        return this.municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EntidadFederativa_id")
    public EntidadFederativa getEntidadFederativa() {
        return this.entidadFederativa;
    }

    public void setEntidadFederativa(EntidadFederativa entidadFederativa) {
        this.entidadFederativa = entidadFederativa;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Ciudad_id")
    public Ciudad getCiudad() {
        return this.ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }


    
	/**
	 * Método de acceso al campo medidas.
	 * @return El valor del campo medidas
	 */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "domicilio")
	public Set<Medida> getMedidas() {
		return medidas;
	}

	/**
	 * Asigna el valor al campo medidas.
	 * @param medidas el valor medidas a asignar
	 */
	public void setMedidas(Set<Medida> medidas) {
		this.medidas = medidas;
	}

	@Override
	public String toString() {
		String strCalle=(this.calle!=null)?this.calle:"";
		String strNumeroExt=(this.numeroExterior!=null)?this.numeroExterior:"";
		String strNumeroInt=(this.numeroInterior!=null)?this.numeroInterior:"";
		String strCiudad=(this.ciudad!=null)?this.ciudad.getNombreCiudad():"";
		return strCalle+" "+strNumeroExt+" "+strNumeroInt+" "+strCiudad;
	}    
	
}

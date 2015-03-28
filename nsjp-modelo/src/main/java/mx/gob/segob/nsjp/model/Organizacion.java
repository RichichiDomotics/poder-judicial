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

/**
 * Organizacion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Organizacion")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "Organizacion_id")
public class Organizacion extends Elemento {

    // Fields

    private Valor valorBySectorGubernamentalVal;
    private Valor valorByOrganizacionFormalVal;
    private Valor valorByComunidadVirtualVal;
    private Valor valorByTipoOrganizacionVal;
    private String nombreOrganizacion;
    private String nombreCorto;
    private String rfc;
    private String giro;
    private String numeroActaConstitutiva;
    private String areaDeInfluencia;
    private String direccionInternet;
    private String tipoCiberespacio;
    private String propositoCiberespacio;
    private String descripcionDelictiva; 
    
    // Constructors

    /** default constructor */
    public Organizacion() {
    }
    
    /**
     * 
     * @param elementoId
     */
    public Organizacion(Long elementoId) {
    	super();
    	setElementoId(elementoId);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SectorGubernamental_val")
    public Valor getValorBySectorGubernamentalVal() {
        return this.valorBySectorGubernamentalVal;
    }

    public void setValorBySectorGubernamentalVal(
            Valor valorBySectorGubernamentalVal) {
        this.valorBySectorGubernamentalVal = valorBySectorGubernamentalVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrganizacionFormal_val")
    public Valor getValorByOrganizacionFormalVal() {
        return this.valorByOrganizacionFormalVal;
    }

    public void setValorByOrganizacionFormalVal(
            Valor valorByOrganizacionFormalVal) {
        this.valorByOrganizacionFormalVal = valorByOrganizacionFormalVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ComunidadVirtual_val")
    public Valor getValorByComunidadVirtualVal() {
        return this.valorByComunidadVirtualVal;
    }

    public void setValorByComunidadVirtualVal(Valor valorByComunidadVirtualVal) {
        this.valorByComunidadVirtualVal = valorByComunidadVirtualVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoOrganizacion_val", nullable = false)
    public Valor getValorByTipoOrganizacionVal() {
        return this.valorByTipoOrganizacionVal;
    }

    public void setValorByTipoOrganizacionVal(Valor valorByTipoOrganizacionVal) {
        this.valorByTipoOrganizacionVal = valorByTipoOrganizacionVal;
    }

    @Column(name = "cNombreOrganizacion", length = 60)
    public String getNombreOrganizacion() {
        return this.nombreOrganizacion;
    }

    public void setNombreOrganizacion(String nombreOrganizacion) {
        this.nombreOrganizacion = nombreOrganizacion;
    }

    @Column(name = "cNombreCorto", length = 30)
    public String getNombreCorto() {
        return this.nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    @Column(name = "cRFC", length = 13)
    public String getRfc() {
        return this.rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    @Column(name = "cGiro", length = 60)
    public String getGiro() {
        return this.giro;
    }

    public void setGiro(String giro) {
        this.giro = giro;
    }

    @Column(name = "cNumeroActaConstitutiva", length = 20)
    public String getNumeroActaConstitutiva() {
        return this.numeroActaConstitutiva;
    }

    public void setNumeroActaConstitutiva(String numeroActaConstitutiva) {
        this.numeroActaConstitutiva = numeroActaConstitutiva;
    }

    @Column(name = "cAreaDeInfluencia", length = 150)
    public String getAreaDeInfluencia() {
        return this.areaDeInfluencia;
    }

    public void setAreaDeInfluencia(String areaDeInfluencia) {
        this.areaDeInfluencia = areaDeInfluencia;
    }

    @Column(name = "cDireccionInternet", length = 150)
    public String getDireccionInternet() {
        return this.direccionInternet;
    }

    public void setDireccionInternet(String direccionInternet) {
        this.direccionInternet = direccionInternet;
    }

    @Column(name = "cTipoCiberespacio", length = 50)
    public String getTipoCiberespacio() {
        return this.tipoCiberespacio;
    }

    public void setTipoCiberespacio(String tipoCiberespacio) {
        this.tipoCiberespacio = tipoCiberespacio;
    }

    @Column(name = "cPropositoCiberespacio", length = 150)
    public String getPropositoCiberespacio() {
        return this.propositoCiberespacio;
    }

    public void setPropositoCiberespacio(String propositoCiberespacio) {
        this.propositoCiberespacio = propositoCiberespacio;
    }

    /**
     * Método de acceso al campo descripcionDelictiva.
     * 
     * @return El valor del campo descripcionDelictiva
     */
    @Column(name = "cDescripcionDelictiva", length = 150)
    public String getDescripcionDelictiva() {
        return descripcionDelictiva;
    }

    /**
     * Asigna el valor al campo descripcionDelictiva.
     * 
     * @param descripcionDelictiva
     *            el valor descripcionDelictiva a asignar
     */
    public void setDescripcionDelictiva(String descripcionDelictiva) {
        this.descripcionDelictiva = descripcionDelictiva;
    }

}

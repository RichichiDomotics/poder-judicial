package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Inmueble entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Inmueble")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "Inmueble_id")
public class Inmueble extends Objeto {

    // Fields

    private Integer superficie;
    private Integer mconstruccion;
    private Valor tipoInmueble;
    private Valor tipoConstruccion;
    private Valor unidadMconstruccion;
    private Valor unidadMterreno;

    // Constructors

    /** default constructor */
    public Inmueble() {
    }

    @Column(name = "iSuperficie", precision = 8, scale = 0)
    public Integer getSuperficie() {
        return this.superficie;
    }

    public void setSuperficie(Integer isuperficie) {
        this.superficie = isuperficie;
    }

    @Column(name = "iMconstruccion", precision = 8, scale = 0)
    public Integer getMconstruccion() {
        return this.mconstruccion;
    }

    public void setMconstruccion(Integer imconstruccion) {
        this.mconstruccion = imconstruccion;
    }

    /**
     * Método de acceso al campo tipoInmueble.
     * 
     * @return El valor del campo tipoInmueble
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoInmueble_val")
    public Valor getTipoInmueble() {
        return tipoInmueble;
    }

    /**
     * Asigna el valor al campo tipoInmueble.
     * 
     * @param tipoInmueble
     *            el valor tipoInmueble a asignar
     */
    public void setTipoInmueble(Valor tipoInmueble) {
        this.tipoInmueble = tipoInmueble;
    }

    /**
     * Método de acceso al campo tipoConstruccion.
     * 
     * @return El valor del campo tipoConstruccion
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoConstruccion_val")
    public Valor getTipoConstruccion() {
        return tipoConstruccion;
    }

    /**
     * Asigna el valor al campo tipoConstruccion.
     * 
     * @param tipoConstruccion
     *            el valor tipoConstruccion a asignar
     */
    public void setTipoConstruccion(Valor tipoConstruccion) {
        this.tipoConstruccion = tipoConstruccion;
    }

    /**
     * Método de acceso al campo unidadMconstruccion.
     * 
     * @return El valor del campo unidadMconstruccion
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UnidadMConstruccion_val")
    public Valor getUnidadMconstruccion() {
        return unidadMconstruccion;
    }

    /**
     * Asigna el valor al campo unidadMconstruccion.
     * 
     * @param unidadMconstruccion
     *            el valor unidadMconstruccion a asignar
     */
    public void setUnidadMconstruccion(Valor unidadMconstruccion) {
        this.unidadMconstruccion = unidadMconstruccion;
    }

    /**
     * Método de acceso al campo unidadMterreno.
     * 
     * @return El valor del campo unidadMterreno
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UnidadMTerreno_val")
    public Valor getUnidadMterreno() {
        return unidadMterreno;
    }

    /**
     * Asigna el valor al campo unidadMterreno.
     * 
     * @param unidadMterreno
     *            el valor unidadMterreno a asignar
     */
    public void setUnidadMterreno(Valor unidadMterreno) {
        this.unidadMterreno = unidadMterreno;
    }

}
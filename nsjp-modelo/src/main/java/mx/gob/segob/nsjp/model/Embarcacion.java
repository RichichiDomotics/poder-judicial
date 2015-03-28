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
 * Embarcacion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Embarcacion")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "Embarcacion_id")
public class Embarcacion extends Objeto {

    // Fields

    private String matricula;
    private String noSerie;
    private String noMotor;
    private String nombreEmbarcacion;
    private Valor paisOrigen;
    private Valor color;
    private Valor marca;
    private Valor tipoEmbarcacion;
    private Valor subMarca;

    // Constructors

    /** default constructor */
    public Embarcacion() {
    }

    @Column(name = "cMatricula", length = 15)
    public String getMatricula() {
        return this.matricula;
    }

    public void setMatricula(String cmatricula) {
        this.matricula = cmatricula;
    }

    @Column(name = "cNserie", length = 30)
    public String getNoSerie() {
        return this.noSerie;
    }

    public void setNoSerie(String cnserie) {
        this.noSerie = cnserie;
    }

    @Column(name = "cNmotor", length = 20)
    public String getNoMotor() {
        return this.noMotor;
    }

    public void setNoMotor(String cnmotor) {
        this.noMotor = cnmotor;
    }

    @Column(name = "cNombreEmbarcacion", length = 30)
    public String getNombreEmbarcacion() {
        return this.nombreEmbarcacion;
    }

    public void setNombreEmbarcacion(String cnombreEmbarcacion) {
        this.nombreEmbarcacion = cnombreEmbarcacion;
    }

    /**
     * Método de acceso al campo paisOrigen.
     * 
     * @return El valor del campo paisOrigen
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PaisOrigen_val")
    public Valor getPaisOrigen() {
        return paisOrigen;
    }

    /**
     * Asigna el valor al campo paisOrigen.
     * 
     * @param paisOrigen
     *            el valor paisOrigen a asignar
     */
    public void setPaisOrigen(Valor paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    /**
     * Método de acceso al campo color.
     * 
     * @return El valor del campo color
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Color_val")
    public Valor getColor() {
        return color;
    }

    /**
     * Asigna el valor al campo color.
     * 
     * @param color
     *            el valor color a asignar
     */
    public void setColor(Valor color) {
        this.color = color;
    }

    /**
     * Método de acceso al campo marca.
     * 
     * @return El valor del campo marca
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Marca_val")
    public Valor getMarca() {
        return marca;
    }

    /**
     * Asigna el valor al campo marca.
     * 
     * @param marca
     *            el valor marca a asignar
     */
    public void setMarca(Valor marca) {
        this.marca = marca;
    }

    /**
     * Método de acceso al campo tipoEmbarcacion.
     * 
     * @return El valor del campo tipoEmbarcacion
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoEmbarcacion_val")
    public Valor getTipoEmbarcacion() {
        return tipoEmbarcacion;
    }

    /**
     * Asigna el valor al campo tipoEmbarcacion.
     * 
     * @param tipoEmbarcacion
     *            el valor tipoEmbarcacion a asignar
     */
    public void setTipoEmbarcacion(Valor tipoEmbarcacion) {
        this.tipoEmbarcacion = tipoEmbarcacion;
    }

    /**
     * Método de acceso al campo subMarca.
     * 
     * @return El valor del campo subMarca
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Submarca_val")
    public Valor getSubMarca() {
        return subMarca;
    }

    /**
     * Asigna el valor al campo subMarca.
     * 
     * @param subMarca
     *            el valor subMarca a asignar
     */
    public void setSubMarca(Valor subMarca) {
        this.subMarca = subMarca;
    }

}
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
import javax.persistence.Transient;

/**
 * Aeronave entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Aeronave")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "Aeronave_id")
public class Aeronave extends Objeto {

    // Fields

    private Valor paisProcedencia;
    private String matricula;
    private String noSerie;
    private String noMotor;
    private String modelo;
    private Valor marca;
    private Valor subMarca;
    private Valor tipoAeroNave;
    private Valor color;

    // Constructors

    /** default constructor */
    public Aeronave() {
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PaisOrigen_val")
    public Valor getPaisProcedencia() {
        return this.paisProcedencia;
    }

    public void setPaisProcedencia(Valor valor) {
        this.paisProcedencia = valor;
    }

    @Column(name = "cMatricula", length = 15)
    public String getMatricula() {
        return this.matricula;
    }

    public void setMatricula(String cmatricula) {
        this.matricula = cmatricula;
    }

    @Column(name = "cNserie", length = 20)
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

    /**
     * Método de acceso al campo modelo.
     * 
     * @return El valor del campo modelo
     */
    @Column(name = "cModelo", length = 25)
    public String getModelo() {
        return modelo;
    }

    /**
     * Asigna el valor al campo modelo.
     * 
     * @param modelo
     *            el valor modelo a asignar
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
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

    /**
     * Método de acceso al campo tipoAeroNave.
     * 
     * @return El valor del campo tipoAeroNave
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoAeroNave_val")
    public Valor getTipoAeroNave() {
        return tipoAeroNave;
    }

    /**
     * Asigna el valor al campo tipoAeroNave.
     * 
     * @param tipoAeroNave
     *            el valor tipoAeroNave a asignar
     */
    public void setTipoAeroNave(Valor tipoAeroNave) {
        this.tipoAeroNave = tipoAeroNave;
    }

    /**
     * Método de acceso al campo color.
     * @return El valor del campo color
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Color_val")
    public Valor getColor() {
        return color;
    }

    /**
     * Asigna el valor al campo color.
     * @param color el valor color a asignar
     */
    public void setColor(Valor color) {
        this.color = color;
    }

}
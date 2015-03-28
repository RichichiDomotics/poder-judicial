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
 * Arma entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Arma")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "Arma_id")
public class Arma extends Objeto {

    // Fields

    private String matricula;
    private String calibre;
    private Valor marca;
    private Valor tipoArma;
    private String modelo;
    // Constructors

    /** default constructor */
    public Arma() {
    }

    @Column(name = "cMatricula", length = 20)
    public String getMatricula() {
        return this.matricula;
    }

    public void setMatricula(String cmatricula) {
        this.matricula = cmatricula;
    }

    @Column(name = "cCalibre", length = 15)
    public String getCalibre() {
        return this.calibre;
    }

    public void setCalibre(String ccalibre) {
        this.calibre = ccalibre;
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
     * Método de acceso al campo tipoArma.
     * 
     * @return El valor del campo tipoArma
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoArma_val")
    public Valor getTipoArma() {
        return tipoArma;
    }

    /**
     * Asigna el valor al campo tipoArma.
     * 
     * @param tipoArma
     *            el valor tipoArma a asignar
     */
    public void setTipoArma(Valor tipoArma) {
        this.tipoArma = tipoArma;
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

}
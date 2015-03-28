package mx.gob.segob.nsjp.model;

import java.util.Date;

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
 * Numerario entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Numerario")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "Numerario_id")
public class Numerario extends Objeto {

    // Fields

    private Date fechaDeposito;
    private String ctaTesoreria;
    private Long cantidad;
    private String moneda;
    private Valor tipoMoneda;
    private Boolean esAutentico;

    // Constructors

    /** default constructor */
    public Numerario() {
    }

    @Column(name = "dFechadeposito", length = 23)
    public Date getFechaDeposito() {
        return this.fechaDeposito;
    }

    public void setFechaDeposito(Date dfechadeposito) {
        this.fechaDeposito = dfechadeposito;
    }

    @Column(name = "cCtaTesoreria", length = 30)
    public String getCtaTesoreria() {
        return this.ctaTesoreria;
    }

    public void setCtaTesoreria(String cctaTesoreria) {
        this.ctaTesoreria = cctaTesoreria;
    }

    @Column(name = "iCantidad", precision = 18, scale = 0)
    public Long getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(Long icantidad) {
        this.cantidad = icantidad;
    }

    /**
     * Método de acceso al campo moneda.
     * 
     * @return El valor del campo moneda
     */
    @Column(name = "cMoneda", length = 25)
    public String getMoneda() {
        return moneda;
    }

    /**
     * Asigna el valor al campo moneda.
     * 
     * @param moneda
     *            el valor moneda a asignar
     */
    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    /**
     * Método de acceso al campo tipoMoneda.
     * @return El valor del campo tipoMoneda
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoMoneda_val")
    public Valor getTipoMoneda() {
        return tipoMoneda;
    }

    /**
     * Asigna el valor al campo tipoMoneda.
     * @param tipoMoneda el valor tipoMoneda a asignar
     */
    public void setTipoMoneda(Valor tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    /**
     * Método de acceso al campo esAutentico.
     * @return El valor del campo esAutentico
     */
    @Column(name = "bEsAutentico", precision = 1, scale = 0)
    public Boolean getEsAutentico() {
        return esAutentico;
    }

    /**
     * Asigna el valor al campo esAutentico.
     * @param esAutentico el valor esAutentico a asignar
     */
    public void setEsAutentico(Boolean esAutentico) {
        this.esAutentico = esAutentico;
    }

}
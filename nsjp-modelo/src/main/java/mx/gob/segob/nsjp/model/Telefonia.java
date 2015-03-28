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
 * Telefonia entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Telefonia")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "Telefonia_id")
public class Telefonia extends Objeto {

    // Fields

    private Long cantidad;
    private Valor marca;
    private Valor tipoTelefono;
    private String modelo;

    // Constructors

    /** default constructor */
    public Telefonia() {
    }

    @Column(name = "iCantidad", precision = 4, scale = 0)
    public Long getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(Long icantidad) {
        this.cantidad = icantidad;
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
     * Método de acceso al campo tipoTelefono.
     * 
     * @return El valor del campo tipoTelefono
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoTelefono_val")
    public Valor getTipoTelefono() {
        return tipoTelefono;
    }

    /**
     * Asigna el valor al campo tipoTelefono.
     * 
     * @param tipoTelefono
     *            el valor tipoTelefono a asignar
     */
    public void setTipoTelefono(Valor tipoTelefono) {
        this.tipoTelefono = tipoTelefono;
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
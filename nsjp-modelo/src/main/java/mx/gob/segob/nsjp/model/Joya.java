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
 * Joya entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Joya")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "Joya_id")
public class Joya extends Objeto {

    // Fields

    private Long cantidad;
    private Valor tipoJoya;
    private String material;

    // Constructors

    /** default constructor */
    public Joya() {
    }

    @Column(name = "iCantidad", precision = 4, scale = 0)
    public Long getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(Long icantidad) {
        this.cantidad = icantidad;
    }

    /**
     * Método de acceso al campo tipoJoya.
     * 
     * @return El valor del campo tipoJoya
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoJoya_val")
    public Valor getTipoJoya() {
        return tipoJoya;
    }

    /**
     * Asigna el valor al campo tipoJoya.
     * 
     * @param tipoJoya
     *            el valor tipoJoya a asignar
     */
    public void setTipoJoya(Valor tipoJoya) {
        this.tipoJoya = tipoJoya;
    }

    /**
     * Método de acceso al campo material.
     * 
     * @return El valor del campo material
     */
    @Column(name = "cMaterial", length = 25)
    public String getMaterial() {
        return material;
    }

    /**
     * Asigna el valor al campo material.
     * 
     * @param material
     *            el valor material a asignar
     */
    public void setMaterial(String material) {
        this.material = material;
    }

}
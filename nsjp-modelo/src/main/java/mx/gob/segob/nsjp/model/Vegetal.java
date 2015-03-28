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
 * Vegetal entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Vegetal")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "Vegetal_id")
public class Vegetal extends Objeto {

    // Fields

    private Long cantidad;
    private Valor tipoVegetal;
    private Valor unidadMedida;

    // Constructors

    /** default constructor */
    public Vegetal() {
    }

    @Column(name = "iCantidad", precision = 4, scale = 0)
    public Long getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(Long icantidad) {
        this.cantidad = icantidad;
    }

    /**
     * Método de acceso al campo tipoVegetal.
     * 
     * @return El valor del campo tipoVegetal
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoVegetal_val")
    public Valor getTipoVegetal() {
        return tipoVegetal;
    }

    /**
     * Asigna el valor al campo tipoVegetal.
     * 
     * @param tipoVegetal
     *            el valor tipoVegetal a asignar
     */
    public void setTipoVegetal(Valor tipoVegetal) {
        this.tipoVegetal = tipoVegetal;
    }

    /**
     * Método de acceso al campo unidadMedida.
     * 
     * @return El valor del campo unidadMedida
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UnidadMedida_val")
    public Valor getUnidadMedida() {
        return unidadMedida;
    }

    /**
     * Asigna el valor al campo unidadMedida.
     * 
     * @param unidadMedida
     *            el valor unidadMedida a asignar
     */
    public void setUnidadMedida(Valor unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

}
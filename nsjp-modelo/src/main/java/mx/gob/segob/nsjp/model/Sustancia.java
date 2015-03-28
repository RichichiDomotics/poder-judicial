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
 * Sustancia entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Sustancia")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "Sustancia_id")
public class Sustancia extends Objeto {

    // Fields

    private Long cantidad;
    private Valor tipoSustancia;
    private Valor empaque;
    private Valor unidadMedida;

    // Constructors

    /** default constructor */
    public Sustancia() {
    }

    @Column(name = "iCantidad", precision = 4, scale = 0)
    public Long getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(Long icantidad) {
        this.cantidad = icantidad;
    }

    /**
     * Método de acceso al campo tipoSustancia.
     * 
     * @return El valor del campo tipoSustancia
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoSustancia_val")
    public Valor getTipoSustancia() {
        return tipoSustancia;
    }

    /**
     * Asigna el valor al campo tipoSustancia.
     * 
     * @param tipoSustancia
     *            el valor tipoSustancia a asignar
     */
    public void setTipoSustancia(Valor tipoSustancia) {
        this.tipoSustancia = tipoSustancia;
    }

    /**
     * Método de acceso al campo empaque.
     * 
     * @return El valor del campo empaque
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Empaque_val")
    public Valor getEmpaque() {
        return empaque;
    }

    /**
     * Asigna el valor al campo empaque.
     * 
     * @param empaque
     *            el valor empaque a asignar
     */
    public void setEmpaque(Valor empaque) {
        this.empaque = empaque;
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
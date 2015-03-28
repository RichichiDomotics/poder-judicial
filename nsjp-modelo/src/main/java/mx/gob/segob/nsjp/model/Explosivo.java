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
 * Explosivo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Explosivo")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "Explosivo_id")
public class Explosivo extends Objeto {

    // Fields

    private Long cantidad;
    private Valor tipoExplosivo;
    private Valor unidadMedida;

    // Constructors

    /** default constructor */
    public Explosivo() {
    }

    @Column(name = "iCantidad", precision = 4, scale = 0)
    public Long getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(Long icantidad) {
        this.cantidad = icantidad;
    }

    /**
     * Método de acceso al campo tipoExplosivo.
     * 
     * @return El valor del campo tipoExplosivo
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoExplosivo_val")
    public Valor getTipoExplosivo() {
        return tipoExplosivo;
    }

    /**
     * Asigna el valor al campo tipoExplosivo.
     * 
     * @param tipoExplosivo
     *            el valor tipoExplosivo a asignar
     */
    public void setTipoExplosivo(Valor tipoExplosivo) {
        this.tipoExplosivo = tipoExplosivo;
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
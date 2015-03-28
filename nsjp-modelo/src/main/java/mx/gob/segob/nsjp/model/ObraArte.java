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
 * ObraArte entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ObraArte")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "ObraArte_id")
public class ObraArte extends Objeto {

    // Fields

    private Long cantidad;
    private Valor tipoObraArte;
    // Constructors

    /** default constructor */
    public ObraArte() {
    }

    @Column(name = "iCantidad", precision = 4, scale = 0)
    public Long getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(Long icantidad) {
        this.cantidad = icantidad;
    }

    /**
     * Método de acceso al campo tipoObraArte.
     * 
     * @return El valor del campo tipoObraArte
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoObraArte")
    public Valor getTipoObraArte() {
        return tipoObraArte;
    }

    /**
     * Asigna el valor al campo tipoObraArte.
     * 
     * @param tipoObraArte
     *            el valor tipoObraArte a asignar
     */
    public void setTipoObraArte(Valor tipoObraArte) {
        this.tipoObraArte = tipoObraArte;
    }

}
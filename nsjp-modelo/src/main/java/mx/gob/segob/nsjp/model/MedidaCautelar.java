package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * MedidaCautelar entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MedidaCautelar")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "MedidaCautelar_id")
public class MedidaCautelar extends Medida {

    // Fields

    private Boolean esActivo;

    // Constructors

    /** default constructor */
    public MedidaCautelar() {
    }

    @Column(name = "bEsActivo", nullable = false, precision = 1, scale = 0)
    public Boolean getEsActivo() {
        return this.esActivo;
    }

    public void setEsActivo(Boolean esActivo) {
        this.esActivo = esActivo;
    }

}
package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CatComponente entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CatComponente")
public class CatComponente implements java.io.Serializable {

    // Fields

    private Long catComponenteId;
    private Valor tipoComponente;
    private String monogramaComponente;
    private String nombreComponente;

    // Constructors

    /** default constructor */
    public CatComponente() {
    }

    /** full constructor */
    public CatComponente(Long catComponenteId, Valor valor,
            String cmonogramaComponente, String cnombreComponente) {
        this.catComponenteId = catComponenteId;
        this.tipoComponente = valor;
        this.monogramaComponente = cmonogramaComponente;
        this.nombreComponente = cnombreComponente;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CatComponente_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getCatComponenteId() {
        return this.catComponenteId;
    }

    public void setCatComponenteId(Long catComponenteId) {
        this.catComponenteId = catComponenteId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoComponente_val", nullable = false)
    public Valor getTipoComponente() {
        return this.tipoComponente;
    }

    public void setTipoComponente(Valor valor) {
        this.tipoComponente = valor;
    }

    @Column(name = "cMonogramaComponente", nullable = false, length = 3)
    public String getMonogramaComponente() {
        return this.monogramaComponente;
    }

    public void setMonogramaComponente(String cmonogramaComponente) {
        this.monogramaComponente = cmonogramaComponente;
    }

    @Column(name = "cNombreComponente", nullable = false, length = 100)
    public String getNombreComponente() {
        return this.nombreComponente;
    }

    public void setNombreComponente(String cnombreComponente) {
        this.nombreComponente = cnombreComponente;
    }

}
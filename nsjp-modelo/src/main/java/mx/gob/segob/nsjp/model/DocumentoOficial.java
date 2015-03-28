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
 * DocumentoOficial entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DocumentoOficial")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "DocumentoOficial_id")
public class DocumentoOficial extends Objeto {

    // Fields

    private Long cantidad;
    private Valor tipoDocumento;

    // Constructors

    /** default constructor */
    public DocumentoOficial() {
    }

    @Column(name = "iCantidad", precision = 4, scale = 0)
    public Long getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(Long icantidad) {
        this.cantidad = icantidad;
    }

    /**
     * Método de acceso al campo tipoDocumento.
     * 
     * @return El valor del campo tipoDocumento
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoDocumento_val")
    public Valor getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Asigna el valor al campo tipoDocumento.
     * 
     * @param tipoDocumento
     *            el valor tipoDocumento a asignar
     */
    public void setTipoDocumento(Valor tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

}
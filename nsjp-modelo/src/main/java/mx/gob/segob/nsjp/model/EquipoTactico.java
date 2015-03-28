package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * EquipoTactico entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "EquipoTactico")
@PrimaryKeyJoinColumn(name = "EquipoTactico_id")
public class EquipoTactico extends Objeto {

    // Fields

    private Valor tipoEquipoTactico;
    private Valor marca;
    private Valor modelo;
    private Valor unidadMedida;
    private String ninventario;
    private String nserie;
    private Long cantidad;

    // Constructors

    /** default constructor */
    public EquipoTactico() {
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoEquipoTactico_val")
    public Valor getTipoEquipoTactico() {
        return this.tipoEquipoTactico;
    }

    public void setTipoEquipoTactico(Valor valorByTipoEquipoTacticoVal) {
        this.tipoEquipoTactico = valorByTipoEquipoTacticoVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Marca_val")
    public Valor getMarca() {
        return this.marca;
    }

    public void setMarca(Valor valorByMarcaVal) {
        this.marca = valorByMarcaVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Modelo_val")
    public Valor getModelo() {
        return this.modelo;
    }

    public void setModelo(Valor valorByModeloVal) {
        this.modelo = valorByModeloVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UnidadMedida_val")
    public Valor getUnidadMedida() {
        return this.unidadMedida;
    }

    public void setUnidadMedida(Valor valorByUnidadMedidaVal) {
        this.unidadMedida = valorByUnidadMedidaVal;
    }

    @Column(name = "cNInventario", length = 30)
    public String getNinventario() {
        return this.ninventario;
    }

    public void setNinventario(String cninventario) {
        this.ninventario = cninventario;
    }

    @Column(name = "cNSerie", length = 30)
    public String getNserie() {
        return this.nserie;
    }

    public void setNserie(String cnserie) {
        this.nserie = cnserie;
    }

    @Column(name = "iCantidad", precision = 18, scale = 0)
    public Long getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(Long icantidad) {
        this.cantidad = icantidad;
    }

}
package mx.gob.segob.nsjp.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Objeto entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Objeto")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "Objeto_id")
public class Objeto extends Elemento {

    // Fields

    private Valor valorByCondicionFisicaVal;
    private Valor valorByTipoObjetoVal;
    private Almacen almacen = new Almacen(1L);
    private String descripcion;
    private String nombreObjeto;

    /**
     * Relación con la institución que lo presenta a la audiencia.
     */
    private ConfInstitucion institucionPresenta;

    private Evidencia evidencia;
    // Constructors

    /** default constructor */
    public Objeto() {
    }
    public Objeto(Long id) {
        super(id);
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CondicionFisica_val", nullable = false)
    public Valor getValorByCondicionFisicaVal() {
        return this.valorByCondicionFisicaVal;
    }

    public void setValorByCondicionFisicaVal(Valor valorByCondicionFisicaVal) {
        this.valorByCondicionFisicaVal = valorByCondicionFisicaVal;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoObjeto_val", nullable = false)
    public Valor getValorByTipoObjetoVal() {
        return this.valorByTipoObjetoVal;
    }

    public void setValorByTipoObjetoVal(Valor valorByTipoObjetoVal) {
        this.valorByTipoObjetoVal = valorByTipoObjetoVal;
    }

    @Column(name = "cnombreObjeto", nullable = true, length = 30)
    public String getNombreObjeto() {
        return this.nombreObjeto;
    }

    public void setNombreObjeto(String cnombreObjeto) {
        this.nombreObjeto = cnombreObjeto;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iIdentificadorAlmacen", nullable = true)
    public Almacen getAlmacen() {
        return this.almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    @Column(name = "cDescripcion", length = 200)
    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String cdescripcion) {
        this.descripcion = cdescripcion;
    }

    /**
     * Método de acceso al campo evidencia.
     * 
     * @return El valor del campo evidencia
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "objeto")
    public Evidencia getEvidencia() {
        return evidencia;
    }

    /**
     * Asigna el valor al campo evidencia.
     * 
     * @param evidencia
     *            el valor evidencia a asignar
     */
    public void setEvidencia(Evidencia evidencia) {
        this.evidencia = evidencia;
    }

    /**
     * Método de acceso al campo institucionPresenta.
     * 
     * @return El valor del campo institucionPresenta
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ConfInstitucion_id")
    public ConfInstitucion getInstitucionPresenta() {
        return institucionPresenta;
    }

    /**
     * Asigna el valor al campo institucionPresenta.
     * 
     * @param institucionPresenta
     *            el valor institucionPresenta a asignar
     */
    public void setInstitucionPresenta(ConfInstitucion institucionPresenta) {
        this.institucionPresenta = institucionPresenta;
    }
}
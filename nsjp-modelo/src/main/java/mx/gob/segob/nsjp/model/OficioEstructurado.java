package mx.gob.segob.nsjp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Intento entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "OficioEstructurado")
public class OficioEstructurado implements java.io.Serializable {

    // Fields
    private Long oficioEstructuradoId;
    private String encabezado;
    private String pie;
    private Valor tipoOficio;
    private List<CuerpoOficioEstructurado> cuerposOficio = new ArrayList<CuerpoOficioEstructurado>();
    private Documento documento;

    // Constructors

    /** default constructor */
    public OficioEstructurado() {
    }

    public OficioEstructurado(Long oficioEstructuradoId) {
        super();
        this.oficioEstructuradoId = oficioEstructuradoId;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OficioEstructurado_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getOficioEstructuradoId() {
        return oficioEstructuradoId;
    }

    /**
     * Asigna el valor al campo oficioEstructuradoId.
     * 
     * @param oficioEstructuradoId
     *            el valor oficioEstructuradoId a asignar
     */
    public void setOficioEstructuradoId(Long oficioEstructuradoId) {
        this.oficioEstructuradoId = oficioEstructuradoId;
    }

    /**
     * Método de acceso al campo encabezado.
     * 
     * @return El valor del campo encabezado
     */
    @Column(name = "cEncabezado")
    public String getEncabezado() {
        return encabezado;
    }

    /**
     * Asigna el valor al campo encabezado.
     * 
     * @param encabezado
     *            el valor encabezado a asignar
     */
    public void setEncabezado(String encabezado) {
        this.encabezado = encabezado;
    }

    /**
     * Método de acceso al campo pie.
     * 
     * @return El valor del campo pie
     */
    @Column(name = "cPie")
    public String getPie() {
        return pie;
    }

    /**
     * Asigna el valor al campo pie.
     * 
     * @param pie
     *            el valor pie a asignar
     */
    public void setPie(String pie) {
        this.pie = pie;
    }

    /**
     * Método de acceso al campo tipoOficio.
     * 
     * @return El valor del campo tipoOficio
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoOficio_val")
    public Valor getTipoOficio() {
        return tipoOficio;
    }

    /**
     * Asigna el valor al campo tipoOficio.
     * 
     * @param tipoOficio
     *            el valor tipoOficio a asignar
     */
    public void setTipoOficio(Valor tipoOficio) {
        this.tipoOficio = tipoOficio;
    }

    /**
     * Método de acceso al campo cuerposOficio.
     * 
     * @return El valor del campo cuerposOficio
     */
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "oficioEstructurado")
    public List<CuerpoOficioEstructurado> getCuerposOficio() {
        return cuerposOficio;
    }

    /**
     * Asigna el valor al campo cuerposOficio.
     * 
     * @param cuerposOficio
     *            el valor cuerposOficio a asignar
     */
    public void setCuerposOficio(List<CuerpoOficioEstructurado> cuerposOficio) {
        this.cuerposOficio = cuerposOficio;
    }

    /**
     * Método de acceso al campo documento.
     * 
     * @return El valor del campo documento
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "Documento_id", nullable = false, unique = true)
    public Documento getDocumento() {
        return documento;
    }

    /**
     * Asigna el valor al campo documento.
     * 
     * @param documento
     *            el valor documento a asignar
     */
    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

}

package mx.gob.segob.nsjp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Elemento entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Elemento")
@Inheritance(strategy = InheritanceType.JOINED)
public class Elemento implements java.io.Serializable {

    // Fields

    private Long elementoId;
    private Expediente expediente;
    private Calidad calidad;
    private Valor tipoElemento;
    private java.util.Date fechaCreacionElemento;
    private Set<Relacion> relacionsForSujetoId = new HashSet<Relacion>(0);
    private Set<Relacion> relacionsForComplementoId = new HashSet<Relacion>(0);
    private Set<RelacionDocumentoElemento> relacionDocumentos = new HashSet<RelacionDocumentoElemento>(
            0);
    // Representa la foto del elemento, tal como objetos y personas
    private ArchivoDigital archivoDigital;
    private Set<RelacionReincidencia> relacionesReincidencia = new HashSet<RelacionReincidencia>(
            0);
    private Boolean esActivo;
    /**
     * Propiedad usada para co-relacionar los elementos entre las instituciones.<br>
     * Esta formado por el <code>monograma</code> de la instituciones que lo
     * registra mas el <code>elementoId</code> (Ejemplo: <i>PG-1, PG-999</i>).
     */
    private String folioElemento;

    // Constructors

    /** default constructor */
    public Elemento() {
    }

    /**
     * @param elementoId
     */
    public Elemento(Long elementoId) {
        super();
        this.elementoId = elementoId;
    }

    /** minimal constructor */
    public Elemento(Long eementoId, Expediente expediente, Calidad calidad,
            Valor valor, java.util.Date fechaCreacionElemento) {
        this.elementoId = eementoId;
        this.expediente = expediente;
        this.calidad = calidad;
        this.tipoElemento = valor;
        this.fechaCreacionElemento = fechaCreacionElemento;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Elemento_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getElementoId() {
        return this.elementoId;
    }

    public void setElementoId(Long eementoId) {
        this.elementoId = eementoId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Expediente_id", nullable = false)
    public Expediente getExpediente() {
        return this.expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Calidad_id", nullable = false)
    @org.hibernate.annotations.Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    public Calidad getCalidad() {
        return this.calidad;
    }

    public void setCalidad(Calidad calidad) {
        this.calidad = calidad;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoElemento_val", nullable = false)
    public Valor getTipoElemento() {
        return this.tipoElemento;
    }

    public void setTipoElemento(Valor valor) {
        this.tipoElemento = valor;
    }

    @Column(name = "dFechaCreacionElemento", nullable = false, length = 23)
    public java.util.Date getFechaCreacionElemento() {
        return this.fechaCreacionElemento;
    }

    public void setFechaCreacionElemento(java.util.Date fechaCreacionElemento) {
        this.fechaCreacionElemento = fechaCreacionElemento;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "elementoBySujetoId")
    public Set<Relacion> getRelacionsForSujetoId() {
        return this.relacionsForSujetoId;
    }

    public void setRelacionsForSujetoId(Set<Relacion> relacionsForSujetoId) {
        this.relacionsForSujetoId = relacionsForSujetoId;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "elementoByComplementoId")
    public Set<Relacion> getRelacionsForComplementoId() {
        return this.relacionsForComplementoId;
    }

    public void setRelacionsForComplementoId(
            Set<Relacion> relacionsForComplementoId) {
        this.relacionsForComplementoId = relacionsForComplementoId;
    }
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "elemento")
    public Set<RelacionDocumentoElemento> getRelacionDocumentos() {
        return this.relacionDocumentos;
    }

    public void setRelacionDocumentos(
            Set<RelacionDocumentoElemento> relacionDocumentoElementos) {
        this.relacionDocumentos = relacionDocumentoElementos;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ArchivoDigital_id", nullable = true)
    public ArchivoDigital getArchivoDigital() {
        return this.archivoDigital;
    }

    public void setArchivoDigital(ArchivoDigital archivoDigital) {
        this.archivoDigital = archivoDigital;
    }

    /**
     * Método de acceso al campo relacionesReincidencia.
     * 
     * @return El valor del campo relacionesReincidencia
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "elemento")
    public Set<RelacionReincidencia> getRelacionesReincidencia() {
        return relacionesReincidencia;
    }

    /**
     * Asigna el valor al campo relacionesReincidencia.
     * 
     * @param relacionesReincidencia
     *            el valor relacionesReincidencia a asignar
     */
    public void setRelacionesReincidencia(
            Set<RelacionReincidencia> relacionesReincidencia) {
        this.relacionesReincidencia = relacionesReincidencia;
    }

    /**
     * Método de acceso al campo esActivo.
     * 
     * @return El valor del campo esActivo
     */
    @Column(name = "bEsActivo", precision = 1, scale = 0)
    public Boolean getEsActivo() {
        return esActivo;
    }

    /**
     * Asigna el valor al campo esActivo.
     * 
     * @param esActivo
     *            el valor esActivo a asignar
     */
    public void setEsActivo(Boolean esActivo) {
        if (esActivo == null)
            this.esActivo = true;
        else
            this.esActivo = esActivo;
    }

    /**
     * Método de acceso al campo folioElemento.
     * 
     * @return El valor del campo folioElemento
     */
    @Column(name = "cFolioElemento", length = 20, nullable = true)
    public String getFolioElemento() {
        return folioElemento;
    }

    /**
     * Asigna el valor al campo folioElemento.
     * 
     * @param folioElemento
     *            el valor folioElemento a asignar
     */
    public void setFolioElemento(String folioElemento) {
        this.folioElemento = folioElemento;
    }

}

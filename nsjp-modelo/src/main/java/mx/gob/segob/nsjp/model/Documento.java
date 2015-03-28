package mx.gob.segob.nsjp.model;

import java.util.Date;
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
import javax.persistence.Transient;

/**
 * Documento entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Documento")
@Inheritance(strategy = InheritanceType.JOINED)
public class Documento implements java.io.Serializable {

    // Fields

    private Long documentoId;
    private Valor tipoDocumento;
    private Forma forma;
    private Boolean esGuardadoParcial;
    private ArchivoDigital archivoDigital;
    private String folioDocumento;
    private String nombreDocumento;
    private java.util.Date fechaCreacion;
    private Double version;
    private String numeroFojas;
    private Funcionario responsableDocumento; // TODO
    private Boolean esModificable;    
    private Long catDiscriminanteOrigen;
    private String claveDiscriminanteOrigen;
    private Long idFuncionarioSolicitante;
    private String descripcion;



    /**
     * Interno (generado por sistema) Extenor (integrado, file upload)
     */
    private Short origenDocumento;

    private Actividad actividad;

    private String textoParcial;
    /**
     * Objeto con estado transitorio siempre, la relación no existe
     */
    private Expediente expediente;

    private Set<Nota> notas;
    private Boolean esEnviado;
    private Set<RelacionDocumentoElemento> relacionElementos = new HashSet<RelacionDocumentoElemento>(
            0);
    private OficioEstructurado oficioEstructurado;
    /**
     * Cuando la solicitud es de otra institución, se asocia con la institución.
     */

    private ConfInstitucion confInstitucion;
    
    private Boolean esCompartido;
    private Set<RelacionDocumento> documentosRelacionados = new HashSet<RelacionDocumento>(
            0);
    // Constructors

    /** default constructor */
    public Documento(Long documentoId) {
    	this.documentoId = documentoId;
    }

    public Documento() {
    }
    /**
     * 
     * @param documentoId
     * @param nombreDocumento
     */
    public Documento(Long documentoId, String nombreDocumento, Long formaId,
            Long idTipoDoc, String nomTipoDoc, Date fecReg) {
        super();
        this.documentoId = documentoId;
        this.nombreDocumento = nombreDocumento;
        this.forma = new Forma(formaId);
        this.tipoDocumento = new Valor(idTipoDoc, nomTipoDoc);
        this.fechaCreacion = fecReg;
    }

    public Documento(Long idDoc, String nomDoc, Long idExp, String noExp,
            Long idCaso, String nCaso, Long idTipoDoc, String nomTipoDoc,
            Date fecReg, String folioDocumento, String descripcion) {
        this.documentoId = idDoc;
        this.nombreDocumento = nomDoc;
        this.tipoDocumento = new Valor(idTipoDoc, nomTipoDoc);
        this.expediente = new Expediente();
        this.expediente.setExpedienteId(idExp);
        this.expediente.setNumeroExpediente(noExp);
        final Caso caso = new Caso();
        caso.setCasoId(idCaso);
        caso.setNumeroGeneralCaso(nCaso);
        this.expediente.setCaso(caso);
        this.fechaCreacion = fecReg;
        this.folioDocumento = folioDocumento;
        this.descripcion = descripcion;
        // this.oficioEstructurado =oficio;
    }

    /** minimal constructor */
    public Documento(Long documentoId, Valor valor, String nombreDocumento,
            java.util.Date fechaCreacion) {
        this.documentoId = documentoId;
        this.tipoDocumento = valor;
        this.nombreDocumento = nombreDocumento;
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * @param documentoId
     *            : Identificador de la controversia resuelta
     * @param tipoDocumento
     * @param archivoDigital
     * @param nombreDocumento
     * @param fechaCreacion
     *            : Fecha de envío de la misma.
     * @param responsableDocumento
     *            : Nombre completo (nombre, apellido paterno, apellido materno)
     *            del fiscal que llevó a cabo la controversia
     * @param actividad
     * @param expediente
     *            : Número de caso
     * @param esEnviado
     *            : Bandera si ya ha sido leída la controversia
     */
    public Documento(Long documentoId, Valor tipoDocumento,
            ArchivoDigital archivoDigital, String nombreDocumento,
            Date fechaCreacion, Funcionario responsableDocumento,
            Actividad actividad, Boolean esEnviado) {
        super();
        this.documentoId = documentoId;
        this.tipoDocumento = tipoDocumento;
        this.archivoDigital = archivoDigital;
        this.nombreDocumento = nombreDocumento;
        this.fechaCreacion = fechaCreacion;
        this.responsableDocumento = responsableDocumento;
        this.actividad = actividad;
        this.esEnviado = esEnviado;
    }

    /** full constructor */
    public Documento(Long documentoId, Valor valor, String ifolioDoumento,
            String nombreDocumento, java.util.Date fechaCreacion,
            Double dcVersion, String inumeroFojas,
            Funcionario responsableDocumento, Short origenDocumento,
            Actividad actividad) {
        this.documentoId = documentoId;
        this.tipoDocumento = valor;
        this.folioDocumento = ifolioDoumento;
        this.nombreDocumento = nombreDocumento;
        this.fechaCreacion = fechaCreacion;
        this.version = dcVersion;
        this.numeroFojas = inumeroFojas;
        this.responsableDocumento = responsableDocumento;
        this.origenDocumento = origenDocumento;
        this.actividad = actividad;

    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Documento_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getDocumentoId() {
        return this.documentoId;
    }

    public void setDocumentoId(Long documentoId) {
        this.documentoId = documentoId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoDocumento_val", nullable = false)
    public Valor getTipoDocumento() {
        return this.tipoDocumento;
    }

    public void setTipoDocumento(Valor valor) {
        this.tipoDocumento = valor;
    }

    @Column(name = "iFolioDocumento", length = 20)
    public String getFolioDocumento() {
        return this.folioDocumento;
    }

    @Column(name = "esGuardadoParcial", precision = 1, scale = 0)
    public Boolean getEsGuardadoParcial() {
        return esGuardadoParcial;
    }
    
    public void setEsGuardadoParcial(Boolean esGuardadoParcial) {
        if (esGuardadoParcial == null)
            this.esGuardadoParcial = true;
        else
            this.esGuardadoParcial = esGuardadoParcial;
    }

    public void setFolioDocumento(String ifolioDoumento) {
        this.folioDocumento = ifolioDoumento;
    }

    @Column(name = "cNombreDocumento", nullable = false, length = 60)
    public String getNombreDocumento() {
        return this.nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    @Column(name = "dFechaCreacion", nullable = false, length = 23)
    public java.util.Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    public void setFechaCreacion(java.util.Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Column(name = "dcVersion", precision = 5)
    public Double getVersion() {
        return this.version;
    }

    public void setVersion(Double dcVersion) {
        this.version = dcVersion;
    }

    @Column(name = "iNumeroFojas", length = 20)
    public String getNumeroFojas() {
        return this.numeroFojas;
    }

    public void setNumeroFojas(String inumeroFojas) {
        this.numeroFojas = inumeroFojas;
    }

    @JoinColumn(name = "Funcionario_id")
    @ManyToOne
    public Funcionario getResponsableDocumento() {
        return this.responsableDocumento;
    }

    public void setResponsableDocumento(Funcionario responsableDocumento) {
        this.responsableDocumento = responsableDocumento;
    }

    @Column(name = "iOrigenDocumento", precision = 4, scale = 0)
    public Short getOrigenDocumento() {
        return this.origenDocumento;
    }

    public void setOrigenDocumento(Short origenDocumento) {
        this.origenDocumento = origenDocumento;
    }

    @Column(name = "cTextoParcial")
    public String getTextoParcial() {
        return textoParcial;
    }

    public void setTextoParcial(String textoParcial) {
        this.textoParcial = textoParcial;
    }

    /**
     * Método de acceso al campo forma.
     * 
     * @return El valor del campo forma
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Forma_id", nullable = false)
    public Forma getForma() {
        return forma;
    }

    /**
     * Asigna el valor al campo forma.
     * 
     * @param forma
     *            el valor forma a asignar
     */
    public void setForma(Forma forma) {
        this.forma = forma;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ArchivoDigital_id")
    public ArchivoDigital getArchivoDigital() {
        return this.archivoDigital;
    }

    public void setArchivoDigital(ArchivoDigital archivoDigital) {
        this.archivoDigital = archivoDigital;
    }

    /**
     * Método de acceso al campo expediente, usado únicamente para obgtener
     * información de BD.<br>
     * Se debe mantener <code>@Transient</code> siempre.
     * 
     * @return El valor del campo expediente
     */
    @Transient
    public Expediente getExpediente() {
        return expediente;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "documento")
    public Set<Nota> getNotas() {
        return notas;
    }

    public void setNotas(Set<Nota> notas) {
        this.notas = notas;
    }

    /**
     * Método de acceso al campo esEnviado.
     * 
     * @return El valor del campo esEnviado
     */
    @Column(name = "bEsEnviado", precision = 1, scale = 0)
    public Boolean getEsEnviado() {
        return esEnviado;
    }

    /**
     * Asigna el valor al campo esEnviado.
     * 
     * @param esEnviado
     *            el valor esEnviado a asignar
     */
    public void setEsEnviado(Boolean esEnviado) {
        this.esEnviado = esEnviado;
    }

    /**
     * Método de acceso al campo esModificable.
     * 
     * @return El valor del campo esModificable
     */
    @Column(name = "bEsModificable", precision = 1, scale = 0)
    public Boolean getEsModificable() {
        return esModificable;
    }

    /**
     * Asigna el valor al campo esModificable.
     * 
     * @param esModificable
     *            el valor esModificable a asignar
     */
    public void setEsModificable(Boolean esModificable) {
        this.esModificable = esModificable;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "documento")
    public Set<RelacionDocumentoElemento> getRelacionElementos() {
        return this.relacionElementos;
    }

    public void setRelacionElementos(
            Set<RelacionDocumentoElemento> relacionDocumentoElementos) {
        this.relacionElementos = relacionDocumentoElementos;
    }

    /**
     * Método de acceso al campo oficioEstructurado.
     * 
     * @return El valor del campo oficioEstructurado
     */
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "documento")
    public OficioEstructurado getOficioEstructurado() {
        return oficioEstructurado;
    }

    /**
     * Asigna el valor al campo oficioEstructurado.
     * 
     * @param oficioEstructurado
     *            el valor oficioEstructurado a asignar
     */
    public void setOficioEstructurado(OficioEstructurado oficioEstructurado) {
        this.oficioEstructurado = oficioEstructurado;
    }
    

    /**
     * Método de acceso al campo actividad.
     * 
     * @return El valor del campo actividad
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "documento")
    public Actividad getActividad() {
        return actividad;
    }

    /**
     * Asigna el valor al campo actividad.
     * 
     * @param actividad
     *            el valor actividad a asignar
     */
    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ConfInstitucion_id")
    public ConfInstitucion getConfInstitucion() {
        return this.confInstitucion;
    }

    public void setConfInstitucion(ConfInstitucion confInstitucion) {
        this.confInstitucion = confInstitucion;
    }

    /**
     * Método de acceso al campo esCompartido.
     * @return El valor del campo esCompartido
     */
    @Column(name = "bEsCompartido", precision = 1, scale = 0)
    public Boolean getEsCompartido() {
        return esCompartido;
    }

    /**
     * Asigna el valor al campo esCompartido.
     * @param esCompartido el valor esCompartido a asignar
     */
    public void setEsCompartido(Boolean esCompartido) {
        this.esCompartido = esCompartido;
    }
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "documentoPrincipal")
    public Set<RelacionDocumento> getDocumentosRelacionados() {
        return this.documentosRelacionados;
    }

    public void setDocumentosRelacionados(
            Set<RelacionDocumento> relacionDocumentosForDocumentoPrincipalId) {
        this.documentosRelacionados = relacionDocumentosForDocumentoPrincipalId;
    }

    @Column(name = "catDiscriminanteOrigen")
	public Long getCatDiscriminanteOrigen() {
		return catDiscriminanteOrigen;
	}

	public void setCatDiscriminanteOrigen(Long catDiscriminanteOrigen) {
		this.catDiscriminanteOrigen = catDiscriminanteOrigen;
	}

    @Column(name = "cClaveDiscriminanteOrigen", length = 3)
	public String getClaveDiscriminanteOrigen() {
		return claveDiscriminanteOrigen;
	}

	
	public void setClaveDiscriminanteOrigen(String claveDiscriminanteOrigen) {
		this.claveDiscriminanteOrigen = claveDiscriminanteOrigen;
	}

	@Column(name = "idFuncionarioSolicitante")
	public Long getIdFuncionarioSolicitante() {
		return idFuncionarioSolicitante;
	}

	public void setIdFuncionarioSolicitante(Long idFuncionarioSolicitante) {
		this.idFuncionarioSolicitante = idFuncionarioSolicitante;
	}
	
    @Column(name = "cDescripcion", length = 300)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
    
    
}

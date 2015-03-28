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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * ArchivoDigital entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "ArchivoDigital")
public class ArchivoDigital implements java.io.Serializable {

    // Fields

    private Long archivoDigitalId;
    private String nombreArchivo;
    private String tipoArchivo;
    private byte[] contenido;
    private Set<SolicitudAdjuntos> solicitudAdjuntoses = new HashSet<SolicitudAdjuntos>(
            0);
    private Set<Documento> documentos = new HashSet<Documento>(0);
    
    private Comentario comentario;
    
    // Constructors

    /** default constructor */
    public ArchivoDigital() {
    }

    /** minimal constructor */
    public ArchivoDigital(Long archivoDigitalId, String cnombreArchivo,
            String ctipoArchivo) {
        this.archivoDigitalId = archivoDigitalId;
        this.nombreArchivo = cnombreArchivo;
        this.tipoArchivo = ctipoArchivo;
    }
    /** minimal constructor */
    public ArchivoDigital(Long archivoDigitalId) {
        this.archivoDigitalId = archivoDigitalId;
    }
    public ArchivoDigital(Long archivoDigitalId, String cnombreArchivo,
            String ctipoArchivo, byte[] contenido ) {
        this.archivoDigitalId = archivoDigitalId;
        this.nombreArchivo = cnombreArchivo;
        this.tipoArchivo = ctipoArchivo;
        this.contenido = contenido;
    }
    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ArchivoDigital_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getArchivoDigitalId() {
        return this.archivoDigitalId;
    }

    public void setArchivoDigitalId(Long archivoDigitalId) {
        this.archivoDigitalId = archivoDigitalId;
    }

    @Column(name = "cnombreArchivo", nullable = false, length = 60)
    public String getNombreArchivo() {
        return this.nombreArchivo;
    }

    public void setNombreArchivo(String cnombreArchivo) {
        this.nombreArchivo = cnombreArchivo;
    }

    @Column(name = "ctipoArchivo", nullable = false, length = 10)
    public String getTipoArchivo() {
        return this.tipoArchivo;
    }

    public void setTipoArchivo(String ctipoArchivo) {
        this.tipoArchivo = ctipoArchivo;
    }

    @Column(name = "bContenido", nullable = false)
    public byte[] getContenido() {
        return this.contenido;
    }

    public void setContenido(byte[] bcontenido) {
        this.contenido = bcontenido;
    }


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "archivoDigital")
    public Set<SolicitudAdjuntos> getSolicitudAdjuntoses() {
        return this.solicitudAdjuntoses;
    }

    public void setSolicitudAdjuntoses(
            Set<SolicitudAdjuntos> solicitudAdjuntoses) {
        this.solicitudAdjuntoses = solicitudAdjuntoses;
    }
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "archivoDigital")
    public Set<Documento> getDocumentos() {
        return this.documentos;
    }

    public void setDocumentos(Set<Documento> documentos) {
        this.documentos = documentos;
    }

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Comentario_id")
	public Comentario getComentario() {
		return comentario;
	}

	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}
}
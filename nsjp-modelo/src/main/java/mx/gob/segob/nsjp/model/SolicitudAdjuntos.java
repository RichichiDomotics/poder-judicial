package mx.gob.segob.nsjp.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entidad usada para cuando una solicitud tiene archivos adjuntos.
 */
@Entity
@Table(name = "SolicitudAdjuntos")
public class SolicitudAdjuntos implements java.io.Serializable {

    // Fields

    private SolicitudAdjuntosId id;
    private Solicitud solicitud;
    private ArchivoDigital archivoDigital;

    // Constructors

    /** default constructor */
    public SolicitudAdjuntos() {
    }

    /** full constructor */
    public SolicitudAdjuntos(SolicitudAdjuntosId id, Solicitud solicitud,
            ArchivoDigital archivoDigital) {
        this.id = id;
        this.solicitud = solicitud;
        this.archivoDigital = archivoDigital;
    }

    // Property accessors
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "solicitudId", column = @Column(name = "Solicitud_id", nullable = false, precision = 18, scale = 0)),
            @AttributeOverride(name = "archivoDigitalId", column = @Column(name = "ArchivoDigital_id", nullable = false, precision = 18, scale = 0))})
    public SolicitudAdjuntosId getId() {
        return this.id;
    }

    public void setId(SolicitudAdjuntosId id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Solicitud_id", nullable = false, insertable = false, updatable = false)
    public Solicitud getSolicitud() {
        return this.solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ArchivoDigital_id", nullable = false, insertable = false, updatable = false)
    public ArchivoDigital getArchivoDigital() {
        return this.archivoDigital;
    }

    public void setArchivoDigital(ArchivoDigital archivoDigital) {
        this.archivoDigital = archivoDigital;
    }

}
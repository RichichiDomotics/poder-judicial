package mx.gob.segob.nsjp.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entidad para la configuración de las instituciones, usada para las
 * solicitudes, acuses y notificaciones, el valor más importante de esta entidad
 * es la URL (o IP) a donde se debe conectar la aplicación para contestar a
 * otras instituciones que también tiene una instancia de NSJP.<br>
 * ConfInstitucion entity. @author vaguirre
 */
@Entity
@Table(name = "ConfInstitucion")
public class ConfInstitucion implements java.io.Serializable {

    // Fields

    private Long confInstitucionId;
    private String clave;
    private String nombreInst;
    private String urlInst;
    private Boolean esInstalacionActual;
    private String monograma;

    // Constructors

    /** default constructor */
    public ConfInstitucion() {
    }

    /** minimal constructor */
    public ConfInstitucion(Long confInstitucionId, String cnombreInst) {
        this.confInstitucionId = confInstitucionId;
        this.nombreInst = cnombreInst;
    }

    /** full constructor */
    public ConfInstitucion(Long confInstitucionId, String cclave,
            String cnombreInst, String curlInst) {
        this.confInstitucionId = confInstitucionId;
        this.clave = cclave;
        this.nombreInst = cnombreInst;
        this.urlInst = curlInst;
    }
    
    /** full constructor */
    public ConfInstitucion(Long confInstitucionId, 
            String cnombreInst, String curlInst) {
        this.confInstitucionId = confInstitucionId;
        this.nombreInst = cnombreInst;
        this.urlInst = curlInst;
    }


    public ConfInstitucion(Long confInstitucionId) {
        this.confInstitucionId = confInstitucionId;
    }

    // Property accessors
    @Id
    @Column(name = "ConfInstitucion_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getConfInstitucionId() {
        return this.confInstitucionId;
    }

    public void setConfInstitucionId(Long confInstitucionId) {
        this.confInstitucionId = confInstitucionId;
    }

    @Column(name = "cClave", nullable = false, length = 15)
    public String getClave() {
        return this.clave;
    }

    public void setClave(String cclave) {
        this.clave = cclave;
    }

    @Column(name = "cNombreInst", nullable = false, length = 100)
    public String getNombreInst() {
        return this.nombreInst;
    }

    public void setNombreInst(String cnombreInst) {
        this.nombreInst = cnombreInst;
    }

    @Column(name = "cUrlInst", nullable = false, length = 100)
    public String getUrlInst() {
        return this.urlInst;
    }

    public void setUrlInst(String curlInst) {
        this.urlInst = curlInst;
    }

    /**
     * Método de acceso al campo esInstalacionActual.
     * 
     * @return El valor del campo esInstalacionActual
     */
    @Column(name = "bEsInstalacionActual", nullable = false, precision = 1, scale = 0)
    public Boolean getEsInstalacionActual() {
        return esInstalacionActual;
    }

    /**
     * Asigna el valor al campo esInstalacionActual.
     * 
     * @param esInstalacionActual
     *            el valor esInstalacionActual a asignar
     */
    public void setEsInstalacionActual(Boolean esInstalacionActual) {
        this.esInstalacionActual = esInstalacionActual;
    }

    /**
     * Método de acceso al campo monograma.
     * @return El valor del campo monograma
     */
    @Column(name = "cMonograma", nullable = true, length = 2)
    public String getMonograma() {
        return monograma;
    }

    /**
     * Asigna el valor al campo monograma.
     * @param monograma el valor monograma a asignar
     */
    public void setMonograma(String monograma) {
        this.monograma = monograma;
    }
}
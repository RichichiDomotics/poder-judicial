package mx.gob.segob.nsjp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * AvisoDetencion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "AvisoDetencion")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "AvisoDetencion_id")
public class AvisoDetencion extends Notificacion {

    // Fields

    private String detenido;
    private Detencion detencion;
    private Set<AvisoDetencionDelito> avisoDetencionDelitos = new HashSet<AvisoDetencionDelito>();
    /**
     * Para cuando se atiende el avise de detención se debe asociar a un
     * expediente.
     */
    private Expediente expediente;
    // Constructors

    /** default constructor */
    public AvisoDetencion() {
    }

    @Column(name = "cDetenido", length = 100)
    public String getDetenido() {
        return this.detenido;
    }

    public void setDetenido(String cdetenido) {
        this.detenido = cdetenido;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Detencion_id")
    public Detencion getDetencion() {
        return this.detencion;
    }

    public void setDetencion(Detencion detencion) {
        this.detencion = detencion;
    }

    /**
     * Método de acceso al campo avisoDetencionDelitos.
     * 
     * @return El valor del campo avisoDetencionDelitos
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "avisoDetencion")
    public Set<AvisoDetencionDelito> getAvisoDetencionDelitos() {
        return avisoDetencionDelitos;
    }

    /**
     * Asigna el valor al campo avisoDetencionDelitos.
     * 
     * @param avisoDetencionDelitos
     *            el valor avisoDetencionDelitos a asignar
     */
    public void setAvisoDetencionDelitos(
            Set<AvisoDetencionDelito> avisoDetencionDelitos) {
        this.avisoDetencionDelitos = avisoDetencionDelitos;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Expediente_id")
    public Expediente getExpediente() {
        return this.expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

}
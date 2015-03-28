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
 * RolFuncion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RolFuncion")
public class RolFuncion implements java.io.Serializable {

    // Fields

    private RolFuncionId id;
    private Funcion funcion;
    private Rol rol;

    // Constructors

    /** default constructor */
    public RolFuncion() {
    }

    /** full constructor */
    public RolFuncion(RolFuncionId id, Funcion funcion, Rol rol) {
        this.id = id;
        this.funcion = funcion;
        this.rol = rol;
    }

    // Property accessors
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "rolId", column = @Column(name = "Rol_id", nullable = false, precision = 18, scale = 0)),
            @AttributeOverride(name = "funcionId", column = @Column(name = "Funcion_id", nullable = false, precision = 18, scale = 0))})
    public RolFuncionId getId() {
        return this.id;
    }

    public void setId(RolFuncionId id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Funcion_id", nullable = false, insertable = false, updatable = false)
    public Funcion getFuncion() {
        return this.funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Rol_id", nullable = false, insertable = false, updatable = false)
    public Rol getRol() {
        return this.rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

}
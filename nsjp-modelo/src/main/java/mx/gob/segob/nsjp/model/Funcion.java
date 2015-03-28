package mx.gob.segob.nsjp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;

/**
 * Funcion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Funcion", uniqueConstraints = @UniqueConstraint(columnNames = "cNombreFuncion"))
public class Funcion implements java.io.Serializable {

    // Fields

    /**
	 * 
	 */
	private static final long serialVersionUID = 3858965997663391989L;
	private Long funcionId;
    private String nombreFuncion;
    private String descripcionFuncion;
    private Set<RolFuncion> rolFunciones = new HashSet<RolFuncion>(0);
    private List <Modulo> modulos = new ArrayList<Modulo>();

    // Constructors

    /** default constructor */
    public Funcion() {
    }
    
    /** minimal constructor */
    public Funcion(Long funcionId){
    	this.funcionId=funcionId;
    }
    /**
     * Constructor de String
     * @param nombreFuncion
     */
    public Funcion (String nombreFuncion){
    	this.nombreFuncion=nombreFuncion;
    }
    public Funcion (String nombreFuncion, String cdescripcionFuncion){
    	this.nombreFuncion=nombreFuncion;
    	this.descripcionFuncion = cdescripcionFuncion;
    }
    
    /** minimal constructor */
    public Funcion(Long funcionId, String cnombreFuncion,
            String cdescripcionFuncion) {
        this.funcionId = funcionId;
        
        this.nombreFuncion = cnombreFuncion;
        this.descripcionFuncion = cdescripcionFuncion;
    }

    /** full constructor */
    public Funcion(Long funcionId, String cnombreFuncion,
            String cdescripcionFuncion, Set<RolFuncion> rolFuncions) {
        this.funcionId = funcionId;
        this.nombreFuncion = cnombreFuncion;
        this.descripcionFuncion = cdescripcionFuncion;
        this.rolFunciones = rolFuncions;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "Funcion_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getFuncionId() {
        return this.funcionId;
    }

    public void setFuncionId(Long funcionId) {
        this.funcionId = funcionId;
    }

    @Column(name = "cNombreFuncion", unique = true, nullable = false, length = 200)
    public String getNombreFuncion() {
        return this.nombreFuncion;
    }

    public void setNombreFuncion(String cnombreFuncion) {
        this.nombreFuncion = cnombreFuncion;
    }

    @Column(name = "cDescripcionFuncion", nullable = false, length = 200)
    public String getDescripcionFuncion() {
        return this.descripcionFuncion;
    }

    public void setDescripcionFuncion(String cdescripcionFuncion) {
        this.descripcionFuncion = cdescripcionFuncion;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "funcion")
    public Set<RolFuncion> getRolFunciones() {
        return this.rolFunciones;
    }

    public void setRolFunciones(Set<RolFuncion> rolFuncions) {
        this.rolFunciones = rolFuncions;
    }

	/**
	 * @return the modulos
	 */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ModuloFuncion",
    joinColumns = { @JoinColumn(name = "Funcion_id", updatable = true) },
    inverseJoinColumns = { @JoinColumn(name = "Modulo_id", updatable = true) })
	public List<Modulo> getModulos() {
		return modulos;
	}

	/**
	 * @param modulos the modulos to set
	 */
	public void setModulos(List<Modulo> modulos) {
		this.modulos = modulos;
	}

}
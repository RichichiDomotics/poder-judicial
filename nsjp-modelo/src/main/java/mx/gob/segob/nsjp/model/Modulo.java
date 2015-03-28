/**

 * 
 */
package mx.gob.segob.nsjp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;

/**
 * @author LuisMG
 *
 */
@Entity
@Table(name = "Modulo", uniqueConstraints = @UniqueConstraint(columnNames = "cNombreModulo"))
public class Modulo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7118989137996328892L;
	private Long moduloId;
    private String nombreModulo;
    private String descripcionModulo;
    private List<Funcion> funciones = new ArrayList<Funcion>();
    private List<Rol> roles = new ArrayList<Rol>();
    
    /**
     * Constructor por default
     */
    public Modulo(){
    	
    }

	/**
	 * @return the moduloId
	 */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "Modulo_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getModuloId() {
		return moduloId;
	}

	/**
	 * @return the nombreModulo
	 */
    @Column(name = "cNombreModulo", unique = true, nullable = false, length = 200)
	public String getNombreModulo() {
		return nombreModulo;
	}

	/**
	 * @return the descripcionModulo
	 */
    @Column(name = "cDescripcionModulo", nullable = false, length = 200)
	public String getDescripcionModulo() {
		return descripcionModulo;
	}

    /**
	 * @return the funciones
	 */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ModuloFuncion",
    joinColumns = { @JoinColumn(name = "Modulo_id", updatable = true) },
    inverseJoinColumns = { @JoinColumn(name = "Funcion_id", updatable = true) })
   	public List<Funcion> getFunciones() {
		return funciones;
	}
    
	/**
	 * @return the roles
	 */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "RolModulo",
    joinColumns = { @JoinColumn(name = "Modulo_id", updatable = true) },
    inverseJoinColumns = { @JoinColumn(name = "Rol_id", updatable = true) })
	public List<Rol> getRoles() {
		return roles;
	}

    
	/**
	 * @param moduloId the moduloId to set
	 */
	public void setModuloId(Long moduloId) {
		this.moduloId = moduloId;
	}

	/**
	 * @param nombreModulo the nombreModulo to set
	 */
	public void setNombreModulo(String nombreModulo) {
		this.nombreModulo = nombreModulo;
	}

	/**
	 * @param descripcionModulo the descripcionModulo to set
	 */
	public void setDescripcionModulo(String descripcionModulo) {
		this.descripcionModulo = descripcionModulo;
	}

	

	/**
	 * @param funciones the funciones to set
	 */
	public void setFunciones(List<Funcion> funciones) {
		this.funciones = funciones;
	}


	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}
   
}

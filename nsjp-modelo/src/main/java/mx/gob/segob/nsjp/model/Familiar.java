package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Familiar entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Familiar")
public class Familiar implements java.io.Serializable {

    // Fields

    private Long familiarId;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Short edad;
	private Valor escolaridad;
	private Valor estadoCivil;
	private Valor ocupacion;
	//Representa el parentesco de una persona
	private CatRelacion relacion;
	private EntrevistaComplementaria entrevistaComplementaria;

    // Constructors

    /** default constructor */
    public Familiar() {
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Familiar_id", unique = true, nullable = false, precision = 18, scale = 0)
    public Long getFamiliarId() {
        return this.familiarId;
    }

    public void setFamiliarId(Long familiarId) {
        this.familiarId = familiarId;
    }

    @Column(name = "cNombre", length = 50)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "cApellidoPaterno", length = 50)
    public String getApellidoPaterno() {
        return this.apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    @Column(name = "cApellidoMaterno", length = 50)
    public String getApellidoMaterno() {
        return this.apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

	/**
	 * Método de acceso al campo edad.
	 * @return El valor del campo edad
	 */
    @Column(name = "iEdad", precision = 4, scale = 0)
	public Short getEdad() {
		return edad;
	}

	/**
	 * Asigna el valor al campo edad.
	 * @param edad el valor edad a asignar
	 */
	public void setEdad(Short edad) {
		this.edad = edad;
	}

	/**
	 * Método de acceso al campo escolaridad.
	 * @return El valor del campo escolaridad
	 */
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Escolaridad_val")
	public Valor getEscolaridad() {
		return escolaridad;
	}

	/**
	 * Asigna el valor al campo escolaridad.
	 * @param escolaridad el valor escolaridad a asignar
	 */
	public void setEscolaridad(Valor escolaridad) {
		this.escolaridad = escolaridad;
	}

	/**
	 * Método de acceso al campo estadoCivil.
	 * @return El valor del campo estadoCivil
	 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EstadoCivil_val")
	public Valor getEstadoCivil() {
		return estadoCivil;
	}

	/**
	 * Asigna el valor al campo estadoCivil.
	 * @param estadoCivil el valor estadoCivil a asignar
	 */
	public void setEstadoCivil(Valor estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	/**
	 * Método de acceso al campo ocupacion.
	 * @return El valor del campo ocupacion
	 */
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Ocupacion_val")
	public Valor getOcupacion() {
		return ocupacion;
	}

	/**
	 * Asigna el valor al campo ocupacion.
	 * @param ocupacion el valor ocupacion a asignar
	 */
	public void setOcupacion(Valor ocupacion) {
		this.ocupacion = ocupacion;
	}

	/**
	 * Método de acceso al campo entrevistaComplementaria.
	 * @return El valor del campo entrevistaComplementaria
	 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EntrevistaComplementaria_id")
	public EntrevistaComplementaria getEntrevistaComplementaria() {
		return entrevistaComplementaria;
	}

	/**
	 * Asigna el valor al campo entrevistaComplementaria.
	 * @param entrevistaComplementaria el valor entrevistaComplementaria a asignar
	 */
	public void setEntrevistaComplementaria(
			EntrevistaComplementaria entrevistaComplementaria) {
		this.entrevistaComplementaria = entrevistaComplementaria;
	}

	/**
	 * Método de acceso al campo relacion.
	 * @return El valor del campo relacion
	 */
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CatRelacion_id")
	public CatRelacion getRelacion() {
		return relacion;
	}

	/**
	 * Asigna el valor al campo relacion.
	 * @param relacion el valor relacion a asignar
	 */
	public void setRelacion(CatRelacion relacion) {
		this.relacion = relacion;
	}
	
}

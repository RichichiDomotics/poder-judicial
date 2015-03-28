package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * AreaGeografica entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "AreaGeografica" )
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "AreaGeografica_id")
public class AreaGeografica extends Lugar {

	// Fields
	
	private String nombre;
	private String referencias;

	// Constructors

	/** default constructor */
	public AreaGeografica() {
	}
	
	/** full constructor */
	public AreaGeografica(String nombre,
			String referencias) {		
		this.nombre = nombre;
		this.referencias = referencias;
	}

	// Property accessors	

	@Column(name = "cNombre", length = 60)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "cReferencias", length = 150)
	public String getReferencias() {
		return this.referencias;
	}

	public void setReferencias(String referencias) {
		this.referencias = referencias;
	}

}

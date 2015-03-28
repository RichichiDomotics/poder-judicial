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
 * Animal entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Animal" )
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "Animal_id")
public class Animal extends Objeto {

	// Fields

	private Valor tipoAnimal;
	private String estadoAnimal;
	private String razaAnimal;

	// Constructors

	/** default constructor */
	public Animal() {
	}



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoAnimal_val")
	public Valor getTipoAnimal() {
		return this.tipoAnimal;
	}

	public void setTipoAnimal(Valor ctipoAnimal) {
		this.tipoAnimal = ctipoAnimal;
	}

	@Column(name = "cEstadoAnimal", length = 7)
	public String getEstadoAnimal() {
		return this.estadoAnimal;
	}

	public void setEstadoAnimal(String cestadoAnimal) {
		this.estadoAnimal = cestadoAnimal;
	}

	@Column(name = "cRazaAnimal", length = 15)
	public String getRazaAnimal() {
		return this.razaAnimal;
	}

	public void setRazaAnimal(String crazaAnimal) {
		this.razaAnimal = crazaAnimal;
	}

}
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
 * EspacioAereo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "EspacioAereo" )
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "EspacioAereo_id")
public class EspacioAereo extends Lugar {

	// Fields	
	private String lineaAerea;
	private String ruta;

	// Constructors

	/** default constructor */
	public EspacioAereo() {
	}
	
	/** full constructor */
	public EspacioAereo(String lineaAerea,
			String ruta) {				
		this.lineaAerea = lineaAerea;
		this.ruta = ruta;
	}

	// Property accessors	
	@Column(name = "cLineaAerea", length = 50)
	public String getLineaAerea() {
		return this.lineaAerea;
	}

	public void setLineaAerea(String lineaAerea) {
		this.lineaAerea = lineaAerea;
	}

	@Column(name = "cRuta", length = 50)
	public String getRuta() {
		return this.ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

}

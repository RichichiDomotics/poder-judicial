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
 * EspacioMaritimo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "EspacioMaritimo" )
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "EspacioMaritimo_id")
public class EspacioMaritimo extends Lugar {

	// Fields
	private String zonaCostera;
	private String puerto;
	private String zonaMaritima;

	// Constructors

	/** default constructor */
	public EspacioMaritimo() {
	}	

	/** full constructor */
	public EspacioMaritimo(String zonaCostera, String puerto, String zonaMaritima) {		
		this.zonaCostera = zonaCostera;
		this.puerto = puerto;
		this.zonaMaritima = zonaMaritima;
	}

	// Property accessors	
	@Column(name = "cZonaCostera", length = 50)
	public String getZonaCostera() {
		return this.zonaCostera;
	}

	public void setZonaCostera(String zonaCostera) {
		this.zonaCostera = zonaCostera;
	}

	@Column(name = "cPuerto", length = 50)
	public String getPuerto() {
		return this.puerto;
	}

	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}

	@Column(name = "cZonaMaritima", length = 50)
	public String getZonaMaritima() {
		return this.zonaMaritima;
	}

	public void setZonaMaritima(String zonaMaritima) {
		this.zonaMaritima = zonaMaritima;
	}

}

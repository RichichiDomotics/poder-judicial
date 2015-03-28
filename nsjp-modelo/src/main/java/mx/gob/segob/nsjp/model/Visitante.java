/**
 * 
 */
package mx.gob.segob.nsjp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.UniqueConstraint;

/**
 * @author LuisMG
 * 
 */
@Entity
@Table(name = "Visitante", uniqueConstraints = { @UniqueConstraint(columnNames = "Visitante_id") })
public class Visitante implements java.io.Serializable {

	// Atributos
	private Long visitanteId;
	private String cIp;
	private String cMac;
	private Integer iIntentos;

	// Constructores
	/**
	 * Constructor por default
	 */
	public Visitante() {
	}

	/**
	 * Constructor Mínimo
	 * @param visitanteId
	 * @param cIP
	 */
	public Visitante(Long visitanteId,String cIp){
		this.visitanteId=visitanteId;
		this.cIp=cIp;
	}
	
	/**
	 * Constructor Completo
	 * @param visitanteId
	 * @param cIp
	 * @param cMac
	 * @param iIntentos
	 */
	public Visitante(Long visitanteId,String cIp,String cMac, Integer iIntentos){
		this.visitanteId=visitanteId;
		this.cIp=cIp;
		this.cMac=cMac;
		this.iIntentos=iIntentos;
	}

	/**
	 * @return the visitanteId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Visitante_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getVisitanteId() {
		return visitanteId;
	}

	/**
	 * @param visitanteId the visitanteId to set
	 */
	public void setVisitanteId(Long visitanteId) {
		this.visitanteId = visitanteId;
	}

	/**
	 * @return the cIp
	 */
	@Column(name = "cIP")
	public String getcIp() {
		return cIp;
	}

	/**
	 * @param cIp the cIp to set
	 */
	public void setcIp(String cIp) {
		this.cIp = cIp;
	}

	/**
	 * @return the cMac
	 */
	@Column(name = "cMac")
	public String getcMac() {
		return cMac;
	}

	/**
	 * @param cMac the cMac to set
	 */
	public void setcMac(String cMac) {
		this.cMac = cMac;
	}

	/**
	 * @return the iIntentos
	 */
	@Column(name = "iIntentos")
	public Integer getiIntentos() {
		return iIntentos;
	}

	/**
	 * @param iIntentos the iIntentos to set
	 */
	public void setiIntentos(Integer iIntentos) {
		this.iIntentos = iIntentos;
	}
	
	// Getters y Setters
	
	
}

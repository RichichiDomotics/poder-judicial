/**
* Nombre del Programa : Sala.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 17/10/2011
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
* Programa Dependiente  :N/A
* Programa Subsecuente :N/A
* Cond. de ejecucion        :N/A
* Dias de ejecucion          :N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor                       :N/A
* Compania               :N/A
* Proyecto                 :N/A                                 Fecha: N/A
* Modificacion           :N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entidad que representa la sala, ya sea Sala Audiencia o 
 * Sala Temporal.
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
@Entity
@Table(name = "Sala")
public class Sala implements java.io.Serializable {
	
	// Fields
	private Long salaId;
	private String domicilioSala;
	private String ubicacionSala;
	private Boolean esActivo;
    
	// Relaciones
	private Set<Audiencia> audiencias = new HashSet<Audiencia>(0);
	
	// Constructores
	public Sala(){
	}
	public Sala(Long salaId) {
		this.salaId = salaId;
	}

	public Sala(Long salaId, String domicilioSala, String ubicacionSala,
			Boolean esActivo) {
		this.salaId = salaId;
		this.domicilioSala = domicilioSala;
		this.ubicacionSala = ubicacionSala;
		this.esActivo = esActivo;
	}

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "Sala_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getSalaId() {
		return salaId;
	}

	public void setSalaId(Long salaId) {
		this.salaId = salaId;
	}

	@Column(name = "cDomicilioSala", nullable = false, length = 150)
	public String getDomicilioSala() {
		return domicilioSala;
	}

	public void setDomicilioSala(String domicilioSala) {
		this.domicilioSala = domicilioSala;
	}

	@Column(name = "cUbicacionSala", nullable = false, length = 200)
	public String getUbicacionSala() {
		return ubicacionSala;
	}

	public void setUbicacionSala(String ubicacionSala) {
		this.ubicacionSala = ubicacionSala;
	}

	@Column(name = "bEsActivo", precision = 1, scale = 0)
	public Boolean getEsActivo() {
		return esActivo;
	}
	
	public void setEsActivo(Boolean esActivo) {
		this.esActivo = esActivo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sala")	
	public Set<Audiencia> getAudiencias() {
		return audiencias;
	}

	public void setAudiencias(Set<Audiencia> audiencias) {
		this.audiencias = audiencias;
	}
}

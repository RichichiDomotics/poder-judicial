/**
* Nombre del Programa : SeniaParticular.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 07/07/2011
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

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Clase que contiene las operaciones y los atributos para realizar la función 
 * asociada a las señas particulares de una persona.
 * 
 * Un tipo de seña particular de un individuo es un rasgo físico poco común en las personas. 
 * 
 * Algunas de las señas particulares son: tatuajes, cicatrices, perforaciones, 
 * defectos físicos (cojo, bizco, tuerto, manco, etc.) lunares, lesiones, quemadura, operación.
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
@Entity
@Table(name="SeniaParticular")
public class SeniaParticular implements Serializable{

	private Long seniaParticularId;

	private MediaFiliacion  mediaFiliacion = null;
	
	private Boolean tieneCicatrices;
	private String descripcionCicatrices;
	
	private Boolean tieneProtesis;
	private String descripcionProtesis;
	
	private Boolean tieneTatuajes;
	private String descripcionTatuajes;
	
	private Boolean tieneLunares;
	private String descripcionLunares;
	
	private Boolean tieneDefectosFisicos;
	private String descripcionDefectosFisicos;

	private Boolean tieneOtraSenia;
	private String descripcionOtraSenia;

	
	 // Constructors

    /** default constructor */
	public SeniaParticular(){
		
	}
	
	/**
	 * 
	 * @param seniaParticularId
	 */
	public SeniaParticular(Long seniaParticularId ){
		this.seniaParticularId = seniaParticularId;
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SeniaParticular_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getSeniaParticularId() {
		return seniaParticularId;
	}

	public void setSeniaParticularId(Long seniaParticularId) {
		this.seniaParticularId = seniaParticularId;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MediaFiliacion_id",  nullable = false)	
	public MediaFiliacion getMediaFiliacion() {
		return mediaFiliacion;
	}

	public void setMediaFiliacion(MediaFiliacion mediaFiliacion) {
		this.mediaFiliacion = mediaFiliacion;
	}

	@Column(name = "bTieneCicatrices", precision = 1, scale = 0)
	public Boolean getTieneCicatrices() {
		return tieneCicatrices;
	}

	public void setTieneCicatrices(Boolean tieneCicatrices) {
		this.tieneCicatrices = tieneCicatrices;
	}

	@Column(name = "cDescripcionCicatrices", length = 300)
	public String getDescripcionCicatrices() {
		return descripcionCicatrices;
	}

	public void setDescripcionCicatrices(String descripcionCicatrices) {
		this.descripcionCicatrices = descripcionCicatrices;
	}

	@Column(name = "bTieneProtesis", precision = 1, scale = 0)
	public Boolean getTieneProtesis() {
		return tieneProtesis;
	}

	public void setTieneProtesis(Boolean tieneProtesis) {
		this.tieneProtesis = tieneProtesis;
	}

	@Column(name = "cDescripcionProtesis", length = 300)
	public String getDescripcionProtesis() {
		return descripcionProtesis;
	}

	public void setDescripcionProtesis(String descripcionProtesis) {
		this.descripcionProtesis = descripcionProtesis;
	}

	@Column(name = "bTieneTatuajes", precision = 1, scale = 0)	
	public Boolean getTieneTatuajes() {
		return tieneTatuajes;
	}

	public void setTieneTatuajes(Boolean tieneTatuajes) {
		this.tieneTatuajes = tieneTatuajes;
	}

	@Column(name = "cDescripcionTatuajes", length = 300)
	public String getDescripcionTatuajes() {
		return descripcionTatuajes;
	}

	public void setDescripcionTatuajes(String descripcionTatuajes) {
		this.descripcionTatuajes = descripcionTatuajes;
	}

	@Column(name = "bTieneLunares", precision = 1, scale = 0)	
	public Boolean getTieneLunares() {
		return tieneLunares;
	}

	public void setTieneLunares(Boolean tieneLunares) {
		this.tieneLunares = tieneLunares;
	}

	@Column(name = "cDescripcionLunares", length = 300)
	public String getDescripcionLunares() {
		return descripcionLunares;
	}

	public void setDescripcionLunares(String descripcionLunares) {
		this.descripcionLunares = descripcionLunares;
	}

	@Column(name = "bTieneDefectosFisicos", precision = 1, scale = 0)
	public Boolean getTieneDefectosFisicos() {
		return tieneDefectosFisicos;
	}

	public void setTieneDefectosFisicos(Boolean tieneDefectosFisicos) {
		this.tieneDefectosFisicos = tieneDefectosFisicos;
	}

	@Column(name = "cDescripcionDefectosFisicos", length = 300)
	public String getDescripcionDefectosFisicos() {
		return descripcionDefectosFisicos;
	}

	public void setDescripcionDefectosFisicos(String descripcionDefectosFisicos) {
		this.descripcionDefectosFisicos = descripcionDefectosFisicos;
	}

	@Column(name = "bTieneOtraSenia", precision = 1, scale = 0)	
	public Boolean getTieneOtraSenia() {
		return tieneOtraSenia;
	}

	public void setTieneOtraSenia(Boolean tieneOtraSenia) {
		this.tieneOtraSenia = tieneOtraSenia;
	}

	@Column(name = "cDescripcionOtraSenia", length = 300)
	public String getDescripcionOtraSenia() {
		return descripcionOtraSenia;
	}

	public void setDescripcionOtraSenia(String descripcionOtraSenia) {
		this.descripcionOtraSenia = descripcionOtraSenia;
	}
}

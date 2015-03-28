/**
 * 
 */
package mx.gob.segob.nsjp.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author adrian
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "FechaCompArchDig")
public class FechaCompArchDig implements Serializable {

	private FechaCompArchDigId id;
	private FechaCompromiso fechaCompromiso;
	private ArchivoDigital archivoDigital;

	/**
	 * @param id the id to set
	 */
	public void setId(FechaCompArchDigId id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "fechaCompromisoId", column = @Column(name = "FechaCompromiso_id", nullable = false, precision = 18, scale = 0)),
			@AttributeOverride(name = "archivoDigitalId", column = @Column(name = "ArchivoDigital_id", nullable = false, precision = 18, scale = 0)) })
	public FechaCompArchDigId getId() {
		return id;
	}

	/**
	 * @param fechaCompromisoId the fechaCompromisoId to set
	 */
	public void setFechaCompromiso(FechaCompromiso fechaCompromisoId) {
		this.fechaCompromiso = fechaCompromisoId;
	}

	/**
	 * @return the fechaCompromisoId
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FechaCompromiso_id", nullable = false, insertable = false, updatable = false)
	public FechaCompromiso getFechaCompromiso() {
		return fechaCompromiso;
	}

	/**
	 * @param archivoDigitalId the archivoDigitalId to set
	 */
	public void setArchivoDigital(ArchivoDigital archivoDigitalId) {
		this.archivoDigital = archivoDigitalId;
	}

	/**
	 * @return the archivoDigitalId
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ArchivoDigital_id", nullable = false, insertable = false, updatable = false)
	public ArchivoDigital getArchivoDigital() {
		return archivoDigital;
	}
}

/**
 * 
 */
package mx.gob.segob.nsjp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author adrian
 *
 */
@SuppressWarnings("serial")
@Embeddable
public class FechaCompArchDigId implements Serializable {
	
	private FechaCompromiso fechaCompromisoId;
	private ArchivoDigital archivoDigitalId;
	
	
	public FechaCompArchDigId() {
		super();
	}
	public FechaCompArchDigId(FechaCompromiso fechaCompromiso_id,
			ArchivoDigital archivoDigital_id) {
		super();
		this.fechaCompromisoId = fechaCompromiso_id;
		this.archivoDigitalId = archivoDigital_id;
	}
	/**
	 * @return the fechaCompromiso_id
	 */
	@Column(name = "FechaCompromiso_id", nullable = false, precision = 18, scale = 0)
	public FechaCompromiso getFechaCompromisoId() {
		return fechaCompromisoId;
	}
	/**
	 * @param fechaCompromiso_id the fechaCompromiso_id to set
	 */
	public void setFechaCompromisoId(FechaCompromiso fechaCompromiso_id) {
		this.fechaCompromisoId = fechaCompromiso_id;
	}
	/**
	 * @return the archivoDigital_id
	 */
	@Column(name = "ArchivoDigital_id", nullable = false, precision = 18, scale = 0)
	public ArchivoDigital getArchivoDigitalId() {
		return archivoDigitalId;
	}
	/**
	 * @param archivoDigital_id the archivoDigital_id to set
	 */
	public void setArchivoDigitalId(ArchivoDigital archivoDigital_id) {
		this.archivoDigitalId = archivoDigital_id;
	}

	
}

/**
* Nombre del Programa : NotaExpediente.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 07/07/2011
* Marca de cambio        : N/A
* Descripcion General    : Permite asociar notas a un expediente
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
 * Clase que permite asociar notas a un expediente
 * @version 1.0
 * @author rgama
 *
 */

@Entity
@Table(name="NotaExpediente")
public class NotaExpediente implements java.io.Serializable {

	private Long idNota;
	private String nombreNota;
	private java.util.Date fechaCreacion;
	private String descripcion;
	private java.util.Date fechaActualizacion;
	private Expediente expediente;
	
	
	public NotaExpediente(){
	}
	
	/**
	 * 
	 * @param idNota
	 */
	public NotaExpediente(Long idNota){
		this.idNota= idNota;
	}
	
	
	/**
	 * Método de acceso al campo idNota.
	 * @return El valor del campo idNota
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Nota_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getIdNota() {
		return idNota;
	}

	public void setIdNota(Long idNota) {
		this.idNota = idNota;
	}
	
    @Column(name = "cNombreNota", nullable = false, length = 100)
	public String getNombreNota() {
		return nombreNota;
	}

	public void setNombreNota(String nombreNota) {
		this.nombreNota = nombreNota;
	}

    @Column(name = "dFechaCreacion", nullable = false, length = 23)
	public java.util.Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(java.util.Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@Column(name = "cDescripcion")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

    @Column(name = "dFechaActualizacion", length = 23)
	public java.util.Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(java.util.Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Expediente_id", nullable = false)
	public Expediente getExpediente() {
		return expediente;
	}

	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}
	
}

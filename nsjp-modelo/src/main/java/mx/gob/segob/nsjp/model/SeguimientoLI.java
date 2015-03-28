/**
* Nombre del Programa : SeguimientoLI.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 14/09/2011
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

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Clase que permite dar seguimiento a la Linea de Investigación.
 * 
 * @version 1.0
 * @author GustavoBP
 */
@SuppressWarnings("serial")
@Entity
@Table(name="SeguimientoLI")
public class SeguimientoLI implements java.io.Serializable {

	private Long seguimientoLIId;
	private Date fechaCreacion;
	private String hipotesis;
	
	private List<LineaInvestigacion> lineasInvestigacion;
	private Expediente expediente;

	

	public SeguimientoLI( ) {
		
	}
	
	public SeguimientoLI(Long seguimientoLIId, Date fechaCreacion,
			String hipotesis,Expediente expediente) {
		super();
		this.seguimientoLIId = seguimientoLIId;
		this.fechaCreacion = fechaCreacion;
		this.hipotesis = hipotesis;
		this.expediente=expediente;
	}

    public SeguimientoLI(Long seguimientoLIId) {
    	this.seguimientoLIId=seguimientoLIId;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SeguimientoLI_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getSeguimientoLIId() {
		return seguimientoLIId;
	}

	public void setSeguimientoLIId(Long seguimientoLIId) {
		this.seguimientoLIId = seguimientoLIId;
	}

	@Column(name = "dFechaCreacion", nullable = false, length = 23)
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@Column(name = "cHipotesis")
	public String getHipotesis() {
		return hipotesis;
	}

	public void setHipotesis(String hipotesis) {
		this.hipotesis = hipotesis;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "seguimientoLI")
	public List<LineaInvestigacion> getLineasInvestigacion() {
		return lineasInvestigacion;
	}

	public void setLineasInvestigacion(List<LineaInvestigacion> lineasInvestigacion) {
		this.lineasInvestigacion = lineasInvestigacion;
	}

	/**
	 * Método de acceso al campo expediente.
	 * @return El valor del campo expediente
	 */
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Expediente_id", nullable = false, updatable = false)
	public Expediente getExpediente() {
		return expediente;
	}

	/**
	 * Asigna el valor al campo expediente.
	 * @param expediente el valor expediente a asignar
	 */
	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}
	
	
}

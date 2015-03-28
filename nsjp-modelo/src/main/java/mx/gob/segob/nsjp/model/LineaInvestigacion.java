/**
* Nombre del Programa : LineaInvestigacion.java
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase que representa una Línea de investigación que da seguimiento 
 * asociado a un Numero de Expediente (SeguiminetoLI)
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="LineaInvestigacion")
public class LineaInvestigacion implements java.io.Serializable {
	
	private Long lineaInvestigacionId;
	private Date fechaCreacion;
	private String descripcion;
	private Long numComentarios;
	private Date fechaUltimoComentario;
	private Boolean esImpreso;
	
	private List<Comentario> comentarios;
	private Valor tipoLineaInvestigacion;
	
	private SeguimientoLI seguimientoLI;
    private Expediente expediente;
    private Integer consecutivo;

	public LineaInvestigacion() {
	}
	
	public LineaInvestigacion(Long lineaInvestigacionId, Date fechaCreacion,
			String descripcion, Long numComentarios, Date fechaUltimoComentario,
			Boolean esImpreso) {
		super();
		this.lineaInvestigacionId = lineaInvestigacionId;
		this.fechaCreacion = fechaCreacion;
		this.descripcion = descripcion;
		this.numComentarios = numComentarios;
		this.fechaUltimoComentario = fechaUltimoComentario;
		this.esImpreso = esImpreso;
	}
	
	public LineaInvestigacion(Long lineaInvestigacionId) {
		this.lineaInvestigacionId=lineaInvestigacionId;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LineaInvestigacion_Id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getLineaInvestigacionId() {
		return lineaInvestigacionId;
	}
	public void setLineaInvestigacionId(Long lineaInvestigacionId) {
		this.lineaInvestigacionId = lineaInvestigacionId;
	}
	
	@Column(name = "dFechaCreacion", nullable = false, length = 23)
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	@Column(name = "cDescripcion")
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Column(name = "nComentarios", precision = 18, scale = 0)
	public Long getNumComentarios() {
		return numComentarios;
	}
	public void setNumComentarios(Long numComentarios) {
		this.numComentarios = numComentarios;
	}
	
	@Column(name = "dFechaUltimoComentario", length = 23)
	public Date getFechaUltimoComentario() {
		return fechaUltimoComentario;
	}
	public void setFechaUltimoComentario(Date fechaUltimoComentario) {
		this.fechaUltimoComentario = fechaUltimoComentario;
	}
	
	@Column(name = "bEsImpreso", precision = 1, scale = 0)
	public Boolean getEsImpreso() {
		return esImpreso;
	}
	public void setEsImpreso(Boolean esImpreso) {
		this.esImpreso = esImpreso;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lineaInvestigacion")
	public List<Comentario> getComentarios() {
		return comentarios;
	}
	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoLineaInvestigacion_val", nullable = false)
	public Valor getTipoLineaInvestigacion() {
		return tipoLineaInvestigacion;
	}
	public void setTipoLineaInvestigacion(Valor tipoLineaInvestigacion) {
		this.tipoLineaInvestigacion = tipoLineaInvestigacion;
	}
	 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SeguimientoLI_id", nullable = false)
	public SeguimientoLI getSeguimientoLI() {
		return seguimientoLI;
	}

	public void setSeguimientoLI(SeguimientoLI seguimientoLI) {
		this.seguimientoLI = seguimientoLI;
	}

	/**
	 * Método de acceso al campo expediente.
	 * @return El valor del campo expediente
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Expediente_id", nullable = false)
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

	/**
	 * @return the consecutivo
	 */
	@Column (name="iConsecutivo")
	public Integer getConsecutivo() {
		return consecutivo;
	}

	/**
	 * @param consecutivo the consecutivo to set
	 */
	public void setConsecutivo(Integer consecutivo) {
		this.consecutivo = consecutivo;
	}
	
	
		
}

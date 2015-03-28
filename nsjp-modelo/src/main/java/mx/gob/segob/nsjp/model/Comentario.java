/**
* Nombre del Programa : Comentario.java
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
 * Clase que asocia un comentario asociado a una Linea de Investigación.
 * 
 * @version 1.0
 * @author GustavoBP
 */
@SuppressWarnings("serial")
@Entity
@Table(name="Comentario")
public class Comentario implements java.io.Serializable {

	private Long comentarioId;
	private Date fechaCreacion;
	private String descripcion;
	
	private List<ArchivoDigital> archivoDigital; 
	private Funcionario funcionario;
	
	private LineaInvestigacion lineaInvestigacion;
	
	public Comentario() {
		
	}

	public Comentario(Date fechaCreacion, String descripcion) {
		this.fechaCreacion = fechaCreacion;
		this.descripcion = descripcion;
	}
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Comentario_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getComentarioId() {
		return comentarioId;
	}

	public void setComentarioId(Long comentarioId) {
		this.comentarioId = comentarioId;
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
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "comentario")
	public List<ArchivoDigital> getArchivoDigital() {
		return archivoDigital;
	}
	public void setArchivoDigital(List<ArchivoDigital> archivoDigital) {
		this.archivoDigital = archivoDigital;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "iClaveFuncionario")
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LineaInvestigacion_Id")
	public LineaInvestigacion getLineaInvestigacion() {
		return lineaInvestigacion;
	}

	public void setLineaInvestigacion(LineaInvestigacion lineaInvestigacion) {
		this.lineaInvestigacion = lineaInvestigacion;
	}
}

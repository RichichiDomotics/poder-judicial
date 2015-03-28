/**
* Nombre del Programa : Sitio.java
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

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * Entidad que representa la relación del traslado del imputado y el centro de detención
 * 
 * @version 1.0
 * @author GustavoBP
 */
@Entity
@Table(name = "Sitio")
public class Sitio implements java.io.Serializable {
	
	private Long sitioId;
	private Date fecha;
	
	//Relaciones
	private Valor tipoSitioTraslado;
	private CentroDetencion centroDetencion;
	private Sala sala; 
	
	//Cosntructores
	public Sitio(){
	}

	public Sitio(Long sitioId, Date fecha) {
		this.sitioId = sitioId;
		this.fecha = fecha;
	}

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Sitio_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getSitioId() {
		return sitioId;
	}

	public void setSitioId(Long sitioId) {
		this.sitioId = sitioId;
	}

	@Column(name = "dFecha", nullable=false, length = 23)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TipoSitioTraslado_val")
	public Valor getTipoSitioTraslado() {
		return tipoSitioTraslado;
	}

	public void setTipoSitioTraslado(Valor tipoSitioTraslado) {
		this.tipoSitioTraslado = tipoSitioTraslado;
	}

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CentroDetencion_id", nullable = true)
	public CentroDetencion getCentroDetencion() {
		return centroDetencion;
	}

	public void setCentroDetencion(CentroDetencion centroDetencion) {
		this.centroDetencion = centroDetencion;
	}

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Sala_id", nullable = false)
	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
}

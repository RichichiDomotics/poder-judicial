/**
 * 
 */
package mx.gob.segob.nsjp.model;

import java.io.Serializable;

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
 * @author EdgarTE
 *
 */
@Entity
@Table(name = "ActoBuenaConducta")
public class ActoBuenaConducta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6571562389211207489L;
	
	private Long actoBuenaConductaId;
	private String cnombre;
	private String cdescripcion;
	private Long ipuntosOtorgados;
	private PeriodoAcumulacionPuntos periodoAcumulacionPuntos;
	private Sentencia sentencia;
	
	/**
	 * Método de acceso al campo actoBuenaConductaId.
	 * @return El valor del campo actoBuenaConductaId
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ActoBuenaConducta_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getActoBuenaConductaId() {
		return actoBuenaConductaId;
	}
	
	/**
	 * Asigna el valor al campo actoBuenaConductaId.
	 * @param actoBuenaConductaId el valor actoBuenaConductaId a asignar
	 */
	public void setActoBuenaConductaId(Long actoBuenaConductaId) {
		this.actoBuenaConductaId = actoBuenaConductaId;
	}
	
	/**
	 * Método de acceso al campo cnombre.
	 * @return El valor del campo cnombre
	 */
	@Column(name = "cNombre", nullable = false, length = 200)
	public String getCnombre() {
		return cnombre;
	}
	
	/**
	 * Asigna el valor al campo cnombre.
	 * @param cnombre el valor cnombre a asignar
	 */
	public void setCnombre(String cnombre) {
		this.cnombre = cnombre;
	}
	
	/**
	 * Método de acceso al campo cdescripcion.
	 * @return El valor del campo cdescripcion
	 */
	@Column(name = "cDescripcion", nullable = false, length = 200)
	public String getCdescripcion() {
		return cdescripcion;
	}
	
	/**
	 * Asigna el valor al campo cdescripcion.
	 * @param cdescripcion el valor cdescripcion a asignar
	 */
	public void setCdescripcion(String cdescripcion) {
		this.cdescripcion = cdescripcion;
	}
	
	/**
	 * Método de acceso al campo ipuntosOtorgados.
	 * @return El valor del campo ipuntosOtorgados
	 */
	@Column(name = "iPuntosOtorgados", precision = 18, scale = 0)
	public Long getIpuntosOtorgados() {
		return ipuntosOtorgados;
	}
	
	/**
	 * Asigna el valor al campo ipuntosOtorgados.
	 * @param ipuntosOtorgados el valor ipuntosOtorgados a asignar
	 */
	public void setIpuntosOtorgados(Long ipuntosOtorgados) {
		this.ipuntosOtorgados = ipuntosOtorgados;
	}
	
	/**
	 * Método de acceso al campo periodoAcumulacionPuntos.
	 * @return El valor del campo periodoAcumulacionPuntos
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PeriodoAcumulacionPuntos_Id", nullable = true)
	public PeriodoAcumulacionPuntos getPeriodoAcumulacionPuntos() {
		return periodoAcumulacionPuntos;
	}
	
	/**
	 * Asigna el valor al campo periodoAcumulacionPuntos.
	 * @param periodoAcumulacionPuntos el valor periodoAcumulacionPuntos a asignar
	 */
	public void setPeriodoAcumulacionPuntos(
			PeriodoAcumulacionPuntos periodoAcumulacionPuntos) {
		this.periodoAcumulacionPuntos = periodoAcumulacionPuntos;
	}
	
	/**
	 * Método de acceso al campo sentencia.
	 * @return El valor del campo sentencia
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Sentencia_Id", nullable = false, updatable = false)
	public Sentencia getSentencia() {
		return this.sentencia;
	}
	
	/**
	 * Asigna el valor al campo sentencia.
	 * @param sentencia el valor sentencia a asignar
	 */
	public void setSentencia(Sentencia sentencia) {
		this.sentencia = sentencia;
	}
}

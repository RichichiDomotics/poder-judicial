/**
 * 
 */
package mx.gob.segob.nsjp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author EdgarTE
 *
 */
@Entity
@Table(name = "PeriodoAcumulacionPuntos")
public class PeriodoAcumulacionPuntos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7824233327355384593L;
	
	private Long periodoAcumulacionPuntosId;
	private Date dfechaInicio;
	private Date dfechaTermino;
	private String cNombrePeriodo;
	private Set<ActoBuenaConducta> actosBuenaConducta = new HashSet<ActoBuenaConducta>(0);
	
	/**
	 * Método de acceso al campo periodoAcumulacionPuntosId.
	 * @return El valor del campo periodoAcumulacionPuntosId
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PeriodoAcumulacionPuntos_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getPeriodoAcumulacionPuntosId() {
		return periodoAcumulacionPuntosId;
	}
	
	/**
	 * Asigna el valor al campo periodoAcumulacionPuntosId.
	 * @param periodoAcumulacionPuntosId el valor periodoAcumulacionPuntosId a asignar
	 */
	public void setPeriodoAcumulacionPuntosId(Long periodoAcumulacionPuntosId) {
		this.periodoAcumulacionPuntosId = periodoAcumulacionPuntosId;
	}
	
	/**
	 * Método de acceso al campo dfechaInicio.
	 * @return El valor del campo dfechaInicio
	 */
	@Column(name = "dFechaInicio", nullable = false, length = 23)
	public Date getDfechaInicio() {
		return dfechaInicio;
	}
	
	/**
	 * Asigna el valor al campo dfechaInicio.
	 * @param dfechaInicio el valor dfechaInicio a asignar
	 */
	public void setDfechaInicio(Date dfechaInicio) {
		this.dfechaInicio = dfechaInicio;
	}
	
	/**
	 * Método de acceso al campo dfechaTermino.
	 * @return El valor del campo dfechaTermino
	 */
	@Column(name = "dFechaTermino", nullable = false, length = 23)
	public Date getDfechaTermino() {
		return dfechaTermino;
	}
	
	/**
	 * Asigna el valor al campo dfechaTermino.
	 * @param dfechaTermino el valor dfechaTermino a asignar
	 */
	public void setDfechaTermino(Date dfechaTermino) {
		this.dfechaTermino = dfechaTermino;
	}
	
	/**
	 * Método de acceso al campo cNombrePeriodo.
	 * @return El valor del campo cNombrePeriodo
	 */
	@Column(name = "cNombrePeriodo", nullable = false, length = 200)
	public String getcNombrePeriodo() {
		return cNombrePeriodo;
	}
	
	/**
	 * Asigna el valor al campo cNombrePeriodo.
	 * @param cNombrePeriodo el valor cNombrePeriodo a asignar
	 */
	public void setcNombrePeriodo(String cNombrePeriodo) {
		this.cNombrePeriodo = cNombrePeriodo;
	}

	/**
	 * Método de acceso al campo actosBuenaConducta.
	 * @return El valor del campo actosBuenaConducta
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "periodoAcumulacionPuntos")
	public Set<ActoBuenaConducta> getActosBuenaConducta() {
		return actosBuenaConducta;
	}

	/**
	 * Asigna el valor al campo actosBuenaConducta.
	 * @param actosBuenaConducta el valor actosBuenaConducta a asignar
	 */
	public void setActosBuenaConducta(Set<ActoBuenaConducta> actosBuenaConducta) {
		this.actosBuenaConducta = actosBuenaConducta;
	}

}

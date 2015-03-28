/**
* Nombre del Programa 		: InventarioPertenencia.java
* Autor 					: EdgarTE
* Compania 					: Ultrasist
* Proyecto 					: NSJP 							Fecha: 28 Mar 2012
* Marca de cambio        	: N/A
* Descripcion General    	: Entidad de persistencia para InventarioPertenencia
* Programa Dependiente  	: N/A
* Programa Subsecuente 		: N/A
* Cond. de ejecucion        : N/A
* Dias de ejecucion 		: N/A 							Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor 					: N/A
* Compania 					: N/A
* Proyecto 					: N/A 							Fecha: N/A
* Modificacion 				: N/A
*------------------------------------------------------------------------------
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Clase que modela la entidad de persistencia de Hibernate para
 * la tabla InventarioPertenencia.
 * @version 1.0
 * @author EdgarTE
 *
 */
@Entity
@Table(name = "InventarioPertenencia")
public class InventarioPertenencia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3101165876681239935L;
	
	private Long inventarioPertenenciaId;
	private Sentencia sentencia;
	private CentroDetencion centroDetencion;
	private Date fechaIngreso;
	private Boolean definitivo;
	private Boolean entregado;
	private Date fechaEntrega;
	private Set<Pertenencia> pertenencias = new HashSet<Pertenencia>(0);
	
	/**
	 * Método de acceso al campo inventarioPertenenciaId.
	 * @return El valor del campo inventarioPertenenciaId
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "InventarioPertenencia_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getInventarioPertenenciaId() {
		return inventarioPertenenciaId;
	}
	
	/**
	 * Asigna el valor al campo inventarioPertenenciaId.
	 * @param inventarioPertenenciaId el valor inventarioPertenenciaId a asignar
	 */
	public void setInventarioPertenenciaId(Long inventarioPertenenciaId) {
		this.inventarioPertenenciaId = inventarioPertenenciaId;
	}
	
	/**
	 * Método de acceso al campo sentencia.
	 * @return El valor del campo sentencia
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Sentencia_id", nullable = false, updatable = false)
	public Sentencia getSentencia() {
		return sentencia;
	}
	
	/**
	 * Asigna el valor al campo sentencia.
	 * @param sentencia el valor sentencia a asignar
	 */
	public void setSentencia(Sentencia sentencia) {
		this.sentencia = sentencia;
	}
	
	/**
	 * Método de acceso al campo centroDetencion.
	 * @return El valor del campo centroDetencion
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CentroDetencion_id", nullable = false)
	public CentroDetencion getCentroDetencion() {
		return centroDetencion;
	}
	
	/**
	 * Asigna el valor al campo centroDetencion.
	 * @param centroDetencion el valor centroDetencion a asignar
	 */
	public void setCentroDetencion(CentroDetencion centroDetencion) {
		this.centroDetencion = centroDetencion;
	}
	
	/**
	 * Método de acceso al campo fechaIngreso.
	 * @return El valor del campo fechaIngreso
	 */
	@Column(name = "dFechaIngreso", nullable = false, length = 23)
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	
	/**
	 * Asigna el valor al campo fechaIngreso.
	 * @param fechaIngreso el valor fechaIngreso a asignar
	 */
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	
	/**
	 * Método de acceso al campo definitivo.
	 * @return El valor del campo definitivo
	 */
	@Column(name = "bDefinitivo", nullable = false, precision = 1, scale = 0)
	public Boolean getDefinitivo() {
		return definitivo;
	}
	
	/**
	 * Asigna el valor al campo definitivo.
	 * @param definitivo el valor definitivo a asignar
	 */
	public void setDefinitivo(Boolean definitivo) {
		this.definitivo = definitivo;
	}
	
	/**
	 * Método de acceso al campo entregado.
	 * @return El valor del campo entregado
	 */
	@Column(name = "bEntregado", nullable = false, precision = 1, scale = 0)
	public Boolean getEntregado() {
		return entregado;
	}
	
	/**
	 * Asigna el valor al campo entregado.
	 * @param entregado el valor entregado a asignar
	 */
	public void setEntregado(Boolean entregado) {
		this.entregado = entregado;
	}
	
	/**
	 * Método de acceso al campo fechaEntrega.
	 * @return El valor del campo fechaEntrega
	 */
	@Column(name = "dFechaEntrega", nullable = false, length = 23)
	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	
	/**
	 * Asigna el valor al campo fechaEntrega.
	 * @param fechaEntrega el valor fechaEntrega a asignar
	 */
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	/**
	 * Método de acceso al campo pertenencias.
	 * @return El valor del campo pertenencias
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "inventarioPertenencia")
	public Set<Pertenencia> getPertenencias() {
		return pertenencias;
	}

	/**
	 * Asigna el valor al campo pertenencias.
	 * @param pertenencias el valor pertenencias a asignar
	 */
	public void setPertenencias(Set<Pertenencia> pertenencias) {
		this.pertenencias = pertenencias;
	}
	

}

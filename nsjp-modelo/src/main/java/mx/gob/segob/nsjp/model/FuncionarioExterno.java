/**
* Nombre del Programa 		: FuncionarioExterno.java
* Autor 					: EdgarTE
* Compania 					: Ultrasist
* Proyecto 					: NSJP 								Fecha: 10 Apr 2012
* Marca de cambio 			: N/A
* Descripcion General 		: Clase de persistencia para la tabla FuncionarioExterno
* Programa Dependiente 		: N/A
* Programa Subsecuente 		: N/A
* Cond. de ejecucion 		: N/A
* Dias de ejecucion 		: N/A 								Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor 					: N/A
* Compania 					: N/A
* Proyecto 					: N/A 								Fecha: N/A
* Modificacion 				: N/A
*------------------------------------------------------------------------------
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
 * Clase de Hibernate que Modela la entidad FuncionarioExterno.
 * @version 1.0
 * @author EdgarTE
 *
 */
@Entity
@Table(name = "FuncionarioExterno")
public class FuncionarioExterno implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8163518024256517213L;
	
	private ConfInstitucion confInstitucion;
	private Long funcionarioExternoId;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String area;
	private String puesto;
	private String email;
	private Long cveFuncionarioInstExt;
	
	/**
	 * Método de acceso al campo funcionarioExternoId.
	 * @return El valor del campo funcionarioExternoId
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FuncionarioExterno_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getFuncionarioExternoId() {
		return funcionarioExternoId;
	}
	
	/**
	 * Asigna el valor al campo funcionarioExternoId.
	 * @param funcionarioExternoId el valor funcionarioExternoId a asignar
	 */
	public void setFuncionarioExternoId(Long funcionarioExternoId) {
		this.funcionarioExternoId = funcionarioExternoId;
	}
	
	/**
	 * Método de acceso al campo confInstitucion.
	 * @return El valor del campo confInstitucion
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ConfInstitucion_id", nullable = false)
	public ConfInstitucion getConfInstitucion() {
		return confInstitucion;
	}
	
	/**
	 * Asigna el valor al campo confInstitucion.
	 * @param confInstitucion el valor confInstitucion a asignar
	 */
	public void setConfInstitucion(ConfInstitucion confInstitucion) {
		this.confInstitucion = confInstitucion;
	}
	
	/**
	 * Método de acceso al campo nombre.
	 * @return El valor del campo nombre
	 */
	@Column(name = "cNombre", nullable = false, length = 50)
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Asigna el valor al campo nombre.
	 * @param nombre el valor nombre a asignar
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Método de acceso al campo apellidoPaterno.
	 * @return El valor del campo apellidoPaterno
	 */
	@Column(name = "cApellidoPaterno", nullable = false, length = 50)
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	
	/**
	 * Asigna el valor al campo apellidoPaterno.
	 * @param apellidoPaterno el valor apellidoPaterno a asignar
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	
	/**
	 * Método de acceso al campo apellidoMaterno.
	 * @return El valor del campo apellidoMaterno
	 */
	@Column(name = "cApellidoMaterno", nullable = true, length = 50)
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	/**
	 * Asigna el valor al campo apellidoMaterno.
	 * @param apellidoMaterno el valor apellidoMaterno a asignar
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	/**
	 * Método de acceso al campo area.
	 * @return El valor del campo area
	 */
	@Column(name = "cArea", nullable = true, length = 100)
	public String getArea() {
		return area;
	}
	
	/**
	 * Asigna el valor al campo area.
	 * @param area el valor area a asignar
	 */
	public void setArea(String area) {
		this.area = area;
	}
	
	/**
	 * Método de acceso al campo puesto.
	 * @return El valor del campo puesto
	 */
	@Column(name = "cPuesto", nullable = true, length = 100)
	public String getPuesto() {
		return puesto;
	}
	
	/**
	 * Asigna el valor al campo puesto.
	 * @param puesto el valor puesto a asignar
	 */
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	
	/**
	 * Método de acceso al campo email.
	 * @return El valor del campo email
	 */
	@Column(name = "cEmail", nullable = true, length = 60)
	public String getEmail() {
		return email;
	}
	
	/**
	 * Asigna el valor al campo email.
	 * @param email el valor email a asignar
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Método de acceso al campo cveFuncionarioInstExt.
	 * @return El valor del campo cveFuncionarioInstExt
	 */
	@Column(name = "iCveFuncionarioInstExt", nullable = false, precision = 18, scale = 0)
	public Long getCveFuncionarioInstExt() {
		return cveFuncionarioInstExt;
	}
	
	/**
	 * Asigna el valor al campo cveFuncionarioInstExt.
	 * @param cveFuncionarioInstExt el valor cveFuncionarioInstExt a asignar
	 */
	public void setCveFuncionarioInstExt(Long cveFuncionarioInstExt) {
		this.cveFuncionarioInstExt = cveFuncionarioInstExt;
	}
	
	
}

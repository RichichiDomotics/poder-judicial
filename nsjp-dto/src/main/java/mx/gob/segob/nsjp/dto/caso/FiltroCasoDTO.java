/**
* Nombre del Programa : FiltroCasoDTO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 2 May 2011
* Marca de cambio        : N/A
* Descripcion General    : DTO para los filtros de busqueda de Caso
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
package mx.gob.segob.nsjp.dto.caso;

import java.util.Date;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * DTO para los filtros de busqueda de Caso
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class FiltroCasoDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3020409009868156788L;
	
	private String nombre;
	private String apellidos;	
	private String numeroExpediente;
	private String alias;
	//1=Contiene 0=Es igual
	private Long tipoBusqueda;
	private Date fechaCreacionInicio;
	private Date fechaCreacionFin;
	private String delito;
	private String apellidoMat;
	
	
	/**
	 * Método de acceso al campo nombre.
	 * @return El valor del campo nombre
	 */
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
	 * Método de acceso al campo apellidos.
	 * @return El valor del campo apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}
	/**
	 * Asigna el valor al campo apellidos.
	 * @param apellidos el valor apellidos a asignar
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	/**
	 * Método de acceso al campo numeroExpediente.
	 * @return El valor del campo numeroExpediente
	 */
	public String getNumeroExpediente() {
		return numeroExpediente;
	}
	/**
	 * Asigna el valor al campo numeroExpediente.
	 * @param numeroExpediente el valor numeroExpediente a asignar
	 */
	public void setNumeroExpediente(String numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}
	/**
	 * Método de acceso al campo alias.
	 * @return El valor del campo alias
	 */
	public String getAlias() {
		return alias;
	}
	/**
	 * Asigna el valor al campo alias.
	 * @param alias el valor alias a asignar
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}
	/**
	 * Método de acceso al campo tipoBusqueda.
	 * @return El valor del campo tipoBusqueda
	 */
	public Long getTipoBusqueda() {
		return tipoBusqueda;
	}
	/**
	 * Asigna el valor al campo tipoBusqueda.
	 * @param tipoBusqueda el valor tipoBusqueda a asignar
	 */
	public void setTipoBusqueda(Long tipoBusqueda) {
		this.tipoBusqueda = tipoBusqueda;
	}
	/**
	 * Método de acceso al campo fechaCreacionInicio.
	 * @return El valor del campo fechaCreacionInicio
	 */
	public Date getFechaCreacionInicio() {
		return fechaCreacionInicio;
	}
	/**
	 * Asigna el valor al campo fechaCreacionInicio.
	 * @param fechaCreacionInicio el valor fechaCreacionInicio a asignar
	 */
	public void setFechaCreacionInicio(Date fechaCreacionInicio) {
		this.fechaCreacionInicio = fechaCreacionInicio;
	}
	/**
	 * Método de acceso al campo fechaCreacionFin.
	 * @return El valor del campo fechaCreacionFin
	 */
	public Date getFechaCreacionFin() {
		return fechaCreacionFin;
	}
	/**
	 * Asigna el valor al campo fechaCreacionFin.
	 * @param fechaCreacionFin el valor fechaCreacionFin a asignar
	 */
	public void setFechaCreacionFin(Date fechaCreacionFin) {
		this.fechaCreacionFin = fechaCreacionFin;
	}
	/**
	 * Método de acceso al campo delitoId.
	 * @return El valor del campo delitoId
	 */
	public String getDelito() {
		return delito;
	}
	/**
	 * Asigna el valor al campo delitoId.
	 * @param delitoId el valor delitoId a asignar
	 */
	public void setDelito(String delito) {
		this.delito = delito;
	}
	/**
	 * @return the apellidoMat
	 */
	public String getApellidoMat() {
		return apellidoMat;
	}
	/**
	 * @param apellidoMat the apellidoMat to set
	 */
	public void setApellidoMat(String apellidoMat) {
		this.apellidoMat = apellidoMat;
	}	
	

}

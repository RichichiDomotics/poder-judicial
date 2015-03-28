/**
* Nombre del Programa : DetencionWSDTO.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 02/07/2011
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
package mx.gob.segob.nsjp.dto.detencion;

import java.util.Date;

import mx.gob.segob.nsjp.dto.base.GenericWSDTO;

/**
 * DTO para el transporte de datos de una detención entre
 * instituciones
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public class DetencionWSDTO extends GenericWSDTO{

	private String detenido;
	private String folioElemento;
	private String nombreDetenido;
	private String apellidoPaternoDetenido;
	private String apellidoMaternoDetenido;
	private Date fechaInicioDetencion;
	private Date fechaFinDetencion;
	private String motivoDetencion;
	private Date fechaRecepcionDetecion;
	private String observaciones;
	private String lugarDetencionProvisional;
	private String lugarDetencion;
	
	/**
	 * Método de acceso al campo detenido.
	 * @return El valor del campo detenido
	 */
	public String getDetenido() {
		return detenido;
	}
	/**
	 * Asigna el valor al campo detenido.
	 * @param detenido el valor detenido a asignar
	 */
	public void setDetenido(String detenido) {
		this.detenido = detenido;
	}
	/**
	 * Establece el valor de la propiedad folioElemento
	 * @param folioElemento valo folioElemento a almacenar
	 */
	public void setFolioElemento(String folioElemento) {
		this.folioElemento = folioElemento;
	}
	/**
	 * Regresa el valor de la propiedad folioElemento
	 * @return the folioElemento
	 */
	public String getFolioElemento() {
		return folioElemento;
	}
	/**
	 * Regresa el valor de la propiedad nombreDetenido
	 * @return the nombreDetenido
	 */
	public String getNombreDetenido() {
		return nombreDetenido;
	}
	/**
	 * Establece el valor de la propiedad nombreDetenido
	 * @param nombreDetenido valo nombreDetenido a almacenar
	 */
	public void setNombreDetenido(String nombreDetenido) {
		this.nombreDetenido = nombreDetenido;
	}
	/**
	 * Regresa el valor de la propiedad apellidoPaternoDetenido
	 * @return the apellidoPaternoDetenido
	 */
	public String getApellidoPaternoDetenido() {
		return apellidoPaternoDetenido;
	}
	/**
	 * Establece el valor de la propiedad apellidoPaternoDetenido
	 * @param apellidoPaternoDetenido valo apellidoPaternoDetenido a almacenar
	 */
	public void setApellidoPaternoDetenido(String apellidoPaternoDetenido) {
		this.apellidoPaternoDetenido = apellidoPaternoDetenido;
	}
	/**
	 * Regresa el valor de la propiedad apellidoMaternoDetenido
	 * @return the apellidoMaternoDetenido
	 */
	public String getApellidoMaternoDetenido() {
		return apellidoMaternoDetenido;
	}
	/**
	 * Establece el valor de la propiedad apellidoMaternoDetenido
	 * @param apellidoMaternoDetenido valo apellidoMaternoDetenido a almacenar
	 */
	public void setApellidoMaternoDetenido(String apellidoMaternoDetenido) {
		this.apellidoMaternoDetenido = apellidoMaternoDetenido;
	}
	/**
	 * Método de acceso al campo fechaInicioDetencion.
	 * @return El valor del campo fechaInicioDetencion
	 */
	public Date getFechaInicioDetencion() {
		return fechaInicioDetencion;
	}
	/**
	 * Asigna el valor al campo fechaInicioDetencion.
	 * @param fechaInicioDetencion el valor fechaInicioDetencion a asignar
	 */
	public void setFechaInicioDetencion(Date fechaInicioDetencion) {
		this.fechaInicioDetencion = fechaInicioDetencion;
	}
	/**
	 * Método de acceso al campo fechaFinDetencion.
	 * @return El valor del campo fechaFinDetencion
	 */
	public Date getFechaFinDetencion() {
		return fechaFinDetencion;
	}
	/**
	 * Asigna el valor al campo fechaFinDetencion.
	 * @param fechaFinDetencion el valor fechaFinDetencion a asignar
	 */
	public void setFechaFinDetencion(Date fechaFinDetencion) {
		this.fechaFinDetencion = fechaFinDetencion;
	}
	/**
	 * Método de acceso al campo motivoDetencion.
	 * @return El valor del campo motivoDetencion
	 */
	public String getMotivoDetencion() {
		return motivoDetencion;
	}
	/**
	 * Asigna el valor al campo motivoDetencion.
	 * @param motivoDetencion el valor motivoDetencion a asignar
	 */
	public void setMotivoDetencion(String motivoDetencion) {
		this.motivoDetencion = motivoDetencion;
	}
	/**
	 * Método de acceso al campo fechaRecepcionDetecion.
	 * @return El valor del campo fechaRecepcionDetecion
	 */
	public Date getFechaRecepcionDetecion() {
		return fechaRecepcionDetecion;
	}
	/**
	 * Asigna el valor al campo fechaRecepcionDetecion.
	 * @param fechaRecepcionDetecion el valor fechaRecepcionDetecion a asignar
	 */
	public void setFechaRecepcionDetecion(Date fechaRecepcionDetecion) {
		this.fechaRecepcionDetecion = fechaRecepcionDetecion;
	}
	/**
	 * Método de acceso al campo observaciones.
	 * @return El valor del campo observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}
	/**
	 * Asigna el valor al campo observaciones.
	 * @param observaciones el valor observaciones a asignar
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	/**
	 * Método de acceso al campo lugarDetencion.
	 * @return El valor del campo lugarDetencion
	 */
	public String getLugarDetencion() {
		return lugarDetencion;
	}
	/**
	 * Asigna el valor al campo lugarDetencion.
	 * @param lugarDetencion el valor lugarDetencion a asignar
	 */
	public void setLugarDetencion(String lugarDetencion) {
		this.lugarDetencion = lugarDetencion;
	}
	/**
	 * Método de acceso al campo lugarDetencionProvisional.
	 * @return El valor del campo lugarDetencionProvisional
	 */
	public String getLugarDetencionProvisional() {
		return lugarDetencionProvisional;
	}
	/**
	 * Asigna el valor al campo lugarDetencionProvisional.
	 * @param lugarDetencionProvisional el valor lugarDetencionProvisional a asignar
	 */
	public void setLugarDetencionProvisional(String lugarDetencionProvisional) {
		this.lugarDetencionProvisional = lugarDetencionProvisional;
	}
	
	
}

/**
* Nombre del Programa : MedioPruebaDTO.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 30/09/2011
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
package mx.gob.segob.nsjp.dto.prueba;

import java.util.List;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.elemento.ElementoDTO;

/**
 * DTO para la transferencia de parámetros de Medio Prueba, entre la Vista y Servicio.
 * @version 1.0
 * @author GustavoBP
 *
 */
public class MedioPruebaDTO extends GenericDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2487651119074063472L;
	private Long medioPruebaId;
	private String nombreMedio;
	private String numeroIdentificacion;
	private String descripcion;
	private String ubicacionFisica;
	
	//Relaciones
	private List<RelacionDatoMedioPruebaDTO> relacionesDatoMedioPrueba;
	private ElementoDTO elementoMedioPrueba;
	private DocumentoDTO documentoMedioPrueba;
	
	/**
	 * 
	 */
	public MedioPruebaDTO() {
	}
	public MedioPruebaDTO(Long medioPruebaId, String nombreMedio,
			String numeroIdentificacion) {
		this.medioPruebaId = medioPruebaId;
		this.nombreMedio = nombreMedio;
		this.numeroIdentificacion = numeroIdentificacion;
	}
	public MedioPruebaDTO(Long medioPruebaId, String nombreMedio,
			String numeroIdentificacion, String descripcion,
			String ubicacionFisica) {
		this.medioPruebaId = medioPruebaId;
		this.nombreMedio = nombreMedio;
		this.numeroIdentificacion = numeroIdentificacion;
		this.descripcion = descripcion;
		this.ubicacionFisica = ubicacionFisica;
	}
	/**
	 * Método de acceso al campo medioPruebaId.
	 * @return El valor del campo medioPruebaId
	 */
	public Long getMedioPruebaId() {
		return medioPruebaId;
	}
	/**
	 * Asigna el valor al campo medioPruebaId.
	 * @param medioPruebaId el valor medioPruebaId a asignar
	 */
	public void setMedioPruebaId(Long medioPruebaId) {
		this.medioPruebaId = medioPruebaId;
	}
	/**
	 * Método de acceso al campo nombreMedio.
	 * @return El valor del campo nombreMedio
	 */
	public String getNombreMedio() {
		return nombreMedio;
	}
	/**
	 * Asigna el valor al campo nombreMedio.
	 * @param nombreMedio el valor nombreMedio a asignar
	 */
	public void setNombreMedio(String nombreMedio) {
		this.nombreMedio = nombreMedio;
	}
	/**
	 * Método de acceso al campo numeroIdentificacion.
	 * @return El valor del campo numeroIdentificacion
	 */
	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}
	/**
	 * Asigna el valor al campo numeroIdentificacion.
	 * @param numeroIdentificacion el valor numeroIdentificacion a asignar
	 */
	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}
	/**
	 * Método de acceso al campo descripcion.
	 * @return El valor del campo descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * Asigna el valor al campo descripcion.
	 * @param descripcion el valor descripcion a asignar
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * Método de acceso al campo ubicacionFisica.
	 * @return El valor del campo ubicacionFisica
	 */
	public String getUbicacionFisica() {
		return ubicacionFisica;
	}
	/**
	 * Asigna el valor al campo ubicacionFisica.
	 * @param ubicacionFisica el valor ubicacionFisica a asignar
	 */
	public void setUbicacionFisica(String ubicacionFisica) {
		this.ubicacionFisica = ubicacionFisica;
	}
	/**
	 * Método de acceso al campo relacionesDatoMedioPrueba.
	 * @return El valor del campo relacionesDatoMedioPrueba
	 */
	public List<RelacionDatoMedioPruebaDTO> getRelacionesDatoMedioPrueba() {
		return relacionesDatoMedioPrueba;
	}
	/**
	 * Asigna el valor al campo relacionesDatoMedioPrueba.
	 * @param relacionesDatoMedioPrueba el valor relacionesDatoMedioPrueba a asignar
	 */
	public void setRelacionesDatoMedioPrueba(
			List<RelacionDatoMedioPruebaDTO> relacionesDatoMedioPrueba) {
		this.relacionesDatoMedioPrueba = relacionesDatoMedioPrueba;
	}
	/**
	 * Método de acceso al campo elementoMedioPrueba.
	 * @return El valor del campo elementoMedioPrueba
	 */
	public ElementoDTO getElementoMedioPrueba() {
		return elementoMedioPrueba;
	}
	/**
	 * Asigna el valor al campo elementoMedioPrueba.
	 * @param elementoMedioPrueba el valor elementoMedioPrueba a asignar
	 */
	public void setElementoMedioPrueba(ElementoDTO elementoMedioPrueba) {
		this.elementoMedioPrueba = elementoMedioPrueba;
	}
	/**
	 * Método de acceso al campo documentoMedioPrueba.
	 * @return El valor del campo documentoMedioPrueba
	 */
	public DocumentoDTO getDocumentoMedioPrueba() {
		return documentoMedioPrueba;
	}
	/**
	 * Asigna el valor al campo documentoMedioPrueba.
	 * @param documentoMedioPrueba el valor documentoMedioPrueba a asignar
	 */
	public void setDocumentoMedioPrueba(DocumentoDTO documentoMedioPrueba) {
		this.documentoMedioPrueba = documentoMedioPrueba;
	}
}

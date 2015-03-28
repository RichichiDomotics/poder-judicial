/**
* Nombre del Programa : DatoPruebaDTO.java
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

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.elemento.ElementoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;

/**
 * DTO para la transferencia de parámetros de Dato Prueba entre la Vista y Servicio.
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
@SuppressWarnings("serial")
public class DatoPruebaDTO  extends GenericDTO {

	private Long datoPruebaId;
	private String nombreDato;
	private String numeroIdentificacion;
	private String descripcion;
	private String folioCadenaCustodia;
	private Boolean esAceptado;
	private Date tiempoCancelacion;
	
	//Relaciones
	private ElementoDTO elementoPrueba;
	private DocumentoDTO documentoPrueba;
	private ValorDTO motivoCancelacion;
	private List<RelacionPruebaInvolucradoDTO> relacionesPruebaInvolucrado;
	private ExpedienteDTO expediente;
	private List<RelacionDatoMedioPruebaDTO> relacionesDatosMedioPrueba;
	
	/**
	 * 
	 */
	public DatoPruebaDTO() {
	}
	public DatoPruebaDTO(Long datoPruebaId, String nombreDato,
			String numeroIdentificacion) {
		this.datoPruebaId = datoPruebaId;
		this.nombreDato = nombreDato;
		this.numeroIdentificacion = numeroIdentificacion;
	}
	public DatoPruebaDTO(Long datoPruebaId, String nombreDato,
			String numeroIdentificacion, String descripcion,
			String folioCadenaCustodia, Boolean esAceptado,
			Date tiempoCancelacion, ValorDTO motivoCancelacion) {
		this.datoPruebaId = datoPruebaId;
		this.nombreDato = nombreDato;
		this.numeroIdentificacion = numeroIdentificacion;
		this.descripcion = descripcion;
		this.folioCadenaCustodia = folioCadenaCustodia;
		this.esAceptado = esAceptado;
		this.tiempoCancelacion = tiempoCancelacion;
		this.motivoCancelacion = motivoCancelacion;
	}
	/**
	 * Método de acceso al campo datoPruebaId.
	 * @return El valor del campo datoPruebaId
	 */
	public Long getDatoPruebaId() {
		return datoPruebaId;
	}
	/**
	 * Asigna el valor al campo datoPruebaId.
	 * @param datoPruebaId el valor datoPruebaId a asignar
	 */
	public void setDatoPruebaId(Long datoPruebaId) {
		this.datoPruebaId = datoPruebaId;
	}
	/**
	 * Método de acceso al campo nombreDato.
	 * @return El valor del campo nombreDato
	 */
	public String getNombreDato() {
		return nombreDato;
	}
	/**
	 * Asigna el valor al campo nombreDato.
	 * @param nombreDato el valor nombreDato a asignar
	 */
	public void setNombreDato(String nombreDato) {
		this.nombreDato = nombreDato;
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
	 * Método de acceso al campo folioCadenaCustodia.
	 * @return El valor del campo folioCadenaCustodia
	 */
	public String getFolioCadenaCustodia() {
		return folioCadenaCustodia;
	}
	/**
	 * Asigna el valor al campo folioCadenaCustodia.
	 * @param folioCadenaCustodia el valor folioCadenaCustodia a asignar
	 */
	public void setFolioCadenaCustodia(String folioCadenaCustodia) {
		this.folioCadenaCustodia = folioCadenaCustodia;
	}
	/**
	 * Método de acceso al campo esAceptado.
	 * @return El valor del campo esAceptado
	 */
	public Boolean getEsAceptado() {
		return esAceptado;
	}
	/**
	 * Asigna el valor al campo esAceptado.
	 * @param esAceptado el valor esAceptado a asignar
	 */
	public void setEsAceptado(Boolean esAceptado) {
		this.esAceptado = esAceptado;
	}
	/**
	 * Método de acceso al campo tiempoCancelacion.
	 * @return El valor del campo tiempoCancelacion
	 */
	public Date getTiempoCancelacion() {
		return tiempoCancelacion;
	}
	/**
	 * Asigna el valor al campo tiempoCancelacion.
	 * @param tiempoCancelacion el valor tiempoCancelacion a asignar
	 */
	public void setTiempoCancelacion(Date tiempoCancelacion) {
		this.tiempoCancelacion = tiempoCancelacion;
	}
	/**
	 * Método de acceso al campo elementoPrueba.
	 * @return El valor del campo elementoPrueba
	 */
	public ElementoDTO getElementoPrueba() {
		return elementoPrueba;
	}
	/**
	 * Asigna el valor al campo elementoPrueba.
	 * @param elementoPrueba el valor elementoPrueba a asignar
	 */
	public void setElementoPrueba(ElementoDTO elementoPrueba) {
		this.elementoPrueba = elementoPrueba;
	}
	/**
	 * Método de acceso al campo documentoPrueba.
	 * @return El valor del campo documentoPrueba
	 */
	public DocumentoDTO getDocumentoPrueba() {
		return documentoPrueba;
	}
	/**
	 * Asigna el valor al campo documentoPrueba.
	 * @param documentoPrueba el valor documentoPrueba a asignar
	 */
	public void setDocumentoPrueba(DocumentoDTO documentoPrueba) {
		this.documentoPrueba = documentoPrueba;
	}
	/**
	 * Método de acceso al campo motivoCancelacion.
	 * @return El valor del campo motivoCancelacion
	 */
	public ValorDTO getMotivoCancelacion() {
		return motivoCancelacion;
	}
	/**
	 * Asigna el valor al campo motivoCancelacion.
	 * @param motivoCancelacion el valor motivoCancelacion a asignar
	 */
	public void setMotivoCancelacion(ValorDTO motivoCancelacion) {
		this.motivoCancelacion = motivoCancelacion;
	}
	/**
	 * Método de acceso al campo relacionesPruebaInvolucrado.
	 * @return El valor del campo relacionesPruebaInvolucrado
	 */
	public List<RelacionPruebaInvolucradoDTO> getRelacionesPruebaInvolucrado() {
		return relacionesPruebaInvolucrado;
	}
	/**
	 * Asigna el valor al campo relacionesPruebaInvolucrado.
	 * @param relacionesPruebaInvolucrado el valor relacionesPruebaInvolucrado a asignar
	 */
	public void setRelacionesPruebaInvolucrado(
			List<RelacionPruebaInvolucradoDTO> relacionesPruebaInvolucrado) {
		this.relacionesPruebaInvolucrado = relacionesPruebaInvolucrado;
	}
	/**
	 * Método de acceso al campo expediente.
	 * @return El valor del campo expediente
	 */
	public ExpedienteDTO getExpediente() {
		return expediente;
	}
	/**
	 * Asigna el valor al campo expediente.
	 * @param expediente el valor expediente a asignar
	 */
	public void setExpediente(ExpedienteDTO expediente) {
		this.expediente = expediente;
	}
	/**
	 * Método de acceso al campo relacionesDatosMedioPrueba.
	 * @return El valor del campo relacionesDatosMedioPrueba
	 */
	public List<RelacionDatoMedioPruebaDTO> getRelacionesDatosMedioPrueba() {
		return relacionesDatosMedioPrueba;
	}
	/**
	 * Asigna el valor al campo relacionesDatosMedioPrueba.
	 * @param relacionesDatosMedioPrueba el valor relacionesDatosMedioPrueba a asignar
	 */
	public void setRelacionesDatosMedioPrueba(
			List<RelacionDatoMedioPruebaDTO> relacionesDatosMedioPrueba) {
		this.relacionesDatosMedioPrueba = relacionesDatosMedioPrueba;
	}
	
}

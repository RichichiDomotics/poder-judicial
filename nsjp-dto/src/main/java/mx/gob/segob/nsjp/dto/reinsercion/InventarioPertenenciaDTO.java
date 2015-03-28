/**
* Nombre del Programa 		: InventarioPertenenciaDTO.java
* Autor 					: EdgarTE
* Compania 					: Ultrasist
* Proyecto 					: NSJP 							Fecha: 28 Mar 2012
* Marca de cambio 			: N/A
* Descripcion General 		: DTO para InventarioPertenencia
* Programa Dependiente 		: N/A
* Programa Subsecuente 		: N/A
* Cond. de ejecucion 		: N/A
* Dias de ejecucion         : N/A 							Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor 					: N/A
* Compania               	: N/A
* Proyecto 					: N/A 							Fecha: N/A
* Modificacion           	: N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.dto.reinsercion;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.detencion.CentroDetencionDTO;
import mx.gob.segob.nsjp.dto.detencion.PertenenciaDTO;
import mx.gob.segob.nsjp.dto.sentencia.SentenciaDTO;

/**
 * Objeto de transferencia de datos (DTO) para la entidad de
 * InventarioPertenencia.
 * @version 1.0
 * @author EdgarTE
 *
 */
public class InventarioPertenenciaDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1824617642307388489L;
	
	private Long inventarioPertenenciaId;
	private SentenciaDTO sentenciaDTO;
	private CentroDetencionDTO centroDetencionDTO;
	private Date fechaIngreso;
	private Boolean definitivo;
	private Boolean entregado;
	private Date fechaEntrega;
	private List<PertenenciaDTO> pertenenciasDTO;
	
	/**
	 * Método de acceso al campo inventarioPertenenciaId.
	 * @return El valor del campo inventarioPertenenciaId
	 */
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
	 * Método de acceso al campo sentenciaDTO.
	 * @return El valor del campo sentenciaDTO
	 */
	public SentenciaDTO getSentenciaDTO() {
		return sentenciaDTO;
	}
	
	/**
	 * Asigna el valor al campo sentenciaDTO.
	 * @param sentenciaDTO el valor sentenciaDTO a asignar
	 */
	public void setSentenciaDTO(SentenciaDTO sentenciaDTO) {
		this.sentenciaDTO = sentenciaDTO;
	}
	
	/**
	 * Método de acceso al campo centroDetencionDTO.
	 * @return El valor del campo centroDetencionDTO
	 */
	public CentroDetencionDTO getCentroDetencionDTO() {
		return centroDetencionDTO;
	}
	
	/**
	 * Asigna el valor al campo centroDetencionDTO.
	 * @param centroDetencionDTO el valor centroDetencionDTO a asignar
	 */
	public void setCentroDetencionDTO(CentroDetencionDTO centroDetencionDTO) {
		this.centroDetencionDTO = centroDetencionDTO;
	}
	
	/**
	 * Método de acceso al campo fechaIngreso.
	 * @return El valor del campo fechaIngreso
	 */
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
	 * Método de acceso al campo pertenenciasDTO.
	 * @return El valor del campo pertenenciasDTO
	 */
	public List<PertenenciaDTO> getPertenenciasDTO() {
		return pertenenciasDTO;
	}
	
	/**
	 * Asigna el valor al campo pertenenciasDTO.
	 * @param pertenenciasDTO el valor pertenenciasDTO a asignar
	 */
	public void setPertenenciasDTO(List<PertenenciaDTO> pertenenciasDTO) {
		this.pertenenciasDTO = pertenenciasDTO;
	}
}

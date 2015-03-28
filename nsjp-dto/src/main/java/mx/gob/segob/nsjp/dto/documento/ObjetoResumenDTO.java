/**
* Nombre del Programa : ObjetoResumenDTO.java
* Autor                            : Emigdio Hernández
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 10/06/2011
* Marca de cambio        : N/A
* Descripcion General    : Resumen de los datos de un elemento del expediente
* del tipo Objeto, este DTO se utiliza para mostrar la información resumida
* de un objeto en el árbol de elementos al editar un documento.
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
package mx.gob.segob.nsjp.dto.documento;

import java.util.Date;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;

/**
 * Resumen de los datos de un elemento del expediente
 * del tipo Objeto, este DTO se utiliza para mostrar la información resumida
 * de un objeto en el árbol de elementos al editar un documento.
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public class ObjetoResumenDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7008360733616402226L;

	private Long elementoId;	
	private Date fechaCreacionElemento;
	private CalidadDTO calidadDTO;
	private String descripcionResumen;
	/**
	 * Método de acceso al campo elementoId.
	 * @return El valor del campo elementoId
	 */
	public Long getElementoId() {
		return elementoId;
	}
	/**
	 * Asigna el valor al campo elementoId.
	 * @param elementoId el valor elementoId a asignar
	 */
	public void setElementoId(Long elementoId) {
		this.elementoId = elementoId;
	}
	/**
	 * Método de acceso al campo fechaCreacionElemento.
	 * @return El valor del campo fechaCreacionElemento
	 */
	public Date getFechaCreacionElemento() {
		return fechaCreacionElemento;
	}
	/**
	 * Asigna el valor al campo fechaCreacionElemento.
	 * @param fechaCreacionElemento el valor fechaCreacionElemento a asignar
	 */
	public void setFechaCreacionElemento(Date fechaCreacionElemento) {
		this.fechaCreacionElemento = fechaCreacionElemento;
	}
	/**
	 * Método de acceso al campo calidadDTO.
	 * @return El valor del campo calidadDTO
	 */
	public CalidadDTO getCalidadDTO() {
		return calidadDTO;
	}
	/**
	 * Asigna el valor al campo calidadDTO.
	 * @param calidadDTO el valor calidadDTO a asignar
	 */
	public void setCalidadDTO(CalidadDTO calidadDTO) {
		this.calidadDTO = calidadDTO;
	}
	/**
	 * Método de acceso al campo descripcionResumen.
	 * @return El valor del campo descripcionResumen
	 */
	public String getDescripcionResumen() {
		return descripcionResumen;
	}
	/**
	 * Asigna el valor al campo descripcionResumen.
	 * @param descripcionResumen el valor descripcionResumen a asignar
	 */
	public void setDescripcionResumen(String descripcionResumen) {
		this.descripcionResumen = descripcionResumen;
	}
	
}

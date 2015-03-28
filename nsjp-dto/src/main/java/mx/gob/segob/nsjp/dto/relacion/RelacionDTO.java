/**
 * Nombre del Programa : RelacionDTO.java
 * Autor                            : cesarAgustin
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 20 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : DTO para el objeto Relacion
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
package mx.gob.segob.nsjp.dto.relacion;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.elemento.ElementoDTO;

/**
 * DTO para el objeto Relacion.
 * 
 * @version 1.0
 * @author cesarAgustin
 * 
 */
public class RelacionDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4390002598615948553L;

	private Long relacionId;	
	private ElementoDTO elementoByComplementoId;
	private ElementoDTO elementoBySujetoId;
	private Short tipoRelacion;
	private CatRelacionDTO catRelacion;
	private Boolean esActivo;

	
	/**
	 * Método de acceso al campo relacionId.
	 * @return El valor del campo relacionId
	 */
	public Long getRelacionId() {
		return relacionId;
	}
	/**
	 * Asigna el valor al campo relacionId.
	 * @param relacionId el valor relacionId a asignar
	 */
	public void setRelacionId(Long relacionId) {
		this.relacionId = relacionId;
	}
	/**
	 * Método de acceso al campo elementoByComplementoId.
	 * @return El valor del campo elementoByComplementoId
	 */
	public ElementoDTO getElementoByComplementoId() {
		return elementoByComplementoId;
	}
	/**
	 * Asigna el valor al campo elementoByComplementoId.
	 * @param elementoByComplementoId el valor elementoByComplementoId a asignar
	 */
	public void setElementoByComplementoId(ElementoDTO elementoByComplementoId) {
		this.elementoByComplementoId = elementoByComplementoId;
	}
	/**
	 * Método de acceso al campo elementoBySujetoId.
	 * @return El valor del campo elementoBySujetoId
	 */
	public ElementoDTO getElementoBySujetoId() {
		return elementoBySujetoId;
	}
	/**
	 * Asigna el valor al campo elementoBySujetoId.
	 * @param elementoBySujetoId el valor elementoBySujetoId a asignar
	 */
	public void setElementoBySujetoId(ElementoDTO elementoBySujetoId) {
		this.elementoBySujetoId = elementoBySujetoId;
	}
	/**
	 * Método de acceso al campo tipoRelacion.
	 * @return El valor del campo tipoRelacion
	 */
	public Short getTipoRelacion() {
		return tipoRelacion;
	}
	/**
	 * Asigna el valor al campo tipoRelacion.
	 * @param tipoRelacion el valor tipoRelacion a asignar
	 */
	public void setTipoRelacion(Short tipoRelacion) {
		this.tipoRelacion = tipoRelacion;
	}
	/**
	 * Método de acceso al campo catRelacion.
	 * @return El valor del campo catRelacion
	 */
	public CatRelacionDTO getCatRelacion() {
		return catRelacion;
	}
	/**
	 * Asigna el valor al campo catRelacion.
	 * @param catRelacion el valor catRelacion a asignar
	 */
	public void setCatRelacion(CatRelacionDTO catRelacion) {
		this.catRelacion = catRelacion;
	}
	/**
	 * Método de acceso al campo esActivo.
	 * @return El valor del campo esActivo
	 */
	public Boolean getEsActivo() {
		return esActivo;
	}
	/**
	 * Asigna el valor al campo esActivo.
	 * @param esActivo el valor esActivo a asignar
	 */
	public void setEsActivo(Boolean esActivo) {
		this.esActivo = esActivo;
	}	
}

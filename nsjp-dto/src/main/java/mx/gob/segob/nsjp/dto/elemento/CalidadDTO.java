/**
* Nombre del Programa : CalidadDTO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 8 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : Clase para la transferencia de datos entre la vista y el negocio, del objeto calidad
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
package mx.gob.segob.nsjp.dto.elemento;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;

/**
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto calidad.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class CalidadDTO extends GenericDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4362500186227645063L;
	
	private Long calidadId;
	private Calidades calidades;	
	private String descripcionEstadoFisico;
	private ValorDTO valorIdCalidad;	

	/**
	 * Método de acceso al campo calidadId.
	 * @return El valor del campo calidadId
	 */
	public Long getCalidadId() {
		return calidadId;
	}
	/**
	 * Asigna el valor al campo calidadId.
	 * @param calidadId el valor calidadId a asignar
	 */
	public void setCalidadId(Long calidadId) {
		this.calidadId = calidadId;
	}
	/**
	 * Método de acceso al campo calidades.
	 * @return El valor del campo calidades
	 */
	public Calidades getCalidades() {
		return calidades;
	}
	/**
	 * Asigna el valor al campo calidades.
	 * @param calidades el valor calidades a asignar
	 */
	public void setCalidades(Calidades calidades) {
		this.calidades = calidades;
	}
	/**
	 * Método de acceso al campo descripcionEstadoFisico.
	 * @return El valor del campo descripcionEstadoFisico
	 */
	public String getDescripcionEstadoFisico() {
		return descripcionEstadoFisico;
	}
	/**
	 * Asigna el valor al campo descripcionEstadoFisico.
	 * @param descripcionEstadoFisico el valor descripcionEstadoFisico a asignar
	 */
	public void setDescripcionEstadoFisico(String descripcionEstadoFisico) {
		this.descripcionEstadoFisico = descripcionEstadoFisico;
	}
	/**
	 * Método de acceso al campo valorIdCalidad.
	 * @return El valor del campo valorIdCalidad
	 */
	public ValorDTO getValorIdCalidad() {
		return valorIdCalidad;
	}
	/**
	 * Asigna el valor al campo valorIdCalidad.
	 * @param valorIdCalidad el valor valorIdCalidad a asignar
	 */
	public void setValorIdCalidad(ValorDTO valorIdCalidad) {
		this.valorIdCalidad = valorIdCalidad;
	}
}

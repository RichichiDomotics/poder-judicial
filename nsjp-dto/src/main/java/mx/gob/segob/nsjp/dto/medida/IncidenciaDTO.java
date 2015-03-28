/**
* Nombre del Programa : IncidenciaDTO.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 05/08/2011
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
package mx.gob.segob.nsjp.dto.medida;

import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;

/**
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto Incidencia.
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public class IncidenciaDTO extends DocumentoDTO{
	private String reporte;
	private MedidaDTO medida;
	
	
	/**
	 * Método de acceso al campo reporte.
	 * @return El valor del campo reporte
	 */
	public String getReporte() {
		return reporte;
	}
	/**
	 * Asigna el valor al campo reporte.
	 * @param reporte el valor reporte a asignar
	 */
	public void setReporte(String reporte) {
		this.reporte = reporte;
	}
	/**
	 * Método de acceso al campo medida.
	 * @return El valor del campo medida
	 */
	public MedidaDTO getMedida() {
		return medida;
	}
	/**
	 * Asigna el valor al campo medida.
	 * @param medida el valor medida a asignar
	 */
	public void setMedida(MedidaDTO medida) {
		this.medida = medida;
	} 
}

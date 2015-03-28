/**
* Nombre del Programa : SolicitarBeneficiosPreliberacionWSDTO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 18 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : DTO sencillo para la creación de una solicitud de beneficios de preliberacion
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
package mx.gob.segob.nsjp.dto.solicitud;

/**
 * DTO sencillo para la creación de una solicitud de beneficios de preliberacion.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class SolicitudBeneficiosPreliberacionWSDTO extends SolicitudAudienciaBasicoWSDTO {

	private String numCarpetaEjecucion;

	/**
	 * Método de acceso al campo numCarpetaEjecucion.
	 * @return El valor del campo numCarpetaEjecucion
	 */
	public String getNumCarpetaEjecucion() {
		return numCarpetaEjecucion;
	}

	/**
	 * Asigna el valor al campo numCarpetaEjecucion.
	 * @param numCarpetaEjecucion el valor numCarpetaEjecucion a asignar
	 */
	public void setNumCarpetaEjecucion(String numCarpetaEjecucion) {
		this.numCarpetaEjecucion = numCarpetaEjecucion;
	}
	
	
}

/**
* Nombre del Programa : SolicitudTrasladoImputadoWSDTO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 21 Sep 2011
* Marca de cambio        : N/A
* Descripcion General    : DTO para registrar una solicitud de traslado de imputado
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
 * DTO para registrar una solicitud de traslado de imputado.
 * @author cesarAgustin
 *
 */
public class SolicitudTrasladoImputadoWSDTO extends SolicitudAudienciaBasicoWSDTO {

	 private String folioInvolucrado;
	 private String folioAudiecia;
	/**
	 * @return the folioInvolucrado
	 */
	public String getFolioInvolucrado() {
		return folioInvolucrado;
	}
	/**
	 * @param folioInvolucrado the folioInvolucrado to set
	 */
	public void setFolioInvolucrado(String folioInvolucrado) {
		this.folioInvolucrado = folioInvolucrado;
	}
	/**
	 * @return the folioAudiecia
	 */
	public String getFolioAudiecia() {
		return folioAudiecia;
	}
	/**
	 * @param folioAudiecia the folioAudiecia to set
	 */
	public void setFolioAudiecia(String folioAudiecia) {
		this.folioAudiecia = folioAudiecia;
	}	 	 	
	 	 	
}

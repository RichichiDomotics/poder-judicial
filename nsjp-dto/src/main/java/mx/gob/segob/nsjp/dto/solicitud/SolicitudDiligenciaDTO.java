/**
* Nombre del Programa : SolicitudDiligenciaDTO.java
* Autor                            : cesar
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 28 Jul 2011
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
package mx.gob.segob.nsjp.dto.solicitud;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class SolicitudDiligenciaDTO extends SolicitudDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2420944111146903155L;

	private ValorDTO tipoDiligencia;

	public SolicitudDiligenciaDTO(Long idSolDiligencia) {		
		this.setDocumentoId(idSolDiligencia);
	}

	public SolicitudDiligenciaDTO() {
		
	}

	/**
	 * Método de acceso al campo tipoDiligencia.
	 * @return El valor del campo tipoDiligencia
	 */
	public ValorDTO getTipoDiligencia() {
		return tipoDiligencia;
	}

	/**
	 * Asigna el valor al campo tipoDiligencia.
	 * @param tipoDiligencia el valor tipoDiligencia a asignar
	 */
	public void setTipoDiligencia(ValorDTO tipoDiligencia) {
		this.tipoDiligencia = tipoDiligencia;
	}	
	 
}

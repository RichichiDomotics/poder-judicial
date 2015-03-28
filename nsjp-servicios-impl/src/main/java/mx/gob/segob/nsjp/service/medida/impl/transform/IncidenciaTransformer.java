/**
* Nombre del Programa : IncidenciaTransformer.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 08/08/2011
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
package mx.gob.segob.nsjp.service.medida.impl.transform;

import mx.gob.segob.nsjp.dto.medida.IncidenciaDTO;
import mx.gob.segob.nsjp.model.Incidencia;
import mx.gob.segob.nsjp.service.documento.impl.tranform.DocumentoTransformer;

/**
 * Transforma el objeto de Incidencia a IncidenciaDTO, y viceversa.
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public class IncidenciaTransformer {

	public static IncidenciaDTO transformarIncidencia(Incidencia incidencia){
		IncidenciaDTO incidenciaDTO = new IncidenciaDTO(); 
		incidenciaDTO = (IncidenciaDTO)DocumentoTransformer.transformarDocumento(incidencia);
		incidenciaDTO.setReporte(incidencia.getReporte());
		return incidenciaDTO;
	}

	public static Incidencia transformarIncidencia(IncidenciaDTO incidenciaDTO){
		Incidencia incidencia = new Incidencia(); 
		incidencia = (Incidencia)DocumentoTransformer.transformarDocumentoDTO(incidenciaDTO);
		incidencia.setReporte(incidenciaDTO.getReporte());
		return incidencia;
	}

}

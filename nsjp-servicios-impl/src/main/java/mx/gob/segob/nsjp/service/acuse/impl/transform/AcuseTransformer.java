/**
* Nombre del Programa : AcuseTransformer.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 23/06/2011
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
package mx.gob.segob.nsjp.service.acuse.impl.transform;


import mx.gob.segob.nsjp.dto.documento.AcuseReciboDTO;
import mx.gob.segob.nsjp.model.AcuseRecibo;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
public class AcuseTransformer {
	
	public static AcuseReciboDTO transformarDTO(AcuseRecibo acus){
		AcuseReciboDTO acuseReciboDTO=new AcuseReciboDTO();
		
		acuseReciboDTO.setNombreRecibe(acus.getNombreRecibe());
		acuseReciboDTO.setFechaAcuse(acus.getFechaAcuse());
		
		return acuseReciboDTO;
	}

}

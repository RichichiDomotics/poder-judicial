/**
* Nombre del Programa : BitacoraConsultaTransformer.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 31/08/2011
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
package mx.gob.segob.nsjp.service.bitacora.impl.transform;

import mx.gob.segob.nsjp.dto.bitacora.BitacoraConsultaDTO;
import mx.gob.segob.nsjp.model.BitacoraConsulta;
import mx.gob.segob.nsjp.service.expediente.impl.transform.UsuarioTransformer;

/**
 * Clase para convertir objetos BitacoraConsulta a BitacoraConsultaDTO y viceversa.
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public class BitacoraConsultaTransformer {

	public static BitacoraConsultaDTO transformarBitacoraConsulta( BitacoraConsulta bitacoraConsulta ){
		BitacoraConsultaDTO bitacoraConsultaDTO = new BitacoraConsultaDTO();
		if(bitacoraConsulta==null)
			return null;
		
		bitacoraConsultaDTO.setBitacoraConsultaId(bitacoraConsulta.getBitacoraConsultaId());
		bitacoraConsultaDTO.setFechaConsulta(bitacoraConsulta.getFechaConsulta());
		bitacoraConsultaDTO.setEsPermitida(bitacoraConsulta.getEsPermitida());
		
		if(bitacoraConsulta.getNumeroExpediente()!= null){
			bitacoraConsultaDTO.setNumeroExpediente(bitacoraConsulta.getNumeroExpediente().getNumeroExpediente());
			bitacoraConsultaDTO.setNumeroExpedienteId(bitacoraConsulta.getNumeroExpediente().getNumeroExpedienteId());
		}

		if(bitacoraConsulta.getUsuario()!= null && bitacoraConsulta.getUsuario().getFuncionario()!= null){
			bitacoraConsultaDTO.setUsuario(UsuarioTransformer.transformarUsuario(bitacoraConsulta.getUsuario()));
		}

		
		return bitacoraConsultaDTO;
	}
}

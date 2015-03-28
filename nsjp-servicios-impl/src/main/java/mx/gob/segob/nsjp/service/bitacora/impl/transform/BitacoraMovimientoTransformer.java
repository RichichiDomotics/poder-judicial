/**
* Nombre del Programa : BitacoraMovimientoTransformer.java
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

import mx.gob.segob.nsjp.dto.bitacora.BitacoraMovimientoDTO;
import mx.gob.segob.nsjp.dto.bitacora.CategoriaElementoDTO;
import mx.gob.segob.nsjp.model.BitacoraMovimiento;
import mx.gob.segob.nsjp.service.expediente.impl.transform.UsuarioTransformer;

/**
 * Clase para convertir objetos BitacoraMovimiento a BitacoraMovimientoDTO y viceversa.
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public class BitacoraMovimientoTransformer {

	public static BitacoraMovimientoDTO transformarBitacoraMovimiento(BitacoraMovimiento bitacoraMovimiento){
		BitacoraMovimientoDTO bitacoraMovimientoDTO = new BitacoraMovimientoDTO();
		if(bitacoraMovimiento==null)
			return null;
		
		bitacoraMovimientoDTO.setBitacoraMovimientoId(bitacoraMovimiento.getBitacoraMovimientoId());
		bitacoraMovimientoDTO.setFechaMovimiento(bitacoraMovimiento.getFechaMovimiento());
		bitacoraMovimientoDTO.setGrupoDatos(bitacoraMovimiento.getGrupoDatos());
		bitacoraMovimientoDTO.setAccion(bitacoraMovimiento.getAccion());
		bitacoraMovimientoDTO.setCampo(bitacoraMovimiento.getCampo());
		bitacoraMovimientoDTO.setValorAnterior(bitacoraMovimiento.getValorAnterior());
		bitacoraMovimientoDTO.setValorNuevo(bitacoraMovimiento.getValorNuevo());
		
		if(bitacoraMovimiento.getNumeroExpediente()!= null){
			bitacoraMovimientoDTO.setNumeroExpediente(bitacoraMovimiento.getNumeroExpediente().getNumeroExpediente());
			bitacoraMovimientoDTO.setNumeroExpedienteId(bitacoraMovimiento.getNumeroExpediente().getNumeroExpedienteId());
		}

		if(bitacoraMovimiento.getUsuario()!= null && bitacoraMovimiento.getUsuario().getFuncionario()!= null){
			bitacoraMovimientoDTO.setUsuario(UsuarioTransformer.transformarUsuario(bitacoraMovimiento.getUsuario()));
		}

		if(bitacoraMovimiento.getElemento()!= null)
			bitacoraMovimientoDTO.setElemento(bitacoraMovimiento.getElemento());

		if(bitacoraMovimiento.getCategoriaElemento()!= null){
			//TODO Realizar Transformer
			CategoriaElementoDTO categoriaElementoDTO = new CategoriaElementoDTO(bitacoraMovimiento.getCategoriaElemento().getCategoriaElementoId());
			categoriaElementoDTO.setNombre(bitacoraMovimiento.getCategoriaElemento().getNombre());
			bitacoraMovimientoDTO.setCategoriaElemento(categoriaElementoDTO);
		}
		
		return bitacoraMovimientoDTO;
	}
}

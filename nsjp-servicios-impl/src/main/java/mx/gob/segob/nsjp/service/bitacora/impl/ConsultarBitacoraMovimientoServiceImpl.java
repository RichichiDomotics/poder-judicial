/**
* Nombre del Programa : ConsultarBitacoraMovimientoServiceImpl.java
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
package mx.gob.segob.nsjp.service.bitacora.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.bitacora.BitacoraMovimientoDAO;
import mx.gob.segob.nsjp.dto.bitacora.BitacoraMovimientoDTO;
import mx.gob.segob.nsjp.model.BitacoraMovimiento;
import mx.gob.segob.nsjp.service.bitacora.ConsultarBitacoraMovimientoService;
import mx.gob.segob.nsjp.service.bitacora.impl.transform.BitacoraMovimientoTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author GustavoBP
 *
 */
@Service
@Transactional
public class ConsultarBitacoraMovimientoServiceImpl implements ConsultarBitacoraMovimientoService {

	@Autowired
    private  BitacoraMovimientoDAO bitacoraMovimientoDAO;
    
	public List<BitacoraMovimientoDTO> consultarBitacoraMovimientoPorNumeroExpedienteCategoria(
			String numeroExpediente, Long idCategoriaElemento) throws NSJPNegocioException{
//		No es necesaria la validación
//		if(numeroExpediente==null || !numeroExpediente.trim().isEmpty() ||
//				idCategoriaElemento==null || idCategoriaElemento <= 0)
//			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		List<BitacoraMovimientoDTO>  bitacorasDTO = new ArrayList<BitacoraMovimientoDTO>();
		
		List<BitacoraMovimiento> bitacoras = bitacoraMovimientoDAO.consultarBitacoraMovimientoPorFiltro(
				numeroExpediente, idCategoriaElemento);
		
		for (BitacoraMovimiento bitacoraMovimiento : bitacoras) {
			bitacorasDTO.add(BitacoraMovimientoTransformer.transformarBitacoraMovimiento(bitacoraMovimiento));
		}
		return bitacorasDTO;
	}
			
}

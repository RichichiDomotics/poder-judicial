/**
* Nombre del Programa : BitacoraMovimientoDelegateImpl.java
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
package mx.gob.segob.nsjp.delegate.bitacora.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.bitacora.BitacoraMovimientoDelegate;
import mx.gob.segob.nsjp.dto.bitacora.BitacoraMovimientoDTO;
import mx.gob.segob.nsjp.dto.bitacora.RegistroBitacoraDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.service.bitacora.ConsultarBitacoraMovimientoService;
import mx.gob.segob.nsjp.service.bitacora.ConsultarRegistroBitacoraService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Exposición de servicios referentes a la Bitacora de Movimientos IAM
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
@Service("BitacoraMovimientoDelegate")
public class BitacoraMovimientoDelegateImpl implements
		BitacoraMovimientoDelegate {
	@Autowired
	private ConsultarBitacoraMovimientoService consultarBitacoraMovimientoService;
	
	@Autowired
	private ConsultarRegistroBitacoraService consultarRegistroBitacoraService;
	
	public List<BitacoraMovimientoDTO> consultarBitacoraMovimientoPorNumeroExpedienteCategoria(
			String numeroExpediente, Long idCategoriaElemento) throws NSJPNegocioException{
		return consultarBitacoraMovimientoService.consultarBitacoraMovimientoPorNumeroExpedienteCategoria(numeroExpediente, idCategoriaElemento);
	}

    @Override
    public List<RegistroBitacoraDTO> consultarRegistrosByExpediente(
            ExpedienteDTO expInput) throws NSJPNegocioException {
        return this.consultarRegistroBitacoraService.consultarRegistrosByExpediente(expInput);
    }
	
}

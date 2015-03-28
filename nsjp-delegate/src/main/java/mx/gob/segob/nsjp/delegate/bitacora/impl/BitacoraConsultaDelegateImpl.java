/**
* Nombre del Programa : BitacoraConsultaDelegateImpl.java
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.bitacora.BitacoraConsultaDelegate;
import mx.gob.segob.nsjp.dto.bitacora.BitacoraConsultaDTO;
import mx.gob.segob.nsjp.dto.bitacora.FiltroBitacoraConsultaDTO;
import mx.gob.segob.nsjp.service.bitacora.ConsultarBitacoraConsultaService;

/**
 * Exposición de servicios referentes a la Bitacora de Consulta INQ
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
@Service("BitacoraConsultaDelegate")
public class BitacoraConsultaDelegateImpl implements BitacoraConsultaDelegate{
	@Autowired
	private ConsultarBitacoraConsultaService consultarBitacoraConsultaService;
	
	public List<BitacoraConsultaDTO> consultarBitacoraConsultaPorFiltro(FiltroBitacoraConsultaDTO filtroBitacoraConsultaDTO) throws NSJPNegocioException{
		return consultarBitacoraConsultaService.consultarBitacoraConsultaPorFiltro(filtroBitacoraConsultaDTO);
	}
	
}

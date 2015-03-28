/**
* Nombre del Programa : LugarDelegateImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 15 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del Delegate para los servicios relacionados con Lugar
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
package mx.gob.segob.nsjp.delegate.lugar.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.lugar.LugarDelegate;
import mx.gob.segob.nsjp.dto.domicilio.AsentamientoDTO;
import mx.gob.segob.nsjp.dto.domicilio.LugarDTO;
import mx.gob.segob.nsjp.service.lugar.CompletarAsentamientoService;
import mx.gob.segob.nsjp.service.lugar.IngresarLugarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementacion del Delegate para los servicios relacionados con Lugar.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service("lugarDelegate")
public class LugarDelegateImpl implements LugarDelegate {
	
	@Autowired
	private IngresarLugarService ingresarLugarService;
	@Autowired
	private CompletarAsentamientoService completarAsentamientoService;
	
	@Override
	public LugarDTO ingresarLugar(LugarDTO lugarDTO)
			throws NSJPNegocioException {
		
		return this.ingresarLugarService.ingresarLugar(lugarDTO);
	}

	@Override
	public List<AsentamientoDTO> completarAsentamientoXCodigoPostal(
			String codigoPostal) throws NSJPNegocioException {
		return completarAsentamientoService.completarAsentamientoXCodigoPostal(codigoPostal.trim());
	}
	
	@Override
	public String obtenerCodigoPostalXIdAsentamiento(Long idAsentamiento) throws NSJPNegocioException {
		return completarAsentamientoService.obtenerCodigoPostalXIdAsentamiento(idAsentamiento);
	}

	@Override
	public AsentamientoDTO obtenerAentamientoPrId(Long idAsentamiento)
			throws NSJPNegocioException {
		return completarAsentamientoService.obtenerAentamientoPrId(idAsentamiento);
	}

}

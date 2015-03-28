/**
 * Nombre del Programa : CentroDetencionDelegateImpl.java
 * Autor                            : Cuauhtemoc Paredes Serrano
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 22-Sept-2011
 * Marca de cambio        : N/A
 * Descripcion General    : N/A
 * Programa Dependient    :N/A
 * Programa Subsecuente   :N/A
 * Cond. de ejecucion     :N/A
 * Dias de ejecucion      :N/A                                Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                            :N/A
 * Compania                         :N/A
 * Proyecto                         :N/A                      Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.delegate.detencion.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.detencion.CentroDetencionDelegate;
import mx.gob.segob.nsjp.dto.detencion.CentroDetencionDTO;
import mx.gob.segob.nsjp.service.detencion.CentroDetencionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author CuauhtemocPS
 *
 */

@Service
public class CentroDetencionDelegateImpl implements CentroDetencionDelegate {
	
	@Autowired
	private CentroDetencionService centroDetencionService;
	
	@Override
	public CentroDetencionDTO consultarCentroDetencion(Long centroDetencionId)throws NSJPNegocioException{
		
		return centroDetencionService.consultarCentroDetencion(centroDetencionId);
	}

	@Override
	public List<CentroDetencionDTO> consultarCentrosDetencionPorTipo(Long tipoCentroDetencion) throws NSJPNegocioException {
		
		return centroDetencionService.consultarCentrosDetencionPorTipo(tipoCentroDetencion);
	}
	

}

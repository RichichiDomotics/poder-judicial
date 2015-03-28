/**
* Nombre del Programa : ReinsercionDelegateImpl.java
* Autor                            : EdgarTE
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 29 Mar 2012
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
package mx.gob.segob.nsjp.delegate.reinsercion.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.reinsercion.ReinsercionDelegate;
import mx.gob.segob.nsjp.dto.detencion.PertenenciaDTO;
import mx.gob.segob.nsjp.dto.reinsercion.InventarioPertenenciaDTO;
import mx.gob.segob.nsjp.service.reinsercion.ReinsercionService;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author EdgarTE
 *
 */
@Service
@Transactional
public class ReinsercionDelegateImpl implements ReinsercionDelegate {
	
	@Autowired
	private ReinsercionService reinsercionService;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.reinsercion.ReinsercionDelegate#crearInventarioPertenencia(mx.gob.segob.nsjp.dto.reinsercion.InventarioPertenenciaDTO)
	 */
	@Override
	public InventarioPertenenciaDTO crearInventarioPertenencia(
			InventarioPertenenciaDTO inventarioPertenenciaDTO) {
		return reinsercionService.crearInventarioPertenencia(inventarioPertenenciaDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.reinsercion.ReinsercionDelegate#actualizarInventarioPertenencias(mx.gob.segob.nsjp.dto.reinsercion.InventarioPertenenciaDTO)
	 */
	@Override
	public void actualizarInventarioPertenencias(
			InventarioPertenenciaDTO inventarioPertenenciaDTO) {
		reinsercionService.actualizarInventarioPertenencias(inventarioPertenenciaDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.reinsercion.ReinsercionDelegate#consultarPertenenciasPorInventario(mx.gob.segob.nsjp.dto.reinsercion.InventarioPertenenciaDTO)
	 */
	@Override
	public List<PertenenciaDTO> consultarPertenenciasPorInventario(
			InventarioPertenenciaDTO inventarioPertenenciaDTO) throws NSJPNegocioException{
		return reinsercionService.consultarPertenenciasPorInventario(inventarioPertenenciaDTO);
	}

}

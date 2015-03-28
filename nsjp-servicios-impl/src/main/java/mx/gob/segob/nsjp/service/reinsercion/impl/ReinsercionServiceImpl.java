/**
* Nombre del Programa 	: ReinsercionServiceImpl.java
* Autor 				: EdgarTE
* Compania 				: Ultrasist
* Proyecto 				: NSJP 								Fecha: 28 Mar 2012
* Marca de cambio 		: N/A
* Descripcion General 	: Describir el objetivo de la clase de manera breve
* Programa Dependiente 	: N/A
* Programa Subsecuente 	: N/A
* Cond. de ejecucion 	: N/A
* Dias de ejecucion 	: N/A 								Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor 				: N/A
* Compania 				: N/A
* Proyecto 				: N/A 								Fecha: N/A
* Modificacion 			: N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.service.reinsercion.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.reinsercion.InventarioPertenenciaDAO;
import mx.gob.segob.nsjp.dto.detencion.PertenenciaDTO;
import mx.gob.segob.nsjp.dto.reinsercion.InventarioPertenenciaDTO;
import mx.gob.segob.nsjp.model.InventarioPertenencia;
import mx.gob.segob.nsjp.model.Pertenencia;
import mx.gob.segob.nsjp.service.detencion.impl.transform.PertenenciaTransformer;
import mx.gob.segob.nsjp.service.reinsercion.ReinsercionService;
import mx.gob.segob.nsjp.service.reinsercion.impl.transform.InventarioPertenenciaTransformer;

/**
 * Clase que expone los m&eacute;todos de los DAO's para su consumo
 * desde las capas superiores.
 * @version 1.0
 * @author EdgarTE
 *
 */
@Service
@Transactional
public class ReinsercionServiceImpl implements ReinsercionService {
	
	@Autowired
	private InventarioPertenenciaDAO inventarioPertenenciaDAO;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.reinsercion.ReinsercionService#crearInventarioPertenencia(mx.gob.segob.nsjp.dto.reinsercion.InventarioPertenenciaDTO)
	 */
	@Override
	public InventarioPertenenciaDTO crearInventarioPertenencia(
			InventarioPertenenciaDTO inventarioPertenenciaDTO) {
		InventarioPertenencia inventarioPertenencia = InventarioPertenenciaTransformer.transformar(inventarioPertenenciaDTO, 
				InventarioPertenenciaTransformer.CON_PERTENENCIAS);
		Long inventarioId = inventarioPertenenciaDAO.create(inventarioPertenencia);
		inventarioPertenenciaDAO.flush();
		inventarioPertenenciaDTO.setInventarioPertenenciaId(inventarioId);
		return inventarioPertenenciaDTO;
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.reinsercion.ReinsercionService#actualizarInventarioPertenencias(mx.gob.segob.nsjp.dto.reinsercion.InventarioPertenenciaDTO)
	 */
	@Override
	public void actualizarInventarioPertenencias(
			InventarioPertenenciaDTO inventarioPertenenciaDTO) {
		InventarioPertenencia inventarioPertenencia = InventarioPertenenciaTransformer.transformar(inventarioPertenenciaDTO, 
				InventarioPertenenciaTransformer.CON_PERTENENCIAS);
		inventarioPertenenciaDAO.merge(inventarioPertenencia);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.reinsercion.ReinsercionService#consultarPertenenciasPorInventario(mx.gob.segob.nsjp.dto.reinsercion.InventarioPertenenciaDTO)
	 */
	@Override
	public List<PertenenciaDTO> consultarPertenenciasPorInventario(
			InventarioPertenenciaDTO inventarioPertenenciaDTO) throws NSJPNegocioException {
		List <PertenenciaDTO> pertenenciasDTO = null;
		List<Pertenencia> pertenencias = inventarioPertenenciaDAO.consultarPertenenciasPorInventario(inventarioPertenenciaDTO);
		if (pertenencias != null && !pertenencias.isEmpty()){
			pertenenciasDTO = new ArrayList<PertenenciaDTO>();
			for (Pertenencia p : pertenencias){
				pertenenciasDTO.add(PertenenciaTransformer.transformarEntity(p));
			}
		}
		return pertenenciasDTO;
	}

}

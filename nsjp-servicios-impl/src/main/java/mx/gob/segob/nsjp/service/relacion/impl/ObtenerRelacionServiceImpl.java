/**
* Nombre del Programa : ObtenerRelacionServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 20 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para recuperar las relaciones entre elementos
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
package mx.gob.segob.nsjp.service.relacion.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.relacion.Relaciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.relacion.RelacionDAO;
import mx.gob.segob.nsjp.dto.relacion.RelacionDTO;
import mx.gob.segob.nsjp.model.Relacion;
import mx.gob.segob.nsjp.service.relacion.ObtenerRelacionService;
import mx.gob.segob.nsjp.service.relacion.impl.transform.RelacionTransformer;

/**
 * Implementacion del servicio para recuperar las relaciones entre elementos.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class ObtenerRelacionServiceImpl implements ObtenerRelacionService {

	@Autowired
	private RelacionDAO relacionDAO;
	
	@Override
	public List<RelacionDTO> obtenerRelacionSimple(Long idElemento, Relaciones tiporRelacion)
			throws NSJPNegocioException {
				
		List<Relacion> relaciones = relacionDAO.obtenerRelacionSimple(idElemento, new Long(tiporRelacion.ordinal()));
		
		List<RelacionDTO> relacionesDTO = new ArrayList<RelacionDTO>();
		for (Relacion relacion : relaciones) {
			relacionesDTO.add(RelacionTransformer.transformarRelacion(relacion));
		}
		
		return relacionesDTO;
	}

}

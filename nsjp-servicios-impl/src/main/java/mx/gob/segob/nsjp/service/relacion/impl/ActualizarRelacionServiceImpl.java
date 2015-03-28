/**
* Nombre del Programa : ActualizarRelacionServiceImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 29/08/2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para actualizar el estatus EsActivo de la Relación
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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.relacion.RelacionDAO;
import mx.gob.segob.nsjp.model.Relacion;
import mx.gob.segob.nsjp.service.relacion.ActualizarRelacionService;

/**
 * Implementacion del servicio para actualizar el estatus EsActivo de la Relación
 * 
 * @version 1.0
 * @author GustavoBP 
 */
@Service
@Transactional
public class ActualizarRelacionServiceImpl implements ActualizarRelacionService {

	@Autowired
	private RelacionDAO relacionDAO;
	
	@Override
	public void actualizarEsActivoRelaciones(List<Long> idRelaciones ) throws NSJPNegocioException{
		
		if( idRelaciones==null || idRelaciones.isEmpty())
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		Relacion relacion = null;
		for (Long idRelacion : idRelaciones) {
			//Verificar que la relacion se encuentre en BD
			relacion = relacionDAO.read(idRelacion);
			
			if(relacion==null)
				throw new NSJPNegocioException(CodigoError.INFORMACION_PARAMETROS_ERRONEA);
			
			relacion.setEsActivo(false);
			relacionDAO.update(relacion);
		}
	}
}

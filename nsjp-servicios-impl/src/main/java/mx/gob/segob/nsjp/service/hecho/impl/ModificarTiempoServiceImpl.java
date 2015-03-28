/**
* Nombre del Programa : ModificarTiempoServiceImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 05/10/2011
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
package mx.gob.segob.nsjp.service.hecho.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.hecho.TiempoDAO;
import mx.gob.segob.nsjp.dto.hecho.TiempoDTO;
import mx.gob.segob.nsjp.model.Tiempo;
import mx.gob.segob.nsjp.service.hecho.IngresarTiempoService;
import mx.gob.segob.nsjp.service.hecho.ModificarTiempoService;
import mx.gob.segob.nsjp.service.hecho.impl.transform.TiempoTransformer;

/**
 * Implementación de los servicios para modificar el Lugar asociado a un Hecho.
 *
 * @version 1.0
 * @author GustavoBP
 *
 */
@Service
@Transactional
public class ModificarTiempoServiceImpl implements ModificarTiempoService {

	public static final Logger logger = Logger.getLogger(ModificarTiempoServiceImpl.class);
	
	@Autowired
	private TiempoDAO tiempoDAO;
	
	@Override
	public TiempoDTO modificarTiempo(TiempoDTO tiempoDTO) throws NSJPNegocioException{
		
		if(tiempoDTO==null || tiempoDTO.getTiempoId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		Tiempo tiempo = TiempoTransformer.transformarTiempo(tiempoDTO); 
		Tiempo tiempoBD = tiempoDAO.read(tiempoDTO.getTiempoId());
		tiempoBD = TiempoTransformer.transformarTiempoUpdate(tiempoBD, tiempo);
		
		tiempoDAO.update(tiempoBD);
		
		return tiempoDTO;
	}
}

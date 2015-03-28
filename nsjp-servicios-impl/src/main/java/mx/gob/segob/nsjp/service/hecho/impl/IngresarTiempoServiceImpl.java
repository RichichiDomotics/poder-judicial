/**
* Nombre del Programa : IngresarTiempoServiceImpl.java
* Autor                            : cesar
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 15 Jun 2011
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
import mx.gob.segob.nsjp.service.hecho.impl.transform.TiempoTransformer;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author cesar
 *
 */
@Service
@Transactional
public class IngresarTiempoServiceImpl implements IngresarTiempoService {
	
	/**
	 * 
	 */
	public static final Logger logger = Logger.getLogger(IngresarTiempoService.class);
	
	@Autowired
	private TiempoDAO tiempoDAO;
	
	@Override
	public TiempoDTO ingresarTiempo(TiempoDTO tiempoDTO)
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICO PARA INGRESAR TIEMPO ****/");
		
		if (tiempoDTO.getTipoRegistro()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		Tiempo tiempo = TiempoTransformer.transformarTiempo(tiempoDTO); 		
		
		return new TiempoDTO(tiempoDAO.create(tiempo));
	}

}

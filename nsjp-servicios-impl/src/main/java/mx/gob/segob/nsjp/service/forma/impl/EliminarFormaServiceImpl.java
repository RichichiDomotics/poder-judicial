/**
* Nombre del Programa : EliminarFormaServiceImpl.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 22/06/2011
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
package mx.gob.segob.nsjp.service.forma.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.forma.FormaDAO;
import mx.gob.segob.nsjp.service.forma.EliminarFormaService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
@Service
@Transactional
public class EliminarFormaServiceImpl implements EliminarFormaService {
	
	public final static Logger logger = 
		Logger.getLogger(ConsultarFormaPlantillaServiceImpl.class);
	
	@Autowired
	private FormaDAO formaDAO;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.forma.EliminarFormaService#eliminarForma(long)
	 */
	@Override
	public void eliminarForma(Long idForma) throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA ELIMINAR UNA PLANTILLA ****/");
		
		/*Verificación de parámetros*/
		if (idForma<0)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		Long[] ids ={idForma};
		formaDAO.deleteAll(ids);

	}

}

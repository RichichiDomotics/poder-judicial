/**
* Nombre del Programa : CalcularCargaTrabajoServiceImpl.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 29/06/2011
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
package mx.gob.segob.nsjp.service.audiencia.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.service.audiencia.CalcularCargaTrabajoService;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
@Service
public class CalcularCargaTrabajoServiceImpl implements 
		CalcularCargaTrabajoService {
	
	public final static Logger logger = 
		Logger.getLogger(CalcularCargaTrabajoServiceImpl.class);

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.audiencia.CalcularCargaTrabajoService#calcularCargaTrabajo(java.lang.Long, java.lang.Long)
	 */
	@Override
	public Double calcularCargaTrabajo(Long complejidad,
			Long sumatoria) throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CALCULAR LA CARGA DE TRABAJO ****/");
		
		/*Verificación de parámetros*/
		if(complejidad==null||sumatoria==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		/*Operación*/
		 Double carga = complejidad+Math.log(sumatoria);
		
		return carga;
	}

}

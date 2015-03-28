/**
* Nombre del Programa  : RegistrarBitacoraServiceImpl.java
* Autor                : CuauhtemocPS
* Compania             : Ultrasist
* Proyecto             : NSJP                    Fecha: 31/08/2011
* Marca de cambio      : N/A
* Descripcion General  : registra el movimiento del expediente en bitacora
* Programa Dependiente :N/A
* Programa Subsecuente :N/A
* Cond. de ejecucion   :N/A
* Dias de ejecucion    :N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor                :N/A
* Compania             :N/A
* Proyecto             :N/A                      Fecha: N/A
* Modificacion         :N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.service.bitacora.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.bitacora.RegistroBitacoraDAO;
import mx.gob.segob.nsjp.model.RegistroBitacora;
import mx.gob.segob.nsjp.service.bitacora.RegistrarBitacoraService;
import mx.gob.segob.nsjp.service.expediente.impl.RegistrarActaCircunstanciadaServiceImpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author CuauhtemocP
 *
 */
@Service
@Transactional
public class RegistrarBitacoraServiceImpl implements RegistrarBitacoraService {
	
	public final static Logger logger = Logger
	.getLogger(RegistrarActaCircunstanciadaServiceImpl.class);

	@Autowired
    private  RegistroBitacoraDAO registroBitacoraDAO;

	@Override
	public void registrarMovimientoDeExpedienteEnBitacora(
			RegistroBitacora registroBitacora) throws NSJPNegocioException {	
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA REGISTRAR LOS CAMBOIS DEL EXPEDIENTE EN BITACORA ****/");

		/* Verificación de parámetros */
		if (registroBitacora.getNumeroExpediente() == null || registroBitacora.getTipoMovimiento() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		/* Operación */
		Short consecutivo = registroBitacoraDAO.obtenerUltimoFolioBitacora(registroBitacora);
		logger.debug("/**** regresa el consecutivo ****/"+ consecutivo);
			
		if(consecutivo != null){			
			consecutivo ++;
			registroBitacora.setConsecutivo(consecutivo);			
		}else{			
			registroBitacora.setConsecutivo((short)1);			
		}		
		registroBitacoraDAO.create(registroBitacora);		
	}				
}

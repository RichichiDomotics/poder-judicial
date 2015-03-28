/**
 * Nombre del Programa  : BitacoraDefensorServiceImpl.java
 * Autor                : Daniel Jiménez
 * Compania             : TATTVA-IT
 * Proyecto             : NSJP                    Fecha: 21 Jul 2011
 * Marca de cambio      : N/A
 * Descripcion General  : Provee los servicios para la manipulación de los objetos
 * 						  Bitacora de Defensor
 * Programa Dependiente : N/A
 * Programa Subsecuente : N/A
 * Cond. de ejecucion   : N/A
 * Dias de ejecucion    : N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                :N/A
 * Compania             :N/A
 * Proyecto             :N/A                                 Fecha: N/A
 * Modificacion         :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.funcionario.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.funcionario.BitacoraDefensorDAO;
import mx.gob.segob.nsjp.dto.funcionario.BitacoraDefensorDTO;
import mx.gob.segob.nsjp.model.BitacoraDefensor;
import mx.gob.segob.nsjp.service.funcionario.BitacoraDefensorService;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.BitacoraDefensorTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Provee los servicios para la manipulación de los objetos Bitacora de Defensor
 * 
 * @version 1.0
 * @author Daniel Jiménez
 * 
 */
@Service
@Transactional
public class BitacoraDefensorServiceImpl implements BitacoraDefensorService {

	@Autowired
	private BitacoraDefensorDAO bicatoraDefensorDAO;
	
	@Override
	public void guardarBitacoraDefensor(BitacoraDefensorDTO bitacora)
			throws NSJPNegocioException {
		if(bitacora == null){
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}
		if(bitacora.getFuncionario() == null || bitacora.getExpediente() == null){
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}
		
		BitacoraDefensor bitDef = null; 
		bitDef = BitacoraDefensorTransformer.transformar(bitacora);
		
		
		bicatoraDefensorDAO.saveOrUpdate(bitDef);
	}

}

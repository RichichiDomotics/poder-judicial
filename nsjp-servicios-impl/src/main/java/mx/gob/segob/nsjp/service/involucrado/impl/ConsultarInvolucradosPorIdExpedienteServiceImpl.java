/**
* Nombre del Programa : ConsultarInvolucradosPorAudienciaServiceImpl.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 27/06/2011
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
package mx.gob.segob.nsjp.service.involucrado.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoDAO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.service.involucrado.ConsultarInvolucradosPorIdExpedienteService;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author rgama
 *
 */
@Service
public class ConsultarInvolucradosPorIdExpedienteServiceImpl implements
	ConsultarInvolucradosPorIdExpedienteService {
	
	public final static Logger logger = 
		Logger.getLogger(ConsultarInvolucradosPorIdExpedienteServiceImpl.class);
	
	@Autowired
	private InvolucradoDAO invDao;

	
	public List<InvolucradoDTO> consultarInvolucradosPorExpediente(Long idExpediente)throws NSJPNegocioException{

		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR INVOLUCRADOS DE UN EXPEDIENTE DADO ****/");
		
		/*Verificación de parámetros*/
		if (idExpediente==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
				
		/*Operación*/
		List<Involucrado> involucrados = invDao.consultarInvolucradosByExpediente(idExpediente);		
		
		/*Transformación*/
		List<InvolucradoDTO> invsDTO = new ArrayList<InvolucradoDTO>();
		for (Involucrado inv : involucrados) {
			invsDTO.add(InvolucradoTransformer.transformarInvolucradoBasico(inv));			
		}
		return invsDTO;
	}

}

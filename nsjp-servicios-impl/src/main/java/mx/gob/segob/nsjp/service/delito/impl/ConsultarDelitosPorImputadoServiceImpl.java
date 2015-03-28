/**
* Nombre del Programa : consultarDelitosPorImputadoServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 23 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para la consulta de los delitos asociados a un individuo.
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
package mx.gob.segob.nsjp.service.delito.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.persona.DelitoPersonaDAO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.model.DelitoPersona;
import mx.gob.segob.nsjp.service.delito.ConsultarDelitosPorImputadoService;
import mx.gob.segob.nsjp.service.delito.impl.transform.DelitoPersonaTransfromer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del servicio para la consulta de los delitos asociados a un individuo
 * @version 1.0
 * @author rgama
 *
 */
@Service
@Transactional
public class ConsultarDelitosPorImputadoServiceImpl implements ConsultarDelitosPorImputadoService {

	public final static Logger logger = Logger.getLogger(ConsultarDelitosPorImputadoServiceImpl.class);
	
	@Autowired
	private DelitoPersonaDAO delitoPersonaDAO;

	public List<DelitoDTO> consultarDelitosPorImputado(Long idInvolucrado, Long idExpediente) throws NSJPNegocioException{		
		if (idInvolucrado==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA BUSCAR LOS DELITOS ASOCIADOS A UN IMPUTO SOBRE UN EXPEDIENTE ****/");
		
		List<DelitoPersona> listDelitos = delitoPersonaDAO.consultarDelitosPorImputado(idInvolucrado,idExpediente);
		List<DelitoDTO> loDelitosDTO  = new ArrayList<DelitoDTO>();
		
		for(DelitoPersona loDelitoPer: listDelitos){
			loDelitosDTO.add(DelitoPersonaTransfromer.transformarDelitoPersona(loDelitoPer));			
		}		
		logger.debug(loDelitosDTO);
		return loDelitosDTO;
	}

}

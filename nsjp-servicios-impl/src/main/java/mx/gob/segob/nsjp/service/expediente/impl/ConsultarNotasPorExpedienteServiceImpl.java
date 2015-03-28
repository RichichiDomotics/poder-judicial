/**
* Nombre del Programa : ConsultarNotasPorNotaExpedienteServiceImpl.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 08 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para Consultar Notas de un NotaExpediente
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
package mx.gob.segob.nsjp.service.expediente.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.NotaExpedienteDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.NotaExpedienteDTO;
import mx.gob.segob.nsjp.model.NotaExpediente;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.service.expediente.ConsultarNotasPorExpedienteService;
import mx.gob.segob.nsjp.service.expediente.impl.transform.NotaExpedienteTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del servicio para Consultar Notas de un NotaExpediente
 * @version 1.0
 * @author rgama
 *
 */
@Service
@Transactional
public class ConsultarNotasPorExpedienteServiceImpl implements
	ConsultarNotasPorExpedienteService {

	/**
	 * 
	 */
	public final static Logger logger = Logger.getLogger(ConsultarNotasPorExpedienteServiceImpl.class);

	@Autowired
	private NotaExpedienteDAO notaExpedienteDAO;	
	
	@Autowired
	private NumeroExpedienteDAO numeroExpedienteDAO;
	
	public List<NotaExpedienteDTO> consultarNotas(ExpedienteDTO expedienteDTO) throws NSJPNegocioException{			
		logger.debug("SERVICIO QUE CONSULTA LAS NOTAS ASOCIADAS A UN EXPEDIENTE");
		
		if(expedienteDTO == null || expedienteDTO.getNumeroExpedienteId()==null )
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		NumeroExpediente loNumExp= this.numeroExpedienteDAO.read(expedienteDTO.getNumeroExpedienteId());
		Long idExpediente = loNumExp.getExpediente().getExpedienteId();
		expedienteDTO.setExpedienteId(idExpediente);
		logger.debug(">>>> NumeroExpedienteId: " + expedienteDTO.getNumeroExpedienteId());
		logger.debug(">>>> Expediente ID: " + loNumExp.getExpediente().getExpedienteId());
				
		List<NotaExpediente> resp = notaExpedienteDAO.consultarNotasPorExpediente(idExpediente);
		logger.debug(":::::: El total de notas recibidas es: " + resp.size());
		
		List<NotaExpedienteDTO> notasDTO = new ArrayList<NotaExpedienteDTO>(); 
		for (NotaExpediente notaExpediente : resp) {
			notasDTO.add(NotaExpedienteTransformer.transformarNotaExpediente(notaExpediente));
		}
		return notasDTO;
	}
	
	public NotaExpedienteDTO consultarNotaPorId(Long idNota)throws NSJPNegocioException { 

		logger.info(" Servicio consultarNotaPorId ");
		
		if(idNota==null || idNota<0)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		logger.info(" Nota ID:"+ idNota);
		
		NotaExpediente notaExpediente = notaExpedienteDAO.read(idNota);
		
		NotaExpedienteDTO notaExpedienteDTO = NotaExpedienteTransformer.transformarNotaExpediente(notaExpediente); 
			
		return notaExpedienteDTO;
	}

}

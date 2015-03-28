/**
* Nombre del Programa : ActualizarNotasExpedienteServiceImpl.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 07 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Servicio para la actualizacion las notas de un Expediente
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.NotaExpedienteDTO;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.NotaExpediente;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.service.expediente.RegistarNotasExpedienteService;
import mx.gob.segob.nsjp.service.expediente.impl.transform.NotaExpedienteTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para la actualizacion las notas de un Expediente
 * @version 1.0
 * @author rgama
 *
 */

@Service
@Transactional
public class RegistarNotasExpedienteServiceImpl implements
 RegistarNotasExpedienteService {

    @Autowired
    private NumeroExpedienteDAO numeroExpedienteDAO;    
    @Autowired
    private ExpedienteDAO expedienteDAO;

    private static final Logger logger = Logger.getLogger(RegistarNotasExpedienteServiceImpl.class);
	
	@Override
	public List<NotaExpedienteDTO> registrarActualizarNotasExpediente(List<NotaExpedienteDTO> notas,  ExpedienteDTO expedienteDTO)
			throws NSJPNegocioException {
		List<NotaExpedienteDTO> notasDTO = new ArrayList<NotaExpedienteDTO>();
		
		logger.debug("SERVICIO QUE ACTUALIZA LAS NOTAS DE UN EXPEDIENTE");	
		if(expedienteDTO == null || expedienteDTO.getNumeroExpedienteId()==null 
				|| notas ==  null || notas.size() == 0)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		NumeroExpediente loNumExp= this.numeroExpedienteDAO.read(expedienteDTO.getNumeroExpedienteId());
		Long loIdExpediente = loNumExp.getExpediente().getExpedienteId();
		expedienteDTO.setExpedienteId(loIdExpediente);		
		
		logger.debug("ACTUALIZANDO EL LAS NOTAS DE EXPEDIENTE");
		logger.debug(">>>> NumeroExpedienteId: " + expedienteDTO.getNumeroExpedienteId());
		logger.debug(">>>> Expediente ID: " + expedienteDTO.getExpedienteId());
		//Se lee el expediente
		Expediente loExpediente = expedienteDAO.read(loIdExpediente);
		
		Set<NotaExpediente> notasExpediente = new HashSet<NotaExpediente>();
		for (NotaExpedienteDTO notaExpedienteDTO : notas) {
			logger.debug(">>>> Nota ID: " + notaExpedienteDTO.getIdNota());
			logger.debug(">>>> Nombre Nota: " + notaExpedienteDTO.getNombreNota());
			logger.debug(">>>> Descripcion Nota: " + notaExpedienteDTO.getDescripcion());			
			notaExpedienteDTO.setExpediente(new ExpedienteDTO(loIdExpediente));
			notasExpediente.add(NotaExpedienteTransformer.transformarNotaExpediente(notaExpedienteDTO));
		}
		//Se asignan las notas
		loExpediente.setNotas(notasExpediente);			
		this.expedienteDAO.update(loExpediente);
		
		logger.debug("Las notas se registraron/actualizaron de forma correcta");
		
		//Recuperar nuevamente las notas, para saber su ID
		notasDTO.clear();
		for (NotaExpediente notaExpediente : loExpediente.getNotas()) {
			logger.info("**ID"+notaExpediente.getIdNota());
			notasDTO.add(NotaExpedienteTransformer.transformarNotaExpediente(notaExpediente));
		}
		return (notasDTO);
	}

}

/**
* Nombre del Programa : ActualizarTipoExpedienteServiceImpl.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 06 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Servicio para la actualizacion del tipo de Expediente
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

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.expediente.EtapasExpediente;
import mx.gob.segob.nsjp.comun.enums.expediente.OrigenExpediente;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.expediente.ActualizarEtapaExpedienteService;
import mx.gob.segob.nsjp.service.expediente.ActualizarTipoExpedienteService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para la actualizacion del tipo de Expediente
 * @version 1.0
 * @author rgama
 *
 */

@Service
@Transactional
public class ActualizarTipoExpedienteServiceImpl implements
 ActualizarTipoExpedienteService {

    @Autowired
    private NumeroExpedienteDAO numeroExpedienteDAO;    
    @Autowired
    private ExpedienteDAO expedienteDAO;

    private static final Logger logger = Logger.getLogger(ActualizarTipoExpedienteServiceImpl.class);
	
	@Override
	public void actualizarTipoExpediente(ExpedienteDTO expedienteDTO, OrigenExpediente tipo)
			throws NSJPNegocioException {
		logger.debug("SERVICIO QUE ACTUALIZA EL TIPO DE EXPEDIENTE");	
		if(expedienteDTO == null || expedienteDTO.getNumeroExpedienteId()==null 
				|| tipo ==  null || tipo.getValorId() ==  null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		NumeroExpediente loNumExp= this.numeroExpedienteDAO.read(expedienteDTO.getNumeroExpedienteId());
		logger.debug("ACTUALIZANDO EL TIPO DE EXPEDIENTE loNumExp:: "+loNumExp);
		expedienteDTO.setExpedienteId(loNumExp.getExpediente().getExpedienteId());		
		
		logger.debug("ACTUALIZANDO EL TIPO DE EXPEDIENTE");
		logger.debug(">>>> NumeroExpedienteId: " + expedienteDTO.getNumeroExpedienteId());
		logger.debug(">>>> Expediente ID: " + loNumExp.getExpediente().getExpedienteId());
		logger.debug(">>>> Id del Origen: " + tipo.getValorId());
		
		Expediente loExpediente = expedienteDAO.read(loNumExp.getExpediente().getExpedienteId());
		loExpediente.setOrigen(new Valor(tipo.getValorId()));
		this.expedienteDAO.update(loExpediente);
		logger.debug("El tipo de expediente se actualizo con exito");		

	}

}

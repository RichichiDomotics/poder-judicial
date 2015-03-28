/**
* Nombre del Programa : DesignarAbogadoDefensorServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 12 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para designar un abogado defensor
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
package mx.gob.segob.nsjp.service.funcionario.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudDefensorDAO;
import mx.gob.segob.nsjp.dto.documento.AvisoDesignacionDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDefensorDTO;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.SolicitudDefensor;
import mx.gob.segob.nsjp.service.expediente.AsignarNumeroExpedienteService;
import mx.gob.segob.nsjp.service.funcionario.DesignarAbogadoDefensorService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del servicio para designar un abogado defensor.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class DesignarAbogadoDefensorServiceImpl implements
		DesignarAbogadoDefensorService {
	
	private final static Logger logger = Logger.getLogger(DesignarAbogadoDefensorServiceImpl.class);
	
	@Autowired
	private SolicitudDefensorDAO solicitudDefensorDAO;
	@Autowired
	private FuncionarioDAO funcionarioDAO;
	@Autowired
	private AsignarNumeroExpedienteService asignarNumeroExpedienteService;
	
	
	
	@Override
	public SolicitudDefensorDTO designarAbogadoDefensor(SolicitudDefensorDTO solicitudDefensorDTO)
			throws NSJPNegocioException {							
		
		if(logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA DESIGNAR ABOGADO DEFENSOR!! ****/" + solicitudDefensorDTO.getFuncionario());				
		
		if (!(solicitudDefensorDTO.getDocumentoId()!=null && 
				solicitudDefensorDTO.getFuncionario()!=null)) 
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		SolicitudDefensor solDefensor =  solicitudDefensorDAO.read(solicitudDefensorDTO.getDocumentoId());
		
		
		solDefensor.setDestinatario(new Funcionario(solicitudDefensorDTO.getFuncionario().getClaveFuncionario()));
				
		//ExpedienteDTO resExpedienteDTO = asignarNumeroExpedienteService.asignarNumeroExpediente(expedienteDTO);	
//		solDefensor.setExpediente(new Expediente(resExpedienteDTO.getExpedienteId()));
	
		solicitudDefensorDAO.update(solDefensor);
		
		SolicitudDefensorDTO resSolDefensorDTO = solicitudDefensorDTO;
												//new SolicitudDefensorDTO();
		//resSolDefensorDTO.setExpedienteDTO(new ExpedienteDTO(resExpedienteDTO.getExpedienteId()));
		//resSolDefensorDTO.setFormaId(solDefensor.getFormaId());
		
		this.actualizaCargaTrabajoAbogadoDefensor(resSolDefensorDTO);
		
		return resSolDefensorDTO;
	}
	
	@Override
	public void actualizaCargaTrabajoAbogadoDefensor(
			SolicitudDefensorDTO solicitudDefensorDTO)
			throws NSJPNegocioException {
		
		
		Funcionario f = this.funcionarioDAO.read(solicitudDefensorDTO.getFuncionario().getClaveFuncionario());
		
		// TODO Generar Calculo para designacion de cargas de Abogado Defensor
		
		funcionarioDAO.update(f);
		
	}

}

/**
* Nombre del Programa : SolicitarDefensorServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 19 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para realizar la solicitud de un defensor
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
package mx.gob.segob.nsjp.service.solicitud.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudDefensorDAO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDefensorDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.SolicitudDefensor;
import mx.gob.segob.nsjp.model.SolicitudDefensorDelito;
import mx.gob.segob.nsjp.service.audiencia.impl.transform.AudienciaTransformer;
import mx.gob.segob.nsjp.service.delito.impl.transform.DelitoTransfromer;
import mx.gob.segob.nsjp.service.solicitud.GenerarFolioSolicitudService;
import mx.gob.segob.nsjp.service.solicitud.SolicitarDefensorService;
import mx.gob.segob.nsjp.service.solicitud.impl.transform.SolicitudDefensorTransformer;
import mx.gob.segob.nsjp.service.solicitud.impl.transform.SolicitudTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del servicio para realizar la solicitud de un defensor.
 * @version 1.0
 * @author cesarAagustin
 *
 */
@Service
@Transactional
public class SolicitarDefensorServiceImpl implements SolicitarDefensorService {
	
	/**
	 * 
	 */
	private final static Logger logger = Logger.getLogger(SolicitarDefensorServiceImpl.class);
	
	@Autowired
	private SolicitudDefensorDAO solicitudDefensorDAO;
    @Autowired
    private GenerarFolioSolicitudService generarFolioSolicitudService;
    
	@Override
	public SolicitudDefensorDTO solicitarDefensor(
			SolicitudDefensorDTO solicitudDefensorDTO)
			throws NSJPNegocioException {

		if (logger.isDebugEnabled()){
			logger.debug("/**** SERVICIO PARA SOLICITAR DEFENSOR ****/");
		}
		
		SolicitudDefensor solicitudDefensor = SolicitudTransformer.solDefensorTransformer(solicitudDefensorDTO);
		solicitudDefensor.setFolioSolicitud(generarFolioSolicitudService.generarFolioSolicitud());
		Long idSolDefensor = solicitudDefensorDAO.create(solicitudDefensor);
		
		return new SolicitudDefensorDTO(idSolDefensor);
	}

	@Override
	public List<SolicitudDefensorDTO> consultarSolDefensorAsignadas(
			FuncionarioDTO funcionarioDTO) throws NSJPNegocioException {
		
		if (logger.isDebugEnabled()){
			logger.debug("/**** SERVICIO PARA CONSULTAR LAS SOLICITUDES DEFENSORIA ASIGNADAS A UN DEFENSOR ****/");
		}
		
		if (funcionarioDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		List<SolicitudDefensor> solsDefensoria = solicitudDefensorDAO.consultarSolDefensorAsignadas(funcionarioDTO.getClaveFuncionario());
		
		List<SolicitudDefensorDTO> solsRespuesta = new ArrayList<SolicitudDefensorDTO>();
		for (SolicitudDefensor solicitudDefensor : solsDefensoria) {
			SolicitudDefensorDTO solDefDTO = SolicitudDefensorTransformer.transformarSolicitudDefensoria(solicitudDefensor); 			
		
			List<DelitoDTO> delitosDTO = new ArrayList<DelitoDTO>();
			for (SolicitudDefensorDelito solDefDelito : solicitudDefensor.getSolicitudDefensorDelitos()) {
				if (solDefDelito.getDelito()!=null) {
					delitosDTO.add(DelitoTransfromer.transformarDelito(solDefDelito.getDelito()));
				}
			}
			solDefDTO.setDelitos(delitosDTO);
			
			if (solicitudDefensor.getAudiencia()!=null) {
				solDefDTO.setAudiencia(AudienciaTransformer.transformarDTO(solicitudDefensor.getAudiencia()));
			}
			
			solsRespuesta.add(solDefDTO);
		}
		
		return solsRespuesta;
	}

	@Override
	public SolicitudDefensorDTO reasignarDefensorAExpediente(ExpedienteDTO expedienteDTO,
			FuncionarioDTO funcionarioDTO) throws NSJPNegocioException {
		if (logger.isDebugEnabled()){
			logger.debug("/**** SERVICIO REASIGNAR DEFENSOR A UN EXPEDIENTE ****/");
		}
		
		if (expedienteDTO.getExpedienteId()==null
				|| funcionarioDTO.getClaveFuncionario()==null )
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		SolicitudDefensor solDefensor = solicitudDefensorDAO.obtenerSolDfensorByExpedienteYFuncionario(expedienteDTO.getExpedienteId(), funcionarioDTO.getClaveFuncionario()); 
		logger.debug("/**** SOL. DEF ****/ "+solDefensor.getDocumentoId());
		
		//Eliminar defensor de la solicitud
		SolicitudDefensorDTO solDefDTO = new SolicitudDefensorDTO();
		if (solDefensor!=null) {
			logger.debug("/**** ELIMINANDO FUNCIONARIO DE SOLICITUD ****/");
			solDefensor.setDestinatario(null);
			solicitudDefensorDAO.update(solDefensor);
			solDefDTO.setDocumentoId(solDefensor.getDocumentoId());
		} else
			solDefDTO.setDocumentoId(new Long(0));
		
		return solDefDTO;
	}

}

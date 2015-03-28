/**
 * Nombre del Programa : ConsultarAudienciasPorEstatusServiceImpl.java
 * Autor                            : adrian
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 23/06/2011
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

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.audiencia.AudienciaDAO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.service.audiencia.ConsultarAudienciasPorEstatusService;
import mx.gob.segob.nsjp.service.audiencia.impl.transform.AudienciaTransformer;
import mx.gob.segob.nsjp.service.infra.PJClienteService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author adrian
 * 
 */
@Service
@Transactional
public class ConsultarAudienciasPorEstatusServiceImpl implements
		ConsultarAudienciasPorEstatusService {

	private final static Logger logger = Logger
			.getLogger(ConsultarAudienciasPorEstatusServiceImpl.class);

	@Autowired
	private AudienciaDAO audDao;
	@Autowired
	private PJClienteService pjClienteService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.segob.nsjp.service.audiencia.ConsultarAudienciasPorEstatusService
	 * #consultarAudienciasPorEstatus(java.lang.String)
	 */
	@Override
	public List<AudienciaDTO> consultarAudienciasPorEstatus(Long estatus)
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR AUDIENCIAS POR ESTADO ****/");
		
		/*Verificación de parámetros*/
		if (estatus==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		/*Operación*/
		List<Audiencia> audiencias =audDao.consultarAudienciasPorEstatus(estatus);
		
		/*Transformación*/
		List<AudienciaDTO> audienciasDTO=new ArrayList<AudienciaDTO>();
		for (Audiencia aud : audiencias) {
			audienciasDTO.add(AudienciaTransformer.transformarDTO(aud));			
		}
		return audienciasDTO;
	}


	
	@Override
	public List<AudienciaDTO> consultarAudienciasFromPoderJudicial(
			AudienciaDTO audiencia) throws NSJPNegocioException {
		if(audiencia.getEstatusAudiencia() == null || audiencia.getFechaFiltroInicio() == null){
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}
		return pjClienteService.consultarAudienciasByFechasYEstatus(audiencia);
	}
	
	@Override
	public AudienciaDTO consultarAudienciaFromPoderJudicial(
			AudienciaDTO audiencia) throws NSJPNegocioException {
		if(audiencia.getId() == null){
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}
		return pjClienteService.consultarAudienciasById(audiencia);
	}

}

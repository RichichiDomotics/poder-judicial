/**
 * 
 */
package mx.gob.segob.nsjp.service.audiencia.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.audiencia.SalaAudienciaDAO;
import mx.gob.segob.nsjp.dao.audiencia.SalaJAVSDAO;
import mx.gob.segob.nsjp.dao.catalogo.CatDiscriminateDAO;
import mx.gob.segob.nsjp.dao.catalogo.CatDistritoDAO;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.dto.audiencia.SalaAudienciaDTO;
import mx.gob.segob.nsjp.dto.audiencia.SalaJAVSDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDistritoDTO;
import mx.gob.segob.nsjp.model.CatDiscriminante;
import mx.gob.segob.nsjp.model.CatDistrito;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.SalaAudiencia;
import mx.gob.segob.nsjp.model.SalaJAVS;
import mx.gob.segob.nsjp.service.audiencia.AdministrarSalaService;
import mx.gob.segob.nsjp.service.audiencia.impl.transform.SalaAudienciaTransformer;
import mx.gob.segob.nsjp.service.catalogo.impl.transform.CatDistritoTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación de los serivicios que permiten administrar las Salas:
 * Audiencia
 * JAVS
 * @author GustavoBP
 *
 */
@Service
@Transactional
public class AdministrarSalaServiceImpl implements AdministrarSalaService {

	/**
	 * Logger.
	 */
	private final static Logger logger = Logger
			.getLogger(AdministrarSalaServiceImpl.class);

	@Autowired
	private SalaAudienciaDAO salaAudienciaDAO;
	@Autowired
	private CatDistritoDAO catDistritoConsultaDAO;	
	@Autowired
	private SalaJAVSDAO salaJAVSDAO;
	@Autowired
	private CatDiscriminateDAO catDiscriminateDAO;
	@Autowired
	private FuncionarioDAO funcionarioDAO;
	
	
	
	public SalaAudienciaDTO consultarSalaAudiencia(
			SalaAudienciaDTO salaAudienciaDTO) throws NSJPNegocioException {
		
		logger.info("--- SERVICIO consultarSalaAudiencia ---");
		if(salaAudienciaDTO==null || salaAudienciaDTO.getSalaAudienciaId()==null || salaAudienciaDTO.getSalaAudienciaId() < 0)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		SalaAudiencia salaAudiencia = salaAudienciaDAO.read(salaAudienciaDTO.getSalaAudienciaId());
		
		SalaAudienciaDTO salaADTO = SalaAudienciaTransformer.transformarSalaAudienciaDTO(salaAudiencia);
		
		if(salaADTO.getCatDiscriminanteDTO()!=null && salaADTO.getCatDiscriminanteDTO().getCatDiscriminanteId()!=null){
			CatDistrito catDistrito;
			catDistrito = catDistritoConsultaDAO.consultarDistritoPorDiscriminante(salaADTO.getCatDiscriminanteDTO().getCatDiscriminanteId().longValue());
			CatDistritoDTO catDistritoDTO = CatDistritoTransformer.transformarDistritoSimple(catDistrito);
			salaADTO.getCatDiscriminanteDTO().setDistrito(catDistritoDTO);
		}
		
		return salaADTO;
	}
	
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.audiencia.AdministrarSalaService#registrarModificarSalaAudiencia(mx.gob.segob.nsjp.dto.audiencia.SalaAudienciaDTO)
	 */
	@Override
	public SalaAudienciaDTO guardarActualizarSalaAudiencia(
			SalaAudienciaDTO salaAudienciaDTO) throws NSJPNegocioException {

		logger.info("--- SERVICIO guardarActualizarSalaAudiencia ---");
		if(salaAudienciaDTO==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		if(salaAudienciaDTO.getSalaAudienciaId()==null)
			salaAudienciaDTO = guardarSalaAudiencia(salaAudienciaDTO);
		else
			salaAudienciaDTO = actualizarSalaAudiencia(salaAudienciaDTO);
		
		return salaAudienciaDTO;
	}
	
	private SalaAudienciaDTO guardarSalaAudiencia(SalaAudienciaDTO salaAudienciaDTO)  throws NSJPNegocioException {
		
		SalaAudiencia salaAudiencia = SalaAudienciaTransformer.transformarSalaAudienciaBasico(salaAudienciaDTO);
		
		if(salaAudienciaDTO.getCatDiscriminanteDTO()!=null && salaAudienciaDTO.getCatDiscriminanteDTO().getCatDiscriminanteId()!=null){
			CatDiscriminante tribunal = catDiscriminateDAO.read(salaAudienciaDTO.getCatDiscriminanteDTO().getCatDiscriminanteId());
			salaAudiencia.setCatDiscriminante(tribunal);
		}
		
		if(salaAudienciaDTO.getFuncionarioDTO()!=null && salaAudienciaDTO.getFuncionarioDTO().getClaveFuncionario()!=null){
			Funcionario encargado = funcionarioDAO.read(salaAudienciaDTO.getFuncionarioDTO().getClaveFuncionario());
			salaAudiencia.setEncargado(encargado);
		}

		Long idSala = salaAudienciaDAO.create(salaAudiencia);
		logger.info("SalaAudiencia Creada: "+ idSala);
		
		if(salaAudienciaDTO.getSalaJAVSDTO()!=null ){
			SalaJAVS salaJAVS =  guardarSalaJAVS(salaAudienciaDTO.getSalaJAVSDTO());
			salaJAVS.setSalaAudiencia(salaAudiencia);
			Long idSalaJAVS = salaJAVSDAO.create(salaJAVS);
			logger.info("SalaJAVS Creada: "+ idSalaJAVS);
			
			salaAudienciaDTO.getSalaJAVSDTO().setSalaJAVSId(idSalaJAVS);
		}
		
		salaAudienciaDTO.setSalaAudienciaId(idSala);
		
		return salaAudienciaDTO;
	}
	
	
	private SalaJAVS guardarSalaJAVS(SalaJAVSDTO salaJAVSDTO)   throws NSJPNegocioException {
		SalaJAVS salaJAVS = null;
		
		if(salaJAVSDTO!=null && salaJAVSDTO.getDireccionIP()!=null &&
				salaJAVSDTO.getPassword()!=null &&
				salaJAVSDTO.getUsuarioJAVS()!=null){
			salaJAVS = SalaJAVSTransformer.transformarSalaJAVSBasico(salaJAVSDTO);
		}
		return salaJAVS;
	}
	
	
	private SalaAudienciaDTO actualizarSalaAudiencia(SalaAudienciaDTO salaAudienciaDTO)  throws NSJPNegocioException {
		
		if(salaAudienciaDTO.getSalaAudienciaId()==null || salaAudienciaDTO.getSalaAudienciaId()<0)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		SalaAudiencia salaAudiencia  = salaAudienciaDAO.read(salaAudienciaDTO.getSalaAudienciaId());
		
		salaAudiencia = SalaAudienciaTransformer.transformarSalaAudienciaUpdate(salaAudienciaDTO, salaAudiencia);
		
		if(salaAudienciaDTO.getCatDiscriminanteDTO()!=null && salaAudienciaDTO.getCatDiscriminanteDTO().getCatDiscriminanteId()!=null){
			CatDiscriminante tribunal = catDiscriminateDAO.read(salaAudienciaDTO.getCatDiscriminanteDTO().getCatDiscriminanteId());
			salaAudiencia.setCatDiscriminante(tribunal);
		}
		else{
			salaAudiencia.setCatDiscriminante(null);
		}
			
		
		if(salaAudienciaDTO.getFuncionarioDTO()!=null && salaAudienciaDTO.getFuncionarioDTO().getClaveFuncionario()!=null){
			Funcionario encargado = funcionarioDAO.read(salaAudienciaDTO.getFuncionarioDTO().getClaveFuncionario());
			salaAudiencia.setEncargado(encargado);
		}
		else{
			salaAudiencia.setEncargado(null);
		}

		salaAudienciaDAO.update(salaAudiencia);
		logger.info("SalaAudiencia Actualizada: "+ salaAudiencia.getSalaAudienciaId());
		
		
		if(salaAudienciaDTO.getSalaJAVSDTO()!=null){
			if(salaAudienciaDTO.getSalaJAVSDTO().getSalaJAVSId()!=null && salaAudienciaDTO.getSalaJAVSDTO().getSalaJAVSId() > 0){
				SalaJAVS salaJAVS =  actualizarSalaJAVS(salaAudienciaDTO.getSalaJAVSDTO());
				logger.info("SalaJAVS Modificada: "+ salaJAVS.getSalaJAVSId());
			}
			else{
				SalaJAVS salaJAVS =  guardarSalaJAVS(salaAudienciaDTO.getSalaJAVSDTO());
				salaJAVS.setSalaAudiencia(salaAudiencia);
				Long idSalaJAVS = salaJAVSDAO.create(salaJAVS);
				logger.info("SalaJAVS Creada: "+ idSalaJAVS);	
				salaAudienciaDTO.getSalaJAVSDTO().setSalaJAVSId(idSalaJAVS);
			}
		}
		else{
			if(salaAudiencia.getSalaJAVS()!= null && salaAudiencia.getSalaJAVS().getSalaJAVSId()!=null){
				SalaJAVS salaJAVS = salaJAVSDAO.read(salaAudiencia.getSalaJAVS().getSalaJAVSId());
				if(salaJAVS!=null){
					salaAudiencia.setSalaJAVS(null);
					salaJAVS.setSalaAudiencia(null);
					salaJAVSDAO.delete(salaJAVS);
				}
				logger.info("SalaJAVS Eliminada: "+ salaJAVS);
			}
		}
			
		
		//Eliminación logica de SalaAudiencia implica Eliminación física de la SalaJavs
		if(salaAudienciaDTO.getEsActivo()==false){
			if(salaAudienciaDTO.getSalaJAVSDTO()!=null && salaAudienciaDTO.getSalaJAVSDTO().getSalaJAVSId()!=null && salaAudienciaDTO.getSalaJAVSDTO().getSalaJAVSId() > 0){
				SalaJAVS salaJAVS = salaJAVSDAO.read(salaAudienciaDTO.getSalaJAVSDTO().getSalaJAVSId());
				if(salaJAVS!=null){
					salaAudiencia.setSalaJAVS(null);
					salaJAVS.setSalaAudiencia(null);
					salaJAVSDAO.delete(salaJAVS);
				}
				logger.info("SalaJAVS Eliminada: "+ salaJAVS);
			}
		}
		
		return salaAudienciaDTO;
	}
	
	private SalaJAVS actualizarSalaJAVS(SalaJAVSDTO salaJAVSDTO)   throws NSJPNegocioException {
		SalaJAVS salaJAVS = null;
		
		if(salaJAVSDTO!=null && salaJAVSDTO.getDireccionIP()!=null &&
				salaJAVSDTO.getPassword()!=null &&
				salaJAVSDTO.getUsuarioJAVS()!=null &&
				salaJAVSDTO.getSalaJAVSId()!=null && 
				salaJAVSDTO.getSalaJAVSId() > 0){
			
			salaJAVS = salaJAVSDAO.read(salaJAVSDTO.getSalaJAVSId());  
			salaJAVS = SalaJAVSTransformer.transformarSalaJAVSUpdate(salaJAVSDTO, salaJAVS);
		}
		return salaJAVS;
	}
	
}

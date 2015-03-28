
package mx.gob.segob.nsjp.service.sentencia.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoDAO;
import mx.gob.segob.nsjp.dao.sentencia.SentenciaDAO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.dto.sentencia.SentenciaDTO;
import mx.gob.segob.nsjp.dto.sentencia.SentenciaWSDTO;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.NombreDemografico;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Sentencia;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;
import mx.gob.segob.nsjp.service.infra.ClienteGeneralService;
import mx.gob.segob.nsjp.service.infra.impl.transform.sentencia.ExpedienteWSDTOTransformer;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;
import mx.gob.segob.nsjp.service.persona.impl.transform.NombreDemograficoTransformer;
import mx.gob.segob.nsjp.service.sentencia.SentenciaService;
import mx.gob.segob.nsjp.service.sentencia.impl.transform.SentenciaTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sun.util.logging.resources.logging;

/**
 * @author AntonioBV
 *
 */
@Service("sentenciaService")
@Transactional
public class SentenciaServiceImpl implements SentenciaService{

	@Autowired
	SentenciaDAO sentenciaDAO;
	
	@Autowired
	InvolucradoDAO involucradoDAO;
	
	@Autowired
	ExpedienteDAO expedienteDAO;
	
	@Autowired
	ClienteGeneralService clienteGeneralService;
	
	@Override
	public List<SentenciaDTO> consultarNUS(NombreDemograficoDTO nombreDemograficoDTO)
			throws NSJPNegocioException {
		
		List<SentenciaDTO> lstResultadoSentenciaDTO = new ArrayList<SentenciaDTO>();
		SentenciaDTO sentenciaDTO  = null;
		
		NombreDemografico nombreDemografico = NombreDemograficoTransformer.transformarNombreDemografico(nombreDemograficoDTO);
		List<Sentencia> lstSentencias = null;
		
		if(nombreDemografico != null && nombreDemografico.getCurp() != null && !nombreDemografico.getCurp().isEmpty() ){
			lstSentencias = sentenciaDAO.consultarNUS(nombreDemografico, Boolean.TRUE);
		}
		
		if (lstSentencias != null && !lstSentencias.isEmpty()) {
			sentenciaDTO = SentenciaTransformer.transformar(lstSentencias.get(0));
			sentenciaDTO.setEsUnicoNUS(Boolean.TRUE);
			lstResultadoSentenciaDTO.add(sentenciaDTO);
		} else {
			
			lstSentencias = sentenciaDAO.consultarNUS(nombreDemografico, Boolean.FALSE);
			if (lstSentencias != null && !lstSentencias.isEmpty()) {
				
				for (Sentencia sentencia : lstSentencias){
					sentenciaDTO = SentenciaTransformer.transformar(sentencia);
					sentenciaDTO.setEsUnicoNUS(Boolean.FALSE);
					lstResultadoSentenciaDTO.add(sentenciaDTO);
				}
			}
		}
			
			
		
		
			
				
		return lstResultadoSentenciaDTO;
	}


	@Override
	public SentenciaDTO consultarSentenciaCompleta(SentenciaDTO sentenciaDTO) throws NSJPNegocioException {
		
		Sentencia sentencia = sentenciaDAO.read(sentenciaDTO.getSentenciaId());
		
		Involucrado involucrado = involucradoDAO.read(sentencia.getInvolucrado().getElementoId());
		
		Expediente expediente = expedienteDAO.read(sentencia.getNumeroExpediente().getNumeroExpedienteId());
		
		SentenciaDTO sentenciaCompletaDTO = SentenciaTransformer.transformar(sentencia);
		
		InvolucradoDTO involucradoDTO = InvolucradoTransformer.transformarInvolucrado(involucrado);
		
		ExpedienteDTO expedienteDTO = ExpedienteTransformer.transformaExpediente(expediente);
		
		sentenciaCompletaDTO.setInvolucradoDTO(involucradoDTO);
		
		sentenciaCompletaDTO.setNumeroExpedienteDTO(expedienteDTO);
		
		
		
		return sentenciaCompletaDTO;
	}

	@Override
	public void crearSentencia(SentenciaWSDTO sentenciaWSDTO) throws NSJPNegocioException {
		Sentencia sentencia = SentenciaTransformer.transformarLocalWSDTO2Entity(sentenciaWSDTO);
		
		ExpedienteDTO expedienteDTO = ExpedienteWSDTOTransformer.expedienteWsdto2ExpedienteDto(sentenciaWSDTO.getNumeroExpedienteDTO());
		
		Expediente expediente = ExpedienteTransformer.transformarExpediente(expedienteDTO);
		
		sentencia = SentenciaTransformer.quitarIDs(sentencia);
		
		Long involucradoId = involucradoDAO.create(sentencia.getInvolucrado());

		Long expedienteId  = expedienteDAO.create(expediente);
		
		sentencia.setInvolucrado(new Involucrado(involucradoId));
		
		sentencia.setNumeroExpediente(new NumeroExpediente(expedienteId));
		
		sentenciaDAO.merge(sentencia);		
	}


	@Override
	public boolean enviarSentencia(SentenciaDTO sentenciaDTO, Instituciones institucion) throws NSJPNegocioException {
		
		sentenciaDTO = this.consultarSentenciaCompleta(sentenciaDTO);
		
		clienteGeneralService.enviarSentencia(sentenciaDTO, institucion);
		
		return false;
	}

}

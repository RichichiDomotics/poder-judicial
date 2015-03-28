package mx.gob.segob.nsjp.service.expediente.impl;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.service.expediente.BuscarExpedientePorCasoImputadoService;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class BuscarExpedientePorCasoImputadoServiceImpl implements
		BuscarExpedientePorCasoImputadoService {

	@Autowired
	private ExpedienteDAO expedienteDAO;

	@Autowired
	private NumeroExpedienteDAO numeroExpedienteDAO;
	
	@Override
	public ExpedienteDTO buscarExpedientePorCasoImputado(
			String numeroGeneralcaso, InvolucradoDTO imputado)
			throws NSJPNegocioException {
		Expediente expediente = null;
		
		if(imputado.getFolioElemento() != null){
			expediente = expedienteDAO.buscarExpedientePorCasoFolioInvolucrado(numeroGeneralcaso, imputado.getFolioElemento());
					
		}else{		
			NombreDemograficoDTO ndDTO = imputado.getNombresDemograficoDTO().get(0);
			expediente = expedienteDAO.buscarExpedientePorCasoCalidadNombreImputado(
					numeroGeneralcaso, ndDTO.getNombre(),
					ndDTO.getApellidoPaterno(), ndDTO.getApellidoMaterno(),
					imputado.getCalidadDTO().getCalidades().getValorId());
		}
		return ExpedienteTransformer.transformarExpedienteBasico(expediente);
	}	
	
	@Override	
	public ExpedienteDTO buscarExpedientePorCasoImputado(String numeroCaso,
			String imputado) {
		
		Expediente expediente = expedienteDAO.buscarExpedientePorCasoImputado(
				numeroCaso, imputado, Calidades.PROBABLE_RESPONSABLE_PERSONA.getValorId());

		return ExpedienteTransformer.transformarExpedienteBasico(expediente);
	}

	@Override	
	public ExpedienteDTO buscarNumeroExpedientePorCasoImputado(String numeroCaso,
			String folioImputado) {
		
		NumeroExpediente expediente = numeroExpedienteDAO.buscarNumeroExpedientePorCasoFolioImputado(
				numeroCaso, folioImputado, Calidades.PROBABLE_RESPONSABLE_PERSONA.getValorId());

		return ExpedienteTransformer.transformarExpedienteBasico(expediente);
	}
	
}

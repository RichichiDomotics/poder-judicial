package mx.gob.segob.nsjp.service.objeto.impl;

import static mx.gob.segob.nsjp.service.solicitud.impl.transform.ConfInstitucionTransformer.transformarInstitucion;

import java.util.LinkedList;
import java.util.List;

import mx.gob.segob.nsjp.dao.audiencia.AudienciaEvidenciaDAO;
import mx.gob.segob.nsjp.dto.audiencia.EventoDTO;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.model.AudienciaEvidencia;
import mx.gob.segob.nsjp.model.Evidencia;
import mx.gob.segob.nsjp.model.Objeto;
import mx.gob.segob.nsjp.service.objeto.ConsultarObjetosAudienciaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ConsultarObjetosAudienciaServiceImpl implements
		ConsultarObjetosAudienciaService {

	@Autowired
	private AudienciaEvidenciaDAO audienciaEvidenciaDAO;
	
	@Override
	public List<EvidenciaDTO> consultarObjetosEnAudiencia(EventoDTO input) {
		List<AudienciaEvidencia> lista = null;
		LinkedList<EvidenciaDTO> evidencias = new LinkedList<EvidenciaDTO>(); 
		lista = audienciaEvidenciaDAO.consultarObjetosByAudiencia(input.getId());
		ObjetoDTO obj;
		CadenaDeCustodiaDTO cdc;
		EvidenciaDTO evidenciaDTO;
		for(AudienciaEvidencia au : lista){
			evidenciaDTO = new EvidenciaDTO();
			Evidencia evidencia = au.getEvidencia();
			evidenciaDTO.setEvidenciaId(evidencia.getEvidenciaId());
				obj = new ObjetoDTO();
				Objeto objeto = evidencia.getObjeto();
				obj.setElementoId(objeto.getElementoId());
				obj.setInstitucionPresenta(transformarInstitucion(objeto.getInstitucionPresenta()));
				obj.setDescripcion(objeto.getDescripcion());			
				
				cdc = new CadenaDeCustodiaDTO(evidencia.getCadenaDeCustodia().getCadenaDeCustodiaId());
				cdc.setFolio(evidencia.getCadenaDeCustodia().getFolio());
				obj.setCadenaDeCustodia(cdc);
			evidenciaDTO.setObjeto(obj);
			evidenciaDTO.setDescripcion(evidencia.getDescripcion());
			evidenciaDTO.setNumeroEvidencia(evidencia.getNumeroEvidencia());
			evidencias.add(evidenciaDTO);		
		}
		
		return evidencias;
	}

}

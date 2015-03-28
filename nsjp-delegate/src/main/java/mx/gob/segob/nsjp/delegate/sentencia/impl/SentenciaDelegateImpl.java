/**
 * 
 */
package mx.gob.segob.nsjp.delegate.sentencia.impl;

import java.util.HashMap;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.delegate.sentencia.SentenciaDelegate;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.dto.sentencia.SentenciaDTO;
import mx.gob.segob.nsjp.service.sentencia.SentenciaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author AntonioBV
 *
 */
@Service
@Transactional
public class SentenciaDelegateImpl implements SentenciaDelegate {

	@Autowired
	SentenciaService sentenciaService;
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.sentencia.SentenciaDelegate#consultarNUS(mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO)
	 */
	@Override
	public List<SentenciaDTO> consultarNUS(InvolucradoDTO involucradoDTO)
			throws NSJPNegocioException {
		
		List<NombreDemograficoDTO> lstDtos = involucradoDTO.getNombresDemograficoDTO();
		List<SentenciaDTO> lstResultadoSentenciaDTO = null;
		
		if (!lstDtos.isEmpty()){
			NombreDemograficoDTO nombreDemograficoDTO = lstDtos.get(0);
			lstResultadoSentenciaDTO = sentenciaService.consultarNUS(nombreDemograficoDTO);			
		}

		return lstResultadoSentenciaDTO;
	}

	@Override
	public HashMap<String, String> consultarNUSTOJSON (
			InvolucradoDTO involucradoDTO) throws NSJPNegocioException {
		
		HashMap<String, String> hashMapSentencias = new HashMap<String, String>();
		List<SentenciaDTO> lstSentenciaDTO = consultarNUS(involucradoDTO);
		
		if( lstSentenciaDTO != null && !lstSentenciaDTO.isEmpty() ) {
			 
			SentenciaDTO unico = lstSentenciaDTO.get(0);
			boolean esUnico = false; 
			 if (unico.getEsUnicoNUS()){
				 hashMapSentencias.put("unico", "TRUE");
				 esUnico = true;
			 } else {
				 hashMapSentencias.put("unico", "FALSE");
			 } 
			 
			 for (SentenciaDTO sentenciaDTO : lstSentenciaDTO) {
				 if(esUnico){
					 hashMapSentencias.put(sentenciaDTO.getCnus(), sentenciaDTO.getCnus());
				 } else { 
					 hashMapSentencias.put(sentenciaDTO.getCnus(), llenarJSONObject(sentenciaDTO));
				 }
			 }
			 
			 if(!esUnico){
				 StringBuffer jsonText = new StringBuffer();
					jsonText.append("{");
					jsonText.append("\"nombreCompleto\":\"Nuevo\", ");
					jsonText.append("\"fechaIncio\": \"\" ,");
					jsonText.append("\"fechaFin\" :\"\" , ");
					jsonText.append("\"NUS\" :\"" + involucradoDTO.getElementoId()+"\"");
					jsonText.append("}");				 
					
					hashMapSentencias.put("" + involucradoDTO.getElementoId(), jsonText.toString());
			 }
			 

		} else {
			hashMapSentencias.put("unico", "TRUE");
			hashMapSentencias.put("" + involucradoDTO.getElementoId(), "" + involucradoDTO.getElementoId());
		}
		
		return hashMapSentencias;
		
	}

	private String llenarJSONObject(SentenciaDTO sentenciaDTO){
		
		StringBuffer jsonText = new StringBuffer();
		InvolucradoDTO involucradoDTO = sentenciaDTO.getInvolucradoDTO();
		jsonText.append("{");
		jsonText.append("\"nombreCompleto\":\"" + involucradoDTO.getNombreCompleto() + "\", ");
		jsonText.append("\"fechaIncio\":\"" + DateUtils.formatear(sentenciaDTO.getDfechaInicioPena()) + "\", ");
		jsonText.append("\"fechaFin\":\"" + DateUtils.formatear(sentenciaDTO.getDfechaFinPena()) + "\", ");
		jsonText.append("\"NUS\":\"" + sentenciaDTO.getCnus()+"\"");
		jsonText.append("}");
		
		return jsonText.toString();
		
	}	
	
}

package mx.gob.segob.nsjp.service.test.objeto.impl;

import java.util.List;

import mx.gob.segob.nsjp.dto.audiencia.EventoDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.service.objeto.ConsultarObjetosAudienciaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

import org.apache.log4j.Logger;

public class ConsultarObjetosAudienciaServiceImplTest extends
		BaseTestServicios<ConsultarObjetosAudienciaService> {
	
	private final static Logger logger = Logger.getLogger(ConsultarObjetosAudienciaServiceImplTest.class);
	
	public void testConsultarObjetosAudiencia(){
		EventoDTO evento = new EventoDTO();
		evento.setId(2L);
		List<EvidenciaDTO> lista = service.consultarObjetosEnAudiencia(evento);
		
		for(EvidenciaDTO evidencia : lista){
			logger.info("Otorgante: "+evidencia.getObjeto().getInstitucionPresenta().getNombreInst());
			logger.info("Descripcion: "+evidencia.getObjeto().getDescripcion());
			logger.info("Cadena de Custodia : "+evidencia.getObjeto().getCadenaDeCustodia().getFolio());
			logger.info("Num prueba: "+evidencia.getNumeroEvidencia());
			logger.info("Estado: no entregado");
		}
		
	}

}

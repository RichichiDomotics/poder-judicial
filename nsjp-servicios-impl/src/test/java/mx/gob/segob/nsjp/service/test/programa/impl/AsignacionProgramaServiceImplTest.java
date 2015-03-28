/**
 * 
 */
package mx.gob.segob.nsjp.service.test.programa.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.dto.sentencia.SentenciaDTO;
import mx.gob.segob.nsjp.service.programa.AsignacionProgramaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

import org.apache.log4j.Logger;

/**
 * @author AntonioBV
 *
 */
public class AsignacionProgramaServiceImplTest extends BaseTestServicios<AsignacionProgramaService> {
	private final static Logger LOGGER = Logger.getLogger(AsignacionProgramaServiceImplTest.class);
			

	public void testConsultarSentenciaConHijos(){
		
		try {
			SentenciaDTO  sentenciaDTO = new SentenciaDTO();
			sentenciaDTO.setSentenciaId(2L);
			
			sentenciaDTO = service.consultarSentenciaPorId(sentenciaDTO);
			
			assertNotNull("Se debe de tener un numero de expediente : ", sentenciaDTO.getNumeroExpedienteDTO());
			
		} catch (NSJPNegocioException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

}

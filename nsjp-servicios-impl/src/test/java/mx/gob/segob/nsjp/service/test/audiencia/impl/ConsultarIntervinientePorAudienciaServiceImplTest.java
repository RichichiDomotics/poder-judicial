/**
* Nombre del Programa : ConsultarIntervinientePorAudienciaServiceImplTest.java
* Autor                            : LuisMG
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 17/10/2011
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
package mx.gob.segob.nsjp.service.test.audiencia.impl;


import java.util.List;


import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;

import mx.gob.segob.nsjp.dto.audiencia.IntervinienteDTO;
import mx.gob.segob.nsjp.service.audiencia.ConsultarIntervinientePorAudienciaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author LuisMG
 *
 */
public class ConsultarIntervinientePorAudienciaServiceImplTest extends BaseTestServicios<ConsultarIntervinientePorAudienciaService > {
	public void testConsultarIntervinientePorAudiencia() {
		try {
			List<IntervinienteDTO> respuesta = service.consultarIntervinientePorAudiencia(103L);
			assertTrue("La lista debe tener minimo una audiencia ", respuesta.size()>=0);
			logger.info("Intervinientes ::: "+respuesta.size());
			for (IntervinienteDTO intervinienteDTO : respuesta) {
				logger.info("-----------------------");
				logger.info("Interviniente ID :: "+intervinienteDTO.getIntervinienteId());
				logger.info("Es aceptado :: "+intervinienteDTO.isEsAceptado());
				logger.info("Es parte fiscalia :: "+intervinienteDTO.isEsParteFiscalia());
				logger.info("Es presente :: "+intervinienteDTO.isEsPresente());
				logger.info("Es SuperVeniente :: "+intervinienteDTO.isEsSuperVeniente());
				logger.info("Es Titular :: "+intervinienteDTO.isEsTitular());
				if (intervinienteDTO.getAudiencia().getId()!= null)
				logger.info("Audiencia ID :: "+intervinienteDTO.getAudiencia().getId());
				if (intervinienteDTO.getFuncionario().getClaveFuncionario()!= null)
				logger.info("Funcionario ID :: "+intervinienteDTO.getFuncionario().getClaveFuncionario());
				if (intervinienteDTO.getInvolucrado().getInvolucradoIdDefensor()!= null)
				logger.info("Involucrado ID :: "+intervinienteDTO.getInvolucrado().getInvolucradoIdDefensor());
			
			
				logger.info("-----------------------");
			}
		} catch (NSJPNegocioException e) {
		logger.error(e.getMessage());
		}
	}
	
	
	
}

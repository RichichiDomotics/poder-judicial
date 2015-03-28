/**
* Nombre del Programa : ConsultarTareaFuncionarioServiceImplTest.java
* Autor                            : cesar
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 13 Jul 2011
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
package mx.gob.segob.nsjp.service.test.tarea.impl;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.eventocita.EstatusEventoCita;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.tarea.TareaDTO;
import mx.gob.segob.nsjp.service.tarea.ConsultarTareaFuncionarioService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author cesar
 *
 */
public class ConsultarTareaFuncionarioServiceImplTest extends
		BaseTestServicios<ConsultarTareaFuncionarioService> {

	public void testConsultarTareasFuncionario () {
		TareaDTO tareaDTO = new TareaDTO();
		
		tareaDTO.setDiaTarea(new Date());
		tareaDTO.setIdFuncionario(5L);
		
		try {
			List<TareaDTO> respuesta = service.consultarTareasFuncionario(tareaDTO);
			assertTrue("Lalista debe tener minimo una Tarea : ", respuesta.size()>0);
			logger.info("Lalista debe tener minimo una Tarea : " + respuesta.size());
			for (TareaDTO tarea : respuesta) {
				logger.info("-----------------------");
				logger.info("Tarea ID : "+tarea.getTareaId());
				logger.info("Tiempo tarea : "+tarea.getNtiempoReal());
				logger.info("Fecha tarea : "+tarea.getEventoCita().getFechaInicioEvento());
				logger.info("Nombre tarea : "+tarea.getEventoCita().getNombreEvento());
				logger.info("-----------------------");
			}
			
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	
	public void testConsultarTareasFuncionarioByEstatus () {
		TareaDTO tareaDTO = new TareaDTO();
		
		tareaDTO.setDiaTarea(new Date());
		tareaDTO.setIdFuncionario(5L);
		
		try {
			List<TareaDTO> respuesta = service.consultarTareasFuncionarioByEstatus(tareaDTO, EstatusEventoCita.NO_ATENDIDO);
			assertTrue("Lalista debe tener minimo una Tarea : ", respuesta.size()>0);
			logger.info("Lalista debe tener minimo una Tarea : " + respuesta.size());
			for (TareaDTO tarea : respuesta) {
				logger.info("-----------------------");
				logger.info("Tarea ID : "+tarea.getTareaId());
				logger.info("Tiempo tarea : "+tarea.getNtiempoReal());
				logger.info("Fecha tarea : "+tarea.getEventoCita().getFechaInicioEvento());
				logger.info("Nombre tarea : "+tarea.getEventoCita().getNombreEvento());
				logger.info("Estatus tarea : "+tarea.getEventoCita().getEstatus());
				logger.info("-----------------------");
			}
			
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
}

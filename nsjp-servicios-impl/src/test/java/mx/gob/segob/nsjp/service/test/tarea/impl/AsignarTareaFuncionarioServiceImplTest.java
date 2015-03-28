/**
* Nombre del Programa : AsignarTareaFuncionarioServiceImplTest.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 12 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba unitaria para el servicvio de asignar tareas a los funcionarios
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

import mx.gob.segob.nsjp.comun.enums.eventocita.EstatusEventoCita;
import mx.gob.segob.nsjp.comun.enums.tarea.TipoTarea;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.tarea.EventoCitaDTO;
import mx.gob.segob.nsjp.dto.tarea.TareaDTO;
import mx.gob.segob.nsjp.service.tarea.AsignarTareaFuncionarioService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria para el servicvio de asignar tareas a los funcionarios.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class AsignarTareaFuncionarioServiceImplTest extends
		BaseTestServicios<AsignarTareaFuncionarioService> {

	/**
	 * 
	 */
	public void testAsignarTareaFuncionario () {
		try {
			TareaDTO tarDTO = new TareaDTO();
			EventoCitaDTO eventoCita = new EventoCitaDTO();
			
			eventoCita.setNombreEvento("Tarea prueba");
			eventoCita.setEstatus(new ValorDTO(EstatusEventoCita.NO_ATENDIDO.getValorId()));
			eventoCita.setFechaInicioEvento(DateUtils.obtener("05/10/2011", "12:00"));
			
			tarDTO.setNtiempoReal((short)90);
			tarDTO.setValor(new ValorDTO(TipoTarea.ENTREVISTAR_TESTIGO.getValorId()));
			tarDTO.setIdFuncionario(new Long(14));
			tarDTO.setEventoCita(eventoCita);
								
			TareaDTO respuesta = service.asignarTareaFuncionario(tarDTO);
			
			assertTrue("El identificador de la tarea debe ser mayor a cero : ", respuesta.getTareaId()>0);
			logger.info("El identificador de la tarea debe ser mayor a cero : " + respuesta.getTareaId());
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	
}

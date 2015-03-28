/**
* Nombre del Programa : ReducirCargaDeTrabajoServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 18 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : implementacion del servicio para reducir la carga de trabajo de los Funcionarios
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
package mx.gob.segob.nsjp.service.tarea.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.tarea.TipoEvento;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.dao.tarea.EventoCitaDAO;
import mx.gob.segob.nsjp.dao.tarea.TareaDAO;
import mx.gob.segob.nsjp.model.EventoCita;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.service.tarea.ReducirCargaDeTrabajoService;

/**
 * implementacion del servicio para reducir la carga de trabajo de los Funcionarios.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class ReducirCargaDeTrabajoServiceImpl implements
		ReducirCargaDeTrabajoService {

	/**
	 * 
	 */
	public static final Logger logger = Logger.getLogger(ReducirCargaDeTrabajoServiceImpl.class);
	
	@Autowired
	private FuncionarioDAO funcionarioDAO;
	@Autowired
	private TareaDAO tareaDAO;
	@Autowired
	private EventoCitaDAO eventoCitaDAO;
	
	@Override
	public void reducirCargaDeTrabajo() throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA REDUCIR LA CARGA DE TRABAJO DE LOS FUNCIONARIOS ****/");
		
		List<Long> idFuncionarios = funcionarioDAO.findAllId();
		Date fechaRed = new Date();
		
		for (Long idFun : idFuncionarios) {
			logger.debug("---------------------------");
			logger.debug("/**** FUNCIONARIO CON ID ****/" + idFun);
			
			Funcionario funcionario = funcionarioDAO.read(idFun);
			Double cargaBD = funcionario.getCargaTrabajo();
			
			Long cargaDia = tareaDAO.sumCargaDiaria(fechaRed, idFun);
			if (cargaDia!=null) {
				Double cargaRed = cargaDia.doubleValue()/60; 
				Double cargaAct =  cargaBD-cargaRed;
				logger.debug("/**** SE ACTUALIZO LA CARGA DEL FUNCIONARIO ****/ " + cargaRed);
				funcionario.setCargaTrabajo(cargaAct);
				funcionarioDAO.update(funcionario);
			} else {
				logger.debug("/**** VERIFICAR SI NO SE ENCUENTRA DE VACACIONES O INCAPACIDAD ****/ ");
				Calendar calTemp = Calendar.getInstance();
				
				List<EventoCita> eventoVacacion = eventoCitaDAO.consultarEventosPorTipoYFecha(TipoEvento.VACACIONES, calTemp.getTime());
				List<EventoCita> eventoIncapacidad = eventoCitaDAO.consultarEventosPorTipoYFecha(TipoEvento.INCAPACIDAD, calTemp.getTime());
				if (!eventoVacacion.isEmpty() || !eventoIncapacidad.isEmpty()) {
					if (cargaBD!=null) {
						Double cargaAct =  cargaBD-8.0;
						funcionario.setCargaTrabajo(cargaAct);
						funcionarioDAO.update(funcionario);
						logger.debug("/**** SE ACTUALIZO LA CARGA DEL FUNCIONARIO POR VACACIONES O INCAPACIDAD ****/ ");
					}
				}
			}			
			
			logger.debug("---------------------------");
		}
	}

}

package mx.gob.segob.nsjp.web.defensor.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.eventocita.EstatusEventoCita;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.delegate.funcionario.FuncionarioDelegate;
import mx.gob.segob.nsjp.delegate.tarea.TareaDelegate;
import mx.gob.segob.nsjp.dto.tarea.TareaDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

public class ActualizarTareaDefensorAction extends GenericAction {
	
	private static final Logger log = Logger.getLogger(ActualizarTareaDefensorAction.class);
	@Autowired
	private FuncionarioDelegate funcionarioDelegate;
	
	@Autowired
	private TareaDelegate tareaDelegate;
	
		public ActionForward consultaTareasDefensor(ActionMapping mapping,
				ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws IOException {
			try {
				
				log.info("Actualiza Tarea DEFENSORES");
				String id = request.getParameter("id");
				log.info("id====" + id);
				String dia = request.getParameter("dia");
				
				log.info("Dia====" + dia);
				Date fecha = DateUtils.obtener(dia);
				TareaDTO tareaDTO = new TareaDTO();
				tareaDTO.setIdFuncionario(Long.parseLong(id));
				tareaDTO.setDiaTarea(fecha);
				List<TareaDTO> tareaDTOs = new ArrayList<TareaDTO>();
				tareaDTOs = tareaDelegate.consultarTareasFuncionarioByEstatus(tareaDTO, EstatusEventoCita.ATENDIDO);
				
				
				log.info("LISTA Tareas del Defensor" + tareaDTOs);
				
				
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();

				writer.print("<rows>");		

				int lTotalRegistros = tareaDTOs.size();
				writer.print("<records>" + lTotalRegistros + "</records>");
				//NSJYUCPROC2011333WT

				for(TareaDTO tareaDTO2 : tareaDTOs){

					writer.print("<row id='"+tareaDTO2.getTareaId()+ "'>");
					
					writer.print("<cell>");
					if(tareaDTO2.getEventoCita().getNombreEvento() !=null){
						writer.print(tareaDTO2.getEventoCita().getNombreEvento());
							}
					writer.print("</cell>");
					
					
					writer.print("</row>");
				}
				writer.print("</rows>");
				writer.flush();
				writer.close();
				

			} catch (Exception e) {
			    log.error(e.getMessage(), e);
			}
			return null;
		}

		
	public ActionForward asignarCargaTrabajoActualizado(ActionMapping mapping,
				ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws IOException {
			try {
				
				log.info("DEFENSORES Tareas");
              				
				
				
				String carga = request.getParameter("carga");
				log.info("carga====" + carga);
				String idCarga = request.getParameter("idCarga");
				log.info("idCarga====" + idCarga);
				String id = request.getParameter("id");
				log.info("id====" + id);
				String nombreDef = request.getParameter("nombreDef");
				log.info("nombreDef====" + nombreDef);
				String fecha = request.getParameter("fecha");
				log.info("fecha====" + fecha);
				String hora = request.getParameter("hora");
				log.info("hora====" + hora);
				
				TareaDTO tareaDTO = new TareaDTO();
				tareaDTO.setIdFuncionario(Long.parseLong(id));
				tareaDTO.setTareaId(Long.parseLong(idCarga));
				tareaDTO.setNtiempoReal(Short.parseShort(carga));
				
				tareaDelegate.actualizarTareaFuncionario(tareaDTO);
			
				log.info("Tareas asignadas"+tareaDTO);
			

			} catch (Exception e) {
			    log.error(e.getMessage(), e);
			}
			return null;
		}
}

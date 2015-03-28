package mx.gob.segob.nsjp.web.defensor.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.catalogo.Catalogos;
import mx.gob.segob.nsjp.comun.enums.eventocita.EstatusEventoCita;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.bitacora.BitacoraMovimientoDelegate;
import mx.gob.segob.nsjp.delegate.funcionario.FuncionarioDelegate;
import mx.gob.segob.nsjp.delegate.tarea.TareaDelegate;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.bitacora.RegistroBitacoraDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.tarea.EventoCitaDTO;
import mx.gob.segob.nsjp.dto.tarea.TareaDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

public class TareaDefensorAction extends GenericAction {
	
	private static final Logger log = Logger.getLogger(TareaDefensorAction.class);
	@Autowired
	private FuncionarioDelegate funcionarioDelegate;
	
	@Autowired
	private TareaDelegate tareaDelegate;
	
	@Autowired
	private BitacoraMovimientoDelegate bitacoraDelegate;
	
		public ActionForward consultaDefensor(ActionMapping mapping,
				ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws IOException {
			try {
				
				log.info("DEFENSORES");
				
				List<FuncionarioDTO> funcionarioDTOs = new ArrayList<FuncionarioDTO>();
				funcionarioDTOs = funcionarioDelegate.consultarDefensores();
				log.info("LISTA DEFENSORES" + funcionarioDTOs);
				
				
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();

				writer.print("<rows>");		

				int lTotalRegistros = funcionarioDTOs.size();
				writer.print("<records>" + lTotalRegistros + "</records>");
				//NSJYUCPROC2011333WT

				for(FuncionarioDTO funcionarioDTO : funcionarioDTOs){

					writer.print("<row id='"+funcionarioDTO.getClaveFuncionario()+ "'>");
					
					writer.print("<cell>");
					if(funcionarioDTO.getNombreCompleto() !=null){
						writer.print(funcionarioDTO.getNombreCompleto());
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

		
		public ActionForward consultarCatalogoActividades(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws IOException {
	    	try {
	    		log.info("ejecutando Action Cargar Combo Actividades"); 
	    		List<CatalogoDTO> listaCatalogo= catDelegate.recuperarCatalogoCompleto(Catalogos.TIPO_TAREA);
	    		
	    		
	    		
	    	
	    		response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();

				writer.print("<rows>");		

				int lTotalRegistros = listaCatalogo.size();
				writer.print("<records>" + lTotalRegistros + "</records>");
				//NSJYUCPROC2011333WT

				for(CatalogoDTO catalogoDTO : listaCatalogo){
					
					for (ValorDTO cataValDTO : catalogoDTO.getValoresExras()) {
						writer.print("<row id='"+ cataValDTO.getIdCampo()+ "'>");
										
					
					writer.print("<cell>");
					if(catalogoDTO.getValor() !=null){
						writer.print(catalogoDTO.getValor());
							}
					writer.print("</cell>");
					
					
					writer.print("<cell>");
					if(cataValDTO.getValor() !=null){
						writer.print(cataValDTO.getValor());
							}
					writer.print("</cell>");
					
					
					writer.print("</row>");
					}
				}
				writer.print("</rows>");
				writer.flush();
				writer.close();
				}
			catch (Exception e) {
				log.info(e);
			}
				
				
//				e.printStackTrace();
//			}

			return null;
		}
	    
		
		public ActionForward asignarCargaTrabajo(ActionMapping mapping,
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
				EventoCitaDTO eventoCita = new EventoCitaDTO();
				FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
				
				eventoCita.setNombreEvento(nombreDef);
				eventoCita.setEstatus(new ValorDTO(EstatusEventoCita.NO_ATENDIDO.getValorId()));
				eventoCita.setFechaInicioEvento(DateUtils.obtener(fecha, hora));
				ValorDTO valorDTO = new ValorDTO();
				valorDTO.setValor(idCarga);
				tareaDTO.setNtiempoReal(Short.parseShort(carga));
				tareaDTO.setValor(valorDTO);
				tareaDTO.setIdFuncionario(Long.parseLong(id));
				tareaDTO.setEventoCita(eventoCita);
				
				tareaDTO = tareaDelegate.asignarTareaFuncionario(tareaDTO, funcionarioDTO);
				log.info("Tareas asignadas"+tareaDTO);
			

			} catch (Exception e) {
			    log.error(e.getMessage(), e);
			}
			return null;
		}

		public ActionForward consultarBitacoraPorExpediente(ActionMapping mapping,
				ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws IOException {
			try {
				Long numeroExpedienteId = new Long(request.getParameter("numeroExpedienteId"));
				ExpedienteDTO expedienteDTO = new ExpedienteDTO();
				expedienteDTO.setNumeroExpedienteId(numeroExpedienteId);
				
				List<RegistroBitacoraDTO> registrosBitacoraDTOs = new ArrayList<RegistroBitacoraDTO>();
				registrosBitacoraDTOs = bitacoraDelegate.consultarRegistrosByExpediente(expedienteDTO);
				
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();

				writer.print("<rows>");		

				final PaginacionDTO pag = PaginacionThreadHolder.get();
				log.debug("pag :: " + pag);
                if (pag != null && pag.getTotalRegistros() != null) {
                    writer.print("<total>" + pag.getTotalPaginas() + "</total>");
                    writer.print("<records>" + pag.getTotalRegistros() + "</records>");
                } else {
                    writer.print("<total>0</total>");
                    writer.print("<records>0</records>");
                }

				for(RegistroBitacoraDTO regBitacoraDTO : registrosBitacoraDTOs){

					writer.print("<row id='"+regBitacoraDTO.getRegistroBitacoraId()+ "'>");
					
					writer.print("<cell>");
					if(regBitacoraDTO.getFechaInicio() !=null){
						writer.print(DateUtils.formatear(regBitacoraDTO.getFechaInicio()));
					}else{
						writer.print("Fecha");
					}
					writer.print("</cell>");
					
					writer.print("<cell>");
					if(regBitacoraDTO.getFechaInicio() != null){
						Calendar cal = Calendar.getInstance();
						cal.setTime(regBitacoraDTO.getFechaInicio());
						int hour = cal.get(Calendar.HOUR_OF_DAY);
				        int minutes = cal.get(Calendar.MINUTE);
				        writer.print((hour < 10 ? "0" + hour : hour) + ":" + (minutes < 10 ? "0" + minutes : minutes));
					}
					writer.print("</cell>");
					
					writer.print("<cell>");
					if(regBitacoraDTO.getTipoMovimiento() !=null){
						writer.print(regBitacoraDTO.getTipoMovimiento().getValor());
					}else{
						writer.print("Tipo de Movimiento");
					}
					writer.print("</cell>");
					
					writer.print("<cell>");
					if(regBitacoraDTO.getNuevo() !=null){
						writer.print(regBitacoraDTO.getNuevo());
					}else{
						writer.print("Descripción");
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

}

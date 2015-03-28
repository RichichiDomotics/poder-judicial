package mx.gob.segob.nsjp.web.defensor.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.tarea.TipoEvento;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.funcionario.FuncionarioDelegate;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.tarea.EventoCitaDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

public class RegistroVacacionesIncapacidadesAction extends GenericAction {
	
	private static final Logger log = Logger.getLogger(RegistroVacacionesIncapacidadesAction.class);
	
	@Autowired
	private FuncionarioDelegate funcionarioDelegate;
	
		/**
		 * Metodo utilizado para realizar la consulta de defensores activos
		 * a la JSP de atencionSolicitudAudienciaNotificador
		 * @param mapping
		 * @param form
		 * @param request 
		 * @param response
		 * @return succes - En caso de que el proceso sea correcto
		 * @throws IOException En caso de obtener una exception
		 */
		public ActionForward consultarDefensoresActivos(ActionMapping mapping,
				ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws IOException {
			try {
				
				log.info("ENTRA A CONSULTAR DEFENSORES ACTIVOS");
								
				List<FuncionarioDTO> funcionarioDTOs = funcionarioDelegate.consultarDefensoresActivosPorTipoDefensa(null);
				if(log.isDebugEnabled()){				
				    log.debug("Funcionarios" + funcionarioDTOs);
				}				
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
				
				for(FuncionarioDTO funcionarioDTO : funcionarioDTOs){
					if(request.getParameter("index") != null && (Integer.parseInt(request.getParameter("index")) == 1)){
						writer.print("<row id='"+funcionarioDTO.getClaveFuncionario()+"'>");
						if(funcionarioDTO.getTipoEspecialidad() != null){
							writer.print("<cell>"+funcionarioDTO.getTipoEspecialidad().getValor()+ "</cell>");
						}else{
							writer.print("<cell> --- </cell>");
						}
						writer.print("<cell>"+funcionarioDTO.getNombreFuncionario()+" "+funcionarioDTO.getApellidoPaternoFuncionario() +" "+funcionarioDTO.getApellidoMaternoFuncionario()+"</cell>");
						if(funcionarioDTO.getEspecialidad() != null){
							writer.print("<cell>"+funcionarioDTO.getEspecialidad().getValor()+ "</cell>");
						}else{
							writer.print("<cell> --- </cell>");
						}
						writer.print("</row>");
					}else{
						writer.print("<row id='"+funcionarioDTO.getClaveFuncionario()+ "'>");
						writer.print("<cell>"+funcionarioDTO.getNombreFuncionario()+ "</cell>");
						writer.print("<cell>"+funcionarioDTO.getApellidoPaternoFuncionario()+ "</cell>");
						writer.print("<cell>"+funcionarioDTO.getApellidoMaternoFuncionario()+ "</cell>");
						writer.print("</row>");
					}	
				}
				writer.print("</rows>");
				writer.flush();
				writer.close();
				

			} catch (Exception e) {
			    log.error(e.getMessage(), e);
			}
			return null;
		}
		
		/**
		 * Metodo utilizado para realizar el registro de vacaciones e inactividades
		 * a la JSP de atencionSolicitudAudienciaNotificador
		 * @param mapping
		 * @param form
		 * @param request 
		 * @param response
		 * @return succes - En caso de que el proceso sea correcto
		 * @throws IOException En caso de obtener una exception
		 */
		public ActionForward registrarVacacionesIncapacidad(ActionMapping mapping,
				ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws IOException {
			try {
				
				log.info("ENTRA A REGISTRAR VACACIONES O INCAPACIDAD");
				
				String idFuncionario = request.getParameter("claveFuncionario");
				log.info("llega id funcionario:" + idFuncionario);
					
				String nombreEvento = request.getParameter("nombreEvento");
				log.info("llega nombre evento:" + nombreEvento);
					
				Long tipoEvento = Long.parseLong(request.getParameter("tipoRegistro"));
				log.info("llega tipo:" + tipoEvento);
									
				String fechaInicio = request.getParameter("fechaInicio");
				log.info("llega:" + fechaInicio);
					
				String fechaFin = request.getParameter("fechaFin");
				log.info("llega:" + fechaFin);			
				
				DateFormat formato = new SimpleDateFormat("dd/MM/yy");
				Date fechaEventoInicio = null;
				Date fechaEventoFin = null;
				
				try {
					fechaEventoInicio = formato.parse(fechaInicio);
					fechaEventoFin = formato.parse(fechaFin);		
				
				} catch (ParseException e) {
				
					e.printStackTrace();
				}
				
				FuncionarioDTO funcionario = new FuncionarioDTO();
				funcionario.setClaveFuncionario(Long.parseLong(idFuncionario));
				
				ValorDTO valorDTO = new ValorDTO();
								
				if (tipoEvento == 1) {
					log.info("entra a vacaciones");
					valorDTO.setIdCampo(TipoEvento.VACACIONES.getValorId());
					log.info("valor de:" + TipoEvento.VACACIONES.getValorId());
		
				}else{
					log.info("entra a incapacidad");
					valorDTO.setIdCampo(TipoEvento.INCAPACIDAD.getValorId());
					log.info("valor de:" + TipoEvento.INCAPACIDAD.getValorId());					
					
				}		
				
				super.getUsuarioFirmado(request);
				
				EventoCitaDTO periodo = new EventoCitaDTO();
				periodo.setFechaInicioEvento(fechaEventoInicio);
				periodo.setFechaFinEvento(fechaEventoFin);
				periodo.setTipoEvento(valorDTO);
				periodo.setNombreEvento(nombreEvento);
				periodo.setEsAlertaAlarma(false);
				
				try{
					funcionarioDelegate.registrarVacacionesIncapacidad(funcionario, periodo, super.getUsuarioFirmado(request));
					
					converter.alias("mensaje",String.class);
					String xml = converter.toXML("El registro se ha realizado con exito");
					escribirRespuesta(response, xml);
					
				}catch(NSJPNegocioException e){
					if(e.getCodigo() == CodigoError.EJCUCION_OPERACION_ESTADO_INCORRECTO){
						
						converter.alias("mensaje",String.class);
						String xml = converter.toXML("No se puede registrar el evento por que el periodo coincide con un periodo registrado");
						escribirRespuesta(response, xml);
						
					}
				}
								

			} catch (Exception e) {
			    log.error(e.getMessage(), e);
			}
			return null;
		}
				
}

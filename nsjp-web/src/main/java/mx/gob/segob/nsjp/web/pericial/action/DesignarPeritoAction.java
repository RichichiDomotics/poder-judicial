/**
* Nombre del Programa : DesignarPeritoAction.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 13/07/2011
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
package mx.gob.segob.nsjp.web.pericial.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.delegate.evidencia.EvidenciaDelegate;
import mx.gob.segob.nsjp.delegate.solicitud.SolicitudDelegate;
import mx.gob.segob.nsjp.delegate.solicitud.SolicitudPericialDelegate;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.institucion.DepartamentoDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudPericialDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Componente de Action que atiende las solicitudes para asignar peritos a un expediente
 * y atender una solicitud de servicio pericial
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public class DesignarPeritoAction extends GenericAction {
	@Autowired
	SolicitudPericialDelegate solicitudPericialDelegate;
	@Autowired
	SolicitudDelegate solicitudDelegate;
	@Autowired
	EvidenciaDelegate evidenciaDelegate;
	
	
	/**Log de clase*/
	private static final Logger log  = Logger.getLogger(DesignarPeritoAction.class);
	
	/**
	 * Método para realizar al búsqueda de peritos por 3 tipos de filtro:
	 * Nombre: Busca peritos por nombre, apellido paterno y/o apellido materno
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward buscarPeritosPorFiltro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		//criterios
		FuncionarioDTO filtro = new FuncionarioDTO();
		List<FuncionarioDTO> resultados = new ArrayList<FuncionarioDTO>();
		
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter writer = response.getWriter();

		writer.print("<rows>");

	

		writer.print("<records>" + resultados.size() + "</records>");
		boolean sinResultados = request.getParameter("limpiar") != null;
		
		if(!sinResultados){
			sinResultados = true;
			if( StringUtils.isNotBlank(request.getParameter("nombre"))){
				filtro.setNombreFuncionario(request.getParameter("nombre"));
				sinResultados = false;
			}
			if( StringUtils.isNotBlank(request.getParameter("apellidoPaterno"))){
				filtro.setApellidoPaternoFuncionario(request.getParameter("apellidoPaterno"));
				sinResultados = false;
			}
			if( StringUtils.isNotBlank(request.getParameter("apellidoMaterno"))){
				filtro.setApellidoMaternoFuncionario(request.getParameter("apellidoMaterno"));
				sinResultados = false;
			}
			if( StringUtils.isNotBlank(request.getParameter("institucion"))){
				Long idInstitucion = NumberUtils.toLong(request.getParameter("institucion"),0);
				if(idInstitucion>0){
					filtro.setDepartamento(new DepartamentoDTO());
					filtro.getDepartamento().setArea(new AreaDTO(idInstitucion));
					sinResultados = false;
				}
				
			}
			if( StringUtils.isNotBlank(request.getParameter("especialidad"))){
				Long idEspecialidad = NumberUtils.toLong(request.getParameter("especialidad"),0);
				if(idEspecialidad>0){
					filtro.setEspecialidad(new ValorDTO(idEspecialidad));
					sinResultados = false;
				}
			}
						
			try {
				if(!sinResultados){
					resultados = solicitudPericialDelegate.consultarFuncionarioPorFiltro(filtro);
					for (FuncionarioDTO funcionario : resultados) {
						   
						writer.print("<row id='"+ funcionario.getClaveFuncionario() + "'>");
						writer.print("<cell>"+ funcionario.getNombreCompleto() +  "</cell>");
						writer.print("<cell>"+ (funcionario.getEspecialidad()!=null?funcionario.getEspecialidad().getValor():"-") +  "</cell>");
						writer.print("<cell>"+ (funcionario.getDepartamento()!=null?funcionario.getDepartamento().getArea().getNombre():"-") +  "</cell>");
						
						List<SolicitudPericialDTO> solicitudesDTOs = new ArrayList<SolicitudPericialDTO>();
						solicitudesDTOs = 
							solicitudPericialDelegate.consultarSolicitudesPericialesPorEstatusYDestinatario(EstatusSolicitud.EN_PROCESO, funcionario);
						if(solicitudesDTOs != null){
							writer.print("<cell>"+ solicitudesDTOs.size() +  "</cell>");
						}else{
							writer.print("<cell>0</cell>");
						}
						writer.print("</row>");
					}
					
				}
				
				
				
				
			} catch (NSJPNegocioException e) {
				log.error(e);
			}
			
			
		}
		
		writer.print("</rows>");
		writer.flush();
		writer.close();
		return null;
	}
	
	/**
	 * Método para realizar al búsqueda de peritos por 3 tipos de filtro:
	 * Nombre: Busca peritos por nombre, apellido paterno y/o apellido materno
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarPeritosDesignados(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			
			Long solicitudPericialId= NumberUtils.toLong(request.getParameter("solicitudPericialId"),0);
			
			if(solicitudPericialId >0){
				
				//consultar la solicitud pericial
				SolicitudPericialDTO solicitud = solicitudPericialDelegate.consultarSolicitudPericialPorId(solicitudPericialId);
				
				List<SolicitudPericialDTO> resultados = solicitud.getSolicitudesHijas();
				if(resultados == null){
					resultados = new ArrayList<SolicitudPericialDTO>();
				}
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();

				writer.print("<rows>");
				writer.print("<records>" + resultados.size() + "</records>");
				
				for (SolicitudPericialDTO solHija : resultados) {

					writer.print("<row id='" + solHija.getDestinatario().getClaveFuncionario()
							+ "'>");
					writer.print("<cell>" + solHija.getDestinatario().getNombreCompleto()
							+ "</cell>");
					writer.print("<cell>"
							+ (solHija.getDestinatario().getEspecialidad() != null ? solHija.getDestinatario()
									.getEspecialidad().getValor() : "-")
							+ "</cell>");
					writer.print("<cell>"
							+ (solHija.getDestinatario().getDepartamento() != null ? solHija.getDestinatario()
									.getDepartamento().getArea().getNombre() : "-")
							+ "</cell>");
					writer.print("</row>");
				}
				writer.print("</rows>");
				writer.flush();
				writer.close();
			}
			
			
			

		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}
	
	
	/**
	 * Método para realizar al búsqueda de peritos por 3 tipos de filtro:
	 * Nombre: Busca peritos por nombre, apellido paterno y/o apellido materno
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward guardarPeritosDesignados(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			log.info("ACTION GUARDAR PERITOS DESIGNADOS---- ");
			Long solicitudPericialId= NumberUtils.toLong(request.getParameter("solicitudPericialId"));
			String idsPeritosString = request.getParameter("idsPeritos");
			String sEstatus = request.getParameter("estatus");
			log.info("ID DE LA SOLICITUD PERICIAL----"+solicitudPericialId);
			log.info("ID'S DE LOS PERITOS SELECCIONADOS----"+idsPeritosString);
			log.info("ESTATUS----"+sEstatus);
			if(StringUtils.isNotBlank(idsPeritosString)){
				//consultar la solicitud pericial
				SolicitudPericialDTO solicitud = solicitudPericialDelegate.consultarSolicitudPericialPorId(solicitudPericialId);
				if(sEstatus.equals("NA")){ //NO ATENDIDA
					solicitud.setEstatus(new ValorDTO(EstatusSolicitud.EN_PROCESO.getValorId()));
				}else{ //EN PROCESO
					solicitud.setEstatus(new ValorDTO(EstatusSolicitud.CERRADA.getValorId()));
				}
				if(solicitud != null && idsPeritosString != null){
					//log.info("NUMERO DE EXPEDIENTE ID ... " + solicitud.getExpedienteDTO().getNumeroExpedienteId());
					//log.info("NUMERO DE EXPEDIENTE ... " + solicitud.getExpedienteDTO().getNumeroExpediente());
					//log.info("FOLIO SOLICITUD ... " + solicitud.getFolioSolicitud());
					solicitud.setFechaModificacion(new Date());
					String[] idsPeritos = idsPeritosString.split(",");
					solicitud.setPeritosDesignados(new ArrayList<FuncionarioDTO>());
					for(String idPerito:idsPeritos){
						solicitud.getPeritosDesignados().add(new FuncionarioDTO(NumberUtils.toLong(idPerito)));
					}
					
					solicitudPericialDelegate.registrarActulizarSolicitudPericial(solicitud);
					escribirRespuesta(response,converter.toXML(idsPeritosString));
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	/**
	 * Método para realizar la consulta de asignaciones no atendidas por un perito, tanto la asignacion
	 * de una asesoria, como de un dictamen
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultaAsignacionesNoAtendidas(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			log.info("EJECUTANDO ACTION CONSULTAR ASIGNACIONES NO ATENDIDAS DE UN PERITO--- ");
			
			UsuarioDTO usuarioFirmado = getUsuarioFirmado(request);
			if(usuarioFirmado != null){
				
				List<SolicitudPericialDTO> listaDeSolicitudes = solicitudPericialDelegate.consultarSolicitudesPericialesPorEstatusYDestinatario(EstatusSolicitud.ABIERTA, usuarioFirmado.getFuncionario());
				
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();

				writer.print("<rows>");
				writer.print("<records>" + listaDeSolicitudes.size() + "</records>");
				
				for (SolicitudPericialDTO solicitud : listaDeSolicitudes) {

//					writer.print("<row id='" + (solicitud.getDestinatario() != null ? solicitud.getDestinatario().getClaveFuncionario(): "")+ "'>");
					writer.print("<row id='" + (solicitud.getDocumentoId() != null ? solicitud.getDocumentoId() : "")+ "'>");					
						writer.print("<cell>" + (solicitud.getUsuarioSolicitante() != null ? solicitud.getUsuarioSolicitante().getNombreCompleto(): "")+ "</cell>");
						writer.print("<cell>" + (solicitud.getTipoSolicitudDTO() != null ? solicitud.getTipoSolicitudDTO().getValor(): "")+ "</cell>");
						writer.print("<cell>" + (solicitud.getExpedienteDTO() != null ? solicitud.getExpedienteDTO().getNumeroExpediente(): "")+ "</cell>");
						writer.print("<cell>"+ (solicitud.getExpedienteDTO()!=null&&
								solicitud.getExpedienteDTO().getCasoDTO()!=null?
								solicitud.getExpedienteDTO().getCasoDTO().getNumeroGeneralCaso():"") +  "</cell>");
						if(solicitud.getFechaLimite() != null){
							String fechaSolicitud=DateUtils.formatear(solicitud.getFechaLimite());
							writer.print("<cell>" + fechaSolicitud + "</cell>");
						}else{
							writer.print("<cell>" + "" + "</cell>");
						}
						writer.print("<cell>" + "" + "</cell>");
					writer.print("</row>");
				}
				writer.print("</rows>");
				writer.flush();
				writer.close();
				
			}
			
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}
	
	/**
	 * Método para realizar la consulta de asignaciones no atendidas por un perito, tanto la asignacion
	 * de una asesoria, como de un dictamen
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward confirmarAsignacionDePerito(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			log.info("EJECUTANDO ACTION CONSULTAR ASIGNACIONES NO ATENDIDAS DE UN PERITO--- ");
			
			Long solicitudPericialId= NumberUtils.toLong(request.getParameter("solicitudPericialId"));
				
				solicitudDelegate.actualizaEstatusSolicitud(new SolicitudDTO(solicitudPericialId), EstatusSolicitud.EN_PROCESO);
				String xml = converter.toXML("ok");
				escribir(response, xml,null);										
			
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}
	
	
	/**
	 * Método para realizar la consulta de asignaciones no atendidas por un perito, tanto la asignacion
	 * de una asesoria, como de un dictamen
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarEvidenciasDeCadenaDeCustodia(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			log.info("EJECUTANDO ACTION CONSULTAR EVIDENCIAS DE CADENA DE CUSTODIA--- ");
			String folioCadenaCustodia = request.getParameter("folioCadenaCustodia");
			Long solicitudPericialId= NumberUtils.toLong(request.getParameter("solicitudPericialId"));
			
			log.info("folioCadenaCustodia===="+folioCadenaCustodia);
			log.info("solicitudPericialId===="+solicitudPericialId);
			
			if(folioCadenaCustodia != null && solicitudPericialId != null){
				
				CadenaDeCustodiaDTO cadena =  new CadenaDeCustodiaDTO();
				cadena.setFolio(folioCadenaCustodia);
				List<EvidenciaDTO> listaEvidencias =  evidenciaDelegate.consultarEvidenciaXCadenaCustodiaYPeritoDeSolicitudPericial(cadena, new SolicitudPericialDTO(solicitudPericialId));
				
				
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();

				writer.print("<rows>");		

				int lTotalRegistros = listaEvidencias.size();
				writer.print("<records>" + lTotalRegistros + "</records>");
				for(EvidenciaDTO eviDTO : listaEvidencias){
					
					if(eviDTO.getUltimoEslabon() != null && eviDTO.getUltimoEslabon().getFuncionariRecibe()!= null){
						
						writer.print("<row id='"+folioCadenaCustodia+","+eviDTO.getUltimoEslabon().getFuncionariRecibe().getClaveFuncionario()+","+eviDTO.getEvidenciaId()+"'>");
					}else{
						writer.print("<row id='"+folioCadenaCustodia+",,"+eviDTO.getEvidenciaId()+ "'>");
					}
					//Numero de evidencia
					writer.print("<cell>");
						if(eviDTO.getNumeroEvidencia() != null){
							writer.print(eviDTO.getNumeroEvidencia());
						}
					writer.print("</cell>");
					
					//Tipo de Objeto
					writer.print("<cell>");
						if(eviDTO.getObjeto() != null && eviDTO.getObjeto().getTipoObjeto() != null){
							writer.print(eviDTO.getObjeto().getTipoObjeto());
						}
					writer.print("</cell>");
					
					//Nombre objeto
					writer.print("<cell>");
						if(eviDTO.getObjeto() != null && eviDTO.getObjeto().getValorIdElemento() != null && eviDTO.getObjeto().getValorIdElemento().getNombreCampo() != null){
							writer.print(eviDTO.getObjeto().getValorIdElemento().getNombreCampo());//objeto
						}
					writer.print("</cell>");
					
					//descripcion 
					writer.print("<cell>");
						if(eviDTO.getObjeto() != null && eviDTO.getObjeto().getDescripcion() != null){
							writer.print(eviDTO.getObjeto().getDescripcion());
						}
					writer.print("</cell>");
					
					//codigo de barras
					writer.print("<cell>");
						if(eviDTO.getCodigoBarras() != null){
							writer.print(eviDTO.getCodigoBarras());
						}
					writer.print("</cell>");
					
					//Perito asignado
					writer.print("<cell>");
						if(eviDTO.getUltimoEslabon() != null && eviDTO.getUltimoEslabon().getFuncionariRecibe()!= null){
							writer.print(eviDTO.getUltimoEslabon().getFuncionariRecibe().getNombreCompleto());
						}
					writer.print("</cell>");
					
					writer.print("</row>");

				}
				
				writer.print("</rows>");
				writer.flush();
				writer.close();
			}
			
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}
	
	
	/**
	 * Método para realizar la consulta de todos aquellos peritos que no esten en posesion o tengan asignada una 
	 * evidencia, relacionada con la solicitud
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarPeritosLibresDeEvidencia(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			log.info("EJECUTANDO ACTION CONSULTAR PERITOS LIBRES DE EVIDENCIA--- ");
			String folioCadenaCustodia = request.getParameter("folioCadenaCustodia");
			Long solicitudPericialId= NumberUtils.toLong(request.getParameter("solicitudPericialId"));
			
			log.info("folioCadenaCustodia===="+folioCadenaCustodia);
			log.info("solicitudPericialId===="+solicitudPericialId);
			
			if(folioCadenaCustodia != null && solicitudPericialId != null){
				
				CadenaDeCustodiaDTO cadena =  new CadenaDeCustodiaDTO();
				cadena.setFolio(folioCadenaCustodia);
				List<FuncionarioDTO> listaPeritos =  evidenciaDelegate.consultarPeritosSinAsignacionEnCadenaDeCustodia(cadena,new SolicitudPericialDTO(solicitudPericialId));
				
				
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();

				writer.print("<rows>");	
				int lTotalRegistros = listaPeritos.size();
				writer.print("<records>" + lTotalRegistros + "</records>");
				for(FuncionarioDTO perito : listaPeritos){
					
					if(perito.getClaveFuncionario() != null){
						
						writer.print("<row id='"+perito.getClaveFuncionario()+"'>");
					}else{
						writer.print("<row id='"+""+"'>");
					}
					
					
					//Numero del perito
					writer.print("<cell>");
						if(perito.getNombreCompleto() != null){
							writer.print(perito.getNombreCompleto());
						}
					writer.print("</cell>");
					
					//Especialidad
					writer.print("<cell>");
						if(perito.getEspecialidad() != null && perito.getEspecialidad().getNombreCampo() != null){
							writer.print(perito.getEspecialidad().getNombreCampo());
						}
					writer.print("</cell>");
					
					
					//Institucion
					writer.print("<cell>");
						if(perito.getNombreInstitucion() != null){
							writer.print(perito.getNombreInstitucion());
						}
					writer.print("</cell>");
					
					writer.print("</row>");

				}
				
				writer.print("</rows>");
				writer.flush();
				writer.close();
			}
			
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}
	
	/**
	 * Método realizar la asociacion del perito con la evidencia
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward guardaAsignacionEvidencia(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			log.info("EJECUTANDO ACTION GUARDAR ASIGNACIONES DE EVIDENCIA:::::_____::::::______::::::");
			String evidenciaId = request.getParameter("evidenciaId");
			String peritoId = request.getParameter("peritoId");
			String solicitudPericialId = request.getParameter("solicitudPericialId");
			log.info("evidenciaId===="+evidenciaId);
			log.info("peritoId===="+peritoId);
			log.info("solicitudPericialId===="+solicitudPericialId);
			
			String xml = converter.toXML("ok");
			escribir(response, xml,null);

		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}
	
	/**
	 * Método para asignar los peritos a la bandeja de entrada
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward enviarSolicitudPerito(ActionMapping mapping, ActionForm form, HttpServletRequest request,
											   HttpServletResponse response) 
			throws IOException {

		try {
			log.info("ACTION ENVIAR SOLICITUD PERITO---- ");
			Long solicitudPericialId= NumberUtils.toLong(request.getParameter("solicitudPericialId"));
			String idsPeritosString = request.getParameter("idsPeritos");
			log.info("ID DE LA SOLICITUD PERICIAL----"+solicitudPericialId);
			log.info("ID'S DE LOS PERITOS SELECCIONADOS----"+idsPeritosString);
			if(StringUtils.isNotBlank(idsPeritosString)){
				//consultar la solicitud pericial
				/*SolicitudPericialDTO solicitud = solicitudPericialDelegate.consultarSolicitudPericialPorId(solicitudPericialId);
				if(solicitud != null && idsPeritosString != null){
					log.info("NUMERO DE EXPEDIENTE ID ... " + solicitud.getExpedienteDTO().getNumeroExpedienteId());
					log.info("NUMERO DE EXPEDIENTE ... " + solicitud.getExpedienteDTO().getNumeroExpediente());
					log.info("FOLIO SOLICITUD ... " + solicitud.getFolioSolicitud());
					solicitud.setFechaModificacion(new Date());
					String[] idsPeritos = idsPeritosString.split(",");
					solicitud.setPeritosDesignados(new ArrayList<FuncionarioDTO>());
					for(String idPerito:idsPeritos){
						solicitud.getPeritosDesignados().add(new FuncionarioDTO(NumberUtils.toLong(idPerito)));
					}
					
					solicitudPericialDelegate.registrarActulizarSolicitudPericial(solicitud);
					escribirRespuesta(response,converter.toXML(idsPeritosString));
				}*/
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
}

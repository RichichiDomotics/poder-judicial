package mx.gob.segob.nsjp.web.visitaduria.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.catalogo.Catalogos;
import mx.gob.segob.nsjp.comun.enums.catalogo.TipoDiscriminante;
import mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.actividad.ConfActividadDocumentoDelegate;
import mx.gob.segob.nsjp.delegate.catalogo.CatUIEspecializadaDelegate;
import mx.gob.segob.nsjp.delegate.catalogo.DistritoDelegate;
import mx.gob.segob.nsjp.delegate.expediente.ExpedienteDelegate;
import mx.gob.segob.nsjp.delegate.funcionario.FuncionarioDelegate;
import mx.gob.segob.nsjp.delegate.solicitud.SolicitudPericialDelegate;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatUIEspecializadaDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.RelNumExpedienteAuditoriaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.institucion.JerarquiaOrganizacionalDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

public class VisitaduriaAction extends GenericAction{
	/* Log de clase*/
	private static final Logger log  = Logger.getLogger(VisitaduriaAction.class);
	
	@Autowired
	protected ConfActividadDocumentoDelegate confActividadDocumentoDelegate;
	
	@Autowired
	protected SolicitudPericialDelegate solicitudPericialDelegate;
	
	@Autowired
	protected FuncionarioDelegate funcionarioDelegate;
	
	@Autowired
	protected ExpedienteDelegate expedienteDelegate;
	
	@Autowired
	protected DistritoDelegate distritoDelegate;
	
	@Autowired
	protected CatUIEspecializadaDelegate catUIEDelegate;
	/**
	 * Método utilizado para generar el HTML de los departamentos en el menu izquierdo
	 * en el coordinador de visitaduria
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultaDepartamentosCarpetaDeAuditoria(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			
			log.info("ejecutando VisitaduriaAction - consultaDepartamentosCarpetaDeAuditoria");	
			Long[] idAreas = {Areas.ALMACEN.parseLong(),Areas.COORDINACION_POLICIA_MINISTERIAL.parseLong(), Areas.COORDINACION_VISITADURIA.parseLong(),Areas.COORDINACION_PERICIALES_PG.parseLong() };
		      List<Long> idsAreasADescartar = Arrays.asList(idAreas);
			Long[] idDepartamentos = {Areas.REGISTRO_INICIAL.parseLong()};
			List<Long> idsDepartamentosADescartar = Arrays.asList(idDepartamentos);
			List<JerarquiaOrganizacionalDTO> listaDeptosXInstitucion= catDelegate.consultarDepartamentosExceptoAreasYDepartamentos(null,idsAreasADescartar,idsDepartamentosADescartar); 
			
			//generamos la opcion de todas las areas y la insertamos en la lista
			JerarquiaOrganizacionalDTO nodoTodasLasAreas= new JerarquiaOrganizacionalDTO(0L,"Todas las Áreas");
			listaDeptosXInstitucion.add(0, nodoTodasLasAreas);
			
			converter.alias("listaDepartamentos", java.util.List.class);
			converter.alias("departamentos", JerarquiaOrganizacionalDTO.class);
			String xml = converter.toXML(listaDeptosXInstitucion);
			escribirRespuesta(response,xml);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}
	
	/**
	 * Método utilizado para realizar la consulta de departamentos por filtro
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarCatalogoDeDepartamentosXFiltro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar catalogos de departamentos x Filtro");	
			Long[] idAreas = {Areas.ALMACEN.parseLong(),Areas.COORDINACION_POLICIA_MINISTERIAL.parseLong(), Areas.COORDINACION_VISITADURIA.parseLong(),Areas.COORDINACION_PERICIALES_PG.parseLong() };
		      List<Long> idsAreasADescartar = Arrays.asList(idAreas);
			Long[] idDepartamentos = {Areas.REGISTRO_INICIAL.parseLong()};
			List<Long> idsDepartamentosADescartar = Arrays.asList(idDepartamentos);
			List<JerarquiaOrganizacionalDTO> listaDeptosXInstitucion= catDelegate.consultarDepartamentosExceptoAreasYDepartamentos(null,idsAreasADescartar,idsDepartamentosADescartar); 
			
			converter.alias("listaDepartamentos", java.util.List.class);
			converter.alias("departamentos", JerarquiaOrganizacionalDTO.class);
			String xml = converter.toXML(listaDeptosXInstitucion);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Método utilizado para realizar la consulta de estatus por departamento
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarEstadosPorDepartamento(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
	
			log.info("ejecutando Action consultar de estatus por departamento");
			//consultamos los estatus
			List<ValorDTO> listaEstatus= confActividadDocumentoDelegate.consultarEstadosPorJerarquiaOrganizacional(Areas.VISITADURIA.parseLong());
			converter.alias("listaEstatusDep", java.util.List.class);
			converter.alias("estatus", ValorDTO.class);
			String xml = converter.toXML(listaEstatus);
			if(log.isDebugEnabled())
			{
				log.info(xml);
			}
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirRespuesta(response, "");
		}
		return null;
	}
	
	/**
	 * Método utilizado para realizar la consulta de estatus por departamento
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarEstadosPorDepartamentoDinamico(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			String area = request.getParameter("area");
			log.info("ejecutando Action consultar de estatus por departamento");
			//consultamos los estatus
			List<ValorDTO> listaEstatus= confActividadDocumentoDelegate.consultarEstadosPorJerarquiaOrganizacional(Long.parseLong(area));
			converter.alias("listaEstatusDep", java.util.List.class);
			converter.alias("estatus", ValorDTO.class);
			String xml = converter.toXML(listaEstatus);
			if(log.isDebugEnabled())
			{
				log.info(xml);
			}
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirRespuesta(response, "");
		}
		return null;
	}
	
	/**
	 * funcion para consultar los visitadores
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultaVisitadores(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			String idDepartamento=request.getParameter("idDepartamento");
			String idEstatus=request.getParameter("idEstatus");
			log.info("ejecutando action VisitaduiraAction - consultaVisitadores :: "+ idDepartamento+","+idEstatus);

			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
				
			//List <FuncionarioDTO> listaFuncioanrios = funcionarioDelegate.consultarFuncionarioPorDepartamentoYArea(Long.parseLong(idDepartamento));
			List <FuncionarioDTO> listaFuncioanrios =null;
			if(Long.parseLong(idDepartamento)==0L)
			{
				listaFuncioanrios = expedienteDelegate.consultarFuncionariosPorEstatusDeparamento(Long.parseLong(idEstatus),null);
			}
			else
			{
				listaFuncioanrios = expedienteDelegate.consultarFuncionariosPorEstatusDeparamento(Long.parseLong(idEstatus),Long.parseLong(idDepartamento));
			}
			
			writer.print("<rows>");
			
			final PaginacionDTO pag = PaginacionThreadHolder.get();
            if (pag != null && pag.getTotalRegistros() != null) {
                writer.print("<total>" + pag.getTotalPaginas() + "</total>");
                writer.print("<records>" + pag.getTotalRegistros() + "</records>");
            } else {
                writer.print("<total>0</total>");
                writer.print("<records>0</records>");
            }	
			
			for (FuncionarioDTO funcionarioDTO : listaFuncioanrios) {
				writer.print("<row id='"+funcionarioDTO.getClaveFuncionario()+"'>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+funcionarioDTO.getNombreCompleto()+" </div>]]></cell>");
				writer.print("</row>");
			}
			writer.print("</rows>");
			writer.flush();
			writer.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}
	
	/**
	 * funcion para consultar los visitadores
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultaCarpetasAuditoria(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			String idDepartamento=request.getParameter("idDepartamento");
			String idEstatus=request.getParameter("idEstatus");
			log.info("ejecutando action VisitaduiraAction - consultaVisitadores :: "+ idDepartamento+","+idEstatus);

			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
				
			List <FuncionarioDTO> listaFuncioanrios = funcionarioDelegate.consultarFuncionarioPorDepartamentoYArea(Long.parseLong(idDepartamento));
			
			writer.print("<rows>");
			int lTotalRegistros=listaFuncioanrios.size();
			writer.print("<records>" + lTotalRegistros + "</records>");	
			
			for (FuncionarioDTO funcionarioDTO : listaFuncioanrios) {
				writer.print("<row id='"+funcionarioDTO.getClaveFuncionario()+"'>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+funcionarioDTO.getNombreCompleto()+" </div>]]></cell>");
				writer.print("</row>");
			}
			writer.print("</rows>");
			writer.flush();
			writer.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}
	
	
	/**
	 * funcion para consultar los expedientes de el autenticado visitadores
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultaexpedientesVisitadores(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			String idDepartamento=request.getParameter("idDepartamento");
			String idEstatus=request.getParameter("idEstatus");
			String idFuncionario=request.getParameter("idFuncionario");
			String opcionFuncionario=request.getParameter("opcionFuncionario");
			String opcionStatus=request.getParameter("opcionStatus");
			log.info("ejecutando action VisitaduiraAction - consultaexpedientesVisitadores :: "+ idDepartamento+","+idEstatus+","+idFuncionario+","+opcionFuncionario+","+opcionStatus);

			RelNumExpedienteAuditoriaDTO relNumExpedienteAuditoriaDTO=new RelNumExpedienteAuditoriaDTO();
			ExpedienteDTO auditadoExpedienteDTO=new ExpedienteDTO();
			ExpedienteDTO expeAuditado=new ExpedienteDTO();
			if(idDepartamento!= null && !idDepartamento.equals("")){
				if(!idDepartamento.equals("0")){
					expeAuditado.setArea(new AreaDTO(Long.parseLong(idDepartamento)));
				}
				
			}
			if(opcionFuncionario!=null && opcionFuncionario.equals("1")){
				UsuarioDTO usu=super.getUsuarioFirmado(request);
				auditadoExpedienteDTO.setUsuario(usu);
			}else{
				if(idFuncionario!= null && !idFuncionario.equals("")){
					UsuarioDTO usuarioDTO=new UsuarioDTO();
					usuarioDTO.setFuncionario(new FuncionarioDTO(Long.parseLong(idFuncionario)));
					auditadoExpedienteDTO.setUsuario(usuarioDTO);
				}
			}
			if(opcionStatus!=null && opcionStatus.equals("1")){
				if(idEstatus!= null && !idEstatus.equals("")){
					auditadoExpedienteDTO.setEstatus( new ValorDTO(EstatusExpediente.ABIERTO.getValorId()));
				}else{
					auditadoExpedienteDTO.setEstatus( new ValorDTO(EstatusExpediente.ABIERTO.getValorId()));
				}
			}else{
				if(idEstatus!= null && !idEstatus.equals("")){
					auditadoExpedienteDTO.setEstatus(new ValorDTO(Long.parseLong(idEstatus)));	
				}else{
					auditadoExpedienteDTO.setEstatus(null);	
				}
			}
			
			relNumExpedienteAuditoriaDTO.setNumeroExpediente(expeAuditado);
			relNumExpedienteAuditoriaDTO.setNumeroAuditoria(auditadoExpedienteDTO);
			log.info("ejecutando action VisitaduiraAction - consultaexpedientesVisitadores :: "+ relNumExpedienteAuditoriaDTO);
			List<RelNumExpedienteAuditoriaDTO> listRelNumExpedienteAuditoriaDTOs=expedienteDelegate.consultarNumeroVisitaduriaPorFiltro(relNumExpedienteAuditoriaDTO);
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
				
			
			
			writer.print("<rows>");
			int lTotalRegistros=listRelNumExpedienteAuditoriaDTOs.size();
			writer.print("<records>" + lTotalRegistros + "</records>");	
			log.info("ejecutando action VisitaduiraAction - consultaexpedientesVisitadores :: "+lTotalRegistros);
			
			for (RelNumExpedienteAuditoriaDTO relNumExpedienteAuditoriaDTO2 : listRelNumExpedienteAuditoriaDTOs) {
				log.info("ejecutando action VisitaduiraAction - consultaexpedientesVisitadores :: "+relNumExpedienteAuditoriaDTO2.getNumeroAuditoria().getNumeroExpediente());
				writer.print("<row id='"+relNumExpedienteAuditoriaDTO2.getNumeroAuditoria().getNumeroExpedienteId()+"'>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+relNumExpedienteAuditoriaDTO2.getNumeroAuditoria().getNumeroExpediente()+" </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+relNumExpedienteAuditoriaDTO2.getNumeroExpediente().getNumeroExpediente()+" </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+relNumExpedienteAuditoriaDTO2.getNumeroExpediente().getUsuario().getFuncionario().getNombreCompleto()+" </div>]]></cell>");	
				
				
				if(relNumExpedienteAuditoriaDTO2.getNumeroAuditoria().getDelitoPrincipal()!=null && relNumExpedienteAuditoriaDTO2.getNumeroAuditoria().getDelitoPrincipal().getCatDelitoDTO()!=null){
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+relNumExpedienteAuditoriaDTO2.getNumeroAuditoria().getDelitoPrincipal().getCatDelitoDTO().getNombre()+" </div>]]></cell>");	
				}else{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Sin Delito"+" </div>]]></cell>");
				}
				if(relNumExpedienteAuditoriaDTO2.getNumeroExpediente().getOrigen()!= null){
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+relNumExpedienteAuditoriaDTO2.getNumeroExpediente().getOrigen().getValor()+" </div>]]></cell>");	
				}else{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Denuncia"+" </div>]]></cell>");
				}
				
				if(relNumExpedienteAuditoriaDTO2.getNumeroAuditoria().getEstatus()!=null){
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+relNumExpedienteAuditoriaDTO2.getNumeroAuditoria().getEstatus().getValor()+" </div>]]></cell>");
				}else{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Abierto"+" </div>]]></cell>");
				}
				
				
				writer.print("</row>");
			}
			writer.print("</rows>");
			writer.flush();
			writer.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}
	
	
	/**
	 * Método utilizado para realizar la consulta de la informacion de resumen de visitaduria
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarInfoResumenVisitaduria(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando Action consultarInfoResumenVisitaduria");
			String idNumeroExpediente=request.getParameter("idExpediente");
			
			RelNumExpedienteAuditoriaDTO infoVisitaduriaDTO= expedienteDelegate.consultarRelacionPorIdAuditoria(Long.parseLong(idNumeroExpediente)); 
			
			//consultamos la informacion de detalle
			converter.alias("AuditoriaDTO", RelNumExpedienteAuditoriaDTO.class);
			String xml = converter.toXML(infoVisitaduriaDTO);
			if(log.isDebugEnabled())
			{
				log.info("infoVisitaduria idExpediente:: "+idNumeroExpediente);
				log.info("infoVisitaduria:: "+xml);
			}
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirRespuesta(response, "");
		}
		return null;
	}
	
	/**
	 * Método utilizado para consultar los tipos de las agencias de un distrito
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarDiscriminantesXDistrito(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

			log.info("EJECUTANDO ACTION CONSULTAR AGENCIAS DE DISTRITOS");		
			try {
				
				Long distritoId = NumberUtils.toLong(request.getParameter("distritoId"), 0L);
				
				List<CatDiscriminanteDTO> listaCatalogo = distritoDelegate
						.consultarDiscriminantesXDistrito(distritoId,TipoDiscriminante.AGENCIA);
				
				converter.alias("CatDiscriminanteDTO", java.util.List.class);
				converter.alias("catDiscriminanteDTO", CatDiscriminanteDTO.class);
				String xml = converter.toXML(listaCatalogo);
				response.setContentType("text/xml");
				PrintWriter pw = response.getWriter();
				pw.print(xml);
				pw.flush();
				pw.close();
			} catch (Exception e) {
				log.error(e.getMessage(), e);
}
			return null;
	}
	
	public ActionForward consultarCatUIE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		
		log.info("/**** ACTION PARA ONSULTAR CAT ****/");
		try {
			List<CatUIEspecializadaDTO> catUIEList = catUIEDelegate.consultarUnidadesIEspecializadas();
			converter.alias("consultarEstadosPorDepartamento", java.util.List.class);
			converter.alias("unidad", CatUIEspecializadaDTO.class);						
			
			String xml = converter.toXML(catUIEList);
			log.info(" XML :: "+xml);
			escribirRespuesta(response, xml);
		} catch (NSJPNegocioException e) {
			log.error(e.getMessage(), e);			
		}
		
		return null;
	}
	
	/**
	 * funcion para consultar los expedientes de el autenticado visitadores de UIE
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultaExpedientesVisitadoresUIE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			String idDepartamento=request.getParameter("idDepartamento");
			String idEstatus=request.getParameter("idEstatus");
			
			log.info("ejecutando action VisitaduiraAction - consultaexpedientesVisitadoresUIE :: "+ idDepartamento+", "+idEstatus);

			RelNumExpedienteAuditoriaDTO relNumExpedienteAuditoriaDTO=new RelNumExpedienteAuditoriaDTO(); 
			ExpedienteDTO expeAuditado=new ExpedienteDTO();
			if(idDepartamento!= null && !idDepartamento.equals("")){				
				expeAuditado.setArea(new AreaDTO(Long.parseLong(idDepartamento)));						
			}
			if (idEstatus!=null && !idEstatus.isEmpty()) {
				expeAuditado.setEstatus(new ValorDTO(Long.parseLong(idEstatus)));
			}
			
			relNumExpedienteAuditoriaDTO.setNumeroExpediente(expeAuditado);
						
			List<RelNumExpedienteAuditoriaDTO> listRelNumExpedienteAuditoriaDTOs=expedienteDelegate.consultarNumeroVisitaduriaUIE(relNumExpedienteAuditoriaDTO);
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
				
			if (listRelNumExpedienteAuditoriaDTOs==null || listRelNumExpedienteAuditoriaDTOs.isEmpty()) {
				return null;
			}
			
			writer.print("<rows>");
			int lTotalRegistros=listRelNumExpedienteAuditoriaDTOs.size();
			writer.print("<records>" + lTotalRegistros + "</records>");	
			log.info("ejecutando action VisitaduiraAction - consultaexpedientesVisitadores :: "+lTotalRegistros);
			
			for (RelNumExpedienteAuditoriaDTO relNumExpedienteAuditoriaDTO2 : listRelNumExpedienteAuditoriaDTOs) {
				log.info("ejecutando action VisitaduiraAction - consultaexpedientesVisitadores :: "+relNumExpedienteAuditoriaDTO2.getNumeroAuditoria().getNumeroExpediente());
				writer.print("<row id='"+relNumExpedienteAuditoriaDTO2.getNumeroAuditoria().getNumeroExpedienteId()+"'>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+relNumExpedienteAuditoriaDTO2.getNumeroAuditoria().getNumeroExpediente()+" </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+relNumExpedienteAuditoriaDTO2.getNumeroExpediente().getNumeroExpediente()+" </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+relNumExpedienteAuditoriaDTO2.getNumeroExpediente().getUsuario().getFuncionario().getNombreCompleto()+" </div>]]></cell>");	
				
				
				if(relNumExpedienteAuditoriaDTO2.getNumeroAuditoria().getDelitoPrincipal()!=null && relNumExpedienteAuditoriaDTO2.getNumeroAuditoria().getDelitoPrincipal().getCatDelitoDTO()!=null){
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+relNumExpedienteAuditoriaDTO2.getNumeroAuditoria().getDelitoPrincipal().getCatDelitoDTO().getNombre()+" </div>]]></cell>");	
				}else{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Sin Delito"+" </div>]]></cell>");
				}
				if(relNumExpedienteAuditoriaDTO2.getNumeroExpediente().getOrigen()!= null){
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+relNumExpedienteAuditoriaDTO2.getNumeroExpediente().getOrigen().getValor()+" </div>]]></cell>");	
				}else{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Denuncia"+" </div>]]></cell>");
				}
				
				if(relNumExpedienteAuditoriaDTO2.getNumeroAuditoria().getEstatus()!=null){
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+relNumExpedienteAuditoriaDTO2.getNumeroAuditoria().getEstatus().getValor()+" </div>]]></cell>");
				}else{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"Abierto"+" </div>]]></cell>");
				}
				
				
				writer.print("</row>");
			}
			writer.print("</rows>");
			writer.flush();
			writer.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}
}

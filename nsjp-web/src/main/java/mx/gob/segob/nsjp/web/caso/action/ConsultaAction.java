/**
* Nombre del Programa : ConsultaAction.java
* Autor                            : AlejandroGA
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 18 Apr 2011
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
package mx.gob.segob.nsjp.web.caso.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.actividad.Actividades;
import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.expediente.OrigenExpediente;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.delegate.actividad.ActividadDelegate;
import mx.gob.segob.nsjp.delegate.expediente.ExpedienteDelegate;
import mx.gob.segob.nsjp.delegate.involucrado.InvolucradoDelegate;
import mx.gob.segob.nsjp.delegate.persona.PersonaDelegate;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.domicilio.LugarDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.elemento.ElementoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.TurnoDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.institucion.DepartamentoDTO;
import mx.gob.segob.nsjp.dto.involucrado.AliasInvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.BiometricoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.MediaFiliacionDTO;
import mx.gob.segob.nsjp.dto.organizacion.OrganizacionDTO;
import mx.gob.segob.nsjp.dto.persona.MedioDeContactoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.dto.persona.PersonaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AlejandroGA
 *
 */
public class ConsultaAction extends GenericAction{
	
	/*Log de clase*/
	private static final Logger log  = Logger.getLogger(ConsultaAction.class);
	
	@Autowired
	InvolucradoDelegate involucradoDelegate;
	
	@Autowired
	ExpedienteDelegate expedienteDelegate;
	@Autowired
	ActividadDelegate actividadDelegate;
	@Autowired
	PersonaDelegate personaDelegate;
	
	public ActionForward acarrear(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String idInvolucrado = request.getParameter("idInvolucrado");
		String idCalidad = request.getParameter("idCalidad");
		log.info("idInvolucrado :: [" + idInvolucrado+"]");
		request.setAttribute("idInvolucrado", idInvolucrado);
		log.info("idCalidad :: [" + idCalidad+"]");
		request.setAttribute("idCalidad", idCalidad);
		if (idCalidad.equalsIgnoreCase("PROBABLE_RESPONSABLE")) {
			return mapping.findForward("PROBABLE_RESPONSABLE");
		}else if(idCalidad.equalsIgnoreCase("CONTACTO_ORGANIZACION")){
			return mapping.findForward("CONTACTO_ORGANIZACION");
		}else if(idCalidad.equalsIgnoreCase("DENUNCIANTE")){
			return mapping.findForward("DENUNCIANTE");
		}else if(idCalidad.equalsIgnoreCase("VICTIMA")){
			return mapping.findForward("VICTIMA");
		}else if(idCalidad.equalsIgnoreCase("TESTIGO")){
			return mapping.findForward("TESTIGO");
		}else if(idCalidad.equalsIgnoreCase("REPRESENTANTE_LEGAL")){
			return mapping.findForward("REPRESENTANTE_LEGAL");
		}else if(idCalidad.equalsIgnoreCase("TUTOR")){
			return mapping.findForward("TUTOR");
		}else if(idCalidad.equalsIgnoreCase("TRADUCTOR")){
			return mapping.findForward("TRADUCTOR");
		}else{ 
			return mapping.findForward("Fail");
		}
		
	}
	
	/**
	 * Metodo utilizado para obtener los datos del Involucrado
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 
	 */
	//Nota: Revisar Metodo, al parecer no se utiliza por contener datos en duro  ----05072011-----
	public ActionForward buscarInvolucrado(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			//ExpedienteDTO expedienteSession = (ExpedienteDTO)request.getSession().getAttribute("expediente");
			//log.info("expedienteSession:" + expedienteSession);
			log.info("***********************Ejecutando Action buscarInvolucrado***************************************+");
			Long idinvol=Long.parseLong(request.getParameter("idInvolucrado"));
			log.info("idInvolucrado:" + idinvol);
//			String idCalidad=request.getParameter("idCalidad");
			InvolucradoDTO involucrado=new InvolucradoDTO();
			involucrado.setElementoId(idinvol);
			CalidadDTO calidadDTO=new CalidadDTO();
			calidadDTO.setCalidades(Calidades.PROBABLE_RESPONSABLE_PERSONA);
			involucrado.setCalidadDTO(calidadDTO);
			ExpedienteDTO expedienteDTO=new ExpedienteDTO();
			expedienteDTO.setExpedienteId((long)1);
			involucrado.setExpedienteDTO(expedienteDTO);  
			InvolucradoDTO involucradoDTO=involucradoDelegate.obtenerInvolucrado(involucrado);
			List<NombreDemograficoDTO> lstNombresDemograficos = involucradoDTO.getNombresDemograficoDTO();
			for (NombreDemograficoDTO nombreDemograficoDTO : lstNombresDemograficos) {
				log.info("Nombre: " + nombreDemograficoDTO.getNombre() + " " + nombreDemograficoDTO.getApellidoPaterno());
			}
			
			//Este codigo se descomenta cuando pasen bien las calidades
//			if(idCalidad.equals("PROBABLE_RESPONSABLE_PERSONA")){
//				request.getSession().setAttribute("narrativa", involucradoDTO.getExpedienteDTO().getNarrativa());
//			}
			
			ExpedienteDTO expedienteDTO2=involucradoDTO.getExpedienteDTO();
			if(expedienteDTO2.getStrFechaApertura()!=null)
				expedienteDTO2.setStrFechaApertura(expedienteDTO2.getStrFechaApertura());
			if(expedienteDTO2.getStrFechaCierre()!=null)
				expedienteDTO2.setStrFechaCierre(expedienteDTO2.getStrFechaCierre()); 
			if(expedienteDTO2.getStrHoraApertura()!=null)
				expedienteDTO2.setStrHoraApertura(expedienteDTO2.getStrHoraApertura());
			if(expedienteDTO2.getStrHoraCierre()!=null)
				expedienteDTO2.setStrHoraCierre(expedienteDTO2.getStrHoraCierre());
			
			involucradoDTO.setExpedienteDTO(expedienteDTO2);
			
			if (log.isDebugEnabled()) {
				log.debug("CARGA INVOLUCRADO:::::::::" + involucradoDTO);
			}
			log.info("Ejecutando Action buscarDenunciante mas denunciante:"+involucradoDelegate);
			//Codigo se elimina cuando se pasen bien las calidades
			request.getSession().setAttribute("narrativa", involucradoDTO.getExpedienteDTO().getNarrativa());
			converter.alias("calidadDTO", CalidadDTO.class);
			converter.alias("valorDTO", ValorDTO.class);
			converter.alias("medioDeContactoDTO", MedioDeContactoDTO.class);
			converter.alias("nombreDemograficoDTO", NombreDemograficoDTO.class);
			converter.alias("elementoDTO", ElementoDTO.class);
			converter.alias("personaDTO", PersonaDTO.class);
			converter.alias("involucradoDTO", InvolucradoDTO.class);
			converter.alias("expedienteDTO", ExpedienteDTO.class);
			converter.alias("domicilioDTO", DomicilioDTO.class);
			converter.alias("aliasInvolucradoDTO", AliasInvolucradoDTO.class);
			converter.alias("biometricoDTO", BiometricoDTO.class);
			converter.alias("lugarDTO", LugarDTO.class);
			converter.alias("organizacionDTO", OrganizacionDTO.class);
			converter.alias("mediaFiliacionDTO", MediaFiliacionDTO.class);
			
			String xml = converter.toXML(involucradoDTO);
			
			response.setContentType("text/xml");
			log.info("xml CONSULTA ACTION ----- : " + xml);
			
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();	
			
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
	}
	
	/**
	 * Metodo utilizado para obtener los presuntos responsables con calidad de detenidos
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 
	 */
	public ActionForward buscarIndividuosConCalidadDetenidos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			log.info("***********************Ejecutando Action buscarIndividuosConCalidadDetenidos***************************************+");
			String numeroExpediente=request.getParameter("numeroExpediente");
			
			log.info("numeroExpediente buscarIndividuosConCalidadDetenidos :" + numeroExpediente);
			ExpedienteDTO expediente = super.getExpedienteTrabajo(request,numeroExpediente);
			log.info("IdExpediente buscarIndividuosConCalidadDetenidos :::" + expediente.getExpedienteId());
			UsuarioDTO usuario = super.getUsuarioFirmado(request);
			log.info("Expediente:::" + expediente);
			log.info("Ususario:::" + usuario);
			List<InvolucradoDTO> lstInvolucrados = involucradoDelegate.consultarInvolucradoExpediente(expediente, Calidades.PROBABLE_RESPONSABLE_PERSONA,usuario);
			log.info("lstInvolucrados RESP SIZE :::" + lstInvolucrados.size());

			List<InvolucradoDTO> lstInvolucradosDetenidos = new ArrayList<InvolucradoDTO>();
			
			for(InvolucradoDTO involucradoDTO : lstInvolucrados){
				if(involucradoDTO.getEsDetenido()){
					lstInvolucradosDetenidos.add(involucradoDTO);
				}
				
			}
			
			converter.alias("lstInvolucradosDetenidos", java.util.List.class);
			converter.alias("involucrado", InvolucradoDTO.class);
			converter.alias("nombreDemograficoDTO", NombreDemograficoDTO.class);
			
			String xml = converter.toXML(lstInvolucradosDetenidos);
			
			response.setContentType("text/xml");
			
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();	
			
		}
		catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
			log.error(e);
			
		}
		return null;
	}

	
	public ActionForward nuevaDenuncia(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		log.info("********Ejecutando Action nueva denuncia*********************************+");
		
		
		String idNuevaDenuncia = request.getParameter("idNuevaDenuncia");
		String idExpediente=request.getParameter("idExpediente");
		String numeroExpediente=request.getParameter("numeroExpediente");
		String ingresoDenuncia=request.getParameter("ingresoDenuncia");
		String respuestaPenal=request.getParameter("abreenPenal");
		
		log.info("idNuevaDenuncia :: [" + idNuevaDenuncia+"]");
		log.info("idExpediente :: [" + idExpediente+"]");
		log.info("numeroExpediente :: [" + numeroExpediente+"]");
		log.info("ingresoDenuncia :::::::::::::: [" + ingresoDenuncia+"]");
		log.info("respuestaPenal :::::::::::::: [" + respuestaPenal+"]");
		
		request.setAttribute("idNuevaDenuncia", idNuevaDenuncia);
		request.setAttribute("respuestaPenal", respuestaPenal);
		request.setAttribute("numeroExpediente", numeroExpediente);
		
			return mapping.findForward("succes");
	}
	
	public ActionForward nuevoExpediente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			
			log.info("ejecutando Action consultar Action en el metodo nuevoExpediente");
			ExpedienteDTO expedienteDTO=new ExpedienteDTO();
//			expedienteDTO.setArea(new AreaDTO(Areas.ATENCION_TEMPRANA_PG));
			expedienteDTO.setFechaApertura(new Date());
			TurnoDTO turno=new TurnoDTO();
			String idTurno=request.getParameter("turno");
			log.info("#########################************************************** Metodo nuevoExpediente el id del turno es: "+idTurno);
			turno.setTurnoId(Long.parseLong(idTurno));
			UsuarioDTO usuario=super.getUsuarioFirmado(request);
			expedienteDTO.setArea(usuario.getAreaActual());
			turno.setExpediente(expedienteDTO);
			turno.setUsuario(usuario);
			expedienteDTO = expedienteDelegate.asignarNumeroExpedientePenal(turno);
			expedienteDTO.setConsulta(false);
			request.getSession().setAttribute("numeroExpediente", expedienteDTO.getNumeroExpediente());
			log.info("************************************** Metodo nuevoExpediente el id del numero de expediente  es: "+expedienteDTO.getNumeroExpediente());
			log.info("************************************** Metodo nuevoExpediente el id del numero de expediente  es: "+expedienteDTO.getExpedienteId());
			super.setExpedienteTrabajo(request, expedienteDTO);
			converter.alias("expedienteDTO", ExpedienteDTO.class);
			String xml = converter.toXML(expedienteDTO);
			//super.escribirRespuesta(response, xml);
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

	public ActionForward nuevoExpedienteTempranaAdministrativa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			ExpedienteDTO expedienteDTO=new ExpedienteDTO();
			expedienteDTO.setArea(new AreaDTO(Areas.ATENCION_TEMPRANA_PG_NO_PENAL));
			expedienteDTO.setFechaApertura(new Date());
			TurnoDTO turno=new TurnoDTO();
			String idTurno=request.getParameter("turnoTempAdmin");
			turno.setTurnoId(Long.parseLong(idTurno));
			turno.setExpediente(expedienteDTO);
			turno.setUsuario(super.getUsuarioFirmado(request));
			expedienteDTO = expedienteDelegate.asignarNumeroExpediente(turno);
			expedienteDTO.setConsulta(false);
			super.setExpedienteTrabajo(request, expedienteDTO);
			converter.alias("expedienteDTO", ExpedienteDTO.class);
			String xml = converter.toXML(expedienteDTO);
			request.getSession().setAttribute("numeroExpedienteTempAdmin", expedienteDTO.getNumeroExpediente());
			request.getSession().setAttribute("numeroUnicoExpediente", expedienteDTO.getNumeroExpediente());
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

	public ActionForward nuevaDenunciaTempranaAdministrativa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		log.info("********Ejecutando Action nueva denuncia temprana administrativa *************************+");
		
		//idNumeroExpedienteopTempAdmin='+numeroExpedienteIdTempAdmin+'&
		//idExpedienteopTempAdmin='+idExpedienteTempAdmin+'
			
		String idNuevaDenunciaTempAdmin = request.getParameter("idNuevaDenunciaTempAdmin");
		String idExpedienteTempAdmin=request.getParameter("idExpedienteTempAdmin");
		String numeroExpedienteTempAdmin=request.getParameter("numeroExpedienteTempAdmin");
		String ingresoDenunciaTempAdmin=request.getParameter("ingresoDenunciaTempAdmin");
		String respuestaPenalTempAdmin=request.getParameter("abreenPenalTempAdmin");
		
		log.info("idNuevaDenunciaTempAdmin :: [" + idNuevaDenunciaTempAdmin+"]");
		log.info("idExpedienteTempAdmin :: [" + idExpedienteTempAdmin+"]");
		log.info("numeroExpedienteTempAdmin :: [" + numeroExpedienteTempAdmin+"]");
		log.info("ingresoDenunciaTempAdmin :::::::::::::: [" + ingresoDenunciaTempAdmin+"]");
		log.info("respuestaPenalTempAdmin :::::::::::::: [" + respuestaPenalTempAdmin+"]");
		
		request.setAttribute("idNuevaDenunciaTempAdmin", idNuevaDenunciaTempAdmin);
		request.setAttribute("respuestaPenalTempAdmin", respuestaPenalTempAdmin);
		request.setAttribute("numeroExpedienteTempAdmin", numeroExpedienteTempAdmin);
		
		request.setAttribute("idNuevaDenuncia", idNuevaDenunciaTempAdmin);
		request.setAttribute("respuestaPenal", respuestaPenalTempAdmin);
		request.setAttribute("numeroUnicoExpediente", numeroExpedienteTempAdmin);
		
		return mapping.findForward("succes");
	}
	
	
	public ActionForward nuevoNumeroExpediente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			
			log.info("ejecutando Action consultar Action en el metodo nuevoNumeroExpediente");
			String idExpediente=request.getParameter("idExpediente");
			String idArea=request.getParameter("idArea");
			log.info("ejecutando Action consultar Action en el metodo nuevoNumeroExpediente el numero de expediente es :"+idExpediente);
			ExpedienteDTO expedienteDTO=new ExpedienteDTO();
			if(idArea!=null && !idArea.equals("")){
				expedienteDTO.setArea(new AreaDTO(Long.parseLong(idArea)));
			}else{
				expedienteDTO.setArea(new AreaDTO(Areas.ATENCION_TEMPRANA_PG_PENAL));
			}
			
			expedienteDTO.setFechaApertura(new Date());
			if(idExpediente!=null && !idExpediente.equals("")){
				expedienteDTO.setExpedienteId(Long.parseLong(idExpediente));
			}
			log.info("************************************** Metodo nuevoNumeroExpediente el id del numero de expediente antiguo es: "+expedienteDTO.getNumeroExpedienteId());
			UsuarioDTO usuarioDTO=super.getUsuarioFirmado(request);
			expedienteDTO.setUsuario(usuarioDTO);
			expedienteDTO=expedienteDelegate.asignarNumeroExpediente(expedienteDTO);
			expedienteDTO.setConsulta(true);
			request.getSession().setAttribute("numeroExpediente", expedienteDTO.getNumeroExpediente());
			log.info("************************************** Metodo nuevoNumeroExpediente el id del numero de expediente  es: "+expedienteDTO.getNumeroExpediente());
			log.info("************************************** Metodo nuevoNumeroExpediente el id del numero de expediente nuevo es: "+expedienteDTO.getNumeroExpedienteId());
			super.setExpedienteTrabajo(request, expedienteDTO);
			converter.alias("expedienteDTO", ExpedienteDTO.class);
			String xml = converter.toXML(expedienteDTO);
			//super.escribirRespuesta(response, xml);
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
	
	public ActionForward nuevosNumerosExpedienteVisitaduria(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			
			log.info("ejecutando Action consultar Action en el metodo nuevosNumerosExpedienteVisitaduria");
			String idsExpediente=request.getParameter("idExpediente");
			log.info("ejecutando Action consultar Action en el metodo nuevosNumerosExpedienteVisitaduria los numeros expedientes son :"+idsExpediente);
			String idVisitador=request.getParameter("idVisitador");
			log.info("ejecutando Action consultar Action en el metodo nuevosNumerosExpedienteVisitaduria visitadorID:"+idVisitador);
			String tipoVisita=request.getParameter("tipoVisita");
			log.info("ejecutando Action consultar Action en el metodo nuevosNumerosExpedienteVisitaduria tipoVisita:"+tipoVisita);
			UsuarioDTO usuarioDTO=super.getUsuarioFirmado(request);
			if(idVisitador!=null && !idVisitador.equals("")){
				usuarioDTO.getFuncionario().setClaveFuncionario(Long.parseLong(idVisitador));
			}
			ValorDTO valorDTO = new ValorDTO();
			if(tipoVisita!=null && !tipoVisita.equals("")){
				valorDTO.setIdCampo(Long.parseLong(tipoVisita));
			}
			List<ExpedienteDTO> listExpedientes=new ArrayList<ExpedienteDTO>();
			String[] idsExpedientes = idsExpediente.split(",");
			for (int i = 0; i < idsExpedientes.length; i++) {
				if(idsExpedientes[i]!=null && !idsExpedientes[i].equals("")){
					Long idExpediente = Long.parseLong(idsExpedientes[i]);
					log.info("************************************** Metodo nuevosNumerosExpedienteVisitaduria ids entrada: "+idExpediente);
					ExpedienteDTO expedienteDTO=new ExpedienteDTO();
					expedienteDTO.setArea(new AreaDTO(Areas.VISITADURIA));
					expedienteDTO.setFechaApertura(new Date());
					expedienteDTO.setNumeroExpedienteId(idExpediente);
					expedienteDTO.setUsuario(usuarioDTO);
					if(valorDTO != null){
						expedienteDTO.setTipoExpediente(valorDTO);
					}
					listExpedientes.add(expedienteDTO);
				}
			}
			
			List<ExpedienteDTO> listaExpedientesVisitaduria=expedienteDelegate.asignarNumeroExpedienteAuditoria(listExpedientes);
			for (ExpedienteDTO expedienteDTO2 : listaExpedientesVisitaduria) {
				request.getSession().setAttribute("numeroExpediente", expedienteDTO2.getNumeroExpediente());
				super.setExpedienteTrabajo(request, expedienteDTO2);
				log.info("************************************** Metodo nuevosNumerosExpedienteVisitaduria el id del numero de expediente  es: "+expedienteDTO2.getNumeroExpediente());
				log.info("************************************** Metodo nuevosNumerosExpedienteVisitaduria el id del numero de expediente nuevo es: "+expedienteDTO2.getNumeroExpedienteId());

			}
			
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
				
			
			
			writer.print("<rows>");
			int lTotalRegistros=listaExpedientesVisitaduria.size();
			writer.print("<records>" + lTotalRegistros + "</records>");	
			log.info("ejecutando action VisitaduiraAction - consultaexpedientesVisitadores :: "+lTotalRegistros);
			
			for (ExpedienteDTO expedienteDTO : listaExpedientesVisitaduria) {
				writer.print("<row id='"+expedienteDTO.getNumeroExpedienteId()+"'>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expedienteDTO.getNumeroExpediente()+" </div>]]></cell>");
				String numexp=expedienteDTO.getNumeroExpediente().toString();
				String idexp=expedienteDTO.getExpedienteId().toString();
				
				if(expedienteDTO.getTipoExpediente() != null && expedienteDTO.getTipoExpediente().getValor() != null){
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expedienteDTO.getTipoExpediente().getValor()+" </div>]]></cell>");
				}
				else{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ " " +" </div>]]></cell>");
				}
				
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+"<input id='btnElabora"+idexp+"' type='button' class='btn_grande' onclick='desabilitarBoton(\"btnElabora"+idexp+"\");elaboraNotificacionAuditoria(\""+numexp+"\")' value='Enviar Notificaci�n' style='height:20px;width:100px;font-size:-3'/>"+" </div>]]></cell>");
				writer.print("</row>");
			}
			writer.print("</rows>");
			writer.flush();
			writer.close();
			
			
			
//			
//			converter.alias("listaExpedientesVisitaduria", ArrayList.class);
//			converter.alias("expedienteDTO", ExpedienteDTO.class);
//			String xml = converter.toXML(listaExpedientesVisitaduria);
//			super.escribirRespuesta(response, xml);
//			response.setContentType("text/xml");
//			PrintWriter pw = response.getWriter();
//			pw.print(xml);
//			pw.flush();
//			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	public ActionForward nuevoExpedienteUI(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			
			log.info("ejecutando Action consultar Action en el metodo nuevoExpedienteUI");
			ExpedienteDTO expedienteDTO=new ExpedienteDTO();
//			expedienteDTO.setArea(new AreaDTO(Areas.UNIDAD_INVESTIGACION));
			expedienteDTO.setFechaApertura(new Date());
			UsuarioDTO usuario=super.getUsuarioFirmado(request);
			expedienteDTO.setArea(usuario.getAreaActual());
			usuario.getFuncionario().setDepartamento(new DepartamentoDTO(usuario.getFuncionario().getJerarquiaOrganizacional().getJerarquiaOrganizacionalId(),null));
			usuario.getFuncionario().getDepartamento().setArea(new AreaDTO(usuario.getFuncionario().getJerarquiaOrganizacional().getJerarquiaOrganizacionalId()));
			expedienteDTO.setUsuario(usuario);
			expedienteDTO = expedienteDelegate.generarExpediente(expedienteDTO);
			expedienteDTO.setConsulta(false);
			request.getSession().setAttribute("numeroExpediente", expedienteDTO.getNumeroExpediente());
			log.info("************************************** Metodo nuevoExpedienteUI el id del numero de expediente  es: "+expedienteDTO.getNumeroExpediente());
			log.info("************************************** Metodo nuevoExpedienteUI el id del numero de expediente  es: "+expedienteDTO.getExpedienteId());
			super.setExpedienteTrabajo(request, expedienteDTO);
			actividadDelegate.registrarActividad(expedienteDTO, usuario.getFuncionario(), Actividades.ATENDER_CANALIZACION_UI.getValorId());
			
			String incompetencia = request.getParameter("activarIncompetencia");
			log.info("$$$$$$$$$$$$$$$$$$$$$$$$  activarIncompetencia es "+incompetencia);
			if( incompetencia != null && incompetencia.equals("true")){
				expedienteDelegate.actualizarTipoExpediente( expedienteDTO, OrigenExpediente.INCOMPETENCIA);
			}
			
			converter.alias("expedienteDTO", ExpedienteDTO.class);
			String xml = converter.toXML(expedienteDTO);
			//super.escribirRespuesta(response, xml);
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
	
	public ActionForward buscarPersona(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
            log.info("***********************Ejecutando Action buscarPersona*************************************");
            Long idinvol=Long.parseLong(request.getParameter("idInvolucrado"));
            log.info("idInvolucrado:::::::::::: " + idinvol);

            PersonaDTO persona=new PersonaDTO();
            persona.setElementoId(idinvol);
            log.info("Persona:::::::::::::: "+persona);

//            CalidadDTO calidadDTO=new CalidadDTO();
//            calidadDTO.setCalidades(Calidades.PROBABLE_RESPONSABLE_PERSONA);
//            persona.setCalidadDTO(calidadDTO);
//            log.info("CAlidadddd::::: "+calidadDTO);            

//          ExpedienteDTO expedienteDTO=new ExpedienteDTO();
//          expedienteDTO.setExpedienteId((long)1);
//          persona.setExpedienteDTO(expedienteDTO);

            PersonaDTO personaDTO = personaDelegate.consultaPersonaPorID(persona);            
            log.info("/**** Se obtuvo la persona en el Action ****/");
            
            List<NombreDemograficoDTO> lstNombresDemograficos = personaDTO.getNombresDemograficoDTO();
            for (NombreDemograficoDTO nombreDemograficoDTO : lstNombresDemograficos) {
                  log.info("Nombre: " + nombreDemograficoDTO.getNombre() + " " + nombreDemograficoDTO.getApellidoPaterno());
            }                

            //Este codigo se descomenta cuando pasen bien las calidades

//          if(idCalidad.equals("PROBABLE_RESPONSABLE_PERSONA")){

//                request.getSession().setAttribute("narrativa", involucradoDTO.getExpedienteDTO().getNarrativa());

//          }                

            ExpedienteDTO expedienteDTO2=personaDTO.getExpedienteDTO();

            if(expedienteDTO2.getStrFechaApertura()!=null)

                  expedienteDTO2.setStrFechaApertura(expedienteDTO2.getStrFechaApertura());

            if(expedienteDTO2.getStrFechaCierre()!=null)

                  expedienteDTO2.setStrFechaCierre(expedienteDTO2.getStrFechaCierre());

            if(expedienteDTO2.getStrHoraApertura()!=null)

                  expedienteDTO2.setStrHoraApertura(expedienteDTO2.getStrHoraApertura());

            if(expedienteDTO2.getStrHoraCierre()!=null)

                  expedienteDTO2.setStrHoraCierre(expedienteDTO2.getStrHoraCierre());

           

            personaDTO.setExpedienteDTO(expedienteDTO2);

           

            if (log.isDebugEnabled()) {

                  log.debug("CARGA PERSONA::::::::: " + personaDTO);

            }

            log.info("Ejecutando Action buscarDenunciante mas denunciante:"+personaDelegate);

            //Codigo se elimina cuando se pasen bien las calidades

            request.getSession().setAttribute("narrativa", personaDTO.getExpedienteDTO().getNarrativa());

            converter.alias("calidadDTO", CalidadDTO.class);

            converter.alias("valorDTO", ValorDTO.class);

            converter.alias("medioDeContactoDTO", MedioDeContactoDTO.class);

            converter.alias("nombreDemograficoDTO", NombreDemograficoDTO.class);

            converter.alias("elementoDTO", ElementoDTO.class);

            converter.alias("personaDTO", PersonaDTO.class);

            converter.alias("involucradoDTO", InvolucradoDTO.class);

            converter.alias("expedienteDTO", ExpedienteDTO.class);

            converter.alias("domicilioDTO", DomicilioDTO.class);

            converter.alias("aliasInvolucradoDTO", AliasInvolucradoDTO.class);

            converter.alias("biometricoDTO", BiometricoDTO.class);

            converter.alias("lugarDTO", LugarDTO.class);

            converter.alias("organizacionDTO", OrganizacionDTO.class);

            converter.alias("mediaFiliacionDTO", MediaFiliacionDTO.class);

           

            String xml = converter.toXML(personaDTO);                 

            response.setContentType("text/xml");

            log.info("xml CONSULTA ACTION PERSONA -----> : " + xml);              
            PrintWriter pw = response.getWriter();
            pw.print(xml);
            pw.flush();
            pw.close();

      }

      catch (Exception e) {

            e.printStackTrace();

            log.error(e);

      }

      return null;

}
	
	
}

package mx.gob.segob.nsjp.web.uavd.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.actividad.Actividades;
import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.catalogo.Catalogos;
import mx.gob.segob.nsjp.comun.enums.funcionario.Puestos;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.enums.sesion.TipoSesion;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.delegate.catalogo.CatalogoDelegate;
import mx.gob.segob.nsjp.delegate.expediente.ExpedienteDelegate;
import mx.gob.segob.nsjp.delegate.familiar.FamiliarDelegate;
import mx.gob.segob.nsjp.delegate.funcionario.FuncionarioDelegate;
import mx.gob.segob.nsjp.delegate.relacion.RelacionDelegate;
import mx.gob.segob.nsjp.delegate.sesion.SesionDelegate;
import mx.gob.segob.nsjp.delegate.solicitud.SolicitudDelegate;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.DatosGeneralesExpedienteUAVDDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.FiltroExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.relacion.CatRelacionDTO;
import mx.gob.segob.nsjp.dto.sesion.EntrevistaComplementariaDTO;
import mx.gob.segob.nsjp.dto.sesion.EntrevistaInicialDTO;
import mx.gob.segob.nsjp.dto.sesion.FamiliarDTO;
import mx.gob.segob.nsjp.dto.sesion.NotaEvolucionDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

public class UnidadAtencionVictimasDelitoAction extends GenericAction{
	/* Log de clase*/
	private static final Logger log  = Logger.getLogger(UnidadAtencionVictimasDelitoAction.class);
	
	@Autowired
	private CatalogoDelegate catalogoDelegate;
	
	@Autowired
	private FuncionarioDelegate funcionarioDelegate;
	
	@Autowired
	private SolicitudDelegate solicitudDelegate;
	
	@Autowired
	private ExpedienteDelegate expedienteDlegate;
	
	@Autowired
	private SesionDelegate sesionDelegate;
	
	@Autowired
	private FamiliarDelegate familiarDelegate;
	
	@Autowired
	private RelacionDelegate relacionDelegate;
	
	/***
	 * Funcion para regresar el contenido del catalogo de tipo de ayuda para 
	 * UAVD
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarCatalogoTipoAyuda(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
    	try {
    		log.info("ejecutando Action - consultarCatalogoTipoAyuda"); 
    		List<CatalogoDTO> listaCatalogo=catalogoDelegate.recuperarCatalogo(Catalogos.TIPO_ELEMENTO);
    		converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catElemento", CatalogoDTO.class);
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
			}
		catch (Exception e) {
			log.info(e);
		}
		return null;
	}
	
	/***
	 * Funcion para llenar el grid de destinatario para la solicitud de la ayuda psicologica
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward cargaDestinatarioSolicitudAyudaPsicologica(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando UnidadAtencionVictimasDelitoAction - cargaDestinatarioSolicitudAyudaPsicologica");
			//List<FuncionarioDTO> listaFuncionario=funcionarioDelegate.consultarFuncionariosPorRolMultiRol(Roles.UAVD.getValorId());
			List<FuncionarioDTO> listaFuncionario=funcionarioDelegate.consultarFuncionariosPorAreayPuesto(Areas.COORDINACION_ATENCION_VICTIMAS.parseLong(), Puestos.COORDINADOR_ATENCION_VICTIMAS.getValorId());
			
			//parseo todos los delitos a XML
			converter.alias("listaFuncionario", java.util.List.class);
			converter.alias("funcionarioDTO", FuncionarioDTO.class);
			log.info("lista_destinatario_ayuda_psiciologica_uavd :: "+ converter.toXML(listaFuncionario));
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.print("<rows>");
						
			int lTotalRegistros=listaFuncionario.size();

			writer.print("<records>" + lTotalRegistros + "</records>");
			for (FuncionarioDTO funcionarioDTO : listaFuncionario) {
				//'ID','Nombre','Puesto', 'Correo','Principal','Copia'
				writer.print("<row id='" + funcionarioDTO.getClaveFuncionario()+ "'>");
				writer.print("<cell>" + funcionarioDTO.getClaveFuncionario()+ "</cell>");
				writer.print("<cell>" + funcionarioDTO.getNombreCompleto()+ "</cell>");
				writer.print("<cell>" + funcionarioDTO.getPuesto().getValor()+ "</cell>");
				if(funcionarioDTO.getEmail() != null){
					writer.print("<cell>" + funcionarioDTO.getEmail()+ "</cell>");
				}else{
					writer.print("<cell>" +"-"+ "</cell>");
				}
				writer.print("<cell>SI</cell>");
				writer.print("<cell></cell>");
				writer.print("<cell>"+Areas.COORDINACION_ATENCION_VICTIMAS.parseLong()+"</cell>");
				writer.print("</row>");
			}
			writer.print("</rows>");
			writer.flush();
			writer.close();
			
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}	
	
	/***
	 * Metodo para mandar consultar las solicitudes por atender con cierto estatus,tipo y area
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultaSolicitudesXAtenderUAVD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			try {
				log.info("ejecutando Action PosibleHechoDelictivoAction en metodo consultaSolicitudesXAtenderUI:#####");
				String tipoSolicitud=request.getParameter("tipoSoliciutd");
				String estatusSolicitud=request.getParameter("estatus");
				//construimos la lista de los tipos de solicitud a consultar
				String[] idTiposols=tipoSolicitud.split(",");
				log.info("idsTipoSols:: "+tipoSolicitud);
				List<Long> idsTipSols=new ArrayList<Long>();log.info("arrIDsTipoSols:: "+idTiposols);
				for (String long1 : idTiposols) {
					idsTipSols.add(Long.parseLong(long1));
				}
				
				//construimos la lista de los estatus a consultar
				String[] idEstatus=estatusSolicitud.split(",");
				List<Long> idsEstatus=new ArrayList<Long>();log.info("arrIDsEstatus:: "+idEstatus);
				for (String long1 : idEstatus) {
					idsEstatus.add(Long.parseLong(long1));
				}
				//consultamos las solicitudes por atender
				List<SolicitudDTO> listaSolicitudes = new ArrayList<SolicitudDTO> ();
				
				/**Si el usuario es corrdinador no se aplica el filtro de clave usuario**/
				if(super.getUsuarioFirmado(request).getAreaActual().getAreaId().equals(Areas.COORDINACION_ATENCION_VICTIMAS.parseLong())){
					 listaSolicitudes= solicitudDelegate.consultarSolicitudesParaAtender(idsEstatus,idsTipSols, super.getUsuarioFirmado(request).getAreaActual().getAreaId(), null,null);
				}
				else{
					listaSolicitudes= solicitudDelegate.consultarSolicitudesParaAtender(idsEstatus,idsTipSols, super.getUsuarioFirmado(request).getAreaActual().getAreaId(), super.getUsuarioFirmado(request).getFuncionario().getClaveFuncionario(),null);
				}
			    converter.alias("ListaSols", java.util.List.class);
				converter.alias("SolicitudDTO", SolicitudDTO.class);
				String xml = converter.toXML(listaSolicitudes);
				if(log.isDebugEnabled())
				{
					log.debug("listaSOLS:: "+xml);	
				}
				//generamos el HTML del grid
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
			
				writer.print("<rows>");
				int lTotalRegistros=listaSolicitudes.size();
				writer.print("<records>" + lTotalRegistros + "</records>");	
				
				for (SolicitudDTO solicitudDTO : listaSolicitudes) {
					//obtenemos el expediente relacionado a la solicitud
					ExpedienteDTO expediente= new ExpedienteDTO();
					expediente.setArea(new AreaDTO(Areas.COORDINACION_ATENCION_VICTIMAS.parseLong()));
					expediente.setNumeroExpedienteId(solicitudDTO.getExpedienteDTO().getNumeroExpedienteId());
					log.info("Id de numero Expediente "+solicitudDTO.getExpedienteDTO().getNumeroExpedienteId());
					Long idExpe=expedienteDlegate.obtenerExpedienteIdPorNumExpId(expediente);
					log.info("Id de Expediente "+idExpe);
					expediente.setExpedienteId(idExpe);
					expediente.setInvolucradosSolicitados(true);
					//obtenemos el expediente
					expediente = expedienteDlegate.obtenerExpediente(expediente);
					
					writer.print("<row id='"+solicitudDTO.getDocumentoId()+","+expediente.getExpedienteId()+","+expediente.getNumeroExpediente()+","+expediente.getNumeroExpedienteId()+","+solicitudDTO.getTipoSolicitudDTO().getIdCampo()+"'>");
//					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+solicitudDTO.getDocumentoId()+" </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+solicitudDTO.getFolioSolicitud()+" </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+DateUtils.formatear(solicitudDTO.getFechaCreacion())+" </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+DateUtils.formatearHora(solicitudDTO.getFechaCreacion())+" </div]]></cell>");
					
					
					converter.alias("expedienteDTO", ExpedienteDTO.class);
					log.info("expedienteDTO_UAVD:: "+converter.toXML(expediente));
					if(expediente.getInvolucradoByCalidad(Calidades.VICTIMA_PERSONA)==null || expediente.getInvolucradoByCalidad(Calidades.VICTIMA_PERSONA).size()==0)
					{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>---</div]]></cell>");
					}
					else
					{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expediente.getInvolucradoByCalidad(Calidades.VICTIMA_PERSONA).get(0).getNombreCompleto()+" </div]]></cell>");
					}
					if(expediente.getDelitos()==null || expediente.getDelitos().size()==0)
					{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>---</div]]></cell>");
					}
					else
					{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expediente.getDelitos().get(0).getCatDelitoDTO().getNombre()+" </div]]></cell>");
					}
					if(solicitudDTO.getAreaOrigen()==null || solicitudDTO.getAreaOrigen().longValue()==0)
					{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>---</div]]></cell>");
					}
					else
					{
					//obtenemos el id del area y el texto
					Long uno=solicitudDTO.getAreaOrigen().longValue();
				    Areas[] ar=Areas.values();
				    String nombreArea="";
				    for (Areas areas : ar) 
				    {
				       	if(areas.parseLong().equals(uno))
				       	{
				       		nombreArea=areas.name();
				     	}
				     }
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+nombreArea+" </div]]></cell>");
					}
					writer.print("</row>");	
				}
			writer.print("</rows>");
			writer.flush();
			writer.close();
		
		} catch (Exception e) {		
			log.info(e.getCause(),e);
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
	public ActionForward consultarInfoResumenUAVD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando Action consultarInfoResumenUAVD");
			String idSolicitud=request.getParameter("idSolicitud");
			log.info("infoUAVD idSolicitud:: "+idSolicitud);
			SolicitudDTO solicitud= new SolicitudDTO(Long.parseLong(idSolicitud));
			solicitud.setDocumentoId(Long.parseLong(idSolicitud));
			DatosGeneralesExpedienteUAVDDTO datosSolicitudUAVD = expedienteDlegate.obtenerResumenDeExpedienteUAVD(solicitud);
			
			//consultamos la informacion de detalle
			converter.alias("DatosGeneralesExpedienteUAVDDTO", DatosGeneralesExpedienteUAVDDTO.class);
			String xml = converter.toXML(datosSolicitudUAVD);
			if(log.isDebugEnabled())
			{
				log.info("infoUAVD idSolicitud:: "+idSolicitud);
				log.info("infoUAVD:: "+xml);
			}
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirRespuesta(response, "");
		}
		return null;
	}
	
	/***
	 * Funcion para consultar una entrevista complementaria de uavd por Id
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarFamiliaresEntrevistaComplementariaPorId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
				log.info("ejecutando Action consultarFamiliaresEntrevistaComplementariaPorId");
				String idSesion=request.getParameter("idSesion").split(",")[1];
				log.info("infoUAVD idSolicitud:: "+idSesion);
				log.info("consultando UAVD_entrevista_complementaria...");
				//consultamos la entrevista complementaria por su ID
				EntrevistaComplementariaDTO entrevistaComplementariaDTO=new EntrevistaComplementariaDTO(Long.parseLong(idSesion));
				log.info("TERMINE consultando UAVD_entrevista_complementaria...");
								
				List<FamiliarDTO> listaFamiliares = familiarDelegate.consultarFamiliaresPorIdEntrevistaComplementaria(entrevistaComplementariaDTO);
				
				converter.alias("ListaFamiliares", java.util.List.class);
				converter.alias("FamiliarDTO", FamiliarDTO.class);
				
				String xml = converter.toXML(listaFamiliares);
				if(log.isDebugEnabled())
				{
					log.debug("listaFamiliares:: "+xml);	
				}
				//generamos el HTML del grid
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
			
				writer.print("<rows>");
				int lTotalRegistros=listaFamiliares.size();
				writer.print("<records>" + lTotalRegistros + "</records>");	
				
				for (FamiliarDTO familiarDTO : listaFamiliares) {
					writer.print("<row id='"+familiarDTO.getFamiliarId()+"'>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+familiarDTO.getNombre()+" </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+familiarDTO.getApellidoPaterno()+" </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+familiarDTO.getApellidoMaterno()+" </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+familiarDTO.getEdad()+" </div]]></cell>");
					if(familiarDTO.getRelacion().getDescripcionRelacion()==null)
					{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>---</div]]></cell>");
					}
					else
					{
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+familiarDTO.getRelacion().getDescripcionRelacion()+" </div]]></cell>");
					}
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+familiarDTO.getEscolaridad().getValor()+" </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+familiarDTO.getEstadoCivil().getValor()+" </div]]></cell>");
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+familiarDTO.getOcupacion().getValor()+" </div]]></cell>");
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
	
	/***
	 * Funcion para consultar una entrevista complementaria de uavd por Id
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarEntrevistaComplementariaPorId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando Action consultarEntrevistaComplementariaPorId");
			String idSesion=request.getParameter("idSesion").split(",")[1];
			log.info("infoUAVD idSolicitud:: "+idSesion);
			log.info("consultando UAVD_entrevista_complementaria...");
			//consultamos la entrevista complementaria por su ID
			EntrevistaComplementariaDTO entrevistaComplementariaDTO=sesionDelegate.consultarEntrevistaComplementariaPorId(new EntrevistaComplementariaDTO(Long.parseLong(idSesion)));
			log.info("TERMINE consultando UAVD_entrevista_complementaria...");
			
			//regresamos la informacion a la vista
			converter.alias("EntrevistaComplementariaDTO", EntrevistaComplementariaDTO.class);
			String xml = converter.toXML(entrevistaComplementariaDTO);
			if(log.isDebugEnabled())
			{
				log.info("UAVD_entrevista_complementaria:: "+xml);
			}
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirRespuesta(response, "");
		}
		return null;
	}
	
	/***
	 * Funcion para guardar alguna de las siguientes narrativas del aentrevista complementaria :
	 * Motio de Consulta
	 * Conciencia de la problematica
	 * diagnostico familiar
	 * hipotesis familiar
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return XML del objeto EntrevistaComplementariaDTO
	 * @throws IOException
	 */
	public ActionForward guardaActualizaNarrativaEntrevistaComplementariaPorId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando Action guardaActualizaEntrevistaComplementariaPorId");
			String idSesion=request.getParameter("idSesion").split(",")[1];
			String opcGuardado=request.getParameter("opcionGuardado");
			String narrrativa=request.getParameter("narrativa");
			String fechaSesion=request.getParameter("fecha");
			
			log.info("entrevista_complementaria_UAVD_idSolicitud:: "+idSesion);
			log.info("entrevista_complementaria_UAVD_opcGuardado:: "+opcGuardado);
			log.info("entrevista_complementaria_UAVD_narrativa:: "+narrrativa);
			log.info("entrevista_complementaria_UAVD_fechaSesion:: "+fechaSesion);
			
			log.info("ingrresar_actualizar narrativa UAVD_entrevista_complementaria...");
			//consultamos la entrevista complementaria por su ID para no planchar la informacion previa
			EntrevistaComplementariaDTO entrevistaComplementariaDTO=sesionDelegate.consultarEntrevistaComplementariaPorId(new EntrevistaComplementariaDTO(Long.parseLong(idSesion)));
			log.info("TERMINE consultando UAVD_entrevista_complementaria...");
			
			//revisamos que la consulta no sea null
			if(entrevistaComplementariaDTO==null)
			{
				entrevistaComplementariaDTO= new EntrevistaComplementariaDTO();
				entrevistaComplementariaDTO.setNumeroSesion((short)0);
			}
			
			//seteamos la fecha de atencion
			if(fechaSesion!=null && !fechaSesion.equals("")){
				Date fecha=DateUtils.obtener(fechaSesion);
				entrevistaComplementariaDTO.setFechaSesion(fecha);
			}
			
			//ahora revisamos que narrativa se guaradara
			if(opcGuardado.equals("MOTIVO_CONSULTA"))//guardamos o actualizamos motivo de Consulta
			{
				entrevistaComplementariaDTO.setMotivoConsulta(narrrativa);
			}
			else if(opcGuardado.equals("CONCIENCIA_PROBLEMATICA"))//Guardamos o actualizamos conciencia de la problematica
			{
				entrevistaComplementariaDTO.setConcienciaProblema(narrrativa);
			}
			else if(opcGuardado.equals("DIAGNOSTICO_FAMILIAR"))//Guardamos o actualizamos diagnostico familiar
			{
				entrevistaComplementariaDTO.setImpresionDiagnostico(narrrativa);
			}
			else if(opcGuardado.equals("HIPOTESIS_FAMILIAR"))//Guardamos o actualizamos hipotesis familiar
			{
				entrevistaComplementariaDTO.setHipotesisFamilia(narrrativa);
			}
			
			//SI asiste a entrevista
			entrevistaComplementariaDTO.setEsPresente(true);
			//guardamos la informacion
			entrevistaComplementariaDTO= sesionDelegate.guardarEntrevistaComplementaria(entrevistaComplementariaDTO);
			
			//regresamos la informacion a la vista
			converter.alias("EntrevistaComplementariaDTO", EntrevistaComplementariaDTO.class);
			String xml = converter.toXML(entrevistaComplementariaDTO);
			if(log.isDebugEnabled())
			{
				log.info("guardado_actuaizacion narrativa UAVD_entrevista_complementaria:: "+xml);
			}
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirRespuesta(response, "");
		}
		return null;
	}
	
	public ActionForward guardaActualizaFamiliarEntrevistaComplementariaPorId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando Action guardaActualizaFamiliarEntrevistaComplementariaPorId");
			String idSesion=request.getParameter("idSesion").split(",")[1];
			String idFamiliar=request.getParameter("idFamiliar");
			
			String nombre=request.getParameter("nombre");
			String apPaterno=request.getParameter("apPaterno");
			String apMaterno=request.getParameter("apMaterno");
			String edadAprox=request.getParameter("edadAprox");
			
			String idParentesco=request.getParameter("parentesco");
			String idEscolaridad=request.getParameter("escolaridad");
			String idEdoCivil=request.getParameter("edoCivil");
			String idOcupacion=request.getParameter("ocupacion");
			
			log.info("entrevista_complementaria_UAVD_idSolicitud:: "+idSesion);
			log.info("entrevista_complementaria_UAVD_idFamiliar:: "+idFamiliar);
			log.info("entrevista_complementaria_UAVD_nombre:: "+nombre);
			log.info("entrevista_complementaria_UAVD_apPaterno:: "+apPaterno);
			log.info("entrevista_complementaria_UAVD_apMaterno:: "+apMaterno);
			log.info("entrevista_complementaria_UAVD_edadAprox:: "+edadAprox);
			log.info("entrevista_complementaria_UAVD_idParentesco:: "+idParentesco);
			log.info("entrevista_complementaria_UAVD_idEscolaridad:: "+idEscolaridad);
			log.info("entrevista_complementaria_UAVD_idEdoCivil:: "+idEdoCivil);
			log.info("entrevista_complementaria_UAVD_idOcupacion:: "+idOcupacion);
			
			log.info("ingrresar_actualizar familiar UAVD_entrevista_complementaria...");
			//consultamos la entrevista complementaria por su ID para no planchar la informacion previa
			EntrevistaComplementariaDTO entrevistaComplementariaDTO=new EntrevistaComplementariaDTO();
			if(!idSesion.equals("0"))
			{
				entrevistaComplementariaDTO.setSesionId(Long.parseLong(idSesion));
				entrevistaComplementariaDTO.setNumeroSesion(Short.parseShort(idSesion));
			}
			log.info("idSesion_ingresar_familiar:: "+entrevistaComplementariaDTO.getSesionId());
			log.info("idSesion_ingresar_familiar(2):: "+entrevistaComplementariaDTO.getNumeroSesion());
			FamiliarDTO familiar= new FamiliarDTO();
			familiar.setNombre(nombre);
			familiar.setApellidoPaterno(apPaterno);
			familiar.setApellidoMaterno(apMaterno);
			familiar.setEdad(Short.parseShort(edadAprox));
			familiar.setRelacion(new CatRelacionDTO(Long.parseLong(idParentesco)));
			familiar.setEscolaridad(new ValorDTO(Long.parseLong(idEscolaridad)));
			familiar.setEstadoCivil(new ValorDTO(Long.parseLong(idEdoCivil)));
			familiar.setOcupacion(new ValorDTO(Long.parseLong(idOcupacion)));
			familiar.setEntrevistaComplementaria(entrevistaComplementariaDTO);
			//actualizamos los familiares
			log.info("inserta_familiar UAVD_entrevista_complementaria...");
			log.info("inserta_familiar familiarDTO:: "+familiar);
			familiarDelegate.guardarFamiliar(familiar);
			log.info("FIN_inserta_familiar UAVD_entrevista_complementaria...");
			//escribimos la respuesta
			String xml="<respuesta><code>0</code></respuesta>";
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			String xml="<respuesta><code>1</code></respuesta>";
			escribirRespuesta(response, xml);
		}
		return null;
	}
	
	/***
	 * Funcion para consultar una entrevista Inicial de uavd por Id
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarEntrevistaInicialPorId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando Action consultarEntrevistaInicialPorId");
			String idSesion=request.getParameter("idSesion");
			log.info("infoUAVD idSolicitud:: "+idSesion);
			log.info("consultando UAVD_entrevista_Inicial...");
			//consultamos la entrevista complementaria por su ID
			EntrevistaInicialDTO entrevistaInicialDTO=sesionDelegate.consultarEntrevistaInicialPorId(new EntrevistaInicialDTO(Long.parseLong(idSesion)));
			log.info("TERMINE consultando UAVD_entrevista_Inicial...");
			if(entrevistaInicialDTO.getFechaSesion()!=null){
				entrevistaInicialDTO.setStringFecha(DateUtils.formatear(entrevistaInicialDTO.getFechaSesion()));
			}else{
				entrevistaInicialDTO.setStringFecha(DateUtils.formatear(new Date()));
			}
//			entrevistaInicialDTO.setMotivoAtencion("adwd");
			//regresamos la informacion a la vista
			converter.alias("EntrevistaInicialDTO", EntrevistaInicialDTO.class);
			String xml = converter.toXML(entrevistaInicialDTO);
			if(log.isDebugEnabled())
			{
				log.info("UAVD_entrevista_Inicial:: "+xml);
			}
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirRespuesta(response, "");
		}
		return null;
	}
	
	
	/***
	 * Funcion para guardar una entrevista Inicial de uavd por Id
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward guardarEntrevistaInicial(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando Action guardarEntrevistaInicial");
			String idSesion=request.getParameter("idSesion");
			String victimaDirecta=request.getParameter("victimaDirecta");
			String fechaSesion=request.getParameter("fecha");
			String motivo=request.getParameter("motivo");
			log.info("infoUAVD idSolicitud:: "+idSesion);
			log.info("infoUAVD victimaDirecta:: "+victimaDirecta);
			log.info("infoUAVD fechaSesion:: "+fechaSesion);
			log.info("infoUAVD motivo:: "+motivo);
			log.info("guardando UAVD_entrevista_Inicial...");
			//consultamos la entrevista complementaria por su ID
			EntrevistaInicialDTO entrevistaInicialDTO=sesionDelegate.consultarEntrevistaInicialPorId(new EntrevistaInicialDTO(Long.parseLong(idSesion)));
			if(fechaSesion!=null && !fechaSesion.equals("")){
				Date fecha=DateUtils.obtener(fechaSesion);
				entrevistaInicialDTO.setFechaSesion(fecha);
			}
			if(victimaDirecta!=null && !victimaDirecta.equals("")){
				if(victimaDirecta.equals("true")){
					entrevistaInicialDTO.setEsVictimaDirecta(true);
				}else{
					entrevistaInicialDTO.setEsVictimaDirecta(false);
				}
			}
			if(motivo!=null && !motivo.equals("")){
				entrevistaInicialDTO.setMotivoAtencion(motivo);
			}
			if(entrevistaInicialDTO.getFechaSesion()!=null){
				entrevistaInicialDTO.setStringFecha(DateUtils.formatear(entrevistaInicialDTO.getFechaSesion()));
			}else{
				entrevistaInicialDTO.setStringFecha(DateUtils.formatear(new Date()));
			}
			//SI asiste a entrevista
			entrevistaInicialDTO.setEsPresente(true);
			//operacion
			entrevistaInicialDTO=sesionDelegate.guardarEntrevistaInicial(entrevistaInicialDTO);
			//regresamos la informacion a la vista
			converter.alias("EntrevistaInicialDTO", EntrevistaInicialDTO.class);
			String xml = converter.toXML(entrevistaInicialDTO);
			if(log.isDebugEnabled())
			{
				log.info("UAVD_entrevista_Inicial:: "+xml);
			}
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirRespuesta(response, "");
		}
		return null;
	}
	
	/**
	 * Funcion que regresa la informacion de los parentescos
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarRelacionParentesco(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
    		log.info("ejecutando Action consultarRelacionParentesco"); 
    		List<CatRelacionDTO> listaCatalogo=relacionDelegate.consultarParentescos();
    		
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catParentesco", CatRelacionDTO.class);
			
			String xml = converter.toXML(listaCatalogo);
			response.setContentType("text/xml");
			log.info("catalogoParentesco:: "+xml);
			escribirRespuesta(response, xml);
			}
		catch (Exception e) {
			log.info(e);
		}
		return null;
	}
	/***
	 * Funcion para consultar una Nota de evaluacion de uavd por Id
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarNotaEvaluacionPorId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando Action consultarNotaEvaluacionPorId");
			String idSesion=request.getParameter("idSesion");
			log.info("infoUAVD idSolicitud:: "+idSesion);
			log.info("consultando UAVD_NotaEvaluacion...");
			//consultamos la NotaEvaluacion por su ID
			NotaEvolucionDTO notaEvolucionDTO=sesionDelegate.consultarNotaEvolucionPorId(new NotaEvolucionDTO(Long.parseLong(idSesion)));
			log.info("TERMINE consultando UAVD_NotaEvaluacion...");
			if(notaEvolucionDTO.getFechaSesion()!=null){
				notaEvolucionDTO.setStringFecha(DateUtils.formatear(notaEvolucionDTO.getFechaSesion()));
			}else{
				notaEvolucionDTO.setStringFecha(DateUtils.formatear(new Date()));
			}
			
//			notaEvolucionDTO.setSeguimiento("Seguiimiento");
//			notaEvolucionDTO.setObjetivo("Objetivo");
//			notaEvolucionDTO.setAnalisisSesion("Analisis");
//			notaEvolucionDTO.setPlanteamientoTerap("plantera");
			converter.alias("NotaEvolucionDTO", NotaEvolucionDTO.class);
			String xml = converter.toXML(notaEvolucionDTO);
			if(log.isDebugEnabled())
			{
				log.info("UAVD_notaEvolucionDTO:: "+xml);
			}
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirRespuesta(response, "");
		}
		return null;
	}
	
	/***
	 * Funcion para guardar una NotaEvaluacion de uavd por Id
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward guardarNotaEvaluacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando Action guardar NotaEvaluacion");
			String idSesion=request.getParameter("idSesion");
			String seguimiento=request.getParameter("seguimiento");
			String fechaSesion=request.getParameter("fecha");
			String objetivo=request.getParameter("objetivo");
			String analisis=request.getParameter("analisis");
			String plan=request.getParameter("plan");
			String observaciones=request.getParameter("observaciones");
			
			String asistencia=request.getParameter("asistencia");
			log.info("infoUAVD idSolicitud:: "+idSesion);
			log.info("infoUAVD seguimiento:: "+seguimiento);
			log.info("infoUAVD fechaSesion:: "+fechaSesion);
			log.info("infoUAVD objetivo:: "+objetivo);
			log.info("infoUAVD analisis:: "+analisis);
			log.info("infoUAVD plan:: "+plan);
			log.info("infoUAVD observaciones:: "+observaciones);
			log.info("infoUAVD asistencia:: "+asistencia);
			log.info("guardando UAVD_NotaEvaluacion...");
			//consultamos la NotaEvaluacion  por su ID
			NotaEvolucionDTO notaEvolucionDTO=sesionDelegate.consultarNotaEvolucionPorId(new NotaEvolucionDTO(Long.parseLong(idSesion)));
			
			if(fechaSesion!=null && !fechaSesion.equals("")){
				Date fecha=DateUtils.obtener(fechaSesion);
				notaEvolucionDTO.setFechaSesion(fecha);
			}
			if(seguimiento!=null && !seguimiento.equals("")){
				notaEvolucionDTO.setSeguimiento(seguimiento);
			}
			if(objetivo!=null && !objetivo.equals("")){
				notaEvolucionDTO.setObjetivo(objetivo);
			}
			if(analisis!=null && !analisis.equals("")){
				notaEvolucionDTO.setAnalisisSesion(analisis);
			}
			if(plan!=null && !plan.equals("")){
				notaEvolucionDTO.setPlanteamientoTerap(plan);
			}
			if(observaciones!=null && !observaciones.equals("")){
				notaEvolucionDTO.setObsFaltaInteres(observaciones);
			}
			if(asistencia !=null && !asistencia.equals("")){
				if(asistencia.equals("true"))
					notaEvolucionDTO.setEsPresente(true);
				else
					notaEvolucionDTO.setEsPresente(false);
			}
			notaEvolucionDTO=sesionDelegate.guardarNotaEvolucion(notaEvolucionDTO);
			//regresamos la informacion a la vista
			converter.alias("EntrevistaInicialDTO", EntrevistaInicialDTO.class);
			String xml = converter.toXML(notaEvolucionDTO);
			if(log.isDebugEnabled())
			{
				log.info("UAVD_notaEvolucion:: "+xml);
			}
			escribirRespuesta(response, xml);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirRespuesta(response, "");
		}
		return null;
	}
	
	
	
	
	/***
	 * Funcion para guardar una NotaEvaluacion de uavd por nueva
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward guardarNotaEvaluacionNueva(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			try{	
				log.info("$$$$ Metodo guardarNotaEvaluacionNueva  ");
				String idNumeroExpediente=request.getParameter("idNumeroExpediente");
				String sesion=request.getParameter("sesion");
				log.info("$$$$ Metodo guardarNotaEvaluacionNueva :: idNumeroExpediente :"+idNumeroExpediente);
				log.info(":: sesion :"+sesion);

				
				ExpedienteDTO expedienteDTO=new ExpedienteDTO();
				if(idNumeroExpediente!=null && !idNumeroExpediente.equals("")){
					expedienteDTO.setNumeroExpedienteId(Long.parseLong(idNumeroExpediente));
				}
				NotaEvolucionDTO notaEvolucionDTO=new NotaEvolucionDTO();
				notaEvolucionDTO.setNumeroExpediente(expedienteDTO);
				
				notaEvolucionDTO.setEsPresente(false);
//				notaEvolucionDTO.setFechaSesion(new Date());
				int numerose=(Integer.parseInt(sesion)+1);
				log.info(":: sesion next :"+numerose);

				//notaEvolucionDTO.setNumeroSesion((short)numerose);
				notaEvolucionDTO.setTipoSesion(new ValorDTO(TipoSesion.NOTAS_EVOLUCION.getValorId(), TipoSesion.NOTAS_EVOLUCION.toString(), TipoSesion.NOTAS_EVOLUCION.name()));
				notaEvolucionDTO.setUsuario(super.getUsuarioFirmado(request));
				notaEvolucionDTO=sesionDelegate.guardarNotaEvolucion(notaEvolucionDTO);
				
				
				converter.alias("NotaEvolucionDTO", EntrevistaInicialDTO.class);
//				String xml = converter.toXML(notaEvolucionDTO);
				if(log.isDebugEnabled())
				{
//					log.info("UAVD_notaEvolucion:: "+xml);
				}
//				escribirRespuesta(response, xml);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				escribirRespuesta(response, "");
			}
			return null;
	}

	/***
	 * Funcion para regresar las solictiudes por tipo 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarInicialExpPsicologicosUAVDGrid(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
			try {
				log.info("ejecutando UnidadAtencionVictimasDelitoAction - consultarInicialExpPsicologicosUAVDGrid:#####");
				String tipoSolicitud=request.getParameter("tipoSoliciutd");
				String estatusSolicitud=request.getParameter("estatus");
				String areaSolicitud=request.getParameter("idArea");
				
				String idCadenaArea= request.getParameter("cadenaArea");
				
				FiltroExpedienteDTO filtroExpedienteDTO=new FiltroExpedienteDTO();
				filtroExpedienteDTO.setAnio(new Long(Calendar.getInstance().get(Calendar.YEAR)));
				log.info("cadenaArea_UAVD:: "+idCadenaArea);
				if(idCadenaArea.equals("PSICO"))
				{
					filtroExpedienteDTO.setIdArea(new Long(Areas.COORDINACION_ATENCION_VICTIMAS.ordinal()));
					filtroExpedienteDTO.setIdActividad(Actividades.ASIGNAR_SOLICITUD_DE_AYUDA.getValorId());
				}
				else if(idCadenaArea.equals("TRABSOC"))
				{
					filtroExpedienteDTO.setIdArea(new Long(Areas.COORDINACION_ATENCION_VICTIMAS.ordinal()));
					filtroExpedienteDTO.setIdActividad(Actividades.ENVIAR_ESTUDIO_SOCIOECONOMICO.getValorId());
				}
				else if(idCadenaArea.equals("ATNJUR"))
				{
					filtroExpedienteDTO.setIdArea(new Long(Areas.COORDINACION_ATENCION_VICTIMAS.ordinal()));
					filtroExpedienteDTO.setIdActividad(Actividades.PROPORCIONAR_APOYO_LEGAL.getValorId());
				}

				UsuarioDTO usuarioDTO =  getUsuarioFirmado(request);
				filtroExpedienteDTO.setUsuario(usuarioDTO);
				
				//lista de los expedientes
				List<ExpedienteDTO> listaExpedienteDTOs=expedienteDlegate.consultarExpedienteActividadAreaAnio(filtroExpedienteDTO);
				if (log.isDebugEnabled()) {
					log.debug("##################lista de Expedientes_UAVD para solicitud:::::::::" + listaExpedienteDTOs.size());
				}
				//generamos el HTML del grid
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
			
				writer.print("<rows>");
				int lTotalRegistros=listaExpedienteDTOs.size();
				writer.print("<records>" + lTotalRegistros + "</records>");	
				
				for (ExpedienteDTO expediente : listaExpedienteDTOs) {
					//obtenemos la solicitud relacionada al expediente
					expediente.setInvolucradosSolicitados(true);
					expediente=expedienteDlegate.obtenerExpediente(expediente);
					List<SolicitudDTO> listaSolicitudes=solicitudDelegate.consultarSolicitudesPorExpediente(expediente);
					SolicitudDTO solicitudDTO=new SolicitudDTO();
					if(listaSolicitudes!=null && listaSolicitudes.size()>0)
					{
						solicitudDTO=listaSolicitudes.get(0);
						
						writer.print("<row id='"+solicitudDTO.getDocumentoId()+","+expediente.getExpedienteId()+","+expediente.getNumeroExpediente()+","+expediente.getNumeroExpedienteId()+","+solicitudDTO.getTipoSolicitudDTO().getIdCampo()+"'>");
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expediente.getNumeroExpediente()+" </div]]></cell>");
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+DateUtils.formatear(solicitudDTO.getFechaCreacion())+" </div]]></cell>");
						converter.alias("expedienteDTO", ExpedienteDTO.class);
						log.info("expedienteDTO_UAVD:: "+converter.toXML(expediente));
						if(expediente.getInvolucradoByCalidad(Calidades.VICTIMA_PERSONA)==null || expediente.getInvolucradoByCalidad(Calidades.VICTIMA_PERSONA).size()==0)
						{
							writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>---</div]]></cell>");
						}
						else
						{
							writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expediente.getInvolucradoByCalidad(Calidades.VICTIMA_PERSONA).get(0).getNombreCompleto()+" </div]]></cell>");
						}
						if(expediente.getDelitos()==null || expediente.getDelitos().size()==0)
						{
							writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>---</div]]></cell>");
						}
						else
						{
							writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+expediente.getDelitos().get(0).getCatDelitoDTO().getNombre()+" </div]]></cell>");
						}
						writer.print("</row>");	
					}
				}
			writer.print("</rows>");
			writer.flush();
			writer.close();
		} catch (Exception e) {		
			log.info(e.getCause(),e);
		}
		return null;
	}
	
	/***
	 * Funcion para llenar el grid de destinatario para la solicitud de la ayuda psicologica
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultaFuncionarioRemitenteSolucitudPorId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			String idSolicitud=request.getParameter("idSolicitud");
			log.info("idSolicitud_usuario_remitente_UAVD:: "+idSolicitud);
			SolicitudDTO solicitud=solicitudDelegate.consultarSolicitudXId(Long.parseLong(idSolicitud));
			FuncionarioDTO funcionarioDTO=solicitud.getUsuarioSolicitante();
			//funcionarioDTO=funcionarioDelegate.consultarFuncionarioXNumeroEmpleado(funcionarioDTO);
			
			converter.alias("FuncionarioDTO", FuncionarioDTO.class);
			String xml = converter.toXML(funcionarioDTO);
			if(log.isDebugEnabled())
			{
				log.info("UAVD_funcionario_remitente:: "+xml);
			}
			
			//generamos el HTML del grid
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
		
			writer.print("<rows>");
			if(funcionarioDTO!=null)
			{
				int lTotalRegistros=1;
				writer.print("<records>" + lTotalRegistros + "</records>");	
				writer.print("<row id='" + funcionarioDTO.getClaveFuncionario()+ "'>");
				writer.print("<cell>" + funcionarioDTO.getClaveFuncionario()+ "</cell>");
				writer.print("<cell>" + funcionarioDTO.getNombreCompleto()+ "</cell>");
				writer.print("<cell>" + funcionarioDTO.getPuesto().getValor()+ "</cell>");
				writer.print("<cell>" + funcionarioDTO.getEmail()+ "</cell>");
				writer.print("<cell>SI</cell>");
				writer.print("<cell></cell>");
				writer.print("<cell>"+ solicitud.getAreaOrigen() +"</cell>");
				writer.print("</row>");
				writer.print("</rows>");
				writer.flush();
				writer.close();
			}
			
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}	
	
	/***
	 * Funcion para llenar el grid de destinatario para la solicitud de la ayuda psicologica
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultaFuncionarioRemitenteSolucitudPorIdXML(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			String idSolicitud=request.getParameter("idSolicitud");
			log.info("idSolicitud_usuario_remitente_UAVD:: "+idSolicitud);
			SolicitudDTO solicitud=solicitudDelegate.consultarSolicitudXId(Long.parseLong(idSolicitud));
			FuncionarioDTO funcionarioDTO=solicitud.getUsuarioSolicitante();
			
			converter.alias("FuncionarioDTO", FuncionarioDTO.class);
			String xml = converter.toXML(funcionarioDTO);
			if(log.isDebugEnabled())
			{
				log.info("UAVD_funcionario_remitente:: "+xml);
			}
			//escrbir respuesta
			escribirRespuesta(response, xml);
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			escribirRespuesta(response, "");
		}
		return null;
	}	
}

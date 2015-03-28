/**
* Nombre del Programa : InformePHAction.java
* Autor                            : SergioDC
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 2 Aug 2011
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
package mx.gob.segob.nsjp.web.seguridadPublica.action;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.detencion.TipoCentroDetencion;
import mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.delegate.detencion.CentroDetencionDelegate;
import mx.gob.segob.nsjp.delegate.detencion.DetencionDelegate;
import mx.gob.segob.nsjp.delegate.documento.DocumentoDelegate;
import mx.gob.segob.nsjp.delegate.expediente.ExpedienteDelegate;
import mx.gob.segob.nsjp.delegate.informepolicial.InformePolicialHomologadoDelegate;
import mx.gob.segob.nsjp.delegate.involucrado.InvolucradoDelegate;
import mx.gob.segob.nsjp.delegate.notificacion.NotificacionDelegate;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.detencion.CentroDetencionDTO;
import mx.gob.segob.nsjp.dto.detencion.PertenenciaDTO;
import mx.gob.segob.nsjp.dto.documento.AvisoHechoDelictivoDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.documento.NotificacionDTO;
import mx.gob.segob.nsjp.dto.domicilio.AsentamientoDTO;
import mx.gob.segob.nsjp.dto.domicilio.CiudadDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.domicilio.EntidadFederativaDTO;
import mx.gob.segob.nsjp.dto.domicilio.LugarDTO;
import mx.gob.segob.nsjp.dto.domicilio.MunicipioDTO;
import mx.gob.segob.nsjp.dto.domicilio.TipoAsentamientoDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.informepolicial.InformePolicialHomologadoDTO;
import mx.gob.segob.nsjp.dto.involucrado.AliasInvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.DetencionDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoViewDTO;
import mx.gob.segob.nsjp.dto.involucrado.ServidorPublicoDTO;
import mx.gob.segob.nsjp.dto.organizacion.OrganizacionDTO;
import mx.gob.segob.nsjp.dto.persona.CorreoElectronicoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.dto.persona.TelefonoDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudTrasladoImputadoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.ReporteBaseAction;
import mx.gob.segob.nsjp.web.caso.form.IngresarIndividuoForm;
/**
 * Action para invocar servicios de Registro de Detención.
 * @version 1.0
 * @author EduardoG
 *
 */
public class RegistroDetencionAction extends ReporteBaseAction{
	private static final Logger log  = Logger.getLogger(RegistroDetencionAction.class);
	
	@Autowired
	private CentroDetencionDelegate centroDetencionDelegate;
	@Autowired
	private InvolucradoDelegate involucradoDelegate;
	@Autowired
	private ExpedienteDelegate expedienteDelegate;
	@Autowired
	private NotificacionDelegate notificacionDelegate;
	@Autowired
	private DetencionDelegate detencionDelegate;
	@Autowired
	private InformePolicialHomologadoDelegate informePolicialHomologadoDelegate;
	
	/**
	 * Método utilizado para Consultar el Catalogo de Centros de Detención.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarCatalogoCentrosDetencion
				(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.debug("ejecutando Action consultar Catalogo Centros de Detencion");
			Long centroDetencion = new Long(request.getParameter("centroDetencion"));
						
			List<CentroDetencionDTO> centroDetencionList = centroDetencionDelegate.consultarCentrosDetencionPorTipo(centroDetencion);
			converter.alias("CentroDetencionDTO", CentroDetencionDTO.class);
			String xml = converter.toXML(centroDetencionList);
			
			log.debug("centroDetencion [" + centroDetencion + "] respuesta consultar catalogo de Centros de Detencion ------- "+xml);

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
	 * Método utilizado para consultar los expedientes que contienen un folio de hecho delictivo de acuerdo a su estatus.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarRegistroDeDetencionPorEstatus
				(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.debug("ejecutando Action consultar Registro de Detencion por estatus");
						
			Long estatusExpediente = new Long(request.getParameter("estatus"));
			
			List<ExpedienteDTO> lstExpedienteDTO = new ArrayList<ExpedienteDTO>();
			
			log.debug("estatus del expediente ... " + estatusExpediente + " = " + EstatusExpediente.NO_ATENDIDO.getValorId());
			
			if(estatusExpediente.equals(EstatusExpediente.NO_ATENDIDO.getValorId())){
				lstExpedienteDTO = expedienteDelegate.consultarNumeroExpedienteByEstatus(EstatusExpediente.NO_ATENDIDO);
			}
			if(estatusExpediente.equals(EstatusExpediente.ABIERTO.getValorId())){
				lstExpedienteDTO = expedienteDelegate.consultarNumeroExpedienteByEstatus(EstatusExpediente.ABIERTO);
			}
			if(estatusExpediente.equals(EstatusExpediente.CANALIZADO.getValorId())){
				lstExpedienteDTO = expedienteDelegate.consultarNumeroExpedienteByEstatus(EstatusExpediente.CANALIZADO);
			}
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();

			writer.print("<rows>");

			int lTotalRegistros = lstExpedienteDTO.size();
			writer.print("<records>" + lTotalRegistros + "</records>");
			
			for(ExpedienteDTO expedienteDTO : lstExpedienteDTO){
				
				log.info("expedienteDTO ... " + expedienteDTO);
				
				writer.print("<row id='" + expedienteDTO.getExpedienteId() + "'>");
				
				writer.print("<cell>" + expedienteDTO.getNumeroExpediente() +  "</cell>");

				writer.print("<cell>");
				if(expedienteDTO.getHechoDTO().getAvisoHechoDelictivo().getFolioNotificacion() != null){
					writer.print(expedienteDTO.getHechoDTO().getAvisoHechoDelictivo().getFolioNotificacion());
				}else{
					writer.print("---");
				}
				writer.print("</cell>");
				
				writer.print("<cell>");
				if(expedienteDTO.getHechoDTO().getAvisoHechoDelictivo().getFechaCreacion() != null){					
					writer.print(DateUtils.formatear(expedienteDTO.getHechoDTO().getAvisoHechoDelictivo().getFechaCreacion()));
				}else{
					writer.print("---");
				}
				writer.print("</cell>");

				writer.print("<cell>");
				if(expedienteDTO.getHechoDTO().getAvisoHechoDelictivo().getCatDelito().getNombre() != null){
					writer.print(expedienteDTO.getHechoDTO().getAvisoHechoDelictivo().getCatDelito().getNombre());
				}else{
					writer.print("---");
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
	
	/**
	 * Metodo utilizado para consultar un Registro de Detención de acuerdo a un expediente seleccionado.
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward obtenerAvisoPorIdExpediente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("Consultando los datos de un Registro de Detencion por ExpedienteId");
			Long expedienteId = new Long(request.getParameter("expedienteId"));
			String numeroExpediente = request.getParameter("numeroExpediente");
			log.debug("expedienteId ... " + expedienteId);
			log.debug("numeroExpediente ... " + numeroExpediente);
			
			AvisoHechoDelictivoDTO avisoHechoDelictivoDTO = new AvisoHechoDelictivoDTO();
			avisoHechoDelictivoDTO = notificacionDelegate.obtenerAvisoPorIdExpediente(expedienteId);
			UsuarioDTO usuario = getUsuarioFirmado(request);
			avisoHechoDelictivoDTO.setUsuario(usuario);
			converter.alias("avisoHechoDelictivoDTO", AvisoHechoDelictivoDTO.class);
			String xml = converter.toXML(avisoHechoDelictivoDTO);
			log.debug(xml);
			escribir(response, xml, null);
			
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}
		
	/**
	 * Metodo utilizado para guardar los datos de un Probable Responsable.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward agregarProbableResponsable(
			ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			log.info("ejecutando Action agregarProbableResponsable");			
			Long expedienteId = new Long(request.getParameter("expedienteId"));
			String numeroExpediente = request.getParameter("numeroExpediente");
			log.debug("expedienteId ... " + expedienteId);
			log.debug("numeroExpediente ... " + numeroExpediente);
			
			String nombre = request.getParameter("nombre");
			String apellidoPaterno = request.getParameter("apellidoPaterno");
			String apellidoMaterno = request.getParameter("apellidoMaterno");
			String detencion = request.getParameter("detencion");
			String desconocido = request.getParameter("desconocido");
			String fechaDetencion = request.getParameter("fechaDetencion");
			String horarioDetencion = request.getParameter("horarioDetencion");

			InvolucradoDTO involucradoDTO = new InvolucradoDTO();
			ExpedienteDTO expedienteDTO = expedienteDelegate.obtenerExpedientePorNumeroExpediente(numeroExpediente);
			involucradoDTO.setExpedienteDTO(expedienteDTO);
			CalidadDTO calidadDTO = new CalidadDTO();			
			calidadDTO.setCalidades(Calidades.PROBABLE_RESPONSABLE_PERSONA);
			involucradoDTO.setCalidadDTO(calidadDTO);
			involucradoDTO.setTipoPersona(1L);
			
			//IngresarIndividuoForm forma = (IngresarIndividuoForm) form;
			
			log.info("Anonimo:"+desconocido);
			involucradoDTO.setDesconocido(desconocido.equals("Si") ? "true" : "false");

			log.info("Detenido:"+detencion);					
			involucradoDTO.setEsDetenido(detencion.equals("Si") ? true : false);
			
			if(detencion.equals("Si")){
				log.info("Entra a setear datos si esta detenido:"+detencion);						
				ArrayList<DetencionDTO> detencionDTOs = new ArrayList<DetencionDTO>();
				DetencionDTO detencionDTO = new DetencionDTO();
				detencionDTO.setFechaInicioDetencion(fechaDetencion);
				detencionDTO.setHoraInicioDetencion(horarioDetencion);
				detencionDTOs.add(detencionDTO);
				involucradoDTO.setDetenciones(detencionDTOs);
			}
			
			List<NombreDemograficoDTO> lstDatosGenerales = new ArrayList<NombreDemograficoDTO>();
			NombreDemograficoDTO datosGenerales = new NombreDemograficoDTO();
			datosGenerales.setNombre(nombre);
			datosGenerales.setApellidoPaterno(apellidoPaterno);
			datosGenerales.setApellidoMaterno(apellidoMaterno);
			lstDatosGenerales.add(datosGenerales);
			involucradoDTO.setNombresDemograficoDTO(lstDatosGenerales);

			log.info("InvolucradoDTO ... " + involucradoDTO);
			
			Long resp=null;
			resp = involucradoDelegate.guardarInvolucrado(involucradoDTO);
			
			log.info("el valor de la respuesta es:" + resp);

			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.flush();
			writer.close();			
		} catch (Exception e) {
			log.info(e.getCause(), e);
		}
		return null;
	}
	
	/**
	 * Metodo utilizado para realizar la carga del combo Calidad
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward consultarProbableResponsablePorNumeroExpediente(
				ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			log.info("ejecutando Action consultarProbableResponsablePorExpediente");
			Long expedienteId = new Long(request.getParameter("expedienteId"));
			String numeroExpediente = request.getParameter("numeroExpediente");
			log.debug("expedienteId ... " + expedienteId);
			log.debug("numeroExpediente ... " + numeroExpediente);
			ExpedienteDTO expedienteDTO = expedienteDelegate.obtenerExpedientePorNumeroExpediente(numeroExpediente);
			log.debug("dto [expediente] ... " + expedienteDTO);
			List<InvolucradoDTO> lstInvolucradoDTO = new ArrayList<InvolucradoDTO>();
			lstInvolucradoDTO = expedienteDTO.getInvolucradoByCalidad(Calidades.PROBABLE_RESPONSABLE_PERSONA);

			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();

			writer.print("<rows>");		

			int lTotalRegistros = lstInvolucradoDTO.size();
			writer.print("<records>" + lTotalRegistros + "</records>");

			for(InvolucradoDTO involucradoDTO : lstInvolucradoDTO){
				
				writer.print("<row id='"+involucradoDTO.getElementoId()+ "'>");
				writer.print("<cell>"+ involucradoDTO.getNombresDemograficoDTO().get(0).getNombre() +"</cell>");
				writer.print("<cell>"+ involucradoDTO.getNombresDemograficoDTO().get(0).getApellidoPaterno() +"</cell>");
				writer.print("<cell>"+ involucradoDTO.getNombresDemograficoDTO().get(0).getApellidoMaterno() +"</cell>");
				
				writer.print("<cell>");
				if(involucradoDTO.getEsDetenido()){
					writer.print("Si");
				}else{
					writer.print("No");
				}
				writer.print("</cell>");

				writer.print("<cell>");
				if(involucradoDTO.getEsDetenido() && involucradoDTO.getDetenciones() != null && !involucradoDTO.getDetenciones().isEmpty()){
					writer.print(DateUtils.formatear(involucradoDTO.getDetenciones().get(0).getFechaInicioDetencion()) 
								 + " " 
								 + involucradoDTO.getDetenciones().get(0).getHoraInicioDetencion());
				}else{
					writer.print("---");
				}
				writer.print("</cell>");
				
				writer.print("<cell>");
				if(involucradoDTO.getNombresDemograficoDTO().get(0).getEdadAproximada() != null){
					writer.print(involucradoDTO.getNombresDemograficoDTO().get(0).getEdadAproximada() >= 18 ? "Si" : "No");
				}else{
					writer.print("No");
				}
				writer.print("</cell>");
				
				writer.print("</row>");
			}
			writer.print("</rows>");
			writer.flush();
			writer.close();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return null;
	}
	
	/**
	 * Metodo utilizado para eliminar un Probable Responsable.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward eliminarProbableResponsable(
			ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			log.info("ejecutando Action eliminarProbableResponsable");

			Long expedienteId = new Long(request.getParameter("expedienteId"));
			String numeroExpediente = request.getParameter("numeroExpediente");
			Long involucradoId = new Long(request.getParameter("involucradoId"));
			log.debug("expedienteId ... " + expedienteId);
			log.debug("numeroExpediente ... " + numeroExpediente);
			ExpedienteDTO expedienteDTO = expedienteDelegate.obtenerExpedientePorNumeroExpediente(numeroExpediente);
			log.debug("dto [expediente] ... " + expedienteDTO);

			List<InvolucradoDTO> lstInvolucradoDTO = expedienteDTO.getInvolucradoByCalidad(Calidades.PROBABLE_RESPONSABLE_PERSONA); 
			for(InvolucradoDTO involucradoDTO : lstInvolucradoDTO){
				if(involucradoDTO.getElementoId().equals(involucradoId)){
					involucradoDTO.setEsActivo(false);
					detencionDelegate.eliminarInvolucrado(involucradoDTO);
				}
			}
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.flush();
			writer.close();			
		} catch (Exception e) {
			log.info(e.getCause(), e);
		}
		return null;
	}

	/**
	 * Método utilizado para consultar las detenciones de acuerdo a un expediente proporcionado.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarLugarDeDetencionPorNumeroExpediente
				(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.debug("ejecutando Action consultar Lugar de Detencion por Numero de Expediente");						
			String numeroExpediente = request.getParameter("numeroExpediente");
			log.debug("numeroExpediente ... " + numeroExpediente);
			ExpedienteDTO expedienteDTO = expedienteDelegate.obtenerExpedientePorNumeroExpediente(numeroExpediente);
			log.debug("dto [expediente] ... " + expedienteDTO);

			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();

			List<InvolucradoDTO> lstInvolucradoDTO = expedienteDTO.getInvolucradoByCalidad(Calidades.PROBABLE_RESPONSABLE_PERSONA);

			writer.print("<rows>");		

			int lTotalRegistros = 0;

			for(InvolucradoDTO involucradoDTO : lstInvolucradoDTO){
				DetencionDTO detencionDTO = involucradoDTO.getDetenciones().get(0); 

				if(involucradoDTO.getEsDetenido() && detencionDTO.getLugarDetencionDTO() != null){
					writer.print("<row id='"+involucradoDTO.getElementoId()+ "'>");
					writer.print("<cell>"+ involucradoDTO.getNombresDemograficoDTO().get(0).getNombre() 
										 + " " 
										 + involucradoDTO.getNombresDemograficoDTO().get(0).getApellidoPaterno()
										 + " "
										 + involucradoDTO.getNombresDemograficoDTO().get(0).getApellidoMaterno() +"</cell>");
					
					writer.print("<cell>");
					if(detencionDTO.getLugarDetencionDTO() != null){
						writer.print(detencionDTO.getObservaciones());
					}else{
						writer.print("---");
					}
					writer.print("</cell>");
					
					writer.print("</row>");
					
					lTotalRegistros = lTotalRegistros + 1;
				}
			}
			
			writer.print("<records>" + lTotalRegistros + "</records>");
			writer.print("</rows>");				
			
			writer.flush();
			writer.close();

		} catch (Exception e) {
		    log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Metodo utilizado para agregar un Probable Responsable a detención. 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward agregarLugarDeDetencion(
			ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			log.debug("ejecutando Action agregar Lugar de Detencion");						
			String numeroExpediente = request.getParameter("numeroExpediente");
			Long involucradoId = new Long(request.getParameter("involucradoId"));
			log.debug("numeroExpediente ... " + numeroExpediente);
			log.debug("involucradoId ... " + involucradoId);
			String entidadFederativa = request.getParameter("entidadFederativa");
			String pais = request.getParameter("pais");
			String delegacionMunicipio = request.getParameter("delegacionMunicipio");
			String ciudad = request.getParameter("ciudad");
			String asentamientoColonia = request.getParameter("asentamientoColonia");
			String tipoAsentamiento = request.getParameter("tipoAsentamiento");
			String calle = request.getParameter("calle");
			String numExterior = request.getParameter("numExterior");
			String numInterior = request.getParameter("numInterior");
			String entreCalle = request.getParameter("entreCalle"); 
			String ycalle = request.getParameter("ycalle");
			String aliasDomicilio = request.getParameter("aliasDomicilio");
			String edificio = request.getParameter("edificio");
			String referencias = request.getParameter("referencias");
			String tipoCalle = request.getParameter("tipoCalle");
			String codigoPostal = request.getParameter("codigoPostal");
			
			ExpedienteDTO expedienteDTO = expedienteDelegate.obtenerExpedientePorNumeroExpediente(numeroExpediente);
			log.debug("dto [expediente] ... " + expedienteDTO);
			
			List<InvolucradoDTO> lstInvolucradoDTO = expedienteDTO.getInvolucradoByCalidad(Calidades.PROBABLE_RESPONSABLE_PERSONA); 
			for(InvolucradoDTO involucradoDTO : lstInvolucradoDTO){
				if(involucradoDTO.getElementoId().equals(involucradoId)){
					Long detencionId = involucradoDTO.getDetenciones().get(0).getDetencionId();
					ValorDTO valorGenerico = new ValorDTO();
					DomicilioDTO domicilioDTO = new DomicilioDTO();
					AsentamientoDTO asentamientoDTO = new AsentamientoDTO();

					EntidadFederativaDTO entidadDTO = new EntidadFederativaDTO();
					if (!entidadFederativa.equals("") && !entidadFederativa.equals("-1")) {
						entidadDTO.setEntidadFederativaId(new Long(entidadFederativa));
					}

					if (!pais.equals("") && !pais.equals("-1")) {
						valorGenerico = new ValorDTO();
						valorGenerico.setValor(pais);
						entidadDTO.setValorIdPais(valorGenerico);
					}
					if (!delegacionMunicipio.equals("") && !delegacionMunicipio.equals("-1")) {
						MunicipioDTO municipioDTO = new MunicipioDTO(new Long(delegacionMunicipio), "", entidadDTO);
						asentamientoDTO.setMunicipioDTO(municipioDTO);
					}

					CiudadDTO ciudadDTO = new CiudadDTO();
					if (!ciudad.equals("") && !ciudad.equals("-1")) {
						ciudadDTO.setCiudadId(new Long(ciudad));
					}
					asentamientoDTO.setCiudadDTO(ciudadDTO);

					if (!asentamientoColonia.equals("")	&& !asentamientoColonia.equals("-1")) {
						asentamientoDTO.setAsentamientoId(new Long(asentamientoColonia));
					}
					if (!tipoAsentamiento.equals("") && !tipoAsentamiento.equals("-1")) {
						TipoAsentamientoDTO tipoAsentamientoDTO = new TipoAsentamientoDTO(Long.parseLong(tipoAsentamiento), "");
						asentamientoDTO.setTipoAsentamientoDTO(tipoAsentamientoDTO);
					}
					
					domicilioDTO.setAsentamientoDTO(asentamientoDTO);
					log.info("&&&&domicilio.getAsentamientoDTO(asentamientoDTO):"+domicilioDTO.getAsentamientoDTO());
					domicilioDTO.setCalle(calle);
					log.info("&&&&domicilio.getCalle():"+domicilioDTO.getCalle());
					domicilioDTO.setNumeroExterior(numExterior);
					domicilioDTO.setNumeroInterior(numInterior);
					domicilioDTO.setEntreCalle1(entreCalle);
					domicilioDTO.setEntreCalle2(ycalle);
					domicilioDTO.setAlias(aliasDomicilio);
					domicilioDTO.setEdificio(edificio);
					domicilioDTO.setReferencias(referencias);
					if (tipoCalle.equals("") && !tipoCalle.equals("-1")) {
						domicilioDTO.setValorCalleId(new ValorDTO(new Long(tipoCalle)));// Tipo de calle
					}

					CalidadDTO calidadDTO = new CalidadDTO();
					calidadDTO.setCalidades(Calidades.DOMICILIO);
					domicilioDTO.setCalidadDTO(calidadDTO);

					involucradoDTO.getDetenciones().get(0).setObservaciones(domicilioDTO.getCalle() + " " + domicilioDTO.getNumeroExterior());
					involucradoDTO.getDetenciones().get(0).setLugarDetencionDTO(domicilioDTO);
					
					Long resp=null;
					resp = detencionDelegate.registrarLugarDetencion(involucradoDTO.getDetenciones().get(0));
					/*
					 * Long mx.gob.segob.nsjp.delegate.detencion.DetencionDelegate.registrarLugarDetencion(DetencionDTO detencion) throws NSJPNegocioException

						Registra el lugar dela detencion.
						
						Parameters:
						detencion obligatorios detencionId y lugarDetencionDTO.
						Returns:
						El id del lugar registrado
						Throws:
						NSJPNegocioException
					 */					
					log.info("el valor de la respuesta es:" + resp);
				}
			}
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.flush();
			writer.close();			
		} catch (Exception e) {
			log.info(e.getCause(), e);
		}
		return null;
	}

	/**
	 * Metodo utilizado para cancelar una Detención.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward eliminarDetencion(
			ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			log.info("ejecutando Action eliminarDetencion");
		} catch (Exception e) {
			log.info(e.getCause(), e);
		}
		return null;
	}

	/**
	 * Método utilizado para consultar las pertenencias de probables resonsables de acuerdo a un expediente proporcionado.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public ActionForward consultarPertenenciasPorDetencionId
				(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.debug("ejecutando Action consultar Pertenencias por Expediente");
						
			Long expedienteId = new Long(request.getParameter("expedienteId"));
			String numeroExpediente = request.getParameter("numeroExpediente");
			log.debug("expedienteId ... " + expedienteId);
			log.debug("numeroExpediente ... " + numeroExpediente);
			ExpedienteDTO expedienteDTO = expedienteDelegate.obtenerExpedientePorNumeroExpediente(numeroExpediente);
			log.debug("dto [expediente] ... " + expedienteDTO);
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();

			List<InvolucradoDTO> lstInvolucradoDTO = expedienteDTO.getInvolucradoByCalidad(Calidades.PROBABLE_RESPONSABLE_PERSONA);
			writer.print("<rows>");		

			int lTotalRegistros = 0;

			for(InvolucradoDTO involucradoDTO : lstInvolucradoDTO){
				if(involucradoDTO.getEsDetenido()){
					Long detencionId = involucradoDTO.getDetenciones().get(0).getDetencionId();
					List<PertenenciaDTO> lstPertenenciaDTO = detencionDelegate.consultarPertenenciaByDetencion(detencionId);
					for(PertenenciaDTO pertenenciaDTO : lstPertenenciaDTO){					
						writer.print("<row id='"+pertenenciaDTO.getPertenenciaId()+ "'>");
	
						writer.print("<cell>");
						if(involucradoDTO.getNombreCompleto() != null){
							writer.print(involucradoDTO.getNombreCompleto());
						}else{
							writer.print(involucradoDTO.getNombresDemograficoDTO().get(0).getNombre()
									     + " " + involucradoDTO.getNombresDemograficoDTO().get(0).getApellidoPaterno()
									     + " " + involucradoDTO.getNombresDemograficoDTO().get(0).getApellidoMaterno());
						}
						writer.print("</cell>");
						
						writer.print("<cell>");
						if(pertenenciaDTO.getCantidad() != null){
							writer.print(pertenenciaDTO.getCantidad());
						}else{
							writer.print("---");
						}
						writer.print("</cell>");
	
						writer.print("<cell>");
						if(pertenenciaDTO.getTipoPertenencia() != null){
							writer.print(pertenenciaDTO.getTipoPertenencia().getValor());
						}else{
							writer.print("---");
						}
						writer.print("</cell>");
	
						writer.print("<cell>");
						if(pertenenciaDTO.getCondicionFisica() != null){
							writer.print(pertenenciaDTO.getCondicionFisica().getValor());
						}else{
							writer.print("---");
						}
						writer.print("</cell>");
						
						writer.print("<cell>");
						if(pertenenciaDTO.getDescripcion() != null){
							writer.print(pertenenciaDTO.getDescripcion());
						}else{
							writer.print("---");
						}
						writer.print("</cell>");
											
						writer.print("</row>");
						
						lTotalRegistros = lTotalRegistros + 1;
					}
				}
			}
			writer.print("<records>" + lTotalRegistros + "</records>");
			writer.print("</rows>");				
			
			writer.flush();
			writer.close();

		} catch (Exception e) {
		    log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Metodo utilizado para agregar una pertenencia del Probable Responsable. 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward agregarPertenencia(
			ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			
			log.debug("ejecutando Action agregar Pertenencias por Expediente");
			
			Long expedienteId = new Long(request.getParameter("expedienteId"));
			String numeroExpediente = request.getParameter("numeroExpediente");
			log.debug("expedienteId ... " + expedienteId);
			log.debug("numeroExpediente ... " + numeroExpediente);
			Long involucradoId = new Long(request.getParameter("probableResponsableId"));
			Short cantidad = new Short(request.getParameter("cantidad"));
			Long tipoId = new Long(request.getParameter("tipoId"));
			Long condicionFisicaId = new Long(request.getParameter("condicionFisicaId"));
			String descripcion = request.getParameter("descripcion");
			
			ExpedienteDTO expedienteDTO = expedienteDelegate.obtenerExpedientePorNumeroExpediente(numeroExpediente);
			log.debug("dto [expediente] ... " + expedienteDTO);
			
			List<InvolucradoDTO> lstInvolucradoDTO = expedienteDTO.getInvolucradoByCalidad(Calidades.PROBABLE_RESPONSABLE_PERSONA); 
			for(InvolucradoDTO involucradoDTO : lstInvolucradoDTO){
				if(involucradoDTO.getElementoId().equals(involucradoId)){
					PertenenciaDTO pertenenciaDTO = new PertenenciaDTO();
					pertenenciaDTO.setCantidad(cantidad);
					ValorDTO valorDTO = new ValorDTO();
					valorDTO.setIdCampo(tipoId);
					pertenenciaDTO.setTipoPertenencia(valorDTO);
					valorDTO = new ValorDTO();
					valorDTO.setIdCampo(condicionFisicaId);
					pertenenciaDTO.setCondicionFisica(valorDTO);
					pertenenciaDTO.setDescripcion(descripcion);
					pertenenciaDTO.setDetencion(involucradoDTO.getDetenciones().get(0));					
					
					Long resp=null;
					resp = detencionDelegate.registrarPertenecia(pertenenciaDTO);
					log.info("el valor de la respuesta es:" + resp);
				}
			}
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.flush();
			writer.close();			
		} catch (Exception e) {
			log.info(e.getCause(), e);
		}
		return null;
	}

	/**
	 * Metodo utilizado para cancelar una Pertenencia.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward eliminarPertenencia(
			ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			log.info("ejecutando Action eliminarPertenencia");
		} catch (Exception e) {
			log.info(e.getCause(), e);
		}
		return null;
	}

	/**
	 * Metodo utilizado para enviar el registro de detención a la unidad de captura.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward enviarAUnidadDeCaptura(
			ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			
			log.info("ejecutando Action enviarAUnidadDeCaptura");
			
			String numeroExpediente = request.getParameter("numeroExpediente");
			log.debug("numeroExpediente ... " + numeroExpediente);			
			ExpedienteDTO expedienteDTO = expedienteDelegate.obtenerExpedientePorNumeroExpediente(numeroExpediente);
			log.debug("dto [expediente] ... " + expedienteDTO);
			
			UsuarioDTO usuario = getUsuarioFirmado(request);
			expedienteDTO.setUsuario(usuario);
			
			InformePolicialHomologadoDTO iph = informePolicialHomologadoDelegate.ObtenerFolioIPH(expedienteDTO);
			super.setExpedienteTrabajo(request, iph.getExpediente());
			log.info("iph.getExpediente().getNumeroExpedienteId() ... " + iph.getExpediente().getNumeroExpedienteId());
			log.info("iph.getExpediente().getExpedienteId() ... " + iph.getExpediente().getExpedienteId());
			//request.getSession().setAttribute("numeroExpedienteId", iph.getExpediente().getNumeroExpedienteId());
			converter.alias("iphDTO", InformePolicialHomologadoDTO.class);
			String xml = converter.toXML(iph);
			log.info("respuesta generar folio IPH ------- "+xml);

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
	 * Metodo utilizado para mostrar el IPH.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward mostrarIPH(
			ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		return null;
	}	
	
}

/**
 * Nombre del Programa 	: ConsultarSolicitudSSAction                                    
 * Autor               	: Cuauhtemoc Paredes                                              
 * Compania            	: Ultrasist                                                
 * Proyecto            	: NSJP              			Fecha:07/06/2011 
 * Marca de cambio     	: N/A                                                     
 * Descripcion General   : Clase para consulta de solicitudes de mandato
 * Programa Dependiente  : N/A                                                      
 * Programa Subsecuente  : N/A                                                      
 * Cond. de ejecucion    : N/A                                                  
 * Dias de ejecucion     : N/A                             Horario: N/A
 *                               MODIFICACIONES                                       
 *------------------------------------------------------------------------------           
 * Autor                 :N/A                                                           
 * Compania              :N/A                                                           
 * Proyecto              :N/A                                   Fecha: N/A       
 * Modificacion          :N/A                                                           
 *------------------------------------------------------------------------------      
 */
package mx.gob.segob.nsjp.web.solicitud.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.catalogo.Catalogos;
import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.enums.quejaciudadana.EstatusQueja;
import mx.gob.segob.nsjp.comun.enums.quejaciudadana.MotivoRechazo;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.expediente.ExpedienteDelegate;
import mx.gob.segob.nsjp.delegate.funcionario.FuncionarioDelegate;
import mx.gob.segob.nsjp.delegate.quejaciudadana.QuejaCiudadanaDelegate;
import mx.gob.segob.nsjp.delegate.solicitud.SolicitudDelegate;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.persona.CorreoElectronicoDTO;
import mx.gob.segob.nsjp.dto.persona.MedioDeContactoDTO;
import mx.gob.segob.nsjp.dto.persona.TelefonoDTO;
import mx.gob.segob.nsjp.dto.quejaciudadana.QuejaCiudadanaDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudMandamientoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Clase que implementa las acciones para consultar solicitudes de audiencia o
 * de otro tipo.
 * 
 * @version 1.0
 * @author Cuauhtemoc Paredes
 * 
 */
public class ConsultarSolicitudSSPAction extends GenericAction {

	/** Log de clase */
	private static final Logger log = Logger
			.getLogger(ConsultarSolicitudSSPAction.class);

	@Autowired
	public SolicitudDelegate solicitudDelegate;
	
	@Autowired
	public ExpedienteDelegate expedienteDelegate; 
	
	@Autowired 
	public QuejaCiudadanaDelegate quejaCiudadanaDelegate;
		
	@Autowired 
	public FuncionarioDelegate funcionarioDelegate;

	/** 
	 * Metodo utilizado para consultar el numero de salas
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws IOException
	 * En caso de obtener una exception
	 */
	public ActionForward consultarSolicitudesMandatoJudicial(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {

			log.info("EJECUTANDO ACTION ---- CONSULTAR SOLICITUDES MANDATO JUDICIAL");
			
			
			List<SolicitudMandamientoDTO> solicitudMandamientoDTOs = solicitudDelegate.consultarSolicitudesMandatoJudicial();
			
			log.info("Solicitudes de mandamiento"+solicitudMandamientoDTOs);
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			
			writer.print("<rows>");
			
			int lTotalRegistros=solicitudMandamientoDTOs.size();
			
			writer.print("<records>" + lTotalRegistros + "</records>");
				for (SolicitudMandamientoDTO mandamientoDTO  : solicitudMandamientoDTOs) {
					
					log.info("Solicitud"+mandamientoDTO);
					
						writer.print("<row id='" + mandamientoDTO.getDocumentoId()+ "'>");
						writer.print("<cell>" + "-"+ "</cell>");
						writer.print("<cell>" + "-"+ "</cell>");	
						writer.print("<cell>");
						
						if(mandamientoDTO.getInvolucradoDTO()!=null && mandamientoDTO.getInvolucradoDTO().getDelitosCometidos()!=null){
							
						for (DelitoDTO delitoDTO : mandamientoDTO.getInvolucradoDTO().getDelitosCometidos()) {
							
							writer.print(delitoDTO.getCatDelitoDTO().getNombre()+"  ");
							
							}
						}else{
							
							writer.print("-");
						}
						
							writer.print("</cell>");						
						
							writer.print("<cell>" + mandamientoDTO .getTipoMandamiento().getValor()+ "</cell>");
							writer.print("<cell>" + "-"  + "</cell>");
							writer.print("<cell>" + mandamientoDTO.getFechaCreacion()  + "</cell>");
												
					writer.print("</row>");
				}			
			writer.print("</rows>");
			writer.flush();
			writer.close();

		} catch (Exception e) {
			log.info(e.getCause(), e);

		}
		return null;
	}
	
	
	/**
	 * Metodo utilizado para genera numero
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws IOException
	 * En caso de obtener una exception
	 */
	public ActionForward generarExpedienteSSPPolicia(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			
			log.info("Solicitudes no Atendidas");
//			ExpedienteDelegate
//			public ExpedienteDTO asignarNumeroExpediente(ExpedienteDTO expedienteDTO)
//			throws NSJPNegocioException;
			
			ExpedienteDTO expedienteDTO = new ExpedienteDTO();
			AreaDTO areaDTO = new AreaDTO();
			
			areaDTO.setAreaId(Instituciones.SSP.getValorId());
			expedienteDTO.setArea(areaDTO);
			UsuarioDTO usuario =getUsuarioFirmado(request);
			expedienteDTO.setUsuario(usuario);
			expedienteDTO = expedienteDelegate.asignarNumeroExpediente(expedienteDTO);
			setExpedienteTrabajo(request, expedienteDTO);
			
			
			log.info("Expediente de trabajo ;;;;;"+getExpedienteTrabajo(request, "expedienteDTO"));
			String xml = null;
			PrintWriter pw = null;
			//converter.alias("defensorDTOs",  java.util.List.class);
			converter.alias("expedienteDTO", ExpedienteDTO.class);
			xml = converter.toXML(expedienteDTO);
			response.setContentType("text/xml");
			pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
			log.info("Regreso del Expediente" + expedienteDTO );	
						
			

		} catch (Exception e) {
		    log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Metodo utilizado para genera numero
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws IOException
	 * En caso de obtener una exception
	 */
	public ActionForward guardaQejaCiudadana(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		log.info("Entra queja Ciudadana :::::::");
		String descripcion = request.getParameter("descripcion");
		log.info("descripcion======" + descripcion);
		
		String numExpediente = request.getParameter("numExpediente");
		log.info("numExpediente====" + numExpediente);
		
		String quejaAnonima = request.getParameter("quejaAnonima");
		log.info("quejaAnonima====" + quejaAnonima);
		
		String tipoQueja = request.getParameter("tipoQueja");
		log.info("tipoQueja====" + tipoQueja);
		
		//String idQuejoso = request.getParameter("idQuejoso");
		//log.info("idQuejoso====" + idQuejoso);

		String nombreQuejoso = request.getParameter("nombreQuejoso");
		log.info("nombreQuejoso====" + nombreQuejoso);
		
		String aPQuejoso = request.getParameter("aPQuejoso");
		log.info("aPQuejoso====" + aPQuejoso);
		
		String aMQuejoso = request.getParameter("aMQuejoso");
		log.info("aMQuejoso====" + aMQuejoso);
		
		String nombreFuncionario = request.getParameter("nombreFuncionario");
		log.info("nombreFuncionario====" + nombreFuncionario);
		
		String aPFuncionario = request.getParameter("aPFuncionario");
		log.info("aPFuncionario====" + aPFuncionario);
		
		String aMFuncionario = request.getParameter("aMFuncionario");
		log.info("aMFuncionario====" + aMFuncionario);
		
		String nombreAfectado = request.getParameter("nombreAfectado");
		log.info("nombreAfectado====" + nombreAfectado);
		
		String aPAfectado = request.getParameter("aPAfectado");
		log.info("aPAfectado====" + aPAfectado);
		
		String aMAfectado = request.getParameter("aMAfectado");
		log.info("aMAfectado====" + aMAfectado);
		
		String calidadAfectado = request.getParameter("calidadAfectado");
		log.info("calidadAfectado====" + calidadAfectado);

		//medios de contacto
		String medioContactoTelefono = request.getParameter("medioContactoTelefono");
		String medioContactoCorreo = request.getParameter("medioContactoCorreo");
		
		try {
			
			log.info("Entra queja Ciudadana :::::::");

			ValorDTO valorDTO =new ValorDTO();
			
			valorDTO.setIdCampo(NumberUtils.toLong(tipoQueja));
			QuejaCiudadanaDTO quejaDTO = new QuejaCiudadanaDTO();
			quejaDTO.setTipoQuejaDTO(valorDTO);

			quejaDTO.setDescripcionQueja(descripcion);
			quejaDTO.setNumeroExpediente(numExpediente);
			
			//if(quejaAnonima == "false"){
				quejaDTO.setNombreQuejoso(nombreQuejoso);
				quejaDTO.setApellidoPatQuejoso(aPQuejoso);
				quejaDTO.setApellidoMatQuejoso(aMQuejoso);
			//}

			quejaDTO.setNombreDenunciado(nombreFuncionario);
			quejaDTO.setApellidoPatDenunciado(aPFuncionario);
			quejaDTO.setApellidoMatDenunciado(aMFuncionario);
			
			quejaDTO.setNombreAfectado(nombreAfectado);
			quejaDTO.setApellidoPatAfectado(aPAfectado);
			quejaDTO.setApellidoMatAfectado(aMAfectado);
			
			valorDTO =new ValorDTO();
			valorDTO.setIdCampo(NumberUtils.toLong(calidadAfectado));
			quejaDTO.setCalidadAfectado(valorDTO);
			
			List<MedioDeContactoDTO> mediosDenunciado = new ArrayList<MedioDeContactoDTO>();
			quejaDTO.setMediosDenunciado(mediosDenunciado);

			List<MedioDeContactoDTO> lstTelefonos = new ArrayList<MedioDeContactoDTO>();
			String strTelefonos = medioContactoTelefono;
			StringTokenizer lstStrTelefonos = new StringTokenizer(strTelefonos,
					"|");
			while (lstStrTelefonos.hasMoreElements()) {
				String strTelefono = (String) lstStrTelefonos.nextElement();
				String[] datosTelefono = strTelefono.split(",");

				TelefonoDTO telefono = new TelefonoDTO();

				ValorDTO valorTipoTelefono = new ValorDTO();
				if(datosTelefono.length!=0){
					valorTipoTelefono.setIdCampo(new Long(datosTelefono[0]));
					log.info("&&&&Telefono:"+datosTelefono[0]);
					telefono.setValorTipoTelefono(valorTipoTelefono);
					telefono.setCodigoPais(datosTelefono[1]);
					log.info("&&&&Telefono:"+datosTelefono[1]);
					telefono.setCodigoArea(datosTelefono[2]);
					log.info("&&&&Telefono:"+datosTelefono[2]);
					telefono.setNumeroTelefonico(datosTelefono[3]);
					log.info("&&&&Telefono:"+datosTelefono[3]);
					lstTelefonos.add(telefono);
				}
				
			}
			if(!medioContactoCorreo.trim().isEmpty()){
				String[] datosCorreo = medioContactoCorreo.split(",");
				for (int i = 0; i < datosCorreo.length; i++) {
					CorreoElectronicoDTO correo = new CorreoElectronicoDTO();
					correo.setDireccionElectronica(datosCorreo[i]);
					lstTelefonos.add(correo);
				}
			}

			quejaDTO.setMediosDenunciado(lstTelefonos);

			Long queja = quejaCiudadanaDelegate.registrarQuejaCiudadana(quejaDTO);
			
			String xml = null;
			PrintWriter pw = null;
			converter.alias("quejaDTO", QuejaCiudadanaDTO.class);
			xml = converter.toXML(queja);
			log.info("XML Queja--- "+xml);
			response.setContentType("text/xml");
			pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
			log.info("Regreso de la queja" + queja );	
						
		} catch (Exception e) {
		    log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * Metodo utilizado para genera numero
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws IOException
	 * En caso de obtener una exception
	 */
	public ActionForward consultaGridQuejasPEndientes(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		
		try{
		List<QuejaCiudadanaDTO> quejaCiudadanaDTOs = new ArrayList<QuejaCiudadanaDTO>();
		Long estatus = EstatusQueja.EN_PROCESO.getValorId();
		quejaCiudadanaDTOs = quejaCiudadanaDelegate.consultarQuejasPorEstatus(estatus);
		
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
        
			for (QuejaCiudadanaDTO quejaCiudadanaDTO  : quejaCiudadanaDTOs) {
				
				log.info("Solicitud"+quejaCiudadanaDTOs);
				
					writer.print("<row id='" + quejaCiudadanaDTO.getQuejaCiudadanaId()+ "'>");
					writer.print("<cell>");
					if(quejaCiudadanaDTO.getFolioQueja()!=null){
						writer.print(quejaCiudadanaDTO.getFolioQueja());
					}else{
						writer.print("-");
					}
					writer.print("</cell>");
					writer.print("<cell>");
					if(quejaCiudadanaDTO.getNombreQuejoso()!=null){
						writer.print(quejaCiudadanaDTO.getNombreQuejoso()+' '+quejaCiudadanaDTO.getApellidoPatQuejoso()+' '+quejaCiudadanaDTO.getApellidoMatQuejoso());
					}else{
						writer.print("-");
					}
					writer.print("</cell>");
					writer.print("<cell>");
					if(quejaCiudadanaDTO.getCalidadAfectado()!=null){
						writer.print(quejaCiudadanaDTO.getCalidadAfectado().getValor());
					}else{
						writer.print("-");
					}
					writer.print("</cell>");
					writer.print("<cell>");
					if(quejaCiudadanaDTO.getNombreDenunciado()!=null){
						writer.print(quejaCiudadanaDTO.getNombreDenunciado()+' '+quejaCiudadanaDTO.getApellidoPatDenunciado()+' '+quejaCiudadanaDTO.getApellidoMatDenunciado());
					}else{
						writer.print("-");
					}
					writer.print("</cell>");		
					writer.print("<cell>");
					if(quejaCiudadanaDTO.getTipoQuejaDTO()!=null){
						writer.print(quejaCiudadanaDTO.getTipoQuejaDTO().getValor());
					}else{
						writer.print("-");
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
	 * Metodo utilizado para genera numero
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws IOException
	 * En caso de obtener una exception
	 */
	public ActionForward consultaGridQuejasConcluidas(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		
		try{
		List<QuejaCiudadanaDTO> quejaCiudadanaDTOs = new ArrayList<QuejaCiudadanaDTO>();
		Long estatus = EstatusQueja.TERMINADA.getValorId();
		quejaCiudadanaDTOs = quejaCiudadanaDelegate.consultarQuejasPorEstatus(estatus);
		
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
        
			for (QuejaCiudadanaDTO quejaCiudadanaDTO  : quejaCiudadanaDTOs) {
				
				log.info("Solicitud"+quejaCiudadanaDTOs);
				
					writer.print("<row id='" + quejaCiudadanaDTO.getQuejaCiudadanaId()+ "'>");
					writer.print("<cell>");
					if(quejaCiudadanaDTO.getFolioQueja()!=null){
						writer.print(quejaCiudadanaDTO.getFolioQueja());
					}else{
						writer.print("-");
					}
					writer.print("</cell>");
					writer.print("<cell>");
					if(quejaCiudadanaDTO.getNombreQuejoso()!=null){
						writer.print(quejaCiudadanaDTO.getNombreQuejoso()+' '+quejaCiudadanaDTO.getApellidoPatQuejoso()+' '+quejaCiudadanaDTO.getApellidoMatQuejoso());
					}else{
						writer.print("-");
					}
					writer.print("</cell>");
					writer.print("<cell>");
					if(quejaCiudadanaDTO.getCalidadAfectado()!=null){
						writer.print(quejaCiudadanaDTO.getCalidadAfectado().getValor());
					}else{
						writer.print("-");
					}
					writer.print("</cell>");
					writer.print("<cell>");
					if(quejaCiudadanaDTO.getNombreDenunciado()!=null){
						writer.print(quejaCiudadanaDTO.getNombreDenunciado()+' '+quejaCiudadanaDTO.getApellidoPatDenunciado()+' '+quejaCiudadanaDTO.getApellidoMatDenunciado());
					}else{
						writer.print("-");
					}
					writer.print("</cell>");		
					writer.print("<cell>");
					if(quejaCiudadanaDTO.getMotivoRechazoDTO() != null){
						writer.print(quejaCiudadanaDTO.getMotivoRechazoDTO().getValor());
					}else{
						writer.print("-");
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
	 * Metodo utilizado para consultarQuejaCiudadanaXIdSSPPolicia
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws IOException
	 * En caso de obtener una exception
	 */
	public ActionForward consultarQuejaCiudadanaXIdSSPPolicia(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		log.info("Entra queja Ciudadana LA CONSULTA:::::::");
		Long idQueja = NumberUtils.toLong(request.getParameter("idQueja")) ;
		log.info("descripcion pasa idQueja====" + idQueja);
		
		try {
			log.info("Entra queja Ciudadana :::::::");
			QuejaCiudadanaDTO quejaCiudadanaDTO = new QuejaCiudadanaDTO();
			
			quejaCiudadanaDTO = quejaCiudadanaDelegate.consultarQuejaCiudadanaXId(idQueja);
			//super.setExpedienteTrabajo(request, quejaCiudadanaDTO.get)
			
			String xml = null;
			PrintWriter pw = null;
			converter.alias("quejaCiudadanaDTO", QuejaCiudadanaDTO.class);
			xml = converter.toXML(quejaCiudadanaDTO);
			log.info("Termina queja Ciudadana :::xml::::"+xml);
			response.setContentType("text/xml");
			pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
		    log.error(e.getMessage(), e);
		}
		return null;
	}
	
	public ActionForward consultarCatalogoTipoQueja(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
    	try {
    		log.info("ejecutando Action Cargar Combo Tipo queja"); 
    		List<CatalogoDTO> listaCatalogo=catDelegate.recuperarCatalogo(Catalogos.TIPO_QUEJA);
    	
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catTipoQueja", CatalogoDTO.class);
			
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

	public ActionForward consultarCatalogoRechazoQueja(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
    	try {
    		log.info("ejecutando Action Cargar Combo Tipo queja"); 
    		List<CatalogoDTO> listaCatalogo=catDelegate.recuperarCatalogo(Catalogos.CAUSA_RECHAZO_QUEJA_CIUDADANA);
    	
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catRechazoQueja", CatalogoDTO.class);
			
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

	/**
	 * Metodo utilizado para asignarMotivoRechazoQuejaCiudadana
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws IOException
	 * En caso de obtener una exception
	 */
	public ActionForward asignarMotivoRechazoQuejaCiudadana(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		Long idQueja = NumberUtils.toLong(request.getParameter("idQueja")) ;
		Long motivoRechazo = NumberUtils.toLong(request.getParameter("rechazoQueja")) ;
		
		try {
			quejaCiudadanaDelegate.asignarMotivoRechazo(idQueja, MotivoRechazo.getByValor(motivoRechazo));
			
			String xml = null;
			PrintWriter pw = null;
			//converter.alias("quejaCiudadanaDTO", QuejaCiudadanaDTO.class);
			//xml = converter.toXML(quejaCiudadanaDTO);
			response.setContentType("text/xml");
			pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
		    log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Metodo utilizado para buscarFuncionarioPorNombre
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws IOException
	 * En caso de obtener una exception
	 */
	public ActionForward buscarFuncionarioPorNombre(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String nomFuncionario = request.getParameter("nomFuncionario");
		
		try {
			FuncionarioDTO funcionarioDTO = funcionarioDelegate.obtenerFuncionarioPorNombreCompleto(nomFuncionario);
			
			String xml = null;
			PrintWriter pw = null;
			converter.alias("funcionarioDTO", FuncionarioDTO.class);
			xml = converter.toXML(funcionarioDTO);
			response.setContentType("text/xml");
			pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
		    log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Metodo utilizado para consultarCatalogoRechazoAviso
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws IOException
	 * En caso de obtener una exception
	 */
	public ActionForward consultarCatalogoRechazoAviso(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
    		List<CatalogoDTO> listaCatalogo=catDelegate.recuperarCatalogo(Catalogos.CAUSA_RECHAZO_AVISO_AUXILIO);
    	
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catRechazoAviso", CatalogoDTO.class);
			
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

}
/**
 * 
 */
package mx.gob.segob.nsjp.web.aviso.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.documento.EstatusNotificacion;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.documento.DocumentoDelegate;
import mx.gob.segob.nsjp.delegate.implicado.ImplicadoDelegate;
import mx.gob.segob.nsjp.delegate.notificacion.NotificacionDelegate;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDelitoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.AvisoHechoDelictivoDTO;
import mx.gob.segob.nsjp.dto.domicilio.AsentamientoDTO;
import mx.gob.segob.nsjp.dto.domicilio.CiudadDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.domicilio.EntidadFederativaDTO;
import mx.gob.segob.nsjp.dto.domicilio.MunicipioDTO;
import mx.gob.segob.nsjp.dto.domicilio.TipoAsentamientoDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.hecho.HechoDTO;
import mx.gob.segob.nsjp.dto.involucrado.ImplicadoDTO;
import mx.gob.segob.nsjp.dto.persona.CorreoElectronicoDTO;
import mx.gob.segob.nsjp.dto.persona.MedioDeContactoDTO;
import mx.gob.segob.nsjp.dto.persona.TelefonoDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;
import mx.gob.segob.nsjp.web.caso.form.IngresarIndividuoForm;

/**
 * @author SergioDC
 *
 */
public class SolicitudApoyoAction extends GenericAction {

    private static final Logger log =
        Logger.getLogger(SolicitudApoyoAction.class);
	@Autowired
	private NotificacionDelegate notificacionDelegate;
	
	@Autowired
	private ImplicadoDelegate implicadoDelegate;

	@Autowired
	public DocumentoDelegate documentoDelegate;
	
	public ActionForward guardarLugarDeLosHechos(ActionMapping mapping, ActionForm form,
	        HttpServletRequest request, HttpServletResponse response)
	        throws IOException {

		String llamadaAnonima = request.getParameter("llamadaAnonima");
		log.info("llamadaAnonima====" + llamadaAnonima);

		String nombreImplicado = request.getParameter("implicadoNombre");
		log.info("nombreAfectado====" + nombreImplicado);
		
		String aPImplicado = request.getParameter("implicadoAPaterno");
		log.info("aPAfectado====" + aPImplicado);
		
		String aMImplicado = request.getParameter("implicadoAMaterno");
		log.info("aMAfectado====" + aMImplicado);
		
		String calidadImplicado = request.getParameter("calidadImplicado");
		log.info("calidadAfectado====" + calidadImplicado);

		String delitoId = request.getParameter("delito");
		log.info("delito====" + delitoId);

		//medios de contacto
		String medioContactoTelefono = request.getParameter("medioContactoTelefono");
		String medioContactoCorreo = request.getParameter("medioContactoCorreo");

		try {
			log.info("guardarLugarDeLosHechos---AvisoHechoDelictivo");
			IngresarIndividuoForm forma = (IngresarIndividuoForm) form;

			ValorDTO valorDTO =new ValorDTO();
			ValorDTO valorGenerico =new ValorDTO();
			AvisoHechoDelictivoDTO avisoHecho = new AvisoHechoDelictivoDTO();
			
			valorDTO =new ValorDTO();
			valorDTO.setIdCampo(NumberUtils.toLong(calidadImplicado));
			avisoHecho.setCalidadImplicado(valorDTO);
			
			CatDelitoDTO catDelito =new CatDelitoDTO();
			catDelito.setCatDelitoId(NumberUtils.toLong(delitoId));
			avisoHecho.setCatDelito(catDelito);
			
			avisoHecho.setNombreImplicado(nombreImplicado);
			avisoHecho.setApellidoPatImplicado(aPImplicado);
			avisoHecho.setApellidoMatImplicado(aMImplicado);

			avisoHecho.setEsAnonimo(Boolean.parseBoolean(llamadaAnonima));
			
			List<MedioDeContactoDTO> mediosImplicado = new ArrayList<MedioDeContactoDTO>();
			avisoHecho.setMediosImplicado(mediosImplicado);

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

			avisoHecho.setMediosImplicado(lstTelefonos);
			
			DomicilioDTO domicilio = new DomicilioDTO();
			AsentamientoDTO asentamientoDTO = new AsentamientoDTO();

			EntidadFederativaDTO estado = new EntidadFederativaDTO();
			if (!forma.getEntidadFederativa().equals("")
					&& !forma.getEntidadFederativa().equals("-1")) {
				estado.setEntidadFederativaId(new Long(forma
						.getEntidadFederativa()));
				domicilio.setEntidadDTO(estado);
			}

			if (!forma.getPais().equals("") && !forma.getPais().equals("-1")) {
				valorGenerico = new ValorDTO();
				valorGenerico.setValor(forma.getPais());
				estado.setValorIdPais(valorGenerico);
			}
			if (!forma.getDelegacionMunicipio().equals("")
					&& !forma.getDelegacionMunicipio().equals("-1")) {
				MunicipioDTO municipio = new MunicipioDTO(new Long(
						forma.getDelegacionMunicipio()), "", estado);
				asentamientoDTO.setMunicipioDTO(municipio);
				domicilio.setMunicipioDTO(municipio);
			}

			CiudadDTO ciudad = new CiudadDTO();
			if (!forma.getCiudad().equals("")
					&& !forma.getCiudad().equals("-1")) {
				ciudad.setCiudadId(new Long(forma.getCiudad()));
			}
			asentamientoDTO.setCiudadDTO(ciudad);
			domicilio.setCiudadDTO(ciudad);

			if (!forma.getAsentamientoColonia().equals("")
					&& !forma.getAsentamientoColonia().equals("-1")) {
				asentamientoDTO.setAsentamientoId(new Long(forma
						.getAsentamientoColonia()));
			}
			if (!forma.getTipoAsentamiento().equals("")
					&& !forma.getTipoAsentamiento().equals("-1")) {
				TipoAsentamientoDTO tipoAsentamientoDTO=new TipoAsentamientoDTO(Long.parseLong(forma.getTipoAsentamiento()), "");
				asentamientoDTO.setTipoAsentamientoDTO(tipoAsentamientoDTO);
			}
			
			domicilio.setAsentamientoDTO(asentamientoDTO);
			log.info("&&&&domicilio.getAsentamientoDTO(asentamientoDTO):"+domicilio.getAsentamientoDTO());
			domicilio.setCalle(forma.getCalle());
			log.info("&&&&domicilio.getCalle():"+domicilio.getCalle());
			domicilio.setNumeroExterior(forma.getNumExterior());
			domicilio.setNumeroInterior(forma.getNumInterior());
			domicilio.setEntreCalle1(forma.getEntreCalle());
			domicilio.setEntreCalle2(forma.getYcalle());
			domicilio.setAlias(forma.getAliasDomicilio());
			domicilio.setEdificio(forma.getEdificio());
			domicilio.setReferencias(forma.getReferencias());
			if (!forma.getTipoCalle().equals("")
					&& !forma.getTipoCalle().equals("-1")) {
				domicilio.setValorCalleId(new ValorDTO(new Long(forma
						.getTipoCalle())));// Tipo de calle
			}

			CalidadDTO calidadDTO = new CalidadDTO();
			calidadDTO.setCalidades(Calidades.DOMICILIO);
			domicilio.setCalidadDTO(calidadDTO);
			
			HechoDTO hechoDTO = new HechoDTO();
			hechoDTO.setLugar(domicilio);
			log.info("&&&&hechoDTO.getLugar():"+hechoDTO.getLugar());
			
			avisoHecho.setHechoDTO(hechoDTO);
			
			
			AvisoHechoDelictivoDTO avisoHechoDTO = notificacionDelegate.ingresarAvisoHechoDeictivoSSP(avisoHecho);
			
			String xml = null;
			PrintWriter pw = null;
			converter.alias("avisoHechoDTO", AvisoHechoDelictivoDTO.class);
			xml = converter.toXML(avisoHechoDTO);
			response.setContentType("text/xml");
			log.info("xml de la Notificacion" + xml );	
			pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
			log.info("resultado de la Notificacion" + avisoHechoDTO );	
						
			
	
		} catch (Exception e) {
		    log.error(e.getMessage(), e);
		    
			String xml = null;
			PrintWriter pw = null;
			AvisoHechoDelictivoDTO avisoHechoDTO = new AvisoHechoDelictivoDTO(0L);			
			converter.alias("avisoHechoDTO", AvisoHechoDelictivoDTO.class);
			xml = converter.toXML(avisoHechoDTO);
			response.setContentType("text/xml");
			log.info("xml de la Notificacion" + xml );	
			pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
			log.info("resultado de la Notificacion" + avisoHechoDTO );	
		}
		return null;	
	}

	public ActionForward consultaGridAvisosAuxilio(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			Long estatus = Long.parseLong(request.getParameter("estatus"));
			log.info("AvisosAuxilio estatus---- "+estatus);

			List<AvisoHechoDelictivoDTO> avisoDTOs = new ArrayList<AvisoHechoDelictivoDTO>();
			avisoDTOs = documentoDelegate.consultarAvisosHDelictivoPorEstatus(estatus);
			log.info("Avisos -- " + avisoDTOs.size());
			
			
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
            
			for(AvisoHechoDelictivoDTO avisoHechoDTO : avisoDTOs){

				writer.print("<row id='"+avisoHechoDTO.getDocumentoId()+ "'>");
				writer.print("<cell>"+ avisoHechoDTO.getFolioNotificacion()+  "</cell>");
				
				writer.print("<cell>");
				if (avisoHechoDTO.getCatDelito()!=null ) {
					writer.print(avisoHechoDTO.getCatDelito().getNombre());
				} else {
					writer.print("---");
				}
				writer.print("</cell>");

				writer.print("<cell>");
				if(avisoHechoDTO.getNombreImplicado() != null && avisoHechoDTO.getApellidoPatImplicado() != null){
					writer.print(avisoHechoDTO.getNombreImplicado() +' '+ avisoHechoDTO.getApellidoPatImplicado() +' '+ avisoHechoDTO.getApellidoMatImplicado());
				}else{
					writer.print("Anónimo");
				}
				writer.print("</cell>");

				writer.print("<cell>");
				if(avisoHechoDTO.getCalidadImplicado() != null){
					writer.print(avisoHechoDTO.getCalidadImplicado().getValor());
				}else{
					writer.print("---");
				}
				writer.print("</cell>");


				writer.print("<cell>");
				if(avisoHechoDTO.getHechoDTO().getDomicilio() != null){
					writer.print(avisoHechoDTO.getHechoDTO().getDomicilio().getCalle());
				}else{
					writer.print("---");
				}
				writer.print("</cell>");
				
				writer.print("<cell>");
				if(avisoHechoDTO.getFechaCreacion() != null){
					Calendar cal = Calendar.getInstance();
					cal.setTime(avisoHechoDTO.getFechaCreacion());
					int day = cal.get(Calendar.DATE);
			        int month = cal.get(Calendar.MONTH) + 1;
			        int year = cal.get(Calendar.YEAR);
					writer.print((day < 10 ? "0" + day : day) + "/" + (month < 10 ? "0" + month : month) + "/" + year);
				}
				writer.print("</cell>");
				
				writer.print("<cell>");
				if(avisoHechoDTO.getFechaCreacion() != null){
					Calendar cal = Calendar.getInstance();
					cal.setTime(avisoHechoDTO.getFechaCreacion());
					int hour = cal.get(Calendar.HOUR_OF_DAY);
			        int minutes = cal.get(Calendar.MINUTE);
			        writer.print((hour < 10 ? "0" + hour : hour) + ":" + (minutes < 10 ? "0" + minutes : minutes));
				}
				writer.print("</cell>");

				writer.print("<cell>");
				if(avisoHechoDTO.getEstatus().getIdCampo().equals(EstatusNotificacion.ATENDIDA.getValorId())){
					if(avisoHechoDTO.getMotivoRechazo() != null){
						writer.print("Aviso Rechazado");
					}else{
						writer.print("Aviso Procesado");
					}
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

	public ActionForward consultarAvisoAuxilioXIdSSPPolicia(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		log.info("Entra aviso auxilio LA CONSULTA:::::::");
		Long avisoId = NumberUtils.toLong(request.getParameter("avisoId")) ;
		log.info("avisoId====" + avisoId);
		
		try {
			log.info("Entra aviso auxilio :::::::");
			AvisoHechoDelictivoDTO avisoHechoDTO = new AvisoHechoDelictivoDTO();
			
			avisoHechoDTO = notificacionDelegate.consultarAvisoHechoXId(avisoId);
			
			String xml = null;
			PrintWriter pw = null;
			converter.alias("avisoHechoDTO", AvisoHechoDelictivoDTO.class);
			xml = converter.toXML(avisoHechoDTO);
			log.info("xml consulta aviso====" + xml);
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
	
	public ActionForward asignarMotivoRechazoAvisoHDelictivo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		Long avisoId = NumberUtils.toLong(request.getParameter("avisoId")) ;
		Long motivoRechazo = NumberUtils.toLong(request.getParameter("rechazoAviso")) ;
		
		try {
			notificacionDelegate.asignarMotivoRechazoHD(avisoId, motivoRechazo);
			
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

	public ActionForward enviarAvisoHDelictivo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			Long avisoId = NumberUtils.toLong(request.getParameter("avisoId")) ;
			AvisoHechoDelictivoDTO avisoHDTO = new AvisoHechoDelictivoDTO();
			avisoHDTO.setDocumentoId(avisoId);
			
			notificacionDelegate.enviarAvisoHDelictivo(avisoHDTO);
			
			String xml = null;
			PrintWriter pw = null;
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

	public ActionForward procesarAvisoHDelictivo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		try {
			Long avisoId = NumberUtils.toLong(request.getParameter("avisoId"));
			AvisoHechoDelictivoDTO avisoHDTO = new AvisoHechoDelictivoDTO();
			avisoHDTO.setDocumentoId(avisoId);
			avisoHDTO.setUsuario(super.getUsuarioFirmado(request));
			
			notificacionDelegate.atenderAvisoHechoDelictivo(avisoHDTO);
			
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

	public ActionForward consultaContactosTelefonoImplicado(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			
			String idImplicado=request.getParameter("idImplicado");
			log.info("%%%%%%%%%%%%Este es el id del idImplicado a consultar: "+idImplicado);
			ImplicadoDTO implicadoDTO=new ImplicadoDTO();
			implicadoDTO.setImplicadoId(Long.parseLong(idImplicado));
			implicadoDTO=implicadoDelegate.consultarMediosDeContactoImplicadoXId(implicadoDTO);
			List<TelefonoDTO> telefonoDTOs = new ArrayList<TelefonoDTO>();

			if (implicadoDTO.getMedioDeContactos() != null) {
				for(MedioDeContactoDTO medio : implicadoDTO.getMedioDeContactos()){
					if( medio instanceof TelefonoDTO){
						TelefonoDTO telefono = ((TelefonoDTO) medio);
						telefonoDTOs.add(telefono);
					}
				}
			}
			
			converter.alias("listaTelefonos", java.util.ArrayList.class);
			converter.alias("TelefonoDTO", TelefonoDTO.class);
			log.info("tels_medios_contacto:: "+converter.toXML(telefonoDTOs));
			
			log.info("Lista de Telefonos" + telefonoDTOs);
			
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			
			writer.print("<rows>");		

			int lTotalRegistros = telefonoDTOs.size();
			writer.print("<records>" + lTotalRegistros + "</records>");

			for(TelefonoDTO tareaDTO2 : telefonoDTOs){

				writer.print("<row id='"+tareaDTO2.getMedioDeContadoId()+ "'>");
				
				writer.print("<cell>");
				if(tareaDTO2.getValorTipoTelefono().getIdCampo() !=null){
					writer.print(tareaDTO2.getValorTipoTelefono().getIdCampo());
						}
				writer.print("</cell>");
				writer.print("<cell>");
				if(tareaDTO2.getValorTipoTelefono() !=null){
					writer.print(tareaDTO2.getValorTipoTelefono().getValor());
						}
				writer.print("</cell>");
				writer.print("<cell>");
				if(tareaDTO2.getCodigoPais() !=null){
					writer.print(tareaDTO2.getCodigoPais());
						}
				writer.print("</cell>");
				
				writer.print("<cell>");
				if(tareaDTO2.getCodigoArea() !=null){
					writer.print(tareaDTO2.getCodigoArea());
						}
				writer.print("</cell>");
				
				writer.print("<cell>");
				if(tareaDTO2.getNumeroTelefonico() !=null){
					writer.print(tareaDTO2.getNumeroTelefonico());
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

	public ActionForward consultaContactosCorreoImplicado(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			
			log.info("Consulta Correos");
			String idImplicado=request.getParameter("idImplicado");
			log.info("%%%%%%%%%%%%Este es el id del idImplicado a consultar: "+idImplicado);
			ImplicadoDTO implicadoDTO=new ImplicadoDTO();
			implicadoDTO.setImplicadoId(Long.parseLong(idImplicado));
			implicadoDTO=implicadoDelegate.consultarMediosDeContactoImplicadoXId(implicadoDTO);

			List<CorreoElectronicoDTO> correoElectronicoDTOs = new ArrayList<CorreoElectronicoDTO>();

			if (implicadoDTO.getMedioDeContactos() != null) {
				for(MedioDeContactoDTO medio : implicadoDTO.getMedioDeContactos()){
					if( medio instanceof CorreoElectronicoDTO){
						CorreoElectronicoDTO correo = ((CorreoElectronicoDTO) medio);
						correoElectronicoDTOs.add(correo);
					}
				}
			}
			
			converter.alias("listaCorreos", java.util.ArrayList.class);
			converter.alias("CorreoElectronicoDTO", CorreoElectronicoDTO.class);
			log.info("corr_medios_contacto:: "+converter.toXML(correoElectronicoDTOs));
			
			log.info("Lista de Correos" + correoElectronicoDTOs);
			
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			
			writer.print("<rows>");		

			int lTotalRegistros = correoElectronicoDTOs.size();
			writer.print("<records>" + lTotalRegistros + "</records>");
			//NSJYUCPROC2011333WT

			for(CorreoElectronicoDTO  tareaDTO2 : correoElectronicoDTOs){

				writer.print("<row id='"+tareaDTO2.getMedioDeContadoId()+ "'>");
				
				writer.print("<cell>");
				if(tareaDTO2.getDireccionElectronica() !=null){
					writer.print(tareaDTO2.getDireccionElectronica());
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

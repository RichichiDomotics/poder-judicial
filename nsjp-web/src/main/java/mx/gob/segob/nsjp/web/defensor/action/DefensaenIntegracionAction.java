package mx.gob.segob.nsjp.web.defensor.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.actividad.Actividades;
import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.enums.involucrado.SituacionJuridica;
import mx.gob.segob.nsjp.delegate.delito.DelitoDelegate;
import mx.gob.segob.nsjp.delegate.documento.DocumentoDelegate;
import mx.gob.segob.nsjp.delegate.involucrado.InvolucradoDelegate;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.domicilio.AsentamientoDTO;
import mx.gob.segob.nsjp.dto.domicilio.CiudadDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioExtranjeroDTO;
import mx.gob.segob.nsjp.dto.domicilio.EntidadFederativaDTO;
import mx.gob.segob.nsjp.dto.domicilio.MunicipioDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.involucrado.DetencionDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.MediaFiliacionDTO;
import mx.gob.segob.nsjp.dto.involucrado.SeniaParticularDTO;
import mx.gob.segob.nsjp.dto.persona.CorreoElectronicoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.dto.persona.TelefonoDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;
import mx.gob.segob.nsjp.web.caso.form.IngresarIndividuoForm;
import mx.gob.segob.nsjp.web.solicitud.form.solicitanteForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

public class DefensaenIntegracionAction extends GenericAction{
	
	@Autowired
	private InvolucradoDelegate involucradoDelegate;
	@Autowired
	private DocumentoDelegate documentoDelegate;
	@Autowired
	private DelitoDelegate delitoDelegate;
	private static final Logger log = Logger.getLogger(DefensaenIntegracionAction.class);
	
	
	public ActionForward guardaInvolucradoIntegracion(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			log.info("/**** GUARDAR INVOLUCRADO EN INTEGRACION ****/");
			
			solicitanteForm formaSolicitante = (solicitanteForm) form;
			
			String invitado=request.getParameter("invitado");
			log.info("&&&&&&&&&&&&&Invitado:"+ invitado);
			
			String idIndividuo=request.getParameter("idIndividuo");
			log.info("&&&&&&&&&&&&&Elemnto id:"+idIndividuo);
			//String numExpediente = request.getParameter("numExpediente");
			//log.info("numExpediente====" + numExpediente);
			String idExpediente = request.getParameter("idExpediente");
			log.info("idExpediente====" + idExpediente);
			//String lugarDelDetenido = request.getParameter("lugarDelDetenido");
			//log.info("lugarDelDetenido====" + lugarDelDetenido);
			String numDelitos = request.getParameter("numDelitos");
			log.info("numDelitos====" + numDelitos);
			//String detenido = request.getParameter("detenido");
			//log.info("Detenido" + detenido);
			//String fechaIngreso = request.getParameter("fechaIngreso");
			//log.info("fechaIngreso" + fechaIngreso);
			//String horaIngreso = request.getParameter("horaIngreso");
			//log.info("horaIngreso" + horaIngreso);			
			
			//domicilio
			String pais = request.getParameter("pais");
			log.info("PAIS="+pais);
			String codigoPostal = request.getParameter("codigoPostal");
			log.info("CP="+codigoPostal);
			String entidadFederativa = request.getParameter("entidadFederativa");
			log.info("ENTIDAD FEDERATIVA="+entidadFederativa);
			String ciudad = request.getParameter("ciudad");
			log.info("CIUDAD="+ciudad);
			String delegacionMunicipio = request.getParameter("delegacionMunicipio");
			log.info("DELEGACION-MUNICIPIO="+delegacionMunicipio);
			String asentamientoColonia = request.getParameter("asentamientoColonia");
			log.info("ASENTAMIENTO COLONIA="+asentamientoColonia);
			String tipoAsentamiento = request.getParameter("tipoAsentamiento");
			log.info("TIPO ASENTAMIENTO="+tipoAsentamiento);
			String tipoCalle = request.getParameter("tipoCalle");
			log.info("TIPO CALLE="+tipoCalle);
			String calle = request.getParameter("calle");
			log.info("CALLE="+calle);
			String numExterior = request.getParameter("numExterior");
			log.info("NUMERO EXTERIOR="+numExterior);
			String numInterior = request.getParameter("numInterior");
			log.info("NUMERO INTERIOR="+numInterior);
			String referencias = request.getParameter("referencias");
			log.info("REFERENCIAS="+referencias);
			String entreCalle = request.getParameter("entreCalle");
			log.info("ENTRE CALLE="+entreCalle);
			String ycalle = request.getParameter("ycalle");
			log.info("Y CALLE="+ycalle);
			String aliasDomicilio = request.getParameter("aliasDomicilio");
			log.info("ALIAS DOMICILIO="+aliasDomicilio);
			String edificio = request.getParameter("edificio");
			log.info("EDIFICIO="+edificio);
			String longitud = request.getParameter("longitud");
			log.info("LONGITUD="+longitud);
			String latitud = request.getParameter("latitud");
			log.info("LATITUD="+latitud);						
					
			//medios de contacto
			String medioContactoTelefono = request.getParameter("medioContactoTelefono");
			log.info("horaIngreso" + medioContactoTelefono);
									
			String medioContactoCorreo = request.getParameter("medioContactoCorreo");
			log.info("horaIngreso" + medioContactoCorreo);
									
			InvolucradoDTO involucradoDTO = new InvolucradoDTO();
			
			if(invitado == "1"){
				involucradoDTO.setValorSituacionJuridica(new ValorDTO(SituacionJuridica.INVITADO.getValorId()));			
			}
			
			//seteamos id del involucrado
			involucradoDTO.setElementoId(Long.parseLong(idIndividuo));
			
			ExpedienteDTO expedienteDTO = new ExpedienteDTO();
			List<DelitoDTO> delitoDTOs = new ArrayList<DelitoDTO>();
			List<DetencionDTO> detencionDTOLista = new ArrayList<DetencionDTO>();
			expedienteDTO.setExpedienteId(Long.parseLong(idExpediente));
			AreaDTO areaDTO = new AreaDTO(Areas.DEFENSORIA);
			expedienteDTO.setArea(areaDTO);
			//expedienteDTO.setNumeroExpediente(numExpediente);
			involucradoDTO.setExpedienteDTO(expedienteDTO);
			            
			DetencionDTO detencionDTO = new DetencionDTO();
			//involucradoDTO.setDelitosCometidos(delitoDTOs);
			
			//Falta Pasar Nombre delito
			
			if(numDelitos!=null){
				String[] idCatDelitos = numDelitos.split(",");			
				for (String idDelito : idCatDelitos) {
					log.info("Delito ID ::: "+idDelito);
					DelitoDTO delitoDTO = new DelitoDTO();
					delitoDTO.setDelitoId(Long.parseLong(idDelito));
					delitoDTO.setEsProbable(false);
					delitoDTO.setEsPrincipal(false);
					delitoDTOs.add(delitoDTO);
				}				
			}
			
			detencionDTOLista.add(detencionDTO);			
			involucradoDTO.setDetenciones(detencionDTOLista);
			//detencionDTO.setLugarDetencionProvicional(lugarDelDetenido);
			
			CalidadDTO calidadDTO = new CalidadDTO();
			calidadDTO.setCalidades(Calidades.PROBABLE_RESPONSABLE_PERSONA);	
						
			involucradoDTO.setCalidadDTO(calidadDTO);
			involucradoDTO.setTipoPersona(new Long(1));
						
            //detencionDTO.setFechaFinDetencion(fechaIngreso);
            //detencionDTO.setHoraFinDetencion(horaIngreso);
			DetencionDTO valorDetencionDTO = new DetencionDTO();
			List<DetencionDTO> listaDetencion = new ArrayList<DetencionDTO>();
			listaDetencion.add(valorDetencionDTO);
			
			DelitoDTO pDelitoDTO = new DelitoDTO();			
			
			expedienteDTO.setDelitoPrincipal(pDelitoDTO);

			//if (detenido.equals("true")) {
			//	involucradoDTO.setEsDetenido(true);
			involucradoDTO.setDelitosCometidos(delitoDTOs);	
				//involucradoDTO.setEsDetenido(Boolean.parseBoolean(detenido));
			//	log.info("numDelitos cometidos====" + delitoDTOs);
			//}
		
			NombreDemograficoDTO nombresDemograficoDTO = new NombreDemograficoDTO();
			/**
			 * CATALOGOS AL ACTION
			 */
			ValorDTO valorIdiomaDTO = new ValorDTO();
			// ValorDTO valorReligionDTO = new ValorDTO();
			ValorDTO valorEscolaridadDTO = new ValorDTO();
			ValorDTO valorEstadoCivilDTO = new ValorDTO();
			// ValorDTO valorSexoDTO = new ValorDTO();
			ValorDTO valorOcupacionDTO = new ValorDTO();
			ValorDTO valorNacionalidadDTO = new ValorDTO();

			nombresDemograficoDTO.setNombre(formaSolicitante.getNombre());
			nombresDemograficoDTO.setApellidoPaterno(formaSolicitante.getApellidoPaterno());
			nombresDemograficoDTO.setApellidoMaterno(formaSolicitante.getApellidoMaterno());
			nombresDemograficoDTO.setCurp(formaSolicitante.getCurp());
			nombresDemograficoDTO.setRfc(formaSolicitante.getRfc());
			//nombresDemograficoDTO.setEsVerdadero(esVerdadero);
			List<NombreDemograficoDTO> listaNombre = new ArrayList<NombreDemograficoDTO>();
			listaNombre.add(nombresDemograficoDTO);

			involucradoDTO.setNombresDemograficoDTO(listaNombre);

			/**
			 * PARSELONG
			 */
			//if(valorIdiomaDTO.setIdCampo!=null)
			valorIdiomaDTO.setIdCampo(Long.parseLong(formaSolicitante.getIdioma()));
			// valorReligionDTO.setIdCampo(Long.parseLong(formaSolicitante.getReligion()));
			valorEscolaridadDTO.setIdCampo(Long.parseLong(formaSolicitante.getEscolaridad()));
			valorEstadoCivilDTO.setIdCampo(Long.parseLong(formaSolicitante.getEstadoCivil()));
			valorOcupacionDTO.setIdCampo(Long.parseLong(formaSolicitante.getOcupacion()));
			valorNacionalidadDTO.setIdCampo(Long.parseLong(formaSolicitante.getNacionalidad()));

			/**
			 * INCORPORANDO A INVOLUCRADO
			 */
			involucradoDTO.setValorIdIdioma(valorIdiomaDTO);
			// involucradoDTO.setValorIdReligion(valorReligionDTO);
			involucradoDTO.setValorIdEscolaridad(valorEscolaridadDTO);
			involucradoDTO.setValorIdEstadoCivil(valorEstadoCivilDTO);
			nombresDemograficoDTO.setSexo(formaSolicitante.getSexo());

			List<ValorDTO> listaOcupacion = new ArrayList<ValorDTO>();
			listaOcupacion.add(valorOcupacionDTO);
			involucradoDTO.setValorIdOcupacion(listaOcupacion);

			List<ValorDTO> listaNacionalidad = new ArrayList<ValorDTO>();
			listaNacionalidad.add(valorNacionalidadDTO);
			involucradoDTO.setValorIdNacionalidad(listaNacionalidad);
			
			DomicilioDTO domicilioDTO = new DomicilioDTO();
						
		//CUANDO EL PAIS ES MEXICO
		if((Long.parseLong(pais)==10 || pais.equalsIgnoreCase("mexico") || pais.equalsIgnoreCase("méxico")) && (Long.parseLong(pais)!= -1L)){
							
			//parte izquierda de la pantalla ingresar domicilio				
				//entidad federativa
			if(!entidadFederativa.equalsIgnoreCase("")){
				
				if(Long.parseLong(entidadFederativa)!= -1L ){
					EntidadFederativaDTO entidadDTO = new EntidadFederativaDTO();
					entidadDTO.setEntidadFederativaId(Long.parseLong(entidadFederativa));
					domicilioDTO.setEntidadDTO(entidadDTO);
				}
			}
			
				//ciudad
			if(!ciudad.equalsIgnoreCase("")){
				
				if(Long.parseLong(ciudad)!= -1L ){
					CiudadDTO ciudadDTO = new CiudadDTO();
					ciudadDTO.setCiudadId(Long.parseLong(ciudad));
					domicilioDTO.setCiudadDTO(ciudadDTO);
				}
			}
				//delegacion-municipio
			if(!delegacionMunicipio.equalsIgnoreCase("")){
				
				if(Long.parseLong(delegacionMunicipio)!= -1L ){
					MunicipioDTO municipioDTO = new MunicipioDTO();
					municipioDTO.setMunicipioId(Long.parseLong(delegacionMunicipio));
					domicilioDTO.setMunicipioDTO(municipioDTO);
				}
			}
				
				//asentamiento-colonia
			if(!asentamientoColonia.equalsIgnoreCase("")){
				
				if(Long.parseLong(asentamientoColonia)!= -1L ){
					AsentamientoDTO asentamientoDTO = new AsentamientoDTO();
					asentamientoDTO.setAsentamientoId(Long.parseLong(asentamientoColonia));
					domicilioDTO.setAsentamientoDTO(asentamientoDTO);		
				}
			}
				
				//tipo de calle
			if(!tipoCalle.equalsIgnoreCase("")){
				
				if(Long.parseLong(tipoCalle) != -1){
					
					ValorDTO valorCalleId = new ValorDTO(Long.parseLong(tipoCalle));
					domicilioDTO.setValorCalleId(valorCalleId);
				}
			}					
			
			//parte derecha de la pantalla ingresar domicilio
			domicilioDTO.setCalle(calle);
			domicilioDTO.setNumeroExterior(numExterior);
			domicilioDTO.setNumeroInterior(numInterior);
			domicilioDTO.setEntreCalle1(entreCalle);
			domicilioDTO.setEntreCalle2(ycalle);
			domicilioDTO.setAlias(aliasDomicilio);
			domicilioDTO.setEdificio(edificio);
			domicilioDTO.setReferencias(referencias);
			
			if( longitud != null && !longitud.equalsIgnoreCase("")){
				domicilioDTO.setLongitud(longitud);
			}
			else{
				domicilioDTO.setLongitud(null);
			}
			if(latitud != null && !latitud.isEmpty()){
				domicilioDTO.setLatitud(latitud);
			}
			else{
				domicilioDTO.setLatitud(null);
			}
							
			//Seteamos la calidad del domicilio
			CalidadDTO calidadDomicilioDTO = new CalidadDTO();
			calidadDomicilioDTO.setCalidades(Calidades.DOMICILIO);
			domicilioDTO.setCalidadDTO(calidadDomicilioDTO);				
			domicilioDTO.setFechaCreacionElemento(new Date());

			//Seteamos el testigo con su domicilio
			involucradoDTO.setDomicilio(domicilioDTO);				

		}
		//CUANDO EL PAIS NO ES MEXICO
		else{
			
			DomicilioExtranjeroDTO domicilioExtranjeroDTO = new DomicilioExtranjeroDTO();
			
			//Parte izq de la pantalla ingresar domicilio
			if(!pais.equalsIgnoreCase("")){
				if(Long.parseLong(pais)!= -1L){
				
						//id del pais
					domicilioExtranjeroDTO.setPais(pais);
				}
			}
			
			domicilioExtranjeroDTO.setCodigoPostal(codigoPostal);
			domicilioExtranjeroDTO.setEstado(entidadFederativa);
			domicilioExtranjeroDTO.setCiudad(ciudad);
			domicilioExtranjeroDTO.setMunicipio(delegacionMunicipio);
			domicilioExtranjeroDTO.setAsentamientoExt(asentamientoColonia);
			
			//parte derecha de la pantalla ingresar domicilio
			domicilioExtranjeroDTO.setCalle(calle);
			domicilioExtranjeroDTO.setNumeroExterior(numExterior);
			domicilioExtranjeroDTO.setNumeroInterior(numInterior);
			domicilioExtranjeroDTO.setEntreCalle1(entreCalle);
			domicilioExtranjeroDTO.setEntreCalle2(ycalle);
			domicilioExtranjeroDTO.setAlias(aliasDomicilio);
			domicilioExtranjeroDTO.setEdificio(edificio);
			domicilioExtranjeroDTO.setReferencias(referencias);
			
			if(!longitud.equalsIgnoreCase("")){
				domicilioExtranjeroDTO.setLongitud(longitud);
			}
			else{
				domicilioExtranjeroDTO.setLongitud(null);
			}
			if(!latitud.equalsIgnoreCase("")){
				domicilioExtranjeroDTO.setLatitud(latitud);
			}
			else{
				domicilioExtranjeroDTO.setLatitud(null);
			}
							
			//Seteamos la calidad del domicilio
			
			CalidadDTO calidadDomicilioExtranjeroDTO = new CalidadDTO();
			calidadDomicilioExtranjeroDTO.setCalidades(Calidades.DOMICILIO);
			domicilioExtranjeroDTO.setCalidadDTO(calidadDomicilioExtranjeroDTO);
			domicilioExtranjeroDTO.setFechaCreacionElemento(new Date());
			//domicilioExtranjeroDTO.setElementoId(domicilioId);
							
			//Seteamos el domicilio extranjero de notificaciones a la persona
			involucradoDTO.setDomicilio(domicilioExtranjeroDTO);
			}				
		
			List<TelefonoDTO> lstTelefonos = new ArrayList<TelefonoDTO>();
			String strTelefonos = medioContactoTelefono;
			StringTokenizer lstStrTelefonos = new StringTokenizer(strTelefonos,	"|");
			
			while (lstStrTelefonos.hasMoreElements()) {
				
				String strTelefono = (String) lstStrTelefonos.nextElement();
				String[] datosTelefono = strTelefono.split(",");
	
				TelefonoDTO telefono = new TelefonoDTO();
	
				ValorDTO valorTipoTelefono = new ValorDTO();
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
			involucradoDTO.setTelefonosDTO(lstTelefonos);
	
			List<CorreoElectronicoDTO> lstCorreos = new ArrayList<CorreoElectronicoDTO>();
			
			if(!medioContactoTelefono.trim().isEmpty()){
				String[] datosCorreo = medioContactoTelefono.split(",");
				for (int i = 0; i < datosCorreo.length; i++) {
					CorreoElectronicoDTO correo = new CorreoElectronicoDTO();
					correo.setDireccionElectronica(datosCorreo[i]);
					lstCorreos.add(correo);
				}
			}
			involucradoDTO.setCorreosDTO(lstCorreos);
			
			Long recuperaInvolucrado;
			recuperaInvolucrado = involucradoDelegate.guardarInvolucrado(involucradoDTO);
			log.info("regresa del servicio Inv" +recuperaInvolucrado);
			
			String xml = null;
			PrintWriter pw = null;
			converter.alias("involucrado", InvolucradoDTO.class);
			
			xml = converter.toXML(recuperaInvolucrado);
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

	
	
	
	
	public ActionForward guardaInvolucradoIntegracionMediaFiliacion(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
		
			IngresarIndividuoForm forma = (IngresarIndividuoForm) form;
		
			String idInvolucrado = request.getParameter("idInvolucrado");
			log.info("idInvolucrado ::"+idInvolucrado);
			
			String idExpediente = request.getParameter("idExpediente");
			log.info("idExpediente ::"+idExpediente);
			
			InvolucradoDTO involucradoDTO = new InvolucradoDTO();
			involucradoDTO.setElementoId(Long.parseLong(idInvolucrado));
			ExpedienteDTO expedienteDTO =new ExpedienteDTO();
			involucradoDTO.setExpedienteDTO(expedienteDTO);
			CalidadDTO calidadDTO = new CalidadDTO();
			calidadDTO.setCalidades(Calidades.PROBABLE_RESPONSABLE_PERSONA);
			involucradoDTO.setCalidadDTO(calidadDTO);
			expedienteDTO.setExpedienteId(Long.parseLong(idExpediente));
			log.info("idExpediente ::"+expedienteDTO);
//			MediaFiliacionDTO mediaFiliacionDTO = new MediaFiliacionDTO();
			
			involucradoDTO.setMediaFiliacionDTO(obtenerMediaFiliacion(forma));
			involucradoDTO.setTipoPersona(new Long(1));
			
			involucradoDTO.setElementoId(involucradoDelegate.guardarInvolucrado(involucradoDTO));
			
			String xml = null;
			PrintWriter pw = null;
			converter.alias("involucrado", InvolucradoDTO.class);
			
			xml = converter.toXML(involucradoDTO);
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
	
	public MediaFiliacionDTO obtenerMediaFiliacion(IngresarIndividuoForm forma){
		//Datos de Media Filiacion Cara
		MediaFiliacionDTO mediaFiliacionDTO = new MediaFiliacionDTO();
		ValorDTO valorGenericoMF = new ValorDTO();
		Long tamanoBoca = 0L;
		if(forma.getTamanoBoca() != null &&  ! forma.getTamanoBoca().equals("-1")){
			tamanoBoca=Long.parseLong(forma.getTamanoBoca());
		}else{
			tamanoBoca=null;
		}
		valorGenericoMF.setIdCampo(tamanoBoca);
		mediaFiliacionDTO.setTamanioBoca(valorGenericoMF);
		
		valorGenericoMF = new ValorDTO();
		Long tipoCara = 0L;
		if(forma.getTipoCara() != null &&  ! forma.getTipoCara() .equals("-1")){
			tipoCara=Long.parseLong(forma.getTipoCara());
		}else{
			tipoCara=null;
		}
		valorGenericoMF.setIdCampo(tipoCara);
		mediaFiliacionDTO.setTipoCara(valorGenericoMF);
		
		valorGenericoMF = new ValorDTO();
		Long formaMenton = 0L;
		if(forma.getFormaMenton() != null &&  ! forma.getFormaMenton() .equals("-1")){
			formaMenton=Long.parseLong(forma.getFormaMenton());
		}else{
			formaMenton=null;
		}
		valorGenericoMF.setIdCampo(formaMenton);
		mediaFiliacionDTO.setFormaMenton(valorGenericoMF);

		valorGenericoMF = new ValorDTO();
		Long tipoMenton = 0L;
		if(forma.getTipoMenton() != null &&  ! forma.getTipoMenton() .equals("-1")){
			tipoMenton=Long.parseLong(forma.getTipoMenton());
		}else{
			tipoMenton=null;
		}
		valorGenericoMF.setIdCampo(tipoMenton);
		mediaFiliacionDTO.setTipoMenton(valorGenericoMF);

		valorGenericoMF = new ValorDTO();
		Long tez = 0L;
		if(forma.getTez() != null &&  ! forma.getTez() .equals("-1")){
			tez=Long.parseLong(forma.getTez());
		}else{
			tez=null;
		}
		valorGenericoMF.setIdCampo(tez);
		mediaFiliacionDTO.setTez(valorGenericoMF);

		valorGenericoMF = new ValorDTO();
		Long inclinacionMenton = 0L;
		if(forma.getInclinacionMenton() != null &&  ! forma.getInclinacionMenton() .equals("-1")){
			inclinacionMenton=Long.parseLong(forma.getInclinacionMenton());
		}else{
			inclinacionMenton=null;
		}
		valorGenericoMF.setIdCampo(inclinacionMenton);
		mediaFiliacionDTO.setInclinacionMenton(valorGenericoMF);

		//Datos de Media Filiacion Cabello
		valorGenericoMF = new ValorDTO();
		Long colorCabello = 0L;
		if(forma.getColorCabello() != null &&  ! forma.getColorCabello() .equals("-1")){
			colorCabello=Long.parseLong(forma.getColorCabello());
		}else{
			colorCabello=null;
		}
		valorGenericoMF.setIdCampo(colorCabello);
		mediaFiliacionDTO.setColorCabello(valorGenericoMF);

		valorGenericoMF = new ValorDTO();
		Long formaCabello = 0L;
		if(forma.getFormaCabello() != null &&  ! forma.getFormaCabello() .equals("-1")){
			formaCabello=Long.parseLong(forma.getFormaCabello());
		}else{
			formaCabello=null;
		}
		valorGenericoMF.setIdCampo(formaCabello);
		mediaFiliacionDTO.setFormaCabello(valorGenericoMF);

		valorGenericoMF = new ValorDTO();
		Long calvieTipo = 0L;
		if(forma.getCalvieTipo() != null &&  ! forma.getCalvieTipo() .equals("-1")){
			calvieTipo=Long.parseLong(forma.getCalvieTipo());
		}else{
			calvieTipo=null;
		}
		valorGenericoMF.setIdCampo(calvieTipo);
		mediaFiliacionDTO.setCalvicieTipo(valorGenericoMF);

		valorGenericoMF = new ValorDTO();
		Long cabelloImplantacion = 0L;
		if(forma.getCabelloImplantacion() != null &&  ! forma.getCabelloImplantacion() .equals("-1")){
			cabelloImplantacion=Long.parseLong(forma.getCabelloImplantacion());
		}else{
			cabelloImplantacion=null;
		}
		valorGenericoMF.setIdCampo(cabelloImplantacion);
		mediaFiliacionDTO.setCabelloImplantacion(valorGenericoMF);

		valorGenericoMF = new ValorDTO();
		Long cantidadCabello = 0L;
		if(forma.getCantidadCabello() != null &&  ! forma.getCantidadCabello() .equals("-1")){
			cantidadCabello=Long.parseLong(forma.getCantidadCabello());
		}else{
			cantidadCabello=null;
		}
		valorGenericoMF.setIdCampo(cantidadCabello);
		mediaFiliacionDTO.setCabelloCantidad(valorGenericoMF);

		//Datos de Media Filiacion Oreja
		valorGenericoMF = new ValorDTO();
		Long tamanoOreja = 0L;
		if(forma.getTamanoOreja() != null &&  ! forma.getTamanoOreja() .equals("-1")){
			tamanoOreja=Long.parseLong(forma.getTamanoOreja());
		}else{
			tamanoOreja=null;
		}
		valorGenericoMF.setIdCampo(tamanoOreja);
		mediaFiliacionDTO.setOrejaTamanio(valorGenericoMF);

		valorGenericoMF = new ValorDTO();
		Long lobuloParticularidad = 0L;
		if(forma.getLobuloParticularidad() != null &&  ! forma.getLobuloParticularidad() .equals("-1")){
			lobuloParticularidad=Long.parseLong(forma.getLobuloParticularidad());
		}else{
			lobuloParticularidad=null;
		}
		valorGenericoMF.setIdCampo(lobuloParticularidad);
		mediaFiliacionDTO.setOrejaLobParticularidad(valorGenericoMF);

		valorGenericoMF = new ValorDTO();
		Long lobuloDimension = 0L;
		if(forma.getLobuloDimension() != null &&  ! forma.getLobuloDimension() .equals("-1")){
			lobuloDimension=Long.parseLong(forma.getLobuloDimension());
		}else{
			lobuloDimension=null;
		}
		valorGenericoMF.setIdCampo(lobuloDimension);
		mediaFiliacionDTO.setOrejaLobDimension(valorGenericoMF);

		valorGenericoMF = new ValorDTO();
		Long lobuloAdherencia = 0L;
		if(forma.getLobuloAdherencia() != null &&  ! forma.getLobuloAdherencia() .equals("-1")){
			lobuloAdherencia=Long.parseLong(forma.getLobuloAdherencia());
		}else{
			lobuloAdherencia=null;
		}
		valorGenericoMF.setIdCampo(lobuloAdherencia);
		mediaFiliacionDTO.setOrejaLobAdherencia(valorGenericoMF);

		valorGenericoMF = new ValorDTO();
		Long helixAnterior = 0L;
		if(forma.getHelixAnterior() != null &&  ! forma.getHelixAnterior() .equals("-1")){
			helixAnterior=Long.parseLong(forma.getHelixAnterior());
		}else{
			helixAnterior=null;
		}
		valorGenericoMF.setIdCampo(helixAnterior);
		mediaFiliacionDTO.setHelixSuperior(valorGenericoMF);

		valorGenericoMF = new ValorDTO();
		Long helixPosterior = 0L;
		if(forma.getHelixPosterior() != null &&  ! forma.getHelixPosterior() .equals("-1")){
			helixPosterior=Long.parseLong(forma.getHelixPosterior());
		}else{
			helixPosterior=null;
		}
		valorGenericoMF.setIdCampo(helixPosterior);
		mediaFiliacionDTO.setHelixPosterior(valorGenericoMF);

		valorGenericoMF = new ValorDTO();
		Long helixContorno = 0L;
		if(forma.getHelixContorno() != null &&  ! forma.getHelixContorno() .equals("-1")){
			helixContorno=Long.parseLong(forma.getHelixContorno());
		}else{
			helixContorno=null;
		}
		valorGenericoMF.setIdCampo(helixContorno);
		mediaFiliacionDTO.setHelixContorno(valorGenericoMF);

		valorGenericoMF = new ValorDTO();
		Long helixAdherencia = 0L;
		if(forma.getHelixAdherencia() != null &&  ! forma.getHelixAdherencia() .equals("-1")){
			helixAdherencia=Long.parseLong(forma.getHelixAdherencia());
		}else{
			helixAdherencia=null;
		}
		valorGenericoMF.setIdCampo(helixAdherencia);
		mediaFiliacionDTO.setHelixAdherencia(valorGenericoMF);

		valorGenericoMF = new ValorDTO();
		Long formaOreja = 0L;
		if(forma.getFormaOreja() != null &&  ! forma.getFormaOreja() .equals("-1")){
			formaOreja=Long.parseLong(forma.getFormaOreja());
		}else{
			formaOreja=null;
		}
		valorGenericoMF.setIdCampo(formaOreja);
		mediaFiliacionDTO.setFormaOreja(valorGenericoMF);

		//Datos de Media Filiacion Ojos
		valorGenericoMF = new ValorDTO();
		Long formaOjos = 0L;
		if(forma.getFormaOjos() != null &&  ! forma.getFormaOjos() .equals("-1")){
			formaOjos=Long.parseLong(forma.getFormaOjos());
		}else{
			formaOjos=null;
		}
		valorGenericoMF.setIdCampo(formaOjos);
		mediaFiliacionDTO.setFormaOjos(valorGenericoMF);

		valorGenericoMF = new ValorDTO();
		Long colorOjos = 0L;
		if(forma.getColorOjos() != null &&  ! forma.getColorOjos() .equals("-1")){
			colorOjos=Long.parseLong(forma.getColorOjos());
		}else{
			colorOjos=null;
		}
		valorGenericoMF.setIdCampo(colorOjos);
		mediaFiliacionDTO.setColorOjos(valorGenericoMF);

		valorGenericoMF = new ValorDTO();
		Long tamanoOjos = 0L;
		if(forma.getTamanoOjos() != null &&  ! forma.getTamanoOjos() .equals("-1")){
			tamanoOjos=Long.parseLong(forma.getTamanoOjos());
		}else{
			tamanoOjos=null;
		}
		valorGenericoMF.setIdCampo(tamanoOjos);
		mediaFiliacionDTO.setTamanioOjo(valorGenericoMF);
		
		
		//cejas
		valorGenericoMF = new ValorDTO();
		Long implantacionCejas = 0L;
		if(forma.getImplantacionCejas() != null &&  ! forma.getImplantacionCejas() .equals("-1")){
			implantacionCejas=Long.parseLong(forma.getImplantacionCejas());
		}else{
			implantacionCejas=null;
		}
		valorGenericoMF.setIdCampo(implantacionCejas);
		mediaFiliacionDTO.setImplantacionCeja(valorGenericoMF);
		
		valorGenericoMF = new ValorDTO();
		Long formaCejas = 0L;
		if(forma.getFormaCejas() != null &&  ! forma.getFormaCejas() .equals("-1")){
			formaCejas=Long.parseLong(forma.getFormaCejas());
		}else{
			formaCejas=null;
		}
		valorGenericoMF.setIdCampo(formaCejas);
		mediaFiliacionDTO.setFormaCeja(valorGenericoMF);
		
		valorGenericoMF = new ValorDTO();
		Long tamanoCejas = 0L;
		if(forma.getTamanoCejas() != null &&  ! forma.getTamanoCejas() .equals("-1")){
			tamanoCejas=Long.parseLong(forma.getTamanoCejas());
		}else{
			tamanoCejas=null;
		}
		valorGenericoMF.setIdCampo(tamanoCejas);
		mediaFiliacionDTO.setTamanioCeja(valorGenericoMF);
		
		SeniaParticularDTO seniaParticularDTO=new SeniaParticularDTO();
		if(forma.getCicatricesSenas().equals("1")){
			seniaParticularDTO.setTieneCicatrices(true);
		}else{
			seniaParticularDTO.setTieneCicatrices(false);
		}
		seniaParticularDTO.setDescripcionCicatrices(forma.getCicatricesSenasText());
		
		if(forma.getProtesisSenas().equals("1")){
			seniaParticularDTO.setTieneProtesis(true);
		}else{
			seniaParticularDTO.setTieneProtesis(false);
		}
		seniaParticularDTO.setDescripcionProtesis(forma.getProtesisSenasText());
		
		if(forma.getTatuajeSenas().equals("1")){
			seniaParticularDTO.setTieneTatuajes(true);
		}else{
			seniaParticularDTO.setTieneTatuajes(false);
		}
		seniaParticularDTO.setDescripcionTatuajes(forma.getTatuajeSenasText());
		seniaParticularDTO.setDescripcionOtraSenia(forma.getOtraSenasText());
		log.info("Lunares:::::::::::::::::"+forma.getLunaresSenas());
		if(forma.getLunaresSenas().equals("1")){
			seniaParticularDTO.setTieneLunares(true);
		}else{
			seniaParticularDTO.setTieneLunares(false);
		}
		seniaParticularDTO.setDescripcionLunares(forma.getLunaresSenasText());
		if(forma.getDefectosSenas().equals("1")){
			seniaParticularDTO.setTieneDefectosFisicos(true);
		}else{
			seniaParticularDTO.setTieneDefectosFisicos(false);
		}
		seniaParticularDTO.setDescripcionDefectosFisicos(forma.getDefectosSenasText());
		mediaFiliacionDTO.setSeniaParticularDTO(seniaParticularDTO);
		if(forma.getLentesOtros().equals("1")){
			mediaFiliacionDTO.setUsaLentes(true);
		}else{
			mediaFiliacionDTO.setUsaLentes(false);
		}
		if(forma.getBarbaOtros().equals("1")){
			mediaFiliacionDTO.setTieneBarba(true);
		}else{
			mediaFiliacionDTO.setTieneBarba(false);
		}
		if(forma.getBigoteOtros().equals("1")){
			mediaFiliacionDTO.setTieneBigote(true);
		}else{
			mediaFiliacionDTO.setTieneBigote(false);
		}
		if(forma.getEstauraOtros()!=null && forma.getEstauraOtros()!=""){
			mediaFiliacionDTO.setEstatura(Double.parseDouble(forma.getEstauraOtros()));
		}
		if(forma.getPesoOtros()!=null && forma.getPesoOtros()!=""){
			mediaFiliacionDTO.setPeso(Double.parseDouble(forma.getPesoOtros()));
		}
		valorGenericoMF = new ValorDTO();
		Long tipoSangre = 0L;
		if(forma.getTipoSangreOtros() != null &&  ! forma.getTipoSangreOtros() .equals("-1")){
			tipoSangre=Long.parseLong(forma.getTipoSangreOtros());
		}else{
			tipoSangre=null;
		}
		valorGenericoMF.setIdCampo(tipoSangre);
		mediaFiliacionDTO.setTipoSangre(valorGenericoMF);
		if(forma.getFactorrhOtros().equals("1")){
			mediaFiliacionDTO.setFactorRH("Positivo");
		}else if(forma.getFactorrhOtros().equals("0")){
			mediaFiliacionDTO.setFactorRH("Negativo");
		}
		
		
		
		
                // Jacob
                mapeaFormaEnDto(forma, "comisuras", mediaFiliacionDTO, "labioComisuras");
                mapeaFormaEnDto(forma, "alturaNasoLabial", mediaFiliacionDTO, "alturaNasoLabial");
                mapeaFormaEnDto(forma, "espesorLabioInferior", mediaFiliacionDTO, "espesorLabioInf");
                mapeaFormaEnDto(forma, "espesorLabioSuperior", mediaFiliacionDTO, "espesorLabioSup");
                mapeaFormaEnDto(forma, "prominencia", mediaFiliacionDTO, "labiosProminencia");
                mapeaFormaEnDto(forma, "anchoNariz", mediaFiliacionDTO, "anchoNariz");
                mapeaFormaEnDto(forma, "alturaNariz", mediaFiliacionDTO, "alturaNariz");
                mapeaFormaEnDto(forma, "baseNariz", mediaFiliacionDTO, "baseNariz");
                mapeaFormaEnDto(forma, "raizNariz", mediaFiliacionDTO, "raizNariz");
                mapeaFormaEnDto(forma, "frenteAltura", mediaFiliacionDTO, "frenteAltura");
                mapeaFormaEnDto(forma, "frenteAncho", mediaFiliacionDTO, "frenteAncho");
                mapeaFormaEnDto(forma, "inclinacionFrente", mediaFiliacionDTO, "frenteInclinacion");
		return mediaFiliacionDTO;
	}
	
	 /**
     * Actualiza el campo {@code campoDelDto} del {@code dtoActualizable}
     * con la informacion del campo {@code campoDeLaForma} tomado del Form
     * {@code formaActualizadora}. Actualmente solo actualiza los campos
     * del tipo ValorDTO, Long y String del dto. <p/>
     * Si el tipo del dto es ValorDTO, se crea un nuevo idCampo de tipo Long
     * a partir del campo indicado de la forma; si el tipo de regreso de
     * la forma es un String, se valida que no sea null y distinto de "-1"
     * y se trata de parsear a un Long, si no es parseable se deja como null;
     * si el tipo de regreso de la forma es un Long este es asignado al
     * idCampo del ValorDTO.<p/>
     * Si el tipo del dto es un Long se obtiene uno a partir del campo
     * de la forma de la misma manera que se obtiene el idCampo de para un
     * ValorDTO. <p/>
     * Si el tipo del dto es un String, se obtiene el campo de la forma y
     * si es distinto de null, se invoca a su metodo toString() y este valor
     * se le asigna al dto.
     * @param formaActualizadora La forma de donde se toma el campo para
     * actualizar al DTO.
     * @param campoDeLaForma El campo de donde se tomara el valor para
     * actualizar el dto.
     * @param dtoActualizable El DTO que sera actualizado.
     * @param campoDelDto El campo del DTO que se actualizara.
     */
    public static void mapeaFormaEnDto(ActionForm formaActualizadora,
            String campoDeLaForma, GenericDTO dtoActualizable, String campoDelDto) {
        boolean mapeado = false;
        Method[] metodos = dtoActualizable.getClass().getMethods();
        for (Method metodo : metodos) {
            metodo.setAccessible(true);
            try {
                String nombreMetodoDto = metodo.getName();
                if (nombreMetodoDto.equals("set" + nombreDelCampoToPrimerUpper(campoDelDto))) {
                    Class<?>[] tipos = metodo.getParameterTypes();
                    if (log.isDebugEnabled()) {
                        log.debug("tipos.length = " + tipos.length);
                    }
                    if (tipos.length == 1) {
                        Class tipoParametro = tipos[0];
                        if (log.isDebugEnabled()) {
                            log.debug("tipoParametro = " + tipoParametro);
                        }
                        // si el set de dto requiere un ValorDTO, lo creamos
                        if (tipoParametro.equals(ValorDTO.class)) {
                            ValorDTO valorDTO = new ValorDTO();
                            Long idCampo = obtenLongDeForma(formaActualizadora, campoDeLaForma);
                            valorDTO.setIdCampo(idCampo);
                            metodo.invoke(dtoActualizable, valorDTO);
                            mapeado = true;
                        }
                        if (tipoParametro.equals(Long.class)) {
                            Long l = obtenLongDeForma(formaActualizadora, campoDeLaForma);
                            metodo.invoke(dtoActualizable, l);
                            mapeado = true;
                        }
                        if (tipoParametro.equals(String.class)) {
                            String parametro = obtenStringDeForma(formaActualizadora, campoDeLaForma);
                            metodo.invoke(dtoActualizable, parametro);
                            mapeado = true;
                        }
                    }
                }
            } catch (IllegalAccessException ex) {
                log.debug(ex);
            } catch (IllegalArgumentException ex) {
                log.debug(ex);
            } catch (InvocationTargetException ex) {
                log.debug(ex);
            }
        }
        if (!mapeado) {
            /**
             * Si se llega a este punto es porque el nombre de un atributo de
             * un ActionForm que se mapeaba con un DTO no corresponde a lo
             * esperado. Se debe verificar cual es el nombre de los campos
             * mapeados y el tipo que espera el setter del dto ya que este metodo
             * solo funciona con los tipos documentados.
             */
            throw new IllegalArgumentException("No fue posible mapear el atributo :"
                    + campoDeLaForma
                    + " de la forma :" + formaActualizadora + " en el campo: "
                    + campoDelDto + " del dto: " + dtoActualizable);
        }
    }

    private static String nombreDelCampoToPrimerUpper(String campoDelDto) {
        return "" + campoDelDto.substring(0, 1).toUpperCase() + campoDelDto.substring(1);
    }

    private static Long obtenLongDeForma(ActionForm formaActualizadora, String campoDeLaForma) {
        Method[] metodos = formaActualizadora.getClass().getMethods();
        Long idCampo = null;
        try {
            for (Method metodo : metodos) {
                metodo.setAccessible(true);
                if (metodo.getName().equals("get" + nombreDelCampoToPrimerUpper(campoDeLaForma))) {
                    Class<?> tipoDeRegreso = metodo.getReturnType();
                    // Si el tipo de regreso de la forma es un String.
                    if (tipoDeRegreso != null) {
                        Object valorDeRegreso = metodo.invoke(formaActualizadora);
                        if (tipoDeRegreso.equals(String.class)) {
                            // ... tratamos de parsear el string a un long en caso que el string sea
                            // distinto de -1
                            if (valorDeRegreso != null && !valorDeRegreso.equals("-1")) {
                                try {
                                    idCampo = Long.parseLong((String) valorDeRegreso);
                                } catch (NumberFormatException nfe) {
                                    log.debug(nfe);
                                }
                            }
                        }
                        if (tipoDeRegreso.equals(Long.class)) {
                            idCampo = (Long) valorDeRegreso;
                        }
                    }
                }
            }
        } catch (IllegalAccessException ex) {
            log.debug(ex);
        } catch (IllegalArgumentException ex) {
            log.debug(ex);
        } catch (InvocationTargetException ex) {
            log.debug(ex);
        }
        return idCampo;
    }
    
    private static String obtenStringDeForma(ActionForm formaActualizadora, String campoDeLaForma) {
        Method[] metodos = formaActualizadora.getClass().getMethods();
        String string = null;
        try {
            for (Method metodo : metodos) {
                metodo.setAccessible(true);
                if (metodo.getName().equals("get" + nombreDelCampoToPrimerUpper(campoDeLaForma))) {
                    Class<?> tipoDeRegreso = metodo.getReturnType();
                    // Si el tipo de regreso de la forma es un String.
                    if (tipoDeRegreso != null) {
                        Object valorDeRegreso = metodo.invoke(formaActualizadora);
                        if (valorDeRegreso != null) {
                            string = valorDeRegreso.toString();
                        }
                    }
                }
            }
        } catch (IllegalAccessException ex) {
            log.debug(ex);
        } catch (IllegalArgumentException ex) {
            log.debug(ex);
        } catch (InvocationTargetException ex) {
            log.debug(ex);
        }
        return string;
    }


    public ActionForward guardaDelitosIntegracion(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
		
			InvolucradoDTO involucradoDTO = new InvolucradoDTO();
			DelitoDTO delitoDTO = new DelitoDTO();
			ExpedienteDTO expedienteDTO = new ExpedienteDTO();
			List<DelitoDTO> delitoDTOs = new ArrayList<DelitoDTO>();
			
			String numDelitos = request.getParameter("numDelitos");
			log.info("numDelitos====" + numDelitos);
			
			String idExpediente = request.getParameter("idExpediente");
			log.info("idExpediente ::"+idExpediente);
			
			String idInvolucrado = request.getParameter("idInvolucrado");
			log.info("idInvolucrado ::"+idInvolucrado);
			
			delitoDTOs.add(delitoDTO);
			
			
			DelitoDTO pDelitoDTO = new DelitoDTO();
			involucradoDTO.setElementoId(Long.parseLong(idInvolucrado));
			involucradoDTO.setExpedienteDTO(expedienteDTO);
			expedienteDTO.setExpedienteId(Long.parseLong(idExpediente));
			delitoDTO.setEsProbable(false);
			delitoDTO.setEsPrincipal(false);
			expedienteDTO.setDelitoPrincipal(pDelitoDTO);

			involucradoDTO.setDelitosCometidos(delitoDTOs);
			
			log.info("numDelitos cometidos====" + delitoDTOs);
			//Falta Pasar Nombre delito
			 if(numDelitos!=null){
				delitoDTO.setDelitoId(Long.parseLong(numDelitos));
			}
			 
			 delitoDelegate.asociarDelitoResponsableVictima(null, delitoDTO, involucradoDTO, null, null, null, null, null, null, null, null);
			
			String xml = null;
			PrintWriter pw = null;
			converter.alias("involucrado", InvolucradoDTO.class);
			
			xml = converter.toXML(involucradoDTO);
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
    
    public ActionForward guardaHechosIntegracion(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
		
			
		
//			String idInvolucrado = request.getParameter("idInvolucrado");
//			log.info("idInvolucrado ::"+idInvolucrado);
//			
//			InvolucradoDTO involucradoDTO = new InvolucradoDTO();
//			involucradoDTO.setElementoId(Long.parseLong(idInvolucrado));
//			
//			MediaFiliacionDTO mediaFiliacionDTO = new MediaFiliacionDTO();
//			
//			involucradoDTO.setMediaFiliacionDTO(obtenerMediaFiliacion(forma));
//			
//			involucradoDTO.setElementoId(involucradoDelegate.guardarInvolucrado(involucradoDTO));
//
//			
//			String xml = null;
//			PrintWriter pw = null;
//			converter.alias("involucrado", InvolucradoDTO.class);
//			
//			xml = converter.toXML(involucradoDTO);
//			response.setContentType("text/xml");
//			pw = response.getWriter();
//			pw.print(xml);
//			pw.flush();
//			pw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
    
	/**
	 * Metodo utilizado para consultar los documentos asociados al expediente
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
    
    public ActionForward consultarDocumentosPorExpediente(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {		
			
     		String idExpediente = request.getParameter("idExpediente");
			log.info("idExpediente ::"+idExpediente);
			
			String tipo = request.getParameter("tipo");
			log.info("tipo ::"+tipo);
			
			ArchivoDigitalDTO archivoDigitalDTO = new ArchivoDigitalDTO();
			
			if(Long.parseLong(tipo) == 0){
			
			archivoDigitalDTO = documentoDelegate.consultarArchivoDitalSinContenidoPorActividad(Long.parseLong(idExpediente),Actividades.GENERAR_CEDULA_DE_IDENTIFICACION);
			
			}else{
			
			archivoDigitalDTO = documentoDelegate.consultarArchivoDitalSinContenidoPorActividad(Long.parseLong(idExpediente),Actividades.GENERAR_ACUERDO_DE_DEFENSA);
				
			}			
			
			log.info("documento" + archivoDigitalDTO);
			
			String xml = null;
			PrintWriter pw = null;
			converter.alias("archivoDigitalDTO", ArchivoDigitalDTO.class);
			
			xml = converter.toXML(archivoDigitalDTO);
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
    
}
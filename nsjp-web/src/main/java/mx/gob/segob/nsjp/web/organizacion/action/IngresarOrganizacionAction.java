/**
 * Nombre del Programa 		: IngresarHechosAction.java
 * Autor                     : ArmandoCT
 * Compania                  : Ultrasist
 * Proyecto                  : NSJP                    Fecha: 14/junio/2011
 * Marca de cambio        	: N/A
 * Descripcion General    	: Clase Action para ingresar objetos
 * Programa Dependiente  	: N/A
 * Programa Subsecuente 		: N/A
 * Cond. de ejecucion        : N/A
 * Dias de ejecucion         : N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                     : N/A
 * Compania              	: N/A
 * Proyecto                 	: N/A                                 Fecha: N/A
 * Modificacion           	: N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.web.organizacion.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.delegate.involucrado.InvolucradoDelegate;
import mx.gob.segob.nsjp.delegate.organizacion.OrganizacionDelegate;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.domicilio.AsentamientoDTO;
import mx.gob.segob.nsjp.dto.domicilio.CiudadDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioExtranjeroDTO;
import mx.gob.segob.nsjp.dto.domicilio.EntidadFederativaDTO;
import mx.gob.segob.nsjp.dto.domicilio.MunicipioDTO;
import mx.gob.segob.nsjp.dto.domicilio.TipoAsentamientoDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.organizacion.OrganizacionDTO;
import mx.gob.segob.nsjp.dto.persona.CorreoElectronicoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.dto.persona.PersonaDTO;
import mx.gob.segob.nsjp.dto.persona.TelefonoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;
import mx.gob.segob.nsjp.web.caso.form.IngresarIndividuoForm;
import mx.gob.segob.nsjp.web.organizacion.form.OrganizacionForm;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Clase Action para ingresar hechos.
 * 
 * @version 1.0
 * @author ArmandoCT
 * 
 */
public class IngresarOrganizacionAction extends GenericAction {

	/* Log de clase */
	private static final Logger log = Logger
			.getLogger(IngresarOrganizacionAction.class);

	@Autowired
	private OrganizacionDelegate organizacionDelegate;
	
	@Autowired
	public InvolucradoDelegate involucradoDelegate;
	

	/**
	 * Metodo utilizado para guardar una organizacion
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward ingresarOrganizacion(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			log.info("ejecutando Action guardar Organizacion");
			// hacemos el cast de la forma de Organizacion
			OrganizacionForm formaOrganizacion = (OrganizacionForm) form;

			log.info("FORMA ORGANIZACION:::::::::::::::::::::::");
			
			//revisamos que la informacion de la organizacion no sea nula
			if(StringUtils.isBlank(formaOrganizacion.getNombreOrg()))
			{
				formaOrganizacion.setNombreOrg("");
			}
			if(StringUtils.isBlank(formaOrganizacion.getNombreCortoOrg()))
			{
				formaOrganizacion.setNombreCortoOrg("");
			}
			if(StringUtils.isBlank(formaOrganizacion.getOrgDirInternet()))
			{
				formaOrganizacion.setOrgDirInternet("");
			}
			log.info("3  :::::::::::::::::::::::");
			if(StringUtils.isBlank(formaOrganizacion.getTipoOrg()))
			{
				formaOrganizacion.setTipoOrg("");
			}
			log.info("4  :::::::::::::::::::::::");
			if(StringUtils.isBlank(formaOrganizacion.getMedioContactoCorreo()))
			{
				formaOrganizacion.setMedioContactoCorreo("");
			}
			log.info("5  :::::::::::::::::::::::");
			if(StringUtils.isBlank(formaOrganizacion.getMedioContactoTelefono()))
			{
				formaOrganizacion.setMedioContactoTelefono("");
			}
			log.info("6  :::::::::::::::::::::::");
			//sector publico
			if(StringUtils.isBlank(formaOrganizacion.getOrgsSecPubOrgSectorPublico()))
			{
				formaOrganizacion.setOrgsSecPubOrgSectorPublico("");
			}
			if(StringUtils.isBlank(formaOrganizacion.getAreaOrgSectorPublico()))
			{
				formaOrganizacion.setAreaOrgSectorPublico("");
			}
			
			//NO formal
			if(StringUtils.isBlank(formaOrganizacion.getAreaOrgNoFormal()))
			{
				formaOrganizacion.setAreaOrgNoFormal("");
			}
			if(StringUtils.isBlank(formaOrganizacion.getGiroOrgNoFormal()))
			{
				formaOrganizacion.setGiroOrgNoFormal("");
			}
			
			//formal
			if(StringUtils.isBlank(formaOrganizacion.getTipoOrgFormal()))
			{
				formaOrganizacion.setTipoOrgFormal("");
			}
			if(StringUtils.isBlank(formaOrganizacion.getNoActaOrgFormal()))
			{
				formaOrganizacion.setNoActaOrgFormal("");
			}
			if(StringUtils.isBlank(formaOrganizacion.getRfcOrgFormal()))
			{
				formaOrganizacion.setRfcOrgFormal("");
			}
			if(StringUtils.isBlank(formaOrganizacion.getGiroOrgFormal()))
			{
				formaOrganizacion.setGiroOrgFormal("");
			}
			
			//delictiva
			if(StringUtils.isBlank(formaOrganizacion.getDescOrgDelictiva()))
			{
				formaOrganizacion.setDescOrgDelictiva("");
			}
			
			//comunidad virtual
			if(StringUtils.isBlank(formaOrganizacion.getTipoOrgVirtual()))
			{
				formaOrganizacion.setTipoOrgVirtual("");
			}
			if(StringUtils.isBlank(formaOrganizacion.getDirElectronicaOrgVirtual()))
			{
				formaOrganizacion.setDirElectronicaOrgVirtual("");
			}
			if(StringUtils.isBlank(formaOrganizacion.getPropositoOrgVirtual()))
			{
				formaOrganizacion.setPropositoOrgVirtual("");
			}
			log.info("20 :::::::::::");
			// revisamos que los datos de domicilio no sea nula

//			if (formaOrganizacion.getPais().equalsIgnoreCase("")) {
//				formaOrganizacion.setPais("");
//			}
			if (StringUtils.isBlank(formaOrganizacion.getCodigoPostal())) 
			{
				formaOrganizacion.setCodigoPostal("");
			}

//			if (formaOrganizacion.getEntidadFederativa().equalsIgnoreCase("")
//					|| formaOrganizacion.getEntidadFederativa().equalsIgnoreCase("-1")) {
//				formaOrganizacion.setEntidadFederativa(null);
//			}

//			if (formaOrganizacion.getCiudad().equalsIgnoreCase("")
//					|| formaOrganizacion.getCiudad().equalsIgnoreCase("-1")) {
//				formaOrganizacion.setCiudad(null);
//			}

//			if (formaOrganizacion.getDelegacionMunicipio().equalsIgnoreCase("")
//					|| formaOrganizacion.getDelegacionMunicipio().equalsIgnoreCase("-1")) {
//				formaOrganizacion.setDelegacionMunicipio(null);
//			}

//			if (formaOrganizacion.getAsentamientoColonia().equalsIgnoreCase("")
//					|| formaOrganizacion.getAsentamientoColonia().equalsIgnoreCase("-1")) {
//				formaOrganizacion.setAsentamientoColonia(null);
//			}

//			if (formaOrganizacion.getTipoAsentamiento().equalsIgnoreCase("")
//					|| formaOrganizacion.getTipoAsentamiento().equalsIgnoreCase("-1")) {
//				formaOrganizacion.setTipoAsentamiento(null);
//			}

//			if (formaOrganizacion.getTipoCalle().equalsIgnoreCase("")
//					|| formaOrganizacion.getTipoAsentamiento().equalsIgnoreCase("-1")) {
//				formaOrganizacion.setTipoCalle(null);
//			}

			if (StringUtils.isBlank(formaOrganizacion.getCalle())) {
				formaOrganizacion.setCalle("");
			}

			if (StringUtils.isBlank(formaOrganizacion.getNumExterior())) {
				formaOrganizacion.setNumExterior("");
			}

			if (StringUtils.isBlank(formaOrganizacion.getNumInterior())) {
				formaOrganizacion.setNumInterior("");
			}

			if (StringUtils.isBlank(formaOrganizacion.getReferencias())) {
				formaOrganizacion.setReferencias("");
			}

			if (StringUtils.isBlank(formaOrganizacion.getEntreCalle())) {
				formaOrganizacion.setEntreCalle("");
			}

			if (StringUtils.isBlank(formaOrganizacion.getYcalle())) {
				formaOrganizacion.setYcalle("");
			}

			if (StringUtils.isBlank(formaOrganizacion.getAliasDomicilio())) {
				formaOrganizacion.setAliasDomicilio("");
			}

			if (StringUtils.isBlank(formaOrganizacion.getEdificio())) {
				formaOrganizacion.setEdificio("");
			}

			if (StringUtils.isBlank(formaOrganizacion.getLongitud())) {
				formaOrganizacion.setLongitud("");
			}

			if (StringUtils.isBlank(formaOrganizacion.getLatitud())) {
				formaOrganizacion.setLatitud("");
			}
			// FIN revisamos que los datos de domicilio no sea nula
			

			// Declaramos la instancia a guardar en BD
			OrganizacionDTO organizacionDTO = new OrganizacionDTO();
			log.info("%%%%%%%%Numero de expediente para Organizacion:::: "+ formaOrganizacion.getNumeroExpediente());
			ExpedienteDTO expedienteDTO = super.getExpedienteTrabajo(request,formaOrganizacion.getNumeroExpediente());
			organizacionDTO.setExpedienteDTO(expedienteDTO);
			organizacionDTO.setFechaCreacionElemento(new Date());
						
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuarioDTO.setIdUsuario(Long.parseLong(formaOrganizacion.getIdUsuario()));
			organizacionDTO.setUsuario(usuarioDTO);
			
			log.info("CALIDAD ORGANIZACION:::: "+ formaOrganizacion.getCalidadPersonaMoral());
			
			if(formaOrganizacion.getCalidadPersonaMoral().equals("PROBABLE_RESPONSABLE"))
			{
				CalidadDTO calidadOrgDTO=new CalidadDTO();
				calidadOrgDTO.setCalidades(Calidades.PROBABLE_RESPONSABLE_ORGANIZACION);
				organizacionDTO.setCalidadDTO(calidadOrgDTO);
			}
			else if(formaOrganizacion.getCalidadPersonaMoral().equals("DENUNCIANTE"))
			{
				CalidadDTO calidadOrgDTO=new CalidadDTO();
				calidadOrgDTO.setCalidades(Calidades.DENUNCIANTE_ORGANIZACION);
				organizacionDTO.setCalidadDTO(calidadOrgDTO);
			}
							
			// Seteamos el domicilio de la organizacion
			
			CalidadDTO calidadDTO = new CalidadDTO();
			calidadDTO.setCalidades(Calidades.DOMICILIO);
			
			// Encapsulamos la informacion del domicilio
			if (Long.parseLong(formaOrganizacion.getPais()) == 10)// Mexico
			{
				DomicilioDTO domicilioDTO = new DomicilioDTO();
				log.info("#################################55555");	
				domicilioDTO.setLatitud(formaOrganizacion.getLatitud());
				log.info("#################################66");	
				domicilioDTO.setLongitud(formaOrganizacion.getLongitud());
				domicilioDTO.setEdificio(formaOrganizacion.getEdificio());
				domicilioDTO.setAlias(formaOrganizacion.getAliasDomicilio());
				domicilioDTO.setEntreCalle2(formaOrganizacion.getYcalle());
				domicilioDTO.setEntreCalle1(formaOrganizacion.getEntreCalle());
				domicilioDTO.setReferencias(formaOrganizacion.getReferencias());
				domicilioDTO.setNumeroInterior(formaOrganizacion.getNumInterior());
				domicilioDTO.setNumeroExterior(formaOrganizacion.getNumExterior());
				domicilioDTO.setCalle(formaOrganizacion.getCalle());
				log.info("#################################000000000000000000000000");	
				if(StringUtils.isNotBlank(formaOrganizacion.getTipoCalle()) && !formaOrganizacion.getTipoCalle().equals("-1"))
				{
					domicilioDTO.setValorCalleId(new ValorDTO(Long
							.parseLong(formaOrganizacion.getTipoCalle())));
				}
				domicilioDTO.setCalidadDTO(calidadDTO);
				domicilioDTO.setExpedienteDTO(expedienteDTO);
				domicilioDTO.setFechaCreacionElemento(new Date());
				// delcaramos el nuevo asentamiento
				AsentamientoDTO asentamientoDTO = new AsentamientoDTO();
				if(StringUtils.isNotBlank(formaOrganizacion.getAsentamientoColonia()) && !formaOrganizacion.getAsentamientoColonia().equals("-1"))
				{
					asentamientoDTO.setAsentamientoId(Long.parseLong(formaOrganizacion
							.getAsentamientoColonia()));
				}
//				if (formaOrganizacion.getAsentamientoColonia() != null) {
//					asentamientoDTO.setAsentamientoId(Long.parseLong(formaOrganizacion
//							.getAsentamientoColonia()));
//				}
				asentamientoDTO.setCodigoPostal(formaOrganizacion.getCodigoPostal());

				// Declaramos el tipo de asentamiento
				if(StringUtils.isNotBlank(formaOrganizacion.getTipoAsentamiento()) && !formaOrganizacion.getTipoAsentamiento().equals("-1"))
				{
					TipoAsentamientoDTO tipoAsentamientoDTO = new TipoAsentamientoDTO(
							Long.parseLong(formaOrganizacion.getTipoAsentamiento()), "");
					asentamientoDTO.setTipoAsentamientoDTO(tipoAsentamientoDTO);
				}
//				if (formaOrganizacion.getTipoAsentamiento() != null) {
//					TipoAsentamientoDTO tipoAsentamientoDTO = new TipoAsentamientoDTO(
//							Long.parseLong(formaOrganizacion.getTipoAsentamiento()), "");
//					asentamientoDTO.setTipoAsentamientoDTO(tipoAsentamientoDTO);
//				}
				// Declaramos el municipio
				MunicipioDTO municipioDTO = new MunicipioDTO();
				
				if(StringUtils.isNotBlank(formaOrganizacion.getDelegacionMunicipio()) && !formaOrganizacion.getDelegacionMunicipio().equals("-1"))
				{
					municipioDTO.setMunicipioId(Long.parseLong(formaOrganizacion
							.getDelegacionMunicipio()));
				}
				
//				if (formaOrganizacion.getDelegacionMunicipio() != null) {
//					municipioDTO.setMunicipioId(Long.parseLong(formaOrganizacion
//							.getDelegacionMunicipio()));
//				}
				asentamientoDTO.setMunicipioDTO(municipioDTO);

				// declaramos la Ciudad
				CiudadDTO ciudadDTO = new CiudadDTO();
				
				if(StringUtils.isNotBlank(formaOrganizacion.getCiudad()) && !formaOrganizacion.getCiudad().equals("-1"))
				{
					ciudadDTO.setCiudadId(Long.parseLong(formaOrganizacion.getCiudad()));
				}
				
//				if (formaOrganizacion.getCiudad() != null) {
//					ciudadDTO.setCiudadId(Long.parseLong(formaOrganizacion.getCiudad()));
//				}
				// declaramos la entidad federativa
				EntidadFederativaDTO entidadFederativaDTO = new EntidadFederativaDTO();
				if(StringUtils.isNotBlank(formaOrganizacion.getEntidadFederativa()) && !formaOrganizacion.getEntidadFederativa().equals("-1"))
				{
					entidadFederativaDTO.setEntidadFederativaId(Long
							.parseLong(formaOrganizacion.getEntidadFederativa()));
				}
//				if (formaOrganizacion.getEntidadFederativa() != null) {
//					entidadFederativaDTO.setEntidadFederativaId(Long
//							.parseLong(formaOrganizacion.getEntidadFederativa()));
//				}
				if(StringUtils.isNotBlank(formaOrganizacion.getPais()) && !formaOrganizacion.getPais().equals("-1"))
				{
					entidadFederativaDTO.setValorIdPais(new ValorDTO(Long
							.parseLong(formaOrganizacion.getPais())));
				}
				
//				if (formaOrganizacion.getPais() != null) {
//					entidadFederativaDTO.setValorIdPais(new ValorDTO(Long
//							.parseLong(formaOrganizacion.getPais())));
//				}
				ciudadDTO.setEntidadFederativaDTO(entidadFederativaDTO);
				asentamientoDTO.setCiudadDTO(ciudadDTO);
				domicilioDTO.setCiudadDTO(ciudadDTO);
				domicilioDTO.setEntidadDTO(entidadFederativaDTO);
				domicilioDTO.setAsentamientoDTO(asentamientoDTO);
				domicilioDTO.setMunicipioDTO(municipioDTO);
				
				//REVISAMOS SI ES LA MISMA DIRECCION PARA EL DOMICILIO DE NOTIFICACIONES

				//FIN REVISAMOS SI ES LA MISMA DIRECCION PARA EL DOMICILIO DE NOTIFICACIONES
				if(formaOrganizacion.getMismoDomicilioNotificaciones().equals("true"))
				{
					
				}
				// seteamos el domicilio al Hecho
				organizacionDTO.setDomicilioDTO(domicilioDTO);
				log.info("#################################111111111111111111");	
			} else// Otro pais
			{
				DomicilioExtranjeroDTO domExtranjreoDTO = new DomicilioExtranjeroDTO();

				domExtranjreoDTO.setLatitud(formaOrganizacion
						.getLatitud());
				domExtranjreoDTO.setLongitud(formaOrganizacion
						.getLongitud());
				domExtranjreoDTO.setEdificio(formaOrganizacion.getEdificio());
				domExtranjreoDTO.setAlias(formaOrganizacion.getAliasDomicilio());
				domExtranjreoDTO.setEntreCalle2(formaOrganizacion.getYcalle());
				domExtranjreoDTO.setEntreCalle1(formaOrganizacion.getEntreCalle());
				domExtranjreoDTO.setReferencias(formaOrganizacion.getReferencias());
				domExtranjreoDTO.setNumeroInterior(formaOrganizacion.getNumInterior());
				domExtranjreoDTO.setNumeroExterior(formaOrganizacion.getNumExterior());
				domExtranjreoDTO.setCalle(formaOrganizacion.getCalle());
				domExtranjreoDTO.setPais(formaOrganizacion.getPais());
				domExtranjreoDTO.setCodigoPostal(formaOrganizacion.getCodigoPostal());
				domExtranjreoDTO.setCiudad(formaOrganizacion.getCiudad());
				domExtranjreoDTO.setMunicipio(formaOrganizacion.getDelegacionMunicipio());
				domExtranjreoDTO.setAsentamientoExt(formaOrganizacion
						.getAsentamientoColonia());
				domExtranjreoDTO.setEstado(formaOrganizacion.getEntidadFederativa());
				domExtranjreoDTO.setCalidadDTO(calidadDTO);
				domExtranjreoDTO.setExpedienteDTO(expedienteDTO);
				domExtranjreoDTO.setFechaCreacionElemento(new Date());
				domExtranjreoDTO.setCalidadDTO(calidadDTO);
				// private String tipoAsentamiento; SE VA EN DOMICILIO
				// EXTRANJERO
				// private String tipoCalle; SE VA EN DOMICILIO EXTRANJERO

				// seteamos el domicilio extranjero al Hecho
				organizacionDTO.setDomicilioDTO(domExtranjreoDTO);
			}
			log.info("#################################22222222222222222");			
			ValorDTO valorTipoOrg = new ValorDTO();
			valorTipoOrg.setIdCampo(Long.parseLong(formaOrganizacion
					.getTipoOrg()));
			organizacionDTO.setValorByTipoOrganizacionVal(valorTipoOrg);

			// Inicia seteo de campos de organizacion
			// private String tipoCiberespacio;
			organizacionDTO.setNombreOrganizacion(formaOrganizacion
					.getNombreOrg());
			organizacionDTO.setNombreCorto(formaOrganizacion
					.getNombreCortoOrg());
			organizacionDTO.setDireccionInternet(formaOrganizacion
					.getOrgDirInternet());
			log.info("#################################333333333");
			// seteamos los valores que dependen del tipo de la organizacion
			switch (Integer.parseInt(formaOrganizacion.getTipoOrg())) {
			case 230:// Formal
				ValorDTO tipoOrgFormal = new ValorDTO(
						Long.parseLong(formaOrganizacion.getTipoOrgFormal()));
				organizacionDTO.setValorByOrganizacionFormalVal(tipoOrgFormal);
				organizacionDTO.setNumeroActaConstitutiva(formaOrganizacion
						.getNoActaOrgFormal());
				organizacionDTO.setRfc(formaOrganizacion.getRfcOrgFormal());
				organizacionDTO.setGiro(formaOrganizacion.getGiroOrgFormal());
				break;
			case 231:// No Formal
				organizacionDTO.setAreaDeInfluencia(formaOrganizacion
						.getAreaOrgNoFormal());
				organizacionDTO.setGiro(formaOrganizacion.getGiroOrgNoFormal());
				break;
			case 232:// Comunidad Virtual
				ValorDTO tipoOrgVirtual = new ValorDTO(
						Long.parseLong(formaOrganizacion.getTipoOrgVirtual()));
				organizacionDTO.setValorByComunidadVirtualVal(tipoOrgVirtual);
				// organizacionDTO.setDireccionInternet(formaOrganizacion.getDirElectronicaOrgVirtual());
				organizacionDTO.setPropositoCiberespacio(formaOrganizacion
						.getPropositoOrgVirtual());
				break;
			case 233:// Sector Publico
				ValorDTO orgSectorPublico = new ValorDTO(
						Long.parseLong(formaOrganizacion
								.getOrgsSecPubOrgSectorPublico()));
				organizacionDTO
						.setValorBySectorGubernamentalVal(orgSectorPublico);
				organizacionDTO.setAreaDeInfluencia(formaOrganizacion
						.getAreaOrgSectorPublico());
				break;
			case 234:// Delictiva
				organizacionDTO.setDescripcionDelictiva(formaOrganizacion
						.getDescOrgDelictiva());
				break;
			}

			// FIN Inicia seteo de campos de organizacion

			// seteamos los valores nulos para representante legal y contacto de
			// organizacion
			organizacionDTO.setRepresentanteLegal(null);
			organizacionDTO.setContacto(null);

			log.info("$%&$%&$%&$%&$%&$%& este es el DTO para ingresar una organicacion ke se le manda al servico");
			log.info("###"+organizacionDTO);
			// mandamos guardar la organizacion
			//organizacionDTO = organizacionDelegate.ingresarOrganizacion(organizacionDTO);
			InvolucradoDTO involucradoDTO=new InvolucradoDTO();
			involucradoDTO.setOrganizacionDTO(organizacionDTO);
			
			//revisamos si existe el Id de la organizacion, si existe es una consulta, en caso contrario es un insert
			if(StringUtils.isNotBlank(formaOrganizacion.getIdIndividuo()))
			{
				organizacionDTO.setElementoId(Long.parseLong(formaOrganizacion.getIdIndividuo()));
				involucradoDTO.setElementoId(Long.parseLong(formaOrganizacion.getIdIndividuo()));
				//revisamos si se debe anular la organizacion
				if(formaOrganizacion.getAnular()!=null){
					involucradoDTO.setEsActivo(!formaOrganizacion.getAnular());
				}

			}
			
			if(formaOrganizacion.getCalidadPersonaMoral().equals("PROBABLE_RESPONSABLE"))
			{
				CalidadDTO calidadOrgDTO=new CalidadDTO();
				calidadOrgDTO.setCalidades(Calidades.PROBABLE_RESPONSABLE_ORGANIZACION);
				involucradoDTO.setCalidadDTO(calidadOrgDTO);
			}
			else if(formaOrganizacion.getCalidadPersonaMoral().equals("DENUNCIANTE"))
			{
				CalidadDTO calidadOrgDTO=new CalidadDTO();
				calidadOrgDTO.setCalidades(Calidades.DENUNCIANTE_ORGANIZACION);
				involucradoDTO.setCalidadDTO(calidadOrgDTO);
			}
//			CalidadDTO calidadDTO2=new CalidadDTO();
//			calidadDTO2.setCalidades(Calidades.PROBABLE_RESPONSABLE_ORGANIZACION);
//			involucradoDTO.setCalidadDTO(calidadDTO2);
			log.info("%%%%%%%% expediente DTO"+expedienteDTO);
			involucradoDTO.setExpedienteDTO(expedienteDTO);
			involucradoDTO.setTipoPersona(0L);
			log.info("%%%%%%%%  involucrado:"+involucradoDTO);
			Long idInvolucrado=involucradoDelegate.guardarInvolucrado(involucradoDTO);
			organizacionDTO.setOrganizacionId(idInvolucrado);
			organizacionDTO.setNombreOrganizacion(formaOrganizacion.getNombreOrg());
			log.info("%%%%%%%% organizacionDTO:"+organizacionDTO);
			log.info("%%%%%%%% id involucrado:"+idInvolucrado);
			organizacionDTO.setElementoId(idInvolucrado);
			// revisamos si el guardado fue exitoso para mandar el xml
			// correspondiente
			if (organizacionDTO != null
					&& organizacionDTO.getOrganizacionId() != null) {
				log.info("%%%%%%%% Organizacion ID: "+organizacionDTO.getElementoId());
				log.info("%%%%%%%% Organizacion ID: "+organizacionDTO.getOrganizacionId());
				converter.alias("organizacionDTO", OrganizacionDTO.class);
				String xml = converter.toXML(organizacionDTO);
				escribirRespuesta(response, xml);
			} else {
				organizacionDTO.setOrganizacionId(0L);
				organizacionDTO.setElementoId(0L);
				organizacionDTO.setNombreOrganizacion("Organizacion");
				converter.alias("organizacionDTO", OrganizacionDTO.class);
				String xml = converter.toXML(organizacionDTO);
				//log.info(xml);
				escribirRespuesta(response, xml);
			}
			log.info("Termina ejecucion Action guardar Organizacion - FIN ");
		} catch (NSJPNegocioException e) {
			log.error(e.getMessage(), e);
			log.info("error:"+e.getCause());
			escribir(response, "", e);
		}catch (Exception ee) {
			log.error(ee.getMessage(), ee);
			log.info("error:"+ee.getCause());
			NSJPNegocioException neg=new NSJPNegocioException(CodigoError.EJCUCION_OPERACION_ESTADO_INCORRECTO,ee);
			escribir(response, "", neg);
		}
		return null;
	}

	
	/**
	 * Metodo utilizado para guardar de un cobtacto de la organizacion
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward guardarContactoOrg(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			log.info("ejecutando Action guardar Contacto Organizacion");
			// hacemos el cast de la forma de Contacto Organizacion
			IngresarIndividuoForm formaContOrg = (IngresarIndividuoForm) form;

			log.info("FORMA CONTACTO ORGANIZACION:::::::::::::::::::::::");

			//revisamos q los datos generales no vengan nulos
			if (StringUtils.isBlank(formaContOrg.getNombre())) {
				formaContOrg.setNombre("");
			}
			if (StringUtils.isBlank(formaContOrg.getApellidoPaterno())) {
				formaContOrg.setApellidoPaterno("");
			}
			if (StringUtils.isBlank(formaContOrg.getApellidoMaterno())) {
				formaContOrg.setApellidoMaterno("");
			}
			if (StringUtils.isBlank(formaContOrg.getCurp())) {
				formaContOrg.setCurp("");
			}
			if (StringUtils.isBlank(formaContOrg.getRfc())) {
				formaContOrg.setRfc("");
			}
			if (StringUtils.isBlank(formaContOrg.getFechaIngreso())) {
				formaContOrg.setFechaIngreso("");
			}
			if (StringUtils.isBlank(formaContOrg.getIdioma())) {
				formaContOrg.setIdioma("");
			}
			if (StringUtils.isBlank(formaContOrg.getReligion())) {
				formaContOrg.setReligion("");
			}
			if (StringUtils.isBlank(formaContOrg.getEscolaridad())) {
				formaContOrg.setEscolaridad("");
			}
			if (StringUtils.isBlank(formaContOrg.getEstadoCivil())) {
				formaContOrg.setEstadoCivil("");
			}
			if (StringUtils.isBlank(formaContOrg.getSexo())) {
				formaContOrg.setSexo("");
			}
			if (StringUtils.isBlank(formaContOrg.getFechaNacimiento())) {
				formaContOrg.setFechaNacimiento("");
			}
			if (formaContOrg.getEdadAproximada()==null) {
				formaContOrg.setEdadAproximada(Short.parseShort("0"));
			}
			if (StringUtils.isBlank(formaContOrg.getAlias())) {
				formaContOrg.setAlias("");
			}
			if (StringUtils.isBlank(formaContOrg.getOcupacion())) {
				formaContOrg.setOcupacion("");
			}
			if (StringUtils.isBlank(formaContOrg.getNacionalidad())) {
				formaContOrg.setNacionalidad("");
			}
			if (StringUtils.isBlank(formaContOrg.getPaisNacimiento())) {
				formaContOrg.setPaisNacimiento("");
			}
			if (StringUtils.isBlank(formaContOrg.getEntFederativaNacimiento())) {
				formaContOrg.setEntFederativaNacimiento("");
			}
			if (StringUtils.isBlank(formaContOrg.getMunicipioNacimiento())) {
				formaContOrg.setMunicipioNacimiento("");
			}
			log.info("CONTACTO ORG:::: Revision datos generales....");
			//FIN revisamos q los datos generales no vengan nulos
			//revisamos que medios de contacto no vengan nulos
			if (StringUtils.isBlank(formaContOrg.getMedioContactoTelefono())) {
				formaContOrg.setMedioContactoTelefono("");
			}
			if (StringUtils.isBlank(formaContOrg.getMedioContactoCorreo())) {
				formaContOrg.setMedioContactoCorreo("");
			}
			log.info("CONTACTO ORG:::: Revision Medio de contacto....");
			//FIN revisamos que medios de contacto no vengan nulos
			//revisamos que los datos del documento de identificacion no vengan vacios
			if (StringUtils.isBlank(formaContOrg.getDocIdentificacion())) {
				formaContOrg.setDocIdentificacion("");
			}
			if (StringUtils.isBlank(formaContOrg.getFolioDoc())) {
				formaContOrg.setFolioDoc("");
			}
			log.info("CONTACTO ORG:::: Revision documento de identificacion....");
			//FIN revisamos que los datos del documento de identificacion no vengan vacios
			// revisamos que los datos de domicilio no sea nula

//			if (formaContOrg.getPais().equalsIgnoreCase("")) {
//				formaContOrg.setPais("");
//			}
			if (StringUtils.isBlank(formaContOrg.getCodigoPostal())) {
				formaContOrg.setCodigoPostal("");
			}

//			if (formaContOrg.getEntidadFederativa().equalsIgnoreCase("")
//					|| formaContOrg.getEntidadFederativa().equalsIgnoreCase("-1")) {
//				formaContOrg.setEntidadFederativa(null);
//			}

//			if (formaContOrg.getCiudad().equalsIgnoreCase("")
//					|| formaContOrg.getCiudad().equalsIgnoreCase("-1")) {
//				formaContOrg.setCiudad(null);
//			}

//			if (formaContOrg.getDelegacionMunicipio().equalsIgnoreCase("")
//					|| formaContOrg.getDelegacionMunicipio().equalsIgnoreCase("-1")) {
//				formaContOrg.setDelegacionMunicipio(null);
//			}

//			if (formaContOrg.getAsentamientoColonia().equalsIgnoreCase("")
//					|| formaContOrg.getAsentamientoColonia().equalsIgnoreCase("-1")) {
//				formaContOrg.setAsentamientoColonia(null);
//			}

//			if (formaContOrg.getTipoAsentamiento().equalsIgnoreCase("")
//					|| formaContOrg.getTipoAsentamiento().equalsIgnoreCase("-1")) {
//				formaContOrg.setTipoAsentamiento(null);
//			}

//			if (formaContOrg.getTipoCalle().equalsIgnoreCase("")
//					|| formaContOrg.getTipoAsentamiento().equalsIgnoreCase("-1")) {
//				formaContOrg.setTipoCalle(null);
//			}

			if (StringUtils.isBlank(formaContOrg.getCalle())) {
				formaContOrg.setCalle("");
			}

			if (StringUtils.isBlank(formaContOrg.getNumExterior())) {
				formaContOrg.setNumExterior("");
			}

			if (StringUtils.isBlank(formaContOrg.getNumInterior())) {
				formaContOrg.setNumInterior("");
			}

			if (StringUtils.isBlank(formaContOrg.getReferencias())) {
				formaContOrg.setReferencias("");
			}

			if (StringUtils.isBlank(formaContOrg.getEntreCalle())) {
				formaContOrg.setEntreCalle("");
			}

			if (StringUtils.isBlank(formaContOrg.getYcalle())) {
				formaContOrg.setYcalle("");
			}

			if (StringUtils.isBlank(formaContOrg.getAliasDomicilio())) {
				formaContOrg.setAliasDomicilio("");
			}

			if (StringUtils.isBlank(formaContOrg.getEdificio())) {
				formaContOrg.setEdificio("");
			}

			if (StringUtils.isBlank(formaContOrg.getLongitud())) {
				formaContOrg.setLongitud("");
			}

			if (StringUtils.isBlank(formaContOrg.getLatitud())) {
				formaContOrg.setLatitud("");
			}
			log.info("CONTACTO ORG:::: domicilio....");
			// FIN revisamos que los datos de domicilio no sea nula
			//REVISAMOS que el domicilio notificaciones si viene no sea nulo
//			if (formaContOrg.getPaisNotif().equalsIgnoreCase("")) {
//				formaContOrg.setPaisNotif("");
//			}
			if (StringUtils.isBlank(formaContOrg.getCodigoPostalNotif())) {
				formaContOrg.setCodigoPostalNotif("");
			}

//			if (formaContOrg.getEntidadFederativaNotif().equalsIgnoreCase("")
//					|| formaContOrg.getEntidadFederativaNotif().equalsIgnoreCase("-1")) {
//				formaContOrg.setEntidadFederativaNotif(null);
//			}

//			if (formaContOrg.getCiudadNotif().equalsIgnoreCase("")
//					|| formaContOrg.getCiudadNotif().equalsIgnoreCase("-1")) {
//				formaContOrg.setCiudadNotif(null);
//			}

//			if (formaContOrg.getDelegacionMunicipioNotif().equalsIgnoreCase("")
//					|| formaContOrg.getDelegacionMunicipioNotif().equalsIgnoreCase("-1")) {
//				formaContOrg.setDelegacionMunicipioNotif(null);
//			}

//			if (formaContOrg.getAsentamientoColoniaNotif().equalsIgnoreCase("")
//					|| formaContOrg.getAsentamientoColoniaNotif().equalsIgnoreCase("-1")) {
//				formaContOrg.setAsentamientoColoniaNotif(null);
//			}

//			if (formaContOrg.getTipoAsentamientoNotif().equalsIgnoreCase("")
//					|| formaContOrg.getTipoAsentamientoNotif().equalsIgnoreCase("-1")) {
//				formaContOrg.setTipoAsentamientoNotif(null);
//			}

//			if (formaContOrg.getTipoCalleNotif().equalsIgnoreCase("")
//					|| formaContOrg.getTipoAsentamientoNotif().equalsIgnoreCase("-1")) {
//				formaContOrg.setTipoCalleNotif(null);
//			}

			if (StringUtils.isBlank(formaContOrg.getCalleNotif())) {
				formaContOrg.setCalleNotif("");
			}

			if (StringUtils.isBlank(formaContOrg.getNumExteriorNotif())) {
				formaContOrg.setNumExteriorNotif("");
			}

			if (StringUtils.isBlank(formaContOrg.getNumInteriorNotif())) {
				formaContOrg.setNumInteriorNotif("");
			}

			if (StringUtils.isBlank(formaContOrg.getReferenciasNotif())) {
				formaContOrg.setReferenciasNotif("");
			}

			if (StringUtils.isBlank(formaContOrg.getEntreCalleNotif())) {
				formaContOrg.setEntreCalleNotif("");
			}

			if (StringUtils.isBlank(formaContOrg.getYcalleNotif())) {
				formaContOrg.setYcalleNotif("");
			}

			if (StringUtils.isBlank(formaContOrg.getAliasDomicilioNotif())) {
				formaContOrg.setAliasDomicilioNotif("");
			}

			if (StringUtils.isBlank(formaContOrg.getEdificioNotif())) {
				formaContOrg.setEdificioNotif("");
			}

			if (StringUtils.isBlank(formaContOrg.getLongitudNotif())) {
				formaContOrg.setLongitudNotif("");
			}

			if (StringUtils.isBlank(formaContOrg.getLatitudNotif())) {
				formaContOrg.setLatitudNotif("");
			}
			log.info("CONTACTO ORG:::: Revision domicilio notificaciones....");
			//FIN REVISAMOS que el domicilio notificaciones si viene no sea nulo
			
			// Declaramos la instancia a guardar en BD
			InvolucradoDTO contactoOrgDTO=new InvolucradoDTO();
			
			//seteo el expediente
			ExpedienteDTO expedienteDTO = super.getExpedienteTrabajo(request,formaContOrg.getNumExpediente());
			contactoOrgDTO.setExpedienteDTO(expedienteDTO);
			contactoOrgDTO.setFechaCreacionElemento(new Date());
			log.info("CONTACTO ORG:::: Seteo expediente....");
			//seteo el usuario 				
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuarioDTO.setIdUsuario(Long.parseLong(formaContOrg.getIdUsuario()));
			contactoOrgDTO.setUsuario(usuarioDTO);
			log.info("CONTACTO ORG:::: Seteo usuario....");
					
			// Seteamos el domicilio del contacto
			CalidadDTO calidadDTO = new CalidadDTO();
			calidadDTO.setCalidades(Calidades.DOMICILIO);
			
			// Encapsulamos la informacion del domicilio
			if (Long.parseLong(formaContOrg.getPais()) == 10)// Mexico
			{
				DomicilioDTO domicilioDTO = new DomicilioDTO();

				domicilioDTO.setLatitud(formaContOrg.getLatitud());
				domicilioDTO
						.setLongitud(formaContOrg.getLongitud());
				domicilioDTO.setEdificio(formaContOrg.getEdificio());
				domicilioDTO.setAlias(formaContOrg.getAliasDomicilio());
				domicilioDTO.setEntreCalle2(formaContOrg.getYcalle());
				domicilioDTO.setEntreCalle1(formaContOrg.getEntreCalle());
				domicilioDTO.setReferencias(formaContOrg.getReferencias());
				domicilioDTO.setNumeroInterior(formaContOrg.getNumInterior());
				domicilioDTO.setNumeroExterior(formaContOrg.getNumExterior());
				domicilioDTO.setCalle(formaContOrg.getCalle());
				
				if (StringUtils.isNotBlank(formaContOrg.getTipoCalle()) && !formaContOrg.getTipoCalle().equals("-1")) {
					domicilioDTO.setValorCalleId(new ValorDTO(Long
							.parseLong(formaContOrg.getTipoCalle())));
				}
				domicilioDTO.setCalidadDTO(calidadDTO);
				domicilioDTO.setExpedienteDTO(expedienteDTO);
				domicilioDTO.setFechaCreacionElemento(new Date());
				// delcaramos el nuevo asentamiento
				AsentamientoDTO asentamientoDTO = new AsentamientoDTO();
				
				if (StringUtils.isNotBlank(formaContOrg.getAsentamientoColonia()) &&  !formaContOrg.getAsentamientoColonia().equals("-1")) {
					asentamientoDTO.setAsentamientoId(Long.parseLong(formaContOrg
							.getAsentamientoColonia()));
				}
				asentamientoDTO.setCodigoPostal(formaContOrg.getCodigoPostal());

				// Declaramos el tipo de asentamiento
				if (StringUtils.isNotBlank(formaContOrg.getTipoAsentamiento()) &&  !formaContOrg.getTipoAsentamiento().equals("-1")) {
					TipoAsentamientoDTO tipoAsentamientoDTO = new TipoAsentamientoDTO(
							Long.parseLong(formaContOrg.getTipoAsentamiento()), "");
					asentamientoDTO.setTipoAsentamientoDTO(tipoAsentamientoDTO);
				}
				// Declaramos el municipio
				MunicipioDTO municipioDTO = new MunicipioDTO();
				if (StringUtils.isNotBlank(formaContOrg.getDelegacionMunicipio()) && !formaContOrg.getDelegacionMunicipio().equals("-1")) {
					municipioDTO.setMunicipioId(Long.parseLong(formaContOrg
							.getDelegacionMunicipio()));
				}
				asentamientoDTO.setMunicipioDTO(municipioDTO);

				// declaramos la Ciudad
				CiudadDTO ciudadDTO = new CiudadDTO();
				if (StringUtils.isNotBlank(formaContOrg.getCiudad()) && !formaContOrg.getCiudad().equals("-1")) {
					ciudadDTO.setCiudadId(Long.parseLong(formaContOrg.getCiudad()));
				}
				// declaramos la entidad federativa
				EntidadFederativaDTO entidadFederativaDTO = new EntidadFederativaDTO();
				
				if (StringUtils.isNotBlank(formaContOrg.getEntidadFederativa()) && !formaContOrg.getEntidadFederativa().equals("-1")) {
					entidadFederativaDTO.setEntidadFederativaId(Long
							.parseLong(formaContOrg.getEntidadFederativa()));
				}
				
				if (StringUtils.isNotBlank(formaContOrg.getPais()) && !formaContOrg.getPais().equals("-1")) {
					entidadFederativaDTO.setValorIdPais(new ValorDTO(Long
							.parseLong(formaContOrg.getPais())));
				}
				ciudadDTO.setEntidadFederativaDTO(entidadFederativaDTO);
				asentamientoDTO.setCiudadDTO(ciudadDTO);
				domicilioDTO.setCiudadDTO(ciudadDTO);
				domicilioDTO.setEntidadDTO(entidadFederativaDTO);
				domicilioDTO.setAsentamientoDTO(asentamientoDTO);
				domicilioDTO.setMunicipioDTO(municipioDTO);
				
				// seteamos el domicilio al Hecho
				contactoOrgDTO.setDomicilio(domicilioDTO);
				
				//REVISAMOS SI ES LA MISMA DIRECCION PARA EL DOMICILIO DE NOTIFICACIONES
				if(formaContOrg.getMismoDomicilioNotificaciones().equals("true"))
				{
					contactoOrgDTO.setDomicilioNotificacion(domicilioDTO);
				}
				
			} else// Otro pais
			{
				DomicilioExtranjeroDTO domExtranjreoDTO = new DomicilioExtranjeroDTO();

				domExtranjreoDTO.setLatitud(formaContOrg
						.getLatitud());
				domExtranjreoDTO.setLongitud(formaContOrg
						.getLongitud());
				domExtranjreoDTO.setEdificio(formaContOrg.getEdificio());
				domExtranjreoDTO.setAlias(formaContOrg.getAliasDomicilio());
				domExtranjreoDTO.setEntreCalle2(formaContOrg.getYcalle());
				domExtranjreoDTO.setEntreCalle1(formaContOrg.getEntreCalle());
				domExtranjreoDTO.setReferencias(formaContOrg.getReferencias());
				domExtranjreoDTO.setNumeroInterior(formaContOrg.getNumInterior());
				domExtranjreoDTO.setNumeroExterior(formaContOrg.getNumExterior());
				domExtranjreoDTO.setCalle(formaContOrg.getCalle());
				domExtranjreoDTO.setPais(formaContOrg.getPais());
				domExtranjreoDTO.setCodigoPostal(formaContOrg.getCodigoPostal());
				domExtranjreoDTO.setCiudad(formaContOrg.getCiudad());
				domExtranjreoDTO.setMunicipio(formaContOrg.getDelegacionMunicipio());
				domExtranjreoDTO.setAsentamientoExt(formaContOrg
						.getAsentamientoColonia());
				domExtranjreoDTO.setEstado(formaContOrg.getEntidadFederativa());
				domExtranjreoDTO.setCalidadDTO(calidadDTO);
				domExtranjreoDTO.setExpedienteDTO(expedienteDTO);
				domExtranjreoDTO.setFechaCreacionElemento(new Date());
				domExtranjreoDTO.setCalidadDTO(calidadDTO);
				// seteamos el domicilio extranjero al Hecho
				contactoOrgDTO.setDomicilio(domExtranjreoDTO);
				
				//REVISAMOS SI ES LA MISMA DIRECCION PARA EL DOMICILIO DE NOTIFICACIONES
				if(formaContOrg.getMismoDomicilioNotificaciones().equals("true"))
				{
					contactoOrgDTO.setDomicilioNotificacion(domExtranjreoDTO);
				}
			}
			log.info("CONTACTO ORG:::: Seteo domicilio notificaciones....");
			//FIN seteo domicilio
			//seteamos domicilio notificaciones distinto al domicilio uno
			if(!formaContOrg.getMismoDomicilioNotificaciones().equals("true"))
			{
				if (Long.parseLong(formaContOrg.getPaisNotif()) == 10)// Mexico
				{
					DomicilioDTO domicilioDTO = new DomicilioDTO();

					domicilioDTO.setLatitud(formaContOrg.getLatitudNotif());
					domicilioDTO
							.setLongitud(formaContOrg.getLongitudNotif());
					domicilioDTO.setEdificio(formaContOrg.getEdificioNotif());
					domicilioDTO.setAlias(formaContOrg.getAliasDomicilioNotif());
					domicilioDTO.setEntreCalle2(formaContOrg.getYcalleNotif());
					domicilioDTO.setEntreCalle1(formaContOrg.getEntreCalleNotif());
					domicilioDTO.setReferencias(formaContOrg.getReferenciasNotif());
					domicilioDTO.setNumeroInterior(formaContOrg.getNumInteriorNotif());
					domicilioDTO.setNumeroExterior(formaContOrg.getNumExteriorNotif());
					domicilioDTO.setCalle(formaContOrg.getCalleNotif());
					if (StringUtils.isNotBlank(formaContOrg.getTipoCalleNotif()) && !formaContOrg.getTipoCalleNotif().equals("-1")) {
						domicilioDTO.setValorCalleId(new ValorDTO(Long
								.parseLong(formaContOrg.getTipoCalleNotif())));
					}
					domicilioDTO.setCalidadDTO(calidadDTO);
					domicilioDTO.setExpedienteDTO(expedienteDTO);
					domicilioDTO.setFechaCreacionElemento(new Date());
					// delcaramos el nuevo asentamiento
					AsentamientoDTO asentamientoDTO = new AsentamientoDTO();
					if (StringUtils.isNotBlank(formaContOrg.getAsentamientoColoniaNotif()) && !formaContOrg.getAsentamientoColoniaNotif().equals("-1")) {
						asentamientoDTO.setAsentamientoId(Long.parseLong(formaContOrg
								.getAsentamientoColoniaNotif()));
					}
					asentamientoDTO.setCodigoPostal(formaContOrg.getCodigoPostalNotif());

					// Declaramos el tipo de asentamiento
					if (StringUtils.isNotBlank(formaContOrg.getTipoAsentamientoNotif()) && !formaContOrg.getTipoAsentamientoNotif().equals("-1")) {
						TipoAsentamientoDTO tipoAsentamientoDTO = new TipoAsentamientoDTO(
								Long.parseLong(formaContOrg.getTipoAsentamientoNotif()), "");
						asentamientoDTO.setTipoAsentamientoDTO(tipoAsentamientoDTO);
					}
					// Declaramos el municipio
					MunicipioDTO municipioDTO = new MunicipioDTO();
					if (StringUtils.isNotBlank(formaContOrg.getDelegacionMunicipioNotif()) && !formaContOrg.getDelegacionMunicipioNotif().equals("-1")) {
						municipioDTO.setMunicipioId(Long.parseLong(formaContOrg
								.getDelegacionMunicipioNotif()));
					}
					asentamientoDTO.setMunicipioDTO(municipioDTO);

					// declaramos la Ciudad
					CiudadDTO ciudadDTO = new CiudadDTO();
					if (StringUtils.isNotBlank(formaContOrg.getCiudadNotif()) && !formaContOrg.getCiudadNotif().equals("-1")) {
						ciudadDTO.setCiudadId(Long.parseLong(formaContOrg.getCiudadNotif()));
					}
					// declaramos la entidad federativa
					EntidadFederativaDTO entidadFederativaDTO = new EntidadFederativaDTO();
					if (StringUtils.isNotBlank(formaContOrg.getEntidadFederativaNotif()) && !formaContOrg.getEntidadFederativaNotif().equals("-1")) {
						entidadFederativaDTO.setEntidadFederativaId(Long
								.parseLong(formaContOrg.getEntidadFederativaNotif()));
					}
					if (StringUtils.isNotBlank(formaContOrg.getPaisNotif()) && !formaContOrg.getPaisNotif().equals("-1")) {
						entidadFederativaDTO.setValorIdPais(new ValorDTO(Long
								.parseLong(formaContOrg.getPaisNotif())));
					}
					ciudadDTO.setEntidadFederativaDTO(entidadFederativaDTO);
					asentamientoDTO.setCiudadDTO(ciudadDTO);
					domicilioDTO.setCiudadDTO(ciudadDTO);
					domicilioDTO.setEntidadDTO(entidadFederativaDTO);
					domicilioDTO.setAsentamientoDTO(asentamientoDTO);
					domicilioDTO.setMunicipioDTO(municipioDTO);
					
					// seteamos el domicilio al Hecho
					contactoOrgDTO.setDomicilioNotificacion(domicilioDTO);
					
				} else// Otro pais
				{
					DomicilioExtranjeroDTO domExtranjreoDTO = new DomicilioExtranjeroDTO();

					domExtranjreoDTO.setLatitud(formaContOrg
							.getLatitudNotif());
					domExtranjreoDTO.setLongitud(formaContOrg
							.getLongitudNotif());
					domExtranjreoDTO.setEdificio(formaContOrg.getEdificioNotif());
					domExtranjreoDTO.setAlias(formaContOrg.getAliasDomicilioNotif());
					domExtranjreoDTO.setEntreCalle2(formaContOrg.getYcalleNotif());
					domExtranjreoDTO.setEntreCalle1(formaContOrg.getEntreCalleNotif());
					domExtranjreoDTO.setReferencias(formaContOrg.getReferenciasNotif());
					domExtranjreoDTO.setNumeroInterior(formaContOrg.getNumInteriorNotif());
					domExtranjreoDTO.setNumeroExterior(formaContOrg.getNumExteriorNotif());
					domExtranjreoDTO.setCalle(formaContOrg.getCalleNotif());
					domExtranjreoDTO.setPais(formaContOrg.getPaisNotif());
					domExtranjreoDTO.setCodigoPostal(formaContOrg.getCodigoPostalNotif());
					domExtranjreoDTO.setCiudad(formaContOrg.getCiudadNotif());
					domExtranjreoDTO.setMunicipio(formaContOrg.getDelegacionMunicipioNotif());
					domExtranjreoDTO.setAsentamientoExt(formaContOrg
							.getAsentamientoColoniaNotif());
					domExtranjreoDTO.setEstado(formaContOrg.getEntidadFederativaNotif());
					domExtranjreoDTO.setCalidadDTO(calidadDTO);
					domExtranjreoDTO.setExpedienteDTO(expedienteDTO);
					domExtranjreoDTO.setFechaCreacionElemento(new Date());
					domExtranjreoDTO.setCalidadDTO(calidadDTO);
					// seteamos el domicilio extranjero al Hecho
					contactoOrgDTO.setDomicilioNotificacion(domExtranjreoDTO);
				}
			}
			log.info("CONTACTO ORG:::: Seteo domicilio notificaciones....");
			//FIN seteamos domicilio notificaciones distinto al domicilio uno			
			NombreDemograficoDTO nombre= new NombreDemograficoDTO();
			nombre.setNombre(formaContOrg.getNombre());
			nombre.setApellidoPaterno(formaContOrg.getApellidoPaterno());
			nombre.setApellidoMaterno(formaContOrg.getApellidoMaterno());
			nombre.setRfc(formaContOrg.getRfc());
			nombre.setCurp(formaContOrg.getCurp());
			nombre.setSexo(formaContOrg.getSexo());
			nombre.setFechaNacimiento(DateUtils.obtener(formaContOrg.getFechaNacimiento()));
			nombre.setEdadAproximada(formaContOrg.getEdadAproximada());
			
			
			List<NombreDemograficoDTO> nombreL= new ArrayList<NombreDemograficoDTO>();
			nombreL.add(nombre);
			contactoOrgDTO.setNombresDemograficoDTO(nombreL);
			contactoOrgDTO.setFechaCreacionElemento(new Date());
			
			ValorDTO paisValorDTO=new ValorDTO(Long.parseLong(formaContOrg.getPais()));
			nombre.setPaisValorDTO(paisValorDTO);
			EntidadFederativaDTO entidadFederativaDTO=new EntidadFederativaDTO(Long.parseLong(formaContOrg.getEntFederativaNacimiento()));
			nombre.setEntidadFederativaDTO(entidadFederativaDTO);
			MunicipioDTO municipioDTO = new MunicipioDTO(Long.parseLong(formaContOrg.getMunicipioNacimiento()));
			nombre.setMunicipioDTO(municipioDTO);

			//seteo la calidad
			CalidadDTO calidadInvDTO=new CalidadDTO();
			calidadInvDTO.setCalidades(Calidades.CONTACTO_ORGANIZACION);
			contactoOrgDTO.setCalidadDTO(calidadInvDTO);
			ValorDTO idiomaDTO= new ValorDTO(Long.parseLong(formaContOrg.getIdioma()));
			contactoOrgDTO.setValorIdIdioma(idiomaDTO);
			//ValorDTO religionDTO=new ValorDTO(Long.parseLong(formaContOrg.getReligion()));
			//contactoOrgDTO.setValorIdReligion(religionDTO);
			
			ValorDTO escolaridadDTO= new ValorDTO(Long.parseLong(formaContOrg.getEscolaridad()));
			contactoOrgDTO.setValorIdEscolaridad(escolaridadDTO);
			ValorDTO estadoCivilDTO = new ValorDTO(Long.parseLong(formaContOrg.getEstadoCivil()));
			contactoOrgDTO.setValorIdEstadoCivil(estadoCivilDTO);
			//private String alias; NO APLICA PARA CONTACTO ORGANIZACIONAL

			//seteo las ocupaciones
			if(!formaContOrg.getOcupacion().equalsIgnoreCase(""))
			{
				//barremos las ocupaciones
				String[] ocupaciones=formaContOrg.getOcupacion().split(",");
				List<ValorDTO> ocupacionesL=new ArrayList<ValorDTO>();
				for (String ocupacion : ocupaciones) 
				{
				 ValorDTO ocupacionV=new ValorDTO(Long.parseLong(ocupacion));
				 ocupacionesL.add(ocupacionV);
				}
				contactoOrgDTO.setValorIdOcupacion(ocupacionesL);
			}
			//seteo las nacionalidades
			if(!formaContOrg.getNacionalidad().equalsIgnoreCase(""))
			{
				//barremos las ocupaciones
				String[] nacionalidades=formaContOrg.getNacionalidad().split(",");
				List<ValorDTO> nacionalidadesL=new ArrayList<ValorDTO>();
				for (String nacionalidad : nacionalidades) 
				{
				 ValorDTO nacionalidadV=new ValorDTO(Long.parseLong(nacionalidad));
				 nacionalidadesL.add(nacionalidadV);
				}
				contactoOrgDTO.setValorIdNacionalidad(nacionalidadesL);
			}
			//FIN seteo datos generales
			
			log.info("CONTACTO ORG:::: Seteo datos generales....");
			//FIN seteo datos generales
			
			//seteo medios de contacto
			//agregamos los correos
			String[] correos=formaContOrg.getMedioContactoCorreo().split(",");
			List<CorreoElectronicoDTO> correosL=new ArrayList<CorreoElectronicoDTO>();
			for (String correoElectronicoS : correos) {
				CorreoElectronicoDTO correoDTO=new CorreoElectronicoDTO();
				correoDTO.setDireccionElectronica(correoElectronicoS);
				correosL.add(correoDTO);
			}
			contactoOrgDTO.setCorreosDTO(correosL);
			//agregamos los telefonos
			String telefonoBien=formaContOrg.getMedioContactoTelefono().substring(0, formaContOrg.getMedioContactoTelefono().length()-1 );
			log.info(telefonoBien);
			String[] telefonos=telefonoBien.split("\\|");
			List<TelefonoDTO> telefonosL=new ArrayList<TelefonoDTO>();
			for (String telefonoS : telefonos) {
				TelefonoDTO telefonoDTO=new TelefonoDTO();
				log.info("mmm::: "+telefonoS);
				String[] telefonoXPartes=telefonoS.split(",");
				ValorDTO telefonoValor=new ValorDTO(Long.parseLong(telefonoXPartes[0]));
				telefonoDTO.setValorTipoTelefono(telefonoValor);
				telefonoDTO.setCodigoPais(telefonoXPartes[1]);
				telefonoDTO.setCodigoArea(telefonoXPartes[2]);
				telefonoDTO.setNumeroTelefonico(telefonoXPartes[3]);
				telefonosL.add(telefonoDTO);
			}
			contactoOrgDTO.setTelefonosDTO(telefonosL);
			log.info("CONTACTO ORG:::: Seteo medios de contacto....");
			//FIN seteo medios de contacto
			
			//seteo documento de identifacion
			ValorDTO docIdentificacion=new ValorDTO();
			docIdentificacion.setIdCampo(Long.parseLong(formaContOrg.getDocIdentificacion()));			
			contactoOrgDTO.setValorIdIdentificaion(docIdentificacion);
			contactoOrgDTO.setFolioIdentificacion(formaContOrg.getFolioDoc());
			log.info("CONTACTO ORG:::: Seteo documento de identifacion....");
			//FIN seteo documento de identifacion
			
			//generamos la instancia de organizacion con su Id
			OrganizacionDTO organizacionDTO = new OrganizacionDTO();
			organizacionDTO.setElementoId(formaContOrg.getIdOrganizacion());
			log.info("CONTACTO ORG:::: ID org ::: "+formaContOrg.getIdOrganizacion());
			//guardamos al contacto de la organizacion
			PersonaDTO personaDTO = organizacionDelegate.ingresarPersonaOrganizacion(contactoOrgDTO, organizacionDTO);

			// revisamos si el guardado fue exitoso para mandar el xml
			// correspondiente
			if (organizacionDTO != null
					&& organizacionDTO.getOrganizacionId() != null) {
				converter.alias("organizacionDTO", PersonaDTO.class);
				String xml = converter.toXML(personaDTO);
				log.info(xml);
				escribirRespuesta(response, xml);
			} else {
				organizacionDTO.setOrganizacionId(0L);
				converter.alias("organizacionDTO", PersonaDTO.class);
				String xml = converter.toXML(personaDTO);
				log.info(xml);
				escribirRespuesta(response, xml);
			}
			log.info("Termina ejecucion Action guardar Contacto Organizacion - FIN ");
		} catch (NSJPNegocioException e) {
			log.error(e.getMessage(), e);
			escribir(response, "", e);
		}
		return null;
	}

	/**
	 * Metodo utilizado para guardar un representante legal de una organizacion
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward guardarRepLegalOrg(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			log.info("ejecutando Action guardar Representante Legal");
			// hacemos el cast de la forma de Contacto Organizacion
			IngresarIndividuoForm formaContOrg = (IngresarIndividuoForm) form;

			log.info("FORMA REPRESENTANTE LEGAL:::::::::::::::::::::::");			
			//revisamos q los datos generales no vengan nulos
			if (StringUtils.isBlank(formaContOrg.getNombre()) || formaContOrg.getNombre().equals(-1)){
				formaContOrg.setNombre(null);
			}
			if (StringUtils.isBlank(formaContOrg.getApellidoPaterno()) || formaContOrg.getApellidoPaterno().equals(-1)) {
				formaContOrg.setApellidoPaterno("");
			}
			if (StringUtils.isBlank(formaContOrg.getApellidoMaterno()) || formaContOrg.getApellidoMaterno().equals(-1)) {
				formaContOrg.setApellidoMaterno("");
			}
			if (StringUtils.isBlank(formaContOrg.getSexo())) {
				formaContOrg.setSexo(null);
			}
			if (StringUtils.isBlank(formaContOrg.getCurp()) || formaContOrg.getCurp().equals(-1)) {
				formaContOrg.setCurp(null);
			}
			if (StringUtils.isBlank(formaContOrg.getRfc()) || formaContOrg.getRfc().equals(-1)) {
				formaContOrg.setRfc(null);
			}
			if (StringUtils.isBlank(formaContOrg.getIdioma())|| formaContOrg.getIdioma().equals("- Selecciona -")  || formaContOrg.getIdioma().equals(-1)) {
				formaContOrg.setIdioma(null);
			}
			if (StringUtils.isBlank(formaContOrg.getEscolaridad()) || formaContOrg.getEscolaridad().equals("- Selecciona -") || formaContOrg.getEscolaridad().equals(-1)) {
				formaContOrg.setEscolaridad(null);
			}
			if (StringUtils.isBlank(formaContOrg.getEstadoCivil()) || formaContOrg.getEstadoCivil().equals("- Selecciona -") || formaContOrg.getEstadoCivil().equals(-1)) {
				formaContOrg.setEstadoCivil(null);
			}
			if (StringUtils.isBlank(formaContOrg.getOcupacion()) || formaContOrg.getOcupacion().equals("- Selecciona -") || formaContOrg.getOcupacion().equals(-1) || formaContOrg.getOcupacion().equals("undefined")) {
				formaContOrg.setOcupacion(null);
			}
			if (StringUtils.isBlank(formaContOrg.getNacionalidad()) || formaContOrg.getNacionalidad().equals("- Selecciona -") || formaContOrg.getNacionalidad().equals(-1)) {
				formaContOrg.setNacionalidad(null);
			}
			if (StringUtils.isBlank(formaContOrg.getFechaNacimiento()) || formaContOrg.getFechaNacimiento().equals(-1)) {
				formaContOrg.setFechaNacimiento(null);
			}
			if (formaContOrg.getEdadAproximada()==null) {
				formaContOrg.setEdadAproximada(null);
			}
			if (StringUtils.isBlank(formaContOrg.getPaisNacimiento()) || formaContOrg.getPaisNacimiento().equals("- Selecciona -")  || formaContOrg.getPaisNacimiento().equals(-1)) {
				formaContOrg.setPaisNacimiento(null);
			}
			if (StringUtils.isBlank(formaContOrg.getEntFederativaNacimiento()) || formaContOrg.getEntFederativaNacimiento().equals("- Selecciona -")  || formaContOrg.getEntFederativaNacimiento().equals(-1) || formaContOrg.getEntFederativaNacimiento().equals("-1") || formaContOrg.getEntFederativaNacimiento().equals("undefined")) {
				formaContOrg.setEntFederativaNacimiento(null);
			}
			if (StringUtils.isBlank(formaContOrg.getMunicipioNacimiento()) || formaContOrg.getMunicipioNacimiento().equals("- Selecciona -")  || formaContOrg.getMunicipioNacimiento().equals(-1) || formaContOrg.getMunicipioNacimiento().equals("-1") || formaContOrg.getMunicipioNacimiento().equals("undefined")) {
				formaContOrg.setMunicipioNacimiento(null);
			}
			if (StringUtils.isBlank(formaContOrg.getFechaIngreso()) || formaContOrg.getFechaIngreso().equals("- Selecciona -")  || formaContOrg.getFechaIngreso().equals(-1)) {
				formaContOrg.setFechaIngreso(null);
			}
			if (StringUtils.isBlank(formaContOrg.getReligion()) || formaContOrg.getReligion().equals("- Selecciona -")  || formaContOrg.getReligion().equals(-1)) {
				formaContOrg.setReligion(null);
			}
			if (StringUtils.isBlank(formaContOrg.getAlias()) || formaContOrg.getAlias().equals(-1)) {
				formaContOrg.setAlias(null);
			}
			log.info("REP LEGAL:::: Revision datos generales....");
			//FIN revisamos q los datos generales no vengan nulos
			//revisamos que los datos del documento de identificacion no vengan vacios
			if (StringUtils.isBlank(formaContOrg.getDocIdentificacion())|| formaContOrg.getDocIdentificacion().equals("- Selecciona -")  || formaContOrg.getDocIdentificacion().equals(-1) || formaContOrg.getDocIdentificacion().equals("-1") || formaContOrg.getDocIdentificacion().equals("undefined")) {
				formaContOrg.setIdioma(null);
			}
			if (StringUtils.isBlank(formaContOrg.getFolioDoc()) || formaContOrg.getFolioDoc().equals(-1)) {
				formaContOrg.setFolioDoc(null);
			}
			log.info("REP LEGAL:::: Revision documento de identificacion....");

			if (StringUtils.isBlank(formaContOrg.getCodigoPostal())) {
				formaContOrg.setCodigoPostal("");
			}
			if (StringUtils.isBlank(formaContOrg.getCalle())) {
				formaContOrg.setCalle("");
			}
			if (StringUtils.isBlank(formaContOrg.getNumExterior())) {
				formaContOrg.setNumExterior("");
			}
			if (StringUtils.isBlank(formaContOrg.getNumInterior())) {
				formaContOrg.setNumInterior("");
			}
			if (StringUtils.isBlank(formaContOrg.getReferencias())) {
				formaContOrg.setReferencias("");
			}
			if (StringUtils.isBlank(formaContOrg.getEntreCalle())) {
				formaContOrg.setEntreCalle("");
			}
			if (StringUtils.isBlank(formaContOrg.getYcalle())) {
				formaContOrg.setYcalle("");
			}
			if (StringUtils.isBlank(formaContOrg.getAliasDomicilio())) {
				formaContOrg.setAliasDomicilio("");
			}
			if (StringUtils.isBlank(formaContOrg.getEdificio())) {
				formaContOrg.setEdificio("");
			}
			if (StringUtils.isBlank(formaContOrg.getLongitud())) {
				formaContOrg.setLongitud("");
			}
			if (StringUtils.isBlank(formaContOrg.getLatitud())) {
				formaContOrg.setLatitud("");
			}
			if (StringUtils.isBlank(formaContOrg.getCodigoPostalNotif())) {
				formaContOrg.setCodigoPostalNotif("");
			}
			if (StringUtils.isBlank(formaContOrg.getCalleNotif())) {
				formaContOrg.setCalleNotif("");
			}
			if (StringUtils.isBlank(formaContOrg.getNumExteriorNotif())) {
				formaContOrg.setNumExteriorNotif("");
			}
			if (StringUtils.isBlank(formaContOrg.getNumInteriorNotif())) {
				formaContOrg.setNumInteriorNotif("");
			}
			if (StringUtils.isBlank(formaContOrg.getReferenciasNotif())) {
				formaContOrg.setReferenciasNotif("");
			}
			if (StringUtils.isBlank(formaContOrg.getEntreCalleNotif())) {
				formaContOrg.setEntreCalleNotif("");
			}
			if (StringUtils.isBlank(formaContOrg.getYcalleNotif())) {
				formaContOrg.setYcalleNotif("");
			}
			if (StringUtils.isBlank(formaContOrg.getAliasDomicilioNotif())) {
				formaContOrg.setAliasDomicilioNotif("");
			}
			if (StringUtils.isBlank(formaContOrg.getEdificioNotif())) {
				formaContOrg.setEdificioNotif("");
			}
			if (StringUtils.isBlank(formaContOrg.getLongitudNotif())) {
				formaContOrg.setLongitudNotif("");
			}
			if (StringUtils.isBlank(formaContOrg.getLatitudNotif())) {
				formaContOrg.setLatitudNotif("");
			}
			log.info("REP LEGAL:::: Revision domicilio notificaciones....");
			// Declaramos la instancia a guardar en BD
			InvolucradoDTO contactoOrgDTO=new InvolucradoDTO();
			
			//seteo el expediente
			ExpedienteDTO expedienteDTO = super.getExpedienteTrabajo(request,formaContOrg.getNumExpediente());
			contactoOrgDTO.setExpedienteDTO(expedienteDTO);
			contactoOrgDTO.setFechaCreacionElemento(new Date());
			log.info("REP LEGAL:::: Seteo expediente....");
			//seteo el usuario 				
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuarioDTO.setIdUsuario(Long.parseLong(formaContOrg.getIdUsuario()));
			contactoOrgDTO.setUsuario(usuarioDTO);
			log.info("REP LEGAL:::: Seteo usuario....");
					
			// Seteamos el domicilio del contacto
			CalidadDTO calidadDTO = new CalidadDTO();
			calidadDTO.setCalidades(Calidades.DOMICILIO);
			
			// Encapsulamos la informacion del domicilio
			if (Long.parseLong(formaContOrg.getPais()) == 10)// Mexico
			{
				DomicilioDTO domicilioDTO = new DomicilioDTO();

				domicilioDTO.setLatitud(formaContOrg.getLatitud());
				domicilioDTO
						.setLongitud(formaContOrg.getLongitud());
				domicilioDTO.setEdificio(formaContOrg.getEdificio());
				domicilioDTO.setAlias(formaContOrg.getAliasDomicilio());
				domicilioDTO.setEntreCalle2(formaContOrg.getYcalle());
				domicilioDTO.setEntreCalle1(formaContOrg.getEntreCalle());
				domicilioDTO.setReferencias(formaContOrg.getReferencias());
				domicilioDTO.setNumeroInterior(formaContOrg.getNumInterior());
				domicilioDTO.setNumeroExterior(formaContOrg.getNumExterior());
				domicilioDTO.setCalle(formaContOrg.getCalle());
				if (StringUtils.isNotBlank(formaContOrg.getTipoCalle() )&& !formaContOrg.getTipoCalle().equals("-1")) {
					domicilioDTO.setValorCalleId(new ValorDTO(Long
							.parseLong(formaContOrg.getTipoCalle())));
				}
				domicilioDTO.setCalidadDTO(calidadDTO);
				domicilioDTO.setExpedienteDTO(expedienteDTO);
				domicilioDTO.setFechaCreacionElemento(new Date());
				// delcaramos el nuevo asentamiento
				AsentamientoDTO asentamientoDTO = new AsentamientoDTO();
				if (StringUtils.isNotBlank(formaContOrg.getAsentamientoColonia()) && !formaContOrg.getAsentamientoColonia().equals("-1")) {
					asentamientoDTO.setAsentamientoId(Long.parseLong(formaContOrg
							.getAsentamientoColonia()));
				}
				asentamientoDTO.setCodigoPostal(formaContOrg.getCodigoPostal());

				// Declaramos el tipo de asentamiento
				if (StringUtils.isNotBlank(formaContOrg.getTipoAsentamiento()) && !formaContOrg.getTipoAsentamiento().equals("-1")) {
					TipoAsentamientoDTO tipoAsentamientoDTO = new TipoAsentamientoDTO(
							Long.parseLong(formaContOrg.getTipoAsentamiento()), "");
					asentamientoDTO.setTipoAsentamientoDTO(tipoAsentamientoDTO);
				}
				// Declaramos el municipio
				MunicipioDTO municipioDTO = new MunicipioDTO();
				if (StringUtils.isNotBlank(formaContOrg.getDelegacionMunicipio()) && !formaContOrg.getDelegacionMunicipio().equals("-1")) {
					municipioDTO.setMunicipioId(Long.parseLong(formaContOrg
							.getDelegacionMunicipio()));
				}
				asentamientoDTO.setMunicipioDTO(municipioDTO);

				// declaramos la Ciudad
				CiudadDTO ciudadDTO = new CiudadDTO();
				if (StringUtils.isNotBlank(formaContOrg.getCiudad()) && !formaContOrg.getCiudad().equals("-1")) {
					ciudadDTO.setCiudadId(Long.parseLong(formaContOrg.getCiudad()));
				}
				// declaramos la entidad federativa
				EntidadFederativaDTO entidadFederativaDTO = new EntidadFederativaDTO();
				if (StringUtils.isNotBlank(formaContOrg.getEntidadFederativa()) && !formaContOrg.getEntidadFederativa().equals("-1")) {
					entidadFederativaDTO.setEntidadFederativaId(Long
							.parseLong(formaContOrg.getEntidadFederativa()));
				}
				if (StringUtils.isNotBlank(formaContOrg.getPais()) && !formaContOrg.getPais().equals("-1")) {
					entidadFederativaDTO.setValorIdPais(new ValorDTO(Long
							.parseLong(formaContOrg.getPais())));
				}
				ciudadDTO.setEntidadFederativaDTO(entidadFederativaDTO);
				asentamientoDTO.setCiudadDTO(ciudadDTO);
				domicilioDTO.setCiudadDTO(ciudadDTO);
				domicilioDTO.setEntidadDTO(entidadFederativaDTO);
				domicilioDTO.setAsentamientoDTO(asentamientoDTO);
				domicilioDTO.setMunicipioDTO(municipioDTO);
				
				// seteamos el domicilio al Hecho
				contactoOrgDTO.setDomicilio(domicilioDTO);
				
				//REVISAMOS SI ES LA MISMA DIRECCION PARA EL DOMICILIO DE NOTIFICACIONES
				if(formaContOrg.getMismoDomicilioNotificaciones().equals("true"))
				{
					contactoOrgDTO.setDomicilioNotificacion(domicilioDTO);
				}
				
			} else// Otro pais
			{
				DomicilioExtranjeroDTO domExtranjreoDTO = new DomicilioExtranjeroDTO();

				domExtranjreoDTO.setLatitud(formaContOrg
						.getLatitud());
				domExtranjreoDTO.setLongitud(formaContOrg
						.getLongitud());
				domExtranjreoDTO.setEdificio(formaContOrg.getEdificio());
				domExtranjreoDTO.setAlias(formaContOrg.getAliasDomicilio());
				domExtranjreoDTO.setEntreCalle2(formaContOrg.getYcalle());
				domExtranjreoDTO.setEntreCalle1(formaContOrg.getEntreCalle());
				domExtranjreoDTO.setReferencias(formaContOrg.getReferencias());
				domExtranjreoDTO.setNumeroInterior(formaContOrg.getNumInterior());
				domExtranjreoDTO.setNumeroExterior(formaContOrg.getNumExterior());
				domExtranjreoDTO.setCalle(formaContOrg.getCalle());
				domExtranjreoDTO.setPais(formaContOrg.getPais());
				domExtranjreoDTO.setCodigoPostal(formaContOrg.getCodigoPostal());
				domExtranjreoDTO.setCiudad(formaContOrg.getCiudad());
				domExtranjreoDTO.setMunicipio(formaContOrg.getDelegacionMunicipio());
				domExtranjreoDTO.setAsentamientoExt(formaContOrg
						.getAsentamientoColonia());
				domExtranjreoDTO.setEstado(formaContOrg.getEntidadFederativa());
				domExtranjreoDTO.setCalidadDTO(calidadDTO);
				domExtranjreoDTO.setExpedienteDTO(expedienteDTO);
				domExtranjreoDTO.setFechaCreacionElemento(new Date());
				domExtranjreoDTO.setCalidadDTO(calidadDTO);
				// seteamos el domicilio extranjero al Hecho
				contactoOrgDTO.setDomicilio(domExtranjreoDTO);
				
				//REVISAMOS SI ES LA MISMA DIRECCION PARA EL DOMICILIO DE NOTIFICACIONES
				if(formaContOrg.getMismoDomicilioNotificaciones()!=null && formaContOrg.getMismoDomicilioNotificaciones().equals("true"))
				{
					contactoOrgDTO.setDomicilioNotificacion(domExtranjreoDTO);
				}
			}
			log.info("REP LEGAL:::: Seteo domicilio notificaciones....");
			//FIN seteo domicilio
			//seteamos domicilio notificaciones distinto al domicilio uno
			if(!formaContOrg.getMismoDomicilioNotificaciones().equals("true"))
			{
				if (Long.parseLong(formaContOrg.getPaisNotif()) == 10)// Mexico
				{
					DomicilioDTO domicilioDTO = new DomicilioDTO();

					domicilioDTO.setLatitud(formaContOrg.getLatitudNotif());
					domicilioDTO
							.setLongitud(formaContOrg.getLongitudNotif());
					domicilioDTO.setEdificio(formaContOrg.getEdificioNotif());
					domicilioDTO.setAlias(formaContOrg.getAliasDomicilioNotif());
					domicilioDTO.setEntreCalle2(formaContOrg.getYcalleNotif());
					domicilioDTO.setEntreCalle1(formaContOrg.getEntreCalleNotif());
					domicilioDTO.setReferencias(formaContOrg.getReferenciasNotif());
					domicilioDTO.setNumeroInterior(formaContOrg.getNumInteriorNotif());
					domicilioDTO.setNumeroExterior(formaContOrg.getNumExteriorNotif());
					domicilioDTO.setCalle(formaContOrg.getCalleNotif());
					if (StringUtils.isNotBlank(formaContOrg.getTipoCalleNotif()) && !formaContOrg.getTipoCalleNotif().equals("-1")) {
						domicilioDTO.setValorCalleId(new ValorDTO(Long
								.parseLong(formaContOrg.getTipoCalleNotif())));
					}
					domicilioDTO.setCalidadDTO(calidadDTO);
					domicilioDTO.setExpedienteDTO(expedienteDTO);
					domicilioDTO.setFechaCreacionElemento(new Date());
					// delcaramos el nuevo asentamiento
					AsentamientoDTO asentamientoDTO = new AsentamientoDTO();
					if (StringUtils.isNotBlank(formaContOrg.getAsentamientoColoniaNotif()) && !formaContOrg.getAsentamientoColoniaNotif().equals("-1")) {
						asentamientoDTO.setAsentamientoId(Long.parseLong(formaContOrg
								.getAsentamientoColoniaNotif()));
					}
					asentamientoDTO.setCodigoPostal(formaContOrg.getCodigoPostalNotif());

					// Declaramos el tipo de asentamiento
					if (StringUtils.isNotBlank(formaContOrg.getTipoAsentamientoNotif()) && !formaContOrg.getTipoAsentamientoNotif().equals("-1")) {
						TipoAsentamientoDTO tipoAsentamientoDTO = new TipoAsentamientoDTO(
								Long.parseLong(formaContOrg.getTipoAsentamientoNotif()), "");
						asentamientoDTO.setTipoAsentamientoDTO(tipoAsentamientoDTO);
					}
					// Declaramos el municipio
					MunicipioDTO municipioDTO = new MunicipioDTO();
					if (StringUtils.isNotBlank(formaContOrg.getDelegacionMunicipioNotif()) && !formaContOrg.getDelegacionMunicipioNotif().equals("-1")) {
						municipioDTO.setMunicipioId(Long.parseLong(formaContOrg
								.getDelegacionMunicipioNotif()));
					}
					asentamientoDTO.setMunicipioDTO(municipioDTO);

					// declaramos la Ciudad
					CiudadDTO ciudadDTO = new CiudadDTO();
					if (StringUtils.isNotBlank(formaContOrg.getCiudadNotif()) && !formaContOrg.getCiudadNotif().equals("-1")) {
						ciudadDTO.setCiudadId(Long.parseLong(formaContOrg.getCiudadNotif()));
					}
					// declaramos la entidad federativa
					EntidadFederativaDTO entidadFederativaDTO = new EntidadFederativaDTO();
					if (StringUtils.isNotBlank(formaContOrg.getEntidadFederativaNotif()) && !formaContOrg.getEntidadFederativaNotif().equals("-1")) {
						entidadFederativaDTO.setEntidadFederativaId(Long
								.parseLong(formaContOrg.getEntidadFederativaNotif()));
					}
					if (StringUtils.isNotBlank(formaContOrg.getPaisNotif()) && !formaContOrg.getPaisNotif().equals("-1")) {
						entidadFederativaDTO.setValorIdPais(new ValorDTO(Long
								.parseLong(formaContOrg.getPaisNotif())));
					}
					ciudadDTO.setEntidadFederativaDTO(entidadFederativaDTO);
					asentamientoDTO.setCiudadDTO(ciudadDTO);
					domicilioDTO.setCiudadDTO(ciudadDTO);
					domicilioDTO.setEntidadDTO(entidadFederativaDTO);
					domicilioDTO.setAsentamientoDTO(asentamientoDTO);
					domicilioDTO.setMunicipioDTO(municipioDTO);
					
					// seteamos el domicilio al Hecho
					contactoOrgDTO.setDomicilioNotificacion(domicilioDTO);
					
				} else// Otro pais
				{
					DomicilioExtranjeroDTO domExtranjreoDTO = new DomicilioExtranjeroDTO();

					domExtranjreoDTO.setLatitud(formaContOrg
							.getLatitudNotif());
					domExtranjreoDTO.setLongitud(formaContOrg
							.getLongitudNotif());
					domExtranjreoDTO.setEdificio(formaContOrg.getEdificioNotif());
					domExtranjreoDTO.setAlias(formaContOrg.getAliasDomicilioNotif());
					domExtranjreoDTO.setEntreCalle2(formaContOrg.getYcalleNotif());
					domExtranjreoDTO.setEntreCalle1(formaContOrg.getEntreCalleNotif());
					domExtranjreoDTO.setReferencias(formaContOrg.getReferenciasNotif());
					domExtranjreoDTO.setNumeroInterior(formaContOrg.getNumInteriorNotif());
					domExtranjreoDTO.setNumeroExterior(formaContOrg.getNumExteriorNotif());
					domExtranjreoDTO.setCalle(formaContOrg.getCalleNotif());
					domExtranjreoDTO.setPais(formaContOrg.getPaisNotif());
					domExtranjreoDTO.setCodigoPostal(formaContOrg.getCodigoPostalNotif());
					domExtranjreoDTO.setCiudad(formaContOrg.getCiudadNotif());
					domExtranjreoDTO.setMunicipio(formaContOrg.getDelegacionMunicipioNotif());
					domExtranjreoDTO.setAsentamientoExt(formaContOrg
							.getAsentamientoColoniaNotif());
					domExtranjreoDTO.setEstado(formaContOrg.getEntidadFederativaNotif());
					domExtranjreoDTO.setCalidadDTO(calidadDTO);
					domExtranjreoDTO.setExpedienteDTO(expedienteDTO);
					domExtranjreoDTO.setFechaCreacionElemento(new Date());
					domExtranjreoDTO.setCalidadDTO(calidadDTO);
					// seteamos el domicilio extranjero al Hecho
					contactoOrgDTO.setDomicilioNotificacion(domExtranjreoDTO);
				}
			}
			log.info("REP LEGAL:::: Seteo domicilio notificaciones....");
			//FIN seteamos domicilio notificaciones distinto al domicilio uno
			
			NombreDemograficoDTO nombre= new NombreDemograficoDTO();
			nombre.setNombre(formaContOrg.getNombre());
			nombre.setApellidoPaterno(formaContOrg.getApellidoPaterno());
			nombre.setApellidoMaterno(formaContOrg.getApellidoMaterno());
			nombre.setRfc(formaContOrg.getRfc());
			nombre.setCurp(formaContOrg.getCurp());
			nombre.setSexo(formaContOrg.getSexo());
			if(formaContOrg.getFechaNacimiento()!=null && !formaContOrg.getFechaNacimiento().equals("")){
				nombre.setFechaNacimiento(DateUtils.obtener(formaContOrg.getFechaNacimiento()));
			}
			nombre.setEdadAproximada(formaContOrg.getEdadAproximada());
			
			
			List<NombreDemograficoDTO> nombreL= new ArrayList<NombreDemograficoDTO>();
			nombreL.add(nombre);
			contactoOrgDTO.setNombresDemograficoDTO(nombreL);
			contactoOrgDTO.setFechaCreacionElemento(new Date());
		
			
			
			if(StringUtils.isNotBlank(formaContOrg.getPais()) && !formaContOrg.getPais().equals("-1")){
				ValorDTO paisValorDTO=new ValorDTO(Long.parseLong(formaContOrg.getPais()));
				nombre.setPaisValorDTO(paisValorDTO);
			}			
			if(StringUtils.isNotBlank(formaContOrg.getEntidadFederativa()) && !formaContOrg.getEntidadFederativa().equals("-1")){
				EntidadFederativaDTO entidadFederativaDTO=new EntidadFederativaDTO(Long.parseLong(formaContOrg.getEntFederativaNacimiento()));
				nombre.setEntidadFederativaDTO(entidadFederativaDTO);
			}
			if(StringUtils.isNotBlank(formaContOrg.getMunicipioNacimiento()) && !formaContOrg.getMunicipioNacimiento().equals("-1")){
				MunicipioDTO municipioDTO = new MunicipioDTO(Long.parseLong(formaContOrg.getMunicipioNacimiento()));
				nombre.setMunicipioDTO(municipioDTO);
			}
			//seteo la calidad
			CalidadDTO calidadInvDTO=new CalidadDTO();
			calidadInvDTO.setCalidades(Calidades.REPRESENTANTE_LEGAL);
			contactoOrgDTO.setCalidadDTO(calidadInvDTO);
			if(StringUtils.isNotBlank(formaContOrg.getIdioma()) && !formaContOrg.getIdioma().equals("-1")){
				ValorDTO idiomaDTO= new ValorDTO(Long.parseLong(formaContOrg.getIdioma()));
				contactoOrgDTO.setValorIdIdioma(idiomaDTO);
			}
			if(formaContOrg.getEscolaridad() != null && !formaContOrg.getEscolaridad().isEmpty() && !formaContOrg.getEscolaridad().equals("- Selecciona -")){
				ValorDTO escolaridadDTO= new ValorDTO(Long.parseLong(formaContOrg.getEscolaridad()));
				contactoOrgDTO.setValorIdEscolaridad(escolaridadDTO);
			}else{
				contactoOrgDTO.setValorIdEscolaridad(null);
			}
			if(formaContOrg.getEstadoCivil() != null && !formaContOrg.getEstadoCivil().isEmpty() && !formaContOrg.getEstadoCivil().equals("- Selecciona -"))
			{
				ValorDTO estadoCivilDTO = new ValorDTO(Long.parseLong(formaContOrg.getEstadoCivil()));
				contactoOrgDTO.setValorIdEstadoCivil(estadoCivilDTO);
			}else
			{
				contactoOrgDTO.setValorIdEstadoCivil(null);
			}
			
			//seteo de la ocupacion
			List<ValorDTO> listaValor=new ArrayList<ValorDTO>();
			ValorDTO valorGenerico = new ValorDTO();
			Long ocupacion=null; 
			try{
				if(formaContOrg.getOcupacion()!=null && !formaContOrg.getOcupacion().equals("- Selecciona -"))
					ocupacion=Long.parseLong(formaContOrg.getOcupacion());
			}catch (NumberFormatException e) {
				ocupacion=null;
			}
			valorGenerico.setIdCampo(ocupacion);
			listaValor.add(valorGenerico);
			contactoOrgDTO.setValorIdOcupacion(listaValor);			
			
			//seteo las nacionalidades
			if(!formaContOrg.getNacionalidad().equalsIgnoreCase(""))
			{
				//barremos las ocupaciones
				String[] nacionalidades=formaContOrg.getNacionalidad().split(",");
				List<ValorDTO> nacionalidadesL=new ArrayList<ValorDTO>();
				for (String nacionalidad : nacionalidades) 
				{
				 ValorDTO nacionalidadV=new ValorDTO(Long.parseLong(nacionalidad));
				 nacionalidadesL.add(nacionalidadV);
				}
				contactoOrgDTO.setValorIdNacionalidad(nacionalidadesL);
			}
			//FIN seteo datos generales
			
			log.info("REP LEGAL:::: Seteo datos generales....");
			//FIN seteo datos generales

			//seteo documento de identifacion
			if(StringUtils.isNotBlank(formaContOrg.getDocIdentificacion()) && !formaContOrg.getDocIdentificacion().equals("-1")){
				ValorDTO docIdentificacion=new ValorDTO();
				docIdentificacion.setIdCampo(Long.parseLong(formaContOrg.getDocIdentificacion()));			
				contactoOrgDTO.setValorIdIdentificaion(docIdentificacion);
				contactoOrgDTO.setFolioIdentificacion(formaContOrg.getFolioDoc());
			}
			log.info("REP LEGAL:::: Seteo documento de identifacion....");
			//FIN seteo documento de identifacion
			
			//generamos la instancia de organizacion con su Id
			OrganizacionDTO organizacionDTO = new OrganizacionDTO();
			organizacionDTO.setElementoId(formaContOrg.getIdOrganizacion());
			log.info("REP ORG:::: ID org ::: "+formaContOrg.getIdOrganizacion());
			//guardamos al contacto de la organizacion
			PersonaDTO personaDTO = organizacionDelegate.ingresarPersonaOrganizacion(contactoOrgDTO, organizacionDTO);

			// revisamos si el guardado fue exitoso para mandar el xml
			// correspondiente
			if (organizacionDTO != null
					&& organizacionDTO.getOrganizacionId() != null) {
				converter.alias("organizacionDTO", PersonaDTO.class);
				String xml = converter.toXML(personaDTO);
				log.info("Aqui mandamos el XML personaDTO::::::: "+xml);
				escribirRespuesta(response, xml);
			} else {
				organizacionDTO.setOrganizacionId(0L);
				converter.alias("organizacionDTO", PersonaDTO.class);
				String xml = converter.toXML(personaDTO);
				log.info("id organizacion es cero '0' en elseee XMLLLL personaDTO:::::::: "+xml);
				escribirRespuesta(response, xml);
			}
			log.info("Termina ejecucion Action guardar Representante Legal - FIN ");
		} catch (NSJPNegocioException e) {
			log.error(e.getMessage(), e);
			escribir(response, "", e);
		}
		catch (Exception e){
			log.error("Errorrrrrr:::::"+ e.getMessage());
		}
		return null;
	}

}

/**
* Nombre del Programa : ModificarIndividuoServiceImplTest.java
* Autor                            : cesar
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 20 May 2011
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
package mx.gob.segob.nsjp.service.test.involucrado.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.organizacion.TipoOrganizacion;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.domicilio.AsentamientoDTO;
import mx.gob.segob.nsjp.dto.domicilio.CiudadDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.domicilio.EntidadFederativaDTO;
import mx.gob.segob.nsjp.dto.domicilio.MunicipioDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.AliasInvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.MediaFiliacionDTO;
import mx.gob.segob.nsjp.dto.involucrado.SeniaParticularDTO;
import mx.gob.segob.nsjp.dto.involucrado.ServidorPublicoDTO;
import mx.gob.segob.nsjp.dto.organizacion.OrganizacionDTO;
import mx.gob.segob.nsjp.dto.persona.CorreoElectronicoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.dto.persona.TelefonoDTO;
import mx.gob.segob.nsjp.service.catalogo.CatalogoService;
import mx.gob.segob.nsjp.service.involucrado.ConsultarIndividuoService;
import mx.gob.segob.nsjp.service.involucrado.ModificarIndividuoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author cesar
 *
 */
public class ModificarIndividuoServiceImplTest extends BaseTestServicios<ModificarIndividuoService> {

	@Autowired
	private ConsultarIndividuoService consultarIndividuoService;
	@Autowired
	private CatalogoService catalogoService;
	
	public void testModificarIndividuo () {
		InvolucradoDTO involucradoDTO = new InvolucradoDTO();
				
		Long idElemento = 347L;
		Long idExpediente = 38L;
		Long tipoPersona = 0L; //0 Moral 1 Fisica
		
		//Calidad del individuo		
		CalidadDTO  calidad = new CalidadDTO();
		if(tipoPersona==1){
			calidad.setDescripcionEstadoFisico("Se encontro en mal estado");
			calidad.setCalidades(Calidades.PROBABLE_RESPONSABLE_PERSONA);
		}else{
			calidad.setDescripcionEstadoFisico("Se encontro en excelente estado");
			calidad.setCalidades(Calidades.PROBABLE_RESPONSABLE_ORGANIZACION);
		}
		involucradoDTO.setCalidadDTO(calidad);
			
		
		involucradoDTO.setElementoId(idElemento); //Elemento id a modificar
		
		ExpedienteDTO expDTO = new ExpedienteDTO(idExpediente); //Expediente DTO
		involucradoDTO.setExpedienteDTO(expDTO);

		//TipoPersona
		involucradoDTO.setTipoPersona(tipoPersona);
		
		//Datos Involucrados
		involucradoDTO.setEsDetenido(true);
		involucradoDTO.setCondicion((short)1);
		involucradoDTO.setMotivoComparecencia("Prueba Mod");
		involucradoDTO.setFechaCreacionElemento(new Date());
		involucradoDTO.setEsVivo(true);
		//Los valores son de acuerdo a la calidad del involucrado:
		//Víctima - "Desconocido"
		//Denunciante - "Anónima"
		//Probable Responsable - "A quien resulte responsable"
		involucradoDTO.setDesconocido("Desconocido");
		
		//Persona Fisica
		if(tipoPersona==1){
			//DATOS GENERALES
			//NombresDemograficoDTO
			List<NombreDemograficoDTO> nombres = new ArrayList<NombreDemograficoDTO>();
			NombreDemograficoDTO nombre = new NombreDemograficoDTO();
			nombre.setApellidoMaterno("Páez");
			nombre.setApellidoPaterno("Basurto");
			nombre.setCurp("iuju896745hdfium");
			nombre.setLugarNacimiento("Guanajuato MexicoM");
			nombre.setNombre("Gustavo");
			nombre.setRfc("iuju896745um7");
			nombre.setSexo("m");
	//		nombre.setStrFechaNacimiento("");
			nombre.setEdadAproximada((short)25);
			//EntidadFederativa		
			nombre.setEntidadFederativaDTO(new EntidadFederativaDTO(32L)); //Zacateas
			nombre.setEsVerdadero(false);
			nombre.setFechaNacimiento(new Date());
			nombre.setMunicipioDTO(new MunicipioDTO(2399L));
			nombre.setPaisValorDTO(new ValorDTO(10L));
			nombres.add(nombre);
			involucradoDTO.setNombresDemograficoDTO(nombres);
	
			involucradoDTO.setValorIdIdioma(new ValorDTO(54L));
			involucradoDTO.setValorIdEscolaridad(new ValorDTO(109L));
			involucradoDTO.setValorIdEstadoCivil(new ValorDTO(107L));
			involucradoDTO.setValorIdReligion(new ValorDTO(100L));
				
			//MEDIA FILIACION
			IngresarIndividuoServiceImplTest ingresarIndividuoServiceImplTest = new IngresarIndividuoServiceImplTest();
			MediaFiliacionDTO mediaFiliacionDTO = ingresarIndividuoServiceImplTest.datosAleatoriosMediaFiliacionDTO(catalogoService );
			//Señas Particulares		
			SeniaParticularDTO seniaParticularDTO = ingresarIndividuoServiceImplTest.datosAleatoriosSeniasParticularesDTO();
			mediaFiliacionDTO.setSeniaParticularDTO(seniaParticularDTO);
			involucradoDTO.setMediaFiliacionDTO(mediaFiliacionDTO);
			
				
			//VALORES Q SON MODIFICADOS EN SU TOTALIDAD  (delete - create)
			//Servidor Publico Si se modifica cambian relaciones
			ServidorPublicoDTO servidorPublicoDTO = new ServidorPublicoDTO();
			involucradoDTO.setEsServidor(true);
			servidorPublicoDTO.setDependencia("NSJP PUM2");
			servidorPublicoDTO.setNumeroEmpleado("00002");
			servidorPublicoDTO.setPuesto("GerenteM");
			servidorPublicoDTO.setNivelDependencia(new ValorDTO(1443L));
			servidorPublicoDTO.setTipoDependencia(new ValorDTO(1450L));
			involucradoDTO.setServidorPublicoDTO(servidorPublicoDTO);
		}
		
		//DOMICILIO
		DomicilioDTO domicilio = new DomicilioDTO();
		domicilio.setCalle("calle1M");
		domicilio.setNumeroExterior("10M");
		domicilio.setNumeroInterior("201M");
		domicilio.setNumeroLote("LoteM");
		domicilio.setReferencias("Ref MODM");
		domicilio.setEntreCalle1("entre calle 1M");
		domicilio.setEntreCalle2("entre calle 2M");
		domicilio.setAlias("RanchitoM");
		domicilio.setEdificio("CN");
		domicilio.setAsentamientoDTO(new AsentamientoDTO(20685L));
		domicilio.setEntidadDTO(new EntidadFederativaDTO(32L));
		domicilio.setMunicipioDTO(new MunicipioDTO(2L));
		domicilio.setCiudadDTO(new CiudadDTO(2L));		
		domicilio.setValorCalleId(new ValorDTO(73L));
		
		domicilio.setFechaCreacionElemento(new Date());
		//Lugar -> Elemento
		domicilio.setDescripcion("descripcion domicilio 1M");
		//CoordenadaGeografica - Lugar
		domicilio.setLatitud("105.10");
		domicilio.setLongitud("110.10");
		//Calidad de Domicilio
		CalidadDTO calidadDomicilio = new CalidadDTO();
		calidadDomicilio.setDescripcionEstadoFisico("En buen estadoM");
		calidadDomicilio.setValorIdCalidad(new ValorDTO(Calidades.DOMICILIO.getValorId()));
		calidadDomicilio.setCalidades(Calidades.DOMICILIO);	
		domicilio.setCalidadDTO(calidadDomicilio);
		domicilio.setExpedienteDTO(expDTO);
		involucradoDTO.setDomicilio(domicilio);
		
		//MEDIOS DE CONTACTO
		List<TelefonoDTO> telefonosDTO = new ArrayList<TelefonoDTO>();		
		telefonosDTO.add(new TelefonoDTO("01", "55", "57675610", new ValorDTO(233L)));
		telefonosDTO.add(new TelefonoDTO("T04L", "55", "57678610", new ValorDTO(233L)));
		involucradoDTO.setTelefonosDTO(telefonosDTO);
		
		List<CorreoElectronicoDTO> correosElectronicosDTO = new ArrayList<CorreoElectronicoDTO>();
		correosElectronicosDTO.add(new CorreoElectronicoDTO("salinas@hotmail.com.m"));
		correosElectronicosDTO.add(new CorreoElectronicoDTO("csalinas@yahoo.com.m"));		
		involucradoDTO.setCorreosDTO(correosElectronicosDTO);
		
		//Fisica
		if(tipoPersona ==1){
			//TIPO DE DOCUMENTO
			involucradoDTO.setFolioIdentificacion("000002");
			involucradoDTO.setValorIdIdentificaion(new ValorDTO(12L));
		
			//Nacionalidades		
			List<ValorDTO> nacionalidades = new ArrayList<ValorDTO>();
			nacionalidades.add(new ValorDTO(176L));
			nacionalidades.add(new ValorDTO(181L));
			nacionalidades.add(new ValorDTO(191L));
			involucradoDTO.setValorIdNacionalidad(nacionalidades);		
			//Ocupaciones
			List<ValorDTO> ocupaciones = new ArrayList<ValorDTO>();
			ocupaciones.add(new ValorDTO(132L));
			ocupaciones.add(new ValorDTO(139L));
			ocupaciones.add(new ValorDTO(148L));
			involucradoDTO.setValorIdOcupacion(ocupaciones);
			//Alias
			List<AliasInvolucradoDTO> aliasDTO = new ArrayList<AliasInvolucradoDTO>();
			AliasInvolucradoDTO alias = new AliasInvolucradoDTO();
			alias.setAlias("prueba mod1");
			aliasDTO.add(alias);
			AliasInvolucradoDTO alias2 = new AliasInvolucradoDTO();
			alias2.setAlias("la pistola");
			aliasDTO.add(alias2);
			involucradoDTO.setAliasInvolucradoDTO(aliasDTO);			
			
			//DOMICILIO NOTIFICACION
			CalidadDTO calidadDomicilioNot = new CalidadDTO();
			calidadDomicilioNot.setDescripcionEstadoFisico("En buen estadoM");
			calidadDomicilioNot.setValorIdCalidad(new ValorDTO(Calidades.DOMICILIO.getValorId()));
			calidadDomicilioNot.setCalidades(Calidades.DOMICILIO);
			
			DomicilioDTO domicilioNotificacion = new DomicilioDTO();
			//domicilioNotificacion.setElementoId(591L); //ID domicilio Not
			domicilioNotificacion.setReferencias("RefNot MOD");
			domicilioNotificacion.setValorCalleId(new ValorDTO(96L));
			domicilioNotificacion.setEntidadDTO(new EntidadFederativaDTO(23L));
			domicilioNotificacion.setValorIdElemento(new ValorDTO(31L));
			domicilioNotificacion.setCalle("calle notificacion 1M");
			domicilioNotificacion.setDescripcion("descripcion domicilio notificacion 1M");
			domicilioNotificacion.setFechaCreacionElemento(new Date());
			domicilioNotificacion.setEntreCalle1("entre calle notificacin 1M");
			domicilioNotificacion.setEntreCalle2("entre calle notificacion 2M");						
			domicilioNotificacion.setAlias("HaciendaM");
			domicilioNotificacion.setCalidadDTO(calidadDomicilioNot);
			domicilioNotificacion.setExpedienteDTO(expDTO);
			domicilioNotificacion.setAsentamientoDTO(new AsentamientoDTO(20684L));
			domicilioNotificacion.setCiudadDTO(new CiudadDTO(2L));
			domicilioNotificacion.setMunicipioDTO(new MunicipioDTO(2L));
			involucradoDTO.setDomicilioNotificacion(domicilioNotificacion);		
		}
		//Moral
		else{
			//ORGANIZACION
			CalidadDTO calOrgDTO = new CalidadDTO();
			calOrgDTO.setCalidades(Calidades.DENUNCIANTE_ORGANIZACION);
			
			OrganizacionDTO organizacionDTO = new OrganizacionDTO();
			organizacionDTO.setNombreCorto("ORG-MOD");
			organizacionDTO.setNombreOrganizacion("ORGANIZACION-MOD 1");
			organizacionDTO.setNumeroActaConstitutiva("1234568MOD");
			organizacionDTO.setDireccionInternet("www.orgmod1.com");
			organizacionDTO.setAreaDeInfluencia("MetropolitanaMod");
			organizacionDTO.setFechaCreacionElemento(new Date());
			organizacionDTO.setGiro("Financiero");
			organizacionDTO.setPropositoCiberespacio("Desconocido");
	
			organizacionDTO.setValorByComunidadVirtualVal(new ValorDTO(228L));
			organizacionDTO.setValorByOrganizacionFormalVal(new ValorDTO(229L));
			organizacionDTO.setValorBySectorGubernamentalVal(new ValorDTO(230L));
			
			organizacionDTO.setValorByTipoOrganizacionVal(new ValorDTO(new Long(TipoOrganizacion.FORMAL.getValorId() )));
			organizacionDTO.setExpedienteDTO(expDTO);
			organizacionDTO.setCalidadDTO(calOrgDTO);
	
			//DOMICILIO ORGANIZACION
			DomicilioDTO domicilioDTO = new DomicilioDTO();
			domicilioDTO.setCalle("calle organizacionMod 2");
			domicilioDTO.setDescripcion("descripcion domicilio organizacionMd 1");
			domicilioDTO.setFechaCreacionElemento(new Date());
			domicilioDTO.setEntreCalle1("entre calle organizacionMod 1");
			domicilioDTO.setEntreCalle2("entre calle organizacionMod 2");
			domicilioDTO.setLatitud("205.15");
			domicilioDTO.setLongitud("14.25");
			domicilioDTO.setAsentamientoDTO(new AsentamientoDTO());
			domicilioDTO.setExpedienteDTO(expDTO);
			domicilioDTO.setAsentamientoDTO(new AsentamientoDTO(20684L));
			domicilioDTO.setCiudadDTO(new CiudadDTO(1L));
			domicilioDTO.setMunicipioDTO(new MunicipioDTO(1L));
			CalidadDTO calDomDTO = new CalidadDTO();
			calDomDTO.setCalidades(Calidades.DOMICILIO);
			domicilioDTO.setCalidadDTO(calDomDTO);
			organizacionDTO.setDomicilioDTO(domicilioDTO);
		
			//REPRESENTANTE LEGAL -> ORGANIZACION  -  Es un involucrado con:
			//Datos Generales, Domicilio, Medios de contacto, Tipo de documento de indentificacion
			//Calidad como Representante_Legal
			CalidadDTO calRepDTO = new CalidadDTO();
			calRepDTO.setCalidades(Calidades.REPRESENTANTE_LEGAL);
			InvolucradoDTO representanteDTO = new InvolucradoDTO();
			representanteDTO.setEsVivo(true);
			representanteDTO.setFechaCreacionElemento(new Date());
			//NO NECESARIO representanteDTO.setValorIdElemento(new ValorDTO(Elementos.PERSONA.getValorId()));
			representanteDTO.setCalidadDTO(calRepDTO);
			representanteDTO.setExpedienteDTO(expDTO);
			
			List<NombreDemograficoDTO> nombresRL = new ArrayList<NombreDemograficoDTO>();
			NombreDemograficoDTO nombreRL = new NombreDemograficoDTO();
			nombreRL.setApellidoMaterno("RPLM1");
			nombreRL.setApellidoPaterno("RPLM2");
			nombreRL.setCurp("iuju896745hdfium");
			nombreRL.setLugarNacimiento("Guanajuato MexicoM");
			nombreRL.setNombre("RPLM");
			nombreRL.setSexo("m");
	//		nombre.setStrFechaNacimiento("");
			nombreRL.setEdadAproximada((short)30);
			//EntidadFederativa		
			nombreRL.setEntidadFederativaDTO(new EntidadFederativaDTO(32L)); //Zacateas
			nombreRL.setEsVerdadero(false);
			nombreRL.setFechaNacimiento(new Date());
			nombreRL.setMunicipioDTO(new MunicipioDTO(2399L));
			nombreRL.setPaisValorDTO(new ValorDTO(10L));
			nombresRL.add(nombreRL);
			representanteDTO.setNombresDemograficoDTO(nombresRL);
						
			//RL - OR - Domicilio
			DomicilioDTO domicilioRL = new DomicilioDTO();
			domicilioRL.setCalle("calle1 RLM");
			domicilioRL.setNumeroExterior("10 RLM");
			domicilioRL.setNumeroInterior("201 RLM");
			domicilioRL.setNumeroLote("Lote RLM");
			domicilioRL.setReferencias("Ref MOD RLM");
			domicilioRL.setEntreCalle1("entre calle 1 RLM");
			domicilioRL.setEntreCalle2("entre calle 2 RLM");
			domicilioRL.setAlias("Ranchito RLM");
			domicilioRL.setEdificio("C RLM");
			domicilioRL.setAsentamientoDTO(new AsentamientoDTO(20684L));
			domicilioRL.setEntidadDTO(new EntidadFederativaDTO(23L));
			domicilioRL.setMunicipioDTO(new MunicipioDTO(1L));
			domicilioRL.setCiudadDTO(new CiudadDTO(1L));
			domicilioRL.setValorCalleId(new ValorDTO(72L)); //Tipo Calle
			
			domicilioRL.setFechaCreacionElemento(new Date());
			//Lugar -> Elemento
			domicilioRL.setDescripcion("descripcion domicilio 1 RLM");
			//CoordenadaGeografica - Lugar
			domicilioRL.setLatitud("1000.10");
			domicilioRL.setLongitud("1010.10");
			//Calidad de Domicilio
			CalidadDTO calidadDomicilioRL = new CalidadDTO();
			calidadDomicilioRL.setDescripcionEstadoFisico("En buen estado RLM");
			calidadDomicilioRL.setValorIdCalidad(new ValorDTO(Calidades.DOMICILIO.getValorId()));
			calidadDomicilioRL.setCalidades(Calidades.DOMICILIO);				
			domicilioRL.setCalidadDTO(calidadDomicilioRL);
			domicilioRL.setExpedienteDTO(expDTO);
			representanteDTO.setDomicilio(domicilioRL);
							
			//RL - OR - MEDIOS DE CONTACTO
			List<TelefonoDTO> telefonosRLDTO = new ArrayList<TelefonoDTO>();	
			telefonosRLDTO.add(new TelefonoDTO("01", "55", "57675609", new ValorDTO(233L)));
			telefonosRLDTO.add(new TelefonoDTO("T04L", "55", "57678676", new ValorDTO(233L)));
			representanteDTO.setTelefonosDTO(telefonosRLDTO);
			
			List<CorreoElectronicoDTO> correosElectronicosRLDTO = new ArrayList<CorreoElectronicoDTO>();		
			correosElectronicosRLDTO.add(new CorreoElectronicoDTO("salinas@hotmail.com"));
			correosElectronicosRLDTO.add(new CorreoElectronicoDTO("csalinas@yahoo.com"));
			representanteDTO.setCorreosDTO(correosElectronicosRLDTO);
	
			//RL - OR - TIPO DE DOCUMENTO
			representanteDTO.setFolioIdentificacion("000005");
			representanteDTO.setValorIdIdentificaion(new ValorDTO(11L));
			
			//RL - OR - Nacionalidades		
			List<ValorDTO> nacionalidadesRL = new ArrayList<ValorDTO>();
			nacionalidadesRL.add(new ValorDTO(175L));
			nacionalidadesRL.add(new ValorDTO(180L));
			representanteDTO.setValorIdNacionalidad(nacionalidadesRL);
			//RL - OR - Ocupaciones
			List<ValorDTO> ocupacionesRL = new ArrayList<ValorDTO>();
			ocupacionesRL.add(new ValorDTO(131L));
			ocupacionesRL.add(new ValorDTO(138L));
			representanteDTO.setValorIdOcupacion(ocupacionesRL);
	
			organizacionDTO.setRepresentanteLegal(representanteDTO);
	
			//NO APLICA
			//CONTACTO DE LA ORGANIZACION
	//		CalidadDTO calContactoDTO = new CalidadDTO();
	//		calContactoDTO.setCalidades(Calidades.CONTACTO_ORGANIZACION);
	//		PersonaDTO contactoDTO = new PersonaDTO();
	//		contactoDTO.setEsVivo(1L);
	//		contactoDTO.setFechaCreacionElemento(new Date());
	//		contactoDTO.setValorIdElemento(new ValorDTO(Elementos.PERSONA.getValorId()));
	//		contactoDTO.setCalidadDTO(calContactoDTO);
	//		contactoDTO.setExpedienteDTO(expDTO);
	//		
	//		List<NombreDemograficoDTO> nombresContacto = new ArrayList<NombreDemograficoDTO>();
	//		NombreDemograficoDTO nombreContacto = new NombreDemograficoDTO();
	//		nombreContacto.setApellidoMaterno("APContacto");
	//		nombreContacto.setApellidoPaterno("AMContacto");
	//		nombreContacto.setCurp("iuju896745hdfium");
	//		nombreContacto.setLugarNacimiento("Guanajuato MexicoM");
	//		nombreContacto.setNombre("RPLContacto");
	//		nombreContacto.setSexo("f");
	//		nombreContacto.setEdadAproximada((short)35);
	//		//EntidadFederativa		
	//		nombreContacto.setEntidadFederativaDTO(new EntidadFederativaDTO(32L)); //Zacateas
	//		nombreContacto.setEsVerdadero(false);
	//		nombreContacto.setFechaNacimiento(new Date());
	//		nombreContacto.setMunicipioDTO(new MunicipioDTO(2399L));
	//		nombreContacto.setPaisValorDTO(new ValorDTO(10L));
	//		nombresContacto.add(nombreContacto);
	//		contactoDTO.setNombresDemograficoDTO(nombresContacto);
	//		organizacionDTO.setContacto(contactoDTO);
	
			involucradoDTO.setOrganizacionDTO(organizacionDTO);
		}
		try {
			Long respuesta = service.actualizarIndividuo(involucradoDTO);			
			assertTrue("Prueeba exitosa", respuesta>0);
			logger.info("Prueeba exitosa : " + respuesta);
		} catch (NSJPNegocioException e) {
			if (logger.isErrorEnabled())
				logger.error(e.getMessage());
		}
	}
	
	public void testModicaDatosDeInputado() {		
		InvolucradoDTO involucrado = new InvolucradoDTO();		
		
		List<NombreDemograficoDTO> nombres = new ArrayList<NombreDemograficoDTO>();
		NombreDemograficoDTO nombre = new NombreDemograficoDTO();		
		
		
		involucrado.setElementoId(37L);
		involucrado.setEsDetenido(true);
		nombre.setNombre("Ricardo");	
		nombre.setApellidoPaterno("Garcia");
		nombre.setApellidoMaterno("Garcia");
		nombre.setCurp("newu896745hdfiur");
		nombre.setRfc("newu896745ui7");
		nombre.setFechaNacimiento(new Date());
		nombre.setEsVerdadero(false);
		nombre.setEdadAproximada((short)26);
		nombre.setLugarNacimiento("Guanajuato Mexico");		
		nombre.setSexo("M");
		nombres.add(nombre);
		involucrado.setNombresDemograficoDTO(nombres);
		
		involucrado.setValorIdIdioma(new ValorDTO(54L));
		involucrado.setValorIdEscolaridad(new ValorDTO(109L));
		involucrado.setValorIdEstadoCivil(new ValorDTO(107L));
		
		//Se agregan ocupaciones al involucrado
		List<ValorDTO> ocupaciones = new ArrayList<ValorDTO>();
		ocupaciones.add(new ValorDTO(132L));
		ocupaciones.add(new ValorDTO(139L));
		ocupaciones.add(new ValorDTO(148L));
		involucrado.setValorIdOcupacion(ocupaciones);
		//Se agregan alias al involucrado
		List<AliasInvolucradoDTO> aliasInvolucradoDTO = new ArrayList<AliasInvolucradoDTO>();
		AliasInvolucradoDTO alias1 = new AliasInvolucradoDTO();
		alias1.setAlias("perro");
		aliasInvolucradoDTO.add(alias1);
		AliasInvolucradoDTO alias2 = new AliasInvolucradoDTO();
		alias2.setAlias("tarzan");

		aliasInvolucradoDTO.add(alias2);
		involucrado.setAliasInvolucradoDTO(aliasInvolucradoDTO);
		//Se agregan nacionalidades		
		List<ValorDTO> nacionalidades = new ArrayList<ValorDTO>();
		nacionalidades.add(new ValorDTO(176L));
		nacionalidades.add(new ValorDTO(181L));
		nacionalidades.add(new ValorDTO(191L));
		involucrado.setValorIdNacionalidad(nacionalidades);		
		
		//Es necesaria la calidad para poder actualizar
		CalidadDTO calidadDTO = new CalidadDTO();
		calidadDTO.setCalidades(Calidades.DENUNCIANTE);
		involucrado.setCalidadDTO(calidadDTO);
						
		try {
			Long resultado = service.actualizarIndividuo(involucrado);
			assertTrue("El servicio debe retornar el identificador del individuo creado ", resultado>0);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
		
		
	}

	@Deprecated //Se presentan excepciones al efectuar la actualización.   
	public void testModificarIndividuoConsulta(){
		Long idElemento = 1600L;
		
		InvolucradoDTO involucradoDTO = new InvolucradoDTO(idElemento);
		
		try {
			
			involucradoDTO = consultarIndividuoService.obtenerInvolucrado(involucradoDTO);
			imprimirDatosIndividuo(involucradoDTO);
			
			//Verificar si se puede actualizar
			if(involucradoDTO!=null && involucradoDTO.getElementoId()!= null){
				involucradoDTO = datosInvolucradoModificar(involucradoDTO);
				Long respuesta = service.actualizarIndividuo(involucradoDTO);
				assertTrue("Prueba exitosa", respuesta>0);
				logger.info("Prueba exitosa : " + respuesta);
			}
		} catch (NSJPNegocioException e) {
			logger.info(e.getMessage(), e);
		}	
	}

	
	private void imprimirDatosIndividuo(InvolucradoDTO involucradoDTO ){
		if (involucradoDTO==null)
			return;
		logger.info("InvolucradoDTO :: " + involucradoDTO);
		logger.info("ElementoID: " + involucradoDTO.getElementoId());
		logger.info("ExpedienteID: " + involucradoDTO.getExpedienteDTO().getExpedienteId());
		
		if (involucradoDTO.getCalidadDTO()!= null){
			logger.info("Calidad: "+involucradoDTO.getCalidadDTO());
			logger.info("Calidades: "+involucradoDTO.getCalidadDTO().getCalidades());
			//logger.info("CalidadesValorID: "+involucradoDTO.getCalidadDTO().getCalidades().getValorId());			
		}
		for (NombreDemograficoDTO nombreDemograficoDTO : involucradoDTO
				.getNombresDemograficoDTO()) {
			logger.info("Nombre:" + nombreDemograficoDTO.getNombre());
			logger.info("Fecha nacimiento "
					+ nombreDemograficoDTO.getStrFechaNacimiento());
			logger.info("Fecha nacimiento "
					+ nombreDemograficoDTO.getFechaNacimiento());
		}
		//MEDIA FILIACION Y SENIA PARTICULAR
		logger.info("MediaFiliacion:"+involucradoDTO.getMediaFiliacionDTO());
		if (involucradoDTO.getMediaFiliacionDTO()!= null ){
			MediaFiliacionDTO mediaFiliacionDTO =  involucradoDTO.getMediaFiliacionDTO();
			logger.info("MediaFiliacionID:"+mediaFiliacionDTO.getMediaFiliacionId());
			logger.info("factorRH:"+mediaFiliacionDTO.getFactorRH());
			if (mediaFiliacionDTO.getTipoSangre()!=null) 
				logger.info("TipoSangre:"+ mediaFiliacionDTO.getTipoSangre().getIdCampo());
			if( mediaFiliacionDTO.getTamanioBoca()!= null)
				logger.info("TamanioBoca:"+ mediaFiliacionDTO.getTamanioBoca().getIdCampo());
			
			//SENIA PARTICULAR
			if ( mediaFiliacionDTO.getSeniaParticularDTO()!= null){
				logger.info("seniaParticular:"+ mediaFiliacionDTO.getSeniaParticularDTO());
				logger.info("seniaParticularID:"+ mediaFiliacionDTO.getSeniaParticularDTO().getSeniaParticularId());
				logger.info("Cicatrices:"+ mediaFiliacionDTO.getSeniaParticularDTO().getDescripcionCicatrices());
				logger.info("DefectosFisicos:"+ mediaFiliacionDTO.getSeniaParticularDTO().getDescripcionDefectosFisicos());
				logger.info("Lunares:"+ mediaFiliacionDTO.getSeniaParticularDTO().getDescripcionLunares());
				logger.info("OtraSenia:"+ mediaFiliacionDTO.getSeniaParticularDTO().getDescripcionOtraSenia());
				logger.info("Protesis:"+ mediaFiliacionDTO.getSeniaParticularDTO().getDescripcionProtesis());
				logger.info("Tatuajes:"+ mediaFiliacionDTO.getSeniaParticularDTO().getDescripcionTatuajes());
			}
		}
	}

	private InvolucradoDTO datosInvolucradoModificar(InvolucradoDTO involucradoDTO){
		String cadenaModificacion = "*";
		if(involucradoDTO == null)
			return involucradoDTO;
		
		//Revisar Calidad
		CalidadDTO calidadDTO = new CalidadDTO();
		calidadDTO.setCalidades(Calidades.PROBABLE_RESPONSABLE_PERSONA);
		involucradoDTO.setCalidadDTO(calidadDTO);
		
		involucradoDTO.setFechaCreacionElemento(new Date());
		
		if (involucradoDTO.getNombresDemograficoDTO()!= null && !involucradoDTO.getNombresDemograficoDTO().isEmpty() ){
			NombreDemograficoDTO nombreDemograficoDTO = involucradoDTO.getNombresDemograficoDTO().get(0);
			nombreDemograficoDTO.setNombre( nombreDemograficoDTO.getNombre() + cadenaModificacion);
			
			List<NombreDemograficoDTO> lnd = new ArrayList<NombreDemograficoDTO>();
			lnd.add(nombreDemograficoDTO);
			involucradoDTO.setNombresDemograficoDTO(lnd);
		}
		//MediaFiliacion Nueva o a modificar
		if(involucradoDTO.getMediaFiliacionDTO()!=null && involucradoDTO.getMediaFiliacionDTO().getMediaFiliacionId()!=null){
			MediaFiliacionDTO mediaFiliacionDTO = involucradoDTO.getMediaFiliacionDTO();
			mediaFiliacionDTO.setFactorRH("NEGATIVO");
			mediaFiliacionDTO.setTipoSangre(new ValorDTO(2102L)); //B
			
			mediaFiliacionDTO.setTamanioBoca(new ValorDTO(1782L)); //Mediana
			mediaFiliacionDTO.setTamanioCeja(new ValorDTO(1838L)); //Gruesa
			mediaFiliacionDTO.setFormaCeja(new ValorDTO(1835L)); //Arqueda sinuosa
			
			involucradoDTO.setMediaFiliacionDTO(mediaFiliacionDTO);
		}
		
		return involucradoDTO;
		
	}
	

}

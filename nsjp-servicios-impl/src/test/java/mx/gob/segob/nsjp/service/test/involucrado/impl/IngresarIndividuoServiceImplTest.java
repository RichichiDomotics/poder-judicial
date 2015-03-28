/**
*
* Nombre del Programa : ExpedienteDAOImpl.java                                    
* Autor                            : Cesar Agustin                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 30/03/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Prueba para el sevicio de ingresar individuo                      
* Programa Dependiente  :N/A                                                      
* Programa Subsecuente :N/A                                                      
* Cond. de ejecucion        :N/A                                                      
* Dias de ejecucion          :N/A                             Horario: N/A       
*                              MODIFICACIONES                                       
*------------------------------------------------------------------------------           
* Autor                       :N/A                                                           
* Compania               :N/A                                                           
* Proyecto                 :N/A                                   Fecha: N/A       
* Modificacion           :N/A                                                           
*------------------------------------------------------------------------------           
*/
package mx.gob.segob.nsjp.service.test.involucrado.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.catalogo.Catalogos;
import mx.gob.segob.nsjp.comun.enums.organizacion.TipoOrganizacion;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO;
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
import mx.gob.segob.nsjp.service.involucrado.IngresarIndividuoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author CesarAgustin
 * @version 1.0
 */
public class IngresarIndividuoServiceImplTest extends BaseTestServicios<IngresarIndividuoService> {

	@Autowired
	private CatalogoService catalogoService;
	
	public void testIngresarIndividuo() {
		InvolucradoDTO involucrado = llenarInvolucrado();		
		try {
			Long resultado = service.ingresarIndividuo(involucrado);
			logger.info(resultado);
			assertTrue("El servicio debe retornar el identificador del individuo creado ", resultado>0);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
	}
	
	
	public MediaFiliacionDTO datosAleatoriosMediaFiliacionDTO(CatalogoService pCatalogoService){
		List<CatalogoDTO> catalogo = null;
		Random aleatorio = new Random();
		
		MediaFiliacionDTO mediaFiliacionDTO = new MediaFiliacionDTO();
		
		mediaFiliacionDTO.setEstatura(aleatorio.nextDouble()%2);
		mediaFiliacionDTO.setPeso(aleatorio.nextDouble()%100);
		mediaFiliacionDTO.setPerfil(" Perfil del Individuo "+ aleatorio.nextDouble()%100);
		mediaFiliacionDTO.setTieneBarba(aleatorio.nextBoolean());
		mediaFiliacionDTO.setTieneBigote(aleatorio.nextBoolean());
		mediaFiliacionDTO.setUsaLentes(aleatorio.nextBoolean());
		mediaFiliacionDTO.setFactorRH( aleatorio.nextBoolean()?"POSITIVO":"NEGATIVO");
		
		try {
			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.TIPO_SANGRE);
			mediaFiliacionDTO.setTipoSangre(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));
			
			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.FORMA_OREJA);
			mediaFiliacionDTO.setFormaOreja(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));
			
			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.TAMANIO_CEJA);
			mediaFiliacionDTO.setTamanioCeja(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));
			
			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.TAMANIO_OJO);
			mediaFiliacionDTO.setTamanioOjo(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.FORMA_CEJA);
			mediaFiliacionDTO.setFormaCeja(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.ANCHO_NARIZ);
			mediaFiliacionDTO.setAnchoNariz(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.ESPESOR_LABIO);
			mediaFiliacionDTO.setEspesorLabioInf(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.TAMANIO_CEJA);
			mediaFiliacionDTO.setTamanioCeja(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.OREJA_TAMANIO);
			mediaFiliacionDTO.setOrejaTamanio(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.CABELLO_IMPLANTACION);
			mediaFiliacionDTO.setCabelloImplantacion(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.COLOR_OJO);
			mediaFiliacionDTO.setColorOjos(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.FORMA_OJO);
			mediaFiliacionDTO.setFormaOjos(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.FRENTE_ALTURA);
			mediaFiliacionDTO.setFrenteAltura(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.RAIZ_NARIZ);
			mediaFiliacionDTO.setRaizNariz(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.FORMA_MENTON);
			mediaFiliacionDTO.setFormaMenton(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.CALVICIE_TIPO);
			mediaFiliacionDTO.setCalvicieTipo(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.INCLINIACION_MENTON);
			mediaFiliacionDTO.setInclinacionMenton(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.CANTIDAD_CABELLO);
			mediaFiliacionDTO.setCabelloCantidad(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.ALTURA_NASO_LABIAL);
			mediaFiliacionDTO.setAlturaNasoLabial(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.BASE_NARIZ);
			mediaFiliacionDTO.setBaseNariz(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.DORSO_NARIZ);
			mediaFiliacionDTO.setDorsoNariz(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.COLOR_CABELLO);
			mediaFiliacionDTO.setColorCabello(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.COMISURAS_LABIOS);
			mediaFiliacionDTO.setLabioComisuras(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.HELIX_POSTERIOR);
			mediaFiliacionDTO.setHelixPosterior(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.TAMANIO_BOCA);
			mediaFiliacionDTO.setTamanioBoca(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.PROMINENCIA_LABIOS);
			mediaFiliacionDTO.setLabiosProminencia(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.TEZ);
			mediaFiliacionDTO.setTez(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.TIPO_CARA);
			mediaFiliacionDTO.setTipoCara(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.FORMA_CABELLO);
			mediaFiliacionDTO.setFormaCabello(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.IMPLANTACION_CEJA);
			mediaFiliacionDTO.setImplantacionCeja(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.TIPO_MENTON);
			mediaFiliacionDTO.setTipoMenton(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.OREJA_LOBULO_DIMENSION);
			mediaFiliacionDTO.setOrejaLobDimension(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.DIRECCION_CEJA);
			mediaFiliacionDTO.setDireccionCeja(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.ESPESOR_LABIO);
			mediaFiliacionDTO.setEspesorLabioSup(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.FRENTE_ANCHO);
			mediaFiliacionDTO.setFrenteAncho(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.LOBULO_CONTORNO);
			mediaFiliacionDTO.setHelixContorno(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.ALTURA_NARIZ);
			mediaFiliacionDTO.setAlturaNariz(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.OREJA_LOBULO_PARTICULARIDAD);
			mediaFiliacionDTO.setOrejaLobParticularidad(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.HELIX_ADHERENCIA);
			mediaFiliacionDTO.setHelixAdherencia(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.OREJA_LOBULO_ADHERENCIA);
			mediaFiliacionDTO.setOrejaLobAdherencia(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));
			
			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.HELIX_SUPERIOR);
			mediaFiliacionDTO.setHelixSuperior(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));
			
			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.FRENTE_INCLINACION);
			mediaFiliacionDTO.setFrenteInclinacion(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			//TODO GBP MediaFiliacion de HELIX_ORIGINAL esperar a que se tenga actualizado el Enum en Catalogos
			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.HELIX_SUPERIOR);
			mediaFiliacionDTO.setHelixSuperior(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			//TODO GBP MediaFiliacion de OREJA_LOBULO_CONTORNO  esperar a que se tenga actualizado el Enum en Catalogos
			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.OREJA_LOBULO_PARTICULARIDAD);
			mediaFiliacionDTO.setHelixSuperior(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

			catalogo = pCatalogoService.recuperarCatalogo(Catalogos.COMPLEXION);
			mediaFiliacionDTO.setComplexion(new ValorDTO( catalogo.get(aleatorio.nextInt(catalogo.size())).getClave()));

		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
		return mediaFiliacionDTO;
	}
	
	public SeniaParticularDTO datosAleatoriosSeniasParticularesDTO(){
		Random aleatorio = new Random();
		Boolean valor = null;
		
		SeniaParticularDTO seniaParticularDTO = new SeniaParticularDTO();
		seniaParticularDTO.setTieneCicatrices(valor = aleatorio.nextBoolean());
		seniaParticularDTO.setDescripcionCicatrices(valor?valor+" Cicatris en cara....":"NO");
		seniaParticularDTO.setTieneDefectosFisicos(valor = aleatorio.nextBoolean());
		seniaParticularDTO.setDescripcionDefectosFisicos(valor?valor+" DescripcionDefectosFisicos....":"NO");
		seniaParticularDTO.setTieneLunares(valor = aleatorio.nextBoolean());
		seniaParticularDTO.setDescripcionLunares(valor?valor+" Lunares en cara....":"NO");
		seniaParticularDTO.setTieneOtraSenia(valor = aleatorio.nextBoolean());
		seniaParticularDTO.setDescripcionOtraSenia(valor?valor+" Descripcion Otra Senia":"NO");
		seniaParticularDTO.setTieneProtesis(valor = aleatorio.nextBoolean());
		seniaParticularDTO.setDescripcionProtesis(valor?valor+" Protesis...":"NO");
		seniaParticularDTO.setTieneTatuajes(valor = aleatorio.nextBoolean());
		seniaParticularDTO.setDescripcionTatuajes(valor?valor+" Tatuajes en espalda.":"NO");
		
		return seniaParticularDTO;
	}

	public void testGuardarDefensorAsignadoInvolucrado(){
		Long respon=7L;
		InvolucradoDTO defensor=llenarInvolucrado();
		try {
			Long idRel = service.guardarDefensorAsignadoInvolucrado(defensor, respon);
			assertNotNull(idRel);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	public InvolucradoDTO llenarInvolucrado(){
		InvolucradoDTO involucrado = new InvolucradoDTO();		
		
		Long idExpediente = 1L;  //Asocia a Expediente
		//String numeroExpediente = "NSJYUCPROC2011333VW";
		Long tipoPersona = 0L; //0 Moral 1 Fisica
		Long idElemento = 0L; //Se envia cero para ingresar un nuevo elemento
		
		ExpedienteDTO expediente = new ExpedienteDTO();
		expediente.setFechaApertura(new Date());
		expediente.setFechaCierre(new Date());
		expediente.setExpedienteId(idExpediente); //ExpedienteDTO 
		//expediente.setNumeroExpediente(numeroExpediente );
		involucrado.setExpedienteDTO(expediente);
		
		//Calidad del individuo
		CalidadDTO calidad = new CalidadDTO();
		calidad.setDescripcionEstadoFisico("Se encontro en buen estado - Per");
		calidad.setCalidades(Calidades.PROBABLE_RESPONSABLE_ORGANIZACION);
//		calidad.setDescripcionEstadoFisico("Se encontro en buen estado");
//		calidad.setCalidades(Calidades.PROBABLE_RESPONSABLE_PERSONA);
		involucrado.setCalidadDTO(calidad);		

		//TipoPersona
		involucrado.setTipoPersona(tipoPersona);
		
			
		//Datos Involucrados
		involucrado.setElementoId(idElemento);
		involucrado.setEsDetenido(false);
		involucrado.setCondicion(Short.decode("1"));
		involucrado.setMotivoComparecencia("Prob");
		involucrado.setFechaCreacionElemento(new Date());
		involucrado.setEsVivo(true);
		//Los valores son de acuerdo a la calidad del involucrado:
		//Víctima - "Desconocido"
		//Denunciante - "Anónima"
		//Probable Responsable - "A quien resulte responsable"
//		involucrado.setDesconocido("Anónima");
	
		//Persona Fisica 
		if(tipoPersona ==1  ){
			//DATOS GENERALES
			List<NombreDemograficoDTO> nombres = new ArrayList<NombreDemograficoDTO>();
			NombreDemograficoDTO nombre = new NombreDemograficoDTO();
			nombre.setApellidoPaterno("Luna");
			nombre.setApellidoMaterno("Dominguez");
			nombre.setCurp("iuju896745hdfiur");
			nombre.setLugarNacimiento("DF Mexico");
			nombre.setNombre("Aurora");		
			nombre.setRfc("iuju896745ui7");
			nombre.setSexo("m");
			nombre.setEdadAproximada((short)25);
			nombre.setEntidadFederativaDTO(new EntidadFederativaDTO(31L));
			nombre.setEsVerdadero(false);
			nombre.setFechaNacimiento(new Date());
			nombre.setMunicipioDTO(new MunicipioDTO(604L));		
			nombre.setPaisValorDTO(new ValorDTO(10L));
			nombres.add(nombre);
			involucrado.setNombresDemograficoDTO(nombres);
								
			involucrado.setValorIdIdioma(new ValorDTO(53L));
			involucrado.setValorIdEscolaridad(new ValorDTO(108L));
			involucrado.setValorIdEstadoCivil(new ValorDTO(106L));
			involucrado.setValorIdReligion(new ValorDTO(90L));		
			
			//MEDIA FILIACION
			MediaFiliacionDTO mediaFiliacionDTO = datosAleatoriosMediaFiliacionDTO(catalogoService);
			//Señas Particulares
			SeniaParticularDTO seniaParticularDTO = datosAleatoriosSeniasParticularesDTO();
			mediaFiliacionDTO.setSeniaParticularDTO(seniaParticularDTO);
			involucrado.setMediaFiliacionDTO(mediaFiliacionDTO);
			
			
			//VALORES Q SON RELACIONADOS AL INDIVIDUO
			//Servidor Publico 
			ServidorPublicoDTO servidorPublico = new ServidorPublicoDTO();
			involucrado.setEsServidor(false);		
			servidorPublico.setDependencia("NSJP PU");
			servidorPublico.setNumeroEmpleado("00001");
			servidorPublico.setPuesto("Gerente");
			servidorPublico.setNivelDependencia(new ValorDTO(1444L));
			servidorPublico.setTipoDependencia(new ValorDTO(1454L));
			involucrado.setServidorPublicoDTO(servidorPublico);
		}
		
		//DOMICILIO
		DomicilioDTO domicilio = new DomicilioDTO();
		domicilio.setCalle("calle1N");
		domicilio.setNumeroExterior("10N");
		domicilio.setNumeroInterior("201N");
		domicilio.setNumeroLote("LoteN");
		domicilio.setReferencias("Ref MODN");
		domicilio.setEntreCalle1("entre calle 1N");
		domicilio.setEntreCalle2("entre calle 2N");
		domicilio.setAlias("RanchitoN");
		domicilio.setEdificio("CN");
		domicilio.setAsentamientoDTO(new AsentamientoDTO(20684L));
		domicilio.setEntidadDTO(new EntidadFederativaDTO(23L));
		domicilio.setMunicipioDTO(new MunicipioDTO(1L));
		domicilio.setCiudadDTO(new CiudadDTO(1L));
		domicilio.setValorCalleId(new ValorDTO(72L)); //Tipo Calle
		
		domicilio.setFechaCreacionElemento(new Date());
		//Lugar -> Elemento
		domicilio.setDescripcion("descripcion domicilio 1N");
		//CoordenadaGeografica - Lugar
		domicilio.setLatitud("S23°34'2");
		domicilio.setLongitud("E12°4'23");
		//Calidad de Domicilio
		CalidadDTO calidadDomicilio = new CalidadDTO();
		calidadDomicilio.setDescripcionEstadoFisico("En buen estado");
		calidadDomicilio.setValorIdCalidad(new ValorDTO(Calidades.DOMICILIO.getValorId()));
		calidadDomicilio.setCalidades(Calidades.DOMICILIO);				
		domicilio.setCalidadDTO(calidadDomicilio);
		domicilio.setExpedienteDTO(expediente);
		involucrado.setDomicilio(domicilio);
					
		//MEDIOS DE CONTACTO
		List<TelefonoDTO> telefonosDTO = new ArrayList<TelefonoDTO>();	
		telefonosDTO.add(new TelefonoDTO("01", "55", "57675609", new ValorDTO(233L)));
		telefonosDTO.add(new TelefonoDTO("T04L", "55", "57678676", new ValorDTO(233L)));
		involucrado.setTelefonosDTO(telefonosDTO);
		
		List<CorreoElectronicoDTO> correosElectronicosDTO = new ArrayList<CorreoElectronicoDTO>();		
		correosElectronicosDTO.add(new CorreoElectronicoDTO("salinas@hotmail.com"));
		correosElectronicosDTO.add(new CorreoElectronicoDTO("csalinas@yahoo.com"));
		involucrado.setCorreosDTO(correosElectronicosDTO);

	
		//Fisica
		if(tipoPersona ==1){
			//TIPO DE DOCUMENTO
			involucrado.setFolioIdentificacion("000001");
			involucrado.setValorIdIdentificaion(new ValorDTO(11L));

			//Nacionalidades		
			List<ValorDTO> nacionalidades = new ArrayList<ValorDTO>();
			nacionalidades.add(new ValorDTO(175L));
			nacionalidades.add(new ValorDTO(180L));
			nacionalidades.add(new ValorDTO(190L));
			involucrado.setValorIdNacionalidad(nacionalidades);
			//Ocupaciones
			List<ValorDTO> ocupaciones = new ArrayList<ValorDTO>();
			ocupaciones.add(new ValorDTO(131L));
			ocupaciones.add(new ValorDTO(138L));
			ocupaciones.add(new ValorDTO(147L));
			involucrado.setValorIdOcupacion(ocupaciones);
			//Alias
			List<AliasInvolucradoDTO> aliasDTO = new ArrayList<AliasInvolucradoDTO>();
			AliasInvolucradoDTO alias = new AliasInvolucradoDTO();
			alias.setAlias("El flaco");
			aliasDTO.add(alias);
			AliasInvolucradoDTO alias1 = new AliasInvolucradoDTO();
			alias1.setAlias("guayumin2");
			aliasDTO.add(alias1);
			AliasInvolucradoDTO alias2 = new AliasInvolucradoDTO();
			alias2.setAlias("guayusei2");
			aliasDTO.add(alias2);
			involucrado.setAliasInvolucradoDTO(aliasDTO);				
			
			//DOMICILIO NOTIFICACION
			CalidadDTO calidadDomicilioNot = new CalidadDTO();
			calidadDomicilioNot.setDescripcionEstadoFisico("En buen estado");
			calidadDomicilioNot.setValorIdCalidad(new ValorDTO(Calidades.DOMICILIO.getValorId()));
			calidadDomicilioNot.setCalidades(Calidades.DOMICILIO);
		
			DomicilioDTO domicilioNotificacion = new DomicilioDTO();
			domicilioNotificacion.setValorCalleId(new ValorDTO(96L));
			domicilioNotificacion.setEntidadDTO(new EntidadFederativaDTO(23L));
			domicilioNotificacion.setValorIdElemento(new ValorDTO(31L));
			domicilioNotificacion.setCalle("calle notificacion 1");
			domicilioNotificacion.setDescripcion("descripcion domicilio notificacion 1");
			domicilioNotificacion.setFechaCreacionElemento(new Date());
			domicilioNotificacion.setEntreCalle1("entre calle notificacin 1");
			domicilioNotificacion.setEntreCalle2("entre calle notificacion 2");						
			domicilioNotificacion.setAlias("Hacienda");
			domicilioNotificacion.setCalidadDTO(calidadDomicilioNot);
			domicilioNotificacion.setExpedienteDTO(expediente);
			domicilioNotificacion.setAsentamientoDTO(new AsentamientoDTO(20684L));
			domicilioNotificacion.setCiudadDTO(new CiudadDTO(2L));
			domicilioNotificacion.setMunicipioDTO(new MunicipioDTO(2L));
			domicilioNotificacion.setLatitud("E23°4'23");
			domicilioNotificacion.setLongitud("E12°4'40");
			involucrado.setDomicilioNotificacion(domicilioNotificacion);
		
		}
		//Moral
		else{
			//ORGANIZACION
			CalidadDTO calidadOrganizacion = new CalidadDTO();
			calidadOrganizacion.setCalidades(Calidades.PROBABLE_RESPONSABLE_ORGANIZACION);
			
			OrganizacionDTO organizacion = new OrganizacionDTO();
			organizacion.setNombreCorto("ORG B? GAMA");
			organizacion.setNombreOrganizacion("ORGANIZACION 1");
			organizacion.setNumeroActaConstitutiva("1234568");
			organizacion.setDireccionInternet("www.org1.com");
			organizacion.setAreaDeInfluencia("Metropolitana");
			organizacion.setFechaCreacionElemento(new Date());
			organizacion.setGiro("Financiero");
			organizacion.setPropositoCiberespacio("Desconocido");
			organizacion.setValorByComunidadVirtualVal(null);
			organizacion.setValorByOrganizacionFormalVal(null);
			organizacion.setValorBySectorGubernamentalVal(null);
			
			organizacion.setValorByTipoOrganizacionVal(new ValorDTO(new Long(TipoOrganizacion.FORMAL.getValorId())));
			
			organizacion.setExpedienteDTO(expediente);
			organizacion.setCalidadDTO(calidadOrganizacion);
			
			//DOMICILIO ORGANIZACION
			DomicilioDTO domicilioOrganizacion = new DomicilioDTO();
			domicilioOrganizacion.setCalle("calle organizacion 1");
			domicilioOrganizacion.setDescripcion("descripcion domicilio organizacion 1");
			domicilioOrganizacion.setFechaCreacionElemento(new Date());
			domicilioOrganizacion.setEntreCalle1("entre calle organizacion 1");
			domicilioOrganizacion.setEntreCalle2("entre calle organizacion 2");
			domicilioOrganizacion.setLatitud("E12°4'23");
			domicilioOrganizacion.setLongitud("S12°4'23");
			domicilioOrganizacion.setAsentamientoDTO(new AsentamientoDTO());
			domicilioOrganizacion.setExpedienteDTO(expediente);
			CalidadDTO calDomDTO = new CalidadDTO();
			calDomDTO.setCalidades(Calidades.DOMICILIO);
			domicilioOrganizacion.setCalidadDTO(calDomDTO);
			organizacion.setDomicilioDTO(domicilioOrganizacion);
			
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
			representanteDTO.setTipoPersona(1L);
			representanteDTO.setExpedienteDTO(expediente);
			
			List<NombreDemograficoDTO> nombresRL = new ArrayList<NombreDemograficoDTO>();
			NombreDemograficoDTO nombreRL = new NombreDemograficoDTO();
			nombreRL.setApellidoMaterno("RPL1");
			nombreRL.setApellidoPaterno("RPL2");
			nombreRL.setCurp("iuju896745hdfiu");
			nombreRL.setLugarNacimiento("Guanajuato MexicoM");
			nombreRL.setNombre("RPL");
			nombreRL.setSexo("m");
	//		nombre.setStrFechaNacimiento("");
			nombreRL.setEdadAproximada((short)20);
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
			domicilioRL.setCalle("calle1 RL");
			domicilioRL.setNumeroExterior("10 RL");
			domicilioRL.setNumeroInterior("201 RL");
			domicilioRL.setNumeroLote("Lote RL");
			domicilioRL.setReferencias("Ref MOD RL");
			domicilioRL.setEntreCalle1("entre calle 1 RL");
			domicilioRL.setEntreCalle2("entre calle 2 RL");
			domicilioRL.setAlias("Ranchito RL");
			domicilioRL.setEdificio("C RL");
			domicilioRL.setAsentamientoDTO(new AsentamientoDTO(20684L));
			domicilioRL.setEntidadDTO(new EntidadFederativaDTO(23L));
			domicilioRL.setMunicipioDTO(new MunicipioDTO(1L));
			domicilioRL.setCiudadDTO(new CiudadDTO(1L));
			domicilioRL.setValorCalleId(new ValorDTO(72L)); //Tipo Calle
			
			domicilioRL.setFechaCreacionElemento(new Date());
			//Lugar -> Elemento
			domicilioRL.setDescripcion("descripcion domicilio 1 RL");
			//CoordenadaGeografica - Lugar
			domicilioRL.setLatitud("E12°4'23");
			domicilioRL.setLongitud("N12°4'23");
			//Calidad de Domicilio
			CalidadDTO calidadDomicilioRL = new CalidadDTO();
			calidadDomicilioRL.setDescripcionEstadoFisico("En buen estado RL");
			calidadDomicilioRL.setValorIdCalidad(new ValorDTO(Calidades.DOMICILIO.getValorId()));
			calidadDomicilioRL.setCalidades(Calidades.DOMICILIO);				
			domicilioRL.setCalidadDTO(calidadDomicilioRL);
			domicilioRL.setExpedienteDTO(expediente);
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
			nacionalidadesRL.add(new ValorDTO(190L));
			representanteDTO.setValorIdNacionalidad(nacionalidadesRL);
			//RL - OR - Ocupaciones
			List<ValorDTO> ocupacionesRL = new ArrayList<ValorDTO>();
			ocupacionesRL.add(new ValorDTO(131L));
			ocupacionesRL.add(new ValorDTO(138L));
			ocupacionesRL.add(new ValorDTO(147L));
			representanteDTO.setValorIdOcupacion(ocupacionesRL);
			
			organizacion.setRepresentanteLegal(representanteDTO);
	
			involucrado.setOrganizacionDTO(organizacion);
			
		}
		
		
		return involucrado;
	}
}

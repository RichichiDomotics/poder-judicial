/**
* Nombre del Programa : IngresarIndividuoAction.java
* Autor               : Cuauhtemoc Paredes
* Compania            : Ultrasist
* Proyecto            : NSJP                    Fecha: 23/02/2011
* Marca de cambio     : N/A
* Descripcion General : Clase Action para Ingresar Individuo
* Programa Dependiente: N/A
* Programa Subsecuente: N/A
* Cond. de ejecucion  : N/A
* Dias de ejecucion   : N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor               :N/A
* Compania            :N/A
* Proyecto            :N/A                                   Fecha: N/A
* Modificacion        :N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.web.persona.action;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.delegate.detencion.DetencionDelegate;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.domicilio.AsentamientoDTO;
import mx.gob.segob.nsjp.dto.domicilio.CiudadDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioExtranjeroDTO;
import mx.gob.segob.nsjp.dto.domicilio.EntidadFederativaDTO;
import mx.gob.segob.nsjp.dto.domicilio.MunicipioDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.DetencionDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 
 * @author Cuauhtemoc Paredes
 *
 *  Action para ingresar detenido
 *
 */
public class IngresarDetenidoAction extends GenericAction{

private static final Logger log  = Logger.getLogger(IngresarDetenidoAction.class);

@Autowired
private DetencionDelegate detencionDelegate;



	public ActionForward registrarDetenido (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			//Datos generales de la detencion
			String recibeDetenido = request.getParameter("recibeDetenido");
			log.info("llega recibeDetenido" + recibeDetenido);
			//falta traslado
			String trasladaDetenido = request.getParameter("trasladaDetenido");
			log.info("llega trasladaDetenido" + trasladaDetenido);
			String fechaDetencion = request.getParameter("fechaDetencion");
			log.info("llega fechaDetencion" + fechaDetencion);
			String horaDetencion = request.getParameter("horaDetencion");
			log.info("llega horaDetencion" + horaDetencion);
			String fechaRecepcion = request.getParameter("fechaRecepcion");
			log.info("llega fechaRecepcion" + fechaRecepcion);
			String horaRecepcion = request.getParameter("horaRecepcion");
			log.info("llega horaRecepcion" + horaRecepcion);
			String motivoDetencion = request.getParameter("motivoDetencion");
			log.info("llega motivoDetencion" + motivoDetencion);
			String observaciones = request.getParameter("observaciones");
			log.info("llega observaciones" + observaciones);
			String idDetenido = request.getParameter("idDetenido");
			log.info("llega idDetenido" + idDetenido);
			String idCaso = request.getParameter("idCaso");
			log.info("llega idCaso" + idCaso);
			
			DateFormat formato = new SimpleDateFormat("dd/MM/yy");
			Date fechaDetencionInicio = null;
			Date fechaRecepcionInicio = null;
			
			try {
				fechaDetencionInicio = formato.parse(fechaDetencion);
				fechaRecepcionInicio = formato.parse(fechaRecepcion);		
			
			} catch (ParseException e) {
			
				e.printStackTrace();
				
			}
						
			FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
			funcionarioDTO.setClaveFuncionario(Long.parseLong(recibeDetenido));
			DetencionDTO detencionDTO = new DetencionDTO();
			detencionDTO.setFuncionarioByFuncionarioDetiene(funcionarioDTO);
			detencionDTO.setFechaInicioDetencion(fechaDetencionInicio);
			detencionDTO.setFechaRecepcionDetencion(fechaRecepcionInicio);
			detencionDTO.setHoraInicioDetencion(horaDetencion);
			detencionDTO.setHoraRecepcionDetencion(horaRecepcion);
			detencionDTO.setMotivoDetencion(motivoDetencion);
			detencionDTO.setObservaciones(observaciones);
			
			CasoDTO casoDTO = new CasoDTO();
			if(idCaso!= null){			
			casoDTO.setCasoId(Long.parseLong(idCaso));
			}
			
			
			//Datos generales de la persona
			String nombre = request.getParameter("nombre");
			log.info("llega nombre" + nombre);
			String apellidoPaterno = request.getParameter("apellidoPaterno");
			log.info("llega apellidoPaterno" + apellidoPaterno);
			String apellidoMaterno = request.getParameter("apellidoMaterno");
			log.info("llega apellidoMaterno" + apellidoMaterno);
			String curp = request.getParameter("curp");
			log.info("llega curp" + curp);
			String rfc = request.getParameter("rfc");
			log.info("llega rfc" + rfc);
			String fechaIngreso = request.getParameter("fechaIngreso");
			log.info("llega fechaIngreso" + fechaIngreso);
			String idioma = request.getParameter("idioma");
			log.info("llega idioma" + idioma);
			String religion = request.getParameter("religion");
			log.info("llega religion" + religion);
			String escolaridad = request.getParameter("escolaridad");
			log.info("llega escolaridad" + escolaridad);
			String estadoCivil = request.getParameter("estadoCivil");
			log.info("llega estadoCivil" + estadoCivil);
			String sexo = request.getParameter("sexo");
			log.info("llega sexo" + sexo);
			String alias = request.getParameter("alias");
			log.info("llega alias" + alias);
			String ocupacion = request.getParameter("ocupacion");
			log.info("llega ocupacion" + ocupacion);
			String nacionalidad = request.getParameter("nacionalidad");
			log.info("llega nacionalidad" + nacionalidad);
			
			//Datos Nacimiento
			String fechaNacimiento = request.getParameter("fechaNacimiento");
			log.info("llega fechaNacimiento" + fechaNacimiento);
			String edadAproximada = request.getParameter("edadAproximada");
			log.info("llega edadAproximada" + edadAproximada);
			String paisNacimiento = request.getParameter("paisNacimiento");
			log.info("llega paisNacimiento" + paisNacimiento);
			String entFederativaNacimiento = request.getParameter("entFederativaNacimiento");
			log.info("llega entFederativaNacimiento" + entFederativaNacimiento);
			String municipioNacimiento = request.getParameter("municipioNacimiento");
			log.info("llega municipioNacimiento" + municipioNacimiento);
					    
			InvolucradoDTO involucradoDTO =  new InvolucradoDTO();
			DomicilioDTO domicilioDTO = new DomicilioDTO();
			
			if(nombre!=null && !nombre.equals("")){
				log.info("entra modo datos generales");
								
			NombreDemograficoDTO datosGenerales = new NombreDemograficoDTO();
			ValorDTO valorGenerico = new ValorDTO();
			
			List<NombreDemograficoDTO> lstDatosGenerales = new ArrayList<NombreDemograficoDTO>();
			
			datosGenerales.setNombre(nombre);
			datosGenerales.setApellidoPaterno(apellidoPaterno);
			datosGenerales.setApellidoMaterno(apellidoMaterno);
			datosGenerales.setCurp(curp);
			datosGenerales.setRfc(rfc);
			datosGenerales.setSexo(sexo);
			if (fechaNacimiento != null && !fechaNacimiento.equals("")) {
				datosGenerales.setFechaNacimiento(DateUtils.obtener(fechaNacimiento));
			}
			if (edadAproximada != null && !edadAproximada.equals("")) {
				datosGenerales.setEdadAproximada(Short.parseShort(edadAproximada));
			}
											
			if(escolaridad!=null && ! escolaridad.equals("- Selecciona -")){
				valorGenerico.setIdCampo(Long.parseLong(escolaridad));
				involucradoDTO.setValorIdEscolaridad(valorGenerico);
			}			
			
			valorGenerico = new ValorDTO();
			
			if(estadoCivil!=null && ! estadoCivil.equals("- Selecciona -")){
				valorGenerico.setIdCampo(Long.parseLong(estadoCivil));
				involucradoDTO.setValorIdEstadoCivil(valorGenerico);
			}
						
			valorGenerico = new ValorDTO();						
			valorGenerico.setIdCampo(Long.parseLong(idioma));
			involucradoDTO.setValorIdIdioma(valorGenerico);
			
			List<ValorDTO> listaValor=new ArrayList<ValorDTO>();
			valorGenerico = new ValorDTO();
			
			if(ocupacion!=null && !ocupacion.equals("- Selecciona -")){
				valorGenerico.setIdCampo(Long.parseLong(ocupacion));
			}
			
			listaValor.add(valorGenerico);
			involucradoDTO.setValorIdOcupacion(listaValor);
			
			listaValor=new ArrayList<ValorDTO>();
			valorGenerico = new ValorDTO();
			valorGenerico.setIdCampo(Long.parseLong(nacionalidad));
			listaValor.add(valorGenerico);
			involucradoDTO.setValorIdNacionalidad(listaValor);
			
			lstDatosGenerales.add(datosGenerales);
			
			involucradoDTO.setNombresDemograficoDTO(lstDatosGenerales);
						
			}else{
				log.info("entra modo grid");
			involucradoDTO.setElementoId(Long.parseLong("idDetenido"));
			
				
			}
						
			//domicilio
			String pais = request.getParameter("pais");
			String codigoPostal = request.getParameter("codigoPostal");
			String entidadFederativa = request.getParameter("entidadFederativa");
			String ciudad = request.getParameter("ciudad");
			String delegacionMunicipio = request.getParameter("delegacionMunicipio");
			String asentamientoColonia = request.getParameter("asentamientoColonia");
			String tipoAsentamiento = request.getParameter("tipoAsentamiento");
			String tipoCalle = request.getParameter("tipoCalle");
			String calle = request.getParameter("calle");
			String numExterior = request.getParameter("numExterior");
			String numInterior = request.getParameter("numInterior");
			String referencias = request.getParameter("referencias");
			String entreCalle = request.getParameter("entreCalle");
			String ycalle = request.getParameter("ycalle");
			String aliasDomicilio = request.getParameter("aliasDomicilio");
			String edificio = request.getParameter("edificio");
			String longitud = request.getParameter("longitud");
			String latitud = request.getParameter("latitud");
			
			log.info("PAIS="+pais);
			log.info("CP="+codigoPostal);
			log.info("ENTIDAD FEDERATIVA="+entidadFederativa);
			log.info("CIUDAD="+ciudad);
			log.info("DELEGACION-MUNICIPIO="+delegacionMunicipio);
			log.info("ASENTAMIENTO COLONIA="+asentamientoColonia);
			log.info("TIPO ASENTAMIENTO="+tipoAsentamiento);
			log.info("TIPO CALLE="+tipoCalle);
			log.info("CALLE="+calle);
			log.info("NUMERO EXTERIOR="+numExterior);
			log.info("NUMERO INTERIOR="+numInterior);
			log.info("REFERENCIAS="+referencias);
			log.info("ENTRE CALLE="+entreCalle);
			log.info("Y CALLE="+ycalle);
			log.info("ALIAS DOMICILIO="+aliasDomicilio);
			log.info("EDIFICIO="+edificio);
			log.info("LONGITUD="+longitud);
			log.info("LATITUD="+latitud);						
					
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
			
			if(!longitud.equalsIgnoreCase("")){
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
		
		detencionDTO.setInvolucradoDTO(involucradoDTO);		
		
		detencionDTO = detencionDelegate.recibirDetenido(detencionDTO, casoDTO);
		
		log.info("regresa objeto detencion" + detencionDTO);
		
		
		    		
			}
		catch (Exception e) {
			log.info(e.getMessage(), e);
		}
		return null;
		
	}	
		
}

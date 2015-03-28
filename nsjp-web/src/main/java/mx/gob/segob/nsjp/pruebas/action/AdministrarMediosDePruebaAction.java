/**
 * 
 */
package mx.gob.segob.nsjp.pruebas.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.prueba.PruebaDelegate;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.elemento.ElementoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.prueba.DatoPruebaDTO;
import mx.gob.segob.nsjp.dto.prueba.MedioPruebaDTO;
import mx.gob.segob.nsjp.dto.prueba.RelacionDatoMedioPruebaDTO;
import mx.gob.segob.nsjp.pruebas.form.RegistrarMedioDePruebaPJForm;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author AlejandroGA
 *
 */
public class AdministrarMediosDePruebaAction extends GenericAction{
	
	/** Log de clase */
	private static final Logger log = Logger
			.getLogger(AdministrarMediosDePruebaAction.class);
	
	@Autowired
	public PruebaDelegate pruebaDelegate;
	
	/**
	 * Metodo que agrega un medio de prueba a un expediente, de tipo documento digital
	 * @author AlejandroGA
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward agregarMedioPrueba(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("EJECUTANDO ACTION AGREGAR MEDIO PRUEBA");
			
			MedioPruebaDTO medioPruebaDTO = new MedioPruebaDTO();
			
			RegistrarMedioDePruebaPJForm forma = (RegistrarMedioDePruebaPJForm)form;
			
			log.info("__________________VERIFICANDO LA FORMA_________________________");
			log.info("archivo nombre completo::" +forma.getArchivoAdjunto().getFileName());
			
			String[] nombres = forma.getArchivoAdjunto().getFileName().split("\\.");
			log.info("archivoAdjunto Nombre:" +nombres[0]);
			
			String[] extension = forma.getArchivoAdjunto().getFileName().split("\\.");
			log.info("archivoAdjunto Extensión:" +extension[1]);
			
			log.info("archivoAdjunto Tamaño::::" +forma.getArchivoAdjunto().getFileSize()+" Bytes");
			log.info("audienciaId::::::::::::::" +forma.getAudienciaId());
			log.info("tipoMedioPrueba::::::::::" +forma.getTipoMedioPrueba());
			log.info("subTipoMedioPrueba:::::::" +forma.getSubTipoMedioPrueba());
			log.info("nombreDoc::::::::::::::::" +forma.getNombreDoc());
			log.info("numIdDoc:::::::::::::::::" +forma.getNumIdDoc());
			log.info("refUbicacionFisica:::::::" +forma.getRefUbicacionFisica());
			log.info("descDocumento::::::::::::" +forma.getDescDocumento());
			log.info("numeroExpediente:::::::::" +forma.getNumeroExpediente());
			log.info("dato prueba id ::::::::::" +forma.getDatoPruebaId());
			
			if(forma.getArchivoAdjunto() != null ){
				
				ArchivoDigitalDTO archivoDigital = new ArchivoDigitalDTO();
				
				archivoDigital.setContenido(forma.getArchivoAdjunto().getFileData());
				archivoDigital.setNombreArchivo(nombres[0]);
				archivoDigital.setTipoArchivo((".").concat(extension[1]));
				
				DocumentoDTO documento = new DocumentoDTO();
				documento.setNombreDocumento(forma.getNombreDoc());
				documento.setArchivoDigital(archivoDigital);
				medioPruebaDTO.setDocumentoMedioPrueba(documento);
			}
			
			
			medioPruebaDTO.setDescripcion(forma.getDescDocumento());
			medioPruebaDTO.setNombreMedio(forma.getNombreDoc());
			medioPruebaDTO.setNumeroIdentificacion(forma.getNumIdDoc());
			medioPruebaDTO.setUbicacionFisica(forma.getRefUbicacionFisica());
			
			DatoPruebaDTO datoPruebaDTO = new DatoPruebaDTO();
			datoPruebaDTO.setDatoPruebaId(forma.getDatoPruebaId());
			
			pruebaDelegate.registrarMedioPruebaConRelacionADatoPrueba(medioPruebaDTO, datoPruebaDTO);
			//Subimos la informacion a session
			request.getSession().setAttribute("idEvento",forma.getAudienciaId());
			
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return mapping.findForward("succes");
	}
	
	
	
	/**
	 * Metodo para relacionar un medio de prueba Individuo con un dato de prueba
	 * @author AlejandroGA
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward relacionarIndividuoMedioPrueba(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("EJECUTANDO ACTION AGREGAR MEDIO PRUEBA INDIVIDUO");
			
			log.info("__________________VERIFICANDO PARAMETROS_________________________");			
			log.info("involucradoId:::::::::" +request.getParameter("involucradoId"));
			log.info("idDatoPrueba::::::::::" +request.getParameter("idDatoPrueba"));
			log.info("numero de identificacion:::::::::" +request.getParameter("numIde"));
			log.info("nombre medio prueba::::::::::::::" +request.getParameter("nombreMedio"));
			log.info("apPat medio prueba::::::::::::::" +request.getParameter("apPat"));
			log.info("apMat medio prueba::::::::::::::" +request.getParameter("apMat"));
			log.info("idMedioPruebaActuliza::::::::::::::" +request.getParameter("idMedioPruebaActuliza"));
			
			
			Long involucradoId = NumberUtils.toLong(request.getParameter("involucradoId"),0L);
			Long idDatoPrueba = NumberUtils.toLong(request.getParameter("idDatoPrueba"),0L);
			String numIde = request.getParameter("numIde");
			String nombreMedio = request.getParameter("nombreMedio");
			String apPat = request.getParameter("apPat");
			String apMat = request.getParameter("apMat");
			//usado para update del medio de prueba
			Long medioPruebaId = NumberUtils.toLong(request.getParameter("idMedioPruebaActuliza"),0L);
			
			
			if(involucradoId > 0L && idDatoPrueba >0L && numIde!= null && nombreMedio != null){
				
				ElementoDTO involucradoDTO = new InvolucradoDTO();
				involucradoDTO.setElementoId(involucradoId);

				MedioPruebaDTO medioPruebaDTO = new MedioPruebaDTO();
				medioPruebaDTO.setElementoMedioPrueba(involucradoDTO);
				medioPruebaDTO.setNumeroIdentificacion(numIde);
				medioPruebaDTO.setMedioPruebaId(medioPruebaId);

				
				if(apPat != null && !apPat.trim().isEmpty()){
					nombreMedio = nombreMedio.concat(" "+apPat);
					log.info("nombreCompleto  apPat ::::::"+nombreMedio);
				}
				if(apMat != null && !apMat.trim().isEmpty()){
					log.info("nombreCompleto  apMat ::::::"+nombreMedio);
					nombreMedio = nombreMedio.concat(" "+apMat);
				}
				
				medioPruebaDTO.setNombreMedio(nombreMedio);
				
				DatoPruebaDTO datoPruebaDTO = new DatoPruebaDTO();
				datoPruebaDTO.setDatoPruebaId(idDatoPrueba);
				
				MedioPruebaDTO medioPruebaResp = pruebaDelegate.registrarMedioPruebaConRelacionADatoPrueba(medioPruebaDTO,datoPruebaDTO);
				
				if(medioPruebaResp != null && medioPruebaResp.getMedioPruebaId() != null){
					escribir(response,medioPruebaResp.getMedioPruebaId().toString(),null);
				}
				else{
					escribir(response,"fail",null);
				}
			}
						
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return mapping.findForward("succes");
	}
	
	
	/**
	 * Metodo que consulta los medios de prueba NO ASOCIADOS a un dato de prueba
	 * @author AlejandroGA
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarMediosPruebaXDatoPrueba(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("EJECUTANDO ACTION CONSULTAR MEDIO PRUEBA");
			
			
			log.info("__________________VERIFICANDO LOS PARAMETROS_________________________");
			log.info("id del dato prueba::::::" + request.getParameter("idDatoPrueba"));
			log.info("numeroExpediente::::::::" + request.getParameter("numeroExpediente"));
			Long idDatoPrueba = NumberUtils.toLong(request.getParameter("idDatoPrueba"), 0L);
			String numeroExpediente = request.getParameter("numeroExpediente");
			
			if( idDatoPrueba > 0L && numeroExpediente != null){
				
				ExpedienteDTO expedienteDto = new ExpedienteDTO();
				expedienteDto.setNumeroExpediente(numeroExpediente);
				
				DatoPruebaDTO datoPruebaDTO = new DatoPruebaDTO();
				datoPruebaDTO.setDatoPruebaId(idDatoPrueba);
				datoPruebaDTO.setExpediente(expedienteDto);
				
				List<MedioPruebaDTO> listaMedioRelacionados = pruebaDelegate.consultarMediosPruebaXDatoPrueba(datoPruebaDTO, false);
				
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
				
				writer.print("<rows>");
				
				int lTotalRegistros=listaMedioRelacionados.size();
				
				writer.print("<records>" + lTotalRegistros + "</records>");
					for (MedioPruebaDTO medioPruebaDTO : listaMedioRelacionados) {
						
							log.info("medioPruebaDTO="+medioPruebaDTO);
						writer.print("<row id='" + medioPruebaDTO.getMedioPruebaId()+ "'>");
							writer.print("<cell>" + medioPruebaDTO.getNombreMedio() + "</cell>");
							writer.print("<cell>" + medioPruebaDTO.getNumeroIdentificacion()+ "</cell>");
						writer.print("</row>");
					}			
				writer.print("</rows>");
				writer.flush();
				writer.close();

			}
			
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}
	
	
	/**
	 * Metodo que consulta los medios de prueba ASOCIADOS a un dato de prueba
	 * @author AlejandroGA
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarMediosPruebaAsociadosXDatoPrueba(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("EJECUTANDO ACTION CONSULTAR MEDIOS DE PRUEBA ASOCIADOS AL DATO");
			
			
			log.info("__________________VERIFICANDO LOS PARAMETROS_________________________");
			log.info("id del dato prueba::::::" + request.getParameter("idDatoPrueba"));
			log.info("numeroExpediente::::::::" + request.getParameter("numeroExpediente"));
			Long idDatoPrueba = NumberUtils.toLong(request.getParameter("idDatoPrueba"), 0L);
			String numeroExpediente = request.getParameter("numeroExpediente");
			
			if( idDatoPrueba > 0L && numeroExpediente != null){
				
				ExpedienteDTO expedienteDto = new ExpedienteDTO();
				expedienteDto.setNumeroExpediente(numeroExpediente);
				
				DatoPruebaDTO datoPruebaDTO = new DatoPruebaDTO();
				datoPruebaDTO.setDatoPruebaId(idDatoPrueba);
				datoPruebaDTO.setExpediente(expedienteDto);
				
				List<MedioPruebaDTO> listaMedioRelacionados = pruebaDelegate.consultarMediosPruebaXDatoPrueba(datoPruebaDTO,true);
								
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
				
				writer.print("<rows>");
				
				int lTotalRegistros=listaMedioRelacionados.size();
				
				writer.print("<records>" + lTotalRegistros + "</records>");
					for (MedioPruebaDTO medioPruebaDTO : listaMedioRelacionados) {
						
							log.info("medioPruebaDTO="+medioPruebaDTO);
						writer.print("<row id='" + medioPruebaDTO.getMedioPruebaId()+ "'>");
							writer.print("<cell>" + medioPruebaDTO.getNombreMedio() + "</cell>");
							writer.print("<cell>" + medioPruebaDTO.getNumeroIdentificacion()+ "</cell>");
							for(RelacionDatoMedioPruebaDTO relacion : medioPruebaDTO.getRelacionesDatoMedioPrueba()){
								
								if(idDatoPrueba == relacion.getDatoPrueba().getDatoPruebaId()){
									
									String idRelacion=relacion.getRelacionDatoMedioPruebaId().toString();
									
									//SI NO ES ACEPTADO NI RECHAZADO
									if(relacion.getEsAceptado() == null){
										
										//columna aceptar
										writer.print("<cell><![CDATA[" +
								    					"<input type='radio' name='rbtRelacioDPMP_"+idRelacion+"' onclick='aceptarRelacion("+idRelacion+");' id='aceptado_"+idRelacion+"'>"+
								    				"]]></cell>");
										
										//columna cancelar
										writer.print("<cell><![CDATA[" +
								    					"<input type='radio' name='rbtRelacioDPMP_"+idRelacion+"' onclick='cancelarRelacion("+idRelacion+");' id='cancelado_"+idRelacion+"'>"+
								    				"]]></cell>");	
									}
									
									else{
										
										//SI ES ACEPTADO
										if(relacion.getEsAceptado() == true){
											
											//columna aceptar
											writer.print("<cell><![CDATA[" +
									    					"<input type='radio' name='rbtRelacioDPMP_"+idRelacion+"' onclick='aceptarRelacion("+idRelacion+");' id='aceptado_"+idRelacion+"' checked='checked'>"+
									    				"]]></cell>");
											
											//columna cancelar
											writer.print("<cell><![CDATA[" +
									    					"<input type='radio' name='rbtRelacioDPMP_"+idRelacion+"' onclick='cancelarRelacion("+idRelacion+");' id='cancelado_"+idRelacion+"'>"+
									    				"]]></cell>");
											
										}
										
										//SI ES CANCELADO
										else{
											
											//columna aceptar
											writer.print("<cell><![CDATA[" +
									    					"<input type='radio' name='rbtRelacioDPMP_"+idRelacion+"' onclick='aceptarRelacion("+idRelacion+");' id='aceptado_"+idRelacion+"'>"+
									    				"]]></cell>");
											
											//columna cancelar
											writer.print("<cell><![CDATA[" +
									    					"<input type='radio' name='rbtRelacioDPMP_"+idRelacion+"' onclick='cancelarRelacion("+idRelacion+");' id='cancelado_"+idRelacion+"' checked='checked'>"+
									    				"]]></cell>");
										}
									}

								}
							}

						writer.print("</row>");
					}			
				writer.print("</rows>");
				writer.flush();
				writer.close();

			}
			
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}
	
	
	/**
	 * Metodo que consulta los medios de prueba asociados al expediente
	 * @author AlejandroGA
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarMediosPruebaAsociadosAlExpediente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("EJECUTANDO ACTION CONSULTAR MEDIO PRUEBA");
			
			
			log.info("__________________VERIFICANDO LOS PARAMETROS_________________________");
			log.info("id del dato prueba::::::" + request.getParameter("idDatoPrueba"));
			log.info("numeroExpediente::::::::" + request.getParameter("numeroExpediente"));
			
			String numeroExpediente = request.getParameter("numeroExpediente");
			
			if(numeroExpediente != null && !numeroExpediente.equals("")){
				
				ExpedienteDTO expedienteDto = new ExpedienteDTO();
				
				expedienteDto.setNumeroExpediente(numeroExpediente);
				
				DatoPruebaDTO datoPruebaDTO = new DatoPruebaDTO();
				
				datoPruebaDTO.setDatoPruebaId(null);
				datoPruebaDTO.setExpediente(expedienteDto);
				
				List<MedioPruebaDTO> listaMedioRelacionados = pruebaDelegate
						.consultarMediosPruebaXDatoPrueba(datoPruebaDTO, null);

				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();

				writer.print("<rows>");
				final PaginacionDTO pag = PaginacionThreadHolder.get();
                writer.print("<total>" + pag.getTotalPaginas() + "</total>");
                writer.print("<records>" + pag.getTotalRegistros() + "</records>");

                for (MedioPruebaDTO medioPruebaDTO : listaMedioRelacionados) {

					log.info("medioPruebaDTO=" + medioPruebaDTO);
					writer.print("<row id='" + medioPruebaDTO.getMedioPruebaId()
							+ "'>");
					writer.print("<cell>" + medioPruebaDTO.getNombreMedio()
							+ "</cell>");
					writer.print("<cell>"
							+ medioPruebaDTO.getNumeroIdentificacion() + "</cell>");
					writer.print("</row>");
				}
				writer.print("</rows>");
				writer.flush();
				writer.close();				
			}			
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}
		
	
	/**
	 * Metodo para relacionar los medios de prueba a un dato de prueba
	 * @author AlejandroGA
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward relacionarMedioPruebaConDatoPrueba(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("EJECUTANDO ACTION RELACIONAR MEDIOS DE PRUEBA CON DATO DE PRUEBA");
			log.info("__________________VERIFICANDO LOS PARAMETROS_________________________");
			log.info("id del dato prueba::::::" + request.getParameter("idDatoPrueba"));
			log.info("iDsMediosPrueba:::::::::" + request.getParameter("iDsMediosPrueba"));
			log.info("numeroExpediente:::::::::" + request.getParameter("numeroExpediente"));
			
			Long idDatoPrueba = NumberUtils.toLong(request.getParameter("idDatoPrueba"), 0L);
			String iDsMediosPrueba = request.getParameter("iDsMediosPrueba");
			String numeroExpediente = request.getParameter("numeroExpediente");
			
			if(idDatoPrueba > 0L && iDsMediosPrueba != null &&  !iDsMediosPrueba.equals("") && numeroExpediente != null){
				
				ExpedienteDTO expedienteDto = new ExpedienteDTO();
				
				expedienteDto.setNumeroExpediente(numeroExpediente);
				
				DatoPruebaDTO datoPruebaDto = new DatoPruebaDTO();
				
				datoPruebaDto.setDatoPruebaId(idDatoPrueba);
				datoPruebaDto.setExpediente(expedienteDto);
				
				List<MedioPruebaDTO> listaMediosPruebaDTO = obtenerMediosPrueba(iDsMediosPrueba);
				if(listaMediosPruebaDTO != null){
					pruebaDelegate.relacionarMedioPruebaConDatoPrueba(datoPruebaDto, listaMediosPruebaDTO);
					escribir(response,"succes",null);
				}
			}
			else{
				escribir(response,"fail",null);
			}
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}
	
	
	/**
	 * Metodo utilizado para obtener una lista de medios prueba apartir de su id
	 * localizado en una cadena
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	private List<MedioPruebaDTO> obtenerMediosPrueba(String iDsMediosPrueba)throws IOException{
		
		List<MedioPruebaDTO> listaMedios = new ArrayList<MedioPruebaDTO>();
		
		try {
			
			if(iDsMediosPrueba != null){
				String listaIds[] = iDsMediosPrueba.split(",");
				for(String id : listaIds){
					MedioPruebaDTO medioPruebaDto = new MedioPruebaDTO();
					medioPruebaDto.setMedioPruebaId(Long.parseLong(id));
					listaMedios.add(medioPruebaDto);
				}
			}
			
		} catch (Exception e) {
			log.info(e.getCause(), e);
		}
		return listaMedios;
	}
	
	
	/**
	 * Metodo consulta un medio de prueba por su id
	 * @author AlejandroGA
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarMedioPrueba(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("EJECUTANDO ACTION CONSULTAR MEDIO PRUEBA");
			log.info("__________________VERIFICANDO LOS PARAMETROS_________________________");
			
			
			Long medioPruebaId = NumberUtils.toLong(request.getParameter("medioPruebaId"),0L);
			log.info("medio prueba id::::::::::::::::::"+medioPruebaId);
			
			
			if(medioPruebaId != 0L){
				
				MedioPruebaDTO medioPruebaDto = new MedioPruebaDTO();
				
				medioPruebaDto = pruebaDelegate.consultarMedioPrueba(medioPruebaId);
				
				converter.alias("medioPrueba", MedioPruebaDTO.class);
				String xml = converter.toXML(medioPruebaDto);
				escribir(response,xml,null);
			}		
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}
	
	
	
	/**
	 * Metodo consulta un medio de prueba por su id
	 * @author AlejandroGA
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward aceptarCancelarRelacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("EJECUTANDO ACTION ACTUALIZAR ESTADO DE LA RELACION MEDIO PRUEBA / DATO PRUEBA");
			log.info("__________________________VERIFICANDO LOS PARAMETROS_________________________");
			
			Boolean esAceptado = Boolean.parseBoolean(request.getParameter("esAceptado"));
			Long relacionId = NumberUtils.toLong(request.getParameter("relacionId"), 0L);
			

			log.info("esAceptado::::::::::::::::::"+esAceptado);
			log.info("relacionId:::::::::::::::"+relacionId);
			
			if(relacionId > 0 && esAceptado != null){
							
				RelacionDatoMedioPruebaDTO relacionDatoMedioPruebaDTO = new RelacionDatoMedioPruebaDTO();
				
				relacionDatoMedioPruebaDTO.setRelacionDatoMedioPruebaId(relacionId);
				relacionDatoMedioPruebaDTO.setEsAceptado(esAceptado);				
				pruebaDelegate.aceptarCarncelarRelacionMedioPrueba(relacionDatoMedioPruebaDTO);
				
				escribir(response, "succes",null);
			}else{
				escribir(response, "No se completo la operación",null);
			}
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}
	
	
}

package mx.gob.segob.nsjp.pruebas.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.involucrado.InvolucradoDelegate;
import mx.gob.segob.nsjp.delegate.prueba.PruebaDelegate;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoViewDTO;
import mx.gob.segob.nsjp.dto.prueba.DatoPruebaDTO;
import mx.gob.segob.nsjp.dto.prueba.MedioPruebaDTO;
import mx.gob.segob.nsjp.dto.prueba.RelacionPruebaInvolucradoDTO;
import mx.gob.segob.nsjp.service.prueba.AdministrarDatoPruebaService;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

public class AdministrarDatosDePruebaAction extends GenericAction{
	
	/** Log de clase */
	private static final Logger log = Logger
			.getLogger(AdministrarDatosDePruebaAction.class);
	
	@Autowired
	public PruebaDelegate pruebaDelegate;
	
	@Autowired
	public InvolucradoDelegate involucradoDelegate;
	
	
	/**
	 * Metodo que agrega un medio de prueba a un expediente, de tipo documento digital
	 * @author AlejandroGA
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward agregarDatoPrueba(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("EJECUTANDO ACTION AGREGAR DATO DE PRUEBA");
			log.info("__________________VERIFICANDO LOS PARAMETROS_________________________");
			
			String audienciaId = request.getParameter("audienciaId");
			String tipoDatoPrueba = request.getParameter("tipoDatoPrueba");
			Long tipoObjetoDatoPrueba = NumberUtils.toLong(request.getParameter("tipoObjetoDatoPrueba"),0L);
			String nombreDato = request.getParameter("nombreDato");
			String numIdDato = request.getParameter("numIdDato");
			String rccDato = request.getParameter("rccDato");
			String descDato = request.getParameter("descDato");
			String numeroExpediente = request.getParameter("numeroExpediente");
			Long datoPruebaId = NumberUtils.toLong(request.getParameter("datoPruebaId"),0L);
			 
			log.info("audienciaId:::::::::::::::::::::"+audienciaId);
			log.info("tipoDatoPrueba::::::::::::::::::"+tipoDatoPrueba);
			log.info("tipoObjetoDatoPrueba::::::::::::"+tipoObjetoDatoPrueba);
			log.info("nombreDato::::::::::::::::::::::"+nombreDato);
			log.info("numIdDato:::::::::::::::::::::::"+numIdDato);
			log.info("rccDato:::::::::::::::::::::::::"+rccDato);
			log.info("descDato::::::::::::::::::::::::"+descDato);
			log.info("numeroExpediente::::::::::::::::"+numeroExpediente);
			log.info("dato prueba id::::::::::::::::::"+datoPruebaId);
			
			DatoPruebaDTO datoPruebaDTO = new DatoPruebaDTO();
			if(datoPruebaId == 0L){
				datoPruebaId=null;
			}
			
			if(numeroExpediente != null && !numeroExpediente.equals("")){
				
				//Falta setear el tipo de objeto
				ExpedienteDTO expediente = new ExpedienteDTO();
				
				expediente.setNumeroExpediente(numeroExpediente);
				datoPruebaDTO.setNombreDato(nombreDato);
				datoPruebaDTO.setNumeroIdentificacion(numIdDato);
				datoPruebaDTO.setFolioCadenaCustodia(rccDato);
				datoPruebaDTO.setDescripcion(descDato);
				datoPruebaDTO.setExpediente(expediente);
				datoPruebaDTO.setDatoPruebaId(datoPruebaId);
				
				pruebaDelegate.registrarActualizarDatoPrueba(datoPruebaDTO,numeroExpediente);
				
				escribir(response, "succes",null);
			}else{
				escribir(response, "el número de expediente no existe",null);
			}
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}
	
	/**
	 * Metodo consulta un datoDePrueba por su id
	 * @author AlejandroGA
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarDatoPrueba(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("EJECUTANDO ACTION CONSULTAR DATO DE PRUEBA");
			log.info("__________________VERIFICANDO LOS PARAMETROS_________________________");
			
			
			Long datoPruebaId = NumberUtils.toLong(request.getParameter("datoPruebaId"),0L);
			log.info("dato prueba id::::::::::::::::::"+datoPruebaId);
			
			
			if(datoPruebaId != 0L){
				
				DatoPruebaDTO datoPruebaDTO = new DatoPruebaDTO();
				
				datoPruebaDTO = pruebaDelegate.consultarDatoPrueba(datoPruebaId);
				
				converter.alias("datoPrueba", DatoPruebaDTO.class);
				String xml = converter.toXML(datoPruebaDTO);
				escribir(response,xml,null);
			}		
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}
	
	
	/**
	 * Metodo para consultar datos de prueba por filtro
	 * @author AlejandroGA
	 * @param request 
	 * @param response
	 * @return Lista de DatoPruebaDTO en formato Grid
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarDatosDePruebaPorFiltro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("EJECUTANDO ACTION CONSULTAR DATOS DE PRUEBA POR FILTRO");
			log.info("__________________VERIFICANDO LOS PARAMETROS_________________________");
			
			String audienciaId = request.getParameter("audienciaId");
			String numeroExpediente = request.getParameter("numeroExpediente");
			 
			log.info("audienciaId:::::::::::::::::::::"+audienciaId);
			log.info("numeroExpediente::::::::::::::::"+numeroExpediente);
			
			DatoPruebaDTO datoPruebaDTO = new DatoPruebaDTO();
			
			if(numeroExpediente != null && !numeroExpediente.equals("")){
				
			
				List<DatoPruebaDTO> listaDatoPrueba=pruebaDelegate.consultarDatosPruebaPorFiltro(datoPruebaDTO,numeroExpediente);
				
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
				
				writer.print("<rows>");
				
				final PaginacionDTO pag = PaginacionThreadHolder.get();
                writer.print("<total>" + pag.getTotalPaginas() + "</total>");
                writer.print("<records>" + pag.getTotalRegistros() + "</records>");

                	for (DatoPruebaDTO datoPrueba : listaDatoPrueba) {
						
							log.info("DatoPrueba"+datoPruebaDTO);
							writer.print("<row id='" + datoPrueba.getDatoPruebaId()+ "'>");
							writer.print("<cell>" + datoPrueba.getNombreDato() + "</cell>");
							writer.print("<cell>" + datoPrueba.getNumeroIdentificacion()+ "</cell>");
							writer.print("<cell>" + (datoPrueba.getFolioCadenaCustodia()!=null ? datoPrueba.getFolioCadenaCustodia():"---") +"</cell>");
							
							//registrado
							writer.print("<cell><![CDATA[" + 
					    			"<input type='radio' id='registrado_"+datoPrueba.getDatoPruebaId()+"' checked='checked' disabled='disabled'>"+
					    			"]]></cell>");
							
							//Aceptado
							if(datoPrueba.getEsAceptado()!= null && datoPrueba.getEsAceptado()==true){
								String idDato=datoPrueba.getDatoPruebaId().toString();
								writer.print("<cell><![CDATA[" + 
										//"<input type='radio' name='rbtnA/R_"+datoPrueba.getDatoPruebaId()+"' onclick='actualizarEstadoDatoPrueba('aceptado_"+datoPrueba.getDatoPruebaId()+"');' id='aceptado_"+datoPrueba.getDatoPruebaId()+"' checked='checked'>"+
						    			"<input type='radio' name='rbtnA/R_"+datoPrueba.getDatoPruebaId()+"' onclick='aceptarDatoPrueba("+idDato+");' id='aceptado_"+datoPrueba.getDatoPruebaId()+"' checked='checked'>"+
						    			"]]></cell>");
							}
							else{
								String idDato=datoPrueba.getDatoPruebaId().toString();
								writer.print("<cell><![CDATA[" + 
						    			"<input type='radio' name='rbtnA/R_"+datoPrueba.getDatoPruebaId()+"' onclick='aceptarDatoPrueba("+idDato+");' id='aceptado_"+datoPrueba.getDatoPruebaId()+"'>"+
						    			"]]></cell>");
							}
							
							//Rechazado
							if(datoPrueba.getEsAceptado()!= null && datoPrueba.getEsAceptado()== false){
								
								String idDato=datoPrueba.getDatoPruebaId().toString();
								writer.print("<cell><![CDATA[" + 
						    			"<input type='radio' name='rbtnA/R_"+datoPrueba.getDatoPruebaId()+"' onclick='rechazarDatoPrueba("+idDato+");' id='rechazado_"+datoPrueba.getDatoPruebaId()+"' checked='checked'>"+
						    			"]]></cell>");
							}
							else{
								log.info("DIBUJA RBTN");
								String idDato=datoPrueba.getDatoPruebaId().toString();
								writer.print("<cell><![CDATA[" + 
						    			"<input type='radio' name='rbtnA/R_"+datoPrueba.getDatoPruebaId()+"' onclick='rechazarDatoPrueba("+idDato+");' id='rechazado_"+datoPrueba.getDatoPruebaId()+"'>"+
						    			"]]></cell>");
							}
						writer.print("</row>");
					}			
				writer.print("</rows>");
				writer.flush();
				writer.close();

			}else{
				escribir(response, "el número de expediente no existe",null);
			}
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}
	
	
	
	/**
	 * Metodo que actualiza el estado (aceptado/rechazado) de un dato de prueba
	 * @author AlejandroGA
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward actualizarEstadoDatoPrueba(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("EJECUTANDO ACTION ACTUALIZAR ESTADO DATO PRUEBA");
			log.info("__________________VERIFICANDO LOS PARAMETROS_________________________");
			
			Long datoPruebaId = NumberUtils.toLong(request.getParameter("datoPruebaId"),0L);
			Boolean esAceptado = Boolean.parseBoolean(request.getParameter("esAceptado"));
			String numeroExpediente = request.getParameter("numeroExpediente");
			String motivoRechazo = request.getParameter("motivoRechazo");
			
			log.info("datoPruebaId::::::::::::::::"+datoPruebaId);
			log.info("esAceptado::::::::::::::::::"+esAceptado);
			log.info("numeroExpediente::::::::::::"+numeroExpediente);
			log.info("motivoRechazo:::::::::::::::"+motivoRechazo);
			
			if(numeroExpediente != null && !numeroExpediente.equals("")){
				
				//Falta setear el tipo de objeto
				ExpedienteDTO expediente = new ExpedienteDTO();
				
				expediente.setNumeroExpediente(numeroExpediente);
				
				//ValorDTO valorMotivoRechazo = new ValorDTO();
				//valorMotivoRechazo.setValor(motivoRechazo);
				
				DatoPruebaDTO datoPruebaDTO = new DatoPruebaDTO(); 
				
				datoPruebaDTO.setDatoPruebaId(datoPruebaId);
				datoPruebaDTO.setEsAceptado(esAceptado);
				datoPruebaDTO.setExpediente(expediente);
				//datoPruebaDTO.setMotivoCancelacion(valorMotivoRechazo);
				
				pruebaDelegate.aceptarCancelarDatoPrueba(datoPruebaDTO);
				
				
				escribir(response, "succes",null);
			}else{
				escribir(response, "el número de expediente no existe",null);
			}
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;
	}
	
	
	/**
	 * Metodo que consulta los datos de prueba ASOCIADOS a un medio de prueba
	 * @author AlejandroGA
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarDatosPruebaAsociadosXMedioPrueba(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("EJECUTANDO ACTION CONSULTAR MEDIOS DE PRUEBA ASOCIADOS AL DATO");
			
			
			log.info("__________________VERIFICANDO LOS PARAMETROS_________________________");
			log.info("id del medio prueba::::::" + request.getParameter("medioPruebaId"));
			Long medioPruebaId = NumberUtils.toLong(request.getParameter("medioPruebaId"), 0L);
			
			if( medioPruebaId > 0L){
							
				List<DatoPruebaDTO> listaDatosRelacionados = pruebaDelegate.consultarDatosPruebaXMedioPrueba(medioPruebaId,true);
				
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
				
				writer.print("<rows>");
				
				int lTotalRegistros=listaDatosRelacionados.size();
				
				writer.print("<records>" + lTotalRegistros + "</records>");
					for (DatoPruebaDTO datoPruebaDTO : listaDatosRelacionados) {
						
							log.info("medioPruebaDTO="+datoPruebaDTO);
						writer.print("<row id='" + datoPruebaDTO.getDatoPruebaId()+ "'>");
							writer.print("<cell>" + datoPruebaDTO.getNombreDato() + "</cell>");
							writer.print("<cell>" + datoPruebaDTO.getNumeroIdentificacion()+ "</cell>");
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
	 * Metodo que consulta las pruebas por numero de expediente
	 * @author AlejandroGA
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarPruebasPorNumeroExpediente(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("EJECUTANDO ACTION CONSULTAR PRUEBAS");
			
			
			log.info("__________________VERIFICANDO LOS PARAMETROS_________________________");
			log.info("numeroExpediente::::::" + request.getParameter("numeroExpediente"));
			String numeroExpediente = request.getParameter("numeroExpediente");
			
			if( numeroExpediente != null && !numeroExpediente.trim().isEmpty()){
							
				List<DatoPruebaDTO> listaDePruebas = pruebaDelegate.consultarPruebasPorNumeroExpediente(numeroExpediente);
				
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
				
				writer.print("<rows>");
				
				final PaginacionDTO pag = PaginacionThreadHolder.get();
                writer.print("<total>" + pag.getTotalPaginas() + "</total>");
                writer.print("<records>" + pag.getTotalRegistros() + "</records>");

					for (DatoPruebaDTO datoPruebaDTO : listaDePruebas) {
						
							log.info("datoPruebaDTO="+datoPruebaDTO);
						writer.print("<row id='" + datoPruebaDTO.getDatoPruebaId()+ "'>");
							writer.print("<cell>" + datoPruebaDTO.getNombreDato() + "</cell>");
							writer.print("<cell>" + datoPruebaDTO.getNumeroIdentificacion()+ "</cell>");
							writer.print("<cell>" + (datoPruebaDTO.getFolioCadenaCustodia()!=null ? datoPruebaDTO.getFolioCadenaCustodia():"---") +"</cell>");

							StringBuffer dropDown = new StringBuffer();
							dropDown.append("<cell>")
									.append("<![CDATA[")
									.append("<select id='relPruebaInvolucrado' style='width: 200px;'>");
							if (datoPruebaDTO.getRelacionesPruebaInvolucrado() != null){
								List<RelacionPruebaInvolucradoDTO> lstRelacionPruebaInvolucradoDTO = datoPruebaDTO.getRelacionesPruebaInvolucrado();
								
								writer.print("<cell>" + lstRelacionPruebaInvolucradoDTO.size() +"</cell>");
								for (RelacionPruebaInvolucradoDTO dto : lstRelacionPruebaInvolucradoDTO){
									if(dto.getInvolucrado() != null){
										InvolucradoDTO involucradoDTO = dto.getInvolucrado();
										dropDown.append("<option value='")
												.append(involucradoDTO.getElementoId())
												.append("'>")
												.append(involucradoDTO.getNombreCompleto())
												.append("</option>");
									}
								}
							}else{
								writer.print("<cell>0</cell>");
								dropDown.append("<option value='0'>No hay involucrados asociados</option>");
							}
							dropDown.append("</select>")
									.append("]]>")
									.append("</cell>");
							writer.print(dropDown.toString());	
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
	 * Metodo que consulta los imputados que no tengan relacion al dato de prueba
	 * @author AlejandroGA
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarImputadosXDatoPrueba(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("EJECUTANDO ACTION CONSULTAR IMPUTADOS POR DATO PRUEBA");
			
			
			log.info("__________________VERIFICANDO LOS PARAMETROS_________________________");
			log.info("idDatoPrueba::::::::" + request.getParameter("idDatoPrueba"));
			log.info("idAudiencia:::::::::" + request.getParameter("idAudiencia"));
			log.info("numeroExpediente::::" + request.getParameter("numeroExpediente"));
			
			Long idDatoPrueba = NumberUtils.toLong(request.getParameter("idDatoPrueba"), 0L);
			Long idAudiencia = NumberUtils.toLong(request.getParameter("idAudiencia"),0L);
			String numeroExpediente = request.getParameter("numeroExpediente");
			
			if( idAudiencia > 0L && idDatoPrueba > 0L && numeroExpediente != null ){
				
				DatoPruebaDTO datoPruebaDTO = new DatoPruebaDTO(); 
				datoPruebaDTO.setDatoPruebaId(idDatoPrueba);
				
				AudienciaDTO audienciaDTO = new AudienciaDTO();
				audienciaDTO.setId(idAudiencia);
				
				List<InvolucradoViewDTO> listaInvolucrados = pruebaDelegate.obtenerImputadosAudienciaSinRelacionConPrueba(audienciaDTO, datoPruebaDTO,numeroExpediente);
				
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();
				
				writer.print("<rows>");
				
				int lTotalRegistros=listaInvolucrados.size();
				
				writer.print("<records>" + lTotalRegistros + "</records>");
					for (InvolucradoViewDTO involucrado : listaInvolucrados) {
						
							log.info("involucrado="+involucrado);
						writer.print("<row id='" + involucrado.getInvolucradoId()+ "'>");
							writer.print("<cell>" + involucrado.getNombreCompleto() + "</cell>");
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
	 * Metodo para relacionar datos de prueba con imputados
	 * @author AlejandroGA
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward asociarImputadoConDatoPrueba(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("EJECUTANDO ACTION RELACIONAR IMPUTADO CON DATO DE PRUEBA");
			log.info("__________________VERIFICANDO LOS PARAMETROS_________________________");
			log.info("idDatoPrueba::::::" + request.getParameter("idDatoPrueba"));
			log.info("iDsImputados:::::::::" + request.getParameter("iDsImputados"));
			
			
			Long idDatoPrueba = NumberUtils.toLong(request.getParameter("idDatoPrueba"), 0L);
			String iDsImputados = request.getParameter("iDsImputados");
			
			
			if(idDatoPrueba > 0L && iDsImputados != null &&  !iDsImputados.equals("")){
				
				DatoPruebaDTO datoPruebaDto = new DatoPruebaDTO();
				
				datoPruebaDto.setDatoPruebaId(idDatoPrueba);
				
				List<InvolucradoDTO> listaImputados = obtenerImputados(iDsImputados);
				
				if(listaImputados != null){
					pruebaDelegate.relacionarPruebaAInvolucrado(datoPruebaDto, listaImputados);
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
	 * Metodo utilizado para obtener una lista de involucradosDTO a partir de su
	 * id, localizado en una cadena
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	private List<InvolucradoDTO> obtenerImputados(String iDsImputados)throws IOException{
		
		List<InvolucradoDTO> listaImputados = new ArrayList<InvolucradoDTO>();
		
		try {
			
			if(iDsImputados != null){
				String listaIds[] = iDsImputados.split(",");
				for(String id : listaIds){
					InvolucradoDTO involucradoDTO = new InvolucradoDTO();
					involucradoDTO.setElementoId(Long.parseLong(id));	
					listaImputados.add(involucradoDTO);
				}
			}
			
		} catch (Exception e) {
			log.info(e.getCause(), e);
		}
		return listaImputados;
	}
	
}

package mx.gob.segob.nsjp.web.catalogo.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.catalogo.Catalogos;
import mx.gob.segob.nsjp.comun.enums.catalogo.TipoDiscriminante;
import mx.gob.segob.nsjp.comun.enums.configuracion.Parametros;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.catalogo.AdministrarCatalogoDelegate;
import mx.gob.segob.nsjp.delegate.catalogo.CatDiscriminanteDelegate;
import mx.gob.segob.nsjp.delegate.catalogo.CatalogoDelegate;
import mx.gob.segob.nsjp.delegate.catalogo.DistritoDelegate;
import mx.gob.segob.nsjp.delegate.configuracion.ConfiguracionDelegate;
import mx.gob.segob.nsjp.delegate.parametro.ParametroDelegate;
import mx.gob.segob.nsjp.dto.ParametroDTO;
import mx.gob.segob.nsjp.dto.audiencia.SalaAudienciaDTO;
import mx.gob.segob.nsjp.dto.audiencia.SalaJAVSDTO;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDelitoDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDistritoDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatUIEspecializadaDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoCompletoDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.configuracion.ConfInstitucionDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;
import mx.gob.segob.nsjp.web.catalogo.form.CatalogosForm;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

public class AdministrarCatalogosAction extends GenericAction {

	private static final Logger logger = Logger.getLogger(AdministrarCatalogosAction.class);
	
	@Autowired
	private AdministrarCatalogoDelegate administrarCatalogoDelegate;
	@Autowired
	private CatalogoDelegate catalogoDelegate;
	@Autowired
	private ConfiguracionDelegate  configuracionDelegate ;
	@Autowired
	private DistritoDelegate distritoDelegate;
	@Autowired
	private CatDiscriminanteDelegate catDiscriminanteDelegate;
	@Autowired
	private ParametroDelegate parametroDelegate;
	
		
	@SuppressWarnings("unchecked")
	public ActionForward administrarCatalogos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			CatalogosForm forma = (CatalogosForm)form;
			List<CatalogoDTO> catalogos = administrarCatalogoDelegate.obtenerListaCatalogos();
			Collections.sort(catalogos);  
			forma.setCatalogos(catalogos);
			logger.info("Los catalogos del sistema se deben de ordenar ascendentemente");

			request.setAttribute("CatalogosForm", forma);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return mapping.findForward("success");
	}
	
	public ActionForward construirGridCatalogos(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			CatalogosForm forma = (CatalogosForm)form;
			logger.info("IidCatalogo_Admin:: "+forma.getIdCatalogo());
			if(forma.getIdCatalogo()!=null)
			{
				CatalogoCompletoDTO catalogoCompleto = administrarCatalogoDelegate.obtenerCatalogo(forma.getIdCatalogo());
				forma.setEstructuraCatalogo(catalogoCompleto.getColumnas());
				request.setAttribute("CatalogosForm", forma);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return mapping.findForward("success");
	}
	
	public ActionForward consultarValoresCatalogo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			logger.info("idCatalogo_mel:: "+request.getParameter("idCatalogo"));
			Long idCatalogo = NumberUtils.toLong(request.getParameter("idCatalogo"), 0L);
			CatalogoCompletoDTO valores = administrarCatalogoDelegate.obtenerCatalogo(idCatalogo);
			
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("CatalogoDTO", CatalogoDTO.class);
			String xml = converter.toXML("valores_catalogo_admin:: "+valores);
			logger.info("catalogos_admin_mel:: "+xml);
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.print("<rows>");		
			
			final PaginacionDTO pag = PaginacionThreadHolder.get();
            if (pag != null && pag.getTotalRegistros() != null) {
                writer.print("<total>" + pag.getTotalPaginas() + "</total>");
                writer.print("<records>" + pag.getTotalRegistros() + "</records>");
            } else {
                writer.print("<total>0</total>");
                writer.print("<records>0</records>");
            }										
			
			for(CatalogoDTO catalogo : valores.getValores()){			
				writer.print("<row id='"+catalogo.getClave()+"'>"); logger.info("id del grid::::"+catalogo.getClave());
				if ((catalogo.getValor()!=null) && (!catalogo.getValor().equals("HIDE_CAMPO"))) {
				    writer.print("<cell>"+catalogo.getValor()+"</cell>");
				}
				if(catalogo.getValoresExras() != null){
					for (ValorDTO valor : catalogo.getValoresExras()) {
						if(valor.getValorPadre() != null){
							writer.print("<cell>"+valor.getValorPadre().getValor()+"</cell>");
						}else{
							writer.print("<cell>"+valor.getValor()+"</cell>");
						}
					}
				}
				writer.print("</row>");
			}
			
			writer.print("</rows>");
			writer.flush();
			writer.close();
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	public ActionForward consultarValoresCatalogoAgencias(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			Long idCatalogo = NumberUtils.toLong(request.getParameter("idClaveCatalogo"));
			logger.info("id de la consulta del Catalogo:::::"+idCatalogo);
			CatalogoCompletoDTO valores = administrarCatalogoDelegate.obtenerCatalogo(idCatalogo);
			//catalogoCompletoDTO valores catalogoDTO valoresExras valorDTO
			converter.alias("listaCatalogo", java.util.List.class);
			converter.alias("catalogoCompletoDTO", CatalogoCompletoDTO.class);
			
			//converter.alias("valores");
			converter.alias("catalogoDTO", CatalogoDTO.class);
			//converter.alias("valoresExras");
			converter.alias("valorDTO", ValorDTO.class);
			
			String xml = converter.toXML(valores);
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	public ActionForward consultaValorDeCatalogo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
            logger.debug("CatalogosForm forma = (CatalogosForm)form;");
			CatalogosForm forma = (CatalogosForm)form;
            logger.debug("List<CatalogoDTO> catalogoPadre =null;");
			List<CatalogoDTO> catalogoPadre =null;
            logger.debug("CatalogoDTO catalogo ;");
			CatalogoDTO catalogo ;
            logger.debug("boolean esNuevo = false;");
			boolean esNuevo = false;
            logger.debug("Map<String, List<CatalogoDTO>> catalogos = new HashMap<String, List<CatalogoDTO>>();");
			Map<String, List<CatalogoDTO>> catalogos = new HashMap<String, List<CatalogoDTO>>();
            logger.debug("if(forma.getCatalogo().getClave() == null && forma.getIdCatalogo() != null)"+forma.getIdCatalogo());
			if(forma.getCatalogo().getClave() == null && forma.getIdCatalogo() != null){
                logger.debug("catalogo = administrarCatalogoDelegate.obtenerDefinicion(forma.getIdCatalogo());");
				catalogo = administrarCatalogoDelegate.obtenerDefinicion(forma.getIdCatalogo());
                logger.debug("esNuevo = true;");
				esNuevo = true;
                logger.debug("}else{");
			}else{
                logger.debug("CatalogoDTO input = new CatalogoDTO();");
			    CatalogoDTO input = new CatalogoDTO();
                logger.debug("input.setClave(forma.getCatalogo().getClave());");
			    input.setClave(forma.getCatalogo().getClave());
                logger.debug("input.setIdCatalogo(forma.getIdCatalogo());");
			    input.setIdCatalogo(forma.getIdCatalogo());
                logger.debug("catalogo = administrarCatalogoDelegate.obtenerValor(input);");
				catalogo = administrarCatalogoDelegate.obtenerValor(input);
			}
				logger.debug("for (ValorDTO valor : catalogo.getValoresExras())");
				for (ValorDTO valor : catalogo.getValoresExras()) {
                    logger.debug("if(valor.getValorPadre() != null){");
					if(valor.getValorPadre() != null){
                        logger.debug("catalogoPadre = catalogoDelegate.recuperarCatalogo(Catalogos.getEnum(valor.getCatalogoPadre()));");
						catalogoPadre = catalogoDelegate.recuperarCatalogo(Catalogos.getEnum(valor.getCatalogoPadre()));
                        logger.debug("catalogos.put(valor.getNombreCampo(), catalogoPadre);");
						catalogos.put(valor.getNombreCampo(), catalogoPadre);
					}
				}
			logger.debug("forma.setCatalogo(catalogo);");
			forma.setCatalogo(catalogo);
            logger.debug("request.setAttribute(\"esNuevo\", esNuevo);");
			request.setAttribute("esNuevo", esNuevo);
            logger.debug("request.setAttribute(\"mapaCatalogos\", catalogos);");
			request.setAttribute("mapaCatalogos", catalogos);
            logger.debug("request.setAttribute(\"CatalogosForm\", forma);");
			request.setAttribute("CatalogosForm", forma);
            logger.debug("return mapping.findForward(\"success\");");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return mapping.findForward("success");
	}
	
	
	/**
	 * Metodo que permite consultar un valor del catalogo de delitos
	 * por medio de su id. Si el parametro esNuevo = false
	* significa que es un valor nuevo y s?lo redirige a la p?gina
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultaValorDeCatalogoDelito(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {

			logger.info("EJECUTANDO CONSULTA DE VALOR DE CATALOGO DELITOS");

			CatalogosForm forma = (CatalogosForm) form;
			logger.info(":::::::::::::::::::::VERIFICACNDO PARAMETROS:::::::::::::::::::::::::");
			logger.info("forma.getCatalogo().getClave()="
					+ forma.getCatalogo().getClave());
			logger.info("forma.getIdCatalogo()=" + forma.getIdCatalogo());
			logger.info("esNuevo=" + request.getParameter("esNuevo"));
			
			Boolean esNuevo = true;
			CatDelitoDTO catDelito = null;
			
			if(request.getParameter("esNuevo") != null){
				esNuevo =  Boolean.parseBoolean(request.getParameter("esNuevo"));
			}
			
			if(esNuevo == false){
				if (forma.getCatalogo() != null
						&& forma.getCatalogo().getClave() != null
						&& forma.getIdCatalogo() != null) {
					
					catDelito = catalogoDelegate.consultarCatDelitoPorId(forma
							.getCatalogo().getClave());
				}
			}

			request.setAttribute("catDelito",catDelito);
			request.setAttribute("esNuevo",esNuevo);
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
		}
		return mapping.findForward("success");
	}

	/**
	 * Metodo que permite consultar un valor del catalogo de Sala Audiencia
	 * por medio de su id. Si el parametro esNuevo = false
	 * significa que es un valor nuevo y s?lo redirige a la p?gina
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultaValorDeCatalogoSalaAudiencia(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {

			SalaAudienciaDTO salaAudienciaDto = new SalaAudienciaDTO();
			
			CatalogosForm forma = (CatalogosForm) form;

			Boolean esNuevo = true;	
			if(request.getParameter("esNuevo") != null){esNuevo =  Boolean.parseBoolean(request.getParameter("esNuevo"));}

			Long idSala = NumberUtils.toLong(request.getParameter("idSala"), 0L);

			salaAudienciaDto.setSalaAudienciaId(idSala);

			if(idSala != 0L){
				salaAudienciaDto = catalogoDelegate.consultarSalaAudiencia(salaAudienciaDto);						
			}

			request.setAttribute("SalaAudienciaDTO",salaAudienciaDto);
			request.setAttribute("esNuevo",esNuevo);
			
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
		}
		return mapping.findForward("success");
	}

	/**
	 *Funcion que permite Guardar/Actualizar el catDelito 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward guardarValorEnCatalogoDelito(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {

			logger.info("EJECUTANDO GUARDAR/CONSULTAR  VALOR DE CATALOGO DELITOS");

			logger.info(":::::::::::::::::::::VERIFICACNDO PARAMETROS:::::::::::::::::::::::::");
			logger.info("delId=" + request.getParameter("delitoId"));
			logger.info("cveDel=" + request.getParameter("cveDel"));
			logger.info("nombDel=" + request.getParameter("nombDel"));
			logger.info("esGraveDel=" + request.getParameter("esGraveDel"));
			logger.info("esAccPenalPrivDel=" + request.getParameter("esAccPenalPrivDel"));
			//logger.info("jerarquiaOrgDelito=" + request.getParameter("jerarquiaOrgDelito"));
			logger.info("jerarquiaUIEdelito=" + request.getParameter("jerarquiaUIEdelito"));
			logger.info("penaMinA=" + request.getParameter("penaMinA"));
			logger.info("penaMinM=" + request.getParameter("penaMinM"));
			logger.info("penaMinD=" + request.getParameter("penaMinD"));
			logger.info("penaMaxA=" + request.getParameter("penaMaxA"));
			logger.info("penaMaxM=" + request.getParameter("penaMaxM"));
			logger.info("penaMaxD=" + request.getParameter("penaMaxD"));
			
			String mensaje = "exito";
			
			Long  delitoId = NumberUtils.toLong(request.getParameter("delitoId"), 0L);
			String cveDelito = request.getParameter("cveDel");
			String nombDelito = request.getParameter("nombDel");
			Boolean esGrave = Boolean.parseBoolean(request.getParameter("esGraveDel"));
			Boolean esAccPenPriv = Boolean.parseBoolean(request.getParameter("esAccPenalPrivDel"));
			
			//Long jerarquiaId = NumberUtils.toLong(request.getParameter("jerarquiaOrgDelito"), 0L);
			Long jerarquiaUIEId = NumberUtils.toLong(request.getParameter("jerarquiaUIEdelito"), 0L);
			
			Long penaMinAnioDel = NumberUtils.toLong(request.getParameter("penaMinA"), 0L);
			Long penaMinMesDel = NumberUtils.toLong(request.getParameter("penaMinM"), 0L);
			Long penaMinDiaDel = NumberUtils.toLong(request.getParameter("penaMinD"), 0L);
			
			Long penaMaxAnioDel = NumberUtils.toLong(request.getParameter("penaMaxA"), 0L);
			Long penaMaxMesDel = NumberUtils.toLong(request.getParameter("penaMaxM"), 0L);
			Long penaMaxDiaDel = NumberUtils.toLong(request.getParameter("penaMaxD"), 0L);
			
			CatDelitoDTO catDelitoDto = new CatDelitoDTO();
			
			catDelitoDto.setCatDelitoId(delitoId);
			catDelitoDto.setClaveDelito(cveDelito);
			catDelitoDto.setNombre(nombDelito);
			catDelitoDto.setEsGrave(esGrave);
			catDelitoDto.setEsAccionPenPriv(esAccPenPriv);
			
			CatUIEspecializadaDTO catUnidadInvEspDto = new CatUIEspecializadaDTO();
			catUnidadInvEspDto.setCatUIEId(jerarquiaUIEId);
			catDelitoDto.setUnidadIEspecializada(catUnidadInvEspDto);
			
			catDelitoDto.setPenaMinimaAnios(penaMinAnioDel);
			catDelitoDto.setPenaMinimaMeses(penaMinMesDel);
			catDelitoDto.setPenaMinimaDias(penaMinDiaDel);
			
			catDelitoDto.setPenaMaximaAnios(penaMaxAnioDel);
			catDelitoDto.setPenaMaximaMeses(penaMaxMesDel);
			catDelitoDto.setPenaMaximaDias(penaMaxDiaDel);
			
			catDelitoDto = catalogoDelegate.guardarActualizarCatDelito(catDelitoDto);
	
			if(catDelitoDto==null){
				mensaje = "fallo";
			}
			
			logger.info("catDelitoDto guardado/Actualizado:::::::::::::::::::::::::::::::::::::::::::::::::::=" + catDelitoDto);
			logger.info("mensaje::::::::"+mensaje);
			
			
			escribirRespuesta(response, mensaje);			
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
			escribirRespuesta(response,"");
		}
		return null;
	}
	
	/**
	 *Funci?n que permite Guardar/Actualizar catSala 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward guardarValorEnCatalogoSalaAudiencia(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
        try {
						
			Long estado = 0L;
			
			String nombreSala = request.getParameter("nombreSala");
			String domicilioSala = request.getParameter("domicilioSala");
			String ubicacionSala = request.getParameter("ubicacionSala");
			String eliminaSala = request.getParameter("eliminaSala");

			Long  claveSala = NumberUtils.toLong(request.getParameter("claveSala"), 0L);
			SalaAudienciaDTO salaAudienciaDto = new SalaAudienciaDTO();
						
			Long  claveSalaJAVS = NumberUtils.toLong(request.getParameter("claveSalaJAVS"), 0L);
			Long  catDiscriminanteId = NumberUtils.toLong(request.getParameter("tribunal"), 0L);
			Long  claveFuncionario = NumberUtils.toLong(request.getParameter("funcionario"), 0L);
			
			String direccionIPJAVS = request.getParameter("direccionJAVS");
			String usuarioJAVS = request.getParameter("usuarioJAVS");
			String passwordJAVS = request.getParameter("passwordJAVS");
					
			SalaJAVSDTO salaJAVSDto = new SalaJAVSDTO();

			if(direccionIPJAVS != null && usuarioJAVS != null && passwordJAVS != null){
				salaJAVSDto.setDireccionIP(direccionIPJAVS);
				salaJAVSDto.setUsuarioJAVS(usuarioJAVS);
				salaJAVSDto.setPassword(passwordJAVS);		
				salaJAVSDto.setSalaJAVSId(claveSalaJAVS);
				salaAudienciaDto.setSalaJAVSDTO(salaJAVSDto);
			}
					
			CatDiscriminanteDTO catDiscriminanteDto = new CatDiscriminanteDTO();
			catDiscriminanteDto.setCatDiscriminanteId(catDiscriminanteId);
					
			FuncionarioDTO funcionarioDto = new FuncionarioDTO();
			funcionarioDto.setClaveFuncionario(claveFuncionario);
			funcionarioDto.setDiscriminante(catDiscriminanteDto);								
			
			salaAudienciaDto.setNombreSala(nombreSala);
			salaAudienciaDto.setDomicilioSala(domicilioSala);
			salaAudienciaDto.setUbicacionSala(ubicacionSala);
			salaAudienciaDto.setEsActivo(true);

			if(claveSala != 0){
				salaAudienciaDto.setSalaAudienciaId(claveSala);
			}
			
			salaAudienciaDto.setFuncionarioDTO(funcionarioDto);
			salaAudienciaDto.setCatDiscriminanteDTO(catDiscriminanteDto);
			
			if(eliminaSala != null){
				if(eliminaSala.equals("SI")){
					salaAudienciaDto.setEsActivo(false);
				}
			}
			
			salaAudienciaDto = catalogoDelegate.guardarActualizarSalaAudiencia(salaAudienciaDto);
			estado = salaAudienciaDto.getSalaAudienciaId();
			
			String xml = converter.toXML(estado);
			response.setContentType("text/xml");
			
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();	               	
        	
        } catch (Exception e) {
            logger.error(e.getMessage(), e);               	        	
        }   
        
        return null;
	}	

	public ActionForward eliminarValorEnCatalogoDelito(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {

			logger.info("EJECUTANDO ELMINAR  VALOR DE CATALOGO DELITOS");

			Long  catDelitoId = NumberUtils.toLong(request.getParameter("delitoId"), 0L);
			Long resultado = 0L;
			
			if(catDelitoId != 0L){
				resultado = catalogoDelegate.eliminarCatDelito(catDelitoId);
			}				
			
			String xml = converter.toXML(resultado);
			response.setContentType("text/xml");
			
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();	               	
				
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
		
	
	public ActionForward guardarValorEnCatalogo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
		    
			Long clave = NumberUtils.toLong(request.getParameter("clave"), 0);
			Long idCampo = NumberUtils.toLong(request.getParameter("idCampo"), 0);
			String value = request.getParameter("valor");
			String valoresExtra = request.getParameter("valorExtra");
			Long idCatalogo = Long.decode(request.getParameter("idCatalogo"));
			CatalogoDTO input = new CatalogoDTO();
			input.setClave(clave);
			input.setCampoId(idCampo);
			input.setValor(value);
			int institucionActual = 0;
			ConfInstitucionDTO loInstitucion = configuracionDelegate.consultarInstitucionActual();
			
			if(loInstitucion != null && loInstitucion.getConfInstitucionId() != null)
				institucionActual = loInstitucion.getConfInstitucionId().intValue();
			logger.info("La institucion actual es " + institucionActual + " by gama");
			
			if(valoresExtra!=null && !valoresExtra.isEmpty()){
				StringTokenizer st = new StringTokenizer(valoresExtra, "|");
				while(st.hasMoreTokens()){
					ValorDTO valor = null;
					String[] atributos;
					atributos = st.nextToken().split("#");
					valor = new ValorDTO();
					valor.setIdCampo(NumberUtils.toLong(atributos[0]));
					valor.setNombreCampo(atributos[1]);
					valor.setValor(atributos[2]);
					input.addValorExtra(valor);
				}
				//Para el catalogo de Agencias
				if(idCatalogo == Catalogos.AGENCIAS.ordinal()){
					//Se configura la agencia/tribunal dependiendo de la Institucion
					ValorDTO valor = null;
					valor = new ValorDTO();
					valor.setIdCampo(0L);
					valor.setNombreCampo("Clasificacion");
					
					switch (institucionActual){
						case 1://Instituciones.PGJ.ordinal()
							valor.setValor((TipoDiscriminante.AGENCIA.ordinal()) +"");
							break;
						case 3://Instituciones.PJ.ordinal()
							valor.setValor((TipoDiscriminante.TRIBUNAL.ordinal()) +"");
							break;
						default: 							
							valor.setValor((TipoDiscriminante.FANTASMA.ordinal()) +"");
					}
					input.addValorExtra(valor);
				}
				
				
			}
			input.setIdCatalogo(idCatalogo);
			//Si se trata del catalogo de distritos
			if(idCatalogo == Catalogos.DISTRITOS.ordinal()){
				if(institucionActual == Instituciones.SSP.getValorId() || institucionActual == Instituciones.DEF.getValorId()){
					CatDistritoDTO loCatDistritoDTO = new CatDistritoDTO(); 
					loCatDistritoDTO.setCatDistritoId(input.getClave()==0?null:input.getClave());
					loCatDistritoDTO.setClaveDistrito(input.getValor());					
					
					if(input.getValoresExras().get(0).getNombreCampo().equals("NombreDist"))
						loCatDistritoDTO.setNombreDist(input.getValoresExras().get(0).getValor());
					else
						loCatDistritoDTO.setNombreDist(input.getValoresExras().get(1).getValor());

					distritoDelegate.registrarDistritoConFantasma(loCatDistritoDTO);
					
				}else{
					if(input.getClave() > 0){
						administrarCatalogoDelegate.actualizarValor(input);
					}else{			
						administrarCatalogoDelegate.registrarValor(input);
					}
				}
				
			}else{
				if(input.getClave() > 0){
					administrarCatalogoDelegate.actualizarValor(input);
				}else{			
					administrarCatalogoDelegate.registrarValor(input);
				}
			}
				
			
			
			String mensaje = "exito";
			String xml = converter.toXML(mensaje);			
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			String mensaje = "fallo";
			String xml = converter.toXML(mensaje);			
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		}
		return null;
	}
	
	public ActionForward eliminarValorDeCatalogo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			logger.info("EJECUTANDO ELMINAR VALOR DE CATALOGOS");
			logger.info(":::::::::::::::::::::VERIFICACNDO PARAMETROS:::::::::::::::::::::::::");
			logger.info("clave:::::::::::::::::::::::::"+request.getParameter("clave"));
			logger.info("idCatalogo::::::::::::::::::::"+request.getParameter("idCatalogo"));
			
			Long clave = NumberUtils.toLong(request.getParameter("clave"), 0);
			Long idCatalogo = NumberUtils.toLong(request.getParameter("idCatalogo"), 0);
			CatalogoDTO catalogo = new CatalogoDTO();
			
			if(clave > 0){
				catalogo.setClave(clave);
				if(idCatalogo > 0){
					catalogo.setIdCatalogo(idCatalogo);
				}
				administrarCatalogoDelegate.eliminarValor(catalogo);
			}
			
			String mensaje = "exito";
			String xml = converter.toXML(mensaje);			
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			String mensaje = "fallo";
			String xml = converter.toXML(mensaje);			
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		}
		return null;
	}
	
	
	
	public ActionForward modificarCatDiscriminante(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			   
			 String idCatalogo = request.getParameter("idCatalogo");
			 logger.info("imprime idCatalogo="+idCatalogo);
			 String clave = request.getParameter("clave");
			 logger.info("imprime clave="+clave);
			 String idCampo = request.getParameter("idCampo");
			 logger.info("imprime idCampo="+idCampo);
			 String nombre = request.getParameter("nombre");
			 logger.info("imprime nombre="+nombre);
			
			CatDiscriminanteDTO catDiscriminanteDTO = new CatDiscriminanteDTO();
			CatDistritoDTO catDistritoDTO = new CatDistritoDTO();
			
			catDiscriminanteDTO.setCatDiscriminanteId(NumberUtils.toLong(idCatalogo));
			catDiscriminanteDTO.setClave(clave);
			catDistritoDTO.setCatDistritoId(NumberUtils.toLong(idCampo));
			catDiscriminanteDTO.setDistrito(catDistritoDTO);
			catDiscriminanteDTO.setNombre(nombre);
			
			
			logger.info("imprime tipo="+getUsuarioFirmado(request).getFuncionario().getDiscriminante().getTipo());
			catDiscriminanteDTO.setTipo(getUsuarioFirmado(request).getFuncionario().getDiscriminante().getTipo());
			
			
			
			logger.info("antes del Delegate imprime catDiscriminanteDTO="+catDiscriminanteDTO);
			Long res = distritoDelegate.registrarDiscriminante(catDiscriminanteDTO);
			
			logger.info("despues del Delegate imprime res="+res);
			
			String xml = converter.toXML(res);			
			response.setContentType("text/xml");
			PrintWriter pw = response.getWriter();
			pw.print(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	/**
	 * Metodo utilizado para realizar lipiar el grid de registros
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward limpiarGrid(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {	
					
		try {
			
			Integer columns = NumberUtils.toInt(request.getParameter("numeroColumnas"),0);			
			if (logger.isDebugEnabled()) {
				logger.debug(":::::EJECUTANDO ACTION LIMPIAR GRID");
				logger.debug(":::::No de Columnas:"+columns);
				
			}

			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.print("<rows>");
			
			writer.print("<total>0</total>");
			writer.print("<records>0</records>");
			
			writer.print("</rows>");
			writer.flush();
			writer.close();
			
		} catch (Exception e) {		
			logger.info(e.getCause(),e);
			
		}
		return null;
	}
	
	
	public ActionForward eliminarCatalogo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
    	Long respuesta = -1L;

		try{
			Long idCatalogo= NumberUtils.toLong(request.getParameter("idCatalogo"), 0L);
			Long clave= NumberUtils.toLong(request.getParameter("idValor"), 0L);
			
			if(clave > 0){
				Catalogos cat = Catalogos.values()[idCatalogo.intValue()];    	
		    	
				switch (cat) {
					case DISTRITOS:					
						respuesta = distritoDelegate.eliminarDistrito(new CatDistritoDTO(clave));			
						break;					
					case AGENCIAS:
						respuesta = catDiscriminanteDelegate.eliminarAgencia(new CatDiscriminanteDTO(clave));
						break;
					}
			}
	    		
		}catch (Exception e) {		
			logger.info(e.getCause(),e);				
		}finally{
			converter.alias("respuesta",String.class);
			escribirRespuesta(response,converter.toXML(respuesta.toString()));
			
		}
		return null;		
	}
	
	
	
	public ActionForward consultarParametro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String respuesta = "0";

		try{
			Long idParametro= NumberUtils.toLong(request.getParameter("idParametro"), -1L);
						
			if(idParametro >= 0){
				Parametros parametro = Parametros.values()[idParametro.intValue()];
				ParametroDTO loParametroDTO = parametroDelegate.consultarParametro(parametro);
				if(loParametroDTO != null && loParametroDTO.getValor() != null){
					respuesta = loParametroDTO.getValor().toString();
				}
				converter.alias("respuesta",String.class);
				escribirRespuesta(response,converter.toXML(respuesta.toString()));

			}
	    		
		}catch (Exception e) {		
			logger.info(e.getCause(),e);				
		}
		return null;		
	}
	
	
}

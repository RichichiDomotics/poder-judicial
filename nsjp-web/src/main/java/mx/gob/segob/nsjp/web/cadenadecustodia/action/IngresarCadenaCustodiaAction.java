/**
 * Nombre del Programa 		: IngresarCadenacustodiaAction.java
 * Autor                     : ArmandoCT
 * Compania                  : Ultrasist
 * Proyecto                  : NSJP                    Fecha: 25/julio/2011
 * Marca de cambio        	: N/A
 * Descripcion General    	: Clase Action para ingresar la cadena de custodia
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
package mx.gob.segob.nsjp.web.cadenadecustodia.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.evidencia.EstatusEvidencia;
import mx.gob.segob.nsjp.comun.enums.objeto.Objetos;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.cadenadecustodia.CadenaDeCustodiaDelegate;
import mx.gob.segob.nsjp.delegate.eslabon.EslabonDelegate;
import mx.gob.segob.nsjp.delegate.evidencia.EvidenciaDelegate;
import mx.gob.segob.nsjp.delegate.expediente.ExpedienteDelegate;
import mx.gob.segob.nsjp.delegate.funcionario.FuncionarioDelegate;
import mx.gob.segob.nsjp.delegate.objeto.ObjetoDelegate;
import mx.gob.segob.nsjp.delegate.usuario.UsuarioDelegate;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.evidencia.EslabonDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.objeto.AeronaveDTO;
import mx.gob.segob.nsjp.dto.objeto.AnimalDTO;
import mx.gob.segob.nsjp.dto.objeto.ArmaDTO;
import mx.gob.segob.nsjp.dto.objeto.DocumentoOficialDTO;
import mx.gob.segob.nsjp.dto.objeto.EmbarcacionDTO;
import mx.gob.segob.nsjp.dto.objeto.EquipoComputoDTO;
import mx.gob.segob.nsjp.dto.objeto.ExplosivoDTO;
import mx.gob.segob.nsjp.dto.objeto.JoyaDTO;
import mx.gob.segob.nsjp.dto.objeto.NumerarioDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.dto.objeto.ObraArteDTO;
import mx.gob.segob.nsjp.dto.objeto.SustanciaDTO;
import mx.gob.segob.nsjp.dto.objeto.TelefoniaDTO;
import mx.gob.segob.nsjp.dto.objeto.VehiculoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;
import mx.gob.segob.nsjp.web.cadenadecustodia.form.CadenaCustodiaForm;
import mx.gob.segob.nsjp.web.cadenadecustodia.form.EslabonCadenaCustodiaForm;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Clase Action para ingresar cadena de custodia.
 * 
 * @version 1.0
 * @author ArmandoCT
 * 
 */
public class IngresarCadenaCustodiaAction extends GenericAction {

	/* Log de clase */
	private static final Logger log = Logger
			.getLogger(IngresarCadenaCustodiaAction.class);

	@Autowired
	private CadenaDeCustodiaDelegate cadenaCustodiaDelegate;
	
	@Autowired
	private ExpedienteDelegate expedienteDelegate;
	
	@Autowired
	private ObjetoDelegate objetoDelegate;
	
	@Autowired
	private EvidenciaDelegate evidenciaDelegate;
	
	@Autowired
	private EslabonDelegate eslabonDelegate;
	
	@Autowired
	private CadenaDeCustodiaDelegate cadenaDeCustodiaDelegate;
	
	@Autowired
	private UsuarioDelegate usuarioDelegate;
	
	@Autowired
	private FuncionarioDelegate funcionarioDelegate;

	/**
	 * Metodo utilizado para guardar una cadena de custodia
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward ingresarCadenaCustodia(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {

			log.info("ejecutando Action guardar Cadena Custodia");
			CadenaCustodiaForm forma = (CadenaCustodiaForm) form;
			
			log.info("FORMA CADENA CUSTODIA:::::::::::::::::::::::");
			
			/*** Revisamos que los campos no sean nulos ****/		    
			if(StringUtils.isEmpty(forma.getCadenaDeCustodiaId()))
			{
				forma.setCadenaDeCustodiaId(null);
				log.debug("Id cadena custodia es null.");
			}
			if(StringUtils.isEmpty(forma.getFechaIntercambio()))
			{
				forma.setFechaIntercambio("");
				log.debug("fecha de intercambio es null.");
			}
			
			if(StringUtils.isEmpty(forma.getObservacion()))
			{
				forma.setObservacion("");
				log.debug("observacion es null.");
			}
			if(StringUtils.isEmpty(forma.getQuienEntrega()))
			{
				forma.setQuienEntrega("");
				log.debug("quien entrega es null.");
			}
			if(StringUtils.isEmpty(forma.getQuienRecibe()))
			{
				forma.setQuienRecibe("");
				log.debug("quien recibe es null.");
			}
			if(StringUtils.isEmpty(forma.getQuienEmbala()))
			{
				forma.setQuienEmbala("");
				log.debug("quien embala es null.");
			}
			if(StringUtils.isEmpty(forma.getQuienTransporta()))
			{
				forma.setQuienTransporta("");
				log.debug("quien transporta es null.");
			}
			if(StringUtils.isEmpty(forma.getFechaLevantamiento()))
			{
				forma.setFechaLevantamiento("");
				log.debug("fecha de levantamiento es null.");
			}
			if(StringUtils.isEmpty(forma.getFolio()))
			{
				forma.setFolio("");
				log.debug("folio es null.");
			}
			if(StringUtils.isEmpty(forma.getHoraIntercambio()))
			{
				forma.setHoraIntercambio("");
				log.debug("hora intercambio es null");
			}
			if(StringUtils.isEmpty(forma.getHoraLevantamiento()))
			{
				forma.setHoraLevantamiento("");
				log.debug("hora levantamiento es null");
			}
			if(StringUtils.isEmpty(forma.getInstitutionTraslada()))
			{
				forma.setInstitutionTraslada("");
				log.debug("institucion traslada es null");
			}
			if(StringUtils.isEmpty(forma.getInstitutionEmbala()))
			{
				forma.setInstitutionEmbala("");
				log.debug("institucion embala es null");
			}
			/*** FIN Revisamos que los campos no sean nulos ****/

					
			//encapsulamos la informacion del expediente
			ExpedienteDTO expedienteDTO=super.getExpedienteTrabajo(request, forma.getNumeroExpediente());
			
			//encapsulamos la informacion de la cadena de custodia
			CadenaDeCustodiaDTO custodiaDTO=new CadenaDeCustodiaDTO();
			if(forma.getCadenaDeCustodiaId()!=null)
			{
				custodiaDTO.setCadenaDeCustodiaId(Long.parseLong(forma.getCadenaDeCustodiaId()));
			}
			else
			{
				custodiaDTO.setCadenaDeCustodiaId(null);
			}
		    custodiaDTO.setFechaIntercambio(DateUtils.obtener(forma.getFechaIntercambio(),forma.getHoraIntercambio()));
		    
		    custodiaDTO.setObservacion(forma.getObservacion());
		    custodiaDTO.setQuienEntrega(forma.getQuienEntrega());
		    custodiaDTO.setQuienRecibe(forma.getQuienRecibe());
		    custodiaDTO.setQuienEmbala(forma.getQuienEmbala());
		    custodiaDTO.setQuienTransporta(forma.getQuienTransporta());
		    custodiaDTO.setFechaLevantamiento(DateUtils.obtener(forma.getFechaLevantamiento(),forma.getHoraLevantamiento()));
		    custodiaDTO.setFolio(forma.getFolio());
		    
		    custodiaDTO.setInstitucionEmbalaje(forma.getInstitutionEmbala());
		    custodiaDTO.setInstitucionTraslado(forma.getInstitutionTraslada());
			//Mandamos guardar la cadena de custodia a la BD
		    custodiaDTO=cadenaCustodiaDelegate.guardarCadenaCustodia(custodiaDTO, expedienteDTO);

			//mandamos la respuesta a la vista
			if(custodiaDTO!=null && custodiaDTO.getCadenaDeCustodiaId()!=null)
			{
				converter.alias("custodiaDTO", CadenaDeCustodiaDTO.class);
				String xml = converter.toXML(custodiaDTO);
				log.info(xml);
				escribirRespuesta(response, xml);
			}
			else
			{
				custodiaDTO.setCadenaDeCustodiaId(0L);
				converter.alias("custodiaDTO", CadenaDeCustodiaDTO.class);
				String xml = converter.toXML(custodiaDTO);
				escribirRespuesta(response, xml);
			}
			log.info("Termina ejecucion Action guardar hecho - FIN ");
		} catch (NSJPNegocioException e) {
			log.error(e.getMessage(), e);
			escribir(response, "", e);
		}
		return null;
	}
	
	
	
	
	
	/**
	 * Metodo utilizado para consultar las cadenas de custodia de un expediente
	 * regresando a vista solo las evidencias
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward consultarEvidenciasCadenaCustodiaPorExpediente(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {

			log.info("ejecutando consultaEvidencias de Cadena de Custodia");
			
			String numeroExpediente=request.getParameter("numeroExpediente");
			String folioCadCustodia=request.getParameter("folioCadCustodia");
			log.info("NE!!! "+numeroExpediente);log.info("FCC!!! "+folioCadCustodia);
			CadenaDeCustodiaDTO cadenaDTO=new CadenaDeCustodiaDTO();
			cadenaDTO.setCadenaDeCustodiaId(Long.parseLong(folioCadCustodia));
			
			cadenaDTO=cadenaCustodiaDelegate.consultarCadenaCustodia(cadenaDTO);
			
			List<EvidenciaDTO> evidencias=cadenaDTO.getEvidencias();
			
			converter.alias("listaEvidencias", java.util.List.class);
			converter.alias("evidenciaDTO", EvidenciaDTO.class);
			String xml = converter.toXML(evidencias);
			if(log.isDebugEnabled())
			{
				log.debug(xml);
			}
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
		
			writer.print("<rows>");
			int lTotalRegistros=evidencias.size();
			writer.print("<records>" + lTotalRegistros + "</records>");	
			
			for (EvidenciaDTO evidenciaDTO : evidencias) {
				writer.print("<row id='"+evidenciaDTO.getEvidenciaId()+"'>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+evidenciaDTO.getNumeroEvidencia()+" </div]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+evidenciaDTO.getEvidenciaId()+" </div]]></cell>");
				if(evidenciaDTO.getObjeto().getTipoObjeto().getValorId() == Objetos.EQUIPO_TELEFONICO.getValorId())
				{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>Equipo Telef&oacute;nico</div]]></cell>");
				}
				else if(evidenciaDTO.getObjeto().getTipoObjeto().getValorId() == Objetos.VEHICULO.getValorId())
				{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>Veh&iacute;culo</div]]></cell>");
				}
				else if(evidenciaDTO.getObjeto().getTipoObjeto().getValorId() == Objetos.ARMA.getValorId())
				{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>Arma</div]]></cell>");
				}
				else if(evidenciaDTO.getObjeto().getTipoObjeto().getValorId() == Objetos.EQUIPO_DE_COMPUTO.getValorId())
				{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>Equipo de C&oacute;mputo</div]]></cell>");
				}
				else if(evidenciaDTO.getObjeto().getTipoObjeto().getValorId() == Objetos.EXPLOSIVO.getValorId())
				{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>Explosivo</div]]></cell>");
				}
				else if(evidenciaDTO.getObjeto().getTipoObjeto().getValorId() == Objetos.SUSTANCIA.getValorId())
				{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>Sustancia</div]]></cell>");
				}
				else if(evidenciaDTO.getObjeto().getTipoObjeto().getValorId() == Objetos.ANIMAL.getValorId())
				{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>Animal</div]]></cell>");
				}
				else if(evidenciaDTO.getObjeto().getTipoObjeto().getValorId() == Objetos.AERONAVE.getValorId())
				{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>Aeronave</div]]></cell>");
				}
				else if(evidenciaDTO.getObjeto().getTipoObjeto().getValorId() == Objetos.EMBARCACION.getValorId())
				{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>Embarcaci&oacute;n</div]]></cell>");
				}
				else if(evidenciaDTO.getObjeto().getTipoObjeto().getValorId() == Objetos.NUMERARIO.getValorId())
				{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>Numerario</div]]></cell>");
				}
				else if(evidenciaDTO.getObjeto().getTipoObjeto().getValorId() == Objetos.VEGETAL.getValorId())
				{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>Vegetal</div]]></cell>");
				}
				else if(evidenciaDTO.getObjeto().getTipoObjeto().getValorId() == Objetos.DOCUMENTO_OFICIAL.getValorId())
				{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>Documento Oficial</div]]></cell>");
				}
				else if(evidenciaDTO.getObjeto().getTipoObjeto().getValorId() == Objetos.JOYA.getValorId())
				{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>Joya</div]]></cell>");
				}
				else if(evidenciaDTO.getObjeto().getTipoObjeto().getValorId() == Objetos.OBRA_DE_ARTE.getValorId())
				{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>Obra de Arte</div]]></cell>");
				}
				else
				{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+evidenciaDTO.getObjeto().getTipoObjeto().toString()+"</div]]></cell>");
				}
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+DateUtils.formatear(evidenciaDTO.getFechaLevantamiento())+" </div]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+DateUtils.formatearHora(evidenciaDTO.getFechaLevantamiento())+" </div]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+evidenciaDTO.getOrigenEvidencia()+" </div]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+evidenciaDTO.getObjeto().getTipoObjeto().getValorId()+" </div]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+evidenciaDTO.getObjeto().getElementoId()+" </div]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+evidenciaDTO.getEvidenciaId()+" </div]]></cell>");
				writer.print("</row>");	
			}
			writer.print("</rows>");
			writer.flush();
			writer.close();
			log.info("Termina ejecucion Action consultar Evidencias de cadena de custodia- FIN ");
		} catch (NSJPNegocioException e) {
			log.error(e.getCause(), e);
			escribir(response, "", e);
		}
		return null;
	}
	
	/**
	 * Metodo utilizado para consultar los objetos de un expediente que no son parte de una cadena de custodia
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward consultarEvidenciasCadenaCustodiaPorExpedienteNoasentadas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
			String numeroExpediente=request.getParameter("numeroExpediente");
			//String numeroExpedienteId=request.getParameter("numeroExpedienteId");
			//String areaExpediente=request.getParameter("areaExpediente");
			
			log.info("$$$$ numero DE Expediente consultar objetos no asentados en cadena $$$ : "+numeroExpediente);

			
			ExpedienteDTO expedienteDTO=new ExpedienteDTO();
			expedienteDTO=super.getExpedienteTrabajo(request,numeroExpediente);

			List<ObjetoDTO> listaObjetos=objetoDelegate.consultarObjetosNoEvidencia(expedienteDTO);
			
		
			converter.alias("listaObjetos", java.util.List.class);
			converter.alias("objetoDTO", DelitoDTO.class);
			String xml = converter.toXML(listaObjetos);
			if(log.isDebugEnabled())
			{
				log.debug(xml);
			}
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.print("<rows>");
						
			int lTotalRegistros=listaObjetos.size();

			writer.print("<records>" + lTotalRegistros + "</records>");
			int i=0;
			for (ObjetoDTO objetoDTO : listaObjetos) {
				
				writer.print("<row id='" + objetoDTO.getElementoId()+ "'>");
				//Columna de tipo de objetos
				if(objetoDTO.getTipoObjeto().getValorId() == Objetos.EQUIPO_TELEFONICO.getValorId())
				{
					writer.print("<cell>Equipo Telefónico</cell>");
				}
				else if(objetoDTO.getTipoObjeto().getValorId() == Objetos.VEHICULO.getValorId())
				{
					writer.print("<cell>Vehículo</cell>");
				}
				else  if(objetoDTO.getTipoObjeto().getValorId() == Objetos.ARMA.getValorId())
				{
					writer.print("<cell>Arma</cell>");
				}
				else if(objetoDTO.getTipoObjeto().getValorId() == Objetos.EQUIPO_DE_COMPUTO.getValorId())
				{
					writer.print("<cell>Equipo de Cómputo</cell>");
				}
				else if(objetoDTO.getTipoObjeto().getValorId() == Objetos.EXPLOSIVO.getValorId())
				{
					writer.print("<cell>Explosivo</cell>");
				}
				else if(objetoDTO.getTipoObjeto().getValorId() == Objetos.SUSTANCIA.getValorId())
				{
					writer.print("<cell>Sustancia</cell>");
				}
				else if(objetoDTO.getTipoObjeto().getValorId() == Objetos.ANIMAL.getValorId())
				{
					writer.print("<cell>Animal</cell>");
				}
				else if(objetoDTO.getTipoObjeto().getValorId() == Objetos.AERONAVE.getValorId())
				{
					writer.print("<cell>Aeronave</cell>");
				}
				else if(objetoDTO.getTipoObjeto().getValorId() == Objetos.EMBARCACION.getValorId())
				{
					writer.print("<cell>Embarcaci&oacute;n</cell>");
				}
				else if(objetoDTO.getTipoObjeto().getValorId() == Objetos.NUMERARIO.getValorId())
				{
					writer.print("<cell>Numerario</cell>");
				}
				else if(objetoDTO.getTipoObjeto().getValorId() == Objetos.VEGETAL.getValorId())
				{
					writer.print("<cell>Vegetal</cell>");
				}
				else if(objetoDTO.getTipoObjeto().getValorId() == Objetos.DOCUMENTO_OFICIAL.getValorId())
				{
					writer.print("<cell>Documento Oficial</cell>");
				}
				else if(objetoDTO.getTipoObjeto().getValorId() == Objetos.JOYA.getValorId())
				{
					writer.print("<cell>Joya</cell>");
				}
				else if(objetoDTO.getTipoObjeto().getValorId() == Objetos.OBRA_DE_ARTE.getValorId())
				{
					writer.print("<cell>Obra de Arte</cell>");
				}
				else  
				{
					writer.print("<cell>"+objetoDTO.getTipoObjeto().toString()+" - " +objetoDTO.getTipoObjeto().getValorId()+"</cell>");
				}
				
				//Columna para mostrar la informacion del objeto
				if(objetoDTO.getTipoObjeto().getValorId() == Objetos.EQUIPO_TELEFONICO.getValorId())
				{
					writer.print("<cell>"+((TelefoniaDTO)objetoDTO).getTipoTelefono().getValor()+"</cell>");
				}
				else if(objetoDTO.getTipoObjeto().getValorId() == Objetos.VEHICULO.getValorId())
				{
					writer.print("<cell>"+((VehiculoDTO)objetoDTO).getPlaca() +" "+ ((VehiculoDTO)objetoDTO).getValorByTipoVehiculo().getValor()+"</cell>");
				}
				else if(objetoDTO.getTipoObjeto().getValorId() == Objetos.ARMA.getValorId())
				{
					writer.print("<cell>"+((ArmaDTO)objetoDTO).getTipoArma().getValor()+"</cell>");
				}
				else if(objetoDTO.getTipoObjeto().getValorId() == Objetos.EQUIPO_DE_COMPUTO.getValorId())
				{
					writer.print("<cell>"+((EquipoComputoDTO)objetoDTO).getTipoEquipo().getValor()+"</cell>");
				}
				else if(objetoDTO.getTipoObjeto().getValorId() == Objetos.EXPLOSIVO.getValorId())
				{
					writer.print("<cell>"+((ExplosivoDTO)objetoDTO).getTipoExplosivo().getValor()+"</cell>");
				}
				else if(objetoDTO.getTipoObjeto().getValorId() == Objetos.SUSTANCIA.getValorId())
				{
					writer.print("<cell>"+((SustanciaDTO)objetoDTO).getTipoSustancia().getValor()+"</cell>");
				}
				else if(objetoDTO.getTipoObjeto().getValorId() == Objetos.ANIMAL.getValorId())
				{
					writer.print("<cell>"+((AnimalDTO)objetoDTO).getTipoAnimal().getValor()+"</cell>");
				}
				else if(objetoDTO.getTipoObjeto().getValorId() == Objetos.AERONAVE.getValorId())
				{
					writer.print("<cell>"+((AeronaveDTO)objetoDTO).getTipoAeroNave().getValor()+"</cell>");
				}
				else if(objetoDTO.getTipoObjeto().getValorId() == Objetos.EMBARCACION.getValorId())
				{
					writer.print("<cell>"+((EmbarcacionDTO)objetoDTO).getTipoEmbarcacion().getValor()+"</cell>");
				}
				else if(objetoDTO.getTipoObjeto().getValorId() == Objetos.NUMERARIO.getValorId())
				{
					writer.print("<cell>"+((NumerarioDTO)objetoDTO).getMoneda()+"</cell>");
				}
				//TODO descomentar este if cuando los objetos no regresen VEGETAL como tipo, por ejemplo documento
				/*else if(objetoDTO.getTipoObjeto().toString().equals("VEGETAL"))
				{
					writer.print("<cell>"+((VegetalDTO)objetoDTO).getTipoVegetal().getValor()+"</cell>");
				}*/
				else if(objetoDTO.getTipoObjeto().getValorId() == Objetos.DOCUMENTO_OFICIAL.getValorId())
				{
					writer.print("<cell>"+((DocumentoOficialDTO)objetoDTO).getTipoDocumento().getValor()+"</cell>");
				}
				else if(objetoDTO.getTipoObjeto().getValorId() == Objetos.JOYA.getValorId())
				{
					writer.print("<cell>"+((JoyaDTO)objetoDTO).getTipoJoya().getValor()+"</cell>");
				}
				else if(objetoDTO.getTipoObjeto().getValorId() == Objetos.OBRA_DE_ARTE.getValorId())
				{
					writer.print("<cell>"+((ObraArteDTO)objetoDTO).getTipoObraArte().getValor()+"</cell>");
				}
				writer.print("<cell><![CDATA[<div>"+"<input type='checkbox' class='chkObjNoAsentado' id='chk_"+objetoDTO.getElementoId()+"'> </div]]></cell>");
				//writer.print("<cell>"+catDelitoDTO.getCatDelitoDTO().getClaveDelito()+ "_" +catDelitoDTO.getDelitoId() +"_C"+ "</cell>");
				writer.print("</row>");
				i++;
			}			
			
			writer.print("</rows>");
			writer.flush();
			writer.close();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			//escribirError(response, null);
		}
		return null;
	}
	
	/**
	 * Metodo utilizado para guardar objetos existentes en la Cadena de custodia
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward guardarObjetoNoAsentadoEnCadCus(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {

			log.info("ejecutando Action guardarObjetoNoAsentadoEnCadCus");
			
			String idsObjetos=request.getParameter("ids");
			String idCadenacustodia=request.getParameter("idCadenacustodia");
			String fecha=request.getParameter("fecha");
			String origen=request.getParameter("origen");
			String numeroExpediente=request.getParameter("numeroExpediente");
			
			String[] arrayIDs=idsObjetos.split(",");
			//encapsulamos la informacion del expediente
			ExpedienteDTO expedienteDTO=super.getExpedienteTrabajo(request, numeroExpediente);
			
			String idEvidencias="";
			for (String id : arrayIDs) {
				//barremos el arreglo de IDs
				EvidenciaDTO evidenciaDTO=new EvidenciaDTO();
				evidenciaDTO.setFechaLevantamiento(DateUtils.obtener(fecha.split(" ")[0], fecha.split(" ")[1]));
				evidenciaDTO.setOrigenEvidencia(origen);
				CadenaDeCustodiaDTO cadenaDeCustodia=new CadenaDeCustodiaDTO();
				cadenaDeCustodia.setCadenaDeCustodiaId(Long.parseLong(idCadenacustodia));
				evidenciaDTO.setCadenaDeCustodia(cadenaDeCustodia);
				evidenciaDTO.setDescripcion("");
				evidenciaDTO.setFuncionario(super.getUsuarioFirmado(request).getFuncionario());
				evidenciaDTO.setEstatus(new ValorDTO(EstatusEvidencia.EN_ALMACEN.getValorId()));
				evidenciaDTO.setObjeto(new ObjetoDTO(Long.parseLong(id)));
				idEvidencias+= evidenciaDelegate.guardarEvidencia(evidenciaDTO).toString()+",";
			}
			//mandamos la respuesta a la vista
			if(idEvidencias!=null && idEvidencias.length()>0)
			{
				String xml="<exito><bandera>1</bandera></exito>";
				log.info(xml);
				escribirRespuesta(response, xml);
			}
			else
			{
				String xml="<exito><bandera>0</bandera></exito>";
				log.info(xml);
				escribirRespuesta(response, xml);
			}
			log.info("Termina ejecucion Action guardar objetos no asentados - FIN ");
		} catch (NSJPNegocioException e) {
			log.error(e.getMessage(), e);
			String xml="<exito><bandera>0</bandera></exito>";
			escribir(response, xml, e);
		}
		return null;
	}
	
	/**
	 * Metodo utilizado para consultar las evidencias de una cadena de custodia
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward consultarEvidenciasXCadenaCustodia(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {

			log.info("ejecutando consultarEvidenciasXCadenaCustodia de Cadena de Custodia");
			
			String folioCadCustodia=request.getParameter("folioCadCustodia");
			
			CadenaDeCustodiaDTO cadenaDTO=new CadenaDeCustodiaDTO();
			cadenaDTO.setCadenaDeCustodiaId(Long.parseLong(folioCadCustodia));
			
			// Consultamos las evidencias de la cadena de custodia
			List<EvidenciaDTO> evidencias=evidenciaDelegate.consultarEvidenciasXCadenaCustodia(cadenaDTO);
				
			converter.alias("listaEvidencias", java.util.List.class);
			converter.alias("evidenciaDTO", EvidenciaDTO.class);
			String xml = converter.toXML(evidencias);
			if(log.isDebugEnabled())
			{
				log.debug(xml);
			}
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
		
			writer.print("<rows>");
			int lTotalRegistros=evidencias.size();
			writer.print("<records>" + lTotalRegistros + "</records>");	
			
			
			for (EvidenciaDTO evidenciaDTO : evidencias) {
				writer.print("<row id='"+evidenciaDTO.getEvidenciaId()+"'>");
				//columna del checkbox
				writer.print("<cell><![CDATA[<div>"+"<input type='checkbox' class='chkObjEvdXCadCus' id='chk_"+evidenciaDTO.getEvidenciaId()+"'> </div]]></cell>");
				//columna Numero de evidencia
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+evidenciaDTO.getNumeroEvidencia()+" </div]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+evidenciaDTO.getEvidenciaId()+" </div]]></cell>");
				//Columna para mostrar la informacion del objeto
				log.info("JO!!! "+evidenciaDTO.getObjeto().getTipoObjeto().toString());
				/*ObjetoDTO objeto= evidenciaDTO.getObjeto();
				if(evidenciaDTO.getObjeto().getTipoObjeto().toString().equals("EQUIPO_TELEFONICO"))
				{
					writer.print("<cell>"+((TelefoniaDTO)objeto).getTipoTelefono().getValor()+"</cell>");
				}
				else if(evidenciaDTO.getObjeto().getTipoObjeto().toString().equals("VEHICULO"))
				{
					writer.print("<cell>"+((VehiculoDTO)objeto).getPlaca() +" "+ ((VehiculoDTO)evidenciaDTO.getObjeto()).getValorByTipoVehiculo().getValor()+"</cell>");
				}
				else if(evidenciaDTO.getObjeto().getTipoObjeto().toString().equals("ARMA"))
				{
					writer.print("<cell>"+((ArmaDTO)objeto).getTipoArma().getValor()+"</cell>");
				}
				else if(evidenciaDTO.getObjeto().getTipoObjeto().toString().equals("EQUIPO_COMPUTO"))
				{
					writer.print("<cell>"+((EquipoComputoDTO)objeto).getTipoEquipo().getValor()+"</cell>");
				}*/
				
				String tipoObjeto=evidenciaDTO.getObjeto().getTipoObjeto().toString();
				//Columna de tipo de objetos
				if(tipoObjeto.equals("EQUIPO_TELEFONICO"))
				{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>Equipo Telefónico </div]]></cell>");
				}
				else if(tipoObjeto.equals("VEHICULO"))
				{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>Vehículo </div]]></cell>");
				}
				else  if(tipoObjeto.equals("ARMA"))
				{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>Arma </div]]></cell>");
				}
				else if(tipoObjeto.equals("EQUIPO_COMPUTO"))
				{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>Equipo de Cómputo </div]]></cell>");
				}
				else if(tipoObjeto.equals("EXPLOSIVO"))
				{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>Explosivo </div]]></cell>");
				}
				else if(tipoObjeto.equals("SUSTANCIA"))
				{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>Sustancia </div]]></cell>");
				}
				else if(tipoObjeto.equals("ANIMAL"))
				{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>Animal </div]]></cell>");
				}
				else if(tipoObjeto.equals("AERONAVE"))
				{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>Aeronave </div]]></cell>");
				}
				else if(tipoObjeto.equals("EMBARCACION"))
				{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>Embarcaci&oacute;n </div]]></cell>");
				}
				else if(tipoObjeto.equals("NUMERARIO"))
				{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>Numerario </div]]></cell>");
				}
				else if(tipoObjeto.equals("VEGETAL"))
				{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>Vegetal </div]]></cell>");
				}
				else if(tipoObjeto.equals("DOCUMENTO_OFICIAL"))
				{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>Documento Oficial </div]]></cell>");
				}
				else if(tipoObjeto.equals("JOYA"))
				{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>Joya </div]]></cell>");
				}
				else if(tipoObjeto.equals("OBRA_DE_ARTE"))
				{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>Obra de Arte </div]]></cell>");
				}
				else  
				{
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+evidenciaDTO.getObjeto().getTipoObjeto().toString()+" </div]]></cell>");
				}
					
					
					
				//writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+evidenciaDTO.getObjeto().getTipoObjeto()+" </div]]></cell>");
				//columna fecha y hora de levantamiento
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+DateUtils.formatear(evidenciaDTO.getFechaLevantamiento())+" "+DateUtils.formatearHora(evidenciaDTO.getFechaLevantamiento())+" </div]]></cell>");
				//Columna Origen de la evidencia
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+evidenciaDTO.getOrigenEvidencia()+" </div]]></cell>");
				writer.print("</row>");	
			}
			writer.print("</rows>");
			writer.flush();
			writer.close();
			log.info("Termina ejecucion Action consultar Evidencias X cadena de custodia- FIN ");
		} catch (NSJPNegocioException e) {
			log.error(e.getCause(), e);
			escribir(response, "", e);
		}
		return null;
	}
	
	/**
	 * Metodo utilizado para guardar una cadena de custodia
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward ingresarEslabonCadenaCustodia(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {

			String posicionEvidencia=request.getParameter("posicionEvidencia");
			String ubicacionFisica=request.getParameter("ubicacionFisica");


			
			log.info("ejecutando Action guardar Eslabon Cadena Custodia");
			EslabonCadenaCustodiaForm forma = (EslabonCadenaCustodiaForm) form;
			
			log.info("FORMA ESLABON CADENA CUSTODIA:::::::::::::::::::::::");
			
			/*** Revisamos que los campos no sean nulos ****/		    
			if(StringUtils.isEmpty(forma.getTipoEslabon()))
			{
				forma.setTipoEslabon(null);
				log.debug("Tipo eslabon entrega es null.");
			}
			if(StringUtils.isEmpty(forma.getNombrePersonaEntrega()))
			{
				forma.setNombrePersonaEntrega("");
				log.debug("Nombre de persona q entrega es null.");
			}
			if(StringUtils.isEmpty(forma.getFechaEntrega()))
			{
				forma.setFechaEntrega("");
				log.debug("fecha de entrega es null.");
			}
			if(StringUtils.isEmpty(forma.getHoraEntrega()))
			{
				forma.setHoraEntrega("");
				log.debug("hora de entrega es null.");
			}
			if(StringUtils.isEmpty(forma.getObsEntrega()))
			{
				forma.setObsEntrega(null);
				log.debug("observaciones de entrega es null.");
			}
			if(StringUtils.isEmpty(forma.getInstitucionEnt()))
			{
				forma.setInstitucionEnt(null);
				log.debug("institucion de entrega es null.");
			}
			
			//recepcion
			if(StringUtils.isEmpty(forma.getTipoEslabonRec()))
			{
				forma.setTipoEslabonRec(null);
				log.debug("Tipo eslabon recepcion es null.");
			}
			if(StringUtils.isEmpty(forma.getNombrePersonaRecepcion()))
			{
				forma.setNombrePersonaRecepcion(null);
				log.debug("nombre de la persona que recibe es null.");
			}
			if(StringUtils.isEmpty(forma.getFechaRecepcion()))
			{
				forma.setFechaRecepcion(null);
				log.debug("fecha de recepcion es null.");
			}
			if(StringUtils.isEmpty(forma.getHoraRecepcion()))
			{
				forma.setHoraRecepcion(null);
				log.debug("hora de recepcion es null.");
			}
			if(StringUtils.isEmpty(forma.getObsRecepcion()))
			{
				forma.setObsRecepcion(null);
				log.debug("observaciones de recepcion es null.");
			}
			if(StringUtils.isEmpty(forma.getIdsEvidencias()))
			{
				forma.setIdsEvidencias(null);
				log.debug("observaciones de recepcion es null.");
			}
			if(StringUtils.isEmpty(forma.getInstitucionRec()))
			{
				forma.setInstitucionRec(null);
				log.debug("institucion de recepcion es null.");
			}
			
			//Validaciones de los campos para el periodo de prestamo
			if(StringUtils.isEmpty(forma.getFechaIniPres()))
			{
				forma.setFechaIniPres(null);
				log.debug("fecha de inicio prestamo es null.");
			}
			if(StringUtils.isEmpty(forma.getHoraIniPres()))
			{
				forma.setHoraIniPres(null);
				log.debug("hora de inicio prestamo es null.");
			}
			if(StringUtils.isEmpty(forma.getFechaFinPres()))
			{
				forma.setFechaFinPres(null);
				log.debug("fecha de fin prestamo es null.");
			}
			if(StringUtils.isEmpty(forma.getHoraFinPres()))
			{
				forma.setHoraFinPres(null);
				log.debug("hora de fin prestamo es null.");
			}
			/*** FIN Revisamos que los campos no sean nulos ****/
					
			//encapsulamos la informacion del expediente
			//ExpedienteDTO expedienteDTO=super.getExpedienteTrabajo(request, forma.getNumeroExpediente());
			
			if(forma.getIdsEvidencias()==null)
			{
				throw new Exception("Se necesita seleccionar al menos una evidencia.");
			}
			
			//Seteamos la informacion del eslabon
			//entrega
			EslabonDTO eslabonDTO=new EslabonDTO();
			
			FuncionarioDTO funcionarioEntrega=new FuncionarioDTO();			
			funcionarioEntrega.setApellidoPaternoFuncionario(forma.getNombrePersonaEntrega().split("-")[1]);
			funcionarioEntrega.setApellidoMaternoFuncionario(forma.getNombrePersonaEntrega().split("-")[2]);
			funcionarioEntrega.setNombreFuncionario(forma.getNombrePersonaEntrega().split("-")[0]);
			eslabonDTO.setFuncionariEntrega(funcionarioEntrega);
			eslabonDTO.setQuienEntrega(funcionarioEntrega.getNombreCompleto());
			eslabonDTO.setFechaInicioMovimiento(DateUtils.obtener(forma.getFechaEntrega(), forma.getHoraEntrega()));
			eslabonDTO.setFechaFinMovimiento(DateUtils.obtener(forma.getFechaRecepcion(), forma.getHoraRecepcion()));
			eslabonDTO.setUbicacionFisica(ubicacionFisica);
			
			log.info("Ins_Eslabon_Funcionario_entrega:: "+funcionarioEntrega.getNombreCompleto());
			log.info("Ins_Eslabon_Fecha_inicio_mov_ent:: "+DateUtils.obtener(forma.getFechaEntrega(), forma.getHoraEntrega()));
			log.info("Ins_Eslabon_Fecha_fin_mov_ent:: "+DateUtils.obtener(forma.getFechaRecepcion(), forma.getHoraRecepcion()));
			log.info("Ins_Eslabon_Ubicacion_Fisica:: "+ubicacionFisica);
			log.info("Ins_Eslabon_Posicion_Evidencia:: "+posicionEvidencia);
			log.info("Ins_Eslabon_Fecha_Ini_prestamo:: "+DateUtils.obtener(forma.getFechaIniPres(), forma.getHoraIniPres()));
			log.info("Ins_Eslabon_Fecha_Fin_prestamo:: "+DateUtils.obtener(forma.getFechaFinPres(), forma.getHoraFinPres()));
			eslabonDTO.setPosicion(posicionEvidencia);
			if(forma.getInstitucionEnt()!=null)
			{
				eslabonDTO.setInstitucionQueEntrega(forma.getInstitucionEnt());
				log.info("Ins_Eslabon_Institucion_Entrega:: "+forma.getInstitucionEnt());
			}
			eslabonDTO.setObservacion(forma.getObsEntrega());
			log.info("Ins_Eslabon_ObservacionEntrega:: "+forma.getObsEntrega());
			
			//recepcion
			FuncionarioDTO funcionarioRecepcion=new FuncionarioDTO();
			funcionarioRecepcion.setApellidoPaternoFuncionario(forma.getNombrePersonaRecepcion().split("-")[1]);
			funcionarioRecepcion.setApellidoMaternoFuncionario(forma.getNombrePersonaRecepcion().split("-")[2]);
			funcionarioRecepcion.setNombreFuncionario(forma.getNombrePersonaRecepcion().split("-")[0]);
			eslabonDTO.setFuncionariRecibe(funcionarioRecepcion);
			eslabonDTO.setQuienRecibe(funcionarioRecepcion.getNombreCompleto());
			eslabonDTO.setFechaInicioMovimiento(DateUtils.obtener(forma.getFechaEntrega(), forma.getHoraEntrega()));
			eslabonDTO.setFechaFinMovimiento(DateUtils.obtener(forma.getFechaRecepcion(), forma.getHoraRecepcion()));

			//fechas y horas del lapso de prestamo
			eslabonDTO.setFechaInicioPrestamo(DateUtils.obtener(forma.getFechaIniPres(), forma.getHoraIniPres()));
			eslabonDTO.setFechaFinPrestamo(DateUtils.obtener(forma.getFechaFinPres(), forma.getHoraFinPres()));
			
			log.info("Ins_Eslabon_Fecha_Ini_prestamo:: "+eslabonDTO.getFechaInicioPrestamo());
			log.info("Ins_Eslabon_Fecha_Fin_prestamo:: "+eslabonDTO.getFechaFinPrestamo());
			log.info("Ins_Eslabon_Funcionario_recepcion:: "+funcionarioRecepcion.getNombreCompleto());
			log.info("Ins_Eslabon_Fecha_inicio_mov_rec:: "+DateUtils.obtener(forma.getFechaEntrega(), forma.getHoraEntrega()));
			log.info("Ins_Eslabon_Fecha_fin_mov_rec:: "+DateUtils.obtener(forma.getFechaEntrega(), forma.getHoraRecepcion()));
			if(forma.getInstitucionRec()!=null)
			{
				eslabonDTO.setInstitucionQueRecibe(forma.getInstitucionRec());	
				log.info("Ins_Eslabon_Institucion_Recibe:: "+forma.getInstitucionRec());
			}

			//FIN Seteamos la informacion del eslabon
			if(forma.getTipoEslabon() != null)
				eslabonDTO.setTipoEslabon(new ValorDTO(Long.parseLong(forma.getTipoEslabon())));
			if(forma.getTipoEslabonRec() != null)
				eslabonDTO.setTipoEslabonDeRecepcion(new ValorDTO(Long.parseLong(forma.getTipoEslabonRec())));
			
			log.info("Ins_Eslabon_TipoEslabon:: "+forma.getTipoEslabon());
			log.info("Ins_Eslabon_Evidencias:: "+forma.getIdsEvidencias());
			
			String[] idsEvidencias= forma.getIdsEvidencias().split(",");
			
			for (String idEvidencia : idsEvidencias) {
				log.debug("ID_ECD:: "+idEvidencia);
				EvidenciaDTO evidenciaDTO=new EvidenciaDTO();
				evidenciaDTO.setEvidenciaId(Long.parseLong(idEvidencia));
				evidenciaDTO.setEstatus(new ValorDTO(EstatusEvidencia.EN_ALMACEN.getValorId()));
				
				log.info(eslabonDTO.getObservacion());
				log.info(eslabonDTO.getFechaInicioMovimiento());
				log.info(eslabonDTO.getFechaFinMovimiento());
				log.info(eslabonDTO.getFuncionariRecibe().getNombreFuncionario());
				log.info(eslabonDTO.getFuncionariEntrega().getNombreFuncionario());
				
				eslabonDelegate.guardarEslabon(evidenciaDTO, eslabonDTO);
				evidenciaDTO=null;
			}
			String xml="<exito><bandera>1</bandera></exito>";
			log.info(xml);
			escribirRespuesta(response, xml);
			
			log.info("Termina ejecucion Action guardar Eslabon Cadena Custodia - FIN ");
		} catch (NSJPNegocioException e) {
			log.error(e.getMessage(), e);
			String xml="<exito><bandera>0</bandera></exito>";
			log.info(xml);
			escribir(response, xml, e);
		}
		catch(Exception ex)
		{
			log.error(ex.getMessage(), ex);
			String xml="<exito><bandera>0</bandera></exito>";
			log.info(xml);
			escribirError(response,null);
		}
		return null;
	}
	
	/**
	 * Metodo utilizado para consultar el usuario firmado para cadena de custodia
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward consultarFuncionarioFirmado(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {

			log.info("ejecutando Action consultar funcionario firmado - Cadena Custodia");
			UsuarioDTO user = super.getUsuarioFirmado(request);
			FuncionarioDTO funcionario=user.getFuncionario();
			
			String xml = null;
			converter.alias("funcionarioDTO",FuncionarioDTO.class);
			xml = converter.toXML(funcionario);
			log.info("usuario_firmado - consultarFuncionarioFirmado: "+xml);
			escribirRespuesta(response, xml);
			log.info("Termina ejecucion Action consultar funcionario firmado - Cadena Custodia FIN");
		}
		catch(Exception ex)
		{
			log.error(ex.getMessage(), ex);
			String xml="<exito><bandera>0</bandera></exito>";
			log.info(xml);
			escribirError(response,null);
		}
		return null;
	}
	
	
	
	/**
	 * Permite consultar las relaciones por expediente
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarInventarioDeEvidenciasXNumExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {		
		try {
			String numeroCaso = request.getParameter("numeroCaso");			
			
			log.info("$$$$ numeroCaso $$$ : " + numeroCaso );

			ExpedienteDTO loExp = new ExpedienteDTO();
			loExp.setNumeroExpediente(numeroCaso);
			loExp.setExpedienteId(8L);
			
			List<CadenaDeCustodiaDTO> cadenas= cadenaDeCustodiaDelegate.consultarCadenaCustodiaXExpediente(loExp);			
			String folioCadena = null;
			String numEvidencia = null;
			Long idCadenaCustodia = null;
			Long idEvidencia = null;
			String estatusEvidencia = "-";
			String infoObjeto = "-";

			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();		
			writer.print("<rows>");
			writer.print("<records>" + "desconocido" + "</records>");	
			
			for (CadenaDeCustodiaDTO dto : cadenas) {
				idCadenaCustodia = dto.getCadenaDeCustodiaId();
				folioCadena = dto.getFolio();
				
				if (dto.getEvidencias() != null) {
					//Iteramos sobre las evidencias
					for (EvidenciaDTO evi : dto.getEvidencias()) {
						idEvidencia = evi.getEvidenciaId();
						if(evi.getEstatus() != null)
							estatusEvidencia = evi.getEstatus().getValor();			
						if (evi.getObjeto() != null){
							ObjetoDTO obj = evi.getObjeto();
							infoObjeto = obj.getDescripcion();			
						}
						
						writer.print("<row id='"+idEvidencia+"'>");
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ (folioCadena != null? folioCadena:"-") +" </div>]]></cell>");
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ (numEvidencia != null? numEvidencia:"-") +" </div>]]></cell>");
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ (infoObjeto != null? infoObjeto:"-") +" </div>]]></cell>");
						writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ (estatusEvidencia != null? estatusEvidencia:"-") +" </div>]]></cell>");
						writer.print("</row>");
					}
				}
			}
			
			
		writer.print("</rows>");
		writer.flush();
		writer.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			escribirError(response, null);
		}
		return null;
	}

	/**
	 * Permite consultar las relaciones por expediente
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward consultarCadenasCustodiaDelExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {		
		try {
			String numeroExpediente=request.getParameter("numeroExpediente");
			
			log.info("$$$$ numero DE Expediente consultar sus CADENAS DE CUSTODIA $$$ : "+numeroExpediente);

			
			ExpedienteDTO expedienteDTO=new ExpedienteDTO();
			expedienteDTO=super.getExpedienteTrabajo(request,numeroExpediente);

			List<CadenaDeCustodiaDTO> listaCadenas= cadenaDeCustodiaDelegate.consultarCadenaCustodiaXExpediente(expedienteDTO);
				
			if(log.isDebugEnabled())
			{
				converter.alias("listaCadenas", java.util.List.class);
				converter.alias("cadenaDeCustodiaDTO", DelitoDTO.class);
				String xml = converter.toXML(listaCadenas);
				log.debug(xml);
			}
			
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
            
			int i=0;
			for (CadenaDeCustodiaDTO cadenaDTO : listaCadenas) {
				
				writer.print("<row id='" + cadenaDTO.getCadenaDeCustodiaId()+ "'>");
				writer.print("<cell>"+cadenaDTO.getCadenaDeCustodiaId()+ "</cell>");
				writer.print("<cell>"+cadenaDTO.getFolio()+ "</cell>");
				writer.print("</row>");
				i++;
			}			
			
			writer.print("</rows>");
			writer.flush();
			writer.close();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			//escribirError(response, null);
		}
		return null;
	}
	
	/**
	 * Metodo utilizado para consultar el usuario firmado para cadena de custodia
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward consultarCadenaDeCustodiaPorId(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {

			log.info("ejecutando Action consultarCadenaDeCustodiaPorId - Cadena Custodia");
			String idCadena=request.getParameter("idCadena");
			
			CadenaDeCustodiaDTO cadenaDTO= new CadenaDeCustodiaDTO();
			cadenaDTO.setCadenaDeCustodiaId(Long.parseLong(idCadena));
			cadenaDTO=cadenaDeCustodiaDelegate.consultarCadenaCustodia(cadenaDTO);
			
			String xml = null;
			converter.alias("cadenaDTO",CadenaDeCustodiaDTO.class);
			xml = converter.toXML(cadenaDTO);
			log.info(xml);
			escribirRespuesta(response, xml);
			log.info("Termina ejecucion Action consultarCadenaDeCustodiaPorId - Cadena Custodia FIN");
		}
		catch(Exception ex)
		{
			log.error(ex.getMessage(), ex);
			String xml="<exito><bandera>0</bandera></exito>";
			log.info(xml);
			escribirError(response,null);
		}
		return null;
	}
	
	
	/**
	 * Metodo utilizado para consultar el usuario firmado para cadena de custodia
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward consultarEslabonesEvidenciaPorId(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {

			log.info("ejecutando Action consultarEslabonesEvidenciaPorId - Cadena Custodia");
			String idCadena=request.getParameter("idEvidencia");
			
			EvidenciaDTO evidenciaDTO=new EvidenciaDTO(Long.parseLong(idCadena));
			List<EslabonDTO> listaEslabones = eslabonDelegate.consultarEslabonesPorEvidencia(evidenciaDTO);
			log.info("Numero_de_eslabones_de_evidencia ["+idCadena+"] --- "+listaEslabones.size());
			String xml = null;
			converter.alias("eslabonesDTO",java.util.List.class);
			converter.alias("eslabonDTO",EslabonDTO.class);
			xml = converter.toXML(listaEslabones);
			log.info("lista_evidencias_eslabon_agentemp::"+xml);
			escribirRespuesta(response, xml);
			log.info("Termina ejecucion Action consultarEslabonesEvidenciaPorId - Cadena Custodia FIN");
		}
		catch(Exception ex)
		{
			log.error(ex.getMessage(), ex);
			String xml="<exito><bandera>0</bandera></exito>";
			log.info(xml);
			escribirError(response,null);
		}
		return null;
	}
	
	/**
	 * Metodo utilizado para consultar el usuario NO presente en la captura de la cadena de custodia
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward consultarUsuarioNoPresenteCadCustodia(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {

			log.info("ejecutando Action IngresarCadenaCustodiaAction - consultarUsuarioNoPresenteCadCustodia");
			
			String numEmpleado=request.getParameter("numeroEmpleado");
			log.info("noEmpleado:: "+numEmpleado);
			if(numEmpleado!=null)
			{
				FuncionarioDTO funcionario= new FuncionarioDTO();
				funcionario.setNumeroEmpleado(numEmpleado);
				if(funcionarioDelegate.consultarFuncionarioXNumeroEmpleado(funcionario).size()>0)
				{
					funcionario = funcionarioDelegate.consultarFuncionarioXNumeroEmpleado(funcionario).get(0);
					String xml = null;
					converter.alias("usuarioDTO",FuncionarioDTO.class);
					xml = converter.toXML(funcionario);
					log.info("cadCusUsuario_busqueda_no_presente:: "+xml);
					escribirRespuesta(response, "<bandera>1</bandera>"+xml);
				}
				else
				{
					log.info("cadCusUsuario_busqueda_no:: Usuario NO encontrado");
					escribirRespuesta(response, "<bandera>0</bandera>");
				}
			}
			else
			{
				log.info("cadCusUsuario_busqueda_no:: Usuario NO encontrado");
				escribirRespuesta(response, "<bandera>0</bandera>");
			}
			log.info("Termina ejecucion Action consultarUsuarioPresenteCadCustodia - Cadena Custodia FIN");
		}
		catch(Exception ex)
		{
			log.error(ex.getMessage(), ex);
			String xml="<bandera>0</bandera>";
			log.info("Busqueda_Empleado_por__No:: "+xml);
			escribirRespuesta(response,xml);
		}
		return null;
	}
	
	/**
	 * Metodo utilizado para consultar el usuario presente en la captura de la cadena de custodia
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward consultarUsuarioPresenteCadCustodia(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {

			log.info("ejecutando Action IngresarCadenaCustodiaAction - consultarUsuarioPresenteCadCustodia");
			
			String usuario=request.getParameter("usuario");
			String pwd=request.getParameter("pwd");
			log.info("usuario:: "+usuario);
			log.info("pwd:: "+pwd);
			
			if(usuario!=null && pwd!=null)
			{
				UsuarioDTO usuarioDTO= new UsuarioDTO();
				
				usuarioDTO.setClaveUsuario(usuario);
				usuarioDTO.setPassword(pwd);
				usuarioDTO = usuarioDelegate.obtenerUsuarioValidado(usuarioDTO);
				if(usuarioDTO!=null)
				{
				String xml = null;
				converter.alias("usuarioDTO",UsuarioDTO.class);
				xml = converter.toXML(usuarioDTO);
				log.info("cadCusUsuario_busqueda_presente:: "+xml);
				escribirRespuesta(response, "<bandera>1</bandera>"+xml);
				}
				else
				{
					log.info("cadCusUsuario_busqueda_no:: Usuario NO encontrado");
					escribirRespuesta(response, "<bandera>0</bandera>");
				}
			}
			else
			{
				log.info("cadCusUsuario_busqueda_no:: Usuario NO encontrado");
				escribirRespuesta(response, "<bandera>0</bandera>");
			}
			log.info("Termina ejecucion Action consultarUsuarioPresenteCadCustodia - Cadena Custodia FIN");
		}
		catch(Exception ex)
		{
			log.error(ex.getMessage(), ex);
			String xml="<bandera>0</bandera>";
			log.info("Busqueda_Empleado_por__No:: "+xml);
			escribirRespuesta(response,xml);
		}
		return null;
	}
	
	/**
	 * Metodo utilizado para eliminar una evidencia de la cadena de custodia
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward eliminarEvdncDeCdnCstd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {

			log.info("ejecutando Action IngresarCadenaCustodiaAction - eliminarEvdncDeCdnCstd");
			
			String idEvidencia=request.getParameter("idEvidenica");
			String idObjeto=request.getParameter("idObjeto");
			log.info("idEvidencia_eliminarCadCus:: "+idEvidencia);
			log.info("idObjeto_eliminarCadCus:: "+idObjeto);
			
			if((idEvidencia!=null && idObjeto!=null)&&(idEvidencia!=null && idObjeto!=null) ){
				if((Long.parseLong(idEvidencia)>0) && (Long.parseLong(idObjeto))>0){
					//ahora validamos que el objeto de la evidencia NO este en algun eslabon de la Cadena de Custodia
					if(!eslabonDelegate.tieneEslabonPorEvidenciaYObjeto(Long.parseLong(idObjeto))){
						//tratamos de eliminar la evidencia
						if(evidenciaDelegate.eliminarEvidencia(Long.parseLong(idEvidencia))){
							//se elimino correctamente
							log.info("eliminarEvdncDeCdnCstd:: La evidencia SE logro eliminar en la BD");
							escribirRespuesta(response, "<bandera>1</bandera><mensajeOp>La evidencia se anuló exitosamente</mensajeOp>");
						}
						else{
							//mandamos la bandera de error
							log.info("eliminarEvdncDeCdnCstd:: La evidencia no se logro eliminar en la BD");
							escribirRespuesta(response, "<bandera>-1</bandera><mensajeOp>La evidencia no se logró eliminar porque está en un eslabón</mensajeOp>");
						}
					}
					else{
						//mandamos la bandera de error
						log.info("eliminarEvdncDeCdnCstd:: La evidencia se encuentra en un eslabon de cadena de custodia");
						escribirRespuesta(response, "<bandera>-1</bandera><mensajeOp>La evidencia no se logró eliminar porque está en un eslabón</mensajeOp>");
					}
				}
				else{
					//mandamos la bandera igual a cero por los IDs invalidos
					log.info("eliminarEvdncDeCdnCstd:: IDs INVALIDOS para realizar la eliminacion de la evidencia_CadCus");
					escribirRespuesta(response, "<bandera>0</bandera><mensajeOp>No se logró eliminar la evidencia, verifique su selección</mensajeOp>");
				}
			}
			else
			{
				//mandamos la bandera igual a cero por los IDs invalidos
				log.info("eliminarEvdncDeCdnCstd:: IDs INVALIDOS para realizar la eliminacion de la evidencia_CadCus");
				escribirRespuesta(response, "<bandera>0</bandera><mensajeOp>No se logró eliminar la evidencia, verifique su selección</mensajeOp>");
			}
			log.info("Termina ejecucion Action ngresarCadenaCustodiaAction - eliminarEvdncDeCdnCstd FIN");
		}
		catch(Exception ex)
		{
			log.error(ex.getMessage(), ex);
			log.info("eliminarEvdncDeCdnCstd:: Ocurrió un ERROR en la BD al tratar de eliminar la evidencia");
			escribirRespuesta(response, "<bandera>0</bandera><mensajeOp>Ocurrió un error al tratar de anular la evidencia, intente más tarde</mensajeOp>");
		}
		return null;
	}
	
	
	/**
	 * Metodo utilizado para eliminar una evidencia de la cadena de custodia
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null, debido a la comunicacion Ajax
	 * @throws IOException
	 *             En caso de obtener una exception
	 */
	public ActionForward verificaObjEsEvdncNoEslbn(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {

			log.info("ejecutando Action IngresarCadenaCustodiaAction - verificaObjEsEvdncNoEslbn");
			
			String idObjeto=request.getParameter("idObjeto");
			log.info("idObjeto_eliminarCadCus:: "+idObjeto);
			if(StringUtils.isNotEmpty(idObjeto)){
				if(Long.parseLong(idObjeto)>0){
					//verificamos el objeto
					if(eslabonDelegate.esEvidenciaNoTieneEslabon(Long.parseLong(idObjeto))){
						//es evidencia y no tiene eslabones
						log.info("verificaObjEsEvidenciaNOEslabones:: el objeto es evidencia y no tiene eslabones, se puede eliminar pero se necesita un confirm");
						escribirRespuesta(response, "<bandera>1</bandera><mensajeOp></mensajeOp>");
					}
					else{
						//es evidencia o no, y si lo es tiene eslabon asociado
						log.info("verificaObjEsEvidenciaNOEslabones:: El objeto es o no evidencia y de serlo no tiene eslabones, por lo tanto se puede eliminar");
						escribirRespuesta(response, "<bandera>2</bandera><mensajeOp></mensajeOp>");
					}
				}
				else
				{
					//mandamos la bandera igual a cero porque no llego el ID del objeto
					log.info("verificaObjEsEvidenciaNOEslabones:: el ID del objeto es igual o menor a CERO");
					escribirRespuesta(response, "<bandera>0</bandera><mensajeOp>Ocurrió un error inesperado, <br/> favor de contactar a su administrador</mensajeOp>");
				}
			}
			else
			{
				//mandamos la bandera igual a cero porque no llego el ID del objeto
				log.info("verificaObjEsEvidenciaNOEslabones:: el ID del objeto no llego desde el front");
				escribirRespuesta(response, "<bandera>0</bandera><mensajeOp>Ocurrió un error inesperado, <br/> favor de contactar a su administrador</mensajeOp>");
			}
			log.info("Termina ejecucion Action IngresarCadenaCustodiaAction - verificaObjEsEvdncNoEslbn FIN");
		}
		catch(Exception ex)
		{
			log.error(ex.getMessage(), ex);
			log.info("verificaObjEsEvidenciaNOEslabones:: Ocurrió un ERROR en la BD al tratar de verificar el objeto");
			escribirRespuesta(response, "<bandera>0</bandera><mensajeOp>Ocurrió un error inesperado, <br/> favor de contactar a su administrador</mensajeOp>");
		}
		return null;
	}
}

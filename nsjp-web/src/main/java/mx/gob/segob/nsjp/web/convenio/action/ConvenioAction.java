package mx.gob.segob.nsjp.web.convenio.action;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.delegate.expediente.ConvenioDelegate;
import mx.gob.segob.nsjp.delegate.expediente.ExpedienteDelegate;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ConvenioDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.medida.CompromisoPeriodicoDTO;
import mx.gob.segob.nsjp.dto.medida.FechaCompromisoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author rgama
 */
public class ConvenioAction extends GenericAction {

    private static final Logger log =
            Logger.getLogger(ConvenioAction.class);

    @Autowired
    ExpedienteDelegate expedienteDelegate;
    @Autowired
    ConvenioDelegate convenioDelegate;
    
    /**
	 * Metodo utilizado para registrar un convenio 
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return null
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward registrarConvenio(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			log.info("ejecutando registrar convenio");
			Long numeroExpedienteId = null;			
			
			Long idProbResponsable = NumberUtils.toLong(request.getParameter("cbxProbResponsable"),0);			
			Long idVictima = NumberUtils.toLong(request.getParameter("cbxVictima"),0);			
			Double cantidadTotal = (double) NumberUtils.toLong(request.getParameter("txtCantidadTotal"),0);			
			Long tipoConvenio = NumberUtils.toLong(request.getParameter("tipoConvenio"),0);			
			Date fechaInicio = DateUtils.obtener(request.getParameter("fechaInicio"));
			Date fechaFin = DateUtils.obtener(request.getParameter("fechaFin"));
			String pagos = request.getParameter("pagos");

			UsuarioDTO loUsuario = super.getUsuarioFirmado(request); 			
			
			//String solicitante = loUsuario.getFuncionario().getNombreCompleto();
			String numeroExpediente =request.getParameter("numeroExpediente");
			

			log.info("idProbResponsable: " + idProbResponsable);
			log.info("idVictima: " + idVictima);			
			log.info("cantidadTotal: " + cantidadTotal);			
			log.info("fechaInicio: " + fechaInicio);			
			log.info("fechaFin: " + fechaFin);			
			log.info("pagos: " + pagos);			
			log.info("numeroExpediente: " + numeroExpediente);			
			log.info("loUsuario: " + loUsuario);			
			log.info("Funcionario: " + (loUsuario != null && loUsuario.getFuncionario() != null? loUsuario.getFuncionario().getClaveFuncionario(): " NULO"));			


			if(numeroExpediente != null && numeroExpediente != "")
				//FIXME: VADP Solo interesa obtener numeroExpedienteId
				numeroExpedienteId = expedienteDelegate.obtenerExpedientePorNumeroExpediente(numeroExpediente).getExpedienteId();
				
			
			ConvenioDTO dto = new ConvenioDTO();
			
			dto.setInvolucradoPR(new InvolucradoDTO(idProbResponsable));
			dto.setInvolucradoVictima(new InvolucradoDTO(idVictima));
			dto.setFuncionario(new FuncionarioDTO(loUsuario.getFuncionario().getClaveFuncionario()));		
			
			dto.setFechaInicio(fechaInicio);
			dto.setFechaFin(fechaFin);
			dto.setTipoConvenio(new ValorDTO(tipoConvenio));
			
			ExpedienteDTO expediente = new ExpedienteDTO();
			expediente.setNumeroExpedienteId(numeroExpedienteId);			
			dto.setNumeroExpediente(expediente);
		
			//Se genera el monto total
			CompromisoPeriodicoDTO loCompromiso =new CompromisoPeriodicoDTO();			
			loCompromiso.setMonto(cantidadTotal);
			
			//Se generan los pagos
			if(!pagos.equals("")){
				List<FechaCompromisoDTO> fechas = new ArrayList<FechaCompromisoDTO>();
				pagos = pagos.substring(0, pagos.lastIndexOf("-"));
				String[] renglon = pagos.split("-");
				for (String loRenglon : renglon) {
					String[] columna = loRenglon.split(",");

					FechaCompromisoDTO loFecha = new FechaCompromisoDTO();
					loFecha.setFechaCompromiso(DateUtils.obtener(columna[1]));
					//loFecha.setFechaCumplimiento(new Date());
					loFecha.setMonto(Double.parseDouble(columna[0]));
					//loFecha.setObservaciones("Observaciones del pago");
					fechas.add(loFecha);
				}		
				loCompromiso.setFechaCompromisos(fechas);
			}
			dto.setCompromisoPeriodicoDTO(loCompromiso);
			
			Long idConvenio = convenioDelegate.guardarAcuerdoRestaurativo(dto);
			ConvenioDTO resultado = new ConvenioDTO();
			log.info("guarda con exito" + idConvenio);
			// revisamos si el guardado fue exitoso para mandar el xml
			if (idConvenio != null && idConvenio>0) {
				resultado.setConvenioID(idConvenio);
				converter.alias("ConvenioDTO", ConvenioDTO.class);
				String xml = converter.toXML(resultado);
				log.info(xml);
				escribirRespuesta(response, xml);
			} else {
				resultado.setConvenioID(0L);
				converter.alias("ConvenioDTO", ConvenioDTO.class);
				String xml = converter.toXML(resultado);
				log.info(xml);
				escribirRespuesta(response, xml);
				log.info(xml);
			}
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
			
		}
		return null;
	}
	
	/**
	 * Permite consultar las solicitudes de almacen
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * 
	 */
	public ActionForward consultarPagosDePrimerConvenio(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {		
		try {
			
			String idConvenio =request.getParameter("idConvenio");
			ConvenioDTO loConvenioDTO = new ConvenioDTO(); 
			loConvenioDTO.setConvenioID(Long.parseLong(idConvenio));
			ConvenioDTO conveniosBD = convenioDelegate.consultarDetalleConvenio(loConvenioDTO);
			
			List<FechaCompromisoDTO> fechasCompromiso = null;
			//Se obtiene las fechas compromiso
			fechasCompromiso = conveniosBD.getCompromisoPeriodicoDTO().getFechaCompromisos();
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();		
			writer.print("<rows>");
			writer.print("<records>" + "desconocido" + "</records>");	
			
			for (FechaCompromisoDTO dto : fechasCompromiso) {		
				writer.print("<row id='"+dto.getFechaCompromisoId()+"'>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ (dto.getMonto() != null? dto.getMonto() :"-") +" </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ (dto.getFechaCompromiso() != null? DateUtils.formatear(dto.getFechaCompromiso()) :"-") +" </div>]]></cell>");
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
	 * Metodo para consultar el detalle de un convenio
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return null
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarPrimerConvenio(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			String numeroExpediente =request.getParameter("numeroExpediente");
			
			Long numeroExpedienteId = null;			
			if(numeroExpediente != null && numeroExpediente != "")
				numeroExpedienteId = expedienteDelegate.obtenerExpedientePorNumeroExpediente(numeroExpediente).getExpedienteId();
			
			ExpedienteDTO loExpediente = new ExpedienteDTO();
			loExpediente.setNumeroExpedienteId(numeroExpedienteId);
			List<ConvenioDTO> convenios = convenioDelegate.consultarConveniosPorExpediente(loExpediente);
			
			ConvenioDTO loConvenio = null;
			
			if(convenios != null && convenios.size() > 0){
				loConvenio = convenios.get(0);
			}
			
			converter.alias("loConvenio", ConvenioDTO.class);
			String xml = converter.toXML(loConvenio);
			log.info("xml del convenio respuesta: :::::::::"+ xml);
			
			escribir(response, xml,null);												
			
		} catch (Exception e) {		
			log.info("ERROR AL CONSULTAR EL PRIMER CONVENIO ---- consultarPrimerConvenio");
			log.info(e.getCause(),e);
			escribir(response, "consultarPrimerConvenio",null);
			
		}
		return null;
	}
	
	/**
	 * Metodo utilizado para consultar los convenios asociados a un numero de expediente 
	 * no atendidas
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return succes - En caso de que el proceso sea correcto
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward consultarConvenios(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			log.info("EJECUTANDO ACTION CONSULTAR CONVENIOS");
			String numeroExpediente =request.getParameter("numeroExpediente");
			log.info("Expediente ииии"+numeroExpediente);
			Long numeroExpedienteId = null;			
			if(numeroExpediente != null && numeroExpediente != "")
				numeroExpedienteId = expedienteDelegate.obtenerExpedientePorNumeroExpediente(numeroExpediente).getExpedienteId();
			
			ExpedienteDTO loExpediente = new ExpedienteDTO();
			loExpediente.setNumeroExpedienteId(numeroExpedienteId);
			List<ConvenioDTO> convenios = convenioDelegate.consultarConveniosPorExpediente(loExpediente);
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter writer = response.getWriter();

				writer.print("<rows>");

				int lTotalRegistros = convenios.size();

				writer.print("<records>" + lTotalRegistros + "</records>");
				FechaCompromisoDTO ultimoPago = null;
				for (ConvenioDTO objetoDTO : convenios) {
					//log.info("expediente id=****"+objetoDTO.getNumeroExpediente().getNumeroExpedienteId());
					//Cantidad Total por Pago','Fecha Uiltimo Cumplimiento','Convenio'
					writer.print("<row id='"+ objetoDTO.getConvenioID() + "'>");
					//writer.print("<cell>"+ (objetoDTO.getConvenioID()!=null ?objetoDTO.getConvenioID():"-" ) +  "</cell>");
					writer.print("<cell>"+ (objetoDTO.getNumeroConvenio()!=null ? objetoDTO.getNumeroConvenio():"-" ) +  "</cell>");
					writer.print("<cell>"+ (objetoDTO.getFechaInicio()!=null ? DateUtils.formatear(objetoDTO.getFechaInicio()):"-" ) +  "</cell>");
					writer.print("<cell>"+ (objetoDTO.getFechaFin()!=null ? DateUtils.formatear(objetoDTO.getFechaFin()):"-" ) +  "</cell>");
					writer.print("<cell>"+ (objetoDTO.getCompromisoPeriodicoDTO()!=null &&
							objetoDTO.getCompromisoPeriodicoDTO().getMonto() != null? objetoDTO.getCompromisoPeriodicoDTO().getMonto():"-" ) +  "</cell>");
					if(objetoDTO.getCompromisoPeriodicoDTO() != null && objetoDTO.getCompromisoPeriodicoDTO().getFechaCompromisos() != null)
						ultimoPago = consultaUltimoPago(objetoDTO.getCompromisoPeriodicoDTO().getFechaCompromisos());
					writer.print("<cell>"+ (ultimoPago !=  null && ultimoPago.getMonto() != null? ultimoPago.getMonto():"-" ) +  "</cell>");
					writer.print("<cell>"+ (ultimoPago !=  null && ultimoPago.getFechaCumplimiento() != null? DateUtils.formatear(ultimoPago.getFechaCumplimiento()):"-" ) +  "</cell>");
					writer.print("<cell>"+ "<a href='#' title='Ver Documento' onclick='consultaPDF("+ objetoDTO.getConvenioID() + ")'>Ver documento</a>" +  "</cell>");
					//writer.print("<cell>"+ (objetoDTO.getCompromisoPeriodicoDTO()!=null ? objetoDTO.getCompromisoPeriodicoDTO().getMonto():"-" ) +  "</cell>");
					writer.print("</row>");
				}
				writer.print("</rows>");
				writer.flush();
				writer.close();
			
		} catch (Exception e) {		
			log.info(e.getCause(),e);
			
		}
		return null;	

	}
	
	
	private static FechaCompromisoDTO consultaUltimoPago(List<FechaCompromisoDTO> pagos){
		FechaCompromisoDTO ultimoPago =  null;
		for (FechaCompromisoDTO dbFechaPago : pagos) {
			if(dbFechaPago != null && dbFechaPago.getFechaCumplimiento() != null){
				if(ultimoPago == null)
					ultimoPago = dbFechaPago;
				else{//Se comparan fechas de cumplimiento y se obtiene la mayor fecha 
					if(dbFechaPago.getFechaCumplimiento().compareTo(ultimoPago.getFechaCumplimiento()) >  0)
						ultimoPago = dbFechaPago;
				}
			}			
		}
		
		return ultimoPago;
	}
	
	public ActionForward consultarDetalleConvenio(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		log.info("Convenio Inicio: :::::::::");
		String idConvenio =request.getParameter("idConvenio");
		log.info("Convenio ID: :::::::::"+idConvenio);
		ConvenioDTO convenioDTO = new ConvenioDTO();
		convenioDTO.setConvenioID(NumberUtils.toLong(idConvenio));
		
		try {
			convenioDTO = convenioDelegate.consultarDetalleConvenio(convenioDTO);
			
			log.info("Convenio Dto del convenio respuesta: :::::::::"+ convenioDTO);
			convenioDTO.setFechaFinString(DateUtils.formatear(convenioDTO.getFechaFin()));
			convenioDTO.setFechaInicioString(DateUtils.formatear(convenioDTO.getFechaInicio()));
			converter.alias("convenioDTO", ConvenioDTO.class);
			String xml = converter.toXML(convenioDTO);
			log.info("xml del convenio respuesta: :::::::::"+ xml);
			
			escribir(response, xml,null);												
			
		} catch (Exception e) {		
			log.info("ERROR AL CONSULTAR CONVENIO ---- consultarDetalleConvenio");
			log.info(e.getCause(),e);
			escribir(response, "consultarPrimerConvenio",null);
			
		}
		return null;
	}
	
	
	public ActionForward actualizaConvenio(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
		//idConvenio cumplioConvenio fCumplimiento hCumplimiento descripcionConvenio
		log.info("Convenio Inicio: :::::::::");
		String idConvenio =request.getParameter("idConvenio");
		log.info("Convenio ID: :::::::::"+idConvenio);
		String fechaCompromisoid =request.getParameter("fechaCompromisoid");
		log.info("Convenio fechaCompromisoid: :::::::::"+fechaCompromisoid);
		//String cumplioConvenio =request.getParameter("cumplioConvenio");
		//log.info("Convenio cumplioConvenio: :::::::::"+cumplioConvenio);
		String fCumplimiento =request.getParameter("fCumplimiento");
		log.info("Convenio fCumplimiento: :::::::::"+fCumplimiento);
		String hCumplimiento =request.getParameter("hCumplimiento");
		log.info("Convenio hCumplimiento: :::::::::"+hCumplimiento);
		String descripcionConvenio =request.getParameter("descripcionConvenio");
		log.info("Convenio descripcionConvenio: :::::::::"+descripcionConvenio);
		
		
		ConvenioDTO convenioDTO = new ConvenioDTO();
		//CompromisoPeriodicoDTO compromisoPeriodicoDTO = new CompromisoPeriodicoDTO();
		FechaCompromisoDTO fechaCompromisoDTO = new FechaCompromisoDTO();
		convenioDTO.setConvenioID(NumberUtils.toLong(idConvenio));
		//convenioDTO.set
		
		fechaCompromisoDTO.setFechaCompromisoId(NumberUtils.toLong(fechaCompromisoid));
		fechaCompromisoDTO.setFechaCumplimiento(DateUtils.obtener(fCumplimiento, hCumplimiento));
		fechaCompromisoDTO.setObservaciones(descripcionConvenio);
		
		
		
		
		
			convenioDelegate.actualizarFechaCompromido(fechaCompromisoDTO);
			//Long ActualconvenioId = convenioDelegate.guardarAcuerdoRestaurativo(convenioDTO);
			
			log.info("Convenio Dto del convenio respuesta: :::::::::"+ convenioDTO);
			
			converter.alias("fechaCompromisoDTO", ConvenioDTO.class);
			String xml = converter.toXML(fechaCompromisoDTO);
			log.info("xml del convenio respuesta: :::::::::"+ xml);
			
			escribir(response, xml,null);												
			
		} catch (Exception e) {		
			log.info("ERROR AL CONSULTAR CONVENIO ---- consultarDetalleConvenio");
			log.info(e.getCause(),e);
			escribir(response, "consultarPrimerConvenio",null);
			
		}
		return null;
	}
	
	/**
	 * Permite consultar las solicitudes de almacen
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * 
	 */
	public ActionForward consultarPagosDeDetalleConvenio(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {		
		try {
			
			String idConvenio =request.getParameter("idConvenio");
			ConvenioDTO loConvenioDTO = new ConvenioDTO(); 
			loConvenioDTO.setConvenioID(Long.parseLong(idConvenio));
			ConvenioDTO conveniosBD = convenioDelegate.consultarDetalleConvenio(loConvenioDTO);
			
			List<FechaCompromisoDTO> fechasCompromiso = null;
			//Se obtiene las fechas compromiso
			fechasCompromiso = conveniosBD.getCompromisoPeriodicoDTO().getFechaCompromisos();
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();		
			writer.print("<rows>");
			writer.print("<records>" + "desconocido" + "</records>");	
			
			for (FechaCompromisoDTO dto : fechasCompromiso) {		
				writer.print("<row id='"+dto.getFechaCompromisoId()+"'>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ (dto.getMonto() != null? dto.getMonto() :"-") +" </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ (dto.getFechaCompromiso() != null? DateUtils.formatear(dto.getFechaCompromiso()) :"-") +" </div>]]></cell>");
				if(dto.getFechaCumplimiento() != null){
					writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'><input type='checkbox' checked='checked' disabled='disabled'> </div>]]></cell>");
				}else 
					{writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'><input type='checkbox' disabled='disabled'></div>]]></cell>"); }
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ (dto.getFechaCumplimiento() != null? DateUtils.formatear(dto.getFechaCumplimiento())+" "+DateUtils.formatearHora((dto.getFechaCompromiso())) :"-") +" </div>]]></cell>");
				writer.print("<cell><![CDATA[<div style='background-color: #f2f2f2; color:#393939;'>"+ (dto.getObservaciones() != null? dto.getObservaciones() :"-") +" </div>]]></cell>");
				writer.print("</row>");
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
	
	public ActionForward horaActual(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		try {
		//idConvenio cumplioConvenio fCumplimiento hCumplimiento descripcionConvenio
		String hora= DateUtils.formatear(new Date());
		String fecha =DateUtils.formatearHora(new Date()); 
		
		
		String horayFecha =hora+","+fecha;
		
		
		
			
			//Long ActualconvenioId = convenioDelegate.guardarAcuerdoRestaurativo(convenioDTO);
			
			log.info("Convenio Hora Fecha Actual: :::::::::"+ horayFecha);
			
			converter.alias("horayFecha", Date.class);
			String xml = converter.toXML(horayFecha);
			log.info("xml del convenio respuesta horayFecha: :::::::::"+ xml);
			
			escribir(response, xml,null);												
			
		} catch (Exception e) {		
			log.info("ERROR AL CONSULTAR CONVENIO ---- consultarDetalleConvenio");
			log.info(e.getCause(),e);
			escribir(response, "consultarPrimerConvenio",null);
			
		}
		return null;
	}
	
	
	public ActionForward consultarPagoPorId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		log.info("Convenio consultarPagoPorId: :::::::::");
		String idFechaCompromiso =request.getParameter("idFechaCompromiso");
		log.info("Convenio ID: :::::::::"+idFechaCompromiso);
		
		FechaCompromisoDTO fechaCompromisoDTO=new FechaCompromisoDTO();
		if(idFechaCompromiso!=null && !idFechaCompromiso.equals("")){
			fechaCompromisoDTO.setFechaCompromisoId(Long.parseLong(idFechaCompromiso));
		}
		
		try {
			fechaCompromisoDTO = convenioDelegate.consultarPagoXId(fechaCompromisoDTO);
			
			log.info("Convenio Dto del fechaCompromisoDTO respuesta: :::::::::"+ fechaCompromisoDTO);
			fechaCompromisoDTO.setFechaCumplimientoString(DateUtils.formatear(fechaCompromisoDTO.getFechaCumplimiento()));
			fechaCompromisoDTO.setHoraCumplimientoString(DateUtils.formatearHora(fechaCompromisoDTO.getFechaCumplimiento()));
			
			converter.alias("fechaCompromisoDTO", FechaCompromisoDTO.class);
			String xml = converter.toXML(fechaCompromisoDTO);
			log.info("xml del convenio respuesta: :::::::::"+ xml);
			
			escribir(response, xml,null);												
			
		} catch (Exception e) {		
			log.info("ERROR AL CONSULTAR CONVENIO ---- consultarPagoPorId");
			log.info(e.getCause(),e);
			escribir(response, "consultarPagoPorId",null);
			
		}
		return null;
	}
	
	
	
}
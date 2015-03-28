package mx.gob.segob.nsjp.web.expdiente.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.delegate.expediente.ExpedienteDelegate;
import mx.gob.segob.nsjp.delegate.funcionario.FuncionarioDelegate;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

public class AsignarPermisosAFuncionarioSobreExpedienteAction extends
		GenericAction {
	private static final Logger logger = Logger.getLogger(AsignarPermisosAFuncionarioSobreExpedienteAction.class);
	
	@Autowired
	private FuncionarioDelegate funcionarioDelegate;
	@Autowired
	private ExpedienteDelegate expedienteDelegate;
	
	public ActionForward cargarGridExpedientesPropios(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			Long claveFuncionario = NumberUtils.toLong(request.getParameter("claveFuncioanrio"),0);
			logger.info("cargarGridExpedientesPropios - ejecuantdo action");
			List<ExpedienteDTO> expedientes = null;
			
			UsuarioDTO usuarioFirmado = getUsuarioFirmado(request);
			
			//expedientes = funcionarioDelegate.consultarExpedientesDelFuncionario(claveFuncionario);
			expedientes = funcionarioDelegate.consultarExpedientesDelFuncionario(usuarioFirmado);
			
			converter.alias("listaExpPropios", java.util.List.class);
			converter.alias("expedienteDTO", ExpedienteDTO.class);
			if(logger.isDebugEnabled())
			{
				//logger.info("Expedientes_propios_grid:: "+converter.toXML(expedientes));
				logger.info("Funcionario:: "+claveFuncionario+" - Total_exp_propios_cargarGridExpedientesPropios:: "+expedientes.size());
			}
			
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter writer = response.getWriter();
			writer.print("<rows>");		
			
			final PaginacionDTO pag = PaginacionThreadHolder.get();
			logger.info("cargarGridExpedientesPropios_paginas:: "+pag.getTotalPaginas()+" - renglones:: "+pag.getTotalRegistros());
            if (pag != null && pag.getTotalRegistros() != null) {
            	logger.info("SI hay registros_cargarGridExpedientesPropios");
                writer.print("<total>" + pag.getTotalPaginas() + "</total>");
                writer.print("<records>" + pag.getTotalRegistros() + "</records>");
            } else {
            	logger.info("NO hay registros_cargarGridExpedientesPropios");
                writer.print("<total>0</total>");
                writer.print("<records>0</records>");
            }	
            
			for (ExpedienteDTO expediente : expedientes) {
				writer.print("<row id='"+expediente.getNumeroExpedienteId()+"'>");
				writer.print("<cell>"+expediente.getNumeroExpediente()+"</cell>");
				writer.print("<cell>"+DateUtils.formatear(expediente.getFechaApertura())+"</cell>");
				List<InvolucradoDTO> denunciantes = expediente.getInvolucradoByCalidad(Calidades.DENUNCIANTE);
				
				if(denunciantes != null && !denunciantes.isEmpty()){
					writer.print("<cell>"+denunciantes.get(0).getNombreCompleto()+"</cell>");
				}else{
					writer.print("<cell>-</cell>");
				}
				DelitoDTO delito = expediente.getDelitoPrincipal();
				if(delito == null && expediente.getDelitos() != null && !expediente.getDelitos().isEmpty()){
					delito = expediente.getDelitos().get(0);
				}
				if(delito != null && delito.getCatDelitoDTO() != null){
					writer.print("<cell>"+delito.getCatDelitoDTO().getNombre()+"</cell>");
				}else{
					writer.print("<cell>-</cell>");
				}
				if(expediente.getOrigen() != null){
					writer.print("<cell>"+expediente.getOrigen().getValor()+"</cell>");
				}else{
					writer.print("<cell> - </cell>");
				}
				writer.print("<cell>"+expediente.getEstatus().getValor()+"</cell>");
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
	
	public ActionForward cargarGridSubordinados(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			
			Long claveFuncionario = NumberUtils.toLong(request.getParameter("claveFuncionario"),0);
			FuncionarioDTO funcionarioDTO = new FuncionarioDTO(claveFuncionario);
			List<FuncionarioDTO> funcionarios = Collections.emptyList();
			funcionarios = funcionarioDelegate.consultarFuncionariosSubordinados(funcionarioDTO);
			
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
            
			for (FuncionarioDTO funcionario : funcionarios) {
				logger.info("id_funcionario_cargarGridSubordinados:: "+funcionario.getClaveFuncionario());
				writer.print("<row id='"+funcionario.getClaveFuncionario()+"'>");
				writer.print("<cell>"+funcionario.getNombreCompleto()+"</cell>");
				if(funcionario.getJerarquiaOrganizacional() != null){
					writer.print("<cell>"+funcionario.getJerarquiaOrganizacional().getNombre()+"</cell>");
				}else{
					writer.print("<cell>-</cell>");
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
	
	public ActionForward cargarGridExpedientesFuncionario(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			Long claveFuncionario = NumberUtils.toLong(request.getParameter("claveFuncionario"),0);
			logger.info("cargarGridExpedientesFuncionario_asignarPermisosAFuncionario:: "+request.getParameter("claveFuncionario"));
			List<ExpedienteDTO> expedientes = null;
			
			expedientes = funcionarioDelegate.consultarExpedientescConPermisoFuncionario(claveFuncionario);
			
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
            UsuarioDTO usuario = null;
            FuncionarioDTO funcionario = null;
			for (ExpedienteDTO expediente : expedientes) {
				writer.print("<row id='"+expediente.getNumeroExpedienteId()+"'>");
				usuario = expediente.getUsuario();
				writer.print("<cell>"+expediente.getNumeroExpediente()+"</cell>");

				writer.print("<cell>"+DateUtils.formatear(expediente.getFechaLimitePermiso())+"</cell>");
				
				writer.print("<cell> SI </cell>");
				
				if(expediente.isEsEscritura()){
					writer.print("<cell> SI </cell>");
				}else{
					writer.print("<cell> NO </cell>");
				}
				writer.print("</row>");
			}
			
			writer.print("</rows>");
			writer.flush();
			writer.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return mapping.findForward("success");
	}
	
	public ActionForward asignarPermisosSobreExpedinte(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			Long claveFuncionario = NumberUtils.toLong(request.getParameter("claveFuncionario"),0);
			Long numeroExpediente = NumberUtils.toLong(request.getParameter("numeroExpediente"),0);
			Date fechaVencimietno = DateUtils.obtener(request.getParameter("fechaVencimiento"));
			Boolean permiso = BooleanUtils.toBoolean(request.getParameter("escritura"), "true", "false");
			expedienteDelegate.asignarPermisoExpedienteFuncionario(claveFuncionario, numeroExpediente, fechaVencimietno, permiso);

			escribir(response, "Se guardó el permiso con éxito", null);
			
		} catch (Exception e) {
			escribir(response, "Se produjo un error al guardar.", null);
			logger.error(e.getMessage(), e);
		}
		return mapping.findForward("success");
	}
	
	public ActionForward eliminarPermisosSobreExpedinte(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			Long claveFuncionario = NumberUtils.toLong(request.getParameter("claveFuncionario"),0);
			Long numeroExpediente = NumberUtils.toLong(request.getParameter("numeroExpediente"),0);
			expedienteDelegate.eliminarPermisoExpedienteFuncionario(claveFuncionario, numeroExpediente);

			escribir(response, "Se eliminó el permiso con éxito", null);
		} catch (Exception e) {
			escribir(response, "Se produjo un error al eliminar.", null);
			logger.error(e.getMessage(), e);
		}
		return mapping.findForward("success");
	}
}

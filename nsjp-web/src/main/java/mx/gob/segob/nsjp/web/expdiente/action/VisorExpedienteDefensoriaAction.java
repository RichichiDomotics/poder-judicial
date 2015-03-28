package mx.gob.segob.nsjp.web.expdiente.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.catalogo.Catalogos;
import mx.gob.segob.nsjp.delegate.catalogo.CatalogoDelegate;
import mx.gob.segob.nsjp.delegate.expediente.ExpedienteDelegate;
import mx.gob.segob.nsjp.delegate.involucrado.InvolucradoDelegate;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

public class VisorExpedienteDefensoriaAction extends GenericAction {

	private static final Logger logger = Logger.getLogger(VisorExpedienteDefensoriaAction.class);

	@Autowired
	private ExpedienteDelegate expedienteDelegate;
	@Autowired
	private InvolucradoDelegate involucradoDelegate;
	@Autowired
	private CatalogoDelegate catalogoDelegate;
	
	public ActionForward visorExpedienteDefensoria(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			
			ExpedienteDTO expedienteDTO = new ExpedienteDTO();
			Long idNumeroExpediente = NumberUtils.toLong(request.getParameter("idNumeroExpediente"), 0);
			if(idNumeroExpediente > 0){
				expedienteDTO.setNumeroExpedienteId(idNumeroExpediente);
				expedienteDTO.setInvolucradosSolicitados(true);
				expedienteDTO.setAvisosDesignacionSolicitados(true);
				expedienteDTO = expedienteDelegate.obtenerExpediente(expedienteDTO);
				setExpedienteTrabajo(request, expedienteDTO);
				request.setAttribute("expediente", expedienteDTO);
				request.getSession().setAttribute("expedienteId", expedienteDTO.getExpedienteId());
				return mapping.findForward("success");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return mapping.findForward("error");
	}
	
	public ActionForward consultarInvolucradoDefensoria(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try{
			Long idInvolucrado = NumberUtils.toLong(request.getParameter("idInvolucrado"), 0);
			if(idInvolucrado > 0){
				InvolucradoDTO involucrado = new InvolucradoDTO();
				involucrado.setElementoId(idInvolucrado);
				involucrado = involucradoDelegate.obtenerInvolucrado(involucrado);
				request.setAttribute("involucrado", involucrado);
			}
			List<CatalogoDTO> idiomas = catalogoDelegate.recuperarCatalogo(Catalogos.IDIOMA);
			List<CatalogoDTO> escolaridad = catalogoDelegate.recuperarCatalogo(Catalogos.ESCOLARIDAD);
			List<CatalogoDTO> estadoCivil = catalogoDelegate.recuperarCatalogo(Catalogos.ESTADO_CIVIL);
			List<CatalogoDTO> nacionalidad = catalogoDelegate.recuperarCatalogo(Catalogos.NACIONALIDAD);
			List<CatalogoDTO> ocupacion = catalogoDelegate.recuperarCatalogo(Catalogos.OCUPACION);
			request.setAttribute("consultar", true);
			request.setAttribute("idiomas", idiomas);
			request.setAttribute("ocupacion", ocupacion);
			request.setAttribute("escolaridad", escolaridad);
			request.setAttribute("estadoCivil", estadoCivil);
			request.setAttribute("nacionalidad", nacionalidad);
			return mapping.findForward("success");
		}catch(Exception e){
		
			return mapping.findForward("error");
		}
	} 
}

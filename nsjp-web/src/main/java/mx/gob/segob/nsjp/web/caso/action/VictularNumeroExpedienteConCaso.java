package mx.gob.segob.nsjp.web.caso.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.delegate.caso.CasoDelegate;
import mx.gob.segob.nsjp.dto.caso.FiltroCasoDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

public class VictularNumeroExpedienteConCaso extends GenericAction {

	@Autowired
	private CasoDelegate casoDelegate;
	
	public ActionForward consultarCasosPorFiltro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
//		casoDelegate.consultarCasosEnSistema();
		return null;
		
	}
		
}

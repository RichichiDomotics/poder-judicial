package mx.gob.segob.nsjp.almacen.action;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.gob.segob.nsjp.web.base.action.GenericAction;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Jacob Lobaco
 */
public class AdministrarEntradasDeAlmacenAction extends GenericAction {

    private static final Logger logger =
            Logger.getLogger(AdministrarEntradasDeAlmacenAction.class);

    public ActionForward consultarCatalogoTipoAlmacen(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if (logger.isDebugEnabled()) {
            logger.debug("consultarCatalogoTipoAlmacen");
        }
        return null;
    }
}

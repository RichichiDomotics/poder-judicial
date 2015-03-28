package mx.gob.segob.nsjp.almacen.action;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.almacen.AlmacenDelegate;
import mx.gob.segob.nsjp.delegate.cadenadecustodia.CadenaDeCustodiaDelegate;
import mx.gob.segob.nsjp.delegate.evidencia.EvidenciaDelegate;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;
import mx.gob.segob.nsjp.web.notificacion.action.ConsultarNotificacionesAction;
import mx.gob.segob.nsjp.web.notificacion.action.Grid;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Jacob Lobaco
 */
public class AdministrarSalidasDeAlmacenAction extends GenericAction {

    private static final Logger logger =
            Logger.getLogger(AdministrarSalidasDeAlmacenAction.class);
    @Autowired
    private AlmacenDelegate almacenDelegate;
    @Autowired
    private CadenaDeCustodiaDelegate cadenaDeCustodiaDelegate;
    @Autowired
    private EvidenciaDelegate evidenciaDelegate;

    public ActionForward consultarEvidenciasXCadenaCustodiaASDA(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("consultarEvidenciasXCadenaCustodiaASDA");
            }
            String parametroIdCadenaDeCustodia = request.getParameter("idCadenaDeCustodia");
            Long idCadenaDeCustodia = ConsultarNotificacionesAction.parsea(parametroIdCadenaDeCustodia, 0L);
            CadenaDeCustodiaDTO cadenaDeCustodiaDto = new CadenaDeCustodiaDTO();
            cadenaDeCustodiaDto.setCadenaDeCustodiaId(idCadenaDeCustodia);
            List<EvidenciaDTO> evidenciasDto =
                evidenciaDelegate.consultarEvidenciasXCadenaCustodia(cadenaDeCustodiaDto);
            Grid<EvidenciaDTO> paginaDeGrid =
                    new Grid<EvidenciaDTO>(0, evidenciasDto.size(), 10, evidenciasDto,
                    /**
                     * id
                     * número de la evidencia,
                     * información de la evidencia, ¿descripcion?
                     * origen de la evidencia,
                     * último eslabón asociado, ¿eslabonId?
                     * número de eslabón,
                     * tipo de eslabón,
                     * almacén donde se encuentra la evidencia,
                     * estado de la evidencia.
                     */
                    "evidenciaId",
                    "numeroEvidencia",
                    "descripcion",
                    "origenEvidencia",
                    "ultimoEslabon.eslabonId",
                    "ultimoEslabon.numeroEslabon",
                    "ultimoEslabon.tipoEslabon.valor",
                    "objeto.almacen.nombreAlmacen",
                    "estatus.valor");
            if (logger.isDebugEnabled()) {
                logger.debug("paginaDeGrid = " + paginaDeGrid);
            }
            escribirRespuesta(response, paginaDeGrid.toString());
        } catch (NSJPNegocioException ex) {
            java.util.logging.Logger.getLogger(AdministrarSalidasDeAlmacenAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public ActionForward consultarCadenaCustodiaXFolio(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("consultarCadenaCustodiaXFolio");
            }
            String obtenFolioCadenaDeCustodia = obtenFolioCadenaDeCustodia(request);
            CadenaDeCustodiaDTO cadenaDeCustodiaDto =
                    cadenaDeCustodiaDelegate.consultarCadenaCustodiaXFolio(obtenFolioCadenaDeCustodia);
            if (logger.isDebugEnabled()) {
                logger.debug("cadenaDeCustodiaDto = " + cadenaDeCustodiaDto);
            }
            if (cadenaDeCustodiaDto != null) {
                escribirRespuesta(response, "<id>" + cadenaDeCustodiaDto.getCadenaDeCustodiaId() + "</id>");
            } else {
                escribirRespuesta(response, "<id>" + -1 + "</id>");
            }
        } catch (NSJPNegocioException ex) {
            java.util.logging.Logger.getLogger(AdministrarSalidasDeAlmacenAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private String obtenFolioCadenaDeCustodia(HttpServletRequest request) {
        String folioCadenaDeCustodia = request.getParameter("folioCadenaDeCustodia");
        if (logger.isDebugEnabled()) {
            logger.debug("folioCadenaDeCustodia = " + folioCadenaDeCustodia);
        }
        folioCadenaDeCustodia = folioCadenaDeCustodia == null ? "" : folioCadenaDeCustodia;
        return folioCadenaDeCustodia;
    }
}

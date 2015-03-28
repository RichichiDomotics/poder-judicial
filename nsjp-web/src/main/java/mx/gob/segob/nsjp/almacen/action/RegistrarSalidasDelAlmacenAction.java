package mx.gob.segob.nsjp.almacen.action;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.almacen.AlmacenDelegate;
import mx.gob.segob.nsjp.delegate.evidencia.EvidenciaDelegate;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;
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
public class RegistrarSalidasDelAlmacenAction extends GenericAction {

    private static final Logger logger =
            Logger.getLogger(RegistrarSalidasDelAlmacenAction.class);
    @Autowired
    private EvidenciaDelegate evidenciaDelegate;

    @Autowired
    private AlmacenDelegate almacenDelegate;

    public ActionForward consultarEvidenciasEnAlmacen(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("consultarEvidenciasEnAlmacen");
            }
            String[] evidenciasId = request.getParameterValues("evidenciaId");
            if (logger.isDebugEnabled()) {
                logger.debug("evidenciasId = " + evidenciasId);
                if (evidenciasId != null) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("evidenciasId.length = " + evidenciasId.length);
                    }
                }
            }
            almacenDelegate.consultarEvidenciaPorAlmacen(new AlmacenDTO());
            List<EvidenciaDTO> evidenciasDto = Collections.EMPTY_LIST;
            if (evidenciasId != null) {
                evidenciasDto = new LinkedList<EvidenciaDTO>();
                for (String evidenciaId : evidenciasId) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("evidenciaId = " + evidenciaId);
                    }
                    Long idEvidencia = Long.parseLong(evidenciaId);
                    EvidenciaDTO evidenciaDto = evidenciaDelegate.consultaEvidencia(idEvidencia);
                    evidenciasDto.add(evidenciaDto);
                }
            }
            Grid<EvidenciaDTO> gridEvidencias = new Grid<EvidenciaDTO>(0, evidenciasDto.size(),10, evidenciasDto,
                    "evidenciaId",
                    "numeroEvidencia",
                    "descripcion",
                    "origenEvidencia",
                    "ultimoEslabon.eslabonId",
                    "ultimoEslabon.numeroEslabon",
                    "ultimoEslabon.tipoEslabon.valor",
                    "objeto.almacen.nombreAlmacen");
            if (logger.isDebugEnabled()) {
                logger.debug("gridEvidencias = " + gridEvidencias);
            }
            escribirRespuesta(response, gridEvidencias.toString());
        } catch (NSJPNegocioException ex) {
            java.util.logging.Logger.getLogger(RegistrarSalidasDelAlmacenAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

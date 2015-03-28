package mx.gob.segob.nsjp.web.notificacion.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.notificacion.NotificacionDelegate;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.NotificacionDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Jacob Lobaco
 */
public class ConsultarNotificacionesAction extends GenericAction {

    private static final Logger logger =
            Logger.getLogger(ConsultarNotificacionesAction.class);
    @Autowired
    private NotificacionDelegate notificacionDelegate;

    public ActionForward consultarNumeroTotalDeNotificacionesXUsuario(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        return null;
    }

    public ActionForward consultarNotificacion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            NotificacionDTO consulta = new NotificacionDTO();
            String idNotificacionParam = request.getParameter("idNotificacion");
            if (idNotificacionParam == null) {
                idNotificacionParam = "";
            }
            Long idNotificacion = parsea(idNotificacionParam, 0);
            consulta.setDocumentoId(idNotificacion);
            NotificacionDTO notificacion = notificacionDelegate.consultaNotificacion(consulta);
            converter.alias("notificacion", NotificacionDTO.class);
            String notificacionXml = converter.toXML(notificacion);
            escribirRespuesta(response, notificacionXml);
        } catch (NSJPNegocioException ex) {
            logger.error("Ocurrio un error ejecutando consultarNotificacion", ex);
        }
        return null;
    }

    public ActionForward consultarNotificacionesXUsuario(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            /**
             * Obtenemos los parametros enviados por el grid para consultar por
             * paginas las notificaciones de un usuario.
             */
            // pagina requerida, por default consultamos la 1
            String pagina = request.getParameter("page") != null ? request.getParameter("page") : "1";
            // numero de registros que se van a consultar, por default 10
            String numeroDeRegistrosBuscados = request.getParameter("rows") != null
                    ? request.getParameter("rows") : "10";
            // la columna por la cual se ordenan los resultados por default por numeroDeCaso
            String columnaDeOrden = request.getParameter("sidx") != null
                    ? request.getParameter("sidx") : "numeroDeCaso";
            // Indica si el orden es descendente o ascendente, default desc
            String direccion = request.getParameter("sord") != null
                    ? request.getParameter("sord") : "desc";
            if (logger.isDebugEnabled()) {
                logger.debug("pagina = " + pagina);
                logger.debug("numeroDeRegistrosBuscados = " + numeroDeRegistrosBuscados);
                logger.debug("ordenIndicadoPorElUsuario = " + columnaDeOrden);
                logger.debug("direccion = " + direccion);
            }
            long paginaInt = parsea(pagina, 1);
            long numeroDeRegistrosPorPagina = parsea(numeroDeRegistrosBuscados, 10);
            int direccionInt = 0;
            if (!direccion.equals("desc")) {
                direccionInt = 1;
            }
            ValorDTO estatus = new ValorDTO(1775L);
            FuncionarioDTO funcionario = getUsuarioFirmado(request).getFuncionario();
            long numeroTotalNotificaciones = notificacionDelegate.consultarNumeroTotalDeNotificacionesXUsuario(funcionario, estatus);
            List<NotificacionDTO> notificaciones = notificacionDelegate.consultarNotificacionesXUsuario(funcionario, estatus, (int) paginaInt,
                    (int) numeroDeRegistrosPorPagina, columnaDeOrden, direccionInt);
            if (logger.isDebugEnabled()) {
                logger.debug("numeroTotalNotificaciones = " + numeroTotalNotificaciones);
                logger.debug("numeroDeRegistrosPorPagina = " + numeroDeRegistrosPorPagina);
                logger.debug("numeroTotalNotificaciones / (long)numeroDeRegistrosPorPagina = " + (numeroTotalNotificaciones / (double) numeroDeRegistrosPorPagina));
                logger.debug("(int)Math.ceil(numeroTotalNotificaciones / (long)numeroDeRegistrosPorPagina) = " + (int) Math.ceil(numeroTotalNotificaciones / (double) numeroDeRegistrosPorPagina));
            }
            long paginaNumero = paginaInt;
            int numeroDeRenglones = notificaciones.size();
            String rowsInfo = "";
            rowsInfo += "<rows>\n";
            rowsInfo += "<page>" + paginaNumero + "</page>\n";
            rowsInfo += "<total>" + (int) Math.ceil(numeroTotalNotificaciones / (double) numeroDeRegistrosPorPagina) + "</total>\n";
            rowsInfo += "<records>" + numeroTotalNotificaciones + "</records>\n";
            String rowsXml = "";
            int numeroElementosProcesados = 0;
            if (numeroElementosProcesados++ < numeroDeRegistrosPorPagina) {
                for (NotificacionDTO notificacionDto : notificaciones) {
                    String idNotificacion = notificacionDto.getDocumentoId() + "";
                    ExpedienteDTO expedienteDTO = notificacionDto.getExpedienteDTO();
                    String numeroDecaso = "";
                    String numeroExpediente = notificacionDto.getNumeroExpedienteUsuarioNotif();
                    if (expedienteDTO != null) {
                        if (expedienteDTO.getCasoDTO() != null) {
                            numeroDecaso = expedienteDTO.getCasoDTO().getNumeroGeneralCaso();
                        }
                    }
                    String asunto = notificacionDto.getMotivo();
                    String fechaHoraElaboracion = notificacionDto.getFechaCreacion() + "";
                    rowsXml += Grid.creaRow(idNotificacion, numeroDecaso, numeroExpediente, asunto, fechaHoraElaboracion);
                }
            }
            String xmlFinal = rowsInfo + rowsXml + "</rows>";
            if (logger.isDebugEnabled()) {
                logger.debug("enviado = " + xmlFinal);
            }
            PrintWriter writer = response.getWriter();
            writer.write(xmlFinal);
            writer.flush();
            writer.close();
        } catch (NSJPNegocioException ex) {
            logger.error("Ocurrio un error ejecutando consultarNotificacionesXUsuario", ex);
        }
        return null;
    }

    

    public static long parsea(String pagina, long inicial) {
        long paginaInt = inicial;
        try {
            paginaInt = Integer.parseInt(pagina);
        } catch (NumberFormatException nfe) {
//            logger.error("pagina = " + pagina, nfe);
        }
        return paginaInt;
    }
}

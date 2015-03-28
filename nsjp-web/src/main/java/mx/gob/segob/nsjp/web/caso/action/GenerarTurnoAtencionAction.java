/**
 * Nombre del Programa : GenerarTurnoAtencionAction.java
 * Autor               : Arturo Leon
 * Compania            : Ultrasist
 * Proyecto            : NSJP                    Fecha: 01/04/2011
 * Marca de cambio     : N/A
 * Descripcion General : Clase Action con los metodos usados para dar funcionalidad a la pantalla Generar turno de atencion
 * Programa Dependiente: N/A
 * Programa Subsecuente: N/A
 * Cond. de ejecucion  : N/A
 * Dias de ejecucion   : N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor               :N/A
 * Compania            :N/A
 * Proyecto            :N/A                                   Fecha: N/A
 * Modificacion        :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.web.caso.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.expediente.TipoTurno;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.delegate.expediente.TurnoDelegate;
import mx.gob.segob.nsjp.dto.expediente.TurnoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.ReporteBaseAction;

import org.apache.commons.lang.BooleanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * Clase Action con los metodos usados para dar funcionalidad a la pantalla
 * Generar turno de atencion.
 * 
 * @version 1.0
 * @author Arturo Leon
 */
public class GenerarTurnoAtencionAction extends ReporteBaseAction {
    private static final Logger log = Logger
            .getLogger(GenerarTurnoAtencionAction.class);

    @Autowired
    private TurnoDelegate turnoDelegate;

    private final static String KEY_SESSION_TURNO_GENERADO = "KEY_SESSION_TURNO_GENERADO";

    /**
     * Metodo utilizado para generar un nuevo turno
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public ActionForward generarTurno(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            log.info("ejecutando Action generarTurno");

            Integer tipoTurno = Integer.parseInt(request
                    .getParameter("tipoTurno"));
            Boolean esUrgente = Boolean.parseBoolean(request
                    .getParameter("esUrgente"));

            if (log.isDebugEnabled()) {

                log.debug("EsUrgente::" + esUrgente);
                log.debug("Turno::" + tipoTurno);

            }

            TurnoDTO turnoDTO = new TurnoDTO();
            turnoDTO.setTipoTurno(TipoTurno.values()[tipoTurno]);
            turnoDTO.setEsUrgente(esUrgente);

            // turnoDTO.setTurnoId(forma.getTurnoId());
            // turnoDTO.setNumeroTurno(forma.getNumeroTurno());
            // turnoDTO.setEstado(forma.getEstado());
            // turnoDTO.setEsUrgente(forma.getEsUrgente());
            // turnoDTO.setFichaAtencion(forma.getFichaAtencion());

            if (log.isDebugEnabled()) {
                log.debug("turnoDelegate::" + turnoDelegate);
                log.debug("turnoDTO::" + turnoDTO);
                log.debug("turnoDTO.get::" + turnoDTO.getTipoTurno());
            }
            
            //Para las agencias de PGJ
            UsuarioDTO usuario = getUsuarioFirmado(request);
            turnoDTO.setUsuario(usuario);
            
            turnoDTO = turnoDelegate.generarTurnoAtencion(turnoDTO);

            if (log.isDebugEnabled()) {
                try {
                    log.debug("turnoDTO.get jorge::"
                            + turnoDTO.getNumeroTurno());
                } catch (Exception e) {
                    log.info(e.getCause(), e);
                }
            }
            request.getSession().setAttribute(KEY_SESSION_TURNO_GENERADO,
                    turnoDTO);
            converter.alias("turnoDTO", TurnoDTO.class);
            log.info("ejecutando Action generarTurno2");
            String xml = converter.toXML(turnoDTO);
            log.info("turnoDTO - generaTurno: "+xml);
            // super.escribirRespuesta(response, xml);
            response.setContentType("text/xml");
            PrintWriter pw = response.getWriter();
            pw.print(xml);
            pw.flush();
            pw.close();

            if (log.isDebugEnabled()) {

                log.debug("geturno::" + turnoDTO.getNumeroTurno());

            }

        } catch (Exception e) {
            log.error(e.getCause(), e);

        }
        return null;
    }
    
        
    /**
     * Metodo utilizado para generar un nuevo turno
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public ActionForward generarConsultarTurnoAtencion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            log.info("ejecutando Action generarTurno");

            Integer tipoTurno = Integer.parseInt(request
                    .getParameter("tipoTurno"));
            Boolean esUrgente = Boolean.parseBoolean(request
                    .getParameter("esUrgente"));

            if (log.isDebugEnabled()) {

                log.debug("EsUrgente::" + esUrgente);
                log.debug("Turno::" + tipoTurno);

            }

            TurnoDTO turnoDTO = new TurnoDTO();
            turnoDTO.setTipoTurno(TipoTurno.values()[tipoTurno]);
            turnoDTO.setEsUrgente(esUrgente);

            if (log.isDebugEnabled()) {
                log.debug("turnoDelegate::" + turnoDelegate);
                log.debug("turnoDTO::" + turnoDTO);
                log.debug("turnoDTO.get::" + turnoDTO.getTipoTurno());
            }
            //Inicia solucion de inicidencia con folio 20 Registro 1
            UsuarioDTO usuarioDTO =  getUsuarioFirmado(request);
            turnoDTO.setUsuario(usuarioDTO);
            //Finaliza solucion de incidencia con folio 20 Registro 1          
            turnoDTO = turnoDelegate.generarConsultarTurnoAtencion(turnoDTO);

            if (log.isDebugEnabled()) {
                try {
                    log.debug("turnoDTO.get jorge::"
                            + turnoDTO.getNumeroTurno());
                } catch (Exception e) {
                    log.info(e.getCause(), e);
                }
            }
            request.getSession().setAttribute(KEY_SESSION_TURNO_GENERADO,
                    turnoDTO);
            converter.alias("turnoDTO", TurnoDTO.class);
            log.info("ejecutando Action generarTurno2");
            String xml = converter.toXML(turnoDTO);
            log.info("turnoDTO - generaTurno: "+xml);
            // super.escribirRespuesta(response, xml);
            response.setContentType("text/xml");
            PrintWriter pw = response.getWriter();
            pw.print(xml);
            pw.flush();
            pw.close();

            if (log.isDebugEnabled()) {

                log.debug("geturno::" + turnoDTO.getNumeroTurno());

            }

        } catch (Exception e) {
            log.error(e.getCause(), e);

        }
        return null;
    }
    
    
    
    
    /**
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public ActionForward imprimirTurno(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
  
            
            log.info("ejecutando Action imprimirTurno");
            final TurnoDTO turnoDTO = (TurnoDTO) request.getSession()
                    .getAttribute(KEY_SESSION_TURNO_GENERADO);
            if (log.isDebugEnabled()) {
                log.debug("geturno::" + turnoDTO.getNumeroTurno());
            }
            if (turnoDTO != null) {

                if (log.isDebugEnabled()) {
                    log.debug("geturno::" + turnoDTO.getNumeroTurno());
                }
                Map<String, Object> parametrosReporte = new HashMap<String, Object>();

                parametrosReporte.put("no", turnoDTO.getNumeroTurno());
                parametrosReporte.put("tipoAtencion", turnoDTO.getTipoTurno()
                        .name());
                SimpleDateFormat sdf = new SimpleDateFormat(
                        "dd/MM/yyyy hh:mm:ss a");
                parametrosReporte.put("fechaHora", sdf.format(new Date()));
                if (BooleanUtils.isTrue(turnoDTO.getEsUrgente())) {
                    parametrosReporte.put("isUrgente", "Urgente");
                }

                ByteArrayOutputStream baos = super.generarReportePDF(
                        parametrosReporte, "/Turno.jasper");

                super.escribirReporte(response, baos,
                        "Turno-" + turnoDTO.getNumeroTurno() + ".pdf");}
        } catch (Exception e) {
            log.error(e.getCause(), e);

        }
        return null;
    }

    /**
     * Metodo utilizado para asociar un expediente con un turno generado
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public ActionForward asociarTurnoAlExpediente(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        //
        // try {
        // log.info("ejecutando Action asociar turno");
        // ArrayList<TurnoDTO> lstTurno = null;
        // lstTurno = turnoBDelegate.generarTurno();
        //
        // if(lstTurno != null){
        // for(int i=0;i<lstTurno.size();i++)
        // log.info(lstTurno.get(i).getGcNumeroTurno());
        // }
        // converter.alias("asociarturno", java.util.List.class);
        // converter.alias("aturno", TurnoDTO.class);
        //
        // String xml = converter.toXML(lstTurno);
        // response.setContentType("text/xml");
        // PrintWriter pw = response.getWriter();
        // pw.print(xml);
        // pw.flush();
        // pw.close();
        // }
        // catch (Exception e) {
        // log.info(e);
        // e.printStackTrace();
        // }
        return null;
    }

    /**
     * Metodo utilizado para realizar la consulta de los turno asigandos a una
     * unidad de investigacion
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public ActionForward consultarTurnosAsignadosAUnidadDeInvestigacion(
            ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        //
        // try {
        // log.info("ejecutando Action Consultar Turnos");
        // ArrayList<TurnoDTO> lstTurno = null;
        // lstTurno = turnoBDelegate.generarTurno();
        //
        // if(lstTurno != null){
        // for(int i=0;i<lstTurno.size();i++)
        // log.info(lstTurno.get(i).getGcNumeroTurno());
        // }
        // converter.alias("unidadturno", java.util.List.class);
        // converter.alias("uturno", TurnoDTO.class);
        //
        // String xml = converter.toXML(lstTurno);
        // response.setContentType("text/xml");
        // PrintWriter pw = response.getWriter();
        // pw.print(xml);
        // pw.flush();
        // pw.close();
        // }
        // catch (Exception e) {
        // log.info(e);
        // e.printStackTrace();
        // }
        return null;
    }

    /**
     * Metodo utilizado para consultar un expediente que tiene un turno asociado
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public ActionForward consultarExpedienteAsociadoAlTurno(
            ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        //
        // try {
        // log.info("ejecutando Action generarTurno");
        // ArrayList<TurnoDTO> lstTurno = null;
        // lstTurno = turnoBDelegate.generarTurno();
        //
        // if(lstTurno != null){
        // for(int i=0;i<lstTurno.size();i++)
        // log.info(lstTurno.get(i).getGcNumeroTurno());
        // }
        // converter.alias("expedienteturno", java.util.List.class);
        // converter.alias("eturno", TurnoDTO.class);
        //
        // String xml = converter.toXML(lstTurno);
        // response.setContentType("text/xml");
        // PrintWriter pw = response.getWriter();
        // pw.print(xml);
        // pw.flush();
        // pw.close();
        // }
        // catch (Exception e) {
        // log.info(e);
        // e.printStackTrace();
        // }
        return null;
    }
    /**
     * Crea un turno de atención creado en la ventanilla de atención del público
     * de Poder Judicial
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward generarTurnoAtPublicoPJ(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        try {

            TurnoDTO turnoDTO = new TurnoDTO();
            turnoDTO.setTipoTurno(TipoTurno.JUDICIAL);
            turnoDTO.setEsUrgente(false);
            turnoDTO.setNombreCiudadano(request
                    .getParameter("nombreSolAccPenalPrivada"));
            turnoDTO.setApellidoPaternoCiudadano(request
                    .getParameter("apPatSolAccPenalPrivada"));
            turnoDTO.setApellidoMaternoCiudadano(request
                    .getParameter("apMatSolAccPenalPrivada"));

            //Para las agencias de PGJ
            UsuarioDTO usuario = getUsuarioFirmado(request);
            log.debug("*_*Usuario Firmado::" +usuario);
            turnoDTO.setUsuario(usuario);
            turnoDTO = turnoDelegate.generarTurnoAtencion(turnoDTO);

            escribirRespuesta(response,
                    converter.toXML(turnoDTO.getNumeroTurno()));

        } catch (Exception e) {
            log.error(e.getCause(), e);

        }
        return null;
    }

    /**
     * Consulta los turnos no atendidos por el encargadod de causa de poder
     * judicial
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward consultarTurnosPendientesPJ(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        try {

            List<TurnoDTO> turnos = turnoDelegate
                    .obtenerTurnosPendientesPorTipo(TipoTurno.JUDICIAL);

            log.info("TURNO DE ATENCION=====================" + turnos);
            response.setContentType("text/xml; charset=UTF-8");
            response.setHeader("Cache-Control", "no-cache");
            PrintWriter writer = response.getWriter();

            writer.print("<rows>");
            writer.print("<records>" + turnos.size() + "</records>");
            for (TurnoDTO turno : turnos) {
                log.info("TURNO DE ATENCION ID====================="
                        + turno.getTurnoId());
                log.info("TURNO DE ATENCION FECHA====================="
                        + DateUtils.formatear(turno.getFechaAtencion()));
                log.info("TURNO DE ATENCION NOMBRE====================="
                        + turno.getNombreCompleto());
                writer.print("<row id='" + turno.getTurnoId() + "'>");
                writer.print("<cell>"
                        + DateUtils.formatear(turno.getFechaAtencion())
                        + "</cell>");
                writer.print("<cell>" + turno.getNombreCompleto() + "</cell>");
                writer.print("</row>");
            }
            writer.print("</rows>");
            writer.flush();
            writer.close();

        } catch (Exception e) {
            log.error(e.getCause(), e);
        }
        return null;
    }

}

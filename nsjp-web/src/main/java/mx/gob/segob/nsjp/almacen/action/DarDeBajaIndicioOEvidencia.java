package mx.gob.segob.nsjp.almacen.action;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.gob.segob.nsjp.comun.enums.catalogo.Catalogos;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.catalogo.CatalogoDelegate;
import mx.gob.segob.nsjp.delegate.evidencia.EvidenciaDelegate;
import mx.gob.segob.nsjp.delegate.funcionario.FuncionarioDelegate;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
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
public class DarDeBajaIndicioOEvidencia extends GenericAction{

    private static final Logger logger =
            Logger.getLogger(DarDeBajaIndicioOEvidencia.class);
    
    @Autowired
    private CatalogoDelegate catalogoDelegate;

    @Autowired
    private EvidenciaDelegate evidenciaDelegate;

    @Autowired
    private FuncionarioDelegate funcionarioDelegate;
    
    private FuncionarioDTO construyeFuncionario(HttpServletRequest request) {
        FuncionarioDTO criterio = new FuncionarioDTO();
        criterio.setNombreFuncionario(obtenNombreFuncionario(request));
        criterio.setApellidoPaternoFuncionario(obtenApellidoPaterno(request));
        criterio.setApellidoMaternoFuncionario(obtenApellidoMaterno(request));
        //            criterio.setNombreInstitucion("Procu");
        criterio.setNombreInstitucion(obtenNombreInstitucion(request));
        ValorDTO puesto = new ValorDTO();
        puesto.setValor(obtenPuesto(request));
        criterio.setPuesto(puesto);
        return criterio;
    }

    private String obtenPuesto(HttpServletRequest request) {
        if (logger.isDebugEnabled()) {
            logger.debug(request.getParameter("puesto"));
        }
        return request.getParameter("puesto");
    }

    private String obtenInstitucion(HttpServletRequest request) {
        if (logger.isDebugEnabled()) {
            logger.debug(request.getParameter("institucion"));
        }
        return request.getParameter("institucion");
    }

    private String obtenNombreFuncionario(HttpServletRequest request) {
        if (logger.isDebugEnabled()) {
            logger.debug(request.getParameter("nombre"));
        }
        return request.getParameter("nombre");
    }

    private String obtenApellidoPaterno(HttpServletRequest request) {
        if (logger.isDebugEnabled()) {
            logger.debug(request.getParameter("apellidoPaterno"));
        }
        return request.getParameter("apellidoPaterno");
    }
    private String obtenApellidoMaterno(HttpServletRequest request) {
        if (logger.isDebugEnabled()) {
            logger.debug(request.getParameter("apellidoMaterno"));
        }
        return request.getParameter("apellidoMaterno");
    }

    private String obtenNombreInstitucion(HttpServletRequest request) {
        if (logger.isDebugEnabled()) {
            logger.debug(request.getParameter("institucion"));
        }
        return request.getParameter("institucion");
    }

    public ActionForward consultarCatalogoTipoBajaEvidencia(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("consultarCatalogoTipoBajaEvidencia");
                logger.debug("catalogoDelegate = " + catalogoDelegate);
            }
            List<CatalogoDTO> tiposBajaEvidencia = catalogoDelegate.recuperarCatalogo(Catalogos.ESTATUS_EVIDENCIA);
            converter.alias("Evidencias", List.class);
            converter.alias("evidencia", CatalogoDTO.class);
            String tiposBajaXml = converter.toXML(tiposBajaEvidencia);
            escribirRespuesta(response, tiposBajaXml);
        } catch (NSJPNegocioException ex) {
            java.util.logging.Logger.getLogger(RegistrarSalidasDelAlmacenAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ActionForward existeFuncionarioPorNombreInstitucionYPuesto(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            FuncionarioDTO criterio = construyeFuncionario(request);
            FuncionarioDTO funcionario = funcionarioDelegate.consultaFuncionarioPorNombreInstitucionYPuesto(criterio);
            if (funcionario != null) {
                escribirRespuesta(response, "true");
            }else{
                escribirRespuesta(response, "false");
            }
        } catch (NSJPNegocioException ex) {
            java.util.logging.Logger.getLogger(RegistrarSalidasDelAlmacenAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }



    public ActionForward registrarBajaEvidencia(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("registrarBajaEvidencia");
            }
            String[] idsEvidencia = request.getParameterValues("evidenciaId");
            if (logger.isDebugEnabled()) {
                if (idsEvidencia != null) {
                    logger.debug("idsEvidencia = " + idsEvidencia.length);
                } else {
                    logger.debug("idsEvidencia = " + idsEvidencia);
                }
            }
            if (logger.isDebugEnabled()) {
                logger.debug("nombreCompleto = " + obtenNombreFuncionario(request));
                logger.debug("observaciones = " + request.getParameter("observaciones"));
                logger.debug("institucion = " + obtenInstitucion(request));
                logger.debug("puesto = " + obtenPuesto(request));
            }
            List<EvidenciaDTO> evidenciasDto = new LinkedList<EvidenciaDTO>();
            FuncionarioDTO funcionarioDTO = construyeFuncionario(request);
            for (String string : idsEvidencia) {
                try {
                    Long id = Long.parseLong(string);
                    EvidenciaDTO evidenciaDto = new EvidenciaDTO(id);
                    evidenciaDto.setMotivoBaja(request.getParameter("observaciones"));
                    evidenciaDto.setFuncionarioBaja(funcionarioDTO);
                    evidenciasDto.add(evidenciaDto);
                } catch (NumberFormatException nfe) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("nfe = " + nfe);
                    }
                }
            }
            evidenciaDelegate.registrarBajaEvidencia(evidenciasDto);
            escribirRespuesta(response, "ok");
        } catch (NSJPNegocioException ex) {
            java.util.logging.Logger.getLogger(RegistrarSalidasDelAlmacenAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

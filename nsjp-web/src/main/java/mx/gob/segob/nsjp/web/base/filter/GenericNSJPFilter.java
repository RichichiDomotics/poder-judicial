/**
 * Nombre del Programa : GenericNSJPFilter.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 24 Aug 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Describir el objetivo de la clase de manera breve
 * Programa Dependiente  :N/A
 * Programa Subsecuente :N/A
 * Cond. de ejecucion        :N/A
 * Dias de ejecucion          :N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                       :N/A
 * Compania               :N/A
 * Proyecto                 :N/A                                 Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.web.base.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.gob.segob.nsjp.comun.util.tl.PaginacionThreadHolder;
import mx.gob.segob.nsjp.dto.base.PaginacionDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class GenericNSJPFilter implements Filter {
    /**
     * Logger.
     */
    private final static Logger logger = Logger
            .getLogger(GenericNSJPFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
            ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        logger.debug("req.getServletPath() :: " + req.getServletPath());
        PaginacionDTO fromVista = null;
        HttpSession sesion = req.getSession();
        UsuarioDTO usrFirmado = (UsuarioDTO) sesion
                .getAttribute(GenericAction.KEY_SESSION_USUARIO_FIRMADO);
        if (usrFirmado != null) {
            fromVista = new PaginacionDTO();
            fromVista.setUsuarioId(usrFirmado.getIdUsuario());
        }

        String page = req.getParameter("page");
        String rows = req.getParameter("rows");
        String sidx = req.getParameter("sidx"); // id de ordenamiento
        String sord = req.getParameter("sord"); // direccion de ordenamiento

        logger.debug("page :: [" + page + "]");
        logger.debug("rows :: [" + rows + "]");
        logger.debug("sidx :: [" + sidx + "]");
        logger.debug("sord :: [" + sord + "]");

        if (NumberUtils.isNumber(page)) {
            if (fromVista == null) {
                fromVista = new PaginacionDTO();
            }
            fromVista.setPage(Integer.decode(page));
            if (NumberUtils.isNumber(rows)) {
                fromVista.setRows(Integer.decode(rows));
            }
            fromVista.setCampoOrd(sidx);
            fromVista.setDirOrd(sord);
        }
        if (fromVista != null) {
            PaginacionThreadHolder.set(fromVista);
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}

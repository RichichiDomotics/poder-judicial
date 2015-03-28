/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.segob.nsjp.web.notificacion.action;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Jacob Lobaco
 */
public class Grid<T> {

    public static String creaCell(String contenido) {
        return "<cell>" + contenido + "</cell>\n";
    }

    public static String creaRow(String... contenidoCeldas) {
        String celdasXml = "";
        for (String cell : contenidoCeldas) {
            String cellXml = creaCell(cell);
            celdasXml += cellXml;
        }
        return "<row>" + celdasXml + "</row>\n";
    }
    private static final Logger logger =
            Logger.getLogger(Grid.class);
    private long numeroDePagina;
    private long numeroTotalRegistros;
    private List<T> elementos;
    private long numeroDeRegistrosPorPagina;
    private String[] campos;

    public Grid(long numeroDePagina,
            long numeroTotalRegistros, long numeroDeRegistrosPorPagina,
            List<T> elementos, String... campos) {
        this.numeroDePagina = numeroDePagina;
        this.numeroTotalRegistros = numeroTotalRegistros;
        this.numeroDeRegistrosPorPagina = numeroDeRegistrosPorPagina;
        this.elementos = elementos;
        this.campos = campos;
    }

    @Override
    public String toString() {
        if (logger.isDebugEnabled()) {
            logger.debug("toString = ");
        }
        StringBuilder xml = new StringBuilder();
        xml.append("<rows>\n");
        xml.append("<page>").append(numeroDePagina).append("</page>\n");
        xml.append("<total>").append((int) Math.ceil(numeroTotalRegistros / (double) numeroDeRegistrosPorPagina)).append("</total>\n");
        xml.append("<records>").append(numeroTotalRegistros).append("</records>\n");
        if (logger.isDebugEnabled()) {
            logger.debug("campos = " + campos.length);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("elementos = " + elementos.size());
        }
        for (T elemento : elementos) {
            StringBuilder celda = new StringBuilder();
            for (String cadenaDeNavegacion : campos) {
                if (logger.isDebugEnabled()) {
                    logger.debug("obteniendo la propiedad: " + cadenaDeNavegacion + " de: " + elemento);
                }
                Object propiedad = getObject(cadenaDeNavegacion, elemento);
                if (propiedad != null) {
                    celda.append(creaCell(propiedad.toString()));
                }else{
                    celda.append(creaCell(""));
                }
            }
            xml.append("<row>").append(celda.toString()).append("</row>\n");
        }
        xml.append("</rows>");
        return xml.toString();
    }

    private Object getObject(String cadenaDeNavegacion, Object elemento) {
        if (elemento == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("no se puede obtener la propieda = " + cadenaDeNavegacion + " de un objeto null");
            }
            return null;
        }
        try {
            int indexOf = cadenaDeNavegacion.indexOf(".");
            if (indexOf >= 0) {
                String subPropiedad = cadenaDeNavegacion.substring(0, indexOf);
                Object subobjeto = getObject(subPropiedad, elemento);
                return getObject(cadenaDeNavegacion.substring(indexOf + 1), subobjeto);
            }
            Method getter = elemento.getClass().getMethod(nombreGetter(cadenaDeNavegacion));
            getter.setAccessible(true);
            return getter.invoke(elemento);
        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ConsultarNotificacionesAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
//            java.util.logging.Logger.getLogger(ConsultarNotificacionesAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
//            java.util.logging.Logger.getLogger(ConsultarNotificacionesAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
//            java.util.logging.Logger.getLogger(ConsultarNotificacionesAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
//            java.util.logging.Logger.getLogger(ConsultarNotificacionesAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    private String nombreGetter(String propiedad) {
        String primeraLetra = propiedad.substring(0, 1).toUpperCase();
        propiedad = propiedad.substring(1);
        return "get" + primeraLetra + propiedad;
    }

    public List<T> getElementos() {
        return elementos;
    }

    public void setElementos(List<T> elementos) {
        this.elementos = elementos;
    }

    public long getNumeroDePagina() {
        return numeroDePagina;
    }

    public void setNumeroDePagina(long numeroDePagina) {
        this.numeroDePagina = numeroDePagina;
    }

    public long getNumeroTotalRegistros() {
        return numeroTotalRegistros;
    }

    public void setNumeroTotalRegistros(long numeroTotalRegistros) {
        this.numeroTotalRegistros = numeroTotalRegistros;
    }
}

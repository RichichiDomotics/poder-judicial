/**
 * Nombre del Programa : DateUtils.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 18 Apr 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Clase de utileria de fechas.
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
package mx.gob.segob.nsjp.comun.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;

/**
 * Clase de utileria de fechas.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class DateUtils {
    /**
     * <code>SimpleDateFormat</code> para fechas con la mascara
     * <b>dd/MM/yyyy</b>
     */
    private final static SimpleDateFormat SDF_FECHA = new SimpleDateFormat(
            "dd/MM/yyyy");
    private final static SimpleDateFormat SDF_HORA = new SimpleDateFormat(
            "HH:mm");
    private final static SimpleDateFormat SDF_HORA_AM = new SimpleDateFormat(
    "hh:mm a");
    private final static SimpleDateFormat SDF_HORA_SS = new SimpleDateFormat(
            "HH:mm:ss");
    private final static SimpleDateFormat SDF_FECHA_HORA = new SimpleDateFormat(
            "dd/MM/yyyy-HH:mm");
    private final static SimpleDateFormat SDF_FECHA_HORA_US = new SimpleDateFormat(
            "M/d/yyyy-HH:mm");
    private final static SimpleDateFormat SDF_FECHA_SQL_112 = new SimpleDateFormat(
            "yyyyMMdd");
    private final static SimpleDateFormat SDF_FECHA_SQL_120 = new SimpleDateFormat(
    "yyyy-MM-dd HH:mm:ss");
    
    private final static SimpleDateFormat SDF_FECHA_SQL_131 = new SimpleDateFormat(
    		"MM/dd/yyyy HH:mm:ss a");//Correccion del formato de fecha para la funcionalidad de la agenda  R.H.R. 05-ene-2015 DOMOTICS
    
    
    
    /**
     * Logger-
     */
    private final static Logger logger = Logger.getLogger(DateUtils.class);

    /**
     * Convierte una cadena en una fecha.
     * 
     * @param fechaStr
     *            Fecha en <code>String</code> a transformar en formato
     *            <b>dd/MM/yyyy</b>.
     * @return
     * @throws NSJPNegocioException
     *             En caso de que la cadena no tenga el formato esperado.
     */
    public static Date obtener(String fechaStr) throws NSJPNegocioException {
        try {
            final Calendar temp = Calendar.getInstance();
            temp.setTime(SDF_FECHA.parse(fechaStr));
            setMedioDia(temp);
            return temp.getTime();
        } catch (ParseException e) {
            logger.error(e.getMessage());
            throw new NSJPNegocioException(CodigoError.FORMATO, e);
        }
    }

    /**
     * Crea una fecha a partir de las cadenas de fecha (en formato
     * <b>dd/MM/yyyy</b>) y hora (en formato <b>HH:mm</b>).
     * 
     * @param fechaStr
     *            Fecha en <code>String</code> a transformar en formato
     *            <b>dd/MM/yyyy</b>.
     * @param horaStr
     *            Hora en <code>String</code> a transformar en formato
     *            <b>HH:mm</b>.
     * @return
     * @throws NSJPNegocioException
     *             En caso de que la cadena no tenga el formato esperado.
     */
    public static Date obtener(String fechaStr, String horaStr)
            throws NSJPNegocioException {
        try {
            final Date resp = SDF_FECHA_HORA.parse(fechaStr + "-" + horaStr);
            return resp;
        } catch (ParseException e) {
            logger.error(e.getMessage());
            throw new NSJPNegocioException(CodigoError.FORMATO, e);
        }
    }

    /**
     * Crea una fecha a partir de las cadenas de fecha (en formato
     * <b>dd/MM/yyyy</b>) y hora (en formato <b>HH:mm</b>).
     * 
     * @param fechaStr
     *            Fecha en <code>String</code> a transformar en formato
     *            <b>dd/MM/yyyy</b>.
     * @param horaStr
     *            Hora en <code>String</code> a transformar en formato
     *            <b>HH:mm</b>.
     * @return
     * @throws NSJPNegocioException
     *             En caso de que la cadena no tenga el formato esperado.
     */
    public static Date obtenerUS(String fechaStr, String horaStr)
            throws NSJPNegocioException {
        try {
            final Date resp = SDF_FECHA_HORA_US.parse(fechaStr + "-" + horaStr);
            return resp;
        } catch (ParseException e) {
            logger.error(e.getMessage());
            throw new NSJPNegocioException(CodigoError.FORMATO, e);
        }
    }

    /**
     * Crea una fecha a partir de las cadenas de fecha (en formato
     * <b>dd/MM/yyyy</b>) y hora (en formato <b>HH:mm</b>).
     * 
     * @param fechaStr
     *            Fecha en <code>String</code> a transformar en formato
     *            <b>dd/MM/yyyy</b>.
     * @param horaStr
     *            Hora en <code>String</code> a transformar en formato
     *            <b>HH:mm</b>.
     * @return La fecha con el a�os, mes d�a, hora y minuto especificado. Si
     *         alguna de las cadenas es apunta a <code>null</code> regresa
     *         <code>null</code>.
     * @throws NSJPNegocioException
     *             En caso de que la cadena no tenga el formato esperado.
     */
    public static Date obtenerNulleable(String fechaStr, String horaStr) {
        if (StringUtils.isNotBlank(fechaStr) && StringUtils.isNotBlank(horaStr)) {
            try {
                return obtener(fechaStr, horaStr);
            } catch (NSJPNegocioException e) {
                logger.warn(e.getMessage());
            }
        }
        return null;
    }

    /**
     * Obtiene la hora en una representaci�n <code>String</code> con el formato
     * <b>HH:mm</b>.
     * 
     * @param date
     *            Fecha a transformar.
     * @return Fecha en <code>String</code>.
     */
    public static String formatearHora(Date date) {
        return date != null ? SDF_HORA.format(date) : StringUtils.EMPTY;
    }

    /**
     * Obtiene la hora en una representaci�n <code>String</code> con el formato
     * <b>HH:mm:ss</b>.
     * 
     * @param date
     *            Fecha a transformar.
     * @return Fecha en <code>String</code>.
     */
    public static String formatearHoraSegs(Date date) {
        return date != null ? SDF_HORA_SS.format(date) : StringUtils.EMPTY;
    }

    /**
     * Obtiene la fecha en una representaci�n <code>String</code> con el formato
     * <b>dd/MM/yyyy</b>.
     * 
     * @param date
     *            Fecha a transformar.
     * @return Fecha en <code>String</code>.
     */
    public static String formatear(Date date) {

        return date != null ? SDF_FECHA.format(date) : StringUtils.EMPTY;
    }

    /**
     * Obtiene la fecha en una representaci�n <code>String</code> con el formato
     * <b>yyyyMMdd</b> para usarse con el <code>CONVERT</code> de sql Server con
     * el <i>style</i> <b>112</b>, ejempo:
     * <code>CONVERT (nvarchar, obj.fechaAtencion, 112) =
     * ?</code> .
     * 
     * @param date
     *            Fecha a transformar.
     * @return Fecha en <code>String</code>.
     */
    public static String formatearBD(Calendar date) {
        return formatearBD(date.getTime());
    }

    /**
     * Obtiene la fecha en una representaci�n <code>String</code> con el formato
     * <b>yyyyMMdd</b> para usarse con el <code>CONVERT</code> de sql Server con
     * el <i>style</i> <b>112</b>, ejempo:
     * <code>CONVERT (nvarchar, obj.fechaAtencion, 112) =
     * ?</code> .
     * 
     * @param date
     *            Fecha a transformar.
     * @return Fecha en <code>String</code>.
     */
    public static String formatearBD(Date date) {
        return SDF_FECHA_SQL_112.format(date);
    }

    /**
     * Obtiene la fecha en una representaci�n <code>String</code> con el formato
     * <b>dd/MM/yyyy</b>.
     * 
     * @param date
     *            Fecha a transformar.
     * @return Fecha en <code>String</code>.
     */
    public static String formatear(Calendar date) {
        return formatear(date.getTime());
    }

    /**
     * Asigna la hora del d�a exactamente a las 12:00:00.00 PM.
     * 
     * @param fecha
     *            Instancia de calendario
     */
    private static void setMedioDia(final Calendar fecha) {
        fecha.set(Calendar.HOUR_OF_DAY, 12);
        fecha.set(Calendar.MINUTE, 0);
        fecha.set(Calendar.SECOND, 0);
        fecha.set(Calendar.MILLISECOND, 0);
    }
    
    /**
     * Asigna la fecha en hora, minuto, segundo y milisegundo en 0.
     * Para obtener solo la fecha.
     * @param fecha
     */
    public static void setHoraMinutoSegundoCero(final Calendar fecha) {
        fecha.set(Calendar.AM_PM, Calendar.AM);
        fecha.set(Calendar.HOUR, 0);
        fecha.set(Calendar.MINUTE, 0);
        fecha.set(Calendar.SECOND, 0);
        fecha.set(Calendar.MILLISECOND, 0);
    }
    
    /**
     * Suma un determinado numero de d�as a una  fecha.
     * Se respeta la congruencia de la fecha.
     * 
     * @param fecha
     * @param dias
     */
    public static void sumarDias(final Calendar fecha, int dias) {
        fecha.add(Calendar.DATE, dias);
    }
    
    /**
     * Suma minutos a una fecha y hora particular.
     * @param input ehca y hora
     * @param sumaMinutos cantidad de minutis a sumar.
     * @return
     */
    public static Date sumarMinutos(Date input, int sumaMinutos) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(input);
        cal.add(Calendar.MINUTE, sumaMinutos);
        return cal.getTime();
    }
    
    /**
     * Obtiene la fecha en una representaci�n <code>String</code> con el formato
     * <b>YYYY-MM-DD HH:MI:SS</b> para usarse con el <code>CONVERT</code> de sql Server con
     * el <i>style</i> <b>131/b>, ejempo:
     * <code>CONVERT (nvarchar, obj.fechaAtencion, 131) =
     * ?</code> .
     * 
     * @param date
     *            Fecha a transformar.
     * @return Fecha en <code>String</code>.
     */
    public static String formatearBD120(Date date) {
        return SDF_FECHA_SQL_120.format(date);
    }
    

    /**
     * Obtiene la hora en una representaci�n <code>String</code> con el formato
     * <b>hh:mm a</b>. La hora con formato am/pm
     * 
     * @param date
     *            Fecha a transformar.
     * @return Fecha en <code>String</code>.
     */
    public static String formatearHoraAm(Date date) {
        return date != null ? SDF_HORA_AM.format(date) : StringUtils.EMPTY;
}
    
    public static String formatearBDConHora(Date date) {
        return SDF_FECHA_SQL_131.format(date);
    }
    
}

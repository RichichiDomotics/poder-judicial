/**
 * 
 */
package mx.gob.segob.nsjp.service.infra.impl.transform;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.XMLGregorianCalendar;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

/**
 * @author vaguirre
 * 
 */
public class WsTransformer {
    /**
     * 
     * @param input
     * @return
     */
    public static XMLGregorianCalendar transformFecha(Date input) {
        if (input == null) {
            return null;
        }
        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar
                .getInstance();
        cal.setTime(input);
        XMLGregorianCalendar rsp = new XMLGregorianCalendarImpl(cal);
        
        return rsp;
    }

    /**
     * 
     * @param input
     * @return
     */
    public static XMLGregorianCalendar transformFecha(Calendar input) {
        return WsTransformer.transformFecha(input.getTime());
    }
    
    
    public static Calendar transformXmlCalendar(XMLGregorianCalendar xml) {
    	Calendar cal = Calendar.getInstance();
    	cal.set(Calendar.YEAR, xml.getYear());
    	cal.set(Calendar.MONTH, xml.getMonth());
    	cal.set(Calendar.DAY_OF_MONTH, xml.getDay());
    	cal.set(Calendar.HOUR, xml.getHour());
    	cal.set(Calendar.MINUTE, xml.getMinute());
    	cal.set(Calendar.SECOND, xml.getSecond());
    	
    	return cal;
    }

    public static Date tranformXmlDate(XMLGregorianCalendar xml) {
    	Calendar cal = Calendar.getInstance();
    	cal.set(Calendar.YEAR, xml.getYear());
    	cal.set(Calendar.MONTH, (xml.getMonth()-1));
    	cal.set(Calendar.DAY_OF_MONTH, xml.getDay());
    	cal.set(Calendar.HOUR, xml.getHour());
    	cal.set(Calendar.MINUTE, xml.getMinute());
    	cal.set(Calendar.SECOND, xml.getSecond());
    	
    	return cal.getTime();    	
    }

}

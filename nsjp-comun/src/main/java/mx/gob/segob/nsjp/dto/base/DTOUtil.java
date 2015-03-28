/**
 * Nombre del Programa : DTOUtil.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 13 Apr 2011
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
package mx.gob.segob.nsjp.dto.base;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class DTOUtil implements Serializable{
    /**
     * Logger.
     */
    private final static Logger logger = Logger.getLogger(DTOUtil.class);
    /**
     * Método que obtiene la representación de un objeto en String.<br>
     * El método tiene el <i>scope</i> de <code>package</code> para que
     * únicamente sea alcanzado por la clase <code>GenericDTO</code>.
     * 
     * @param dtoSerializable
     * @return
     */
    static String getDTOasString(Serializable dtoSerializable) {
        try {
            String resp = BeanUtils.describe(dtoSerializable).toString();
            return resp;
        } catch (IllegalAccessException e) {
            logger.warn(e.getMessage());
        } catch (InvocationTargetException e) {
            logger.warn(e.getMessage());
        } catch (NoSuchMethodException e) {
            logger.warn(e.getMessage());
        }
        return null;
    }
}

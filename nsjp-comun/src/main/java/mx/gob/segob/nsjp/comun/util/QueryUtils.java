/**
* Nombre del Programa : QueryUtils.java
* Autor                            : cesar
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 5 May 2011
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
package mx.gob.segob.nsjp.comun.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author cesar
 *
 */
public class QueryUtils {
	
	public static List<Field> obtenerParametrosQuery (String nomClase, Class<?> tipoDato) {
	
		/**
	     * Logger.
	     */
	    final Logger logger = Logger.getLogger(QueryUtils.class);
		
	    String clasePaquete = "mx.gob.segob.nsjp.model." + nomClase;
	    
		Field[] atributos = null;
		List<Field> atributosRes = new ArrayList<Field>();
		try {
			atributos = Class.forName(clasePaquete).getDeclaredFields();
			for (Field field : atributos) {
				if (field.getType().equals(tipoDato))
					atributosRes.add(field);
			}
		} catch (SecurityException e) {
			logger.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
		}		
		
		return atributosRes;
		
	}
}

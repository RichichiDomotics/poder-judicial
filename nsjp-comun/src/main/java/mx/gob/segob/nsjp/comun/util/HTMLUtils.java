/**
* Nombre del Programa : HTMLUtils.java
* Autor                            : Emigdio Hern�ndez
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 01/06/2011
* Marca de cambio        : N/A
* Descripcion General    : Clase de utiler�as para la manipulaci�n y conversi�n de elementos/caracteres/c�digos HTML
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

import mx.gob.segob.nsjp.comun.constants.ConstantesGenerales;

import org.apache.commons.lang.StringUtils;

/**
 * Clase de utiler�as para la manipulaci�n y conversi�n de elementos/caracteres/c�digos HTML.
 * @version 1.0
 * @author Emigdio Hern�ndez
 *
 */
public class HTMLUtils {
	
    /**
     * Convierte los caracteres especiales HTML de una cadena a c�digos unicode 
     * @param cadena Cadena HTML fuente
     * @return cadena transformada
     */
    public static String encodeHtmlToXhtml(String cadena) {
		for (int i = 0; i < ConstantesGenerales.CARACTERES_HTML.length; i++) {
			cadena = StringUtils.replace(cadena, ConstantesGenerales.CARACTERES_HTML[i], 
					ConstantesGenerales.CARACTERES_UNICODE[i]);
		}
		return cadena;
	}

}

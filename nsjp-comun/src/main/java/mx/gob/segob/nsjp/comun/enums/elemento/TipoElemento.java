/**
* Nombre del Programa : TipoElemento.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 14/07/2011
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
package mx.gob.segob.nsjp.comun.enums.elemento;

import java.util.HashMap;
import java.util.Map;

/**
 * Enumeracion para obtener los datos de Tipo Elemento
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public enum TipoElemento {

	PERSONA (427L),
	
	ORGANIZACION (428L),
	
	LUGAR(429L),
	
	OBJETO(430L);
	
    private final static Map<Long, TipoElemento> hash = new HashMap<Long, TipoElemento>();
    
    private Long valorId;

    static {
    	TipoElemento[] objs = TipoElemento.values();
        int pos = 0;
        while (pos < objs.length) {
            hash.put(objs[pos].getValorId(), objs[pos]);
            pos++;
        }
    }
    
    private TipoElemento(Long valorIdPredefinido) {
        this.valorId = valorIdPredefinido;
    }
    
    public static TipoElemento getByValor(Long valorIdPredefinido) {
	        return hash.get(valorIdPredefinido);
	    }
	 
    /**
     * Método de acceso al campo valorId.
     * 
     * @return El valor del campo valorId asociado en le BD.
     */
    public Long getValorId() {
        return valorId;
    }
}

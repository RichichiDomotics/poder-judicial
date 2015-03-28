/**
 * 
 */
package mx.gob.segob.nsjp.comun.enums.convenios;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rgama
 *
 */
public enum Convenios {
	
	CONCILIACION(2582L),
	MEDIACION(2581L);
	

	 private final static Map<Long, Convenios> hash = new HashMap<Long, Convenios>();
	    
	    private Long valorId;

	    static {
	    	Convenios[] objs = Convenios.values();
	        int pos = 0;
	        while (pos < objs.length) {
	            hash.put(objs[pos].getValorId(), objs[pos]);
	            pos++;
	        }
	    }
		 public static Convenios getByValor(Long valorIdPredefinido) {
		        return hash.get(valorIdPredefinido);
		    }
	    private Convenios(Long valorIdPredefinido) {
	        this.valorId = valorIdPredefinido;
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

/**
 * 
 */
package mx.gob.segob.nsjp.comun.enums.elemento;

import java.util.HashMap;
import java.util.Map;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;

/**
 * @author adrian
 *
 */
public enum TipoTelefono {
	
	CASA (67L),
	CELULAR (68L),
	OFICINA (69L),
	RECADOS (70L),
	FAX (71L);

	 private final static Map<Long, TipoTelefono> hash = new HashMap<Long, TipoTelefono>();
	    
	    private Long valorId;

	    static {
	    	TipoTelefono[] objs = TipoTelefono.values();
	        int pos = 0;
	        while (pos < objs.length) {
	            hash.put(objs[pos].getValorId(), objs[pos]);
	            pos++;
	        }
	    }
		 public static TipoTelefono getByValor(Long valorIdPredefinido) {
		        return hash.get(valorIdPredefinido);
		    }
	    private TipoTelefono(Long valorIdPredefinido) {
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

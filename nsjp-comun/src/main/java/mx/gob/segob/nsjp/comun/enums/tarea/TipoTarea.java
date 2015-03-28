/**
 * 
 */
package mx.gob.segob.nsjp.comun.enums.tarea;

import java.util.HashMap;
import java.util.Map;

/**
 * @author adrian
 *
 */
public enum TipoTarea {
	
	REVISAR_EXPEDIENTE (2118L),
	ORGANIZAR_EXPEDIENTE (2119L),
	ENTREVISTAR_VICTIMA (2120L),
	PREPARAR_AUDIENCIA (2121L),
	ENTREVISTAR_TESTIGO (2122L),ACUDIR_AUDIENCIA(2122L);

	  private Long valorId;
	    private final static Map<Long, TipoTarea> hash = new HashMap<Long, TipoTarea>();

	    static {
	    	TipoTarea[] acts = TipoTarea.values();
	        int pos = 0;
	        while (pos < acts.length) {
	            hash.put(acts[pos].getValorId(), acts[pos]);
	            pos++;
	        }
	    }
	    private TipoTarea(Long valorIdPredefinido) {
	        this.valorId = valorIdPredefinido;
	    }

	    public static TipoTarea getByValor(Long valorIdPredefinido) {
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
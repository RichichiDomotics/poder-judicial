package mx.gob.segob.nsjp.comun.enums.tiempo;

import java.util.HashMap;
import java.util.Map;



/**
 * Enumeración para el manejo de tipos de registro de la entidad tiempo
 * 
 * @version 1.0
 * @author mgallardo
 * 
 */
public enum TipoTiempo {
	
	ESPECIFICAMENTE_EN(1768L),
    LAPSO(1769L),
    EVENTO_EN_EL_TIEMPO(1770L);
	
	private Long valorId;
    private final static Map<Long, TipoTiempo> hash = new HashMap<Long, TipoTiempo>();
    
    static {
    	TipoTiempo[] objs = TipoTiempo.values();
        int pos = 0;        
        while (pos < objs.length) {
            hash.put(objs[pos].getValorId(), objs[pos]);            
            pos++;
        }
    }
    public static TipoTiempo getByValor(Long valorIdPredefinido) {
        return hash.get(valorIdPredefinido);
    }
    
    private TipoTiempo(Long valorIdPredefinido) {
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

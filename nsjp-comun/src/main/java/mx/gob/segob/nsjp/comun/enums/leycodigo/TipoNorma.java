package mx.gob.segob.nsjp.comun.enums.leycodigo;

import java.util.HashMap;
import java.util.Map;

public enum TipoNorma {
    CONSTITUCION(2070L),
    LEYES(2071L),
    CODIGOS(2072L),
    MANUALES(2073L),
    TRATADOS(2074L),
    ACUERDOS(2075L),
    CIRCULARES(2076L),
    INSTRUCTIVOS(2077L);
    
    private final static Map<Long, TipoNorma> hash = new HashMap<Long, TipoNorma>();
    
    private Long valorId;

    static {
    	TipoNorma[] objs = TipoNorma.values();
        int pos = 0;
        while (pos < objs.length) {
            hash.put(objs[pos].getValorId(), objs[pos]);
            pos++;
        }
    }
	 public static TipoNorma getByValor(Long valorIdPredefinido) {
	        return hash.get(valorIdPredefinido);
	    }
    private TipoNorma(Long valorIdPredefinido) {
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

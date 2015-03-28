package mx.gob.segob.nsjp.comun.enums.documento;

import java.util.HashMap;
import java.util.Map;

public enum Periodicidad {
	DIARIO(2465L), CADA_TERCER_DIA(2466L), SEMANAL(2467L),
	QUINCENAL(2468L), MENSUAL(2469L);
	
	private Long valorId;
    private final static Map<Long, Periodicidad> hash = new HashMap<Long, Periodicidad>();

    static {
    	Periodicidad[] objs = Periodicidad.values();
        int pos = 0;
        while (pos < objs.length) {
            hash.put(objs[pos].getValorId(), objs[pos]);
            pos++;
        }
    }
    public static Periodicidad getByValor(Long valorIdPredefinido) {
        return hash.get(valorIdPredefinido);
    }

    private Periodicidad(Long valorIdPredefinido) {
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

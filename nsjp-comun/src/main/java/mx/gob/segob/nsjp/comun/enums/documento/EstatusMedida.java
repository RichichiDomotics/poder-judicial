package mx.gob.segob.nsjp.comun.enums.documento;

import java.util.HashMap;
import java.util.Map;

public enum EstatusMedida {
	NO_ATENDIDA(2523L), EN_PROCESO(2524L), SUSPENDIDA(2525L),
	ATENDIDA(2526L), CANCELADA(2527L);
	
	private Long valorId;
    private final static Map<Long, EstatusMedida> hash = new HashMap<Long, EstatusMedida>();

    static {
    	EstatusMedida[] objs = EstatusMedida.values();
        int pos = 0;
        while (pos < objs.length) {
            hash.put(objs[pos].getValorId(), objs[pos]);
            pos++;
        }
    }
    public static EstatusMedida getByValor(Long valorIdPredefinido) {
        return hash.get(valorIdPredefinido);
    }

    private EstatusMedida(Long valorIdPredefinido) {
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

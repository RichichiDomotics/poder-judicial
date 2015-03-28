package mx.gob.segob.nsjp.comun.enums.solicitud;

import java.util.HashMap;
import java.util.Map;

public enum TipoMandamiento {
	SENTENCIA(2489L),
	ORDEN_DE_APREHENSION(2850L),
	ORDEN_DE_CATEO(2851L),
	ORDEN_DE_REAPREHENSION(2852L),
	ORDEN_DE_COMPARECENCIA(2853L),
	ORDEN_DE_DEVOLUCION(2854L);
   
    private Long valorId;

    private final static Map<Long, TipoMandamiento> hash = new HashMap<Long, TipoMandamiento>();

    static {
        TipoMandamiento[] objs = TipoMandamiento.values();
        int pos = 0;
        while (pos < objs.length) {
            hash.put(objs[pos].getValorId(), objs[pos]);
            pos++;
        }
    }
    public static TipoMandamiento getByValor(Long valorIdPredefinido) {
        return hash.get(valorIdPredefinido);
    }

    private TipoMandamiento(Long valorIdPredefinido) {
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

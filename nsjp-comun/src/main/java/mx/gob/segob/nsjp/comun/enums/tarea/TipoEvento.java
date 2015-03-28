package mx.gob.segob.nsjp.comun.enums.tarea;

import java.util.HashMap;
import java.util.Map;

public enum TipoEvento {
    VACACIONES(2128L), INCAPACIDAD(2131L), AUDIENCIA(2130L), TAREA(2129l);

    private Long valorId;
    private final static Map<Long, TipoEvento> hash = new HashMap<Long, TipoEvento>();

    static {
    	TipoEvento[] acts = TipoEvento.values();
        int pos = 0;
        while (pos < acts.length) {
            hash.put(acts[pos].getValorId(), acts[pos]);
            pos++;
        }
    }
    private TipoEvento(Long valorIdPredefinido) {
        this.valorId = valorIdPredefinido;
    }

    public static TipoEvento getByValor(Long valorIdPredefinido) {
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

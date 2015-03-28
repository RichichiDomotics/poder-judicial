package mx.gob.segob.nsjp.comun.enums.sesion;

import java.util.HashMap;
import java.util.Map;

public enum TipoSesion {
    ENTREVISTA_INICIAL(2891L), 
    ENTREVISTA_COMPLEMENTARIA(2892L), 
    NOTAS_EVOLUCION(2893L);

    private Long valorId;
    private final static Map<Long, TipoSesion> hash = new HashMap<Long, TipoSesion>();

    static {
    	TipoSesion[] acts = TipoSesion.values();
        int pos = 0;
        while (pos < acts.length) {
            hash.put(acts[pos].getValorId(), acts[pos]);
            pos++;
        }
    }
    private TipoSesion(Long valorIdPredefinido) {
        this.valorId = valorIdPredefinido;
    }

    public static TipoSesion getByValor(Long valorIdPredefinido) {
        return hash.get(valorIdPredefinido);
    }

    /**
     * Método de acceso al campo valorId.
     * 
     * @return El valor del campo valorId asociado en la BD.
     */
    public Long getValorId() {
        return valorId;
    }
}

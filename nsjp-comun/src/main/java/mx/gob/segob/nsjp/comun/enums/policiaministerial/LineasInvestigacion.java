/**
 * 
 */
package mx.gob.segob.nsjp.comun.enums.policiaministerial;

import java.util.HashMap;
import java.util.Map;

/**
 * @author adrian
 *
 */
public enum LineasInvestigacion {
	
	//FIXME VAP: Ajustar con valores de catálogo
	ENTORNO_DE_LA_DISCUSION_ENTRE_VICTIMA_Y_VICTIMARIO (2690L),
	LLAMADAS_TELEFONICAS_ANONIMAS_RECIBIDAS_LA_VISPERA_DEL_HECHO_DELICTIVO (2691L),
	RESENTIMIENTO_DE_FAMILIARES_O_AMIGOS_CONTRA_LA_VICTIMA (2692L),
	ANTECEDENTES_DEL_ARMA_HOMICIDA (2693L),
	RECONSTRUCCION_DE_LA_VIDA_PERSONAL_DE_LA_VICTIMA (2694L),
	RECONSTRUCCION_DE_LA_VIDA_PRIVADA_DEL_PROBABLE_RESPONSABLE (2695L),
	RECONSTRUCCION_DE_LA_RELACION_VICTIMA_VICTIMARIO (2696L);	
	
private final static Map<Long, LineasInvestigacion> hash = new HashMap<Long, LineasInvestigacion>();
    
    private Long valorId;

    static {
    	LineasInvestigacion[] objs = LineasInvestigacion.values();
        int pos = 0;
        while (pos < objs.length) {
            hash.put(objs[pos].getValorId(), objs[pos]);
            pos++;
        }
    }
    
    private LineasInvestigacion(Long valorIdPredefinido) {
        this.valorId = valorIdPredefinido;
    }
    
    public static LineasInvestigacion getByValor(Long valorIdPredefinido) {
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

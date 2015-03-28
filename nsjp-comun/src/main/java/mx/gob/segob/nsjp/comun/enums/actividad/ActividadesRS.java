/**
 * NGENERAR_OFICIO_DE_INGRESO_A_REINSERCION_SOCIAL(337L, "genOficioIngresoRS");ombre del Programa : Actividades.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 12 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Enumaraci�n para las actividades
 * Programa Dependiente  :N/A
 * Programa Subsecuente :N/A
 * Cond. de ejecucion        :N/A
 * Dias de ejecucion          :N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                       :N/A
 * Compania               :N/A
 * Proyecto                 :N/A                                 Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.comun.enums.actividad;

import java.util.HashMap;
import java.util.Map;

/**
 * Enumaración para las actividades de Reinserción Social.
 * 
 * @version 1.0
 * @author AntonioBV 
 */

public enum ActividadesRS {
	
	GENERAR_OFICIO_DE_INGRESO_A_REINSERCION_SOCIAL(337L, "genOficioIngresoRS"),
	OPCION1(-2L, ""),
	OPCION2(-3L, "");
	
    private Long valorId;
    private String cForward;
    
    private final static Map<Long, ActividadesRS> HASH = new HashMap<Long, ActividadesRS>();

    static {
        final ActividadesRS[] acts = ActividadesRS.values();
        int pos = 0;
        while (pos < acts.length) {
            HASH.put(acts[pos].getValorId(), acts[pos]);
            pos++;
        }
    }
    
    private ActividadesRS(final Long valorId) {
        this.valorId = valorId;
    }

    private ActividadesRS(final Long valorId, final String cForward) {
        this.valorId = valorId;
        this.cForward = cForward;
    }    
    
    public static ActividadesRS getByValor(final Long valorId) {
        return HASH.get(valorId);
    }

    /**
     * Método de acceso al campo valorId.
     * 
     * @return El valor del campo valorId asociado en le BD.
     */
    public Long getValorId() {
        return valorId;
    }

	/**
	 * @return the cForward
	 */
	public String getcForward() {
		return cForward;
	}
}

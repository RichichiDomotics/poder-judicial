/**
* Nombre del Programa : CategoriasActividad.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 20/09/2011
* Marca de cambio        : N/A
* Descripcion General    : Enumeración para las Categorias de Actividad
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
 * Enumeración para las Categorias de Actividad
 * 
 * @version 1.0
 * @author GustavoBP
 */
public enum CategoriasActividad {

	ALMACEN(2594L),
	MINISTERIAL(2595L),
	PERICIAL(2596L),
	POLICIAL(2597L);
	
	private Long valorId;
    private final static Map<Long, CategoriasActividad> hash = new HashMap<Long, CategoriasActividad>();

    static {
    	CategoriasActividad[] acts = CategoriasActividad.values();
        int pos = 0;
        while (pos < acts.length) {
            hash.put(acts[pos].getValorId(), acts[pos]);
            pos++;
        }
    }
    private CategoriasActividad(Long valorIdPredefinido) {
        this.valorId = valorIdPredefinido;
    }

    public static CategoriasActividad getByValor(Long valorIdPredefinido) {
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

/**
 * 
 */
package mx.gob.segob.nsjp.comun.enums.elemento;

import java.util.HashMap;
import java.util.Map;

/**
 * @author adrian
 *
 */
public enum CategoriasElementos {

	TRADUCTOR_INTERPRETE (1L), 
	OBJETOS_Y_EVIDENCIAS (2L), 
	HECHO (3L), 
	CADENA_DE_CUSTODIA (4L), 
	DELITO (5L), 
	DENUNCIANTE (6L), 
	VICTIMA (7L), 
	PROBABLE_RESPONSABLE (8L), 
	TESTIGO (9L); 
	
private final static Map<Long, CategoriasElementos> hash = new HashMap<Long, CategoriasElementos>();
    
    private Long valorId;

    static {
    	CategoriasElementos[] objs = CategoriasElementos.values();
        int pos = 0;
        while (pos < objs.length) {
            hash.put(objs[pos].getValorId(), objs[pos]);
            pos++;
        }
    }
    
    private CategoriasElementos(Long valorIdPredefinido) {
        this.valorId = valorIdPredefinido;
    }
    
    public static CategoriasElementos getByValor(Long valorIdPredefinido) {
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

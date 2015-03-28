/**
* Nombre del Programa : TipoOrganizacion.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 20/07/2011
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
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
package mx.gob.segob.nsjp.comun.enums.organizacion;

import java.util.HashMap;
import java.util.Map;


/**
 * Enumeracion para obtener los datos de Tipo Organizacion
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public enum TipoOrganizacion {
	
	FORMAL(230L),
	
	NO_FORMAL(231L),
	
	COMUNIDAD_VIRTUAL(232L),
	
	SECTOR_PUBLICO(233L),
	
	DELICTIVA(234L);
	
	private final static Map<Long, TipoOrganizacion> hash = new HashMap<Long, TipoOrganizacion>();
    
    private Long valorId;

    static {
    	TipoOrganizacion[] objs = TipoOrganizacion.values();
        int pos = 0;
        while (pos < objs.length) {
            hash.put(objs[pos].getValorId(), objs[pos]);
            pos++;
        }
    }
    
    private TipoOrganizacion(Long valorIdPredefinido) {
        this.valorId = valorIdPredefinido;
    }
    
    public static TipoOrganizacion getByValor(Long valorIdPredefinido) {
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

/**
 * Nombre del Programa 			: TipoAtencion.java
 * Autor                        : Alejandro Galaviz
 * Compania                    	: Ultrasist
 * Proyecto                     : NSJP                    Fecha: 12 May 2011
 * Marca de cambio        		: N/A
 * Descripcion General    		: Enumaración para los tipos de atencion
 * Programa Dependiente  		:N/A
 * Programa Subsecuente 		:N/A
 * Cond. de ejecucion        	:N/A
 * Dias de ejecucion          	:N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                       	:N/A
 * Compania               		:N/A
 * Proyecto                 	:N/A                                 Fecha: N/A
 * Modificacion           		:N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.comun.enums.tipoatencion;

import java.util.HashMap;
import java.util.Map;


/**
 * Enumeracion para los tipos de atencion
 * 
 * @version 1.0
 * @author AlejandroGA
 *
 */
public enum TiposAtencion {

	ATENCION_PSICOLOGICA (296L),
	ATENCION_MEDICA (297L),
	ATENCION_DE_TRABAJO_SOCIAL(298L);
	
	 private Long valorId;
	    private final static Map<Long, TiposAtencion> hash = new HashMap<Long, TiposAtencion>();

	    static {
	    	TiposAtencion[] acts = TiposAtencion.values();
	        int pos = 0;
	        while (pos < acts.length) {
	            hash.put(acts[pos].getValorId(), acts[pos]);
	            pos++;
	        }
	    }
	    private TiposAtencion(Long valorIdPredefinido) {
	        this.valorId = valorIdPredefinido;
	    }

	    public static TiposAtencion getByValor(Long valorIdPredefinido) {
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

/**
* Nombre del Programa : OperacionDocumento.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 21/06/2011
* Marca de cambio        : N/A
* Descripcion General    : Enumeración con los tipos de operaciones disponibles para después de imprimir
* un documento
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
package mx.gob.segob.nsjp.comun.enums.documento;

import java.util.HashMap;
import java.util.Map;

import mx.gob.segob.nsjp.comun.enums.objeto.Objetos;

/**
 * Enumeración con los tipos de operaciones disponibles para después de imprimir
 * un documento
 * @version 1.0
 * @author Emigdio Hernándedz
 *
 */
public enum OperacionDocumento {

	ACTUALIZAR_ESTADO_SOLICITUD(1L),
	REGISTRAR_ORDEN_DETENCION(2L),
	ASOCIAR_DOCUMENTO_A_RESOLUTIVO(3L);
	
	private Long valorId;

	
	private final static Map<Long, OperacionDocumento> hash = new HashMap<Long, OperacionDocumento>();

    static {
    	OperacionDocumento[] objs = OperacionDocumento.values();
        int pos = 0;
        while (pos < objs.length) {
            hash.put(objs[pos].getValorId(), objs[pos]);
            pos++;
        }
    }

    
	private OperacionDocumento(Long valorIdPredefinido) {
		this.valorId = valorIdPredefinido;
	
	}
	 public static OperacionDocumento getByValor(Long valorIdPredefinido) {
	        return hash.get(valorIdPredefinido) ;
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

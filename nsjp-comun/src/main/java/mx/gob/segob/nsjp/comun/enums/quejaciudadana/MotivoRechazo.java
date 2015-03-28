/**
* Nombre del Programa : MotivoRechazo.java
* Autor                            : LuisMG
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 19/09/2011
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
package mx.gob.segob.nsjp.comun.enums.quejaciudadana;
import java.util.HashMap;
import java.util.Map;
/**
 * @version 1.0
 * @author LuisMG
 *
 */
public enum MotivoRechazo {
	SIN_ARGUMENTOS (2586L),
	NO_EXISTE_FUNCIONARIO (2585L),
	NO_EXISTE_EXPEDIENTE (2584L),
	DATOS_INSUFICIENTES (2583L);
	
	private Long valorId;
	private final static Map<Long, MotivoRechazo> hash = new HashMap<Long, MotivoRechazo>();
	
	static {
		MotivoRechazo[] objs = MotivoRechazo.values();
		int pos = 0;
		while (pos < objs.length) {
			hash.put(objs[pos].getValorId(), objs[pos]);
			pos++;
		}
	}

	public static MotivoRechazo getByValor(Long valorIdPredefinido) {
		return hash.get(valorIdPredefinido);
	}

	private MotivoRechazo(Long valorIdPredefinido) {
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

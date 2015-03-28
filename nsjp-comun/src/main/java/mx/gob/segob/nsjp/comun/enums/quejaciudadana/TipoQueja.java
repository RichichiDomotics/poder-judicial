/**
 * 
 */
package mx.gob.segob.nsjp.comun.enums.quejaciudadana;

import java.util.HashMap;
import java.util.Map;

/**
 * @author adrian
 * 
 */
public enum TipoQueja {

	CORRUPCION(2509L), TIEMPO_DE_ATENCION(2510L), MALA_ATENCION(2511L), ABUSO_DE_AUTORIDAD(
			2512L), NEGACION_DE_INFORMACION(2513L);

	private Long valorId;
	private final static Map<Long, TipoQueja> hash = new HashMap<Long, TipoQueja>();

	static {
		TipoQueja[] objs = TipoQueja.values();
		int pos = 0;
		while (pos < objs.length) {
			hash.put(objs[pos].getValorId(), objs[pos]);
			pos++;
		}
	}

	public static TipoQueja getByValor(Long valorIdPredefinido) {
		return hash.get(valorIdPredefinido);
	}

	private TipoQueja(Long valorIdPredefinido) {
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

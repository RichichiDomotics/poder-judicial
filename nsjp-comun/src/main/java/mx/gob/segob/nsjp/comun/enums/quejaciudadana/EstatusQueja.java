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
public enum EstatusQueja {
	
	NO_ATENDIDA (2514L),
	EN_PROCESO (2515L),
	TERMINADA (2516L);
	
	private Long valorId;
	private final static Map<Long, EstatusQueja> hash = new HashMap<Long, EstatusQueja>();

	static {
		EstatusQueja[] objs = EstatusQueja.values();
		int pos = 0;
		while (pos < objs.length) {
			hash.put(objs[pos].getValorId(), objs[pos]);
			pos++;
		}
	}

	public static EstatusQueja getByValor(Long valorIdPredefinido) {
		return hash.get(valorIdPredefinido);
	}

	private EstatusQueja(Long valorIdPredefinido) {
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
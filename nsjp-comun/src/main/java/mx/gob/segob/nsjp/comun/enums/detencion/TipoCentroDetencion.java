/**
 * 
 */
package mx.gob.segob.nsjp.comun.enums.detencion;

import java.util.HashMap;
import java.util.Map;

/**
 * @author EduardoG
 * 
 */
public enum TipoCentroDetencion {

	CEDEPRO(2751L), AGENCIA_MP(2752L);

	private Long valorId;
	private final static Map<Long, TipoCentroDetencion> hash = new HashMap<Long, TipoCentroDetencion>();

	static {
		TipoCentroDetencion[] objs = TipoCentroDetencion.values();
		int pos = 0;
		while (pos < objs.length) {
			hash.put(objs[pos].getValorId(), objs[pos]);
			pos++;
		}
	}

	public static TipoCentroDetencion getByValor(Long valorIdPredefinido) {
		return hash.get(valorIdPredefinido);
	}

	private TipoCentroDetencion(Long valorIdPredefinido) {
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

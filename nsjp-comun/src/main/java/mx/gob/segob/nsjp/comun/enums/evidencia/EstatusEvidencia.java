/**
 * 
 */
package mx.gob.segob.nsjp.comun.enums.evidencia;

/**
 * @author adrian
 *
 */
public enum EstatusEvidencia {
    EN_ALMACEN(2263L),DESTRUIDA(2264L),EN_PRESTAMO(2265L),BAJA(2410L);
	
	private Long valorId;

	private EstatusEvidencia (Long valorId) {
		this.valorId=valorId;
	}
	
	/**
	 * Método de acceso al campo valorId.
	 * @return El valor del campo valorId
	 */
	public Long getValorId() {
		return valorId;
	}

}

/**
 * 
 */
package mx.gob.segob.nsjp.comun.enums.evidencia;

/**
 * @author adrian
 *
 */
public enum TipoDestinoLegal {
	DEVOLUCION(2275L), DESTRUCCION(2276L), ASEGURAMIENTO(2277L), TRANSFERENCIA(2278L);

	private Long valorId;

	private TipoDestinoLegal (Long valorId) {
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

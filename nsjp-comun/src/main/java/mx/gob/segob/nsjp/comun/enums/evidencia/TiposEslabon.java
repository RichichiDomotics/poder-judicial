/**
 * 
 */
package mx.gob.segob.nsjp.comun.enums.evidencia;

/**
 * @author adrian
 *
 */
public enum TiposEslabon {
	EMBALAJE(2145L), TRASLADO(2146L), EXAMEN(2147L),ANALISIS_DE_EVIDENCIA(2148L),
	PRESTAMO_DE_EVIDENCIA(2149L),CAMBIO_DE_ALMACEN(2150L),OTROS(2151L),
	//FIXME VAP Asignar valor de Catálogo
	SOLICITUD(2151L);
	
	private Long valorId;

	private TiposEslabon (Long valorId) {
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

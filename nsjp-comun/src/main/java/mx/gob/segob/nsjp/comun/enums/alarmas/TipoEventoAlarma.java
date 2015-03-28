/**
 * 
 */
package mx.gob.segob.nsjp.comun.enums.alarmas;

/**
 * @author adrian
 *
 */
public enum TipoEventoAlarma {
	
	SEGUIMIENTO_A_SOLICITUD(2385L),VENCIMIENTO(2386L);

	 private Long valorId;
    private TipoEventoAlarma(Long valorIdPredefinido) {
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

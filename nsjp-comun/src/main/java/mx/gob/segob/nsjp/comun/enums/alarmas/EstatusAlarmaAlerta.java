/**
 * 
 */
package mx.gob.segob.nsjp.comun.enums.alarmas;

/**
 * @author adrian
 *
 */
public enum EstatusAlarmaAlerta {
	NO_EJECUTADA(2405L), CANCELADA(2406L), EJECUTADA(2407L);

    private Long valorId;
    private EstatusAlarmaAlerta(Long valorIdPredefinido) {
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

/**
 * Nombre del Programa  : TipoMovimiento.java
 * Autor                : cparedes
 * Compania             : Ultrasist
 * Proyecto             : NSJP                    Fecha: 14 Oct 2011
 * Marca de cambio      : N/A
 * Descripcion General  : Enumeración de el tipo de movimiento.
 * Programa Dependiente :N/A
 * Programa Subsecuente :N/A
 * Cond. de ejecucion   :N/A
 * Dias de ejecucion    :N/A                      Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                  :N/A
 * Compania               :N/A
 * Proyecto               :N/A                                 Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.comun.enums.expediente;

import java.util.HashMap;
import java.util.Map;

/**
 * Enumeración del tipo de movimiento.
 * 
 * @version 1.0
 * @author cparedes
 * 
 */
public enum TipoMovimiento {

    RECIBIR_CARPETA_DE_INVESTIGACION(2928L), CAMBIO_DE_ESTATUS_DEL_CASO(2929L), CANALIZACION(
            2930L), CAMBIO_DE_ETAPA_DE_EXPEDIENTE(2931L), CAMBIO_DE_ESTATUS_DE_EXPEDIENTE(
            2932L), CAMBIO_DE_SITUACION_JURIDICA(2934L), ASIGNACION_DE_EXPEDIENTE(
            2935L);

    private final static Map<Long, TipoMovimiento> hash = new HashMap<Long, TipoMovimiento>();

    static {
        TipoMovimiento[] objs = TipoMovimiento.values();
        int pos = 0;
        while (pos < objs.length) {
            hash.put(objs[pos].getValorId(), objs[pos]);
            pos++;
        }
    }

    private Long valorId;
    private TipoMovimiento(Long valorIdPredefinido) {
        this.valorId = valorIdPredefinido;
    }

    public static TipoMovimiento getByValor(Long valorIdPredefinido) {
        return hash.get(valorIdPredefinido);
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

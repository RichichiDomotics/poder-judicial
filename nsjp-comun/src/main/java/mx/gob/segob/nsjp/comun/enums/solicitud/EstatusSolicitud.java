/**
 * Nombre del Programa : EstatusSolicitud.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 20 Jun 2011
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
package mx.gob.segob.nsjp.comun.enums.solicitud;

import java.util.HashMap;
import java.util.Map;

import mx.gob.segob.nsjp.comun.enums.documento.OperacionDocumento;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
//FIXME VAP depurar
public enum EstatusSolicitud {
    /**
     * No Atendida.
     */
    ABIERTA(1775L), 
    /**
     * Atendida.
     */
    CERRADA(1776L),
    /**
     * @deprecated
     */
    RESPONDIDA(1777L),
    /**
     * @deprecated
     */
    ACEPTADA(1778L),
    /**
     * @deprecated
     */
    ENTREGADA(1779L),
    /**
     * En proceso.
     */
    EN_PROCESO(2096L),
    /**
     * Cancelada
     */
    CANCELADA(3554L)	
    ;
    private Long valorId;

    private final static Map<Long, EstatusSolicitud> hash = new HashMap<Long, EstatusSolicitud>();

    static {
        EstatusSolicitud[] objs = EstatusSolicitud.values();
        int pos = 0;
        while (pos < objs.length) {
            hash.put(objs[pos].getValorId(), objs[pos]);
            pos++;
        }
    }
    public static EstatusSolicitud getByValor(Long valorIdPredefinido) {
        return hash.get(valorIdPredefinido);
    }

    private EstatusSolicitud(Long valorIdPredefinido) {
        this.valorId = valorIdPredefinido;
    }

    /**
     * M�todo de acceso al campo valorId.
     * 
     * @return El valor del campo valorId asociado en le BD.
     */
    public Long getValorId() {
        return valorId;
    }
}

/**
 * Nombre del Programa : Estatus.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 30 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Enumeración para los estados del expediente
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
package mx.gob.segob.nsjp.comun.enums.expediente;

import java.util.HashMap;
import java.util.Map;

/**
 * Enumeración para los estados del expediente.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public enum EstatusExpediente {
    ABIERTO(1712L), 
    CANALIZADO(1713L), 
    CERRADO(2100L),
    ABIERTO_RESTAURATIVA(2484L),
    ABIERTO_INTEGRACION(2485L),
    ABIERTO_TECNICA_SIN_CARPETA(2486L),
    ABIERTO_TECNICA_CON_CARPETA(2487L),
    ABIERTO_EJECUCION(2488L),
    SUSPENDIDO_TEMPORALMENTE(2497L),
    ARCHIVO_TEMPORAL(2500L),
    AUDITANDO(2767L),
    ARCHIVO_DEFINITIVO(2501L),
    POR_ATENDER(2279L),//FIXME ver si existe o no en BD
    /**
     * Usado en SSP.
     */
    NO_ATENDIDO(2860L),
    /**
     * Para recibir en procu los expediente generados a través de una recepción del IPH.
     */
    PENDIENTE_REVISION_COMO_IPH(2958L),
    
  //USADOS PARA SISTEMA TRADICIONAL
    EN_TRAMITE(250L),
    ACUMULADO(251L),
    CONSIGNADO(252L),
    INCOMPETENCIA(253L),
    NEAP(254L),
    RESERVA(255L);
    
    private Long valorId;

    

    private final static Map<Long, EstatusExpediente> hash = new HashMap<Long, EstatusExpediente>();

    static {
    	EstatusExpediente[] objs = EstatusExpediente.values();
        int pos = 0;
        while (pos < objs.length) {
            hash.put(objs[pos].getValorId(), objs[pos]);
            pos++;
        }
    }
    public static EstatusExpediente getByValor(Long valorIdPredefinido) {
        return hash.get(valorIdPredefinido);
    }

    private EstatusExpediente(Long valorIdPredefinido) {
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

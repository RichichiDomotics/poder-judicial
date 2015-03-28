/**
 * Nombre del Programa : Objetos.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 4 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Enumeración para el origen de los Expedientes
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
 * Enumeración para el origen de los Expedientes.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public enum OrigenExpediente {
    /**
     * 235.
     */
    DENUNCIA(2078L, "Denuncia"), QUERELLA(2079L, "Querella"),INCOMPETENCIA(311L, "Incompetencia"),
    REPORTE(3975L, "Reporte");

    private Long valorId;

    private String nombreEntity;

    private final static Map<Long, OrigenExpediente> hash = new HashMap<Long, OrigenExpediente>();

    static {
        OrigenExpediente[] objs = OrigenExpediente.values();
        int pos = 0;
        while (pos < objs.length) {
            hash.put(objs[pos].getValorId(), objs[pos]);
            pos++;
        }
    }

    private OrigenExpediente(Long valorIdPredefinido, String nombreEntitdad) {
        this.valorId = valorIdPredefinido;
        this.nombreEntity = nombreEntitdad;
    }
    public static OrigenExpediente getByValor(Long valorIdPredefinido) {
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

    public String getNombreEntity() {
        return nombreEntity;
    }
}

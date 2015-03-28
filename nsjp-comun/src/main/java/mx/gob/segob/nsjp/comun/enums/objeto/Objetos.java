/**
 * Nombre del Programa : Objetos.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 4 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Enumeración para los tipos de objetos
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
package mx.gob.segob.nsjp.comun.enums.objeto;

import java.util.HashMap;
import java.util.Map;

/**
 * Enumeración para los tipos de objetos.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public enum Objetos {
    /**
     * 235.
     */
    EMBARCACION(235L, "Embarcacion"), DOCUMENTO_OFICIAL(236L,
            "DocumentoOficial"), ANIMAL(237L, "Animal"), VEGETAL(238L,
            "Vegetal"),
    /**
     * 393.
     */
    SUSTANCIA(239L, "Sustancia"), EXPLOSIVO(240L, "Explosivo"), ARMA(241L,
            "Arma"), EQUIPO_TELEFONICO(242L, "Telefonia"),
    /**
     * 243.
     */
    EQUIPO_DE_COMPUTO(243L, "EquipoComputo"), OBRA_DE_ARTE(244L, "ObraArte"), JOYA(
            245L, "Joya"), NUMERARIO(246L, "Numerario"),
    /**
     * 247.
     */
    INMUEBLE(247L, "Inmueble"), AERONAVE(248L, "Aeronave"), OTRO(249L, ""),
    /**
     * 1432.
     */
    VEHICULO(1432L, "Vehiculo"), ARMA_BLANCA(2365L,"Arma Blanca"),
    
    EQUIPO_TACTICO (2889L, "EquipoTactico");

    private Long valorId;

    private String nombreEntity;

    private final static Map<Long, Objetos> hash = new HashMap<Long, Objetos>();

    static {
        Objetos[] objs = Objetos.values();
        int pos = 0;
        while (pos < objs.length) {
            hash.put(objs[pos].getValorId(), objs[pos]);
            pos++;
        }
    }

    private Objetos(Long valorIdPredefinido, String nombreEntitdad) {
        this.valorId = valorIdPredefinido;
        this.nombreEntity = nombreEntitdad;
    }
    public static Objetos getByValor(Long valorIdPredefinido) {
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

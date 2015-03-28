/**
 * Nombre del Programa : TipoTurno.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 26 Apr 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Enumeración para el tipo turno.
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

import mx.gob.segob.nsjp.comun.enums.institucion.Areas;

/**
 * Enumeración para el tipo turno.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public enum TipoTurno {
    PENAL(Areas.ATENCION_TEMPRANA_PG_PENAL), ADMINISTRATIVO(Areas.ATENCION_TEMPRANA_PG_NO_PENAL),
    JUDICIAL (Areas.ATENCION_TEMPRANA_PJ),ASESORIA_LEGAL(Areas.ATENCION_TEMPRANA_DEFENSORIA),
    SOLICITUD_CIUDADANA(Areas.ATENCION_TEMPRANA_DEFENSORIA);
    private Areas area;

    private final static Map<Areas, TipoTurno> hash = new HashMap<Areas, TipoTurno>();

    static {
        TipoTurno[] objs = TipoTurno.values();
        int pos = 0;
        while (pos < objs.length) {
            hash.put(objs[pos].getArea(), objs[pos]);
            pos++;
        }
    }

    private TipoTurno(Areas areaAtiende) {
        this.area = areaAtiende;
    }

    public Areas getArea() {
        return area;
    }

    public static TipoTurno getByValor(Areas valorIdPredefinido) {
        return hash.get(valorIdPredefinido);
    }
}

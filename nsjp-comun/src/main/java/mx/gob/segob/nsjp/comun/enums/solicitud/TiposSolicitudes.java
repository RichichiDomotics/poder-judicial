/**
 * Nombre del Programa : Puestos.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    
 * Marca de cambio        : N/A
 * Descripcion General    : Enumaraci�n para las actividades
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

/**
 * Enumaraci�n para los tipos de solicitudes.
 * 
 * @version 1.0
 * @author rgama
 * 
 */
public enum TiposSolicitudes {

    // SolicitudPericial
    ASESORIA(1766L), DICTAMEN(1767L), EVIDENCIA(2114L),
    // SolicitudAudiencia
    AUDIENCIA(1771L),
    // SolicitudDefensor
    DEFENSOR(1772L),
    // SolicitudTranscripcionAudiencia
    TRANSCRIPCION_DE_AUDIENCIA(1773L),
    // Solicitud de Audio y Video
    AUDIO_VIDEO(1774L),
    // SolicitudMandamiento
    MANDATO_JUDICIAL(2132L), MEDIDAS_ALTERNATIVAS(2154L), PRISION_PREVENTIVA(
            2155L), TRASLADO_DE_IMPUTADO(2156L), DESCARCELACION(2157L),
    // Solicitud
    RECURSO_APELACION(2091L), BENEFICIO_PRELIBERACION(2092L), ESTUDIOS(2105L), RECURSO_CASACION(
            2106L), ORDEN_DETENCION(2152L), LIBERTAD(2153L), 
    // CU "Dar aviso del Lugar de los Hechos"
    APOYO(2158L), DILIGENCIA(2274L),ATENCION_A_VICTIMAS_DEL_DELITO(2366L), CARPETA_DE_INVESTIGACION(2383L),
    ASESORIA_DEFENSORIA(2545L),EXTERNOS(2546L),PERMISOS(2547L),PERSONA_EXTERNA(2548L),POLICIA_MINISTERIAL(2549L),SSPE(2550L),ATENCION_PSICOLOGICA(2810L),
    TRABAJO_SOCIAL(2809L),ATENCION_JURIDICA(2811L), REINSERCION_SOCIAL(3551L);

    private Long valorId;
    private final static Map<Long, TiposSolicitudes> HASH = new HashMap<Long, TiposSolicitudes>();

    static {
        TiposSolicitudes[] acts = TiposSolicitudes.values();
        int pos = 0;
        while (pos < acts.length) {
        	HASH.put(acts[pos].getValorId(), acts[pos]);
            pos++;
        }
    }
    private TiposSolicitudes(Long valorIdPredefinido) {
        this.valorId = valorIdPredefinido;
    }

    public static TiposSolicitudes getByValor(Long valorIdPredefinido) {
        return HASH.get(valorIdPredefinido);
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

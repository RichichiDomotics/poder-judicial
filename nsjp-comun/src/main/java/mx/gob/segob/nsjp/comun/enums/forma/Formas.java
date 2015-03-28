/**
* Nombre del Programa : Formas.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 29/06/2011
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
package mx.gob.segob.nsjp.comun.enums.forma;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum con los valores de los ID de las Formas disponibles
 * @version 1.0
 * @author Emigdio Hern�ndez
 *
 */
public enum Formas {
	
	DENUNCIA(1L), ACTA (2L), AUDIENCIA(3L), TRANSCIPCION(4L), ACUSE_RECIBO(5L), OFICIO_CANALIZACION(6L), 
	ACUSE_ATENCION_CIUDADANA(7L), DUPLICIDAD_TERMINO_CONSTITUCIONAL(8L), ACUERDO_RECURSO_RECIBIDO(11L),
	SOLICITUD_DE_ESTUDIO(12L), SOLICITUD(13L), MEDIDA_CAUTELAR(14L), SOLICITUD_DEFENSOR_EXTERNO(15L),
	OFICIO_LOCALIZACION_PADRES(16L), DETENCION_POR_FALTA_ADMINISTRATIVA(17L), OFICIO_LIBERACION(18L), 
	HOJA_DE_INGRESO(19L), ACUERDO_RETENCION(20L),DICTAMEN_PERICIAL(21L),INFORME_PERICIAL(22L),
	ACUERDO_AMP(28L), ESCRITO(29L), DICTAMEN_MOTIVADO_CRITERIO_OP(30L), RAZONES_CRITERIO_OP(31L), ACUERDO_RESTAURATIVO(2364L),
	GENERAR_CEDULA_IDENTIFICACION(99L),GENERAR_LIBRO_GOBIERNO(86L),GENERAR_ACUERDOS_MP(23L),SOLICITAR_TRANSCRIPCION_DE_AUDIENCIA(77L),
	SOLICITAR_COPIA_DE_AUDIO_Y_VIDEO_DE_AUDIENCIA(78L),DEVOLUCION_DE_EVIDENCIAS(39L), PRESERVACION_DE_EVIDENCIAS(38L), DESTRUCCION_DE_EVIDENCIA(100L),
	SOLICITUD_DE_ATENCION_MEDICA(32L),OFICIO_DE_CANALIZACION_A_IE_DESDE_ATP(83L),SOLICITUD_DE_ASESORIA_LEGAL(33L),SOLICITUD_DE_SEGURIDAD_POLICIAL(34L),
	DECLARACION(40L),OFICIO_DE_CANALIZACION_A_JAR_DESDE_ATP(82L),OFICIO_DE_CANALIZACION_A_UI_DESDE_ATP(6L),INCUMPLIMIETO_DE_MEDIDA_CAUTELAR(95L),
	CUMPLIMIENTO_DE_MEDIDAS_CAUTELARES(96L),SOLICITUD_DE_DICTAMEN_PERICIAL(35L),SOLICITUD_DICTAMEN_PSICOLOGIA_FORENSE(36L),
	SOLICITUD_DICTAMEN_QUIMICA_FORENSE(37L),SOLICITUD_DICTAMEN_GRAFOSCOPIA(38L),OFICIO_DE_COLABORACION_DE_CAUTELARES(98L),
	MEDIDA_ALTERNA(103L),OFICIO_DE_INVESTIGACION_POLICIAL(60L), RESPUESTA_CIUDADANA(104L), COLABORACION_PARA_CUMPLIMIENTO_ALTERNATIVAS(106L), 
	INCUMPLIMIENTO_MEDIDA_ALTERNATIVA(107L), CUMPLIMIENTO_MEDIDA_ALTERNATIVA(108L), RECEPCION_LLAMADA_AUXILIO(109L), 
	OFICIO_SANCION_ADMINISTRATIVA(92L),
	OFICIO_DE_APOYO_LEGAL(151L),ESTUDIO_SOCIOECONOMICO(152L),OFICIO_DE_ENVIO_DE_ESTUDIO_SOCIOECONOMICO(153L),
	NOTIFICACION_DE_AUDIENCIA(144L),EXHORTO_DE_COLABORACION(150L),INTERNACION_PREVENTIVA(151L);
		
	
	private Long valorId;

    private final static Map<Long, Formas> hash = new HashMap<Long, Formas>();

    static {
    	Formas[] acts = Formas.values();
        int pos = 0;
        while (pos < acts.length) {
            hash.put(acts[pos].getValorId(), acts[pos]);
            pos++;
        }
    }
    private Formas(Long valorIdPredefinido) {
        this.valorId = valorIdPredefinido;
    }

    public static Formas getByValor(Long valorIdPredefinido) {
        return hash.get(valorIdPredefinido);
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

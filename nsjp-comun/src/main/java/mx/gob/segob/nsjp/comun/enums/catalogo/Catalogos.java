/**
 * Nombre del Programa : Error.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 7 Apr 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Enumeraci�n para agrupar los catalogos
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
package mx.gob.segob.nsjp.comun.enums.catalogo;


/**
 * Enumeraci�n para agrupar los catalogos
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public enum Catalogos {
    /**
     * Para obtener un c�talogo vac�o en desarollo. <br>
     * Ordinal = 0;
     */
    VACIO,
    // 1
    PAISES, ENTIDAD_FEDERATIVA, TIPO_ASENTAMIENTO,
    // 4
    TIPO_CALLE,
    /**
     * Para obtener un c�talogo de los tipos de documentos que genera la
     * aplicaci�n. <br>
     * Ordinal = 5;
     */
    TIPO_DOCUMENTO, TIPO_TELEFONO,
    // 7
    RELIGION, IDIOMA, ESTADO_CIVIL,
    // 10
    NACIONALIDAD, OCUPACION, ESCOLARIDAD,
    // 13
    TIPOS_DOCUMENTO_IDENTIFICACION, AREA,
    /**
     * Para obtener un c�talogo de calidades. <br>
     * Ordinal = 15;
     */
    CALIDAD,
    // 16
    ETAPA_CASO, TIPO_ORGANIZACION,
    // 18
    CIUDAD, DELEGACION_MUNICIPIO, INSTITUCION,
    // 21
    TIPO_OBJETO, DEPARTAMENTO, MODO_PARTICIPACION_DELITO,
    // 24
    TIPO_ELEMENTO, CONDICION_FISICA_OBJETO, MARCA_AERONAVE,
    // 27
    MARCA_EMBARCACION, TIPO_AERONAVE, TIPO_ANIMAL,
    // 30
    TIPO_CONSTRUCCION, TIPO_EMBARCACION, TIPO_VEHICULO,
    // 33
    COLOR, MARCA_VEHICULO, SUBMARCA_VEHICULO,
    // 36
    SUBMARCA_AERONAVE, TIPO_EQUIPO_COMPUTO, MARCA_EQUIPO_COMPUTO,
    // 39
    TIPO_EQUIPO_TELEFONICO, MARCA_TELEFONO, TIPO_ARMA,
    // 42
    MARCA_ARMA, TIPO_EXPLOSIVO, TIPO_SUSTANCIA,
    // 45
    SUBMARCA_EMBARCACION, TIPO_INMUEBLE, UNIDAD_MEDIDA,
    // 48
    TIPO_VEGETAL,
    /**
     * Para identificaci�n de una persona.<br>
     * Ordinal = 49;
     */
    TIPO_DOCUMENTO_OFICIAL, TIPO_JOYA,
    // 51
    TIPO_OBRA_ARTE, TIPO_EMPAQUE, TIPO_COMUNIDAD_VIRTUAL,
    // 54
    TIPO_ORGANIZACION_FORMAL, NIVEL_DEPENDENCIA, TIPO_DEPENDENCIA,
    // 57
    ORGANIZACION_SECTOR_PUBLICO, PUESTO_SERVIDOR_PUBLICO, TIPOS_FORMAS,
    /**
     * Para obtener un c�talogo de las actividades que puede tener un
     * expediente. <br>
     * Ordinal = 60;
     */
    TIPO_ACTIVIDAD, SITUACION_JURIDICA, ESTATUS_EXPEDIENTE,
    // 63
    TIPO_AUDIENCIA, TIPO_ESPECIALIDAD_FUNCIONARIO, TIPO_SOLICITUD,
    // 66
    TIPO_TIEMPO, ESTATUS_SOLICITUD, TAMANIO_BOCA,
    // 69
    TIPO_CARA, FORMA_MENTON, TIPO_MENTON,
    // 72
    TEZ, INCLINIACION_MENTON, COLOR_CABELLO,
    // 75
    FORMA_CABELLO, CALVICIE_TIPO, CABELLO_IMPLANTACION,
    // 78
    CANTIDAD_CABELLO, IMPLANTACION_CEJA, DIRECCION_CEJA,
    // 81
    FORMA_CEJA, TAMANIO_CEJA, FRENTE_ALTURA,
    // 84
    FRENTE_INCLINACION, ALTURA_NASO_LABIAL, COMISURAS_LABIOS,
    // 87
    ESPESOR_LABIO, PROMINENCIA_LABIOS, ANCHO_NARIZ,
    // 90
    ALTURA_NARIZ, BASE_NARIZ, RAIZ_NARIZ,
    // 93
    TAMANIO_OJO, COLOR_OJO, FORMA_OJO,
    // 96
    OREJA_LOBULO_ADHERENCIA, HELIX_SUPERIOR, HELIX_POSTERIOR,
    // 99
    LOBULO_CONTORNO, HELIX_ADHERENCIA, FORMA_OREJA,
    // 102
    COMPLEXION, DORSO_NARIZ, SENIAS_PARTICULARES,
    // 105
    VISTA_SENIAS_PARTICULARES, ESPECIALIDAD_FUNCIONARIO, TIPO_JERARQUIA_ORGANIZACIONAL,
    // 108
    ESTATUS_AUDIENCIA, INSTITUCION_CON_NSJP, FRENTE_ANCHO,
    // 111
    OREJA_TAMANIO, OREJA_LOBULO_PARTICULARIDAD, OREJA_LOBULO_DIMENSION,
    // 114
    ESTATUS_TURNO, ETAPA_EXPEDIENTE, TIPO_NORMA,
    // 117
    ORIGEN_EXPEDIENTE, TIPO_MEDIDA_CAUTELAR, TIPO_EXPEDIENTE,
    // 120
    TIPO_SANGRE, ESTATUS_EVENTO_CITA, TIPO_TAREA,
    // 123
    TIPO_EVENTO, ESTATUS_NOTIFICACION, TIPO_ESLABON,
    // 126
    HELIX_ORIGINAL, TIPO_DILIGENCIA, ESTATUS_EVIDENCIA,
    // 129
    SITUACION_POLICIAL_INDIVIDUO, GRUPO_EDAD, DESTINO_LEGAL_EVIDENCIA,
    /**
     * Modo de participaci�n en IPH. <br>
     * Ordinal = 132;
     */
    TIPO_PARTICIPACION, TIPO_CAT_COMPONENTE, TIPO_CARRETERA,
    // 135
    TIPO_OFICIO_ESTRUCTURADO, TIPO_EVENTO_ALARMA, TIPO_ALARMA,
    // 138
    ESTATUS_ALARMA, ESTUDIO_PERICIAL, MEDIDA_ALTERNA,
    // 141
    PERIODICIDAD, TIPO_MANDAMIENTO, ESTATUS_MANDAMIENTO,
    // 144
    TIPO_QUEJA, ESTATUS_QUEJA, RELACION_HECHOS,
    // 147
    ESTATUS_MEDIDA, UNIDAD_TIEMPO_ALERTAS, TIPO_ASESORIA,
    // 150
    CONVENIOS, CAUSA_RECHAZO_QUEJA_CIUDADANA, CATEGORIA_ACTIVIDAD,
    // 153
    NIVEL_SOCIOECONOMICO, TIPO_CENTRO_DETENCION, DELITO,
    // 156
    ACTUACIONES, PLANTILLAS, LINEAS_INVESTIGACION,
    // 159
    CAUSA_RECHAZO_AVISO_AUXILIO, PARAMETROS, TIPO_VIVIENDA,
    // 162
    CONDICION_VIVIENDA, SERVICIO_PUBLICO_VIVIENDA, LUGAR_EN_VIVIENDA,
    // 165
    TIPO_PERTENENCIA, TIPO_SESION, TIPO_SITIO_TRASLADO,
    // 168
    TIPO_MOVIMIENTO, ESTATUS_INSPECCION, ESTATUS_MULTA_SANCION,
    // 171
    TIPO_SENTENCIA, AGENCIAS,DISTRITOS,
    //174
    TIPO_ATENCION,
    //175
    LUGAR_DELITO,
    //176
    CLASIFICACION_DELITO,
    //177
    MODALIDAD_DELITO,
    //178
    MODUS_DELITO,
    //179
    CAUSA_DELITO,
    //180
    TIPO_VISITA,
    //181
    TIPO_UNIDAD_INVESTIGACION_ESPECIALIZADA,
    //182
    SALA_AUDIENCIA;
    
    /**
     * 
     * @param idCat
     * @return
     */
    public static Catalogos getEnum(Long idCat) {
        return values()[idCat.intValue()];
    }

}

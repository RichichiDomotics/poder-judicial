/**
 * Nombre del Programa : Areas.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 30 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Enumeración de las areas.
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
package mx.gob.segob.nsjp.comun.enums.institucion;

/**
 * Enumeración de las areas.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public enum Areas {
    
    // 0
    NA, 
    PGJ,//Procuraduria General de Justicia
    DEF, //Defensoria
    PJ,//Poder Judicial
    SSP,//Secretaria de Seguridad Publica
    RS,//Reinserción social
    //6
    COORDINACION_ATENCION_TEMPRANA_PG, 
    JUSTICIA_ALTERNATIVA_RESTAURATIVA,
    ALMACEN,
    CORRDINACION_UNIDAD_INVESTIGACION,
    // 10
    UNIDAD_INVESTIGACION, 
    FISCAL_FACILITADOR,
    COORDINACION_ATENCION_VICTIMAS,
    COORDINACION_PERICIALES_PG,
    SERVICIOS_PERICIALES_PG,
    COORDINACION_POLICIA_MINISTERIAL,
    COORDINACION_VISITADURIA,
    ATENCION_TEMPRANA_DEFENSORIA, 
    COORDINACION_DEFENSORIA,
    DEFENSORIA,
    //20
    COORDINACION_PERICIALES_DEF,
    SERVICIOS_PERICIALES_DEF,
    ENLACE_DEF,
    ADMINISTRATIVO_EJECUCION,
    ADMINISTRACION_JUDICIAL,
    ATENCION_TEMPRANA_PJ, //enlace 
    DEPARTAMENTO_CAUSA,
    DEPARTAMENTO_INFORMATICA,
    DEPARTAMENTO_SALA,
    DEPARTAMENTO_SEGUNDA_INSTANCIA,
    // 30
    JUZGADO, //juez
    DEPARTAMENTO_NOTIFICACIONES,
    TRANSCRIPTORES,
    EJECUCION_PENAS_MEDIDA_CAUTELARES,
    CENTROS_DETENCION,
    DIRECCION_POLICIA_PROCESAL,
    MEDICOS,
    POLICIA_SSP,
    POLICIA_PROCESAL,
    EXTERNO_PERITOS_PG,
    // 40
    EXTERNO_DEFENSORES,
    EXTERNO_PERITOS_DEF,
    UMAN, 
    REGISTRO_INICIAL,
    ATENCION_TEMPRANA_PG_PENAL,
    ATENCION_TEMPRANA_PG_NO_PENAL,
    UNIDAD_CAPTURA_SSP,
    COORDINACION_SEGUIMIENTO_SSP,
    @Deprecated COORDINACION_INVESTIGADORES_ROBO_VEHICULO,//NO EXISTE EN BD
    @Deprecated UNIDAD_INVESTIGACION_ROBO_VEHICULO,//NO EXISTE EN BD
    //50
    @Deprecated COORDINACION_INVESTIGADORES_DELITOS_SEXUALES,//NO EXISTE EN BD
    @Deprecated UNIDAD_INVESTIGACION_DELITOS_SEXUALES,//NO EXISTE EN BD
    @Deprecated COORDINACION_INVESTIGADORES_ROBO_VIOLENCIA,//NO EXISTE EN BD
    @Deprecated UNIDAD_INVESTIGACION_ROBO_VIOLENCIA,//NO EXISTE EN BD
    POLICIA_MINISTERIAL,
    VISITADURIA,
    ATENCION_VICTIMAS,//ahora esta funciona para trabajo social
    ATENCION_PSICOLOGICA,
    ATENCION_JURIDICA,
    AGENCIA_DEL_MINISTERIO_PUBLICO,
    //60
    ASE_DE_REINSERCION;
    
    
    /**
     * COnvierte a <code>Long</code> el valor correpondiente al <code>ordinal()</code>.
     * @return
     */
    public Long parseLong() {
        return new Long(ordinal());
    }
}

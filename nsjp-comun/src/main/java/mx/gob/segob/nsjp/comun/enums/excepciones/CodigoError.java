/**
 * Nombre del Programa : Error.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 7 Apr 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Enumeración para agrupar los códigos de error
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
package mx.gob.segob.nsjp.comun.enums.excepciones;

/**
 * Enumeración para agrupar los códigos de error.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public enum CodigoError {
    /**
     * Para cuando algún parametro no tiene el formato requerido.
     */
    FORMATO,
    /**
     * Para cuando los parametros son insuficientes para la ejecución del
     * servicio.
     */
    PARAMETROS_INSUFICIENTES, 
    /**
     * Para cuando se intenta realizar una operación sin haber cumplido la precondiciones
     * necesarias para su ejecución
     */
    EJCUCION_OPERACION_ESTADO_INCORRECTO, 
    /**
     * Para cuando se da un rango de fechas y la FInicial es mayor o igual a FFinal
     */
    RANGO_FECHAS_CRUZADAS,
    /**
     * Para cuando hay un error de comunicación con los web services.
     */
    ERROR_COMUNICACION, 
    /**
     * Para cuando una transformación de una fecha falla
     */
    ERROR_TRANSORMACION_FECHAS, 
    /**
     * Para cuando la información de los parametros de entrada propician un 
     * comportamiento inesperado o erroneo
     */
    INFORMACION_PARAMETROS_ERRONEA,
    /**
     * Para cuando se desea programar una audiencia en una sala que ya esta ocupada
     * (valida para cuando dos usuarios distintos desean programar al mismo tiempo)
     */
    SALA_OCUPADA,
    
    /**
     * Codigo para cuando se intenta asociar un documento a una audiencia, y este se encuentra ya asociado
     * en la tabla AudienciaDocumento
     */
    DOCUMENTO_YA_ASOCIADO,
    
    /**
     * Para cuando se desea cancelar una audiencia
     * (valida para cuando dos usuarios distintos desean programar al mismo tiempo)
     */
    AUDIENCIA_CANCELADA,
    
    /**
     * Representa si una audiencia no se puede cancelar dado que se encuentra en estatus diferente a SOLICITADA / PROCESO
     */
    FAIL_ESTATUS_AUDIENCIA
}

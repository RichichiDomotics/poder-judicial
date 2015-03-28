/**
 * Nombre del Programa : Parametros.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 19 Jul 2011
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
package mx.gob.segob.nsjp.comun.enums.configuracion;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public enum Parametros {
    /**
     * Entidad federativa donde está el despliegue
     */
    ENTIDAD_FEDERATIVA_DESPLIEGUE,
    /**
     * En días.
     */
    LIMITE_HISTORICO_CONSULTAS,
    /**
     * Incrementa la pena a un delito
     */
    CALIFICATIVA,
    /**
     * Decrementa la pena a un delito
     */
    MODIFICATIVA,
    /**
     * Incrementa la pena a un delito
     */
    NATURALEZA_DELITO, TIEMPO_REVISION_ALARMAS, ID_USUARIO_ROBOT_SISTEMA,
    /**
     * URL para localizar el servidor del chat
     */
    URL_SERVIDOR_CHAT,
    
    /**
     * Ruta y Nombre donde se tienes el JAVS
     */
    RUTA_JAVS, 
    
    /**
     * Valor que permite determinar si se habilita el turno o no.
     * 0 - Deshabilitado, es decir, no se muestra el turno dentro de la aplicación.
     * 1 - Habilitar el turno, es decir, se muestra el turno dentro de la aplicación
     */
    HABILITAR_TURNO,
    /**
     * Valor que permite saber si el chat ya se ejecuto si es cero no se a ejecutado si es 1 ya se ejecuto.
     */
    USUARIOS_CHAT,
    
    /**
     * Media para aritmetica para los delitos que pertenecen a un expediente
     */
    MEDIA_ARITMETICA_DELITOS,
    
    /**
     * Determina el la validicacion de delito en caso de  
     * 
     */
    VALIDA_DELITO_GRAVE,
 /**
     * Valor que determina el tiempo permitido de inactividad antes de bloquear la aplicacion
     */
    TIEMPO_BLOQUEO_SESION,
    
    /**
     * URL para localizar el servidor de imagenes
     */
    URL_SERVIDOR_IMAGENES;
}

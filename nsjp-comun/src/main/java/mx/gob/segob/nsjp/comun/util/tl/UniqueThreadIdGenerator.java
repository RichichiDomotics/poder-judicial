/**
 * Nombre del Programa : UniqueThreadIdGenerator.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 24 Aug 2011
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
package mx.gob.segob.nsjp.comun.util.tl;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class UniqueThreadIdGenerator {
    /**
     * Construtor por default.
     */
    private UniqueThreadIdGenerator() {
    }

    /**
     * Atributo uniqueId.
     */
    private static AtomicInteger uniqueId = new AtomicInteger(0);

    /**
     * 
     */
    private static final ThreadLocal<Integer> uniqueNum = 
      new ThreadLocal<Integer>() {
      @Override
      protected Integer initialValue() {
        return uniqueId.getAndIncrement();
      }
    };

    /**
     * M&eacute;todo que recupera el identificador unico del hilo local.
     * 
     * @return Integer
     */
    public static Integer getCurrentThreadId() {
//      Integer unique = new Integer(uniqueId.get());

      return uniqueNum.get();
    }
    /**
     * Recupera el número único.
     * @return Número único
     */
    public static ThreadLocal<Integer> getUniquenum() {
      return uniqueNum;
    }}

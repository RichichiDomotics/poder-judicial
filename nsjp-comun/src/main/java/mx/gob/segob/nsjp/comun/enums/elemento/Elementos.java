/**
* Nombre del Programa : Elementos.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 25 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : Enumeracion para el manejo de los distintos elementos
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
package mx.gob.segob.nsjp.comun.enums.elemento;

/**
 * Enumeracion para el manejo de los distintos elementos.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public enum Elementos {
	
	PERSONA(427L), 
	
	ORGANIZACION(428L),
	
	LUGAR(429L),
	
	OBJETO(430L);
	
	private Long valorId;
	
	private Elementos(Long valorIdPredefinido) {
        this.valorId = valorIdPredefinido;
    }

	/**
	 * Método de acceso al campo valorId.
	 * @return El valor del campo valorId
	 */
	public Long getValorId() {
		return valorId;
	}

}

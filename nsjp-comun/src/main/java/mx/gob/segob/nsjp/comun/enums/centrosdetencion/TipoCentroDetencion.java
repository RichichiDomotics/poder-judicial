/**
* Nombre del Programa : TipoCentroDetencion.java
* Autor                            : AntonioBV
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 20/02/2012
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
package mx.gob.segob.nsjp.comun.enums.centrosdetencion;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public enum TipoCentroDetencion {

	CEDEPRO(2751L, "CEDEPRO"), AGENCIA_MP(2752L,"Agencia MP"), CERESO(3004L, "CERESO");
	
	private Long id;
	private String descripcion;
	
	private TipoCentroDetencion(Long id) {
		this.id = id;
	}
	
	private TipoCentroDetencion(Long id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}

	/**
	 * Método de acceso al campo id.
	 * @return El valor del campo id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Método de acceso al campo descripcion.
	 * @return El valor del campo descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}


	
}

/**
* Nombre del Programa : FechaCompArchDigDTO.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 05/08/2011
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
package mx.gob.segob.nsjp.dto.medida;

import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author GustavoBP
 *
 */
public class FechaCompArchDigDTO {
	
	//TODO GBP revisar con Adrian la creación de esta entidad y de FechaCompArchDigId 
//	ya q no se encuentran en el modelo
//	private FechaCompArchDigId id;
	private FechaCompromisoDTO fechaCompromiso;
	private ArchivoDigitalDTO archivoDigital;

	/**
	 * Método de acceso al campo fechaCompromiso.
	 * @return El valor del campo fechaCompromiso
	 */
	public FechaCompromisoDTO getFechaCompromiso() {
		return fechaCompromiso;
	}
	/**
	 * Asigna el valor al campo fechaCompromiso.
	 * @param fechaCompromiso el valor fechaCompromiso a asignar
	 */
	public void setFechaCompromiso(FechaCompromisoDTO fechaCompromiso) {
		this.fechaCompromiso = fechaCompromiso;
	}
	/**
	 * Método de acceso al campo archivoDigital.
	 * @return El valor del campo archivoDigital
	 */
	public ArchivoDigitalDTO getArchivoDigital() {
		return archivoDigital;
	}
	/**
	 * Asigna el valor al campo archivoDigital.
	 * @param archivoDigital el valor archivoDigital a asignar
	 */
	public void setArchivoDigital(ArchivoDigitalDTO archivoDigital) {
		this.archivoDigital = archivoDigital;
	}
}

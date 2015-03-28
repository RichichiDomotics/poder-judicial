/**
* Nombre del Programa : ObraArteDTO.java                                    
* Autor                            : Tattva-IT                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 5 May 2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Clase para la transferencia de datos entre la vista y el negocio, del objeto Obra de arte.                      
* Programa Dependiente  :N/A                                                      
* Programa Subsecuente :N/A                                                      
* Cond. de ejecucion        :N/A                                                      
* Dias de ejecucion          :N/A                             Horario: N/A       
*                              MODIFICACIONES                                       
*------------------------------------------------------------------------------           
* Autor                       :N/A                                                           
* Compania               :N/A                                                           
* Proyecto                 :N/A                                   Fecha: N/A       
* Modificacion           :N/A                                                           
*------------------------------------------------------------------------------           
*/
package mx.gob.segob.nsjp.dto.objeto;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;


/**
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto obra de arte.
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class ObraArteDTO extends ObjetoDTO {
    private Long cantidad;
    private ValorDTO tipoObraArte;
	/**
	 * M�todo de acceso al campo cantidad.
	 * @return El valor del campo cantidad
	 */
	public Long getCantidad() {
		return cantidad;
	}
	/**
	 * Asigna el valor al campo cantidad.
	 * @param cantidad el valor cantidad a asignar
	 */
	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}
	/**
	 * M�todo de acceso al campo tipoObraArte.
	 * @return El valor del campo tipoObraArte
	 */
	public ValorDTO getTipoObraArte() {
		return tipoObraArte;
	}
	/**
	 * Asigna el valor al campo tipoObraArte.
	 * @param tipoObraArte el valor tipoObraArte a asignar
	 */
	public void setTipoObraArte(ValorDTO tipoObraArte) {
		this.tipoObraArte = tipoObraArte;
	}

}

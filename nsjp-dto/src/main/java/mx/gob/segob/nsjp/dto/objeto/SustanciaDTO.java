/**
* Nombre del Programa : SustanciaDTO.java                                    
* Autor                            : Tattva-IT                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 4 May 2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Clase para la transferencia de datos entre la vista y el negocio, del objeto sustancias.                      
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
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto sustancia.
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class SustanciaDTO extends ObjetoDTO {
	
    private Long cantidad;
    private ValorDTO tipoSustancia;
    private ValorDTO empaque;
    private ValorDTO unidadMedida;
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
	 * M�todo de acceso al campo tipoSustancia.
	 * @return El valor del campo tipoSustancia
	 */
	public ValorDTO getTipoSustancia() {
		return tipoSustancia;
	}
	/**
	 * Asigna el valor al campo tipoSustancia.
	 * @param tipoSustancia el valor tipoSustancia a asignar
	 */
	public void setTipoSustancia(ValorDTO tipoSustancia) {
		this.tipoSustancia = tipoSustancia;
	}
	/**
	 * M�todo de acceso al campo empaque.
	 * @return El valor del campo empaque
	 */
	public ValorDTO getEmpaque() {
		return empaque;
	}
	/**
	 * Asigna el valor al campo empaque.
	 * @param empaque el valor empaque a asignar
	 */
	public void setEmpaque(ValorDTO empaque) {
		this.empaque = empaque;
	}
	/**
	 * M�todo de acceso al campo unidadMedida.
	 * @return El valor del campo unidadMedida
	 */
	public ValorDTO getUnidadMedida() {
		return unidadMedida;
	}
	/**
	 * Asigna el valor al campo unidadMedida.
	 * @param unidadMedida el valor unidadMedida a asignar
	 */
	public void setUnidadMedida(ValorDTO unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
}

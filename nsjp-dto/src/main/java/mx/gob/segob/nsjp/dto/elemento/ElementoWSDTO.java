/**
*
* Nombre del Programa : ElementoWSDTO.java                                    
* Autor                            : GustavoBP                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 22/07/2012 
* Marca de cambio        : N/A                                                     
* Descripcion General    : DTO de intercambio entre sistemas para transportar los datos básicos de un Elemento.                      
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
package mx.gob.segob.nsjp.dto.elemento;

import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalWSDTO;
import mx.gob.segob.nsjp.dto.base.GenericWSDTO;

/**
 * DTO de intercambio entre sistemas para transportar los datos básicos de un Elemento.
 * @author GustavoBP
 * @version 1.0
 */
public class ElementoWSDTO extends GenericWSDTO {
	
	private CalidadWSDTO calidad;
	private Long valorIdElemento;
	private String strDescripcionRelacionarElemento;
	private ArchivoDigitalWSDTO fotoDelElemento;
	private String folioElemento;
	
	/**
	 * 
	 */
	public ElementoWSDTO() {
		super();		
	}

	/**
	 * Método de acceso al campo calidad.
	 * @return El valor del campo calidad
	 */
	public CalidadWSDTO getCalidad() {
		return calidad;
	}
	/**
	 * Asigna el valor al campo calidad.
	 * @param calidad el valor calidad a asignar
	 */
	public void setCalidad(CalidadWSDTO calidad) {
		this.calidad = calidad;
	}
	
	/**
	 * Método de acceso al campo valorIdElemento.
	 * @return El valor del campo valorIdElemento
	 */
	public Long getValorIdElemento() {
		return valorIdElemento;
	}
	/**
	 * Asigna el valor al campo valorIdElemento.
	 * @param valorIdElemento el valor valorIdElemento a asignar
	 */
	public void setValorIdElemento(Long valorIdElemento) {
		this.valorIdElemento = valorIdElemento;
	}


	/**
	 * Método de acceso al campo strDescripcionRelacionarElemento.
	 * @return El valor del campo strDescripcionRelacionarElemento
	 */
	public String getStrDescripcionRelacionarElemento() {
		return strDescripcionRelacionarElemento;
	}

	/**
	 * Asigna el valor al campo strDescripcionRelacionarElemento.
	 * @param strDescripcionRelacionarElemento el valor strDescripcionRelacionarElemento a asignar
	 */
	public void setStrDescripcionRelacionarElemento(
			String strDescripcionRelacionarElemento) {
		this.strDescripcionRelacionarElemento = strDescripcionRelacionarElemento;
	}

	/**
	 * Método de acceso al campo fotoDelElemento.
	 * @return El valor del campo fotoDelElemento
	 */
	public ArchivoDigitalWSDTO getFotoDelElemento() {
		return fotoDelElemento;
	}

	/**
	 * Asigna el valor al campo fotoDelElemento.
	 * @param fotoDelElemento el valor fotoDelElemento a asignar
	 */
	public void setFotoDelElemento(ArchivoDigitalWSDTO fotoDelElemento) {
		this.fotoDelElemento = fotoDelElemento;
	}

    /**
     * Método de acceso al campo folioElemento.
     * @return El valor del campo folioElemento
     */
    public String getFolioElemento() {
        return folioElemento;
    }

    /**
     * Asigna el valor al campo folioElemento.
     * @param folioElemento el valor folioElemento a asignar
     */
    public void setFolioElemento(String folioElemento) {
        this.folioElemento = folioElemento;
    }
	
	
	
	
}

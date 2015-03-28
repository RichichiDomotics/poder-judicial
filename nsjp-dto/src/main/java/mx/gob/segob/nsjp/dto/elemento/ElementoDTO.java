/**
*
* Nombre del Programa : ElementoDTO.java                                    
* Autor                            : Cesar Agustin                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 30/03/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : DTO para la transferencia de parametros de Elemento entre la vista y servicios.                      
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

import java.util.Date;

import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;



/**
 * @author CesarAgustin
 * @version 1.0
 */
public class ElementoDTO extends GenericDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4806865312710065109L;
	
	private Long elementoId;	
	private Date fechaCreacionElemento;
	private ExpedienteDTO expedienteDTO;
	private CalidadDTO calidadDTO;
	private ValorDTO valorIdElemento;
	
	private String strFechaCreacion;
	private String strDescripcionRelacionarElemento;
	private ArchivoDigitalDTO fotoDelElemento;
	private Boolean esActivo;
    /**
     * Propiedad usada para co-relacionar los elementos entre las instituciones.<br>
     * Esta formado por el <code>monograma</code> de la instituciones que lo
     * registra mas el <code>elementoId</code> (Ejemplo: <i>PG-1, PG-999</i>).
     */
    private String folioElemento;
	/**
	 * 
	 * @param elementoId
	 */
	public ElementoDTO(Long elementoId) {		
		this.elementoId = elementoId;
	}
	
	/**
	 * 
	 */
	public ElementoDTO() {
		super();		
	}

	/**
	 * @return the elementoId
	 */
	public Long getElementoId() {
		return elementoId;
	}
	/**
	 * @param elementoId the elementoId to set
	 */
	public void setElementoId(Long elementoId) {
		this.elementoId = elementoId;
	}	
	
	/**
	 * @return the dfechaCreacionElemento
	 */
	public Date getFechaCreacionElemento() {
		return fechaCreacionElemento;
	}
	/**
	 * @param dfechaCreacionElemento the dfechaCreacionElemento to set
	 */
	public void setFechaCreacionElemento(Date fechaCreacionElemento) {
		this.fechaCreacionElemento = fechaCreacionElemento;
	}
	
	/**
	 * Método de acceso al campo expedienteDTO.
	 * @return El valor del campo expedienteDTO
	 */
	public ExpedienteDTO getExpedienteDTO() {
		return expedienteDTO;
	}

	/**
	 * Asigna el valor al campo expedienteDTO.
	 * @param expedienteDTO el valor expedienteDTO a asignar
	 */
	public void setExpedienteDTO(ExpedienteDTO expedienteDTO) {
		this.expedienteDTO = expedienteDTO;
	}
	
	/**
	 * Método de acceso al campo calidadDTO.
	 * @return El valor del campo calidadDTO
	 */
	public CalidadDTO getCalidadDTO() {
		return calidadDTO;
	}
	/**
	 * Asigna el valor al campo calidadDTO.
	 * @param calidadDTO el valor calidadDTO a asignar
	 */
	public void setCalidadDTO(CalidadDTO calidadDTO) {
		this.calidadDTO = calidadDTO;
	}
	
	/**
	 * Método de acceso al campo valorIdElemento.
	 * @return El valor del campo valorIdElemento
	 */
	public ValorDTO getValorIdElemento() {
		return valorIdElemento;
	}
	/**
	 * Asigna el valor al campo valorIdElemento.
	 * @param valorIdElemento el valor valorIdElemento a asignar
	 */
	public void setValorIdElemento(ValorDTO valorIdElemento) {
		this.valorIdElemento = valorIdElemento;
	}

	/**
	 * Método de acceso al campo strFechaCreacion.
	 * @return El valor del campo strFechaCreacion
	 */
	public String getStrFechaCreacion() {
		if (strFechaCreacion!=null) {
			return strFechaCreacion;
		}		
		return (fechaCreacionElemento!=null?DateUtils.formatear(fechaCreacionElemento):null);
	}

	/**
	 * Asigna el valor al campo strFechaCreacion.
	 * @param strFechaCreacion el valor strFechaCreacion a asignar
	 */
	public void setStrFechaCreacion(String strFechaCreacion) {
		this.strFechaCreacion = strFechaCreacion;
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
	public ArchivoDigitalDTO getFotoDelElemento() {
		return fotoDelElemento;
	}

	/**
	 * Asigna el valor al campo fotoDelElemento.
	 * @param fotoDelElemento el valor fotoDelElemento a asignar
	 */
	public void setFotoDelElemento(ArchivoDigitalDTO fotoDelElemento) {
		this.fotoDelElemento = fotoDelElemento;
	}

	/**
	 * Método de acceso al campo esActivo.
	 * @return El valor del campo esActivo
	 */
	public Boolean getEsActivo() {
		return esActivo;
	}

	/**
	 * Asigna el valor al campo esActivo.
	 * @param esActivo el valor esActivo a asignar
	 */
	public void setEsActivo(Boolean esActivo) {
		this.esActivo = esActivo;
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

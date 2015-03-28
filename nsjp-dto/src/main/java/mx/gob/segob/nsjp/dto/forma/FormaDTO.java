/**
* Nombre del Programa : FormaDTO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 13 May 2011
* Marca de cambio        : N/A
* Descripcion General    : DTO para el traslado de atributos de Forma, entre la vista y los servicios
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
package mx.gob.segob.nsjp.dto.forma;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;

/**
 * DTO para el traslado de atributos de Forma, entre la vista y los servicios.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class FormaDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7558870611505949689L;
	
	private Long formaId;
	private ValorDTO tipoFormaDTO;
	private String descForma;
	private String cuerpo;
	private String nombre;
	private List<DocumentoDTO> documentosDTO = new ArrayList<DocumentoDTO>();
	private ValorDTO tipoDocumentoDTO;
	private Date fechaCreacion;
	
	
	public FormaDTO(Long idForma){
		this.formaId = idForma;
	}
	
	public FormaDTO(){}
	/**
	 * Método de acceso al campo formaId.
	 * @return El valor del campo formaId
	 */
	public Long getFormaId() {
		return formaId;
	}
	/**
	 * Asigna el valor al campo formaId.
	 * @param formaId el valor formaId a asignar
	 */
	public void setFormaId(Long formaId) {
		this.formaId = formaId;
	}
	/**
	 * Método de acceso al campo tipoForma.
	 * @return El valor del campo tipoForma
	 */
	public ValorDTO getTipoFormaDTO() {
		return tipoFormaDTO;
	}
	/**
	 * Asigna el valor al campo tipoForma.
	 * @param tipoForma el valor tipoForma a asignar
	 */
	public void setTipoFormaDTO(ValorDTO tipoFormaDTO) {
		this.tipoFormaDTO = tipoFormaDTO;
	}
	/**
	 * Método de acceso al campo descForma.
	 * @return El valor del campo descForma
	 */
	public String getDescForma() {
		return descForma;
	}
	/**
	 * Asigna el valor al campo descForma.
	 * @param descForma el valor descForma a asignar
	 */
	public void setDescForma(String descForma) {
		this.descForma = descForma;
	}
	/**
	 * Método de acceso al campo documentosDTO.
	 * @return El valor del campo documentosDTO
	 */
	public List<DocumentoDTO> getDocumentosDTO() {
		return documentosDTO;
	}
	/**
	 * Asigna el valor al campo documentosDTO.
	 * @param documentosDTO el valor documentosDTO a asignar
	 */
	public void setDocumentosDTO(List<DocumentoDTO> documentosDTO) {
		this.documentosDTO = documentosDTO;
	}	
	public String getCuerpo() {
		return cuerpo;
	}
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setTipoDocumentoDTO(ValorDTO tipoDocumentoDTO) {
		this.tipoDocumentoDTO = tipoDocumentoDTO;
	}

	public ValorDTO getTipoDocumentoDTO() {
		return tipoDocumentoDTO;
	}
	
}

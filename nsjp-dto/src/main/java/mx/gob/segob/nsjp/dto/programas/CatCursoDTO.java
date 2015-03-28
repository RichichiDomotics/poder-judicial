/**
* Nombre del Programa : CatCursoDTO.java
* Autor                            : AntonioBV
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 09/02/2012
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
package mx.gob.segob.nsjp.dto.programas;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.niveles.CatTipoNivelAcademicoDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public class CatCursoDTO extends GenericDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6723969884595128119L;

	private Long catCursoId;
	private CatTipoNivelAcademicoDTO catTipoNivelAcademicoDTO;
	private CatCategoriaCursoDTO catCategoriaCursoDTO;
	private CatTipoCursoDTO catTipoCursoDTO;
	private Long ipuntos;
	private String cnombre;
	private String cdescripcion;
	private String cduracion;
	private Boolean bActivo;
	
	/**
	 * Método de acceso al campo catCursoId.
	 * @return El valor del campo catCursoId
	 */
	public Long getCatCursoId() {
		return catCursoId;
	}
	/**
	 * Asigna el valor al campo catCursoId.
	 * @param catCursoId el valor catCursoId a asignar
	 */
	public void setCatCursoId(Long catCursoId) {
		this.catCursoId = catCursoId;
	}
	/**
	 * Método de acceso al campo catTipoNivelAcademicoDTO.
	 * @return El valor del campo catTipoNivelAcademicoDTO
	 */
	public CatTipoNivelAcademicoDTO getCatTipoNivelAcademicoDTO() {
		return catTipoNivelAcademicoDTO;
	}
	/**
	 * Asigna el valor al campo catTipoNivelAcademicoDTO.
	 * @param catTipoNivelAcademicoDTO el valor catTipoNivelAcademicoDTO a asignar
	 */
	public void setCatTipoNivelAcademicoDTO(
			CatTipoNivelAcademicoDTO catTipoNivelAcademicoDTO) {
		this.catTipoNivelAcademicoDTO = catTipoNivelAcademicoDTO;
	}
	/**
	 * Método de acceso al campo catCategoriaCursoDTO.
	 * @return El valor del campo catCategoriaCursoDTO
	 */
	public CatCategoriaCursoDTO getCatCategoriaCursoDTO() {
		return catCategoriaCursoDTO;
	}
	/**
	 * Asigna el valor al campo catCategoriaCursoDTO.
	 * @param catCategoriaCursoDTO el valor catCategoriaCursoDTO a asignar
	 */
	public void setCatCategoriaCursoDTO(CatCategoriaCursoDTO catCategoriaCursoDTO) {
		this.catCategoriaCursoDTO = catCategoriaCursoDTO;
	}
	/**
	 * Método de acceso al campo catTipoCursoDTO.
	 * @return El valor del campo catTipoCursoDTO
	 */
	public CatTipoCursoDTO getCatTipoCursoDTO() {
		return catTipoCursoDTO;
	}
	/**
	 * Asigna el valor al campo catTipoCursoDTO.
	 * @param catTipoCursoDTO el valor catTipoCursoDTO a asignar
	 */
	public void setCatTipoCursoDTO(CatTipoCursoDTO catTipoCursoDTO) {
		this.catTipoCursoDTO = catTipoCursoDTO;
	}
	/**
	 * Método de acceso al campo ipuntos.
	 * @return El valor del campo ipuntos
	 */
	public Long getIpuntos() {
		return ipuntos;
	}
	/**
	 * Asigna el valor al campo ipuntos.
	 * @param ipuntos el valor ipuntos a asignar
	 */
	public void setIpuntos(Long ipuntos) {
		this.ipuntos = ipuntos;
	}
	/**
	 * Método de acceso al campo cnombre.
	 * @return El valor del campo cnombre
	 */
	public String getCnombre() {
		return cnombre;
	}
	/**
	 * Asigna el valor al campo cnombre.
	 * @param cnombre el valor cnombre a asignar
	 */
	public void setCnombre(String cnombre) {
		this.cnombre = cnombre;
	}
	/**
	 * Método de acceso al campo cdescripcion.
	 * @return El valor del campo cdescripcion
	 */
	public String getCdescripcion() {
		return cdescripcion;
	}
	/**
	 * Asigna el valor al campo cdescripcion.
	 * @param cdescripcion el valor cdescripcion a asignar
	 */
	public void setCdescripcion(String cdescripcion) {
		this.cdescripcion = cdescripcion;
	}
	/**
	 * Método de acceso al campo cduracion.
	 * @return El valor del campo cduracion
	 */
	public String getCduracion() {
		return cduracion;
	}
	/**
	 * Asigna el valor al campo cduracion.
	 * @param cduracion el valor cduracion a asignar
	 */
	public void setCduracion(String cduracion) {
		this.cduracion = cduracion;
	}
	/**
	 * @return the bActivo
	 */
	public Boolean getbActivo() {
		return bActivo;
	}
	/**
	 * @param bActivo the bActivo to set
	 */
	public void setbActivo(Boolean bActivo) {
		this.bActivo = bActivo;
	}
}
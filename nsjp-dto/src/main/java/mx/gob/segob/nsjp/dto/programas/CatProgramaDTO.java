package mx.gob.segob.nsjp.dto.programas;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.detencion.CentroDetencionDTO;



/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public class CatProgramaDTO extends GenericDTO
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1302148239502956067L;

	private Long programaId;
	
	private String nombre;
	
	private String descripcion;
	
	private Date fechaInicioPrograma;
	
	private Date fechaFinPrograma;
	
	private CatTipoProgramaDTO catTipoProgramaDTO;
	
	private List<CatTrabajoDTO> lstCatTrabajoDTO;
	
	private List<CatCursoDTO> lstCatCursoDTO;
	
	private List<CentroDetencionDTO> lstCentroDetencionesDTO;

	private Boolean bActivo;
	
	/**
	 * Método de acceso al campo programaId.
	 * @return El valor del campo programaId
	 */
	public Long getProgramaId() {
		return programaId;
	}

	/**
	 * Asigna el valor al campo programaId.
	 * @param programaId el valor programaId a asignar
	 */
	public void setProgramaId(Long programaId) {
		this.programaId = programaId;
	}

	/**
	 * Método de acceso al campo nombre.
	 * @return El valor del campo nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Asigna el valor al campo nombre.
	 * @param nombre el valor nombre a asignar
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Método de acceso al campo descripcion.
	 * @return El valor del campo descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Asigna el valor al campo descripcion.
	 * @param descripcion el valor descripcion a asignar
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Método de acceso al campo fechaInicioPrograma.
	 * @return El valor del campo fechaInicioPrograma
	 */
	public Date getFechaInicioPrograma() {
		return fechaInicioPrograma;
	}

	/**
	 * Asigna el valor al campo fechaInicioPrograma.
	 * @param fechaInicioPrograma el valor fechaInicioPrograma a asignar
	 */
	public void setFechaInicioPrograma(Date fechaInicioPrograma) {
		this.fechaInicioPrograma = fechaInicioPrograma;
	}

	/**
	 * Método de acceso al campo fechaFinPrograma.
	 * @return El valor del campo fechaFinPrograma
	 */
	public Date getFechaFinPrograma() {
		return fechaFinPrograma;
	}

	/**
	 * Asigna el valor al campo fechaFinPrograma.
	 * @param fechaFinPrograma el valor fechaFinPrograma a asignar
	 */
	public void setFechaFinPrograma(Date fechaFinPrograma) {
		this.fechaFinPrograma = fechaFinPrograma;
	}

	/**
	 * Método de acceso al campo catTipoProgramaDTO.
	 * @return El valor del campo catTipoProgramaDTO
	 */
	public CatTipoProgramaDTO getCatTipoProgramaDTO() {
		return catTipoProgramaDTO;
	}

	/**
	 * Asigna el valor al campo catTipoProgramaDTO.
	 * @param catTipoProgramaDTO el valor catTipoProgramaDTO a asignar
	 */
	public void setCatTipoProgramaDTO(CatTipoProgramaDTO catTipoProgramaDTO) {
		this.catTipoProgramaDTO = catTipoProgramaDTO;
	}

	/**
	 * Método de acceso al campo lstCatTrabajoDTO.
	 * @return El valor del campo lstCatTrabajoDTO
	 */
	public List<CatTrabajoDTO> getLstCatTrabajoDTO() {
		return lstCatTrabajoDTO;
	}

	/**
	 * Asigna el valor al campo lstCatTrabajoDTO.
	 * @param lstCatTrabajoDTO el valor lstCatTrabajoDTO a asignar
	 */
	public void setLstCatTrabajoDTO(List<CatTrabajoDTO> lstCatTrabajoDTO) {
		this.lstCatTrabajoDTO = lstCatTrabajoDTO;
	}

	/**
	 * Método de acceso al campo lstCatCursoDTO.
	 * @return El valor del campo lstCatCursoDTO
	 */
	public List<CatCursoDTO> getLstCatCursoDTO() {
		return lstCatCursoDTO;
	}

	/**
	 * Asigna el valor al campo lstCatCursoDTO.
	 * @param lstCatCursoDTO el valor lstCatCursoDTO a asignar
	 */
	public void setLstCatCursoDTO(List<CatCursoDTO> lstCatCursoDTO) {
		this.lstCatCursoDTO = lstCatCursoDTO;
	}

	/**
	 * Método de acceso al campo lstCentroDetencionesDTO.
	 * @return El valor del campo lstCentroDetencionesDTO
	 */
	public List<CentroDetencionDTO> getLstCentroDetencionesDTO() {
		return lstCentroDetencionesDTO;
	}

	/**
	 * Asigna el valor al campo lstCentroDetencionesDTO.
	 * @param lstCentroDetencionesDTO el valor lstCentroDetencionesDTO a asignar
	 */
	public void setLstCentroDetencionesDTO(
			List<CentroDetencionDTO> lstCentroDetencionesDTO) {
		this.lstCentroDetencionesDTO = lstCentroDetencionesDTO;
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
	
	/**
	 * M&eacute;todo utilitario que calcula el total de puntos 
	 * asociados al programa que provienen de cursos.
	 * @return totalPuntos el n&uacute;mero de puntos derivados de cursos
	 */
	public Long getTotalPuntosCursos(){
		long totalPuntos = 0;
		if (this.lstCatCursoDTO != null && !this.lstCatCursoDTO.isEmpty()){
			for (CatCursoDTO curso : this.lstCatCursoDTO){
				totalPuntos += curso.getIpuntos();
			}
		}
		return totalPuntos;
	}
	
	/**
	 * M&eacute;todo utilitario que calcula el total de puntos 
	 * asociados al programa que provienen de trabajos.
	 * @return totalPuntos el n&uacute;mero de puntos derivados de trabajos
	 */
	public Long getTotalPuntosTrabajos(){
		long totalPuntos = 0;
		if (this.lstCatTrabajoDTO != null && !this.lstCatTrabajoDTO.isEmpty()){
			for (CatTrabajoDTO trabajo : this.lstCatTrabajoDTO){
				totalPuntos += trabajo.getIpuntos();
			}
		}
		return totalPuntos;
	}
	
	/**
	 * M&eacute;todo utilitario que calcula el total de puntos 
	 * asociados al programa que provienen de trabajos y cursos.
	 * @return totalPuntos el n&uacute;mero de puntos derivados de trabajos y cursos.
	 */
	public Long getTotalPuntosPrograma(){
		return getTotalPuntosCursos() + getTotalPuntosTrabajos();
	}
}
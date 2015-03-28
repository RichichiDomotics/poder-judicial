package mx.gob.segob.nsjp.dto.sentencia;


import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.detencion.CentroDetencionDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.programas.ActoBuenaConductaDTO;
import mx.gob.segob.nsjp.dto.programas.AsignacionCentroDetencionDTO;
import mx.gob.segob.nsjp.dto.programas.AsignacionMedidaAlternaDTO;
import mx.gob.segob.nsjp.dto.programas.AsignacionProgramaDTO;
import mx.gob.segob.nsjp.dto.programas.RemisionDTO;
import mx.gob.segob.nsjp.dto.reinsercion.InventarioPertenenciaDTO;

public class SentenciaDTO extends GenericDTO {

	// Fields
	/**
	 * 
	 */
	private static final long serialVersionUID = 5498700229409275424L;
	private Long sentenciaId;
	private ExpedienteDTO numeroExpedienteDTO;
	private ValorDTO valorDTO;
	private InvolucradoDTO involucradoDTO;
	private String cnus;
	private String cnumeroCausa;
	private Boolean blesionado;
	private Date dfechaInicioPena;
	private Date dfechaFinPena;
	private Long ipuntosPorAcumular;
	private Boolean bcumplida;
	private List<AsignacionProgramaDTO> asignacionProgramas;
	private List<AsignacionMedidaAlternaDTO> asignacionMedidaAlternas;
	private List<RemisionDTO> remisions;
	private List<AsignacionCentroDetencionDTO> asignacionCentroDetencions;
	private List<ActoBuenaConductaDTO> actoBuenaConductas;
	private InventarioPertenenciaDTO inventarioPertenenciaDTO;
	private Boolean esUnicoNUS;
	
	/**
	 * @return the sentenciaId
	 */
	public Long getSentenciaId() {
		return sentenciaId;
	}
	/**
	 * @param sentenciaId the sentenciaId to set
	 */
	public void setSentenciaId(Long sentenciaId) {
		this.sentenciaId = sentenciaId;
	}
	/**
	 * @return the numeroExpedienteDTO
	 */
	public ExpedienteDTO getNumeroExpedienteDTO() {
		return numeroExpedienteDTO;
	}
	/**
	 * @param numeroExpedienteDTO the numeroExpedienteDTO to set
	 */
	public void setNumeroExpedienteDTO(ExpedienteDTO numeroExpedienteDTO) {
		this.numeroExpedienteDTO = numeroExpedienteDTO;
	}
	/**
	 * @return the valorDTO
	 */
	public ValorDTO getValorDTO() {
		return valorDTO;
	}
	/**
	 * @param valorDTO the valorDTO to set
	 */
	public void setValorDTO(ValorDTO valorDTO) {
		this.valorDTO = valorDTO;
	}
	/**
	 * @return the involucradoDTO
	 */
	public InvolucradoDTO getInvolucradoDTO() {
		return involucradoDTO;
	}
	/**
	 * @param involucradoDTO the involucradoDTO to set
	 */
	public void setInvolucradoDTO(InvolucradoDTO involucradoDTO) {
		this.involucradoDTO = involucradoDTO;
	}
	/**
	 * @return the cnus
	 */
	public String getCnus() {
		return cnus;
	}
	/**
	 * @param cnus the cnus to set
	 */
	public void setCnus(String cnus) {
		this.cnus = cnus;
	}
	/**
	 * @return the cnumeroCausa
	 */
	public String getCnumeroCausa() {
		return cnumeroCausa;
	}
	/**
	 * @param cnumeroCausa the cnumeroCausa to set
	 */
	public void setCnumeroCausa(String cnumeroCausa) {
		this.cnumeroCausa = cnumeroCausa;
	}
	/**
	 * @return the blesionado
	 */
	public Boolean getBlesionado() {
		return blesionado;
	}
	/**
	 * @param blesionado the blesionado to set
	 */
	public void setBlesionado(Boolean blesionado) {
		this.blesionado = blesionado;
	}
	/**
	 * @return the dfechaInicioPena
	 */
	public Date getDfechaInicioPena() {
		return dfechaInicioPena;
	}
	/**
	 * @param dfechaInicioPena the dfechaInicioPena to set
	 */
	public void setDfechaInicioPena(Date dfechaInicioPena) {
		this.dfechaInicioPena = dfechaInicioPena;
	}
	/**
	 * @return the dfechaFinPena
	 */
	public Date getDfechaFinPena() {
		return dfechaFinPena;
	}
	/**
	 * @param dfechaFinPena the dfechaFinPena to set
	 */
	public void setDfechaFinPena(Date dfechaFinPena) {
		this.dfechaFinPena = dfechaFinPena;
	}
	/**
	 * @return the ipuntosPorAcumular
	 */
	public Long getIpuntosPorAcumular() {
		return ipuntosPorAcumular;
	}
	/**
	 * @param ipuntosPorAcumular the ipuntosPorAcumular to set
	 */
	public void setIpuntosPorAcumular(Long ipuntosPorAcumular) {
		this.ipuntosPorAcumular = ipuntosPorAcumular;
	}
	/**
	 * @return the bcumplida
	 */
	public Boolean getBcumplida() {
		return bcumplida;
	}
	/**
	 * @param bcumplida the bcumplida to set
	 */
	public void setBcumplida(Boolean bcumplida) {
		this.bcumplida = bcumplida;
	}
	/**
	 * @return the asignacionProgramas
	 */
	public List<AsignacionProgramaDTO> getAsignacionProgramas() {
		return asignacionProgramas;
	}
	/**
	 * @param asignacionProgramas the asignacionProgramas to set
	 */
	public void setAsignacionProgramas(
			List<AsignacionProgramaDTO> asignacionProgramas) {
		this.asignacionProgramas = asignacionProgramas;
	}
	/**
	 * @return the asignacionMedidaAlternas
	 */
	public List<AsignacionMedidaAlternaDTO> getAsignacionMedidaAlternas() {
		return asignacionMedidaAlternas;
	}
	/**
	 * @param asignacionMedidaAlternas the asignacionMedidaAlternas to set
	 */
	public void setAsignacionMedidaAlternas(
			List<AsignacionMedidaAlternaDTO> asignacionMedidaAlternas) {
		this.asignacionMedidaAlternas = asignacionMedidaAlternas;
	}
	/**
	 * @return the remisions
	 */
	public List<RemisionDTO> getRemisions() {
		return remisions;
	}
	/**
	 * @param remisions the remisions to set
	 */
	public void setRemisions(List<RemisionDTO> remisions) {
		this.remisions = remisions;
	}
	/**
	 * @return the asignacionCentroDetencions
	 */
	public List<AsignacionCentroDetencionDTO> getAsignacionCentroDetencions() {
		return asignacionCentroDetencions;
	}
	/**
	 * @param asignacionCentroDetencions the asignacionCentroDetencions to set
	 */
	public void setAsignacionCentroDetencions(
			List<AsignacionCentroDetencionDTO> asignacionCentroDetencions) {
		this.asignacionCentroDetencions = asignacionCentroDetencions;
	}
	
	/**
	 * Método de acceso al campo actoBuenaConductas.
	 * @return El valor del campo actoBuenaConductas
	 */
	public List<ActoBuenaConductaDTO> getActoBuenaConductas() {
		return actoBuenaConductas;
	}
	
	/**
	 * Asigna el valor al campo actoBuenaConductas.
	 * @param actoBuenaConductas el valor actoBuenaConductas a asignar
	 */
	public void setActoBuenaConductas(List<ActoBuenaConductaDTO> actoBuenaConductas) {
		this.actoBuenaConductas = actoBuenaConductas;
	}

	/**
	 * @return the esUnicoNUS
	 */
	public Boolean getEsUnicoNUS() {
		return esUnicoNUS;
	}
	/**
	 * @param esUnicoNUS the esUnicoNUS to set
	 */
	public void setEsUnicoNUS(Boolean esUnicoNUS) {
		this.esUnicoNUS = esUnicoNUS;
	}	
	
	
	/**
	 * Método de acceso al campo inventarioPertenenciaDTO.
	 * @return El valor del campo inventarioPertenenciaDTO
	 */
	public InventarioPertenenciaDTO getInventarioPertenenciaDTO() {
		return inventarioPertenenciaDTO;
	}
	
	/**
	 * Asigna el valor al campo inventarioPertenenciaDTO.
	 * @param inventarioPertenenciaDTO el valor inventarioPertenenciaDTO a asignar
	 */
	public void setInventarioPertenenciaDTO(
			InventarioPertenenciaDTO inventarioPertenenciaDTO) {
		this.inventarioPertenenciaDTO = inventarioPertenenciaDTO;
	}
	
	/**
	 * M&eacute;todo utilitario que calcula el total de puntos 
	 * obtenidos de todos los programas que han sido asignados 
	 * a la sentencia y actos de buena conducta que han sido acumulados.
	 * @return totalPuntos el n&uacute;mero de puntos obtenidos 
	 * 		   de programas y actos de buena conducta.
	 */
	public Long getTotalPuntosObtenidos(){
		long totalPuntos = 0;
		if (this.asignacionProgramas != null && !this.asignacionProgramas.isEmpty()){
			for (AsignacionProgramaDTO asignacionPrograma : this.asignacionProgramas){
				totalPuntos += asignacionPrograma.getProgramaDTO().getPuntosObtenidosPrograma();
			}
		}
		if (this.actoBuenaConductas != null && !this.actoBuenaConductas.isEmpty()){
			for (ActoBuenaConductaDTO abcDTO : this.actoBuenaConductas){
				totalPuntos += abcDTO.getPuntosAcumulados();
			}
		}
		return totalPuntos;
	}
	
	
	/**
	 * M&eacute;todo utilitario que se encarga de obtener el centro de 
	 * detenci&oacute;n en el cual se encuentra arraigado el sentenciado 
	 * en el caso de que la bandera arraigado no tenga un valor de verdadero 
	 * para ninguna asignaci&oacute;n, se regresa el centro de detenci&oacute;n 
	 * de la &uacute;ltima asignaci&oacute;n 
	 * 
	 * @return centroDetencion el centro de detenci&oacute;n en el cual 
	 * 		   est&aacute; arraigado el sentenciado.
	 */
	public CentroDetencionDTO getCentroDetencionActual(){
		CentroDetencionDTO cd = null;
		if (this.asignacionCentroDetencions != null && !this.asignacionCentroDetencions.isEmpty()){
			for (AsignacionCentroDetencionDTO asignacionCentro : this.asignacionCentroDetencions){
				if (asignacionCentro.getBarraigado()){
					cd = asignacionCentro.getCentroDetencionDTO();
				}
			}
			if (cd == null){
				int maxAsignacion = this.asignacionCentroDetencions.size()-1;
				cd = this.asignacionCentroDetencions.get(maxAsignacion).getCentroDetencionDTO();
			}
		}
		return cd;
	}

}
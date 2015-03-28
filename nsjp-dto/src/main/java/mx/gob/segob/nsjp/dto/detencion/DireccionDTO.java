package mx.gob.segob.nsjp.dto.detencion;

import java.util.HashSet;
import java.util.Set;

import mx.gob.segob.nsjp.dto.domicilio.AsentamientoDTO;

public class DireccionDTO {
	

	private Long direccionId;
	private AsentamientoDTO asentamiento;
	private String calle;
	private String numeroExterior;
	private String numeroInterior;
	private String numeroLote;
	private String referencias;
	private String entreCalle1;
	private String entreCalle2;
	private Set<CentroDetencionDTO> centroDetencions = new HashSet<CentroDetencionDTO>(
			0);
	/**
	 * @return the direccionId
	 */
	public Long getDireccionId() {
		return direccionId;
	}
	/**
	 * @param direccionId the direccionId to set
	 */
	public void setDireccionId(Long direccionId) {
		this.direccionId = direccionId;
	}
	/**
	 * @return the asentamiento
	 */
	public AsentamientoDTO getAsentamiento() {
		return asentamiento;
	}
	/**
	 * @param asentamiento the asentamiento to set
	 */
	public void setAsentamiento(AsentamientoDTO asentamiento) {
		this.asentamiento = asentamiento;
	}
	/**
	 * @return the calle
	 */
	public String getCalle() {
		return calle;
	}
	/**
	 * @param calle the calle to set
	 */
	public void setCalle(String calle) {
		this.calle = calle;
	}
	/**
	 * @return the numeroExterior
	 */
	public String getNumeroExterior() {
		return numeroExterior;
	}
	/**
	 * @param numeroExterior the numeroExterior to set
	 */
	public void setNumeroExterior(String numeroExterior) {
		this.numeroExterior = numeroExterior;
	}
	/**
	 * @return the numeroInterior
	 */
	public String getNumeroInterior() {
		return numeroInterior;
	}
	/**
	 * @param numeroInterior the numeroInterior to set
	 */
	public void setNumeroInterior(String numeroInterior) {
		this.numeroInterior = numeroInterior;
	}
	/**
	 * @return the numeroLote
	 */
	public String getNumeroLote() {
		return numeroLote;
	}
	/**
	 * @param numeroLote the numeroLote to set
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}
	/**
	 * @return the referencias
	 */
	public String getReferencias() {
		return referencias;
	}
	/**
	 * @param referencias the referencias to set
	 */
	public void setReferencias(String referencias) {
		this.referencias = referencias;
	}
	/**
	 * @return the entreCalle1
	 */
	public String getEntreCalle1() {
		return entreCalle1;
	}
	/**
	 * @param entreCalle1 the entreCalle1 to set
	 */
	public void setEntreCalle1(String entreCalle1) {
		this.entreCalle1 = entreCalle1;
	}
	/**
	 * @return the entreCalle2
	 */
	public String getEntreCalle2() {
		return entreCalle2;
	}
	/**
	 * @param entreCalle2 the entreCalle2 to set
	 */
	public void setEntreCalle2(String entreCalle2) {
		this.entreCalle2 = entreCalle2;
	}
	/**
	 * @return the centroDetencions
	 */
	public Set<CentroDetencionDTO> getCentroDetencions() {
		return centroDetencions;
	}
	/**
	 * @param centroDetencions the centroDetencions to set
	 */
	public void setCentroDetencions(Set<CentroDetencionDTO> centroDetencions) {
		this.centroDetencions = centroDetencions;
	}
	
}

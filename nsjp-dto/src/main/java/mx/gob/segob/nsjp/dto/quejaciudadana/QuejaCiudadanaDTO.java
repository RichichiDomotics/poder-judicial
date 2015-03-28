/**
 * 
 */
package mx.gob.segob.nsjp.dto.quejaciudadana;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.persona.MedioDeContactoDTO;

/**
 * @author adrian
 *
 */
public class QuejaCiudadanaDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2936371115368966572L;
	private Long quejaCiudadanaId;
	private String folioQueja;         	
    private Date fechaRegistro;      	
    private String descripcionQueja; 
    //Denunciado
    private String nombreDenunciado;
    private String apellidoPatDenunciado;
    private String apellidoMatDenunciado;
    private ValorDTO calidadDenunciado;
    //Afectado
    private String nombreAfectado;  
    private String apellidoPatAfectado;  
    private String apellidoMatAfectado;
    private ValorDTO calidadAfectado;
    //Quejoso
    private String nombreQuejoso;
    private String apellidoPatQuejoso;
    private String apellidoMatQuejoso;
    private ValorDTO calidadQuejoso;
    
    private String numeroExpediente; 
    private ValorDTO tipoQuejaDTO;
    private ValorDTO motivoRechazoDTO;
    private ValorDTO estatusQuejaDTO;
    private InvolucradoDTO involucradoDTO;
    private QuejaConfirmadaDTO quejaConfirmadaDTO;
    private List<DocumentoDTO> documentosDTO;
    private List<MedioDeContactoDTO> mediosDenunciado;
	/**
	 * @return the quejaCiudadanaId
	 */
	public Long getQuejaCiudadanaId() {
		return quejaCiudadanaId;
	}
	/**
	 * @return the folioQueja
	 */
	public String getFolioQueja() {
		return folioQueja;
	}
	/**
	 * @return the fechaRegistro
	 */
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	/**
	 * @return the descripcionQueja
	 */
	public String getDescripcionQueja() {
		return descripcionQueja;
	}		
	/**
	 * @return the nombreAfectado
	 */
	public String getNombreAfectado() {
		return nombreAfectado;
	}
	/**
	 * @return the numeroExpediente
	 */
	public String getNumeroExpediente() {
		return numeroExpediente;
	}
	/**
	 * @return the tipoQuejaDTO
	 */
	public ValorDTO getTipoQuejaDTO() {
		return tipoQuejaDTO;
	}
	/**
	 * @return the motivoRechazoDTO
	 */
	public ValorDTO getMotivoRechazoDTO() {
		return motivoRechazoDTO;
	}
	/**
	 * @return the estatusQuejaDTO
	 */
	
	public ValorDTO getEstatusQuejaDTO() {
		return estatusQuejaDTO;
	}
	/**
	 * @return the involucradoDTO
	 */
	public InvolucradoDTO getInvolucradoDTO() {
		return involucradoDTO;
	}
	/**
	 * @return the quejaConfirmadaDTO
	 */
	public QuejaConfirmadaDTO getQuejaConfirmadaDTO() {
		return quejaConfirmadaDTO;
	}
	/**
	 * @return the documentosDTO
	 */
	public List<DocumentoDTO> getDocumentosDTO() {
		return documentosDTO;
	}
	/**
	 * @param quejaCiudadanaId the quejaCiudadanaId to set
	 */
	public void setQuejaCiudadanaId(Long quejaCiudadanaId) {
		this.quejaCiudadanaId = quejaCiudadanaId;
	}
	/**
	 * @param folioQueja the folioQueja to set
	 */
	public void setFolioQueja(String folioQueja) {
		this.folioQueja = folioQueja;
	}
	/**
	 * @param fechaRegistro the fechaRegistro to set
	 */
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	/**
	 * @param descripcionQueja the descripcionQueja to set
	 */
	public void setDescripcionQueja(String descripcionQueja) {
		this.descripcionQueja = descripcionQueja;
	}	
	/**
	 * @param nombreAfectado the nombreAfectado to set
	 */
	public void setNombreAfectado(String nombreAfectado) {
		this.nombreAfectado = nombreAfectado;
	}
	/**
	 * @param numeroExpediente the numeroExpediente to set
	 */
	public void setNumeroExpediente(String numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}
	/**
	 * @param tipoQuejaDTO the tipoQuejaDTO to set
	 */
	public void setTipoQuejaDTO(ValorDTO tipoQuejaDTO) {
		this.tipoQuejaDTO = tipoQuejaDTO;
	}
	/**
	 * @param motivoRechazoDTO the motivoRechazoDTO to set
	 */
	public void setMotivoRechazoDTO(ValorDTO motivoRechazoDTO) {
		this.motivoRechazoDTO = motivoRechazoDTO;
	}
	/**
	 * @param estatusQuejaDTO the estatusQuejaDTO to set
	 */
	public void setEstatusQuejaDTO(ValorDTO estatusQuejaDTO) {
		this.estatusQuejaDTO = estatusQuejaDTO;
	}
	/**
	 * @param involucradoDTO the involucradoDTO to set
	 */
	public void setInvolucradoDTO(InvolucradoDTO involucradoDTO) {
		this.involucradoDTO = involucradoDTO;
	}
	/**
	 * @param quejaConfirmadaDTO the quejaConfirmadaDTO to set
	 */
	public void setQuejaConfirmadaDTO(QuejaConfirmadaDTO quejaConfirmadaDTO) {
		this.quejaConfirmadaDTO = quejaConfirmadaDTO;
	}
	/**
	 * @param documentosDTO the documentosDTO to set
	 */
	public void setDocumentosDTO(List<DocumentoDTO> documentosDTO) {
		this.documentosDTO = documentosDTO;
	}
	/**
	 * Método de acceso al campo apellidoPatAfectado.
	 * @return El valor del campo apellidoPatAfectado
	 */
	public String getApellidoPatAfectado() {
		return apellidoPatAfectado;
	}
	/**
	 * Asigna el valor al campo apellidoPatAfectado.
	 * @param apellidoPatAfectado el valor apellidoPatAfectado a asignar
	 */
	public void setApellidoPatAfectado(String apellidoPatAfectado) {
		this.apellidoPatAfectado = apellidoPatAfectado;
	}
	/**
	 * Método de acceso al campo apellidoMatAfectado.
	 * @return El valor del campo apellidoMatAfectado
	 */
	public String getApellidoMatAfectado() {
		return apellidoMatAfectado;
	}
	/**
	 * Asigna el valor al campo apellidoMatAfectado.
	 * @param apellidoMatAfectado el valor apellidoMatAfectado a asignar
	 */
	public void setApellidoMatAfectado(String apellidoMatAfectado) {
		this.apellidoMatAfectado = apellidoMatAfectado;
	}
	/**
	 * Método de acceso al campo nombreDenunciado.
	 * @return El valor del campo nombreDenunciado
	 */
	public String getNombreDenunciado() {
		return nombreDenunciado;
	}
	/**
	 * Asigna el valor al campo nombreDenunciado.
	 * @param nombreDenunciado el valor nombreDenunciado a asignar
	 */
	public void setNombreDenunciado(String nombreDenunciado) {
		this.nombreDenunciado = nombreDenunciado;
	}
	/**
	 * Método de acceso al campo apellidoPatDenunciado.
	 * @return El valor del campo apellidoPatDenunciado
	 */
	public String getApellidoPatDenunciado() {
		return apellidoPatDenunciado;
	}
	/**
	 * Asigna el valor al campo apellidoPatDenunciado.
	 * @param apellidoPatDenunciado el valor apellidoPatDenunciado a asignar
	 */
	public void setApellidoPatDenunciado(String apellidoPatDenunciado) {
		this.apellidoPatDenunciado = apellidoPatDenunciado;
	}
	/**
	 * Método de acceso al campo apellidoMatDenunciado.
	 * @return El valor del campo apellidoMatDenunciado
	 */
	public String getApellidoMatDenunciado() {
		return apellidoMatDenunciado;
	}
	/**
	 * Asigna el valor al campo apellidoMatDenunciado.
	 * @param apellidoMatDenunciado el valor apellidoMatDenunciado a asignar
	 */
	public void setApellidoMatDenunciado(String apellidoMatDenunciado) {
		this.apellidoMatDenunciado = apellidoMatDenunciado;
	}
	/**
	 * Método de acceso al campo nombreQuejoso.
	 * @return El valor del campo nombreQuejoso
	 */
	public String getNombreQuejoso() {
		return nombreQuejoso;
	}
	/**
	 * Asigna el valor al campo nombreQuejoso.
	 * @param nombreQuejoso el valor nombreQuejoso a asignar
	 */
	public void setNombreQuejoso(String nombreQuejoso) {
		this.nombreQuejoso = nombreQuejoso;
	}
	/**
	 * Método de acceso al campo apellidoPatQuejoso.
	 * @return El valor del campo apellidoPatQuejoso
	 */
	public String getApellidoPatQuejoso() {
		return apellidoPatQuejoso;
	}
	/**
	 * Asigna el valor al campo apellidoPatQuejoso.
	 * @param apellidoPatQuejoso el valor apellidoPatQuejoso a asignar
	 */
	public void setApellidoPatQuejoso(String apellidoPatQuejoso) {
		this.apellidoPatQuejoso = apellidoPatQuejoso;
	}
	/**
	 * Método de acceso al campo apellidiMatQuejoso.
	 * @return El valor del campo apellidiMatQuejoso
	 */
	public String getApellidoMatQuejoso() {
		return apellidoMatQuejoso;
	}
	/**
	 * Asigna el valor al campo apellidiMatQuejoso.
	 * @param apellidiMatQuejoso el valor apellidiMatQuejoso a asignar
	 */
	public void setApellidoMatQuejoso(String apellidoMatQuejoso) {
		this.apellidoMatQuejoso = apellidoMatQuejoso;
	}
	/**
	 * Método de acceso al campo mediosDenunciado.
	 * @return El valor del campo mediosDenunciado
	 */
	public List<MedioDeContactoDTO> getMediosDenunciado() {
		return mediosDenunciado;
	}
	/**
	 * Asigna el valor al campo mediosDenunciado.
	 * @param mediosDenunciado el valor mediosDenunciado a asignar
	 */
	public void setMediosDenunciado(List<MedioDeContactoDTO> mediosDenunciado) {
		this.mediosDenunciado = mediosDenunciado;
	}
	/**
	 * @return the calidadDenunciado
	 */
	public ValorDTO getCalidadDenunciado() {
		return calidadDenunciado;
	}
	/**
	 * @param calidadDenunciado the calidadDenunciado to set
	 */
	public void setCalidadDenunciado(ValorDTO calidadDenunciado) {
		this.calidadDenunciado = calidadDenunciado;
	}
	/**
	 * @return the calidadAfectado
	 */
	public ValorDTO getCalidadAfectado() {
		return calidadAfectado;
	}
	/**
	 * @param calidadAfectado the calidadAfectado to set
	 */
	public void setCalidadAfectado(ValorDTO calidadAfectado) {
		this.calidadAfectado = calidadAfectado;
	}
	/**
	 * @return the calidadQuejoso
	 */
	public ValorDTO getCalidadQuejoso() {
		return calidadQuejoso;
	}
	/**
	 * @param calidadQuejoso the calidadQuejoso to set
	 */
	public void setCalidadQuejoso(ValorDTO calidadQuejoso) {
		this.calidadQuejoso = calidadQuejoso;
	}
	
}

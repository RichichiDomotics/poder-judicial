/**
 * 
 */
package mx.gob.segob.nsjp.dto.documento;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.dto.catalogo.CatDelitoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.hecho.HechoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.persona.MedioDeContactoDTO;


/**
 * @author adrian
 *
 */
public class AvisoHechoDelictivoDTO extends NotificacionDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4924826347216531640L;
	
	private Date fechaAtencion;
	private HechoDTO hechoDTO;
	private CatDelitoDTO catDelito;
	
	private Boolean esAnonimo;
	private Long idImplicado;
	private String nombreImplicado;
	private String apellidoMatImplicado;
	private String apellidoPatImplicado;
	private ValorDTO calidadImplicado;
	private ValorDTO motivoRechazo;
	private List<MedioDeContactoDTO> mediosImplicado;
	
	public AvisoHechoDelictivoDTO() {
		super();
	}
	public AvisoHechoDelictivoDTO(Long idAvisoHecho) {
		super();
		setDocumentoId(idAvisoHecho);
	}
	/**
	 * @return the dFechaAtencion
	 */
	public Date getFechaAtencion() {
		return fechaAtencion;
	}
	/**
	 * @param dFechaAtencion the dFechaAtencion to set
	 */
	public void setFechaAtencion(Date dFechaAtencion) {
		this.fechaAtencion = dFechaAtencion;
	}
	/**
	 * @return the hechoDTO
	 */
	public HechoDTO getHechoDTO() {
		return hechoDTO;
	}
	/**
	 * @param hechoDTO the hechoDTO to set
	 */
	public void setHechoDTO(HechoDTO hechoDTO) {
		this.hechoDTO = hechoDTO;
	}
	/**
	 * @param catDelito the catDelito to set
	 */
	public void setCatDelito(CatDelitoDTO catDelito) {
		this.catDelito = catDelito;
	}
	/**
	 * @return the catDelito
	 */
	public CatDelitoDTO getCatDelito() {
		return catDelito;
	}
	/**
	 * @return the nombreImplicado
	 */
	public String getNombreImplicado() {
		return nombreImplicado;
	}
	/**
	 * @param nombreImplicado the nombreImplicado to set
	 */
	public void setNombreImplicado(String nombreImplicado) {
		this.nombreImplicado = nombreImplicado;
	}
	/**
	 * @return the apellidoMatImplicado
	 */
	public String getApellidoMatImplicado() {
		return apellidoMatImplicado;
	}
	/**
	 * @param apellidoMatImplicado the apellidoMatImplicado to set
	 */
	public void setApellidoMatImplicado(String apellidoMatImplicado) {
		this.apellidoMatImplicado = apellidoMatImplicado;
	}
	/**
	 * @return the apellidoPatImplicado
	 */
	public String getApellidoPatImplicado() {
		return apellidoPatImplicado;
	}
	/**
	 * @param apellidoPatImplicado the apellidoPatImplicado to set
	 */
	public void setApellidoPatImplicado(String apellidoPatImplicado) {
		this.apellidoPatImplicado = apellidoPatImplicado;
	}
	/**
	 * @return the calidadImplicado
	 */
	public ValorDTO getCalidadImplicado() {
		return calidadImplicado;
	}
	/**
	 * @param calidadImplicado the calidadImplicado to set
	 */
	public void setCalidadImplicado(ValorDTO calidadImplicado) {
		this.calidadImplicado = calidadImplicado;
	}
	/**
	 * @return the mediosImplicado
	 */
	public List<MedioDeContactoDTO> getMediosImplicado() {
		return mediosImplicado;
	}
	/**
	 * @param mediosImplicado the mediosImplicado to set
	 */
	public void setMediosImplicado(List<MedioDeContactoDTO> mediosImplicado) {
		this.mediosImplicado = mediosImplicado;
	}
	/**
	 * @return the esAnonimo
	 */
	public Boolean getEsAnonimo() {
		return esAnonimo;
	}
	/**
	 * @param esAnonimo the esAnonimo to set
	 */
	public void setEsAnonimo(Boolean esAnonimo) {
		this.esAnonimo = esAnonimo;
	}
    /**
     * Método de acceso al campo motivoRechazo.
     * @return El valor del campo motivoRechazo
     */
    public ValorDTO getMotivoRechazo() {
        return motivoRechazo;
    }
    /**
     * Asigna el valor al campo motivoRechazo.
     * @param motivoRechazo el valor motivoRechazo a asignar
     */
    public void setMotivoRechazo(ValorDTO motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }
	/**
	 * Método de acceso al campo idImplicado.
	 * @return El valor del campo idImplicado
	 */
	public Long getIdImplicado() {
		return idImplicado;
	}
	/**
	 * Asigna el valor al campo idImplicado.
	 * @param idImplicado el valor idImplicado a asignar
	 */
	public void setIdImplicado(Long idImplicado) {
		this.idImplicado = idImplicado;
	}
	
}

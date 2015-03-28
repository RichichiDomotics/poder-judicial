/**
 * 
 */
package mx.gob.segob.nsjp.web.solicitud.form;

import mx.gob.segob.nsjp.web.base.form.GenericForm;

/**
 * @author AlejandroGA
 *
 */
public class VisorSolicitudAudienciaForm extends GenericForm{
	
	/**
	 *Serializacion 
	 */
	private static final long serialVersionUID = 1L;
	private Long idAudiencia;
	private Long idVisor;
	private Long idAudienciaSiguiente;
	
	/**
	 * @return the idAudiencia
	 */
	public Long getIdAudiencia() {
		return idAudiencia;
	}
	
	/**
	 * @param idAudiencia the idAudiencia to set
	 */
	public void setIdAudiencia(Long idAudiencia) {
		this.idAudiencia = idAudiencia;
	}
	
	/**
	 * @return the idVisor
	 */
	public Long getIdVisor() {
		return idVisor;
	}
	
	/**
	 * @param idVisor the idVisor to set
	 */
	public void setIdVisor(Long idVisor) {
		this.idVisor = idVisor;
	}
	
	/**
	 * @return the idAudienciaSiguiente
	 */
	public Long getIdAudienciaSiguiente() {
		return idAudienciaSiguiente;
	}
	/**
	 * @param idAudienciaSiguiente the idAudienciaSiguiente to set
	 */
	public void setIdAudienciaSiguiente(Long idAudienciaSiguiente) {
		this.idAudienciaSiguiente = idAudienciaSiguiente;
	}
	
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

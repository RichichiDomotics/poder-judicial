/**
 * 
 */
package mx.gob.segob.nsjp.dto.involucrado;

import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;

/**
 * @author EduardoAD
 *
 */
public class InvolucradoAudienciaDTO {
	private InvolucradoAudienciaIdDTO id;
	private InvolucradoDTO involucrado;
	private AudienciaDTO audiencia;	
	private Boolean esPresente;
	
	
	public InvolucradoAudienciaIdDTO getId() {
		return id;
	}
	public void setId(InvolucradoAudienciaIdDTO id) {
		this.id = id;
	}
	public InvolucradoDTO getInvolucrado() {
		return involucrado;
	}
	public void setInvolucrado(InvolucradoDTO involucrado) {
		this.involucrado = involucrado;
	}
	public AudienciaDTO getAudiencia() {
		return audiencia;
	}
	public void setAudiencia(AudienciaDTO audiencia) {
		this.audiencia = audiencia;
	}
	public Boolean getEsPresente() {
		return esPresente;
	}
	public void setEsPresente(Boolean esPresente) {
		this.esPresente = esPresente;
	}
}

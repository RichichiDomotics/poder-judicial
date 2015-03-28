/**
 * 
 */
package mx.gob.segob.nsjp.dto.funcionario;

import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;

/**
 * @author EduardoAD
 *
 */
public class FuncionarioAudienciaDTO {
	private FuncionarioAudienciaIdDTO id;
	private FuncionarioDTO funcionario;
	private AudienciaDTO audiencia;	
	private Boolean esPresente;
	private Boolean esTitular;

	public FuncionarioAudienciaIdDTO getId() {
		return id;
	}
	public void setId(FuncionarioAudienciaIdDTO id) {
		this.id = id;
	}
	public FuncionarioDTO getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(FuncionarioDTO funcionario) {
		this.funcionario = funcionario;
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
	public Boolean getEsTitular() {
		return esTitular;
	}
	public void setEsTitular(Boolean esTitular) {
		this.esTitular = esTitular;
	}
}

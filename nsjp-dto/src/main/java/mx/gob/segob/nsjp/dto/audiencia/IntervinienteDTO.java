/**
 * Nombre del Programa : IntervinienteDTO.java
 * Autor                            : LuisMG
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 14/10/2011
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
package mx.gob.segob.nsjp.dto.audiencia;

import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author LuisMG
 * 
 */
public class IntervinienteDTO {
	private Long intervinienteId;
	private boolean esAceptado;
	private boolean esParteFiscalia;
	private boolean esSuperVeniente;
	private boolean esPresente;
	private boolean esTitular;
	private FuncionarioDTO funcionario;
	private InvolucradoDTO involucrado;
	private AudienciaDTO audiencia;

	/**
	 * 
	 */
	public IntervinienteDTO() {

	}

	public void setIntervinienteId(Long intervinienteId) {
		this.intervinienteId = intervinienteId;
	}

	public Long getIntervinienteId() {
		return intervinienteId;
	}

	public boolean isEsAceptado() {
		return esAceptado;
	}

	public void setEsAceptado(boolean esAceptado) {
		this.esAceptado = esAceptado;
	}

	public boolean isEsParteFiscalia() {
		return esParteFiscalia;
	}

	public void setEsParteFiscalia(boolean esParteFiscalia) {
		this.esParteFiscalia = esParteFiscalia;
	}

	public boolean isEsSuperVeniente() {
		return esSuperVeniente;
	}

	public void setEsSuperVeniente(boolean esSuperVeniente) {
		this.esSuperVeniente = esSuperVeniente;
	}

	public boolean isEsPresente() {
		return esPresente;
	}

	public void setEsPresente(boolean esPresente) {
		this.esPresente = esPresente;
	}

	public boolean isEsTitular() {
		return esTitular;
	}

	public void setEsTitular(boolean esTitular) {
		this.esTitular = esTitular;
	}

	public FuncionarioDTO getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioDTO funcionario) {
		this.funcionario = funcionario;
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

}

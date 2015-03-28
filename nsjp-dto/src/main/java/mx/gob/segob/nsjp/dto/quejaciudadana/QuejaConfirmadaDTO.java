/**
 * 
 */
package mx.gob.segob.nsjp.dto.quejaciudadana;

import java.util.List;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.documento.NotaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;

/**
 * @author adrian
 *
 */
public class QuejaConfirmadaDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8150109323331034602L;
	
	private Long quejaConfirmadaId;
	private List<NotaDTO> notasDTO;
	private FuncionarioDTO funcionarioDenunciadoDTO;
	private ExpedienteDTO expedienteDTO;
	/**
	 * @return the quejaConfirmadaId
	 */
	public Long getQuejaConfirmadaId() {
		return quejaConfirmadaId;
	}
	/**
	 * @return the notasDTO
	 */
	public List<NotaDTO> getNotasDTO() {
		return notasDTO;
	}
	/**
	 * @return the funcionarioDenunciadoDTO
	 */
	public FuncionarioDTO getFuncionarioDenunciadoDTO() {
		return funcionarioDenunciadoDTO;
	}
	/**
	 * @return the expedienteDTO
	 */
	public ExpedienteDTO getExpedienteDTO() {
		return expedienteDTO;
	}
	/**
	 * @param quejaConfirmadaId the quejaConfirmadaId to set
	 */
	public void setQuejaConfirmadaId(Long quejaConfirmadaId) {
		this.quejaConfirmadaId = quejaConfirmadaId;
	}
	/**
	 * @param notasDTO the notasDTO to set
	 */
	public void setNotasDTO(List<NotaDTO> notasDTO) {
		this.notasDTO = notasDTO;
	}
	/**
	 * @param funcionarioDenunciadoDTO the funcionarioDenunciadoDTO to set
	 */
	public void setFuncionarioDenunciadoDTO(FuncionarioDTO funcionarioDenunciadoDTO) {
		this.funcionarioDenunciadoDTO = funcionarioDenunciadoDTO;
	}
	/**
	 * @param expedienteDTO the expedienteDTO to set
	 */
	public void setExpedienteDTO(ExpedienteDTO expedienteDTO) {
		this.expedienteDTO = expedienteDTO;
	}

	
}

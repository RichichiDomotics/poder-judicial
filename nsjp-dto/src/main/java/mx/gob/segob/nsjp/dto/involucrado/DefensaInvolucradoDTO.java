package mx.gob.segob.nsjp.dto.involucrado;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;

public class DefensaInvolucradoDTO extends GenericDTO {

	private static final long serialVersionUID = 1L;

	private ExpedienteDTO expediente;
    
    private InvolucradoDTO involucrado;

	/**
	 * Regresa el valor de la propiedad numeroExpediente
	 * @return the numeroExpediente
	 */
	public ExpedienteDTO getExpediente() {
		return expediente;
	}

	/**
	 * Establece el valor de la propiedad numeroExpediente
	 * @param numeroExpediente valo numeroExpediente a almacenar
	 */
	public void setExpediente(ExpedienteDTO expediente) {
		this.expediente = expediente;
	}

	/**
	 * Regresa el valor de la propiedad involucrado
	 * @return the involucrado
	 */
	public InvolucradoDTO getInvolucrado() {
		return involucrado;
	}

	/**
	 * Establece el valor de la propiedad involucrado
	 * @param involucrado valo involucrado a almacenar
	 */
	public void setInvolucrado(InvolucradoDTO involucrado) {
		this.involucrado = involucrado;
	}

	
    
}

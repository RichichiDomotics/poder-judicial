package mx.gob.segob.nsjp.dto.informepolicial;

import java.util.Date;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;

public class InvolucradoIPHDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private InvolucradoIPHIdDTO id;
	private InvolucradoDTO involucrado;
	private InformePolicialHomologadoDTO informePolicialHomologado;
	private Date fechaDictamenMedico;
	
	public InvolucradoIPHDTO() {
		super();
	}

	public InvolucradoIPHDTO(InvolucradoIPHIdDTO id,
			InvolucradoDTO involucrado,
			InformePolicialHomologadoDTO informePolicialHomologado,
			Date fechaDictamenMedico) {
		super();
		this.id = id;
		this.involucrado = involucrado;
		this.informePolicialHomologado = informePolicialHomologado;
		this.fechaDictamenMedico = fechaDictamenMedico;
	}

	public InvolucradoIPHIdDTO getId() {
		return id;
	}

	public void setId(InvolucradoIPHIdDTO id) {
		this.id = id;
	}

	public InvolucradoDTO getInvolucrado() {
		return involucrado;
	}

	public void setInvolucrado(InvolucradoDTO involucrado) {
		this.involucrado = involucrado;
	}

	public InformePolicialHomologadoDTO getInformePolicialHomologado() {
		return informePolicialHomologado;
	}

	public void setInformePolicialHomologado(
			InformePolicialHomologadoDTO informePolicialHomologado) {
		this.informePolicialHomologado = informePolicialHomologado;
	}

	public Date getFechaDictamenMedico() {
		return fechaDictamenMedico;
	}

	public void setFechaDictamenMedico(Date fechaDictamenMedico) {
		this.fechaDictamenMedico = fechaDictamenMedico;
	}
	
	
	
	
}

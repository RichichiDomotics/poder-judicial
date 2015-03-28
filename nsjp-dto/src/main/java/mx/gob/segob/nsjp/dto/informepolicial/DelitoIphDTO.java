package mx.gob.segob.nsjp.dto.informepolicial;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDelitoDTO;

public class DelitoIphDTO extends GenericDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DelitoIphIdDTO id;
	private CatDelitoDTO catDelito;
	private InformePolicialHomologadoDTO informePolicialHomologado;
		
		
	public DelitoIphDTO() {
		super();
	}
	public DelitoIphDTO(DelitoIphIdDTO id, CatDelitoDTO catDelito,
			InformePolicialHomologadoDTO informePolicialHomologado) {
		super();
		this.id = id;
		this.catDelito = catDelito;
		this.informePolicialHomologado = informePolicialHomologado;
	}
	public DelitoIphIdDTO getId() {
		return id;
	}
	public void setId(DelitoIphIdDTO id) {
		this.id = id;
	}
	public CatDelitoDTO getCatDelito() {
		return catDelito;
	}
	public void setCatDelito(CatDelitoDTO catDelito) {
		this.catDelito = catDelito;
	}
	public InformePolicialHomologadoDTO getInformePolicialHomologado() {
		return informePolicialHomologado;
	}
	public void setInformePolicialHomologado(
			InformePolicialHomologadoDTO informePolicialHomologado) {
		this.informePolicialHomologado = informePolicialHomologado;
	}
	
	

}

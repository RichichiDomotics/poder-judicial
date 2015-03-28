package mx.gob.segob.nsjp.dto.informepolicial;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatFaltaAdministrativaDTO;

public class FaltaAdministrativaIphDTO extends GenericDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private FaltaAdministrativaIphIdDTO id;
    private CatFaltaAdministrativaDTO catFaltaAdministrativa;
    private InformePolicialHomologadoDTO informePolicialHomologado;
	
    public FaltaAdministrativaIphDTO(FaltaAdministrativaIphIdDTO id,
			CatFaltaAdministrativaDTO catFaltaAdministrativa,
			InformePolicialHomologadoDTO informePolicialHomologado) {
		super();
		this.id = id;
		this.catFaltaAdministrativa = catFaltaAdministrativa;
		this.informePolicialHomologado = informePolicialHomologado;
	}
	public FaltaAdministrativaIphDTO() {
		super();
	}
	public FaltaAdministrativaIphIdDTO getId() {
		return id;
	}
	public void setId(FaltaAdministrativaIphIdDTO id) {
		this.id = id;
	}
	public CatFaltaAdministrativaDTO getCatFaltaAdministrativa() {
		return catFaltaAdministrativa;
	}
	public void setCatFaltaAdministrativa(
			CatFaltaAdministrativaDTO catFaltaAdministrativa) {
		this.catFaltaAdministrativa = catFaltaAdministrativa;
	}
	public InformePolicialHomologadoDTO getInformePolicialHomologado() {
		return informePolicialHomologado;
	}
	public void setInformePolicialHomologado(
			InformePolicialHomologadoDTO informePolicialHomologado) {
		this.informePolicialHomologado = informePolicialHomologado;
	}
    
}

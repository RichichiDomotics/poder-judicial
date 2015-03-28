package mx.gob.segob.nsjp.dto.informepolicial;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

public class OperativoDTO extends GenericDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Long operativoId;
    private InformePolicialHomologadoDTO informePolicialHomologado;
    private String nombre;
    /**
     * Representa el nombre del comandante del operativo.
     */
    private String nombreComte;
    /**
     * Representa el nombre del comandante de agrupamiento
     */
    
    
    private String nombreComteAgrupto;
	public OperativoDTO(Long operativoId,
			InformePolicialHomologadoDTO informePolicialHomologado,
			String nombre, String nombreComte, String nombreComteAgrupto) {
		super();
		this.operativoId = operativoId;
		this.informePolicialHomologado = informePolicialHomologado;
		this.nombre = nombre;
		this.nombreComte = nombreComte;
		this.nombreComteAgrupto = nombreComteAgrupto;
	}
	
	public OperativoDTO() {
		super();
	}

	public Long getOperativoId() {
		return operativoId;
	}
	public void setOperativoId(Long operativoId) {
		this.operativoId = operativoId;
	}
	public InformePolicialHomologadoDTO getInformePolicialHomologado() {
		return informePolicialHomologado;
	}
	public void setInformePolicialHomologado(
			InformePolicialHomologadoDTO informePolicialHomologado) {
		this.informePolicialHomologado = informePolicialHomologado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombreComte() {
		return nombreComte;
	}
	public void setNombreComte(String nombreComte) {
		this.nombreComte = nombreComte;
	}
	public String getNombreComteAgrupto() {
		return nombreComteAgrupto;
	}
	public void setNombreComteAgrupto(String nombreComteAgrupto) {
		this.nombreComteAgrupto = nombreComteAgrupto;
	}
    
    

}

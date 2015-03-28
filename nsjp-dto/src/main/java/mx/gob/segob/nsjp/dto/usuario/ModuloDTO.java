/**
 * 
 */
package mx.gob.segob.nsjp.dto.usuario;

import java.util.List;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * @author LuisMG
 *
 */
public class ModuloDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5169505370970376211L;
	private Long moduloId;
	private String nombreModulo;
	private String descripcionModulo;
	private boolean esSeleccionado;
	private List<FuncionDTO> funciones ;
	
	
	/**
	 * Constructor por Default
	 */
	public ModuloDTO (){
		
	}
	
	/**
	 * Constructor Mínimo
	 * @param moduloId
	 */
	public ModuloDTO(Long moduloId){
		this.moduloId=moduloId;
	}

	/**
	 * @return the moduloId
	 */
	public Long getModuloId() {
		return moduloId;
	}

	/**
	 * @return the nombreModulo
	 */
	public String getNombreModulo() {
		return nombreModulo;
	}

	/**
	 * @return the descripcionModulo
	 */
	public String getDescripcionModulo() {
		return descripcionModulo;
	}

	/**
	 * @return the funciones
	 */
	public List<FuncionDTO> getFunciones() {
		return funciones;
	}

	/**
	 * @param moduloId the moduloId to set
	 */
	public void setModuloId(Long moduloId) {
		this.moduloId = moduloId;
	}

	/**
	 * @param nombreModulo the nombreModulo to set
	 */
	public void setNombreModulo(String nombreModulo) {
		this.nombreModulo = nombreModulo;
	}

	/**
	 * @param descripcionModulo the descripcionModulo to set
	 */
	public void setDescripcionModulo(String descripcionModulo) {
		this.descripcionModulo = descripcionModulo;
	}

	/**
	 * @param funciones the funciones to set
	 */
	public void setFunciones(List<FuncionDTO> funciones) {
		this.funciones = funciones;
	}

	/**
	 * @return the esSeleccionado
	 */
	public boolean isEsSeleccionado() {
		return esSeleccionado;
	}

	/**
	 * @param esSeleccionado the esSeleccionado to set
	 */
	public void setEsSeleccionado(boolean esSeleccionado) {
		this.esSeleccionado = esSeleccionado;
	}

}

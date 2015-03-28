/**
 * 
 */
package mx.gob.segob.nsjp.dto.elementomenu;

import java.util.List;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.usuario.FuncionDTO;


/**
 * @author LuisMG
 *
 */
public class ElementoMenuDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 920059399434061214L;
	
	private Long elementoMenuId;
	private String cNombre;
	private String cComando;
	private String cIdHTML;
	private String cClassHTML;
	private Integer iOrden;
	private boolean esActivo;
	private ElementoMenuDTO elementoMenuPadreDTO;
	private List<ElementoMenuDTO> elementoMenuHijosDTO;
	private FuncionDTO funcion;

	/** Constructor por Default
	 * 
	 */
	public ElementoMenuDTO(){
		
	}
	/** Constructor Minimo por Id
	 * 
	 * @param elementoMenuId
	 */
	public ElementoMenuDTO(Long elementoMenuId){
		this.elementoMenuId = elementoMenuId;
	}
	/**
	 * Constructor por Nombre y comando
	 * @param cNombre
	 * @param cComando
	 */
	public ElementoMenuDTO (String cNombre,String cComando){
		this.cNombre=cNombre;
		this.cComando=cComando;
	}
	public Long getElementoMenuId() {
		return elementoMenuId;
	}
	public ElementoMenuDTO getElementoMenuPadreDTO() {
		return elementoMenuPadreDTO;
	}
	public List<ElementoMenuDTO> getElementoMenuHijosDTO() {
		return elementoMenuHijosDTO;
	}
	public FuncionDTO getFuncion() {
		return funcion;
	}
	public String getcNombre() {
		return cNombre;
	}
	public String getcComando() {
		return cComando;
	}
	public Integer getiOrden() {
		return iOrden;
	}
	public void setElementoMenuId(Long elementoMenuId) {
		this.elementoMenuId = elementoMenuId;
	}
	public void setElementoMenuPadreDTO(ElementoMenuDTO elementoMenuPadreDTO) {
		this.elementoMenuPadreDTO = elementoMenuPadreDTO;
	}
	public void setElementoMenuHijosDTO(List<ElementoMenuDTO> elementoMenuHijosDTO) {
		this.elementoMenuHijosDTO = elementoMenuHijosDTO;
	}
	public void setFuncion(FuncionDTO funcion) {
		this.funcion = funcion;
	}
	public void setcNombre(String cNombre) {
		this.cNombre = cNombre;
	}
	public void setcComando(String cComando) {
		this.cComando = cComando;
	}
	public void setiOrden(Integer iOrden) {
		this.iOrden = iOrden;
	}
	/**
	 * @return the cIdHTML
	 */
	public String getcIdHTML() {
		return cIdHTML;
	}
	/**
	 * @return the cClassHTML
	 */
	public String getcClassHTML() {
		return cClassHTML;
	}
	/**
	 * @param cIdHTML the cIdHTML to set
	 */
	public void setcIdHTML(String cIdHTML) {
		this.cIdHTML = cIdHTML;
	}
	/**
	 * @param cClassHTML the cClassHTML to set
	 */
	public void setcClassHTML(String cClassHTML) {
		this.cClassHTML = cClassHTML;
	}
	/**
	 * @return the esActivo
	 */
	public boolean isEsActivo() {
		return esActivo;
	}
	/**
	 * @param esActivo the esActivo to set
	 */
	public void setEsActivo(boolean esActivo) {
		this.esActivo = esActivo;
	}

}

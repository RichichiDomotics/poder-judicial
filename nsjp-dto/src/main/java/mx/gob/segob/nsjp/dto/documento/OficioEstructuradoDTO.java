/**
 * 
 */
package mx.gob.segob.nsjp.dto.documento;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;

/**
 * @author adrian
 *
 */
public class OficioEstructuradoDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6318419854195735169L;
	
	private Long oficioEstructuradoId;
    private String encabezado;
    private String pie;
    private ValorDTO tipoOficio;
    private List<CuerpoOficioEstructuradoDTO> cuerposOficio =new ArrayList<CuerpoOficioEstructuradoDTO>();
    
    public OficioEstructuradoDTO(){}
    
	public OficioEstructuradoDTO(Long oficioEstructuradoId, String encabezado,
			String pie, ValorDTO tipoOficio) {
		super();
		this.oficioEstructuradoId = oficioEstructuradoId;
		this.encabezado = encabezado;
		this.pie = pie;
		this.tipoOficio = tipoOficio;
	}
	/**
	 * @return the oficioEstructuradoId
	 */
	public Long getOficioEstructuradoId() {
		return oficioEstructuradoId;
	}
	/**
	 * @param oficioEstructuradoId the oficioEstructuradoId to set
	 */
	public void setOficioEstructuradoId(Long oficioEstructuradoId) {
		this.oficioEstructuradoId = oficioEstructuradoId;
	}
	/**
	 * @return the encabezado
	 */
	public String getEncabezado() {
		return encabezado;
	}
	/**
	 * @param encabezado the encabezado to set
	 */
	public void setEncabezado(String encabezado) {
		this.encabezado = encabezado;
	}
	/**
	 * @return the pie
	 */
	public String getPie() {
		return pie;
	}
	/**
	 * @param pie the pie to set
	 */
	public void setPie(String pie) {
		this.pie = pie;
	}
	/**
	 * @return the tipoOficio
	 */
	public ValorDTO getTipoOficio() {
		return tipoOficio;
	}
	/**
	 * @param tipoOficio the tipoOficio to set
	 */
	public void setTipoOficio(ValorDTO tipoOficio) {
		this.tipoOficio = tipoOficio;
	}

	/**
	 * @param cuerposOficio the cuerposOficio to set
	 */
	public void setCuerposOficio(List<CuerpoOficioEstructuradoDTO> cuerposOficio) {
		this.cuerposOficio = cuerposOficio;
	}

	/**
	 * @return the cuerposOficio
	 */
	public List<CuerpoOficioEstructuradoDTO> getCuerposOficio() {
		return cuerposOficio;
	} 

}

/**
 * 
 */
package mx.gob.segob.nsjp.dto.policiaministerial;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;

/**
 * @author adrian
 *
 */
public class SeguimientoLIDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5760578323834817252L;
	
	private Long seguimientoLIId;
	private Date fechaCreacion;
	private String hipotesis;
	
	private List<LineaInvestigacionDTO> lineasInvestigacionDTO;
	private ExpedienteDTO expedienteDTO;
	
	public SeguimientoLIDTO(){
		super();
	}
	
	/**
	 * @param seguimientoLIId
	 */
	public SeguimientoLIDTO(Long seguimientoLIId) {
		super();
		this.seguimientoLIId = seguimientoLIId;
	}
	/**
	 * @return the seguimientoLIId
	 */
	public Long getSeguimientoLIId() {
		return seguimientoLIId;
	}
	/**
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	/**
	 * @return the hipotesis
	 */
	public String getHipotesis() {
		return hipotesis;
	}
	/**
	 * @return the lineasInvestigacionDTO
	 */
	public List<LineaInvestigacionDTO> getLineasInvestigacionDTO() {
		return lineasInvestigacionDTO;
	}
	/**
	 * @return the expedienteDTO
	 */
	public ExpedienteDTO getExpedienteDTO() {
		return expedienteDTO;
	}
	/**
	 * @param seguimientoLIId the seguimientoLIId to set
	 */
	public void setSeguimientoLIId(Long seguimientoLIId) {
		this.seguimientoLIId = seguimientoLIId;
	}
	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	/**
	 * @param hipotesis the hipotesis to set
	 */
	public void setHipotesis(String hipotesis) {
		this.hipotesis = hipotesis;
	}
	/**
	 * @param lineasInvestigacionDTO the lineasInvestigacionDTO to set
	 */
	public void setLineasInvestigacionDTO(
			List<LineaInvestigacionDTO> lineasInvestigacionDTO) {
		this.lineasInvestigacionDTO = lineasInvestigacionDTO;
	}
	/**
	 * @param expedienteDTO the expedienteDTO to set
	 */
	public void setExpedienteDTO(ExpedienteDTO expedienteDTO) {
		this.expedienteDTO = expedienteDTO;
	}
	
	

}

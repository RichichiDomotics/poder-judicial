/**
 * 
 */
package mx.gob.segob.nsjp.dto.policiaministerial;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;

/**
 * @author adrian
 *
 */
public class ComentarioDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1515301729865558400L;
	
	private Long comentarioId;
	private Date fechaCreacion;
	private String descripcion;
	
	private List<ArchivoDigitalDTO> archivoDigitalDTOs; 
	private FuncionarioDTO funcionarioDTO;
	
	private LineaInvestigacionDTO lineaInvestigacionDTO;
	
	public ComentarioDTO(){
		super();
	}

	/**
	 * @param comentarioId
	 */
	public ComentarioDTO(Long comentarioId) {
		super();
		this.comentarioId = comentarioId;
	}

	/**
	 * @return the comentarioId
	 */
	public Long getComentarioId() {
		return comentarioId;
	}

	/**
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @return the archivoDigitalDTOs
	 */
	public List<ArchivoDigitalDTO> getArchivoDigitalDTOs() {
		return archivoDigitalDTOs;
	}

	/**
	 * @return the funcionarioDTO
	 */
	public FuncionarioDTO getFuncionarioDTO() {
		return funcionarioDTO;
	}

	/**
	 * @return the lineaInvestigacionDTO
	 */
	public LineaInvestigacionDTO getLineaInvestigacionDTO() {
		return lineaInvestigacionDTO;
	}

	/**
	 * @param comentarioId the comentarioId to set
	 */
	public void setComentarioId(Long comentarioId) {
		this.comentarioId = comentarioId;
	}

	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @param archivoDigitalDTOs the archivoDigitalDTOs to set
	 */
	public void setArchivoDigitalDTOs(List<ArchivoDigitalDTO> archivoDigitalDTOs) {
		this.archivoDigitalDTOs = archivoDigitalDTOs;
	}

	/**
	 * @param funcionarioDTO the funcionarioDTO to set
	 */
	public void setFuncionarioDTO(FuncionarioDTO funcionarioDTO) {
		this.funcionarioDTO = funcionarioDTO;
	}

	/**
	 * @param lineaInvestigacionDTO the lineaInvestigacionDTO to set
	 */
	public void setLineaInvestigacionDTO(LineaInvestigacionDTO lineaInvestigacionDTO) {
		this.lineaInvestigacionDTO = lineaInvestigacionDTO;
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.dto.base.GenericDTO#toString()
	 */
	@Override
	public String toString() {
		String strFunc=(this.funcionarioDTO!=null)?this.funcionarioDTO.getNombreCompleto():"__";
		Locale loc	 = new Locale("es");
		Calendar ahora =Calendar.getInstance();
		ahora.setTime(this.fechaCreacion);
		String strMes=new SimpleDateFormat("MMMMMMMMMM",loc).format(this.fechaCreacion);
		DecimalFormat df=new DecimalFormat("00");
		
		String strCom="<p>Fecha: "+ahora.get(Calendar.DAY_OF_MONTH)+" de "+strMes+" de "+ahora.get(Calendar.YEAR)+
		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
		"Hora: "+df.format(ahora.get(Calendar.HOUR_OF_DAY))+":"+df.format(ahora.get(Calendar.MINUTE))+"</p>"+
		"<p>Funcionario: "+strFunc+"</p>"+
		"<p>Descripción: "+this.descripcion+"</p>";
		
		return strCom;
	}

	
}

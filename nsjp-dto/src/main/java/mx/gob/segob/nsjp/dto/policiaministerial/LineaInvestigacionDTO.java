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

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;

/**
 * @author adrian
 *
 */
public class LineaInvestigacionDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7443463368772858935L;
	
	private Long lineaInvestigacionId;
	private Date fechaCreacion;
	private String descripcion;
	private Long numComentarios;
	private Date fechaUltimoComentario;
	private Boolean esImpreso;
	
	private List<ComentarioDTO> comentarioDTOs;
	private ValorDTO tipoLineaInvestigacioValorDTO;
    private ExpedienteDTO expedienteDTO;
    private Long consecutivo;

	
	private SeguimientoLIDTO seguimientoLIDTO;
	
	public LineaInvestigacionDTO(){
		super();
	}

	/**
	 * @param lineaInvestigacionId
	 */
	public LineaInvestigacionDTO(Long lineaInvestigacionId) {
		super();
		this.lineaInvestigacionId = lineaInvestigacionId;
	}

	/**
	 * @return the lineaInvestigacionId
	 */
	public Long getLineaInvestigacionId() {
		return lineaInvestigacionId;
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
	 * @return the numComentarios
	 */
	public Long getNumComentarios() {
		return numComentarios;
	}

	/**
	 * @return the fechaUltimoComentario
	 */
	public Date getFechaUltimoComentario() {
		return fechaUltimoComentario;
	}

	/**
	 * @return the esImpreso
	 */
	public Boolean getEsImpreso() {
		return esImpreso;
	}

	/**
	 * @return the comentarioDTOs
	 */
	public List<ComentarioDTO> getComentarioDTOs() {
		return comentarioDTOs;
	}

	/**
	 * @return the tipoLineaInvestigacioValorDTO
	 */
	public ValorDTO getTipoLineaInvestigacioValorDTO() {
		return tipoLineaInvestigacioValorDTO;
	}

	/**
	 * @return the seguimientoLIDTO
	 */
	public SeguimientoLIDTO getSeguimientoLIDTO() {
		return seguimientoLIDTO;
	}

	/**
	 * @param lineaInvestigacionId the lineaInvestigacionId to set
	 */
	public void setLineaInvestigacionId(Long lineaInvestigacionId) {
		this.lineaInvestigacionId = lineaInvestigacionId;
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
	 * @param numComentarios the numComentarios to set
	 */
	public void setNumComentarios(Long numComentarios) {
		this.numComentarios = numComentarios;
	}

	/**
	 * @param fechaUltimoComentario the fechaUltimoComentario to set
	 */
	public void setFechaUltimoComentario(Date fechaUltimoComentario) {
		this.fechaUltimoComentario = fechaUltimoComentario;
	}

	/**
	 * @param esImpreso the esImpreso to set
	 */
	public void setEsImpreso(Boolean esImpreso) {
		this.esImpreso = esImpreso;
	}

	/**
	 * @param comentarioDTOs the comentarioDTOs to set
	 */
	public void setComentarioDTOs(List<ComentarioDTO> comentarioDTOs) {
		this.comentarioDTOs = comentarioDTOs;
	}

	/**
	 * @param tipoLineaInvestigacioValorDTO the tipoLineaInvestigacioValorDTO to set
	 */
	public void setTipoLineaInvestigacioValorDTO(
			ValorDTO tipoLineaInvestigacioValorDTO) {
		this.tipoLineaInvestigacioValorDTO = tipoLineaInvestigacioValorDTO;
	}

	/**
	 * @param seguimientoLIDTO the seguimientoLIDTO to set
	 */
	public void setSeguimientoLIDTO(SeguimientoLIDTO seguimientoLIDTO) {
		this.seguimientoLIDTO = seguimientoLIDTO;
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.dto.base.GenericDTO#toString()
	 */
	@Override
	public String toString() {
//		String strImpreso=(this.esImpreso)?"SÍ":"NO";
		String strTipo=(this.tipoLineaInvestigacioValorDTO!=null)?this.tipoLineaInvestigacioValorDTO.getValor():"__";
		Locale loc	 = new Locale("es");
		Calendar ahora =Calendar.getInstance();
		Date mes=new Date(ahora.getTimeInMillis());
		String strMes=new SimpleDateFormat("MMMMMMMMMM",loc).format(mes);
		DecimalFormat df=new DecimalFormat("00");
		String strCaso="__";
		int numCom=1;


		if(this.getExpedienteDTO()!=null)
			strCaso=(this.getExpedienteDTO().getCasoDTO()!=null)?this.getExpedienteDTO().getCasoDTO().getNumeroGeneralCaso():"__";
		String strLinea="<a align=\"right\"><p><strong>L&iacute;nea de Investigaci&oacute;n: "+this.consecutivo+"</strong></p><br/>" +
		"<hr/><br/>"+
		"<p>N&uacute;mero de Caso: "+strCaso+"</p>"+
		"<p>N&uacute;mero de Expediente: "+this.getExpedienteDTO().getNumeroExpediente()+"</p>"+
		"<p>Fecha de Cerrada: "+ahora.get(Calendar.DAY_OF_MONTH)+" de "+strMes+" de "+ahora.get(Calendar.YEAR)+
		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
		"Hora: "+df.format(ahora.get(Calendar.HOUR_OF_DAY))+":"+df.format(ahora.get(Calendar.MINUTE))+"</p><br/><br/></a>"+
		"<p>T&iacute;tulo de la L&iacute;nea de Investigaci&oacute;n: "+strTipo+"</p>";
		
		ahora.setTime(this.fechaCreacion);
		strMes=new SimpleDateFormat("MMMMMMMMMM",loc).format(this.fechaCreacion);
		strLinea+="<p>Fecha de Creaci&oacute;n: "+ahora.get(Calendar.DAY_OF_MONTH)+" de "+strMes+" de "+ahora.get(Calendar.YEAR)+
		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
		"Hora: "+df.format(ahora.get(Calendar.HOUR_OF_DAY))+":"+df.format(ahora.get(Calendar.MINUTE))+"</p>"+
		"<p>N&uacute;mero de Comentarios: "+(this.numComentarios != null ? this.numComentarios : "0")+"</p>";
		if(this.fechaUltimoComentario != null){
			ahora.setTime(this.fechaUltimoComentario);
			strMes=new SimpleDateFormat("MMMMMMMMMM",loc).format(this.fechaUltimoComentario);
			strLinea+="<p>Fecha de &Uacute;ltimo Comentario: "+ahora.get(Calendar.DAY_OF_MONTH)+" de "+strMes+" de "+ahora.get(Calendar.YEAR)+
			"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
			"Hora: "+df.format(ahora.get(Calendar.HOUR_OF_DAY))+":"+df.format(ahora.get(Calendar.MINUTE))+"</p>";
		}
		
		strLinea+= "<br/><p>Tema de la L&iacute;nea de Investigaci&oacute;n: </p>"+
		"<p>"+this.descripcion+"</p>";
		if(this.comentarioDTOs.size() > 0)
			strLinea+="<br/><p>Comentarios:</p>";
		
		for (ComentarioDTO com : this.comentarioDTOs) {
			strLinea+="<hr/><p><strong>Comentario "+numCom+"</strong></p>"+com.toString();
			numCom++;
		}
		
		return strLinea;
	}

	/**
	 * Método de acceso al campo expedienteDTO.
	 * @return El valor del campo expedienteDTO
	 */
	public ExpedienteDTO getExpedienteDTO() {
		return expedienteDTO;
	}

	/**
	 * Asigna el valor al campo expedienteDTO.
	 * @param expedienteDTO el valor expedienteDTO a asignar
	 */
	public void setExpedienteDTO(ExpedienteDTO expedienteDTO) {
		this.expedienteDTO = expedienteDTO;
	}

	/**
	 * @return the consecutivo
	 */
	public Long getConsecutivo() {
		return consecutivo;
	}

	/**
	 * @param consecutivo the consecutivo to set
	 */
	public void setConsecutivo(Long consecutivo) {
		this.consecutivo = consecutivo;
	}
	
	

}

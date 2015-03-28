package mx.gob.segob.nsjp.dto.programas;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.sentencia.SentenciaDTO;


/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author EdgarTE
 *
 */
public class ActoBuenaConductaDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4778437068938343319L;
	
	private Long actoBuenaConductaId;
	private String cnombre;
	private String cdescripcion;
	private Long ipuntosOtorgados;
	private PeriodoAcumulacionPuntosDTO periodoAcumulacionPuntos;
	private SentenciaDTO sentenciaDTO;
	
	/**
	 * Método de acceso al campo actoBuenaConductaId.
	 * @return El valor del campo actoBuenaConductaId
	 */
	public Long getActoBuenaConductaId() {
		return actoBuenaConductaId;
	}
	
	/**
	 * Asigna el valor al campo actoBuenaConductaId.
	 * @param actoBuenaConductaId el valor actoBuenaConductaId a asignar
	 */
	public void setActoBuenaConductaId(Long actoBuenaConductaId) {
		this.actoBuenaConductaId = actoBuenaConductaId;
	}
	
	/**
	 * Método de acceso al campo cnombre.
	 * @return El valor del campo cnombre
	 */
	public String getCnombre() {
		return cnombre;
	}
	
	/**
	 * Asigna el valor al campo cnombre.
	 * @param cnombre el valor cnombre a asignar
	 */
	public void setCnombre(String cnombre) {
		this.cnombre = cnombre;
	}
	
	/**
	 * Método de acceso al campo cdescripcion.
	 * @return El valor del campo cdescripcion
	 */
	public String getCdescripcion() {
		return cdescripcion;
	}
	
	/**
	 * Asigna el valor al campo cdescripcion.
	 * @param cdescripcion el valor cdescripcion a asignar
	 */
	public void setCdescripcion(String cdescripcion) {
		this.cdescripcion = cdescripcion;
	}
	
	/**
	 * Método de acceso al campo ipuntosOtorgados.
	 * @return El valor del campo ipuntosOtorgados
	 */
	public Long getIpuntosOtorgados() {
		return ipuntosOtorgados;
	}
	
	/**
	 * Asigna el valor al campo ipuntosOtorgados.
	 * @param ipuntosOtorgados el valor ipuntosOtorgados a asignar
	 */
	public void setIpuntosOtorgados(Long ipuntosOtorgados) {
		this.ipuntosOtorgados = ipuntosOtorgados;
	}
	
	/**
	 * Método de acceso al campo periodoAcumulacionPuntos.
	 * @return El valor del campo periodoAcumulacionPuntos
	 */
	public PeriodoAcumulacionPuntosDTO getPeriodoAcumulacionPuntos() {
		return periodoAcumulacionPuntos;
	}
	
	/**
	 * Asigna el valor al campo periodoAcumulacionPuntos.
	 * @param periodoAcumulacionPuntos el valor periodoAcumulacionPuntos a asignar
	 */
	public void setPeriodoAcumulacionPuntos(
			PeriodoAcumulacionPuntosDTO periodoAcumulacionPuntos) {
		this.periodoAcumulacionPuntos = periodoAcumulacionPuntos;
	}
	
	/**
	 * Método de acceso al campo sentenciaDTO.
	 * @return El valor del campo sentenciaDTO
	 */
	public SentenciaDTO getSentenciaDTO() {
		return sentenciaDTO;
	}
	
	/**
	 * Asigna el valor al campo sentenciaDTO.
	 * @param sentenciaDTO el valor sentenciaDTO a asignar
	 */
	public void setSentenciaDTO(SentenciaDTO sentenciaDTO) {
		this.sentenciaDTO = sentenciaDTO;
	}
	
	
	/**
	 * M&eacute;todo utilitario que calcula el total de puntos acumulados 
	 * para el acto de buena conducta, s&oacute;lo se consideran los puntos 
	 * que se encuentran dentro de un período de acumulación. 
	 * @return totalPuntos El total de puntos que ha sido acumulado.
	 */
	public Long getPuntosAcumulados(){
		Long totalPuntos = 0L;
		if (getPeriodoAcumulacionPuntos() != null && getPeriodoAcumulacionPuntos().getPeriodoAcumulacionPuntosId() != null){
			totalPuntos = getIpuntosOtorgados();
		}
		return totalPuntos;
	}

}

/**
 * 
 */
package mx.gob.segob.nsjp.dto.alarmas;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;

/**
 * @author adrian
 *
 */
public class AlarmaDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5903301344585316741L;
	
	private Long alarmaId;
	private Date fechaAlarma;
	private String motivo;
	private String folioEvento;
	private String datosAsociados;
	
	private ValorDTO estatusAlarmaAlerta;
	private ValorDTO tipoEventoAlarma;
	private FuncionarioDTO funcionario;
	private List<AlertaDTO> alertas;
	
	public AlarmaDTO(){
		super();
	}
	
	/**
	 * @param alarmaId
	 */
	public AlarmaDTO(Long alarmaId) {
		super();
		this.alarmaId = alarmaId;
	}
	/**
	 * @return the alarmaId
	 */
	public Long getAlarmaId() {
		return alarmaId;
	}
	/**
	 * @param alarmaId the alarmaId to set
	 */
	public void setAlarmaId(Long alarmaId) {
		this.alarmaId = alarmaId;
	}
	/**
	 * @return the fechaAlarma
	 */
	public Date getFechaAlarma() {
		return fechaAlarma;
	}
	/**
	 * @param fechaAlarma the fechaAlarma to set
	 */
	public void setFechaAlarma(Date fechaAlarma) {
		this.fechaAlarma = fechaAlarma;
	}
	/**
	 * @return the motivo
	 */
	public String getMotivo() {
		return motivo;
	}
	/**
	 * @param motivo the motivo to set
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	/**
	 * @return the folioEvento
	 */
	public String getFolioEvento() {
		return folioEvento;
	}
	/**
	 * @param folioEvento the folioEvento to set
	 */
	public void setFolioEvento(String folioEvento) {
		this.folioEvento = folioEvento;
	}
	/**
	 * @return the datosAsociados
	 */
	public String getDatosAsociados() {
		return datosAsociados;
	}
	/**
	 * @param datosAsociados the datosAsociados to set
	 */
	public void setDatosAsociados(String datosAsociados) {
		this.datosAsociados = datosAsociados;
	}
	/**
	 * @return the estatusAlarmaAlerta
	 */
	public ValorDTO getEstatusAlarmaAlerta() {
		return estatusAlarmaAlerta;
	}
	/**
	 * @param estatusAlarmaAlerta the estatusAlarmaAlerta to set
	 */
	public void setEstatusAlarmaAlerta(ValorDTO estatusAlarmaAlerta) {
		this.estatusAlarmaAlerta = estatusAlarmaAlerta;
	}
	/**
	 * @return the tipoEventoAlarma
	 */
	public ValorDTO getTipoEventoAlarma() {
		return tipoEventoAlarma;
	}
	/**
	 * @param tipoEventoAlarma the tipoEventoAlarma to set
	 */
	public void setTipoEventoAlarma(ValorDTO tipoEventoAlarma) {
		this.tipoEventoAlarma = tipoEventoAlarma;
	}

	/**
	 * @param funcionario the funcionario to set
	 */
	public void setFuncionario(FuncionarioDTO funcionario) {
		this.funcionario = funcionario;
	}

	/**
	 * @return the funcionario
	 */
	public FuncionarioDTO getFuncionario() {
		return funcionario;
	}

	/**
	 * @param alertas the alertas to set
	 */
	public void setAlertas(List<AlertaDTO> alertas) {
		this.alertas = alertas;
	}

	/**
	 * @return the alertas
	 */
	public List<AlertaDTO> getAlertas() {
		return alertas;
	}

}

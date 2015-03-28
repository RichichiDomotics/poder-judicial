/**
 * 
 */
package mx.gob.segob.nsjp.dto.expediente;

import java.util.Date;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.medida.CompromisoPeriodicoDTO;

/**
 * @author adrian
 *
 */
public class ConvenioDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9041311261256167899L;
	private Long convenioID;
	private Long numeroConvenio;
	private Date fechaInicio;
	private Date fechaFin;
	private String fechaInicioString;
	private String fechaFinString;
	private Double monto;
	private InvolucradoDTO involucradoPR;
	private FuncionarioDTO funcionario;
	private ValorDTO periodicidad;
	private CompromisoPeriodicoDTO compromisoPeriodicoDTO;
	private ExpedienteDTO numeroExpediente;
	
	private InvolucradoDTO involucradoVictima;
	private ValorDTO tipoConvenio;


	
	/**
	 * @return the acuerdoRestaurativoID
	 */
	public Long getConvenioID() {
		return convenioID;
	}
	/**
	 * @param acuerdoRestaurativoID the acuerdoRestaurativoID to set
	 */
	public void setConvenioID(Long acuerdoRestaurativoID) {
		this.convenioID = acuerdoRestaurativoID;
	}
	/**
	 * @return the funcionario
	 */
	public FuncionarioDTO getFuncionario() {
		return funcionario;
	}
	/**
	 * @param funcionario the funcionario to set
	 */
	public void setFuncionario(FuncionarioDTO funcionario) {
		this.funcionario = funcionario;
	}
	/**
	 * @return the periodicidad
	 */
	public ValorDTO getPeriodicidad() {
		return periodicidad;
	}
	/**
	 * @param periodicidad the periodicidad to set
	 */
	public void setPeriodicidad(ValorDTO periodicidad) {
		this.periodicidad = periodicidad;
	}
	/**
	 * @return the involucradoPR
	 */
	public InvolucradoDTO getInvolucradoPR() {
		return involucradoPR;
	}
	/**
	 * @param involucradoPR the involucradoPR to set
	 */
	public void setInvolucradoPR(InvolucradoDTO involucradoPR) {
		this.involucradoPR = involucradoPR;
	}
	/**
	 * @return the numeroMediacion
	 */
	public Long getNumeroConvenio() {
		return numeroConvenio;
	}
	/**
	 * @param numeroMediacion the numeroMediacion to set
	 */
	public void setNumeroConvenio(Long numeroMediacion) {
		this.numeroConvenio = numeroMediacion;
	}
	/**
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * @return the fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}
	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	/**
	 * @return the monto
	 */
	public Double getMonto() {
		return monto;
	}
	/**
	 * @param monto the monto to set
	 */
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	/**
	 * @return the numeroExpediente
	 */
	public ExpedienteDTO getNumeroExpediente() {
		return numeroExpediente;
	}
	/**
	 * @param numeroExpediente the numeroExpediente to set
	 */
	public void setNumeroExpediente(ExpedienteDTO numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}
	/**
	 * @param compromisoPeriodicoDTO the compromisoPeriodicoDTO to set
	 */
	public void setCompromisoPeriodicoDTO(CompromisoPeriodicoDTO compromisoPeriodicoDTO) {
		this.compromisoPeriodicoDTO = compromisoPeriodicoDTO;
	}
	/**
	 * @return the compromisoPeriodicoDTO
	 */
	public CompromisoPeriodicoDTO getCompromisoPeriodicoDTO() {
		return compromisoPeriodicoDTO;
	}
	/**
	 * Método de acceso al campo involucradoVictima.
	 * @return El valor del campo involucradoVictima
	 */
	public InvolucradoDTO getInvolucradoVictima() {
		return involucradoVictima;
	}
	/**
	 * Asigna el valor al campo involucradoVictima.
	 * @param involucradoVictima el valor involucradoVictima a asignar
	 */
	public void setInvolucradoVictima(InvolucradoDTO involucradoVictima) {
		this.involucradoVictima = involucradoVictima;
	}
	/**
	 * Método de acceso al campo tipoConvenio.
	 * @return El valor del campo tipoConvenio
	 */
	public ValorDTO getTipoConvenio() {
		return tipoConvenio;
	}
	/**
	 * Asigna el valor al campo tipoConvenio.
	 * @param tipoConvenio el valor tipoConvenio a asignar
	 */
	public void setTipoConvenio(ValorDTO tipoConvenio) {
		this.tipoConvenio = tipoConvenio;
	}
	/**
	 * @return the fechaInicioString
	 */
	public String getFechaInicioString() {
		return fechaInicioString;
	}
	/**
	 * @param fechaInicioString the fechaInicioString to set
	 */
	public void setFechaInicioString(String fechaInicioString) {
		this.fechaInicioString = fechaInicioString;
	}
	/**
	 * @return the fechaFinString
	 */
	public String getFechaFinString() {
		return fechaFinString;
	}
	/**
	 * @param fechaFinString the fechaFinString to set
	 */
	public void setFechaFinString(String fechaFinString) {
		this.fechaFinString = fechaFinString;
	}
	
	

	
}

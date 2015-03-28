package mx.gob.segob.nsjp.dto.informepolicial;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.hecho.HechoDTO;



public class InformePolicialHomologadoDTO extends GenericDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private Long informePolicialHomologadoId;
    private HechoDTO hecho;
    private FuncionarioDTO funcionarioElabora;
    private FuncionarioDTO funcionarioDestinatario;
    private ExpedienteDTO expediente;
    private ValorDTO tipoParticipacion;
    private Date fechaCaptura;
    private String objetivosGenerales;
    private Long numeroOficio;
    private Long anio;
    private Long folioIPH;
    private String numEcoTransporte;
    private OperativoDTO operativo;
    private List<TurnoLaboralIphDTO> turnoLaboralIphs=new ArrayList<TurnoLaboralIphDTO>();
    private List<InvolucradoIPHDTO> involucradoIphs = new ArrayList<InvolucradoIPHDTO>();            
    private List<DelitoIphDTO> delitoIph=new ArrayList<DelitoIphDTO>();
    private List<FaltaAdministrativaIphDTO> faltaIph;
    private String asunto;
    
    public Long getInformePolicialHomologadoId() {
		return informePolicialHomologadoId;
	}
	public void setInformePolicialHomologadoId(Long informePolicialHomologadoId) {
		this.informePolicialHomologadoId = informePolicialHomologadoId;
	}
	public HechoDTO getHecho() {
		return hecho;
	}
	public void setHecho(HechoDTO hecho) {
		this.hecho = hecho;
	}
	public FuncionarioDTO getFuncionarioElabora() {
		return funcionarioElabora;
	}
	public void setFuncionarioElabora(FuncionarioDTO funcionarioElabora) {
		this.funcionarioElabora = funcionarioElabora;
	}
	public FuncionarioDTO getFuncionarioDestinatario() {
		return funcionarioDestinatario;
	}
	public void setFuncionarioDestinatario(FuncionarioDTO funcionarioDestinatario) {
		this.funcionarioDestinatario = funcionarioDestinatario;
	}
	public ExpedienteDTO getExpediente() {
		return expediente;
	}
	public void setExpediente(ExpedienteDTO expediente) {
		this.expediente = expediente;
	}
	public ValorDTO getTipoParticipacion() {
		return tipoParticipacion;
	}
	public void setTipoParticipacion(ValorDTO tipoParticipacion) {
		this.tipoParticipacion = tipoParticipacion;
	}
	public Date getFechaCaptura() {
		return fechaCaptura;
	}
	public void setFechaCaptura(Date fechaCaptura) {
		this.fechaCaptura = fechaCaptura;
	}
	public String getObjetivosGenerales() {
		return objetivosGenerales;
	}
	public void setObjetivosGenerales(String objetivosGenerales) {
		this.objetivosGenerales = objetivosGenerales;
	}
	public Long getNumeroOficio() {
		return numeroOficio;
	}
	public void setNumeroOficio(Long numeroOficio) {
		this.numeroOficio = numeroOficio;
	}
	public Long getAnio() {
		return anio;
	}
	public void setAnio(Long anio) {
		this.anio = anio;
	}
	public Long getFolioIPH() {
		return folioIPH;
	}
	public void setFolioIPH(Long folioIPH) {
		this.folioIPH = folioIPH;
	}
	public void setNumEcoTransporte(String numEcoTransporte) {
		this.numEcoTransporte = numEcoTransporte;
	}
	public String getNumEcoTransporte() {
		return numEcoTransporte;
	}
	public OperativoDTO getOperativo() {
		return operativo;
	}
	public void setOperativo(OperativoDTO operativo) {
		this.operativo = operativo;
	}
	public void setDelitoIph(List<DelitoIphDTO> delitoIph) {
		this.delitoIph = delitoIph;
	}
	public List<DelitoIphDTO> getDelitoIph() {
		return delitoIph;
	}
	public void setFaltaIph(List<FaltaAdministrativaIphDTO> faltaIph) {
		this.faltaIph = faltaIph;
	}
	public List<FaltaAdministrativaIphDTO> getFaltaIph() {
		return faltaIph;
	}
	/**
	 * @param involucradoIphs the involucradoIphs to set
	 */
	public void setInvolucradoIphs(List<InvolucradoIPHDTO> involucradoIphs) {
		this.involucradoIphs = involucradoIphs;
	}
	/**
	 * @return the involucradoIphs
	 */
	public List<InvolucradoIPHDTO> getInvolucradoIphs() {
		return involucradoIphs;
	}
	/**
	 * @param turnoLaboralIphs the turnoLaboralIphs to set
	 */
	public void setTurnoLaboralIphs(List<TurnoLaboralIphDTO> turnoLaboralIphs) {
		this.turnoLaboralIphs = turnoLaboralIphs;
	}
	/**
	 * @return the turnoLaboralIphs
	 */
	public List<TurnoLaboralIphDTO> getTurnoLaboralIphs() {
		return turnoLaboralIphs;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getAsunto() {
		return asunto;
	}
	
    
}

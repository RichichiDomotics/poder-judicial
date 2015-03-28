/**
 * 
 */
package mx.gob.segob.nsjp.dto.defensoria;

import java.util.Date;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;

/**
 * @author adrian
 *
 */
public class ArancelDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4231391690137327731L;
	
	//Fields
	private Long arancel_id;
	private Date fechaRegistro;
    private Date fechaInicio;
    private Date fechaFin;
    private Short horas;
    private Double monto;
    private Date fechaPago;
    
    private ValorDTO clase_valDTO;
    private ExpedienteDTO expedienteDTO;
    private FuncionarioDTO funcionarioDTO;
    
    public ArancelDTO() {
		super();
	}
    
	/**
	 * @param arancel_id
	 */
	public ArancelDTO(Long arancel_id) {
		super();
		this.arancel_id = arancel_id;
	}
	/**
	 * @return the arancel_id
	 */
	public Long getArancel_id() {
		return arancel_id;
	}
	/**
	 * Establece el valor de la propiedad fechaRegistro
	 * @param fechaRegistro valo fechaRegistro a almacenar
	 */
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	/**
	 * Regresa el valor de la propiedad fechaRegistro
	 * @return the fechaRegistro
	 */
	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	/**
	 * @return the dFechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @return the dFechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}
	/**
	 * @return the iHoras
	 */
	public Short getHoras() {
		return horas;
	}
	/**
	 * @return the dcMonto
	 */
	public Double getMonto() {
		return monto;
	}
	/**
	 * @return the dFechaPago
	 */
	public Date getFechaPago() {
		return fechaPago;
	}
	/**
	 * @return the clase_valDTO
	 */
	public ValorDTO getClase_valDTO() {
		return clase_valDTO;
	}
	/**
	 * @return the expedienteDTO
	 */
	public ExpedienteDTO getExpedienteDTO() {
		return expedienteDTO;
	}
	/**
	 * @return the funcionarioDTO
	 */
	public FuncionarioDTO getFuncionarioDTO() {
		return funcionarioDTO;
	}
	/**
	 * @param arancel_id the arancel_id to set
	 */
	public void setArancel_id(Long arancel_id) {
		this.arancel_id = arancel_id;
	}
	/**
	 * @param dFechaInicio the dFechaInicio to set
	 */
	public void setFechaInicio(Date dFechaInicio) {
		this.fechaInicio = dFechaInicio;
	}
	/**
	 * @param dFechaFin the dFechaFin to set
	 */
	public void setFechaFin(Date dFechaFin) {
		this.fechaFin = dFechaFin;
	}
	/**
	 * @param iHoras the iHoras to set
	 */
	public void setHoras(Short iHoras) {
		this.horas = iHoras;
	}
	/**
	 * @param dcMonto the dcMonto to set
	 */
	public void setMonto(Double dcMonto) {
		this.monto = dcMonto;
	}
	/**
	 * @param dFechaPago the dFechaPago to set
	 */
	public void setFechaPago(Date dFechaPago) {
		this.fechaPago = dFechaPago;
	}
	/**
	 * @param clase_valDTO the clase_valDTO to set
	 */
	public void setClase_valDTO(ValorDTO clase_valDTO) {
		this.clase_valDTO = clase_valDTO;
	}
	/**
	 * @param expedienteDTO the expedienteDTO to set
	 */
	public void setExpedienteDTO(ExpedienteDTO expedienteDTO) {
		this.expedienteDTO = expedienteDTO;
	}

	/**
	 * @param funcionarioDTO the funcionarioDTO to set
	 */
	public void setFuncionarioDTO(FuncionarioDTO funcionarioDTO) {
		this.funcionarioDTO = funcionarioDTO;
	}

}

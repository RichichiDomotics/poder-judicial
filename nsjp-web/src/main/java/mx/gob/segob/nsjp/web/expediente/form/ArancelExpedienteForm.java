package mx.gob.segob.nsjp.web.expediente.form;

import java.util.Calendar;
import java.util.Date;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.web.base.form.GenericForm;

public class ArancelExpedienteForm extends GenericForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long arancel_id;
	private Date fechaRegistro = Calendar.getInstance().getTime();
    private String fechaInicial;
    private String fechaFinal;
    private Short horas;
    private Double monto;
    private Date fechaPago;
    
    private ValorDTO clase_valDTO = new ValorDTO();
    private ExpedienteDTO expedienteDTO = new ExpedienteDTO();
    private FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
	
    /**
	 * Regresa el valor de la propiedad arancel_id
	 * @return the arancel_id
	 */
	public Long getArancel_id() {
		return arancel_id;
	}
	/**
	 * Establece el valor de la propiedad arancel_id
	 * @param arancel_id valo arancel_id a almacenar
	 */
	public void setArancel_id(Long arancel_id) {
		this.arancel_id = arancel_id;
	}
	/**
	 * Regresa el valor de la propiedad fechaRegistro
	 * @return the fechaRegistro
	 */
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	/**
	 * Establece el valor de la propiedad fechaRegistro
	 * @param fechaRegistro valo fechaRegistro a almacenar
	 */
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	/**
	 * Regresa el valor de la propiedad fechaInicial
	 * @return the fechaInicial
	 */
	public String getFechaInicial() {
		return fechaInicial;
	}
	/**
	 * Establece el valor de la propiedad fechaInicial
	 * @param fechaInicial valo fechaInicial a almacenar
	 */
	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}
	/**
	 * Regresa el valor de la propiedad fechaFinal
	 * @return the fechaFinal
	 */
	public String getFechaFinal() {
		return fechaFinal;
	}
	/**
	 * Establece el valor de la propiedad fechaFinal
	 * @param fechaFinal valo fechaFinal a almacenar
	 */
	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	/**
	 * Regresa el valor de la propiedad horas
	 * @return the horas
	 */
	public Short getHoras() {
		return horas;
	}
	/**
	 * Establece el valor de la propiedad horas
	 * @param horas valo horas a almacenar
	 */
	public void setHoras(Short horas) {
		this.horas = horas;
	}
	/**
	 * Regresa el valor de la propiedad monto
	 * @return the monto
	 */
	public Double getMonto() {
		return monto;
	}
	/**
	 * Establece el valor de la propiedad monto
	 * @param monto valo monto a almacenar
	 */
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	/**
	 * Regresa el valor de la propiedad fechaPago
	 * @return the fechaPago
	 */
	public Date getFechaPago() {
		return fechaPago;
	}
	/**
	 * Establece el valor de la propiedad fechaPago
	 * @param fechaPago valo fechaPago a almacenar
	 */
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	/**
	 * Regresa el valor de la propiedad clase_valDTO
	 * @return the clase_valDTO
	 */
	public ValorDTO getClase_valDTO() {
		return clase_valDTO;
	}
	/**
	 * Establece el valor de la propiedad clase_valDTO
	 * @param clase_valDTO valo clase_valDTO a almacenar
	 */
	public void setClase_valDTO(ValorDTO clase_valDTO) {
		this.clase_valDTO = clase_valDTO;
	}
	/**
	 * Regresa el valor de la propiedad expedienteDTO
	 * @return the expedienteDTO
	 */
	public ExpedienteDTO getExpedienteDTO() {
		return expedienteDTO;
	}
	/**
	 * Establece el valor de la propiedad expedienteDTO
	 * @param expedienteDTO valo expedienteDTO a almacenar
	 */
	public void setExpedienteDTO(ExpedienteDTO expedienteDTO) {
		this.expedienteDTO = expedienteDTO;
	}
	/**
	 * Regresa el valor de la propiedad funcionarioDTO
	 * @return the funcionarioDTO
	 */
	public FuncionarioDTO getFuncionarioDTO() {
		return funcionarioDTO;
	}
	/**
	 * Establece el valor de la propiedad funcionarioDTO
	 * @param funcionarioDTO valo funcionarioDTO a almacenar
	 */
	public void setFuncionarioDTO(FuncionarioDTO funcionarioDTO) {
		this.funcionarioDTO = funcionarioDTO;
	}
	
}

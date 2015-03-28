package mx.gob.segob.nsjp.dto.evidencia;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import mx.gob.segob.nsjp.dto.base.GenericWSDTO;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaWSDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoWSDTO;

public class EvidenciaWSDTO extends GenericWSDTO {

    private Long evidenciaId;
    private ObjetoWSDTO objeto;
    private CadenaDeCustodiaWSDTO cadenaDeCustodia;
    private Long numeroEvidencia;
    private String descripcion;
    private Date fechaLevantamiento;
    private String origenEvidencia;
    private String codigoBarras;
    private EslabonWSDTO ultimoEslabon;
    private Set<EslabonWSDTO> eslabones = new HashSet<EslabonWSDTO>(0);
    private Long idEstatus;
    private Long idDestinoLegal;
    private String funcionario;
    private String funcionarioBaja;
    private String motivoBaja;
    
    private int cantidad;

	/**
	 * Regresa el valor de la propiedad evidenciaId
	 * @return the evidenciaId
	 */
	public Long getEvidenciaId() {
		return evidenciaId;
	}

	/**
	 * Establece el valor de la propiedad evidenciaId
	 * @param evidenciaId valo evidenciaId a almacenar
	 */
	public void setEvidenciaId(Long evidenciaId) {
		this.evidenciaId = evidenciaId;
	}

	/**
	 * Regresa el valor de la propiedad objeto
	 * @return the objeto
	 */
	public ObjetoWSDTO getObjeto() {
		return objeto;
	}

	/**
	 * Establece el valor de la propiedad objeto
	 * @param objeto valo objeto a almacenar
	 */
	public void setObjeto(ObjetoWSDTO objeto) {
		this.objeto = objeto;
	}

	/**
	 * Regresa el valor de la propiedad cadenaDeCustodia
	 * @return the cadenaDeCustodia
	 */
	public CadenaDeCustodiaWSDTO getCadenaDeCustodia() {
		return cadenaDeCustodia;
	}

	/**
	 * Establece el valor de la propiedad cadenaDeCustodia
	 * @param cadenaDeCustodia valo cadenaDeCustodia a almacenar
	 */
	public void setCadenaDeCustodia(CadenaDeCustodiaWSDTO cadenaDeCustodia) {
		this.cadenaDeCustodia = cadenaDeCustodia;
	}

	/**
	 * Regresa el valor de la propiedad numeroEvidencia
	 * @return the numeroEvidencia
	 */
	public Long getNumeroEvidencia() {
		return numeroEvidencia;
	}

	/**
	 * Establece el valor de la propiedad numeroEvidencia
	 * @param numeroEvidencia valo numeroEvidencia a almacenar
	 */
	public void setNumeroEvidencia(Long numeroEvidencia) {
		this.numeroEvidencia = numeroEvidencia;
	}

	/**
	 * Regresa el valor de la propiedad descripcion
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Establece el valor de la propiedad descripcion
	 * @param descripcion valo descripcion a almacenar
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Regresa el valor de la propiedad fechaLevantamiento
	 * @return the fechaLevantamiento
	 */
	public Date getFechaLevantamiento() {
		return fechaLevantamiento;
	}

	/**
	 * Establece el valor de la propiedad fechaLevantamiento
	 * @param fechaLevantamiento valo fechaLevantamiento a almacenar
	 */
	public void setFechaLevantamiento(Date fechaLevantamiento) {
		this.fechaLevantamiento = fechaLevantamiento;
	}

	/**
	 * Regresa el valor de la propiedad origenEvidencia
	 * @return the origenEvidencia
	 */
	public String getOrigenEvidencia() {
		return origenEvidencia;
	}

	/**
	 * Establece el valor de la propiedad origenEvidencia
	 * @param origenEvidencia valo origenEvidencia a almacenar
	 */
	public void setOrigenEvidencia(String origenEvidencia) {
		this.origenEvidencia = origenEvidencia;
	}

	/**
	 * Regresa el valor de la propiedad codigoBarras
	 * @return the codigoBarras
	 */
	public String getCodigoBarras() {
		return codigoBarras;
	}

	/**
	 * Establece el valor de la propiedad codigoBarras
	 * @param codigoBarras valo codigoBarras a almacenar
	 */
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	/**
	 * Regresa el valor de la propiedad ultimoEslabon
	 * @return the ultimoEslabon
	 */
	public EslabonWSDTO getUltimoEslabon() {
		return ultimoEslabon;
	}

	/**
	 * Establece el valor de la propiedad ultimoEslabon
	 * @param ultimoEslabon valo ultimoEslabon a almacenar
	 */
	public void setUltimoEslabon(EslabonWSDTO ultimoEslabon) {
		this.ultimoEslabon = ultimoEslabon;
	}

	/**
	 * Regresa el valor de la propiedad eslabones
	 * @return the eslabones
	 */
	public Set<EslabonWSDTO> getEslabones() {
		return eslabones;
	}

	/**
	 * Establece el valor de la propiedad eslabones
	 * @param eslabones valo eslabones a almacenar
	 */
	public void setEslabones(Set<EslabonWSDTO> eslabones) {
		this.eslabones = eslabones;
	}

	/**
	 * Regresa el valor de la propiedad idEstatus
	 * @return the idEstatus
	 */
	public Long getIdEstatus() {
		return idEstatus;
	}

	/**
	 * Establece el valor de la propiedad idEstatus
	 * @param idEstatus valo idEstatus a almacenar
	 */
	public void setIdEstatus(Long idEstatus) {
		this.idEstatus = idEstatus;
	}

	/**
	 * Regresa el valor de la propiedad idDestinoLegal
	 * @return the idDestinoLegal
	 */
	public Long getIdDestinoLegal() {
		return idDestinoLegal;
	}

	/**
	 * Establece el valor de la propiedad idDestinoLegal
	 * @param idDestinoLegal valo idDestinoLegal a almacenar
	 */
	public void setIdDestinoLegal(Long idDestinoLegal) {
		this.idDestinoLegal = idDestinoLegal;
	}

	/**
	 * Regresa el valor de la propiedad funcionario
	 * @return the funcionario
	 */
	public String getFuncionario() {
		return funcionario;
	}

	/**
	 * Establece el valor de la propiedad funcionario
	 * @param funcionario valo funcionario a almacenar
	 */
	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}

	/**
	 * Regresa el valor de la propiedad funcionarioBaja
	 * @return the funcionarioBaja
	 */
	public String getFuncionarioBaja() {
		return funcionarioBaja;
	}

	/**
	 * Establece el valor de la propiedad funcionarioBaja
	 * @param funcionarioBaja valo funcionarioBaja a almacenar
	 */
	public void setFuncionarioBaja(String funcionarioBaja) {
		this.funcionarioBaja = funcionarioBaja;
	}

	/**
	 * Regresa el valor de la propiedad motivoBaja
	 * @return the motivoBaja
	 */
	public String getMotivoBaja() {
		return motivoBaja;
	}

	/**
	 * Establece el valor de la propiedad motivoBaja
	 * @param motivoBaja valo motivoBaja a almacenar
	 */
	public void setMotivoBaja(String motivoBaja) {
		this.motivoBaja = motivoBaja;
	}

	/**
	 * Regresa el valor de la propiedad cantidad
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * Establece el valor de la propiedad cantidad
	 * @param cantidad valo cantidad a almacenar
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
    
}

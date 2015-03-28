package mx.gob.segob.nsjp.dto.evidencia;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;

@SuppressWarnings("serial")
public class EvidenciaDTO extends GenericDTO implements Comparable<Object> {

    private Long evidenciaId;
    private ObjetoDTO objeto;
    private CadenaDeCustodiaDTO cadenaDeCustodia;
    private Long numeroEvidencia;
    private String descripcion;
    private Date fechaLevantamiento;
    private String origenEvidencia;
    private String codigoBarras;
    private EslabonDTO ultimoEslabon;
    private Set<EslabonDTO> eslabones = new HashSet<EslabonDTO>(0);
    private FuncionarioDTO funcionario;
    private String strFuncionario;
    private ValorDTO estatus;
    private ValorDTO destinoLegal;
    private FuncionarioDTO funcionarioBaja;
    private String strFuncionarioBaja;
    private String motivoBaja;
    
    private int cantidad;

    /**
     * Usado en el CU de resguardo de evidencias para mostrar la contidad de
     * evidencias resguardadas para una cadena de custodia.
     */
    private Integer cantEvidenciasResguardadas = 0;
    /**
     * Usado en el CU de resguardo de evidencias para mostrar el dueño de la
     * evidencia, es decir, al AMP dueño del expediente al que está asiciada la
     * evidencia.
     */
    private String duenioEvidencia;
    
    public EvidenciaDTO(){}
    
    public EvidenciaDTO(Long id){
    	setEvidenciaId(id);
    }
    /**
     * @return the evidenciaId
     */
    public Long getEvidenciaId() {
        return evidenciaId;
    }

    /**
     * @param evidenciaId
     *            the evidenciaId to set
     */
    public void setEvidenciaId(Long evidenciaId) {
        this.evidenciaId = evidenciaId;
    }

    /**
     * @return the objeto
     */
    public ObjetoDTO getObjeto() {
        return objeto;
    }

    /**
     * @param objeto
     *            the objeto to set
     */
    public void setObjeto(ObjetoDTO objeto) {
        this.objeto = objeto;
    }

    /**
     * @return the cadenaDeCustodia
     */
    public CadenaDeCustodiaDTO getCadenaDeCustodia() {
        return cadenaDeCustodia;
    }

    /**
     * @param cadenaDeCustodia
     *            the cadenaDeCustodia to set
     */
    public void setCadenaDeCustodia(CadenaDeCustodiaDTO cadenaDeCustodia) {
        this.cadenaDeCustodia = cadenaDeCustodia;
    }

    /**
     * @return the numeroEvidencia
     */
    public Long getNumeroEvidencia() {
        return numeroEvidencia;
    }

    /**
     * @param numeroEvidencia
     *            the numeroEvidencia to set
     */
    public void setNumeroEvidencia(Long numeroEvidencia) {
        this.numeroEvidencia = numeroEvidencia;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion
     *            the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the fechaLevantamiento
     */
    public Date getFechaLevantamiento() {
        return fechaLevantamiento;
    }

    /**
     * @param fechaLevantamiento
     *            the fechaLevantamiento to set
     */
    public void setFechaLevantamiento(Date fechaLevantamiento) {
        this.fechaLevantamiento = fechaLevantamiento;
    }

    /**
     * @return the origenEvidencia
     */
    public String getOrigenEvidencia() {
        return origenEvidencia;
    }

    /**
     * @param origenEvidencia
     *            the origenEvidencia to set
     */
    public void setOrigenEvidencia(String origenEvidencia) {
        this.origenEvidencia = origenEvidencia;
    }

    /**
     * @return the codigoBarras
     */
    public String getCodigoBarras() {
        return codigoBarras;
    }

    /**
     * @param codigoBarras
     *            the codigoBarras to set
     */
    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public EslabonDTO getUltimoEslabon() {
        return ultimoEslabon;
    }

    public void setUltimoEslabon(EslabonDTO ultimoEslabon) {
        this.ultimoEslabon = ultimoEslabon;
    }

    public Set<EslabonDTO> getEslabones() {
        return eslabones;
    }

    public void setEslabones(Set<EslabonDTO> eslabones) {
        this.eslabones = eslabones;
    }

    public FuncionarioDTO getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(FuncionarioDTO funcionario) {
        this.funcionario = funcionario;
    }

    /**
     * Método de acceso al campo cantEvidenciasResguardadas.
     * 
     * @return El valor del campo cantEvidenciasResguardadas
     */
    public Integer getCantEvidenciasResguardadas() {
        return cantEvidenciasResguardadas;
    }

    /**
     * Asigna el valor al campo cantEvidenciasResguardadas.
     * 
     * @param cantEvidenciasResguardadas
     *            el valor cantEvidenciasResguardadas a asignar
     */
    public void setCantEvidenciasResguardadas(Integer cantEvidenciasResguardadas) {
        this.cantEvidenciasResguardadas = cantEvidenciasResguardadas;
    }

    /**
     * Método de acceso al campo duenioEvidencia.
     * @return El valor del campo duenioEvidencia
     */
    public String getDuenioEvidencia() {
        return duenioEvidencia;
    }

    /**
     * Asigna el valor al campo duenioEvidencia.
     * @param duenioEvidencia el valor duenioEvidencia a asignar
     */
    public void setDuenioEvidencia(String duenioEvidencia) {
        this.duenioEvidencia = duenioEvidencia;
    }

	/**
	 * @param estatus the estatus to set
	 */
	public void setEstatus(ValorDTO estatus) {
		this.estatus = estatus;
	}

	/**
	 * @return the estatus
	 */
	public ValorDTO getEstatus() {
		return estatus;
	}

	/**
	 * @param destinoLegal the destinoLegal to set
	 */
	public void setDestinoLegal(ValorDTO destinoLegal) {
		this.destinoLegal = destinoLegal;
	}

	/**
	 * @return the destinoLegal
	 */
	public ValorDTO getDestinoLegal() {
		return destinoLegal;
	}

    public FuncionarioDTO getFuncionarioBaja() {
        return funcionarioBaja;
    }

    public void setFuncionarioBaja(FuncionarioDTO funcionarioBaja) {
        this.funcionarioBaja = funcionarioBaja;
    }

    public String getMotivoBaja() {
        return motivoBaja;
    }

    public void setMotivoBaja(String motivoBaja) {
        this.motivoBaja = motivoBaja;
    }

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * Regresa el valor de la propiedad strfuncionario
	 * @return the strfuncionario
	 */
	public String getStrFuncionario() {
		return strFuncionario;
	}

	/**
	 * Establece el valor de la propiedad strfuncionario
	 * @param strfuncionario valo strfuncionario a almacenar
	 */
	public void setStrFuncionario(String strfuncionario) {
		this.strFuncionario = strfuncionario;
	}

	/**
	 * Regresa el valor de la propiedad strfuncionarioBaja
	 * @return the strfuncionarioBaja
	 */
	public String getStrFuncionarioBaja() {
		return strFuncionarioBaja;
	}

	/**
	 * Establece el valor de la propiedad strfuncionarioBaja
	 * @param strfuncionarioBaja valo strfuncionarioBaja a almacenar
	 */
	public void setStrFuncionarioBaja(String strfuncionarioBaja) {
		this.strFuncionarioBaja = strfuncionarioBaja;
	}
	
    public boolean equals(Object obj) {
		if ((this == obj))
			return true;
		if ((obj == null))
			return false;
		if (!(obj instanceof EvidenciaDTO))
			return false;
		EvidenciaDTO evidenciaTemp = (EvidenciaDTO) obj;

		return ((this.getEvidenciaId().equals( evidenciaTemp
				.getEvidenciaId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (this.getEvidenciaId() == null ? 0 : this
						.getEvidenciaId().hashCode());
		return result;
	}

	/**
     * para ordenar una lista de evidenciaID
     * @param obj
     */
    public int compareTo(Object obj) {
    	EvidenciaDTO evidenciaDTO = (EvidenciaDTO)obj;       
    	 return (this.getEvidenciaId().compareTo(evidenciaDTO.getEvidenciaId()));
    }
}

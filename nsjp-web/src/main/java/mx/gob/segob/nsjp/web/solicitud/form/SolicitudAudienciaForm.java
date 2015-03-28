package mx.gob.segob.nsjp.web.solicitud.form;

import mx.gob.segob.nsjp.web.base.form.GenericForm;

/**
 *
 * @author Jacob Lobaco
 */
public class SolicitudAudienciaForm extends GenericForm {
	
	private static final long serialVersionUID = 1L;
	
	protected String numeroDeCaso;
    protected Long tipoDeAudiencia;
    protected Long institucionSolicitante;
    protected String nombreDelSolicitante;
    protected String fundamentoDeLaSolicitud;
    protected String victimas;
    protected String imputados;
    protected String fechaLimiteAudiencia;
    protected String horaLimiteAudiencia;
    protected String situacionesEspeciales;
    private String idExpedienteSoli;
    private String idNumeroExpediente;
    protected Long distrito;
    protected Long tribunal;
    protected Long funcionarioDestinatario;
    
    
    /**
     * Get the value of numeroDeCaso
     *
     * @return the value of numeroDeCaso
     */
    public String getNumeroDeCaso() {
        return numeroDeCaso;
    }

    /**
     * Set the value of numeroDeCaso
     *
     * @param numeroDeCaso new value of numeroDeCaso
     */
    public void setNumeroDeCaso(String numeroDeCaso) {
        this.numeroDeCaso = numeroDeCaso;
    }

    /**
     * Get the value of tipoDeAudiencia
     *
     * @return the value of tipoDeAudiencia
     */
    public Long getTipoDeAudiencia() {
        return tipoDeAudiencia;
    }

    /**
     * Set the value of tipoDeAudiencia
     *
     * @param tipoDeAudiencia new value of tipoDeAudiencia
     */
    public void setTipoDeAudiencia(Long tipoDeAudiencia) {
        this.tipoDeAudiencia = tipoDeAudiencia;
    }

    /**
     * Get the value of institucionSolicitante
     *
     * @return the value of institucionSolicitante
     */
    public Long getInstitucionSolicitante() {
        return institucionSolicitante;
    }

    /**
     * Set the value of institucionSolicitante
     *
     * @param institucionSolicitante new value of institucionSolicitante
     */
    public void setInstitucionSolicitante(Long institucionSolicitante) {
        this.institucionSolicitante = institucionSolicitante;
    }
    
    /**
     * Get the value of nombreDelSolicitante
     *
     * @return the value of nombreDelSolicitante
     */
    public String getNombreDelSolicitante() {
        return nombreDelSolicitante;
    }

    /**
     * Set the value of nombreDelSolicitante
     *
     * @param nombreDelSolicitante new value of nombreDelSolicitante
     */
    public void setNombreDelSolicitante(String nombreDelSolicitante) {
        this.nombreDelSolicitante = nombreDelSolicitante;
    }

    /**
     * Get the value of fundamentoDeLaSolicitud
     *
     * @return the value of fundamentoDeLaSolicitud
     */
    public String getFundamentoDeLaSolicitud() {
        return fundamentoDeLaSolicitud;
    }

    /**
     * Set the value of fundamentoDeLaSolicitud
     *
     * @param fundamentoDeLaSolicitud new value of fundamentoDeLaSolicitud
     */
    public void setFundamentoDeLaSolicitud(String fundamentoDeLaSolicitud) {
        this.fundamentoDeLaSolicitud = fundamentoDeLaSolicitud;
    }

    /**
     * Get the value of victimas
     *
     * @return the value of victimas
     */
    public String getVictimas() {
        return victimas;
    }

    /**
     * Set the value of victimas
     *
     * @param victimas new value of victimas
     */
    public void setVictimas(String victimas) {
        this.victimas = victimas;
    }

    /**
     * Get the value of imputados
     *
     * @return the value of imputados
     */
    public String getImputados() {
        return imputados;
    }

    /**
     * Set the value of imputados
     *
     * @param imputados new value of imputados
     */
    public void setImputados(String imputados) {
        this.imputados = imputados;
    }

    /**
     * Get the value of fechaLimiteAudiencia
     *
     * @return the value of fechaLimiteAudiencia
     */
    public String getFechaLimiteAudiencia() {
        return fechaLimiteAudiencia;
    }

    /**
     * Set the value of fechaLimiteAudiencia
     *
     * @param fechaLimiteAudiencia new value of fechaLimiteAudiencia
     */
    public void setFechaLimiteAudiencia(String fechaLimiteAudiencia) {
        this.fechaLimiteAudiencia = fechaLimiteAudiencia;
    }

    /**
     * Get the value of horaLimiteAudiencia
     *
     * @return the value of horaLimiteAudiencia
     */
    public String getHoraLimiteAudiencia() {
        return horaLimiteAudiencia;
    }

    /**
     * Set the value of horaLimiteAudiencia
     *
     * @param horaLimiteAudiencia new value of horaLimiteAudiencia
     */
    public void setHoraLimiteAudiencia(String horaLimiteAudiencia) {
        this.horaLimiteAudiencia = horaLimiteAudiencia;
    }

    /**
     * Get the value of situacionesEspeciales
     *
     * @return the value of situacionesEspeciales
     */
    public String getSituacionesEspeciales() {
        return situacionesEspeciales;
    }

    /**
     * Set the value of situacionesEspeciales
     *
     * @param situacionesEspeciales new value of situacionesEspeciales
     */
    public void setSituacionesEspeciales(String situacionesEspeciales) {
        this.situacionesEspeciales = situacionesEspeciales;
    }
    
	/**
	 * @return the idExpedienteSoli
	 */
	public String getIdExpedienteSoli() {
		return idExpedienteSoli;
	}

	/**
	 * @param idExpedienteSoli the idExpedienteSoli to set
	 */
	public void setIdExpedienteSoli(String idExpedienteSoli) {
		this.idExpedienteSoli = idExpedienteSoli;
	}

	
	/**
	 * Establece el valor de la propiedad idNumeroExpediente
	 * @param idNumeroExpediente valo idNumeroExpediente a almacenar
	 */
	public void setIdNumeroExpediente(String idNumeroExpediente) {
		this.idNumeroExpediente = idNumeroExpediente;
	}

	/**
	 * Regresa el valor de la propiedad idNumeroExpediente
	 * @return the idNumeroExpediente
	 */
	public String getIdNumeroExpediente() {
		return idNumeroExpediente;
	}
	
	/**
	 * @return the distrito
	 */
	public Long getDistrito() {
		return distrito;
	}

	/**
	 * @param distrito the distrito to set
	 */
	public void setDistrito(Long distrito) {
		this.distrito = distrito;
	}

	/**
	 * @return the tribunal
	 */
	public Long getTribunal() {
		return tribunal;
	}

	/**
	 * @param tribunal the tribunal to set
	 */
	public void setTribunal(Long tribunal) {
		this.tribunal = tribunal;
	}

	/**
	 * @return the funcionarioDestinatario
	 */
	public Long getFuncionarioDestinatario() {
		return funcionarioDestinatario;
	}

	/**
	 * @param funcionarioDestinatario the funcionarioDestinatario to set
	 */
	public void setFuncionarioDestinatario(Long funcionarioDestinatario) {
		this.funcionarioDestinatario = funcionarioDestinatario;
	}

}

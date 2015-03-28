package mx.gob.segob.nsjp.dto.evidencia;

import java.util.Date;

import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dto.audiencia.HoraDisponibilidad;
import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;

/**
 *
 * @author Jacob Lobaco
 */
@SuppressWarnings("serial")
public class EslabonDTO extends GenericDTO {

    private Integer numeroEslabon;
    private ValorDTO tipoEslabon;
    private FuncionarioDTO funcionariRecibe;
    private FuncionarioDTO funcionariEntrega;
    private String quienEntrega;
    private String quienRecibe;
    
    private Long eslabonId;
    private Date fechaInicioMovimiento;
    private Date fechaFinMovimiento;
    private String strFechaInicioMov;
    private String strHoraInicioMov;
    private String strFechaFinMov;
    private String strHoraFinMov;
    private String observacion;
    
    private DocumentoDTO documentoDTO;
    private String ubicacionFisica;
    private String posicion;
    
    private ValorDTO tipoEslabonDeRecepcion;
    private String institucionQueEntrega;
    private String institucionQueRecibe;
    
    private Date fechaInicioPrestamo;
    private String strFechaInicioPrestamo;
    private String strHoraInicioPrestamo;
    
    private Date fechaFinPrestamo;
    private String strFechaFinPrestamo;
    private String strHoraFinPrestamo;
    
    

    /**
	 * @return the documentoDTO
	 */
	public DocumentoDTO getDocumentoDTO() {
		return documentoDTO;
	}

	/**
	 * @return the ubicacionFisica
	 */
	public String getUbicacionFisica() {
		return ubicacionFisica;
	}

	/**
	 * @return the posicion
	 */
	public String getPosicion() {
		return posicion;
	}

	/**
	 * @param documentoDTO the documentoDTO to set
	 */
	public void setDocumentoDTO(DocumentoDTO documentoDTO) {
		this.documentoDTO = documentoDTO;
	}

	/**
	 * @param ubicacionFisica the ubicacionFisica to set
	 */
	public void setUbicacionFisica(String ubicacionFisica) {
		this.ubicacionFisica = ubicacionFisica;
	}

	/**
	 * @param posicion the posicion to set
	 */
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public Integer getNumeroEslabon() {
        return numeroEslabon;
    }

    public void setNumeroEslabon(Integer numeroEslabon) {
        this.numeroEslabon = numeroEslabon;
    }

    public ValorDTO getTipoEslabon() {
        return tipoEslabon;
    }

    public void setTipoEslabon(ValorDTO tipoEslabon) {
        this.tipoEslabon = tipoEslabon;
    }

    /**
     * Método de acceso al campo funcionariRecibe.
     * @return El valor del campo funcionariRecibe
     */
    public FuncionarioDTO getFuncionariRecibe() {
        return funcionariRecibe;
    }

    /**
     * Asigna el valor al campo funcionariRecibe.
     * @param funcionariRecibe el valor funcionariRecibe a asignar
     */
    public void setFuncionariRecibe(FuncionarioDTO funcionariRecibe) {
        this.funcionariRecibe = funcionariRecibe;
    }

    /**
     * Método de acceso al campo funcionariEntrega.
     * @return El valor del campo funcionariEntrega
     */
    public FuncionarioDTO getFuncionariEntrega() {
        return funcionariEntrega;
    }

    /**
     * Asigna el valor al campo funcionariEntrega.
     * @param funcionariEntrega el valor funcionariEntrega a asignar
     */
    public void setFuncionariEntrega(FuncionarioDTO funcionariEntrega) {
        this.funcionariEntrega = funcionariEntrega;
    }

	/**
	 * @param evidenciaId the evidenciaId to set
	 */
	public void setEslabonId(Long eslabonId) {
		this.eslabonId = eslabonId;
	}

	/**
	 * @return the evidenciaId
	 */
	public Long getEslabonId() {
		return eslabonId;
	}

	/**
	 * Método de acceso al campo fechaEntrega.
	 * @return El valor del campo fechaEntrega
	 */
	public Date getFechaInicioMovimiento() {
		return fechaInicioMovimiento;
	}

	/**
	 * Asigna el valor al campo fechaEntrega.
	 * @param fechaEntrega el valor fechaEntrega a asignar
	 */
	public void setFechaInicioMovimiento(Date fechaEntrega) {
		this.fechaInicioMovimiento = fechaEntrega;
	}

	

	/**
	 * Asigna el valor al campo strFechaEntrega.
	 * @param strFechaEntrega el valor strFechaEntrega a asignar
	 */
	public void setStrFechaEntrega(String strFechaEntrega) {
		this.strFechaInicioMov = strFechaEntrega;
	}

	/**
	 * Método de acceso al campo strHoraEntrega.
	 * @return El valor del campo strHoraEntrega
	 */
	public String getStrHoraEntrega() {
		 if (strHoraInicioMov != null) {
	            return strHoraInicioMov;
	        }
	        return (fechaInicioMovimiento!= null
	                ? DateUtils.formatearHora(fechaInicioMovimiento)
	                : null);
	}

	/**
	 * Asigna el valor al campo strHoraEntrega.
	 * @param strHoraEntrega el valor strHoraEntrega a asignar
	 */
	public void setStrHoraEntrega(String strHoraEntrega) {
		this.strHoraInicioMov = strHoraEntrega;
	}

	/**
	 * Método de acceso al campo fechaRecepcion.
	 * @return El valor del campo fechaRecepcion
	 */
	public Date getFechaFinMovimiento() {
		return fechaFinMovimiento;
	}

	/**
	 * Asigna el valor al campo fechaRecepcion.
	 * @param fechaRecepcion el valor fechaRecepcion a asignar
	 */
	public void setFechaFinMovimiento(Date fechaRecepcion) {
		this.fechaFinMovimiento = fechaRecepcion;
	}

	/**
	 * Método de acceso al campo strFechaRecepcion.
	 * @return El valor del campo strFechaRecepcion
	 */
	public String getStrFechaRecepcion() {
		if (strFechaFinMov!=null) {
			return strFechaFinMov;
		}		
		return (fechaFinMovimiento!=null?DateUtils.formatear(fechaFinMovimiento):null);
	}

	public String getStrFechaEntrega() {
		if (strFechaInicioMov!=null) {
			return strFechaInicioMov;
		}		
		return (fechaInicioMovimiento!=null?DateUtils.formatear(fechaInicioMovimiento):null);
	}

	/**
	 * Asigna el valor al campo strFechaRecepcion.
	 * @param strFechaRecepcion el valor strFechaRecepcion a asignar
	 */
	public void setStrFechaRecepcion(String strFechaRecepcion) {
		this.strFechaFinMov = strFechaRecepcion;
	}

	/**
	 * Método de acceso al campo strHoraRecepcion.
	 * @return El valor del campo strHoraRecepcion
	 */
	public String getStrHoraRecepcion() {
		 if (strHoraFinMov != null) {
	            return strHoraFinMov;
	        }
	        return (fechaFinMovimiento!= null
	                ? DateUtils.formatearHora(fechaFinMovimiento)
	                : null);
	}

	/**
	 * Asigna el valor al campo strHoraRecepcion.
	 * @param strHoraRecepcion el valor strHoraRecepcion a asignar
	 */
	public void setStrHoraRecepcion(String strHoraRecepcion) {
		this.strHoraFinMov = strHoraRecepcion;
	}

	/**
	 * @param observacion the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * Método de acceso al campo quienEntrega.
	 * @return El valor del campo quienEntrega
	 */
	public String getQuienEntrega() {
		return quienEntrega;
	}

	/**
	 * Asigna el valor al campo quienEntrega.
	 * @param quienEntrega el valor quienEntrega a asignar
	 */
	public void setQuienEntrega(String quienEntrega) {
		this.quienEntrega = quienEntrega;
	}

	/**
	 * Método de acceso al campo quienRecibe.
	 * @return El valor del campo quienRecibe
	 */
	public String getQuienRecibe() {
		return quienRecibe;
	}

	/**
	 * Asigna el valor al campo quienRecibe.
	 * @param quienRecibe el valor quienRecibe a asignar
	 */
	public void setQuienRecibe(String quienRecibe) {
		this.quienRecibe = quienRecibe;
	}

	/**
	 * Método de acceso al campo tipoEslabonDeRecepcion.
	 * @return El valor del campo tipoEslabonDeRecepcion
	 */
	public ValorDTO getTipoEslabonDeRecepcion() {
		return tipoEslabonDeRecepcion;
	}

	/**
	 * Asigna el valor al campo tipoEslabonDeRecepcion.
	 * @param tipoEslabonDeRecepcion el valor tipoEslabonDeRecepcion a asignar
	 */
	public void setTipoEslabonDeRecepcion(ValorDTO tipoEslabonDeRecepcion) {
		this.tipoEslabonDeRecepcion = tipoEslabonDeRecepcion;
	}

	/**
	 * Método de acceso al campo institucionQueEntrega.
	 * @return El valor del campo institucionQueEntrega
	 */
	public String getInstitucionQueEntrega() {
		return institucionQueEntrega;
	}

	/**
	 * Asigna el valor al campo institucionQueEntrega.
	 * @param institucionQueEntrega el valor institucionQueEntrega a asignar
	 */
	public void setInstitucionQueEntrega(String institucionQueEntrega) {
		this.institucionQueEntrega = institucionQueEntrega;
	}

	/**
	 * Método de acceso al campo institucionQueRecibe.
	 * @return El valor del campo institucionQueRecibe
	 */
	public String getInstitucionQueRecibe() {
		return institucionQueRecibe;
	}

	/**
	 * Asigna el valor al campo institucionQueRecibe.
	 * @param institucionQueRecibe el valor institucionQueRecibe a asignar
	 */
	public void setInstitucionQueRecibe(String institucionQueRecibe) {
		this.institucionQueRecibe = institucionQueRecibe;
	}

	/**
	 * @return the fechaInicioPrestamo
	 */
	public Date getFechaInicioPrestamo() {
		return fechaInicioPrestamo;
	}

	/**
	 * @param fechaInicioPrestamo the fechaInicioPrestamo to set
	 */
	public void setFechaInicioPrestamo(Date fechaInicioPrestamo) {
		this.fechaInicioPrestamo = fechaInicioPrestamo;
	}

	/**
	 * @return the strFechaInicioPrestamo
	 */
	public String getStrFechaInicioPrestamo() {
		if (strFechaInicioPrestamo != null) {
            return strFechaInicioPrestamo;
        }
        return (fechaInicioPrestamo != null
                ? DateUtils.formatear(fechaInicioPrestamo)
                : null);
	}

	/**
	 * @param strFechaInicioPrestamo the strFechaInicioPrestamo to set
	 */
	public void setStrFechaInicioPrestamo(String strFechaInicioPrestamo) {
		this.strFechaInicioPrestamo = strFechaInicioPrestamo;
	}

	/**
	 * @return the strHoraInicioPrestamo
	 */
	public String getStrHoraInicioPrestamo() {
		if (strHoraInicioPrestamo != null) {
            return strHoraInicioPrestamo;
        }
        return (fechaInicioPrestamo != null
                ? DateUtils.formatearHora(fechaInicioPrestamo)
                : null);
	}

	/**
	 * @param strHoraInicioPrestamo the strHoraInicioPrestamo to set
	 */
	public void setStrHoraInicioPrestamo(String strHoraInicioPrestamo) {
		this.strHoraInicioPrestamo = strHoraInicioPrestamo;
	}

	/**
	 * @return the fechaFinPrestamo
	 */
	public Date getFechaFinPrestamo() {
		return fechaFinPrestamo;
	}

	/**
	 * @param fechaFinPrestamo the fechaFinPrestamo to set
	 */
	public void setFechaFinPrestamo(Date fechaFinPrestamo) {
		this.fechaFinPrestamo = fechaFinPrestamo;
	}

	/**
	 * @return the strFechaFinPrestamo
	 */
	public String getStrFechaFinPrestamo() {
		if (strFechaFinPrestamo != null) {
            return strFechaFinPrestamo;
        }
        return (fechaFinPrestamo != null
                ? DateUtils.formatear(fechaFinPrestamo)
                : null);
	}

	/**
	 * @param strFechaFinPrestamo the strFechaFinPrestamo to set
	 */
	public void setStrFechaFinPrestamo(String strFechaFinPrestamo) {
		this.strFechaFinPrestamo = strFechaFinPrestamo;
	}

	/**
	 * @return the strHoraFinPrestamo
	 */
	public String getStrHoraFinPrestamo() {
		if (strHoraFinPrestamo != null) {
            return strHoraFinPrestamo;
        }
        return (fechaFinPrestamo != null
                ? DateUtils.formatearHora(fechaFinPrestamo)
                : null);
	}

	/**
	 * @param strHoraFinPrestamo the strHoraFinPrestamo to set
	 */
	public void setStrHoraFinPrestamo(String strHoraFinPrestamo) {
		this.strHoraFinPrestamo = strHoraFinPrestamo;
	}
	
}

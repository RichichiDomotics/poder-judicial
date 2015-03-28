/**
 * Nombre del Programa : TurnoDTO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 26 Apr 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Objeto de transferencia para el turno
 * Programa Dependiente  :N/A
 * Programa Subsecuente :N/A
 * Cond. de ejecucion        :N/A
 * Dias de ejecucion          :N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                       :N/A
 * Compania               :N/A
 * Proyecto                 :N/A                                 Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.dto.expediente;

import org.apache.commons.lang.StringUtils;

import mx.gob.segob.nsjp.comun.enums.expediente.EstatusTurno;
import mx.gob.segob.nsjp.comun.enums.expediente.TipoTurno;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * Objeto de transferencia para el turno.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class TurnoDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2414141342810438266L;
	
	private Long turnoId;
    private String numeroTurno;
    private TipoTurno tipoTurno;
    private Boolean esUrgente;
    private String fichaAtencion;
    private EstatusTurno estado;
    private java.util.Date fechaAtencion;
    private String strFechaAtencion;
    private String strHoraAtencion;

    private ExpedienteDTO expediente;
    private UsuarioDTO usuario;	
	
    private String nombreCiudadano;
    private String apellidoPaternoCiudadano;
    private String apellidoMaternoCiudadano;
    
    private CatDiscriminanteDTO discriminante;

    
    /**
     * @param numeroTurno
     */
    public TurnoDTO() {
    }

    /**
     * 
     * @param turnoId
     */
    public TurnoDTO(Long turnoId) {
    	this.turnoId= turnoId;
    }
    
    /**
     * @param numeroTurno
     */
    public TurnoDTO(String numeroTurno) {
        this.numeroTurno = numeroTurno;
    }

    
    
    /**
     * Método de acceso al campo turnoId.
     * 
     * @return El valor del campo turnoId
     */
    public Long getTurnoId() {
        return turnoId;
    }
    /**
     * Asigna el valor al campo turnoId.
     * 
     * @param turnoId
     *            el valor turnoId a asignar
     */
    public void setTurnoId(Long turnoId) {
        this.turnoId = turnoId;
    }
    /**
     * Método de acceso al campo numeroTurno.
     * 
     * @return El valor del campo numeroTurno
     */
    public String getNumeroTurno() {
        return numeroTurno;
    }
    /**
     * Asigna el valor al campo numeroTurno.
     * 
     * @param numeroTurno
     *            el valor numeroTurno a asignar
     */
    public void setNumeroTurno(String numeroTurno) {
        this.numeroTurno = numeroTurno;
    }
    /**
     * Método de acceso al campo tipoTurno.
     * 
     * @return El valor del campo tipoTurno
     */
    public TipoTurno getTipoTurno() {
        return tipoTurno;
    }
    /**
     * Asigna el valor al campo tipoTurno.
     * 
     * @param tipoTurno
     *            el valor tipoTurno a asignar
     */
    public void setTipoTurno(TipoTurno tipoTurno) {
        this.tipoTurno = tipoTurno;
    }
    /**
     * Método de acceso al campo esUrgente.
     * 
     * @return El valor del campo esUrgente
     */
    public Boolean getEsUrgente() {
        return esUrgente;
    }
    /**
     * Asigna el valor al campo esUrgente.
     * 
     * @param esUrgente
     *            el valor esUrgente a asignar
     */
    public void setEsUrgente(Boolean esUrgente) {
        this.esUrgente = esUrgente;
    }
    /**
     * Método de acceso al campo fichaAtencion.
     * 
     * @return El valor del campo fichaAtencion
     */
    public String getFichaAtencion() {
        return fichaAtencion;
    }
    /**
     * Asigna el valor al campo fichaAtencion.
     * 
     * @param fichaAtencion
     *            el valor fichaAtencion a asignar
     */
    public void setFichaAtencion(String fichaAtencion) {
        this.fichaAtencion = fichaAtencion;
    }

	/**
	 * Método de acceso al campo estado.
	 * @return El valor del campo estado
	 */
	public EstatusTurno getEstado() {
		return estado;
	}

	/**
	 * Asigna el valor al campo estado.
	 * @param estado el valor estado a asignar
	 */
	public void setEstado(EstatusTurno estado) {
		this.estado = estado;
	}

	public ExpedienteDTO getExpediente() {
		return expediente;
	}

	public void setExpediente(ExpedienteDTO expedienteDTO) {
		this.expediente = expedienteDTO;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuarioDTO) {
		this.usuario = usuarioDTO;
	}

	public java.util.Date getFechaAtencion() {
		return fechaAtencion;
	}

	public void setFechaAtencion(java.util.Date fechaAtencion) {
		this.fechaAtencion = fechaAtencion;
	}

	public String getStrFechaAtencion() {
		if (strFechaAtencion != null) {
            return strFechaAtencion;
        }
        return (fechaAtencion != null
                ? DateUtils.formatear(fechaAtencion)
                : null);
	}

	public void setStrFechaAtencion(String strFechaAtencion) {
		this.strFechaAtencion = strFechaAtencion;
	}

	public String getStrHoraAtencion() {
		 if (strHoraAtencion != null) {
	            return strHoraAtencion;
	        }
	        return (fechaAtencion != null
	                ? DateUtils.formatearHora(fechaAtencion)
	                : null);
	}

	public void setStrHoraAtencion(String strHoraAtencion) {
		this.strHoraAtencion = strHoraAtencion;
	}

	/**
	 * Método de acceso al campo nombreCiudadano.
	 * @return El valor del campo nombreCiudadano
	 */
	public String getNombreCiudadano() {
		return nombreCiudadano;
	}

	/**
	 * Asigna el valor al campo nombreCiudadano.
	 * @param nombreCiudadano el valor nombreCiudadano a asignar
	 */
	public void setNombreCiudadano(String nombreCiudadano) {
		this.nombreCiudadano = nombreCiudadano;
	}

	/**
	 * Método de acceso al campo apellidoPaternoCiudadano.
	 * @return El valor del campo apellidoPaternoCiudadano
	 */
	public String getApellidoPaternoCiudadano() {
		return apellidoPaternoCiudadano;
	}

	/**
	 * Asigna el valor al campo apellidoPaternoCiudadano.
	 * @param apellidoPaternoCiudadano el valor apellidoPaternoCiudadano a asignar
	 */
	public void setApellidoPaternoCiudadano(String apellidoPaternoCiudadano) {
		this.apellidoPaternoCiudadano = apellidoPaternoCiudadano;
	}

	/**
	 * Método de acceso al campo apellidoMaternoCiudadano.
	 * @return El valor del campo apellidoMaternoCiudadano
	 */
	public String getApellidoMaternoCiudadano() {
		return apellidoMaternoCiudadano;
	}

	/**
	 * Asigna el valor al campo apellidoMaternoCiudadano.
	 * @param apellidoMaternoCiudadano el valor apellidoMaternoCiudadano a asignar
	 */
	public void setApellidoMaternoCiudadano(String apellidoMaternoCiudadano) {
		this.apellidoMaternoCiudadano = apellidoMaternoCiudadano;
	}
	/**
	 * 
	 * @return
	 */
	public String getNombreCompleto() {
		final StringBuffer nc = new StringBuffer();
        if (StringUtils.isNotBlank(nombreCiudadano)) {
            nc.append(nombreCiudadano).append(" ");
        }
        if (StringUtils.isNotBlank(apellidoPaternoCiudadano)) {
            nc.append(apellidoPaternoCiudadano).append(" ");
        }
        if (StringUtils.isNotBlank(apellidoMaternoCiudadano)) {
            nc.append(apellidoMaternoCiudadano);
        }
        return nc.toString().trim();
	}

	/**
	 * @return the discriminante
	 */
	public CatDiscriminanteDTO getDiscriminante() {
		return discriminante;
	}

	/**
	 * @param discriminante the discriminante to set
	 */
	public void setDiscriminante(CatDiscriminanteDTO discriminante) {
		this.discriminante = discriminante;
	}
}

/**
 * Nombre del Programa : SolicitudDTO.java
 * Autor                            : cesarAgustin
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 12 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : DTO para el traslado de atributos de Solicitud, entre la vista y los servicios
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
package mx.gob.segob.nsjp.dto.solicitud;

import java.util.LinkedList;
import java.util.List;

import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dto.archivo.SolicitudAdjuntosDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.configuracion.ConfInstitucionDTO;
import mx.gob.segob.nsjp.dto.documento.AcuseReciboDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.funcionarioexterno.FuncionarioExternoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;

/**
 * DTO para el traslado de atributos de Solicitud, entre la vista y los
 * servicios.
 * 
 * @version 1.0
 * @author cesarAgustin
 * 
 */
public class SolicitudDTO extends DocumentoDTO {

    /**
	 * 
	 */
    private static final long serialVersionUID = 705132372590963671L;
	//Si se modifica/agrega alguna tambien se sebe modificar en Solicitud  
    public final static String POR_ATENDER = "Solicitudes Por Atender";
	public final static String GENERADAS = "Solicitudes Generadas";
	public final static String TODAS = "Todas las Solicitudes";
	public final static String PARCIALES = "Documentos Parciales";
	public final static String NUMEROCASOASOCIADO = "cNumeroCasoAsociado";
	
    /**
     * @see mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes
     */
    
    private ValorDTO tipoSolicitudDTO;
    private Long areaOrigen;
    private Long areaDestino;
    private String numeroCasoAsociado;
    private java.util.Date fechaLimite;
    private ValorDTO estatus;
    private FuncionarioDTO usuarioSolicitante;
    private java.util.Date fechaModificacion;
    private java.util.Date fechaCierre;
    
    private String fechaLimiteStr;
    private String fechaModificacionStr;
    private String fechaCierreStr;
    
    private String motivo;
    private String strFechaLimite;
    private String strHoraLimite;
    private Long solicitanteExterno;
    private List<AcuseReciboDTO> acusesReciboDTO;
    /**
     * @deprecated Usar <code>expedienteDTO.numeroExpediente</code>
     */
    private InvolucradoDTO involucradoDTO;
    private Boolean esUrgente;
   
    /**
     * Campos a mostrar, estos campos se llenan dependiendo si es una solicitud externa o interna
     * con el resultado correspondiente
     */
    private String nombreInstitucionSolicitante;
    
    private String nombreSolicitanteExternoInterno;
    /**
     * Usado para el registro desde atencion tempra de PJ.
     */
    private String nombreSolicitante;
    /**
     * Institucion
     */
    private ConfInstitucionDTO institucion;
    
    
    private FuncionarioDTO destinatario;
    /**
     * Folio de la solicitud.<br>
     * Se deben mostrar en todas las bandejas de solicitudes.
     */
    private String folioSolicitud;

    private List<SolicitudAdjuntosDTO> archivosAdjuntos = new LinkedList<SolicitudAdjuntosDTO>();
    
    private String asuntoSolicitud;
    
    private String observaciones;
    
    private String numCarpetaEjecucion;
    
    private String numCausa;
    
    private FuncionarioExternoDTO destinatarioInstExterna;
    
    private FuncionarioExternoDTO solicitanteInstExterna;
    
    public SolicitudDTO(Long idSolicitud) {
		super();
		setDocumentoId(idSolicitud);
	}
	
    public SolicitudDTO() {
		super();
	}
	
	public String getMotivo() {
        return motivo;
    }
    
	public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    /**
     * M�todo de acceso al campo tipoSolicitudDTO.
     * 
     * @return El valor del campo tipoSolicitudDTO
     */
    public ValorDTO getTipoSolicitudDTO() {
        return tipoSolicitudDTO;
    }
    /**
     * Asigna el valor al campo tipoSolicitudDTO.
     * 
     * @param tipoSolicitudDTO
     *            el valor tipoSolicitudDTO a asignar
     */
    public void setTipoSolicitudDTO(ValorDTO tipoSolicitudDTO) {
        this.tipoSolicitudDTO = tipoSolicitudDTO;
    }
    /**
     * M�todo de acceso al campo areaOrigen.
     * 
     * @return El valor del campo areaOrigen
     */
    public Long getAreaOrigen() {
        return areaOrigen;
    }
    /**
     * Asigna el valor al campo areaOrigen.
     * 
     * @param areaOrigen
     *            el valor areaOrigen a asignar
     */
    public void setAreaOrigen(Long areaOrigen) {
        this.areaOrigen = areaOrigen;
    }
    /**
     * M�todo de acceso al campo areaDestino.
     * 
     * @return El valor del campo areaDestino
     */
    public Long getAreaDestino() {
        return areaDestino;
    }
    /**
     * Asigna el valor al campo areaDestino.
     * 
     * @param areaDestino
     *            el valor areaDestino a asignar
     */
    public void setAreaDestino(Long areaDestino) {
        this.areaDestino = areaDestino;
    }


    /**
     * M�todo de acceso al campo numeroCasoAsociado.
     * 
     * @return El valor del campo numeroCasoAsociado
     */
    public String getNumeroCasoAsociado() {
        return numeroCasoAsociado;
    }
    /**
     * Asigna el valor al campo numeroCasoAsociado.
     * 
     * @param numeroCasoAsociado
     *            el valor numeroCasoAsociado a asignar
     */
    public void setNumeroCasoAsociado(String numeroCasoAsociado) {
        this.numeroCasoAsociado = numeroCasoAsociado;
    }

    /**
     * M�todo de acceso al campo cestatus.
     * 
     * @return El valor del campo cestatus
     */
    public ValorDTO getEstatus() {
        return estatus;
    }
    /**
     * Asigna el valor al campo cestatus.
     * 
     * @param cestatus
     *            el valor cestatus a asignar
     */
    public void setEstatus(ValorDTO estatus) {
        this.estatus = estatus;
    }
    /**
     * M�todo de acceso al campo fecha limite o fecha de vencimiento
     * 
     * @return fechaLimite
     */
    public java.util.Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(java.util.Date fechaLimite) {
    	this.setFechaLimiteStr(DateUtils.formatear(fechaLimite));    	
        this.fechaLimite = fechaLimite;
    }
    public FuncionarioDTO getUsuarioSolicitante() {
        return usuarioSolicitante;
    }
    public void setUsuarioSolicitante(FuncionarioDTO usuarioSolicitante) {
        this.usuarioSolicitante = usuarioSolicitante;
    }
    public java.util.Date getFechaModificacion() {
        return fechaModificacion;
    }
    public void setFechaModificacion(java.util.Date fechaModificacion) {
    	this.setFechaModificacionStr(DateUtils.formatear(fechaModificacion));
        this.fechaModificacion = fechaModificacion;
    }
    public java.util.Date getFechaCierre() {
    	this.setFechaCierreStr(DateUtils.formatear(fechaCierre));
        return fechaCierre;
    }
    public void setFechaCierre(java.util.Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    /**
     * M�todo de acceso al campo strFechaLimite.
     * 
     * @return El valor del campo strFechaLimite
     */
    public String getStrFechaLimite() {
        return strFechaLimite;
    }
    /**
     * Asigna el valor al campo strFechaLimite.
     * 
     * @param strFechaLimite
     *            el valor strFechaLimite a asignar
     */
    public void setStrFechaLimite(String strFechaLimite) {
        this.strFechaLimite = strFechaLimite;
    }
    /**
     * M�todo de acceso al campo strHoraLimite.
     * 
     * @return El valor del campo strHoraLimite
     */
    public String getStrHoraLimite() {
        return strHoraLimite;
    }
    /**
     * Asigna el valor al campo strHoraLimite.
     * 
     * @param strHoraLimite
     *            el valor strHoraLimite a asignar
     */
    public void setStrHoraLimite(String strHoraLimite) {
        this.strHoraLimite = strHoraLimite;
    }
    /**
     * M�todo de acceso al campo nombreSolicitante.
     * 
     * @return El valor del campo nombreSolicitante
     */
    public String getNombreSolicitante() {
        return nombreSolicitante;
    }
    /**
     * Asigna el valor al campo nombreSolicitante.
     * 
     * @param nombreSolicitante
     *            el valor nombreSolicitante a asignar
     */
    public void setNombreSolicitante(String nombreSolicitante) {
        this.nombreSolicitante = nombreSolicitante;
    }
    /**
     * M�todo de acceso al campo institucion.
     * @return El valor del campo institucion
     */
    public ConfInstitucionDTO getInstitucion() {
        return institucion;
    }
    /**
     * Asigna el valor al campo institucion.
     * @param institucion el valor institucion a asignar
     */
    public void setInstitucion(ConfInstitucionDTO institucion) {
        this.institucion = institucion;
    }
	/**
	 * M�todo de acceso al campo solicitanteExterno.
	 * @return El valor del campo solicitanteExterno
	 */
	public Long getSolicitanteExterno() {
		return solicitanteExterno;
	}
	/**
	 * Asigna el valor al campo solicitanteExterno.
	 * @param solicitanteExterno el valor solicitanteExterno a asignar
	 */
	public void setSolicitanteExterno(Long solicitanteExterno) {
		this.solicitanteExterno = solicitanteExterno;
	}
	/**
	 * M�todo de acceso al campo nombreInstitucionSolicitante.
	 * @return El valor del campo nombreInstitucionSolicitante
	 */
	public String getNombreInstitucionSolicitante() {
		return nombreInstitucionSolicitante;
	}
	/**
	 * Asigna el valor al campo nombreInstitucionSolicitante.
	 * @param nombreInstitucionSolicitante el valor nombreInstitucionSolicitante a asignar
	 */
	public void setNombreInstitucionSolicitante(String nombreInstitucionSolicitante) {
		this.nombreInstitucionSolicitante = nombreInstitucionSolicitante;
	}
	/**
	 * M�todo de acceso al campo nombreSolicitanteExternoInterno.
	 * @return El valor del campo nombreSolicitanteExternoInterno
	 */
	public String getNombreSolicitanteExternoInterno() {
		return nombreSolicitanteExternoInterno;
	}
	/**
	 * Asigna el valor al campo nombreSolicitanteExternoInterno.
	 * @param nombreSolicitanteExternoInterno el valor nombreSolicitanteExternoInterno a asignar
	 */
	public void setNombreSolicitanteExternoInterno(
			String nombreSolicitanteExternoInterno) {
		this.nombreSolicitanteExternoInterno = nombreSolicitanteExternoInterno;
	}
	/**
	 * Asigna el valor al campo acusesReciboDTO.
	 * @param acusesReciboDTO el valor acusesReciboDTO a asignar
	 */
	public void setAcusesReciboDTO(List<AcuseReciboDTO> acusesReciboDTO) {
		this.acusesReciboDTO = acusesReciboDTO;
	}
	/**
	 * M�todo de acceso al campo acusesReciboDTO.
	 * @return El valor del campo acusesReciboDTO
	 */
	public List<AcuseReciboDTO> getAcusesReciboDTO() {
		return acusesReciboDTO;
	}
	/**
	 * M�todo de acceso al campo involucradoDTO.
	 * @return El valor del campo involucradoDTO
	 */
	public InvolucradoDTO getInvolucradoDTO() {
		return involucradoDTO;
	}
	/**
	 * Asigna el valor al campo involucradoDTO.
	 * @param involucradoDTO el valor involucradoDTO a asignar
	 */
	public void setInvolucradoDTO(InvolucradoDTO involucradoDTO) {
		this.involucradoDTO = involucradoDTO;
	}
	/**
	 * M�todo de acceso al campo esUrgente.
	 * @return El valor del campo esUrgente
	 */
	public Boolean getEsUrgente() {
		return esUrgente;
	}
	/**
	 * Asigna el valor al campo esUrgente.
	 * @param esUrgente el valor esUrgente a asignar
	 */
	public void setEsUrgente(Boolean esUrgente) {
		this.esUrgente = esUrgente;
	}

	/**
	 * @return the archivosAdjuntos
	 */
	public List<SolicitudAdjuntosDTO> getArchivosAdjuntos() {
		return archivosAdjuntos;
	}

	/**
	 * @param archivosAdjuntos the archivosAdjuntos to set
	 */
	public void setArchivosAdjuntos(List<SolicitudAdjuntosDTO> archivosAdjuntos) {
		this.archivosAdjuntos = archivosAdjuntos;
	}

	
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * M�todo de acceso al campo destinatario.
	 * @return El valor del campo destinatario
	 */
	public FuncionarioDTO getDestinatario() {
		return destinatario;
	}

	/**
	 * Asigna el valor al campo destinatario.
	 * @param destinatario el valor destinatario a asignar
	 */
	public void setDestinatario(FuncionarioDTO destinatario) {
		this.destinatario = destinatario;
	}

    /**
     * M�todo de acceso al campo folioSolicitud.
     * @return El valor del campo folioSolicitud
     */
    public String getFolioSolicitud() {
        return folioSolicitud;
    }

    /**
     * Asigna el valor al campo folioSolicitud.
     * @param folioSolicitud el valor folioSolicitud a asignar
     */
    public void setFolioSolicitud(String folioSolicitud) {
        this.folioSolicitud = folioSolicitud;
    }

	public String getFechaLimiteStr() {
		return fechaLimiteStr;
	}

	public void setFechaLimiteStr(String fechaLimiteStr) {
		this.fechaLimiteStr = fechaLimiteStr;
	}

	public String getFechaModificacionStr() {
		return fechaModificacionStr;
	}

	public void setFechaModificacionStr(String fechaModificacionStr) {
		this.fechaModificacionStr = fechaModificacionStr;
	}

	public String getFechaCierreStr() {
		return fechaCierreStr;
	}

	public void setFechaCierreStr(String fechaCierreStr) {
		this.fechaCierreStr = fechaCierreStr;
	}

	public String getAsuntoSolicitud() {
		return asuntoSolicitud;
	}

	public void setAsuntoSolicitud(String asuntoSolicitud) {
		this.asuntoSolicitud = asuntoSolicitud;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * M�todo de acceso al campo numCarpetaEjecucion.
	 * @return El valor del campo numCarpetaEjecucion
	 */
	public String getNumCarpetaEjecucion() {
		return numCarpetaEjecucion;
	}

	/**
	 * Asigna el valor al campo numCarpetaEjecucion.
	 * @param numCarpetaEjecucion el valor numCarpetaEjecucion a asignar
	 */
	public void setNumCarpetaEjecucion(String numCarpetaEjecucion) {
		this.numCarpetaEjecucion = numCarpetaEjecucion;
	}

	/**
	 * M�todo de acceso al campo numCausa.
	 * @return El valor del campo numCausa
	 */
	public String getNumCausa() {
		return numCausa;
	}

	/**
	 * Asigna el valor al campo numCausa.
	 * @param numCausa el valor numCausa a asignar
	 */
	public void setNumCausa(String numCausa) {
		this.numCausa = numCausa;
	}

	/**
	 * Método de acceso al campo destinatarioInstExterna.
	 * @return El valor del campo destinatarioInstExterna
	 */
	public FuncionarioExternoDTO getDestinatarioInstExterna() {
		return destinatarioInstExterna;
	}

	/**
	 * Asigna el valor al campo destinatarioInstExterna.
	 * @param destinatarioInstExterna el valor destinatarioInstExterna a asignar
	 */
	public void setDestinatarioInstExterna(
			FuncionarioExternoDTO destinatarioInstExterna) {
		this.destinatarioInstExterna = destinatarioInstExterna;
	}

	/**
	 * Método de acceso al campo solicitanteInstExterna.
	 * @return El valor del campo solicitanteInstExterna
	 */
	public FuncionarioExternoDTO getSolicitanteInstExterna() {
		return solicitanteInstExterna;
	}

	/**
	 * Asigna el valor al campo solicitanteInstExterna.
	 * @param solicitanteInstExterna el valor solicitanteInstExterna a asignar
	 */
	public void setSolicitanteInstExterna(
			FuncionarioExternoDTO solicitanteInstExterna) {
		this.solicitanteInstExterna = solicitanteInstExterna;
	}
}

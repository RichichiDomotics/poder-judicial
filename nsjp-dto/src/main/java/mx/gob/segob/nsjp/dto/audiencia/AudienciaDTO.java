/**
 * Nombre del Programa : AudienciaDTO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 30 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Objeto de Transferencia para la audiencia
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
package mx.gob.segob.nsjp.dto.audiencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.dto.resolutivo.ResolutivoDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaDTO;

/**
 * Objeto de Transferencia para la audiencia.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class AudienciaDTO extends EventoDTO {

    private ValorDTO tipoAudiencia;
    private Date fechaAsignacionSala;
    private SalaAudienciaDTO sala;
    private List<FuncionarioDTO> funcionarios = new ArrayList<FuncionarioDTO>();
    private Integer duracionEstimada;
    private SolicitudAudienciaDTO solicitud;
    private List<ObjetoDTO> evidencias = new ArrayList<ObjetoDTO>();
    private ValorDTO estatusAudiencia;
    private boolean conResolutivos;
    private List<ResolutivoDTO> resolutivos = new ArrayList<ResolutivoDTO>();
    private ExpedienteDTO causa;
    private ExpedienteDTO tocaOrCarpeta;
    private InvolucradoDTO sentenciado;
    private Short consecutivo;
    private String situacionesEspeciales;
    private Date fechaFiltroInicio;
    private Date fechaFiltroFin;
    private String numeroCausa;
    private String numeroTocaOrCarpeta;
    private String strFechaInicio;
    private String strFechaFin;
    private String strHoraInicio;
    private String strHoraFin;
    private String folioAudiencia;
    private SalaTemporalDTO salaTemporal;
    private Boolean esReprogramacionDeAudiencia = false;
    
    //Permite filtrar las audiencias programadas por Distrito
    private Long idDistritoFiltroAudiencias;
    
    /**
     * 
     * @param idAudiencia
     */
    public AudienciaDTO(Long idAudiencia) {
    	super(idAudiencia);
	}
    /**
     * 
     * @param folioAud
     */
    public AudienciaDTO(String folioAud) {
        super();
        this.folioAudiencia = folioAud;
    }
	public AudienciaDTO() {
		
	}

	/**
     * 
     * @param nuevoJuez
     */
    public void addFuncionario(FuncionarioDTO nuevoJuez) {
        if (funcionarios == null) {
            funcionarios = new ArrayList<FuncionarioDTO>();
        }
        funcionarios.add(nuevoJuez);
    }

    /**
     * 
     * @param evi
     */
    public void addEvidencia(ObjetoDTO evi) {
        if (evidencias == null) {
            evidencias = new ArrayList<ObjetoDTO>();
        }
        evidencias.add(evi);
    }

    /**
     * Método de acceso al campo tipoAudiencia.
     * 
     * @return El valor del campo tipoAudiencia
     */
    public ValorDTO getTipoAudiencia() {
        return tipoAudiencia;
    }

    /**
     * Asigna el valor al campo tipoAudiencia.
     * 
     * @param tipoAudiencia
     *            el valor tipoAudiencia a asignar
     */
    public void setTipoAudiencia(ValorDTO tipoAudiencia) {
        this.tipoAudiencia = tipoAudiencia;
    }

    /**
     * Método de acceso al campo fechaAsignacionSala.
     * 
     * @return El valor del campo fechaAsignacionSala
     */
    public Date getFechaAsignacionSala() {
        return fechaAsignacionSala;
    }

    /**
     * Asigna el valor al campo fechaAsignacionSala.
     * 
     * @param fechaAsignacionSala
     *            el valor fechaAsignacionSala a asignar
     */
    public void setFechaAsignacionSala(Date fechaAsignacionSala) {
        this.fechaAsignacionSala = fechaAsignacionSala;
    }

    /**
     * Método de acceso al campo jueces.
     * 
     * @return El valor del campo jueces
     */
    public List<FuncionarioDTO> getFuncionarios() {
        return funcionarios;
    }

    /**
     * Asigna el valor al campo jueces.
     * 
     * @param jueces
     *            el valor jueces a asignar
     */
    public void setFuncionarios(List<FuncionarioDTO> jueces) {
        this.funcionarios = jueces;
    }

    /**
     * Método de acceso al campo sala.
     * 
     * @return El valor del campo sala
     */
    public SalaAudienciaDTO getSala() {
        return sala;
    }

    /**
     * Asigna el valor al campo sala.
     * 
     * @param sala
     *            el valor sala a asignar
     */
    public void setSala(SalaAudienciaDTO sala) {
        this.sala = sala;
    }

    /**
     * Método de acceso al campo duracionEstimada.
     * 
     * @return El valor del campo duracionEstimada
     */
    public Integer getDuracionEstimada() {
        return duracionEstimada;
    }

    /**
     * Asigna el valor al campo duracionEstimada.
     * 
     * @param duracionEstimada
     *            el valor duracionEstimada a asignar
     */
    public void setDuracionEstimada(Integer duracionEstimada) {
        this.duracionEstimada = duracionEstimada;
    }

    /**
     * Método de acceso al campo solicitud.
     * 
     * @return El valor del campo solicitud
     */
    public SolicitudAudienciaDTO getSolicitud() {
        return solicitud;
    }

    /**
     * Asigna el valor al campo solicitud.
     * 
     * @param solicitud
     *            el valor solicitud a asignar
     */
    public void setSolicitud(SolicitudAudienciaDTO solicitud) {
        this.solicitud = solicitud;
    }

    /**
     * Método de acceso al campo evidencias.
     * 
     * @return El valor del campo evidencias
     */
    public List<ObjetoDTO> getEvidencias() {
        return evidencias;
    }

    /**
     * Asigna el valor al campo evidencias.
     * 
     * @param evidencias
     *            el valor evidencias a asignar
     */
    public void setEvidencias(List<ObjetoDTO> evidencias) {
        this.evidencias = evidencias;
    }

    /**
     * Asigna el valor al campo estatusAudiencia.
     * @param estatusAudiencia el valor estatusAudiencia a asignar
     */
    public void setEstatusAudiencia(ValorDTO estatusAudiencia) {
        this.estatusAudiencia = estatusAudiencia;
    }

    /**
     * Método de acceso al campo estatusAudiencia.
     * @return El valor del campo estatusAudiencia
     */
    public ValorDTO getEstatusAudiencia() {
        return estatusAudiencia;
    }

    /**
     * Asigna el valor al campo resolutivos.
     * @param resolutivos el valor resolutivos a asignar
     */
    public void setResolutivos(List<ResolutivoDTO> resolutivos) {
        this.resolutivos = resolutivos;
    }

    /**
     * Método de acceso al campo resolutivos.
     * @return El valor del campo resolutivos
     */
    public List<ResolutivoDTO> getResolutivos() {
        return resolutivos;
    }

	/**
     * Asigna el valor al campo conResolutivos.
     * @param conResolutivos el valor conResolutivos a asignar
     */
    public void setConResolutivos(boolean conResolutivos) {
        this.conResolutivos = conResolutivos;
    }

    /**
     * Método de acceso al campo conResolutivos.
     * @return El valor del campo conResolutivos
     */
    public boolean isConResolutivos() {
        return conResolutivos;
    }

	/**
	 * Método de acceso al campo causa.
	 * @return El valor del campo causa
	 */
	public ExpedienteDTO getCausa() {
		return causa;
	}

	/**
	 * Asigna el valor al campo causa.
	 * @param causa el valor causa a asignar
	 */
	public void setCausa(ExpedienteDTO causa) {
		this.causa = causa;
	}

	/**
	 * Método de acceso al campo tocaOrCarpeta.
	 * @return El valor del campo tocaOrCarpeta
	 */
	public ExpedienteDTO getTocaOrCarpeta() {
		return tocaOrCarpeta;
	}

	/**
	 * Asigna el valor al campo tocaOrCarpeta.
	 * @param tocaOrCarpeta el valor tocaOrCarpeta a asignar
	 */
	public void setTocaOrCarpeta(ExpedienteDTO tocaOrCarpeta) {
		this.tocaOrCarpeta = tocaOrCarpeta;
	}

	/**
	 * Método de acceso al campo sentenciado.
	 * @return El valor del campo sentenciado
	 */
	public InvolucradoDTO getSentenciado() {
		return sentenciado;
	}

	/**
	 * Asigna el valor al campo sentenciado.
	 * @param sentenciado el valor sentenciado a asignar
	 */
	public void setSentenciado(InvolucradoDTO sentenciado) {
		this.sentenciado = sentenciado;
	}

	/**
	 * Método de acceso al campo consecutivo.
	 * @return El valor del campo consecutivo
	 */
	public Short getConsecutivo() {
		return consecutivo;
	}

	/**
	 * Asigna el valor al campo consecutivo.
	 * @param consecutivo el valor consecutivo a asignar
	 */
	public void setConsecutivo(Short consecutivo) {
		this.consecutivo = consecutivo;
	}

	/**
	 * Método de acceso al campo situacionesEspeciales.
	 * @return El valor del campo situacionesEspeciales
	 */
	public String getSituacionesEspeciales() {
		return situacionesEspeciales;
	}

	/**
	 * Asigna el valor al campo situacionesEspeciales.
	 * @param situacionesEspeciales el valor situacionesEspeciales a asignar
	 */
	public void setSituacionesEspeciales(String situacionesEspeciales) {
		this.situacionesEspeciales = situacionesEspeciales;
	}

	/**
	 * Método de acceso al campo fechaFiltroInicio.
	 * @return El valor del campo fechaFiltroInicio
	 */
	public Date getFechaFiltroInicio() {
		return fechaFiltroInicio;
	}

	/**
	 * Asigna el valor al campo fechaFiltroInicio.
	 * @param fechaFiltroInicio el valor fechaFiltroInicio a asignar
	 */
	public void setFechaFiltroInicio(Date fechaFiltroInicio) {
		this.fechaFiltroInicio = fechaFiltroInicio;
	}

	/**
	 * Método de acceso al campo fechaFiltroFin.
	 * @return El valor del campo fechaFiltroFin
	 */
	public Date getFechaFiltroFin() {
		return fechaFiltroFin;
	}

	/**
	 * Asigna el valor al campo fechaFiltroFin.
	 * @param fechaFiltroFin el valor fechaFiltroFin a asignar
	 */
	public void setFechaFiltroFin(Date fechaFiltroFin) {
		this.fechaFiltroFin = fechaFiltroFin;
	}

	/**
	 * Método de acceso al campo numeroCausa.
	 * @return El valor del campo numeroCausa
	 */
	public String getNumeroCausa() {
		return numeroCausa;
	}

	/**
	 * Asigna el valor al campo numeroCausa.
	 * @param numeroCausa el valor numeroCausa a asignar
	 */
	public void setNumeroCausa(String numeroCausa) {
		this.numeroCausa = numeroCausa;
	}

	/**
	 * Método de acceso al campo numeroTocaOrCarpeta.
	 * @return El valor del campo numeroTocaOrCarpeta
	 */
	public String getNumeroTocaOrCarpeta() {
		return numeroTocaOrCarpeta;
	}

	/**
	 * Asigna el valor al campo numeroTocaOrCarpeta.
	 * @param numeroTocaOrCarpeta el valor numeroTocaOrCarpeta a asignar
	 */
	public void setNumeroTocaOrCarpeta(String numeroTocaOrCarpeta) {
		this.numeroTocaOrCarpeta = numeroTocaOrCarpeta;
	}

	/**
	 * Método de acceso al campo strFechaInicio.
	 * @return El valor del campo strFechaInicio
	 */
	public String getStrFechaInicio() {
		return strFechaInicio;
	}

	/**
	 * Asigna el valor al campo strFechaInicio.
	 * @param strFechaInicio el valor strFechaInicio a asignar
	 */
	public void setStrFechaInicio(String strFechaInicio) {
		this.strFechaInicio = strFechaInicio;
	}

	/**
	 * Método de acceso al campo strFechaFin.
	 * @return El valor del campo strFechaFin
	 */
	public String getStrFechaFin() {
		return strFechaFin;
	}

	/**
	 * Asigna el valor al campo strFechaFin.
	 * @param strFechaFin el valor strFechaFin a asignar
	 */
	public void setStrFechaFin(String strFechaFin) {
		this.strFechaFin = strFechaFin;
	}

	/**
	 * Método de acceso al campo strHoraInicio.
	 * @return El valor del campo strHoraInicio
	 */
	public String getStrHoraInicio() {
		return strHoraInicio;
	}

	/**
	 * Asigna el valor al campo strHoraInicio.
	 * @param strHoraInicio el valor strHoraInicio a asignar
	 */
	public void setStrHoraInicio(String strHoraInicio) {
		this.strHoraInicio = strHoraInicio;
	}

	/**
	 * Método de acceso al campo strHoraFin.
	 * @return El valor del campo strHoraFin
	 */
	public String getStrHoraFin() {
		return strHoraFin;
	}

	/**
	 * Asigna el valor al campo strHoraFin.
	 * @param strHoraFin el valor strHoraFin a asignar
	 */
	public void setStrHoraFin(String strHoraFin) {
		this.strHoraFin = strHoraFin;
	}

    /**
     * Método de acceso al campo folioAudiencia.
     * @return El valor del campo folioAudiencia
     */
    public String getFolioAudiencia() {
        return folioAudiencia;
    }

    /**
     * Asigna el valor al campo folioAudiencia.
     * @param folioAudiencia el valor folioAudiencia a asignar
     */
    public void setFolioAudiencia(String folioAudiencia) {
        this.folioAudiencia = folioAudiencia;
    }
    
	/**
	 * @return metodo de acceso al campo salaTemporal.
	 */
	public SalaTemporalDTO getSalaTemporal() {
		return salaTemporal;
	}
	/**
	 * Asigna el valor al campo salaTemporal.
	 * @param salaTemporal the salaTemporal to set
	 */
	public void setSalaTemporal(SalaTemporalDTO salaTemporal) {
		this.salaTemporal = salaTemporal;
	}
	
	 /**
     * Permite saber si desde vista se quiere reprogramar una audiencia
     * @return
     */
	public Boolean getEsReprogramacionDeAudiencia() {
		return esReprogramacionDeAudiencia;
	}
	public void setEsReprogramacionDeAudiencia(Boolean esReprogramacionDeAudiencia) {
		this.esReprogramacionDeAudiencia = esReprogramacionDeAudiencia;
	}
	public Long getIdDistritoFiltroAudiencias() {
		return idDistritoFiltroAudiencias;
	}
	public void setIdDistritoFiltroAudiencias(Long idDistritoFiltroAudiencias) {
		this.idDistritoFiltroAudiencias = idDistritoFiltroAudiencias;
	}	
	
}

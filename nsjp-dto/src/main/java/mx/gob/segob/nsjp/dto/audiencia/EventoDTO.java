/**
 * Nombre del Programa : EventoDTO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 30 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Objeto de Transferencia para el evento
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

import mx.gob.segob.nsjp.comun.enums.audiencia.BandejaNotificador;
import mx.gob.segob.nsjp.comun.enums.audiencia.Eventos;
import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoViewDTO;

/**
 * Objeto de Transferencia para el evento.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class EventoDTO extends GenericDTO {
	
    private Long id;
    private ExpedienteDTO expediente;
    private Eventos tipoEvento;
    private ValorDTO tipo;
    private Date fechaSolicitud;
    private Date fechaEvento;
    private Date fechaHoraFin;
    private String caracter;
    private String lugarEvento;
    private String domicilioEvento;
    private String ubicacionEvento;
    private ValorDTO estatusAudiencia;

	/**
     * Campo usado en el filtro par aidentificar la bandeja.
     */
    private BandejaNotificador bandeja;
    
    /**
     * Involucrados a los que se les debe notificar del evento.
     */
    private List<InvolucradoDTO> involucrados = new ArrayList<InvolucradoDTO>();

    
    /**
     * Funcionarios a los que se les debe notificar del evento.
     */
    private List<FuncionarioDTO> funcionarios = new ArrayList<FuncionarioDTO>();

    
    /**
     * Involucrados a los que se les debe notificar del evento.
     */
    private List<InvolucradoViewDTO> involucradosView = new ArrayList<InvolucradoViewDTO>();

    
    public EventoDTO() {
        super();
    }
    
    public EventoDTO(Long id) {
        this.id = id;
    }
    

	/**
     * Obtiene los involucrado de acuerdo a una calidad especifica
     * 
     * @param calidad
     * @return
     */
    public List<InvolucradoDTO> getInvolucradoByCalidad(Calidades calidad) {
        final List<InvolucradoDTO> involucradosByCalidad = new ArrayList<InvolucradoDTO>();
        for (InvolucradoDTO involucradoDTO : involucrados) {        	
            if ((involucradoDTO.getCalidadDTO().getValorIdCalidad().getIdCampo()
                    .equals(calidad.getValorId()))
                    || (calidad.equals(Calidades.VICTIMA_PERSONA)
                            && involucradoDTO.getCalidadDTO()
                                    .getValorIdCalidad().getIdCampo()
                                    .equals(Calidades.DENUNCIANTE.getValorId()) && involucradoDTO
                            .getCondicion().intValue() == 1)) {
                involucradosByCalidad.add(involucradoDTO);
            }
        }
        return involucradosByCalidad;
    }

    /**
     * Método de acceso al campo id.
     * 
     * @return El valor del campo id
     */
    public Long getId() {
        return id;
    }
    /**
     * Asigna el valor al campo id.
     * 
     * @param id
     *            el valor id a asignar
     */
    public void setId(Long id) {
        this.id = id;
    }

    public void addInvolucrado(InvolucradoDTO inv) {
        if (involucrados == null) {
            involucrados = new ArrayList<InvolucradoDTO>();
        }
        involucrados.add(inv);
    }

    /**
     * Método de acceso al campo expediente.
     * 
     * @return El valor del campo expediente
     */
    public ExpedienteDTO getExpediente() {
        return expediente;
    }
    /**
     * Asigna el valor al campo expediente.
     * 
     * @param expediente
     *            el valor expediente a asignar
     */
    public void setExpediente(ExpedienteDTO expediente) {
        this.expediente = expediente;
    }
    /**
     * Método de acceso al campo tipo.
     * 
     * @return El valor del campo tipo
     */
    public Eventos getTipoEvento() {
        return tipoEvento;
    }
    /**
     * Asigna el valor al campo tipo.
     * 
     * @param tipo
     *            el valor tipo a asignar
     */
    public void setTipoEvento(Eventos tipo) {
        this.tipoEvento = tipo;
    }
    /**
     * Método de acceso al campo objetivo.
     * 
     * @return El valor del campo objetivo
     */
    public ValorDTO getTipo() {
        return tipo;
    }
    /**
     * Asigna el valor al campo objetivo.
     * 
     * @param objetivo
     *            el valor objetivo a asignar
     */
    public void setTipo(ValorDTO objetivo) {
        this.tipo = objetivo;
    }
    /**
     * Método de acceso al campo fechaSolicitud.
     * 
     * @return El valor del campo fechaSolicitud
     */
    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }
    /**
     * Asigna el valor al campo fechaSolicitud.
     * 
     * @param fechaSolicitud
     *            el valor fechaSolicitud a asignar
     */
    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }
    /**
     * Método de acceso al campo fechaEvento.
     * 
     * @return El valor del campo fechaEvento
     */
    public Date getFechaEvento() {
        return fechaEvento;
    }
    /**
     * Asigna el valor al campo fechaEvento.
     * 
     * @param fechaEvento
     *            el valor fechaEvento a asignar
     */
    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }
    /**
     * Asigna el valor al campo fechaHoraFin.
     * 
     * @param fechaHoraFin
     *            el valor fechaHoraFin a asignar
     */
    public void setFechaHoraFin(Date fechaHoraFin) {
		this.fechaHoraFin = fechaHoraFin;
	}

    /**
     * Método de acceso al campo fechaHoraFin.
     * 
     * @return El valor del campo fechaHoraFin
     */
    public Date getFechaHoraFin() {
		return fechaHoraFin;
	}

	/**
     * Método de acceso al campo involucrados.
     * 
     * @return El valor del campo involucrados
     */
    public List<InvolucradoDTO> getInvolucrados() {
        return involucrados;
    }
    /**
     * Asigna el valor al campo involucrados.
     * 
     * @param involucrados
     *            el valor involucrados a asignar
     */
    public void setInvolucrados(List<InvolucradoDTO> involucrados) {
        this.involucrados = involucrados;
    }

    public void addFuncionariol(FuncionarioDTO func) {
        if (funcionarios == null) {
        	funcionarios = new ArrayList<FuncionarioDTO>();
        }
        funcionarios.add(func);
    }
    
    /**
	 * Regresa el valor de la propiedad funcionarios
	 * @return the funcionarios
	 */
	public List<FuncionarioDTO> getFuncionarios() {
		return funcionarios;
	}

	
	/**
	 * Establece el valor de la propiedad funcionarios
	 * @param funcionarios valo funcionarios a almacenar
	 */
	public void setFuncionarios(List<FuncionarioDTO> funcionarios) {
		this.funcionarios = funcionarios;
	}

	/**
	 * Establece el valor de la propiedad involucradosView
	 * @param involucradosView valo involucradosView a almacenar
	 */
	public void setInvolucradosView(List<InvolucradoViewDTO> involucradosView) {
		this.involucradosView = involucradosView;
	}

	/**
	 * Regresa el valor de la propiedad involucradosView
	 * @return the involucradosView
	 */
	public List<InvolucradoViewDTO> getInvolucradosView() {
		return involucradosView;
	}

	/**
     * Método de acceso al campo bandeja.
     * 
     * @return El valor del campo bandeja
     */
    public BandejaNotificador getBandeja() {
        return bandeja;
    }
    /**
     * Asigna el valor al campo bandeja.
     * 
     * @param bandeja
     *            el valor bandeja a asignar
     */
    public void setBandeja(BandejaNotificador bandeja) {
        this.bandeja = bandeja;
    }
    /**
     * Método de acceso al campo caracter.
     * 
     * @return El valor del campo caracter
     */
    public String getCaracter() {
        return caracter;
    }
    /**
     * Asigna el valor al campo caracter.
     * 
     * @param caracter
     *            el valor caracter a asignar
     */
    public void setCaracter(String caracter) {
        this.caracter = caracter;
    }
    /**
     * Método de acceso al campo lugarEvento.
     * 
     * @return El valor del campo lugarEvento
     */
    public String getLugarEvento() {
        return lugarEvento;
    }
    /**
     * Asigna el valor al campo lugarEvento.
     * 
     * @param lugarEvento
     *            el valor lugarEvento a asignar
     */
    public void setLugarEvento(String lugarEvento) {
        this.lugarEvento = lugarEvento;
    }
    /**
     * Método de acceso al campo direccionEvento.
     * 
     * @return El valor del campo direccionEvento
     */
    public String getDomicilioEvento() {
        return domicilioEvento;
    }
    /**
     * Asigna el valor al campo direccionEvento.
     * 
     * @param direccionEvento
     *            el valor direccionEvento a asignar
     */
    public void setDomicilioEvento(String direccionEvento) {
        this.domicilioEvento = direccionEvento;
    }
    /**
     * Método de acceso al campo strFechaEvento.
     * 
     * @return El valor del campo strFechaEvento
     */
    public String getStrFechaEvento() {
        if (this.fechaEvento != null) {
        	return DateUtils.formatear(this.fechaEvento);
        }
        return null;
    }
    /**
     * Método de acceso al campo strHoraEvento.
     * 
     * @return El valor del campo strHoraEvento
     */
    public String getStrHoraEvento() {
        if (this.fechaEvento != null) {
            return DateUtils.formatearHora(this.fechaEvento);
        }
        return null;
    }
    /**
     * Método de acceso al campo ubicacionEvento.
     * 
     * @return El valor del campo ubicacionEvento
     */
    public String getUbicacionEvento() {
        return ubicacionEvento;
    }
    /**
     * Asigna el valor al campo ubicacionEvento.
     * 
     * @param ubicacionEvento
     *            el valor ubicacionEvento a asignar
     */
    public void setUbicacionEvento(String ubicacionEvento) {
        this.ubicacionEvento = ubicacionEvento;
    }

	public ValorDTO getEstatusAudiencia() {
		return estatusAudiencia;
	}

	public void setEstatusAudiencia(ValorDTO estatusAudiencia) {
		this.estatusAudiencia = estatusAudiencia;
	}
    
    
    

}

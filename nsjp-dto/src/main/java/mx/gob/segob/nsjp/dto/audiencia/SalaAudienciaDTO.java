/**
 * Nombre del Programa : SalaAudienciaDTO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 9 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Describir el objetivo de la clase de manera breve
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
import java.util.List;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;

/**
 * DTO para la transferencia de parametros de SalaAudiencia entre la vista y
 * servicios.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class SalaAudienciaDTO extends GenericDTO {
	
	
	private static final long serialVersionUID = 1058568464781973624L;
	
	private Long salaAudienciaId;
    /**
     * Uso exclusivo de salas permanentes.
     */
    private String nombreSala;
    private String domicilioSala;
    private String ubicacionSala;
    private Boolean esActivo;
    private FuncionarioDTO funcionarioDTO;
    private CatDiscriminanteDTO catDiscriminanteDTO;
    private SalaJAVSDTO salaJAVSDTO;
    
    /**
     * Uso exclusivo de salas temporales.
     */
    private String motivo;
    
    private boolean temporal;

    List<EspacioCalendarioDTO> eventos = new ArrayList<EspacioCalendarioDTO>();

    /**
     * 
     */
    public SalaAudienciaDTO() {
    }
    /**
     * 
     * @param id
     * @param nom
     */
    public SalaAudienciaDTO(Long id, String nom) {
        this.salaAudienciaId = id;
        this.nombreSala = nom;
    }
    /**
     * 
     * @param evento
     */
    public void addEvento(EspacioCalendarioDTO evento) {
        if (evento == null) {
            eventos = new ArrayList<EspacioCalendarioDTO>();
        }
        eventos.add(evento);
    }

    public EspacioCalendarioDTO getUltimoEvento() {
        if (eventos == null || eventos.isEmpty()) {
            return null;
        }
        return eventos.get(eventos.size() - 1);
    }

    public int getTotalEspacios() {
        if (eventos == null || eventos.isEmpty()) {
            return 0;
        }

        int tamanios = 0;
        for (EspacioCalendarioDTO ev : eventos) {
            tamanios += ev.getTamanio();
        }
        return tamanios;
    }

    /**
     * Método de acceso al campo nombreSala.
     * 
     * @return El valor del campo nombreSala
     */
    public String getNombreSala() {
        return nombreSala;
    }
    /**
     * Asigna el valor al campo nombreSala.
     * 
     * @param nombreSala
     *            el valor nombreSala a asignar
     */
    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }
    /**
     * Método de acceso al campo domicilioSala.
     * 
     * @return El valor del campo domicilioSala
     */
    public String getDomicilioSala() {
        return domicilioSala;
    }
    /**
     * Asigna el valor al campo domicilioSala.
     * 
     * @param domicilioSala
     *            el valor domicilioSala a asignar
     */
    public void setDomicilioSala(String domicilioSala) {
        this.domicilioSala = domicilioSala;
    }
    /**
     * Método de acceso al campo ubicacionSala.
     * 
     * @return El valor del campo ubicacionSala
     */
    public String getUbicacionSala() {
        return ubicacionSala;
    }
    /**
     * Asigna el valor al campo ubicacionSala.
     * 
     * @param ubicacionSala
     *            el valor ubicacionSala a asignar
     */
    public void setUbicacionSala(String ubicacionSala) {
        this.ubicacionSala = ubicacionSala;
    }
    /**
	 * @return the esActivo
	 */
	public Boolean getEsActivo() {
		return esActivo;
	}
	/**
	 * @param esActivo the esActivo to set
	 */
	public void setEsActivo(Boolean esActivo) {
		this.esActivo = esActivo;
	}
	/**
	 * @return the funcionarioDTO
	 */
	public FuncionarioDTO getFuncionarioDTO() {
		return funcionarioDTO;
	}
	/**
	 * @param funcionarioDTO the funcionarioDTO to set
	 */
	public void setFuncionarioDTO(FuncionarioDTO funcionarioDTO) {
		this.funcionarioDTO = funcionarioDTO;
	}
	/**
	 * @return the catDiscriminanteDTO
	 */
	public CatDiscriminanteDTO getCatDiscriminanteDTO() {
		return catDiscriminanteDTO;
	}
	/**
	 * @param catDiscriminanteDTO the catDiscriminanteDTO to set
	 */
	public void setCatDiscriminanteDTO(CatDiscriminanteDTO catDiscriminanteDTO) {
		this.catDiscriminanteDTO = catDiscriminanteDTO;
	}
	/**
	 * @return the salaJAVSDTO
	 */
	public SalaJAVSDTO getSalaJAVSDTO() {
		return salaJAVSDTO;
	}
	/**
	 * @param salaJAVSDTO the salaJAVSDTO to set
	 */
	public void setSalaJAVSDTO(SalaJAVSDTO salaJAVSDTO) {
		this.salaJAVSDTO = salaJAVSDTO;
	}
	/**
     * Método de acceso al campo temporal.
     * 
     * @return El valor del campo temporal
     */
    public boolean isTemporal() {
        return temporal;
    }
    /**
     * Asigna el valor al campo temporal.
     * 
     * @param temporal
     *            el valor temporal a asignar
     */
    public void setTemporal(boolean temporal) {
        this.temporal = temporal;
    }
    /**
     * Método de acceso al campo salaAudienciaId.
     * 
     * @return El valor del campo salaAudienciaId
     */
    public Long getSalaAudienciaId() {
        return salaAudienciaId;
    }
    /**
     * Asigna el valor al campo salaAudienciaId.
     * 
     * @param salaAudienciaId
     *            el valor salaAudienciaId a asignar
     */
    public void setSalaAudienciaId(Long salaAudienciaId) {
        this.salaAudienciaId = salaAudienciaId;
    }
    /**
     * Método de acceso al campo audiencias.
     * 
     * @return El valor del campo audiencias
     */
    public List<EspacioCalendarioDTO> getEventos() {
        return eventos;
    }
    /**
     * Asigna el valor al campo audiencias.
     * 
     * @param audiencias
     *            el valor audiencias a asignar
     */
    public void setEventos(List<EspacioCalendarioDTO> audiencias) {
        this.eventos = audiencias;
    }
    /**
     * Método de acceso al campo motivo.
     * @return El valor del campo motivo
     */
    public String getMotivo() {
        return motivo;
    }
    /**
     * Asigna el valor al campo motivo.
     * @param motivo el valor motivo a asignar
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}

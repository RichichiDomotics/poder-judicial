/**
 * Nombre del Programa : FiltroAudienciaDTO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 15 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : DTO para el filtro de audiencias
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

import java.util.Date;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * DTO para el filtro de audiencias.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class FiltroAudienciaDTO extends GenericDTO {
    /**
     * Fecha inicial para el rango.<br>
     * Fecha del día a buscar.
     */
    private Date fechaInicial;
    /**
     * Fecha final del rango.<br>
     * Puede ser nulo cuando se buscan las de un sólo día.
     */
    private Date fechaFinal;
    /**
     * Indica que cuanod se busque un sólo día este sea completo o que
     * discrimine las que ya pasaron de acuerdo a la hora actual.
     */
    private boolean diaCompleto;
    /**
     * Usado par abuscar loas audiencias de un número de expediente.
     */
    private Long numeroExpedienteId;

    private Long tipoAudiencia;
    
    private String numeroExpediente;
    
    private boolean causa;
    
    private UsuarioDTO usuario;
    
    //Permite filtrar las audiencias programadas por Distrito
    private Long idDistritoFiltroAudiencias;
    
    
    public boolean isRango() {
        return (fechaInicial != null && fechaFinal != null);
    }

    /**
     * Método de acceso al campo fechaInicial.
     * 
     * @return El valor del campo fechaInicial
     */
    public Date getFechaInicial() {
        return fechaInicial;
    }
    /**
     * Asigna el valor al campo fechaInicial.
     * 
     * @param fechaInicial
     *            el valor fechaInicial a asignar
     */
    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }
    /**
     * Método de acceso al campo fechaFinal.
     * 
     * @return El valor del campo fechaFinal
     */
    public Date getFechaFinal() {
        return fechaFinal;
    }
    /**
     * Asigna el valor al campo fechaFinal.
     * 
     * @param fechaFinal
     *            el valor fechaFinal a asignar
     */
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
    /**
     * Método de acceso al campo diaCompleto.
     * 
     * @return El valor del campo diaCompleto
     */
    public boolean isDiaCompleto() {
        return diaCompleto;
    }
    /**
     * Asigna el valor al campo diaCompleto.
     * 
     * @param diaCompleto
     *            el valor diaCompleto a asignar
     */
    public void setDiaCompleto(boolean diaCompleto) {
        this.diaCompleto = diaCompleto;
    }
    /**
     * Método de acceso al campo numeroExpedienteId.
     * 
     * @return El valor del campo numeroExpedienteId
     */
    public Long getNumeroExpedienteId() {
        return numeroExpedienteId;
    }
    /**
     * Asigna el valor al campo numeroExpedienteId.
     * 
     * @param numeroExpedienteId
     *            el valor numeroExpedienteId a asignar
     */
    public void setNumeroExpedienteId(Long numeroExpedienteId) {
        this.numeroExpedienteId = numeroExpedienteId;
    }
	
    /**
     * Método de acceso al campo tipoAudiencia.
     * 
     * @return El valor del campo tipoAudiencia
     */
    public void setTipoAudiencia(Long tipoAudiencia) {
		this.tipoAudiencia = tipoAudiencia;
	}

    /**
     * Asigna el valor al campo tipoAudiencia.
     * 
     * @param numeroExpedienteId
     *            el valor tipoAudiencia a asignar
     */
    public Long getTipoAudiencia() {
		return tipoAudiencia;
	}

	
    /**
	 * @return the causa
	 */
	public boolean isCausa() {
		return causa;
	}
	

	/**
	 * @param causa the causa to set
	 */
	public void setCausa(boolean causa) {
		this.causa = causa;
	}

	public void setNumeroExpediente(String numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}

	public String getNumeroExpediente() {
		return numeroExpediente;
	}

	/**
	 * @return the usuario
	 */
	public UsuarioDTO getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public Long getIdDistritoFiltroAudiencias() {
		return idDistritoFiltroAudiencias;
	}

	public void setIdDistritoFiltroAudiencias(Long idDistritoFiltroAudiencias) {
		this.idDistritoFiltroAudiencias = idDistritoFiltroAudiencias;
	}


	
}

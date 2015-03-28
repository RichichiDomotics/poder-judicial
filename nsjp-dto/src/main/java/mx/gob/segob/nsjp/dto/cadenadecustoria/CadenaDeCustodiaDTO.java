/**
 * Nombre del Programa : CadenaDeCustodiaDTO.java                                    
 * Autor                            : Tattva-IT                                              
 * Compania                    : Ultrasist                                                
 * Proyecto                      : NSJP                    Fecha: 3 May 2011 
 * Marca de cambio        : N/A                                                     
 * Descripcion General    : IntegraciÃ³n xxxxxxxxxxx                      
 * Programa Dependiente  :N/A                                                      
 * Programa Subsecuente :N/A                                                      
 * Cond. de ejecucion        :N/A                                                      
 * Dias de ejecucion          :N/A                             Horario: N/A       
 *                              MODIFICACIONES                                       
 *------------------------------------------------------------------------------           
 * Autor                       :N/A                                                           
 * Compania               :N/A                                                           
 * Proyecto                 :N/A                                   Fecha: N/A       
 * Modificacion           :N/A                                                           
 *------------------------------------------------------------------------------           
 */
package mx.gob.segob.nsjp.dto.cadenadecustoria;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;

/**
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto
 * CadenaDeCustodia.
 * 
 * @version 1.0
 * @author Tattva-IT
 * 
 */
public class CadenaDeCustodiaDTO extends GenericDTO {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2747141571398513935L;
	private Long cadenaDeCustodiaId;
    private Date fechaIntercambio;
    private String observacion;
    private String quienEntrega;
	private String quienRecibe;
    private String quienEmbala;
    private String quienTransporta;
    private String institucionEmbalaje;
    private String institucionTraslado;
    private Date fechaLevantamiento;
    private String folio;
    private List<EvidenciaDTO> evidencias=new ArrayList<EvidenciaDTO>();
    
    private EvidenciaDTO evidencia;//Para cuando se quiere actualizar
    private ExpedienteDTO expedienteDTO;
    /**
     * 
     * @param cadenaDeCustodiaId
     */
    public CadenaDeCustodiaDTO(Long cadenaDeCustodiaId) {
        this.cadenaDeCustodiaId = cadenaDeCustodiaId;
    }
    /**
	 * 
	 */
    public CadenaDeCustodiaDTO() {
    }
    /**
     * Mï¿½todo de acceso al campo cadenaDeCustodiaId.
     * 
     * @return El valor del campo cadenaDeCustodiaId
     */
    public Long getCadenaDeCustodiaId() {
        return cadenaDeCustodiaId;
    }
    /**
     * Asigna el valor al campo cadenaDeCustodiaId.
     * 
     * @param cadenaDeCustodiaId
     *            el valor cadenaDeCustodiaId a asignar	
     */
    public void setCadenaDeCustodiaId(Long cadenaDeCustodiaId) {
        this.cadenaDeCustodiaId = cadenaDeCustodiaId;
    }
    /**
     * Mï¿½todo de acceso al campo fechaIntercambio.
     * 
     * @return El valor del campo fechaIntercambio
     */
    public Date getFechaIntercambio() {
        return fechaIntercambio;
    }
    /**
     * Asigna el valor al campo fechaIntercambio.
     * 
     * @param fechaIntercambio
     *            el valor fechaIntercambio a asignar
     */
    public void setFechaIntercambio(Date fechaIntercambio) {
        this.fechaIntercambio = fechaIntercambio;
    }
    /**
     * Mï¿½todo de acceso al campo observacion.
     * 
     * @return El valor del campo observacion
     */
    public String getObservacion() {
        return observacion;
    }
    /**
     * Asigna el valor al campo observacion.
     * 
     * @param observacion
     *            el valor observacion a asignar
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    /**
     * Mï¿½todo de acceso al campo fechaLevantamiento.
     * 
     * @return El valor del campo fechaLevantamiento
     */
    public Date getFechaLevantamiento() {
        return fechaLevantamiento;
    }
    /**
     * Asigna el valor al campo fechaLevantamiento.
     * 
     * @param fechaLevantamiento
     *            el valor fechaLevantamiento a asignar
     */
    public void setFechaLevantamiento(Date fechaLevantamiento) {
        this.fechaLevantamiento = fechaLevantamiento;
    }
    /**
     * @return the folio
     */
    public String getFolio() {
        return folio;
    }

    /**
     * @param folio
     *            the folio to set
     */
    public void setFolio(String folio) {
        this.folio = folio;
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
	 * Método de acceso al campo quienEmbala.
	 * @return El valor del campo quienEmbala
	 */
	public String getQuienEmbala() {
		return quienEmbala;
	}
	/**
	 * Asigna el valor al campo quienEmbala.
	 * @param quienEmbala el valor quienEmbala a asignar
	 */
	public void setQuienEmbala(String quienEmbala) {
		this.quienEmbala = quienEmbala;
	}
	/**
	 * Método de acceso al campo quienTransporta.
	 * @return El valor del campo quienTransporta
	 */
	public String getQuienTransporta() {
		return quienTransporta;
	}
	/**
	 * Asigna el valor al campo quienTransporta.
	 * @param quienTransporta el valor quienTransporta a asignar
	 */
	public void setQuienTransporta(String quienTransporta) {
		this.quienTransporta = quienTransporta;
	}

    /**
	 * @return the institucionEmbalaje
	 */
	public String getInstitucionEmbalaje() {
		return institucionEmbalaje;
	}
	/**
	 * @param institucionEmbalaje the institucionEmbalaje to set
	 */
	public void setInstitucionEmbalaje(String institucionEmbalaje) {
		this.institucionEmbalaje = institucionEmbalaje;
	}
	/**
	 * @return the institucionTraslado
	 */
	public String getInstitucionTraslado() {
		return institucionTraslado;
	}
	/**
	 * @param institucionTraslado the institucionTraslado to set
	 */
	public void setInstitucionTraslado(String institucionTraslado) {
		this.institucionTraslado = institucionTraslado;
	}
	public EvidenciaDTO getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(EvidenciaDTO evidencia) {
        this.evidencia = evidencia;
    }
	/**
	 * @param evidencias the evidencias to set
	 */
	public void setEvidencias(List<EvidenciaDTO> evidencias) {
		this.evidencias = evidencias;
	}
	/**
	 * @return the evidencias
	 */
	public List<EvidenciaDTO> getEvidencias() {
		return evidencias;
	}
	/**
	 * @param expedienteDTO the expedienteDTO to set
	 */
	public void setExpedienteDTO(ExpedienteDTO expedienteDTO) {
		this.expedienteDTO = expedienteDTO;
	}
	/**
	 * @return the expedienteDTO
	 */
	public ExpedienteDTO getExpedienteDTO() {
		return expedienteDTO;
	}
}

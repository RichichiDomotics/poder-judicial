/**
* Nombre del Programa : AudienciaWSDTO.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 30/06/2011
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
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.NotificacionWSDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoWSDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.dto.resolutivo.ResolutivoDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaDTO;

/**
 * DTO de intercambio entre sistemas para transportar los datos básicos de una audiencia
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public class AudienciaWSDTO {
	
	 private Long tipoAudienciaId;
	 
	 private String numeroGeneralCaso;
	 
	 private Date fechaAsignacionSala;
	 
	 private Integer duracionEstimada;

	 private Long estatusAudienciaId;
	 
	 private Long audienciaId; 
	 
	 private boolean salaTemporal;
	 
	 private String nombreSala;
	 
	 private String domicilioSala;
	    
	 private String ubicacionSala;

	 private Date fechaHoraAudiencia;
	 
	 private Date fechaInicio;
	 
	 private Date fechaFin;
    /**
     * Uso exclusivo de salas temporales.
     */
     private String motivo;     
     
     private String numeroCausa;
     
     private String numeroTocaOrCarpeta;
     
     private String folioAudiencia;
     
     private List<InvolucradoWSDTO> involucrados;
     /**
      * Objeto usado para transferir los datos de la notificación.
      */
     private NotificacionWSDTO notificaion;
     
	/**
	 * Método de acceso al campo tipoAudienciaId.
	 * @return El valor del campo tipoAudienciaId
	 */
	public Long getTipoAudienciaId() {
		return tipoAudienciaId;
	}
	/**
	 * Asigna el valor al campo tipoAudienciaId.
	 * @param tipoAudienciaId el valor tipoAudienciaId a asignar
	 */
	public void setTipoAudienciaId(Long tipoAudienciaId) {
		this.tipoAudienciaId = tipoAudienciaId;
	}
	/**
	 * Establece el valor de la propiedad numeroGeneralCaso
	 * @param numeroGeneralCaso valo numeroGeneralCaso a almacenar
	 */
	public void setNumeroGeneralCaso(String numeroGeneralCaso) {
		this.numeroGeneralCaso = numeroGeneralCaso;
	}
	/**
	 * Regresa el valor de la propiedad numeroGeneralCaso
	 * @return the numeroGeneralCaso
	 */
	public String getNumeroGeneralCaso() {
		return numeroGeneralCaso;
	}
	/**
	 * Método de acceso al campo fechaAsignacionSala.
	 * @return El valor del campo fechaAsignacionSala
	 */
	public Date getFechaAsignacionSala() {
		return fechaAsignacionSala;
	}
	/**
	 * Asigna el valor al campo fechaAsignacionSala.
	 * @param fechaAsignacionSala el valor fechaAsignacionSala a asignar
	 */
	public void setFechaAsignacionSala(Date fechaAsignacionSala) {
		this.fechaAsignacionSala = fechaAsignacionSala;
	}
	/**
	 * Método de acceso al campo duracionEstimada.
	 * @return El valor del campo duracionEstimada
	 */
	public Integer getDuracionEstimada() {
		return duracionEstimada;
	}
	/**
	 * Asigna el valor al campo duracionEstimada.
	 * @param duracionEstimada el valor duracionEstimada a asignar
	 */
	public void setDuracionEstimada(Integer duracionEstimada) {
		this.duracionEstimada = duracionEstimada;
	}
	/**
	 * Método de acceso al campo estatusAudienciaId.
	 * @return El valor del campo estatusAudienciaId
	 */
	public Long getEstatusAudienciaId() {
		return estatusAudienciaId;
	}
	/**
	 * Asigna el valor al campo estatusAudienciaId.
	 * @param estatusAudienciaId el valor estatusAudienciaId a asignar
	 */
	public void setEstatusAudienciaId(Long estatusAudienciaId) {
		this.estatusAudienciaId = estatusAudienciaId;
	}
	/**
	 * Método de acceso al campo audienciaId.
	 * @return El valor del campo audienciaId
	 */
	public Long getAudienciaId() {
		return audienciaId;
	}
	/**
	 * Asigna el valor al campo audienciaId.
	 * @param audienciaId el valor audienciaId a asignar
	 */
	public void setAudienciaId(Long audienciaId) {
		this.audienciaId = audienciaId;
	}
	/**
	 * Método de acceso al campo salaTemporal.
	 * @return El valor del campo salaTemporal
	 */
	public boolean isSalaTemporal() {
		return salaTemporal;
	}
	/**
	 * Asigna el valor al campo salaTemporal.
	 * @param salaTemporal el valor salaTemporal a asignar
	 */
	public void setSalaTemporal(boolean salaTemporal) {
		this.salaTemporal = salaTemporal;
	}
	/**
	 * Método de acceso al campo nombreSala.
	 * @return El valor del campo nombreSala
	 */
	public String getNombreSala() {
		return nombreSala;
	}
	/**
	 * Asigna el valor al campo nombreSala.
	 * @param nombreSala el valor nombreSala a asignar
	 */
	public void setNombreSala(String nombreSala) {
		this.nombreSala = nombreSala;
	}
	/**
	 * Método de acceso al campo domicilioSala.
	 * @return El valor del campo domicilioSala
	 */
	public String getDomicilioSala() {
		return domicilioSala;
	}
	/**
	 * Asigna el valor al campo domicilioSala.
	 * @param domicilioSala el valor domicilioSala a asignar
	 */
	public void setDomicilioSala(String domicilioSala) {
		this.domicilioSala = domicilioSala;
	}
	/**
	 * Método de acceso al campo ubicacionSala.
	 * @return El valor del campo ubicacionSala
	 */
	public String getUbicacionSala() {
		return ubicacionSala;
	}
	/**
	 * Asigna el valor al campo ubicacionSala.
	 * @param ubicacionSala el valor ubicacionSala a asignar
	 */
	public void setUbicacionSala(String ubicacionSala) {
		this.ubicacionSala = ubicacionSala;
	}
	/**
	 * Método de acceso al campo fechaHoraAudiencia.
	 * @return El valor del campo fechaHoraAudiencia
	 */
	public Date getFechaHoraAudiencia() {
		return fechaHoraAudiencia;
	}
	/**
	 * Asigna el valor al campo fechaHoraAudiencia.
	 * @param fechaHoraAudiencia el valor fechaHoraAudiencia a asignar
	 */
	public void setFechaHoraAudiencia(Date fechaHoraAudiencia) {
		this.fechaHoraAudiencia = fechaHoraAudiencia;
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
	/**
	 * Método de acceso al campo fechaInicio.
	 * @return El valor del campo fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * Asigna el valor al campo fechaInicio.
	 * @param fechaInicio el valor fechaInicio a asignar
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * Método de acceso al campo fechaFin.
	 * @return El valor del campo fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}
	/**
	 * Asigna el valor al campo fechaFin.
	 * @param fechaFin el valor fechaFin a asignar
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
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
	 * Establece el valor de la propiedad involucrados
	 * @param involucrados valo involucrados a almacenar
	 */
	public void setInvolucrados(List<InvolucradoWSDTO> involucrados) {
		this.involucrados = involucrados;
	}
	/**
	 * Regresa el valor de la propiedad involucrados
	 * @return the involucrados
	 */
	public List<InvolucradoWSDTO> getInvolucrados() {
		return involucrados;
	}
    /**
     * Método de acceso al campo notificaion.
     * @return El valor del campo notificaion
     */
    public NotificacionWSDTO getNotificaion() {
        return notificaion;
    }
    /**
     * Asigna el valor al campo notificaion.
     * @param notificaion el valor notificaion a asignar
     */
    public void setNotificaion(NotificacionWSDTO notificaion) {
        this.notificaion = notificaion;
    }
	
	 
}

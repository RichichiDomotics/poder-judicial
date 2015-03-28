/**
 * Nombre del Programa  : AudienciaDefensoriaWSDTO.java
 * Autor                : Daniel Jiménez
 * Compania             : TATTVA-IT
 * Proyecto             : NSJP                    Fecha: 25 Ago 2011
 * Marca de cambio      : N/A
 * Descripcion General  : Almacena la información de audiencia que será enviada a 
 * 						  defensoría
 * Programa Dependiente : N/A
 * Programa Subsecuente : N/A
 * Cond. de ejecucion   : N/A
 * Dias de ejecucion    : N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                :N/A
 * Compania             :N/A
 * Proyecto             :N/A                                 Fecha: N/A
 * Modificacion         :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.dto.audiencia;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.dto.involucrado.InvolucradoAudienciaDefWSDTO;

public class AudienciaDefensoriaWSDTO {

	private Long audienciaId;
	private Long tipoAudienciaId; 
	private String caracter;
	private String numeroCaso;
	private String nombreSala;
	private String domicilioSala; 
	private String ubicacionSala;
	private Date fechaAudiencia;
	private Date filtroFechaInicio;
	private Date filtroFechaFinal;
	
    
    //Permite filtrar las audiencias programadas por Distrito
    private Long idDistritoFiltroAudiencias;
    	
	private List<InvolucradoAudienciaDefWSDTO> involucrados;
	
	 /**
	 * Regresa el valor de la propiedad audienciaId
	 * @return the audienciaId
	 */
	public Long getAudienciaId() {
		return audienciaId;
	}

	/**
	 * Establece el valor de la propiedad audienciaId
	 * @param audienciaId valo audienciaId a almacenar
	 */
	public void setAudienciaId(Long audienciaId) {
		this.audienciaId = audienciaId;
	}

	/**
	 * Regresa el valor de la propiedad tipoAudienciaId
	 * @return the tipoAudienciaId
	 */
	public Long getTipoAudienciaId() {
		return tipoAudienciaId;
	}

	/**
	 * Establece el valor de la propiedad tipoAudienciaId
	 * @param tipoAudienciaId valo tipoAudienciaId a almacenar
	 */
	public void setTipoAudienciaId(Long tipoAudienciaId) {
		this.tipoAudienciaId = tipoAudienciaId;
	}

	/**
	 * Regresa el valor de la propiedad caracter
	 * @return the caracter
	 */
	public String getCaracter() {
		return caracter;
	}

	/**
	 * Establece el valor de la propiedad caracter
	 * @param caracter valo caracter a almacenar
	 */
	public void setCaracter(String caracter) {
		this.caracter = caracter;
	}

	/**
	 * Regresa el valor de la propiedad numeroCaso
	 * @return the numeroCaso
	 */
	public String getNumeroCaso() {
		return numeroCaso;
	}

	/**
	 * Establece el valor de la propiedad numeroCaso
	 * @param numeroCaso valo numeroCaso a almacenar
	 */
	public void setNumeroCaso(String numeroCaso) {
		this.numeroCaso = numeroCaso;
	}

	/**
	 * Regresa el valor de la propiedad nombreSala
	 * @return the nombreSala
	 */
	public String getNombreSala() {
		return nombreSala;
	}

	/**
	 * Establece el valor de la propiedad nombreSala
	 * @param nombreSala valo nombreSala a almacenar
	 */
	public void setNombreSala(String nombreSala) {
		this.nombreSala = nombreSala;
	}

	/**
	 * Regresa el valor de la propiedad domicilioSala
	 * @return the domicilioSala
	 */
	public String getDomicilioSala() {
		return domicilioSala;
	}

	/**
	 * Establece el valor de la propiedad domicilioSala
	 * @param domicilioSala valo domicilioSala a almacenar
	 */
	public void setDomicilioSala(String domicilioSala) {
		this.domicilioSala = domicilioSala;
	}

	/**
	 * Regresa el valor de la propiedad ubicacionSala
	 * @return the ubicacionSala
	 */
	public String getUbicacionSala() {
		return ubicacionSala;
	}

	/**
	 * Establece el valor de la propiedad ubicacionSala
	 * @param ubicacionSala valo ubicacionSala a almacenar
	 */
	public void setUbicacionSala(String ubicacionSala) {
		this.ubicacionSala = ubicacionSala;
	}

	
	/**
	 * Regresa el valor de la propiedad fechaAudiencia
	 * @return the fechaAudiencia
	 */
	public Date getFechaAudiencia() {
		return fechaAudiencia;
	}

	
	/**
	 * Establece el valor de la propiedad fechaAudiencia
	 * @param fechaAudiencia valo fechaAudiencia a almacenar
	 */
	public void setFechaAudiencia(Date fechaAudiencia) {
		this.fechaAudiencia = fechaAudiencia;
	}

	
	/**
	 * Regresa el valor de la propiedad filtroFechaInicio
	 * @return the filtroFechaInicio
	 */
	public Date getFiltroFechaInicio() {
		return filtroFechaInicio;
	}

	
	/**
	 * Establece el valor de la propiedad filtroFechaInicio
	 * @param filtroFechaInicio valo filtroFechaInicio a almacenar
	 */
	public void setFiltroFechaInicio(Date filtroFechaInicio) {
		this.filtroFechaInicio = filtroFechaInicio;
	}

	
	/**
	 * Regresa el valor de la propiedad filtroFechaFinal
	 * @return the filtroFechaFinal
	 */
	public Date getFiltroFechaFinal() {
		return filtroFechaFinal;
	}

	
	/**
	 * Establece el valor de la propiedad filtroFechaFinal
	 * @param filtroFechaFinal valo filtroFechaFinal a almacenar
	 */
	public void setFiltroFechaFinal(Date filtroFechaFinal) {
		this.filtroFechaFinal = filtroFechaFinal;
	}

	/**
	 * Regresa el valor de la propiedad involucrados
	 * @return the involucrados
	 */
	public List<InvolucradoAudienciaDefWSDTO> getInvolucrados() {
		return involucrados;
	}

	/**
	 * Establece el valor de la propiedad involucrados
	 * @param involucrados valo involucrados a almacenar
	 */
	public void setInvolucrados(List<InvolucradoAudienciaDefWSDTO> involucrados) {
		this.involucrados = involucrados;
	}

	public Long getIdDistritoFiltroAudiencias() {
		return idDistritoFiltroAudiencias;
	}

	public void setIdDistritoFiltroAudiencias(Long idDistritoFiltroAudiencias) {
		this.idDistritoFiltroAudiencias = idDistritoFiltroAudiencias;
	}
	
	
}

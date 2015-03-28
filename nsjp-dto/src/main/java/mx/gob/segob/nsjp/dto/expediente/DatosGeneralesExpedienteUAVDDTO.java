/**
* Nombre del Programa : DatosGeneralesExpedienteUAVDDTO.java
* Autor                            : Ricardo Gama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 12/10/2011
* Marca de cambio        : N/A
* Descripcion General    : DTO que contiene la información ordenada del 
* expediente
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

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * DTO que contiene la información de un expediente asociada a la seccion de "Resumen" 
 * @version 1.0
 * @author RGama
 *
 */
public class DatosGeneralesExpedienteUAVDDTO extends GenericDTO {

	private static final long serialVersionUID = -5777524541556828339L;
    //Información relacionanda a un numero de expediente
	private Long expedienteId;
	
	//Datos del resumen
    private String tipoSolicitud;
    private String nombreDeLaVictima;
    private String delito;
    private String ampSolicitante;
    private String areaSolicitante;    
    private String fechaDeCreacionDelExpediente;
    private String estatusDelExpediente;
	/**
	 * Método de acceso al campo expedienteId.
	 * @return El valor del campo expedienteId
	 */
	public Long getExpedienteId() {
		return expedienteId;
	}
	/**
	 * Asigna el valor al campo expedienteId.
	 * @param expedienteId el valor expedienteId a asignar
	 */
	public void setExpedienteId(Long expedienteId) {
		this.expedienteId = expedienteId;
	}
	/**
	 * Método de acceso al campo tipoSolicitud.
	 * @return El valor del campo tipoSolicitud
	 */
	public String getTipoSolicitud() {
		return tipoSolicitud;
	}
	/**
	 * Asigna el valor al campo tipoSolicitud.
	 * @param tipoSolicitud el valor tipoSolicitud a asignar
	 */
	public void setTipoSolicitud(String tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}
	/**
	 * Método de acceso al campo nombreDeLaVictima.
	 * @return El valor del campo nombreDeLaVictima
	 */
	public String getNombreDeLaVictima() {
		return nombreDeLaVictima;
	}
	/**
	 * Asigna el valor al campo nombreDeLaVictima.
	 * @param nombreDeLaVictima el valor nombreDeLaVictima a asignar
	 */
	public void setNombreDeLaVictima(String nombreDeLaVictima) {
		this.nombreDeLaVictima = nombreDeLaVictima;
	}
	/**
	 * Método de acceso al campo delito.
	 * @return El valor del campo delito
	 */
	public String getDelito() {
		return delito;
	}
	/**
	 * Asigna el valor al campo delito.
	 * @param delito el valor delito a asignar
	 */
	public void setDelito(String delito) {
		this.delito = delito;
	}
	/**
	 * Método de acceso al campo ampSolicitante.
	 * @return El valor del campo ampSolicitante
	 */
	public String getAmpSolicitante() {
		return ampSolicitante;
	}
	/**
	 * Asigna el valor al campo ampSolicitante.
	 * @param ampSolicitante el valor ampSolicitante a asignar
	 */
	public void setAmpSolicitante(String ampSolicitante) {
		this.ampSolicitante = ampSolicitante;
	}
	/**
	 * Método de acceso al campo areaSolicitante.
	 * @return El valor del campo areaSolicitante
	 */
	public String getAreaSolicitante() {
		return areaSolicitante;
	}
	/**
	 * Asigna el valor al campo areaSolicitante.
	 * @param areaSolicitante el valor areaSolicitante a asignar
	 */
	public void setAreaSolicitante(String areaSolicitante) {
		this.areaSolicitante = areaSolicitante;
	}
	/**
	 * Método de acceso al campo fechaDeCreacionDelExpediente.
	 * @return El valor del campo fechaDeCreacionDelExpediente
	 */
	public String getFechaDeCreacionDelExpediente() {
		return fechaDeCreacionDelExpediente;
	}
	/**
	 * Asigna el valor al campo fechaDeCreacionDelExpediente.
	 * @param fechaDeCreacionDelExpediente el valor fechaDeCreacionDelExpediente a asignar
	 */
	public void setFechaDeCreacionDelExpediente(String fechaDeCreacionDelExpediente) {
		this.fechaDeCreacionDelExpediente = fechaDeCreacionDelExpediente;
	}
	/**
	 * Método de acceso al campo estatusDelExpediente.
	 * @return El valor del campo estatusDelExpediente
	 */
	public String getEstatusDelExpediente() {
		return estatusDelExpediente;
	}
	/**
	 * Asigna el valor al campo estatusDelExpediente.
	 * @param estatusDelExpediente el valor estatusDelExpediente a asignar
	 */
	public void setEstatusDelExpediente(String estatusDelExpediente) {
		this.estatusDelExpediente = estatusDelExpediente;
	}
}

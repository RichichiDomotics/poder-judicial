/**
* Nombre del Programa : AvisoDetencionWSDTO.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 01/07/2011
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
package mx.gob.segob.nsjp.dto.documento;

import java.util.List;

import mx.gob.segob.nsjp.dto.base.GenericWSDTO;
import mx.gob.segob.nsjp.dto.detencion.DetencionWSDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoWSDTO;

/**
 * DTO para el transporte de datos de un aviso de detención 
 * entre instituciones
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public class AvisoDetencionWSDTO extends GenericWSDTO{
	
	
	private String numeroCasoAsociado;
	private Long estatusNotificacionId;
	private Long avisoDetencionId;
	private DetencionWSDTO detencion;
	private List<DelitoWSDTO> delitos;
	private String folioNotificacion;
	
	/**
	 * Método de acceso al campo numeroCasoAsociado.
	 * @return El valor del campo numeroCasoAsociado
	 */
	public String getNumeroCasoAsociado() {
		return numeroCasoAsociado;
	}
	
	/**
	 * Asigna el valor al campo numeroCasoAsociado.
	 * @param numeroCasoAsociado el valor numeroCasoAsociado a asignar
	 */
	public void setNumeroCasoAsociado(String numeroCasoAsociado) {
		this.numeroCasoAsociado = numeroCasoAsociado;
	}
	
	/**
	 * Método de acceso al campo estatusNotificacionId.
	 * @return El valor del campo estatusNotificacionId
	 */
	public Long getEstatusNotificacionId() {
		return estatusNotificacionId;
	}
	
	/**
	 * Asigna el valor al campo estatusNotificacionId.
	 * @param estatusNotificacionId el valor estatusNotificacionId a asignar
	 */
	public void setEstatusNotificacionId(Long estatusNotificacionId) {
		this.estatusNotificacionId = estatusNotificacionId;
	}
	
	/**
	 * Método de acceso al campo avisoDetencionId.
	 * @return El valor del campo avisoDetencionId
	 */
	public Long getAvisoDetencionId() {
		return avisoDetencionId;
	}
	
	/**
	 * Asigna el valor al campo avisoDetencionId.
	 * @param avisoDetencionId el valor avisoDetencionId a asignar
	 */
	public void setAvisoDetencionId(Long avisoDetencionId) {
		this.avisoDetencionId = avisoDetencionId;
	}
	
	/**
	 * Método de acceso al campo detencion.
	 * @return El valor del campo detencion
	 */
	public DetencionWSDTO getDetencion() {
		return detencion;
	}
	
	/**
	 * Asigna el valor al campo detencion.
	 * @param detencion el valor detencion a asignar
	 */
	public void setDetencion(DetencionWSDTO detencion) {
		this.detencion = detencion;
	}
	
	/**
	 * Método de acceso al campo delitos.
	 * @return El valor del campo delitos
	 */
	public List<DelitoWSDTO> getDelitos() {
		return delitos;
	}
	
	/**
	 * Asigna el valor al campo delitos.
	 * @param delitos el valor delitos a asignar
	 */
	public void setDelitos(List<DelitoWSDTO> delitos) {
		this.delitos = delitos;
	}
    
	/**
     * Método de acceso al campo folioNotificacion.
     * @return El valor del campo folioNotificacion
     */
    public String getFolioNotificacion() {
        return folioNotificacion;
    }
    
    /**
     * Asigna el valor al campo folioNotificacion.
     * @param folioNotificacion el valor folioNotificacion a asignar
     */
    public void setFolioNotificacion(String folioNotificacion) {
        this.folioNotificacion = folioNotificacion;
    }
}

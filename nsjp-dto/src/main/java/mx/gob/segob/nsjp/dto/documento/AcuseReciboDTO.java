/**
 * Nombre del Programa : AcuseReciboDTO.java
 * Autor                            : rgama
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 22 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Objeto de transferencia de la notificacion.
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

import java.util.Date;

import mx.gob.segob.nsjp.dto.configuracion.ConfInstitucionDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;

/**
 * Objeto de transferencia de un Acuse de Recibo
 * 
 * @version 1.0
 * @author rgama
 * 
 */
public class AcuseReciboDTO extends DocumentoDTO {

    private SolicitudDTO solicitud;
    private ConfInstitucionDTO confInstitucion;
    private Date fechaAcuse;
    private String nombreRecibe;
	/**
	 * Método de acceso al campo solicitud.
	 * @return El valor del campo solicitud
	 */
	public SolicitudDTO getSolicitud() {
		return solicitud;
	}
	/**
	 * Asigna el valor al campo solicitud.
	 * @param solicitud el valor solicitud a asignar
	 */
	public void setSolicitud(SolicitudDTO solicitud) {
		this.solicitud = solicitud;
	}
	/**
	 * Método de acceso al campo confInstitucion.
	 * @return El valor del campo confInstitucion
	 */
	public ConfInstitucionDTO getConfInstitucion() {
		return confInstitucion;
	}
	/**
	 * Asigna el valor al campo confInstitucion.
	 * @param confInstitucion el valor confInstitucion a asignar
	 */
	public void setConfInstitucion(ConfInstitucionDTO confInstitucion) {
		this.confInstitucion = confInstitucion;
	}
	/**
	 * Método de acceso al campo fechaAcuse.
	 * @return El valor del campo fechaAcuse
	 */
	public Date getFechaAcuse() {
		return fechaAcuse;
	}
	/**
	 * Asigna el valor al campo fechaAcuse.
	 * @param fechaAcuse el valor fechaAcuse a asignar
	 */
	public void setFechaAcuse(Date fechaAcuse) {
		this.fechaAcuse = fechaAcuse;
	}
	/**
	 * Método de acceso al campo nombreRecibe.
	 * @return El valor del campo nombreRecibe
	 */
	public String getNombreRecibe() {
		return nombreRecibe;
	}
	/**
	 * Asigna el valor al campo nombreRecibe.
	 * @param nombreRecibe el valor nombreRecibe a asignar
	 */
	public void setNombreRecibe(String nombreRecibe) {
		this.nombreRecibe = nombreRecibe;
	}	
}

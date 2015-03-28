/**
* Nombre del Programa : ExpedienteAdscritoDTO.java
* Autor                            : cesar
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 13 May 2011
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
package mx.gob.segob.nsjp.dto.expediente;

import java.util.Date;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author cesar
 *
 */
public class ExpedienteAdscritoDTO extends ExpedienteDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4634235217980298881L;

	private String relacionImputadoSolicitante;
	private String lugarDondeEstaElDetenido;
	private Date fechaDeDetencion;
	/**
	 * Método de acceso al campo relacionImputadoSolicitante.
	 * @return El valor del campo relacionImputadoSolicitante
	 */
	public String getRelacionImputadoSolicitante() {
		return relacionImputadoSolicitante;
	}
	/**
	 * Asigna el valor al campo relacionImputadoSolicitante.
	 * @param relacionImputadoSolicitante el valor relacionImputadoSolicitante a asignar
	 */
	public void setRelacionImputadoSolicitante(String relacionImputadoSolicitante) {
		this.relacionImputadoSolicitante = relacionImputadoSolicitante;
	}
	/**
	 * Método de acceso al campo lugarDondeEstaElDetenido.
	 * @return El valor del campo lugarDondeEstaElDetenido
	 */
	public String getLugarDondeEstaElDetenido() {
		return lugarDondeEstaElDetenido;
	}
	/**
	 * Asigna el valor al campo lugarDondeEstaElDetenido.
	 * @param lugarDondeEstaElDetenido el valor lugarDondeEstaElDetenido a asignar
	 */
	public void setLugarDondeEstaElDetenido(String lugarDondeEstaElDetenido) {
		this.lugarDondeEstaElDetenido = lugarDondeEstaElDetenido;
	}
	/**
	 * Método de acceso al campo fechaDeDetencion.
	 * @return El valor del campo fechaDeDetencion
	 */
	public Date getFechaDeDetencion() {
		return fechaDeDetencion;
	}
	/**
	 * Asigna el valor al campo fechaDeDetencion.
	 * @param fechaDeDetencion el valor fechaDeDetencion a asignar
	 */
	public void setFechaDeDetencion(Date fechaDeDetencion) {
		this.fechaDeDetencion = fechaDeDetencion;
	}

	
}

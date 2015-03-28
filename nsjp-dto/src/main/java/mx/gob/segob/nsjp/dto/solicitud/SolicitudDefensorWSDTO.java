/**
* Nombre del Programa : SolicitudTranscripcionWSDTO.java
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
package mx.gob.segob.nsjp.dto.solicitud;

import java.util.List;

import mx.gob.segob.nsjp.dto.expediente.DelitoWSDTO;

/**
 * Objeto de transferencia entre sistemas para transporte de los datos de una solicitud de 
 * defensor
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public class SolicitudDefensorWSDTO extends SolicitudAudienciaBasicoWSDTO {

	private String folioElemento;
	private String nombreImputado;
	private String apellidoPaternoImputado;
	private String apellidoMaternoImputado;
	private Boolean esDetenido;
	private List<DelitoWSDTO> delitos;

	/**
	 * Establece el valor de la propiedad folioElemento
	 * @param folioElemento valo folioElemento a almacenar
	 */
	public void setFolioElemento(String folioElemento) {
		this.folioElemento = folioElemento;
	}
	

	/**
	 * Regresa el valor de la propiedad folioElemento
	 * @return the folioElemento
	 */
	public String getFolioElemento() {
		return folioElemento;
	}
	

	/**
	 * Método de acceso al campo nombreImputado.
	 * @return El valor del campo nombreImputado
	 */
	public String getNombreImputado() {
		return nombreImputado;
	}

	/**
	 * Asigna el valor al campo nombreImputado.
	 * @param nombreImputado el valor nombreImputado a asignar
	 */
	public void setNombreImputado(String nombreImputado) {
		this.nombreImputado = nombreImputado;
	}

	/**
	 * Regresa el valor de la propiedad apellidoPaternoImputado
	 * @return the apellidoPaternoImputado
	 */
	public String getApellidoPaternoImputado() {
		return apellidoPaternoImputado;
	}
	
	/**
	 * Establece el valor de la propiedad apellidoPaternoImputado
	 * @param apellidoPaternoImputado valo apellidoPaternoImputado a almacenar
	 */
	public void setApellidoPaternoImputado(String apellidoPaternoImputado) {
		this.apellidoPaternoImputado = apellidoPaternoImputado;
	}
	
	/**
	 * Regresa el valor de la propiedad apellidoMaternoImputado
	 * @return the apellidoMaternoImputado
	 */
	public String getApellidoMaternoImputado() {
		return apellidoMaternoImputado;
	}

	/**
	 * Establece el valor de la propiedad apellidoMaternoImputado
	 * @param apellidoMaternoImputado valo apellidoMaternoImputado a almacenar
	 */
	public void setApellidoMaternoImputado(String apellidoMaternoImputado) {
		this.apellidoMaternoImputado = apellidoMaternoImputado;
	}

	/**
	 * Regresa el valor de la propiedad esDetenido
	 * @return the esDetenido
	 */
	public Boolean getEsDetenido() {
		return esDetenido;
	}
	
	/**
	 * Establece el valor de la propiedad esDetenido
	 * @param esDetenido valo esDetenido a almacenar
	 */
	public void setEsDetenido(Boolean esDetenido) {
		this.esDetenido = esDetenido;
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
	
}

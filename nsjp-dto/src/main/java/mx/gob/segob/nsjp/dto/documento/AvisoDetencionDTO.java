/**
 * Nombre del Programa  : RegistrarObjetoEnAudienciaService.java
 * Autor                : Hugo Serrano
 * Compania             : Ultrasist
 * Proyecto             : NSJP                    Fecha: 24 Jun 2011
 * Marca de cambio      : N/A
 * Descripcion General  : Contiene la información de una Detención.
 * Programa Dependiente : N/A
 * Programa Subsecuente : N/A
 * Cond. de ejecucion   : N/A
 * Dias de ejecucion    : N/A                     Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                : Daniel Jiménez
 * Compania             : Tattva-It
 * Proyecto             : NSJP                    Fecha: 03 Ago 2011
 * Modificacion         : Se cambiaron las propiedades del objeto para que
 * 						  cumplan con la especificación del negocio
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.dto.documento;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.involucrado.DetencionDTO;

/**
 * Objeto de Aviso de Detencion
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class AvisoDetencionDTO extends NotificacionDTO {
	
    private String detenido;
    private Date fechaDetencion;
    private DetencionDTO detencion;
    private List<DelitoDTO> delitos = new LinkedList<DelitoDTO>();

    /**
	 * Regresa el valor de la propiedad detenido
	 * @return the detenido
	 */
	public String getDetenido() {
		return detenido;
	}

	/**
	 * Establece el valor de la propiedad detenido
	 * @param detenido valo detenido a almacenar
	 */
	public void setDetenido(String detenido) {
		this.detenido = detenido;
	}

	/**
	 * Regresa el valor de la propiedad fechaDetencion
	 * @return the fechaDetencion
	 */
	public Date getFechaDetencion() {
		return fechaDetencion;
	}

	/**
	 * Establece el valor de la propiedad fechaDetencion
	 * @param fechaDetencion valo fechaDetencion a almacenar
	 */
	public void setFechaDetencion(Date fechaDetencion) {
		this.fechaDetencion = fechaDetencion;
	}

	/**
	 * Regresa el valor de la propiedad detencion
	 * @return the detencion
	 */
	public DetencionDTO getDetencion() {
		return detencion;
	}

	/**
	 * Establece el valor de la propiedad detencion
	 * @param detencion valo detencion a almacenar
	 */
	public void setDetencion(DetencionDTO detencion) {
		this.detencion = detencion;
	}

	/**
	 * Regresa el valor de la propiedad delitos
	 * @return the delitos
	 */
	public List<DelitoDTO> getDelitos() {
		return delitos;
	}

	/**
	 * Establece el valor de la propiedad delitos
	 * @param delitos valo delitos a almacenar
	 */
	public void setDelitos(List<DelitoDTO> delitos) {
		this.delitos = delitos;
	}
}
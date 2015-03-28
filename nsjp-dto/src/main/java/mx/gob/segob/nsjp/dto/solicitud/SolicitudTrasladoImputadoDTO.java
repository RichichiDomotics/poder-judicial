/**
* Nombre del Programa : SolicitudTrasladoImputadoDTO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 13 Sep 2011
* Marca de cambio        : N/A
* Descripcion General    : DTO para la solicitud de traslado de imputado
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

import java.util.Date;

import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;

/**
 * DTO para la solicitud de traslado de imputado.
 * @author cesarAgustin
 *
 */
public class SolicitudTrasladoImputadoDTO extends SolicitudDTO {	

	// Fields
	private Date fechaTraslado;
	private Double tiempo;
	
    private InvolucradoDTO involucrado;
//    private Sitio sitioOrigen;
//    private Sitio sitioDestino;

    
    @Deprecated
    private AudienciaDTO audiencia;
	public SolicitudTrasladoImputadoDTO(Long idSoltraslado) {
		super();
		setDocumentoId(idSoltraslado);		
	}
	
	public SolicitudTrasladoImputadoDTO() {
		
	}

	public Date getFechaTraslado() {
		return fechaTraslado;
	}

	public void setFechaTraslado(Date fechaTraslado) {
		this.fechaTraslado = fechaTraslado;
	}

	public Double getTiempo() {
		return tiempo;
	}

	public void setTiempo(Double tiempo) {
		this.tiempo = tiempo;
	}

	/**
	 * @return the involucrado
	 */
	public InvolucradoDTO getInvolucrado() {
		return involucrado;
	}
	/**
	 * @param involucrado the involucrado to set
	 */
	public void setInvolucrado(InvolucradoDTO involucrado) {
		this.involucrado = involucrado;
	}
	/**
	 * @return the audiencia
	 */
	public AudienciaDTO getAudiencia() {
		return audiencia;
	}
	/**
	 * @param audiencia the audiencia to set
	 */
	public void setAudiencia(AudienciaDTO audiencia) {
		this.audiencia = audiencia;
	}
        
}

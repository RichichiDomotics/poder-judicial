/**
*
* Nombre del Programa : FiltroExpedienteDTO.java                                    
* Autor                            : Cesar Agustin                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 05/04/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : DTO para la transferencia de parametros de Expediente entre la vista y servicios.                      
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
package mx.gob.segob.nsjp.dto.expediente;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.dto.caso.FiltroCasoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * @author CesarAgustin
 * @version 1.0
 *
 */
public class FiltroExpedienteDTO extends FiltroCasoDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6250765994676470357L;
	
	
	private Long identificadorEvidencia;
	private String nombreEvidencia;
	private List<String> palabrasClave = new ArrayList<String>();
	private Long anio;
	private Long idActividad;
	private Long idArea;
	private UsuarioDTO usuario;
	
	/**
	 * @param nombre
	 * @param apellidoPaterno
	 * @param apellidoMaterno
	 * @param numeroExpediente
	 * @param identificadorEvidencia
	 * @param palabrasClave
	 * @param alias
	 */
	public FiltroExpedienteDTO(Long identificadorEvidencia, List<String> palabrasClave) {
		super();		
		this.identificadorEvidencia = identificadorEvidencia;
		this.palabrasClave = palabrasClave;		
	}
	
	/**
	 * 
	 */
	public FiltroExpedienteDTO() {
		super();		
	}
	
	
	/**
	 * @return the identificadorEvidencia
	 */
	public Long getIdentificadorEvidencia() {
		return identificadorEvidencia;
	}
	/**
	 * @param identificadorEvidencia the identificadorEvidencia to set
	 */
	public void setIdentificadorEvidencia(Long identificadorEvidencia) {
		this.identificadorEvidencia = identificadorEvidencia;
	}
	/**
	 * @return the palabrasClave
	 */
	public List<String> getPalabrasClave() {
		return palabrasClave;
	}
	/**
	 * @param palabrasClave the palabrasClave to set
	 */
	public void setPalabrasClave(List<String> palabrasClave) {
		this.palabrasClave = palabrasClave;
	}

	/**
	 * Método de acceso al campo nombreEvidencia.
	 * @return El valor del campo nombreEvidencia
	 */
	public String getNombreEvidencia() {
		return nombreEvidencia;
	}

	/**
	 * Asigna el valor al campo nombreEvidencia.
	 * @param nombreEvidencia el valor nombreEvidencia a asignar
	 */
	public void setNombreEvidencia(String nombreEvidencia) {
		this.nombreEvidencia = nombreEvidencia;
	}

	/**
	 * 
	 * @return
	 */
	public Long getAnio() {
		return anio;
	}
 
	/**
	 * 
	 * @param anio
	 */
	public void setAnio(Long anio) {
		this.anio = anio;
	}

	/**
	 * 
	 * @return
	 */
	public Long getIdActividad() {
		return idActividad;
	}

	/**
	 * 
	 * @param idActividad
	 */
	public void setIdActividad(Long idActividad) {
		this.idActividad = idActividad;
	}

	/**
	 * 
	 * @return
	 */
	public Long getIdArea() {
		return idArea;
	}

	/**
	 * 
	 * @param idArea
	 */
	public void setIdArea(Long idArea) {
		this.idArea = idArea;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	
}

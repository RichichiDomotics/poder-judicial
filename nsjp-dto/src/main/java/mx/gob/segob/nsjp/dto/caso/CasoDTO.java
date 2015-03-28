/**
* Nombre del Programa : CasoDTO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 15 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : DTO para la transferencia de parametros de Caso entre la vista y servicios
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
package mx.gob.segob.nsjp.dto.caso;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.caso.EstatusCaso;
import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * DTO para la transferencia de parametros de Caso entre la vista y servicios.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class CasoDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5443547919300667251L;
	
	private Long casoId;
 	private String numeroGeneralCaso;	
	private Date fechaApertura;
	private Date fechaCierre;
	private String imputado;
	private String victima;
	/**
	 * @deprecated
	 */
	private String delito;
	private EstatusCaso estatus;
	private List<ExpedienteDTO> expedintesDTO;
	
			
	/**
	 * 
	 */
	public CasoDTO() {
		super();
	}

	/**
	 * @param 
	 */
	public CasoDTO(Long casoId) {
		this.casoId = casoId;
	}
	
	/**
	 * @param 
	 */
	public CasoDTO(Long casoId, UsuarioDTO usr) {
		this.casoId = casoId;
		setUsuario(usr);
	}
	
    public CasoDTO(Long casoId, String noCaso) {
        this.casoId = casoId;
        this.numeroGeneralCaso = noCaso;
    }	
	
	/**
	 * Método de acceso al campo casoId.
	 * @return El valor del campo casoId
	 */
	public Long getCasoId() {
		return casoId;
	}
	/**
	 * Asigna el valor al campo casoId.
	 * @param casoId el valor casoId a asignar
	 */
	public void setCasoId(Long casoId) {
		this.casoId = casoId;
	}
	/**
	 * Método de acceso al campo numeroGeneralCaso.
	 * @return El valor del campo numeroGeneralCaso
	 */
	public String getNumeroGeneralCaso() {
		return numeroGeneralCaso;
	}
	/**
	 * Asigna el valor al campo numeroGeneralCaso.
	 * @param numeroGeneralCaso el valor numeroGeneralCaso a asignar
	 */
	public void setNumeroGeneralCaso(String numeroGeneralCaso) {
		this.numeroGeneralCaso = numeroGeneralCaso;
	}
	/**
	 * Método de acceso al campo fechaApertura.
	 * @return El valor del campo fechaApertura
	 */
	public Date getFechaApertura() {
		return fechaApertura;
	}
	/**
	 * Asigna el valor al campo fechaApertura.
	 * @param fechaApertura el valor fechaApertura a asignar
	 */
	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	} 
	/**
	 * Método de acceso al campo fechaCierre.
	 * @return El valor del campo fechaCierre
	 */
	public Date getFechaCierre() {
		return fechaCierre;
	}
	/**
	 * Asigna el valor al campo fechaCierre.
	 * @param fechaCierre el valor fechaCierre a asignar
	 */
	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}	
	/**
	 * Método de acceso al campo imputado.
	 * @return El valor del campo imputado
	 */
	public String getImputado() {
		return imputado;
	}
	/**
	 * Asigna el valor al campo imputado.
	 * @param imputado el valor imputado a asignar
	 */
	public void setImputado(String imputado) {
		this.imputado = imputado;
	}
	/**
	 * Método de acceso al campo victima.
	 * @return El valor del campo victima
	 */
	public String getVictima() {
		return victima;
	}
	/**
	 * Asigna el valor al campo victima.
	 * @param victima el valor victima a asignar
	 */
	public void setVictima(String victima) {
		this.victima = victima;
	}
	/**
	 * Método de acceso al campo delito.
	 * @return El valor del campo delito
	 * @deprecated
	 */
	public String getDelito() {
		return delito;
	}
	/**
	 * Asigna el valor al campo delito.
	 * @param delito el valor delito a asignar
	 * @deprecated
	 */
	public void setDelito(String delito) {
		this.delito = delito;
	}
	/**
	 * Método de acceso al campo estatus.
	 * @return El valor del campo estatus
	 */
	public EstatusCaso getEstatus() {
		return estatus;
	}
	/**
	 * Asigna el valor al campo estatus.
	 * @param estatus el valor estatus a asignar
	 */
	public void setEstatus(EstatusCaso estatus) {
		this.estatus = estatus;
	}

	/**
	 * Método de acceso al campo expedintesDTO.
	 * @return El valor del campo expedintesDTO
	 */
	public List<ExpedienteDTO> getExpedintesDTO() {
		return expedintesDTO;
	}

	/**
	 * Asigna el valor al campo expedintesDTO.
	 * @param expedintesDTO el valor expedintesDTO a asignar
	 */
	public void setExpedintesDTO(List<ExpedienteDTO> expedintesDTO) {
		this.expedintesDTO = expedintesDTO;
	}	
	
}

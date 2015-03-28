/**
*
* Nombre del Programa : DomicilioWSDTO.java                                    
* Autor                            : GustavoBP                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 22/07/2012 
* Marca de cambio        : N/A                                                     
* Descripcion General    : DTO de intercambio entre sistemas para transportar los datos básicos de un domicilio                     
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

package mx.gob.segob.nsjp.dto.domicilio;

/**
 * DTO de intercambio entre sistemas para transportar los datos básicos de un domicilio
 * @author GustavoBP
 * @version 1.0	
 */
public class DomicilioWSDTO extends LugarWSDTO {

	private String calle;
	private String numeroExterior;
	private String numeroInterior;
	private String numeroLote;
	private String referencias;
	private String entreCalle1;
	private String entreCalle2;
	private String alias;
	private String edificio;
	private Long valorCalleId;	
	private Long entidadId;
	private Long asentamientoId;
	private Long municipioId;
	private Long ciudadId;
	
	public DomicilioWSDTO(String calle, String numeroExterior,
			String numeroInterior, String numeroLote, String referencias,
			String entreCalle1, String entreCalle2, String alias,
			String edificio, Boolean esNotificacion) {
		super();		
		this.calle = calle;
		this.numeroExterior = numeroExterior;
		this.numeroInterior = numeroInterior;
		this.numeroLote = numeroLote;
		this.referencias = referencias;
		this.entreCalle1 = entreCalle1;
		this.entreCalle2 = entreCalle2;
		this.alias = alias;
		this.edificio = edificio;		
		this.edificio = edificio;
	}
	
	/**
	 * 
	 */
	public DomicilioWSDTO() {
		
	}
		
	/**
	 * @return the calle
	 */
	public String getCalle() {
		return calle;
	}
	/**
	 * @param calle the calle to set
	 */
	public void setCalle(String calle) {
		this.calle = calle;
	}
	/**
	 * @return the numeroExterior
	 */
	public String getNumeroExterior() {
		return numeroExterior;
	}
	/**
	 * @param numeroExterior the numeroExterior to set
	 */
	public void setNumeroExterior(String numeroExterior) {
		this.numeroExterior = numeroExterior;
	}
	/**
	 * @return the numeroInterior
	 */
	public String getNumeroInterior() {
		return numeroInterior;
	}
	/**
	 * @param numeroInterior the numeroInterior to set
	 */
	public void setNumeroInterior(String numeroInterior) {
		this.numeroInterior = numeroInterior;
	}
	/**
	 * @return the numeroLote
	 */
	public String getNumeroLote() {
		return numeroLote;
	}
	/**
	 * @param numeroLote the numeroLote to set
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}
	/**
	 * @return the referencias
	 */
	public String getReferencias() {
		return referencias;
	}
	/**
	 * @param referencias the referencias to set
	 */
	public void setReferencias(String referencias) {
		this.referencias = referencias;
	}
	/**
	 * @return the entreCalle1
	 */
	public String getEntreCalle1() {
		return entreCalle1;
	}
	/**
	 * @param entreCalle1 the entreCalle1 to set
	 */
	public void setEntreCalle1(String entreCalle1) {
		this.entreCalle1 = entreCalle1;
	}
	/**
	 * @return the entreCalle2
	 */
	public String getEntreCalle2() {
		return entreCalle2;
	}
	/**
	 * @param entreCalle2 the entreCalle2 to set
	 */
	public void setEntreCalle2(String entreCalle2) {
		this.entreCalle2 = entreCalle2;
	}
	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}
	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}
	/**
	 * @return the edificio
	 */
	public String getEdificio() {
		return edificio;
	}
	/**
	 * @param edificio the edificio to set
	 */
	public void setEdificio(String edificio) {
		this.edificio = edificio;
	}

	/**
	 * Método de acceso al campo asentamientoDTO.
	 * @return El valor del campo asentamientoDTO
	 */
	public Long getAsentamientoId() {
		return asentamientoId;
	}

	/**
	 * Asigna el valor al campo asentamientoDTO.
	 * @param asentamientoDTO el valor asentamientoDTO a asignar
	 */
	public void setAsentamientoId(Long asentamientoId) {
		this.asentamientoId = asentamientoId;
	}

	/**
	 * Método de acceso al campo valorCalleId.
	 * @return El valor del campo valorCalleId
	 */
	public Long getValorCalleId() {
		return valorCalleId;
	}

	/**
	 * Asigna el valor al campo valorCalleId.
	 * @param valorCalleId el valor valorCalleId a asignar
	 */
	public void setValorCalleId(Long valorCalleId) {
		this.valorCalleId = valorCalleId;
	}

	/**
	 * Método de acceso al campo entidadDTO.
	 * @return El valor del campo entidadDTO
	 */
	public Long getEntidadId() {
		return entidadId;
	}

	/**
	 * Asigna el valor al campo entidadDTO.
	 * @param entidadDTO el valor entidadDTO a asignar
	 */
	public void setEntidadId(Long entidadId) {
		this.entidadId = entidadId;
	}

	/**
	 * Método de acceso al campo municipioDTO.
	 * @return El valor del campo municipioDTO
	 */
	public Long getMunicipioId() {
		return municipioId;
	}

	/**
	 * Asigna el valor al campo municipioDTO.
	 * @param municipioDTO el valor municipioDTO a asignar
	 */
	public void setMunicipioId(Long municipioId) {
		this.municipioId = municipioId;
	}

	/**
	 * Método de acceso al campo ciudadDTO.
	 * @return El valor del campo ciudadDTO
	 */
	public Long getCiudadId() {
		return ciudadId;
	}

	/**
	 * Asigna el valor al campo ciudadDTO.
	 * @param ciudadDTO el valor ciudadDTO a asignar
	 */
	public void setCiudadId(Long ciudadId) {
		this.ciudadId = ciudadId;
	}

	
	
	
	
}

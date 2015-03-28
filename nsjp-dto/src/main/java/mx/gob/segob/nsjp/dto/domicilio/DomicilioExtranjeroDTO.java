/**
* Nombre del Programa : DomicilioExtranjeroDTO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 11 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : Clase para la transferencia de datos entre la vista y el negocio, del objeto domicilioExtranjero
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
package mx.gob.segob.nsjp.dto.domicilio;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;


/**
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto domicilioExtranjero.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class DomicilioExtranjeroDTO extends DomicilioDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8635626691401703373L;
	
	private Long domicilioExtranjeroId;	
	private ValorDTO paisValor;
	private String codigoPostal;
	private String asentamientoExt;
	private String municipio;
	private String ciudad;
	private String estado;
	private String pais;
	
	/**
	 * @param domicilioId
	 * @param calle
	 * @param numeroExterior
	 * @param numeroInterior
	 * @param numeroLote
	 * @param referencias
	 * @param entreCalle1
	 * @param entreCalle2
	 * @param alias
	 * @param edificio
	 * @param esNotificacion
	 * @param domicilioExtranjeroId
	 * @param codigoPostal
	 * @param asentamientoExt
	 * @param municipio
	 * @param ciudad
	 * @param estado
	 * @param pais
	 */
	public DomicilioExtranjeroDTO(String calle,
			String numeroExterior, String numeroInterior, String numeroLote,
			String referencias, String entreCalle1, String entreCalle2,
			String alias, String edificio, Boolean esNotificacion,
			Long domicilioExtranjeroId, String codigoPostal,
			String asentamientoExt, String municipio, String ciudad,
			String estado, String pais) {
		super(calle, numeroExterior, numeroInterior, numeroLote,
				referencias, entreCalle1, entreCalle2, alias, edificio,
				esNotificacion);
		this.domicilioExtranjeroId = domicilioExtranjeroId;
		this.codigoPostal = codigoPostal;
		this.asentamientoExt = asentamientoExt;
		this.municipio = municipio;
		this.ciudad = ciudad;
		this.estado = estado;
		this.pais = pais;
	}
	
	/**
	 * 
	 */
	public DomicilioExtranjeroDTO() {
		super();		
	}

	/**
	 * Método de acceso al campo domicilioExtranjeroId.
	 * @return El valor del campo domicilioExtranjeroId
	 */
	public Long getDomicilioExtranjeroId() {
		return domicilioExtranjeroId;
	}

	/**
	 * Asigna el valor al campo domicilioExtranjeroId.
	 * @param domicilioExtranjeroId el valor domicilioExtranjeroId a asignar
	 */
	public void setDomicilioExtranjeroId(Long domicilioExtranjeroId) {
		this.domicilioExtranjeroId = domicilioExtranjeroId;
	}

	/**
	 * Método de acceso al campo codigoPostal.
	 * @return El valor del campo codigoPostal
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}

	/**
	 * Asigna el valor al campo codigoPostal.
	 * @param codigoPostal el valor codigoPostal a asignar
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	/**
	 * Método de acceso al campo asentamientoExt.
	 * @return El valor del campo asentamientoExt
	 */
	public String getAsentamientoExt() {
		return asentamientoExt;
	}

	/**
	 * Asigna el valor al campo asentamientoExt.
	 * @param asentamientoExt el valor asentamientoExt a asignar
	 */
	public void setAsentamientoExt(String asentamientoExt) {
		this.asentamientoExt = asentamientoExt;
	}

	/**
	 * Método de acceso al campo municipio.
	 * @return El valor del campo municipio
	 */
	public String getMunicipio() {
		return municipio;
	}

	/**
	 * Asigna el valor al campo municipio.
	 * @param municipio el valor municipio a asignar
	 */
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	/**
	 * Método de acceso al campo ciudad.
	 * @return El valor del campo ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * Asigna el valor al campo ciudad.
	 * @param ciudad el valor ciudad a asignar
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * Método de acceso al campo estado.
	 * @return El valor del campo estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Asigna el valor al campo estado.
	 * @param estado el valor estado a asignar
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Método de acceso al campo pais.
	 * @return El valor del campo pais
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * Asigna el valor al campo pais.
	 * @param pais el valor pais a asignar
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * Método de acceso al campo paisValor.
	 * @return El valor del campo paisValor
	 */
	public ValorDTO getPaisValor() {
		return paisValor;
	}

	/**
	 * Asigna el valor al campo paisValor.
	 * @param paisValor el valor paisValor a asignar
	 */
	public void setPaisValor(ValorDTO paisValor) {
		this.paisValor = paisValor;
	}	
	
	

}

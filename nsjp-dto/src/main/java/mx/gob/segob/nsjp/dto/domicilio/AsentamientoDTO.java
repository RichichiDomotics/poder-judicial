/**
* Nombre del Programa : AsentamientoDTO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 11 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : Clase para la transferencia de datos entre la vista y el negocio, del objeto asentamiento
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

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto asentamiento.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class AsentamientoDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6944635533055222335L;

	private Long asentamientoId;
	private String codigoPostal;
	private String nombreAsentamiento;
	private TipoAsentamientoDTO tipoAsentamientoDTO;
	private CiudadDTO ciudadDTO;
	private MunicipioDTO municipioDTO;
	/**
	 * @param asentamientoId
	 * @param codigoPostal
	 * @param nombreAsentamiento
	 * @param tipoAsentamientoDTO
	 * @param ciudadDTO
	 * @param municipioDTO
	 */
	public AsentamientoDTO(Long asentamientoId, String codigoPostal,
			String nombreAsentamiento, TipoAsentamientoDTO tipoAsentamientoDTO,
			CiudadDTO ciudadDTO, MunicipioDTO municipioDTO) {
		super();
		this.asentamientoId = asentamientoId;
		this.codigoPostal = codigoPostal;
		this.nombreAsentamiento = nombreAsentamiento;
		this.tipoAsentamientoDTO = tipoAsentamientoDTO;
		this.ciudadDTO = ciudadDTO;
		this.municipioDTO = municipioDTO;
	}
	
	/**
	 * 
	 */
	public AsentamientoDTO() {
		super();		
	}

	public AsentamientoDTO(Long asentamientoId) {
		super();
		this.asentamientoId = asentamientoId;
	}

	
	/**
	 * Método de acceso al campo asentamientoId.
	 * @return El valor del campo asentamientoId
	 */
	public Long getAsentamientoId() {
		return asentamientoId;
	}

	/**
	 * Asigna el valor al campo asentamientoId.
	 * @param asentamientoId el valor asentamientoId a asignar
	 */
	public void setAsentamientoId(Long asentamientoId) {
		this.asentamientoId = asentamientoId;
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
	 * Método de acceso al campo nombreAsentamiento.
	 * @return El valor del campo nombreAsentamiento
	 */
	public String getNombreAsentamiento() {
		return nombreAsentamiento;
	}

	/**
	 * Asigna el valor al campo nombreAsentamiento.
	 * @param nombreAsentamiento el valor nombreAsentamiento a asignar
	 */
	public void setNombreAsentamiento(String nombreAsentamiento) {
		this.nombreAsentamiento = nombreAsentamiento;
	}

	/**
	 * Método de acceso al campo tipoAsentamientoDTO.
	 * @return El valor del campo tipoAsentamientoDTO
	 */
	public TipoAsentamientoDTO getTipoAsentamientoDTO() {
		return tipoAsentamientoDTO;
	}

	/**
	 * Asigna el valor al campo tipoAsentamientoDTO.
	 * @param tipoAsentamientoDTO el valor tipoAsentamientoDTO a asignar
	 */
	public void setTipoAsentamientoDTO(TipoAsentamientoDTO tipoAsentamientoDTO) {
		this.tipoAsentamientoDTO = tipoAsentamientoDTO;
	}

	/**
	 * Método de acceso al campo ciudadDTO.
	 * @return El valor del campo ciudadDTO
	 */
	public CiudadDTO getCiudadDTO() {
		return ciudadDTO;
	}

	/**
	 * Asigna el valor al campo ciudadDTO.
	 * @param ciudadDTO el valor ciudadDTO a asignar
	 */
	public void setCiudadDTO(CiudadDTO ciudadDTO) {
		this.ciudadDTO = ciudadDTO;
	}

	/**
	 * Método de acceso al campo municipioDTO.
	 * @return El valor del campo municipioDTO
	 */
	public MunicipioDTO getMunicipioDTO() {
		return municipioDTO;
	}

	/**
	 * Asigna el valor al campo municipioDTO.
	 * @param municipioDTO el valor municipioDTO a asignar
	 */
	public void setMunicipioDTO(MunicipioDTO municipioDTO) {
		this.municipioDTO = municipioDTO;
	}		
	
}

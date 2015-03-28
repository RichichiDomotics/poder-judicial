/**
* Nombre del Programa : PerfilDTO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 8 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : Clase para la transferencia de datos entre la vista y el negocio, del objeto perfil.
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
package mx.gob.segob.nsjp.dto.involucrado;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto perfil.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class PerfilDTO extends GenericDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3505256286675154169L;

	private Long perfilId;	
	private String filias;
	private String fobias;
	private String adicciones;
	private String preferenciaSexual;
	private String paranoias;
	private String ansiedades;
	private String depresiones;
	private String perfilPsicologico;
	private InvolucradoDTO involucradoDTO;
	
	/**
	 * 
	 */
	public PerfilDTO() {
		super();
	}	

	/**
	 * @param perfilId
	 * @param filias
	 * @param fobias
	 * @param adicciones
	 * @param preferenciaSexual
	 * @param paranoias
	 * @param ansiedades
	 * @param depresiones
	 * @param perfilPsicologico
	 * @param involucradoDTO
	 */
	public PerfilDTO(Long perfilId, String filias, String fobias,
			String adicciones, String preferenciaSexual, String paranoias,
			String ansiedades, String depresiones, String perfilPsicologico,
			InvolucradoDTO involucradoDTO) {
		super();
		this.perfilId = perfilId;
		this.filias = filias;
		this.fobias = fobias;
		this.adicciones = adicciones;
		this.preferenciaSexual = preferenciaSexual;
		this.paranoias = paranoias;
		this.ansiedades = ansiedades;
		this.depresiones = depresiones;
		this.perfilPsicologico = perfilPsicologico;
		this.involucradoDTO = involucradoDTO;
	}

	/**
	 * Método de acceso al campo perfilId.
	 * @return El valor del campo perfilId
	 */
	public Long getPerfilId() {
		return perfilId;
	}

	/**
	 * Asigna el valor al campo perfilId.
	 * @param perfilId el valor perfilId a asignar
	 */
	public void setPerfilId(Long perfilId) {
		this.perfilId = perfilId;
	}

	/**
	 * Método de acceso al campo filias.
	 * @return El valor del campo filias
	 */
	public String getFilias() {
		return filias;
	}

	/**
	 * Asigna el valor al campo filias.
	 * @param filias el valor filias a asignar
	 */
	public void setFilias(String filias) {
		this.filias = filias;
	}

	/**
	 * Método de acceso al campo fobias.
	 * @return El valor del campo fobias
	 */
	public String getFobias() {
		return fobias;
	}

	/**
	 * Asigna el valor al campo fobias.
	 * @param fobias el valor fobias a asignar
	 */
	public void setFobias(String fobias) {
		this.fobias = fobias;
	}

	/**
	 * Método de acceso al campo adicciones.
	 * @return El valor del campo adicciones
	 */
	public String getAdicciones() {
		return adicciones;
	}

	/**
	 * Asigna el valor al campo adicciones.
	 * @param adicciones el valor adicciones a asignar
	 */
	public void setAdicciones(String adicciones) {
		this.adicciones = adicciones;
	}

	/**
	 * Método de acceso al campo preferenciaSexual.
	 * @return El valor del campo preferenciaSexual
	 */
	public String getPreferenciaSexual() {
		return preferenciaSexual;
	}

	/**
	 * Asigna el valor al campo preferenciaSexual.
	 * @param preferenciaSexual el valor preferenciaSexual a asignar
	 */
	public void setPreferenciaSexual(String preferenciaSexual) {
		this.preferenciaSexual = preferenciaSexual;
	}

	/**
	 * Método de acceso al campo paranoias.
	 * @return El valor del campo paranoias
	 */
	public String getParanoias() {
		return paranoias;
	}

	/**
	 * Asigna el valor al campo paranoias.
	 * @param paranoias el valor paranoias a asignar
	 */
	public void setParanoias(String paranoias) {
		this.paranoias = paranoias;
	}

	/**
	 * Método de acceso al campo ansiedades.
	 * @return El valor del campo ansiedades
	 */
	public String getAnsiedades() {
		return ansiedades;
	}

	/**
	 * Asigna el valor al campo ansiedades.
	 * @param ansiedades el valor ansiedades a asignar
	 */
	public void setAnsiedades(String ansiedades) {
		this.ansiedades = ansiedades;
	}

	/**
	 * Método de acceso al campo depresiones.
	 * @return El valor del campo depresiones
	 */
	public String getDepresiones() {
		return depresiones;
	}

	/**
	 * Asigna el valor al campo depresiones.
	 * @param depresiones el valor depresiones a asignar
	 */
	public void setDepresiones(String depresiones) {
		this.depresiones = depresiones;
	}

	/**
	 * Método de acceso al campo perfilPsicologico.
	 * @return El valor del campo perfilPsicologico
	 */
	public String getPerfilPsicologico() {
		return perfilPsicologico;
	}

	/**
	 * Asigna el valor al campo perfilPsicologico.
	 * @param perfilPsicologico el valor perfilPsicologico a asignar
	 */
	public void setPerfilPsicologico(String perfilPsicologico) {
		this.perfilPsicologico = perfilPsicologico;
	}

	/**
	 * Método de acceso al campo involucradoDTO.
	 * @return El valor del campo involucradoDTO
	 */
	public InvolucradoDTO getInvolucradoDTO() {
		return involucradoDTO;
	}

	/**
	 * Asigna el valor al campo involucradoDTO.
	 * @param involucradoDTO el valor involucradoDTO a asignar
	 */
	public void setInvolucradoDTO(InvolucradoDTO involucradoDTO) {
		this.involucradoDTO = involucradoDTO;
	}	

}

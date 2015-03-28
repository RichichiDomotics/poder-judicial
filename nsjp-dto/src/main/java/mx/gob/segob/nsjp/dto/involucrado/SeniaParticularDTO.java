/**
* Nombre del Programa : SeniaParticularDTO.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 08/07/2011
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
package mx.gob.segob.nsjp.dto.involucrado;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto SeniaParticular.
 * @version 1.0
 * @author GustavoBP
 *
 */
public class SeniaParticularDTO  extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 435940199181092182L;

	private Long seniaParticularId;

	private Boolean tieneCicatrices;
	private String descripcionCicatrices;
	
	private Boolean tieneProtesis;
	private String descripcionProtesis;
	
	private Boolean tieneTatuajes;
	private String descripcionTatuajes;
	
	private Boolean tieneLunares;
	private String descripcionLunares;
	
	private Boolean tieneDefectosFisicos;
	private String descripcionDefectosFisicos;

	private Boolean tieneOtraSenia;
	private String descripcionOtraSenia;

	 // Constructors

    /** default constructor */
	public SeniaParticularDTO() {
	
	}
	
	/**
	 * 
	 * @param seniaParticularId
	 */
	public SeniaParticularDTO(Long seniaParticularId ){
		this.seniaParticularId = seniaParticularId;
	}

	public Long getSeniaParticularId() {
		return seniaParticularId;
	}

	public void setSeniaParticularId(Long seniaParticularId) {
		this.seniaParticularId = seniaParticularId;
	}

	public Boolean getTieneCicatrices() {
		return tieneCicatrices;
	}

	public void setTieneCicatrices(Boolean tieneCicatrices) {
		this.tieneCicatrices = tieneCicatrices;
	}

	public String getDescripcionCicatrices() {
		return descripcionCicatrices;
	}

	public void setDescripcionCicatrices(String descripcionCicatrices) {
		this.descripcionCicatrices = descripcionCicatrices;
	}

	public Boolean getTieneProtesis() {
		return tieneProtesis;
	}

	public void setTieneProtesis(Boolean tieneProtesis) {
		this.tieneProtesis = tieneProtesis;
	}

	public String getDescripcionProtesis() {
		return descripcionProtesis;
	}

	public void setDescripcionProtesis(String descripcionProtesis) {
		this.descripcionProtesis = descripcionProtesis;
	}

	public Boolean getTieneTatuajes() {
		return tieneTatuajes;
	}

	public void setTieneTatuajes(Boolean tieneTatuajes) {
		this.tieneTatuajes = tieneTatuajes;
	}

	public String getDescripcionTatuajes() {
		return descripcionTatuajes;
	}

	public void setDescripcionTatuajes(String descripcionTatuajes) {
		this.descripcionTatuajes = descripcionTatuajes;
	}

	public Boolean getTieneLunares() {
		return tieneLunares;
	}

	public void setTieneLunares(Boolean tieneLunares) {
		this.tieneLunares = tieneLunares;
	}

	public String getDescripcionLunares() {
		return descripcionLunares;
	}

	public void setDescripcionLunares(String descripcionLunares) {
		this.descripcionLunares = descripcionLunares;
	}

	public Boolean getTieneDefectosFisicos() {
		return tieneDefectosFisicos;
	}

	public void setTieneDefectosFisicos(Boolean tieneDefectosFisicos) {
		this.tieneDefectosFisicos = tieneDefectosFisicos;
	}

	public String getDescripcionDefectosFisicos() {
		return descripcionDefectosFisicos;
	}

	public void setDescripcionDefectosFisicos(String descripcionDefectosFisicos) {
		this.descripcionDefectosFisicos = descripcionDefectosFisicos;
	}

	public Boolean getTieneOtraSenia() {
		return tieneOtraSenia;
	}

	public void setTieneOtraSenia(Boolean tieneOtraSenia) {
		this.tieneOtraSenia = tieneOtraSenia;
	}

	public String getDescripcionOtraSenia() {
		return descripcionOtraSenia;
	}

	public void setDescripcionOtraSenia(String descripcionOtraSenia) {
		this.descripcionOtraSenia = descripcionOtraSenia;
	}
	
}

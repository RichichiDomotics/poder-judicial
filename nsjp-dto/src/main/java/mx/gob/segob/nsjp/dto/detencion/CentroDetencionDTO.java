/**
* Nombre del Programa : CentroDetencionDTO.java
* Autor                            : Cuauhtemoc
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 22/09/2011
* Marca de cambio        : N/A
* Descripcion General    : DetencionDTO
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
package mx.gob.segob.nsjp.dto.detencion;

import java.util.HashSet;
import java.util.Set;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;

public class CentroDetencionDTO {

	private Long centroDetencionId;
	private DireccionDTO direccion;
	private String nombre;
	private String nombreDirector;
	private Set<InvolucradoDTO> involucrados = new HashSet<InvolucradoDTO>(0);
	private ValorDTO tipo;
	/**
	 * @return the centroDetencionId
	 */
	public Long getCentroDetencionId() {
		return centroDetencionId;
	}
	/**
	 * @param centroDetencionId the centroDetencionId to set
	 */
	public void setCentroDetencionId(Long centroDetencionId) {
		this.centroDetencionId = centroDetencionId;
	}
	/**
	 * @return the direccion
	 */
	public DireccionDTO getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(DireccionDTO direccion) {
		this.direccion = direccion;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the nombreDirector
	 */
	public String getNombreDirector() {
		return nombreDirector;
	}
	/**
	 * @param nombreDirector the nombreDirector to set
	 */
	public void setNombreDirector(String nombreDirector) {
		this.nombreDirector = nombreDirector;
	}
	/**
	 * @return the involucrados
	 */
	public Set<InvolucradoDTO> getInvolucrados() {
		return involucrados;
	}
	/**
	 * @param involucrados the involucrados to set
	 */
	public void setInvolucrados(Set<InvolucradoDTO> involucrados) {
		this.involucrados = involucrados;
	}
	/**
	 * @return the tipo
	 */
	public ValorDTO getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(ValorDTO tipo) {
		this.tipo = tipo;
	}
	
	
	
}

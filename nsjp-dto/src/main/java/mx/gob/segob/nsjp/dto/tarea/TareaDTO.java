/**
* Nombre del Programa : TareaDTO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 12 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Objeto de transferencia de Tarea
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
package mx.gob.segob.nsjp.dto.tarea;

import java.util.Date;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;

/**
 * Objeto de transferencia de Tarea.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class TareaDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5927396372215357169L;

	private Long tareaId;
	/**
	 * Tipo de Tarea
	 */
	private ValorDTO valor;
	private EventoCitaDTO eventoCita;
	private Short ntiempoReal;
	private Long idFuncionario;
	private Date diaTarea;
	
	/**
	 * Método de acceso al campo tareaId.
	 * @return El valor del campo tareaId
	 */
	public Long getTareaId() {
		return tareaId;
	}
	/**
	 * Asigna el valor al campo tareaId.
	 * @param tareaId el valor tareaId a asignar
	 */
	public void setTareaId(Long tareaId) {
		this.tareaId = tareaId;
	}
	/**
	 * Método de acceso al campo valor.
	 * @return El valor del campo valor
	 */
	public ValorDTO getValor() {
		return valor;
	}
	/**
	 * Asigna el valor al campo valor.
	 * @param valor el valor valor a asignar
	 */
	public void setValor(ValorDTO valor) {
		this.valor = valor;
	}
	/**
	 * Método de acceso al campo eventoCita.
	 * @return El valor del campo eventoCita
	 */
	public EventoCitaDTO getEventoCita() {
		return eventoCita;
	}
	/**
	 * Asigna el valor al campo eventoCita.
	 * @param eventoCita el valor eventoCita a asignar
	 */
	public void setEventoCita(EventoCitaDTO eventoCita) {
		this.eventoCita = eventoCita;
	}
	/**
	 * Método de acceso al campo ntiempoReal.
	 * @return El valor del campo ntiempoReal
	 */
	public Short getNtiempoReal() {
		return ntiempoReal;
	}
	/**
	 * Asigna el valor al campo ntiempoReal.
	 * @param ntiempoReal el valor ntiempoReal a asignar
	 */
	public void setNtiempoReal(Short ntiempoReal) {
		this.ntiempoReal = ntiempoReal;
	}
	/**
	 * Método de acceso al campo idFuncionario.
	 * @return El valor del campo idFuncionario
	 */
	public Long getIdFuncionario() {
		return idFuncionario;
	}
	/**
	 * Asigna el valor al campo idFuncionario.
	 * @param idFuncionario el valor idFuncionario a asignar
	 */
	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	/**
	 * Método de acceso al campo diaTarea.
	 * @return El valor del campo diaTarea
	 */
	public Date getDiaTarea() {
		return diaTarea;
	}
	/**
	 * Asigna el valor al campo diaTarea.
	 * @param diaTarea el valor diaTarea a asignar
	 */
	public void setDiaTarea(Date diaTarea) {
		this.diaTarea = diaTarea;
	}		
	
	
}

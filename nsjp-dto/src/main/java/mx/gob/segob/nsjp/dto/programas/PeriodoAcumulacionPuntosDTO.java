/**
* Nombre del Programa : PeriodoAcumulacionPuntosDTO.java
* Autor                            : EdgarTE
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 19 Mar 2012
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
package mx.gob.segob.nsjp.dto.programas;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author EdgarTE
 *
 */
public class PeriodoAcumulacionPuntosDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 556166359568026669L;
	
	private Long periodoAcumulacionPuntosId;
	private Date dfechaInicio;
	private Date dfechaTermino;
	private String cNombrePeriodo;
	private List<ActoBuenaConductaDTO> actosBuenaConducta;
	
	/**
	 * Método de acceso al campo periodoAcumulacionPuntosId.
	 * @return El valor del campo periodoAcumulacionPuntosId
	 */
	public Long getPeriodoAcumulacionPuntosId() {
		return periodoAcumulacionPuntosId;
	}
	
	/**
	 * Asigna el valor al campo periodoAcumulacionPuntosId.
	 * @param periodoAcumulacionPuntosId el valor periodoAcumulacionPuntosId a asignar
	 */
	public void setPeriodoAcumulacionPuntosId(Long periodoAcumulacionPuntosId) {
		this.periodoAcumulacionPuntosId = periodoAcumulacionPuntosId;
	}
	
	/**
	 * Método de acceso al campo dfechaInicio.
	 * @return El valor del campo dfechaInicio
	 */
	public Date getDfechaInicio() {
		return dfechaInicio;
	}
	
	/**
	 * Asigna el valor al campo dfechaInicio.
	 * @param dfechaInicio el valor dfechaInicio a asignar
	 */
	public void setDfechaInicio(Date dfechaInicio) {
		this.dfechaInicio = dfechaInicio;
	}
	
	/**
	 * Método de acceso al campo dfechaTermino.
	 * @return El valor del campo dfechaTermino
	 */
	public Date getDfechaTermino() {
		return dfechaTermino;
	}
	
	/**
	 * Asigna el valor al campo dfechaTermino.
	 * @param dfechaTermino el valor dfechaTermino a asignar
	 */
	public void setDfechaTermino(Date dfechaTermino) {
		this.dfechaTermino = dfechaTermino;
	}
	
	/**
	 * Método de acceso al campo cNombrePeriodo.
	 * @return El valor del campo cNombrePeriodo
	 */
	public String getcNombrePeriodo() {
		return cNombrePeriodo;
	}
	
	/**
	 * Asigna el valor al campo cNombrePeriodo.
	 * @param cNombrePeriodo el valor cNombrePeriodo a asignar
	 */
	public void setcNombrePeriodo(String cNombrePeriodo) {
		this.cNombrePeriodo = cNombrePeriodo;
	}
	
	/**
	 * Método de acceso al campo actosBuenaConducta.
	 * @return El valor del campo actosBuenaConducta
	 */
	public List<ActoBuenaConductaDTO> getActosBuenaConducta() {
		return actosBuenaConducta;
	}
	
	/**
	 * Asigna el valor al campo actosBuenaConducta.
	 * @param actosBuenaConducta el valor actosBuenaConducta a asignar
	 */
	public void setActosBuenaConducta(List<ActoBuenaConductaDTO> actosBuenaConducta) {
		this.actosBuenaConducta = actosBuenaConducta;
	}
	
	/**
	 * M&eacute;todo utilitario que lleva a cabo la sumatoria de todos los puntos
	 * otorgados por actos de buena conducta que han sido acumulados en el 
	 * per&iacute;odo. 
	 * @return totalPuntos el total de puntos del per&iacute;
	 */
	public Long getTotalPuntosPeriodo(){
		long totalPuntos = 0L;
		if (getActosBuenaConducta() != null && !getActosBuenaConducta().isEmpty()){
			for (ActoBuenaConductaDTO abc : getActosBuenaConducta()){
				totalPuntos += abc.getIpuntosOtorgados();
			}
		}
		return totalPuntos;
	}
}

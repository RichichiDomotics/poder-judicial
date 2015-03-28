/**
* Nombre del Programa : ServidorPublicoDTO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 28 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Clase para la transferencia de datos entre la vista y el negocio, del objeto ServidorPublico
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
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;

/**
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto ServidorPublico.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class ServidorPublicoDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6100615355515135248L;

	private Long servidorPublicoId;	
	private ValorDTO tipoDependencia;
	private ValorDTO nivelDependencia;
	private String dependencia;
	private String puesto;
	private String numeroEmpleado;
	
	/**
	 * Método de acceso al campo servidorPublicoId.
	 * @return El valor del campo servidorPublicoId
	 */
	public Long getServidorPublicoId() {
		return servidorPublicoId;
	}
	/**
	 * Asigna el valor al campo servidorPublicoId.
	 * @param servidorPublicoId el valor servidorPublicoId a asignar
	 */
	public void setServidorPublicoId(Long servidorPublicoId) {
		this.servidorPublicoId = servidorPublicoId;
	}	
	/**
	 * Método de acceso al campo tipoDependencia.
	 * @return El valor del campo tipoDependencia
	 */
	public ValorDTO getTipoDependencia() {
		return tipoDependencia;
	}
	/**
	 * Asigna el valor al campo tipoDependencia.
	 * @param tipoDependencia el valor tipoDependencia a asignar
	 */
	public void setTipoDependencia(ValorDTO tipoDependencia) {
		this.tipoDependencia = tipoDependencia;
	}
	/**
	 * Método de acceso al campo nivelDependencia.
	 * @return El valor del campo nivelDependencia
	 */
	public ValorDTO getNivelDependencia() {
		return nivelDependencia;
	}
	/**
	 * Asigna el valor al campo nivelDependencia.
	 * @param nivelDependencia el valor nivelDependencia a asignar
	 */
	public void setNivelDependencia(ValorDTO nivelDependencia) {
		this.nivelDependencia = nivelDependencia;
	}
	/**
	 * Método de acceso al campo dependencia.
	 * @return El valor del campo dependencia
	 */
	public String getDependencia() {
		return dependencia;
	}
	/**
	 * Asigna el valor al campo dependencia.
	 * @param dependencia el valor dependencia a asignar
	 */
	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}
	/**
	 * Método de acceso al campo puesto.
	 * @return El valor del campo puesto
	 */
	public String getPuesto() {
		return puesto;
	}
	/**
	 * Asigna el valor al campo puesto.
	 * @param puesto el valor puesto a asignar
	 */
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	/**
	 * Método de acceso al campo numeroEmpleado.
	 * @return El valor del campo numeroEmpleado
	 */
	public String getNumeroEmpleado() {
		return numeroEmpleado;
	}
	/**
	 * Asigna el valor al campo numeroEmpleado.
	 * @param numeroEmpleado el valor numeroEmpleado a asignar
	 */
	public void setNumeroEmpleado(String numeroEmpleado) {
		this.numeroEmpleado = numeroEmpleado;
	}		
	
}

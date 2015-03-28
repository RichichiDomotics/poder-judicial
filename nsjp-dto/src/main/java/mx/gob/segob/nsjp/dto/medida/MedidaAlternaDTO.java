/**
* Nombre del Programa : MedidaAlternaDTO.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 05/08/2011
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
package mx.gob.segob.nsjp.dto.medida;

import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;

/**
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto Medida Alterna.
 * @version 1.0
 * @author GustavoBP
 *
 */
public class MedidaAlternaDTO extends MedidaDTO {
	
	private Short anios;
	private Short meses;
	private Long sentenciaId;
	
	private boolean guardadoDefinitivo;
	
	/**
	 * Método de acceso al campo anios.
	 * @return El valor del campo anios
	 */
	public Short getAnios() {
		return anios;
	}
	/**
	 * Asigna el valor al campo anios.
	 * @param anios el valor anios a asignar
	 */
	public void setAnios(Short anios) {
		this.anios = anios;
	}
	/**
	 * Método de acceso al campo meses.
	 * @return El valor del campo meses
	 */
	public Short getMeses() {
		return meses;
	}
	/**
	 * Asigna el valor al campo meses.
	 * @param meses el valor meses a asignar
	 */
	public void setMeses(Short meses) {
		this.meses = meses;
	}
	/**
	 * Método de acceso al campo sentenciaId.
	 * @return El valor del campo sentenciaId
	 */
	public Long getSentenciaId() {
		return sentenciaId;
	}
	/**
	 * Asigna el valor al campo sentenciaId.
	 * @param sentenciaId el valor sentenciaId a asignar
	 */
	public void setSentenciaId(Long sentenciaId) {
		this.sentenciaId = sentenciaId;
	}
	/**
	 * Método de acceso al campo guardadoDefinitivo.
	 * @return El valor del campo guardadoDefinitivo
	 */
	public boolean isGuardadoDefinitivo() {
		return guardadoDefinitivo;
	}
	/**
	 * Asigna el valor al campo guardadoDefinitivo.
	 * @param guardadoDefinitivo el valor guardadoDefinitivo a asignar
	 */
	public void setGuardadoDefinitivo(boolean guardadoDefinitivo) {
		this.guardadoDefinitivo = guardadoDefinitivo;
	}
	
	
    public boolean equals(Object obj) {
		if ((this == obj))
			return true;
		if ((obj == null))
			return false;
		if (!(obj instanceof FuncionarioDTO))
			return false;
		MedidaAlternaDTO alternaTemp = (MedidaAlternaDTO) obj;

		return ((this.getDocumentoId() == alternaTemp
				.getDocumentoId()));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (this.getDocumentoId() == null ? 0 : this
						.getDocumentoId().hashCode());
		return result;
	}
}

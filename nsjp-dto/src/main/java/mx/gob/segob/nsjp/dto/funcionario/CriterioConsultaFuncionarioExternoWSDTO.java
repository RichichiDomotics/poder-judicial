/**
* Nombre del Programa : CriterioConsultaFuncionarioExternoWSDTO.java
* Autor                            : EdgarTE
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 10 Apr 2012
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
package mx.gob.segob.nsjp.dto.funcionario;

import mx.gob.segob.nsjp.dto.base.GenericWSDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteWSDTO;
import mx.gob.segob.nsjp.dto.usuario.RolWSDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author EdgarTE
 *
 */
public class CriterioConsultaFuncionarioExternoWSDTO extends GenericWSDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -39274439664756118L;
	
	private ExpedienteWSDTO expedienteWSDTO;
	private FuncionarioWSDTO funcionarioWSDTO;
	private RolWSDTO rolWSDTO;
	
	/**
	 * Método de acceso al campo expedienteWSDTO.
	 * @return El valor del campo expedienteWSDTO
	 */
	public ExpedienteWSDTO getExpedienteWSDTO() {
		return expedienteWSDTO;
	}
	
	/**
	 * Asigna el valor al campo expedienteWSDTO.
	 * @param expedienteWSDTO el valor expedienteWSDTO a asignar
	 */
	public void setExpedienteWSDTO(ExpedienteWSDTO expedienteWSDTO) {
		this.expedienteWSDTO = expedienteWSDTO;
	}
	
	/**
	 * Método de acceso al campo funcionarioWSDTO.
	 * @return El valor del campo funcionarioWSDTO
	 */
	public FuncionarioWSDTO getFuncionarioWSDTO() {
		return funcionarioWSDTO;
	}
	
	/**
	 * Asigna el valor al campo funcionarioWSDTO.
	 * @param funcionarioWSDTO el valor funcionarioWSDTO a asignar
	 */
	public void setFuncionarioWSDTO(FuncionarioWSDTO funcionarioWSDTO) {
		this.funcionarioWSDTO = funcionarioWSDTO;
	}
	
	/**
	 * Método de acceso al campo rolWSDTO.
	 * @return El valor del campo rolWSDTO
	 */
	public RolWSDTO getRolWSDTO() {
		return rolWSDTO;
	}
	
	/**
	 * Asigna el valor al campo rolWSDTO.
	 * @param rolWSDTO el valor rolWSDTO a asignar
	 */
	public void setRolWSDTO(RolWSDTO rolWSDTO) {
		this.rolWSDTO = rolWSDTO;
	}
}

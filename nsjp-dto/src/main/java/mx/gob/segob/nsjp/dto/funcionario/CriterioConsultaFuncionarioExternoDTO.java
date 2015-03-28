/**
* Nombre del Programa 		: CriterioConsultaFuncionarioExternoDTO.java
* Autor 					: EdgarTE
* Compania 					: Ultrasist
* Proyecto 					: NSJP 							Fecha: 3 Apr 2012
* Marca de cambio        	: N/A
* Descripcion General    	: DTO para los criterios de consulta de funcionarios
* Programa Dependiente  	: N/A
* Programa Subsecuente 		: N/A
* Cond. de ejecucion 		: N/A
* Dias de ejecucion 		: N/A 							Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor 					: N/A
* Compania 					: N/A
* Proyecto 					: N/A 							Fecha: N/A
* Modificacion 				: N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.dto.funcionario;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.usuario.RolDTO;

/**
 * DTO que modela los atributos que pueden ser utilizados para llevar a cabo la
 * consulta de funcionarios por criterios.
 * @version 1.0
 * @author EdgarTE
 *
 */
public class CriterioConsultaFuncionarioExternoDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7235286943959784418L;
	
	private ExpedienteDTO expendienteDTO;
	private FuncionarioDTO funcionarioDTO;
	private RolDTO rolDTO;

	/**
	 * Método de acceso al campo expendienteDTO.
	 * @return El valor del campo expendienteDTO
	 */
	public ExpedienteDTO getExpendienteDTO() {
		return expendienteDTO;
	}

	/**
	 * Asigna el valor al campo expendienteDTO.
	 * @param expendienteDTO el valor expendienteDTO a asignar
	 */
	public void setExpendienteDTO(ExpedienteDTO expendienteDTO) {
		this.expendienteDTO = expendienteDTO;
	}

	/**
	 * Método de acceso al campo funcionarioDTO.
	 * @return El valor del campo funcionarioDTO
	 */
	public FuncionarioDTO getFuncionarioDTO() {
		return funcionarioDTO;
	}

	/**
	 * Asigna el valor al campo funcionarioDTO.
	 * @param funcionarioDTO el valor funcionarioDTO a asignar
	 */
	public void setFuncionarioDTO(FuncionarioDTO funcionarioDTO) {
		this.funcionarioDTO = funcionarioDTO;
	}

	/**
	 * Método de acceso al campo rolDTO.
	 * @return El valor del campo rolDTO
	 */
	public RolDTO getRolDTO() {
		return rolDTO;
	}

	/**
	 * Asigna el valor al campo rolDTO.
	 * @param rolDTO el valor rolDTO a asignar
	 */
	public void setRolDTO(RolDTO rolDTO) {
		this.rolDTO = rolDTO;
	}

}

/**
* Nombre del Programa : ConsultarFuncionarioPorDepartamentoServiceImplTest.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 14/07/2011
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
package mx.gob.segob.nsjp.service.test.funcionario.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.service.funcionario.ConsultarFuncionarioPorDepartamentoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
public class ConsultarFuncionarioPorDepartamentoServiceImplTest extends
		BaseTestServicios<ConsultarFuncionarioPorDepartamentoService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.funcionario.impl.ConsultarFuncionarioPorDepartamentoServiceImpl#consultarFuncionarioPorDepartamento(java.lang.Long)}.
	 */
	public void testConsultarFuncionarioPorDepartamento() {
		Long idDepartamento=7L;
		try {
			List<FuncionarioDTO> funcionarios = service.consultarFuncionarioPorDepartamento(idDepartamento);
			logger.info("Existen "+funcionarios.size()+" funcionarios");
			for (FuncionarioDTO func : funcionarios) {
				logger.info("------------------------------------------");
				logger.info("Nombre :"+func.getNombreFuncionario());
				logger.info("Apellido Paterno :"+func.getApellidoPaternoFuncionario());
				logger.info("Apellido Materno :"+func.getApellidoMaternoFuncionario()); 
				String puesto=(func.getPuesto()==null)?"Sin Puesto":func.getPuesto().getValor();
				logger.info("Puesto administrativo de la persona :"+puesto);
				logger.info("Dirección de correo electrónico :"+func.getEmail());
				logger.info("Agencia: "+ func.getDiscriminante());
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
	}

}

/**
* Nombre del Programa 		: FuncionarioExternoDelegateImpl.java
* Autor 					: EdgarTE
* Compania 					: Ultrasist
* Proyecto 					: NSJP 							Fecha: 13 Apr 2012
* Marca de cambio 			: N/A
* Descripcion General 		: Describir el objetivo de la clase de manera breve
* Programa Dependiente 		: N/A
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
package mx.gob.segob.nsjp.delegate.funcionarioexterno.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.delegate.funcionarioexterno.FuncionarioExternoDelegate;
import mx.gob.segob.nsjp.dto.funcionarioexterno.FuncionarioExternoDTO;
import mx.gob.segob.nsjp.service.funcionarioexterno.FuncionarioExternoService;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author EdgarTE
 *
 */
@Service
@Transactional
public class FuncionarioExternoDelegateImpl implements
		FuncionarioExternoDelegate {
	
	@Autowired
	private FuncionarioExternoService funcionarioExternoService;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.funcionarioexterno.FuncionarioExternoDelegate#crearFuncionarioExterno(mx.gob.segob.nsjp.dto.funcionarioexterno.FuncionarioExternoDTO)
	 */
	@Override
	public FuncionarioExternoDTO crearFuncionarioExterno(
			FuncionarioExternoDTO funcionarioExternoDTO) {
		return funcionarioExternoService.crearFuncionarioExterno(funcionarioExternoDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.funcionarioexterno.FuncionarioExternoDelegate#actualizarFuncionarioExterno(mx.gob.segob.nsjp.dto.funcionarioexterno.FuncionarioExternoDTO)
	 */
	@Override
	public void actualizarFuncionarioExterno(
			FuncionarioExternoDTO funcionarioExternoDTO) {
		funcionarioExternoService.actualizarFuncionarioExterno(funcionarioExternoDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.funcionarioexterno.FuncionarioExternoDelegate#eliminarFuncionarioExterno(mx.gob.segob.nsjp.dto.funcionarioexterno.FuncionarioExternoDTO)
	 */
	@Override
	public void eliminarFuncionarioExterno(
			FuncionarioExternoDTO funcionarioExternoDTO) {
		funcionarioExternoService.eliminarFuncionarioExterno(funcionarioExternoDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.funcionarioexterno.FuncionarioExternoDelegate#consultarFuncionarioExternoPorId(mx.gob.segob.nsjp.dto.funcionarioexterno.FuncionarioExternoDTO)
	 */
	@Override
	public FuncionarioExternoDTO consultarFuncionarioExternoPorId(
			FuncionarioExternoDTO funcionarioExternoDTO) {
		return funcionarioExternoService.consultarFuncionarioExternoPorId(funcionarioExternoDTO);
	}

}

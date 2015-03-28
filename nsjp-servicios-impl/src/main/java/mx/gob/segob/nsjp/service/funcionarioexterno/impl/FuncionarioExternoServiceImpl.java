/**
* Nombre del Programa 		: FuncionarioExternoServiceImpl.java
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
package mx.gob.segob.nsjp.service.funcionarioexterno.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.dao.funcionarioexterno.FuncionarioExternoDAO;
import mx.gob.segob.nsjp.dto.funcionarioexterno.FuncionarioExternoDTO;
import mx.gob.segob.nsjp.model.FuncionarioExterno;
import mx.gob.segob.nsjp.service.funcionarioexterno.FuncionarioExternoService;
import mx.gob.segob.nsjp.service.funcionarioexterno.impl.transform.FuncionarioExternoTransformer;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author EdgarTE
 *
 */
@Service
@Transactional
public class FuncionarioExternoServiceImpl implements FuncionarioExternoService {
	
	@Autowired
	private FuncionarioExternoDAO funcionarioExternoDAO;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.funcionarioexterno.FuncionarioExternoService#crearFuncionarioExterno(mx.gob.segob.nsjp.dto.funcionarioexterno.FuncionarioExternoDTO)
	 */
	@Override
	public FuncionarioExternoDTO crearFuncionarioExterno(
			FuncionarioExternoDTO funcionarioExternoDTO) {
		FuncionarioExterno funcionarioExterno  = FuncionarioExternoTransformer.transformar(funcionarioExternoDTO);
		Long idFuncionario = funcionarioExternoDAO.create(funcionarioExterno);
		funcionarioExternoDAO.flush();
		funcionarioExternoDTO.setFuncionarioExternoId(idFuncionario);
		return funcionarioExternoDTO;
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.funcionarioexterno.FuncionarioExternoService#actualizarFuncionarioExterno(mx.gob.segob.nsjp.dto.funcionarioexterno.FuncionarioExternoDTO)
	 */
	@Override
	public void actualizarFuncionarioExterno(
			FuncionarioExternoDTO funcionarioExternoDTO) {
		FuncionarioExterno funcionarioExterno = FuncionarioExternoTransformer.transformar(funcionarioExternoDTO);
		funcionarioExternoDAO.merge(funcionarioExterno);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.funcionarioexterno.FuncionarioExternoService#eliminarFuncionarioExterno(mx.gob.segob.nsjp.dto.funcionarioexterno.FuncionarioExternoDTO)
	 */
	@Override
	public void eliminarFuncionarioExterno(
			FuncionarioExternoDTO funcionarioExternoDTO) {
		FuncionarioExterno funcionarioExterno = FuncionarioExternoTransformer.transformar(funcionarioExternoDTO);
		funcionarioExternoDAO.delete(funcionarioExterno);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.funcionarioexterno.FuncionarioExternoService#consultarFuncionarioExternoPorId(mx.gob.segob.nsjp.dto.funcionarioexterno.FuncionarioExternoDTO)
	 */
	@Override
	public FuncionarioExternoDTO consultarFuncionarioExternoPorId(
			FuncionarioExternoDTO funcionarioExternoDTO) {
		FuncionarioExterno funcionarioExterno = funcionarioExternoDAO.read(funcionarioExternoDTO.getFuncionarioExternoId());
		FuncionarioExternoDTO funcExtDTO = FuncionarioExternoTransformer.transformar(funcionarioExterno);
		return funcExtDTO;
	}

}

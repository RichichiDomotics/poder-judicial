/**
 * 
 */
package mx.gob.segob.nsjp.service.test.funcionario.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.service.funcionario.AdjuntarArchivoAFuncionarioService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author alfonso
 *
 */
public class AdjuntarArchivoAFuncionarioServiceImplTest extends 
BaseTestServicios<AdjuntarArchivoAFuncionarioService> {

	
	public void testAdjuntarArchivoDigitalAFuncionario() throws NSJPNegocioException{
		Long idfuncionario = 0L;
		ArchivoDigitalDTO adjuntoDTO = null;
		service.adjuntarArchivoAFuncionario(idfuncionario, adjuntoDTO);
	}
	
}

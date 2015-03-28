/**
* Nombre del Programa : GuardarEvidenciaServiceImplTest.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 19/07/2011
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
package mx.gob.segob.nsjp.service.test.evidencia.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.service.evidencia.GuardarEvidenciaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
public class GuardarEvidenciaServiceImplTest extends BaseTestServicios<GuardarEvidenciaService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.cadenadecustodia.impl.GuardarEvidenciaServiceImpl#guardarEvidencia(mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO)}.
	 */
	public void testGuardarEvidencia() {
		EvidenciaDTO evid=new EvidenciaDTO();
		evid.setOrigenEvidencia("Obtenido en escena");
		evid.setFechaLevantamiento(new Date());
		ObjetoDTO obj = new ObjetoDTO(1174L);
		evid.setObjeto(obj);
		evid.setDescripcion("explosivos encontrados");
		CadenaDeCustodiaDTO cad=new CadenaDeCustodiaDTO(64L);
////		cad.setFolio("Cad_001");
		evid.setCodigoBarras("ASDF1244");
		evid.setCadenaDeCustodia(cad);
		FuncionarioDTO func=new FuncionarioDTO(28L);
		evid.setFuncionario(func);
		try {
			Long idEvidencia = service.guardarEvidencia(evid);
			assertTrue("Exito con la evidencia: "+idEvidencia,true);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertFalse("Falla",true);
		}
	}

}

/**
* Nombre del Programa : AsignarJuezAudienciaServiceImplTest.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 29/06/2011
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
package mx.gob.segob.nsjp.service.test.audiencia.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.service.audiencia.AsignarJuezAudienciaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
public class AsignarJuezAudienciaServiceImplTest extends BaseTestServicios<AsignarJuezAudienciaService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.audiencia.impl.AsignarJuezAudienciaServiceImpl#asignarJuezAudiencia(mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO, java.util.List)}.
	 */
	public void testAsignarJuezAudiencia() {
		AudienciaDTO audiencia=new AudienciaDTO();
		audiencia.setId(2L);
		Long[] idis={1L, 2L,3L,10L};
		List<FuncionarioDTO> jueces = juecesParaAudiencia(idis);
		
		try {
			service.asignarJuezAudiencia(audiencia, jueces);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue("FALLO: asignarJuezAudiencia", false);
		}
	}

	private List<FuncionarioDTO> juecesParaAudiencia(Long[] idis) {
		List<FuncionarioDTO> jueces=new ArrayList<FuncionarioDTO>();
		
		for (Long id : idis) {
			jueces.add(new FuncionarioDTO(id));
		}
		return jueces;
	}

}

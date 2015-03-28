/**
 * 
 */
package mx.gob.segob.nsjp.service.test.catalogo.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.catalogo.TipoDiscriminante;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;
import mx.gob.segob.nsjp.service.catalogo.ConsultarDiscriminanteService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author AlineGS
 *
 */
public class ConsultarDiscriminanteServiceImplTest extends BaseTestServicios<ConsultarDiscriminanteService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.catalogo.impl.ConsultarDiscriminanteServiceImpl#consultarDiscriminantes()}.
	 */
	public void testConsultarDiscriminantes() {
		try {
			TipoDiscriminante tipo=null;
//			TipoDiscriminante tipo=TipoDiscriminante.AGENCIA;
			List<CatDiscriminanteDTO> discriminantes = service.consultarDiscriminantes(tipo);
			assertNotNull(discriminantes);
			logger.info("Existen "+discriminantes.size()+" discriminantes");
			for (CatDiscriminanteDTO dto : discriminantes) {
				logger.info("-----------------------------");
				logger.info("Id: "+dto.getCatDiscriminanteId());
				logger.info("Clave: "+dto.getClave());
				logger.info("Nombre: "+dto.getNombre());
				logger.info("Tipo: "+dto.getTipo());
				logger.info("Distrito: "+dto.getDistrito());
//				logger.info("$DistritoID: "+dto.getCatDistrito().getCatDistritoId());
//				logger.info("$DistritoClave: "+dto.getCatDistrito().getClaveDistrito());
//				logger.info("$DistritoNombre: "+dto.getCatDistrito().getNombreDist());
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.catalogo.impl.ConsultarDiscriminanteServiceImpl#consultarDiscriminanteXId(java.lang.Long)}.
	 */
	public void testConsultarDiscriminanteXId() {
		try {
			Long discriminanteID=1L;
			CatDiscriminanteDTO dto = service.consultarDiscriminanteXId(discriminanteID);
			assertNotNull(dto);
				logger.info("-----------------------------");
				logger.info("Id: "+dto.getCatDiscriminanteId());
				logger.info("Clave: "+dto.getClave());
				logger.info("Nombre: "+dto.getNombre());
				logger.info("Tipo: "+dto.getTipo());
				logger.info("Distrito: "+dto.getDistrito());
				logger.info("$DistritoID: "+dto.getDistrito().getCatDistritoId());
				logger.info("$DistritoClave: "+dto.getDistrito().getClaveDistrito());
				logger.info("$DistritoNombre: "+dto.getDistrito().getNombreDist());
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.catalogo.impl.ConsultarDiscriminanteServiceImpl#consultarDiscriminantesXDistrito(java.lang.Long)}.
	 */
	public void testConsultarDiscriminantesXDistrito() {
		try {
			Long distritoID=2L;
			TipoDiscriminante tipo=TipoDiscriminante.AGENCIA;
			List<CatDiscriminanteDTO> discriminantes = service.consultarDiscriminantesXDistrito(distritoID,tipo);
			assertNotNull(discriminantes);
			logger.info("Existen "+discriminantes.size()+" discriminantes");
			for (CatDiscriminanteDTO dto : discriminantes) {
				logger.info("-----------------------------");
				logger.info("Id: "+dto.getCatDiscriminanteId());
				logger.info("Clave: "+dto.getClave());
				logger.info("Nombre: "+dto.getNombre());
				logger.info("Tipo: "+dto.getTipo());
				logger.info("Distrito: "+dto.getDistrito());
//				logger.info("$DistritoID: "+dto.getCatDistrito().getCatDistritoId());
//				logger.info("$DistritoClave: "+dto.getCatDistrito().getClaveDistrito());
//				logger.info("$DistritoNombre: "+dto.getCatDistrito().getNombreDist());
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.catalogo.impl.ConsultarDiscriminanteServiceImpl#consultarDiscriminantesXDistrito(java.lang.Long)}.
	 */
	public void testconsultarDiscriminantesXDistritoYTipoInstitucion(){
		try {
			
			Long distritoID=1L;
			
			List<CatDiscriminanteDTO> discriminantes = service.consultarDiscriminantesXDistritoYTipoInstitucion(distritoID);
			
			logger.info("Existen "+discriminantes.size()+" discriminantes");
			for (CatDiscriminanteDTO dto : discriminantes) {
				logger.info("-----------------------------");
				logger.info("Id: "+dto.getCatDiscriminanteId());
				logger.info("Clave: "+dto.getClave());
				logger.info("Nombre: "+dto.getNombre());
				logger.info("Tipo: "+dto.getTipo());
				logger.info("Distrito: "+dto.getDistrito());
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
	}

}

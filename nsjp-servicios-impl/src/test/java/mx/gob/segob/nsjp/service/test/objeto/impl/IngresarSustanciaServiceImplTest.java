/**
 * 
 */
package mx.gob.segob.nsjp.service.test.objeto.impl;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.objeto.SustanciaDTO;
import mx.gob.segob.nsjp.service.objeto.IngresarSustanciaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class IngresarSustanciaServiceImplTest extends BaseTestServicios<IngresarSustanciaService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.objeto.impl.IngresarSustanciaServiceImpl#ingresarSustancia(mx.gob.segob.nsjp.dto.objeto.SustanciaDTO)}.
	 */
	public void testIngresarSustancia() {
		SustanciaDTO dto=new SustanciaDTO();
		
		dto.setCantidad(66L);
		dto.setTipoSustancia(new ValorDTO(1358L));
		dto.setEmpaque(new ValorDTO(1427L));
		dto.setUnidadMedida(new ValorDTO(1396L));
		
		dto.setExpedienteDTO(new ExpedienteDTO(326L));
		CalidadDTO cali=new CalidadDTO();
        cali.setCalidades(Calidades.PRUEBA);
        cali.setValorIdCalidad(new ValorDTO(215L));
        cali.setDescripcionEstadoFisico("Descripcion calidad nuevo vehiculo");
		dto.setCalidadDTO(cali);
		dto.setValorByCondicionFisicaVal(new ValorDTO(434L));
		dto.setDescripcion("Extasis");
		
		try {
			Long idsust = service.ingresarSustancia(dto);
			assertNotNull(idsust);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
	}

}

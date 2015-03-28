/**
 * 
 */
package mx.gob.segob.nsjp.service.test.usuario.impl;

import java.util.List;

import org.apache.log4j.Logger;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.usuario.ModuloDTO;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;
import mx.gob.segob.nsjp.service.usuario.ModuloService;

/**
 * @author LuisMG
 * 
 */
public class ModuloServiceImplTest extends BaseTestServicios<ModuloService> {
	private static final Logger logger = Logger
			.getLogger(ModuloServiceImplTest.class);

	public void testConsultarFunciones() {
		try {
			List<ModuloDTO> modulos = service.consultarModulos();
			if (modulos != null) {
				for (int i = 0; i < modulos.size(); i++) {
					logger.info("Nombre: " + modulos.get(i).getNombreModulo());
					logger.info("Descripción: "
							+ modulos.get(i).getDescripcionModulo());
					if (modulos.get(i).getFunciones() != null) {
						logger.info("Funciones: "
								+ modulos.get(i).getFunciones().size());
					}
				}
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
	}
}

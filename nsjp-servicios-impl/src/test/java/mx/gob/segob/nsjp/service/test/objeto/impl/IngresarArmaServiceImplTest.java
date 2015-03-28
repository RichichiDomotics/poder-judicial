/**
 * Nombre del Programa : IngresarArmaServiceImplTest.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 06-jul-2011
 * Marca de cambio        : N/A
 * Descripcion General    : N/A
 * Programa Dependient    :N/A
 * Programa Subsecuente   :N/A
 * Cond. de ejecucion     :N/A
 * Dias de ejecucion      :N/A                                Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                            :N/A
 * Compania                         :N/A
 * Proyecto                         :N/A                      Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.test.objeto.impl;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.objeto.ArmaDTO;
import mx.gob.segob.nsjp.service.objeto.IngresarArmaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
public class IngresarArmaServiceImplTest extends
		BaseTestServicios<IngresarArmaService> {

	public void testIngresarArmaService() {
		try {
			logger.info("Probando el servicio de: IngresarArmaService");
			assert service != null;
			// Para actualizacion...
			ArmaDTO actualizable = new ArmaDTO();
			actualizable.setElementoId(1947L);
			actualizable.setMarca(new ValorDTO(1337L));
			actualizable.setDescripcion("Un arma de oro");
			actualizable.setCalibre("calibre 40");
			ExpedienteDTO expedienteDTO = new ExpedienteDTO();
			expedienteDTO.setExpedienteId(5L);
			actualizable.setExpedienteDTO(expedienteDTO);
			CalidadDTO calidadDTO = new CalidadDTO();
			calidadDTO.setCalidades(Calidades.PRUEBA);
			actualizable.setCalidadDTO(calidadDTO);
			actualizable.setValorByCondicionFisicaVal(new ValorDTO(434L));
			actualizable.setTipoArma(new ValorDTO(1325L));
			actualizable.setFolioElemento("ARMA123");

			actualizable.setEsActivo(true);

			service.ingresarArma(actualizable);

//			 //Para registro de una nueva arma...
//			 ArmaDTO guardable = new ArmaDTO();
//			 // guardable.setElementoId(0L);
//			 guardable.setMarca(new ValorDTO(1337L));
//			 guardable.setDescripcion("Un arma de oro");
//			 guardable.setCalibre("calibre 40");
//			 ExpedienteDTO expedienteDTO = new ExpedienteDTO();
//			 expedienteDTO.setExpedienteId(5L);
//			 guardable.setExpedienteDTO(expedienteDTO);
//			 CalidadDTO calidadDTO = new CalidadDTO();
//			 calidadDTO.setCalidades(Calidades.PRUEBA);
//			 guardable.setCalidadDTO(calidadDTO);
//			 guardable.setValorByCondicionFisicaVal(new ValorDTO(434L));
//			 guardable.setTipoArma(new ValorDTO(1325L));
//			 guardable.setFolioElemento("ARMA123");
//			
//			 service.ingresarArma(guardable);
		} catch (NSJPNegocioException ex) {
			ex.printStackTrace();
			if (logger.isDebugEnabled()) {
				logger.debug(ex);
			}
			fail("Ocurrio una excepcion al ejecutar el test IngresarArmaService");
		}
	}

}

/**
 * Nombre del Programa : GuardarDelitoServiceImplTest.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 04-jul-2011
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
package mx.gob.segob.nsjp.service.test.delito.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.CatDelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.service.delito.GuardarDelitoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
public class GuardarDelitoServiceImplTest
    extends BaseTestServicios<GuardarDelitoService> {

    public void testGuardarDelitoService(){
        try {
            logger.info("Probando el servicio de: GuardarDelitoService");
            assert service != null;
            
            List<DelitoDTO> delitosDto=delitosParaGuardar();
			ExpedienteDTO expedienteDto=new ExpedienteDTO(34L);
			service.guardarDelito(delitosDto, expedienteDto);
        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
            fail("Ocurrio una excepcion al ejecutar el test GuardarDelitoService");
        }
    }

	private List<DelitoDTO> delitosParaGuardar() {

		List<DelitoDTO> delitos = new ArrayList<DelitoDTO>();
		delitos.add(new DelitoDTO(13L, new CatDelitoDTO(1L), true, false, new ExpedienteDTO(34L)));
		delitos.add(new DelitoDTO(211L, new CatDelitoDTO(106L), true, true, new ExpedienteDTO(34L)));
		delitos.add(new DelitoDTO(212L, new CatDelitoDTO(106L), true, false, new ExpedienteDTO(34L)));
		delitos.add(new DelitoDTO(null, new CatDelitoDTO(106L), true, false, new ExpedienteDTO(34L)));
//		delitos.add(new DelitoDTO(null, new CatDelitoDTO(106L), true, false, new ExpedienteDTO(34L)));
		return delitos;
	}
   
}

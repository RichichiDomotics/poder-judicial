/**
 * Nombre del Programa : ConsultarActuacionesPorCatDelitosServiceImplTest.java
 * Autor                            : rgama
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 24-08-2011
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
package mx.gob.segob.nsjp.service.test.actividad.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.service.actividad.CanalizarCausaService;
import mx.gob.segob.nsjp.service.actividad.ConsultarActuacionesPorCatDelitosService;
import mx.gob.segob.nsjp.service.actividad.ConsultarActuacionesPorCatDelitosServiceImpl;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

import org.apache.log4j.Logger;

/**
 *
 * @version 1.0
 * @author rgama
 */
public class ConsultarActuacionesPorCatDelitosServiceImplTest
    extends BaseTestServicios<ConsultarActuacionesPorCatDelitosService> {
	
	private final static Logger logger = Logger
	.getLogger(CanalizarCausaService.class);
	

    public void testConsultarActuacionesPorIdsCatDelito(){
            logger.info("Probando el servicio de: ConsultarActuacionesPorCatDelitosServiceImpl");
            List<Long> idCatDelitos = new ArrayList<Long>();
        	idCatDelitos.add(4L);
        	idCatDelitos.add(3L);

            List<ValorDTO> data;
			try {
				data = service.consultarActividadesPorIdsCatDelito(idCatDelitos);
	            for (ValorDTO actuacion : data) {
	            	System.out.println("Nombre actuacion: " + actuacion.getValor());
	    		}
	            logger.debug("data.size() :: "+data.size());
			} catch (NSJPNegocioException e) {
				e.printStackTrace();
			}
		} 
   
}

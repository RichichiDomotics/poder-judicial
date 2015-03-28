/**
 * Nombre del Programa : RegistrarRelacionDeReincidenciaServiceImpl.java
 * Autor                            : cesarAgustin
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 23 Ago 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Prueba unitaria para el servicio de RegistrarRelacionDeReincidencia
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
package mx.gob.segob.nsjp.service.test.caso.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.elemento.RelacionReincidenciaDTO;
import mx.gob.segob.nsjp.service.elemento.RegistrarRelacionDeReincidenciaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria para el servicio de RegistrarRelacionDeReincidenciaService
 * 
 * @version 1.0
 * @author rgama
 * 
 */
public class RegistrarRelacionDeReincidenciaServiceImplTest
        extends
            BaseTestServicios<RegistrarRelacionDeReincidenciaService> {

	public void testRegistrarRelacionDeReincidenciaService() {
    	Long idElemento = 3L;
    	Long idFuncionario = 1L;
    	List<Long> idCasos = new ArrayList<Long>();
    	idCasos.add(1L);
    	idCasos.add(2L);


    	List<RelacionReincidenciaDTO> respuesta= null;
        try {
        	respuesta =service.registrarReinicidencias(idElemento, idCasos, idFuncionario);
        } catch (NSJPNegocioException e) {
            logger.error(e.getMessage());
            fail(e.getMessage());
        }

        assertTrue("El identificador obtenido tiene que ser mayor a cero", respuesta.size() > 0);
    }
}

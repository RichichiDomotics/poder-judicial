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

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.elemento.RelacionReincidenciaDTO;
import mx.gob.segob.nsjp.service.elemento.ConsultarRelacionesDeReincidenciaXElementoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria para el servicio de RegistrarRelacionDeReincidenciaService
 * 
 * @version 1.0
 * @author rgama
 * 
 */
public class ConsultarRelacionesDeReincidenciaXElementoServiceImplTest
        extends
            BaseTestServicios<ConsultarRelacionesDeReincidenciaXElementoService> {

	public void testConsultarReincidenciasXElemento() {
    	Long idElemento = 340L;
    	List<RelacionReincidenciaDTO> respuesta= null;
        try {
        	respuesta =service.consultarReincidenciasXElemento(idElemento);
        	
        	for (RelacionReincidenciaDTO relacionReincidenciaDTO : respuesta) {
        		CasoDTO caso = relacionReincidenciaDTO.getCaso();
        		 logger.debug("ID caso:" + caso.getCasoId());
				 logger.debug("Numero General del caso:"+ caso.getNumeroGeneralCaso());
				 logger.debug("Fecha del caso:" + caso.getFechaApertura());
				 logger.debug("Estatus:" + caso.getEstatus().getNombre());		    
			}
        	
        } catch (NSJPNegocioException e) {
            logger.error(e.getMessage());
            fail(e.getMessage());
        }

        assertTrue("El total de objetos tiene que ser mayor a cero", respuesta.size() > 0);
    }
}

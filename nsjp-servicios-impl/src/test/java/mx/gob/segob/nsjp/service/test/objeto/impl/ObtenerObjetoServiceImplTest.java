/**
 * Nombre del Programa : ObtenerObjetoServiceImplTest.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 18 Jul 2011
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
package mx.gob.segob.nsjp.service.test.objeto.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.objeto.NumerarioDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.dto.objeto.VegetalDTO;
import mx.gob.segob.nsjp.dto.objeto.VehiculoDTO;
import mx.gob.segob.nsjp.service.objeto.ObtenerObjetoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba de servicios.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class ObtenerObjetoServiceImplTest
        extends
            BaseTestServicios<ObtenerObjetoService> {

    public void testConsultarObjeto() {
        try {
            ObjetoDTO dato = service.obtenerObjeto(new ObjetoDTO(4859L));
            VehiculoDTO vehiculoDTO = (VehiculoDTO) dato;
            System.out.println("Info de la imagen: " + vehiculoDTO.getFotoDelElemento());            
            System.out.println("Contenido: " + vehiculoDTO.getFotoDelElemento().getContenido());            
            assertNotNull("vehiculoDTO.getDescripcion()", vehiculoDTO.getElementoId());
            logger.debug("dato ::" +  dato);
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }
    }
    public void testConsultarObjetoVegetal() {
    	try {
    		ObjetoDTO dato = service.obtenerObjeto(new ObjetoDTO(104L));
    		VegetalDTO vegetalDTO = (VegetalDTO) dato;
    		assertNotNull("vegetalDTO.getDescripcion()", vegetalDTO.getElementoId());
    		System.out.println("Cantidad: " + vegetalDTO.getCantidad());
    		System.out.println("Info de la imagen: " + vegetalDTO.getFotoDelElemento());
    		System.out.println("Contenido: " + vegetalDTO.getFotoDelElemento().getContenido());            
    		logger.debug("dato ::" +  dato);
    	} catch (NSJPNegocioException e) {
    		fail(e.getMessage());
    	}
    }
    
    public void testConsultarObjetoNumerario() {
    	try {
    		ObjetoDTO dato = service.obtenerObjeto(new ObjetoDTO(116L));
    		NumerarioDTO NumerarioDTO = (NumerarioDTO) dato;
    		assertNotNull("Descripcion()", NumerarioDTO.getElementoId());
    		System.out.println("Contenido: " + NumerarioDTO.getFotoDelElemento().getContenido());
    		System.out.println("Cantidad: " + NumerarioDTO.getCantidad());
    		System.out.println("Info de la imagen: " + NumerarioDTO.getFotoDelElemento());
    		
    		System.out.println("Contenido: " + NumerarioDTO.getFotoDelElemento().getContenido());
    		
    		logger.debug("dato ::" +  dato);
    	} catch (NSJPNegocioException e) {
    		fail(e.getMessage());
    	}
    }
    
}

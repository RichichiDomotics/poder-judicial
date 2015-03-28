/**
 * Nombre del Programa : RegistrarAlmacenServiceImplTest.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 01-ago-2011
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
package mx.gob.segob.nsjp.service.test.almacen.impl;

import java.util.Calendar;
import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.service.almacen.RegistrarAlmacenService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
public class RegistrarAlmacenServiceImplTest
    extends BaseTestServicios<RegistrarAlmacenService> {

    public void testRegistrarAlmacenService(){
        try {
            logger.info("Probando el servicio de: RegistrarAlmacenService");
            assert service != null;         
            AlmacenDTO almacenDto = new AlmacenDTO();
            almacenDto.setIdentificadorAlmacen(1L);
            almacenDto.setDescripcion("NEW");
            almacenDto.setEsVirtual(true);
            almacenDto.setResguardaEvidencias(true);
            almacenDto.setEstatusActivo(true);
            almacenDto.setNombreAlmacen("NEW");
          
            DomicilioDTO domicilioDTO = new DomicilioDTO();
            //domicilioDTO.setElementoId(1643L);
            CalidadDTO calidadDTO = new CalidadDTO();
            calidadDTO.setCalidades(Calidades.DOMICILIO);
            domicilioDTO.setCalidadDTO(calidadDTO);
            domicilioDTO.setAlias("AlmacenExp");
            domicilioDTO.setCalle("NEWNEW");
            domicilioDTO.setNumeroExterior("999");
            domicilioDTO.setDescripcion("Almacen expediente");
            domicilioDTO.setExpedienteDTO(new ExpedienteDTO(1L));
            domicilioDTO.setFechaCreacionElemento(new Date());
            
            almacenDto.setDomicilio(domicilioDTO);
            
            Long idAlmacenRegistrado = service.registrarAlmacen(almacenDto);
            assertNotNull("idAlmacenRegistrado", idAlmacenRegistrado);
            logger.info("Identificador almacen :: "+idAlmacenRegistrado);            
        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
            fail("Ocurrio una excepcion al ejecutar el test RegistrarAlmacenService");
        }
    }
    
    public void testAsociarExpedienteAlmacen () {
    	try {
    		ExpedienteDTO expedienteDTO = new ExpedienteDTO();
    		expedienteDTO.setNumeroExpedienteId(349L);
    		
			AlmacenDTO respuesta = service.asociarExpedienteAlmacen(new AlmacenDTO(2L), expedienteDTO);
			assertNotNull("", respuesta);
			logger.info("Almacen actualizado "+respuesta);
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
    }
   
}

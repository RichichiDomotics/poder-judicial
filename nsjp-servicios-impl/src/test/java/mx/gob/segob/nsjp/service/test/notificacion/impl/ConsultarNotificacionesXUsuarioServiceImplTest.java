/**
 * Nombre del Programa : ConsultarNotificacionesXUsuarioServiceImplTest.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 18-jul-2011
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
package mx.gob.segob.nsjp.service.test.notificacion.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.documento.EstatusNotificacion;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.NotificacionDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.service.notificacion.ConsultarNotificacionesXUsuarioService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
public class ConsultarNotificacionesXUsuarioServiceImplTest
        extends BaseTestServicios<ConsultarNotificacionesXUsuarioService> {

    public void testConsultarNotificacionesXUsuarioService() {
        try {
            logger.info("Probando el servicio de: ConsultarNotificacionesXUsuarioService");
            assert service != null;
            FuncionarioDTO funcionarioDTO = new FuncionarioDTO(4L);
//            ValorDTO valorDTO = new ValorDTO(1775L);
            ValorDTO valorDTO = new ValorDTO(EstatusNotificacion.EN_PROCESO.getValorId());
            List<NotificacionDTO> notificaciones =
                    service.consultarNotificacionesXUsuario(funcionarioDTO, valorDTO, 1, 10, "fechaElaboracion", 0);
            long total = service.consultarNumeroTotalDeNotificacionesXUsuario(funcionarioDTO, valorDTO);
//            assertTrue(total == notificaciones.size());
            if (logger.isDebugEnabled()) {
                logger.debug("total = " + total);
                logger.debug("notificaciones.size() = " + notificaciones.size());
            }
        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
            fail("Ocurrio una excepcion al ejecutar el test ConsultarNotificacionesXUsuarioService");
        }
    }
    
    public void testConsultarNotificacionesXFuncionario(){
    	try {
			List<NotificacionDTO> notifiaciones = service.consultarNotificacionesXFuncionario(new FuncionarioDTO(1L), null);
			assertNotNull(notifiaciones);
			logger.info("Existen "+notifiaciones.size()+" notificaciones");
			for (NotificacionDTO noti : notifiaciones) {
				logger.info("---------------------");
				logger.info("Notificacion: "+noti);
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
    }
}

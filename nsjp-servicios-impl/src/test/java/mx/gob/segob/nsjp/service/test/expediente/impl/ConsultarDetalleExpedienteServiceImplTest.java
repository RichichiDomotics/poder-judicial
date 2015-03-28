/**
 * Nombre del Programa : ConsultarDetalleExpedienteServiceImplTest.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 05-jul-2011
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
package mx.gob.segob.nsjp.service.test.expediente.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.institucion.JerarquiaOrganizacionalDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.service.expediente.ConsultarDetalleExpedienteService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
public class ConsultarDetalleExpedienteServiceImplTest
    extends BaseTestServicios<ConsultarDetalleExpedienteService> {

    public void testConsultarDetalleExpedienteService(){
        try {
            logger.info("Probando el servicio de: ConsultarDetalleExpedienteService");
            assert service != null;
            ExpedienteDTO expedienteDto = new ExpedienteDTO();
            expedienteDto.setExpedienteId(5L);
            UsuarioDTO usuarioDto = new UsuarioDTO();
            usuarioDto.setIdUsuario(3L);
            ExpedienteDTO detalles =
                    service.consultarDetalleExpediente(expedienteDto, usuarioDto);
            assertNotNull("detalles", detalles);
            if (logger.isDebugEnabled()) {
                logger.debug("detalles.getCasoDTO().getNumeroGeneralCaso() = " +
                        detalles.getCasoDTO().getNumeroGeneralCaso());
            }
        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
            fail("Ocurrio una excepcion al ejecutar el test ConsultarDetalleExpedienteService");
        }
    }
    
    public void testConsultarOrigendeExpediente(){
    	ExpedienteDTO expediente=new ExpedienteDTO(4L);
		try {
			JerarquiaOrganizacionalDTO area = service.consultarOrigendeExpediente(expediente);
			assertNotNull(area);
			logger.info("El área es: "+area);
			logger.info("El id del área es: "+area.getJerarquiaOrganizacionalId());
			logger.info("El nombre del área es: "+area.getNombre());
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
    }
   
}

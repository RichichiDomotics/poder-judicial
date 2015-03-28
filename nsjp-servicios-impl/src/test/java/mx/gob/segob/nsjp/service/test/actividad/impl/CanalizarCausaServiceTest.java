/**
 * Nombre del Programa : CanalizarCausaServiceTest.java
 * Autor                            : rgama
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 11-jul-2011
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

import mx.gob.segob.nsjp.comun.enums.actividad.Actividades;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.ActividadDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.service.actividad.CanalizarCausaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

import org.apache.log4j.Logger;

/**
 *
 * @version 1.0
 * @author rgama
 */
public class CanalizarCausaServiceTest
    extends BaseTestServicios<CanalizarCausaService> {
	
	private final static Logger logger = Logger
	.getLogger(CanalizarCausaService.class);
	

    public void testCanalizarCausaJARService(){

            logger.info("Probando el servicio de: Generar Actividad");
            assert service != null;
            //Se configura el expedienteID
            ExpedienteDTO expedienteDTO = new ExpedienteDTO();
            expedienteDTO.setExpedienteId(31L);
            //Se configura el tipoActividad
            ActividadDTO actividadDTO = new ActividadDTO();
//            actividadDTO.setTipoActividad(Actividades.CANALIZAR_UNIDAD_INVESTIGACION);
            actividadDTO.setTipoActividad(Actividades.CANALIZAR_JUSTICIA_ALTERNATIVA_RESTAURATIVA);
            //Se configura el id del Funcionario
            UsuarioDTO usuarioDto = new UsuarioDTO();
            usuarioDto.setFuncionario(new FuncionarioDTO(1L));
            expedienteDTO.setUsuario(usuarioDto);                
            
			try {
				ActividadDTO loActividad = service.canalizarCausaServiceImpl(expedienteDTO, actividadDTO,false,false,true);
				logger.info("El id de la nueva actividad es: "+ loActividad.getActividadId()) ;
				
			} catch (NSJPNegocioException e) {
				logger.error(e.getMessage(), e);
				fail(e.getMessage());			
			}
    }
    
    public void testCanalizarCausaUIService(){

        logger.info("Probando el servicio de: Generar Actividad");
        assert service != null;
        //Se configura el expedienteID
        ExpedienteDTO expedienteDTO = new ExpedienteDTO();
        expedienteDTO.setExpedienteId(32L);
        //Se configura el tipoActividad
        ActividadDTO actividadDTO = new ActividadDTO();
        actividadDTO.setTipoActividad(Actividades.CANALIZAR_UNIDAD_INVESTIGACION);        
        //Se configura el id del Funcionario
        UsuarioDTO usuarioDto = new UsuarioDTO();
        usuarioDto.setFuncionario(new FuncionarioDTO(1L));
        expedienteDTO.setUsuario(usuarioDto);                
        
		try {				
			ActividadDTO loActividad = service.canalizarCausaServiceImpl(expedienteDTO, actividadDTO,true,false,true);
			logger.info("El id de la nueva actividad es: "+ loActividad.getActividadId()) ;
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
			fail(e.getMessage());			
		}
    }
    
    
    public void _testCanalizarCausaUExternaService(){

        logger.info("Probando el servicio de: Generar Actividad");
        assert service != null;
        //Se configura el expedienteID
        ExpedienteDTO expedienteDTO = new ExpedienteDTO();
        expedienteDTO.setExpedienteId(31L);
        //Se configura el tipoActividad
        ActividadDTO actividadDTO = new ActividadDTO();
        //actividadDTO.setTipoActividad(Actividades.);        
        //Se configura el id del Funcionario
        UsuarioDTO usuarioDto = new UsuarioDTO();
        usuarioDto.setFuncionario(new FuncionarioDTO(1L));
        expedienteDTO.setUsuario(usuarioDto);                
        
		try {				
			ActividadDTO loActividad = service.canalizarCausaServiceImpl(expedienteDTO, actividadDTO,false,false,false);
			logger.info("El id de la nueva actividad es: "+ loActividad.getActividadId()) ;
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
			fail(e.getMessage());			
		}
    }
   
}

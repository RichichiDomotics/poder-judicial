/**
 * Nombre del Programa : ConsultarConfActividadDocumentoServiceImplTest.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 07-jul-2011
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

import java.util.Arrays;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.actividad.Actividades;
import mx.gob.segob.nsjp.comun.enums.actividad.CategoriasActividad;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.ActividadDTO;
import mx.gob.segob.nsjp.dto.ConfActividadDocumentoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.Actividad;
import mx.gob.segob.nsjp.service.actividad.ConsultarConfActividadDocumentoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
public class ConsultarConfActividadDocumentoServiceImplTest
        extends BaseTestServicios<ConsultarConfActividadDocumentoService> {

    public void testConsultarConfActividadDocumentoService() {
        try {
            logger.info("Probando el servicio de: ConsultarConfActividadDocumentoService");
            assert service != null;
            ExpedienteDTO numeroExpedienteDTO = new ExpedienteDTO(
                    null, // id numero expediente
                    "NSJYUCPG20110133334");//TestUtilServiceImpl.nuevoExpedienteUnico()); // string numero expediente
            UsuarioDTO usuarioDto = new UsuarioDTO();
            usuarioDto.setIdUsuario(3L);
            
            Long idCategoriaActividad = CategoriasActividad.MINISTERIAL.getValorId();
            
			List<ConfActividadDocumentoDTO> configuraciones =
                    service.consultarConfActividadDocumento(usuarioDto,
                    numeroExpedienteDTO, idCategoriaActividad);
            assertNotNull("configuraciones", configuraciones);
            if (logger.isDebugEnabled()) {
                logger.debug("configuraciones.size() = " + configuraciones.size());
            }
            for (ConfActividadDocumentoDTO configuracion : configuraciones) {
                if (logger.isDebugEnabled()) {
                	logger.debug("configuracion.getConfActividadDocumentoId() = "
                            + configuracion.getConfActividadDocumentoId());
                	logger.debug("configuracion.getNombreActividad() = "
                            + configuracion.getNombreActividad());
                    logger.debug("configuracion.getNombreDocumento() = "
                            + configuracion.getNombreDocumento());
                    logger.debug("configuracion.getTipoActividadId() = "
                            + configuracion.getTipoActividadId());
                    logger.debug("configuracion.getAccion() = "
                            + configuracion.getAccion());
                }
            }
        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
            fail("Ocurrio una excepcion al ejecutar el test ConsultarConfActividadDocumentoService");
        }
    }
    
    public void testConsultaConfActividadDocumentoPorId(){
    	Long idConsultaConfActividadDocumento = 1L;
    	
    	try {
			ConfActividadDocumentoDTO confActividadDocumentoDTO = service.consultaConfActividadDocumentoPorId(idConsultaConfActividadDocumento);
			assertNotNull("El servicio debe debe regresar un valor", confActividadDocumentoDTO);
			logger.info("confActividadDocumentoDTO:"+ confActividadDocumentoDTO);
			if(confActividadDocumentoDTO!= null){
				logger.info("NombreActividad:"+ confActividadDocumentoDTO.getNombreActividad());
				logger.info("NombreDocumento:"+ confActividadDocumentoDTO.getNombreDocumento());
				logger.info("getTipoActividadId:"+ confActividadDocumentoDTO.getTipoActividadId());
				logger.info("getTipoDocumentoId:"+ confActividadDocumentoDTO.getTipoDocumentoId());
				logger.info("getEstadoCambioExpediente:"+ confActividadDocumentoDTO.getEstadoCambioExpediente());
				logger.info("getForma:"+ confActividadDocumentoDTO.getForma());
				if(confActividadDocumentoDTO.getForma()!= null){
					FormaDTO formaDTO = confActividadDocumentoDTO.getForma();
					logger.info("getFormaID:"+ formaDTO.getFormaId());
					logger.info("getDescForma:"+ formaDTO.getDescForma());
					logger.info("getNombre:"+ formaDTO.getNombre());
				}
			}
				
		} catch (NSJPNegocioException e) {
			if (logger.isDebugEnabled()) {
                logger.debug(e);
            }
            fail("Ocurrio una excepcion al ejecutar el test ConsultaConfActividadDocumentoPorIdActividad");
		}
    	
    }
    
    public void testConsultarEstadosPorJerarquiaOrganizacional(){
//    	Long idJerarquiaOrganizacional = 55L;;
//    	Long idJerarquiaOrganizacional = Areas.VISITADURIA.parseLong();
    	Long idJerarquiaOrganizacional = 10L;//Areas.UNIDAD_INVESTIGACION.parseLong();
    	
		try {
			List<ValorDTO> valoresEstatus = service.consultarEstadosPorJerarquiaOrganizacional(idJerarquiaOrganizacional );
			assertFalse("La lista debe de regresar almenos un valor",
					valoresEstatus.isEmpty());
			logger.info(" #: " + valoresEstatus.size());

			for (ValorDTO valorDTO : valoresEstatus) {
				logger.info(" Estatus: " + valorDTO.getIdCampo());
				logger.info(" Estatus: " + valorDTO.getValor());

			}
		} catch (NSJPNegocioException e) {
            fail("Ocurrio una excepcion al ejecutar el test ConsultarEstadosPorJerarquiaOrganizacional");
		}
    	
    }
    
    public void testConsultarActividadesPorTipoActividadExpedienteId(){
    	Long idExpediente= 802L;
		Long[] idTA= {Actividades.GENERAR_QUERELLA.getValorId(),
				Actividades.GENERAR_DENUNCIA_EN_ATP.getValorId(),
				Actividades.ATENDER_CANALIZACION_UI.getValorId()};
		List<Long> idTipoActividades= Arrays.asList(idTA);
		Boolean documentoRec = true; 
		
		List<ActividadDTO> actividades;
		try {
			actividades = service.consultarActividadesPorTipoActividadExpedienteId(idExpediente, idTipoActividades, documentoRec);
			logger.info(" #Actividades: "+ actividades);
			assertNotNull(actividades);
			for (ActividadDTO actividadDTO : actividades) {
				logger.info("*****Id: "+actividadDTO.getActividadId());			
			}
		} catch (NSJPNegocioException e) {
			logger.info(e.getMessage(), e);
		}
    }
}

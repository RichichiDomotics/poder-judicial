/**
 * Nombre del Programa : ConsultarAudienciaServiceImplTest.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 15 Jun 2011
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
package mx.gob.segob.nsjp.service.test.audiencia.impl;

import java.util.Calendar;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.audiencia.EstatusAudiencia;
import mx.gob.segob.nsjp.comun.enums.audiencia.TipoAudiencia;
import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.audiencia.FiltroAudienciaDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.service.audiencia.ConsultarAudienciaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class ConsultarAudienciaServiceImplTest
        extends
        	BaseTestServicios<ConsultarAudienciaService> {

    public void testBuscarAudiencias() {
        try {
            FiltroAudienciaDTO filtro = new FiltroAudienciaDTO();
            // filtro.setFechaInicial(new Date());
            // filtro.setFechaFinal(new Date());
            // filtro.setDiaCompleto(false);
            // filtro.setUsuario(super.getUsuario());
            //filtro.setNumeroExpedienteId(2L);
//            //filtro.setCausa(true);
//            filtro.setNumeroExpediente("1000");
//            filtro.setTipoAudiencia(TipoAudiencia.JUICIO_ORAL.getValorId());           
            List<AudienciaDTO> resp = service.buscarAudiencias(filtro);
            assertTrue("La lista debe tener minimo un registro : ", resp.size()>0);
            for (AudienciaDTO audienciaDTO : resp) {
            	logger.debug("Audiencia Tipo : "+audienciaDTO.getTipoAudiencia().getValor());
            	logger.debug("Audiencia ID   : "+audienciaDTO.getId());
            	logger.debug("Audiencia Carac: "+audienciaDTO.getCaracter());
				logger.debug("AudienciaDTO : "+audienciaDTO);
			}
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }
    }

    public void testObtenerAudiencia() {
        try {
            AudienciaDTO filtro = new AudienciaDTO();
            filtro.setId(35L);
            AudienciaDTO resp = service.obtenerAudiencia(filtro);
            logger.debug("resp :: " + resp.getSala());
            logger.debug("resp.objs :: " + resp.getInvolucrados().size());
            logger.debug("resp.evs :: " + resp.getEvidencias().size());
            logger.debug("resp :: " + resp.getFechaEvento());
            logger.debug("resp :: " + resp.getDuracionEstimada());
            logger.debug("resp VICTIMA_PERSONA :: " + resp.getInvolucradoByCalidad(Calidades.VICTIMA_PERSONA));
            logger.debug("resp PROBABLE_RESPONSABLE_PERSONA :: " + resp.getInvolucradoByCalidad(Calidades.PROBABLE_RESPONSABLE_PERSONA));
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }
    }
    
    public void testValidarExistenciaPruebas(){
    	AudienciaDTO audienciaDTO=new AudienciaDTO();
    	audienciaDTO.setId(1L);
    	
		try {
			assertTrue("Exito",service.validarExistenciaPruebas(audienciaDTO));
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue("Fallo: validarExistenciaPruebas", false);
		}
    }

    public void testBuscarAudienciasSinResolutivos() throws NSJPNegocioException{
    	List<AudienciaDTO> lista = service.buscarAudienciasSinTranscripcionResolutivos();
    	
    	for(AudienciaDTO audiencia : lista){
    		logger.debug("Fecha - Hora Fin :: "+audiencia.getFechaHoraFin());
    		if(audiencia.getExpediente().getCausaPadre() != null){
    			logger.debug("numero de causa :: "+audiencia.getExpediente().getCausaPadre().getNumeroExpediente());
    			logger.debug("numero de toca :: "+audiencia.getExpediente().getNumeroExpediente());
    		}else{
    			logger.debug("numero de causa :: "+audiencia.getExpediente().getNumeroExpediente());
    		}
    	}
    }
    
    public void testConsultarAudienciasByTipoYFecha () {
    	AudienciaDTO audienciaDTO = new AudienciaDTO();
    	Calendar calTemp = Calendar.getInstance();
		audienciaDTO.setFechaEvento(calTemp.getTime());
		UsuarioDTO usuario = new UsuarioDTO();
		FuncionarioDTO funcionario = new FuncionarioDTO();
		CatDiscriminanteDTO disc = new CatDiscriminanteDTO();
		disc.setCatDiscriminanteId(26L);
		funcionario.setDiscriminante(disc);
		usuario.setFuncionario(funcionario);
		
    	
    	try {
			List<AudienciaDTO> respuesta = service.consultarAudienciasByTipoYFecha(audienciaDTO, TipoAudiencia.EJECUCION,usuario);
			assertTrue("La lista debe tener minimo un registro : ", respuesta.size()>0);
			logger.info("La lista debe tener minimo un registro : " + respuesta.size());
			for (AudienciaDTO audiDTO : respuesta) {
				logger.info("------------------");
				logger.info("Audiencia ID : " + audiDTO.getId());
				if (audiDTO.getExpediente()!=null) {
					logger.info("Numero Causa : " + audiDTO.getExpediente().getNumeroExpediente());
				}				
				logger.info("------------------");
			}
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
    }
    
    public void testObtenerAudienciaByNumeroAudiencia() {
    	AudienciaDTO audienciaDTO = new AudienciaDTO();
    	audienciaDTO.setConsecutivo((short)8);
    	
    	try {
			AudienciaDTO respuesta = service.obtenerAudienciaByNumeroAudiencia(audienciaDTO);
			assertTrue("El identificador de audiencia debe ser mayor a cero : ", respuesta.getId()>0);
			logger.info("------------------");
			logger.info("El identificador de audiencia debe ser mayor a cero : " + respuesta.getId());
			logger.info("------------------");
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
    }
    
    public void testConsultarAudienciaByNumeroExpedienteYEstatus () {
    	ExpedienteDTO expedienteDTO = new ExpedienteDTO();
    	expedienteDTO.setNumeroExpedienteId(23L);
    	
    	try {
			List<AudienciaDTO> respuesta = service.consultarAudienciaByNumeroExpedienteYEstatus(expedienteDTO, EstatusAudiencia.SOLICITADA);
			assertTrue("La lista debe tener minimo un registro : ", respuesta.size()>0);
			logger.info("La lista debe tener minimo un registro : " + respuesta.size());
			for (AudienciaDTO audiDTO : respuesta) {
				logger.info("------------------");
				logger.info("Audiencia ID : " + audiDTO.getId());
				logger.info("Tipo Audiencia : " + audiDTO.getTipo());
				logger.info("Sala Audiencia : " + audiDTO.getSala());
				logger.info("Fecha Audiencia : " + audiDTO.getFechaEvento());
				logger.info("------------------");
			}
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
    }
    /**
     * Método para probar la bandeja del ulencargado de causa donde se muestran las audiencias con 
     * solicitudes
     * @author Emigdio Hernández
     */
    public void testConsultarAudienciasConSolicitudesPorTipoYEstado(){
    	
    	try {
    		UsuarioDTO usuario = new UsuarioDTO();
    		FuncionarioDTO funcionario = new FuncionarioDTO();
    		CatDiscriminanteDTO catDis = new CatDiscriminanteDTO();
    		
    		catDis.setCatDiscriminanteId(26L);
    		funcionario.setDiscriminante(catDis);
    		usuario.setFuncionario(funcionario);
    		
			List<AudienciaDTO> audiencias = service.consultarAudienciasConSolicitudesPorTipoYEstado(new TiposSolicitudes[]
			        {TiposSolicitudes.AUDIO_VIDEO,TiposSolicitudes.TRANSCRIPCION_DE_AUDIENCIA} , 
					new EstatusSolicitud[]{EstatusSolicitud.ABIERTA},usuario);
			logger.debug("Audiencias:" + audiencias.size());
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			fail();
		}
    	
    }
    
    public void testConsultarAudienciasByTipoYEstatus () {
    	try {
			List<AudienciaDTO> respuesta = service.consultarAudienciasByTipoYEstatus(
											TipoAudiencia.EJECUCION, EstatusAudiencia.SOLICITADA);
			assertTrue("La lista debe tener minimo un registro ", respuesta.size()>0);
			logger.info("La lista debe tener minimo un registro "+ respuesta.size());
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
    }
   /**
    * Prueba unitaria para proba la funcionalidad de consultar la siguiene audiencia pendiente o 
    * crearla nueva si no existe una pendiente de agendar
    */
    public void testConsultarORegistrarAudienciaSiguiente(){
    	
    	try {
			AudienciaDTO aud = service.consultarORegistrarSiguienteAudiencia(55L);
			logger.debug(aud);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			fail();
		}
    	
    }
}

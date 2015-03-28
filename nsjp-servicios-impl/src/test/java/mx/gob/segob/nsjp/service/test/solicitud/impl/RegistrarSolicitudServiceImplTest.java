/**
* Nombre del Programa : RegistrarSolicitudServiceImplTest.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 7 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba unitaria para el servicio de registro de una solicitud
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
package mx.gob.segob.nsjp.service.test.solicitud.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.configuracion.ConfInstitucionDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudTranscripcionAudienciaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.ConfInstitucion;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.solicitud.RegistrarSolicitudService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;
import mx.gob.segob.nsjp.service.usuario.UsuarioService;

/**
 * Prueba unitaria para el servicio de registro de una solicitud.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class RegistrarSolicitudServiceImplTest extends
		BaseTestServicios<RegistrarSolicitudService> {
@Autowired
    private UsuarioService usrService;
    
	public void testRegistrarSolicitud () {
		SolicitudDTO solicitudDTO = new SolicitudDTO(); 
		
		solicitudDTO.setTipoSolicitudDTO(new ValorDTO(TiposSolicitudes.AUDIENCIA.getValorId()));
		solicitudDTO.setAreaOrigen(3L);
		solicitudDTO.setNombreSolicitante("Fabian Perez Sanchez");
		solicitudDTO.setInvolucradoDTO(new InvolucradoDTO(1646L));
		solicitudDTO.setEstatus(new ValorDTO(EstatusSolicitud.ABIERTA.getValorId()));
		solicitudDTO.setMotivo("Preliberacion");
		solicitudDTO.setFechaLimite(new Date());
		solicitudDTO.setFechaCierre(new Date());
		solicitudDTO.setFechaModificacion(new Date());
		solicitudDTO.setEsUrgente(true);
		
		solicitudDTO.setTipoDocumentoDTO(new ValorDTO(TipoDocumento.SOLICITUD.getValorId()));
		solicitudDTO.setFormaDTO(new FormaDTO(1L));
		solicitudDTO.setFolioDocumento("F00005");
		solicitudDTO.setNombreDocumento("Audiencia");
		solicitudDTO.setFechaCreacion(new Date());
		solicitudDTO.setVersion(1.0);
		solicitudDTO.setNumeroFojas("100");
		ConfInstitucionDTO conf = new ConfInstitucionDTO();
		conf.setConfInstitucionId(1L);
		solicitudDTO.setInstitucion(conf);
		
		
		try {
			SolicitudDTO respuesta = service.registrarSolicitud(solicitudDTO);
			assertTrue("El identificador de la solicitud debe ser mayor a cero : ", respuesta.getDocumentoId()>0);
			logger.info("El identificador de la solicitud debe ser mayor a cero : " + respuesta.getDocumentoId());
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
		
	}	
	
	
	
	
	public void testRegistrarSolicitudCarpetaInvestigacion(){
	    try {
            UsuarioDTO usr = new UsuarioDTO();
            usr.setClaveUsuario("defensor");
            usr.setPassword("password");
            service.registrarSolicitudCarpetaInvestigacion(16L, usrService.login(usr));
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }
	}
	
	
	public void testRegistrarSolicitudTranscripcion() {
		SolicitudTranscripcionAudienciaDTO solicitudDTO = new SolicitudTranscripcionAudienciaDTO();
		
		
		solicitudDTO.setTipoSolicitudDTO(new ValorDTO(TiposSolicitudes.AUDIENCIA.getValorId()));
		solicitudDTO.setFechaCreacion(new Date());
		solicitudDTO.setNombreSolicitante("Ricardo Gama Garcia");
		solicitudDTO.setAudiencia(new AudienciaDTO(2L));
		solicitudDTO.setAreaOrigen(Long.valueOf(Instituciones.PJ.ordinal()));
		solicitudDTO.setEstatus(new ValorDTO(EstatusSolicitud.ABIERTA.getValorId()));
		
		
		solicitudDTO.setTipoDocumentoDTO(new ValorDTO(TipoDocumento.SOLICITUD.getValorId()));
		solicitudDTO.setFormaDTO(new FormaDTO(1L));
		solicitudDTO.setNombreDocumento("Audiencia");
		solicitudDTO.setEstatus(new ValorDTO(EstatusSolicitud.ABIERTA.getValorId()));
		
		ConfInstitucionDTO conf = new ConfInstitucionDTO();
		conf.setConfInstitucionId(Long.valueOf(Instituciones.PJ.ordinal()));
		solicitudDTO.setInstitucion(conf);
		
		
		try {
			SolicitudDTO respuesta = service.registrarSolicitudTranscripcionAudiencia(solicitudDTO);
			assertTrue("El identificador de la solicitud debe ser mayor a cero : ", respuesta.getDocumentoId()>0);
			logger.info("El identificador de la solicitud debe ser mayor a cero : " + respuesta.getDocumentoId());
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
		
	}

}

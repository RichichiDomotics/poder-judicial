/**
 * Nombre del Programa : ConsultarSolicitudesAudienciaServiceImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 8 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementaci�n del servicio de consulta de solicitudes de audiencia
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
package mx.gob.segob.nsjp.service.solicitud.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudAudienciaDAO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.Solicitud;
import mx.gob.segob.nsjp.model.SolicitudAudiencia;
import mx.gob.segob.nsjp.service.solicitud.ConsultarSolicitudesAudienciaService;
import mx.gob.segob.nsjp.service.solicitud.impl.transform.SolicitudAudienciaTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementaci�n del servicio de consulta de solicitudes de audiencia.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ConsultarSolicitudesAudienciaServiceImpl
        implements
            ConsultarSolicitudesAudienciaService {
    @Autowired
    private SolicitudAudienciaDAO solicitudDao = null;
    
    @Autowired
    private NumeroExpedienteDAO noExpDao= null;

    /*
     * (non-Javadoc)
     * 
     * @see
     * mx.gob.segob.nsjp.service.solicitud.ConsultarSolicitudesAudienciaService
     * #consultarSolicitudesAudiencia(mx.gob.segob.nsjp.dto.usuario.UsuarioDTO)
     */
    @Override
    public List<SolicitudAudienciaDTO> consultarSolicitudesAudiencia(
            UsuarioDTO usuarioDto) {
    	
    	/*
 	    * Usado para obtener el discriminante Id
 	    */
 	      long discriminanteId = 0L; 
 	            
 		
 		if (usuarioDto != null
 				&& usuarioDto.getFuncionario() != null
 				&& usuarioDto.getFuncionario().getDiscriminante() != null
 				&& usuarioDto.getFuncionario().getDiscriminante().getCatDiscriminanteId() != null) {

 			discriminanteId = usuarioDto.getFuncionario().getDiscriminante()
 					.getCatDiscriminanteId();
 		}
 		
        List<SolicitudAudiencia> fromBD = this.solicitudDao.consultarSolicitudesAudienciaPendientes(discriminanteId);
        
        List<SolicitudAudienciaDTO> resp = new ArrayList<SolicitudAudienciaDTO>();

        for (SolicitudAudiencia row : fromBD) {
            resp.add(SolicitudAudienciaTransformer.transformarSolicitud(row));
        }

        return resp;
    }

    @Override
    public List<SolicitudDTO> consultarOtrasSolicitudes(UsuarioDTO usr) {
        List<Solicitud> fromBD = this.solicitudDao.consultarOtrasSolicitudesPendientes();               
        
        return SolicitudAudienciaTransformer.transformarSolicitudes(fromBD);
    }

	@Override
	public List<SolicitudAudienciaDTO> consultarSolicitudesAudienciaPorTipoyEstado(
			TiposSolicitudes tipo, EstatusSolicitud estado) {
		return SolicitudAudienciaTransformer.
		transformarSolicitudesAudiencias(solicitudDao.
				consultarSolicitudesAudienciaPorTipoyEstado(tipo, estado));
	}

	@Override
	public 	List<SolicitudAudienciaDTO> consultarSolicitudesAudienciaConCriterios (
			SolicitudAudienciaDTO solicitudAudienciaDTO,
			List<Long> lstIdEstatusSolicitud, List<Long> lstIdTipoSolicitud,
			List<Long> lstIdEstatusAudiencia, List<Long> lstIdTipoAudiencia,
			String tipoConsulta
			) throws NSJPNegocioException {
		
		SolicitudAudiencia solicitudAudiencia = SolicitudAudienciaTransformer.transformarSolicitud(solicitudAudienciaDTO);
		
		return SolicitudAudienciaTransformer.
			transformarSolicitudesAudiencias(solicitudDao.
					consultarSolicitudesAudienciaConCriterios(
							solicitudAudiencia, 
							lstIdEstatusSolicitud,
							lstIdTipoSolicitud, 
							lstIdEstatusAudiencia,
							lstIdTipoAudiencia, 
							tipoConsulta));
	}

}

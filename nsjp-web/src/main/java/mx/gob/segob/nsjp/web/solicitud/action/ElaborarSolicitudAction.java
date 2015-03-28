package mx.gob.segob.nsjp.web.solicitud.action;



import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.delegate.expediente.ExpedienteDelegate;
import mx.gob.segob.nsjp.delegate.solicitud.SolicitudDelegate;
import mx.gob.segob.nsjp.delegate.usuario.UsuarioDelegate;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.configuracion.ConfInstitucionDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.web.base.action.GenericAction;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author rgama
 */
public class ElaborarSolicitudAction extends GenericAction {

    private static final Logger LOG =
            Logger.getLogger(ElaborarSolicitudAction.class);

    @Autowired
	SolicitudDelegate solicitudDelegate;
    @Autowired
	ExpedienteDelegate expedienteDelegate;
    @Autowired
    UsuarioDelegate usuarioDelegate;
    
    /**
	 * Metodo utilizado para registrar una Solicitud 
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return null
	 * @throws IOException En caso de obtener una exception
	 */
	public ActionForward registrarSolicitud(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			LOG.info("ejecutando registrar solicitud");
			
			Long institucionSolicitante = Long.parseLong(request.getParameter("institucionSolicitante"));
			Long numeroExpedienteId = null;
			UsuarioDTO loUsuario = getUsuarioFirmado(request); 
			String solicitante = loUsuario.getFuncionario().getNombreCompleto();
			String numeroExpediente =request.getParameter("numeroExpediente");
			Long idSolicitud = NumberUtils.toLong(request.getParameter("idSolicitud"),0);
			Long idTipoSolicitud = NumberUtils.toLong(request.getParameter("idTipoSolicitud"),0);			
			String motivo =request.getParameter("motivo");
			Long solicitudIdOrigen = NumberUtils.toLong(request.getParameter("solicitudIdOrigen"),0);
			Long areaDestino = NumberUtils.toLong(request.getParameter("areaDestino"),0);
			

			
			if(numeroExpediente != null && numeroExpediente != "")
				numeroExpedienteId=super.getExpedienteTrabajo(request,numeroExpediente).getNumeroExpedienteId();
			
			String idFuncionariosSolicitantes = request.getParameter("idsFuncionariosSolicitantes");
			String[] iDsFuncionarios = null;
			if(!idFuncionariosSolicitantes.equals(""))
				iDsFuncionarios = idFuncionariosSolicitantes.split(",");
			
			
			Date fechaHora= new Date();

			LOG.info("VERIFICANDO PARAMETROS::::::::::::::::::::::::::");
			LOG.info("institucionSolicitante:" + institucionSolicitante);
			LOG.info("solicitante           :" + solicitante);			
			LOG.info("numeroExpediente      :" + numeroExpediente);			
			LOG.info("idsFuncionariosSolicitantes: " + idFuncionariosSolicitantes);			
			LOG.info("idSolicitud           : " + idSolicitud);			
			LOG.info("usuarioSolicitante    : " + loUsuario.getFuncionario());			
			LOG.info("idTipoSolicitud       : " + idTipoSolicitud);
			LOG.info("motivo                : " + motivo);
			LOG.info("solicitudIdOrigen     : " + solicitudIdOrigen);
			LOG.info("areaDestino     : " + areaDestino);
			
						
			
			ValorDTO tipoSolicitudDTO = new ValorDTO();
			SolicitudDTO solicitudDTO = new SolicitudDTO();
			ExpedienteDTO expedienteDTO = new ExpedienteDTO();
			ConfInstitucionDTO confInstitucionDTO = null;
			if(institucionSolicitante != null && institucionSolicitante>0){
				confInstitucionDTO = new ConfInstitucionDTO();
				confInstitucionDTO.setConfInstitucionId(institucionSolicitante);	
			}
								
			expedienteDTO.setNumeroExpedienteId(numeroExpedienteId);
			
			if(idTipoSolicitud != 0 && idTipoSolicitud == TiposSolicitudes.POLICIA_MINISTERIAL.getValorId().longValue()){
				tipoSolicitudDTO.setIdCampo(TiposSolicitudes.POLICIA_MINISTERIAL.getValorId());
				solicitudDTO.setAreaDestino((long) Areas.COORDINACION_POLICIA_MINISTERIAL.ordinal());
			}else{
				if(idTipoSolicitud != 0)
					tipoSolicitudDTO.setIdCampo(idTipoSolicitud);
				else
					tipoSolicitudDTO.setIdCampo(TiposSolicitudes.APOYO.getValorId());
			}
			
			solicitudDTO.setTipoSolicitudDTO(tipoSolicitudDTO);
			solicitudDTO.setEstatus(new ValorDTO(EstatusSolicitud.ABIERTA.getValorId()));
			
			solicitudDTO.setNombreDocumento("Solicitud");
			solicitudDTO.setFechaCreacion(new Date());
			
			solicitudDTO.setTipoDocumentoDTO(new ValorDTO(TipoDocumento.SOLICITUD.getValorId()));
			solicitudDTO.setFormaDTO(new FormaDTO(1L));
			
			solicitudDTO.setExpedienteDTO(expedienteDTO);	
			solicitudDTO.setFechaCreacion(fechaHora);
			solicitudDTO.setInstitucion(confInstitucionDTO);
			solicitudDTO.setNombreSolicitante(solicitante);
			solicitudDTO.setUsuarioSolicitante(loUsuario.getFuncionario());
			if(motivo != null)
				solicitudDTO.setMotivo(motivo);
			//solicitudDTO.setFolioSolicitud("foliotest");
			solicitudDTO.setDocumentoId(idSolicitud);
			
			//FIXME: tomara el ID del ultimo Destinatario, se ajsutara cuando la entidad Solicitud soporte una lista de destinatarios
			for (int i=0; i< iDsFuncionarios.length; i++) {
				FuncionarioDTO loDestinatario = new FuncionarioDTO(Long.parseLong(iDsFuncionarios[i]));
				solicitudDTO.setDestinatario(loDestinatario);
				UsuarioDTO usuarioDTO=usuarioDelegate.consultarUsuarioPorClaveFuncionario(Long.parseLong(iDsFuncionarios[i]));
				//Tomamos el area del usuario destinatario
				if(areaDestino != null && areaDestino>0L){
					solicitudDTO.setAreaDestino(areaDestino);
				}
				//Quitar cuando aseguremos que todos los flujos que usan este metodo funcionan correctamenete
				else{
					solicitudDTO.setAreaDestino(usuarioDTO.getAreaActual().getAreaId());
				}
			}
			
			//Obtener el area origen de la solicitud que se esta contestando, si en nueva se toma la del usuario
			//if(solicitudIdOrigen != null && solicitudIdOrigen > 0L){
			//	SolicitudDTO solicitudOrigen = new SolicitudDTO();
			//	solicitudOrigen.setDocumentoId(solicitudIdOrigen);
			//	solicitudOrigen = solicitudDelegate.obtenerDetalleSolicitud(solicitudOrigen);
			//	solicitudDTO.setAreaDestino(solicitudOrigen.getAreaOrigen());
			//}
			
			UsuarioDTO usuarioDTO=super.getUsuarioFirmado(request);
			solicitudDTO.setAreaOrigen(usuarioDTO.getAreaActual().getAreaId());
			SolicitudDTO solicitudDTO2 = solicitudDelegate.registrarSolicitud(solicitudDTO);
			
			LOG.info("guarda con exito" + solicitudDTO2);
			// revisamos si el guardado fue exitoso para mandar el xml
			// correspondiente
			if (solicitudDTO2 != null
					&& solicitudDTO2.getDocumentoId() != null) {
				converter.alias("SolicitudDTO", SolicitudDTO.class);
				String xml = converter.toXML(solicitudDTO2);
				LOG.info(xml);
				escribirRespuesta(response, xml);
			} else {
				solicitudDTO2.setDocumentoId(0L);
				converter.alias("SolicitudDTO", SolicitudDTO.class);
				String xml = converter.toXML(solicitudDTO2);
				LOG.info(xml);
				escribirRespuesta(response, xml);
			}
		} catch (Exception e) {		
			LOG.info(e.getCause(),e);
			
		}
		return null;
	}
}

/**
* Nombre del Programa : SolicitudDiligenciaTransformer.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 29 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Transforma de SolicitudDiligencia a SolicitudDiligenciaDTO
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
package mx.gob.segob.nsjp.service.solicitud.impl.transform;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dto.archivo.SolicitudAdjuntosDTO;
import mx.gob.segob.nsjp.dto.archivo.SolicitudAdjuntosIdDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.AcuseReciboDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDiligenciaDTO;
import mx.gob.segob.nsjp.model.AcuseRecibo;
import mx.gob.segob.nsjp.model.ConfInstitucion;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.SolicitudAdjuntos;
import mx.gob.segob.nsjp.model.SolicitudDiligencia;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.acuse.impl.transform.AcuseTransformer;
import mx.gob.segob.nsjp.service.archivo.impl.transform.ArchivoDigitalTransformer;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;

/**
 * Transforma de SolicitudDiligencia a SolicitudDiligenciaDTO.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class SolicitudDiligenciaTransformer {

	public static SolicitudDiligencia transformarSolicitudDiligencia(
			SolicitudDiligenciaDTO solicitudDiligenciaDTO) {
		SolicitudDiligencia solicitudDiligencia = new SolicitudDiligencia();
		
		//Solicitud Diligencia
		if (solicitudDiligenciaDTO.getTipoDiligencia()!=null)
			solicitudDiligencia.setTipoDiligencia(new Valor(solicitudDiligenciaDTO.getTipoDiligencia().getIdCampo()));
		
		//Solicitud
		solicitudDiligencia.setAreaOrigen(solicitudDiligenciaDTO.getAreaOrigen());
		solicitudDiligencia.setNumeroCasoAsociado(solicitudDiligenciaDTO.getNumeroCasoAsociado());
		
		if (solicitudDiligenciaDTO.getTipoSolicitudDTO() != null)
			solicitudDiligencia.setTipoSolicitud(new Valor(solicitudDiligenciaDTO.getTipoSolicitudDTO().getIdCampo()));		
		if (solicitudDiligenciaDTO.getExpedienteDTO() != null)
			solicitudDiligencia.setNumeroExpediente(new NumeroExpediente(solicitudDiligenciaDTO.getExpedienteDTO().getNumeroExpedienteId()));
		if (solicitudDiligenciaDTO.getTipoSolicitudDTO() != null)
			solicitudDiligencia.setTipoSolicitud(new Valor(solicitudDiligenciaDTO.getTipoSolicitudDTO().getIdCampo()));
		if (solicitudDiligenciaDTO.getUsuarioSolicitante() != null)
			solicitudDiligencia.setFuncionarioSolicitante(new Funcionario(solicitudDiligenciaDTO.getUsuarioSolicitante().getClaveFuncionario()));
		if (solicitudDiligenciaDTO.getInstitucion() != null)
			solicitudDiligencia.setConfInstitucion(new ConfInstitucion(solicitudDiligenciaDTO.getInstitucion().getConfInstitucionId()));
		if (solicitudDiligenciaDTO.getUsuarioSolicitante() != null)
			solicitudDiligencia.setFuncionarioSolicitante(new Funcionario(solicitudDiligenciaDTO.getUsuarioSolicitante().getClaveFuncionario()));

		solicitudDiligencia.setNumeroCasoAsociado(solicitudDiligenciaDTO.getNumeroCasoAsociado());
		solicitudDiligencia.setMotivo(solicitudDiligenciaDTO.getMotivo());
		solicitudDiligencia.setFechaLimite(solicitudDiligenciaDTO.getFechaLimite());
		solicitudDiligencia.setFechaModificacion(solicitudDiligenciaDTO.getFechaModificacion());
		solicitudDiligencia.setFechaCierre(solicitudDiligenciaDTO.getFechaCierre());
		solicitudDiligencia.setNombreSolicitante(solicitudDiligenciaDTO.getNombreSolicitante());
		// solicitud.setEstatus(new EstatusSolicitud.ABIERTA);

		// Documento
		if (solicitudDiligenciaDTO.getTipoDocumentoDTO() != null)
			solicitudDiligencia.setTipoDocumento(new Valor(solicitudDiligenciaDTO.getTipoDocumentoDTO().getIdCampo()));
		if (solicitudDiligenciaDTO.getFormaDTO() != null)
			solicitudDiligencia.setForma(new Forma(solicitudDiligenciaDTO.getFormaDTO().getFormaId()));

		solicitudDiligencia.setFechaCreacion(solicitudDiligenciaDTO.getFechaCreacion());
		
		return solicitudDiligencia;
	}

	public static SolicitudDiligenciaDTO transformarSolicitudDiligencia(
			SolicitudDiligencia solicitudDiligencia) {
		
		SolicitudDiligenciaDTO solicitudDiligenciaDTO = new SolicitudDiligenciaDTO();	

		//Solicitud Diligencia
		if (solicitudDiligencia.getTipoDiligencia()!=null)
			solicitudDiligenciaDTO.setTipoDiligencia(new ValorDTO(solicitudDiligencia.getTipoDiligencia().getValorId(), solicitudDiligencia.getTipoDiligencia().getValor()));
		
		// Solicitud
		solicitudDiligenciaDTO.setFolioSolicitud(solicitudDiligencia.getFolioSolicitud());
		solicitudDiligenciaDTO.setDocumentoId(solicitudDiligencia.getDocumentoId());
		solicitudDiligenciaDTO.setFechaCreacion(solicitudDiligencia.getFechaCreacion());
		solicitudDiligenciaDTO.setStrFechaCreacion(DateUtils.formatear(solicitudDiligencia.getFechaCreacion()));
		solicitudDiligenciaDTO.setStrHoraCreacion(DateUtils.formatearHora(solicitudDiligencia.getFechaCreacion()));
		solicitudDiligenciaDTO.setDestinatario(FuncionarioTransformer.transformarFuncionario(solicitudDiligencia.getDestinatario()));
		
		if (solicitudDiligencia.getTipoSolicitud() != null) {	
			solicitudDiligenciaDTO.setTipoSolicitudDTO(new ValorDTO(solicitudDiligencia.getTipoSolicitud().getValorId(), solicitudDiligencia.getTipoSolicitud().getValor()));
		}

		Set<AcuseRecibo> acuces = solicitudDiligencia.getAcuseRecibos();
		if (acuces != null && !acuces.isEmpty()) {
			solicitudDiligenciaDTO.setFechaCierre(acuces.iterator().next()
					.getFechaAcuse());
		}

		// Institución solicitante
		if (solicitudDiligencia.getConfInstitucion() != null) {
			solicitudDiligenciaDTO.setInstitucion(ConfInstitucionTransformer.transformarInstitucion(solicitudDiligencia.getConfInstitucion()));
			solicitudDiligenciaDTO.setNombreInstitucionSolicitante(solicitudDiligencia.getConfInstitucion().getNombreInst());
		}

		// Nombre del solicitante
		solicitudDiligenciaDTO.setNombreSolicitanteExternoInterno(solicitudDiligencia.getNombreSolicitante());

		// Estado
		if (solicitudDiligencia.getEstatus() != null) {
			solicitudDiligenciaDTO.setEstatus(new ValorDTO(solicitudDiligencia.getEstatus().getValorId(), solicitudDiligencia.getEstatus().getValor()));
		}

		// Número de causa
		// Número de TOCA (si existe) (Nota 2)
		// Identificador de Audiencia (si existe) (Nota 2)
		if (solicitudDiligencia.getExpediente() != null) {
			ExpedienteDTO expedienteDTO = ExpedienteTransformer
					.transformaExpediente(solicitudDiligencia.getNumeroExpediente().getExpediente());
			expedienteDTO.setNumeroExpedienteId(solicitudDiligencia.getNumeroExpediente().getNumeroExpedienteId());
			solicitudDiligenciaDTO.setExpedienteDTO(expedienteDTO);
		}

		Set<AcuseRecibo> acuses = solicitudDiligencia.getAcuseRecibos();
		List<AcuseReciboDTO> acusesReciboDTO = new ArrayList<AcuseReciboDTO>();
		for (AcuseRecibo acuseRecibo : acuses) {
			acusesReciboDTO.add(AcuseTransformer.transformarDTO(acuseRecibo));
		}
		solicitudDiligenciaDTO.setAcusesReciboDTO(acusesReciboDTO);
		solicitudDiligenciaDTO.setOrigenDocumento(solicitudDiligencia.getOrigenDocumento());

		if(solicitudDiligencia.getArchivosAdjuntos()!= null && !solicitudDiligencia.getArchivosAdjuntos().isEmpty()){
			SolicitudAdjuntosDTO archivoDTO = null;
			SolicitudAdjuntosIdDTO idDTO = null;
			for(SolicitudAdjuntos archivo : solicitudDiligencia.getArchivosAdjuntos()){
				archivoDTO = new SolicitudAdjuntosDTO();
				archivoDTO.setArchivoDigital(ArchivoDigitalTransformer.transformarArchivoDigital(archivo.getArchivoDigital()));
				archivoDTO.setSolicitud(solicitudDiligenciaDTO);
				idDTO = new SolicitudAdjuntosIdDTO();
				idDTO.setArchivoDigitalId(archivo.getId().getArchivoDigitalId());
				idDTO.setSolicitudId(archivo.getId().getSolicitudId());
				archivoDTO.setId(idDTO);
				solicitudDiligenciaDTO.getArchivosAdjuntos().add(archivoDTO);
			}
		}
		
		return solicitudDiligenciaDTO;
	}

	
}

/**
 * Nombre del Programa : AvisoDetencionTransformer.java
 * Autor                            : Hugo Serrano
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 24 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Clase para la transfomracion de objetos de tipo AvisoDetencion
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
package mx.gob.segob.nsjp.service.avisodetencion.impl.transform;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.dto.configuracion.ConfInstitucionDTO;
import mx.gob.segob.nsjp.dto.documento.AvisoDetencionDTO;
import mx.gob.segob.nsjp.model.AvisoDetencion;
import mx.gob.segob.nsjp.model.AvisoDetencionDelito;
import mx.gob.segob.nsjp.service.delito.impl.transform.DelitoTransfromer;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.DetencionTransformer;

/**
 * Clase para la transfomracion de objetos de tipo AvisoDetencion
 * 
 * @version 1.0
 * @author Tattva-IT
 * 
 */
public class AvisoDetencionTransformer {

	public static List<AvisoDetencionDTO> transformarAvisosDetencion(
			List<AvisoDetencion> lsAv) {
		List<AvisoDetencionDTO> lsADTO = new ArrayList<AvisoDetencionDTO>();

		for (AvisoDetencion a : lsAv) {
			lsADTO.add(transformarAvisoDetencion(a));
		}

		return lsADTO;

	}

	public static AvisoDetencionDTO transformarAvisoDetencion(
			AvisoDetencion aviso) {
		AvisoDetencionDTO dto = new AvisoDetencionDTO();

		dto.setDocumentoId(aviso.getDocumentoId());
		dto.setConsecutivoNotificacion(aviso.getConsecutivoNotificacion());
		dto.setDetenido(aviso.getDetenido());
		dto.setDireccionCitado(aviso.getDomicilio());
		dto.setFechaCitado(aviso.getFechaCitado());
		if (aviso.getDetencion() != null) {
			dto.setFechaDetencion(aviso.getDetencion()
					.getFechaInicioDetencion());
		}
		dto.setNumeroCasoAsociado(aviso.getNumeroCasoAsociado());
		dto.setFechaCreacion(aviso.getFechaCreacion());
		dto.setFolioDocumento(aviso.getFolioDocumento());
		dto.setConfInstitucion(new ConfInstitucionDTO(aviso
				.getConfInstitucion().getConfInstitucionId(), aviso
				.getConfInstitucion().getNombreInst()));
		dto.setFolioNotificacion(aviso.getFolioNotificacion());
		dto.setMotivo(aviso.getMotivo());
		dto.setLugar(aviso.getLugar());
		dto.setLugarCitado(aviso.getLugarCitado());
		dto.setPenalidades(aviso.getPenalidades());
		dto.setOrigenDocumento(aviso.getOrigenDocumento());
		dto.setExpedienteDTO(ExpedienteTransformer.transformaExpediente(aviso
				.getExpediente()));
		// Fin todo

		if (aviso.getDetencion() != null) {
			dto.setDetencion(DetencionTransformer.transformarDetencion(aviso
					.getDetencion()));
		}

		if (aviso.getAvisoDetencionDelitos() != null) {
			for (AvisoDetencionDelito delito : aviso.getAvisoDetencionDelitos()) {
				dto.getDelitos().add(DelitoTransfromer.transformar(delito));
			}
		}
		//Permite transformar la informacion de la agencia solicitante
		dto.setClaveDiscriminanteOrigen(aviso.getClaveDiscriminanteOrigen());
		dto.setCatDiscriminanteOrigen(aviso.getCatDiscriminanteOrigen());

		return dto;
	}

	public static AvisoDetencion transformarAvisoDetencion(
			AvisoDetencionDTO aviso) {
		AvisoDetencion dto = new AvisoDetencion();
		dto.setDocumentoId(aviso.getDocumentoId());
		dto.setConsecutivoNotificacion(aviso.getConsecutivoNotificacion());
		dto.setDetenido(aviso.getDetenido());
		dto.setDomicilio(aviso.getDireccionCitado());
		dto.setFechaCitado(aviso.getFechaCitado());
		dto.setFechaCreacion(aviso.getFechaCreacion());
		dto.setFolioDocumento(aviso.getFolioDocumento());
		dto.setMotivo(aviso.getMotivo());
		dto.setLugar(aviso.getLugar());
		dto.setLugarCitado(aviso.getLugarCitado());
		dto.setPenalidades(aviso.getPenalidades());
		dto.setOrigenDocumento(aviso.getOrigenDocumento());

		if (aviso.getExpedienteDTO() != null) {
			dto.setExpediente(ExpedienteTransformer.transformarExpediente(aviso
					.getExpedienteDTO()));
		}

		if (aviso.getDetencion() != null) {
			dto.setDetencion(DetencionTransformer.transformarDetencion(aviso
					.getDetencion()));
		}

		return dto;
	}

}

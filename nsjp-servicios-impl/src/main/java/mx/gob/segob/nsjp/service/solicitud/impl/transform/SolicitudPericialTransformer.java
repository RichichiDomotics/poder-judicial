/**
* Nombre del Programa : SolicitudPericialTransformer.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 3 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Transforma el objeto en SolicitudPericial a SolicitudPericialDTO y viceversa
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

import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudPericialDTO;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.SolicitudPericial;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;

/**
 * Transforma el objeto en SolicitudPericial a SolicitudPericialDTO y viceversa.
 * @version 1.0
 * @author rgama
 *
 */
public class SolicitudPericialTransformer {

	public static SolicitudPericial solPericialTransformer (SolicitudPericialDTO solPericialDTO) {
		
		SolicitudPericial solPericial = new SolicitudPericial();
		
		//Atributos de Documento
		solPericial.setDocumentoId(solPericialDTO.getDocumentoId());
		solPericial.setNombreDocumento(solPericialDTO.getNombreDocumento());
		solPericial.setFolioDocumento(solPericialDTO.getFolioDocumento());
		solPericial.setFolioSolicitud(solPericialDTO.getFolioSolicitud());
		if (solPericialDTO.getTipoDocumentoDTO()!=null)
			solPericial.setTipoDocumento( new Valor(solPericialDTO.getTipoDocumentoDTO().getIdCampo()));
		if (solPericialDTO.getFormaDTO() != null )//&& solPericialDTO.getFormaDTO().getFormaId()!= null )			
			solPericial.setForma (new Forma((solPericialDTO.getFormaDTO().getFormaId() ) ) );

		if(solPericialDTO.getObservaciones()!=null)
			solPericial.setObservaciones(solPericialDTO.getObservaciones());
		//Atributos propios de la solicitud pericial		
		solPericial.setFechaCreacion(solPericialDTO.getFechaCreacion());
		solPericial.setFechaModificacion(solPericialDTO.getFechaModificacion());
		solPericial.setFechaLimite(solPericialDTO.getFechaLimite());
		solPericial.setFechaCierre(solPericialDTO.getFechaCierre());
		
		if (solPericialDTO.getEspecialidad()!=null)
			solPericial.setEspecialidad(new Valor(solPericialDTO.getEspecialidad().getIdCampo()));		
		
		solPericial.setEsEntregaNotificacionFisica(solPericialDTO.getEsEntregaNotificacionFisica());
		
		if (solPericialDTO.getEstatus()!= null)
			solPericial.setEstatus(new Valor(solPericialDTO.getEstatus().getIdCampo()));
		
		solPericial.setMotivo(solPericialDTO.getMotivo());
		
		if (solPericialDTO.getTipoSolicitudDTO()!=null)
			solPericial.setTipoSolicitud(new Valor(solPericialDTO.getTipoSolicitudDTO().getIdCampo()));
		
		ExpedienteDTO loExpedienteDTO = solPericialDTO.getExpedienteDTO();
		if (loExpedienteDTO!= null){
			NumeroExpediente loNumeroExpediente = new NumeroExpediente();
			loNumeroExpediente.setNumeroExpedienteId(loExpedienteDTO.getNumeroExpedienteId());
			solPericial.setNumeroExpediente(loNumeroExpediente);
		}		

		if (solPericialDTO.getUsuarioSolicitante()!=null){
		    Funcionario funcionario = new Funcionario(solPericialDTO.getUsuarioSolicitante().getClaveFuncionario());
		    solPericial.setFuncionarioSolicitante(funcionario);
		}
		solPericial.setPuestoUsuarioSolicitante(solPericialDTO.getPuestoUsuarioSolicitante());
		solPericial.setNombreSolicitante(solPericialDTO.getNombreSolicitante());

		return solPericial;
	}
	
	public static SolicitudPericialDTO solPericialTransformer(SolicitudPericial aoSolPericial) {
		if(aoSolPericial ==null){
			return null;
		}
		SolicitudPericialDTO solPericialDTO = new SolicitudPericialDTO();	
		
		//Atributos de Documento
		solPericialDTO.setDocumentoId(aoSolPericial.getDocumentoId());
		solPericialDTO.setNombreDocumento(aoSolPericial.getNombreDocumento());
		solPericialDTO.setFolioDocumento(aoSolPericial.getFolioDocumento());
		solPericialDTO.setFolioSolicitud(aoSolPericial.getFolioSolicitud());
		solPericialDTO.setTipoDocumentoDTO(new ValorDTO(aoSolPericial.getTipoDocumento().getValorId(), aoSolPericial.getTipoDocumento().getValor()));
		solPericialDTO.setFormaDTO( new FormaDTO( aoSolPericial.getForma().getFormaId()));
		if(aoSolPericial.getObservaciones()!=null)			
			solPericialDTO.setObservaciones(aoSolPericial.getObservaciones());
		//Atributos propios de la solicitud pericial
		solPericialDTO.setFechaModificacion(aoSolPericial.getFechaModificacion());
		solPericialDTO.setFechaCreacion(aoSolPericial.getFechaCreacion());		
		solPericialDTO.setFechaLimite(aoSolPericial.getFechaLimite());
		solPericialDTO.setFechaCierre(aoSolPericial.getFechaCierre());

		if (aoSolPericial.getEspecialidad()!=null)
		if (aoSolPericial.getEspecialidad()!=null){
		solPericialDTO.setEspecialidad(
				new ValorDTO(aoSolPericial.getEspecialidad().getValorId(), aoSolPericial.getEspecialidad().getValor()));
		}
		solPericialDTO.setEsEntregaNotificacionFisica(aoSolPericial.getEsEntregaNotificacionFisica());
		if (aoSolPericial.getEstatus()!=null) {
		    solPericialDTO.setEstatus(new ValorDTO(aoSolPericial.getEstatus().getValorId(), aoSolPericial.getEstatus().getValor()));
		}
		solPericialDTO.setMotivo(aoSolPericial.getMotivo());
		
		solPericialDTO.setTipoSolicitudDTO(new ValorDTO(aoSolPericial.getTipoSolicitud().getValorId(),aoSolPericial.getTipoSolicitud().getValor()));
		
		if(aoSolPericial.getNumeroExpediente()!= null){
			ExpedienteDTO loExpDTO = new ExpedienteDTO();
			loExpDTO.setNumeroExpedienteId(aoSolPericial.getNumeroExpediente().getNumeroExpedienteId());
			loExpDTO.setNumeroExpediente(aoSolPericial.getNumeroExpediente().getNumeroExpediente());
			loExpDTO.setExpedienteId(aoSolPericial.getNumeroExpediente().getExpediente().getExpedienteId());
			solPericialDTO.setExpedienteDTO(loExpDTO);		
			
			if (aoSolPericial.getNumeroExpediente().getExpediente() != null &&
					aoSolPericial.getNumeroExpediente().getExpediente().getCaso()!= null){
				solPericialDTO.getExpedienteDTO().setCasoDTO(
						new CasoDTO(aoSolPericial.getNumeroExpediente().getExpediente().getCaso().getCasoId(),
								aoSolPericial.getNumeroExpediente().getExpediente().getCaso().getNumeroGeneralCaso()));
			}
		}		
		
		solPericialDTO.setUsuarioSolicitante(FuncionarioTransformer.transformarFuncionario(aoSolPericial.getFuncionarioSolicitante()));
		
		solPericialDTO.setNombreSolicitante(aoSolPericial.getNombreSolicitante());
		
		solPericialDTO.setSolicitudesHijas(new ArrayList<SolicitudPericialDTO>());
		SolicitudPericialDTO hijaDTO = null;
		for(SolicitudPericial hija:aoSolPericial.getSolicitudesHijas()){
			hijaDTO = solPericialTransformer(hija);
			solPericialDTO.getSolicitudesHijas().add(hijaDTO);
		
		}

		return solPericialDTO;
	}
	
}

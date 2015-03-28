/**
* Nombre del Programa : InformePolicialHomologadoWSDTOTransformer.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 07/09/2011
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
package mx.gob.segob.nsjp.service.infra.impl.transform.enviarinformepolicialhomologado;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.informepolicial.InformePolicialHomologadoDTO;
import mx.gob.segob.nsjp.service.infra.impl.transform.WsTransformer;
import mx.gob.segob.nsjp.ws.cliente.enviarinformepolicialhomologado.InformePolicialHomologadoWSDTO;

import org.apache.log4j.Logger;

/**
 * @version 1.0
 * @author GustavoBP
 */
public class InformePolicialHomologadoWSDTOTransformer {
	private final static Logger logger = Logger.getLogger(InformePolicialHomologadoWSDTOTransformer.class);
	
	public static InformePolicialHomologadoWSDTO transformarWSDTO(InformePolicialHomologadoDTO iphDTO){
		InformePolicialHomologadoWSDTO iphWSDTO = new InformePolicialHomologadoWSDTO();
		
		if(iphDTO==null)
			return iphWSDTO;
		
		//Datos Generales del IPH
		iphWSDTO.setInformePolicialHomologadoId(iphDTO.getInformePolicialHomologadoId());
		if(iphDTO.getTipoParticipacion()!= null)
			iphWSDTO.setTipoParticipacion(iphDTO.getTipoParticipacion().getIdCampo());
		
		iphWSDTO.setFechaCaptura(WsTransformer.transformFecha(  iphDTO.getFechaCaptura()));
		iphWSDTO.setObjetivosGenerales(iphDTO.getObjetivosGenerales());
		iphWSDTO.setNumeroOficio(iphDTO.getNumeroOficio());
		iphWSDTO.setAnio(iphDTO.getAnio());
		iphWSDTO.setFolioIPH(iphDTO.getFolioIPH());
		
		if(iphDTO.getExpediente()!= null)
			iphWSDTO.setExpediente( ExpedienteIPHWSDTOTransformer.transformarWSDTO(iphDTO.getExpediente()));
		
		return iphWSDTO;
	}
	
	public static InformePolicialHomologadoDTO transformarWSDTO(mx.gob.segob.nsjp.dto.informepolicial.InformePolicialHomologadoWSDTO iphWSDTO){
		InformePolicialHomologadoDTO iphDTO = new InformePolicialHomologadoDTO();
		
		if(iphWSDTO==null)
			return iphDTO;
		
		//Datos Generales del IPH
		iphDTO.setInformePolicialHomologadoId(iphWSDTO.getInformePolicialHomologadoId());
		if(iphWSDTO.getTipoParticipacion()!= null)
			iphDTO.setTipoParticipacion(new ValorDTO ( iphWSDTO.getTipoParticipacion()) );
		iphDTO.setFechaCaptura(iphWSDTO.getFechaCaptura());
		iphDTO.setObjetivosGenerales(iphWSDTO.getObjetivosGenerales());
		iphDTO.setNumeroOficio(iphWSDTO.getNumeroOficio());
		iphDTO.setAnio(iphWSDTO.getAnio());
		iphDTO.setFolioIPH(iphWSDTO.getFolioIPH());
		
		if(iphWSDTO.getExpediente()!= null)
			iphDTO.setExpediente( ExpedienteIPHWSDTOTransformer.expedienteWsdto2ExpedienteDto(iphWSDTO.getExpediente()));
		
		return iphDTO;
	}
	
}

/**
* Nombre del Programa : DelitoTransfromer.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 31 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Transfroma el objeto Delito a DelitoDTO y viceversa
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
package mx.gob.segob.nsjp.service.delito.impl.transform;

import mx.gob.segob.nsjp.dto.catalogo.CatDelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.model.AvisoDetencionDelito;
import mx.gob.segob.nsjp.model.CatDelito;
import mx.gob.segob.nsjp.model.Delito;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.ws.cliente.avisodetencion.DelitoWSDTO;

/**
 * Transfroma el objeto Delito a DelitoDTO y viceversa.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class DelitoTransfromer {

	/**
	 * Transforma un DelitoDTO a un objeto Delito
	 * @param delitoDTO
	 * @return
	 */
	public static Delito transformarDelito (DelitoDTO delitoDTO) {
		Delito delito = new Delito();
		
		delito.setEsPrincipal(delitoDTO.getEsPrincipal());
		delito.setEsProbable(delitoDTO.getEsProbable());
		delito.setCatDelito(new CatDelito(delitoDTO.getCatDelitoDTO().getCatDelitoId()));
		
		delito.setDelitoId(delitoDTO.getDelitoId());
		if (delitoDTO.getExpedienteDTO()!=null)
			delito.setExpediente(new Expediente(delitoDTO.getExpedienteDTO().getExpedienteId()));
		
		return delito;		
	}
	
	/**
	 * 
	 * @param delitoDTO
	 * @return
	 */
	public static DelitoDTO transformarDelito (Delito delito) {
		DelitoDTO delitoDTO = new DelitoDTO();
		
		delitoDTO.setDelitoId(delito.getDelitoId());
		delitoDTO.setEsPrincipal(delito.getEsPrincipal());
		delitoDTO.setEsProbable(delito.getEsProbable());
		delitoDTO.setCatDelitoDTO(new CatDelitoDTO(delito.getCatDelito().getCatDelitoId(), delito.getCatDelito().getClaveDelito(), delito.getCatDelito().getNombre(), delito.getCatDelito().getEsGrave()));
		delitoDTO.setNombreDelito(delito.getCatDelito().getNombre());
		
		if (delito.getExpediente()!=null)
			delitoDTO.setExpedienteDTO(new ExpedienteDTO(delito.getExpediente().getExpedienteId()));
		
		return delitoDTO;		
	}
	
	
	/**
	 * Copia la informacion de un objeto delito a otra instancia del mismo pero sin el identificador
	 * @param delitoDTO
	 * @return
	 */
	public static DelitoDTO copiarDelito (Delito delito) {
		DelitoDTO delitoDTO = new DelitoDTO();
		
		delitoDTO.setEsPrincipal(delito.getEsPrincipal());
		delitoDTO.setEsProbable(delito.getEsProbable());
//		delitoDTO.setNombreDelito(delito.getNombreDelito());
		
		if (delito.getExpediente()!=null)
			delitoDTO.setExpedienteDTO(new ExpedienteDTO(delito.getExpediente().getExpedienteId()));
		
		return delitoDTO;		
	}


	public static DelitoWSDTO transformar(DelitoDTO delito){
		DelitoWSDTO del = new DelitoWSDTO();
		del.setEsPrincipal(delito.getEsPrincipal());
		del.setEsProbable(delito.getEsProbable());
		del.setNombreDelito(delito.getCatDelitoDTO().getNombre());
		return del;
	}

	
	public static DelitoDTO transformar(AvisoDetencionDelito delito) {
		DelitoDTO delitoDTO = new DelitoDTO();
		
		delitoDTO.setDelitoId(delito.getDelito().getDelitoId());
		delitoDTO.setEsPrincipal(delito.getDelito().getEsPrincipal());
		delitoDTO.setEsProbable(delito.getDelito().getEsProbable());
		delitoDTO.setCatDelitoDTO(new CatDelitoDTO(delito.getDelito().getCatDelito().getCatDelitoId(), delito.getDelito().getCatDelito().getClaveDelito(), delito.getDelito().getCatDelito().getNombre(), delito.getDelito().getCatDelito().getEsGrave()));
		delitoDTO.setNombreDelito(delito.getDelito().getCatDelito().getNombre());
		
		if (delito.getDelito().getExpediente()!=null)
			delitoDTO.setExpedienteDTO(new ExpedienteDTO(delito.getDelito().getExpediente().getExpedienteId()));
		
		return delitoDTO;	
	}
}

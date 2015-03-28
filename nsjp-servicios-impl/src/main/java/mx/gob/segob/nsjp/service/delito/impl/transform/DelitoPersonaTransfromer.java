/**
* Nombre del Programa : DelitoPersonaTransfromer.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 23 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Transfroma el objeto DelitoPersona a DelitoPersonaDTO y viceversa
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

import java.util.HashSet;
import java.util.Set;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoPersonaDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.model.Delito;
import mx.gob.segob.nsjp.model.DelitoPersona;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;

/**
 * Transfroma el objeto DelitoPersona a DelitoPersonaDTO y viceversa.
 * @version 1.0
 * @author rgama
 *
 */
public class DelitoPersonaTransfromer {

	
	/**
	 * 
	 * @param delitoDTO
	 * @return
	 */
	public static DelitoDTO transformarDelitoPersona(DelitoPersona delitoPersona) {
		DelitoDTO delitoDTO = new DelitoDTO();
//		delitoDTO.setNombreDelito(delitoPersona.getDelito().getNombreDelito());
		delitoDTO.setDelitoId(delitoPersona.getDelito().getDelitoId());
		delitoDTO.setEsProbable(delitoPersona.getDelito().getEsProbable());
		delitoDTO.setEsPrincipal(delitoPersona.getDelito().getEsPrincipal());
		return delitoDTO;		
	}
	
	
	public static Set<DelitoPersona> transformarDelitosADelitosPersona(Set<Delito> delitos,Involucrado involucrado) {
		Set<DelitoPersona> loDP = new HashSet<DelitoPersona>();
		for (Delito loDelito : delitos) {			
			DelitoPersona delitoPersona = new DelitoPersona();
			delitoPersona.setDelito(loDelito);
			delitoPersona.setProbableResponsable(involucrado);
			//TODO: Cambiar el valor
			delitoPersona.setFormaParticipacion(new Valor(1L));
			delitoPersona.setCatDelitoClasificacionId(null);
			delitoPersona.setCatDelitoLugarId(null);
			delitoPersona.setCatDelitoModalidadId(null);
			delitoPersona.setCatDelitoModusId(null);
			delitoPersona.setCatDelitoCausaId(null);
			loDP.add(delitoPersona);
		}
		
		return loDP;		
	}


	public static DelitoPersonaDTO transformarDelitoPersonaDTO(
			DelitoPersona dPer) {
		ValorDTO formaParticipacion = new ValorDTO();
		if (dPer.getFormaParticipacion()!=null) {
			formaParticipacion=new ValorDTO(dPer.getFormaParticipacion().getValorId(), dPer.getFormaParticipacion().getValor());
		}
		DelitoDTO delito=DelitoTransfromer.transformarDelito(dPer.getDelito());
		InvolucradoDTO probableResponsable=InvolucradoTransformer.transformarInvolucradoBasico(dPer.getProbableResponsable());
		
		ValorDTO bienTutelado=null;
		InvolucradoDTO victima=null; 	
		Long catDelitoClasificacionId=null;
	    Long catDelitoLugarId=null;
	    Long catDelitoModalidadId=null;
	    Long catDelitoModusId=null;
	    Long catDelitoCausaId=null;
		
		if(dPer.getBienTutelado()!=null)
			if(dPer.getBienTutelado().getValorId()!=null)
			bienTutelado=new ValorDTO(dPer.getBienTutelado().getValorId());
		
		if(dPer.getVictima()!=null)
				victima= InvolucradoTransformer.transformarInvolucradoBasico(dPer.getVictima());
		
		if(dPer.getCatDelitoClasificacionId()!=null)
			catDelitoClasificacionId = dPer.getCatDelitoClasificacionId();
		
		if(dPer.getCatDelitoLugarId()!=null)
			catDelitoLugarId = dPer.getCatDelitoLugarId();
		
		if(dPer.getCatDelitoModalidadId()!=null)
			catDelitoModalidadId = dPer.getCatDelitoModalidadId();
		
		if(dPer.getCatDelitoModusId()!=null)
			catDelitoModusId = dPer.getCatDelitoModusId();
		
		if(dPer.getCatDelitoCausaId()!=null)
			catDelitoCausaId = dPer.getCatDelitoCausaId();
		
		DelitoPersonaDTO dto=new DelitoPersonaDTO(dPer.getDelitoPersonaId(), bienTutelado, formaParticipacion, delito, victima, probableResponsable,
				catDelitoClasificacionId, catDelitoLugarId, catDelitoModalidadId, catDelitoModusId, catDelitoCausaId);
		return dto;
	}
	
	
	

}

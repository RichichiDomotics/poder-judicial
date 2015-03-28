package mx.gob.segob.nsjp.service.informepolicial.impl.transform;

import mx.gob.segob.nsjp.dto.catalogo.CatDelitoDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatUIEspecializadaDTO;
import mx.gob.segob.nsjp.model.CatDelito;
import mx.gob.segob.nsjp.model.CatUIEspecializada;
import mx.gob.segob.nsjp.service.catalogo.impl.transform.CatUIEspecializadaTransformer;

public class CatDelitoTransformer {
	
	public static CatDelito transformarCatDelito(CatDelitoDTO catDelitoDTO)
	{
		CatDelito catDelito = new CatDelito();
		
		catDelito.setCatDelitoId(catDelitoDTO.getCatDelitoId());
		if(catDelitoDTO.getClaveDelito()!=null)
			catDelito.setClaveDelito(catDelitoDTO.getClaveDelito());
		if(catDelitoDTO.getEsGrave()!=null)
			catDelito.setEsGrave(catDelitoDTO.getEsGrave());
		if(catDelitoDTO.getNombre()!=null)
			catDelito.setNombre(catDelitoDTO.getNombre());
		if(catDelitoDTO.getEsAccionPenPriv()!=null)
			catDelito.setEsAccionPenPriv(catDelitoDTO.getEsAccionPenPriv());
		return catDelito;
	}
	
	public static CatDelito transformarCatDelitoDtpCompleto(CatDelitoDTO catDelitoDTO)
	{
		CatDelito catDelito = new CatDelito();
		
		catDelito.setCatDelitoId(catDelitoDTO.getCatDelitoId());
		
		if(catDelitoDTO.getClaveDelito()!=null)
			catDelito.setClaveDelito(catDelitoDTO.getClaveDelito());
		
		if(catDelitoDTO.getNombre()!=null)
			catDelito.setNombre(catDelitoDTO.getNombre());
		
		if(catDelitoDTO.getEsGrave()!=null)
			catDelito.setEsGrave(catDelitoDTO.getEsGrave());
		
		if(catDelitoDTO.getEsAccionPenPriv()!=null)
			catDelito.setEsAccionPenPriv(catDelitoDTO.getEsAccionPenPriv());
		
		if(catDelitoDTO.getUnidadIEspecializada() != null){
			if(catDelitoDTO.getUnidadIEspecializada().getCatUIEId() != null){
				CatUIEspecializada catUIEesp = new CatUIEspecializada();
				catUIEesp.setCatUIEId(catDelitoDTO.getUnidadIEspecializada().getCatUIEId());
				catDelito.setUnidadIEspecializada(catUIEesp);
			}
		}
		
		if(catDelitoDTO.getPenaMinimaAnios() != null){
			catDelito.setPenaMinimaAnios(catDelitoDTO.getPenaMinimaAnios());
		}
		
		if(catDelitoDTO.getPenaMinimaMeses() != null){
			catDelito.setPenaMinimaMeses(catDelitoDTO.getPenaMinimaMeses());
		}
		
		if(catDelitoDTO.getPenaMinimaDias() != null){
			catDelito.setPenaMinimaDias(catDelitoDTO.getPenaMinimaDias());
		}
		
		if(catDelitoDTO.getPenaMaximaAnios() != null){
			catDelito.setPenaMaximaAnios(catDelitoDTO.getPenaMaximaAnios());
		}
		
		if(catDelitoDTO.getPenaMaximaMeses() != null){
			catDelito.setPenaMaximaMeses(catDelitoDTO.getPenaMaximaMeses());
		}
		
		if(catDelitoDTO.getPenaMaximaDias() != null){
			catDelito.setPenaMaximaDias(catDelitoDTO.getPenaMaximaDias());
		}
		
		return catDelito;
	}
	
	public static CatDelitoDTO transformarCatDelito(CatDelito catDelito)
	{
		CatDelitoDTO catDelitoDTO = new CatDelitoDTO();
		catDelitoDTO.setCatDelitoId(catDelito.getCatDelitoId());
		if(catDelito.getClaveDelito()!=null)
			catDelitoDTO.setClaveDelito(catDelito.getClaveDelito());
		if(catDelito.getEsGrave()!=null)
			catDelitoDTO.setEsGrave(catDelito.getEsGrave());
		if(catDelito.getNombre()!=null)
			catDelitoDTO.setNombre(catDelito.getNombre());
		if(catDelito.getEsAccionPenPriv()!=null)
			catDelitoDTO.setEsAccionPenPriv(catDelito.getEsAccionPenPriv());
		if(catDelito.getUnidadIEspecializada() != null)
			catDelitoDTO.setUnidadIEspecializada(CatUIEspecializadaTransformer.transformarCatUIEspecializada(catDelito.getUnidadIEspecializada()));
		return catDelitoDTO;
	}
	
	public static CatDelitoDTO transformarCatDelitoCompleto(CatDelito catDelito){
		
		CatDelitoDTO catDelitoDTO = new CatDelitoDTO();
		
		catDelitoDTO.setCatDelitoId(catDelito.getCatDelitoId());
		
		if(catDelito.getClaveDelito()!=null)
			catDelitoDTO.setClaveDelito(catDelito.getClaveDelito());
		
		if(catDelito.getNombre()!=null)
			catDelitoDTO.setNombre(catDelito.getNombre());
		
		if(catDelito.getEsGrave()!=null)
			catDelitoDTO.setEsGrave(catDelito.getEsGrave());
		
		if(catDelito.getEsAccionPenPriv()!=null)
			catDelitoDTO.setEsAccionPenPriv(catDelito.getEsAccionPenPriv());
		
		if(catDelito.getUnidadIEspecializada() != null){
			if(catDelito.getUnidadIEspecializada().getCatUIEId() != null){
				
				CatUIEspecializadaDTO catUIEspecializadaDto = new CatUIEspecializadaDTO();
				
				catUIEspecializadaDto.setCatUIEId(catDelito.getUnidadIEspecializada().getCatUIEId());
				catDelitoDTO.setUnidadIEspecializada(catUIEspecializadaDto);
			}
		}

		if(catDelito.getPenaMinimaAnios() != null){
			catDelitoDTO.setPenaMinimaAnios(catDelito.getPenaMinimaAnios());
		}
		if(catDelito.getPenaMinimaMeses() != null){
			catDelitoDTO.setPenaMinimaMeses(catDelito.getPenaMinimaMeses());
		}
		if(catDelito.getPenaMinimaDias() != null){
			catDelitoDTO.setPenaMinimaDias(catDelito.getPenaMinimaDias());
		}
		
		if(catDelito.getPenaMaximaAnios() != null){
			catDelitoDTO.setPenaMaximaAnios(catDelito.getPenaMaximaAnios());
		}
		
		if(catDelito.getPenaMaximaMeses() != null){
			catDelitoDTO.setPenaMaximaMeses(catDelito.getPenaMaximaMeses());
		}
		
		if(catDelito.getPenaMaximaDias() != null){
			catDelitoDTO.setPenaMaximaDias(catDelito.getPenaMaximaDias());
		}
		
		return catDelitoDTO;
	}
	
	public static CatDelito transformarCatDelitoDtoUpdate(CatDelitoDTO catDelitoDTO,CatDelito catDelito)
	{
		
		catDelito.setCatDelitoId(catDelitoDTO.getCatDelitoId());
		
		if(catDelitoDTO.getClaveDelito()!=null)
			catDelito.setClaveDelito(catDelitoDTO.getClaveDelito());
		
		if(catDelitoDTO.getNombre()!=null)
			catDelito.setNombre(catDelitoDTO.getNombre());
		
		if(catDelitoDTO.getEsGrave()!=null)
			catDelito.setEsGrave(catDelitoDTO.getEsGrave());
		
		if(catDelitoDTO.getEsAccionPenPriv()!=null)
			catDelito.setEsAccionPenPriv(catDelitoDTO.getEsAccionPenPriv());
		
		if(catDelitoDTO.getUnidadIEspecializada() != null){
			if(catDelitoDTO.getUnidadIEspecializada().getCatUIEId() != null){
				CatUIEspecializada catUnidadEsp = new CatUIEspecializada();
				catUnidadEsp.setCatUIEId(catDelitoDTO.getUnidadIEspecializada().getCatUIEId());
				catDelito.setUnidadIEspecializada(catUnidadEsp);
			}
		}
		
		if(catDelitoDTO.getPenaMinimaAnios() != null){
			catDelito.setPenaMinimaAnios(catDelitoDTO.getPenaMinimaAnios());
		}
		
		if(catDelitoDTO.getPenaMinimaMeses() != null){
			catDelito.setPenaMinimaMeses(catDelitoDTO.getPenaMinimaMeses());
		}
		
		if(catDelitoDTO.getPenaMinimaDias() != null){
			catDelito.setPenaMinimaDias(catDelitoDTO.getPenaMinimaDias());
		}
		
		if(catDelitoDTO.getPenaMaximaAnios() != null){
			catDelito.setPenaMaximaAnios(catDelitoDTO.getPenaMaximaAnios());
		}
		
		if(catDelitoDTO.getPenaMaximaMeses() != null){
			catDelito.setPenaMaximaMeses(catDelitoDTO.getPenaMaximaMeses());
		}
		
		if(catDelitoDTO.getPenaMaximaDias() != null){
			catDelito.setPenaMaximaDias(catDelitoDTO.getPenaMaximaDias());
		}
		
		return catDelito;
	}
}

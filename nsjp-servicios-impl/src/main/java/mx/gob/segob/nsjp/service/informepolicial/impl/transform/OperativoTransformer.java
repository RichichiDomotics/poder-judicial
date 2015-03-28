package mx.gob.segob.nsjp.service.informepolicial.impl.transform;

import mx.gob.segob.nsjp.dto.informepolicial.OperativoDTO;
import mx.gob.segob.nsjp.model.Operativo;

public class OperativoTransformer {

	public static Operativo transformOperativo(OperativoDTO operativo)
	{
		Operativo resp = new Operativo();
		if(operativo.getOperativoId()!=null)
			resp.setOperativoId(operativo.getOperativoId());
		resp.setNombre(operativo.getNombre());
		resp.setNombreComte(operativo.getNombreComte());
		resp.setNombreComteAgrupto(operativo.getNombreComteAgrupto());
		resp.setInformePolicialHomologado(InformePolicialHomologadoTransformer.transformIPH(operativo.getInformePolicialHomologado()));
		return resp;
	}

	public static OperativoDTO transformOperativo(Operativo operativo)
	{
		OperativoDTO resp = new OperativoDTO();
		if(operativo.getOperativoId()!=null)
			resp.setOperativoId(operativo.getOperativoId());
		resp.setNombre(operativo.getNombre());
		resp.setNombreComte(operativo.getNombreComte());
		resp.setNombreComteAgrupto(operativo.getNombreComteAgrupto());
		
		return resp;
	}
	
}

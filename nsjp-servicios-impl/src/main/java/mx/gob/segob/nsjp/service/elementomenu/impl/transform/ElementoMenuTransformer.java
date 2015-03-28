/**
 * 
 */
package mx.gob.segob.nsjp.service.elementomenu.impl.transform;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.dto.elementomenu.ElementoMenuDTO;
import mx.gob.segob.nsjp.model.ElementoMenu;
import mx.gob.segob.nsjp.service.usuario.impl.transformer.FuncionTransformer;

/**
 * @author LuisMG
 * 
 */
public class ElementoMenuTransformer {

	public static ElementoMenu transformar(ElementoMenuDTO eMDTO) {
		ElementoMenu eM = new ElementoMenu();
		if (eMDTO != null) {
			eM.setElementoMenuId(eMDTO.getElementoMenuId());
			eM.setcNombre(eMDTO.getcNombre());
			eM.setiOrden(eMDTO.getiOrden());
			eM.setcComando(eMDTO.getcComando());
			eM.setcClassHTML(eMDTO.getcClassHTML());
			eM.setcIdHTML(eMDTO.getcIdHTML());
			if (eM.getFuncion() != null) {
				eM.setFuncion(FuncionTransformer.transformarFuncionDTO(eMDTO
						.getFuncion()));
			}
			if (eMDTO.getElementoMenuHijosDTO() != null
					&& !eMDTO.getElementoMenuHijosDTO().isEmpty()) {
				List<ElementoMenu> lstEMHijos = new ArrayList<ElementoMenu>();
				for (int i = 0; i < eMDTO.getElementoMenuHijosDTO().size(); i++) {
					lstEMHijos.add(transformar(eMDTO.getElementoMenuHijosDTO()
							.get(i)));
				}
				eM.setElementoMenuHijos(lstEMHijos);
			}
		} else
			eM = null;
		return eM;
	}

	public static ElementoMenuDTO transformar(ElementoMenu eM) {
		ElementoMenuDTO eMDTO = new ElementoMenuDTO();
		if (eM != null) {
			eMDTO.setElementoMenuId(eM.getElementoMenuId());
			eMDTO.setcNombre(eM.getcNombre());
			eMDTO.setiOrden(eM.getiOrden());
			eMDTO.setcComando(eM.getcComando());
			eMDTO.setcClassHTML(eM.getcClassHTML());
			eMDTO.setcIdHTML(eM.getcIdHTML());
			eMDTO.setFuncion(FuncionTransformer.transformarFuncion(eM
					.getFuncion()));

			
		} else
			eMDTO = null;
		return eMDTO;
	}

}

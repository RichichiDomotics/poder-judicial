/**
 * 
 */
package mx.gob.segob.nsjp.service.test.elementomenu.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.dto.elementomenu.ElementoMenuDTO;
import mx.gob.segob.nsjp.dto.usuario.RolDTO;
import mx.gob.segob.nsjp.service.elementomenu.ElementoMenuService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author LuisMG
 *
 */
public class ElementoMenuServiceImplTest extends BaseTestServicios<ElementoMenuService> {
	
	public void testConsultarElementosMenuXRol (){
		RolDTO rolDTO = new RolDTO(7L);
		List<ElementoMenuDTO> elementosMenuDTO = new ArrayList<ElementoMenuDTO>();
		try{
		elementosMenuDTO= service.consultarElementosMenuXRol(rolDTO);
		//assertNotNull(elementoMenuDTO);
		for (int i=0; i<elementosMenuDTO.size();i++){
			imprimeArbol(elementosMenuDTO.get(i),0);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void testConsultarElementoMenu (){
		ElementoMenuDTO eMDTO = new ElementoMenuDTO (30L);
		try{
			eMDTO = service.consultarElementoMenu(eMDTO);
			imprimeArbol(eMDTO,0);
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	void imprimeArbol(ElementoMenuDTO elementoMenu, int nivel) {
		if (elementoMenu != null) {
			if (nivel==0)
				System.out.println("-----------------------------------------------------------------");
			for (int i = 0; i < nivel * 5; i++)
				System.out.print("*");
			System.out.println("Clave: " + elementoMenu.getElementoMenuId()
					+ " Nombre: " + elementoMenu.getcNombre()
					+ " Funcion a ejecutar: " + elementoMenu.getFuncion().getNombreFuncion());
			if (elementoMenu.getElementoMenuHijosDTO() != null
					&& !elementoMenu.getElementoMenuHijosDTO().isEmpty())
				for (int i = 0; i < elementoMenu.getElementoMenuHijosDTO().size(); i++) {
					imprimeArbol(elementoMenu.getElementoMenuHijosDTO().get(i),
							nivel + 1);
				}
		}
	}
	
	

}

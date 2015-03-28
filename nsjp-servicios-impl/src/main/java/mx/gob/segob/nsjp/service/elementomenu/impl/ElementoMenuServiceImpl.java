/**
 * 
 */
package mx.gob.segob.nsjp.service.elementomenu.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.elementomenu.ElementoMenuDAO;
import mx.gob.segob.nsjp.dto.elementomenu.ElementoMenuDTO;
import mx.gob.segob.nsjp.dto.usuario.RolDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.ElementoMenu;
import mx.gob.segob.nsjp.service.elementomenu.ElementoMenuService;
import mx.gob.segob.nsjp.service.elementomenu.impl.transform.ElementoMenuTransformer;
import mx.gob.segob.nsjp.service.expediente.impl.transform.UsuarioTransformer;
import mx.gob.segob.nsjp.service.usuario.impl.transformer.RolTransformer;

/**
 * @author LuisMG
 * 
 */
@Service
@Transactional
public class ElementoMenuServiceImpl implements ElementoMenuService {
	@Autowired
	private ElementoMenuDAO elementoMenuDAO;

	private static final Logger logger = Logger
			.getLogger(ElementoMenuServiceImpl.class);

	@Override
	public List<ElementoMenuDTO> consultarElementosMenuXRol(RolDTO rolDTO)
			throws NSJPNegocioException {
		List<ElementoMenuDTO> resp = null;
		List<ElementoMenu> elementosMenu = elementoMenuDAO
				.consultarElementosMenuXRol(RolTransformer
						.transformarMinimo(rolDTO));
		if (elementosMenu != null && !elementosMenu.isEmpty()) {
			resp = new ArrayList<ElementoMenuDTO>();
			for (int i = 0; i < elementosMenu.size(); i++) {
				if (elementosMenu.get(i).getElementoMenuPadre() == null) {
					resp.add(construyeArbolDTO(elementosMenu,
							elementosMenu.get(i), 1));
				}
			}
		}
		return resp;
	}

	@Override
	public ElementoMenuDTO consultarElementoMenu(ElementoMenuDTO eMDTO)
			throws NSJPNegocioException {
		ElementoMenuDTO resp = null;
		List<ElementoMenu> eM = null;
		if (eMDTO != null) {
			eM = new ArrayList<ElementoMenu>();
			eM.add(elementoMenuDAO
					.consultarElementoMenu(ElementoMenuTransformer
							.transformar(eMDTO)));
			if (eM.get(0).getElementoMenuHijos() != null) {
				eM.addAll(eM.get(0).getElementoMenuHijos());
			}
			resp = construyeArbolDTO(eM, eM.get(0), 1);
		}
		return resp;
	}

	@SuppressWarnings("unchecked")
	/** 
	 * Dado un Elemento Menu y un nivel de profundiad se regresa el árbol pertinente
	 * Para la profundicad los valores indican lo siguiente
	 * 0 - Padres
	 * 1 - Padres e hijos
	 * 2 - Padres, hijos y nietos
	 * y así sucesivamente
	 */
	ElementoMenuDTO construyeArbolDTO(List<ElementoMenu> facultados,
			ElementoMenu eM, int profundidad) {
		ElementoMenuDTO eMDTO = null;
		ElementoMenuDTO eMTemp = null;
		int j = 0;
		if (eM != null) {
			while (j < facultados.size()
					&& facultados.get(j).getElementoMenuId() != eM
							.getElementoMenuId()) {
				j++;
			}
			if (j < facultados.size()) {
				eMDTO = ElementoMenuTransformer.transformar(eM);
				if (profundidad > 0) {
					if (eM.getElementoMenuHijos() != null) {
						eMDTO.setElementoMenuHijosDTO(new ArrayList<ElementoMenuDTO>());
						for (int i = 0; i < eM.getElementoMenuHijos().size(); i++) {
							eMTemp = construyeArbolDTO(facultados, eM
									.getElementoMenuHijos().get(i),
									profundidad - 1);
							if (eMTemp != null) {
								eMDTO.getElementoMenuHijosDTO().add(eMTemp);
							}
						}
					}

				} else {
					eMDTO.setElementoMenuHijosDTO(null);
				}
			} else {
				eMDTO = null;
			}
		}
		return eMDTO;
	}

}

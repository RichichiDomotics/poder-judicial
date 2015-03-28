/**
 * 
 */
package mx.gob.segob.nsjp.service.usuario.impl.transformer;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.dto.usuario.FuncionDTO;
import mx.gob.segob.nsjp.dto.usuario.ModuloDTO;
import mx.gob.segob.nsjp.model.Funcion;
import mx.gob.segob.nsjp.model.Modulo;

/**
 * @author LuisMG
 * 
 */
public class ModuloTransformer {
	/**
	 * Método que realiza la transformación de TODO el entity de Modulo a
	 * ModuloDTO. Es decir, también transforma las funciones. Esto significa,
	 * que hará la consulta a la BD hasta el último de los registros
	 * relacionados al módulo. LOMG
	 * 
	 * @param mod
	 * @return
	 */
	public static ModuloDTO transformar(Modulo mod) {
		ModuloDTO modDTO = null;
		if (mod != null) {
			modDTO = new ModuloDTO();
			modDTO.setModuloId(mod.getModuloId());
			modDTO.setNombreModulo(mod.getNombreModulo());
			modDTO.setDescripcionModulo(mod.getDescripcionModulo());
			if (mod.getFunciones() != null) {
				modDTO.setFunciones(new ArrayList<FuncionDTO>());
				for (int i = 0; i < mod.getFunciones().size(); i++) {
					modDTO.getFunciones().add(
							FuncionTransformer.transformarFuncion(mod
									.getFunciones().get(i)));
				}
			}
		}
		return modDTO;
	}

	/**
	 * Método que realiza la transformación de TODO el entity de ModuloDTO a
	 * Modulo. Es decir, también transforma las funciones. Esto significa, que
	 * hará la consulta a la BD hasta el último de los registros relacionados al
	 * módulo. LOMG
	 * 
	 * @param modDTO
	 * @return
	 */
	public static Modulo transformar(ModuloDTO modDTO) {
		Modulo mod = null;
		if (modDTO != null) {
			mod = new Modulo();
			mod.setModuloId(modDTO.getModuloId());
			mod.setNombreModulo(modDTO.getNombreModulo());
			mod.setDescripcionModulo(modDTO.getDescripcionModulo());
			if (modDTO.getFunciones() != null) {
				mod.setFunciones(new ArrayList<Funcion>());
				for (int i = 0; i < modDTO.getFunciones().size(); i++) {
					mod.getFunciones().add(
							FuncionTransformer.transformarFuncionDTO(modDTO
									.getFunciones().get(i)));
				}
			}
		}
		return mod;
	}

	/**
	 * Método que realiza la transformación ÚNICAMENTE del entity de Modulo a
	 * ModuloDTO. LOMG
	 * 
	 * @param mod
	 * @return
	 */
	public static ModuloDTO transformarMinimo(Modulo mod) {
		ModuloDTO modDTO = null;
		if (mod != null) {
			modDTO = new ModuloDTO();
			modDTO.setModuloId(mod.getModuloId());
			modDTO.setNombreModulo(mod.getNombreModulo());
			modDTO.setDescripcionModulo(mod.getDescripcionModulo());
		}
		return modDTO;
	}

	/**
	 * Método que realiza la transformación ÚNICAMENTE del entity de ModuloDTO a
	 * Modulo. LOMG
	 * 
	 * @param modDTO
	 * @return
	 */
	public static Modulo transformarMinimo(ModuloDTO modDTO) {
		Modulo mod = null;
		if (modDTO != null) {
			mod = new Modulo();
			mod.setModuloId(modDTO.getModuloId());
			mod.setNombreModulo(modDTO.getNombreModulo());
			mod.setDescripcionModulo(modDTO.getDescripcionModulo());
		}
		return mod;
	}

}

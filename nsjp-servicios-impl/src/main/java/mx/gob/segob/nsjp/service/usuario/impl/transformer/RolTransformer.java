package mx.gob.segob.nsjp.service.usuario.impl.transformer;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.dto.ConfActividadDocumentoDTO;
import mx.gob.segob.nsjp.dto.usuario.ModuloDTO;
import mx.gob.segob.nsjp.dto.usuario.RolDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioRolDTO;
import mx.gob.segob.nsjp.model.ConfActividadDocumento;
import mx.gob.segob.nsjp.model.Modulo;
import mx.gob.segob.nsjp.model.Rol;
import mx.gob.segob.nsjp.model.UsuarioRol;
import mx.gob.segob.nsjp.service.actividad.impl.ConfActividadDocumentoTransformer;
import mx.gob.segob.nsjp.service.expediente.impl.transform.JerarquiaOrganizacionalTransformer;
import mx.gob.segob.nsjp.service.expediente.impl.transform.UsuarioTransformer;
import mx.gob.segob.nsjp.service.solicitud.impl.transform.ConfInstitucionTransformer;

public class RolTransformer {

	/**
	 * Método que realiza la transformación de TODO el entity de Rol a DTO. Es
	 * decir, también transforma los módulos y funciones. Esto significa, que
	 * hará la consulta a la BD hasta el último de los registros relacionados al
	 * rol.
	 * LOMG
	 * @param rol
	 * @return
	 */
	public static RolDTO transformar(Rol rol) {
		RolDTO rolDTO = null;
		if (rol != null) {
			rolDTO = new RolDTO();
			rolDTO.setDescripcionRol(rol.getDescripcionRol());
			rolDTO.setNombreRol(rol.getNombreRol());
			rolDTO.setRolId(rol.getRolId());
			rolDTO.setInstitucionPertenece(ConfInstitucionTransformer
					.transformarInstitucion(rol.getInstitucionPertenece()));
			rolDTO.setRolPadre(transformar(rol.getRolPadre()));
			if (rol.getModulos() != null) {
				rolDTO.setModulos(new ArrayList<ModuloDTO>());
				for (int i = 0; i < rol.getModulos().size(); i++) {
					rolDTO.getModulos().add(
							ModuloTransformer.transformar(rol.getModulos().get(
									i)));
				}
			}
			
			rolDTO.setJerarquiaOrganizacionalDTO(JerarquiaOrganizacionalTransformer
					.transformarJerarquiaOrganizacional(rol
							.getJerarquiaOrganizacional()));
		}
		return rolDTO;
	}

	/**
	 * Método que realiza la transformación de TODO el entity de RolDTO a Rol. Es
	 * decir, también transforma los módulos y funciones. Esto significa, que
	 * hará la consulta a la BD hasta el último de los registros relacionados al
	 * rol.
	 * LOMG
	 * @param rolDTO
	 * @return
	 */
	public static Rol transformar(RolDTO rolDTO) {
		Rol rol = null;
		if (rolDTO != null) {
			rol = new Rol();
			rol.setDescripcionRol(rolDTO.getDescripcionRol());
			rol.setNombreRol(rolDTO.getNombreRol());
			rol.setRolId(rolDTO.getRolId());
			rol.setInstitucionPertenece(ConfInstitucionTransformer
					.transformarInstitucion(rolDTO.getInstitucionPertenece()));
			rol.setRolPadre(transformar(rolDTO.getRolPadre()));
			if (rolDTO.getModulos() != null) {
				rol.setModulos(new ArrayList<Modulo>());
				for (int i = 0; i < rolDTO.getModulos().size(); i++) {
					rol.getModulos().add(
							ModuloTransformer.transformar(rolDTO.getModulos()
									.get(i)));
				}
			}
			
			rol.setJerarquiaOrganizacional(JerarquiaOrganizacionalTransformer
					.transformarJerarquiaOrganizacional(rolDTO
							.getJerarquiaOrganizacionalDTO()));
		}
		return rol;
	}



	/**
	 * Método que realiza la transformación ÚNICAMENTE del entity de Rol a RolDTO.
	 * LOMG
	 * @param rol
	 * @return
	 */
	public static RolDTO transformarMinimo(Rol rol) {
		RolDTO rolDTO = null;
		if (rol != null) {
			rolDTO = new RolDTO();
			rolDTO.setDescripcionRol(rol.getDescripcionRol());
			rolDTO.setNombreRol(rol.getNombreRol());
			rolDTO.setRolId(rol.getRolId());
			rolDTO.setInstitucionPertenece(ConfInstitucionTransformer
					.transformarInstitucion(rol.getInstitucionPertenece()));
			rolDTO.setRolPadre(transformar(rol.getRolPadre()));
			rolDTO.setJerarquiaOrganizacionalDTO(JerarquiaOrganizacionalTransformer
					.transformarJerarquiaOrganizacional(rol
							.getJerarquiaOrganizacional()));
		}
		return rolDTO;
	}
/**
 *  Método que realiza la transformación ÚNICAMENTE del entity de RolDTO a Rol.
 *  LOMG
 * @param rolDTO
 * @return
 */
	public static Rol transformarMinimo(RolDTO rolDTO) {
		Rol rol = null;
		if (rolDTO != null) {
			rol = new Rol();
			rol.setDescripcionRol(rolDTO.getDescripcionRol());
			rol.setNombreRol(rolDTO.getNombreRol());
			rol.setRolId(rolDTO.getRolId());
			rol.setRolPadre(transformar(rolDTO.getRolPadre()));			
			rol.setJerarquiaOrganizacional(JerarquiaOrganizacionalTransformer
					.transformarJerarquiaOrganizacional(rolDTO
							.getJerarquiaOrganizacionalDTO()));
		}
		return rol;
	}
	
	/**
	 * Método que realiza la transformación del entity de Rol a RolDTO
	 * hasta el nivel de ModulosDTO.
	 * LOMG
	 * @param rol
	 * @return
	 */
	public static RolDTO transformarMedio(Rol rol) {
		RolDTO rolDTO = null;
		if (rol != null) {
			rolDTO = new RolDTO();
			rolDTO.setDescripcionRol(rol.getDescripcionRol());
			rolDTO.setNombreRol(rol.getNombreRol());
			rolDTO.setRolId(rol.getRolId());
			rolDTO.setInstitucionPertenece(ConfInstitucionTransformer
					.transformarInstitucion(rol.getInstitucionPertenece()));
			rolDTO.setRolPadre(transformar(rol.getRolPadre()));
			if (rol.getModulos() != null) {
				rolDTO.setModulos(new ArrayList<ModuloDTO>());
				for (int i = 0; i < rol.getModulos().size(); i++) {
					rolDTO.getModulos().add(
							ModuloTransformer.transformarMinimo(rol.getModulos().get(
									i)));
				}
			}
			rolDTO.setJerarquiaOrganizacionalDTO(JerarquiaOrganizacionalTransformer
					.transformarJerarquiaOrganizacional(rol
							.getJerarquiaOrganizacional()));
			if(rol.getConfActividadDocumentos()!=null && rol.getConfActividadDocumentos().size()>0){
				List<ConfActividadDocumentoDTO> list=new ArrayList<ConfActividadDocumentoDTO>();
				for (ConfActividadDocumento confActividadDocumento : rol.getConfActividadDocumentos()){
					list.add(ConfActividadDocumentoTransformer.transformarConfActividadDocumento(confActividadDocumento));
				}
				rolDTO.setConfActividadDocumentoDTO(list);
			}
		}
		return rolDTO;
	}

	/**
	 * Método que realiza la transformación del entity de RolDTO a Rol
	 * hasta el nivel de módulo
	 * LOMG
	 * @param rolDTO
	 * @return
	 */
	public static Rol transformarMedio(RolDTO rolDTO) {
		Rol rol = null;
		if (rolDTO != null) {
			rol = new Rol();
			rol.setDescripcionRol(rolDTO.getDescripcionRol());
			rol.setNombreRol(rolDTO.getNombreRol());
			rol.setRolId(rolDTO.getRolId());
			rol.setInstitucionPertenece(ConfInstitucionTransformer
					.transformarInstitucion(rolDTO.getInstitucionPertenece()));
			rol.setRolPadre(transformar(rolDTO.getRolPadre()));
			if (rolDTO.getModulos() != null) {
				rol.setModulos(new ArrayList<Modulo>());
				for (int i = 0; i < rolDTO.getModulos().size(); i++) {
					rol.getModulos().add(
							ModuloTransformer.transformarMinimo(rolDTO.getModulos()
									.get(i)));
				}
			}
			rol.setJerarquiaOrganizacional(JerarquiaOrganizacionalTransformer
					.transformarJerarquiaOrganizacional(rolDTO
							.getJerarquiaOrganizacionalDTO()));
		}
		return rol;
	}

	/**
	 * Transforma un objeto del tipo <code>UsuarioRol</code> a su
	 * correspondiente DTO <code>UsuarioRolDTO</code>
	 * 
	 * @param src
	 * @return
	 */
	public static UsuarioRolDTO transformar(UsuarioRol src) {
		UsuarioRolDTO ur = null;
		if (src != null) {
			ur = new UsuarioRolDTO();
			ur.setFechaFin(src.getFechaFin());
			ur.setFechaInicio(src.getFechaInicio());
			ur.setRol(transformar(src.getRol()));
			ur.setUsuario(UsuarioTransformer.transformarUsuario(src
					.getUsuario()));
		}
		return ur;
	}

}

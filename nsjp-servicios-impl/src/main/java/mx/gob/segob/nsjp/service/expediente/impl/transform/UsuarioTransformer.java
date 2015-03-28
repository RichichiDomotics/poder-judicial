/**
 * Nombre del Programa : UsuarioTransformer.java
 * Autor                            : Hugo Serrano
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 24 May 2011
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
package mx.gob.segob.nsjp.service.expediente.impl.transform;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import mx.gob.segob.nsjp.comun.enums.institucion.TipoJerarquia;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioRolDTO;
import mx.gob.segob.nsjp.model.JerarquiaOrganizacional;
import mx.gob.segob.nsjp.model.Usuario;
import mx.gob.segob.nsjp.model.UsuarioRol;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;
import mx.gob.segob.nsjp.service.usuario.impl.transformer.RolTransformer;

/**
 * Clase que transforma los objetos de tipo Usuario
 * 
 * @version 1.0
 * @author Tattva-IT
 * 
 */
public class UsuarioTransformer {
	private static final Logger log = Logger
			.getLogger(UsuarioTransformer.class);

	public static Usuario transformarDTO(UsuarioDTO usr) {
		Usuario resp = null;
		if (usr != null) {
			resp = new Usuario();
			resp.setClaveUsuario(usr.getClaveUsuario());
			if (usr.getFuncionario() != null) {
				resp.setFuncionario(FuncionarioTransformer
						.transformarFuncionario(usr.getFuncionario()));
			}
			resp.setPasword(usr.getPassword());
			resp.setUsuarioId(usr.getIdUsuario());
			resp.setIdSesionServer(usr.getIdSesion());
			if (usr.getcIP() != null)
				resp.setcIp(usr.getcIP());
			else
				resp.setcIp("0.0.0.0");
		}

		return resp;
	}

	/**
	 * Transforma un Usuario en un UsuarioDTO.
	 * 
	 * @param usr
	 * @return
	 */
	public static UsuarioDTO transformarUsuario(Usuario usr) {
		UsuarioDTO usrDto = null;
		if (usr != null) {
			usrDto = new UsuarioDTO();
			usrDto.setClaveUsuario(usr.getClaveUsuario());
			usrDto.setIdUsuario(usr.getUsuarioId());
			usrDto.setPassword(usr.getPasword());
			usrDto.setiSesion(usr.getiSesion());
			usrDto.setiIntentos(usr.getiIntentos());
			usrDto.setcIP(usr.getcIp());
			AreaDTO areaDto = JerarquiaOrganizacionalTransformer
					.transformarJerarquiaOrganizacionalArea(usr
							.getFuncionario().getArea());
			usrDto.setAreaActual(areaDto);
			usrDto.setFuncionario(FuncionarioTransformer
					.transformarFuncionarioIntermedio(usr.getFuncionario()));
			Set<UsuarioRolDTO> roles = new HashSet<UsuarioRolDTO>();
			for (UsuarioRol usuarioRol : usr.getUsuarioRoles()) {
				roles.add(tranformar(usuarioRol));
			}
			usrDto.setUsuarioRoles(roles);
			usrDto.setIdSesion(usr.getIdSesionServer());
			if (usr.getcIp() != null)
				usrDto.setcIP(usr.getcIp());
			else
				usrDto.setcIP("0.0.0.0");
		}
		return usrDto;
	}

	/**
	 * Transforma un Usuario en un UsuarioDTO.
	 * 
	 * @param usr
	 * @return
	 */
	public static UsuarioDTO transformarUsuarioMinimo(Usuario usr) {
		UsuarioDTO usrDto = new UsuarioDTO();
		try {

			usrDto.setClaveUsuario(usr.getClaveUsuario());
			usrDto.setIdUsuario(usr.getUsuarioId());
			Set<UsuarioRolDTO> roles = new HashSet<UsuarioRolDTO>();
			for (UsuarioRol usuarioRol : usr.getUsuarioRoles()) {
				roles.add(tranformar(usuarioRol));
			}
			usrDto.setUsuarioRoles(roles);

		} catch (Exception e) {
			log.error(e);
		}
		return usrDto;
	}

	public static UsuarioRolDTO tranformar(UsuarioRol usuarioRol) {
		UsuarioRolDTO result = new UsuarioRolDTO();
		result.setFechaFin(usuarioRol.getFechaFin());
		result.setFechaInicio(usuarioRol.getFechaInicio());
		result.setRol(RolTransformer.transformarMinimo(usuarioRol.getRol()));
		result.setEsPrincipal(usuarioRol.getEsPrincipal());
		return result;
	}

	/**
	 * Busca la jerarquia organizacional que sea del tipo 3.
	 * 
	 * @param j
	 * @return
	 */
	private static JerarquiaOrganizacional buscaArea(JerarquiaOrganizacional j) {
		if (j == null) {
			return new JerarquiaOrganizacional();
		}
		if (j.getTipoJerarquia().getValorId()
				.equals(TipoJerarquia.AREA.getValorId())) {
			return j;
		}
		return buscaArea(j.getJerarquiaOrgResponsable());
	}
}

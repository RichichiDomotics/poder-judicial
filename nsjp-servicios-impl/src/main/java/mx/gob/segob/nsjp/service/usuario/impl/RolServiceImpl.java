/**
 * Nombre del Programa  : RolServiceImpl.java
 * Autor                : Daniel Jiménez
 * Compania             : TATTVA-IT
 * Proyecto             : NSJP                    Fecha: 27 Jul 2011
 * Marca de cambio      : N/A
 * Descripcion General  : Servicio que administra la información de Roles
 * Programa Dependiente : N/A
 * Programa Subsecuente : N/A
 * Cond. de ejecucion   : N/A
 * Dias de ejecucion    : N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                :N/A
 * Compania             :N/A
 * Proyecto             :N/A                                 Fecha: N/A
 * Modificacion         :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.usuario.impl;

import java.util.LinkedList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.usuario.RolDAO;
import mx.gob.segob.nsjp.dto.rol.FiltroRolesDTO;
import mx.gob.segob.nsjp.dto.usuario.FuncionDTO;
import mx.gob.segob.nsjp.dto.usuario.RolDTO;
import mx.gob.segob.nsjp.model.ConfInstitucion;
import mx.gob.segob.nsjp.model.Rol;
import mx.gob.segob.nsjp.service.usuario.RolService;
import mx.gob.segob.nsjp.service.usuario.impl.transformer.RolTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio que administra la información de Roles
 * 
 * @version 1.0
 * @author Daniel Jiménez
 * 
 */
@Service
@Transactional
public class RolServiceImpl implements RolService {

	public static final Logger logger = Logger.getLogger(RolServiceImpl.class);

	@Autowired
	private RolDAO rolDAO;

	@Override
	public List<RolDTO> consultarRoles(FiltroRolesDTO filtroRolesDTO)
			throws NSJPNegocioException {

		ConfInstitucion institucionActual = rolDAO.consultarInsitucionActual();

		filtroRolesDTO.setConfInstitucionId(institucionActual
				.getConfInstitucionId());
		List<Rol> result = rolDAO.consultarRoles(filtroRolesDTO);
		List<RolDTO> roles = new LinkedList<RolDTO>();
		for (Rol rol : result) {
			roles.add(RolTransformer.transformarMinimo(rol));
		}
		return roles;
	}

	@Override
	public void actualizarRol(RolDTO rolDTO) throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("SERVICIO PARA ACTUALIZAR LA INFORMACION DE UN ROL");

		if (rolDTO.getRolId() == null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);

		for (FuncionDTO funcionDTO : rolDTO.getFunciones()) {

		}

	}

	/**
	 * Método que consulta la información de un rol, módulos y funciones
	 * asociados - LOMG
	 * @param rol
	 * @return
	 * @throws NSJPNegocioException
	 */
	public RolDTO consultarRolCompleto(RolDTO rolDTO)
			throws NSJPNegocioException {
		RolDTO rolResp = null;
		if (rolDTO != null) {
			rolResp = new RolDTO();
			rolResp = RolTransformer.transformar(rolDAO
					.consultarRol(RolTransformer.transformar(rolDTO)));
		}
		return rolResp;
	}

	@Override
	public RolDTO consultarRol(RolDTO rolDTO) throws NSJPNegocioException {
		RolDTO rolResp = null;
		if (rolDTO != null) {
			rolResp = new RolDTO();
			rolResp = RolTransformer.transformarMedio(rolDAO
					.consultarRol(RolTransformer.transformarMedio(rolDTO)));

		}
		return rolResp;
	}

	/**
	 * Método que consulta, ÚNICAMENTE, la información de un rol - LOMG
	 * @param rol
	 * @return
	 * @throws NSJPNegocioException
	 */
	public RolDTO consultarRolMinimo(RolDTO rolDTO) throws NSJPNegocioException {
		RolDTO rolResp = null;
		if (rolDTO != null) {
			rolResp = new RolDTO();
			rolResp = RolTransformer.transformarMinimo(rolDAO
					.consultarRol(RolTransformer.transformarMinimo(rolDTO)));
		}
		return rolResp;
	}

	@Override
	public boolean actualizaModulosRol(RolDTO rolDTO)
			throws NSJPNegocioException {
		boolean resp = false;
		if (rolDTO != null) {
			// rolDAO.merge(RolTransformer.transformar(rolDTO));
			rolDAO.saveOrUpdate(RolTransformer.transformarMedio(rolDTO));
			resp = true;
		}

		return resp;
	}
	
	public boolean crearRol(RolDTO rolDTO)
			throws NSJPNegocioException {
		boolean resp = false;
		if (rolDTO != null) {
			rolDAO.create(RolTransformer.transformarMedio(rolDTO));
			resp = true;
		}
		return resp;
	}

}

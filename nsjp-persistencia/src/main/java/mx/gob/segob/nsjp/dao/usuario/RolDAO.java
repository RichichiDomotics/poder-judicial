/**
 * Nombre del Programa  : RolDAO.java
 * Autor                : Daniel Jiménez
 * Compania             : TATTVA-IT
 * Proyecto             : NSJP                    Fecha: 27 Jul 2011
 * Marca de cambio      : N/A
 * Descripcion General  : Acceso a la información de Roles
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
package mx.gob.segob.nsjp.dao.usuario;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.dto.rol.FiltroRolesDTO;
import mx.gob.segob.nsjp.model.Rol;

/**
 * Acceso a la información de Roles
 * 
 * @version 1.0
 * @author Daniel Jiménez
 * 
 */
public interface RolDAO extends GenericDao<Rol, Long> {

	/**
	 * Consulta los roles asociados a la institución actual y que se encuentran activos. 
	 * 
	 * @param institucionActualId
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<Rol> consultarRoles(FiltroRolesDTO filtroRolesDTO)throws NSJPNegocioException;
	public Rol consultarRol(Rol rol)throws NSJPNegocioException;
}

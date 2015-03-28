/**
 * Nombre del Programa  : RolService.java
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
package mx.gob.segob.nsjp.service.usuario;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.rol.FiltroRolesDTO;
import mx.gob.segob.nsjp.dto.usuario.RolDTO;

/**
 * Servicio que administra la información de Roles
 * 
 * @version 1.0
 * @author Daniel Jiménez
 * 
 */
public interface RolService {

    /**
     * Consulta los roles del sistema.
     * @return Lista de roles del sistema
     * @throws NSJPNegocioException
     */
	public List<RolDTO> consultarRoles(FiltroRolesDTO filtroRolesDTO) throws NSJPNegocioException;

	/**
	 * Realiza la actualizacion de un rol
	 * @author cesarAgustin
	 * @param rolDTO
	 * @throws NSJPNegocioException
	 */
	public void actualizarRol(RolDTO rolDTO) throws NSJPNegocioException;
	/**
	 * Método que consulta la información de un rol, módulos y funciones asociados
	 * @param rol
	 * @return
	 * @throws NSJPNegocioException
	 */
	public RolDTO consultarRolCompleto (RolDTO rol) throws NSJPNegocioException;
	
	/**
	 * Método que consulta la información de un rol y sus módulos asociados
	 * @param rol
	 * @return
	 * @throws NSJPNegocioException
	 */
	public RolDTO consultarRol(RolDTO rol) throws NSJPNegocioException;
	
	/**
	 * Método que consulta, ÚNICAMENTE, la información de un rol
	 * @param rol
	 * @return
	 * @throws NSJPNegocioException
	 */
	public RolDTO consultarRolMinimo (RolDTO rol) throws NSJPNegocioException;
	
	public boolean actualizaModulosRol (RolDTO rolDTO) throws NSJPNegocioException;
	public boolean crearRol (RolDTO rolDTO) throws NSJPNegocioException;
	
	
	
	
}

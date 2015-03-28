/**
* Nombre del Programa : FuncionDAO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 1 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato de metodos de acceso a datos para la entidad Funcion
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
package mx.gob.segob.nsjp.dao.usuario;

import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.ElementoMenu;
import mx.gob.segob.nsjp.model.Funcion;
import mx.gob.segob.nsjp.model.Usuario;

/**
 * Contrato de metodos de acceso a datos para la entidad Funcion.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface FuncionDAO extends GenericDao<Funcion, Long> {

	/**
	 * 
	 * @return
	 */
	List<Funcion> consultarFunciones();

	/**
	 * 
	 * @param rolId
	 * @return
	 */
	List<Funcion> consultarFuncionesByRol(Long rolId);
	
	List<Funcion> consultarFuncionesXUsuario (Usuario usuario);
	
	/**
	 * Método que dada una función a consultar verifica que el usuario tenga acceso a ella
	 * @param usuario
	 * @param funcion
	 * @return
	 */
	List<Funcion> validarFuncionXUsuario (Usuario usuario, Funcion funcion);
	/**
	 * Método encargado de regresar toda la información de una función dado el nombre
	 * @param funcion
	 * @return
	 */
	Funcion consultarFuncionXNombre (Funcion funcion);
	
	/**
	 * Dada una lista de Funciones el método se encargará de reflejarlo en la base de datos
	 * @param lstFunciones
	 * @return
	 */
	boolean instertarFunciones (List<Funcion> lstFunciones);
	
	
}

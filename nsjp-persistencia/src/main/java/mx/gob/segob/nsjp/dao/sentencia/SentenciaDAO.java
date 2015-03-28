/**
* Nombre del Programa : SentenciaDAO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 9 Sep 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato de metodos de acceso a datos de la entidad Sentencia
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
package mx.gob.segob.nsjp.dao.sentencia;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.NombreDemografico;
import mx.gob.segob.nsjp.model.Sentencia;

/**
 * Contrato de metodos de acceso a datos de la entidad Sentencia.
 * @author cesarAgustin
 *
 */
public interface SentenciaDAO extends GenericDao<Sentencia, Long> {

	/**
	 * M&eacute;todo que consulta todos las sentencias
	 * @return Lista de Sentencias
	 * @throws NSJPNegocioException
	 */

	public List<Sentencia> consultarSentencias() throws NSJPNegocioException;	
	
	/**
	 * M&eacute;todo que consulta una Sentencia por id
	 * @return Sentencia
	 * @throws NSJPNegocioException
	 */
	public Sentencia consultarSentenciaPorId(Sentencia sentencia)throws NSJPNegocioException;
	
	/**
	 * M&eacute;todo encargado de llevar a cabo la consulta de las sentencias que se encuentran en un estado 
	 * espec&iacute;fico. (Se obtiene a trav&eacute;s del expediente asociado a la sentencia).
	 * @param sentencia - Entidad que obtiene el estado sobre el cual se va a filtrar la consulta.
	 * @return List<Sentencia> - Lista de sentencias que cumplen con el criterio del estado 
	 * @throws NSJPNegocioException - En el caso de que no se proporcionen los argumentos suficientes.
	 */
	public List<Sentencia> consultarSentenciasXEstado(Sentencia sentencia) throws NSJPNegocioException;


	/**
	 * M&eacute;todo que consulta un NUS en base al CURP o al Nombre Completo del Involucrado 
	 * @param nombreDemografico
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<Sentencia> consultarNUS(NombreDemografico nombreDemografico, Boolean esPorCURP)throws NSJPNegocioException;

	
}

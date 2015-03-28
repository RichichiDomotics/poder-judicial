/**
* Nombre del Programa : BuscarInvolucradoService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 18 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para consultar un individuo de acuerdo a ciertos parametros de busqueda
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
package mx.gob.segob.nsjp.service.involucrado;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.DefensaInvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;


/**
 * Contrato del servicio para consultar un individuo de acuerdo a ciertos parametros de busqueda.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface ConsultarIndividuoService {
//FIXME cesarAgustin depurar metdos
	/**
	 * 
	 * @param involucradoDTO
	 * @return
	 * @throws NSJPNegocioException
	 * @deprecated
	 */
	public InvolucradoDTO consultarIndividuo(InvolucradoDTO involucradoDTO) throws NSJPNegocioException;
	
	
	/**
	 * Consulta de involucrados por calidades asociados a un numeroExpediente.
	 * 
	 * @param numeroExpediente
	 * @param calidades
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<InvolucradoDTO> obtenerInvolucradosPorExpedienteYCalidades(String numeroExpediente, Calidades[] calidades) throws NSJPNegocioException;
	
	/**
	 * 
	 * @param involucradoDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public InvolucradoDTO obtenerIndividuoDTO(InvolucradoDTO involucradoDTO)
			throws NSJPNegocioException;


	/**
	 * @param involucradoDTO
	 * @return
	 * @throws NSJPNegocioException 	
	 */
	public InvolucradoDTO obtenerInvolucrado(InvolucradoDTO involucradoDTO) throws NSJPNegocioException;

	/**
	 * Obtiene los involucrados de un expediente que correspondan a una calidad especifica
	 * @param expedienteDTO
	 * @param calidad
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<InvolucradoDTO> consultarInvolucradoExpediente (ExpedienteDTO expedienteDTO, Calidades calidad, UsuarioDTO usuario) throws NSJPNegocioException;


	/**
	 * Operación que realiza la funcionalidad de consultar los probables responsables asociados al expediente y cuya situación sea detenido.
	 * 
	 * @param expedienteDTO: El identificador de expediente
	 * @param esDetenido: La bandera esDetenido en verdadero
	 * @return Regresa el listado de probables responsables detenidos y asociados al expediente, en caso contrario regresa null.
	 * @throws NSJPNegocioException
	 */
	public List<InvolucradoDTO> consultarProbablesResponsablesDetenidos(
			ExpedienteDTO expedienteDTO, Boolean esDetenido)throws NSJPNegocioException;

	/**
	 * Operación que realiza la funcionalidad de consultar los datos de los involucrados que se encuentren en cierta calidad y que se encuentren asociados a un caso
	 * 
	 * @param casoDTO: casoId
	 * @param calidadDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<InvolucradoDTO> consultarInvolucradoPorCalidadCaso(
			CasoDTO casoDTO, CalidadDTO calidadDTO)throws NSJPNegocioException;
	
	/**
	 * Busca los involucrados que sean de cierto tipo de calidad y que estén
	 * relacionados a cierto número de expediente
	 * @param numeroExpedienteId Número expediente de los involucrados a buscar
	 * @param calidades calidades buscadas en los involucrados a obtener
	 * de resultado
	 * @return Lista de involucrados encontrados
	 */
	List<InvolucradoDTO> obtenerInvolucradosByCasoYCalidades(String numeroCaso,Calidades []calidades);

	/**
	 * Obtiene una lista de objetos DefensaInvolucradoDTO que contiene los involucrados del expediente <code>expedienteDTO</code>
	 * con la <code>calidad</code>
	 * @param expedienteDTO
	 * @param calidad
	 * @param usuario
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<DefensaInvolucradoDTO> consultarInvolucradoExpedienteDefensoria(
			ExpedienteDTO expedienteDTO, Calidades calidad, UsuarioDTO usuario) throws NSJPNegocioException;
	
}
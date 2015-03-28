/**
 * Nombre del Programa : EvidenciaDelegate.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 30-jun-2011
 * Marca de cambio        : N/A
 * Descripcion General    : N/A
 * Programa Dependient    :N/A
 * Programa Subsecuente   :N/A
 * Cond. de ejecucion     :N/A
 * Dias de ejecucion      :N/A                                Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                            :N/A
 * Compania                         :N/A
 * Proyecto                         :N/A                      Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.delegate.evidencia;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudPericialDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * Contrato del delegate para los metodos de comunicacion de Evidencia(s) entre
 * la vista y los servicios.
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface EvidenciaDelegate {

    /**
     * registrar la baja de las evidencias que recibe como parametro.
     * @param evidenciasDto Las evidencias a dar de baja.
     * @throws NSJPNegocioException En caso de que evidenciasDto sea null
     * o que los id de las evidencias sean nulos.
     */
    void registrarBajaEvidencia(List<EvidenciaDTO> evidenciasDto) throws NSJPNegocioException;

    /**
     * Consulta las evidencias asociadas a una cadena de custodia.
     * 
     * @param cadenaDeCustodiaDTO
     *            Objeto con el número de folio de la cadena de custodia.
     * @return Devuelve el listado de evidencias asociadas a la cadena de
     *         custodia. En caso de no existir una cadena de custodia con el
     *         numero de folio o en caso de no existir evidencias asociadas a la
     *         cade de custodia, regresa la lista vacia.
     *         <ol>
     *         De cada evidencia se consultan los siguientes datos:
     *         <li>Identificador de la evidencia
     *         <li>Información de la evidencia
     *         <li>Hora del levantamiento de la evidencia
     *         <li>Origen de la evidencia
     *         <li>Último eslabón asociado
     *         <li>Número de eslabón
     *         <li>Tipo de eslabón
     *         <li>Almacén (Dentro de Evidencia.Objeto.Almacen)
     *         </ol>
     * @throws NSJPNegocioException
     *             En caso de recibir una {@code cadenaDeCustodiaDTO} nula o en
     *             caso de no contener un numero de folio
     */
    List<EvidenciaDTO> consultarEvidenciasXCadenaCustodia(
            CadenaDeCustodiaDTO cadenaDeCustodiaDTO)
            throws NSJPNegocioException;

    /**
     * Consulta las evidencias resguardadas por un perito, es decir, donde el
     * último eslabón es el usuario (firmado) quien recibe.
     * 
     * @param usrFirmado
     *            Requerido: <b>idUsuario</b>.
     * @return Lista de evidencias
     */
    List<EvidenciaDTO> consultarEvidenciasResguardadasPorUsario(
            UsuarioDTO usrFirmado) throws NSJPNegocioException;
    
    /**
     * Operación que realiza la funcionalidad de guardar los datos capturados de la evidencia
     * Los datos qye guarda son
     * - Descripcion o información de la evidencia
     * - Fecha de levantamiento
     * - Origen de la evidencia
     * - Cadena de Custodia a la que pertenece
     * 
     * @param evidenciaDTO
     * @return
     * @throws NSJPNegocioException
     */
    Long guardarEvidencia(EvidenciaDTO evidenciaDTO)throws NSJPNegocioException;
    /**
     * Consulta las evidencias resguardadas por un perito, es decir, donde el
     * último eslabón es el usuario (firmado) quien recibe.
     * 
     * @param usuario
     *            Requerido <b>idUsuario</b>
     * @param cadena
     *            Requerido <b>cadenaDeCustodiaId</b>
     * @return
     * @throws NSJPNegocioException
     */
    List<EvidenciaDTO> consultarEvidenciasResguardadasPorUsario(
            UsuarioDTO usrFirmado, CadenaDeCustodiaDTO cadena)
            throws NSJPNegocioException;
    
    
    /**
     * Realiza una consulta de las evidencias asignadas a una cadena de custodia
     * que están implicadas en una solicitud pericial.
     * Por cada evidencia verifica si está asignada a alguna de las solicitudes hijas,
     * si está asignada entonces en el funcionario que recibe del último eslabón
     * de la evidencia será el perito de la solicitu hija donde está asignada
     * la evidencia (solo en el DTO para fines de mostrarlo en pantalla)
     * @param cadena Cadena de custodia para la cuál consultar las evidencias
     * @param solicitud Solicitud inicial de pericial sobre la cuál se está trabajando
     * @return Lista de evidencias encontradas y ajustadas según los peritos de la solicitud
     * @throws NSJPNegocioException
     */
    List<EvidenciaDTO> consultarEvidenciaXCadenaCustodiaYPeritoDeSolicitudPericial(CadenaDeCustodiaDTO cadena,SolicitudPericialDTO solicitud) throws NSJPNegocioException;

    /**
     * Obtiene una lista de peritos asignados a una solicitud inicial que no tengan asignación como 
     * último eslabón de la cadena de custodia
     * @param cadena Cadena de custodia a tomar en cuenta para las asignaciones
     * @param solicitud Solicitud incial que tiene solicitudes hijas con peritos asignados
     * @return Lista de peritos no asignados
     * @throws NSJPNegocioException
     */
    List<FuncionarioDTO> consultarPeritosSinAsignacionEnCadenaDeCustodia(CadenaDeCustodiaDTO cadena,SolicitudPericialDTO solicitud) throws NSJPNegocioException;
    /**
	 * Asigna una evidencia a los elementos relacionados a la solicitud pericia, este 
	 * método no crea cadenas de custodia ni eslabones
	 * @param evidencia Evidencia a asignar
	 * @param solicitud Solicitud inicial para la cual se debe buscar el perito
	 * donde se va a relacionar la evidencia
	 * @param perito Perito a asignar evidencias 
	 */
	void asignarEvidenciaASolicitudPericial(EvidenciaDTO evidencia,SolicitudPericialDTO solicitud,FuncionarioDTO perito) throws NSJPNegocioException;
	

	/**
	 * Operación que realiza la funcionalidad de consultar las evidencias asociadas al folio de una cadena de custodia en función de la disponibilidad solicitada(estatus).
	 * @param custodiaDTO: idCadena
	 * @param estatus
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<EvidenciaDTO> consultarEvidenciasXCadenaCustodiaYEstatus(CadenaDeCustodiaDTO custodiaDTO, Long estatus)throws NSJPNegocioException;
	/**
	 * Operación que realiza la funcionalidad de asociar el destino legal de una Evidencia
	 * @param evidenciaDTO: idEvidencia
	 * @param destinoLegal
	 * @throws NSJPNegocioException
	 */
	void asociarDestinoLegal(EvidenciaDTO evidenciaDTO, Long destinoLegal)throws NSJPNegocioException;

	/**
	 * Operación que realiza la funcionalidad de consultar evidencia por Id de Solicitud
	 * @author Marco Gallardo
	 * @param idSolicitud: Clave ID de la solicitud
	 */
    
	List<EvidenciaDTO> consultarEvidenciasXIdSolicitud(Long idSolicitud);
	
	/**
	 * Operación que permite consultar el detalle de una evidencia
	 * @param idEvidencia
	 * @return
	 * @throws NSJPNegocioException
	 */
	EvidenciaDTO consultaEvidencia(Long idEvidencia)throws NSJPNegocioException;
	
	/**
	 * Operación que permite consultar las evidencias por almacen, opcionalmente por estatus y opcionalmente por caso
	 * @param usuarioDTO
	 * @param estatusEv: null=sin importar estatus; -1=Solicitudes de AMP; valor=por estatus  
	 * @param casoDTO: idCaso
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<EvidenciaDTO> consultarEvidenciasXAlmacen(UsuarioDTO usuarioDTO, Long estatusEv, CasoDTO casoDTO, Long idAmacen)throws NSJPNegocioException;
	
	/**
	 * Operación que permite ver los documentos asociados a los eslabones de una evidencia dada
	 * @param evidenciaDTO: idEvidencia
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<DocumentoDTO> consultarDocumentosXEslabonesDEvidencia(EvidenciaDTO evidenciaDTO)throws NSJPNegocioException;
	
	/**
	 * Operación que permite ver las evidencias que deberían estar devueltas y no han sido devueltas a la fecha
	 * @param usuarioDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<EvidenciaDTO> consultarEvidenciasNoDevueltas(UsuarioDTO usuarioDTO)throws NSJPNegocioException;
	
	/**
	 * Operación que permite cambiar el estatus de una evidencia
	 * @param evidenciaDTO: idEvidencia, estatusDeseado
	 * @return
	 * @throws NSJPNegocioException
	 */
	public Long cambiarEstatusEvidencia(EvidenciaDTO evidenciaDTO)throws NSJPNegocioException;
	
    /**
     * Elimina fisicamente la evidencia y da de baja lógica el objeto asociado a dicha evidencia.
     * 
     * @param idEvidencia a ser eliminada
     * @return Boleano que indica si la eliminación se realizó con éxito.
     * @throws NSJPNegocioException
     */
    Boolean eliminarEvidencia(Long idEvidencia) throws NSJPNegocioException;
    
}

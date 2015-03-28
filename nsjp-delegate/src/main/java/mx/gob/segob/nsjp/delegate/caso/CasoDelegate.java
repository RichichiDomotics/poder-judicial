/**
 * Nombre del Programa : CasoDelegate.java
 * Autor                            : cesarAgustin
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 28 Apr 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Contrato de metodos para conectar la vista con servicio de Caso
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
package mx.gob.segob.nsjp.delegate.caso;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.caso.FiltroCasoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.FiltroExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * Contrato de metodos para conectar la vista con servicio de Caso.
 * 
 * @version 1.0
 * @author cesarAgustin
 * 
 */
public interface CasoDelegate {

    /**
     * Consulta la etapa del caso guardada en la base de datos en base al id
     * del caso.
     * @param casoDto
     * @return
     * @throws NSJPNegocioException
     */
    ValorDTO consultarEtapaAnteriorCaso(CasoDTO casoDto) throws NSJPNegocioException;

    void actualizarEtapaCaso(CasoDTO casoDto, ValorDTO nuevaEtapaDto)
            throws NSJPNegocioException;

    /**
     * Consulta los casos que correspondan al numero de caso enviado
     * 
     * @param casoDTO
     * @return Lista de expedientes relacionados al numero de caso
     */
    public List<CasoDTO> consultarCasoPorNumero(CasoDTO casoDTO)
    			throws NSJPNegocioException;

    /**
     * Consulta los Casos de un caso para pintar el árbol de casos-expedientes en base al usuario.
     * 
     * @param usr
     * @return Una lista con los siguientes valores de casos asignados:
     *         <ul>
     *         <li>casoId</il>
     *         <li>numeroGeneralCaso</il>
     *         <li>estatus</il>
     *         </ul>
     * @throws NSJPNegocioException
     */
    List<CasoDTO> consultarCasosPorUsuario(UsuarioDTO usr)
            throws NSJPNegocioException;
    
    /**
     * 
     * @param filtrosDTO
     * @return
     * @throws NSJPNegocioException
     */
    List<InvolucradoDTO> buscarCasoPorFiltros(FiltroExpedienteDTO filtrosDTO)
    		 throws NSJPNegocioException;
    
    /**
     * Consulta los casos de acuerdo a los filtro enviados
     * @param filtrosDTO
     * @return
     * @throws NSJPNegocioException
     */
    List<CasoDTO> buscarCasoPorFiltros (FiltroCasoDTO filtrosDTO) throws NSJPNegocioException;
    
    /**
     * Consulta los números e id's de casos que tengan la menos un evento que se deba mostrar
     * en el árbol de consulta de históricos
     * @param usr Usuario que consulta
     * @return Lista de casos encontrados
     */
    List<CasoDTO> consultarCasosConEventosHistoricos(UsuarioDTO usr) throws NSJPNegocioException;
    
    /**
     * Consulta el caso que ampara al numero de expediente dado
     * @param expedienteDTO: numeroExpedienteID
     * @return
     * @throws NSJPNegocioException
     */
    CasoDTO consultarCasoPorExpediente (ExpedienteDTO expedienteDTO) throws NSJPNegocioException;

    /**
     * Método para enviar la réplica del caso a las demás intituciones.
     * @param expdienteConCaso Obligatorio <b>expedienteId</b>
     * @throws NSJPNegocioException
     */
    void enviarReplicaCaso(ExpedienteDTO expdienteConCaso) throws NSJPNegocioException;

	public List<CasoDTO> consultarCasos(String numeroGeneralCaso) throws NSJPNegocioException;

	/**
	 * Vincula cun expediente a un caso
	 * @param expediente
	 * @param caso
	 * @throws NSJPNegocioException
	 */
	void vincularExpedienteConCaso(ExpedienteDTO expediente, CasoDTO caso,
            InvolucradoDTO involucradoPG)
			throws NSJPNegocioException;
}

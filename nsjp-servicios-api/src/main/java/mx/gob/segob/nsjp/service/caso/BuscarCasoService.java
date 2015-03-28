/**
 * Nombre del Programa : ConsultarCaso.java
 * Autor                            : cesarAgustin
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 28 Apr 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Contrato de metodos del servicio de busquedas de caso
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
package mx.gob.segob.nsjp.service.caso;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.caso.FiltroCasoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.elemento.ElementoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.FiltroExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * Contrato de metodos del servicio de busquedas de caso.
 * 
 * @version 1.0
 * @author cesarAgustin
 * 
 */
public interface BuscarCasoService {

    /**
     * Retorna los expedintes del caso recuperado de acuerdo al numero de caso
     * 
     * @param casoDTO
     * @return
     * @throws NSJPNegocioException
     */
    List<CasoDTO> buscarCasoPorNumero(CasoDTO casoDTO)
            throws NSJPNegocioException;
    /**
     * Consulta los Casos de un caso para pintar el árbol de casos-expedientes
     * en base al usuario.
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
     * Retorna los casos obtenidos por la consulta de acuerdo a los filtros
     * envados
     * 
     * @param filtrosDTO
     * @return
     * @throws NSJPNegocioException
     */
    List<CasoDTO> buscarCasoPorFiltros(FiltroCasoDTO filtrosDTO)
            throws NSJPNegocioException;
    
    
    /**
     * Consulta los números e id's de casos que tengan la menos un evento que se deba mostrar
     * en el árbol de consulta de históricos
     * @param usr Usuario que consulta
     * @return Lista de casos encontrados
     */
    List<CasoDTO> consultarCasosConEventosHistoricos(UsuarioDTO usr) throws NSJPNegocioException;
    /**
     * Consulta el caso que ampara al numero de expediente dado
     * @param expedienteDTO numeroExpedienteID
     * @return
     * @throws NSJPNegocioException
     */
	CasoDTO consultarCasoPorExpediente(ExpedienteDTO expedienteDTO)throws NSJPNegocioException;

    ValorDTO consultarEtapaAnteriorCaso(CasoDTO casoDto) throws NSJPNegocioException;
    
    /**
     * Permite buscar las reincidencias de un elemnto:
     * Si es un Automovil, permitira filtrar por el numero de serie y por el numero de placas
     * Si es un Arma, permitira filtrar por el numero de serie
     * Se es una persona, permitira filtrar por el nombre y apellidos
     * @param elementoDTO
     * @return List<CasoDTO> 
     * @throws NSJPNegocioException
     */
    public List<CasoDTO> buscarReincidenciaDeElementos(ElementoDTO elementoDTO)
	throws NSJPNegocioException;
    
}

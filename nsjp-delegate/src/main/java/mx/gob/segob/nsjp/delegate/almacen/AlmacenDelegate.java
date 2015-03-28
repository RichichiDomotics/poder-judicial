/**
 * Nombre del Programa : AlmacenDelegate.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 29-jul-2011
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
package mx.gob.segob.nsjp.delegate.almacen;


import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;

/**
 * Contrato del delegate para los metodos de comunicacion de Almacen
 * entre la vista y los servicios.
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface AlmacenDelegate {

    /**
     * 
     * @param idTipoAlmacen 0-Fisico 1-Virtual
     * @param estatus
     * @param casoDto
     * @return
     * @throws NSJPNegocioException
     */
    List<AlmacenDTO> consultarAlmacenesPorTipo(Long idTipoAlmacen,
            Boolean estatus, CasoDTO casoDto) throws NSJPNegocioException;

    List<EvidenciaDTO> consultarEvidenciaPorAlmacen(AlmacenDTO almacenDto)
            throws NSJPNegocioException;

    Long registrarAlmacen(AlmacenDTO almacenDto) throws NSJPNegocioException;

    /**
     * Verifica si un folio de una cadena de custodia se encuentra asociado
     * a un almacen dado.
     * @param cadenaDeCustodiaDto Objeto que contiene el folio de la cadena
     * de custodia a validar.
     * @param almacenDto El almacen donde se estara validando si existe el
     * folio.
     * @return {@code true} en caso que el folio si este asociado al almacen,
     * {@code false} en caso contrario.
     * @throws NSJPNegocioException En caso que se cumpla alguna de las
     * siguientes condiciones:
     * <ol>
     * <li> 
     * </ol>
     */
    boolean validarCadenaCustodiaEnAlmacen(CadenaDeCustodiaDTO cadenaDeCustodiaDto,
            AlmacenDTO almacenDto) throws NSJPNegocioException;
    
    /**
     * Recupe la informacion del Almacen en el que se encuentra el expediente enviado como parametro
     * @author cesarAgustin
     * @param expedienteDTO
     * 			<li>numeroExpedienteId<li> Identificador de expediente
     * @return Almacen obtenido
     * @throws NSJPNegocioException
     */
    public AlmacenDTO obtenerAlmacenDelExpediente(ExpedienteDTO expedienteDTO) throws NSJPNegocioException;
    
    /**
     * Obtiene los almacenes para expediente que se encuentren en el estatus requerido
     * @author cesarAgustin
     * @param estatus
     * @return Lista de almacenes
     * @throws NSJPNegocioException
     */
    public List<AlmacenDTO> consultarAlmacenesExpedientePorEstatus (Boolean estatus, Boolean tipo) throws NSJPNegocioException;
    
    /**
     * Realiza el cambio del expediente de un almacen a otro
     * @author cesarAgustin
     * @param almacenActual <li>identificadorAlmacen<li> Identificador del almacen actual
     * @param almacenNuevo <li>identificadorAlmacen<li> Identificador del almacen al que se pasara el expediente
     * @param expedienteDTO <li>numeroExpedienteId<li> Identificador del expediente que se movera
     * @throws NSJPNegocioException
     */
    public void cambiaExpedienteDeAlmacen (AlmacenDTO almacenActual, 
    				AlmacenDTO almacenNuevo, ExpedienteDTO expedienteDTO) throws NSJPNegocioException;
    
    /**
     * Realiza la asociacion de el expediente a un almacen.
     * @author cesarAgustin
     * @param almacenDTO <li>identificadorAlmacen<li> Identificador del almacen al que se asociara el expediente
     * @param expedienteDTO Identificador del expediente ha asociar
     * @return El almacen al cual se asocio el expediente
     * @throws NSJPNegocioException
     */
    public AlmacenDTO asociarExpedienteAlmacen (AlmacenDTO almacenDTO, ExpedienteDTO expedienteDTO) throws NSJPNegocioException;
    
    /**
	 * Servicio que permite consultar los movimientos de Objetos 
	 * del almacen. 
	 * Se consultar las evidencias asociadas a la Cadena Custodia, que asu vez, esta
	 * relacionada a un Expediente (Número de Expediente).
	 * 
	 * @param numeroExpediente
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<EvidenciaDTO> consultarEvidenciasAsociadasCadenaCustodiaPorNumeroExpediente( String numeroExpediente)  throws NSJPNegocioException;
	
	public AlmacenDTO consultarDetalleAlmacenPorId (Long almadenId)throws NSJPNegocioException;
}

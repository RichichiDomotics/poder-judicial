/**
* Nombre del Programa : MedidasCautelaresService.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 6 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato Service para los prsuntos responsables por causa
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
package mx.gob.segob.nsjp.service.medidascautelares;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.MedidaCautelarDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * Contrato Service para los prsuntos responsables por causa
 * @version 1.0
 * @author Tattva-IT
 *
 */
public interface ObtenerMedidasCautelaresService {

	/**
	 * Contrato del servicio para obtener las medidas cautelares por involucrado
	 * @param idInv
	 * @return List<MedidaCautelarDTO>
	 * @throws NSJPNegocioException
	 */
	List<MedidaCautelarDTO> obtenerMedidasCautelaresPorInvolucrado(Long idInv)throws NSJPNegocioException ;
	
	
	/**
	 * 
	 * @param idMedidaCautelar
	 * @param idInvolucrado
	 * @return MedidaCautelar
	 * @throws NSJPNegocioException
	 */
	MedidaCautelarDTO obtenerDetalleMedidaCautelar(Long idMedidaCautelar, Long idInvolucrado)throws NSJPNegocioException ;
	
	/**
	 * Consulta las medidas cautelares asociadas a cierto numero de expediente 
	 * @param numeroExpedienteId Número de expediente de la medida cautelar buscada
	 * @author Emigdio Hernández
	 * @return Lista de medidas cautelares encontradas
	 */
	List<MedidaCautelarDTO> obtenerMedidasCautelaresPorExpediente(Long numeroExpedienteId) throws NSJPNegocioException ;
	
	/**
	 * Consulta las medidas cautelares deacuerdo a su Estatus
	 * @param medidaCautelar DTO de la medida cautelar con el estatus deseado
	 * @author Marco Gallardo
	 * @return Lista de medidas cautelares encontradas
	 * @throws NSJPNegocioException 
	 */
	List<MedidaCautelarDTO> consultaMedidasCautelaresPorEstatus(MedidaCautelarDTO medidaCautelar) throws NSJPNegocioException ;
	/**
	 * Consulta un listado de involucrados por numero expediente/causa y obtiene adicionalmente sus medidas
	 * cautelares si es que las tiene
	 * @param numeroExpediente Número de expeidnte/causa a buscar
	 * @return Lista de involucrados con medidas cautelares
	 * @author Emigdio Hernández
	 */
	List<InvolucradoDTO> consultarInvolucradosConMedidasCautelaresPorNumeroExpedienteOCausa(String numeroExpediente) throws NSJPNegocioException;
	
	/**
	 * Consulta las medidas cautelares deacuerdo ID
	 * @param idMedidaCautelar Id de la medida cautelar con el estatus deseado
	 * @author Marco Gallardo
	 * @return MedidaCautelarDTO
	 * @throws NSJPNegocioException 
	 */
	MedidaCautelarDTO consultarMedidasCautelaresPorId(Long idMedidaCautelar) throws NSJPNegocioException ;
	
	/**
	 * Permite consultar las medidas cautelares que cuentan con una fecha de incumplimiento
	 * del dia anterior a la fecha actual
	 * @return List<MedidaCautelarDTO>
	 * @throws NSJPNegocioException
	 */
	public List<MedidaCautelarDTO> obtenerMedidasCautelaresPorFechaIncumplientoDelDiaAnterior() throws NSJPNegocioException;


    List<MedidaCautelarDTO> consultarMedidasCautelaresPorNumeroExpedienteOCausa(
            String numeroExpediente,UsuarioDTO usuario)  throws NSJPNegocioException; 
}

/**
* Nombre del Programa : MedidasAlternasService.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 19 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato del servicio para realizar las consultas de medidas alternas
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
package mx.gob.segob.nsjp.service.medidasalternas;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.documento.EstatusMedida;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaAlternaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * Contrato del servicio para realizar las consultas de medidas alternas.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public interface ConsultarMedidasAlternasService {

	/**
	 * Consulta las medidad alternas de acuerdo al numero de expediente id
	 * @author cesarAgustin
	 * @param expedienteDTO
	 * 			<li>numeroExpedienteId<li>
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<MedidaAlternaDTO> consultarMedidasAlternasPorNumeroExpediente (ExpedienteDTO expedienteDTO) throws NSJPNegocioException;
	
	/**
	 * Consulta las medidad alternas de acuerdo a su estatus
	 * @author Marco Gallardo
	 * @param MedidaAlternaDTO :El estatus de la medida alterna
	 * 			<li>EstatusMedida<li>
	 * @return List<MedidaAlternaDTO>
	 * @throws NSJPNegocioException
	 */
	List<MedidaAlternaDTO> consultarMedidasAlternasPorEstatus(EstatusMedida estatusMedida) throws NSJPNegocioException;

	/**
	 * Obtiene la informacion a detalle de una medida alterna
	 * @author cesarAgustin
	 * @param medidaAlternaDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	MedidaAlternaDTO consultarMedidasAlternasPorId(MedidaAlternaDTO medidaAlternaDTO) throws NSJPNegocioException;
	
	/**
	 * Servicio que permite consultar los involucrados con Medidas Alternas por el Número de Carpeta de Ejecución 
	 * @param numeroExpediente
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<InvolucradoDTO> consultarInvolucradosConMedidasAlternativasPorCarpetaEjecucion(
			String numeroExpediente,UsuarioDTO usuario) throws NSJPNegocioException ;
	
	/**
	 * Servicio que permite consultar el desatalle del a MEdida Alterna por IdMedida y IdInvolucrado
	 * 
	 * @param idMedidaAlterna
	 * @param idInvolucrado
	 * @return
	 * @throws NSJPNegocioException
	 */
	MedidaAlternaDTO obtenerDetalleMedidaAlterna(Long idMedidaAlterna, Long idInvolucrado) throws NSJPNegocioException;
}

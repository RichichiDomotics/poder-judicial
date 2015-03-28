/**
 * 
 */
package mx.gob.segob.nsjp.delegate.expediente;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ConvenioDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.medida.FechaCompromisoDTO;

/**
 * @author adrian
 *
 */
public interface ConvenioDelegate {
	/**
	 * Operación que permite registrar o modificar un convenio
	 * convenioID==null entonces REGISTRA
	 * convenioID!=null entonces MODIFICA
	 * 
	 * Obligatorios (InvolucradoPR, Funcionario(Mediador), NumeroExpedienteId)
	 * @param aoConvenioDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long guardarAcuerdoRestaurativo(ConvenioDTO aoConvenioDTO) throws NSJPNegocioException;
	
	/**
	 * Operación que realiza la funcionalidad de consultar los convenios de conciliación/mediación asociados al expediente.
	 * @param expedienteDTO: idExpediente, idNumeroExpediente
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<ConvenioDTO> consultarConveniosPorExpediente(ExpedienteDTO expedienteDTO)throws NSJPNegocioException;
	
	/**
	 * Operación que realiza la funcionalidad de consultar el detalle de un convenio 
	 * @param acuerdoRestaurativoDTO: convenioID
	 * @return
	 * @throws NSJPNegocioException
	 */
	ConvenioDTO consultarDetalleConvenio(ConvenioDTO aoConvenioDTO)throws NSJPNegocioException;
	
	/**
     * Actualiza los datos de una Fecha Compromiso. Actualiza la fecha compromiso con los parametros
     * distintos de {@code null} que vengan dentro de la fecha compromiso DTO
     * @throws NSJPNegocioException En caso que "{@code fechaCompromisoDTO}" o
     * "{@code loFechaCompromisoDTO.fechaCompromisoId}" sean {@code null}.|
     */
    public void actualizarFechaCompromido(FechaCompromisoDTO loFechaCompromisoDTO) throws NSJPNegocioException;
    
    /**
     * Permite consultar un pago de convenio por medio del id
     * @param aoFechaCompromiso
     * @return
     * @throws NSJPNegocioException
     */
    public FechaCompromisoDTO consultarPagoXId(FechaCompromisoDTO aoFechaCompromiso)throws NSJPNegocioException;

}

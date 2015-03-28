/**
 * Nombre del Programa  : RegistrarObjetoEnAudienciaService.java
 * Autor                : Daniel Jiménez
 * Compania             : TATTVA-IT
 * Proyecto             : NSJP                    Fecha: 18 Ago 2011
 * Marca de cambio      : N/A
 * Descripcion General  : Realiza búsquedas de expediente por Numero General de Caso
 * 						  y por involucrado y algunas combinaciones
 * Programa Dependiente : N/A
 * Programa Subsecuente : N/A
 * Cond. de ejecucion   : N/A
 * Dias de ejecucion    : N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                :N/A
 * Compania             :N/A
 * Proyecto             :N/A                                 Fecha: N/A
 * Modificacion         :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.expediente;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;


/**
 * Realiza búsquedas de expediente por Numero General de Caso y por involucrado y algunas combinaciones
 * 
 * @version 1.0
 * @author Daniel Jiménez
 * 
 */
public interface BuscarExpedientePorCasoImputadoService {
	
	/**
	 * Busca un expediente que pertenzca al caso identificado por <code>numeroGeneralcaso</code> y un 
	 * involucrado <code>imputado</code>
	 * @param numeroGeneralcaso
	 * @param nombreImputado
	 * @return
	 * @throws NSJPNegocioException
	 */
	public ExpedienteDTO buscarExpedientePorCasoImputado(String numeroGeneralcaso, InvolucradoDTO imputado) throws NSJPNegocioException;

	public ExpedienteDTO buscarExpedientePorCasoImputado(String numeroCaso,
			String imputado);

	
	/**
	 * Busca un numero de expediente que tenga como probable responsable principal 
	 * al involucrado identificado por <code>folioImputado</code>
	 * @param numeroCaso
	 * @param folioImputado
	 * @return
	 */
	public ExpedienteDTO buscarNumeroExpedientePorCasoImputado(String numeroCaso,
			String folioImputado);
	
}

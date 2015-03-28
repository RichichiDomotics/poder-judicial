/**
* Nombre del Programa : ConsultarBitacoraConsultaService.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 31/08/2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato de los servicios que permiten consultar la información de 
* 							la Bitacora de Consultas Permitidas (INQ)
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
package mx.gob.segob.nsjp.service.bitacora;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.bitacora.BitacoraConsultaDTO;
import mx.gob.segob.nsjp.dto.bitacora.FiltroBitacoraConsultaDTO;

/**
 * Contrato de los servicios que permiten consultar la información de 
 * la Bitacora de Consultas Permitidas (INQ) 
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public interface ConsultarBitacoraConsultaService {

	/**
	 * Consulta los registros de la entidad de Bitacora Consulta por cualquiera o
	 * combinación de los siguientes filtros:
	 * numeroExpediente : cadena de numero de expediente 
	 * fechaConsulta:	fecha en formato Date
	 * horaConsulta: hora en formato Date
	 * funcionario:  Nombre del funcionario, puede hacerse la búsqueda por: nombre, AP, AM 
	 * esPermitida:
	 * 
	 * En caso de que no sea proporcionado ninguno de los filtros anteriormente mencionado
	 * se constultan todos los registros de Bitacora Consulta
	 *
	 * @param filtroBitacoraConsultaDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<BitacoraConsultaDTO> consultarBitacoraConsultaPorFiltro(FiltroBitacoraConsultaDTO filtroBitacoraConsultaDTO) throws NSJPNegocioException;
	
}

/**
* Nombre del Programa : BitacoraConsultaDAO.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 31/08/2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato de DAO para BitacoraConsulta.
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
package mx.gob.segob.nsjp.dao.bitacora;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.BitacoraConsulta;
import mx.gob.segob.nsjp.model.Funcionario;

/**
 * Contrato de DAO para BitacoraConsulta.
 * @version 1.0
 * @author GustavoBP
 */
public interface BitacoraConsultaDAO extends GenericDao<BitacoraConsulta, Long> {

	/**
	 * Consulta los registros de la entidad de Bitacora Consulta por cualquiera o
	 * combinación de los siguientes filtros:
	 * 
	 * @param numeroExpediente
	 * @param fechaConsulta
	 * @param horaConsulta
	 * @param funcionario  Nombre del funcionario, puede hacerse la búsqueda por: nombre, AP, AM 
	 * @param esPermitida
	 * @return
	 */
	List<BitacoraConsulta> consultarBitacoraConsultaPorFiltros(
			String numeroExpediente, Date fechaConsulta, Date horaConsulta, Funcionario funcionario, Boolean esPermitida );
}

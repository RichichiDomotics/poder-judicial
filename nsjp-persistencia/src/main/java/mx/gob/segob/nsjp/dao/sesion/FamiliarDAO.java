package mx.gob.segob.nsjp.dao.sesion;

/**
* Nombre del Programa : FamiliarDAO.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 14 Oct 2011
* Marca de cambio        : N/A
* Descripcion General    : Contrato de metodos de acceso a datos de la entidad Familiar
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

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.base.GenericDao;
import mx.gob.segob.nsjp.model.EntrevistaComplementaria;
import mx.gob.segob.nsjp.model.Familiar;


/**
 * Contrato de metodos de acceso a datos de la entidad Familiar.
 * @author rgama
 *
 */
public interface FamiliarDAO extends GenericDao<Familiar, Long> {
	
	/**
	 * Consulta Familiar por identificador
	 * @param aoFamiliar
	 * 		Obligatorio <b>familiarId</b>.
	 * @return
	 * @throws NSJPNegocioException
	 */
    Familiar consultarFamiliarPorId(Familiar aoFamiliar);
	
	/**
	 * Consulta todos los familiares asociados a una Entrevista complementaria
	 * @param aoEntrevistaComplementaria
	 * 	 Obligatorio <b>entrevistaComplementaria.sesionId</b>.
	 * @return
	 * @throws NSJPNegocioException
	 */
    List<Familiar> consultarFamiliaresPorIdEntrevistaComplementaria(EntrevistaComplementaria aoEntrevistaComplementaria);
	
}

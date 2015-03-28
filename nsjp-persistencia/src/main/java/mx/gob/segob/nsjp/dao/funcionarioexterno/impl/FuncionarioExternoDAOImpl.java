/**
* Nombre del Programa 		: FuncionarioExternoDAOImpl.java
* Autor 					: EdgarTE
* Compania 					: Ultrasist
* Proyecto 					: NSJP 							Fecha: 13 Apr 2012
* Marca de cambio 			: N/A
* Descripcion General 		: Describir el objetivo de la clase de manera breve
* Programa Dependiente 		: N/A
* Programa Subsecuente 		: N/A
* Cond. de ejecucion 		: N/A
* Dias de ejecucion 		: N/A 							Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor 					: N/A
* Compania 					: N/A
* Proyecto 					: N/A 							Fecha: N/A
* Modificacion 				: N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.dao.funcionarioexterno.impl;

import org.springframework.stereotype.Repository;

import mx.gob.segob.nsjp.dao.base.impl.GenericDaoHibernateImpl;
import mx.gob.segob.nsjp.dao.funcionarioexterno.FuncionarioExternoDAO;

import mx.gob.segob.nsjp.model.FuncionarioExterno;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author EdgarTE
 *
 */
@Repository
public class FuncionarioExternoDAOImpl extends GenericDaoHibernateImpl<FuncionarioExterno, Long>
		implements FuncionarioExternoDAO {

}

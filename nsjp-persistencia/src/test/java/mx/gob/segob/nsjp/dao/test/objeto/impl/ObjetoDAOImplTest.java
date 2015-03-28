/**
* Nombre del Programa : ObjetoDAOImpl.java
* Autor                            : Emigdio Hernández
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 13/06/2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba unitaria del objeto de acceso a datos de los elementos del tipo objeto
* de un expediente
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
package mx.gob.segob.nsjp.dao.test.objeto.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.objeto.ObjetoDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Objeto;

/**
 * Prueba unitaria del objeto de acceso a datos de los elementos del tipo objeto
 * de un expediente
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public class ObjetoDAOImplTest extends BaseTestPersistencia<ObjetoDAO> {

	public void testConsultarObjetosExpediente(){

		List <Objeto> objetos = daoServcice.consultarObjetosByExpediente((long)227);
		for(Objeto obj:objetos){
			System.out.println(obj.getValorByTipoObjetoVal().getValor());
		}
		logger.info("El total de objetos es " +  objetos.size());

		assertNotNull("La lista de resultado no puede ser nula",objetos);
	}
	
	public void testConsultarInventarioPertenenciasDetenido(){

		List <Objeto> objetos = daoServcice.consultarInventarioPertenenciasDetenido((long)134);
		for(Objeto obj:objetos){
			logger.info("["+obj.getValorByTipoObjetoVal().getValor()+" :: "+obj.getDescripcion()+"]");
		}
		assertNotNull("La lista de resultado no puede ser nula",objetos);
	}
}

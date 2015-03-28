/**
 * Nombre del Programa : CatDelitoDAOImplTest.java
 * Autor                            : rgama
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 24 08 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Describir el objetivo de la clase de manera breve
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
package mx.gob.segob.nsjp.dao.test.catalogo.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.dao.actividad.CatDelitoActuacionDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Valor;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author rgama
 * 
 */
public class CatDelitoActuacionDAOImplTest extends BaseTestPersistencia<CatDelitoActuacionDAO> {
	
    public void testConsultarTodos() {
    	List<Long> idCatDelitos = new ArrayList<Long>();
    	idCatDelitos.add(4L);
    	idCatDelitos.add(3L);

        List<Valor> data = daoServcice.consultarActuacionesPorIdsCatDelito(idCatDelitos);
        for (Valor actuacion : data) {
        	System.out.println("Nombre actuacion: " + actuacion.getValor());
		}
        logger.debug("data.size() :: "+data.size());
    }
    
}

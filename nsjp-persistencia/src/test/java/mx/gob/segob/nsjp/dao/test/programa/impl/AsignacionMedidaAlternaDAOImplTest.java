/**
* Nombre del Programa : AsignacionMedidaAlternaDAOImplTest.java
* Autor                            : AntonioBV
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 26/01/2012
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
package mx.gob.segob.nsjp.dao.test.programa.impl;

import mx.gob.segob.nsjp.dao.programa.AsignacionMedidaAlternaDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.AsignacionMedidaAlterna;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public class AsignacionMedidaAlternaDAOImplTest  extends BaseTestPersistencia<AsignacionMedidaAlternaDAO> {
    	
	public void testConsultarAsignacionMedidaAlternaPorId() {
    	
    	AsignacionMedidaAlterna asignacionMedidaAlterna = new AsignacionMedidaAlterna();
    	asignacionMedidaAlterna.setAsignacionMedidaAlternaId(1L);
    	asignacionMedidaAlterna.setBcumplida(null);
		try {
			asignacionMedidaAlterna = daoServcice.consultarAsignacionMedidaAlternaPorId(asignacionMedidaAlterna);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        assertNotNull(asignacionMedidaAlterna.getBcumplida());
    }	
}


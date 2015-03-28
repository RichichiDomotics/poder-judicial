/**
* Nombre del Programa : ProgramaDAOImplTest.java
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

import mx.gob.segob.nsjp.dao.programa.ProgramaDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Programa;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public class ProgramaDAOImplTest  extends BaseTestPersistencia<ProgramaDAO> {
    	
	public void testConsultarProgramaPorId() {
    	
    	Programa programa = new Programa();
    	programa.setProgramaId(1L);
    	programa.setDfechaIngreso(null);
		try {
			programa = daoServcice.consultarProgramaPorId(programa);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        assertNotNull(programa.getDfechaIngreso());
    }	
}


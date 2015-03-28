/**
* Nombre del CatTipoTrabajoExterno : CatTipoTrabajoExternoDAOImplTest.java
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

import java.util.List;

import mx.gob.segob.nsjp.dao.programa.CatTipoTrabajoExternoDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.CatTipoTrabajoExterno;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public class CatTipoTrabajoExternoDAOImplTest  extends BaseTestPersistencia<CatTipoTrabajoExternoDAO> {
    
	
	public void testConsultarCatTipoTrabajoExternos() {
    	
    	List<CatTipoTrabajoExterno> lstResultado = null;;
		try {
			lstResultado = daoServcice.consultarCatTipoTrabajoExterno();
			System.out.println(lstResultado);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        assertNotNull(lstResultado);
        
    }
    
}

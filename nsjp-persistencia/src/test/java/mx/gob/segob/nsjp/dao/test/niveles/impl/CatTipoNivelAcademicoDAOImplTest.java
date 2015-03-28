/**
* Nombre del CatTipoNivelAcademico : CatTipoNivelAcademicoDAOImplTest.java
* Autor                            : AntonioBV
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 26/01/2012
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
* CatTipoNivelAcademico Dependiente  :N/A
* CatTipoNivelAcademico Subsecuente :N/A
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
package mx.gob.segob.nsjp.dao.test.niveles.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.niveles.CatTipoNivelAcademicoDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.CatTipoNivelAcademico;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public class CatTipoNivelAcademicoDAOImplTest  extends BaseTestPersistencia<CatTipoNivelAcademicoDAO> {
    
	
	public void testConsultarCatTipoNivelAcademicos() {
    	
    	List<CatTipoNivelAcademico> lstResultado = null;;
		try {
			lstResultado = daoServcice.consultarCatTipoNivelAcademico();
			System.out.println(lstResultado);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        assertNotNull(lstResultado);
        
    }
    
}

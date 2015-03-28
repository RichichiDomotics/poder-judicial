/**
* Nombre del CatTipoPrograma : CatTipoProgramaDAOImplTest.java
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

import mx.gob.segob.nsjp.dao.programa.CatTipoProgramaDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.CatTipoPrograma;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public class CatTipoProgramaDAOImplTest  extends BaseTestPersistencia<CatTipoProgramaDAO> {
    
	
	public void testConsultarCatTipoProgramas() {
    	
    	List<CatTipoPrograma> lstResultado = null;;
		try {
			lstResultado = daoServcice.consultarCatTipoPrograma();
			System.out.println(lstResultado);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        assertNotNull(lstResultado);
        
    }
    	
	public void testInsertarCatTipoPrograma() {
    	
    	CatTipoPrograma catTipoPrograma = new CatTipoPrograma();
    	catTipoPrograma.setCatTipoProgramaId(1L);
    	catTipoPrograma.setCdescripcion("Programa Tipo 1");
    	Long resultado=null;
		try {
			resultado = daoServcice.create(catTipoPrograma);
			daoServcice.flush();
			System.out.println(resultado);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        assertNotNull(resultado);
        
    }
    
	
    
}

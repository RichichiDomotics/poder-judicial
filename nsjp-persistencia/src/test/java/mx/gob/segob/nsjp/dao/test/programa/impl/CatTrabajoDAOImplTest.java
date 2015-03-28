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

import mx.gob.segob.nsjp.dao.programa.CatTrabajoDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.CatTrabajo;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public class CatTrabajoDAOImplTest  extends BaseTestPersistencia<CatTrabajoDAO> {
    
	
	public void testConsultarCatTrabajo() {
    	
    	List<CatTrabajo> lstResultado = null;;
		try {
			lstResultado = daoServcice.consultarCatTrabajo();
			
			for (CatTrabajo catTrabajo : lstResultado) {
				if(catTrabajo.getCatTipoTrabajoExterno()!=null){
					System.out.println(catTrabajo.getCatTipoTrabajoExterno().getCdescripcion());
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        assertNotNull(lstResultado);
        
    }

	public void testInsertarCatTrabajo() {
    	
    	CatTrabajo obj = new CatTrabajo();
    	
    	obj.setBesExterno(true);
    	obj.setCdescripcion("algo");
    	obj.setCnombre("nombre-2");
    	obj.setIpuntos(100L);
    	
    	Long id = null;
		try {
			id = daoServcice.create(obj);
			daoServcice.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        assertNotNull(id);
        
    }	
	
	
	public void testEliminarCatTrabajo() {
    	
    	CatTrabajo catTrabajo = new CatTrabajo();
    	catTrabajo.setCatTrabajoId(11L);
    	catTrabajo.setBActivo(Boolean.FALSE);
    	boolean resultado = false;
		try {
			resultado = daoServcice.eliminarTrabajoPorId(catTrabajo);
			daoServcice.flush();
			System.out.println(resultado);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        assertTrue(resultado);
        
    }	
	
	
	
}

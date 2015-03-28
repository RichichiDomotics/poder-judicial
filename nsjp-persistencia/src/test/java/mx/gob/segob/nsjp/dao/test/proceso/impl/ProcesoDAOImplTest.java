/**
* Nombre del Programa : ProcesoDAOImplTest.java
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
package mx.gob.segob.nsjp.dao.test.proceso.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.proceso.ProcesoDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Proceso;
import mx.gob.segob.nsjp.model.Rol;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public class ProcesoDAOImplTest  extends BaseTestPersistencia<ProcesoDAO> {
    /*
    public void testConsultarProcesos() {
    	
    	List<Proceso> lstProceso = null;;
		try {
			lstProceso = daoServcice.consultarProcesos();
			System.out.println(lstProceso);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
        
        assertNotNull(lstProceso);
        
    }*/

    public void testConsultarProcesosPorRol() {
    	
    	List<Proceso> lstProceso = null;;
    	Rol rol = new Rol();
    	rol.setRolId(48L);
		try {
			lstProceso = daoServcice.consultarProcesosPorRol(rol);
			
			for (Proceso proceso : lstProceso) {
				System.out.println(proceso.getCnombreProceso());	
			}
			
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
        
        assertNotNull(lstProceso);
        
    }    
    
    
}

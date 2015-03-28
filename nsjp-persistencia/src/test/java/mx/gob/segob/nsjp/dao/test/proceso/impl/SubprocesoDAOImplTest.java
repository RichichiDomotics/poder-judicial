/**
* Nombre del Programa : SubprocesoDAOImplTest.java
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
import mx.gob.segob.nsjp.dao.proceso.SubprocesoDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Proceso;
import mx.gob.segob.nsjp.model.Subproceso;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public class SubprocesoDAOImplTest  extends BaseTestPersistencia<SubprocesoDAO> {
    /*
    public void testConsultarSubrocesos() {
    	
    	List<Subproceso> lstSubproceso = null;;
		try {
			lstSubproceso = daoServcice.consultarSubprocesos();
			System.out.println(lstSubproceso);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
        
        assertNotNull(lstSubproceso);
        
    }*/

    public void testConsultarSubrocesosPorProceso() {
    	
    	List<Subproceso> lstSubproceso = null;;
    	Proceso proceso = new Proceso();
    	proceso.setId(1L);
		try {
			lstSubproceso = daoServcice.consultarSubprocesosPorProceso(proceso);
			for (Subproceso subproceso : lstSubproceso) {
				System.out.println(subproceso.getCnombreSubproceso());
			}
			
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
        
        assertNotNull(lstSubproceso);
        
    }    
    
    
}
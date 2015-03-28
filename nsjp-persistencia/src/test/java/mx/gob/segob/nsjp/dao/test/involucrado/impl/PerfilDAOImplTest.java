/**
* Nombre del Programa : PerfilDAOImplTest.java
* Autor                            : cesar
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 24 May 2011
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
package mx.gob.segob.nsjp.dao.test.involucrado.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.involucrado.PerfilDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Perfil;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class PerfilDAOImplTest extends BaseTestPersistencia<PerfilDAO> {

	public void testConsultarPerfilByInvolucrado () {		
		List<Perfil> listPerfil = daoServcice.consultarPerfilByInvolucrado(3L);
		assertNotNull("La lista no puede venir nula", listPerfil);	
		assertTrue("La lista debe tener almenos un elemento ", listPerfil.size()>0);		
	}
	
}

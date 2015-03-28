/**
* Nombre del Programa : OrganizacionDAOImplTest.java                                    
* Autor                            : Tattva-IT                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 27/04/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Prueba para el DAO de Organizacion                      
* Programa Dependiente  :N/A                                                      
* Programa Subsecuente :N/A                                                      
* Cond. de ejecucion        :N/A                                                      
* Dias de ejecucion          :N/A                             Horario: N/A       
*                              MODIFICACIONES                                       
*------------------------------------------------------------------------------           
* Autor                       :N/A                                                           
* Compania               :N/A                                                           
* Proyecto                 :N/A                                   Fecha: N/A       
* Modificacion           :N/A                                                           
*------------------------------------------------------------------------------           
*/
package mx.gob.segob.nsjp.dao.test.organizacion.impl;

import mx.gob.segob.nsjp.comun.enums.relacion.Relaciones;
import mx.gob.segob.nsjp.dao.organizacion.OrganizacionDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Organizacion;

/**
 * Prueba para el DAO de Organizacion
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class OrganizacionDAOImplTest extends BaseTestPersistencia<OrganizacionDAO> {
	public void testobtenerOrganizacionByRelacion() {
		Organizacion organizacion = daoServcice.obtenerOrganizacionByRelacion(3821L, new Long(Relaciones.ORGANIZACION_INVOLUCRADA.ordinal()));
				
		assertTrue("Organizacion", organizacion.getElementoId()>0);
		logger.info("Organizacion" + organizacion.getElementoId());
	}
}

/**
* Nombre del Programa : SitioDAOImplTest.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 18/10/2011
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
package mx.gob.segob.nsjp.dao.test.audiencia.impl;

import java.util.Date;

import mx.gob.segob.nsjp.dao.audiencia.SalaDAO;
import mx.gob.segob.nsjp.dao.audiencia.SitioDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Sala;
import mx.gob.segob.nsjp.model.Sitio;
import mx.gob.segob.nsjp.model.Valor;

/**
 * Clase de Pruebas Unitarias sobre la entidad de Sitio.
 * @version 1.0
 * @author GustavoBP
 *
 */
public class SitioDAOImplTest extends BaseTestPersistencia<SitioDAO>{
	
	public void testIngresarSitio(){
		
		Sitio sitio = new Sitio();
		sitio.setFecha(new Date());
		sitio.setSala(new Sala(1L));
//		sitio.setCentroDetencion(new CentroDetencion(1L));
		sitio.setTipoSitioTraslado(new Valor(2156L));
			
		Long idSitio =daoServcice.create(sitio);
		assertTrue("No se fectuo el guardado:", idSitio>0);
	}
	
	public void testConsultarSitio(){
		
		Long sitioId = 1L;
		Sitio sitio = daoServcice.read(sitioId );
		assertNotNull("La entidad no debe ser nula ", sitio);
		logger.info(" SitioID:"+ sitio.getSitioId());
		logger.info(" Sitio - getFecha:"+ sitio.getFecha());
		logger.info(" Sitio - getCentroDetencion:"+ sitio.getCentroDetencion());
		logger.info(" Sitio - getSala:"+ sitio.getSala());
//		logger.info(" Sitio - getTipoSitioTraslado:"+ sitio.getTipoSitioTraslado());
	}
	
	
	

}

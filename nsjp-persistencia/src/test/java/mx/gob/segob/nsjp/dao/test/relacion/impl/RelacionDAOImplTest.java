/**
* Nombre del Programa : RelacionDAOImplTest.java
* Autor                            : cesar
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 23 May 2011
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
package mx.gob.segob.nsjp.dao.test.relacion.impl;

import java.util.Arrays;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.relacion.Relaciones;
import mx.gob.segob.nsjp.comun.enums.relacion.TipoRelacion;
import mx.gob.segob.nsjp.dao.relacion.RelacionDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Relacion;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class RelacionDAOImplTest extends BaseTestPersistencia<RelacionDAO> {

	public void testObtenerRelacionSimple () {
		List<Relacion> relacion = daoServcice.obtenerRelacionSimple(1938L, new Long(Relaciones.UBICACION.ordinal()));
		logger.info("Relaciones ID: " + relacion.size());
		assertTrue("El identificado debe ser mayor a cero : ", relacion.size()>0);
		for (Relacion rel : relacion) {
			logger.info("Valor: "+rel.getElementoByComplementoId().getElementoId());
			logger.info("Valor: "+rel.getElementoBySujetoId().getElementoId());
		}
		
	}
	
	public void testConsultarRelacionByElemento() {
		List<Relacion> relaicones = daoServcice.consultarRelacionByElemento(24L);
		logger.info("La lista debe tener minimo un elemento " + relaicones.size());		
		assertTrue("La lista debe tener minimo un elemento ", relaicones.size()>0);
	}
	
	public void testConsultarRelacionByIdElementoAndTipoRelacion(){
		Long[] idElem= {134L, 137L};
		List<Long> idElementos= Arrays.asList(idElem);
		
		List<Relacion> relaciones = daoServcice.consultarRelacionByIdElementoAndTipoRelacion(idElementos, TipoRelacion.EXPLICITA.getValorId());
		for (Relacion relacion : relaciones) {
			logger.info("Id relacion: "+ relacion.getRelacionId());

		}
		
		assertTrue("La lista debe tener minimo un elemento ", relaciones.size()>0);
		logger.info("Relaciones: "+ relaciones.size());
	}
}

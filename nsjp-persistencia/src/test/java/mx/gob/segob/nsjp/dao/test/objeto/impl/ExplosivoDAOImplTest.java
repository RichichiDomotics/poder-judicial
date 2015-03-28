/**
* Nombre del Programa : ExplosivoDAOImplTest.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 17 May 2011
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
package mx.gob.segob.nsjp.dao.test.objeto.impl;

import java.util.Date;

import mx.gob.segob.nsjp.dao.objeto.ExplosivoDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Almacen;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Explosivo;

/**
 * Prueba para el DAO de Explosivo
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class ExplosivoDAOImplTest  extends BaseTestPersistencia<ExplosivoDAO> {
	
	public void ingresarEquipoComputo(){
		logger.debug("Prueba para ingresar un explosivo");
		Expediente expediente = new Expediente(new Long(1), "0");
		Calidad cal = new Calidad(new Long(1));
		
		Explosivo expl = new Explosivo();
		
		expl.setAlmacen(new Almacen(1L));
		expl.setDescripcion("desc");
		expl.setFechaCreacionElemento(new Date());
		expl.setExpediente(expediente);
		expl.setCalidad(cal);
		
		Long idEquipoComputo = this.daoServcice.create(expl) 		;
		assertTrue("El resultado debe ser mayor a 0 : ",idEquipoComputo>0);

	}



}

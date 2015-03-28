/**
* Nombre del Programa : AnimalDAOImplTets.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 17 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba para el DAO de Animal
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

import mx.gob.segob.nsjp.dao.objeto.AnimalDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Almacen;
import mx.gob.segob.nsjp.model.Animal;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.Expediente;

/**
 * Prueba para el DAO de Animal
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class AnimalDAOImplTets  extends BaseTestPersistencia<AnimalDAO> {
	
	public void ingresarEquipoComputo(){
		logger.debug("Prueba para ingresar un animal");
		Expediente expediente = new Expediente(new Long(1), "0");
		Calidad cal = new Calidad(new Long(1));
		
		Animal  eq = new Animal();
		
		eq.setAlmacen(new Almacen(1L));
		eq.setDescripcion("desc");
		eq.setFechaCreacionElemento(new Date());
		eq.setExpediente(expediente);
		eq.setCalidad(cal);
		
		Long idEquipoComputo = this.daoServcice.create(eq) 		;
		assertTrue("El resultado debe ser mayor a 0 : ",idEquipoComputo>0);

	}


}

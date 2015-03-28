/**
* Nombre del Programa : NumerarioDAOImplTest.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 17 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba para el DAO de Numerario
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

import mx.gob.segob.nsjp.dao.objeto.NumerarioDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Almacen;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Numerario;

/**
 * Prueba para el DAO de Numerario
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class NumerarioDAOImplTest  extends BaseTestPersistencia<NumerarioDAO> {
	
	public void ingresarEquipoComputo(){
		logger.debug("Prueba para ingresar un numerario");
		Expediente expediente = new Expediente(new Long(1), "0");
		Calidad cal = new Calidad(new Long(1));
		
		Numerario num = new Numerario();
		
		num.setAlmacen(new Almacen(1L));
		num.setDescripcion("desc");
		num.setFechaCreacionElemento(new Date());
		num.setExpediente(expediente);
		num.setCalidad(cal);
		
		Long idEquipoComputo = this.daoServcice.create(num) 		;
		assertTrue("El resultado debe ser mayor a 0 : ",idEquipoComputo>0);

	}



}

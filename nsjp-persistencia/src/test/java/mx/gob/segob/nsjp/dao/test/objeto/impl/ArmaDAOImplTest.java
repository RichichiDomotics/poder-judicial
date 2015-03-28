/**
* Nombre del Programa : ArmaDAOImplTest.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 17 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba para el DAO de Arma
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

import mx.gob.segob.nsjp.dao.objeto.ArmaDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Almacen;
import mx.gob.segob.nsjp.model.Arma;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.Expediente;

/**
 * Prueba para el DAO de Arma
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class ArmaDAOImplTest  extends BaseTestPersistencia<ArmaDAO> {
	
	public void ingresarEquipoComputo(){
		logger.debug("Prueba para ingresar un arma");
		Expediente expediente = new Expediente(new Long(1), "0");
		Calidad cal = new Calidad(new Long(1));
		
		Arma ar = new Arma();
		
		ar.setAlmacen(new Almacen(1L));
		ar.setDescripcion("desc");
		ar.setFechaCreacionElemento(new Date());
		ar.setExpediente(expediente);
		ar.setCalidad(cal);
		
		Long idEquipoComputo = this.daoServcice.create(ar) 		;
		assertTrue("El resultado debe ser mayor a 0 : ",idEquipoComputo>0);

	}



}

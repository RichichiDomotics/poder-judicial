/**
* Nombre del Programa : JoyaDAOImplTest.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 17 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba para el DAO de Joya
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

import mx.gob.segob.nsjp.dao.objeto.JoyaDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Almacen;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Joya;

/**
 * Prueba para el DAO de Joya
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class JoyaDAOImplTest  extends BaseTestPersistencia<JoyaDAO> {
	
	public void ingresarEquipoComputo(){
		logger.debug("Prueba para ingresar una Joya");
		Expediente expediente = new Expediente(new Long(1), "0");
		Calidad cal = new Calidad(new Long(1));
		
		Joya jy = new Joya();
		
		jy.setAlmacen(new Almacen(1L));
		jy.setDescripcion("desc");
		jy.setFechaCreacionElemento(new Date());
		jy.setExpediente(expediente);
		jy.setCalidad(cal);
		
		Long idEquipoComputo = this.daoServcice.create(jy);
		assertTrue("El resultado debe ser mayor a 0 : ",idEquipoComputo>0);

	}



}

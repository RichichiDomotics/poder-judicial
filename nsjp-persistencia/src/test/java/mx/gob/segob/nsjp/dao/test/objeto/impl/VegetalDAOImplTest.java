/**
* Nombre del Programa : VegetalDAOImplTest
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 17 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba para el DAO de Vegetal
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

import mx.gob.segob.nsjp.dao.objeto.VegetalDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Almacen;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Vegetal;

/**
 * Prueba para el DAO de Vegetal
 * @version 1.0
 * @author Tattva-IT
 *
 */

public class VegetalDAOImplTest  extends BaseTestPersistencia<VegetalDAO> {
	
	public void ingresarVegetal(){
		logger.debug("Prueba para ingresar un Vegetal");
		Expediente expediente = new Expediente(new Long(1), "0");
		Calidad cal = new Calidad(new Long(1));
		
		Vegetal vg = new Vegetal();
		
		vg.setAlmacen(new Almacen(1L));
		vg.setDescripcion("desc");
		vg.setFechaCreacionElemento(new Date());
		vg.setExpediente(expediente);
		vg.setCalidad(cal);
		
		Long idEquipoComputo = this.daoServcice.create(vg) 		;
		assertTrue("El resultado debe ser mayor a 0 : ",idEquipoComputo>0);

	}


}

/**
* Nombre del Programa : EmbarcacionDAOImplTest
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 17 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba para el DAO de Embarcacion
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

import mx.gob.segob.nsjp.dao.objeto.EmbarcacionDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Almacen;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.Embarcacion;
import mx.gob.segob.nsjp.model.Expediente;

public class EmbarcacionDAOImplTest  extends BaseTestPersistencia<EmbarcacionDAO> {
	
	public void ingresarEquipoComputo(){
		logger.debug("Prueba para ingresar una embarcacion");
		Expediente expediente = new Expediente(new Long(1), "0");
		Calidad cal = new Calidad(new Long(1));
		
		Embarcacion eq = new Embarcacion ();
		
		eq.setAlmacen(new Almacen(1L));
		eq.setDescripcion("desc");
		eq.setFechaCreacionElemento(new Date());
		eq.setExpediente(expediente);
		eq.setCalidad(cal);
		
		Long idEquipoComputo = this.daoServcice.create(eq) 		;
		assertTrue("El resultado debe ser mayor a 0 : ",idEquipoComputo>0);

	}



}

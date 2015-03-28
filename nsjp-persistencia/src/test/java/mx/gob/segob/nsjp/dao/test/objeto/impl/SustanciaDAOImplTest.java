/**
* Nombre del Programa : SustanciaDAOImplTests.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 17 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba para el DAO de Sustancia
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

import mx.gob.segob.nsjp.dao.objeto.SustanciaDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Almacen;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Sustancia;

/**
 * Prueba para el DAO de Sustancia
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class SustanciaDAOImplTest  extends BaseTestPersistencia<SustanciaDAO> {
	
	public void ingresarEquipoComputo(){
		logger.debug("Prueba para ingresar una Sustancia");
		Expediente expediente = new Expediente(new Long(1), "0");
		Calidad cal = new Calidad(new Long(1));
		
		Sustancia st = new Sustancia();
		
		st.setAlmacen(new Almacen(1L));
		st.setDescripcion("desc");
		st.setFechaCreacionElemento(new Date());
		st.setExpediente(expediente);
		st.setCalidad(cal);
		
		Long idEquipoComputo = this.daoServcice.create(st) 		;
		assertTrue("El resultado debe ser mayor a 0 : ",idEquipoComputo>0);

	}



}

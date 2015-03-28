/**
* Nombre del Programa : VehiculoDAOImplTest.java                                    
* Autor                            : Tattva-IT                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 4 May 2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Prueba para el DAO de Vehiculo                
* Programa Dependiente  :N/A                                                      
* Programa Subsecuente :N/A                                                      
* Cond. de ejecucion        :N/A                                                      
* Dias de ejecucion          :N/A                             Horario: N/A       
*                              MODIFICACIONES                                       
*------------------------------------------------------------------------------           
* Autor                       :N/A                                                           
* Compania               :N/A                                                           
* Proyecto                 :N/A                                   Fecha: N/A       
* Modificacion           :N/A                                                           
*------------------------------------------------------------------------------           
*/
package mx.gob.segob.nsjp.dao.test.objeto.impl;

import java.util.Date;

import mx.gob.segob.nsjp.dao.objeto.VehiculoDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Almacen;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Vehiculo;

/**
 * Prueba para el DAO de Vehiculo
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class VehiculoDAOImplTest extends BaseTestPersistencia<VehiculoDAO>{
	public void ingresarVehiculo(){
		logger.debug("Prueba para ingresar un vehiculo");
		Expediente expediente = new Expediente(new Long(1), "0");
//		Calidad cal = new Calidad(new Long(1));
		Vehiculo ve = new Vehiculo();
		
		ve.setAlmacen(new Almacen(1L));
		ve.setCalidad(new Calidad(3L));
		ve.setDescripcion("descripcion");
		ve.setEsBlindado(true);
		ve.setFechaCreacionElemento(new Date());
		ve.setModelo((short)6);
		ve.setNoCilindros((short)7);
		ve.setNoMotor("4435435sfr");
		ve.setNoPuertas((short)8);
		ve.setNoSerie("44343443ffffff");
		ve.setNrfv("0001");
		ve.setPlaca("mav1265");
		
		ve.setExpediente(expediente);
		
		//Long idCalidad = this.daoServcice.create(newInstance)
		
		Long idvehiculo = this.daoServcice.create(ve);
		
		assertTrue("El resultado debe ser mayor a 0 : ",idvehiculo>0);
		logger.debug("Prueba para ingresar una nueva vehiculo "+ idvehiculo);
		
	}

}

/**
* Nombre del Programa : TelefoniaDAOImplTest.java                                    
* Autor                            : Tattva-IT                                              
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 6 May 2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Prueba para el DAO de Telefonia                      
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

import mx.gob.segob.nsjp.dao.objeto.TelefoniaDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Almacen;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Telefonia;

/**
 * Prueba para el DAO de Telefonia
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class TelefoniaDAOImplTest extends BaseTestPersistencia<TelefoniaDAO> {
	
	public void ingresarTelefonia(){
		logger.debug("Prueba para ingresar un equipo telefonico");
		Expediente expediente = new Expediente(new Long(1), "0");
		Calidad cal = new Calidad(new Long(1));
		
		Telefonia telf = new Telefonia();
		telf.setAlmacen(new Almacen(1L));
		
		telf.setDescripcion("descripcion");
		telf.setFechaCreacionElemento(new Date());
		telf.setExpediente(expediente);
		telf.setCalidad(cal);
		
		Long idEquipoComputo = this.daoServcice.create(telf) 		;
		assertTrue("El resultado debe ser mayor a 0 : ",idEquipoComputo>0);

	}

}

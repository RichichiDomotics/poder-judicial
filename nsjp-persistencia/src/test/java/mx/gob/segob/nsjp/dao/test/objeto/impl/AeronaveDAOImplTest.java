/**
* Nombre del Programa : AeronaveDAOImplTest
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 17 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba para el DAO de Aeronave
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

import mx.gob.segob.nsjp.comun.enums.elemento.Elementos;
import mx.gob.segob.nsjp.comun.enums.objeto.Objetos;
import mx.gob.segob.nsjp.dao.objeto.AeronaveDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Aeronave;
import mx.gob.segob.nsjp.model.Almacen;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Valor;


/**
 * Prueba para el DAO de Aeronave
 * @version 1.0
 * @author Tattva-IT
 *
 */

public class AeronaveDAOImplTest  extends BaseTestPersistencia<AeronaveDAO> {
	
	public void testIngresarEquipoComputo(){
		logger.debug("Prueba para ingresar una aeronave");
		Expediente expediente = new Expediente(new Long(128), "0");
		Calidad cal = new Calidad(new Long(1));
		
		Aeronave eq = new Aeronave();
		
		eq.setAlmacen(new Almacen(1L));
		eq.setDescripcion("desc");
		eq.setFechaCreacionElemento(new Date());
		eq.setExpediente(expediente);
		eq.setCalidad(cal);
		eq.setValorByTipoObjetoVal(new Valor(Objetos.AERONAVE.getValorId()));
		eq.setTipoElemento(new Valor(Elementos.OBJETO.getValorId()));
		eq.setValorByCondicionFisicaVal(new Valor(Elementos.OBJETO.getValorId()));
		Long idEquipoComputo = this.daoServcice.create(eq) 		;
		assertTrue("El resultado debe ser mayor a 0 : ",idEquipoComputo>0);

	}


}

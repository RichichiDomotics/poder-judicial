/**
* Nombre del Programa : DocumentoOficialDAOImplTest.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 17 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba para el DAO de Obra de Arte
* * Programa Dependiente  :N/A
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

import mx.gob.segob.nsjp.dao.objeto.DocumentoOficialDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Almacen;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.DocumentoOficial;
import mx.gob.segob.nsjp.model.Expediente;

/**
 * Prueba para el DAO de Obra de Arte
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class DocumentoOficialDAOImplTest  extends BaseTestPersistencia<DocumentoOficialDAO> {
	
	public void ingresarEquipoComputo(){
		logger.debug("Prueba para ingresar un documento oficial");
		Expediente expediente = new Expediente(new Long(1), "0");
		Calidad cal = new Calidad(new Long(1));
		
		DocumentoOficial docOf = new DocumentoOficial();
		
		docOf.setAlmacen(new Almacen(1L));
		docOf.setDescripcion("desc");
		docOf.setFechaCreacionElemento(new Date());
		docOf.setExpediente(expediente);
		docOf.setCalidad(cal);
		
		Long idEquipoComputo = this.daoServcice.create(docOf) 		;
		assertTrue("El resultado debe ser mayor a 0 : ",idEquipoComputo>0);

	}



}

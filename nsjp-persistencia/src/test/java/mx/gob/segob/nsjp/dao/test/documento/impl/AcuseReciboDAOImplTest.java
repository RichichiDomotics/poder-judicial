/**
 * Nombre del Programa : AcuseReciboDAOImplTest.java
 * Autor                            : rgama
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                   
 * Marca de cambio        : N/A
 * Descripcion General    : Describir el objetivo de la clase de manera breve
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
package mx.gob.segob.nsjp.dao.test.documento.impl;

import java.util.Date;

import mx.gob.segob.nsjp.dao.documento.AcuseReciboDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.AcuseRecibo;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.Solicitud;
import mx.gob.segob.nsjp.model.Valor;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author rgama
 * 
 */
public class AcuseReciboDAOImplTest extends 
	BaseTestPersistencia<AcuseReciboDAO> {

	public void testConsultarUltimoAcuse () {
		Long respuesta = daoServcice.consultarUltimoAcuse();
		assertNotNull("El identificador no puede ser nulo ", respuesta); 
		System.out.println("El max id encontrado es " + respuesta);
	}
	
	
	public void testGenerarAcuseRecibo () {
		AcuseRecibo loAcuseRecibo = new AcuseRecibo();
		loAcuseRecibo.setFechaAcuse(new Date());
		loAcuseRecibo.setNombreRecibe("AcuseDeRecibo");
		Solicitud loSolicitud = new Solicitud();
		loSolicitud.setDocumentoId(5L);
		loAcuseRecibo.setSolicitud(loSolicitud);
		
		//Campos obligatorios
		loAcuseRecibo.setNombreDocumento("Acuse de recibo");
		loAcuseRecibo.setFechaCreacion(new Date());
		loAcuseRecibo.setForma(new Forma(1L));
		loAcuseRecibo.setTipoDocumento(new Valor(1780L));//Acuse
		loAcuseRecibo.setFolioDocumento("2");
		

		
		Long idSolicitudDef = daoServcice.create(loAcuseRecibo);
		assertTrue("El id del objeto debe ser mayor a 0 ", idSolicitudDef>0);
	}

}


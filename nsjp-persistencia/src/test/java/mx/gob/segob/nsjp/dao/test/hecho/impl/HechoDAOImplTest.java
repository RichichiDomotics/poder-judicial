/**
 * Nombre del Programa : HechoDAOImplTest.java
 * Autor                            : adrian
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 11/07/2011
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
package mx.gob.segob.nsjp.dao.test.hecho.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.hecho.HechoDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Hecho;
import mx.gob.segob.nsjp.model.Lugar;
import mx.gob.segob.nsjp.model.Tiempo;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author adrian
 * 
 */
public class HechoDAOImplTest extends BaseTestPersistencia<HechoDAO> {

	/**
	 * Test method for
	 * {@link mx.gob.segob.nsjp.dao.hecho.impl.HechoDAOImpl#consultarHechos(mx.gob.segob.nsjp.model.Hecho)}
	 * .
	 */
	public void testConsultarHechos() {
		// ID 30 exp 638 Lugar 1119 Tiempo 34

		Tiempo tiempo = null;
		Expediente expediente = null;
		Lugar lugar = null;
		Hecho hecho = new Hecho(30L, tiempo, expediente, lugar);
		List<Hecho> hechos = daoServcice.consultarHechos(hecho);
		if (hechos == null)
			assertTrue("Lista vacia",false);
		else{
			logger.info("Existen " + hechos.size() + " hechos");
			for (Hecho hch : hechos) {
				logger.info("-------------------------");
				logger.info("ID  : "+hch.getHechoId());
				logger.info("Exp : "+hch.getExpediente().getExpedienteId());
				logger.info("Lug : "+hch.getLugar().getElementoId());
				logger.info("Fech: "+hch.getTiempo().getTiempoId());
				logger.info("Desc: "+hch.getDescNarrativa());
				
			}
		}
			
	}

}

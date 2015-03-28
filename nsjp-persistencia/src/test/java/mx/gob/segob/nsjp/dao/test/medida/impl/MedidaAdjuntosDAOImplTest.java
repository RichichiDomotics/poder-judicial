/**
* Nombre del Programa : MedidaAdjuntosDAOImplTest.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 29 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Pruebas unitarias para metodos de acceso a datos de la entidad MedidaAdjuntos
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
package mx.gob.segob.nsjp.dao.test.medida.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.medida.MedidaAdjuntosDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.ArchivoDigital;

/**
 * Pruebas unitarias para metodos de acceso a datos de la entidad MedidaAdjuntos.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class MedidaAdjuntosDAOImplTest extends
		BaseTestPersistencia<MedidaAdjuntosDAO> {
	
	public void testConsultarArchivosDigitalesPorMedida () {
		List<ArchivoDigital> respuesta = daoServcice.consultarArchivosDigitalesPorMedida(new Long(29));
		
		assertTrue("La lista debe tener minimo un registro", respuesta.size()>0);
		logger.info("La lista debe tener minimo un registro" + respuesta.size());
	}
	
}


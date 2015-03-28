/**
* Nombre del Programa : SolicitudTrasladoImputadoDAOImplTest.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 21 Sep 2011
* Marca de cambio        : N/A
* Descripcion General    : Pruebas unitaria de metodos de acceso a datos de SolicitudTrasladoImputado
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
package mx.gob.segob.nsjp.dao.test.solicitud.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudTrasladoImputadoDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.SolicitudTrasladoImputado;

/**
 * Pruebas unitaria de metodos de acceso a datos de SolicitudTrasladoImputado.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class SolicitudTrasladoImputadoDAOImplTest extends
BaseTestPersistencia<SolicitudTrasladoImputadoDAO> {

	public void testConsultarSolicitudesTrasladosImputadoByEstatus () {
		List<SolicitudTrasladoImputado> respuesta = daoServcice.consultarSolicitudesTrasladosImputadoByEstatus(EstatusSolicitud.ABIERTA.getValorId());
	
		assertNotNull(respuesta);
		assertTrue("La lista debe tener minimo un registro ",respuesta.size()>0);
		logger.info("La lista debe tener minimo un registro "+respuesta.size());
	}
	
}

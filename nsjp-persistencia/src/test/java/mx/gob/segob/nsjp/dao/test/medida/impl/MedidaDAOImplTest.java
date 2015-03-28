/**
* Nombre del Programa : MedidaDAOImplTest.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 09/08/2011
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
package mx.gob.segob.nsjp.dao.test.medida.impl;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.dao.medida.MedidaDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Involucrado;

/**
 * Clase para generar pruebas unitarias de los medotos de MedidaDAO.
 * @version 1.0
 * @author GustavoBP
 *
 */
public class MedidaDAOImplTest extends BaseTestPersistencia<MedidaDAO> {

	public void testConsultarIdInvolucradosMedidasTipoPorFechas(){
		logger.debug("Prueba para recuperar los id de involucrados asociados a una Medida Alterna");
		
		Date fechaFin = new Date();
		Long esMedidaAlterna = 0L;
		Long cFuncionario = 2L;
		
		List<Involucrado> idInvolucrados = daoServcice.consultarIdInvolucradosMedidasTipoPorFechas(cFuncionario, esMedidaAlterna, fechaFin);
		assertTrue("La lista debe traer almenos un registro ", idInvolucrados.size()>0);
		logger.info("#Involucrados : " + idInvolucrados.size());
		for (Involucrado involucrado : idInvolucrados) {
			logger.info("ID:"+involucrado.getElementoId());
			if(involucrado.getCalidad()!= null)
				logger.info("calidad:"+involucrado.getCalidad().getCalidadId());
			if(involucrado.getExpediente()!=null){
				logger.info("expediente:"+involucrado.getExpediente().getExpedienteId());
			}
		}
//		String lista = idInvolucrados.toString();
//		logger.info("Lista:"+lista.substring(1, lista.length()-1));
	}
}

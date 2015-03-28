/**
* Nombre del Programa : IncidenciaDAOImplTest.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 10/08/2011
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

import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.dao.medida.IncidenciaDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.Incidencia;
import mx.gob.segob.nsjp.model.Medida;
import mx.gob.segob.nsjp.model.Valor;

/**
 * Clase para generar pruebas unitarias de los medotos de IncidenciaDAO.
 * @version 1.0
 * @author GustavoBP
 *
 */
public class IncidenciaDAOImplTest extends BaseTestPersistencia<IncidenciaDAO>{
	
	public void testIngresarIncidencias(){
		//Consultar el ID de Medida que se encuentre en BD
		Long medidaId = 272L;
		Long formaId=  4L;
		Long tipoDocumento = TipoDocumento.MEDIDA.getValorId(); 
		
		Incidencia incidencia = new Incidencia();
		incidencia.setMedida(new Medida(medidaId));
		incidencia.setReporte("Reporte de la Incidencia "+ medidaId);
		
		//datos de documentos
		incidencia.setFechaCreacion(new Date());
		incidencia.setForma(new Forma(formaId));
		incidencia.setNombreDocumento("Incidencia de Medida");
		incidencia.setTipoDocumento(new Valor(tipoDocumento));
		
		Long incidenciaId = daoServcice.create(incidencia);
		assertNotNull("Id Incidencia:", incidenciaId);
		logger.info("Id Incidencia:" + incidenciaId);
	}
	
	public void testConsultarIncidenciaPorMedida(){
		logger.debug("Prueba para recuperar las incidencias de una Medida");
		Long idMedida = 272L ;
		
		List<Incidencia> incidencias = daoServcice.consultarIncidenciaPorMedida(idMedida);
		//assertTrue("La lista debe traer almenos un registro ", incidencias.size()>0);
		logger.info("#incidencias : " + incidencias.size());
		for (Incidencia incidencia : incidencias) {
			logger.info("ID:"+incidencia.getDocumentoId());
			logger.info("Reporte:"+incidencia.getReporte());
			logger.info("Medida:"+incidencia.getMedida());
		}
		
	}

}

/**
* Nombre del Programa : SentenciaDAOImplTest.java
* Autor                            : AntonioBV
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 26/01/2012
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
package mx.gob.segob.nsjp.dao.test.sentencia.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.sentencia.SentenciaDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.NombreDemografico;
import mx.gob.segob.nsjp.model.Sentencia;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public class SentenciaDAOImplTest  extends BaseTestPersistencia<SentenciaDAO> {
    	
	public void testConsultarSentenciaPorId() {
    	
    	Sentencia sentencia = new Sentencia();
    	sentencia.setSentenciaId(2L);
    	sentencia.setBcumplida(null);
		try {
			sentencia = daoServcice.consultarSentenciaPorId(sentencia);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        assertNotNull(sentencia.getBcumplida());
    }	

	public void testConsultarNUS() {
    	
		
		NombreDemografico nombreDemografico = new NombreDemografico();
		nombreDemografico.setCurp("DETF930401HMCXLL");	
		List<Sentencia> lstSentencia = null;
		try {
			lstSentencia = daoServcice.consultarNUS(nombreDemografico, true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        assertNotNull(lstSentencia);
        assertTrue(lstSentencia.isEmpty());
    }	
}


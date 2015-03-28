/**
* Nombre del Programa : ValorDAOImplTest.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 29/06/2011
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
package mx.gob.segob.nsjp.dao.test.catalogo.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.catalogo.ValorDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
public class ValorDAOImplTest extends BaseTestPersistencia<ValorDAO> {

//	/**
//	 * Test method for {@link mx.gob.segob.nsjp.dao.catalogo.impl.ValorDAOImpl#consultarComplejidadTipoAudiencia(mx.gob.segob.nsjp.model.Valor)}.
//	 */
//	public void testConsultarComplejidadTipoAudiencia() {
//		Valor tipo=new Valor(2021L,"Juicio Oral",1570L,"Tipo Audiencia",false);
//		Valor compl = daoServcice.consultarComplejidadTipoAudiencia(tipo);
//		if(compl!=null)
//			logger.info("EXITO: la complejidad es: "+compl.getValor()+" con ID: "+compl.getValorId());
//		else
//			assertTrue("FALLO: consultarComplejidadTipoAudiencia", false);
//	}
	
	public void testActualizarComplejidadTipoAudiencia(){
		daoServcice.actualizarComplejidadTipoAudiencia(1315L, 2L);
	}
	   public void testObtenrNombres(){
	        List<String> resp = daoServcice.obtenerNombresColumnas(3L);
	    }

}

/**
* Nombre del CatCurso : CatCursoDAOImplTest.java
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
package mx.gob.segob.nsjp.dao.test.programa.impl;

import java.util.List;

import mx.gob.segob.nsjp.dao.programa.CatCursoDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.CatCategoriaCurso;
import mx.gob.segob.nsjp.model.CatCurso;
import mx.gob.segob.nsjp.model.CatTipoCurso;
import mx.gob.segob.nsjp.model.CatTipoNivelAcademico;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public class CatCursoDAOImplTest  extends BaseTestPersistencia<CatCursoDAO> {
    
	
	public void testConsultarCatCursos() {
    	
    	List<CatCurso> lstResultado = null;;
		try {
			lstResultado = daoServcice.consultarCatCurso();
			System.out.println(lstResultado);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        assertNotNull(lstResultado);
        
    }
    	
	
	public void testInsertarCatCurso() {
    	Long resultado = null;
    	CatCurso catCurso = new CatCurso();
    	CatTipoCurso catTipoCurso = new CatTipoCurso();
    	catTipoCurso.setCatTipoCursoId(1L);
    	catTipoCurso.setCdescripcion("panda");
    	CatCategoriaCurso catCategoriaCurso = new CatCategoriaCurso();
    	catCategoriaCurso.setCategoriaCursoId(1L);
    	CatTipoNivelAcademico catTipoNivelAcademico  = new CatTipoNivelAcademico();
    	catTipoNivelAcademico.setCatTipoNivelAcademicoId(1L);
    	catCurso.setCatTipoCurso(catTipoCurso);
    	catCurso.setCatCategoriaCurso(catCategoriaCurso);
    	catCurso.setCatTipoNivelAcademico(catTipoNivelAcademico);
    	catCurso.setCatCursoId(1L);
    	catCurso.setCnombre("Curso 1");
    	catCurso.setCdescripcion("Curso 1");
    	catCurso.setCduracion("5 años");
    	catCurso.setIpuntos(1983L);
		try {
			
			
			daoServcice.create(catCurso);
			daoServcice.flush();
			System.out.println(resultado);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        assertNotNull(resultado);
        
    }
    
	public void testEliminarCatCurso() {
    	
    	CatCurso catCurso = new CatCurso();
    	catCurso.setCatCursoId(1L);
    	catCurso.setBActivo(Boolean.FALSE);
    	boolean resultado = false;
		try {

			resultado = daoServcice.eliminarCursoPorId(catCurso);
			daoServcice.flush();
			System.out.println(resultado);
		} catch (Exception e) {
			e.printStackTrace();
}
        
        assertTrue(resultado);
        
    }	
	
	
}

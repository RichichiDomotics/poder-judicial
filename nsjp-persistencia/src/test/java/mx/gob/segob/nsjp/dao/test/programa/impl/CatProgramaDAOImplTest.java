/**
* Nombre del Programa : ProgramaDAOImplTest.java
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

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.dao.programa.CatProgramaDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.dto.programas.CatProgramaDTO;
import mx.gob.segob.nsjp.dto.programas.CatTipoProgramaDTO;
import mx.gob.segob.nsjp.model.CatCurso;
import mx.gob.segob.nsjp.model.CatPrograma;
import mx.gob.segob.nsjp.model.CatTrabajo;
import mx.gob.segob.nsjp.model.CentroDetencion;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public class CatProgramaDAOImplTest  extends BaseTestPersistencia<CatProgramaDAO> {
    
	
	public void testConsultarProgramas() {
    	
    	List<CatPrograma> lstResultado = null;;
		try {
			lstResultado = daoServcice.consultarProgramas();
			
			for (CatPrograma catPrograma : lstResultado) {
				System.out.println(catPrograma.getCnombre());
					for (CatCurso catCurso : catPrograma.getCatCursos()) {
						System.out.println("\t c --" + catCurso.getCnombre());		
					}
					for (CatTrabajo catTrabajo : catPrograma.getCatTrabajos()) {
						System.out.println("\t t --" + catTrabajo.getCnombre());		
					}				
					
					for (CentroDetencion centroDetencion : catPrograma.getCentroDetencions()) {
						System.out.println("\t c --" + centroDetencion.getNombre());		
					}				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        assertNotNull(lstResultado);
        
    }
    
	public void testInsertarPrograma() {
    	
    	CatProgramaDTO obj = new CatProgramaDTO();
    	obj.setDescripcion("algo");
    	obj.setNombre("nombre");
    	obj.setFechaFinPrograma(new Date());
    	obj.setFechaInicioPrograma(new Date());
    	
    	CatTipoProgramaDTO catTipoProgramaDTO = new CatTipoProgramaDTO();
    	catTipoProgramaDTO.setCatTipoProgramaId(1L);
    	obj.setCatTipoProgramaDTO(catTipoProgramaDTO);    	
    	
    	
    	Long id = null;
		try {
			//id = daoServcice.create(obj);
		} catch (Exception e) {
			e.printStackTrace();
}
        
        assertNotNull(id);
        
    }	
	
	
	
	public void testConsultarProgramaPorId() {
    	
    	CatPrograma catPrograma = new CatPrograma();
    	catPrograma.setCatProgramaId(16L);
    	catPrograma.setCnombre(null);
		try {
			catPrograma = daoServcice.consultarProgramaPorId(catPrograma);
			
			System.out.println(catPrograma.getCnombre());
			for (CatCurso catCurso : catPrograma.getCatCursos()) {
				System.out.println("\t c --" + catCurso.getCnombre());		
			}
			for (CatTrabajo catTrabajo : catPrograma.getCatTrabajos()) {
				System.out.println("\t t --" + catTrabajo.getCnombre());		
			}				
			
			for (CentroDetencion centroDetencion : catPrograma.getCentroDetencions()) {
				System.out.println("\t c --" + centroDetencion.getNombre());		
			}				
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        assertNotNull(catPrograma.getCnombre());
        
    }
	
	public void testActualizarProgramaPorId() {
    	
    	CatPrograma catPrograma = new CatPrograma();
    	catPrograma.setCatProgramaId(16L);
    	catPrograma.setCnombre(null);
		try {
			
			catPrograma = daoServcice.consultarProgramaPorId(catPrograma);
			catPrograma.setCnombre("Panda");
			daoServcice.merge(catPrograma);
			//daoServcice.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        assertNotNull(catPrograma.getCnombre());
        
    }	
	
	public void testConsultarProgramaPorCentroDetencion() {
    	
    	CentroDetencion cd = new CentroDetencion();
    	cd.setCentroDetencionId(4L);
		try {
			List<CatPrograma> catProgramas = daoServcice.consultarProgramasPorCentroDetencion(cd);
			
			for (CatPrograma catPrograma : catProgramas){
				System.out.println(catPrograma.getCnombre());
				for (CatCurso catCurso : catPrograma.getCatCursos()) {
					System.out.println("\t c --" + catCurso.getCnombre());		
				}
				for (CatTrabajo catTrabajo : catPrograma.getCatTrabajos()) {
					System.out.println("\t t --" + catTrabajo.getCnombre());		
				}				
				
				for (CentroDetencion centroDetencion : catPrograma.getCentroDetencions()) {
					System.out.println("\t c --" + centroDetencion.getNombre());		
				}
			}
			
	        assertNotNull(catProgramas);
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}


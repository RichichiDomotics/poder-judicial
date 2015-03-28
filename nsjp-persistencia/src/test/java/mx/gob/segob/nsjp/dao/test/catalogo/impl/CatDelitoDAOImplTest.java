/**
 * Nombre del Programa : CatDelitoDAOImplTest.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 2 Jun 2011
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

import mx.gob.segob.nsjp.dao.catalogo.CatDelitoDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.CatDelito;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class CatDelitoDAOImplTest extends BaseTestPersistencia<CatDelitoDAO> {
    public void testConsultarTodos() {
        List<CatDelito> data = daoServcice.consultarTodos();
        logger.debug("data.size() :: "+data.size());
    }

    public void testExpediente(){
    	String idsGrid="";
    	List<CatDelito> data = daoServcice.consultarDelitosSinIdsGrid(idsGrid);    	
    	logger.info(data.size());
    	for (CatDelito catDelito : data) {
			logger.info(" Delito:"+ catDelito.getNombre());
			logger.info(" Delito:"+ catDelito.getClaveDelito() );			
		}    
    }
    
    public void testConsultarDelitoPorGravedad(){
    	CatDelito gravedad = new CatDelito();
    	gravedad.setEsGrave(true);
    	List<CatDelito> data = daoServcice.consultarDelitoPorGravedad(gravedad);
    	logger.debug("data.size() :: "+data.size());
 
    }
    public void testConsultarDelitoPorFilro(){
    	CatDelito catDelitoFiltro= new CatDelito();
//    	catDelitoFiltro.setCatDelitoId(1L);
//    	catDelitoFiltro.setClaveDelito("1");
//    	catDelitoFiltro.setNombre("Violación");
//    	catDelitoFiltro.setEsGrave(false);
    	catDelitoFiltro.setEsAccionPenPriv(true);
    	
    	
    	List<CatDelito> data = daoServcice.consultarCatDelitoPorFilro(catDelitoFiltro);
    	logger.debug("data.size() :: "+data.size());
    	for (CatDelito catDelito : data) {
			logger.info(" Delito:"+ catDelito.getCatDelitoId() );
			logger.info(" Delito:"+ catDelito.getNombre() );
			logger.info(" Delito:"+ catDelito.getClaveDelito() );
			logger.info(" Delito:"+ catDelito.getEsGrave());
			logger.info(" Delito:"+ catDelito.getEsAccionPenPriv() );
		}
    	logger.debug("data.size() :: "+data.size());
    }   
    
    public void testconsultarCatDelitoPorFiltro(){
    	CatDelito filtro = new CatDelito();
    	filtro.setClaveDelito("23");
    	CatDelito data = daoServcice.consultarCatDelitoPorFiltro(filtro);
    	logger.debug("Clave del delito :: "+data.getClaveDelito());
 
}
    
    public void consultarNumeroDelitosPorCatDelitoId(){
    	CatDelito catDelito = new CatDelito();
    	catDelito.setCatDelitoId(2L);
    	Long numero = 0L;
    	
    	numero = daoServcice.consultarNumeroDelitosPorCatDelitoId(catDelito);
	    
	    logger.debug("ENCONTRADOS :: "+numero);
    }
}

/**
 * Nombre del Programa : SalaAudienciaDAOImplTest.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 10 Jun 2011
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
package mx.gob.segob.nsjp.dao.test.audiencia.impl;

import java.util.Calendar;
import java.util.Date;

import mx.gob.segob.nsjp.dao.audiencia.SalaAudienciaDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.SalaAudiencia;
import mx.gob.segob.nsjp.model.SalaJAVS;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class SalaAudienciaDAOImplTest
        extends
            BaseTestPersistencia<SalaAudienciaDAO> {

    public void testExisteDisponibilidad() {
        final boolean existe = daoServcice.existeDisponibilidad(1L, new Date(),
                420L);
        assertTrue("Debe existir ", existe);
    }

    public void testExisteDisponibilidadEnLimite() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, 6);
        cal.set(Calendar.DATE, 10);
        final boolean existe = daoServcice.existeDisponibilidad(1L,
                cal.getTime(), 420L);
        assertFalse("No Debe existir ", existe);
    }

    public void testExisteDisponibilidadMenorAlLimite() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, 6);
        cal.set(Calendar.DATE, 10);
        final boolean existe = daoServcice.existeDisponibilidad(1L,
                cal.getTime(), 421L);
        assertTrue("No Debe existir ", existe);
    }

    public void testExisteDisponibilidadMayorAlLimite() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, 6);
        cal.set(Calendar.DATE, 10);
        final boolean existe = daoServcice.existeDisponibilidad(1L,
                cal.getTime(), 90L);
        assertFalse("No Debe existir ", existe);

    }
    public void testCount() {
        Long salas = daoServcice.consultarNumeroSalas();
        logger.debug("cantidad de salas :: "+ salas);
    }
    
    
    public void testCreate(){
        SalaAudiencia sa = new SalaAudiencia();
        sa.setSalaAudienciaId(2L);
        sa.setNombreSala("Nueva");
        sa.setDomicilioSala("Eje central # 3");
        sa.setUbicacionSala("PB");
        sa.setEsActivo(Boolean.TRUE);
        daoServcice.create(sa);
    }

    public void testConsultarSalaAudiencia(){
    	SalaAudiencia salaAudiencia = daoServcice.read(1L);
    	assertNotNull("Objeto no encontrado",salaAudiencia);
    	logger.info("SalaAudiencia: " +  salaAudiencia.getSalaAudienciaId());
    	logger.info("SalaAudiencia: " +  salaAudiencia.getNombreSala());
    	logger.info("SalaAudiencia: " +  salaAudiencia.getUbicacionSala());
    	logger.info("SalaAudiencia: " +  salaAudiencia.getEncargado());
    	logger.info("SalaAudiencia: " +  salaAudiencia.getEsActivo());
    	logger.info("SalaAudiencia: " +  salaAudiencia.getSalaJAVS());
    	if(salaAudiencia.getSalaJAVS()!= null){
    		SalaJAVS salaJavs = salaAudiencia.getSalaJAVS();
    		logger.info(" SalaJAVS:"+ salaJavs.getSalaJAVSId());
    		logger.info(" SalaJAVS:"+ salaJavs.getDireccionIP());
    		logger.info(" SalaJAVS:"+ salaJavs.getPassword());
    		logger.info(" SalaJAVS:"+ salaJavs.getUsuario());
    	}
//    	logger.info("SalaAudiencia: " +  salaAudiencia.getCatDiscriminante());
    	
    	
    }
}

package mx.gob.segob.nsjp.dao.test.elemento.impl;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.dao.catalogo.ValorDAO;
import mx.gob.segob.nsjp.dao.elemento.ElementoDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Elemento;
import mx.gob.segob.nsjp.model.Valor;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

public class ElementoDAOImplTest extends BaseTestPersistencia<ElementoDAO> {
    @Autowired
    private ValorDAO valDao;
    @Autowired
    private ElementoDAO eleDao;
    public void testElminarElementos() {
        List<Elemento> els = daoServcice.findElementosByExpedienteId(222L);
        logger.debug("Elementos encontrados :: " + els.size());
//        daoServcice.deleteAll(els);
        logger.debug("[OK]");
        
    }

    public void testMapeo() {
        try {
            Class[] pojosHB = getClasses("mx.gob.segob.nsjp.model");
            logger.debug("pojosHB.length :: " + pojosHB.length);

            Session ses = daoServcice.getHBSession();
            Class unPojo = null;
            int pos = 0;
            while (pos < pojosHB.length) {
                unPojo = pojosHB[pos];
                logger.debug("probando mapeo de " + unPojo + " " +  pos +" de " + pojosHB.length);
                if (!unPojo.getSimpleName().endsWith("Id") && !unPojo.getSimpleName().equals("Asentamiento")) {
                    ses.createQuery("from " + unPojo.getSimpleName()).list();
                }
                pos++;
            }

        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
    /**
     * Scans all classes accessible from the context class loader which belong
     * to the given package and subpackages.
     * 
     * @param packageName
     *            The base package
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
     */
    private Class[] getClasses(String packageName)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread()
                .getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<Class>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[classes.size()]);
    }

    /**
     * Recursive method used to find all classes in a given directory and
     * subdirs.
     * 
     * @param directory
     *            The base directory
     * @param packageName
     *            The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
     */
    private List<Class> findClasses(File directory, String packageName)
            throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file,
                        packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName
                        + '.'
                        + file.getName().substring(0,
                                file.getName().length() - 6)));
            }
        }
        return classes;
    }
    
    
    public void _testConsultarElementosPorCalidad () {
		logger.info("Prueba unitaria para consultar Elementos Por Calidad");						
		List<Elemento> respuesta = daoServcice.consultarElementosPorCalidad("EXP0000INST00GAM11", Calidades.PROBABLE_RESPONSABLE_PERSONA);
		assertFalse("La lista debe tener registros de elementos ", respuesta.isEmpty());		
		logger.info("Elementos recuperados : " + respuesta.size());		
	}
    
    public void testValor(){
        Valor val = valDao.read(1L);
        logger.debug("val.getValor() ::::: "+val.getValor());
        logger.debug("val.getValorId() ::: "+val.getValorId());
        logger.debug("getCampoCatalogo() :: "+val.getCampoCatalogo());
    }
    
}
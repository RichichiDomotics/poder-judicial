/**
 * 
 */
package mx.gob.segob.nsjp.dao.test.base;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.ParameterizedType;
import java.net.URISyntaxException;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit38.AbstractJUnit38SpringContextTests;
import org.springframework.util.Log4jConfigurer;

/**
 * @author vaguirre
 * 
 */
@ContextConfiguration(locations = {"/dataSource-test-context.xml",
        "/sessionFactory-nsjp-context.xml", "/daos-nsjp-context.xml"})
public abstract class BaseTestPersistencia<T>
        extends
            AbstractJUnit38SpringContextTests {

    static {
        try {
            Log4jConfigurer
                    .initLogging("../nsjp-persistencia/src/test/resources/log4j-config-unitTesting.xml");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tipo del DAO.
     */
    private Class<T> type = null;
    /**
     * Clase bajo prueba.
     */
    protected T daoServcice;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        try {
            daoServcice = super.applicationContext.getBean(getType());
        } catch (Exception e) {
            logger.error("Error al recuperar el DAO del contexto de Spring");
            logger.error(e.getMessage());
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    protected Class<T> getType() {
        if (type == null) {
            Class clazz = getClass();

            while (!(clazz.getGenericSuperclass() instanceof ParameterizedType)) {
                clazz = clazz.getSuperclass();
            }

            type = (Class<T>) ((ParameterizedType) clazz.getGenericSuperclass())
                    .getActualTypeArguments()[0];
        }

        return type;
    }
    /**
     * Convierte un archivo a un arreglo de bytes.
     * 
     * @param archivo
     *            archivo a convertir.
     * @return byte[]
     */
    protected byte[] obtenerArchivo(final String archivo) {
        byte[] arrayByte = null;
        try {
            logger.debug("Inicia Conversion de Arreglo de Bytes");
            InputStream itStrm = null;
            File file;
            file = new File(super.getClass().getResource(archivo).toURI());
            itStrm = new FileInputStream(file);
            DataInputStream dataInputStream = new DataInputStream(itStrm);
            arrayByte = new byte[(int) file.length()];
            dataInputStream.read(arrayByte);
            dataInputStream.close();
            logger.debug("Termina conversion de Arreglo de Bytes");
        } catch (URISyntaxException e) {
            logger.error("Error al obtener la ruta del file", e);
            fail(e.getMessage());
        } catch (FileNotFoundException e) {
            logger.error("Error al obtener el file", e);
            fail(e.getMessage());
        } catch (IOException e) {
            logger.error("Error al generar arreglo de bytes", e);
            fail(e.getMessage());
        }
        return arrayByte;
    }

    protected void escribirArchivo(byte[] contenido, String ruta) {
        OutputStream out = null;
        try {
            out = new FileOutputStream(ruta);
            out.write(contenido);
        } catch (FileNotFoundException e) {
            logger.error("Error al obtener el file", e);
        } catch (IOException e) {
            logger.error("Error al obtener el file", e);
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

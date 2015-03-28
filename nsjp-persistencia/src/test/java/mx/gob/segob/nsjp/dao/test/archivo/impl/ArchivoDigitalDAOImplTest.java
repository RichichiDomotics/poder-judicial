/**
 * Nombre del Programa : ArchivoDigitalDAOImplTest.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 18 May 2011
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
package mx.gob.segob.nsjp.dao.test.archivo.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.archivo.ArchivoDigitalDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.ArchivoDigital;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class ArchivoDigitalDAOImplTest
        extends
            BaseTestPersistencia<ArchivoDigitalDAO> {
    /**
     * 
     */
    public void testInsertarVacio() {
        ArchivoDigital ad = new ArchivoDigital();
        ad.setNombreArchivo("docVacio.pdf");
        ad.setTipoArchivo("pdf");
        ad.setContenido(obtenerArchivo("/archivos/docVacio.pdf"));
        Long id = daoServcice.create(ad);
        assertNotNull("No puede ser nulo el nuevo ID", id);
        assertTrue("El Id debe ser mayor a cero", (id.longValue() > 0));
    }
    /**
     * 
     */
    public void testInsertarBJ() {
        ArchivoDigital ad = new ArchivoDigital();
        ad.setNombreArchivo("d2.pdf");
        ad.setTipoArchivo("pdf");
        ad.setContenido(obtenerArchivo("/archivos/d2.pdf"));
        Long id = daoServcice.create(ad);
        assertNotNull("No puede ser nulo el nuevo ID", id);
        assertTrue("El Id debe ser mayor a cero", (id.longValue() > 0));
    }
    /**
     * 
     */
    public void testObtener() {
        ArchivoDigital ad = new ArchivoDigital();
        ad.setNombreArchivo("docVacio.pdf");
        ad.setTipoArchivo("pdf");
        ad.setContenido(obtenerArchivo("/archivos/docVacio.pdf"));
        Long id = daoServcice.create(ad);
        ad = daoServcice.read(id);
        assertNotNull("No puede ser nulo el registro", ad);
        super.escribirArchivo(ad.getContenido(),
                "/Users/sawbona/NetBeansProjects/svn/B01_DesarrolloTransacciones/"
                        + ad.getNombreArchivo());
        daoServcice.delete(ad);
    }

    public void testConsultarPorArchivo() {

        try {
            byte[] contenido = daoServcice
                    .consultarContenidoPorDocumentoOArchivo(47L, true);
            assertNotNull("El contenido no debe ser nulo", contenido);
            logger.debug(contenido);
        } catch (NSJPNegocioException e) {
            logger.error(e);
            assertNull(e);
        }

    }
    public void testConsultarPorDocumento() {

        try {
            byte[] contenido = daoServcice
                    .consultarContenidoPorDocumentoOArchivo(282L, false);
            assertNotNull("El contenido no debe ser nulo", contenido);
            logger.debug(contenido);
        } catch (NSJPNegocioException e) {
            logger.error(e);
            assertNull(e);
        }

    }
    
    public void testConsultarArchivoDigitalXElementoId() {

        try {
            byte[] contenido = daoServcice.consultarArchivoDigitalXElementoId(256L);
            assertNotNull("El contenido no debe ser nulo", contenido);
            logger.debug(contenido);
        } catch (NSJPNegocioException e) {
            logger.error(e);
            assertNull(e);
        }

    }
    
    public void testConsultarArchivoDigitalPorDocumento(){
    	try {
			ArchivoDigital archivo = daoServcice.consultarArchivoDigitalPorDocumento(4L);
			assertNotNull(archivo);
			logger.info("Id: "+archivo.getArchivoDigitalId());
			logger.info("TipoArchivo: "+archivo.getTipoArchivo());
			logger.info("Nombre: "+archivo.getNombreArchivo());
			logger.info("Comentario: "+archivo.getComentario());
			logger.info("Tam Contenido: "+archivo.getContenido().length);
			logger.info("Docs: "+archivo.getDocumentos().size());
			logger.info("Solis: "+archivo.getSolicitudAdjuntoses().size());
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
    }
    
    

}

/**
 * Nombre del Programa : NotificacionDAOImplTest.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 1 Jun 2011
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
package mx.gob.segob.nsjp.dao.test.documento.impl;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dao.documento.NotificacionDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.Notificacion;
import mx.gob.segob.nsjp.model.Valor;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class NotificacionDAOImplTest
        extends
            BaseTestPersistencia<NotificacionDAO> {

    public void testConsultarNotificacionesPorAudienciaInvolucrado() {
        List<Notificacion> data = daoServcice
                .consultarNotificacionesPorAudienciaInvolucrado(1L, 1L);
        assertNotNull("La lista no puede ser nula", data);
    }

    public void testInsertar() {
        try {
            Notificacion niu = new Notificacion();
            niu.setConsecutivoNotificacion("1");
            niu.setForma(new Forma(1L));
            niu.setLugarCitado("Insurgentes sur # 3300, col. Florida, del. Alvaro obregón, México D.F CP 19000");
            niu.setNombreDocumento("Notificacion 17");            
            niu.setFechaCreacion(new Date());
            niu.setFechaCitado(DateUtils.obtener("12/08/2010"));
            niu.setTipoDocumento(new Valor(1L));

            niu.setInvolucrado(new Involucrado(454L));
            niu.setAudiencia(new Audiencia(1L));

            Long id = daoServcice.create(niu);
            assertNotNull("El ID no puede ser null", id);
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }
    }

}

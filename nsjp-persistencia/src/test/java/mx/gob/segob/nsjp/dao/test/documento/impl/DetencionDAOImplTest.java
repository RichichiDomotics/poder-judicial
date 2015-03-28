/**
 * Nombre del Programa : DetencionDAOImplTest.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 5 Aug 2011
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

import mx.gob.segob.nsjp.dao.involucrado.DetencionDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Detencion;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.Lugar;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class DetencionDAOImplTest extends BaseTestPersistencia<DetencionDAO> {
    public void testCreate() {
        Detencion pojo = new Detencion();
        pojo.setFechaInicioDetencion(new Date());
        pojo.setInvolucrado(new Involucrado(3296L));
//        pojo.setLugar(new Lugar(313L));
        pojo.setLugarDetencionProvisional("En al comisaria del pueblo");
       daoServcice.create(pojo);
    }
}

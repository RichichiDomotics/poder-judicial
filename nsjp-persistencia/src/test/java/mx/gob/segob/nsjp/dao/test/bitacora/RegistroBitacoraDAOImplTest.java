/**
* Nombre del Programa : RegistroBitacoraDAOImplTest.java
* Autor                            : vaguirre
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 17 Oct 2011
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
package mx.gob.segob.nsjp.dao.test.bitacora;

import mx.gob.segob.nsjp.dao.bitacora.RegistroBitacoraDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author vaguirre
 *
 */
public class RegistroBitacoraDAOImplTest extends BaseTestPersistencia<RegistroBitacoraDAO> {

    public void testConsultarByNumeroExpedienteId(){
        super.daoServcice.consultarByNumeroExpedienteId(1L);
    }
    
}

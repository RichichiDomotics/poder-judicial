/**
* Nombre del Programa : CorreoElectronicoDAOImplTest.java
* Autor                            : vaguirre
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 25 Oct 2011
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
package mx.gob.segob.nsjp.dao.test.persona.impl;

import mx.gob.segob.nsjp.dao.persona.CorreoElectronicoDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.CorreoElectronico;
import mx.gob.segob.nsjp.model.Funcionario;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author vaguirre
 *
 */
public class CorreoElectronicoDAOImplTest extends BaseTestPersistencia<CorreoElectronicoDAO> {

    
    public void testCreate() {
        CorreoElectronico pojo = new CorreoElectronico();
        pojo.setDireccionElectronica("fabiola.gutierrez@yahoo.com.mx");
        pojo.setFuncionario(new Funcionario(46L));
        super.daoServcice.create(pojo);
    }
}

/**
* Nombre del Programa : TelefonoDAOImplTest.java
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

import mx.gob.segob.nsjp.comun.enums.elemento.TipoTelefono;
import mx.gob.segob.nsjp.dao.persona.TelefonoDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.CorreoElectronico;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Telefono;
import mx.gob.segob.nsjp.model.Valor;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author vaguirre
 *
 */
public class TelefonoDAOImplTest extends BaseTestPersistencia<TelefonoDAO> {
    public void testCreate() {
        Telefono pojo = new Telefono();
        pojo.setCodigoArea("33");
        pojo.setCodigoPais("52");
        pojo.setNumeroTelefonico("2723576");
        pojo.setValor(new Valor(TipoTelefono.OFICINA.getValorId()));
        pojo.setFuncionario(new Funcionario(56L));
        super.daoServcice.create(pojo);
    }
}

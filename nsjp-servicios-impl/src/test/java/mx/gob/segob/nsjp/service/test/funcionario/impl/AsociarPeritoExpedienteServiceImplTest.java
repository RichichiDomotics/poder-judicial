/**
 * Nombre del Programa : AsociarPeritoExpedienteServiceImplTest.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 27-jun-2011
 * Marca de cambio        : N/A
 * Descripcion General    : N/A
 * Programa Dependient    :N/A
 * Programa Subsecuente   :N/A
 * Cond. de ejecucion     :N/A
 * Dias de ejecucion      :N/A                                Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                            :N/A
 * Compania                         :N/A
 * Proyecto                         :N/A                      Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.test.funcionario.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;
import mx.gob.segob.nsjp.service.funcionario.AsociarPeritoExpedienteService;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;
import mx.gob.segob.nsjp.service.test.TestUtilServiceImpl;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
public class AsociarPeritoExpedienteServiceImplTest
    extends BaseTestServicios<AsociarPeritoExpedienteService> {

    @Autowired
    private FuncionarioDAO funcionarioDao;


    public void testAsociarPeritoExpedienteService(){
        try {
            logger.info("Probando el servicio de: AsociarPeritoExpedienteService");
            assert service != null;
            Funcionario perito = TestUtilServiceImpl.nuevoFuncionarioPerito();
            funcionarioDao.saveOrUpdate(perito);
            if (logger.isDebugEnabled()) {
                logger.debug("Cosme guardado con id:  = " + perito.getClaveFuncionario());
            }
            String numeroExpedienteString = TestUtilServiceImpl.nuevoExpedienteUnico();
            Expediente expediente = new Expediente();
            expediente.setNumeroExpediente(numeroExpedienteString);
            service.asociarPeritoExpediente(FuncionarioTransformer.
                    transformarFuncionario(perito),
                    ExpedienteTransformer.transformaExpediente(expediente));
            Funcionario root = new Funcionario(1L);
            service.asociarPeritoExpediente(FuncionarioTransformer.
                    transformarFuncionario(root),
                    ExpedienteTransformer.transformaExpediente(expediente));
            funcionarioDao.delete(perito);
        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
            fail();
        }
    }
   
}

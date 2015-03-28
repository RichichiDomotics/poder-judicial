/**
 * Nombre del Programa : ConsultarFuncionarioXExpedienteServiceImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 24-jun-2011
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
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.service.funcionario.ConsultarFuncionarioXExpedienteService;
import mx.gob.segob.nsjp.service.test.TestUtilServiceImpl;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
public class ConsultarFuncionarioXExpedienteServiceImplTest
    extends BaseTestServicios<ConsultarFuncionarioXExpedienteService> {

    public void testConsultarFuncionarioXExpedienteService(){
        try {
            logger.info("Probando el servicio de: ConsultarFuncionarioXExpedienteService");
            assert service != null;
            String NUMERO_EXPEDIENTE = TestUtilServiceImpl.nuevoExpedienteUnico();
            FuncionarioDTO funcionarioDto = service.consultarFuncionarioXExpediente(NUMERO_EXPEDIENTE);
            assertNotNull("El funcionario asociado al numero de expediente " +
                    NUMERO_EXPEDIENTE + "no puede ser nulo", funcionarioDto);
            assertNotNull("funcionarioDto.getApellidoMaternoFuncionario()",
                    funcionarioDto.getApellidoMaternoFuncionario());
            assertNotNull("funcionarioDto.getApellidoPaternoFuncionario()",
                    funcionarioDto.getApellidoPaternoFuncionario());
//            assertNotNull("funcionarioDto.getCedula()", funcionarioDto.getCedula());
            assertNotNull("funcionarioDto.getNombreFuncionario()",
                    funcionarioDto.getNombreFuncionario());
        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
        }
    }
   
}

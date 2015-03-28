/**
 * Nombre del Programa : ConsultarPeritoPorNombreServiceImplTest.java
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

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.JerarquiaOrganizacional;
import mx.gob.segob.nsjp.service.funcionario.ConsultarPeritoPorNombreService;
import mx.gob.segob.nsjp.service.test.TestUtilServiceImpl;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
public class ConsultarPeritoPorNombreServiceImplTest
    extends BaseTestServicios<ConsultarPeritoPorNombreService> {

    @Autowired
    private FuncionarioDAO funcionarioDao;

    public void testConsultarPeritoPorNombreService(){
        Funcionario peritoUno = TestUtilServiceImpl.nuevoFuncionarioPerito();
        try {
            logger.info("Probando el servicio de: ConsultarPeritoPorNombreService");
            assert service != null;
            funcionarioDao.saveOrUpdate(peritoUno);
            FuncionarioDTO criterios = new FuncionarioDTO();
            criterios.setNombreFuncionario("Cos");
            if (logger.isDebugEnabled()) {
                logger.debug("Buscando a Cosme Fulanito........... = ");
            }
            List<FuncionarioDTO> peritosPorNombre = service.consultarPeritosPorNombre(criterios);
            assertNotNull("peritosPorNombre", peritosPorNombre);
            assertTrue(!peritosPorNombre.isEmpty());
            if (logger.isDebugEnabled()) {
                logger.debug("Se encontraron tantos numeros de funcionarios cosme:  = " + peritosPorNombre.size());
            }
            for (FuncionarioDTO funcionarioDto : peritosPorNombre) {
                if (logger.isDebugEnabled()) {
                    logger.debug("funcionarioDto.getNombreFuncionario() = " + funcionarioDto.getNombreFuncionario());
                    logger.debug("funcionarioDto.getApellidoPaternoFuncionario() = " + funcionarioDto.getApellidoPaternoFuncionario());
                    logger.debug("funcionarioDto.getApellidoMaternoFuncionario() = " + funcionarioDto.getApellidoMaternoFuncionario());
                }
            }
            if (logger.isDebugEnabled()) {
                logger.debug("Procedemos a eliminar los funcionarios cosme....... ");
            }
            for (FuncionarioDTO funcionarioDTO : peritosPorNombre) {
                Funcionario id = new Funcionario(funcionarioDTO.getClaveFuncionario());
                id.setArea(new JerarquiaOrganizacional(1L));
                funcionarioDao.delete(id);
            }
        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
        }
    }
   
}

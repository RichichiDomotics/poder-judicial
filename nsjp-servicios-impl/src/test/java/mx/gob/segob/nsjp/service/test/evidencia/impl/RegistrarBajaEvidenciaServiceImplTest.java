/**
 * Nombre del Programa : RegistrarBajaEvidenciaServiceImplTest.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 01-ago-2011
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
package mx.gob.segob.nsjp.service.test.evidencia.impl;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.service.evidencia.RegistrarBajaEvidenciaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
public class RegistrarBajaEvidenciaServiceImplTest
    extends BaseTestServicios<RegistrarBajaEvidenciaService> {

    public void testConsultaFuncionarioPorNombreInstitucionYPuesto(){
        try {
            logger.info("testConsultaFuncionarioPorNombreInstitucionYPuesto");
            FuncionarioDTO criterio = new FuncionarioDTO();
            criterio.setApellidoPaternoFuncionario("Ramirez");
            criterio.setApellidoMaternoFuncionario("Chavez");
            criterio.setNombreFuncionario("Eduaro");
            criterio.setNombreInstitucion("Procu");
            ValorDTO puesto = new ValorDTO();
                puesto.setValor("Coordinador de servicios periciales");
            criterio.setPuesto(puesto);
            FuncionarioDTO consultado = service.consultaFuncionarioPorNombreInstitucionYPuesto(criterio);
            if (logger.isDebugEnabled()) {
                logger.debug("consultado = " + consultado);
            }
        } catch (NSJPNegocioException ex) {
            Logger.getLogger(RegistrarBajaEvidenciaServiceImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void _testRegistrarBajaEvidenciaService(){
        try {
            logger.info("Probando el servicio de: RegistrarBajaEvidenciaService");
            assert service != null;
            LinkedList<EvidenciaDTO> evidenciasDto = new LinkedList<EvidenciaDTO>();
            EvidenciaDTO evidenciaDto = new EvidenciaDTO(1L);
            evidenciasDto.add(evidenciaDto);
            service.registrarBajaEvidencia(evidenciasDto);
        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
            fail("Ocurrio una excepcion al ejecutar el test RegistrarBajaEvidenciaService");
        }
    }

    public void testEliminarEvidencia(){
    	logger.info("Probando el servicio de: EliminarEvidencia");
    	
    	Long idEvidencia = 307L;
		try {
			Boolean esEliminado = service.eliminarEvidencia(idEvidencia );
			logger.info("Fue Eliminado:"+esEliminado);
			assertTrue("Fue Eliminado Exitosamente!!", esEliminado);
		} catch (NSJPNegocioException e) {
			logger.debug(e);
			fail("Ocurrio una excepcion al ejecutar el test EliminarEvidencia");
		}
    	
    }
    
}

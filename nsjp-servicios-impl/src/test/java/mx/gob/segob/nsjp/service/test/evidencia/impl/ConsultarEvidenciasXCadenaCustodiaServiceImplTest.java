/**
 * Nombre del Programa : ConsultarEvidenciasXCadenaCustodiaServiceImplTest.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 30-jun-2011
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

import java.util.List;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.evidencia.EslabonDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudPericialDTO;
import mx.gob.segob.nsjp.service.evidencia.ConsultarEvidenciasXCadenaCustodiaService;
import mx.gob.segob.nsjp.service.test.TestUtilServiceImpl;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
public class ConsultarEvidenciasXCadenaCustodiaServiceImplTest
        extends BaseTestServicios<ConsultarEvidenciasXCadenaCustodiaService> {

    public void testConsultarEvidenciasXCadenaCustodiaService() {
        logger.info("Probando el servicio de: ConsultarEvidenciasXCadenaCustodiaService");
        try {
            assert service != null;
            CadenaDeCustodiaDTO cadenaDeCustodiaDTO = new CadenaDeCustodiaDTO(154L);
            cadenaDeCustodiaDTO.setFolio(
                    TestUtilServiceImpl.folioCadenaDeCustodiaConEvidencias());
            List<EvidenciaDTO> consultarEvidenciasXCadenaCustodia =
                    service.consultarEvidenciasXCadenaCustodia(cadenaDeCustodiaDTO);
            assertNotNull("consultarEvidenciasXCadenaCustodia",
                    consultarEvidenciasXCadenaCustodia);
            for (EvidenciaDTO evidenciaDto : consultarEvidenciasXCadenaCustodia) {
                assertNotNull("evidenciaDTO", evidenciaDto);
                //* Identificador de la evidencia
                assertNotNull("evidenciaDto.getEvidenciaId()",
                        evidenciaDto.getEvidenciaId());
                //Información de la evidencia
                assertNotNull("evidenciaDto.getDescripcion()",
                        evidenciaDto.getDescripcion());
                //Hora del levantamiento de la evidencia
                assertNotNull("evidenciaDto.getFechaLevantamiento()",
                        evidenciaDto.getFechaLevantamiento());
                // Origen de la evidencia
                assertNotNull("evidenciaDto.getOrigenEvidencia()",
                        evidenciaDto.getOrigenEvidencia());
                /*
                 * Último eslabón asociado (Nota1) Generarlo como un campo
                 dentro de EvidenciaDTO.ultimoEslabon
                 * Número de eslabón
                 */
                if (logger.isDebugEnabled()) {
                    logger.debug("evidenciaDto.getEvidenciaId() = "
                            + evidenciaDto.getEvidenciaId());
                    logger.debug("evidenciaDto.getDescripcion(): " +
                            evidenciaDto.getDescripcion());
                    logger.debug("evidenciaDto.getFechaLevantamiento(): " +
                            evidenciaDto.getFechaLevantamiento());
                    logger.debug("evidenciaDto.getOrigenEvidencia(): " +
                            evidenciaDto.getOrigenEvidencia());
                    
                }
                
                if(evidenciaDto.getUltimoEslabon()!= null ){
                	EslabonDTO ultimoEslabonDto = evidenciaDto.getUltimoEslabon();
	                assertNotNull("ultimoEslabonDto", ultimoEslabonDto);
	                assertNotNull("ultimoEslabonDto.getTipoEslabon()",
	                        ultimoEslabonDto.getTipoEslabon());
	                if (logger.isDebugEnabled()) {
	                    logger.debug("ultimoEslabonDto.getNumeroEslabon() = " +
	                            ultimoEslabonDto.getNumeroEslabon());
	                }
                }
                //* Almacén (Nota2)
                // Accederlo desde EvidenciaDTO.ObjetoDTO.AlmacenDTO
                
                if(evidenciaDto.getObjeto()!= null){
	                ObjetoDTO objetoDto = evidenciaDto.getObjeto();
	                assertNotNull("objetoDto", objetoDto);
	                if (logger.isDebugEnabled()) {
	                    logger.debug("objetoDto.getElementoId() = " + objetoDto.getElementoId());
	                }
	                if(objetoDto.getAlmacen()!= null){
	                    AlmacenDTO almacenDTO = objetoDto.getAlmacen();
	                    assertNotNull("almacenDTO", almacenDTO);
	                }
                }
                
            }
        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
            fail("Ocurrio una excepcion al ejecutar el test ");
        }
    }
    
    public void testConsultaEvidenciasPorEstatusDePerito(){
    	
    	SolicitudPericialDTO sol = new SolicitudPericialDTO(50L);
    	CadenaDeCustodiaDTO cadena = new CadenaDeCustodiaDTO(1L);
    	cadena.setFolio("AUPV43332");
    	
    	try {
			List<EvidenciaDTO> evidencias = service.consultarEvidenciaXCadenaCustodiaYPeritoDeSolicitudPericial(cadena, sol);
			logger.debug(evidencias);
			
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertNotNull(null);
		}
    	
    }
    
    public void testConsultaPeritosLibres(){
    	SolicitudPericialDTO sol = new SolicitudPericialDTO(50L);
    	CadenaDeCustodiaDTO cadena = new CadenaDeCustodiaDTO(1L);
    	cadena.setFolio("AUPV43332");
    	
    	try {
			List<FuncionarioDTO> funcs = service.consultarPeritosSinAsignacionEnCadenaDeCustodia(cadena, sol);
			logger.debug(funcs.size());
			logger.debug(funcs);
			
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertNotNull(null);
		}
    }
}

/**
 * Nombre del Programa : AsignarNumeroCasoServiceImplTest.java
 * Autor                            : cesarAgustin
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 19 Apr 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Prueba unitaria para el servicio de asignar numero de caso
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
package mx.gob.segob.nsjp.service.test.caso.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.caso.EstatusCaso;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.ConsecutivosUtil;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.institucion.DepartamentoDTO;
import mx.gob.segob.nsjp.service.caso.AsignarNumeroCasoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria para el servicio de asignar numero de caso.
 * 
 * @version 1.0
 * @author cesarAgustin
 * 
 */
public class AsignarNumeroCasoServiceImplTest
        extends
            BaseTestServicios<AsignarNumeroCasoService> {

    public void testAsignarNumeroCaso() {
        CasoDTO casoDTO = new CasoDTO();
        CasoDTO respuesta = null;

        casoDTO.setEstatus(EstatusCaso.INVESTIGACION);
        casoDTO.setFechaApertura(new Date());
        casoDTO.setImputado("ImputadoCaso");
        casoDTO.setVictima("VictimaCaso");
        try {
            respuesta = service.asignarNumeroCaso(casoDTO,obtenerFuncionario());
        } catch (NSJPNegocioException e) {
            logger.error(e.getMessage());
            fail(e.getMessage());
        }

        assertTrue("El identificador obtenido tiene que ser mayor a cero",
                respuesta.getCasoId() > 0);
    }

	public void testAsignarNumeroCasoMasivo() {

        int cont = 0;
        while (cont < 90000) {
            CasoDTO casoDTO = new CasoDTO();
            CasoDTO respuesta = null;

            casoDTO.setEstatus(EstatusCaso.INVESTIGACION);
            casoDTO.setFechaApertura(new Date());
            casoDTO.setImputado("No lo se");
            casoDTO.setVictima("No lo se");

            try {
                respuesta = service.asignarNumeroCaso(casoDTO,obtenerFuncionario());
            } catch (NSJPNegocioException e) {
                logger.error(e.getMessage());
                fail(e.getMessage());
            }

            assertTrue("El identificador obtenido tiene que ser mayor a cero",
                    respuesta.getCasoId() > 0);
            cont++;
        }
    }
	
	private FuncionarioDTO obtenerFuncionario() {        
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		DepartamentoDTO departamento = new DepartamentoDTO();
		departamento.setDepartamentoId(10L);
		AreaDTO area = new AreaDTO();		
		area.setAreaId(10L);// Atención temprana administrativa 
		//area.setAreaId(3L);// UNIDAD_INVESTIGACION 
		departamento.setArea(area);
		funcionarioDTO.setDepartamento(departamento);
		return funcionarioDTO;
	}
    
    public void testVefificaConsecutivo(){
    	String ultimoConsecutivo = "01/02/XX/RBO/2011/ZZ-99999";
    	ConsecutivosUtil loCU = new ConsecutivosUtil();
    	try {
			assertTrue(loCU.incrementarConsecutivoNumeroCaso(ultimoConsecutivo).equals("AA-00000"));
			
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
		
    	
    }
}

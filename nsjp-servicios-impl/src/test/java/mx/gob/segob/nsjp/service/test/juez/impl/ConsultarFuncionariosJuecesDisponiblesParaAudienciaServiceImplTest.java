/**
* Nombre del Programa : ConsultarJuezServiceImpl.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 22/06/2011
* Marca de cambio        : N/A
* Descripcion General    : Pruebas unitarias para la consulta de jueces
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
package mx.gob.segob.nsjp.service.test.juez.impl;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.audiencia.TipoAudiencia;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.model.CatDiscriminante;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.service.funcionario.ConsultarFuncionariosJuecesDisponiblesParaAudienciaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Pruebas unitarias para la consulta de jueces
 * @version 1.0
 * @author Emigdio Hern�ndez
 *
 */
public class ConsultarFuncionariosJuecesDisponiblesParaAudienciaServiceImplTest extends BaseTestServicios<ConsultarFuncionariosJuecesDisponiblesParaAudienciaService>{
	
	public void testConsultaJuecesDisponibles(){
		try{
			Date fechaConsulta = DateUtils.obtener("24/06/2011","09:00");
			Integer duracion = 60;
            FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
            CatDiscriminanteDTO catDiscriminanteDTO = new CatDiscriminanteDTO();
            catDiscriminanteDTO.setCatDiscriminanteId(2L);
            funcionarioDTO.setDiscriminante(catDiscriminanteDTO);
			
			List <FuncionarioDTO> jueces = service.consultarJuecesDisponiblesParaFechaYHoraAudiencia(fechaConsulta, duracion, funcionarioDTO);
			for(FuncionarioDTO j:jueces){
				logger.debug("Juez Disponible: "+j.getNombreFuncionario());
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			assertTrue("Excepci�n: ",false);
		}
		
		
	}
	
	public void testConsultarJuezAutomaticoNoJuicioOral(){
		try{
			Date fechaConsulta = DateUtils.obtener("24/06/2011","09:00");
			Integer duracion = 60;
			AudienciaDTO aud = new AudienciaDTO();
			aud.setId(14L);
			aud.setTipoAudiencia(new ValorDTO(TipoAudiencia.CONTROL.getValorId()));
            FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
            CatDiscriminanteDTO catDiscriminanteDTO = new CatDiscriminanteDTO();
            catDiscriminanteDTO.setCatDiscriminanteId(2L);
            funcionarioDTO.setDiscriminante(catDiscriminanteDTO);
						
			List<FuncionarioDTO> juez = service.consultarJuezAutomaticoADesignar(fechaConsulta, duracion,aud,true, funcionarioDTO);
			for(FuncionarioDTO j:juez){
				logger.debug("Juez Disponible: "+j.getNombreFuncionario());
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			assertTrue("Excepci�n: ",false);
		}
		
	}
	
	public void testConsultarJuezAutomaticoJuicioOral(){
		try{
			Date fechaConsulta = DateUtils.obtener("24/06/2011","09:00");
			Integer duracion = 60;
			AudienciaDTO aud = new AudienciaDTO();
			aud.setId(14L);
			aud.setTipoAudiencia(new ValorDTO(TipoAudiencia.JUICIO_ORAL.getValorId()));

			FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
            CatDiscriminanteDTO catDiscriminanteDTO = new CatDiscriminanteDTO();
            catDiscriminanteDTO.setCatDiscriminanteId(2L);
            funcionarioDTO.setDiscriminante(catDiscriminanteDTO);

			List<FuncionarioDTO> juez = service.consultarJuezAutomaticoADesignar(fechaConsulta, duracion,aud,true, funcionarioDTO);
			for(FuncionarioDTO j:juez){
				logger.debug("Juez Disponible: "+j.getNombreFuncionario());
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			assertTrue("Excepci�n: ",false);
		}
		
	}

}

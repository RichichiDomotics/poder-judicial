/**
 * 
 */
package mx.gob.segob.nsjp.service.test.audiencia.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.SalaAudienciaDTO;
import mx.gob.segob.nsjp.dto.audiencia.SalaJAVSDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.service.audiencia.AdministrarSalaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * 
 * @author GustavoBP
 *
 */
public class AdministrarSalaServiceImplTest 
	extends BaseTestServicios<AdministrarSalaService> {
	
	public void testConsultarSalaAudiencia(){
		SalaAudienciaDTO salaAudienciaDTO = new SalaAudienciaDTO();
		Long salaAudienciaId = 7L;
		salaAudienciaDTO.setSalaAudienciaId(salaAudienciaId );
		try{
			SalaAudienciaDTO salaRegreso =  service.consultarSalaAudiencia(salaAudienciaDTO);
			logger.info(" SalaRegreso: " +  salaRegreso);
			
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}

	public void testGuardarSalaAudiencia(){
	
        SalaAudienciaDTO salaAudienciaDTO = new SalaAudienciaDTO();
        salaAudienciaDTO.setNombreSala("Nueva");
        salaAudienciaDTO.setDomicilioSala("Eje central # 3");
        salaAudienciaDTO.setUbicacionSala("PB");
        salaAudienciaDTO.setEsActivo(Boolean.TRUE);
        
        FuncionarioDTO encargado = new FuncionarioDTO(1L);
        salaAudienciaDTO.setFuncionarioDTO(encargado);
        
        Long catDiscriminanteId = 9L;
		CatDiscriminanteDTO tribunal = new CatDiscriminanteDTO(catDiscriminanteId);
		salaAudienciaDTO.setCatDiscriminanteDTO(tribunal);
		
		
		SalaJAVSDTO salaJAVSDTO = new SalaJAVSDTO();
		salaJAVSDTO.setDireccionIP("http://");
		salaJAVSDTO.setPassword("Pass");
		salaJAVSDTO.setUsuarioJAVS("Usuario");
		salaAudienciaDTO.setSalaJAVSDTO(salaJAVSDTO);
		

		try {
			SalaAudienciaDTO salaRegresoDTO = service.guardarActualizarSalaAudiencia(salaAudienciaDTO);
			logger.info(" SalaRegreso: " +  salaRegresoDTO);
		} catch (NSJPNegocioException e) {
			logger.info(e.getMessage(), e);
		}
		
	}

	public void testActualizarSalaAudiencia(){
		
        SalaAudienciaDTO salaAudienciaDTO = new SalaAudienciaDTO();
        
        salaAudienciaDTO.setEsActivo(Boolean.TRUE);
        
        Long salaAudienciaId = 8L;
		salaAudienciaDTO.setSalaAudienciaId(salaAudienciaId);
		
		
        salaAudienciaDTO.setNombreSala("NuevaXXXXYY");
        salaAudienciaDTO.setDomicilioSala("Eje central # 3XXXYY");
        salaAudienciaDTO.setUbicacionSala("PBXX");
        
        FuncionarioDTO encargado = new FuncionarioDTO(2L);
        salaAudienciaDTO.setFuncionarioDTO(encargado);
        
        Long catDiscriminanteId = 9L;
		CatDiscriminanteDTO tribunal = new CatDiscriminanteDTO(catDiscriminanteId);
		salaAudienciaDTO.setCatDiscriminanteDTO(tribunal);
		
		
		SalaJAVSDTO salaJAVSDTO = new SalaJAVSDTO();
//		Long salaJAVSId = 2L;
//		salaJAVSDTO.setSalaJAVSId(salaJAVSId );
		
		salaJAVSDTO.setDireccionIP("http://XXXX");
		salaJAVSDTO.setPassword("PassXXX");
		salaJAVSDTO.setUsuarioJAVS("UsuarioXX");
		salaAudienciaDTO.setSalaJAVSDTO(salaJAVSDTO);
		
		try {
			SalaAudienciaDTO salaRegresoDTO = service.guardarActualizarSalaAudiencia(salaAudienciaDTO);
			logger.info(" SalaRegreso: " +  salaRegresoDTO);
		} catch (NSJPNegocioException e) {
			logger.info(e.getMessage(), e);
		}
		
	}

	
}

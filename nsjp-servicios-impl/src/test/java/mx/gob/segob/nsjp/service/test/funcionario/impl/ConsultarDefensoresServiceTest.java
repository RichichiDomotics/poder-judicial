/**
 * Nombre del Programa : ConsultarDefensoresServiceTest.java
 * Autor                            : rgama
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    
 * Marca de cambio        : N/A
 * Descripcion General    : Prueba unitaria para el servicio de Registrar Perito
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
package mx.gob.segob.nsjp.service.test.funcionario.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.funcionario.TipoDefensoria;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.persona.CorreoElectronicoDTO;
import mx.gob.segob.nsjp.dto.persona.MedioDeContactoDTO;
import mx.gob.segob.nsjp.dto.persona.TelefonoDTO;
import mx.gob.segob.nsjp.service.funcionario.ConsultarDefensoresService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Servicio para consultar Abogados Defensores
 * @version 1.0
 * @author rgama
 * 
 */
public class ConsultarDefensoresServiceTest extends
		BaseTestServicios<ConsultarDefensoresService> {
	
	 public void testConsultarDefensores(){
	        List<FuncionarioDTO> resultado = null;
			try {
				resultado = service.consultarDefensores();
			} catch (NSJPNegocioException e) {
				e.printStackTrace();
			}
	        assertTrue("El resultado debe ser mayor a 0 : ", resultado.size() > 0);
	        for (FuncionarioDTO funcionarioDTO : resultado) {
	        	muestraInfoDeAbogado(funcionarioDTO);	        	
			}
	        logger.info(resultado);
	    }
	    
	    public void testConsultarDefensorPorTipoDefensa(){
	        List<FuncionarioDTO> resultado = null;
			try {
				resultado = service.consultarDefensorPorTipoDefensa(TipoDefensoria.EXTERNA.getValorId());
			} catch (NSJPNegocioException e) {
				e.printStackTrace();
			}
	        assertTrue("El resultado debe ser mayor a 0 : ", resultado.size() > 0);
	        for (FuncionarioDTO funcionarioDTO : resultado) {
	        	muestraInfoDeAbogado(funcionarioDTO);	        	
			}
	        logger.info(resultado);
	    }
	
	    public void muestraInfoDeAbogado(FuncionarioDTO funcionarioDTO){		  
	       	logger.info("Nombre: "+funcionarioDTO.getNombreFuncionario());
	       	logger.info("APaterno: "+ funcionarioDTO.getApellidoPaternoFuncionario());
	        logger.info("AMaterno: "+ funcionarioDTO.getApellidoMaternoFuncionario());
	        logger.info("Especialidad:"+funcionarioDTO.getEspecialidad().getValor());
	        logger.info("TipoEspecialidad:"+funcionarioDTO.getTipoEspecialidad().getValor());
	    }
	
	    
	    public void testObtenerInformacionDefensor(){
			FuncionarioDTO loFuncionarioDTO = new FuncionarioDTO();
			loFuncionarioDTO.setClaveFuncionario(101L);
			try {
				loFuncionarioDTO = service.obtenerInformacionDefensor(loFuncionarioDTO);			
			} catch (NSJPNegocioException e) {
				e.printStackTrace();
			}
		
			assertNotNull("El puesto debe de ser unico y distinto a null", loFuncionarioDTO);
			
			System.out.println("Datos del defensor: ");
			System.out.println("- Nombre completo: " + loFuncionarioDTO.getNombreFuncionario() + " " 
					+ loFuncionarioDTO.getApellidoPaternoFuncionario()+ " "
					+ loFuncionarioDTO.getApellidoMaternoFuncionario());
			System.out.println("- Correo electrónico: " + loFuncionarioDTO.getEmail());
			for(MedioDeContactoDTO mContacto: loFuncionarioDTO.getMediosContacto()){
				System.out.println("- Medio de contacto: " + mContacto.getMedioDeContadoId());
				if( mContacto instanceof TelefonoDTO){
					TelefonoDTO tel = (TelefonoDTO) mContacto;
					System.out.println("- Medio de contacto tel: " + tel.getMedioDeContadoId());
				}else if( mContacto instanceof CorreoElectronicoDTO){
					CorreoElectronicoDTO correo = (CorreoElectronicoDTO) mContacto;
					System.out.println("- Medio de contacto corr: " + correo.getMedioDeContadoId());
				}
			}	
			System.out.println("Especialidad: " + loFuncionarioDTO.getEspecialidad().getValor());
		}
}

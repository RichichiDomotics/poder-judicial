/**
 * Nombre del Programa : IngresarPeritoServiceImplTest.java
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

import mx.gob.segob.nsjp.comun.enums.funcionario.Puestos;
import mx.gob.segob.nsjp.comun.enums.seguridad.Roles;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.service.funcionario.ConsultarFuncionariosPorRolService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria para consultar una lista de funcionarios por su puesto
	 * Este metodo sera usado para obtener la informacion de:
	 * -Abogado defensor.
	 * -Coordinador de defensores.
	 * -Coordinador de fiscales.
	 * -Coordinador de servicios periciales.
	 * -Fiscal general.
	 * -Fiscal.
	 * -Juez.
	 * -Magistrado.
 * @version 1.0
 * @author rgama
 */
public class ConsultarFuncionariosPorRolServiceImplTest extends
		BaseTestServicios<ConsultarFuncionariosPorRolService> {
	/**
	 * Metodo que permite consultar por filtro
	 */
	public void testConsultarCoordinadorServiciosPericial() {
		List<FuncionarioDTO> loFuncionariosDTO = null;
		try {
			loFuncionariosDTO = service.consultarFuncionariosPorRol(Puestos.COORDINADOR_DE_SERVICIOS_PERICIALES.getValorId());			
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
		
		assertFalse("Se regresa una lista vacia de funcionarios", loFuncionariosDTO.isEmpty());
		
		for (FuncionarioDTO funcionarioDTO : loFuncionariosDTO) {
			logger.info("Nombre funcionario: " + funcionarioDTO.getNombreFuncionario() + " " 
					+ funcionarioDTO.getApellidoPaternoFuncionario()+ " "
					+ funcionarioDTO.getApellidoMaternoFuncionario());
			logger.info("Puesto del funcionario: " + funcionarioDTO.getPuesto().getValor());	
		}
	}	

	public void testConsultarCoordinadorDefensores() {
		List<FuncionarioDTO> loFuncionariosDTO = null;
		try {
			loFuncionariosDTO = service.consultarFuncionariosPorRol(Puestos.COORDINADOR_DE_DEFENSORES.getValorId());
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}

		assertFalse("Se regresa una lista vacia de funcionarios",
				loFuncionariosDTO.isEmpty());

		for (FuncionarioDTO funcionarioDTO : loFuncionariosDTO) {
			logger.info("Nombre funcionario: "
					+ funcionarioDTO.getNombreFuncionario() + " "
					+ funcionarioDTO.getApellidoPaternoFuncionario() + " "
					+ funcionarioDTO.getApellidoMaternoFuncionario());
			logger.info("Puesto del funcionario: "
					+ funcionarioDTO.getPuesto().getValor());
		}
	}
	
	public void testConsultarCoordinadorFiscales() {
		List<FuncionarioDTO> loFuncionariosDTO = null;
		try {
			loFuncionariosDTO = service.consultarFuncionariosPorRol(Puestos.COORDINADOR_DE_FISCALES.getValorId());
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}

		assertFalse("Se regresa una lista vacia de funcionarios",
				loFuncionariosDTO.isEmpty());

		for (FuncionarioDTO funcionarioDTO : loFuncionariosDTO) {
			logger.info("Nombre funcionario: "
					+ funcionarioDTO.getNombreFuncionario() + " "
					+ funcionarioDTO.getApellidoPaternoFuncionario() + " "
					+ funcionarioDTO.getApellidoMaternoFuncionario());
			logger.info("Puesto del funcionario: "
					+ funcionarioDTO.getPuesto().getValor());
		}
	}
	
	public void testConsultarFuncionariosSubordinados () {
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();		
		funcionarioDTO.setClaveFuncionario(1L);
		
		try {
			List<FuncionarioDTO> respuesta = service.consultarFuncionariosSubordinados(funcionarioDTO);
			assertTrue("La lista debe tener minimo un Funcionario :: ",respuesta.size()>0);
			for (FuncionarioDTO funcionarioDTO2 : respuesta) {
				logger.info("-------------------------");
				logger.info("Clave Funcionario "+funcionarioDTO2.getClaveFuncionario());
				logger.info("Nombre Funcionario "+funcionarioDTO2.getNombreCompleto());
				logger.info("-------------------------");
			}
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	
	public void testObtenerDefensorAsignadoAExpediente() {
		
		try {
			FuncionarioDTO respuesta = service.obtenerDefensorAsignadoAExpediente( new ExpedienteDTO(33L));
			assertNotNull("El funcionario no puede ser nulo : ", respuesta);
			logger.info("El funcionario no puede ser nulo : "+ respuesta);			
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	
	public void testObtenerFuncionarioSuperior () {
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		funcionarioDTO.setClaveFuncionario(39L);
		
		try {
			FuncionarioDTO respuesta = service.obtenerFuncionarioSuperior(funcionarioDTO);
			assertNotNull("No puede ser nulo el funcionario superior", respuesta);
			logger.info("Funcionario superior :: "+respuesta);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
	}
	
	public void testObtenerFuncionarioPorNombreCompleto() {
		
		FuncionarioDTO respuesta = service.obtenerFuncionarioPorNombreCompleto("Alberto Pastrana Huicochea");
		
		assertNotNull(respuesta);
		logger.info("---------------------");
		logger.info("Funcionario ID :: "+respuesta.getClaveFuncionario());
		logger.info("Funcionario Nombre :: "+respuesta.getNombreCompleto());
		logger.info("---------------------");
	}

	public void testConsultarFuncionariosPorAreaYPuesto(){
		Long area=9L;
		Long puesto=Puestos.FISCAL.getValorId();
		try {
			List<FuncionarioDTO> funcionarios = service.consultarFuncionariosPorAreayPuesto(area, puesto);
			assertNotNull(funcionarios);
			logger.info("Existen "+funcionarios.size()+" funcionarios");
			for (FuncionarioDTO dto : funcionarios) {
				logger.info("---------------------");
				logger.info("Clave: "+dto.getClaveFuncionario());
				logger.info("NombreCompleto: "+dto.getNombreCompleto());
				logger.info("Depto: "+dto.getDepartamento().getNombreDepto());
				logger.info("Area: "+dto.getDepartamento().getArea().getNombre());
				logger.info("DiscriminanteID: "+dto.getDiscriminante().getClave());
			}
			
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	public void testConsultarFuncionariosPorRolMultiRol(){
		try {
			List<FuncionarioDTO> funcionarios = service.consultarFuncionariosPorRolMultiRol(Roles.UAVD.getValorId());
			assertNotNull(funcionarios);
			logger.info("Existen "+funcionarios.size()+" funcionarios");
			for (FuncionarioDTO dto : funcionarios) {
				logger.info("---------------------");
				logger.info("Clave: "+dto.getClaveFuncionario());
				logger.info("NombreCompleto: "+dto.getNombreCompleto());
				logger.info("Depto: "+dto.getDepartamento().getNombreDepto());
				logger.info("Area: "+dto.getDepartamento().getArea().getNombre());
				logger.info("DiscriminanteID: "+dto.getDiscriminante().getClave());
			}
			
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	public void testConsultarFuncionariosPorDicriminanteYRol(){
		try {
			Long catDiscriminanteId = 18L;
			Long idRol = Roles.COORDINADORAMP.getValorId();
			Long idUIE=1L;
			List<FuncionarioDTO> funcionarios = service.consultarFuncionariosPorDicriminanteYRol(catDiscriminanteId, idRol,idUIE);
			assertNotNull(funcionarios);
			
			logger.info("Existen "+funcionarios.size()+" funcionarios");
			for (FuncionarioDTO dto : funcionarios) {
				logger.info("---------------------");
				logger.info("Clave: "+dto.getClaveFuncionario());
				logger.info("NombreCompleto: "+dto.getNombreCompleto());
				logger.info("Depto: "+dto.getDepartamento().getNombreDepto());
				logger.info("Area: "+dto.getDepartamento().getArea().getNombre());
				logger.info("DiscriminanteID: "+dto.getDiscriminante().getClave());
				logger.info("UIE: "+dto.getUnidadIEspecializada().getNombreUIE());
			}
			
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
//public List<FuncionarioDTO> consultarFuncionariosPorDicriminanteYRol(Long catDiscriminanteId, Long idRol)	
}

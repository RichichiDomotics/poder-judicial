/**
* Nombre del Programa 		: FuncionarioExternoServiceTest.java
* Autor        				: EdgarTE
* Compania 					: Ultrasist
* Proyecto 					: NSJP 							Fecha: 13 Apr 2012
* Marca de cambio 			: N/A
* Descripcion General 		: Describir el objetivo de la clase de manera breve
* Programa Dependiente 		: N/A
* Programa Subsecuente 		: N/A
* Cond. de ejecucion 		: N/A
* Dias de ejecucion 		: N/A 							Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor 					: N/A
* Compania 					: N/A
* Proyecto 					: N/A 							Fecha: N/A
* Modificacion 				: N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.service.test.funcionarioexterno.impl;

import org.apache.log4j.Logger;

import mx.gob.segob.nsjp.dto.configuracion.ConfInstitucionDTO;
import mx.gob.segob.nsjp.dto.funcionarioexterno.FuncionarioExternoDTO;
import mx.gob.segob.nsjp.service.funcionarioexterno.FuncionarioExternoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author EdgarTE
 *
 */
public class FuncionarioExternoServiceTest extends BaseTestServicios<FuncionarioExternoService> {
	
	private static final Logger LOGGER = Logger.getLogger(FuncionarioExternoServiceTest.class); 
	
	public void testCrearFuncionarioExterno(){
		FuncionarioExternoDTO funcionarioExt = new FuncionarioExternoDTO();
		funcionarioExt.setNombre("Juan");
		funcionarioExt.setApellidoPaterno("Perez");
		ConfInstitucionDTO inst = new ConfInstitucionDTO();
		inst.setConfInstitucionId(5L);
		funcionarioExt.setConfInstitucionDTO(inst);
		funcionarioExt.setCveFuncionarioInstExt(1L);
		funcionarioExt = service.crearFuncionarioExterno(funcionarioExt);
		LOGGER.info("Funcionario externo creado exitosamente con el id: " + funcionarioExt.getFuncionarioExternoId());
	}
	
	public void testActualizarFuncionarioExterno(){
		FuncionarioExternoDTO funcionarioExt = new FuncionarioExternoDTO();
		funcionarioExt.setNombre("Pedro");
		funcionarioExt.setApellidoPaterno("Sanchez");
		funcionarioExt.setApellidoMaterno("Lopez");
		ConfInstitucionDTO inst = new ConfInstitucionDTO();
		inst.setConfInstitucionId(1L);
		funcionarioExt.setConfInstitucionDTO(inst);
		funcionarioExt.setCveFuncionarioInstExt(1L);
		funcionarioExt.setFuncionarioExternoId(1L);
		funcionarioExt.setPuesto("Programador");
		funcionarioExt.setArea("Desarrollo");
		funcionarioExt.setEmail("psanchez@nsjp.gob.mx");
		service.actualizarFuncionarioExterno(funcionarioExt);
		LOGGER.info("Funcionario externo actualizado exitosamente.");
	}
	
	public void testConsultarFuncionarioExterno(){
		FuncionarioExternoDTO funcionarioExt = new FuncionarioExternoDTO();
		funcionarioExt.setFuncionarioExternoId(5L);
		funcionarioExt = service.consultarFuncionarioExternoPorId(funcionarioExt);
		if (funcionarioExt != null){
			LOGGER.info("Id: "+ funcionarioExt.getFuncionarioExternoId());
			LOGGER.info("Nombre: "+ funcionarioExt.getNombre());
			LOGGER.info("Apellido Paterno: "+ funcionarioExt.getApellidoPaterno());
			LOGGER.info("Apellido Materno: "+ funcionarioExt.getApellidoMaterno());
			LOGGER.info("Area: "+ funcionarioExt.getArea());
			LOGGER.info("Puesto: "+ funcionarioExt.getPuesto());
			LOGGER.info("E-mail: "+ funcionarioExt.getEmail());
			LOGGER.info("Institucion: "+ funcionarioExt.getConfInstitucionDTO().getNombreInst());
			LOGGER.info("Clave funcionario institucion externa: "+funcionarioExt.getCveFuncionarioInstExt());			
		}else{
			LOGGER.info("No se encontraron resultados de la busqueda");
		}
	}
	
	public void testEliminarFuncionarioExterno(){
		FuncionarioExternoDTO funcionarioExt = new FuncionarioExternoDTO();
		funcionarioExt.setFuncionarioExternoId(1L);
		funcionarioExt = service.consultarFuncionarioExternoPorId(funcionarioExt);
		service.eliminarFuncionarioExterno(funcionarioExt);
		LOGGER.info("El Funcionario externo se elimino correctamente");
	}
}

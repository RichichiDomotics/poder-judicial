/**
* Nombre del Programa : ConsultarAbogadosServiceTest.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 23 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
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


import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.funcionario.TipoDefensoria;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.service.funcionario.ConsultarAbogadosService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author Tattva-IT
 *
 */			 
public class ConsultarAbogadosServiceImplTest extends BaseTestServicios<ConsultarAbogadosService> {
	
	public void testConsultarDefensoresActivosPorTipoDefensa(){
		List<FuncionarioDTO> ls = new ArrayList<FuncionarioDTO>();
		try{
			ls = this.service.consultarDefensoresActivosPorTipoDefensa(TipoDefensoria.INTEGRACION);
		}catch (NSJPNegocioException e) {
			e.printStackTrace();
		}	
		assertNotNull("No debe ser vacia");
		System.out.println("Numero de funcionarios encontrados "+ls);
	}
	
	public void testObtenerDefensoresConCargaMenor(){
		List<FuncionarioDTO> listaDefensoresDTO = null;
		List<FuncionarioDTO> listaDefensoresCargaMenorDTO = null;
		try{
			listaDefensoresDTO =  service.consultarDefensoresActivosPorTipoDefensa(TipoDefensoria.TECNICA);
		
			logger.info(" Lista sin ordenar");
		    printList(listaDefensoresDTO);
		    
		    listaDefensoresCargaMenorDTO = service.obtenerDefensoresConCargaMenor(listaDefensoresDTO);
		    assertNotNull("Lista de defensores con carga menor. ",listaDefensoresCargaMenorDTO);
		    
		    logger.info(" Lista de funcionaros ordenada y con carga de trabajo menor al promedio");
		    printList(listaDefensoresCargaMenorDTO);
			
		}catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
		}		
		
	}
	
	public void testAsignarAleatoriamenteElemento(){
		List<FuncionarioDTO> listaDefensoresDTO = null;
		FuncionarioDTO funcionarioDefensoresElejido = null;
		try{
			listaDefensoresDTO =  service.consultarDefensoresActivosPorTipoDefensa(TipoDefensoria.EXTERNA);
		
			logger.info(" Lista sin ordenar");
		    printList(listaDefensoresDTO);
		    
		    Object[] listaObjetos =listaDefensoresDTO.toArray();
		    logger.info(" Longitud: " + listaObjetos.length);
		    funcionarioDefensoresElejido = (FuncionarioDTO) service.asignarAleatoriamenteElemento(listaObjetos);
		    
		    printFuncionarioDTO(funcionarioDefensoresElejido);
		    
		}catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
		}		
		
	}

	
	private void printList( List<FuncionarioDTO> ldefensoresDTO){
		for (FuncionarioDTO funcionarioDTO : ldefensoresDTO) {
			printFuncionarioDTO(funcionarioDTO);
//			logger.info(" Funcionario Carga"+ funcionarioDTO.getCargaTrabajo());
		}
	}

	private void printFuncionarioDTO(FuncionarioDTO funcionarioDTO){
		logger.info("");
		logger.info("Funcionario id:" + funcionarioDTO.getClaveFuncionario());
		logger.info(" Nombre: "+ funcionarioDTO.getNombreFuncionario() +" " + funcionarioDTO.getApellidoPaternoFuncionario() +
				" "+funcionarioDTO.getApellidoMaternoFuncionario());
		logger.info(" Cedula:"+funcionarioDTO.getCedula());
		logger.info(" Institucion:"+funcionarioDTO.getNombreInstitucion());
		logger.info(" Carga de Trabajo:"+funcionarioDTO.getCargaTrabajo());
	}
	
}

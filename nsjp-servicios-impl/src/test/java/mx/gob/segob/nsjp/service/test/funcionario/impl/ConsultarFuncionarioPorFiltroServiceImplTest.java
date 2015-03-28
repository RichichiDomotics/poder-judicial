/**
 * Nombre del Programa : ConsultarFuncionarioPorFiltroServiceImplTest.java
 * Autor                            : adrian
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 20/07/2011
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

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.CatUIEspecializadaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.institucion.DepartamentoDTO;
import mx.gob.segob.nsjp.service.funcionario.ConsultarFuncionarioPorFiltroService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author adrian
 * 
 */
public class ConsultarFuncionarioPorFiltroServiceImplTest extends
		BaseTestServicios<ConsultarFuncionarioPorFiltroService> {

	/**
	 * Test method for
	 * {@link mx.gob.segob.nsjp.service.funcionario.impl.ConsultarFuncionarioPorFiltroServiceImpl#consultarFuncionarioPorFiltro(mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO)}
	 * .
	 */
	public void testConsultarFuncionarioPorFiltro() {
		FuncionarioDTO filtro = new FuncionarioDTO();
//		DepartamentoDTO depa = new DepartamentoDTO();
//		depa.setArea(new AreaDTO(Areas.COORDINACION_VISITADURIA));
//		DepartamentoDTO depa = new DepartamentoDTO();
//		depa.setArea(new AreaDTO(9L));
//		 filtro.setClaveFuncionario(6L);
		// - Id
		// - Cedula
		// filtro.setCedula("666066");
		// - Email
		// filtro.setEmail("funcionario@dominioaparte.com.mx");
		// - Institución
		// - Área
//		filtro.setDepartamento(depa);
//		filtro.setNumeroEmpleado("12311114");
//		filtro.setClaveFuncionario(242L);
		filtro.setUnidadIEspecializada(new CatUIEspecializadaDTO(2L, null, null));
		try {
			List<FuncionarioDTO> funcionarios = service
					.consultarFuncionarioPorFiltro(filtro);
			logger.info("Existen " + funcionarios.size() + "funcionarios");
			for (FuncionarioDTO dto : funcionarios) {
				logger.info("---------------------");
				logger.info("Clave: "+dto.getClaveFuncionario());
				logger.info("NombreCompleto: "+dto.getNombreCompleto());
				logger.info("Area: "+dto.getJerarquiaOrganizacional().getNombre());
				logger.info("Depto: "+dto.getDepartamento().getNombreDepto());
				logger.info("Area: "+dto.getDepartamento().getArea().getNombre());
//				logger.info("Foto: " + dto.getArchivoDigital().getContenido());
//				logger.info("Foto: " + dto.getArchivoDigital().getNombreArchivo());				
				logger.info("DiscriminanteID: "+dto.getDiscriminante().getClave());
				logger.info("UIE: "+dto.getUnidadIEspecializada().getNombreUIE());
			}

		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
	}

	public void consultarFuncionarioPorDepartamentoYArea() {
		FuncionarioDTO filtro = new FuncionarioDTO();
		DepartamentoDTO depa = new DepartamentoDTO();
		depa.setArea(new AreaDTO(Areas.FISCAL_FACILITADOR));  //Se pasa un departamento, para que se consulte el área.
		filtro.setDepartamento(depa);

		Long idDepartamento = Areas.FISCAL_FACILITADOR.parseLong();
		try {
			List<FuncionarioDTO> funcionarios = service
					.consultarFuncionarioPorDepartamentoYArea(idDepartamento);
			logger.info("Existen " + funcionarios.size() + "funcionarios");
			for (FuncionarioDTO func : funcionarios) {
				logger.info("------------");
				logger.info("Id: " + func.getClaveFuncionario());
				logger.info("NOmbre:" + func.getNombreCompleto());
			}

		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
	}
	
	public void testConsultarFuncionariosXAgencia(){
		try {
			List<FuncionarioDTO> funcionarios = service.consultarFuncionariosXAgencia(1L);
			assertNotNull(funcionarios);
			logger.info("Existen "+funcionarios.size()+ " funcionarios de la agencia");
			for (FuncionarioDTO dto : funcionarios) {
				logger.info("----------------------");
				logger.info("Clave: "+dto.getClaveFuncionario());
				logger.info("Nombre: "+dto.getNombreCompleto());
				logger.info("DiscriminanteID: "+dto.getDiscriminante().getClave());
				
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
}


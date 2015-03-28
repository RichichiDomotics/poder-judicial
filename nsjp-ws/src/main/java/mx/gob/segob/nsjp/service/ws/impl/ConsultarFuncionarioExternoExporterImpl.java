/**
* Nombre del Programa : ConsultarFuncionarioExternoExporterImpl.java
* Autor                            : EdgarTE
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 11 Apr 2012
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
package mx.gob.segob.nsjp.service.ws.impl;

import java.util.List;

import javax.jws.WebService;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.funcionario.CriterioConsultaFuncionarioExternoWSDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioWSDTO;
import mx.gob.segob.nsjp.service.funcionario.ConsultarFuncionarioExternoService;
import mx.gob.segob.nsjp.service.ws.ConsultarFuncionarioExternoExporter;
import mx.gob.segob.nsjp.service.ws.infra.impl.ApplicationContextAwareWSNSJPImpl;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author EdgarTE
 *
 */
@WebService(endpointInterface = "mx.gob.segob.nsjp.service.ws.ConsultarFuncionarioExternoExporter")
public class ConsultarFuncionarioExternoExporterImpl implements
		ConsultarFuncionarioExternoExporter {
	
	
	private ConsultarFuncionarioExternoService consultarFuncionarioExternoService;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.funcionario.ConsultarFuncionarioExternoService#consultarFuncionariosXCriterio(mx.gob.segob.nsjp.dto.funcionario.CriterioConsultaFuncionarioExternoWSDTO)
	 */
	@Override
	public List<FuncionarioWSDTO> consultarFuncionariosXCriterio(
			CriterioConsultaFuncionarioExternoWSDTO criterioConsultaFuncionarioExternoWSDTO)
			throws NSJPNegocioException {

		consultarFuncionarioExternoService = (ConsultarFuncionarioExternoService) ApplicationContextAwareWSNSJPImpl.springApplicationContext
				.getBean("consultarFuncionarioExternoService");

		return consultarFuncionarioExternoService
				.consultarFuncionariosXCriterio(criterioConsultaFuncionarioExternoWSDTO);
	}

}

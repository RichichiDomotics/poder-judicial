/**
* Nombre del Programa : ConsultarFuncionariosXTribunalExporterImpl.java
* Autor                            : rgma
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 03/11/2011
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
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioWSDTO;
import mx.gob.segob.nsjp.service.catalogo.ConsultarFuncionariosXTribunalService;
import mx.gob.segob.nsjp.service.ws.ConsultarFuncionariosXTribunalExporter;
import mx.gob.segob.nsjp.service.ws.infra.impl.ApplicationContextAwareWSNSJPImpl;

import org.apache.log4j.Logger;

/**
 * Implementación para exponer los servicios como web services.
 * @version 1.0
 * @author rgma
 *
 */
@WebService(endpointInterface = "mx.gob.segob.nsjp.service.ws.ConsultarFuncionariosXTribunalExporter")
public class ConsultarFuncionariosXTribunalExporterImpl implements
	ConsultarFuncionariosXTribunalExporter {

	private final static Logger logger =
        Logger.getLogger(ConsultarFuncionariosXTribunalExporterImpl.class);
	
	private ConsultarFuncionariosXTribunalService consultarFuncionariosXTribunalService; 

	@Override
	public List<FuncionarioWSDTO> consultarFuncionariosXTribunal(
		 Long catDiscriminanteId) throws NSJPNegocioException {
		logger.debug("ConsultarFuncionariosXTribunalExporterImpl - consultarFuncionariosXTribunal - catDiscriminanteId: " + catDiscriminanteId);
		
		consultarFuncionariosXTribunalService = (ConsultarFuncionariosXTribunalService)ApplicationContextAwareWSNSJPImpl.springApplicationContext.getBean(
        "consultarFuncionariosXTribunalService");
		
		return consultarFuncionariosXTribunalService.consultarFuncionariosXTribunal(catDiscriminanteId);
	}
}

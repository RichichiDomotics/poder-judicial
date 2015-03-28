/**
* Nombre del Programa : EnviarInformePolicialHomologadoExporterImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 07/09/2011
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

import javax.jws.WebService;

import org.apache.log4j.Logger;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.informepolicial.InformePolicialHomologadoWSDTO;
import mx.gob.segob.nsjp.service.informepolicial.EnviarInformePolicialHomologadoService;
import mx.gob.segob.nsjp.service.ws.EnviarInformePolicialHomologadoExporter;
import mx.gob.segob.nsjp.service.ws.infra.impl.ApplicationContextAwareWSNSJPImpl;

/**
 * Implementación para exponer los servicios como web services.
 * @version 1.0
 * @author GustavoBP
 *
 */
@WebService(endpointInterface = "mx.gob.segob.nsjp.service.ws.EnviarInformePolicialHomologadoExporter")
public class EnviarInformePolicialHomologadoExporterImpl implements
		EnviarInformePolicialHomologadoExporter {

	private final static Logger logger =
        Logger.getLogger(EnviarInformePolicialHomologadoExporterImpl.class);
	
	private EnviarInformePolicialHomologadoService enviarInformePolicialHomologadoService;
	
	
	@Override
	public Long enviarInformePolicialHomologado(InformePolicialHomologadoWSDTO iphWSDTO, Long idAgencia)
			throws NSJPNegocioException {
		
		logger.debug(" EnviarInformePolicialHomologadoExporterImpl - iphWSDTO: " + iphWSDTO);
		
		enviarInformePolicialHomologadoService = (EnviarInformePolicialHomologadoService) ApplicationContextAwareWSNSJPImpl.springApplicationContext.getBean(
        "enviarInformePolicialHomologadoService");
		
        return enviarInformePolicialHomologadoService.enviarInformePolicialHomologado(iphWSDTO,idAgencia);    		
	}

}

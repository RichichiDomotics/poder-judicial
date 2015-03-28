/**
* Nombre del Programa : EnviarDocumentoAInstitucionExporterImpl.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 16/02/2012
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

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DocumentoWSDTO;
import mx.gob.segob.nsjp.service.documento.EnviarDocumentoAInstitucionServerService;
import mx.gob.segob.nsjp.service.ws.EnviarDocumentoAInstitucionExporter;
import mx.gob.segob.nsjp.service.ws.infra.impl.ApplicationContextAwareWSNSJPImpl;

import org.apache.log4j.Logger;

/**
 * Implementación para exponer el servicio que permite enviar un Documento a cualquier otra institución mediante un WebService.
 * @version 1.0
 * @author rgama
 *
 */
@WebService(endpointInterface = "mx.gob.segob.nsjp.service.ws.EnviarDocumentoAInstitucionExporter")
public class EnviarDocumentoAInstitucionExporterImpl implements
	EnviarDocumentoAInstitucionExporter {

	private final static Logger logger =
        Logger.getLogger(EnviarDocumentoAInstitucionExporterImpl.class);
	
	private EnviarDocumentoAInstitucionServerService enviarDocumentoAInstitucionService; 
        		
	public Long enviarDocumentoAInstitucion(DocumentoWSDTO documentoWSDTO, String numeroDeCaso)throws NSJPNegocioException{
		logger.debug(" EnviarDocumentoAInstitucionExporterImpl - enviarDocumentoAInstitucion - DocumentoWSDTO: " + documentoWSDTO);
		logger.info("EnviarDocumentoAInstitucionExporterImpl - numeroDeCaso: "+ numeroDeCaso);
		
		enviarDocumentoAInstitucionService = (EnviarDocumentoAInstitucionServerService)ApplicationContextAwareWSNSJPImpl.springApplicationContext.getBean(
        "enviarDocumentoAInstitucionServerService");
		
		return enviarDocumentoAInstitucionService.enviarDocumentoAInstitucion(documentoWSDTO, numeroDeCaso);
	}		
	
}

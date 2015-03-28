/**
* Nombre del Programa : EnviarDocumentoIncumplimientoMedidaExporterImpl.java
* Autor                            : GustavoBP
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

import javax.jws.WebService;

import org.apache.log4j.Logger;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DocumentoWSDTO;
import mx.gob.segob.nsjp.service.medida.EnviarDocumentoIncumplimientoMedidaService;
import mx.gob.segob.nsjp.service.ws.EnviarDocumentoIncumplimientoMedidaExporter;
import mx.gob.segob.nsjp.service.ws.infra.impl.ApplicationContextAwareWSNSJPImpl;

/**
 * Implementación para exponer los servicios como web services.
 * @version 1.0
 * @author GustavoBP
 *
 */
@WebService(endpointInterface = "mx.gob.segob.nsjp.service.ws.EnviarDocumentoIncumplimientoMedidaExporter")
public class EnviarDocumentoIncumplimientoMedidaExporterImpl implements
		EnviarDocumentoIncumplimientoMedidaExporter {

	private final static Logger logger =
        Logger.getLogger(EnviarDocumentoIncumplimientoMedidaExporterImpl.class);
	
	private EnviarDocumentoIncumplimientoMedidaService enviarDocumentoIncumplimientoMedidaService; 
        		
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.medida.EnviarDocumentoIncumplimientoMedidaService#enviarDocumentoDeIncumplimientoDeMedida(mx.gob.segob.nsjp.dto.documento.DocumentoWSDTO, java.lang.String, java.lang.String)
	 */
	@Override
	public Long enviarDocumentoDeIncumplimientoDeMedida(
			DocumentoWSDTO documentoWSDTO, String numeroCausa,
			String numeroCarpetaEjecucion) throws NSJPNegocioException {
		
		logger.debug(" EnviarDocumentoIncumplimientoMedidaExporterImpl - enviarDocumentoDeIncumplimientoDeMedida - DocumentoWSDTO: " + documentoWSDTO);
		logger.info("EnviarDocumentoIncumplimientoMedidaExporterImpl - NumeroCausa: "+ numeroCausa);
		logger.info("EnviarDocumentoIncumplimientoMedidaExporterImpl - NumeroCarpetaEjecucion: "+ numeroCarpetaEjecucion);
		
		enviarDocumentoIncumplimientoMedidaService = (EnviarDocumentoIncumplimientoMedidaService)ApplicationContextAwareWSNSJPImpl.springApplicationContext.getBean(
        "enviarDocumentoIncumplimientoMedidaService");
		
		return enviarDocumentoIncumplimientoMedidaService.enviarDocumentoDeIncumplimientoDeMedida(documentoWSDTO, numeroCausa, numeroCarpetaEjecucion);
	}
}

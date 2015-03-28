/**
* Nombre del Programa : ConsultarAudienciasByFechasYEstatusExporterImpl.java
* Autor                            : cesar
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 9 Aug 2011
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
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDefensoriaWSDTO;
import mx.gob.segob.nsjp.service.audiencia.ConsultarAudienciasByFechasYEstatusService;
import mx.gob.segob.nsjp.service.ws.ConsultarAudienciasByFechasYEstatusExporter;
import mx.gob.segob.nsjp.service.ws.infra.impl.ApplicationContextAwareWSNSJPImpl;

import org.apache.log4j.Logger;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@WebService(endpointInterface="mx.gob.segob.nsjp.service.ws.ConsultarAudienciasByFechasYEstatusExporter")
public class ConsultarAudienciasByFechasYEstatusExporterImpl 
				implements ConsultarAudienciasByFechasYEstatusExporter {

	/**
	 * 
	 */
	private final static Logger logger = Logger.getLogger(ConsultarAudienciasByFechasYEstatusExporterImpl.class);
	
	private ConsultarAudienciasByFechasYEstatusService consultaAudienciaService;
	
	@Override
	public List<AudienciaDefensoriaWSDTO> consultarAudienciasByFechasYEstatus(
			AudienciaDefensoriaWSDTO audienciaWSDTO) throws NSJPNegocioException {
		
		logger.info("/**** INICIA CONSULTA DE AUDIENCIAS ****/");
		
		consultaAudienciaService = (ConsultarAudienciasByFechasYEstatusService) ApplicationContextAwareWSNSJPImpl.springApplicationContext.
									getBean("consultarAudienciasByFechasYEstatusService");
		
		return consultaAudienciaService.consultarAudienciasByFechasYEstatus(audienciaWSDTO);
	}

	@Override
	public AudienciaDefensoriaWSDTO consultarAudienciaById(
			AudienciaDefensoriaWSDTO audienciaWSDTO)
			throws NSJPNegocioException {
		logger.info("/**** INICIA CONSULTA DE AUDIENCIAS ****/");
		
		consultaAudienciaService = (ConsultarAudienciasByFechasYEstatusService) ApplicationContextAwareWSNSJPImpl.springApplicationContext.
									getBean("consultarAudienciasByFechasYEstatusService");

		return consultaAudienciaService.consultarAudienciaById(audienciaWSDTO);
	}
}

/**
* Nombre del Programa : ConsultarTribunalesPorDistritoServiceExporterImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 02/01/2012
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
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteWSDTO;
import mx.gob.segob.nsjp.service.catalogo.ConsultarTribunalesPorDistritoService;
import mx.gob.segob.nsjp.service.ws.ConsultarTribunalesPorDistritoServiceExporter;
import mx.gob.segob.nsjp.service.ws.infra.impl.ApplicationContextAwareWSNSJPImpl;

import org.apache.log4j.Logger;

/**
 * Implementación para exponer los servicios como web services.
 * @version 1.0
 * @author GustavoBP
 *
 */
@WebService(endpointInterface = "mx.gob.segob.nsjp.service.ws.ConsultarTribunalesPorDistritoServiceExporter")
public class ConsultarTribunalesPorDistritoServiceExporterImpl implements ConsultarTribunalesPorDistritoServiceExporter {

	
	private final static Logger logger =
	        Logger.getLogger(ConsultarTribunalesPorDistritoServiceExporterImpl.class);
	
	private ConsultarTribunalesPorDistritoService consultarTribunalesPorDistritoService;

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.catalogo.ConsultarTribunalesPorDistritoService#consultarTribunalesXDistrito(java.lang.Long)
	 */
	@Override
	public List<CatDiscriminanteWSDTO> consultarTribunalesPorDistrito(
			Long distritoID) throws NSJPNegocioException {
		
		logger.debug(" ConsultarTribunalesPorDistritoServiceExporterImpl - consultarTribunalesPorDistrito - distritoID: " + distritoID);
		
		consultarTribunalesPorDistritoService = (ConsultarTribunalesPorDistritoService)ApplicationContextAwareWSNSJPImpl.springApplicationContext.getBean(
		        "consultarTribunalesPorDistritoService");
		
		return consultarTribunalesPorDistritoService.consultarTribunalesPorDistrito(distritoID);
	}

	@Override
	public List<CatDiscriminanteWSDTO> consultarAgenciasPorDistrito(
			Long idAgencia) throws NSJPNegocioException {
	
		logger.debug(" ConsultarTribunalesPorDistritoServiceExporterImpl - consultarAgenciasPorDistrito - idAgencia: " + idAgencia);
	
		consultarTribunalesPorDistritoService = (ConsultarTribunalesPorDistritoService)ApplicationContextAwareWSNSJPImpl.springApplicationContext.getBean(
		        "consultarTribunalesPorDistritoService");
		
		return consultarTribunalesPorDistritoService.consultarAgenciasPorDistrito(idAgencia);
}

	
	
}


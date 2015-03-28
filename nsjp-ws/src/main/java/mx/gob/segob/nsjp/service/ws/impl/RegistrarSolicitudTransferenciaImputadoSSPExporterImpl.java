/**
* Nombre del Programa : RegistrarSolicitudTransferenciaImputadoSSPExporterImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 21 Sep 2011
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
import mx.gob.segob.nsjp.dto.solicitud.SolicitudTrasladoImputadoWSDTO;
import mx.gob.segob.nsjp.service.solicitud.RegistrarSolicitudTransferenciaImputadoSSPService;
import mx.gob.segob.nsjp.service.ws.RegistrarSolicitudTransferenciaImputadoSSPExporter;
import mx.gob.segob.nsjp.service.ws.infra.impl.ApplicationContextAwareWSNSJPImpl;

import org.apache.log4j.Logger;

/**
 * @version 1.0
 * @author cesarAgustin
 *
 */
@WebService(endpointInterface ="mx.gob.segob.nsjp.service.ws.RegistrarSolicitudTransferenciaImputadoSSPExporter")
public class RegistrarSolicitudTransferenciaImputadoSSPExporterImpl implements
		RegistrarSolicitudTransferenciaImputadoSSPExporter {
	
	public static final Logger logger = Logger.getLogger(RegistrarSolicitudTransferenciaImputadoSSPExporterImpl.class);
	
	private RegistrarSolicitudTransferenciaImputadoSSPService service;
	
	@Override
	public void registrarSolicitudTransferenciaImputadoSSP(
			SolicitudTrasladoImputadoWSDTO solicitudTrasladoImputadoWSDTO)
			throws NSJPNegocioException {
		logger.debug("/**** INICIA WS PARA REGISTRAR SOLICITUD DE TRANSFERENCIA EN SSP ****/");
		
		service = (RegistrarSolicitudTransferenciaImputadoSSPService) ApplicationContextAwareWSNSJPImpl.springApplicationContext
					.getBean("registrarSolicitudTransferenciaImputadoSSPService");
		service.registrarSolicitudTransferenciaImputadoSSP(solicitudTrasladoImputadoWSDTO);
		
		logger.debug("/**** FINALIZA WS PARA REGISTRAR SOLICITUD DE TRANSFERENCIA EN SSP ****/");
	}

}

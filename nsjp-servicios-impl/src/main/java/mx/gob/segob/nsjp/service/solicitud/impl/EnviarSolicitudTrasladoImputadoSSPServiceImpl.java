/**
* Nombre del Programa : EnviarSolicitudTrasladoImputadoSSPServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 21 Sep 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para enviar a SSP la solicitude de trasalado de imputado
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
package mx.gob.segob.nsjp.service.solicitud.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudTrasladoImputadoDAO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudTrasladoImputadoDTO;
import mx.gob.segob.nsjp.model.SolicitudTrasladoImputado;
import mx.gob.segob.nsjp.service.infra.SSPClienteService;
import mx.gob.segob.nsjp.service.solicitud.EnviarSolicitudTrasladoImputadoSSPService;

/**
 * Implementacion del servicio para enviar a SSP la solicitude de trasalado de imputado.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service	
@Transactional
public class EnviarSolicitudTrasladoImputadoSSPServiceImpl implements
		EnviarSolicitudTrasladoImputadoSSPService {
	/**
	 * 
	 */
	public static final Logger logger = Logger.getLogger(EnviarSolicitudTrasladoImputadoSSPServiceImpl.class);
	
	@Autowired
	private SolicitudTrasladoImputadoDAO solicitudTrasladoImputadoDAO;
	@Autowired
	private SSPClienteService clienteService;
	
	@Override
	public void enviarSolicitudTrasladoImputadoASSP(
			SolicitudTrasladoImputadoDTO solicitudTrasladoImputadoDTO)
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA ENVIAR A SSP LA SOLICITUD DE TRASLADO DE IMPUTADO ****/");
		
		if (solicitudTrasladoImputadoDTO.getDocumentoId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		SolicitudTrasladoImputado solicitudTrasladoImputado = solicitudTrasladoImputadoDAO.read(solicitudTrasladoImputadoDTO.getDocumentoId());
		
		clienteService.registrarSolicitudTrasladoImputado(solicitudTrasladoImputado);
		
		if (logger.isDebugEnabled())
			logger.debug("/**** EL ENVIO A SSP DE LA SOLICITUD DE TRASLADO DE IMPUTADO FUE EXITOSA ****/");
	}

}

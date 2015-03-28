/**
* Nombre del Programa : RegistrarSolicitudTransferenciaImputadoSSPServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 21 Sep 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para el resgistro de las solicitudes de transferencia de imputados, que son
 * 							enviadas de Poder Judicial
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

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.forma.Formas;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.audiencia.AudienciaDAO;
import mx.gob.segob.nsjp.dao.elemento.ElementoDAO;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudTrasladoImputadoDAO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudTrasladoImputadoWSDTO;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.SolicitudTrasladoImputado;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.solicitud.RegistrarSolicitudTransferenciaImputadoSSPService;

/**
 * Implementacion del servicio para el resgistro de las solicitudes de transferencia de imputados, que son
 * enviadas de Poder Judicial.
 * @author cesarAgustin
 *
 */
@Service("registrarSolicitudTransferenciaImputadoSSPService")
@Transactional
public class RegistrarSolicitudTransferenciaImputadoSSPServiceImpl implements
		RegistrarSolicitudTransferenciaImputadoSSPService {

	/**
	 * 
	 */
	public static final Logger logger = Logger.getLogger(RegistrarSolicitudTransferenciaImputadoSSPServiceImpl.class);
	
	@Autowired
	private InvolucradoDAO involucradoDAO;
	@Autowired
	private AudienciaDAO audienciaDAO;
	@Autowired
	private SolicitudTrasladoImputadoDAO solicitudTrasladoImputadoDAO;
	
	@Override
	public void registrarSolicitudTransferenciaImputadoSSP(
			SolicitudTrasladoImputadoWSDTO solicitudTrasladoImputadoWSDTO)
			throws NSJPNegocioException {
		
		SolicitudTrasladoImputado solicitudTrasladoImputado = new SolicitudTrasladoImputado();
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA REGISTRAR UNA SOLICITUD DE TRASLADO DE IMPUTADO EN SSP ****/");
		
		if (solicitudTrasladoImputadoWSDTO.getFolioInvolucrado()==null ||
				solicitudTrasladoImputadoWSDTO.getFolioAudiecia()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		Involucrado involucrado = involucradoDAO.obtenerInvolucradoByFolioElemento(solicitudTrasladoImputadoWSDTO.getFolioInvolucrado());
		Audiencia audiencia = audienciaDAO.obtnerAudienciaByFolio(solicitudTrasladoImputadoWSDTO.getFolioAudiecia());
		
		solicitudTrasladoImputado.setInvolucrado(involucrado);
		solicitudTrasladoImputado.setAudiencia(audiencia);
		
		solicitudTrasladoImputado.setTipoSolicitud(new Valor(TiposSolicitudes.TRASLADO_DE_IMPUTADO.getValorId()));
		solicitudTrasladoImputado.setTipoDocumento(new Valor(TipoDocumento.SOLICITUD.getValorId()));
		solicitudTrasladoImputado.setEstatus(new Valor(EstatusSolicitud.ABIERTA.getValorId()));
		solicitudTrasladoImputado.setFechaCreacion(new Date());
		solicitudTrasladoImputado.setForma(new Forma(Formas.SOLICITUD.getValorId()));
		solicitudTrasladoImputado.setNombreDocumento("Solicitud Traslado Imputado");
		
		solicitudTrasladoImputado.setFolioSolicitud(solicitudTrasladoImputadoWSDTO.getFolioSolicitud());
		solicitudTrasladoImputado.setNumeroCasoAsociado(solicitudTrasladoImputadoWSDTO.getNumeroCasoAsociado());
		solicitudTrasladoImputado.setFechaLimite(solicitudTrasladoImputadoWSDTO.getFechaLimite());
		solicitudTrasladoImputado.setSolicitanteExterno(solicitudTrasladoImputadoWSDTO.getSolicitanteExternoId());		
		
		solicitudTrasladoImputadoDAO.create(solicitudTrasladoImputado);	
		
		if (logger.isDebugEnabled())
			logger.debug("/**** EL REGISTRO DE LA SOLICITUD DE TRASLADO EN SSP FUE EXITOSA ****/");
	}

}

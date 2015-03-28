/**
* Nombre del Programa : RegistrarSolicitudTransferenciaImputadoServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 13 Sep 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para registrar una nueva solicitud de tranferencia del imputado
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
import mx.gob.segob.nsjp.dao.solicitud.SolicitudTrasladoImputadoDAO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudTrasladoImputadoDTO;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.SolicitudTrasladoImputado;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.solicitud.RegistrarSolicitudTransferenciaImputadoService;
import mx.gob.segob.nsjp.service.solicitud.impl.transform.SolicitudTransformer;

/**
 * Implementacion del servicio para registrar una nueva solicitud de tranferencia del imputado.
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class RegistrarSolicitudTransferenciaImputadoServiceImpl implements
		RegistrarSolicitudTransferenciaImputadoService {

	/**
	 * 
	 */
	public static final Logger logger = Logger.getLogger(RegistrarSolicitudTransferenciaImputadoServiceImpl.class);
	
	@Autowired
	SolicitudTrasladoImputadoDAO solicitudTrasladoImputadoDAO;
	
	@Override
	public SolicitudTrasladoImputadoDTO registrarSolicitudTrasladoImputado(
			SolicitudTrasladoImputadoDTO solicitudTrasladoImputadoDTO)
			throws NSJPNegocioException {
	
		if (logger.isDebugEnabled())
			logger.debug("SERVICIO PARA REGISTRAR SOLICITUD DE TRASLADO DE IMPUTADOS");
		
		if (solicitudTrasladoImputadoDTO.getInvolucrado()==null ||
				solicitudTrasladoImputadoDTO.getAudiencia()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		SolicitudTrasladoImputado solicitudTrasladoImputado = SolicitudTransformer.solicitudTrasladoImputadoTransformer(solicitudTrasladoImputadoDTO);
		solicitudTrasladoImputado.setTipoSolicitud(new Valor(TiposSolicitudes.TRASLADO_DE_IMPUTADO.getValorId()));
		solicitudTrasladoImputado.setTipoDocumento(new Valor(TipoDocumento.SOLICITUD.getValorId()));
		solicitudTrasladoImputado.setEstatus(new Valor(EstatusSolicitud.ABIERTA.getValorId()));
		solicitudTrasladoImputado.setFechaCreacion(new Date());
		solicitudTrasladoImputado.setForma(new Forma(Formas.SOLICITUD.getValorId()));
		solicitudTrasladoImputado.setNombreDocumento("Solicitud Traslado Imputado");
				
		Long idSoltraslado =  solicitudTrasladoImputadoDAO.create(solicitudTrasladoImputado);
		
		if (logger.isDebugEnabled())
			logger.debug("SE REGISTRO EXITOSAMENTE LA SOLICITUD DE TRASLADO DE IMPUTADO");
		
		return new SolicitudTrasladoImputadoDTO(idSoltraslado);
	}

}
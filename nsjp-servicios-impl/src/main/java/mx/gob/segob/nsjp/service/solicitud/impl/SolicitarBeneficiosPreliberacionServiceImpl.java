/**
* Nombre del Programa : SolicitarBeneficiosPreliberacionServiceImpl.java
* Autor                            : cesar
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 18 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servivo para registrar una solicitud beneficios de preliberacion proveniente de defensoria o seguridad publica
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

import mx.gob.segob.nsjp.comun.enums.audiencia.EstatusAudiencia;
import mx.gob.segob.nsjp.comun.enums.audiencia.TipoAudiencia;
import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.documento.TipoForma;
import mx.gob.segob.nsjp.comun.enums.forma.Formas;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.audiencia.AudienciaDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudAudienciaDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudDAO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudBeneficiosPreliberacionWSDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Solicitud;
import mx.gob.segob.nsjp.model.SolicitudAudiencia;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.solicitud.SolicitarBeneficiosPreliberacionService;
import mx.gob.segob.nsjp.service.solicitud.impl.transform.SolicitudTransformer;

/**
 * Implementacion del servivo para registrar una solicitud beneficios de preliberacion proveniente de defensoria o seguridad publica.
 * @version 1.0
 * @author cesar
 *
 */
@Service("solicitarBeneficiosPreliberacionService")
@Transactional
public class SolicitarBeneficiosPreliberacionServiceImpl implements
		SolicitarBeneficiosPreliberacionService {

	/**
	 * 
	 */
	public static final Logger logger = Logger.getLogger(SolicitarBeneficiosPreliberacionServiceImpl.class);
	
	@Autowired
	private NumeroExpedienteDAO numeroExpedienteDAO;
	@Autowired
	private SolicitudAudienciaDAO solicitudAudienciaDAO;
	@Autowired 
	private AudienciaDAO audienciaDAO;
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.solicitud.SolicitarBeneficiosPreliberacionService#registrarSolicitudBeneficiosPreliberacion(mx.gob.segob.nsjp.dto.solicitud.SolicitudBeneficiosPreliberacionWSDTO)
	 */
	@Override
	public SolicitudBeneficiosPreliberacionWSDTO registrarSolicitudBeneficiosPreliberacion(
			SolicitudBeneficiosPreliberacionWSDTO solBeneficiosPreliberacion)
			throws NSJPNegocioException {
		
		SolicitudAudiencia solicitud = SolicitudTransformer.solicitudTransformer(solBeneficiosPreliberacion);
		solicitud.setTipoSolicitud(new Valor(TiposSolicitudes.BENEFICIO_PRELIBERACION.getValorId()));
		solicitud.setTipoDocumento(new Valor(TipoDocumento.SOLICITUD.getValorId()));
		solicitud.setEstatus(new Valor(EstatusSolicitud.ABIERTA.getValorId()));
		solicitud.setFechaCreacion(new Date());
		solicitud.setForma(new Forma(Formas.SOLICITUD.getValorId()));
		solicitud.setNombreDocumento("Beneficios preliberacion");		
				
		NumeroExpediente carpetaEjecucion = numeroExpedienteDAO.obtenerNumeroExpediente(solBeneficiosPreliberacion.getNumCarpetaEjecucion(),null);		
		solicitud.setNumeroExpediente(carpetaEjecucion);		
		
		Audiencia  audiencia = new Audiencia();
		audiencia.setEstatus(new Valor(EstatusAudiencia.SOLICITADA.getValorId()));
		audiencia.setNumeroExpediente(carpetaEjecucion);
		audiencia.setTipo(new Valor(TipoAudiencia.EJECUCION.getValorId()));
		audiencia.setConsecutivo(Short.decode("1"));//TODO EH
		audienciaDAO.create(audiencia);
		
		solicitud.setAudiencia(audiencia);		
		Long idSolicitud = solicitudAudienciaDAO.create(solicitud);		
		
		SolicitudBeneficiosPreliberacionWSDTO solicitudDTO = new SolicitudBeneficiosPreliberacionWSDTO();
		solicitudDTO.setSolicitudId(idSolicitud);
		
		return solicitudDTO;
	}

}

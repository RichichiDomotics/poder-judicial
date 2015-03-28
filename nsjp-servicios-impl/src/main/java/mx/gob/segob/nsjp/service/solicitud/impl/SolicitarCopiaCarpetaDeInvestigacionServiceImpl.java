/**
* Nombre del Programa : SolicitarCopiaCarpetaDeInvestigacionServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 18 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para registrar la solicitud de la copia de carpeta de investigacion
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

import java.util.Calendar;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.expediente.TipoExpediente;
import mx.gob.segob.nsjp.comun.enums.forma.Formas;
import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudDAO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaBasicoWSDTO;
import mx.gob.segob.nsjp.model.ConfInstitucion;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Solicitud;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.solicitud.SolicitarCopiaCarpetaDeInvestigacionService;
import mx.gob.segob.nsjp.service.solicitud.impl.transform.SolicitudTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del servicio para registrar la solicitud de la copia de carpeta de investigacion.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service("solicitarCopiaCarpetaDeInvestigacionService")
@Transactional
public class SolicitarCopiaCarpetaDeInvestigacionServiceImpl implements
		SolicitarCopiaCarpetaDeInvestigacionService {

	/**
	 * 
	 */
	public static final Logger logger = Logger.getLogger(SolicitarCopiaCarpetaDeInvestigacionServiceImpl.class);
	
	@Autowired
	private SolicitudDAO solicitudDAO;	
	@Autowired
	private NumeroExpedienteDAO numeroExpedienteDAO;
	@Autowired
	private InvolucradoDAO involucradoDAO;

	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.solicitud.SolicitarCopiaCarpetaDeInvestigacionService#solicitarCopiaCarpetaDeInvestigacion(mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaBasicoWSDTO)
	 */
	@Override
	public SolicitudAudienciaBasicoWSDTO solicitarCopiaCarpetaDeInvestigacion(
			SolicitudAudienciaBasicoWSDTO solicitudWsDto,Long catDiscriminante)
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("/**** SERVICIO PARA RECIBIR LA SOLICITUD DE LA COPIA DE LA CARPETA DE INVESTIGACION ****/");
			logger.debug("Recibiendo solicitud con el folio :: " + solicitudWsDto.getFolioSolicitud());
		}
		Valor estadoSolicitud = new Valor(EstatusSolicitud.ABIERTA.getValorId());
		Valor tipoSolicitud = new Valor(TiposSolicitudes.CARPETA_DE_INVESTIGACION.getValorId());
		
		Long idSolicitud = new Long(0);
		if (solicitudWsDto!=null) {
			Solicitud solicitud = SolicitudTransformer.solicitudWSTransformerBasico(solicitudWsDto);
			
			NumeroExpediente carpetaEjecucion = numeroExpedienteDAO.obtenerCarpetaEjecucionByCaso(solicitudWsDto.getNumeroCasoAsociado(), TipoExpediente.CARPETA_DE_EJECUCION.getValorId()); 
			
			solicitud.setEstatus(estadoSolicitud);
			solicitud.setTipoSolicitud(tipoSolicitud);
			solicitud.setFolioSolicitud(solicitudWsDto.getFolioSolicitud());
			solicitud.setNumeroExpediente(carpetaEjecucion);
			//INICA CAMPOS OBLIGATORIOS
			solicitud.setTipoDocumento(new Valor(TipoDocumento.SOLICITUD.getValorId()));
			solicitud.setForma(new Forma(Formas.SOLICITUD.getValorId()));
			solicitud.setEstatus(new Valor(EstatusSolicitud.ABIERTA.getValorId()));
			solicitud.setFechaCreacion(Calendar.getInstance().getTime());
			solicitud.setNombreDocumento("SOLICITUD_DE_CARPETA_DE_INVESTIGACION");
			solicitud.setAreaDestino(solicitudWsDto.getAreaDestino());
			solicitud.setCatDiscriminanteOrigen(catDiscriminante);
			solicitud.setIdFuncionarioSolicitante(solicitudWsDto.getIdFuncionarioSolicitante());
			solicitud.setDestinatario(new Funcionario(solicitudWsDto.getIdFuncionarioSolicitante()));
			logger.debug("El id del Funcionario Destinatario es: " + solicitudWsDto.getIdFuncionarioSolicitante());
			solicitud.setConfInstitucion(new ConfInstitucion(Instituciones.DEF.getValorId()));
			//TERMINA CAMPOS OBLIGATORIOS
			idSolicitud = solicitudDAO.create(solicitud);
		}
		SolicitudAudienciaBasicoWSDTO retorno = new SolicitudAudienciaBasicoWSDTO();
		retorno.setSolicitudId(idSolicitud);
		
		return retorno;
	}

}

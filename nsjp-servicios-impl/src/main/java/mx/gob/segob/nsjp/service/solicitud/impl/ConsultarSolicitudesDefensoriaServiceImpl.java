/**
* Nombre del Programa : ConsultarSolicitudesDefensoriaServiceImpl.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 20 Jun 2011
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
package mx.gob.segob.nsjp.service.solicitud.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.configuracion.Parametros;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.involucrado.DefensaInvolucradoDAO;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoDAO;
import mx.gob.segob.nsjp.dao.parametro.ParametroDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudDefensorDAO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDefensorDTO;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.Parametro;
import mx.gob.segob.nsjp.model.SolicitudDefensor;
import mx.gob.segob.nsjp.service.expediente.BuscarExpedienteService;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;
import mx.gob.segob.nsjp.service.solicitud.ConsultarSolicitudesDefensoriaService;
import mx.gob.segob.nsjp.service.solicitud.impl.transform.SolicitudDefensorTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author Tattva-IT
 *
 */

@Service
@Transactional
public class ConsultarSolicitudesDefensoriaServiceImpl implements
		ConsultarSolicitudesDefensoriaService {

	@Autowired
	private SolicitudDefensorDAO solicitudDefensorDAO;	
	@Autowired
	private BuscarExpedienteService buscarExpedienteService;
	@Autowired
	private ParametroDAO parametroDAO;
	@Autowired
	private InvolucradoDAO involucradoDAO;
	@Autowired
	private DefensaInvolucradoDAO defensaInvolucradoDAO;
	
	private static final Logger logger = Logger
    .getLogger(ConsultarSolicitudesDefensoriaServiceImpl.class);

	@Override
	public List<SolicitudDefensorDTO> obtenerSolicitudesDefensoriaPorEstatus(
            EstatusSolicitud estatusSolc, Instituciones institucion)
            throws NSJPNegocioException {

        logger.debug("Servicio para obtener las Solicitudes no asignadas");

        List<SolicitudDefensorDTO> lsSolDDTO = new ArrayList<SolicitudDefensorDTO>();

        List<SolicitudDefensor> lsSolDef = this.solicitudDefensorDAO
                .obtenerSolicitudesDefensoriaPorEstatus((estatusSolc != null
                        ? estatusSolc.getValorId()
                        : null), institucion);
        logger.debug("Solicitudes encontradas -->" + lsSolDef.size());

        lsSolDDTO = SolicitudDefensorTransformer
                .transformarSolicitudesDefensoria(lsSolDef);

        List<SolicitudDefensorDTO> resp = new ArrayList<SolicitudDefensorDTO>();

        if (lsSolDDTO.size() > 0) {
            int i = 0;
            for (SolicitudDefensorDTO sol : lsSolDDTO) {
                if (sol.getExpedienteDTO() != null) {
                    sol.getExpedienteDTO().setInvolucradosSolicitados(true);
                    ExpedienteDTO expDto = buscarExpedienteService
                            .obtenerExpedienteDefensoria(sol.getExpedienteDTO());
                    sol.setExpedienteDTO(expDto);
                }
                logger.debug("ITERACION# -->" + i++);
                resp.add(sol);

            }
        }
        logger.debug("TAMANO DE LA RESP -->" + resp.size());
        return resp;
    }
	
	@Override
	public List<SolicitudDefensorDTO> obtenerSolicitudesAsesoriaDefensoriaPorEstatus(
            EstatusSolicitud estatusSolc, Instituciones institucion)
            throws NSJPNegocioException {

        logger.debug("Servicio para obtener las Solicitudes no asignadas");

        List<SolicitudDefensorDTO> lsSolDDTO = new ArrayList<SolicitudDefensorDTO>();

        List<SolicitudDefensor> lsSolDef = this.solicitudDefensorDAO
                .obtenerSolicitudesAsesoriaDefensoriaPorEstatus((
                        estatusSolc != null ? estatusSolc.getValorId() : null),
                        institucion);
        logger.debug("Solicitudes encontradas -->" + lsSolDef.size());

        lsSolDDTO = SolicitudDefensorTransformer
                .transformarSolicitudesDefensoria(lsSolDef);

        List<SolicitudDefensorDTO> resp = new ArrayList<SolicitudDefensorDTO>();

        if (lsSolDDTO.size() > 0) {
            int i = 0;
            for (SolicitudDefensorDTO s : lsSolDDTO) {
                if (s.getExpedienteDTO() != null) {
                    s.getExpedienteDTO().setInvolucradosSolicitados(true);
                    ExpedienteDTO expDto = buscarExpedienteService
                            .obtenerExpedienteDefensoria(s.getExpedienteDTO());
                    s.setExpedienteDTO(expDto);
                }
                logger.debug("ITERACION# -->" + i++);
                resp.add(s);

            }
        }
        logger.debug("TAMAÑO RESP -->" + resp.size());
        return resp;
    }
	
	@Override
	public List<SolicitudDefensorDTO> consultarSolicitudesDefensoriaByHistoricoYEstatus(
			EstatusSolicitud estatusSolicitud) throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA CONSULTAR SOLICITUDES DEFENSORIA HISTORICO POR ESTATUS ****/");
		
		if (estatusSolicitud==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		Parametro parametro = parametroDAO.obtenerPorClave(Parametros.LIMITE_HISTORICO_CONSULTAS);		
		Long dias = new Long(parametro.getValor());

		Calendar calTemp = Calendar.getInstance();
		calTemp.setTime(new Date());
		calTemp.add(calTemp.DATE, -dias.intValue());
		
		
		List<SolicitudDefensor> solDefResp = solicitudDefensorDAO.consultarSolicitudesDefensoriaByHistoricoYEstatus(calTemp.getTime(), estatusSolicitud.getValorId());
		
		List<SolicitudDefensorDTO> solDefDTO = new ArrayList<SolicitudDefensorDTO>();
		for (SolicitudDefensor solicitudDefensor : solDefResp) {
			solDefDTO.add(SolicitudDefensorTransformer.transformarSolicitudDefensoria(solicitudDefensor));
		}
		return solDefDTO;
	}

	
	@Override
	public SolicitudDefensorDTO obtenerSolicitudDefensor(
			SolicitudDefensorDTO sol) throws NSJPNegocioException {
	    logger.debug("Recuperando Solicitud de DEfensor con id :: " + sol.getDocumentoId());
		final SolicitudDefensor solicitud = solicitudDefensorDAO.read(sol.getDocumentoId());
		SolicitudDefensorDTO solicitudDTO = SolicitudDefensorTransformer.transformarSolicitudDefensoria(solicitud);
		if(solicitud.getExpediente() != null){
			final List<Involucrado> involucrados = involucradoDAO.consultarInvolucradosByExpediente(solicitud.getExpediente().getExpedienteId());
			for (Involucrado involucrado : involucrados) {
//				solicitudDTO.getExpedienteDTO().addInvolucradoDTO(InvolucradoTransformer.transformarInvolucradoBasico(involucrado));
				solicitudDTO.getExpedienteDTO().addInvolucradoDTO(InvolucradoTransformer.transformarInvolucrado(involucrado));
			}
		}
		return solicitudDTO;
	}

}

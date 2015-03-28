/**
 * Nombre del Programa  : ConsultarInvolucradoAudienciaServiceImpl.java
 * Autor                : Daniel Jiménez
 * Compania             : TATTVA-IT
 * Proyecto             : NSJP                    Fecha: 22 Jun 2011
 * Marca de cambio      : N/A
 * Descripcion General  : Consulta los involucrados en una Audienia
 * Programa Dependiente : N/A
 * Programa Subsecuente : N/A
 * Cond. de ejecucion   : N/A
 * Dias de ejecucion    : N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                :N/A
 * Compania             :N/A
 * Proyecto             :N/A                                 Fecha: N/A
 * Modificacion         :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.involucrado.impl;

import static mx.gob.segob.nsjp.service.audiencia.impl.transform.EventoTransformer.transformarAudienciaCompleto;
import static mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer.transformarInvolucradoView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.relacion.Relaciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.audiencia.AudienciaDAO;
import mx.gob.segob.nsjp.dao.audiencia.InvolucradoAudienciaDAO;
import mx.gob.segob.nsjp.dao.organizacion.OrganizacionDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudAudienciaDAO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoViewDTO;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.Organizacion;
import mx.gob.segob.nsjp.model.SolicitudAudiencia;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;
import mx.gob.segob.nsjp.service.involucrado.ConsultarInvolucradoAudienciaService;
import mx.gob.segob.nsjp.service.organizacion.impl.transform.OrganizacionTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ConsultarInvolucradoAudienciaServiceImpl implements
		ConsultarInvolucradoAudienciaService {

	private final static Logger logger = Logger
			.getLogger(ConsultarInvolucradoAudienciaServiceImpl.class);

	/**
	 * Objeto de Acceso a Datos para la entidad Involucrado.
	 */
	@Autowired
	private AudienciaDAO audienciaDAO;
	@Autowired
	private SolicitudAudienciaDAO solicitudAudienciaDAO;
	@Autowired
	private InvolucradoAudienciaDAO involucradoAudienciaDAO;
	@Autowired
	private OrganizacionDAO organizacionDAO; 

	@Override
	public List<InvolucradoViewDTO> obtenerImputadosVictimasAudiencia(
			AudienciaDTO audienciaDTO) throws NSJPNegocioException {
		LinkedList<InvolucradoDTO> inv = new LinkedList<InvolucradoDTO>();
		LinkedList<InvolucradoViewDTO> involucrados = new LinkedList<InvolucradoViewDTO>();
		if (audienciaDTO == null || audienciaDTO.getId() <= 0) {
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}

		Long audienciaId = audienciaDTO.getId();
		Audiencia aud = audienciaDAO.consultarAudienciaById(audienciaId);
		AudienciaDTO audiencia = transformarAudienciaCompleto(aud);

		List<InvolucradoDTO> aux = null;
		aux = audiencia.getInvolucradoByCalidad(Calidades.VICTIMA_PERSONA);
		if (!aux.isEmpty()) {
			inv.addAll(aux);
		}

		aux = audiencia
				.getInvolucradoByCalidad(Calidades.DENUNCIANTE_ORGANIZACION);
		if (!aux.isEmpty()) {
			inv.addAll(aux);
		}

		aux = audiencia
				.getInvolucradoByCalidad(Calidades.PROBABLE_RESPONSABLE_PERSONA);
		if (!aux.isEmpty()) {
			inv.addAll(aux);
		}

		aux = audiencia
				.getInvolucradoByCalidad(Calidades.PROBABLE_RESPONSABLE_ORGANIZACION);
		if (!aux.isEmpty()) {
			inv.addAll(aux);
		}

		for (InvolucradoDTO involucrado : inv) {
			involucrados.add(transformarInvolucradoView(involucrado));

		}
		return involucrados;
	}

	@Override
	public List<InvolucradoViewDTO> obtenerInvolucradosAudiencia(
			AudienciaDTO audienciaDTO) throws NSJPNegocioException {

		if (audienciaDTO == null || audienciaDTO.getId() <= 0) {
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}
		Long audienciaId = audienciaDTO.getId();
		Audiencia aux = audienciaDAO.read(audienciaId);
		AudienciaDTO audiencia = transformarAudienciaCompleto(aux);

		List<InvolucradoDTO> inv = audiencia.getInvolucrados();
		logger.debug("inv.size() :: " + inv.size());
		LinkedList<InvolucradoViewDTO> involucrados = new LinkedList<InvolucradoViewDTO>();

		for (InvolucradoDTO involucrado : inv) {
			involucrados.add(transformarInvolucradoView(involucrado));
		}

		List<FuncionarioDTO> func = audiencia.getFuncionarios();
		logger.debug("func.size() :: " + func.size());
		for (FuncionarioDTO funcionario : func) {
			involucrados.add(FuncionarioTransformer
					.transformarFuncionarioView(funcionario));
		}
		logger.debug("involucrados.size() :: " + involucrados.size());
		SolicitudAudiencia solAud = solicitudAudienciaDAO
				.consultarSolicitudesAudienciaPorAudiencia(aux.getAudienciaId());

		if (solAud != null && solAud.getNombreSolicitante() != null) {
			String nombreSolicitante = solAud.getNombreSolicitante();
			InvolucradoViewDTO solicitante = new InvolucradoViewDTO();
			solicitante.setNombre(nombreSolicitante);
			solicitante.setInvolucradoId(-999L);
			solicitante.setNombreInstitucion("");
			solicitante.setCalidad("Fiscal");
			solicitante.setFuncionario(true);
			involucrados.add(solicitante);
		}
		return involucrados;
	}

	@Override
	public List<InvolucradoViewDTO> obtenerImputadosAudiencia(
			AudienciaDTO audienciaDTO) throws NSJPNegocioException {
		LinkedList<InvolucradoDTO> inv = new LinkedList<InvolucradoDTO>();
		Long audienciaId = audienciaDTO.getId();
		Audiencia aud = audienciaDAO.consultarAudienciaById(audienciaId);
		AudienciaDTO audiencia = transformarAudienciaCompleto(aud);

		List<InvolucradoDTO> aux = null;

		aux = audiencia
				.getInvolucradoByCalidad(Calidades.PROBABLE_RESPONSABLE_PERSONA);
		if (!aux.isEmpty()) {
			inv.addAll(aux);
		}

		aux = audiencia
				.getInvolucradoByCalidad(Calidades.PROBABLE_RESPONSABLE_ORGANIZACION);
		if (!aux.isEmpty()) {
			List<InvolucradoDTO> auxInvOrg = new ArrayList<InvolucradoDTO>();
			
			for (InvolucradoDTO involucradoDTO : aux) {
				Organizacion organizacion = organizacionDAO.obtenerOrganizacionByRelacion(
						involucradoDTO.getElementoId(),
                        new Long(Relaciones.ORGANIZACION_INVOLUCRADA.ordinal()));
				involucradoDTO.setOrganizacionDTO(OrganizacionTransformer.transformarOrganizacionBasico(organizacion));
				auxInvOrg.add( involucradoDTO);
			}
			inv.addAll(auxInvOrg);
		}

		LinkedList<InvolucradoViewDTO> involucrados = new LinkedList<InvolucradoViewDTO>();

		for (InvolucradoDTO involucrado : inv) {
			involucrados.add(transformarInvolucradoView(involucrado));
		}

		return involucrados;
	}
	
	
	@Override
	public List<InvolucradoViewDTO> obtenerImputadosSiguienteAudiencia(
			AudienciaDTO audienciaDTO) throws NSJPNegocioException {
		
		List<InvolucradoViewDTO> imputados = this.obtenerImputadosAudiencia(audienciaDTO);
		
		//Filtro para obtener solo los imputados que tengan 
		//como Máximo de su audienciaId, al que sea pasado como parámetro 
		
		for (int cont=0; cont<imputados.size(); cont++) {
			InvolucradoViewDTO involucrado =imputados.get(cont) ;
			Long idAudienciaMaxima =  involucradoAudienciaDAO.obtenerMaximoIdAudienciaInvolucrado(involucrado.getInvolucradoId());
			logger.info(" idAudienciaMaxima: "+ idAudienciaMaxima + "-  AudienciaID:"+audienciaDTO.getId());
			if( !idAudienciaMaxima.equals(audienciaDTO.getId())){
				logger.info(" Se Remueve: " + involucrado.getInvolucradoId());
				imputados.remove(cont);
				cont--;
			}
		}

		return imputados;
	}
	
}

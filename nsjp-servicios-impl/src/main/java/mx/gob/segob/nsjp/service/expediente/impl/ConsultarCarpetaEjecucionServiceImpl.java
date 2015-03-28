/**
 * Nombre del Programa : ConsultarCarpetaEjecucionServiceImpl.java
 * Autor                            : Emigdio
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 07/07/2011
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
package mx.gob.segob.nsjp.service.expediente.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente;
import mx.gob.segob.nsjp.comun.enums.expediente.TipoExpediente;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.audiencia.AudienciaDAO;
import mx.gob.segob.nsjp.dao.audiencia.ResolutivoDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoDAO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.resolutivo.ResolutivoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Resolutivo;
import mx.gob.segob.nsjp.service.audiencia.impl.transform.AudienciaTransformer;
import mx.gob.segob.nsjp.service.audiencia.impl.transform.ResolutivoTransformer;
import mx.gob.segob.nsjp.service.expediente.BuscarExpedienteService;
import mx.gob.segob.nsjp.service.expediente.ConsultarCarpetaEjecucionService;
import mx.gob.segob.nsjp.service.expediente.ObtenerDetalleCarpetaEjecucionService;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author Emigdio
 * 
 */
@Service
@Transactional
public class ConsultarCarpetaEjecucionServiceImpl implements
		ConsultarCarpetaEjecucionService {

	private final static Logger logger = Logger
	.getLogger(ConsultarCarpetaEjecucionServiceImpl.class);
	
	@Autowired
	private NumeroExpedienteDAO numeroExpedienteDAO;
	@Autowired
	private AudienciaDAO audienciaDAO;
	@Autowired
	private ResolutivoDAO resolutivoDAO;
	@Autowired
	private InvolucradoDAO involucradoDAO;
	@Autowired
	private BuscarExpedienteService buscarExpedienteService;  
	@Autowired
	private ObtenerDetalleCarpetaEjecucionService detalleCarpetaEjecucionService;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.segob.nsjp.service.expediente.ConsultarCarpetaEjecucionService
	 * #buscarCausaPadreDeNumeroDeExpediente(java.lang.Long)
	 */
	@Override
	public ExpedienteDTO buscarCausaPadreDeNumeroDeExpediente(
			Long numeroExpedienteId) throws NSJPNegocioException {
		NumeroExpediente numExp = numeroExpedienteDAO.read(numeroExpedienteId);

		return numExp != null ? ExpedienteTransformer
				.transformarExpedienteBasico(numExp) : null;
	}

	@Override
	public List<ExpedienteDTO> consultarCarpetasEjecucionPorEstatus(List<ValorDTO> estatusExpediente,UsuarioDTO usuario ) throws NSJPNegocioException {
		List<ExpedienteDTO> carpetasEjecucion = new ArrayList<ExpedienteDTO>();
		List<ExpedienteDTO> carpetasEjecucionRetorno = new ArrayList<ExpedienteDTO>();
		
		logger.info(" consultarCarpetasEjecucionPorEstatus:: "+ estatusExpediente);
		for(ValorDTO estatus : estatusExpediente ){
			logger.info("Estatus:"+ estatus.getIdCampo() );
			List<ExpedienteDTO> respuesta = 
				buscarExpedienteService.consultarNumeroExpedienteByTipoYEstatus(TipoExpediente.CARPETA_DE_EJECUCION, EstatusExpediente.getByValor(estatus.getIdCampo()),usuario);
			if(respuesta!= null && !respuesta.isEmpty())
				carpetasEjecucion .addAll(respuesta);
		}
		
		//Hacer el recorrido para setear el involucrado y el expediente CausaPadre.
		for (ExpedienteDTO expedienteDTO : carpetasEjecucion) {
			logger.info(" Obtener Expediente: "+ expedienteDTO.getNumeroExpedienteId());
			expedienteDTO.setInvolucradosSolicitados(true);
			ExpedienteDTO expDTO = detalleCarpetaEjecucionService.obtenerDetalleCarpetaEjecucion(expedienteDTO); 
			carpetasEjecucionRetorno.add(expDTO);
		}
		return carpetasEjecucionRetorno;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.segob.nsjp.service.expediente.ConsultarCarpetaEjecucionService
	 * #consultarDatosGeneralesExpedienteCarpetaDeEjecucion(java.lang.Long)
	 * 
	 */
	@Override
	public ExpedienteDTO consultarDatosGeneralesExpedienteCarpetaDeEjecucion(
			Long numeroExpedienteId) throws NSJPNegocioException {
		ExpedienteDTO exp = null;
		NumeroExpediente numExp = numeroExpedienteDAO.read(numeroExpedienteId);
		//TODO Traer los mandamientos por resolutivos
		if (numExp != null) {

			exp = ExpedienteTransformer.transformarExpedienteBasico(numExp);
			exp.setInvolucradosDTO(new ArrayList<InvolucradoDTO>());
			// consultar involucrados del expediente
			List<Involucrado> involucradosBD = involucradoDAO
					.consultarInvolucradosByExpediente(exp.getExpedienteId());
			for (Involucrado inv : involucradosBD) {
				exp.getInvolucradosDTO().add(
						InvolucradoTransformer.transformarInvolucrado(inv));
			}
			// Consulta de audiencias
			List<Audiencia> audienciasBD = audienciaDAO
					.consultarAudienciasporExpediente(exp.getExpedienteId());
			if (audienciasBD != null) {
				List<AudienciaDTO> audienciasDTO = new ArrayList<AudienciaDTO>();
				for (Audiencia aud : audienciasBD) {
					AudienciaDTO audDTO = new AudienciaDTO();
					audDTO=AudienciaTransformer.transformarDTO(aud);
					List <Resolutivo> resolutivosBD = resolutivoDAO.consultarResolutivosByAudienciaId(aud.getAudienciaId());
					List <ResolutivoDTO> resolutivosDTO = new ArrayList<ResolutivoDTO>();
					for (Resolutivo res: resolutivosBD){
						resolutivosDTO.add(ResolutivoTransformer.transformarResolutivo(res));
					}
					
					
					audDTO.setResolutivos(resolutivosDTO);
					audienciasDTO.add(audDTO);
					
				}
				
				exp.setAudiencias(audienciasDTO);
			}
			ExpedienteDTO causa = buscarCausaPadreDeNumeroDeExpediente(numeroExpedienteId);
			if (causa != null) {
				exp.setCausaPadre(causa);
			}

		}
		return exp;
	}

}

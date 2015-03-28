/**
 * Nombre del Programa : InvolucradoDelegateImpl.java                                    
 * Autor                            : vaguirre                                              
 * Compania                    : Ultrasist                                                
 * Proyecto                      : NSJP                    Fecha: 04/04/2011 
 * Marca de cambio        : N/A                                                     
 * Descripcion General    : Integraci�n xxxxxxxxxxx                      
 * Programa Dependiente  :N/A                                                      
 * Programa Subsecuente :N/A                                                      
 * Cond. de ejecucion        :N/A                                                      
 * Dias de ejecucion          :N/A                             Horario: N/A       
 *                              MODIFICACIONES                                       
 *------------------------------------------------------------------------------           
 * Autor                       :N/A                                                           
 * Compania               :N/A                                                           
 * Proyecto                 :N/A                                   Fecha: N/A       
 * Modificacion           :N/A                                                           
 *------------------------------------------------------------------------------           
 */
package mx.gob.segob.nsjp.delegate.involucrado.impl;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.involucrado.SituacionJuridica;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.involucrado.InvolucradoDelegate;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.DefensaInvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoViewDTO;
import mx.gob.segob.nsjp.dto.sentencia.SentenciaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.service.graficacion.GraficaDenunciasProbablesResponsablesDetenidosService;
import mx.gob.segob.nsjp.service.involucrado.ActualizarSituacionJuridicaInvolucradoService;
import mx.gob.segob.nsjp.service.involucrado.ConsultarIndividuoService;
import mx.gob.segob.nsjp.service.involucrado.ConsultarInvolucradoAudienciaService;
import mx.gob.segob.nsjp.service.involucrado.ConsultarInvolucradosMedidaService;
import mx.gob.segob.nsjp.service.involucrado.ConsultarInvolucradosPorAudienciaService;
import mx.gob.segob.nsjp.service.involucrado.ConsultarInvolucradosPorIdExpedienteService;
import mx.gob.segob.nsjp.service.involucrado.IngresarIndividuoMenorService;
import mx.gob.segob.nsjp.service.involucrado.IngresarIndividuoService;
import mx.gob.segob.nsjp.service.involucrado.ModificarIndividuoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementaci�n del delegate par alos involucrados de un caso.
 * 
 * @author vaguirre
 * 
 */
@Service ("involucradoDelegate")
public class InvolucradoDelegateImpl implements InvolucradoDelegate {
	
	@Autowired
	private IngresarIndividuoService service;
	
	@Autowired
	private ConsultarIndividuoService consultarIndividuoService;

	@Autowired
	private ModificarIndividuoService modificarIndividuoService;
	
	@Autowired
	private IngresarIndividuoMenorService ingresarIndividuoMenorService;
	
	@Autowired
	private ConsultarInvolucradoAudienciaService consultarInvolucradoAudienciaService;
	@Autowired
	private ConsultarInvolucradosPorAudienciaService consultarInvolucradosPorAudienciaService;
	@Autowired
	private ConsultarInvolucradosMedidaService consultarInvolucradosMedidaService; 
	@Autowired
	private GraficaDenunciasProbablesResponsablesDetenidosService graficaDenunciasProbablesResponsablesDetenidosService;
	@Autowired
	private ActualizarSituacionJuridicaInvolucradoService actualizarSituacionJuridicaInvolucradoService;
	@Autowired
	private ConsultarInvolucradosPorIdExpedienteService consultarInvolucradosPorIdExpedienteService;
	
	
	@Override
	public Long guardarInvolucrado(InvolucradoDTO involucradoDTO)
			throws NSJPNegocioException {
		return service.ingresarIndividuo(involucradoDTO);
	}

	@Override
	public InvolucradoDTO consultarIndividuo(InvolucradoDTO involucradoDTO)
			throws NSJPNegocioException {		
		return consultarIndividuoService.consultarIndividuo(involucradoDTO);
	}

	@Override
	public InvolucradoDTO obtenerInvolucrado(InvolucradoDTO involucradoDTO)
			throws NSJPNegocioException {
		return this.consultarIndividuoService.obtenerInvolucrado(involucradoDTO);
	}

	@Override
	public void actualizarIndividuo(InvolucradoDTO involucradoDTO)
			throws NSJPNegocioException {
		this.modificarIndividuoService.actualizarIndividuo(involucradoDTO);
	}

	@Override
	public List<InvolucradoDTO> ingresarIndividuoMenor(InvolucradoDTO involucradoDTO,
			InvolucradoDTO tutorDTO) throws NSJPNegocioException {		
		return this.ingresarIndividuoMenorService.ingresarIndividuoMenor(involucradoDTO, tutorDTO);
	}

	@Override
	public Long ingresarVictima(InvolucradoDTO involucradoDTO)
			throws NSJPNegocioException {	
		return service.ingresarVictima(involucradoDTO);
	}

	@Override
	public List<InvolucradoDTO> consultarInvolucradoExpediente(
			ExpedienteDTO expedienteDTO, Calidades calidad, UsuarioDTO usuario)
			throws NSJPNegocioException {
			
		return consultarIndividuoService.consultarInvolucradoExpediente(expedienteDTO, calidad, usuario);
	}
	
	@Override
	public List<DefensaInvolucradoDTO> consultarInvolucradoExpedienteDefensoria(
			ExpedienteDTO expedienteDTO, Calidades calidad, UsuarioDTO usuario)
			throws NSJPNegocioException {
			
		return consultarIndividuoService.consultarInvolucradoExpedienteDefensoria(expedienteDTO, calidad, usuario);
	}

	@Override
	public List<InvolucradoViewDTO> obtenerImputadosVictimasAudiencia(AudienciaDTO audienciaDTO)
			throws NSJPNegocioException {
		return consultarInvolucradoAudienciaService.obtenerImputadosVictimasAudiencia(audienciaDTO);
	}
	
	@Override
	public List<InvolucradoViewDTO> obtenerImputadosAudiencia(AudienciaDTO audienciaDTO)
			throws NSJPNegocioException {
		return consultarInvolucradoAudienciaService.obtenerImputadosAudiencia(audienciaDTO);
	}

	@Override
	public List<InvolucradoViewDTO> obtenerInvolucradosAudiencia(AudienciaDTO audienciaDTO)
			throws NSJPNegocioException {
		return consultarInvolucradoAudienciaService.obtenerInvolucradosAudiencia(audienciaDTO);
	}

	@Override
	public List<InvolucradoDTO> consultarInvolucradosPorAudiencia(Long calidad,
			AudienciaDTO audiencia) throws NSJPNegocioException {
		return consultarInvolucradosPorAudienciaService.consultarInvolucradosPorAudiencia(calidad, audiencia);
	}

	@Override
	public List<InvolucradoDTO> consultarProbablesResponsablesDetenidos(
			ExpedienteDTO expedienteDTO, Boolean esDetenido)
			throws NSJPNegocioException {
		return consultarIndividuoService.consultarProbablesResponsablesDetenidos(expedienteDTO,esDetenido);
	}

	@Override
	public List<InvolucradoDTO> consultarInvolucradoPorCalidadCaso(
			CasoDTO casoDTO, CalidadDTO calidadDTO) throws NSJPNegocioException {
		return consultarIndividuoService.consultarInvolucradoPorCalidadCaso(casoDTO, calidadDTO);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.involucrado.InvolucradoDelegate#obtenerInvolucradosByCasoYCalidades(java.lang.String, mx.gob.segob.nsjp.comun.enums.calidad.Calidades[])
	 */
	@Override
	public List<InvolucradoDTO> obtenerInvolucradosByCasoYCalidades(
			String numeroCaso, Calidades[] calidades) {
		return consultarIndividuoService.obtenerInvolucradosByCasoYCalidades(numeroCaso,calidades);
	}
	
	@Override
	public List<InvolucradoDTO> obtenerInvolucradosPorExpedienteYCalidades(String numeroExpediente, Calidades[] calidades) throws NSJPNegocioException{
		return consultarIndividuoService.obtenerInvolucradosPorExpedienteYCalidades(numeroExpediente, calidades);
	}
	
	@Override
	public List<InvolucradoDTO> consultarInvolucradosMedidasAlternasPorFecha(Long cFuncionario, Date fechaFin, Boolean esMedidaAlterna) 
		throws NSJPNegocioException{
		return consultarInvolucradosMedidaService.consultarInvolucradosMedidasPorFecha(cFuncionario, fechaFin, esMedidaAlterna); 
	}

	@Override
	public List<Object[]> obtenerExpedientesPorCondicionDetencionInvYMes(
			Date fechaInicio, Date fechaFin, Boolean condicion)
			throws NSJPNegocioException {
		return graficaDenunciasProbablesResponsablesDetenidosService.obtenerExpedientesPorCondicionDetencionInvYMes(fechaInicio, fechaFin, condicion);		
	}

	@Override
	public Long actualizarSituacionJuridicaInvolucrado(
			InvolucradoDTO involucradoDTO, Long situacionJuridica, SentenciaDTO sentenciaDTO)
			throws NSJPNegocioException {
		return actualizarSituacionJuridicaInvolucradoService.actualizarSituacionJuridicaInvolucrado(involucradoDTO, situacionJuridica, sentenciaDTO);
	}
	
	@Override
	public Long obtenerSituacionJuridicaInvolucrado(
			InvolucradoDTO involucradoDTO)
			throws NSJPNegocioException {
		return actualizarSituacionJuridicaInvolucradoService.obtenerSituacionJuridicaInvolucrado(involucradoDTO);
	}

	@Override
	public Long guardarDefensorAsignadoInvolucrado(
			InvolucradoDTO involucradoDTO, Long probableResponsableId)
			throws NSJPNegocioException {
		return service.guardarDefensorAsignadoInvolucrado(involucradoDTO,probableResponsableId);
	}
	
    @Override
	public List<InvolucradoViewDTO> obtenerImputadosSiguienteAudiencia(
			AudienciaDTO audienciaDTO) throws NSJPNegocioException{
    	return consultarInvolucradoAudienciaService.obtenerImputadosSiguienteAudiencia(audienciaDTO);
    }

    @Override
    public void actualizarSituacionJuridicaDefensoria(
            InvolucradoDTO involucradoDTO, SituacionJuridica situacionJuridica)
            throws NSJPNegocioException {
        actualizarSituacionJuridicaInvolucradoService.actualizarSituacionJuridicaDefensoria(involucradoDTO, situacionJuridica);        
    }
    
    @Override
	public List<InvolucradoDTO> consultarInvolucradosPorExpediente(Long idExpediente) throws NSJPNegocioException {
		return consultarInvolucradosPorIdExpedienteService.consultarInvolucradosPorExpediente(idExpediente);
	}
}

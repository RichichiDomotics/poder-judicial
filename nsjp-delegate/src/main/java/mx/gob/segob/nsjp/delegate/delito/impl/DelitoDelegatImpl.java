/**
 * Nombre del Programa : DelitoDelegatImpl.java
 * Autor                            : cesarAgustin
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 31 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementacion del delegate para Delito y conectar sus servicios con la vista
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
package mx.gob.segob.nsjp.delegate.delito.impl;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.delito.DelitoDelegate;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoPersonaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.service.delito.AsociarDelitoResponsableVictimaService;
import mx.gob.segob.nsjp.service.delito.ConsultarDelitoPersonaService;
import mx.gob.segob.nsjp.service.delito.ConsultarDelitoService;
import mx.gob.segob.nsjp.service.delito.ConsultarDelitosPorImputadoService;
import mx.gob.segob.nsjp.service.delito.GuardarDelitoService;
import mx.gob.segob.nsjp.service.delito.ModificarDelitoService;
import mx.gob.segob.nsjp.service.delito.RegistrarDelitoDenunciaService;
import mx.gob.segob.nsjp.service.delito.ValidarExisteDelitoGraveService;
import mx.gob.segob.nsjp.service.graficacion.GraficaDenunciaVSTipoDelitoService;
import mx.gob.segob.nsjp.service.graficacion.GraficaDetencionesPorTipoService;
import mx.gob.segob.nsjp.service.graficacion.GraficaPrincipalesDelitosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementacion del delegate para Delito y conectar sus servicios con la
 * vista.
 * 
 * @version 1.0
 * @author cesarAgustin
 * 
 */
@Service("delitoDelegate")
public class DelitoDelegatImpl implements DelitoDelegate {

	@Autowired
	private RegistrarDelitoDenunciaService registrarDelitoDenunciaService;
	@Autowired
	private ConsultarDelitoService consultarDelitoService;
	@Autowired
	private ModificarDelitoService modificarDelitoService;
	@Autowired
	private ConsultarDelitosPorImputadoService consultarDelitosPorImputadoService;
	@Autowired
	private ValidarExisteDelitoGraveService validarExisteDelitoGraveService;
	@Autowired
	private GuardarDelitoService guardarDelitoService;
	@Autowired
	private AsociarDelitoResponsableVictimaService asociarDelitoResponsableVictimaService;
	@Autowired
	private ConsultarDelitoPersonaService consultarDelitoPersonaService;
	@Autowired
	private GraficaDenunciaVSTipoDelitoService graficaDenunciaVSTipoDelitoService;
	@Autowired
	private GraficaPrincipalesDelitosService graficaPrincipalesDelitosService;
	@Autowired
	private GraficaDetencionesPorTipoService graficaDetencionesPorTipoService;

	@Override
	public List<DelitoDTO> registrarDelitosDenuncia(List<DelitoDTO> delitosDTO)
			throws NSJPNegocioException {

		return this.registrarDelitoDenunciaService
				.registrarDelitoDenuncia(delitosDTO);
	}

	@Override
	public List<DelitoDTO> consultarDelitosExpediente(
			ExpedienteDTO expedienteDTO) throws NSJPNegocioException {

		return this.consultarDelitoService
				.consultarDelitoExpediente(expedienteDTO);
	}

	@Override
	public void modificarDelito(List<DelitoDTO> delitosDTO)
			throws NSJPNegocioException {
		this.modificarDelitoService.modificarDelito(delitosDTO);
	}

	@Override
	public List<DelitoDTO> consultarDelitosPorImputado(Long idInvolucrado,
			Long idExpediente) throws NSJPNegocioException {
		return consultarDelitosPorImputadoService.consultarDelitosPorImputado(
				idInvolucrado, idExpediente);
	}
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validarExisteDelitoGrave(List<DelitoDTO> delitos)
            throws NSJPNegocioException {
        return validarExisteDelitoGraveService.validarExisteDelitoGrave(delitos);
    }

    
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.delito.DelitoDelegate#existeRelacionProbableResponsableVictimaDelitoFormaParticipacion(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	@Override
	public Boolean existeRelacionProbableResponsableVictimaDelitoFormaParticipacion(
			Long idDelito, Long idProbableResponsable, Long idVictima, Long idFormaParticipacion)
			throws NSJPNegocioException {
		return asociarDelitoResponsableVictimaService
				.existeRelacionProbableResponsableVictimaDelitoFormaParticipacion(idDelito,
						idProbableResponsable, idVictima, idFormaParticipacion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.segob.nsjp.delegate.delito.DelitoDelegate#
	 * asociarDelitoResponsableVictima
	 * (mx.gob.segob.nsjp.dto.expediente.DelitoDTO,
	 * mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO,
	 * mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO, java.lang.Long,
	 * java.lang.Long)
	 */
	@Override
	public Long asociarDelitoResponsableVictima(Long asociacionID,DelitoDTO delito,
			InvolucradoDTO responsable, InvolucradoDTO victima,
			Long formaParticipacion, Long bienTutelado, Long idClasificacion, 
			Long idLugar, Long idModalidad, Long idModus, Long idCausa)
			throws NSJPNegocioException {	
		return asociarDelitoResponsableVictimaService.asociarDelitoResponsableVictima(asociacionID, delito,
				 responsable,  victima, formaParticipacion,  bienTutelado, idClasificacion, 
				 idLugar, idModalidad, idModus, idCausa);
	}

	@Override
	public void guardarDelito(List<DelitoDTO> delitosDto,
			ExpedienteDTO expedienteDto) throws NSJPNegocioException {
		guardarDelitoService.guardarDelito(delitosDto, expedienteDto);
	}

	@Override
	public List<DelitoPersonaDTO> consultarDelitosVictimaPorImputado(
			Long idInvolucrado, Long idExpediente) throws NSJPNegocioException {
		return consultarDelitoPersonaService.consultarDelitosVictimaPorImputado(idInvolucrado,idExpediente);
	}

	@Override
	public Boolean existeRelacionPersonaDelitoExpediente(Long idDelito, Long idExpediente) throws NSJPNegocioException{
		return consultarDelitoPersonaService.existeRelacionPersonaDelitoExpediente(idDelito, idExpediente);
	}
	
	@Override
	public List<DelitoPersonaDTO> consultarVictimaImputadoPorDelito(
			Long idDelito, Long idExpediente) throws NSJPNegocioException {
		return consultarDelitoPersonaService.consultarVictimaImputadoPorDelito(idDelito,idExpediente);
	}
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.delito.DelitoDelegate#consultarDelitosVictimaPorImputado(java.lang.Long)
	 */
	@Override
	public List<DelitoPersonaDTO> consultarDelitosVictimaPorImputado(
			Long idInvolucrado) {
		return consultarDelitoPersonaService.consultarDelitosVictimaPorImputado(idInvolucrado);
		 
	}
	
	/**
	 * Servicio que permite consultar la relacion de probables responsables
	 * @author ricardog
	 */
	public List<DelitoPersonaDTO> consultarDelitosImputadoPorExpediente(Long idExpediente) throws NSJPNegocioException {
		return consultarDelitoPersonaService.consultarVictimaImputadoPorExpediente(idExpediente);
	}

	@Override
	public List<Object[]> delitosTipoPorMes(Date fechaInicial, Date fechaFin,
			boolean tipo) throws NSJPNegocioException {		
		return graficaDenunciaVSTipoDelitoService.delitosTipoPorMes(fechaInicial, fechaFin, tipo);
	}
	
	
	/**
	 * Permite desactivar una relacion Delito Persona
	 * @param delitoPersonaId
	 * @throws NSJPNegocioException
	 */
	public void desactivarDelitoPersona(Long delitoPersonaId) throws NSJPNegocioException{
		consultarDelitoPersonaService.desactivarDelitoPersona(delitoPersonaId);
	}

	@Override
	public List<Object[]> obtenerDelitoPorMes(Date fechaInicio, Date fechaFin,
			Long catDelito) throws NSJPNegocioException {		
		return graficaPrincipalesDelitosService.obtenerDelitoPorMes(fechaInicio, fechaFin, catDelito);
	}

	@Override
	public Long obtenerDetenidosPorMesYDelito(Date fechaInicio,
			Date fechaFin, Long catDelito) throws NSJPNegocioException {		
		return graficaDetencionesPorTipoService.obtenerDetenidosPorMesYDelito(fechaInicio, fechaFin, catDelito);
	}

	@Override
	public Boolean consultarMediaAritmeticaDelitosExpediente(
			ExpedienteDTO inputExpDto) throws NSJPNegocioException {
		return consultarDelitoService.consultarMediaAritmeticaDelitosExpediente(inputExpDto);
	}
	
	@Override
	public Boolean eliminarDelitoPersona(Long delitoPersonaId)throws NSJPNegocioException{
		return consultarDelitoPersonaService.eliminarDelitoPersona(delitoPersonaId); 
	}

}

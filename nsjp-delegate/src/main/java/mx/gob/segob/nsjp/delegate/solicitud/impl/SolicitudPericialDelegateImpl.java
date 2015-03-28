/**
* Nombre del Programa : SolicitudPericialDelegateImpl.java
* Autor                            : rgama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 06 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del delegate para los metodos de comunicacion de Solicitud Pericial entre la vista y los servicios
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
package mx.gob.segob.nsjp.delegate.solicitud.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.funcionario.Puestos;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.solicitud.SolicitudPericialDelegate;
import mx.gob.segob.nsjp.dto.documento.DictamenDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudPericialDTO;
import mx.gob.segob.nsjp.service.evidencia.AsignarEvidenciaService;
import mx.gob.segob.nsjp.service.funcionario.ConsultarFuncionarioPorFiltroService;
import mx.gob.segob.nsjp.service.solicitud.ActualizarStatusSolicitudEvidenciaService;
import mx.gob.segob.nsjp.service.solicitud.AtenderSolicitudPericialService;
import mx.gob.segob.nsjp.service.solicitud.DesignarPeritoASolicitudService;
import mx.gob.segob.nsjp.service.solicitud.RegistrarActualizarServicioPericialService;
import mx.gob.segob.nsjp.service.solicitud.SolicitarPericialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementacion del delegate para los metodos de comunicacion de Solicitud Pericial entre la vista y los servicios.
 * @version 1.0
 * @author rgama
 *
 */
@Service("solicitudPericialDelegate")
public class SolicitudPericialDelegateImpl implements SolicitudPericialDelegate {

	@Autowired
	private SolicitarPericialService solicitarPericialService;
	
	@Autowired
	private ConsultarFuncionarioPorFiltroService consultarPeritosPorFiltroService;
	
	@Autowired 
	private RegistrarActualizarServicioPericialService registrarActualizarServicioPericialService;
	@Autowired
	private AtenderSolicitudPericialService atenderSolicitudPericialService;
	
	 @Autowired
	 private ActualizarStatusSolicitudEvidenciaService actualizarStatusEvidenciaService;
	
	 @Autowired
	 private DesignarPeritoASolicitudService designarPeritoService;
	 
	 @Autowired
	 private AsignarEvidenciaService asignarEvidencia;
	 /**
	 * Metodo que permite registrar a un Abogado Defensor una solicitud pericial
	 */	 	 	
	@Override
	public SolicitudPericialDTO registrarSolicitudPericialAD(
			SolicitudPericialDTO solicitudPericialDTO)
			throws NSJPNegocioException {		
		return this.solicitarPericialService.registrarSolicitudPericial(solicitudPericialDTO);
	}
	
	
	/**
	 * Método que permite guardar/actualizar(Abogado Defensor) la solicitud pericial así como el estado de la solicitud a PENDIENTE
	 * @param solicitudPericialDTO
	 * @throws NSJPNegocioException
	 */
	public SolicitudPericialDTO guardarParcialmenteSolicitudPericialAD(SolicitudPericialDTO solicitudPericialDTO)
	throws NSJPNegocioException{
		return this.solicitarPericialService.guardarParcialmenteSolicitudPericial(solicitudPericialDTO);
	}
		
	/**
	 * Método que permite consultar a un Coordinador de defensoria SolicitudesPericialesDTO de tipo DICTAMEN por estatus,
	 * los estatus se manejan a traves del enum Solicitudes.java 
	 * @param estatusSolicitud
	 * @return List<SolicitudPericialDTO>
	 * @throws NSJPNegocioException
	 */
	public List<SolicitudPericialDTO> consultarSolicitudesPericialesCD(Long estatusSolicitud)
	throws NSJPNegocioException{
		return solicitarPericialService.consultarSolicitudesPericialesDeDictamen(estatusSolicitud);
	}
	
	/* Metodos usandos en un segundo tiempo */
	
	/**
	 * Metodo que permite registrar a un Coordiandor Defensor una Solicitud Pericial
     * Así como el estado de la solicitud a NUEVA
	 */
	public SolicitudPericialDTO registrarSolicitudPericialCD(
			SolicitudPericialDTO solicitudPericialDTO)
			throws NSJPNegocioException {		
		return this.solicitarPericialService.registrarSolicitudPericialCD(solicitudPericialDTO);
	}
	
	/**
	 * Metodo que permite consultar el padre de una solicitud pericial
	 */	
	public Long consultarPadreSolicitudPericial(
			Long documentoHijo)
		throws NSJPNegocioException{
		return this.solicitarPericialService.consultarPadreSolicitudPericial(documentoHijo);
	}	
	
	/**
	 * Metodo que permite registrar/actualizar a un Coordiandor Defensor una Solicitud Pericial
     * Así como el estado de la solicitud a PENDIENTE
	 * @param solicitudPericialDTO
	 * @throws NSJPNegocioException
	 */
	public SolicitudPericialDTO guardarParcialmenteSolicitudPericialCD(SolicitudPericialDTO solicitudPericialDTO)
	throws NSJPNegocioException{
		return this.solicitarPericialService.guardarParcialmenteSolicitudPericialCD(solicitudPericialDTO);
	}
	

	/**
	 * Metodo que permite recuperar a un Coordinador de Periciales(Defensoria) las solicitudes hechas por el Coordinador Defensor, por estatus:
	 * @param estatusSolicitud
	 * @return List<SolicitudPericialDTO>
	 * @throws NSJPNegocioException
	 */
	public List<SolicitudPericialDTO> consultarSolicitudesPericialesCP(Long estatusSolicitud)
	throws NSJPNegocioException{
		return solicitarPericialService.consultarSolicitudesPericialesCP(estatusSolicitud);
	}
	
	
	/**
	 * Metodo que permite consultar Peritos por filtro
	 * @param filtro: permite configurar el objeto para poder filtrar por
	 * nombre, apellido paterno, apellido materno, especialidad e institucion
	 * @return List<PeritoDTO>
	 * @throws NSJPNegocioException
	 */
	@Override
	public List<FuncionarioDTO> consultarFuncionarioPorFiltro(FuncionarioDTO filtro)
			throws NSJPNegocioException {	
		return consultarPeritosPorFiltroService.consultarFuncionarioPorFiltro(filtro);
	}
	
	/**
	 * Método que realiza la funcionalidad de Registrar/Actualizar la información 
	 * de una Solicitud Pericial. 
	 * Registar Nueva Solicitud Pericial no se pasa el IdDocumento
	 * Actualizar Solicitud Pericial es necsario ingresar el IdDocumento
	 * 
	 * Es posible para Registrar/Actualizar la informacion de la solicitud Pericial para el caso de 
	 * pasar, o no, los siguientes parametros 
	 *  -Manejo de expediente (con un numero de expediente) para ser asignado a la solicitud
	 *  -Funcionario - Solicitante considerando el area a que pertence y el puesto del solicitante. 
	 *  
	 * Pendientes: 
	 * 1)El manejo de estatus de la solicitud.
	 * 2)Los atributos de documento y forma, que son necesario para guardar/actualiza la solicitud.
	 * 
	 * @param solicitudPericialDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	@Override
	public SolicitudPericialDTO registrarActulizarSolicitudPericial(
			SolicitudPericialDTO solicitudPericialDTO) throws NSJPNegocioException{
			return registrarActualizarServicioPericialService.registrarActulizarSolicitudPericial(solicitudPericialDTO);
	}

	/*
	 * 
	 */
	/*
	 * 
	 */
    public List<SolicitudPericialDTO> consultarSolicitudesPericialesPorTipoYEstatusDePuestoDestinatario(TiposSolicitudes tipo,
    		EstatusSolicitud estado,Puestos puesto)  throws NSJPNegocioException{
		return solicitarPericialService.consultarSolicitudesPericialesPorTipoYEstatusDePuestoDestinatario(tipo, estado, puesto);
	}
    
    public List<SolicitudPericialDTO> consultarSolicitudesPericialesPorTipoEstatusAreaDestino(TiposSolicitudes tipo,
    		EstatusSolicitud estado,Long area)  throws NSJPNegocioException{
		return solicitarPericialService.consultarSolicitudesPericialesPorTipoEstatusAreaDestinatario(tipo, estado, area);
	}
    

    /*
     * (non-Javadoc)
     * @see mx.gob.segob.nsjp.delegate.solicitud.SolicitudPericialDelegate#consultarSolicitudPericialPorId(java.lang.Long)
     */
	@Override
	public SolicitudPericialDTO consultarSolicitudPericialPorId(Long documentoId) throws NSJPNegocioException{
		return solicitarPericialService.consultarSolicitudPericialPorId(documentoId);
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.solicitud.SolicitudPericialDelegate#consultarSolicitudesPericialesPorEstatusYDestinatario(mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud, mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO)
	 */
	@Override
	public List<SolicitudPericialDTO> consultarSolicitudesPericialesPorEstatusYDestinatario(
			EstatusSolicitud estatus, FuncionarioDTO destinatario)
			throws NSJPNegocioException {
		return solicitarPericialService.consultarSolicitudesPericialesPorEstatusYDestinatario(estatus, destinatario);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.solicitud.SolicitudPericialDelegate#consultarSolicitudesPericialesPorEstatusYDestinatario(mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud, mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO)
	 */
	@Override
	public Long consultarDictamenIdDeSolicitudPericial(Long solicitudPericialId)
			throws NSJPNegocioException {
		return solicitarPericialService.consultarDictamenIdDeSolicitudPericial(solicitudPericialId);
	}

	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.solicitud.SolicitudPericialDelegate#registrarDictamenParaSolicitudPericial(mx.gob.segob.nsjp.dto.documento.DictamenDTO)
	 */
	@Override
	public DictamenDTO registrarDictamenParaSolicitudPericial(
			DictamenDTO dictamen) throws NSJPNegocioException {
		return atenderSolicitudPericialService.registrarDictamenParaSolicitudPericial(dictamen);
	}


	@Override
	public SolicitudDTO actualizarStatusSolicitudEvidencia(
			SolicitudDTO solicitud) throws NSJPNegocioException {		
		return actualizarStatusEvidenciaService.actualizarStatusSolicitudEvidencia(solicitud);
	}


	@Override
	public List<SolicitudDTO> designarPeritoASolicitud(Long idSolicitud,
			List<FuncionarioDTO> funcionarios) throws NSJPNegocioException {

		return designarPeritoService.designarPeritoASolicitud(idSolicitud, funcionarios);		
	}


	@Override
	public Long asignarEvidenciaASolicitudPericial(
			List<EvidenciaDTO> evidencias, SolicitudPericialDTO solicitud) throws NSJPNegocioException,Exception {
		
		return asignarEvidencia.asignarEvidenciaASolicitudPericial(evidencias, solicitud);				
	}


	@Override
	public Long asignarFuncionarioASolicitud(Long idPerito, Long idSolicitud)
			throws NSJPNegocioException, Exception {
		Long result = solicitarPericialService.asignarFuncionarioASolicitud(idPerito, idSolicitud);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.solicitud.SolicitudPericialDelegate#finalizarSolicitudPericial(java.lang.Long)
	 */
	@Override
	public void finalizarSolicitudPericial(Long solicitudId) {
		
		solicitarPericialService.finalizarSolicitudPericial(solicitudId);
		
	}

	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.solicitud.SolicitudPericialDelegate#consultarSolicitudesPericialesPorPuestoSolicitanteEstatusYNumeroExpediente(mx.gob.segob.nsjp.comun.enums.funcionario.Puestos, mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud[], java.lang.Long)
	 */
	@Override
	public List<SolicitudPericialDTO> consultarSolicitudesPericialesPorPuestoSolicitanteEstatusYNumeroExpediente(
			Puestos puesto, EstatusSolicitud[] estados, Long numeroExpedienteId)
			throws NSJPNegocioException {
		return solicitarPericialService.
		consultarSolicitudesPericialesPorPuestoSolicitanteEstatusYNumeroExpediente
		(puesto, estados, numeroExpedienteId);
	}
	
	
}

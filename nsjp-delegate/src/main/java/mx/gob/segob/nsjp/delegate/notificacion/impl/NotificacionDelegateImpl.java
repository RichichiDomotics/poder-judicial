/**
 * Nombre del Programa : NotificacionDelegateImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 18-jul-2011
 * Marca de cambio        : N/A
 * Descripcion General    : N/A
 * Programa Dependient    :N/A
 * Programa Subsecuente   :N/A
 * Cond. de ejecucion     :N/A
 * Dias de ejecucion      :N/A                                Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                            :N/A
 * Compania                         :N/A
 * Proyecto                         :N/A                      Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.delegate.notificacion.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.documento.EstatusNotificacion;
import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.notificacion.NotificacionDelegate;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.AvisoDesignacionDTO;
import mx.gob.segob.nsjp.dto.documento.AvisoHechoDelictivoDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.documento.NotificacionDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.service.documento.AvisoDesignacionService;
import mx.gob.segob.nsjp.service.documento.ConsultarAvisosHDelictivoService;
import mx.gob.segob.nsjp.service.documento.GuardarAvisoHDelictivoService;
import mx.gob.segob.nsjp.service.expediente.AsignarNumeroExpedienteService;
import mx.gob.segob.nsjp.service.notificacion.ActualizarEstatusNotificacionService;
import mx.gob.segob.nsjp.service.notificacion.ConsultaNotificacionService;
import mx.gob.segob.nsjp.service.notificacion.ConsultarNotificacionesAlmacenService;
import mx.gob.segob.nsjp.service.notificacion.ConsultarNotificacionesXUsuarioService;
import mx.gob.segob.nsjp.service.notificacion.ConsultarUltimaNotificacionPorAnioService;
import mx.gob.segob.nsjp.service.notificacion.GuardarNotificacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
@Service("notificacionDelegate")
public class NotificacionDelegateImpl implements NotificacionDelegate {

    @Autowired
    private ConsultarNotificacionesXUsuarioService consultarNotificacionesXUsuarioService;
    @Autowired
    private ConsultaNotificacionService consultaNotificacionService;
    @Autowired
    private ConsultarUltimaNotificacionPorAnioService consultarUltimaNotificacionPorAnioService;
    @Autowired
    private ConsultarNotificacionesAlmacenService consultarNotificacionesAlmacenService;
    @Autowired
    private ActualizarEstatusNotificacionService actualizarEstatusNotificacionService;
    @Autowired
    private AvisoDesignacionService avisoDesignacionService;
    @Autowired
    private GuardarNotificacionService guardarNotificacionService;
    @Autowired
    private GuardarAvisoHDelictivoService guardarAvisoHDelictivoService;
    @Autowired
    private ConsultarAvisosHDelictivoService consultarAvisosHDelictivoService;
    

    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<NotificacionDTO> consultarNotificacionesXUsuario(FuncionarioDTO funcionarioDto,
            ValorDTO estatusDto, int pagina, int numeroDeRegistros,
            String campoOrden, int direccionOrden) throws NSJPNegocioException {
        return consultarNotificacionesXUsuarioService.
                consultarNotificacionesXUsuario(funcionarioDto, estatusDto, pagina,
                numeroDeRegistros, campoOrden, direccionOrden);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long consultarNumeroTotalDeNotificacionesXUsuario(FuncionarioDTO funcionarioDto,
            ValorDTO estatusDto) {
        return consultarNotificacionesXUsuarioService.
                consultarNumeroTotalDeNotificacionesXUsuario(funcionarioDto, estatusDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NotificacionDTO consultaNotificacion(NotificacionDTO consulta) throws NSJPNegocioException {
        return consultaNotificacionService.consultaNotificacion(consulta);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NotificacionDTO consultarUltimaNotificacionPorAnio(FuncionarioDTO
            funcionarioDto) throws NSJPNegocioException {
        return consultarUltimaNotificacionPorAnioService.consultarUltimaNotificacionPorAnio(funcionarioDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<NotificacionDTO> consultarNotificacionesAlmacen(AlmacenDTO almacenDto,
            long tipoMovimiento, long estadoNotificacion) throws NSJPNegocioException {
        return consultarNotificacionesAlmacenService.
                consultarNotificacionesAlmacen(almacenDto, tipoMovimiento, estadoNotificacion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actualizarEstatusNotificacion(NotificacionDTO notificacionDto,
            ValorDTO nuevoEstado) throws NSJPNegocioException {
        actualizarEstatusNotificacionService.actualizarEstatusNotificacion(
                notificacionDto, nuevoEstado);
    }

    @Override
    public AvisoDesignacionDTO consultarAvisoDesignacion(Long idAviso) throws NSJPNegocioException{
    	return avisoDesignacionService.consultarAvisoDesignacion(idAviso);
    }
    
    public void cerrarAvisoDesignacion(Long idAvisoDesignacion) throws NSJPNegocioException{
    	avisoDesignacionService.cerrarAvisoDesignacion(idAvisoDesignacion);
    }
    
	@Override
	public List<AvisoDesignacionDTO> consultarAvisosDesignacion(
			EstatusNotificacion estado, FuncionarioDTO funcionario)
			throws NSJPNegocioException {
		return avisoDesignacionService.consultarAvisosDesignacion(estado, funcionario);
	}

	@Override
	public AvisoDesignacionDTO registrarAvisoDesignacion(
			AvisoDesignacionDTO designacion) throws NSJPNegocioException {
		return avisoDesignacionService.registrarAvisoDesignacion(designacion);
	}

	/**
	 * Asocia un abogado defensor al aviso de Designación para que lo atienda.
	 * @param designacion
	 * @throws NSJPNegocioException
	 */
	@Override
	public void designarAbogadoDefensor(AvisoDesignacionDTO designacion)
			throws NSJPNegocioException{
		avisoDesignacionService.designarAbogadoDefensor(designacion);
	}

	@Override
	public Long guardarNotificacion(NotificacionDTO notificacionDTO, AudienciaDTO audienciaDTO, InvolucradoDTO involucradoDTO)
			throws NSJPNegocioException {
		return guardarNotificacionService.guardarNotificacion(notificacionDTO, audienciaDTO, involucradoDTO);
	}
	
	@Override
	public Long guardarNotificacion(NotificacionDTO notificacionDTO,
			AudienciaDTO audienciaDTO, FuncionarioDTO funcionario) throws NSJPNegocioException {
		return guardarNotificacionService.guardarNotificacion(notificacionDTO, audienciaDTO, funcionario);
	}

	@Override
	public AvisoDesignacionDTO registrarAvisoDesignacionPorReasignacionDefensor(
			ExpedienteDTO input) throws NSJPNegocioException {
		return avisoDesignacionService.registrarAvisoDesignacionPorReasignacionDefensor(input);
	}

    @Override
    public void enviarNotificacion(Long idNotificacion, Long idAudiencia,
            String nombreCompletoFuncionario, Instituciones institucion)
            throws NSJPNegocioException {
        guardarNotificacionService.enviarNotificacion(idNotificacion, idAudiencia, nombreCompletoFuncionario, institucion);        
    }

	@Override
	public AvisoHechoDelictivoDTO ingresarAvisoHechoDeictivoSSP(
			AvisoHechoDelictivoDTO avisoHechoDelictivoDTO)
			throws NSJPNegocioException {		
		return guardarAvisoHDelictivoService.ingresarAvisoHechoDeictivoSSP(avisoHechoDelictivoDTO);
	}

	@Override
	public AvisoHechoDelictivoDTO consultarAvisoHechoXId(Long avisoId)
			throws NSJPNegocioException {
		return consultarAvisosHDelictivoService.consultarAvisoHechoXId(avisoId);
	}

	@Override
	public Long guardarYEnviarNotificacionAMismaInstitucion(ExpedienteDTO expedienteDTO, DocumentoDTO documentoDTO) throws NSJPNegocioException {
		return guardarNotificacionService.guardarYEnviarNotificacionAMismaInstitucion(expedienteDTO, documentoDTO);
	}

	@Override
	public void asignarMotivoRechazoHD(Long avisoId, Long motivoRechazo)
			throws NSJPNegocioException {
		guardarAvisoHDelictivoService.asignarMotivoRechazoHD(avisoId,motivoRechazo);
	}

    @Override
    public void atenderAvisoHechoDelictivo(AvisoHechoDelictivoDTO input)
            throws NSJPNegocioException {
        guardarAvisoHDelictivoService.atenderAvisoHechoDelictivo(input);
        
    }

    @Override
    public AvisoHechoDelictivoDTO obtenerAvisoPorIdExpediente(Long idExpediente)
            throws NSJPNegocioException {
        return consultarAvisosHDelictivoService.obtenerAvisoPorIdExpediente(idExpediente);
    }

    @Override
    public void enviarAvisoHDelictivo(AvisoHechoDelictivoDTO avisoDTO)
            throws NSJPNegocioException {
        this.guardarAvisoHDelictivoService.enviarAvisoHDelictivo(avisoDTO);        
    }

}

/**
 * Nombre del Programa : FuncionarioDelegateImpl.java
 * Autor                            : cesarAgustin
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 12 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementacion de metodos para el acceso a los servicios de Funcionario
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
package mx.gob.segob.nsjp.delegate.funcionario.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.funcionario.TipoDefensoria;
import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.funcionario.FuncionarioDelegate;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.BitacoraDefensorDTO;
import mx.gob.segob.nsjp.dto.funcionario.CriterioConsultaFuncionarioExternoDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.institucion.JerarquiaOrganizacionalDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDefensorDTO;
import mx.gob.segob.nsjp.dto.tarea.EventoCitaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.service.catalogo.AdministrarCatalogoService;
import mx.gob.segob.nsjp.service.evidencia.RegistrarBajaEvidenciaService;
import mx.gob.segob.nsjp.service.expediente.BuscarExpedienteService;
import mx.gob.segob.nsjp.service.funcionario.ActualizarFuncionarioService;
import mx.gob.segob.nsjp.service.funcionario.AdjuntarArchivoAFuncionarioService;
import mx.gob.segob.nsjp.service.funcionario.AsociarPeritoExpedienteService;
import mx.gob.segob.nsjp.service.funcionario.BitacoraDefensorService;
import mx.gob.segob.nsjp.service.funcionario.ConsultarAbogadosService;
import mx.gob.segob.nsjp.service.funcionario.ConsultarAreasAgenciaService;
import mx.gob.segob.nsjp.service.funcionario.ConsultarDefensoresService;
import mx.gob.segob.nsjp.service.funcionario.ConsultarFuncionarioPorDepartamentoService;
import mx.gob.segob.nsjp.service.funcionario.ConsultarFuncionarioPorFiltroService;
import mx.gob.segob.nsjp.service.funcionario.ConsultarFuncionarioXExpedienteService;
import mx.gob.segob.nsjp.service.funcionario.ConsultarFuncionariosPorRolService;
import mx.gob.segob.nsjp.service.funcionario.ConsultarPeritoPorNombreService;
import mx.gob.segob.nsjp.service.funcionario.DesignarAbogadoDefensorService;
import mx.gob.segob.nsjp.service.funcionario.IngresarFuncionarioService;
import mx.gob.segob.nsjp.service.tarea.RegistrarVacacionesIncapacidadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Implementacion de metodos para el acceso a los servicios de Funcionario.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service("funcionarioDelegate")
public class FuncionarioDelegateImpl implements FuncionarioDelegate {
	@Autowired
	ConsultarFuncionarioPorFiltroService consultarFuncionarioPorFiltro;
    @Autowired
    private DesignarAbogadoDefensorService desAbogadoDefensorService;
    @Autowired
    private ConsultarAbogadosService consultarAbogadosService;
    @Autowired
    private ConsultarDefensoresService consultarDefensoresService;
    @Autowired
    private ConsultarFuncionariosPorRolService consultarFuncionarioPorPuestoService;
    @Autowired
    private AsociarPeritoExpedienteService asociarPeritoExpedienteService;
    @Autowired
    private ConsultarFuncionarioXExpedienteService consultarFuncionarioXExpedienteService;
    @Autowired
    private ConsultarPeritoPorNombreService consultarPeritoPorNombreService;
    @Autowired
    private ConsultarFuncionarioPorDepartamentoService consultarFuncionarioPorDepartamentoService;
    @Autowired
    private RegistrarVacacionesIncapacidadService registrarVacacionesIncapacidadService;
    @Autowired
    private BitacoraDefensorService bitacoraDefensorService;
    
    @Autowired
    private IngresarFuncionarioService ingresarPeritoService;
    @Autowired
    private ActualizarFuncionarioService actualizarFuncionarioService;
    @Autowired
    private ConsultarFuncionariosPorRolService consultarFuncionariosService;
    @Autowired
    private RegistrarBajaEvidenciaService registrarBajaEvidenciaService;
    @Autowired
    private ConsultarFuncionarioPorFiltroService consultarFuncionarioPorFiltroService; 
    @Autowired
    private BuscarExpedienteService buscarExpedienteService;
    @Autowired
    private ConsultarAreasAgenciaService consultarAreasAgenciaService;
    @Autowired
	private AdministrarCatalogoService administrarCatalogoService;
    @Autowired
	private AdjuntarArchivoAFuncionarioService adjuntarArchivoAFuncionarioService;
    
    @Override
    public void designarAbogadoDefensor(
            SolicitudDefensorDTO solicitudDefensorDTO) throws NSJPNegocioException {
        this.desAbogadoDefensorService.designarAbogadoDefensor(solicitudDefensorDTO);
    }

    @Override
    public List<FuncionarioDTO> consultarPeritosPorNombre(FuncionarioDTO criterios)
            throws NSJPNegocioException {
        return consultarPeritoPorNombreService.consultarPeritosPorNombre(criterios);
    }

    @Override
    public FuncionarioDTO consultarFuncionarioXExpediente(String expediente)
            throws NSJPNegocioException {
    	return consultarFuncionarioXExpedienteService.consultarFuncionarioXExpediente(expediente);
    }

    @Override
    public void asociarPeritoExpediente(
            FuncionarioDTO peritoDto, ExpedienteDTO numeroExpediente)
            throws NSJPNegocioException {
        asociarPeritoExpedienteService.asociarPeritoExpediente(peritoDto, numeroExpediente);
    }

    @Override
    public List<FuncionarioDTO> consultarDefensoresActivos() throws NSJPNegocioException {
        return this.consultarAbogadosService.consultarDefensoresActivos();
    }

    //FIXME HSERRANO: No hay da servicio
    @Override
    public void designarAbogadoDefensor(
            SolicitudDefensorDTO solicitudDefensorDTO, ExpedienteDTO expedienteDTO) throws NSJPNegocioException {
//        this.desAbogadoDefensorService.designarAbogadoDefensor(solicitudDefensorDTO, expedienteDTO);
    }

    @Override
    public FuncionarioDTO obtenerInformacionDefensor(FuncionarioDTO defensor)
            throws NSJPNegocioException {

        return consultarDefensoresService.obtenerInformacionDefensor(defensor);
    }

    @Override
    public List<FuncionarioDTO> consultarDefensoresActivosPorTipoDefensa(
            TipoDefensoria tipodefensoria) throws NSJPNegocioException {

        return consultarAbogadosService.consultarDefensoresActivosPorTipoDefensa(tipodefensoria);
    }

    /**
     * Metodo que permite consultar los defensores
     * @return Devuelve un listado de defensores
     * @throws NSJPNegocioException
     * @author ricardo
     */
    @Override
    public List<FuncionarioDTO> consultarDefensores() throws NSJPNegocioException {
        return consultarDefensoresService.consultarDefensores();
    }

    public List<FuncionarioDTO> consultarDefensorPorTipoDefensa(Long idTipoDefensa) throws NSJPNegocioException {
        return consultarDefensoresService.consultarDefensorPorTipoDefensa(idTipoDefensa);
    }

    public void registrarVacacionesIncapacidad(FuncionarioDTO funcionario, EventoCitaDTO periodo, UsuarioDTO usuario)throws NSJPNegocioException{
    	registrarVacacionesIncapacidadService.registrarVacacionesIncapacidad(funcionario, periodo, usuario);
    }
    
    @Override
    public List<FuncionarioDTO> consultarFuncionariosPorRol(Long idPuesto)
            throws NSJPNegocioException {
        return consultarFuncionarioPorPuestoService.consultarFuncionariosPorRol(idPuesto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FuncionarioDTO consultarPeritoPorEvidencia(
            EvidenciaDTO evidenciaDto) throws NSJPNegocioException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
	public List<FuncionarioDTO> obtenerDefensoresConCargaMenor(List<FuncionarioDTO> ldefensoresDTO) throws NSJPNegocioException{
		return consultarAbogadosService.obtenerDefensoresConCargaMenor(ldefensoresDTO);
	}

    @Override
	public Object asignarAleatoriamenteElemento(Object[] lista) throws NSJPNegocioException{
		return consultarAbogadosService.asignarAleatoriamenteElemento(lista);
	}
    
    @Override
	public List<FuncionarioDTO> consultarFuncionariosPorAreayPuesto(Long area,
			Long puesto) throws NSJPNegocioException {
		return consultarFuncionarioPorPuestoService.consultarFuncionariosPorAreayPuesto(area, puesto);
	}

	@Override
	public List<FuncionarioDTO> consultarFuncionariosPorRolMultiRol(
			Long idRol) throws NSJPNegocioException {
		return consultarFuncionarioPorPuestoService.consultarFuncionariosPorRolMultiRol(idRol);
	}

	@Override
	public List<FuncionarioDTO> consultarFuncionarioPorDepartamento(
			Long idDepartamento) throws NSJPNegocioException {
		return consultarFuncionarioPorDepartamentoService.consultarFuncionarioPorDepartamento(idDepartamento);
	}

	@Override
	public void guardarBitacoraDefensor(BitacoraDefensorDTO bitacora)
			throws NSJPNegocioException {
		bitacoraDefensorService.guardarBitacoraDefensor(bitacora);
	}
	
	/**
     * Metodo que permite guardar la informacion de un Perito
     * @param peritoDTO: Informacion del Perito
     * @return El identificador con el que fue guardado el Perito 
     * @throws NSJPNegocioException
     */
    public FuncionarioDTO ingresarFuncionario(FuncionarioDTO peritoDTO)
    throws NSJPNegocioException {                           
        return ingresarPeritoService.ingresarFuncionario(peritoDTO);
    }

	@Override
	public FuncionarioDTO modificarDefensor(FuncionarioDTO defensorDTO)
			throws NSJPNegocioException {
		return actualizarFuncionarioService.modificarDefensor(defensorDTO);		
	}

	@Override
	public List<FuncionarioDTO> consultarFuncionariosSubordinados(
			FuncionarioDTO funcionarioDTO) throws NSJPNegocioException {		
		return consultarFuncionariosService.consultarFuncionariosSubordinados(funcionarioDTO);
	}

	@Override
	public FuncionarioDTO obtenerDefensorAsignadoAExpediente(
			ExpedienteDTO expedienteDTO) throws NSJPNegocioException {		
		return consultarFuncionariosService.obtenerDefensorAsignadoAExpediente(expedienteDTO);
	}

	public List<FuncionarioDTO> obtenerFuncionariosConUsuario() throws NSJPNegocioException{
		return consultarFuncionariosService.obtenerFuncionarosConUsuario();
	}

	@Override
	public FuncionarioDTO obtenerFuncionarioSuperior(
			FuncionarioDTO funcionarioDTO) throws NSJPNegocioException {		
		return consultarFuncionariosService.obtenerFuncionarioSuperior(funcionarioDTO);
	}

	@Override
	public List<FuncionarioDTO> consultarFuncionarioXNumeroEmpleado(
			FuncionarioDTO filtroDTO) throws NSJPNegocioException {
		return consultarFuncionarioPorFiltro.consultarFuncionarioPorFiltro(filtroDTO);
	}

    @Override
    public FuncionarioDTO consultaFuncionarioPorNombreInstitucionYPuesto(
            FuncionarioDTO funcionarioDto) throws NSJPNegocioException {
        return registrarBajaEvidenciaService.consultaFuncionarioPorNombreInstitucionYPuesto(funcionarioDto);
    }
    
    @Override
    public List<FuncionarioDTO> consultarFuncionarioPorDepartamentoYArea(Long idDepartamento)
	throws NSJPNegocioException {
    	return consultarFuncionarioPorFiltroService.consultarFuncionarioPorDepartamentoYArea(idDepartamento); 
    }

	@Override
	public FuncionarioDTO obtenerFuncionarioPorNombreCompleto(
			String nombreCompleto) throws NSJPNegocioException {		
		return consultarFuncionariosService.obtenerFuncionarioPorNombreCompleto(nombreCompleto);
	}

	@Override
	public List<ExpedienteDTO> consultarExpedientesDelFuncionario(
			UsuarioDTO usuario) throws NSJPNegocioException {		
		return buscarExpedienteService.consultarExpedientesDelFuncionario(usuario);
	}

	@Override
	public List<ExpedienteDTO> consultarExpedientescConPermisoFuncionario(
			Long claveFuncionario) throws NSJPNegocioException {		
		return buscarExpedienteService.consultarExpedientescConPermisoFuncionario(claveFuncionario);
	}

	@Override
	public List<FuncionarioDTO> consultarFuncionariosXAgencia(Long idAgencia)
			throws NSJPNegocioException {
		return consultarFuncionarioPorFiltro.consultarFuncionariosXAgencia(idAgencia);
	}

	@Override
	public List<JerarquiaOrganizacionalDTO> consultarAreasXAgencia(
			Long idAgencia) throws NSJPNegocioException {
		return consultarAreasAgenciaService.consultarAreasXAgencia(idAgencia);
	}
	
	@Override
	public  List<FuncionarioDTO> consultarFuncionariosPorDicriminanteYRol(Long catDiscriminanteId, Long idRol, Long idUIE)throws NSJPNegocioException{
		return consultarFuncionariosService.consultarFuncionariosPorDicriminanteYRol(catDiscriminanteId, idRol,idUIE);
	}
	
	@Override
	public List<FuncionarioDTO> consultarFuncionariosXTribunal(
			 Long catDiscriminanteId,Instituciones target) throws NSJPNegocioException {
		return administrarCatalogoService.consultarFuncionariosXTribunal(catDiscriminanteId, target);
	}

	@Override
	public void adjuntarArchivoAFuncionario(Long idfuncionario,
			ArchivoDigitalDTO adjunto) throws NSJPNegocioException {
		adjuntarArchivoAFuncionarioService.adjuntarArchivoAFuncionario(idfuncionario, adjunto);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.funcionario.FuncionarioDelegate#consultarFuncionariosXCriterio(mx.gob.segob.nsjp.dto.funcionario.CriterioConsultaFuncionarioExternoDTO)
	 */
	@Override
	public List<FuncionarioDTO> consultarFuncionariosXCriterio(
			CriterioConsultaFuncionarioExternoDTO criterioConsultaFuncionarioExternoDTO)
			throws NSJPNegocioException {
		return consultarFuncionarioPorFiltro.consultarFuncionariosXCriterio(criterioConsultaFuncionarioExternoDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.funcionario.FuncionarioDelegate#consultarFuncionariosExternosXCriterio(mx.gob.segob.nsjp.dto.funcionario.CriterioConsultaFuncionarioExternoDTO, mx.gob.segob.nsjp.comun.enums.institucion.Instituciones)
	 */
	@Override
	public List<FuncionarioDTO> consultarFuncionariosExternosXCriterio(
			CriterioConsultaFuncionarioExternoDTO criterioConsultaFuncionarioExternoDTO,
			Instituciones target) throws NSJPNegocioException {
		return consultarFuncionarioPorFiltro.consultarFuncionariosExternosXCriterio(
				criterioConsultaFuncionarioExternoDTO, target);
	}
}

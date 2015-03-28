/**
 * Nombre del Programa : EvidenciaDelegateImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 30-jun-2011
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
package mx.gob.segob.nsjp.delegate.evidencia.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.evidencia.EvidenciaDelegate;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudPericialDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.service.almacen.ConsultarEvidenciaPorAlmacenService;
import mx.gob.segob.nsjp.service.evidencia.AsignarEvidenciaService;
import mx.gob.segob.nsjp.service.evidencia.AsociarDestinoLegalService;
import mx.gob.segob.nsjp.service.evidencia.CambiarEstatusEvidenciaService;
import mx.gob.segob.nsjp.service.evidencia.ConsultarDocumentosDEvidenciaService;
import mx.gob.segob.nsjp.service.evidencia.ConsultarEvidenciasNoDevueltasService;
import mx.gob.segob.nsjp.service.evidencia.ConsultarEvidenciasResguardadasPorUsuarioService;
import mx.gob.segob.nsjp.service.evidencia.ConsultarEvidenciasXCadenaCustodiaService;
import mx.gob.segob.nsjp.service.evidencia.ConsultarEvidenciasXEstatusService;
import mx.gob.segob.nsjp.service.evidencia.GuardarEvidenciaService;
import mx.gob.segob.nsjp.service.evidencia.RegistrarBajaEvidenciaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
@Service("evidenciaDelegate")
public class EvidenciaDelegateImpl implements EvidenciaDelegate {

    @Autowired
    private ConsultarEvidenciasXCadenaCustodiaService evidenciasXCadenaCustodiaService;
    @Autowired
    private ConsultarEvidenciasResguardadasPorUsuarioService evidenciasXUsuarioService;
    @Autowired
    private GuardarEvidenciaService guardarEvidenciaService;
    @Autowired
    private AsignarEvidenciaService asignarEvidenciaService;
    @Autowired
    private ConsultarEvidenciasXEstatusService consultarEvidenciasXEstatusService;
    @Autowired
    private AsociarDestinoLegalService asociarDestinoLegalService;
    @Autowired
    private RegistrarBajaEvidenciaService registrarBajaEvidenciaService;
    @Autowired
    private ConsultarEvidenciaPorAlmacenService consultarEvidenciasXAlmacenService;
    @Autowired
    private ConsultarDocumentosDEvidenciaService consultarDocumentosDEvidenciaService;
    @Autowired
    private ConsultarEvidenciasNoDevueltasService consultarEvidenciasNoDevueltasService;
    @Autowired
    private CambiarEstatusEvidenciaService cambiarEstatusEvidenciaService;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<EvidenciaDTO> consultarEvidenciasXCadenaCustodia(
            CadenaDeCustodiaDTO cadenaDeCustodiaDTO)
            throws NSJPNegocioException {
        return evidenciasXCadenaCustodiaService
                .consultarEvidenciasXCadenaCustodia(cadenaDeCustodiaDTO);
    }

    @Override
    public List<EvidenciaDTO> consultarEvidenciasResguardadasPorUsario(
            UsuarioDTO usrFirmado) throws NSJPNegocioException {
        return evidenciasXUsuarioService.consultarEvidenciasResguardadasPorUsuario(usrFirmado);
    }

	@Override
	public Long guardarEvidencia(EvidenciaDTO evidenciaDTO)
			throws NSJPNegocioException {
		return guardarEvidenciaService.guardarEvidencia(evidenciaDTO);
	}

    @Override
    public List<EvidenciaDTO> consultarEvidenciasResguardadasPorUsario(
            UsuarioDTO usrFirmado, CadenaDeCustodiaDTO cadena)
            throws NSJPNegocioException {
        return evidenciasXUsuarioService.consultarEvidenciasResguardadasPorUsuario(usrFirmado, cadena);
    }

	@Override
	public List<EvidenciaDTO> consultarEvidenciaXCadenaCustodiaYPeritoDeSolicitudPericial(
			CadenaDeCustodiaDTO cadena, SolicitudPericialDTO solicitud)
			throws NSJPNegocioException {
		return evidenciasXCadenaCustodiaService.consultarEvidenciaXCadenaCustodiaYPeritoDeSolicitudPericial(cadena, solicitud);
	}

	@Override
	public List<FuncionarioDTO> consultarPeritosSinAsignacionEnCadenaDeCustodia(
			CadenaDeCustodiaDTO cadena, SolicitudPericialDTO solicitud)
			throws NSJPNegocioException {
		return evidenciasXCadenaCustodiaService.consultarPeritosSinAsignacionEnCadenaDeCustodia(cadena, solicitud);
	}

	@Override
	public void asignarEvidenciaASolicitudPericial(EvidenciaDTO evidencia,
			SolicitudPericialDTO solicitud, FuncionarioDTO perito)
			throws NSJPNegocioException {		
		asignarEvidenciaService.asignarEvidenciaASolicitudPericial(evidencia, solicitud, perito);
	}

	@Override
	public List<EvidenciaDTO> consultarEvidenciasXCadenaCustodiaYEstatus(
			CadenaDeCustodiaDTO custodiaDTO, Long estatus)
			throws NSJPNegocioException {
		return consultarEvidenciasXEstatusService.consultarEvidenciasXCadenaCustodiaYEstatus(custodiaDTO,estatus);
	}

	@Override
	public void asociarDestinoLegal(EvidenciaDTO evidenciaDTO, Long destinoLegal)
			throws NSJPNegocioException {
		asociarDestinoLegalService.asociarDestinoLegal(evidenciaDTO,destinoLegal);
	}

    @Override
    public void registrarBajaEvidencia(List<EvidenciaDTO> evidenciasDto) throws NSJPNegocioException {
        registrarBajaEvidenciaService.registrarBajaEvidencia(evidenciasDto);
    }

    @Override
    public EvidenciaDTO consultaEvidencia(Long idEvidencia)throws NSJPNegocioException {
        return consultarEvidenciasXEstatusService.consultaEvidencia(idEvidencia);
    }

	@Override
	public List<EvidenciaDTO> consultarEvidenciasXIdSolicitud(
			Long idSolicitud) {
		return consultarEvidenciasXEstatusService.consultarEvidenciasXIdSolicitud(idSolicitud);		
	}

	@Override
	public List<EvidenciaDTO> consultarEvidenciasXAlmacen(
			UsuarioDTO usuarioDTO, Long estatusEv, CasoDTO casoDTO, Long idAmacen)
			throws NSJPNegocioException {
		return consultarEvidenciasXAlmacenService.consultarEvidenciasXAlmacenXEstatus(usuarioDTO, estatusEv, casoDTO, idAmacen);
	}

	@Override
	public List<DocumentoDTO> consultarDocumentosXEslabonesDEvidencia(
			EvidenciaDTO evidenciaDTO) throws NSJPNegocioException {
		return consultarDocumentosDEvidenciaService.consultarDocumentosXEslabonesDEvidencia(evidenciaDTO);
	}

	@Override
	public List<EvidenciaDTO> consultarEvidenciasNoDevueltas(
			UsuarioDTO usuarioDTO) throws NSJPNegocioException {
		return consultarEvidenciasNoDevueltasService.consultarEvidenciasNoDevueltas(usuarioDTO);
	}

	@Override
	public Long cambiarEstatusEvidencia(EvidenciaDTO evidenciaDTO)
			throws NSJPNegocioException {
		return cambiarEstatusEvidenciaService.cambiarEstatusEvidencia(evidenciaDTO);
	}
	
	@Override
	public Boolean eliminarEvidencia(Long idEvidencia) throws NSJPNegocioException{
		return registrarBajaEvidenciaService.eliminarEvidencia(idEvidencia);
	}

}

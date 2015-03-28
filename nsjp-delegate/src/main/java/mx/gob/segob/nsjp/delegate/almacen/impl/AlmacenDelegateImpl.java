/**
 * Nombre del Programa : AlmacenDelegateImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 29-jul-2011
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
package mx.gob.segob.nsjp.delegate.almacen.impl;

import java.util.List;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.almacen.AlmacenDelegate;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.service.almacen.ConsultarAlmacenService;
import mx.gob.segob.nsjp.service.almacen.ConsultarAlmacenesPorTipoService;
import mx.gob.segob.nsjp.service.almacen.ConsultarEvidenciaPorAlmacenService;
import mx.gob.segob.nsjp.service.almacen.ConsultarMovimientosDeObjetosAlmacenService;
import mx.gob.segob.nsjp.service.almacen.RegistrarAlmacenService;
import mx.gob.segob.nsjp.service.almacen.ValidarCadenaCustodiaEnAlmacenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
@Service("almacenDelegate")
public class AlmacenDelegateImpl implements AlmacenDelegate {

    @Autowired
    private ConsultarAlmacenesPorTipoService consultarAlmacenesPorTipoService;
    @Autowired
    private ConsultarEvidenciaPorAlmacenService consultarEvidenciaPorAlmacenService;
    @Autowired
    private RegistrarAlmacenService registrarAlmacenService;
    @Autowired
    private ConsultarAlmacenService consultarAlmacenService;
    @Autowired
    private ConsultarMovimientosDeObjetosAlmacenService consultarMovimientosDeObjetosAlmacenService; 

    @Autowired
    private ValidarCadenaCustodiaEnAlmacenService validarCadenaCustodiaEnAlmacenService;

    @Override
    public List<AlmacenDTO> consultarAlmacenesPorTipo(Long idTipoAlmacen,
            Boolean estatus, CasoDTO casoDto) throws NSJPNegocioException {
        return consultarAlmacenesPorTipoService.consultarAlmacenesPorTipo(
                idTipoAlmacen, estatus, casoDto);
    }

    @Override
    public List<EvidenciaDTO> consultarEvidenciaPorAlmacen(AlmacenDTO almacenDto)
            throws NSJPNegocioException {
        return consultarEvidenciaPorAlmacenService.consultarEvidenciaPorAlmacen(almacenDto);
    }

    @Override
    public Long registrarAlmacen(AlmacenDTO almacenDto) throws NSJPNegocioException {
        return registrarAlmacenService.registrarAlmacen(almacenDto);
    }

    @Override
    public boolean validarCadenaCustodiaEnAlmacen(CadenaDeCustodiaDTO cadenaDeCustodiaDto,
            AlmacenDTO almacenDto) throws NSJPNegocioException {
        return validarCadenaCustodiaEnAlmacenService.
                validarCadenaCustodiaEnAlmacen(cadenaDeCustodiaDto, almacenDto);
    }

	@Override
	public AlmacenDTO obtenerAlmacenDelExpediente(ExpedienteDTO expedienteDTO)
			throws NSJPNegocioException {		
		return consultarAlmacenService.obtenerAlmacenDelExpediente(expedienteDTO);
	}

	@Override
	public List<AlmacenDTO> consultarAlmacenesExpedientePorEstatus(
			Boolean estatus, Boolean tipo) throws NSJPNegocioException {		
		return consultarAlmacenService.consultarAlmacenesExpedientePorEstatus(estatus, tipo);
	}

	@Override
	public void cambiaExpedienteDeAlmacen(AlmacenDTO almacenActual,
			AlmacenDTO almacenNuevo, ExpedienteDTO expedienteDTO)
			throws NSJPNegocioException {
		
	}

	@Override
	public AlmacenDTO asociarExpedienteAlmacen(AlmacenDTO almacenDTO,
			ExpedienteDTO expedienteDTO) throws NSJPNegocioException {		
		return registrarAlmacenService.asociarExpedienteAlmacen(almacenDTO, expedienteDTO);
	}

	/**
	 * Servicio que permite consultar los movimientos de Objetos 
	 * del almacen. 
	 * Se consultar las evidencias asociadas a la Cadena Custodia, que asu vez, esta
	 * relacionada a un Expediente (Número de Expediente).
	 * 
	 * @param numeroExpediente
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<EvidenciaDTO> consultarEvidenciasAsociadasCadenaCustodiaPorNumeroExpediente( String numeroExpediente)  throws NSJPNegocioException{
		return consultarMovimientosDeObjetosAlmacenService.consultarEvidenciasAsociadasCadenaCustodiaPorNumeroExpediente(numeroExpediente);
	}
	
	@Override
	public AlmacenDTO consultarDetalleAlmacenPorId (Long almadenId)throws NSJPNegocioException{
		AlmacenDTO resp = new AlmacenDTO();
		resp = consultarAlmacenService.consultarDetalleAlmacenPorId (almadenId);
		return resp;
	}
	
}

/**
 * Nombre del Programa : CasoDelegateImpl.java
 * Autor                            : cesarAgustin
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 28 Apr 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementacion de metodos para conectar la vista con servicio de Caso
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
package mx.gob.segob.nsjp.delegate.caso.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.caso.CasoDelegate;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.caso.FiltroCasoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.FiltroExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.service.caso.ActualizarEtapaCasoService;
import mx.gob.segob.nsjp.service.caso.BuscarCasoService;
import mx.gob.segob.nsjp.service.caso.EnviarReplicaCasoService;
import mx.gob.segob.nsjp.service.expediente.VincularExpedienteACasoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementacion de metodos para conectar la vista con servicio de Caso.
 * 
 * @version 1.0
 * @author cesarAgustin
 * 
 */
@Service("casoDelegate")
public class CasoDelegateImpl implements CasoDelegate {

    @Autowired
    private BuscarCasoService buscarCasoService;

    @Autowired
    private ActualizarEtapaCasoService actualizarEtapaCasoService;
    
    @Autowired
    private EnviarReplicaCasoService enviarReplicaService;

    @Autowired
    private VincularExpedienteACasoService vincularExpedienteACasoService;
    
    @Override
     public List<CasoDTO> consultarCasoPorNumero(CasoDTO casoDTO)
            throws NSJPNegocioException {

        return this.buscarCasoService.buscarCasoPorNumero(casoDTO);
    }

    @Override
    public List<CasoDTO> consultarCasosPorUsuario(UsuarioDTO usr)
            throws NSJPNegocioException {
        return this.buscarCasoService.consultarCasosPorUsuario(usr);
    }

    @Override
    public List<InvolucradoDTO> buscarCasoPorFiltros(
            FiltroExpedienteDTO filtrosDTO) throws NSJPNegocioException {

        return this.buscarCasoService.buscarCasoPorFiltros(filtrosDTO);
    }

    @Override
    public List<CasoDTO> buscarCasoPorFiltros(FiltroCasoDTO filtrosDTO)
            throws NSJPNegocioException {
        return this.buscarCasoService.buscarCasoPorFiltros(filtrosDTO);
    }
    /*
     * (non-Javadoc)
     * 
     * @see mx.gob.segob.nsjp.delegate.caso.CasoDelegate#
     * consultarCasosConEventosHistoricos
     * (mx.gob.segob.nsjp.dto.usuario.UsuarioDTO)
     */
    @Override
    public List<CasoDTO> consultarCasosConEventosHistoricos(UsuarioDTO usr)
            throws NSJPNegocioException {
        return buscarCasoService.consultarCasosConEventosHistoricos(usr);
    }

    @Override
    public CasoDTO consultarCasoPorExpediente(ExpedienteDTO expedienteDTO)
            throws NSJPNegocioException {
        return buscarCasoService.consultarCasoPorExpediente(expedienteDTO);
    }

    @Override
    public void actualizarEtapaCaso(CasoDTO casoDto, ValorDTO nuevaEtapaDto)
            throws NSJPNegocioException {
        actualizarEtapaCasoService.actualizarEtapaCaso(casoDto, nuevaEtapaDto);
    }

    @Override
    public ValorDTO consultarEtapaAnteriorCaso(CasoDTO casoDto)
            throws NSJPNegocioException {
        return buscarCasoService.consultarEtapaAnteriorCaso(casoDto);
    }

    /**
     * Método para enviar la replica del caso a las demás intituciones.
     * 
     * @param expdienteConCaso
     * @throws NSJPNegocioException
     */
    public void enviarReplicaCaso(ExpedienteDTO expdienteConCaso)
            throws NSJPNegocioException {
    	//Actualizar la bandera que indica que el expediente ha sido replicado
    	enviarReplicaService.actualizarExpedienteReplicado(expdienteConCaso.getExpedienteId());
        enviarReplicaService.enviarReplicaCaso(expdienteConCaso);
    }

	
    @Override
	public List<CasoDTO> consultarCasos(String numeroGeneralCaso) throws NSJPNegocioException{
		return vincularExpedienteACasoService.consultarCasos(numeroGeneralCaso);
	}
    
    @Override
	public void vincularExpedienteConCaso(ExpedienteDTO expediente, CasoDTO caso,
            InvolucradoDTO involucradoPG) throws NSJPNegocioException{
		vincularExpedienteACasoService.vincularExpedienteACaso(expediente, caso, involucradoPG);
	}

}

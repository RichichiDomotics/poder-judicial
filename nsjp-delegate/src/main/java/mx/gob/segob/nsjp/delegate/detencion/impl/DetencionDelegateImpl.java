/**
 * Nombre del Programa : DetencionDelegateImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 05-jul-2011
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
package mx.gob.segob.nsjp.delegate.detencion.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.detencion.DetencionDelegate;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.detencion.PertenenciaDTO;
import mx.gob.segob.nsjp.dto.involucrado.DetencionDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.service.detencion.AdministrarPertenenciaDetencionService;
import mx.gob.segob.nsjp.service.detencion.ConsultarDetencionService;
import mx.gob.segob.nsjp.service.detencion.RegistrarDetencionPersonaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @version 1.0
 * @author Jacob Lobaco
 */
@Service("detencionDelegate")
public class DetencionDelegateImpl implements DetencionDelegate {

    @Autowired
    private ConsultarDetencionService consultarDetencionService;
    @Autowired
    private RegistrarDetencionPersonaService registrarDetencionPersonaService;

    @Autowired
    private AdministrarPertenenciaDetencionService pertenenciaService;
    /**
     * {@inheritDoc}
     */
    @Override
    public DetencionDTO consultarDetencion(Long idInvolucrado, String expediente)
            throws NSJPNegocioException {
        return consultarDetencionService.consultarDetencion(idInvolucrado,
                expediente);
    }

    public Long registrarDetencionDePersona(DetencionDTO detencion, CasoDTO caso)
            throws NSJPNegocioException {
        return registrarDetencionPersonaService
                .registrarDetencionPersonaService(detencion, caso);
    }

    public DetencionDTO recibirDetenido(DetencionDTO detencion, CasoDTO caso)
            throws NSJPNegocioException {
        return registrarDetencionPersonaService
                .recibirDetenido(detencion, caso);
    }

    @Override
    public Long registrarPertenecia(PertenenciaDTO input)
            throws NSJPNegocioException {
        return pertenenciaService.registrarPertenecia(input);
    }

    @Override
    public List<PertenenciaDTO> consultarPertenenciaByDetencion(Long idDetencion)
            throws NSJPNegocioException {
        return pertenenciaService.consultarPertenenciaByDetencion(idDetencion);
    }

    @Override
    public void eliminarInvolucrado(InvolucradoDTO invo2Del)
            throws NSJPNegocioException {
        this.registrarDetencionPersonaService.eliminarInvolucrado(invo2Del);
        
    }

    @Override
    public Long registrarLugarDetencion(DetencionDTO detencion)
            throws NSJPNegocioException {
        return this.registrarDetencionPersonaService.registrarLugarDetencion(detencion);
    }

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.detencion.DetencionDelegate#actualizarPertenencia(mx.gob.segob.nsjp.dto.detencion.PertenenciaDTO)
	 */
	@Override
	public void actualizarPertenencia(PertenenciaDTO pertenenciaDTO) {
		pertenenciaService.actualizarPertenencia(pertenenciaDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.detencion.DetencionDelegate#eliminarPertenencia(mx.gob.segob.nsjp.dto.detencion.PertenenciaDTO)
	 */
	@Override
	public void eliminarPertenencia(PertenenciaDTO pertenenciaDTO) {
		pertenenciaService.eliminarPertenencia(pertenenciaDTO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.detencion.DetencionDelegate#consultarPertenencia(mx.gob.segob.nsjp.dto.detencion.PertenenciaDTO)
	 */
	@Override
	public PertenenciaDTO consultarPertenenciaPorId(PertenenciaDTO pertenenciaDTO) {
		return pertenenciaService.consultarPertenenciaPorId(pertenenciaDTO);
	}
    
}

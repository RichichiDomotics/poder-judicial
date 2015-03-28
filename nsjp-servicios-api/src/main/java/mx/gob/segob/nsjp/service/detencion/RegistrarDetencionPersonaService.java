package mx.gob.segob.nsjp.service.detencion;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.involucrado.DetencionDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;

public interface RegistrarDetencionPersonaService {

    public Long registrarDetencionPersonaService(DetencionDTO detencion,
            CasoDTO caso) throws NSJPNegocioException;

    public DetencionDTO recibirDetenido(DetencionDTO detencion, CasoDTO caso)
            throws NSJPNegocioException;

    /**
     * Método para eliminar un involucrado.
     * 
     * @param invo2Del
     *            requerido <b>elementoId</b>
     * @throws NSJPNegocioException
     */
    void eliminarInvolucrado(InvolucradoDTO invo2Del)
            throws NSJPNegocioException;

    /**
     * Registra el lugar dela detencion.
     * 
     * @param detencion
     *            obligatorios <b>detencionId</b> y <b>lugarDetencionDTO</b>.
     * @return El id del lugar registrado
     * @throws NSJPNegocioException
     */
    Long registrarLugarDetencion(DetencionDTO detencion)
            throws NSJPNegocioException;
}

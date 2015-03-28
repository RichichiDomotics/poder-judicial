/**
 * 
 */
package mx.gob.segob.nsjp.service.documento;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.AvisoHechoDelictivoDTO;

/**
 * @author adrian
 * 
 */
public interface GuardarAvisoHDelictivoService {

    /**
     * Operación que realiza la funcionalidad de guardar/actualizar un Aviso de
     * Hecho Delictivo
     * 
     * @param avisoDTO
     * @return
     * @throws NSJPNegocioException
     */
    Long guardarAvisoHDelictivo(AvisoHechoDelictivoDTO avisoDTO)
            throws NSJPNegocioException;

    /**
     * Registra un Aviso Hecho Delictivo
     * 
     * @author cesarAgustin
     * @param avisoHechoDelictivoDTO
     * @return
     * @throws NSJPNegocioException
     */
    AvisoHechoDelictivoDTO ingresarAvisoHechoDeictivoSSP(
            AvisoHechoDelictivoDTO avisoHechoDelictivoDTO)
            throws NSJPNegocioException;

    /**
     * Operación que permite asignar un motivo de rechazo y actualizar el
     * estatus de un aviso de hecho delictivo
     * 
     * @param avisoId
     * @param motivoRechazo
     * @throws NSJPNegocioException
     */
    void asignarMotivoRechazoHD(Long avisoId, Long motivoRechazo)
            throws NSJPNegocioException;
    /**
     * Atiende un aviso de hecho delictivo generando un número de expediente
     * para el expediente al que está relacionado el aviso de hecho delictivo.
     * 
     * @param input
     *            Requeridos: documentoId y usuario
     * @throws NSJPNegocioException
     */
    void atenderAvisoHechoDelictivo(AvisoHechoDelictivoDTO input)
            throws NSJPNegocioException;
    /**
     * envía el aviso a procuraduria.
     * 
     * @param avisoDTO requerido <b>documentoId</b>
     * @throws NSJPNegocioException
     */
    public void enviarAvisoHDelictivo(AvisoHechoDelictivoDTO avisoDTO)
            throws NSJPNegocioException;
}

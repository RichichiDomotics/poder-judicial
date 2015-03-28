/**
 * Nombre del Programa : DetencionDelegate.java
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
package mx.gob.segob.nsjp.delegate.detencion;


import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.detencion.PertenenciaDTO;
import mx.gob.segob.nsjp.dto.involucrado.DetencionDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;

/**
 * Contrato del delegate para los metodos de comunicacion de Detencion
 * entre la vista y los servicios.
 * @version 1.0
 * @author Jacob Lobaco
 */
public interface DetencionDelegate {

    /**
     * Consulta la detencion vigente para un involucrado.
     * Dado que en el caso de uso se pasa el expediente como cadena aqui
     * se respeta la firma, sin embargo esta no es requerida y puede usarse null.
     * @param idInvolucrado El id del involucrado del que se consultara la
     * detencion vigente, es decir aquella que no tenga asignada una fecha de
     * fin.
     * @param expediente Cadena que puede o no tener informacion ya que no se
     * utiliza en el servicio.
     * @return La detencion vigente para el involucrado o {@code null} es caso
     * que no exista una detencion vigente.
     * @throws NSJPNegocioException En caso que {@code idInvolucrado} sea nulo.
     */
    DetencionDTO consultarDetencion(Long idInvolucrado, String expediente)
            throws NSJPNegocioException;

    /**
     * Registra una detenci&oacute;n 
     * @param detencion informaci&oacute;n de la detenci&oacute;n
     * @return identificador de la nueva detenci&oacute;n
     * @throws NSJPNegocioException
     */
    Long registrarDetencionDePersona(DetencionDTO detencion, CasoDTO caso)throws NSJPNegocioException;

    /**
     * Registra una recepcion de detenido
     * @param detencion informaci&oacute;n de la detenci&oacute;n
     * @return identificador de la nueva detenci&oacute;n
     * @throws NSJPNegocioException
     */
    public DetencionDTO recibirDetenido(DetencionDTO detencion, CasoDTO caso) throws NSJPNegocioException;
    /**
     * 
     * @param input
     * @return
     */
    Long registrarPertenecia(PertenenciaDTO input) throws NSJPNegocioException;
    /**
     * 
     * @param idDetencion
     * @return
     * @throws NSJPNegocioException
     */
    List<PertenenciaDTO> consultarPertenenciaByDetencion(Long idDetencion) throws NSJPNegocioException;
    /**
     * M&eacute;todo para eliminar un involucrado.
     * @param invo2Del requerido <b>elementoId</b>
     * @throws NSJPNegocioException
     */
    void eliminarInvolucrado(InvolucradoDTO invo2Del) throws NSJPNegocioException;
    /**
     * Registra el lugar dela detencion.
     * @param detencion obligatorios <b>detencionId</b> y <b>lugarDetencionDTO</b>.
     * @return El id del lugar registrado
     * @throws NSJPNegocioException
     */
    Long registrarLugarDetencion(DetencionDTO detencion) throws NSJPNegocioException;
    
    /**
     * M&eacute;todo utilizado para llevar a cabo la actualizaci&oacute;n de una 
     * pertenencia previamente registrada en la base de datos.
     * @param pertenenciaDTO - La pertenencia a actualizar.
     */
    public void actualizarPertenencia(PertenenciaDTO pertenenciaDTO);
    
    /**
     * M&eacute;todo utilizado para llevar a cabo la eliminaci&oacute;n f&iacute;sica
     * de una pertenencia previamente registrada en la base de datos.
     * @param pertenenciaDTO - La pertenencia a eliminar.
     */
    public void eliminarPertenencia(PertenenciaDTO pertenenciaDTO);
    
    /**
     * M&eacute;todo que lleva a cabo la consulta de una pertenencia a trav&eacute;s 
     * de su identificador.
     * @param pertenenciaDTO - DTO con el identificador de la pertenencia a consultar.
     * @return pertenenciaDTO - DTO con todos los datos de la base de datos.
     */
    public PertenenciaDTO consultarPertenenciaPorId(PertenenciaDTO pertenenciaDTO);
}

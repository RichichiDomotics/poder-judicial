/**
 * Nombre del Programa : AsignarNumeroExpedienteService.java
 * Autor                            : cesar
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 15 Apr 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Describir el objetivo de la clase de manera breve
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
package mx.gob.segob.nsjp.service.expediente;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.TurnoDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author cesar
 * 
 */
public interface AsignarNumeroExpedienteService {

	/**
     * Genera un nuevo número de expediente.<br>
     * Al generar el número guarda un expediente en la BD.
     * 
     * 
     * @param expedienteDTO
     *            Obligatorio la <b>fechaApertura</b>. <br>
     *            Opcional <b>casoDTO</b>.<br>
     *            Si <b>expedienteId</b> es <code>null</code> se generará un
     *            Expediente completo (con sU respectuvo Número), en caso
     *            contrario sólo se creará un Número de expediente asociado el
     *            expediente.
     * 
     * @return <ul>
     *         <li>expedienteId</il>
     *         <li>numeroExpediente</il>
     *         <li>fechaApertura</il>
     *         </ul>
     * @throws NSJPNegocioException
     *             En caso de ocurrir algún error.
     */
    public ExpedienteDTO asignarNumeroExpediente(ExpedienteDTO expedienteDTO)
            throws NSJPNegocioException;

    /**
     * 
     * @param tipoExpediente
     * @return
     * @throws NSJPNegocioException
     */
    public ExpedienteDTO asignarNumeroExpedienteTipo(ExpedienteDTO expedienteDTO)
            throws NSJPNegocioException;

    /**
     * Genera un nuevo número de expediente a partir de un turno.<br>
     * Al generar el número guarda un expediente en la BD.
     * 
     * @param TurnoDTO
     *            Obligatorios <b>turnoId, usuario.idUsuario</b>.
     * @return <ul>
     *         <li>expedienteId</il>
     *         <li>numeroExpediente</il>
     *         <li>fechaApertura</il>
     *         </ul>
     * @throws NSJPNegocioException
     *             En caso de ocurrir algún error.
     */
    public ExpedienteDTO asignarNumeroExpediente(TurnoDTO turno)
            throws NSJPNegocioException;

    public ExpedienteDTO asignarNumeroExpedientePenal(TurnoDTO turno) throws NSJPNegocioException;
    /**
     * Asigna un numero de expediente a una solicitud
     * @param numeroExpedienteId Numero de expediente a asignar
     * @param solicitudId Solicitud a la cuál será asignado
     */
    void asignarNumeroExpedienteASolicitud(Long numeroExpedienteId,Long solicitudId) throws NSJPNegocioException;

	public ExpedienteDTO asignarNumeroExpedienteDefensoria(
			ExpedienteDTO inputExpediente) throws NSJPNegocioException;

	
	/**
	 * Servicio para crear un nuevo de Auditoría, que permita asignar un nuevo número de expediente, número de auditoría, 
	 * considerando el registro en la relación: RelNumExpedienteAuditoria
	 * 
	 * @param listaNumeroExpedienteAuditados
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<ExpedienteDTO>  asignarNumeroExpedienteAuditoria(List<ExpedienteDTO> listaNumeroExpedienteAuditados) throws NSJPNegocioException;
	
	/**
	 * Servicio que permite la creación de un nuevo expediente, su numero Expediente "Carpeta de Ejecucion de la Sentencia"
	 * A partir del ID se obtiene el Area, funcionario, numeroExpedientePadre de la CAusa.
	 * Para generar el nuevo expediente de las sentencia, es necesario, pasar:
	 * Areas, CausaPadre (NumeroExpedienteId), TipoExpediente, Esxtatus, Funcionario y el ExpedienteID=null.
	 * TODO GBP Falta pasar la informacion de acuerdo al CU. Y Asginarlo cuando se tenga un mandamiento de tipo Sentencia.
	 * 
	 * @param expedienteId
	 * @return
	 * @throws NSJPNegocioException
	 */
	ExpedienteDTO asignarNumeroExpedienteCarpetaEjecucion(Long expedienteId) throws NSJPNegocioException;
	
}

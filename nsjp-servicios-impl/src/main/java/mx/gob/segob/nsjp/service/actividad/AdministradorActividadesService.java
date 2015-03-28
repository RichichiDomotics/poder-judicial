/**
 * Nombre del Programa : AdmisnitradorActividadesService.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 27 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Componente para el manejo de actividades.
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
package mx.gob.segob.nsjp.service.actividad;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.ActividadDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.model.Expediente;

/**
 * Componente para el manejo de actividades.<br>
 * Las actividades permiten saber la situación del <code>Expediente</code> para
 * hacer validaciones de negocio para saber las actividades y acciones que se
 * pueden ejecutar sobre el expediente.<br>
 * Esta clase nunca debe ser expuesta dado que es para el control interno del
 * expediente. 
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public interface AdministradorActividadesService {
    /**
     * Genera una nueva actividad para un expediente <b>nuevo</b>.
     * 
     * @param inputExpediente
     *            Expediente enviado desde la vista.
     * @param nuevoExpDTO
     *            Nuevo expediente a regresar a vista. Le será asignada la
     *            actividad creada.
     * @param nuevoBD
     *            Nuevo expediente a guardar en BD.
     * @throws NSJPNegocioException
     *             En casos de ocurrir algún error.
     */
    void generarActividad(ExpedienteDTO inputExpediente,
            final ExpedienteDTO nuevoExpDTO, Expediente nuevoBD)
            throws NSJPNegocioException;
    
    /**
     * Crea una nueva activdad en base a los datos de la actividad anterior:
     * Se copia el expediente
     * Tipo de actividad
     * Funcionario
     * @param actividadOriginalId ID de la actividad a copiar
     * @return Nueva actividad creada
     */
    ActividadDTO generarActividadEnBaseAOtra(Long actividadOriginalId);
    
    /**
     * Permite crear una nueva actividad en base de datos
     * @param inputExpediente: Atributos obligatorio
     * 		  - inputExpediente.ExpedienteId Representa el id del expediente que tendra la nueva actividad
     * 		  - inputExpediente.usuario.funcionario.claveFuncionario: Representa el usuario de sesion
     * @param actividadDTO
     * 		  - actividadDTO.tipoActividad : Representa el tipo de la actividad 
     * @return
     * @throws NSJPNegocioException
     */
    public ActividadDTO generarActividadAExpediente(ExpedienteDTO inputExpediente, ActividadDTO actividadDTO) throws NSJPNegocioException;    

}

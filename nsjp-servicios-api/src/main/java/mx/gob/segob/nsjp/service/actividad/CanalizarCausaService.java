/**
 * Nombre del Programa : CanalizarCausaService.java
 * Autor                            : rgama
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 11/07/2011
 * Marca de cambio        : N/A
 * Descripcion General    : Define el contrato para el servicio de canalizar causa
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

/**
 * Define el contrato para el servicio  CanazizarCausaService
 * @author rgama
 *
 */
public interface CanalizarCausaService {

    /**
     * Realiza algunas validaciones para saber si se debe de generar una nueva actividad en base de datos
     * y de esa forma queda canalizado el expediente:
     * @param inputExpediente: Atributos obligatorio
     * 		  - inputExpediente.ExpedienteId Representa el id del expediente que tendra la nueva actividad
     * 		  - inputExpediente.usuario.funcionario.claveFuncionario: Representa el usuario de sesion
     * @param actividadDTO
     * 		  - actividadDTO.tipoActividad : Representa el tipo de la actividad
     * 			* Actividades.CANALIZAR_JUSTICIA_ALTERNATIVA_RESTAURATIVA
     * 			* Actividades.CANALIZAR_UNIDAD_INVESTIGACION
     * @param bDelitoPrincipalGrave: Indica si el detlito es principal
     * @param bInputadoReincidente: Indica si el imputado es reincidente
     * @param bSeguimientoInterno:  Indica si el delito es de seguimiento interno o externo
     * @return
     * @throws NSJPNegocioException
     */	
	public ActividadDTO canalizarCausaServiceImpl(ExpedienteDTO inputExpediente, ActividadDTO actividadDTO,
			Boolean bDelitoPrincipalGrave, Boolean bInputadoReincidente, Boolean bSeguimientoInterno)
	throws NSJPNegocioException;
	
}

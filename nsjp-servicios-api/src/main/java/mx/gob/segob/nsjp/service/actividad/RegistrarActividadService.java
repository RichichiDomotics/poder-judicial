/**
* Nombre del Programa : ReegistrarActividadService.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 21/07/2011
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
package mx.gob.segob.nsjp.service.actividad;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
public interface RegistrarActividadService {

	/**
	 * Registra una actividad según el tipo para un expediente dado
	 * @param expedienteDTO: idExpediente
	 * @param funcionarioDTO: idFuncionario
	 * @param tipoActividad: idTipoActividad
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long registrarActividad(ExpedienteDTO expedienteDTO,  FuncionarioDTO funcionarioDTO,  Long tipoActividad)throws NSJPNegocioException;

}

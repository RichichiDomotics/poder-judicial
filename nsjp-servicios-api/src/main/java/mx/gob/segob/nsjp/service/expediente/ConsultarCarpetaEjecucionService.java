/**
* Nombre del Programa : ConsultarCarpetaEjecucionService.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 07/07/2011
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
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * Interfaz del servicio de negocio para la consulta de los elementos de una carpeta de ejecución
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public interface ConsultarCarpetaEjecucionService {
	
	/**
     * Consulta el numero de expediente padre (causa) de un numero de expediente hijo
     * @param numeroExpedienteId Numero de expediente del hijo
     * @return expediente padre (causa)
     * @throws NSJPNegocioException
     */
    ExpedienteDTO buscarCausaPadreDeNumeroDeExpediente(Long numeroExpedienteId) throws NSJPNegocioException;
    /**
     * Obtiene los datos generales del expediente, la causa padre y el sentenciado
     * de un Numero de Expediente ID
     * @param numeroExpedienteId ID a buscar
     * @return Expediente del tipo carpeta de ejecución encontrado
     */
    ExpedienteDTO consultarDatosGeneralesExpedienteCarpetaDeEjecucion(Long numeroExpedienteId)  throws NSJPNegocioException;

    /**
     * Servicio que se encarga de consultar los expediente de tipo "Carpeta de Ejecucion (2095)"
     * Considerando que se tiene un imputado con Situación juridica "Sentenciado(1702).
     * Se regresan los datos de :
     * -Caso (expedienteDTO.getCasoDTO())
     * -Fecha Apertura  (expedienteDTO.getFechaApertura())
     * -NumeroExpediente de Carpeta de Ejecución (expedienteDTO.getNumeroExpediente())
     * -Causa Padre (expedienteDTO.getCausaPadre().getNumeroExpediente() * Revisar si viene)
     * -Imputado  (expedienteDTO.getInputado())
     * 
     * @param estatusExpediente
     * @return
     * @throws NSJPNegocioException
     */
	List<ExpedienteDTO> consultarCarpetasEjecucionPorEstatus(List<ValorDTO> estatusExpediente,UsuarioDTO usuario) throws NSJPNegocioException ;
	
}

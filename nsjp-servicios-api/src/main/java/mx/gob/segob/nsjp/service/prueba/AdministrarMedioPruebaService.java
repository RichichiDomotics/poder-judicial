/**
* Nombre del Programa : AdministrarMedioPruebaService.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 03/10/2011
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
package mx.gob.segob.nsjp.service.prueba;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.prueba.DatoPruebaDTO;
import mx.gob.segob.nsjp.dto.prueba.MedioPruebaDTO;
import mx.gob.segob.nsjp.dto.prueba.RelacionDatoMedioPruebaDTO;

/**
 * Contrato de los servicios para registrar, modificar, consultar y relacionar los
 * Medios de Prueba de una Audiencia
 * 
 * @version 1.0
 * @author  
 */
public interface AdministrarMedioPruebaService {
	
	/**
	 * Servicio que permite registrar un Medio Prueba que puede ser:
	 * -Documento 
	 * -Persona - Elemento
	 * Es necesario indicar el Dato Prueba con el que se va a asociar.
	 * En caso de que el MP sea Documento, el servicio se encarga de almacenar la 
	 * información, incluyendo el Archivo Digital
	 * En caso de que el MP sea Elemento (Persona, Involucrado), solo es necesario el
	 * id del Elemento para hacer la relación.
	 * 
	 * Para la relación (RelacionDatoMedioPrueba) los datos son: 
	 * 	bEsAceptado => null 
	 *  dTiempoCancelación => null 
	 *   
	 * @param medioPruebaDTO
	 * @param datoPruebaDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	MedioPruebaDTO  registrarMedioPruebaConRelacionADatoPrueba( MedioPruebaDTO medioPruebaDTO, DatoPruebaDTO datoPruebaDTO) throws NSJPNegocioException;

	
	/**
	 * Servicio para relacionar un Dato Prueba con uno o varios Medios de Pruebas, previamente
	 * creados, y obviamente, no existe esa relación.
	 * 
	 * @param datoPruebaDTO
	 * @param listaMediosPruebaDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<MedioPruebaDTO>  relacionarMedioPruebaConDatoPrueba( DatoPruebaDTO datoPruebaDTO, List<MedioPruebaDTO> listaMediosPruebaDTO) throws NSJPNegocioException;
	
	/**
	 * Servicio que permite Aceptar o Cancelar el Medio Prueba, la cual se ha relacionado previamente con
	 * un Dato Prueba. Es decir, se modifica la Relación de Dato Medio Prueba en:
	 * -EsAceptado
	 * -TiempoCancelacion : Si es cancelado se debe definir el Tiempo de Cancelacion y Motivo de Cancelación
	 * 
	 * @param relacionDatoMedioPruebaDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	Long aceptarCarncelarRelacionMedioPrueba(
			RelacionDatoMedioPruebaDTO relacionDatoMedioPruebaDTO)throws NSJPNegocioException;


	/**
	 *  Servicio que permite consultar los Medios de Prueba que estén asociados al Expediente
	 * (del Dato Prueba de filtro) y 
	 * relacionados=False ==>que NO esten relacionados al Dato Prueba proporcionado.
	 * relacionados=True ==>que SI esten relacionados al Dato Prueba proporcionado.
	 * relacionados=Null ==>Todos
	 * @param datoPruebaDTO: datoPruebaId(opcional) y expedienteID
	 * @param relacionados
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<MedioPruebaDTO> consultarMediosPruebaPorDatoPrueba(
			DatoPruebaDTO datoPruebaDTO, Boolean relacionados)throws NSJPNegocioException;

	/**
	 * Servicio que consulta el detalle de un Medio Prueba
	 * @param medioPruebaId
	 * @return
	 * @throws NSJPNegocioException
	 */
	MedioPruebaDTO consultarMedioPrueba(Long medioPruebaId)throws NSJPNegocioException;

	/**
	 * Servicio que consulta los datos que se relacionan o no con  un medio prueba
	 * relacionados=False ==>que NO esten relacionados al Dato Prueba proporcionado.
	 * relacionados=True ==>que SI esten relacionados al Dato Prueba proporcionado.
	 * @param medioPruebaId
	 * @param relacionados
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<DatoPruebaDTO> consultarDatosPruebaXMedioPrueba(Long medioPruebaId, Boolean relacionados)throws NSJPNegocioException;
	
	
}

/**
* Nombre del Programa : PruebaDelegate.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 30/09/2011
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
package mx.gob.segob.nsjp.delegate.prueba;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoViewDTO;
import mx.gob.segob.nsjp.dto.prueba.DatoPruebaDTO;
import mx.gob.segob.nsjp.dto.prueba.MedioPruebaDTO;
import mx.gob.segob.nsjp.dto.prueba.RelacionDatoMedioPruebaDTO;

/**
 * Contrato del delegate para exponer los servicios relacionados con Pruebas:
 * Dato Prueba, Medio Prueba y Pruebas. 
 * @version 1.0
 * @author GustavoBP
 */	
public interface PruebaDelegate {
	
	/**
	 * Servicio que permite registrar o actualizar un objeto del tipo DatoPrueba.
	 * El numeroExpediente(CAUSA), es obligatorio para crear la relación del Dato Prueba
	 * con el Expediente.
	 * En caso de que se desee actualizar, es necesario ingresar el idDatoPruab del objeto, 
	 * caso contrario, sea nulo o un valor invalido, será registrado.
	 * En la actualización se hace validación del objeto este registrado en BD, caso contrario
	 * se manda una excepción.
	 * 
	 * @param datoPruebaDTO
	 * @param numeroExpediente
	 * @return
	 * @throws NSJPNegocioException
	 */
	DatoPruebaDTO registrarActualizarDatoPrueba(DatoPruebaDTO datoPruebaDTO, String numeroExpediente) throws NSJPNegocioException;
	
	/**
	 * Servicio que permite consultar los Datos de Pruebas asociados a un 
	 * expediente, a través de su CAUSA (NumeroExpediente)
	 * FALTA DEFINIRSE QUE OTROS FILTROS SON NECESARIOS PARA DATO PRUEBA 
	 * 
	 * @param filtroDTO
	 * @param numeroExpediente
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<DatoPruebaDTO> consultarDatosPruebaPorFiltro(DatoPruebaDTO filtroDTO, String numeroExpediente) throws NSJPNegocioException;
	
	
	/**
	 * Servicio que permite actualizar el estado del dato de Prueba a (Aceptado/Rechazado) 
	 * 
	 * @param filtroDTO
	 * @param numeroExpediente
	 * @return
	 * @throws NSJPNegocioException
	 */
	DatoPruebaDTO aceptarCancelarDatoPrueba(DatoPruebaDTO datoPruebaDTO) throws NSJPNegocioException;
	
	
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
	 * Servicio que permite aceptar o cancelar la relación de un dato con un medio de prueba.
	 * Si se cancela se debe indicar el motivo de cancelación
	 * @param relacionDatoMedioPruebaDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public Long aceptarCarncelarRelacionMedioPrueba(RelacionDatoMedioPruebaDTO relacionDatoMedioPruebaDTO)throws NSJPNegocioException;
	
	/**
	 * Servicio para relacionar un Dato Prueba con uno o varios Medios de Pruebas, previamente
	 * creados, y obviamente, no existe esa relación.
	 * 
	 * @param datoPruebaDTO
	 * @param listaMediosPruebaDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<MedioPruebaDTO>  relacionarMedioPruebaConDatoPrueba(DatoPruebaDTO datoPruebaDTO, List<MedioPruebaDTO> listaMediosPruebaDTO) throws NSJPNegocioException;
	
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
	public List<MedioPruebaDTO> consultarMediosPruebaXDatoPrueba(DatoPruebaDTO datoPruebaDTO, Boolean relacionados)throws NSJPNegocioException;

	/**
	 * Servicio que relaciona una prueba con uno o varios Involucrados
	 * 
	 * @param datoPruebaDTO: El datoPrueba debe ser una Prueba, i.e.: Es aceptada y tiene al menos un medio de prueba aceptado
	 * @param listaMediosPruebaDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public Long relacionarPruebaAInvolucrado(DatoPruebaDTO datoPruebaDTO, List<InvolucradoDTO> listaResponsables) throws NSJPNegocioException;
	
	/**
	 * Servicio para consultar los datos de prueba de una audiencia, 
	 * i.e.: Los datos de prueba del expediente sin asignación y (opcionalmente) los asignados a un grupo de probables responsables
	 * @param listaResponsables
	 * @param numeroExpediente
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<DatoPruebaDTO> consultarDatosPruebaXAudiencia(List<InvolucradoDTO> listaResponsables, String numeroExpediente) throws NSJPNegocioException;
	
	/**
	 * Servicio que consulta el detalle de un DatoPrueba
	 * @param datoPruebaId
	 * @return
	 * @throws NSJPNegocioException
	 */
	public DatoPruebaDTO consultarDatoPrueba(Long datoPruebaId)throws NSJPNegocioException;
	
	/**
	 * Servicio que consulta el detalle de un Medio Prueba
	 * @param medioPruebaId
	 * @return
	 * @throws NSJPNegocioException
	 */
	public MedioPruebaDTO consultarMedioPrueba(Long medioPruebaId)throws NSJPNegocioException;
	
	/**
	 * Servicio que consulta los datos que se relacionan o no con  un medio prueba
	 * relacionados=False ==>que NO esten relacionados al Dato Prueba proporcionado.
	 * relacionados=True ==>que SI esten relacionados al Dato Prueba proporcionado.
	 * @param medioPruebaId
	 * @param relacionados
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<DatoPruebaDTO> consultarDatosPruebaXMedioPrueba(Long medioPruebaId, Boolean relacionados)throws NSJPNegocioException;
	
	/**
	 * Servicio utilizado para mostrar los imputados de la audiencia que no tenga asociada
	 * la prueba pasada como parametro. 
	 * Se consulta primero todos los involucrados de la audiencia, se consulta los involucrados
	 * relacionados a la Prueba (Dato prueba), posteriormente, se descriminan los involucardos
	 * de las segunda lista con la lista de los imputados.
	 *  
	 * Se requiere:
	 * 		-AudienciaId
	 * 		-datoPruebaId
	 * 		-numeroExpediente
	 * @param audienciaDTO
	 * @param datoPruebaDTO
	 * @param numeroExpediente
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<InvolucradoViewDTO> obtenerImputadosAudienciaSinRelacionConPrueba(
			AudienciaDTO audienciaDTO, DatoPruebaDTO datoPruebaDTO, String numeroExpediente) throws NSJPNegocioException ;
	
	/**
	 * Servicio que consulta las pruebas asociadas a un expediente.
	 * La condición para que sea prueba es:
	 * 1.-Que el Dato Prueba sea aceptado -> para poder ser relacionado a Medio Prueba.
	 * 2.-Que exista una relación con almenos un Medio Prueba.
	 * 3.-Que la relación Medio Prueba sea aceptada.
	 *  
	 * @param numeroExpediente
	 * @return
	 * @throws NSJPNegocioException
	 */
	List<DatoPruebaDTO> consultarPruebasPorNumeroExpediente(String numeroExpediente) throws NSJPNegocioException ;
	
}

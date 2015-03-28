/**
 * Nombre del Programa : AdministrarNumeroExpedienteService.java
 * Autor                            : GustavoBP
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 27/06/2011
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

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.DelitoPersonaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.institucion.InstitucionDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * Este caso de uso permitirá al solicitante del servicio crear un número de expediente
 *  y asignarlo a un expediente, o en su caso crear un nuevo expediente en el sistema.
 *  La información que tendrá e nuevo número de expediente es:
 *    - Usuario firmado.
 *    
 *  La información que tendrá el nuevo expediente es:
 *    - Número de expediente.
 *    - Usuario firmado.
 * @version 1.0
 * @author GustavoBP
 *
 */
public interface AdministrarNumeroExpedienteService {

	/**
	 * Este servicio permitirá al solicitante crear un número de expediente y 
	 * asignarlo a un expediente, o en su caso crear un nuevo expediente en el sistema.
	 * La información que tendrá e nuevo número de expediente es:
	 *     - Usuario firmado.
	 *   La información que tendrá el nuevo expediente es:
	 *     - Número de expediente.
	 *     - Usuario firmado.
	 * Para el manejo del Número de Expediente (NE) se tiene el siguiente formato:
	 *      LLL/II/DD/UU/AAAA/C-NNNNN   Ejeplo: 000/PR/15/RBO/2011/C-12345
	 *      
	 *      Longitud máxima: 26 (caracteres incluyendo '/')
	 *      
	 * en donde se devide en 3 partes: 
	 * 
	 * Prefijo:  LLL/II/DD/UU   => 000/PR/15/RBO
	 *     Parte del NE que se podrá configurar en cuanto a la longitud.
	 *     Longitud máxima: 13 (caracteres incluyendo '/')
	 *     En donde:
	 * 			L = Carácter Libre para completar la cadena {3 caracteres máximo}
	 * 			I = Prefijo Institución {2 caracteres máximo}
	 * 			D = Prefijo Distrito {2 caracteres máximo}
	 * 			U = Prefijo Unidad {3 caracteres máximo}
	 *     
	 * Año: /AAAA/    => /2011/
	 * 	   Parte del NE que representa el año actual del sistema, y es fijo.
	 * 	   Longitud exacta: 6 (caracteres incluyendo '/')
	 * 
	 * Subfijo:  C-NNNNN    => C-12345
	 * 		Parte del NE que es fija y representa el valor consecutivo del NE.
	 * 		Longitud exacta: 7 (caracteres incluyendo '-')
	 * 		En donde:
	 * 			C = Carácter que es un consecutivo en el rango de {A-Z}
	 * 			N = Número entero que representa un consecutivo en el rango {0-9}
	 * 
	 *  //TODO GBP Falta determinar la concurrencia del servicio
	 *  
	 * @param usuarioDTO
	 * @param expedienteDTO
	 * @param institucionDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public ExpedienteDTO crearAsignarNumeroExpediente(UsuarioDTO usuarioDTO, ExpedienteDTO expedienteDTO, InstitucionDTO institucionDTO ) throws NSJPNegocioException;
	

    /**
     * Servicio que realiza la funcionalidad de consultar el último número del
     * expediente que se tiene registrado en la Institución
     *
     * Regresa el número de expediente de tipo cadena, en caso contrario devuelve null.
     *
     * @param idInstitucion
     * @return
     * @throws NSJPNegocioException
     */
    public String consultarUltimoNumeroExpediente(Long idInstitucion) throws NSJPNegocioException;

	/**
	 * Servicio que realiza la funcionalidad de crear un expediente con:
	 * 	 Fecha de creación: fecha actual del sistema.
	 *   Estatus: EstatusExpedeinte.ABIERTO
	 * Regresa un objeto de tipo Expediente, en caso contrario regresa NULL.
	 * 
	 * @return
	 * @throws NSJPNegocioException
	 */
	public ExpedienteDTO crearExpediente () throws NSJPNegocioException;

    /**
     * Servicio que realiza la funcionalidad de asociar el Número de Expediente a:
     * 	Usuario - Funcionario 
     *  Institucion - Jerarquia Organizacional.
     *  Expediente
     *  
     * @param expedienteDto
     * @throws NSJPNegocioException
     */
    public void asociarNumExpediente(ExpedienteDTO expedienteDto)
            throws NSJPNegocioException;
	
	/**
	 * Operación que realiza la funcionalidad de asociar la solicitud a la carpeta de investigación.
	 * Recibe como parámetros:
	 * - El número de expediente.
	 * - El identificador de la solicitud.
	 * Regresa verdadero en caso de haber realizado de forma correcta la asociación, 
	 * Arroja una excepción si se encontro con algún problema.
	 * 
	 * El proceso que sigue es el siguiente:
	 * 1.-Consulta la solicitud por id
     * 2.-Consulta el numero de expediente por NumeroExpediente (cadena)
     *    NOTA: El numero del expediente debe ser único, de acuerdo a la regla de negocio, 
     *          caso contrario, se arroja una excepción.
     * 3.-Asocia a la solicitud el expediente consultado.
     * 
	 * @param expedienteDTO
	 * @param idSolicitud
	 * @return
	 * @throws NSJPNegocioException
	 */
    public boolean asociarNumCarpetaASolicitud( ExpedienteDTO expedienteDTO, Long idSolicitud)throws NSJPNegocioException;

	/**
	 * A partir de un número de caso que viene de una institución externa se busca
	 * un numero de expediente correspondiente en la base de datos local:
	 * De encontrar el caso:
	 * 	Si existe solo un número de expediente entonces se retorna este
	 *  De existir más de un número de expediente entonces no se retorna nada
	 * De no encontrar caso:
	 * 	Se crea un primer expediente y un primer caso
	 * @param numeroCaso
	 * @return
	 */
	ExpedienteDTO buscarOCrearExpedienteDeInstitucionExterna(String numeroCaso) throws NSJPNegocioException;
	
	/**
     * Consulta números de expediente relacionados a un Caso de una solicitud
     * @return Numeros de expediente encontrados
     */
	List<ExpedienteDTO> consultarNumeroExpedientePorCasoDeSolicitud(Long solicitudId);
	
	/**
	 * Permite actualizar el reponsable de un conjunto de numerosExpediente
	 * @param idsNumsExpedientes: Representa una lista de ids NumeroExpediente
	 * @param idFuncionario
	 * @throws NSJPNegocioException
	 */
	public void asociarExpedientesAFuncionario(List<Long> idsNumsExpedientes, Long idFuncionario) throws NSJPNegocioException;
	
	/**
	 * Servicio que permite crear un nuevo Expediente, asi mismo copiara la información del:
	 * - Probable Responsable
	 * - Victima
	 * - Delito 
	 * descritos en delitoPersonaDTO
	 * @param delitoPersonaDTO
	 * @return ExpedienteDTO
	 * @throws NSJPNegocioException
	 */
	public ExpedienteDTO generarNuevoExpedienteUAVD(DelitoPersonaDTO delitoPersonaDTO) throws NSJPNegocioException;
	
	/**
	 * Permite crear un nuevo expediente con un nuevo numero de Expediente y su respectivo caso
	 * @param expedienteDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	public ExpedienteDTO generarNuevoExpedienteConCaso(ExpedienteDTO expedienteDTO) throws NSJPNegocioException;


	/**
	 * Servicio que permite pasar un involucrado de un expediente a otro expediente
	 * Para hacer esta nueva insercción del involucrado, se requiere setear todos los valores, 
	 * referentes a una entidad asociada al involucrado a null, es decir, su id.
	 * 
	 * @param expBD  Expediente a donde se va hacer la copia.
	 * @param aoInvolucrado Involucrado a copiar.
	 * @param aoCalidad 	Calidad del involucrado, en caso de que el involucrado no tenga asociada una calidad.
	 * @return
	 * @throws NSJPNegocioException
	 */
	InvolucradoDTO guardarInvolucradoEnExpediente(ExpedienteDTO expBD,
			InvolucradoDTO aoInvolucrado, Calidades aoCalidad) throws NSJPNegocioException;
}
